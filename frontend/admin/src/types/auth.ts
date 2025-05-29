// 认证相关的类型定义

// 登录响应数据
export interface LoginResponseData {
  tokenName: string
  tokenValue: string
  isLogin: boolean
  loginId: string
  loginType: string
  tokenTimeout: number
  sessionTimeout: number
  tokenSessionTimeout: number
  tokenActiveTimeout: number
  loginDeviceType: string
  tag: null | string
}

// 通用API响应格式
export interface ApiResponse<T = any> {
  success: boolean
  code: number
  message: string
  data: T
}

// 登录响应类型
export type LoginResponse = ApiResponse<LoginResponseData>

// 用户信息
export interface UserInfo {
  id: string
  username: string
  nickname?: string
  email?: string
  avatar?: string
  status?: number
  createTime?: string
  updateTime?: string
}

// 角色信息
export interface Role {
  id: string
  name: string
  code: string
  description?: string
}

// 权限信息
export interface Permission {
  id: string
  name: string
  code: string
  type: string
  path?: string
  method?: string
}
