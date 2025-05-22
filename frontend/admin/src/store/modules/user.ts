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
        const { data } = await userLogin(username, password)
        // data is LoginResponse: {"token": "jwt-token-string", "userInfo": { /* User DTO */ }}
        
        const { token, userInfo } = data; // Destructure userInfo from data
        this.token = token;
        localStorage.setItem('token', token);
        
        // Set user information and roles
        this.userInfo = userInfo; 
        this.roles = userInfo.roles || []; // Assuming userInfo DTO has a 'roles' field

        return Promise.resolve(data)
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
    }
  }
})
