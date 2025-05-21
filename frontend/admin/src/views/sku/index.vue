<template>
  <div class="sku-container">
    <div class="filter-container">
      <el-form :inline="true" :model="queryParams" class="form-inline">
        <el-form-item label="商品名称">
          <el-input v-model="queryParams.productName" placeholder="请输入商品名称" clearable />
        </el-form-item>
        <el-form-item label="SKU编码">
          <el-input v-model="queryParams.skuCode" placeholder="请输入SKU编码" clearable />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleQuery">查询</el-button>
          <el-button @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
    </div>

    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>SKU列表</span>
          <div>
            <el-button type="primary" @click="handleAdd" v-if="!productId">添加SKU</el-button>
            <el-button @click="goBack" v-if="productId">返回商品列表</el-button>
          </div>
        </div>
      </template>

      <el-table
        v-loading="loading"
        :data="skuList"
        border
        style="width: 100%"
      >
        <el-table-column type="index" width="50" />
        <el-table-column prop="productName" label="商品名称" min-width="180" show-overflow-tooltip v-if="!productId" />
        <el-table-column prop="skuCode" label="SKU编码" width="120" />
        <el-table-column prop="attributes" label="规格" min-width="150">
          <template #default="scope">
            <el-tag
              v-for="(attr, index) in formatAttributes(scope.row.attributes)"
              :key="index"
              size="small"
              class="attribute-tag"
            >
              {{ attr }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="price" label="价格" width="100">
          <template #default="scope">
            ¥{{ scope.row.price.toFixed(2) }}
          </template>
        </el-table-column>
        <el-table-column prop="stock" label="库存" width="80" />
        <el-table-column prop="sales" label="销量" width="80" />
        <el-table-column prop="status" label="状态" width="80">
          <template #default="scope">
            <el-tag :type="scope.row.status === '1' ? 'success' : 'info'">
              {{ scope.row.status === '1' ? '上架' : '下架' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" fixed="right">
          <template #default="scope">
            <el-button type="primary" link @click="handleEdit(scope.row)">编辑</el-button>
            <el-button
              type="primary"
              link
              :type="scope.row.status === '1' ? 'danger' : 'success'"
              @click="handleStatusChange(scope.row)"
            >
              {{ scope.row.status === '1' ? '下架' : '上架' }}
            </el-button>
            <el-button type="danger" link @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination-container">
        <el-pagination
          v-model:current-page="queryParams.pageNum"
          v-model:page-size="queryParams.pageSize"
          :page-sizes="[10, 20, 50, 100]"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </el-card>

    <!-- SKU表单对话框 -->
    <el-dialog
      v-model="dialogVisible"
      :title="dialogType === 'add' ? '添加SKU' : '编辑SKU'"
      width="600px"
      append-to-body
    >
      <el-form
        ref="formRef"
        :model="form"
        :rules="rules"
        label-width="100px"
      >
        <el-form-item label="商品" prop="productId" v-if="!productId">
          <el-select
            v-model="form.productId"
            placeholder="请选择商品"
            filterable
            remote
            :remote-method="searchProducts"
            :loading="productLoading"
            style="width: 100%"
          >
            <el-option
              v-for="item in productOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="规格" prop="attributes">
          <div class="attributes-container">
            <div v-for="(attr, index) in form.attributes" :key="index" class="attribute-item">
              <el-input
                v-model="attr.name"
                placeholder="规格名"
                style="width: 120px"
              />
              <span class="attribute-separator">:</span>
              <el-input
                v-model="attr.value"
                placeholder="规格值"
                style="width: 200px"
              />
              <el-button
                type="danger"
                circle
                icon="Delete"
                size="small"
                @click="removeAttribute(index)"
              />
            </div>
            <el-button type="primary" plain @click="addAttribute">添加规格</el-button>
          </div>
        </el-form-item>

        <el-form-item label="SKU编码" prop="skuCode">
          <el-input v-model="form.skuCode" placeholder="请输入SKU编码" />
        </el-form-item>

        <el-form-item label="价格" prop="price">
          <el-input-number
            v-model="form.price"
            :precision="2"
            :step="0.1"
            :min="0"
            style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="库存" prop="stock">
          <el-input-number
            v-model="form.stock"
            :min="0"
            :precision="0"
            style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio label="1">上架</el-radio>
            <el-radio label="0">下架</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="SKU图片" prop="image">
          <el-upload
            class="avatar-uploader"
            action="/api/admin/upload"
            :headers="uploadHeaders"
            :show-file-list="false"
            :on-success="handleImageSuccess"
            :before-upload="beforeImageUpload"
          >
            <img v-if="form.image" :src="form.image" class="avatar" />
            <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
          </el-upload>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="submitForm">确定</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, computed, onMounted, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Plus, Delete } from '@element-plus/icons-vue'
import type { FormInstance, FormRules, UploadProps } from 'element-plus'
import { getProductList } from '../../api/product'
import { getProductSkuList, addProductSku, updateProductSku, deleteProductSku } from '../../api/product'

const route = useRoute()
const router = useRouter()
const loading = ref(false)
const productLoading = ref(false)
const dialogVisible = ref(false)
const dialogType = ref<'add' | 'edit'>('add')
const formRef = ref<FormInstance>()
const skuList = ref<any[]>([])
const total = ref(0)
const productOptions = ref<any[]>([])

// 获取路由参数中的商品ID
const productId = computed(() => route.query.productId as string)

// 查询参数
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  productId: '',
  productName: '',
  skuCode: ''
})

