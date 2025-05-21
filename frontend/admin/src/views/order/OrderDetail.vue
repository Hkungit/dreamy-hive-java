<template>
  <div class="order-detail-container" v-loading="loading">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>订单详情</span>
          <el-button @click="goBack">返回订单列表</el-button>
        </div>
      </template>

      <div v-if="orderDetail">
        <!-- 订单基本信息 -->
        <el-descriptions title="订单信息" :column="2" border>
          <el-descriptions-item label="订单号">{{ orderDetail.orderNo }}</el-descriptions-item>
          <el-descriptions-item label="订单状态">
            <el-tag :type="getOrderStatusType(orderDetail.status)">
              {{ getOrderStatusText(orderDetail.status) }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="下单时间">{{ orderDetail.createTime }}</el-descriptions-item>
          <el-descriptions-item label="支付方式">{{ getPaymentMethodText(orderDetail.paymentMethod) }}</el-descriptions-item>
          <el-descriptions-item label="支付时间">{{ orderDetail.payTime || '-' }}</el-descriptions-item>
          <el-descriptions-item label="发货时间">{{ orderDetail.shipTime || '-' }}</el-descriptions-item>
          <el-descriptions-item label="完成时间">{{ orderDetail.completeTime || '-' }}</el-descriptions-item>
          <el-descriptions-item label="取消时间">{{ orderDetail.cancelTime || '-' }}</el-descriptions-item>
        </el-descriptions>

        <!-- 用户信息 -->
        <el-descriptions title="用户信息" :column="1" border class="mt-20">
          <el-descriptions-item label="用户名">{{ orderDetail.username }}</el-descriptions-item>
          <el-descriptions-item label="联系电话">{{ orderDetail.phone }}</el-descriptions-item>
          <el-descriptions-item label="邮箱">{{ orderDetail.email }}</el-descriptions-item>
        </el-descriptions>

        <!-- 收货信息 -->
        <el-descriptions title="收货信息" :column="1" border class="mt-20">
          <el-descriptions-item label="收货人">{{ orderDetail.receiverName }}</el-descriptions-item>
          <el-descriptions-item label="联系电话">{{ orderDetail.receiverPhone }}</el-descriptions-item>
          <el-descriptions-item label="收货地址">
            {{ orderDetail.receiverProvince }} {{ orderDetail.receiverCity }} {{ orderDetail.receiverDistrict }} {{ orderDetail.receiverAddress }}
          </el-descriptions-item>
        </el-descriptions>

        <!-- 商品信息 -->
        <div class="mt-20">
          <div class="section-title">商品信息</div>
          <el-table :data="orderDetail.items" border style="width: 100%">
            <el-table-column prop="productImage" label="商品图片" width="100">
              <template #default="scope">
                <el-image
                  style="width: 60px; height: 60px"
                  :src="scope.row.productImage"
                  fit="cover"
                  :preview-src-list="[scope.row.productImage]"
                />
              </template>
            </el-table-column>
            <el-table-column prop="productName" label="商品名称" min-width="200" show-overflow-tooltip />
            <el-table-column prop="skuAttributes" label="规格" width="150" show-overflow-tooltip />
            <el-table-column prop="price" label="单价" width="100">
              <template #default="scope">
                ¥{{ scope.row.price.toFixed(2) }}
              </template>
            </el-table-column>
            <el-table-column prop="quantity" label="数量" width="80" />
            <el-table-column prop="subtotal" label="小计" width="120">
              <template #default="scope">
                ¥{{ (scope.row.price * scope.row.quantity).toFixed(2) }}
              </template>
            </el-table-column>
          </el-table>
        </div>

        <!-- 金额信息 -->
        <div class="amount-info mt-20">
          <div class="amount-item">
            <span>商品总金额：</span>
            <span class="amount">¥{{ orderDetail.totalProductAmount.toFixed(2) }}</span>
          </div>
          <div class="amount-item">
            <span>运费：</span>
            <span class="amount">¥{{ orderDetail.shippingFee.toFixed(2) }}</span>
          </div>
          <div class="amount-item">
            <span>优惠金额：</span>
            <span class="amount">-¥{{ orderDetail.discountAmount.toFixed(2) }}</span>
          </div>
          <div class="amount-item total">
            <span>订单总金额：</span>
            <span class="amount">¥{{ orderDetail.totalAmount.toFixed(2) }}</span>
          </div>
        </div>

        <!-- 操作按钮 -->
        <div class="operation-container mt-20">
          <el-button
            v-if="orderDetail.status === 'pending'"
            type="primary"
            @click="handleUpdateStatus('processing')"
          >
            接单
          </el-button>
          <el-button
            v-if="orderDetail.status === 'processing'"
            type="primary"
            @click="handleUpdateStatus('shipping')"
          >
            发货
          </el-button>
          <el-button
            v-if="orderDetail.status === 'shipping'"
            type="success"
            @click="handleUpdateStatus('completed')"
          >
            完成订单
          </el-button>
          <el-button
            v-if="['pending', 'processing'].includes(orderDetail.status)"
            type="danger"
            @click="handleUpdateStatus('cancelled')"
          >
            取消订单
          </el-button>
        </div>

        <!-- 订单日志 -->
        <div class="mt-20">
          <div class="section-title">订单日志</div>
          <el-timeline>
            <el-timeline-item
              v-for="(activity, index) in orderDetail.logs"
              :key="index"
              :timestamp="activity.createTime"
              :type="getLogType(activity.action)"
            >
              {{ activity.content }}
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getOrderDetail, updateOrderStatus } from '../../api/order'

