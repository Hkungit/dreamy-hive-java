package com.dreamy.hive.service.impl;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Permission;
import com.dreamy.hive.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限服务扩展
 * 提供额外的权限检查方法
 */
@Component
public class PermissionServiceExtension {
    
    @Autowired
    private PermissionService permissionService;
    
    /**
     * 检查用户是否拥有指定权限
     * @param userId 用户ID
     * @param permissionCode 权限编码
     * @return 是否拥有权限
     */
    public boolean hasPermission(Long userId, String permissionCode) {
        Result<?> result = permissionService.getPermissionsByUserId(userId);
        if (!result.isSuccess()) {
            return false;
        }
        
        List<Permission> permissions = (List<Permission>) result.getData();
        if (permissions == null || permissions.isEmpty()) {
            return false;
        }
        
        List<String> permissionCodes = permissions.stream()
                .map(Permission::getCode)
                .collect(Collectors.toList());
        
        return permissionCodes.contains(permissionCode);
    }
    
    /**
     * 检查用户是否拥有指定的任一权限
     * @param userId 用户ID
     * @param permissionCodes 权限编码列表
     * @return 是否拥有任一权限
     */
    public boolean hasAnyPermission(Long userId, List<String> permissionCodes) {
        Result<?> result = permissionService.getPermissionsByUserId(userId);
        if (!result.isSuccess()) {
            return false;
        }
        
        List<Permission> permissions = (List<Permission>) result.getData();
        if (permissions == null || permissions.isEmpty()) {
            return false;
        }
        
        List<String> userPermissionCodes = permissions.stream()
                .map(Permission::getCode)
                .collect(Collectors.toList());
        
        for (String code : permissionCodes) {
            if (userPermissionCodes.contains(code)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 检查用户是否拥有指定的所有权限
     * @param userId 用户ID
     * @param permissionCodes 权限编码列表
     * @return 是否拥有所有权限
     */
    public boolean hasAllPermissions(Long userId, List<String> permissionCodes) {
        Result<?> result = permissionService.getPermissionsByUserId(userId);
        if (!result.isSuccess()) {
            return false;
        }
        
        List<Permission> permissions = (List<Permission>) result.getData();
        if (permissions == null || permissions.isEmpty()) {
            return false;
        }
        
        List<String> userPermissionCodes = permissions.stream()
                .map(Permission::getCode)
                .collect(Collectors.toList());
        
        return userPermissionCodes.containsAll(permissionCodes);
    }
}
