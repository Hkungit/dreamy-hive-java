<template>
  <div class="sidebar">
    <el-scrollbar>
      <div class="logo-container">
        <router-link to="/">
          <h1 class="logo-title">Dreamy Hive</h1>
        </router-link>
      </div>
      
      <el-menu
        :default-active="activeMenu"
        class="el-menu-vertical"
        :collapse="isCollapse"
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
        router
      >
        <sidebar-item 
          v-for="route in sidebarRoutes" 
          :key="route.path" 
          :item="route" 
          :base-path="route.path" 
        />
      </el-menu>
    </el-scrollbar>
  </div>
</template>

<script lang="ts" setup>
import { computed } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useAppStore } from '../../store/modules/app'
import SidebarItem from './SidebarItem.vue'

const route = useRoute()
const appStore = useAppStore()

// 侧边栏折叠状态
const isCollapse = computed(() => !appStore.sidebar.opened)

// 计算当前激活的菜单项
const activeMenu = computed(() => {
  const { meta, path } = route
  if (meta.activeMenu) {
    return meta.activeMenu
  }
  return path
})

// 获取路由配置中的菜单项
const router = useRouter()
const sidebarRoutes = computed(() => {
  // 过滤出需要显示在侧边栏的路由
  return router.options.routes[0].children.filter(route => !route.hidden)
})
</script>

<style lang="scss" scoped>
.sidebar {
  height: 100%;
  background-color: #304156;
  
  .logo-container {
    height: 60px;
    padding: 10px 0;
    text-align: center;
    overflow: hidden;
    
    .logo-title {
      display: inline-block;
      margin: 0;
      color: #fff;
      font-weight: 600;
      line-height: 40px;
      font-size: 18px;
      font-family: Avenir, Helvetica Neue, Arial, Helvetica, sans-serif;
      vertical-align: middle;
    }
  }
  
  .el-menu-vertical {
    border: none;
    height: calc(100% - 60px);
    width: 100%;
  }
}
</style>
