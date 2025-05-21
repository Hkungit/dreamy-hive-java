<template>
  <div class="order-container">
    <div class="filter-container">
      <el-form :inline="true" :model="queryParams" class="form-inline">
        <el-form-item label="订单号">
          <el-input v-model="queryParams.orderNo" placeholder="请输入订单号" clearable />
        </el-form-item>
        <el-form-item label="订单状态">
          <el-select v-model="queryParams.status" placeholder="请选择订单状态" clearable>
            <el-option
              v-for="item in orderStatusOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="下单时间">
          <el-date-picker
            v-model="dateRange"
            type="daterange"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            value-format="YYYY-MM-DD"
          />
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
          <span>订单列表</span>
        </div>
      </template>

      <el-table
        v-loading="loading"
        :data="orderList"
        border
        style="width: 100%"
      >
        <el-table-column prop="orderNo" label="订单号" min-width="160" />
        <el-table-column prop="username" label="用户名" width="120" />
        <el-table-column prop="totalAmount" label="订单金额" width="120">
          <template #default="scope">
            ¥{{ scope.row.totalAmount.toFixed(2) }}
          </template>
        </el-table-column>
        <el-table-column prop="status" label="订单状态" width="120">
          <template #default="scope">
            <el-tag :type="getOrderStatusType(scope.row.status)">
              {{ getOrderStatusText(scope.row.status) }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="paymentMethod" label="支付方式" width="120">
          <template #default="scope">
            {{ getPaymentMethodText(scope.row.paymentMethod) }}
          </template>
        </el-table-column>
        <el-table-column prop="createTime" label="下单时间" width="160" />
        <el-table-column prop="payTime" label="支付时间" width="160" />
        <el-table-column label="操作" width="200" fixed="right">
          <template #default="scope">
            <el-button type="primary" link @click="handleDetail(scope.row)">
              详情
            </el-button>
            <el-button
              v-if="scope.row.status === 'pending'"
              type="primary"
              link
              @click="handleUpdateStatus(scope.row, 'processing')"
            >
              接单
            </el-button>
            <el-button
              v-if="scope.row.status === 'processing'"
              type="primary"
              link
              @click="handleUpdateStatus(scope.row, 'shipping')"
            >
              发货
            </el-button>
            <el-button
              v-if="scope.row.status === 'shipping'"
              type="success"
              link
              @click="handleUpdateStatus(scope.row, 'completed')"
            >
              完成
            </el-button>
            <el-button
              v-if="['pending', 'processing'].includes(scope.row.status)"
              type="danger"
              link
              @click="handleUpdateStatus(scope.row, 'cancelled')"
            >
              取消
            </el-button>
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
import { ref, reactive, computed, onMounted, watch } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getOrderList, updateOrderStatus } from '../../api/order'

const router = useRouter()
const loading = ref(false)
const orderList = ref([])
const total = ref(0)
const dateRange = ref<string[]>([])

// 订单状态选项
const orderStatusOptions = [
  { label: '待处理', value: 'pending' },
  { label: '处理中', value: 'processing' },
  { label: '已发货', value: 'shipping' },
  { label: '已完成', value: 'completed' },
  { label: '已取消', value: 'cancelled' },
  { label: '已退款', value: 'refunded' }
]

// 查询参数
const queryParams = reactive({
  pageNum: 1,
  pageSize: 10,
  orderNo: '',
  status: '',
  startDate: '',
  endDate: ''
})

// 监听日期范围变化
watch(dateRange, (val) => {
  queryParams.startDate = val && val.length > 0 ? val[0] : ''
  queryParams.endDate = val && val.length > 0 ? val[1] : ''
})

// 获取订单状态类型
const getOrderStatusType = (status: string) => {
  const statusMap: Record<string, string> = {
    pending: 'warning',
    processing: 'primary',
    shipping: 'info',
    completed: 'success',
    cancelled: 'danger',
    refunded: 'danger'
  }
  return statusMap[status] || 'info'
}

// 获取订单状态文本
const getOrderStatusText = (status: string) => {
  const statusMap: Record<string, string> = {
    pending: '待处理',
    processing: '处理中',
    shipping: '已发货',
    completed: '已完成',
    cancelled: '已取消',
    refunded: '已退款'
  }
  return statusMap[status] || '未知状态'
}

// 获取支付方式文本
const getPaymentMethodText = (method: string) => {
  const methodMap: Record<string, string> = {
    alipay: '支付宝',
    wechat: '微信支付',
    creditCard: '信用卡',
    cash: '货到付款'
  }
  return methodMap[method] || '未知方式'
}

// 获取订单列表
const getList = async () => {
  loading.value = true
  try {
    const res = await getOrderList(queryParams)
    orderList.value = res.data.list
    total.value = res.data.total
  } catch (error) {
    console.error('获取订单列表失败', error)
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
  queryParams.orderNo = ''
  queryParams.status = ''
  dateRange.value = []
  queryParams.startDate = ''
  queryParams.endDate = ''
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

// 查看订单详情
const handleDetail = (row: any) => {
  router.push(`/order/detail/${row.id}`)
}

// 更新订单状态
const handleUpdateStatus = async (row: any, status: string) => {
  const statusText = getOrderStatusText(status)
  
  try {
    await ElMessageBox.confirm(`确认要将订单状态更新为"${statusText}"吗?`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    
    await updateOrderStatus(row.id, status)
    ElMessage.success('订单状态更新成功')
    getList()
  } catch (error: any) {
    if (error !== 'cancel') {
      ElMessage.error(`更新失败: ${error.message}`)
    }
  }
}

onMounted(() => {
  getList()
})
</script>

<style lang="scss" scoped>
.order-container {
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
