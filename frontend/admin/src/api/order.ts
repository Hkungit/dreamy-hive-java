import request from './request'

// 获取订单列表
export function getOrderList(params: any) {
  return request({
    url: '/admin/orders',
    method: 'get',
    params
  })
}

// 获取订单详情
export function getOrderDetail(id: string) {
  return request({
    url: `/admin/orders/${id}`,
    method: 'get'
  })
}

// 更新订单状态
export function updateOrderStatus(id: string, status: string) {
  return request({
    url: `/admin/orders/${id}/status`,
    method: 'put',
    data: { status }
  })
}

// 获取订单统计数据
export function getOrderStats() {
  return request({
    url: '/admin/orders/stats',
    method: 'get'
  })
}
