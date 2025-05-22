import request from './request'

// 获取商品列表
export function getProductList(params: any) {
  return request({
    url: '/v1/admin/products',
    method: 'get',
    params
  })
}

// 获取商品详情
export function getProductDetail(id: string) {
  return request({
    url: `/v1/admin/products/${id}`,
    method: 'get'
  })
}

// 添加商品
export function addProduct(data: any) {
  return request({
    url: '/v1/admin/products',
    method: 'post',
    data
  })
}

// 更新商品
export function updateProduct(id: string, data: any) {
  return request({
    url: `/v1/admin/products/${id}`,
    method: 'put',
    data
  })
}

// 删除商品
export function deleteProduct(id: string) {
  return request({
    url: `/v1/admin/products/${id}`,
    method: 'delete'
  })
}

// 获取商品SKU列表
export function getProductSkuList(productId: string) {
  return request({
    url: `/admin/products/${productId}/skus`,
    method: 'get'
  })
}

// 添加商品SKU
export function addProductSku(productId: string, data: any) {
  return request({
    url: `/admin/products/${productId}/skus`,
    method: 'post',
    data
  })
}

// 更新商品SKU
export function updateProductSku(productId: string, skuId: string, data: any) {
  return request({
    url: `/admin/products/${productId}/skus/${skuId}`,
    method: 'put',
    data
  })
}

// 删除商品SKU
export function deleteProductSku(productId: string, skuId: string) {
  return request({
    url: `/admin/products/${productId}/skus/${skuId}`,
    method: 'delete'
  })
}
