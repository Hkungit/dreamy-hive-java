import request from './request'

// 登录接口
export function login(username: string, password: string) {
  return request({
    url: '/v1/user/login',
    method: 'post',
    data: {
      username,
      password
    }
  })
}

// 登录接口 (Store中使用的别名)
export function userLogin(username: string, password: string) {
  return login(username, password)
}

// 登出接口
export function logout() {
  return request({
    url: '/v1/user/logout',
    method: 'post'
  })
}

// 登出接口 (Store中使用的别名)
export function userLogout() {
  return logout()
}

// 获取用户信息
export function getUserInfo() {
  return request({
    url: '/v1/user/profile',
    method: 'get'
  })
}

// 获取用户列表
export function getUserList(params: any) {
  return request({
    url: '/v1/admin/users',
    method: 'get',
    params
  })
}

// 添加用户
export function addUser(data: any) {
  return request({
    url: '/v1/admin/users',
    method: 'post',
    data
  })
}

// 更新用户
export function updateUser(id: string, data: any) {
  return request({
    url: `/v1/admin/users/${id}`,
    method: 'put',
    data
  })
}

// 删除用户
export function deleteUser(id: string) {
  return request({
    url: `/v1/admin/users/${id}`,
    method: 'delete'
  })
}

// 获取用户角色
export function getUserRoles() {
  return request({
    url: '/v1/user/roles',
    method: 'get'
  })
}

// 获取用户权限
export function getUserPermissions() {
  return request({
    url: '/v1/user/permissions',
    method: 'get'
  })
}
