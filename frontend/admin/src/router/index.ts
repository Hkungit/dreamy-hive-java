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
import RoleManagement from '../views/role/RoleManagement.vue' // Import RoleManagement
import PermissionManagement from '../views/permission/PermissionManagement.vue' // Import PermissionManagement
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
      },
      {
        path: '/roles', // Consistent with existing paths like /product, /user
        name: 'RoleManagement',
        component: RoleManagement,
        meta: { title: '角色管理', icon: 'Setting' } // Using 'Setting' icon
      },
      {
        path: '/permissions', // Consistent with existing paths
        name: 'PermissionManagement',
        component: PermissionManagement,
        meta: { title: '权限管理', icon: 'Key' } // Using 'Key' icon
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
import { useUserStore } from '../store/modules/user'; 
import piniaInstance from '../store'; // Assuming store/index.ts exports the pinia instance

router.beforeEach(async (to, from, next) => {
  document.title = `${to.meta.title as string || 'Admin'} - Dreamy Hive`;
  
  // It's generally safer to initialize store access within the guard
  // if there's any doubt about initialization order.
  const userStore = useUserStore(piniaInstance); // Pass the pinia instance
  const token = userStore.token; // Use token from store, which reads from localStorage initially

  if (token) {
    if (to.path === '/login') {
      next({ path: '/' }); // Already logged in, redirect from /login to dashboard
    } else {
      // Check if user info (especially roles) is loaded if routes depend on it.
      // For now, if basic userInfo (like ID) isn't present, fetch it.
      if (!userStore.userInfo || !userStore.userInfo.id) { // Check for a key property like id
        try {
          await userStore.getInfo(); // Load user info if not already loaded
          // If you have role-based routes, you might need to re-evaluate routes here or use addRoute
          next({ ...to, replace: true }); // Ensure navigation happens after getInfo
        } catch (error) {
          console.error('Failed to fetch user info in router guard:', error);
          userStore.resetState(); // Clear invalid token
          next('/login'); // Redirect to login on error
        }
      } else {
        next(); // User is logged in, and info is present
      }
    }
  } else { // No token
    if (to.path !== '/login') {
      next('/login'); // Not logged in, redirect to login if not on login page
    } else {
      next(); // Allow access to login page
    }
  }
});

export default router
