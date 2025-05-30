<template>
  <div class="product-form-container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>{{ isEdit ? '编辑商品' : '添加商品' }}</span>
        </div>
      </template>

      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="120px"
        class="product-form"
      >
        <el-form-item label="商品名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入商品名称" />
        </el-form-item>

        <el-form-item label="商品分类" prop="categoryId">
          <el-select 
            v-model="form.categoryId" 
            placeholder="请选择商品分类" 
            style="width: 100%"
            :loading="categoryLoading"
            @focus="handleCategoryFocus"
            clearable
          >
            <el-option
              v-for="item in categoryOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="商品价格" prop="price">
          <el-input-number
            v-model="form.price"
            :precision="2"
            :step="0.1"
            :min="0"
            style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="商品库存" prop="stock">
          <el-input-number
            v-model="form.stock"
            :min="0"
            :precision="0"
            style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="商品状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio label="1">上架</el-radio>
            <el-radio label="0">下架</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="商品主图" prop="mainImage">
          <el-upload
            class="avatar-uploader"
            action="/api/admin/upload"
            :headers="uploadHeaders"
            :show-file-list="false"
            :on-success="handleMainImageSuccess"
            :before-upload="beforeImageUpload"
          >
            <img v-if="form.mainImage" :src="form.mainImage" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>

        <el-form-item label="商品图片集" prop="images">
          <el-upload
            class="image-uploader"
            action="/api/admin/upload"
            :headers="uploadHeaders"
            list-type="picture-card"
            :file-list="imageList"
            :on-success="handleImageSuccess"
            :on-remove="handleImageRemove"
            :before-upload="beforeImageUpload"
          >
            <el-icon><Plus /></el-icon>
          </el-upload>
        </el-form-item>

        <el-form-item label="商品简介" prop="brief">
          <el-input v-model="form.brief" type="textarea" rows="3" placeholder="请输入商品简介" />
        </el-form-item>

        <el-form-item label="商品详情" prop="detail">
          <div class="editor-container">
            <!-- 这里可以集成富文本编辑器，如 TinyMCE、Quill 等 -->
            <el-input v-model="form.detail" type="textarea" rows="10" placeholder="请输入商品详情" />
          </div>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm">保存</el-button>
          <el-button @click="cancel">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import type { FormInstance, FormRules, UploadProps } from 'element-plus'
import { getProductDetail, addProduct, updateProduct } from '../../api/product'
import { getCategoryTree } from '../../api/category'

// 定义分类选项类型
interface CategoryOption {
  value: string | number
  label: string
}

// 定义图片项类型
interface ImageItem {
  name: string
  url: string
}

// 定义表单数据类型
interface ProductForm {
  id: string
  name: string
  categoryId: string
  price: number
  stock: number
  status: string
  mainImage: string
  images: string[]
  brief: string
  detail: string
}

const route = useRoute()
const router = useRouter()
const formRef = ref<FormInstance>()

// 判断是否为编辑模式
const isEdit = computed(() => {
  return route.params.id !== undefined
})

// 表单数据
const form = reactive<ProductForm>({
  id: '',
  name: '',
  categoryId: '',
  price: 0,
  stock: 0,
  status: '1',
  mainImage: '',
  images: [],
  brief: '',
  detail: ''
})

// 图片列表
const imageList = ref<ImageItem[]>([])

// 分类选项
const categoryOptions = ref<CategoryOption[]>([])

// 分类加载状态
const categoryLoading = ref(false)

// 上传请求头（携带token）
const uploadHeaders = computed(() => {
  return {
    Authorization: `Bearer ${localStorage.getItem('token')}`
  }
})

// 表单验证规则
const rules = reactive<FormRules>({
  name: [
    { required: true, message: '请输入商品名称', trigger: 'blur' },
    { min: 2, max: 100, message: '长度在 2 到 100 个字符', trigger: 'blur' }
  ],
  categoryId: [
    { required: true, message: '请选择商品分类', trigger: 'change' }
  ],
  price: [
    { required: true, message: '请输入商品价格', trigger: 'blur' }
  ],
  stock: [
    { required: true, message: '请输入商品库存', trigger: 'blur' }
  ],
  mainImage: [
    { required: true, message: '请上传商品主图', trigger: 'change' }
  ],
  brief: [
    { required: true, message: '请输入商品简介', trigger: 'blur' }
  ]
})

// 扁平化树形分类数据
const flattenCategories = (categories: any[]): any[] => {
  const result: any[] = []
  
  const flatten = (items: any[], level = 0) => {
    items.forEach(item => {
      // 添加当前分类
      result.push({
        ...item,
        level,
        displayName: '　'.repeat(level) + item.name // 添加缩进显示层级
      })
      
      // 递归处理子分类
      if (item.children && item.children.length > 0) {
        flatten(item.children, level + 1)
      }
    })
  }
  
  flatten(categories)
  return result
}

