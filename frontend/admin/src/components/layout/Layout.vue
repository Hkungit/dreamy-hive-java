<template>
  <div class="app-wrapper" :class="classObj">
    <!-- 侧边栏 -->
    <sidebar class="sidebar-container" />
    
    <div class="main-container">
      <!-- 容器：顶部导航栏 (固定) -->
      <div class="fixed-navbar-container">
        <navbar />
      </div>
      
      <!-- 容器：主要内容区域 -->
      <div class="app-main-wrapper">
        <app-main />
      </div>
    </div>
  </div>
</template>

<script lang="ts" setup>
import { computed } from 'vue'
import Sidebar from './Sidebar.vue'
import Navbar from './Navbar.vue'
import AppMain from './AppMain.vue'
import { useAppStore } from '../../store/modules/app' // Adjust path if necessary

const appStore = useAppStore()

const classObj = computed(() => ({
  hideSidebar: !appStore.sidebar.opened,
  openSidebar: appStore.sidebar.opened,
  withoutAnimation: appStore.sidebar.withoutAnimation
  // mobile: device.value === 'mobile' // Placeholder for future mobile support
}))
</script>

<style lang="scss" scoped>
// Define layout variables
$sidebarWidth: 210px;
$sidebarCollapsedWidth: 64px; // Common collapsed width for Element Plus
$navbarHeight: 60px; // Common navbar height

.app-wrapper {
  position: relative;
  height: 100vh;
  width: 100%;

  // Styles for when sidebar is collapsed
  &.hideSidebar {
    .sidebar-container {
      width: $sidebarCollapsedWidth !important;
    }
    .main-container {
      margin-left: $sidebarCollapsedWidth;
    }
    .fixed-navbar-container {
      left: $sidebarCollapsedWidth;
      width: calc(100% - #{$sidebarCollapsedWidth});
    }
  }
}

.sidebar-container {
  transition: width 0.28s;
  width: $sidebarWidth !important;
  background-color: #304156; // Example color
  height: 100%;
  position: fixed;
  font-size: 0px; // Fix for potential whitespace issues if any
  top: 0;
  bottom: 0;
  left: 0;
  z-index: 1003; // Highest z-index
  overflow: hidden; // Prevent scrollbars on the sidebar itself
}

.main-container {
  min-height: 100%;
  transition: margin-left 0.28s;
  margin-left: $sidebarWidth;
  position: relative; // Establishes a positioning context if needed by children
}

.fixed-navbar-container {
  position: fixed;
  top: 0;
  left: $sidebarWidth; // Positioned to the right of the sidebar
  width: calc(100% - #{$sidebarWidth}); // Takes up the remaining width
  height: $navbarHeight;
  z-index: 1002; // Above AppMain, below Sidebar
  transition: left 0.28s, width 0.28s; // Smooth transition with sidebar collapse
  background-color: #ffffff; // Ensure navbar has a background
  box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08); // Common shadow for navbar
}

.app-main-wrapper {
  /* This wrapper ensures AppMain content starts below the fixed navbar */
  padding-top: $navbarHeight;
  // height: 100%; // Optional: if AppMain itself should fill this wrapper's height
  // overflow: auto; // Optional: if scrolling should be on this wrapper
}
</style>
