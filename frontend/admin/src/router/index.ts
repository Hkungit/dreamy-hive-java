import { createRouter, createWebHistory } from 'vue-router'

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
    children: [
      {
        path: '/dashboard',
        name: 'Dashboard',
        component: Dashboard,
        meta: { title: '仪表盘', icon: 'HomeFilled' }
      },
      {
        path: '/product',
        name: 'Product',
        component: Product,
        meta: { title: '商品管理', icon: 'Goods' }
      },
      {
        path: '/product/add',
        name: 'ProductAdd',
        component: ProductForm,
        meta: { title: '添加商品', activeMenu: '/product' },
        hidden: true
      },
      {
        path: '/product/edit/:id',
        name: 'ProductEdit',
        component: ProductForm,
        meta: { title: '编辑商品', activeMenu: '/product' },
        hidden: true
      },
      {
        path: '/sku',
        name: 'SKU',
        component: SKU,
        meta: { title: 'SKU管理', icon: 'Grid' }
      },
      {
        path: '/category',
        name: 'Category',
        component: Category,
        meta: { title: '分类管理', icon: 'Menu' }
      },
      {
        path: '/order',
        name: 'Order',
        component: Order,
        meta: { title: '订单管理', icon: 'List' }
      },
      {
        path: '/order/detail/:id',
        name: 'OrderDetail',
        component: OrderDetail,
        meta: { title: '订单详情', activeMenu: '/order' },
        hidden: true
      },
      {
        path: '/community',
        name: 'Community',
        component: Community,
        meta: { title: '社区管理', icon: 'ChatDotRound' }
      },
      {
        path: '/user',
        name: 'User',
        component: User,
        meta: { title: '用户管理', icon: 'User' }
      }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login,
    meta: { title: '登录' }
  },
  {
    path: '/:pathMatch(.*)*',
    name: 'NotFound',
    component: NotFound,
    hidden: true
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 路由守卫
router.beforeEach((to, from, next) => {
  // 设置页面标题
  document.title = `${to.meta.title as string || 'Admin'} - Dreamy Hive`
  
  // 暂时关闭登录检查，方便开发调试
  // 正式上线时需要打开这个检查
  // const token = localStorage.getItem('token')
  // if (to.path !== '/login' && !token) {
  //   next('/login')
  // } else {
  //   next()
  // }
  
  // 直接放行所有路由
  next()
})

export default router