// 获取分类选项
const getCategoryOptions = async () => {
  try {
    categoryLoading.value = true
    const res = await getCategoryTree()
    // 处理树形数据结构
    const categories = res.data || []
    
    // 扁平化树形数据以便在选择器中显示
    const flatCategories = flattenCategories(categories)
    
    categoryOptions.value = flatCategories.map((item: any): CategoryOption => {
      return {
        value: item.id,
        label: item.displayName || item.name
      }
    })
    console.log('分类选项加载成功:', categoryOptions.value)
  } catch (error) {
    console.error('获取分类列表失败', error)
    ElMessage.error('获取分类列表失败')
  } finally {
    categoryLoading.value = false
  }
}

// 获取商品详情
const getDetail = async (id: string) => {
  try {
    const res = await getProductDetail(id)
    const productData = res.data
    
    // 填充表单数据 - 使用类型安全的方式
    if (productData.id) form.id = productData.id
    if (productData.name) form.name = productData.name
    if (productData.categoryId) form.categoryId = productData.categoryId
    if (productData.price !== undefined) form.price = productData.price
    if (productData.stock !== undefined) form.stock = productData.stock
    if (productData.status) form.status = productData.status
    if (productData.mainImage) form.mainImage = productData.mainImage
    if (productData.images) form.images = productData.images
    if (productData.brief) form.brief = productData.brief
    if (productData.detail) form.detail = productData.detail
    
    // 处理图片列表
    if (productData.images && productData.images.length > 0) {
      imageList.value = productData.images.map((url: string): ImageItem => {
        return {
          name: url.substring(url.lastIndexOf('/') + 1),
          url
        }
      })
    }
  } catch (error) {
    console.error('获取商品详情失败', error)
    ElMessage.error('获取商品详情失败')
  }
}

// 主图上传成功回调
const handleMainImageSuccess: UploadProps['onSuccess'] = (response) => {
  form.mainImage = response.data.url
}

// 图片集上传成功回调
const handleImageSuccess: UploadProps['onSuccess'] = (response, uploadFile) => {
  const url = response.data.url
  form.images.push(url)
  imageList.value.push({
    name: url.substring(url.lastIndexOf('/') + 1),
    url
  })
}

// 图片集移除回调
const handleImageRemove: UploadProps['onRemove'] = (uploadFile) => {
  if (uploadFile.url) {
    const index = form.images.indexOf(uploadFile.url)
    if (index !== -1) {
      form.images.splice(index, 1)
    }
  }
}

// 图片上传前的校验
const beforeImageUpload: UploadProps['beforeUpload'] = (file) => {
  const isImage = /\.(jpeg|jpg|png|gif)$/.test(file.name.toLowerCase())
  const isLt2M = file.size / 1024 / 1024 < 2

  if (!isImage) {
    ElMessage.error('上传图片只能是 JPG/PNG/GIF 格式!')
  }
  if (!isLt2M) {
    ElMessage.error('上传图片大小不能超过 2MB!')
  }
  return isImage && isLt2M
}

// 提交表单
const submitForm = async () => {
  if (!formRef.value) return
  
  await formRef.value.validate(async (valid) => {
    if (valid) {
      try {
        if (isEdit.value) {
          await updateProduct(form.id, form)
          ElMessage.success('更新商品成功')
        } else {
          await addProduct(form)
          ElMessage.success('添加商品成功')
        }
        router.push('/product')
      } catch (error: any) {
        ElMessage.error(error.message || '操作失败')
      }
    }
  })
}

// 取消操作
const cancel = () => {
  router.push('/product')
}

// 处理分类选择器聚焦事件
const handleCategoryFocus = () => {
  getCategoryOptions()
}

onMounted(() => {
  getCategoryOptions()
  
  // 如果是编辑模式，获取商品详情
  if (isEdit.value && route.params.id && typeof route.params.id === 'string') {
    getDetail(route.params.id)
  }
})
</script>

<style lang="scss" scoped>
.product-form-container {
  .card-header {
    font-weight: bold;
  }
  
  .product-form {
    max-width: 800px;
    margin: 0 auto;
  }
  
  .avatar-uploader {
    :deep(.el-upload) {
      border: 1px dashed #d9d9d9;
      border-radius: 6px;
      cursor: pointer;
      position: relative;
      overflow: hidden;
      transition: var(--el-transition-duration-fast);
      
      &:hover {
        border-color: var(--el-color-primary);
      }
    }
    
    .avatar-uploader-icon {
      font-size: 28px;
      color: #8c939d;
      width: 178px;
      height: 178px;
      text-align: center;
      line-height: 178px;
    }
    
    .avatar {
      width: 178px;
      height: 178px;
      display: block;
    }
  }
  
  .editor-container {
    border: 1px solid #ccc;
    min-height: 300px;
  }
}
</style>
