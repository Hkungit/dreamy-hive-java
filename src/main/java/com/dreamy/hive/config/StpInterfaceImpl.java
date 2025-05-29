package com.dreamy.hive.config;

import cn.dev33.satoken.stp.StpInterface;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Permission;
import com.dreamy.hive.entity.Role;
import com.dreamy.hive.service.PermissionService;
import com.dreamy.hive.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SaToken权限验证接口实现
 * 用于获取用户的角色和权限信息
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        Long userId = Long.valueOf(loginId.toString());
        System.out.println("[StpInterface] 获取用户权限，userId: " + userId);
        
        try {
            Result<?> result = permissionService.getPermissionsByUserId(userId);
            if (result.isSuccess() && result.getData() != null) {
                List<Permission> permissions = (List<Permission>) result.getData();
                List<String> permissionCodes = permissions.stream()
                        .map(Permission::getCode)
                        .collect(Collectors.toList());
                System.out.println("[StpInterface] 用户权限: " + permissionCodes);
                return permissionCodes;
            }
        } catch (Exception e) {
            System.err.println("获取用户权限失败: " + e.getMessage());
            e.printStackTrace();
        }
        
        return new ArrayList<>();
    }

    /**
     * 返回一个账号所拥有的角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        Long userId = Long.valueOf(loginId.toString());
        System.out.println("[StpInterface] 获取用户角色，userId: " + userId);
        
        try {
            Result<?> result = roleService.getUserRoles(userId);
            if (result.isSuccess() && result.getData() != null) {
                List<Role> roles = (List<Role>) result.getData();
                List<String> roleCodes = roles.stream()
                        .map(Role::getCode)
                        .collect(Collectors.toList());
                System.out.println("[StpInterface] 用户角色: " + roleCodes);
                return roleCodes;
            }
        } catch (Exception e) {
            System.err.println("获取用户角色失败: " + e.getMessage());
            e.printStackTrace();
        }
        
        return new ArrayList<>();
    }
} 