import request from './request'

// 获取分类列表
export function getCategoryList(params?: any) {
  return request({
    url: '/admin/categories',
    method: 'get',
    params
  })
}

// 获取分类树结构
export function getCategoryTree() {
  return request({
    url: '/admin/categories/tree',
    method: 'get'
  })
}

// 获取分类详情
export function getCategoryDetail(id: string) {
  return request({
    url: `/admin/categories/${id}`,
    method: 'get'
  })
}

// 添加分类
export function addCategory(data: any) {
  return request({
    url: '/admin/categories',
    method: 'post',
    data
  })
}

// 更新分类
export function updateCategory(id: string, data: any) {
  return request({
    url: `/admin/categories/${id}`,
    method: 'put',
    data
  })
}

// 删除分类
export function deleteCategory(id: string) {
  return request({
    url: `/admin/categories/${id}`,
    method: 'delete'
  })
}
