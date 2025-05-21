<template>
  <div v-if="!item.hidden">
    <!-- 如果没有子菜单，直接渲染菜单项 -->
    <template v-if="!hasChildren(item)">
      <el-menu-item :index="resolvePath(item.path)">
        <el-icon v-if="item.meta && item.meta.icon">
          <component :is="item.meta.icon" />
        </el-icon>
        <span>{{ item.meta?.title }}</span>
      </el-menu-item>
    </template>
    
    <!-- 如果有子菜单，渲染子菜单 -->
    <el-sub-menu v-else :index="resolvePath(item.path)">
      <template #title>
        <el-icon v-if="item.meta && item.meta.icon">
          <component :is="item.meta.icon" />
        </el-icon>
        <span>{{ item.meta?.title }}</span>
      </template>
      
      <sidebar-item
        v-for="child in item.children"
        :key="child.path"
        :item="child"
        :base-path="resolvePath(child.path)"
      />
    </el-sub-menu>
  </div>
</template>

<script lang="ts" setup>
import { defineProps } from 'vue'
import path from 'path-browserify'

const props = defineProps({
  item: {
    type: Object,
    required: true
  },
  basePath: {
    type: String,
    default: ''
  }
})

// 判断是否有子菜单
const hasChildren = (item: any) => {
  return item.children && item.children.length > 0 && !item.hidden
}

// 解析路径
const resolvePath = (routePath: string) => {
  if (routePath.startsWith('/')) {
    return routePath
  }
  if (typeof path.resolve === 'function') {
    return path.resolve(props.basePath, routePath)
  }
  // 如果 path-browserify 没有正确加载，使用简单的路径拼接
  const basePath = props.basePath.endsWith('/') ? props.basePath.slice(0, -1) : props.basePath
  return `${basePath}/${routePath}`
}
</script>
