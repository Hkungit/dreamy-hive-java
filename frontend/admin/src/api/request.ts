import axios from 'axios'
import { ElMessage } from 'element-plus'
import { getToken, getTokenName, clearAuth } from '../utils/auth'

// 创建axios实例
const service = axios.create({
  baseURL: 'http://127.0.0.1:8080/api', // 直接使用固定URL而不是相对路径
  timeout: 15000 // 请求超时时间
})

// 请求拦截器
service.interceptors.request.use(
  config => {
    // 从localStorage获取token并添加到请求头
    const token = getToken()
    const tokenName = getTokenName()
    
    if (token) {
      // SA-Token直接使用token值，不需要Bearer前缀
      config.headers[tokenName] = token
    }
    return config
  },
  error => {
    console.error('Request error:', error)
    return Promise.reject(error)
  }
)

// 响应拦截器
service.interceptors.response.use(
  response => {
    const res = response.data
    
    // 如果返回的success为false，则判断为错误
    if (!res.success) {
      ElMessage({
        message: res.message || '请求错误',
        type: 'error',
        duration: 5 * 1000
      })
        // 401: 未登录或token过期
      if (res.code === 401) {
        // 重新登录
        clearAuth()
        location.href = '/login'
      }
      
      return Promise.reject(new Error(res.message || '请求错误'))
    } else {
      return res
    }
  },
  error => {
    console.error('Response error:', error)
    let message = error.message || '请求失败'
    
    if (error.response) {
      switch (error.response.status) {        case 401:
          message = '未授权，请重新登录'
          // 重新登录
          clearAuth()
          location.href = '/login'
          break
        case 403:
          message = '拒绝访问'
          break
        case 404:
          message = '请求的资源不存在'
          break
        case 500:
          message = '服务器内部错误'
          break
        default:
          message = `请求失败: ${error.response.status}`
      }
    }
    
    ElMessage({
      message,
      type: 'error',
      duration: 5 * 1000
    })
    
    return Promise.reject(error)
  }
)

export default service
