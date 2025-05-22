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
          <el-select v-model="form.categoryId" placeholder="请选择商品分类" style="width: 100%">
            <el-option
              v-for="item in categoryOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>

        <!-- Step 4: Display Attributes in Template -->
        <div v-if="categoryAttributes.length > 0" class="attributes-section">
          <h3>商品属性</h3>
          <el-form-item
            v-for="attr in categoryAttributes"
            :key="attr.id"
            :label="attr.name"
            :prop="'attributeValues.' + attr.id" 
            class="attribute-item"
          >
            <!-- Simple text input for now, as per subtask instructions -->
            <el-input 
              v-if="attr.inputType === 1"
              v-model="form.attributeValues[attr.id]" 
              :placeholder="'请输入 ' + attr.name" 
            />
            <el-select 
              v-else-if="attr.inputType === 2" 
              v-model="form.attributeValues[attr.id]" 
              :placeholder="'请选择 ' + attr.name"
              clearable
              style="width: 100%"
            >
              <el-option 
                v-for="opt in (attr.selectList || '').split(',').map(s => s.trim()).filter(s => s)" 
                :key="opt" 
                :label="opt" 
                :value="opt" 
              />
            </el-select>
            <el-select 
              v-else-if="attr.inputType === 3" 
              v-model="form.attributeValues[attr.id]" 
              multiple 
              :placeholder="'请选择 ' + attr.name"
              clearable
              style="width: 100%"
            >
              <el-option 
                v-for="opt in (attr.selectList || '').split(',').map(s => s.trim()).filter(s => s)" 
                :key="opt" 
                :label="opt" 
                :value="opt" 
              />
            </el-select>
            <!-- Fallback for unknown inputType, providing a basic input -->
            <el-input 
              v-else
              v-model="form.attributeValues[attr.id]" 
              :placeholder="'请输入 ' + attr.name + (attr.selectList ? ' (选项: ' + attr.selectList + ')' : '')" 
            />
          </el-form-item>
        </div>

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
import { ref, reactive, computed, onMounted, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { Plus } from '@element-plus/icons-vue'
import type { FormInstance, FormRules, UploadProps } from 'element-plus'
import { getProductDetail, addProduct, updateProduct } from '../../api/product'
import { getCategoryList } from '../../api/category'
import { getAttributesByCategoryId } from '../../api/attribute' // Step 1: Import API

const route = useRoute()
const router = useRouter()
const formRef = ref<FormInstance>()

// 判断是否为编辑模式
const isEdit = computed(() => {
  return route.params.id !== undefined
})

// 表单数据
const form = reactive({
  id: '',
  name: '',
  categoryId: '',
  price: 0,
  stock: 0,
  status: '1',
  mainImage: '',
  images: [] as string[],
  brief: '',
  detail: '',
  attributeValues: {} as Record<string, any> // Step 2: Add attributeValues to form
})

// Step 2: Add categoryAttributes ref
const categoryAttributes = ref<any[]>([])

// 图片列表
const imageList = ref<any[]>([])

// 分类选项
const categoryOptions = ref<any[]>([])

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

// 获取分类选项
const getCategoryOptions = async () => {
  try {
    const res = await getCategoryList()
    categoryOptions.value = res.data.map((item: any) => {
      return {
        value: item.id,
        label: item.name
      }
    })
  } catch (error) {
    console.error('获取分类列表失败', error)
  }
}

// 获取商品详情
const getDetail = async (id: string) => {
  try {
    const res = await getProductDetail(id)
    const productData = res.data
    
    // 填充表单数据
    Object.keys(form).forEach(key => {
      if (key in productData) {
        // Exclude attributeValues from this generic loop, handle it specifically
        if (key !== 'attributeValues') {
          form[key as keyof typeof form] = productData[key];
        }
      }
    })

    // Step 5 (modified): Populate form.attributeValues if productData has it.
    if (productData.attributeValues) {
      form.attributeValues = { ...productData.attributeValues };
    }

    // Step 3 (modified for onMounted/edit mode): Call fetchCategoryAttributes after form.categoryId is set
    // and after potentially loading form.attributeValues from productData.
    if (isEdit.value && form.categoryId) {
      await fetchCategoryAttributes(form.categoryId);
    }
    
    // 处理图片列表
    if (productData.images && productData.images.length > 0) {
      imageList.value = productData.images.map((url: string) => {
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

// Step 3: Create fetchCategoryAttributes function
const fetchCategoryAttributes = async (categoryId: string) => {
  categoryAttributes.value = []; // Clear previous attributes
  if (!categoryId) { // If categoryId is cleared, ensure attributes are cleared
    form.attributeValues = {}; // Also clear values
    return;
  }
  try {
    const res = await getAttributesByCategoryId(categoryId);
    categoryAttributes.value = res.data || [];
    
    // Initialize form.attributeValues for newly fetched attributes
    // For now, initialize all as empty strings, assuming simple el-input display
    const newAttributeValues = { ...form.attributeValues }; // Preserve existing values if any (e.g. from loaded product)
    categoryAttributes.value.forEach(attr => {
      if (!Object.prototype.hasOwnProperty.call(newAttributeValues, attr.id)) {
        newAttributeValues[attr.id] = ''; // Default to empty string for el-input
      }
    });
    form.attributeValues = newAttributeValues;

  } catch (error) {
    console.error('获取分类属性失败', error);
    ElMessage.error('获取分类属性失败');
    // On error, attributes are cleared. Consider if form.attributeValues should also be cleared.
    // For now, retain potentially loaded values, or clear them: form.attributeValues = {};
  }
};

// Step 3: Watch for categoryId changes
watch(() => form.categoryId, (newCategoryId, oldCategoryId) => {
  // Check if newCategoryId is different from oldCategoryId to prevent unnecessary calls
  // Also check if newCategoryId is truthy
  if (newCategoryId && newCategoryId !== oldCategoryId) {
    // When category changes, it's a good practice to clear attribute values 
    // that might be from a previously selected category.
    form.attributeValues = {}; 
    fetchCategoryAttributes(newCategoryId);
  } else if (!newCategoryId) {
    // If categoryId is cleared (e.g., user deselects the category)
    categoryAttributes.value = [];
    form.attributeValues = {}; // Clear attributes and their values
  }
});

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
  const index = form.images.indexOf(uploadFile.url)
  if (index !== -1) {
    form.images.splice(index, 1)
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

onMounted(() => {
  getCategoryOptions()
  
  // 如果是编辑模式，获取商品详情. fetchCategoryAttributes is now called within getDetail.
  if (isEdit.value && route.params.id) {
    await getDetail(route.params.id as string);
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

  .attributes-section {
    h3 { 
      font-size: 16px;
      margin-bottom: 15px;
      margin-left: 10px; // Align closer to el-form-item content
      font-weight: 600;
      color: #606266; // Standard text color
      padding-bottom: 5px;
      border-bottom: 1px solid #eee; // Separator for the header
    }
    .attribute-item { // Class for individual attribute form items
       margin-bottom: 18px; // Standard el-form-item margin is 22px, can adjust if needed
    }
    margin-top: 10px; 
    padding-top: 10px;
    border-top: 1px solid #ebeef5; // Visual separator for the whole section
    margin-bottom: 22px; // Space before next form item
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