// 表单数据
const form = reactive({
  id: '',
  productId: '',
  skuCode: '',
  attributes: [{ name: '', value: '' }],
  price: 0,
  stock: 0,
  status: '1',
  image: ''
})

// 上传请求头（携带token）
const uploadHeaders = computed(() => {
  return {
    Authorization: `Bearer ${localStorage.getItem('token')}`
  }
})

// 表单验证规则
const rules = reactive<FormRules>({
  productId: [
    { required: true, message: '请选择商品', trigger: 'change' }
  ],
  skuCode: [
    { required: true, message: '请输入SKU编码', trigger: 'blur' }
  ],
  price: [
    { required: true, message: '请输入价格', trigger: 'blur' }
  ],
  stock: [
    { required: true, message: '请输入库存', trigger: 'blur' }
  ]
})

// 监听路由参数变化
watch(() => route.query.productId, (val) => {
  if (val) {
    queryParams.productId = val as string
    getList()
  }
})

// 格式化规格属性
const formatAttributes = (attributes: any[]) => {
  if (!attributes || !attributes.length) return []
  return attributes.map(attr => `${attr.name}: ${attr.value}`)
}

// 添加规格属性
const addAttribute = () => {
  form.attributes.push({ name: '', value: '' })
}

// 移除规格属性
const removeAttribute = (index: number) => {
  form.attributes.splice(index, 1)
  if (form.attributes.length === 0) {
    addAttribute()
  }
}

// 搜索商品
const searchProducts = async (query: string) => {
  if (query) {
    productLoading.value = true
    try {
      const res = await getProductList({ name: query, pageSize: 20 })
      productOptions.value = res.data.list.map((item: any) => {
        return {
          value: item.id,
          label: item.name
        }
      })
    } catch (error) {
      console.error('搜索商品失败', error)
    } finally {
      productLoading.value = false
    }
  } else {
    productOptions.value = []
  }
}

// 获取SKU列表
const getList = async () => {
  loading.value = true
  try {
    // 如果有商品ID，则获取该商品的SKU列表
    if (productId.value) {
      const res = await getProductSkuList(productId.value)
      skuList.value = res.data
      total.value = res.data.length
    } else {
      // 否则获取所有SKU列表
      const res = await getProductList({
        ...queryParams,
        includeSkus: true
      })
      // 这里假设后端返回的数据结构包含了SKU信息
      skuList.value = res.data.list
      total.value = res.data.total
    }
  } catch (error) {
    console.error('获取SKU列表失败', error)
  } finally {
    loading.value = false
  }
}

