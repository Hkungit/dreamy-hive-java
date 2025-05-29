// 认证相关工具函数

const TOKEN_KEY = 'token'
const TOKEN_NAME_KEY = 'tokenName'
const LOGIN_ID_KEY = 'loginId'

// 获取token
export function getToken(): string | null {
  return localStorage.getItem(TOKEN_KEY)
}

// 设置token
export function setToken(token: string): void {
  localStorage.setItem(TOKEN_KEY, token)
}

// 移除token
export function removeToken(): void {
  localStorage.removeItem(TOKEN_KEY)
}

// 获取token名称
export function getTokenName(): string {
  return localStorage.getItem(TOKEN_NAME_KEY) || 'satoken'
}

// 设置token名称
export function setTokenName(tokenName: string): void {
  localStorage.setItem(TOKEN_NAME_KEY, tokenName)
}

// 移除token名称
export function removeTokenName(): void {
  localStorage.removeItem(TOKEN_NAME_KEY)
}

// 获取登录ID
export function getLoginId(): string | null {
  return localStorage.getItem(LOGIN_ID_KEY)
}

// 设置登录ID
export function setLoginId(loginId: string): void {
  localStorage.setItem(LOGIN_ID_KEY, loginId)
}

// 移除登录ID
export function removeLoginId(): void {
  localStorage.removeItem(LOGIN_ID_KEY)
}

// 清除所有认证信息
export function clearAuth(): void {
  removeToken()
  removeTokenName()
  removeLoginId()
}

// 检查是否已登录
export function isLoggedIn(): boolean {
  return !!getToken()
}

// 保存完整的登录信息
export function saveLoginInfo(loginData: {
  tokenValue: string
  tokenName?: string
  loginId: string
}) {
  setToken(loginData.tokenValue)
  setTokenName(loginData.tokenName || 'satoken')
  setLoginId(loginData.loginId)
}
