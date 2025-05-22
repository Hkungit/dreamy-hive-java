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

// 登出接口
export function logout() {
  return request({
    url: '/admin/logout',
    method: 'post'
  })
}

// 获取用户信息
export function getUserInfo() {
  return request({
    url: '/admin/info',
    method: 'get'
  })
}

// 获取用户列表
export function getUserList(params: any) {
  return request({
    url: '/admin/users',
    method: 'get',
    params
  })
}

// 添加用户
export function addUser(data: any) {
  return request({
    url: '/admin/users',
    method: 'post',
    data
  })
}

// 更新用户
export function updateUser(id: string, data: any) {
  return request({
    url: `/admin/users/${id}`,
    method: 'put',
    data
  })
}

// 删除用户
export function deleteUser(id: string) {
  return request({
    url: `/admin/users/${id}`,
    method: 'delete'
  })
}