const route = useRoute()
const router = useRouter()
const loading = ref(false)
const orderDetail = ref<any>(null)

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

// 获取日志类型
const getLogType = (action: string) => {
  const actionMap: Record<string, string> = {
    create: 'primary',
    pay: 'success',
    ship: 'info',
    complete: 'success',
    cancel: 'danger',
    refund: 'warning'
  }
  return actionMap[action] || 'info'
}

// 获取订单详情
const getDetail = async (id: string) => {
  loading.value = true
  try {
    const res = await getOrderDetail(id)
    orderDetail.value = res.data
  } catch (error) {
    console.error('获取订单详情失败', error)
    ElMessage.error('获取订单详情失败')
  } finally {
    loading.value = false
  }
}

// 返回订单列表
const goBack = () => {
  router.push('/order')
}

// 更新订单状态
const handleUpdateStatus = async (status: string) => {
  const statusText = getOrderStatusText(status)
  
  try {
    await ElMessageBox.confirm(`确认要将订单状态更新为"${statusText}"吗?`, '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    
    const orderId = route.params.id as string
    await updateOrderStatus(orderId, status)
    ElMessage.success('订单状态更新成功')
    getDetail(orderId)
  } catch (error: any) {
    if (error !== 'cancel') {
      ElMessage.error(`更新失败: ${error.message}`)
    }
  }
}

onMounted(() => {
  const orderId = route.params.id as string
  if (orderId) {
    getDetail(orderId)
  }
})
</script>

<style lang="scss" scoped>
.order-detail-container {
  .card-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
  }
  
  .mt-20 {
    margin-top: 20px;
  }
  
  .section-title {
    font-size: 16px;
    font-weight: bold;
    margin-bottom: 10px;
    padding-left: 10px;
    border-left: 3px solid #409EFF;
  }
  
  .amount-info {
    margin-left: auto;
    width: 300px;
    
    .amount-item {
      display: flex;
      justify-content: space-between;
      margin-bottom: 10px;
      
      .amount {
        font-weight: bold;
      }
      
      &.total {
        margin-top: 10px;
        padding-top: 10px;
        border-top: 1px solid #EBEEF5;
        font-size: 16px;
        
        .amount {
          color: #F56C6C;
        }
      }
    }
  }
  
  .operation-container {
    display: flex;
    justify-content: center;
    gap: 20px;
  }
}
</style>
