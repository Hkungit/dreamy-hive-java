import { createRouter, createWebHistory } from 'vue-router'
import { useUserStore } from '../store/modules/user'

// 预先导入组件，避免动态导入问题
import Layout from '../components/layout/Layout.vue'
import Dashboard from '../views/dashboard/Dashboard.vue'
import Product from '../views/product/index.vue'
import ProductForm from '../views/product/ProductForm.vue'
import SKU from '../views/sku/index.vue'
import Category from '../views/category/index.vue'
import Order from '../views/order/index.vue'
import OrderDetail from '../views/order/OrderDetail.vue'
import Community from '../views/community/index.vue'
import User from '../views/user/index.vue'
import Login from '../views/login/Login.vue'
import NotFound from '../views/error/404.vue'

type AppRouteRecordRaw = {
  path: string
  name?: string
  component?: any
  redirect?: string
  children?: AppRouteRecordRaw[]
  meta?: {
    title?: string
    icon?: string
    activeMenu?: string
    requiresAuth?: boolean
  }
  hidden?: boolean
}

// 路由配置
const routes: Array<AppRouteRecordRaw> = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect: '/dashboard',
    meta: { requiresAuth: true },
    children: [
      {
        path: '/dashboard',
        name: 'Dashboard',
        component: Dashboard,
        meta: { title: '仪表盘', icon: 'HomeFilled', requiresAuth: true }
      },
      {
        path: '/product',
        name: 'Product',
        component: Product,
        meta: { title: '商品管理', icon: 'Goods', requiresAuth: true }
      },
      {
        path: '/product/add',
        name: 'ProductAdd',
        component: ProductForm,
        meta: { title: '添加商品', activeMenu: '/product', requiresAuth: true },
        hidden: true
      },
      {
        path: '/product/edit/:id',
        name: 'ProductEdit',
        component: ProductForm,
        meta: { title: '编辑商品', activeMenu: '/product', requiresAuth: true },
        hidden: true
      },
      {
        path: '/sku',
        name: 'SKU',
        component: SKU,
        meta: { title: 'SKU管理', icon: 'Grid', requiresAuth: true }
      },
      {
        path: '/category',
        name: 'Category',
        component: Category,
        meta: { title: '分类管理', icon: 'Menu', requiresAuth: true }
      },
      {
        path: '/order',
        name: 'Order',
        component: Order,
        meta: { title: '订单管理', icon: 'List', requiresAuth: true }
      },
      {
        path: '/order/detail/:id',
        name: 'OrderDetail',
        component: OrderDetail,
        meta: { title: '订单详情', activeMenu: '/order', requiresAuth: true },
        hidden: true
      },
      {
        path: '/community',
        name: 'Community',
        component: Community,
        meta: { title: '社区管理', icon: 'ChatDotRound', requiresAuth: true }
      },      {
        path: '/user',
        name: 'User',
        component: User,
        meta: { title: '用户管理', icon: 'User', requiresAuth: true }
      },
      {
        path: '/test/token',
        name: 'TokenTest',
        component: () => import('../views/test/TokenTest.vue'),
        meta: { title: 'Token测试', icon: 'Tools', requiresAuth: false },
        hidden: true
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: { title: '登录', requiresAuth: false }
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: NotFound,
    meta: { title: '页面不存在', requiresAuth: false },
    hidden: true
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes: routes as any
})

// 白名单路由（不需要登录的页面）
const whiteList = ['/login', '/404']

// 路由守卫
router.beforeEach(async (to, from, next) => {
  // 设置页面标题
  document.title = `${to.meta?.title as string || 'Admin'} - Dreamy Hive`
  
  // 获取用户store
  const userStore = useUserStore()
  const hasToken = userStore.token
  
  if (hasToken) {
    // 已登录
    if (to.path === '/login') {
      // 如果已登录且要访问登录页，重定向到首页
      next({ path: '/' })
    } else {
      // 检查是否有用户信息
      if (!userStore.userInfo.id) {
        try {
          // 获取用户信息
          await userStore.getInfo()
          next()
        } catch (error) {
          console.error('获取用户信息失败:', error)
          // 获取用户信息失败，清除token并重定向到登录页
          userStore.resetState()
          next(`/login?redirect=${to.path}`)
        }
      } else {
        next()
      }
    }
  } else {
    // 未登录
    if (whiteList.includes(to.path)) {
      // 在白名单中，直接进入
      next()
    } else {
      // 不在白名单中，重定向到登录页
      next(`/login?redirect=${to.path}`)
    }
  }
})

// 路由错误处理
router.onError((error) => {
  console.error('路由错误:', error)
})

export default router
