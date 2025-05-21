package com.dreamy.hive.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Permission;

import java.util.List;

public interface PermissionService extends IService<Permission> {
    // 获取权限列表
    Result<?> list(int pageNum, int pageSize, String name);
    
    // 获取权限详情
    Result<?> getDetail(Long permissionId);
    
    // 创建权限
    Result<?> create(Permission permission);
    
    // 更新权限
    Result<?> update(Permission permission);
    
    // 删除权限
    Result<?> delete(Long permissionId);
    
    // 根据用户ID获取权限列表
    Result<?> getPermissionsByUserId(Long userId);
    
    // 根据角色ID获取权限列表
    Result<?> getPermissionsByRoleId(Long roleId);
} 