// 查询按钮点击
const handleQuery = () => {
  queryParams.pageNum = 1
  getList()
}

// 重置查询条件
const resetQuery = () => {
  queryParams.productName = ''
  queryParams.skuCode = ''
  handleQuery()
}

// 每页条数改变
const handleSizeChange = (val: number) => {
  queryParams.pageSize = val
  getList()
}

// 当前页改变
const handleCurrentChange = (val: number) => {
  queryParams.pageNum = val
  getList()
}

// 返回商品列表
const goBack = () => {
  router.push('/product')
}

// 重置表单
const resetForm = () => {
  form.id = ''
  form.productId = productId.value || ''
  form.skuCode = ''
  form.attributes = [{ name: '', value: '' }]
  form.price = 0
  form.stock = 0
  form.status = '1'
  form.image = ''
  formRef.value?.resetFields()
}

// 添加SKU
const handleAdd = () => {
  resetForm()
  dialogType.value = 'add'
  dialogVisible.value = true
}

// 编辑SKU
const handleEdit = (row: any) => {
  resetForm()
  dialogType.value = 'edit'
  
  // 填充表单数据
  form.id = row.id
  form.productId = row.productId
  form.skuCode = row.skuCode
  form.price = row.price
  form.stock = row.stock
  form.status = row.status
  form.image = row.image
  
  // 处理规格属性
  if (row.attributes && row.attributes.length > 0) {
    form.attributes = [...row.attributes]
  }
  
  dialogVisible.value = true
}

// 修改状态
const handleStatusChange = async (row: any) => {
  const newStatus = row.status === '1' ? '0' : '1'
  const statusText = newStatus === '1' ? '上架' : '下架'
  
  try {
    await ElMessageBox.confirm(`确认要${statusText}该SKU吗?`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    
    await updateProductSku(row.productId, row.id, { status: newStatus })
    ElMessage.success(`${statusText}成功`)
    getList()
  } catch (error: any) {
    if (error !== 'cancel') {
      ElMessage.error(`${statusText}失败: ${error.message}`)
    }
  }
}

// 删除SKU
const handleDelete = async (row: any) => {
  try {
    await ElMessageBox.confirm('确认要删除该SKU吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    
    await deleteProductSku(row.productId, row.id)
    ElMessage.success('删除成功')
    getList()
  } catch (error: any) {
    if (error !== 'cancel') {
      ElMessage.error(`删除失败: ${error.message}`)
    }
  }
}

// 图片上传成功回调
const handleImageSuccess: UploadProps['onSuccess'] = (response) => {
  form.image = response.data.url
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
        // 过滤掉空的规格属性
        const filteredAttributes = form.attributes.filter(attr => attr.name && attr.value)
        const submitData = { ...form, attributes: filteredAttributes }
        
        if (dialogType.value === 'add') {
          await addProductSku(submitData.productId, submitData)
          ElMessage.success('添加SKU成功')
        } else {
          await updateProductSku(submitData.productId, submitData.id, submitData)
          ElMessage.success('更新SKU成功')
        }
        dialogVisible.value = false
        getList()
      } catch (error: any) {
        ElMessage.error(error.message || '操作失败')
      }
    }
  })
}

onMounted(() => {
  // 如果URL中有productId参数，则设置查询参数
  if (productId.value) {
    queryParams.productId = productId.value
  }
  getList()
})
</script>

<style lang="scss" scoped>
.sku-container {
  .filter-container {
    margin-bottom: 20px;
  }
  
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .pagination-container {
    margin-top: 20px;
    text-align: right;
  }
  
  .attribute-tag {
    margin-right: 5px;
    margin-bottom: 5px;
  }
  
  .attributes-container {
    .attribute-item {
      display: flex;
      align-items: center;
      margin-bottom: 10px;
      
      .attribute-separator {
        margin: 0 10px;
      }
    }
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
}
</style>
