import request from './request';

export function getPermissionTree() {
  return request({
    url: '/admin/permissions/tree',
    method: 'get',
  });
}

export function getPermissionList(params: any) {
  return request({
    url: '/admin/permissions',
    method: 'get',
    params,
  });
}

export function addPermission(data: any) {
  return request({
    url: '/admin/permissions',
    method: 'post',
    data,
  });
}

export function updatePermission(id: string, data: any) {
  return request({
    url: `/admin/permissions/${id}`,
    method: 'put',
    data,
  });
}

export function deletePermission(id: string) {
  return request({
    url: `/admin/permissions/${id}`,
    method: 'delete',
  });
}

export function getPermissionDetail(id: string) {
  return request({
    url: `/admin/permissions/${id}`,
    method: 'get',
  });
}
