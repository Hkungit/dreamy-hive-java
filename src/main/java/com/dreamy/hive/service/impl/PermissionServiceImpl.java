package com.dreamy.hive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Permission;
import com.dreamy.hive.entity.RolePermission;
import com.dreamy.hive.entity.UserRole;
import com.dreamy.hive.mapper.PermissionMapper;
import com.dreamy.hive.mapper.RolePermissionMapper;
import com.dreamy.hive.mapper.UserRoleMapper;
import com.dreamy.hive.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    
    @Autowired
    private PermissionMapper permissionMapper;
    
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Override
    public Result<?> list(int pageNum, int pageSize, String name) {
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(Permission::getName, name);
        }
        Page<Permission> page = this.page(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(page);
    }
    
    @Override
    public Result<?> getDetail(Long permissionId) {
        Permission permission = this.getById(permissionId);
        if (permission == null) {
            return Result.fail("权限不存在");
        }
        return Result.success(permission);
    }
    
    @Override
    public Result<?> create(Permission permission) {
        // 检查权限名是否存在
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getName, permission.getName());
        if (this.count(wrapper) > 0) {
            return Result.fail("权限名已存在");
        }
        
        // 检查权限代码是否存在
        wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Permission::getCode, permission.getCode());
        if (this.count(wrapper) > 0) {
            return Result.fail("权限代码已存在");
        }
        
        this.save(permission);
        return Result.success();
    }
    
    @Override
    public Result<?> update(Permission permission) {
        Permission existingPermission = this.getById(permission.getId());
        if (existingPermission == null) {
            return Result.fail("权限不存在");
        }
        
        // 检查权限名是否重复
        if (!existingPermission.getName().equals(permission.getName())) {
            LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Permission::getName, permission.getName());
            if (this.count(wrapper) > 0) {
                return Result.fail("权限名已存在");
            }
        }
        
        // 检查权限代码是否重复
        if (!existingPermission.getCode().equals(permission.getCode())) {
            LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Permission::getCode, permission.getCode());
            if (this.count(wrapper) > 0) {
                return Result.fail("权限代码已存在");
            }
        }
        
        this.updateById(permission);
        return Result.success();
    }
    
    @Override
    @Transactional
    public Result<?> delete(Long permissionId) {
        Permission permission = this.getById(permissionId);
        if (permission == null) {
            return Result.fail("权限不存在");
        }
        
        // 删除权限
        this.removeById(permissionId);
        
        // 删除角色-权限关联
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getPermissionId, permissionId);
        rolePermissionMapper.delete(wrapper);
        
        return Result.success();
    }
    
    @Override
    public Result<?> getPermissionsByUserId(Long userId) {
        // 获取用户所有角色
        LambdaQueryWrapper<UserRole> urWrapper = new LambdaQueryWrapper<>();
        urWrapper.eq(UserRole::getUserId, userId);
        List<UserRole> userRoles = userRoleMapper.selectList(urWrapper);
        
        if (userRoles.isEmpty()) {
            return Result.success(new ArrayList<>());
        }
        
        // 获取角色ID列表
        List<Long> roleIds = userRoles.stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
        
        // 获取所有角色关联的权限
        LambdaQueryWrapper<RolePermission> rpWrapper = new LambdaQueryWrapper<>();
        rpWrapper.in(RolePermission::getRoleId, roleIds);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(rpWrapper);
        
        if (rolePermissions.isEmpty()) {
            return Result.success(new ArrayList<>());
        }
        
        // 获取权限ID列表（去重）
        Set<Long> permissionIds = rolePermissions.stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toSet());
        
        // 查询权限详情
        List<Permission> permissions = this.listByIds(permissionIds);
        return Result.success(permissions);
    }
    
    @Override
    public Result<?> getPermissionsByRoleId(Long roleId) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(wrapper);
        
        if (rolePermissions.isEmpty()) {
            return Result.success(new ArrayList<>());
        }
        
        List<Long> permissionIds = rolePermissions.stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toList());
        
        List<Permission> permissions = this.listByIds(permissionIds);
        return Result.success(permissions);
    }
} 