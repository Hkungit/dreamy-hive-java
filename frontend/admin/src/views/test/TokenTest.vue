<template>
  <div class="token-test-page">
    <el-card>
      <template #header>
        <div class="card-header">
          <span>Token管理测试页面</span>
        </div>
      </template>
      
      <div class="test-section">
        <h3>当前登录状态</h3>
        <p><strong>是否已登录:</strong> {{ userStore.isLoggedIn ? '是' : '否' }}</p>
        <p><strong>Token:</strong> {{ currentToken || '无' }}</p>
        <p><strong>Token名称:</strong> {{ currentTokenName }}</p>
        <p><strong>登录ID:</strong> {{ currentLoginId || '无' }}</p>
        <p><strong>用户名:</strong> {{ userStore.username || '无' }}</p>
      </div>

      <div class="test-section">
        <h3>Token操作</h3>
        <el-space>
          <el-button type="primary" @click="testLogin">测试登录</el-button>
          <el-button type="danger" @click="testLogout">测试登出</el-button>
          <el-button type="info" @click="testApiCall">测试API调用</el-button>
          <el-button @click="refreshTokenInfo">刷新信息</el-button>
        </el-space>
      </div>

      <div class="test-section">
        <h3>权限测试</h3>
        <p><strong>是否为超级管理员:</strong> {{ userStore.isSuperAdmin ? '是' : '否' }}</p>
        <p><strong>用户角色:</strong> {{ userStore.roles.join(', ') || '无' }}</p>
        <p><strong>用户权限:</strong> {{ userStore.permissions.length }} 个权限</p>
      </div>

      <div class="test-section">
        <h3>测试结果</h3>
        <el-alert 
          v-if="testMessage" 
          :title="testMessage.title"
          :type="testMessage.type"
          :description="testMessage.description"
          show-icon
          :closable="false"
        />
      </div>
    </el-card>
  </div>
</template>

<script lang="ts" setup>
import { ref, computed, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { useUserStore } from '../../store/modules/user'
import { getToken, getTokenName, getLoginId } from '../../utils/auth'
import { getUserInfo } from '../../api/user'

const userStore = useUserStore()

const testMessage = ref<{
  title: string
  type: 'success' | 'warning' | 'info' | 'error'
  description?: string
} | null>(null)

const currentToken = computed(() => getToken())
const currentTokenName = computed(() => getTokenName())
const currentLoginId = computed(() => getLoginId())

const testLogin = async () => {
  try {
    await userStore.login('admin', '123456')
    testMessage.value = {
      title: '登录测试成功',
      type: 'success',
      description: 'Token已保存到localStorage，请求头也会自动添加token'
    }
    ElMessage.success('登录成功')
  } catch (error: any) {
    testMessage.value = {
      title: '登录测试失败',
      type: 'error',
      description: error.message
    }
    ElMessage.error('登录失败')
  }
}

const testLogout = async () => {
  try {
    await userStore.logout()
    testMessage.value = {
      title: '登出测试成功',
      type: 'success',
      description: 'Token已从localStorage清除'
    }
    ElMessage.success('登出成功')
  } catch (error: any) {
    testMessage.value = {
      title: '登出测试失败',
      type: 'error',
      description: error.message
    }
    ElMessage.error('登出失败')
  }
}

const testApiCall = async () => {
  try {
    const response = await getUserInfo()
    testMessage.value = {
      title: 'API调用测试成功',
      type: 'success',
      description: `成功获取用户信息: ${JSON.stringify(response.data)}`
    }
    ElMessage.success('API调用成功')
  } catch (error: any) {
    testMessage.value = {
      title: 'API调用测试失败',
      type: 'error',
      description: error.message
    }
    ElMessage.error('API调用失败')
  }
}

const refreshTokenInfo = () => {
  testMessage.value = {
    title: '信息已刷新',
    type: 'info',
    description: '当前页面显示的token信息已更新'
  }
}

onMounted(() => {
  testMessage.value = {
    title: '页面加载完成',
    type: 'info',
    description: '可以开始测试token管理功能'
  }
})
</script>

<style lang="scss" scoped>
.token-test-page {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  font-size: 18px;
  font-weight: bold;
}

.test-section {
  margin-bottom: 20px;
  padding: 15px;
  border: 1px solid #ebeef5;
  border-radius: 4px;

  h3 {
    margin-top: 0;
    margin-bottom: 10px;
    color: #303133;
  }

  p {
    margin: 5px 0;
    line-height: 1.5;
  }

  strong {
    color: #409eff;
  }
}
</style>
