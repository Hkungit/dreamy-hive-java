<template>
  <div class="product-container">
    <div class="filter-container">
      <el-form :inline="true" :model="queryParams" class="form-inline">
        <el-form-item label="商品名称">
          <el-input v-model="queryParams.name" placeholder="请输入商品名称" clearable />
        </el-form-item>
        <el-form-item label="商品分类">
          <el-select v-model="queryParams.categoryId" placeholder="请选择分类" clearable>
            <el-option
              v-for="item in categoryOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
            <el-option label="上架" value="1" />
            <el-option label="下架" value="0" />
          </el-select>
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
          <span>商品列表</span>
          <el-button type="primary" @click="handleAdd">新增商品</el-button>
        </div>
      </template>

      <el-table
        v-loading="loading"
        :data="productList"
        border
        style="width: 100%"
      >
        <el-table-column type="index" width="50" />
        <el-table-column prop="name" label="商品名称" min-width="200" show-overflow-tooltip />
        <el-table-column prop="categoryName" label="分类" width="120">
          <template #default="scope">
            {{ scope.row.categoryName || '未知分类' }}
          </template>
        </el-table-column>
        <el-table-column prop="price" label="价格" width="100">
          <template #default="scope">
            ¥{{ scope.row.price ? scope.row.price.toFixed(2) : '0.00' }}
          </template>
        </el-table-column>
        <el-table-column prop="stock" label="库存" width="80">
          <template #default="scope">
            {{ scope.row.stock || 0 }}
          </template>
        </el-table-column>
        <el-table-column prop="sales" label="销量" width="80">
          <template #default="scope">
            {{ scope.row.sales || 0 }}
          </template>
        </el-table-column>
        <el-table-column prop="status" label="状态" width="80">
          <template #default="scope">
            <el-tag :type="scope.row.status === 1 ? 'success' : 'info'">
              {{ scope.row.status === 1 ? '上架' : '下架' }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="创建时间" width="160" />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="scope">
            <el-button type="primary" link @click="handleEdit(scope.row)">编辑</el-button>
            <el-button type="primary" link @click="handleViewSku(scope.row)">SKU管理</el-button>
            <el-button
              type="primary"
              link
              :type="scope.row.status === 1 ? 'danger' : 'success'"
              @click="handleStatusChange(scope.row)"
            >
              {{ scope.row.status === 1 ? '下架' : '上架' }}
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
  </div>
</template>

<script lang="ts" setup>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getProductList, deleteProduct, updateProduct } from '../../api/product'
import { getCategoryList } from '../../api/category'

const router = useRouter()
const loading = ref(false)
const productList = ref([])
const total = ref(0)
const categoryOptions = ref([])

// 查询参数
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  name: '',
  categoryId: '',
  status: ''
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

// 获取商品列表
const getList = async () => {
  loading.value = true
  try {
    const res = await getProductList(queryParams)
    productList.value = res.data.records
    total.value = res.data.total
  } catch (error) {
    console.error('获取商品列表失败', error)
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
  queryParams.name = ''
  queryParams.categoryId = ''
  queryParams.status = ''
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

// 新增商品
const handleAdd = () => {
  router.push('/product/add')
}

// 编辑商品
const handleEdit = (row: any) => {
  router.push(`/product/edit/${row.id}`)
}

// 查看SKU
const handleViewSku = (row: any) => {
  router.push(`/sku?productId=${row.id}`)
}

// 修改状态
const handleStatusChange = async (row: any) => {
  const newStatus = row.status === 1 ? 0 : 1
  const statusText = newStatus === 1 ? '上架' : '下架'
  
  try {
    await ElMessageBox.confirm(`确认要${statusText}该商品吗?`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    
    await updateProduct(row.id, { status: newStatus })
    ElMessage.success(`${statusText}成功`)
    getList()
  } catch (error: any) {
    if (error !== 'cancel') {
      ElMessage.error(`${statusText}失败: ${error.message}`)
    }
  }
}

// 删除商品
const handleDelete = async (row: any) => {
  try {
    await ElMessageBox.confirm('确认要删除该商品吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    
    await deleteProduct(row.id)
    ElMessage.success('删除成功')
    getList()
  } catch (error: any) {
    if (error !== 'cancel') {
      ElMessage.error(`删除失败: ${error.message}`)
    }
  }
}

onMounted(() => {
  getList()
  getCategoryOptions()
})
</script>

<style lang="scss" scoped>
.product-container {
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
}
</style>
