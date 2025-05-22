<template>
  <div class="dashboard-container">
    <el-row :gutter="20">
      <!-- 统计卡片 -->
      <el-col :xs="24" :sm="12" :md="6" v-for="(item, index) in statCards" :key="index">
        <el-card class="stat-card" :body-style="{ padding: '20px' }">
          <div class="card-content">
            <el-icon :size="40" :color="item.color">
              <component :is="item.icon" />
            </el-icon>
            <div class="card-info">
              <div class="card-value">{{ item.value }}</div>
              <div class="card-title">{{ item.title }}</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="chart-row">
      <!-- 销售趋势图 -->
      <el-col :xs="24" :lg="16">
        <el-card class="chart-card">
          <template #header>
            <div class="card-header">
              <span>销售趋势</span>
              <el-radio-group v-model="salesTimeRange" size="small">
                <el-radio-button label="week">本周</el-radio-button>
                <el-radio-button label="month">本月</el-radio-button>
                <el-radio-button label="year">全年</el-radio-button>
              </el-radio-group>
            </div>
          </template>
          <div class="chart-container">
            <!-- 这里将集成图表库，如 ECharts -->
            <div class="chart-placeholder">销售趋势图表</div>
          </div>
        </el-card>
      </el-col>

      <!-- 商品分类占比 -->
      <el-col :xs="24" :lg="8">
        <el-card class="chart-card">
          <template #header>
            <div class="card-header">
              <span>商品分类占比</span>
            </div>
          </template>
          <div class="chart-container">
            <!-- 这里将集成图表库，如 ECharts -->
            <div class="chart-placeholder">分类占比图表</div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="table-row">
      <!-- 最近订单 -->
      <el-col :xs="24" :lg="12">
        <el-card class="table-card">
          <template #header>
            <div class="card-header">
              <span>最近订单</span>
              <el-button type="text" @click="goToOrders">查看全部</el-button>
            </div>
          </template>
          <el-table :data="recentOrders" style="width: 100%" :max-height="300">
            <el-table-column prop="orderNo" label="订单号" width="120" />
            <el-table-column prop="customer" label="客户" width="100" />
            <el-table-column prop="amount" label="金额" width="100">
              <template #default="scope">
                ¥{{ scope.row.amount.toFixed(2) }}
              </template>
            </el-table-column>
            <el-table-column prop="status" label="状态">
              <template #default="scope">
                <el-tag :type="getOrderStatusType(scope.row.status)">
                  {{ getOrderStatusText(scope.row.status) }}
                </el-tag>
              </template>
            </el-table-column>
            <el-table-column prop="createTime" label="创建时间" width="160" />
          </el-table>
        </el-card>
      </el-col>

      <!-- 热门商品 -->
      <el-col :xs="24" :lg="12">
        <el-card class="table-card">
          <template #header>
            <div class="card-header">
              <span>热门商品</span>
              <el-button type="text" @click="goToProducts">查看全部</el-button>
            </div>
          </template>
          <el-table :data="hotProducts" style="width: 100%" :max-height="300">
            <el-table-column prop="name" label="商品名称" />
            <el-table-column prop="sales" label="销量" width="100" />
            <el-table-column prop="price" label="价格" width="100">
              <template #default="scope">
                ¥{{ scope.row.price.toFixed(2) }}
              </template>
            </el-table-column>
            <el-table-column prop="inventory" label="库存" width="100" />
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts" setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus' // Import ElMessage
import { getOrderStats } from '../../api/order' // Import getOrderStats
import { 
  ShoppingCart, 
  Goods, 
  User, 
  Money 
} from '@element-plus/icons-vue'

const router = useRouter()
const salesTimeRange = ref('month')
const loading = ref(false) // Add loading state
const dashboardStats = ref<any>({}) // Add dashboardStats state

// 统计卡片数据
const statCards = ref([
  { title: '今日订单', value: 128, icon: ShoppingCart, color: '#409EFF' },
  { title: '商品总数', value: 1024, icon: Goods, color: '#67C23A' },
  { title: '用户总数', value: 1536, icon: User, color: '#E6A23C' },
  { title: '总销售额', value: '¥15,243', icon: Money, color: '#F56C6C' }
])

// 最近订单数据
const recentOrders = ref([
  { orderNo: 'DH20250520001', customer: '张三', amount: 299.00, status: 'pending', createTime: '2025-05-20 10:23:45' },
  { orderNo: 'DH20250519002', customer: '李四', amount: 599.00, status: 'processing', createTime: '2025-05-19 15:30:22' },
  { orderNo: 'DH20250519001', customer: '王五', amount: 1299.00, status: 'completed', createTime: '2025-05-19 09:12:34' },
  { orderNo: 'DH20250518003', customer: '赵六', amount: 499.00, status: 'completed', createTime: '2025-05-18 16:45:10' },
  { orderNo: 'DH20250518002', customer: '钱七', amount: 899.00, status: 'cancelled', createTime: '2025-05-18 14:20:55' }
])

