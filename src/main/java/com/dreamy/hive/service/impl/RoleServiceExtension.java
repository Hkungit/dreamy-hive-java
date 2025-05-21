package com.dreamy.hive.service.impl;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Role;
import com.dreamy.hive.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色服务扩展
 * 提供额外的角色检查方法
 */
@Component
public class RoleServiceExtension {
    
    @Autowired
    private RoleService roleService;
    
    /**
     * 检查用户是否拥有指定角色
     * @param userId 用户ID
     * @param roleCode 角色编码
     * @return 是否拥有角色
     */
    public boolean hasRole(Long userId, String roleCode) {
        Result<?> result = roleService.getUserRoles(userId);
        if (!result.isSuccess()) {
            return false;
        }
        
        List<Role> roles = (List<Role>) result.getData();
        if (roles == null || roles.isEmpty()) {
            return false;
        }
        
        List<String> roleCodes = roles.stream()
                .map(Role::getCode)
                .collect(Collectors.toList());
        
        return roleCodes.contains(roleCode);
    }
    
    /**
     * 检查用户是否拥有指定的任一角色
     * @param userId 用户ID
     * @param roleCodes 角色编码列表
     * @return 是否拥有任一角色
     */
    public boolean hasAnyRole(Long userId, List<String> roleCodes) {
        Result<?> result = roleService.getUserRoles(userId);
        if (!result.isSuccess()) {
            return false;
        }
        
        List<Role> roles = (List<Role>) result.getData();
        if (roles == null || roles.isEmpty()) {
            return false;
        }
        
        List<String> userRoleCodes = roles.stream()
                .map(Role::getCode)
                .collect(Collectors.toList());
        
        for (String code : roleCodes) {
            if (userRoleCodes.contains(code)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * 检查用户是否拥有指定的所有角色
     * @param userId 用户ID
     * @param roleCodes 角色编码列表
     * @return 是否拥有所有角色
     */
    public boolean hasAllRoles(Long userId, List<String> roleCodes) {
        Result<?> result = roleService.getUserRoles(userId);
        if (!result.isSuccess()) {
            return false;
        }
        
        List<Role> roles = (List<Role>) result.getData();
        if (roles == null || roles.isEmpty()) {
            return false;
        }
        
        List<String> userRoleCodes = roles.stream()
                .map(Role::getCode)
                .collect(Collectors.toList());
        
        return userRoleCodes.containsAll(roleCodes);
    }
}
