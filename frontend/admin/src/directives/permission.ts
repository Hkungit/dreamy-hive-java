import type { App, Directive, DirectiveBinding } from 'vue'
import { useUserStore } from '../store/modules/user'

/**
 * 权限指令
 * 使用方法：v-permission="'user:view'"
 */
const permission: Directive = {
  mounted(el: HTMLElement, binding: DirectiveBinding) {
    const { value } = binding
    const userStore = useUserStore()

    if (value) {
      const hasPermission = userStore.hasPermission(value)
      if (!hasPermission) {
        el.style.display = 'none'
        // 或者直接移除元素
        // el.parentNode?.removeChild(el)
      }
    }
  },
  updated(el: HTMLElement, binding: DirectiveBinding) {
    const { value } = binding
    const userStore = useUserStore()

    if (value) {
      const hasPermission = userStore.hasPermission(value)
      if (!hasPermission) {
        el.style.display = 'none'
      } else {
        el.style.display = ''
      }
    }
  }
}

/**
 * 角色指令
 * 使用方法：v-role="'admin'"
 */
const role: Directive = {
  mounted(el: HTMLElement, binding: DirectiveBinding) {
    const { value } = binding
    const userStore = useUserStore()

    if (value) {
      const hasRole = userStore.hasRole(value)
      if (!hasRole) {
        el.style.display = 'none'
        // 或者直接移除元素
        // el.parentNode?.removeChild(el)
      }
    }
  },
  updated(el: HTMLElement, binding: DirectiveBinding) {
    const { value } = binding
    const userStore = useUserStore()

    if (value) {
      const hasRole = userStore.hasRole(value)
      if (!hasRole) {
        el.style.display = 'none'
      } else {
        el.style.display = ''
      }
    }
  }
}

/**
 * 注册权限指令
 */
export function setupPermissionDirectives(app: App) {
  app.directive('permission', permission)
  app.directive('role', role)
}

export { permission, role } 