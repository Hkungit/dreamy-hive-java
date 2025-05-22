import request from './request';

export function getAttributeList(params: any) {
  return request({
    url: '/admin/attributes',
    method: 'get',
    params,
  });
}

export function addAttribute(data: any) {
  return request({
    url: '/admin/attributes',
    method: 'post',
    data,
  });
}

export function updateAttribute(id: string, data: any) {
  return request({
    url: `/admin/attributes/${id}`,
    method: 'put',
    data,
  });
}

export function deleteAttribute(id: string) {
  return request({
    url: `/admin/attributes/${id}`,
    method: 'delete',
  });
}

export function getAttributeDetail(id: string) {
  return request({
    url: `/admin/attributes/${id}`,
    method: 'get',
  });
}

export function getAttributesByCategoryId(categoryId: string) {
  return request({
    url: `/admin/attributes/category/${categoryId}`,
    method: 'get',
  });
}
