import request from './request';

export function getRoleList(params: any) {
  return request({
    url: '/admin/roles',
    method: 'get',
    params,
  });
}

export function addRole(data: any) {
  return request({
    url: '/admin/roles',
    method: 'post',
    data,
  });
}

export function updateRole(id: string, data: any) {
  return request({
    url: `/admin/roles/${id}`,
    method: 'put',
    data,
  });
}

export function deleteRole(id: string) {
  return request({
    url: `/admin/roles/${id}`,
    method: 'delete',
  });
}

export function getRoleDetail(id: string) {
  return request({
    url: `/admin/roles/${id}`,
    method: 'get',
  });
}

export function getRolePermissions(roleId: string) {
  return request({
    url: `/admin/roles/${roleId}/permissions`,
    method: 'get',
  });
}

export function assignPermissionsToRole(roleId: string, data: any) {
  return request({
    url: `/admin/roles/${roleId}/permissions`,
    method: 'put',
    data,
  });
}
