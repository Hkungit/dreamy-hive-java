<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <el-col :span="24">
        <el-card class="welcome-card">
          <template #header>
            <div class="card-header">
              <span>欢迎使用 Dreamy Hive 管理系统</span>
            </div>
          </template>
          <div class="welcome-content">
            <el-avatar :size="60" :src="userStore.avatar">
              <el-icon><User /></el-icon>
            </el-avatar>
            <div class="user-info">
              <h3>{{ greeting }}，{{ userStore.nickname || userStore.username || '管理员' }}！</h3>
              <p>今天是 {{ currentDate }}，祝您工作愉快！</p>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-item">
            <el-icon class="stat-icon" color="#409EFF"><Goods /></el-icon>
            <div class="stat-content">
              <div class="stat-number">1,234</div>
              <div class="stat-label">商品总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-item">
            <el-icon class="stat-icon" color="#67C23A"><List /></el-icon>
            <div class="stat-content">
              <div class="stat-number">567</div>
              <div class="stat-label">订单总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-item">
            <el-icon class="stat-icon" color="#E6A23C"><User /></el-icon>
            <div class="stat-content">
              <div class="stat-number">890</div>
              <div class="stat-label">用户总数</div>
            </div>
          </div>
        </el-card>
      </el-col>
      
      <el-col :span="6">
        <el-card class="stat-card">
          <div class="stat-item">
            <el-icon class="stat-icon" color="#F56C6C"><Money /></el-icon>
            <div class="stat-content">
              <div class="stat-number">¥12,345</div>
              <div class="stat-label">今日收入</div>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" style="margin-top: 20px;">
      <el-col :span="12">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>用户信息</span>
            </div>
          </template>
          <el-descriptions :column="1" border>
            <el-descriptions-item label="用户名">
              {{ userStore.userInfo.username || '未设置' }}
            </el-descriptions-item>
            <el-descriptions-item label="昵称">
              {{ userStore.userInfo.nickname || '未设置' }}
            </el-descriptions-item>
            <el-descriptions-item label="邮箱">
              {{ userStore.userInfo.email || '未设置' }}
            </el-descriptions-item>
            <el-descriptions-item label="手机号">
              {{ userStore.userInfo.phone || '未设置' }}
            </el-descriptions-item>
            <el-descriptions-item label="状态">
              <el-tag :type="userStore.userInfo.status === 1 ? 'success' : 'danger'">
                {{ userStore.userInfo.status === 1 ? '正常' : '禁用' }}
              </el-tag>
            </el-descriptions-item>
          </el-descriptions>
        </el-card>
      </el-col>
      
      <el-col :span="12">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>权限信息</span>
            </div>
          </template>
          <div class="permission-info">
            <div class="permission-section">
              <h4>角色列表</h4>
              <el-tag 
                v-for="role in userStore.roles" 
                :key="role" 
                type="primary" 
                style="margin-right: 8px; margin-bottom: 8px;"
              >
                {{ role }}
              </el-tag>
              <span v-if="userStore.roles.length === 0" class="empty-text">暂无角色</span>
            </div>
            
            <div class="permission-section">
              <h4>权限列表</h4>
              <el-tag 
                v-for="permission in userStore.permissions" 
                :key="permission" 
                type="success" 
                size="small"
                style="margin-right: 8px; margin-bottom: 8px;"
              >
                {{ permission }}
              </el-tag>
              <span v-if="userStore.permissions.length === 0" class="empty-text">暂无权限</span>
            </div>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row style="margin-top: 20px;">
      <el-col :span="24">
        <el-card>
          <template #header>
            <div class="card-header">
              <span>功能测试</span>
            </div>
          </template>
          <el-space>
            <el-button type="primary" @click="testPermission">
              测试权限接口
            </el-button>
            <el-button type="success" @click="refreshUserInfo">
              刷新用户信息
            </el-button>
            <el-button type="warning" @click="showTokenInfo">
              查看Token信息
            </el-button>
          </el-space>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script lang="ts" setup>
import { computed, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { User, Goods, List, Money } from '@element-plus/icons-vue'
import { useUserStore } from '../../store/modules/user'
import request from '../../api/request'

const userStore = useUserStore()

// 当前日期
const currentDate = computed(() => {
  return new Date().toLocaleDateString('zh-CN', {
    year: 'numeric',
    month: 'long',
    day: 'numeric',
    weekday: 'long'
  })
})

// 问候语
const greeting = computed(() => {
  const hour = new Date().getHours()
  if (hour < 6) return '夜深了'
  if (hour < 9) return '早上好'
  if (hour < 12) return '上午好'
  if (hour < 14) return '中午好'
  if (hour < 17) return '下午好'
  if (hour < 19) return '傍晚好'
  return '晚上好'
})

// 测试权限接口
const testPermission = async () => {
  try {
    const response = await request({
      url: '/api/v1/user/test-permission',
      method: 'get'
    })
    ElMessage({
      message: response.data || '权限测试成功',
      type: 'success'
    })
  } catch (error: any) {
    ElMessage({
      message: error.message || '权限测试失败',
      type: 'error'
    })
  }
}

// 刷新用户信息
const refreshUserInfo = async () => {
  try {
    await userStore.getInfo()
    ElMessage({
      message: '用户信息刷新成功',
      type: 'success'
    })
  } catch (error: any) {
    ElMessage({
      message: '刷新用户信息失败',
      type: 'error'
    })
  }
}

// 显示Token信息
const showTokenInfo = () => {
  const tokenInfo = {
    token: userStore.token ? userStore.token.substring(0, 20) + '...' : '无',
    tokenName: localStorage.getItem('tokenName') || '无',
    isLoggedIn: userStore.isLoggedIn
  }
  
  ElMessageBox.alert(
    `Token: ${tokenInfo.token}\nToken名称: ${tokenInfo.tokenName}\n登录状态: ${tokenInfo.isLoggedIn ? '已登录' : '未登录'}`,
    'Token信息',
    {
      confirmButtonText: '确定'
    }
  )
}

onMounted(() => {
  // 页面加载时确保用户信息是最新的
  if (userStore.isLoggedIn && !userStore.userInfo.id) {
    userStore.getInfo()
  }
})
</script>

<style lang="scss" scoped>
.dashboard {
  padding: 20px;
}

.welcome-card {
  .welcome-content {
    display: flex;
    align-items: center;
    gap: 20px;
    
    .user-info {
      h3 {
        margin: 0 0 8px 0;
        color: #303133;
      }
      
      p {
        margin: 0;
        color: #909399;
      }
    }
  }
}

.stat-card {
  .stat-item {
    display: flex;
    align-items: center;
    gap: 15px;
    
    .stat-icon {
      font-size: 40px;
    }
    
    .stat-content {
      .stat-number {
        font-size: 24px;
        font-weight: bold;
        color: #303133;
        margin-bottom: 4px;
      }
      
      .stat-label {
        font-size: 14px;
        color: #909399;
      }
    }
  }
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-weight: bold;
}

.permission-info {
  .permission-section {
    margin-bottom: 20px;
    
    &:last-child {
      margin-bottom: 0;
    }
    
    h4 {
      margin: 0 0 12px 0;
      color: #303133;
      font-size: 14px;
    }
    
    .empty-text {
      color: #909399;
      font-size: 14px;
    }
  }
}
</style>
