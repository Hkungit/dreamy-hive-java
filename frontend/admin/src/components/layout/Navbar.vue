<template>
  <div class="navbar">
    <div class="left-menu">
      <el-icon class="toggle-sidebar" @click="toggleSidebar">
        <Fold v-if="!isCollapse" />
        <Expand v-else />
      </el-icon>
      <breadcrumb class="breadcrumb-container" />
    </div>
    
    <div class="right-menu">
      <el-dropdown class="avatar-container" trigger="click">
        <div class="avatar-wrapper">
          <el-avatar :size="30" :src="avatar">
            <el-icon><User /></el-icon>
          </el-avatar>
          <span class="user-name">{{ displayName }}</span>
          <el-icon><CaretBottom /></el-icon>
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item>
              <el-icon><User /></el-icon>
              <span>{{ userInfo.username || '未知用户' }}</span>
            </el-dropdown-item>
            <el-dropdown-item v-if="userInfo.email">
              <el-icon><Message /></el-icon>
              <span>{{ userInfo.email }}</span>
            </el-dropdown-item>
            <el-dropdown-item divided>
              <el-icon><Setting /></el-icon>
              <span>个人设置</span>
            </el-dropdown-item>
            <el-dropdown-item @click="handleLogout">
              <el-icon><SwitchButton /></el-icon>
              <span style="color: #f56c6c">退出登录</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { computed, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessageBox, ElMessage } from 'element-plus'
import { 
  Fold, 
  Expand, 
  CaretBottom, 
  User, 
  Message, 
  Setting, 
  SwitchButton 
} from '@element-plus/icons-vue'
import { useUserStore } from '../../store/modules/user'
import { useAppStore } from '../../store/modules/app'
import Breadcrumb from './Breadcrumb.vue'

const router = useRouter()
const userStore = useUserStore()
const appStore = useAppStore()

// 侧边栏折叠状态
const isCollapse = computed(() => !appStore.sidebar.opened)

// 用户信息
const userInfo = computed(() => userStore.userInfo)
const username = computed(() => userStore.username)
const nickname = computed(() => userStore.nickname)
const avatar = computed(() => userStore.avatar)

// 显示名称：优先显示昵称，其次用户名
const displayName = computed(() => {
  return nickname.value || username.value || '管理员'
})

// 切换侧边栏折叠状态
const toggleSidebar = () => {
  appStore.toggleSidebar()
}

// 退出登录
const handleLogout = () => {
  ElMessageBox.confirm('确定要退出登录吗？', '退出确认', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning',
    center: true
  }).then(async () => {
    try {
      await userStore.logout()
      ElMessage({
        message: '退出登录成功',
        type: 'success'
      })
      router.push('/login')
    } catch (error: any) {
      console.error('退出登录失败:', error)
      ElMessage({
        message: '退出登录失败，请重试',
        type: 'error'
      })
      // 即使退出失败，也强制跳转到登录页
      router.push('/login')
    }
  }).catch(() => {
    // 用户取消退出
  })
}

// 组件挂载时获取用户信息
onMounted(async () => {
  if (userStore.isLoggedIn && !userStore.userInfo.id) {
    try {
      await userStore.getInfo()
    } catch (error) {
      console.error('获取用户信息失败:', error)
    }
  }
})
</script>

<style lang="scss" scoped>
.navbar {
  height: 60px;
  overflow: hidden;
  position: relative;
  background: #fff;
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
  display: flex;
  justify-content: space-between;
  align-items: center;
  
  .left-menu {
    display: flex;
    align-items: center;
    
    .toggle-sidebar {
      padding: 0 15px;
      height: 60px;
      font-size: 20px;
      cursor: pointer;
      transition: background 0.3s;
      display: flex;
      align-items: center;
      
      &:hover {
        background: rgba(0, 0, 0, 0.025);
      }
    }
    
    .breadcrumb-container {
      margin-left: 8px;
    }
  }
  
  .right-menu {
    display: flex;
    align-items: center;
    height: 100%;
    margin-right: 20px;
    
    .avatar-container {
      cursor: pointer;
      
      .avatar-wrapper {
        display: flex;
        align-items: center;
        padding: 5px 10px;
        border-radius: 4px;
        transition: background-color 0.3s;
        
        &:hover {
          background-color: rgba(0, 0, 0, 0.025);
        }
        
        .user-name {
          margin: 0 8px;
          color: #606266;
          font-size: 14px;
          max-width: 120px;
          overflow: hidden;
          text-overflow: ellipsis;
          white-space: nowrap;
        }
      }
    }
  }
}

:deep(.el-dropdown-menu__item) {
  display: flex;
  align-items: center;
  
  .el-icon {
    margin-right: 8px;
    font-size: 16px;
  }
}
</style>
