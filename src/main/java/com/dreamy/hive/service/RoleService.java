package com.dreamy.hive.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    // 获取角色列表
    Result<?> list(int pageNum, int pageSize, String name);
    
    // 获取角色详情
    Result<?> getDetail(Long roleId);
    
    // 创建角色
    Result<?> create(Role role);
    
    // 更新角色
    Result<?> update(Role role);
    
    // 删除角色
    Result<?> delete(Long roleId);
    
    // 为角色分配权限
    Result<?> assignPermissions(Long roleId, List<Long> permissionIds);
    
    // 获取角色的权限列表
    Result<?> getRolePermissions(Long roleId);
    
    // 获取用户的所有角色
    Result<?> getUserRoles(Long userId);
    
    // 为用户分配角色
    Result<?> assignUserRoles(Long userId, List<Long> roleIds);
} 