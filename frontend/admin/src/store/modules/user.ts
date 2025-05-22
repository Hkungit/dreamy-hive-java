import { defineStore } from 'pinia'
import { login as userLogin, logout as userLogout, getUserInfo } from '../../api/user'

interface UserState {
  token: string
  userInfo: any
  roles: string[]
}

export const useUserStore = defineStore('user', {
  state: (): UserState => ({
    token: localStorage.getItem('token') || '',
    userInfo: {},
    roles: []
  }),
  
  getters: {
    isLoggedIn: (state) => !!state.token,
    username: (state) => state.userInfo?.username || ''
  },
  
  actions: {
    // 登录
    async login(username: string, password: string) {
      try {
        const response: any = await userLogin(username, password)
        if (response.success && response.data) {
          // 从返回的数据中提取token
          const tokenValue = response.data.tokenValue
          const tokenName = response.data.tokenName || 'satoken'
          
          this.token = tokenValue
          localStorage.setItem('token', tokenValue)
          localStorage.setItem('tokenName', tokenName)
          
          return Promise.resolve(response)
        } else {
          return Promise.reject(new Error(response.message || '登录失败'))
        }
      } catch (error) {
        return Promise.reject(error)
      }
    },
    
    // 获取用户信息
    async getInfo() {
      try {
        const { data } = await getUserInfo()
        this.userInfo = data
        this.roles = data.roles || []
        return Promise.resolve(data)
      } catch (error) {
        return Promise.reject(error)
      }
    },
    
    // 登出
    async logout() {
      try {
        await userLogout()
        this.resetState()
        return Promise.resolve()
      } catch (error) {
        return Promise.reject(error)
      }
    },
    
    // 重置状态
    resetState() {
      this.token = ''
      this.userInfo = {}
      this.roles = []
      localStorage.removeItem('token')
      localStorage.removeItem('tokenName')
    }
  }
})
