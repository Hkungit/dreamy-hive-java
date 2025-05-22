import request from './request'

// 获取SPU列表
export function getProductList(params: any) {
  return request({
    url: '/admin/spus',
    method: 'get',
    params
  })
}

// 获取SPU详情
export function getProductDetail(id: string) {
  return request({
    url: `/admin/spus/${id}`,
    method: 'get'
  })
}

// 添加SPU
export function addProduct(data: any) {
  return request({
    url: '/admin/spus',
    method: 'post',
    data
  })
}

// 更新SPU
export function updateProduct(id: string, data: any) {
  return request({
    url: `/admin/spus/${id}`,
    method: 'put',
    data
  })
}

// 删除SPU
export function deleteProduct(id: string) {
  return request({
    url: `/admin/spus/${id}`,
    method: 'delete'
  })
}

// 获取SKU列表 (按SPU ID)
export function getProductSkuList(spuId: string) {
  return request({
    url: `/admin/skus/spu/${spuId}`,
    method: 'get'
  })
}

// 添加SKU (spuId is expected in data)
export function addProductSku(data: any) {
  return request({
    url: `/admin/skus`,
    method: 'post',
    data
  })
}

// 更新SKU
export function updateProductSku(id: string, data: any) {
  return request({
    url: `/admin/skus/${id}`,
    method: 'put',
    data
  })
}

// 删除SKU
export function deleteProductSku(id: string) {
  return request({
    url: `/admin/skus/${id}`,
    method: 'delete'
  })
}

// 获取SKU详情
export function getSkuDetail(id: string) {
  return request({
    url: `/admin/skus/${id}`,
    method: 'get'
  })
}

// --- Product Image Management Functions ---

// 上传单个商品图片
export function uploadProductImage(file: File) {
  const formData = new FormData();
  formData.append('file', file);
  return request({
    url: '/admin/product-images/upload',
    method: 'post',
    data: formData,
    // Assuming 'request' utility or underlying Axios instance handles 'Content-Type: multipart/form-data'
  });
}

// 批量上传商品图片
export function uploadBatchProductImages(files: File[]) {
  const formData = new FormData();
  files.forEach(file => {
    formData.append('files', file); // Backend expects a list of files under the key "files"
  });
  return request({
    url: '/admin/product-images/upload/batch',
    method: 'post',
    data: formData,
    // Assuming 'request' utility or underlying Axios instance handles 'Content-Type: multipart/form-data'
  });
}

// 删除商品图片
export function deleteProductImage(imageUrl: string) {
  return request({
    url: '/admin/product-images/', // Note: API doc implies path might be just /admin/product-images/ for delete with body
    method: 'delete',
    data: { imageUrl }, // Send imageUrl in the request body
  });
}

// 获取SPU的图片列表
export function getSpuImageList(spuId: string) {
  return request({
    url: `/admin/product-images/spu/${spuId}`,
    method: 'get',
  });
}

// 关联SPU图片
export function associateSpuImages(spuId: string, data: { imageUrls: string[] }) {
  return request({
    url: `/admin/product-images/spu/${spuId}/associate`,
    method: 'post',
    data, // e.g. { "imageUrls": ["url1.jpg", "url2.jpg"] }
  });
}

// 设置SPU主图
export function setSpuMainImage(productImageId: string) {
  return request({
    url: `/admin/product-images/${productImageId}/set-main`,
    method: 'put',
  });
}
