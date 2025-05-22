import request from './request'

// 登录接口
export function login(username: string, password: string) {
  return request({
    url: '/admin/login',
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

// 获取用户角色ID列表
export function getUserRoles(userId: string) {
  return request({
    url: `/admin/users/${userId}/roles`,
    method: 'get',
  });
}

// 为用户分配角色
export function assignUserRoles(userId: string, data: { roleIds: string[] }) {
  return request({
    url: `/admin/users/${userId}/roles`,
    method: 'put',
    data, // { "roleIds": ["1", "2"] }
  });
}