// 热门商品数据
const hotProducts = ref([
  { name: '高级蜂蜜礼盒装', sales: 256, price: 299.00, inventory: 120 },
  { name: '野生百花蜜500g', sales: 198, price: 128.00, inventory: 85 },
  { name: '有机蜂王浆100g', sales: 156, price: 168.00, inventory: 42 },
  { name: '蜂胶滴剂30ml', sales: 142, price: 98.00, inventory: 63 },
  { name: '蜂蜜柚子茶300g', sales: 135, price: 78.00, inventory: 94 }
])

// 获取订单状态类型
const getOrderStatusType = (status: string) => {
  const statusMap: Record<string, string> = {
    pending: 'warning',
    processing: 'primary',
    completed: 'success',
    cancelled: 'danger'
  }
  return statusMap[status] || 'info'
}

// 获取订单状态文本
const getOrderStatusText = (status: string) => {
  const statusMap: Record<string, string> = {
    pending: '待处理',
    processing: '处理中',
    completed: '已完成',
    cancelled: '已取消'
  }
  return statusMap[status] || '未知状态'
}

// 跳转到订单页面
const goToOrders = () => {
  router.push('/order')
}

// 跳转到商品页面
const goToProducts = () => {
  router.push('/product')
}

onMounted(() => {
  // 这里可以添加获取仪表盘数据的API调用
  // 例如: fetchDashboardData()
  fetchDashboardData();
})

// Fetch dashboard data
const fetchDashboardData = async () => {
  loading.value = true;
  try {
    const res = await getOrderStats();
    // Assuming the API directly returns the data object upon success (code === 0 handled by interceptor)
    dashboardStats.value = res.data; 

    // Update statCards based on fetched data
    const todayOrderCard = statCards.value.find(card => card.title === '今日订单');
    if (todayOrderCard) {
      todayOrderCard.value = dashboardStats.value.todayOrderCount !== undefined ? dashboardStats.value.todayOrderCount : 'N/A';
    }

    const totalSalesCard = statCards.value.find(card => card.title === '总销售额');
    if (totalSalesCard) {
      totalSalesCard.value = dashboardStats.value.totalSalesAmount !== undefined 
        ? `¥${parseFloat(dashboardStats.value.totalSalesAmount).toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 })}` 
        : 'N/A';
    }

    const productCountCard = statCards.value.find(card => card.title === '商品总数');
    if (productCountCard) {
      // Assuming getOrderStats does not return product count, keep static or set to N/A
      // If it did, it would be:
      // productCountCard.value = dashboardStats.value.totalProductCount !== undefined ? dashboardStats.value.totalProductCount : 'N/A';
      // For now, we'll let it keep its initial static value if not provided by API.
      if (dashboardStats.value.totalProductCount !== undefined) {
        productCountCard.value = dashboardStats.value.totalProductCount;
      }
      // else, it keeps its static value
    }

    const userCountCard = statCards.value.find(card => card.title === '用户总数');
    if (userCountCard) {
      // Assuming getOrderStats does not return user count, keep static or set to N/A
      // if (dashboardStats.value.totalUserCount !== undefined) {
      //   userCountCard.value = dashboardStats.value.totalUserCount;
      // }
      // For now, let it keep its initial static value if not provided by API.
      if (dashboardStats.value.totalUserCount !== undefined) {
        userCountCard.value = dashboardStats.value.totalUserCount;
      }
      // else, it keeps its static value
    }

  } catch (error) {
    console.error("获取仪表盘统计数据失败:", error);
    ElMessage.error('获取仪表盘统计数据失败');
    // Optionally set cards to 'N/A' on error too
    statCards.value.forEach(card => card.value = 'N/A');
  } finally {
    loading.value = false;
  }
};

</script>

<style lang="scss" scoped>
.dashboard-container {
  padding: 20px 0;

  .stat-card {
    margin-bottom: 20px;
    border-radius: 8px;
    box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);

    .card-content {
      display: flex;
      align-items: center;

      .card-info {
        margin-left: 20px;

        .card-value {
          font-size: 24px;
          font-weight: bold;
          color: #303133;
        }

        .card-title {
          margin-top: 5px;
          font-size: 14px;
          color: #909399;
        }
      }
    }
  }

  .chart-row {
    margin-bottom: 20px;

    .chart-card {
      height: 400px;
      margin-bottom: 20px;

      .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
      }

      .chart-container {
        height: 320px;
        display: flex;
        justify-content: center;
        align-items: center;

        .chart-placeholder {
          color: #909399;
          font-size: 16px;
        }
      }
    }
  }

  .table-row {
    .table-card {
      margin-bottom: 20px;

      .card-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
      }
    }
  }
}
</style>
