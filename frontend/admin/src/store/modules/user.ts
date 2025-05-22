import { defineStore } from 'pinia'
import { userLogin, userLogout, getUserInfo, getUserRoles, getUserPermissions } from '../../api/user'
import { getToken, getTokenName, getLoginId, saveLoginInfo, clearAuth } from '../../utils/auth'

interface UserState {
  token: string
  tokenName: string
  loginId: string
  userInfo: any
  roles: string[]
  permissions: string[]
  isLogin: boolean
  tokenTimeout: number
  sessionTimeout: number
}

export const useUserStore = defineStore('user', {  state: (): UserState => ({
    token: getToken() || '',
    tokenName: getTokenName(),
    loginId: getLoginId() || '',
    userInfo: {},
    roles: [],
    permissions: [],
    isLogin: false,
    tokenTimeout: 0,
    sessionTimeout: 0
  }),
  
  getters: {
    isLoggedIn: (state) => !!state.token,
    username: (state) => state.userInfo?.username || '',
    nickname: (state) => state.userInfo?.nickname || '',
    avatar: (state) => state.userInfo?.avatar || '',
    // 检查是否为超级管理员
    isSuperAdmin: (state) => state.roles.includes('super_admin')
  },
  
  actions: {    // 登录
    async login(username: string, password: string) {
      try {
        const response: any = await userLogin(username, password)
        if (response.success && response.data) {
          // 从返回的数据中提取所有token相关信息
          const {
            tokenValue,
            tokenName = 'satoken',
            loginId,
            isLogin,
            tokenTimeout,
            sessionTimeout
          } = response.data
          
          // 保存到store
          this.token = tokenValue
          this.tokenName = tokenName
          this.loginId = loginId
          this.isLogin = isLogin
          this.tokenTimeout = tokenTimeout
          this.sessionTimeout = sessionTimeout
          
          // 使用auth工具保存到localStorage
          saveLoginInfo({
            tokenValue,
            tokenName,
            loginId
          })
          
          // 登录成功后获取用户信息
          await this.getInfo()
          
          return Promise.resolve(response)
        } else {
          return Promise.reject(new Error(response.message || '登录失败'))
        }
      } catch (error: any) {
        console.error('登录失败:', error)
        return Promise.reject(error)
      }
    },
    
    // 获取用户信息
    async getInfo() {
      try {
        const response: any = await getUserInfo()
        if (response.success && response.data) {
          this.userInfo = response.data
          
          // 获取用户角色和权限
          await this.getUserRoles()
          await this.getUserPermissions()
          
          return Promise.resolve(response.data)
        } else {
          return Promise.reject(new Error(response.message || '获取用户信息失败'))
        }
      } catch (error: any) {
        console.error('获取用户信息失败:', error)
        return Promise.reject(error)
      }
    },
    
    // 获取用户角色
    async getUserRoles() {
      try {
        const response: any = await getUserRoles()
        if (response.success) {
          this.roles = response.data || []
        }
      } catch (error) {
        console.error('获取用户角色失败:', error)
        this.roles = []
      }
    },
    
    // 获取用户权限
    async getUserPermissions() {
      try {
        const response: any = await getUserPermissions()
        if (response.success) {
          this.permissions = response.data || []
        }
      } catch (error) {
        console.error('获取用户权限失败:', error)
        this.permissions = []
      }
    },
    
    // 登出
    async logout() {
      try {
        await userLogout()
      } catch (error) {
        console.error('登出请求失败:', error)
      } finally {
        // 无论请求是否成功，都清除本地状态
        this.resetState()
      }
    },    // 重置状态
    resetState() {
      this.token = ''
      this.tokenName = 'satoken'
      this.loginId = ''
      this.userInfo = {}
      this.roles = []
      this.permissions = []
      this.isLogin = false
      this.tokenTimeout = 0
      this.sessionTimeout = 0
      clearAuth()
    },
    
    // 检查权限
    hasPermission(permission: string): boolean {
      // 如果是超级管理员，拥有所有权限
      if (this.roles.includes('super_admin')) {
        return true
      }
      return this.permissions.includes(permission)
    },
    
    // 检查角色
    hasRole(role: string): boolean {
      // 如果是超级管理员，拥有所有角色
      if (this.roles.includes('super_admin')) {
        return true
      }
      return this.roles.includes(role)
    }
  }
})
