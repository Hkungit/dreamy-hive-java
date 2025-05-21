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
          <el-avatar :size="30" :src="avatar" />
          <span class="user-name">{{ username }}</span>
          <el-icon><CaretBottom /></el-icon>
        </div>
        <template #dropdown>
          <el-dropdown-menu>
            <router-link to="/profile">
              <el-dropdown-item>个人中心</el-dropdown-item>
            </router-link>
            <el-dropdown-item divided @click="logout">
              <span style="display: block">退出登录</span>
            </el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { computed } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessageBox } from 'element-plus'
import { Fold, Expand, CaretBottom } from '@element-plus/icons-vue'
import { useUserStore } from '../../store/modules/user'
import { useAppStore } from '../../store/modules/app'
import Breadcrumb from './Breadcrumb.vue'

const router = useRouter()
const userStore = useUserStore()
const appStore = useAppStore()

// 侧边栏折叠状态
const isCollapse = computed(() => !appStore.sidebar.opened)

// 用户名和头像
const username = computed(() => userStore.username || '管理员')
const avatar = computed(() => userStore.userInfo?.avatar || '')

// 切换侧边栏折叠状态
const toggleSidebar = () => {
  appStore.toggleSidebar()
}

// 退出登录
const logout = () => {
  ElMessageBox.confirm('确定要退出登录吗?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    await userStore.logout()
    router.push('/login')
  }).catch(() => {})
}
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
      height: 100%;
      font-size: 20px;
      cursor: pointer;
      transition: background 0.3s;
      
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
        
        .user-name {
          margin: 0 5px;
          color: #606266;
        }
      }
    }
  }
}
</style>
