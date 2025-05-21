package com.dreamy.hive.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Role;
import com.dreamy.hive.entity.RolePermission;
import com.dreamy.hive.entity.UserRole;
import com.dreamy.hive.mapper.RoleMapper;
import com.dreamy.hive.mapper.RolePermissionMapper;
import com.dreamy.hive.mapper.UserRoleMapper;
import com.dreamy.hive.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    
    @Autowired
    private RoleMapper roleMapper;
    
    @Autowired
    private RolePermissionMapper rolePermissionMapper;
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Override
    public Result<?> list(int pageNum, int pageSize, String name) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        if (name != null && !name.isEmpty()) {
            wrapper.like(Role::getName, name);
        }
        Page<Role> page = this.page(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(page);
    }
    
    @Override
    public Result<?> getDetail(Long roleId) {
        Role role = this.getById(roleId);
        if (role == null) {
            return Result.fail("角色不存在");
        }
        return Result.success(role);
    }
    
    @Override
    public Result<?> create(Role role) {
        // 检查角色名是否存在
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getName, role.getName());
        if (this.count(wrapper) > 0) {
            return Result.fail("角色名已存在");
        }
        
        // 检查角色代码是否存在
        wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Role::getCode, role.getCode());
        if (this.count(wrapper) > 0) {
            return Result.fail("角色代码已存在");
        }
        
        this.save(role);
        return Result.success();
    }
    
    @Override
    public Result<?> update(Role role) {
        Role existingRole = this.getById(role.getId());
        if (existingRole == null) {
            return Result.fail("角色不存在");
        }
        
        // 检查角色名是否重复
        if (!existingRole.getName().equals(role.getName())) {
            LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Role::getName, role.getName());
            if (this.count(wrapper) > 0) {
                return Result.fail("角色名已存在");
            }
        }
        
        // 检查角色代码是否重复
        if (!existingRole.getCode().equals(role.getCode())) {
            LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Role::getCode, role.getCode());
            if (this.count(wrapper) > 0) {
                return Result.fail("角色代码已存在");
            }
        }
        
        this.updateById(role);
        return Result.success();
    }
    
    @Override
    @Transactional
    public Result<?> delete(Long roleId) {
        Role role = this.getById(roleId);
        if (role == null) {
            return Result.fail("角色不存在");
        }
        
        // 删除角色
        this.removeById(roleId);
        
        // 删除角色-权限关联关系
        LambdaQueryWrapper<RolePermission> rpWrapper = new LambdaQueryWrapper<>();
        rpWrapper.eq(RolePermission::getRoleId, roleId);
        rolePermissionMapper.delete(rpWrapper);
        
        // 删除用户-角色关联关系
        LambdaQueryWrapper<UserRole> urWrapper = new LambdaQueryWrapper<>();
        urWrapper.eq(UserRole::getRoleId, roleId);
        userRoleMapper.delete(urWrapper);
        
        return Result.success();
    }
    
    @Override
    @Transactional
    public Result<?> assignPermissions(Long roleId, List<Long> permissionIds) {
        Role role = this.getById(roleId);
        if (role == null) {
            return Result.fail("角色不存在");
        }
        
        // 先删除原有的角色-权限关联
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, roleId);
        rolePermissionMapper.delete(wrapper);
        
        // 创建新的角色-权限关联
        if (permissionIds != null && !permissionIds.isEmpty()) {
            List<RolePermission> rolePermissions = permissionIds.stream().map(permissionId -> {
                RolePermission rp = new RolePermission();
                rp.setRoleId(roleId);
                rp.setPermissionId(permissionId);
                return rp;
            }).collect(Collectors.toList());
            
            for (RolePermission rp : rolePermissions) {
                rolePermissionMapper.insert(rp);
            }
        }
        
        return Result.success();
    }
    
    @Override
    public Result<?> getRolePermissions(Long roleId) {
        Role role = this.getById(roleId);
        if (role == null) {
            return Result.fail("角色不存在");
        }
        
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, roleId);
        List<RolePermission> rolePermissions = rolePermissionMapper.selectList(wrapper);
        
        List<Long> permissionIds = rolePermissions.stream()
                .map(RolePermission::getPermissionId)
                .collect(Collectors.toList());
        
        return Result.success(permissionIds);
    }
    
    @Override
    public Result<?> getUserRoles(Long userId) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        List<UserRole> userRoles = userRoleMapper.selectList(wrapper);
        
        List<Long> roleIds = userRoles.stream()
                .map(UserRole::getRoleId)
                .collect(Collectors.toList());
        
        if (roleIds.isEmpty()) {
            return Result.success(new ArrayList<>());
        }
        
        List<Role> roles = this.listByIds(roleIds);
        return Result.success(roles);
    }
    
    @Override
    @Transactional
    public Result<?> assignUserRoles(Long userId, List<Long> roleIds) {
        // 先删除原有的用户-角色关联
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId, userId);
        userRoleMapper.delete(wrapper);
        
        // 创建新的用户-角色关联
        if (roleIds != null && !roleIds.isEmpty()) {
            List<UserRole> userRoles = roleIds.stream().map(roleId -> {
                UserRole ur = new UserRole();
                ur.setUserId(userId);
                ur.setRoleId(roleId);
                return ur;
            }).collect(Collectors.toList());
            
            for (UserRole ur : userRoles) {
                userRoleMapper.insert(ur);
            }
        }
        
        return Result.success();
    }
} 