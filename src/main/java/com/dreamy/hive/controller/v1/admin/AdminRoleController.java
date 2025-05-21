package com.dreamy.hive.controller.v1.admin;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Role;
import com.dreamy.hive.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.dev33.satoken.annotation.SaCheckRole;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@Tag(name = "后台角色管理", description = "管理员角色管理相关接口")
@RestController
@RequestMapping("/api/v1/admin/role")
@SaCheckRole("admin")
public class AdminRoleController {
    @Autowired
    private RoleService roleService;

    @Operation(summary = "角色分页列表")
    @GetMapping("/list")
    public Result<?> list(@RequestParam(defaultValue = "1") int pageNum,
                          @RequestParam(defaultValue = "10") int pageSize,
                          @RequestParam(required = false) String name) {
        return roleService.list(pageNum, pageSize, name);
    }

    @Operation(summary = "获取角色详情")
    @GetMapping("/{roleId}")
    public Result<?> getDetail(@PathVariable Long roleId) {
        return roleService.getDetail(roleId);
    }

    @Operation(summary = "创建角色")
    @PostMapping
    public Result<?> create(@RequestBody Role role) {
        return roleService.create(role);
    }

    @Operation(summary = "更新角色")
    @PutMapping("/{roleId}")
    public Result<?> update(@PathVariable Long roleId, @RequestBody Role role) {
        role.setId(roleId);
        return roleService.update(role);
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/{roleId}")
    public Result<?> delete(@PathVariable Long roleId) {
        return roleService.delete(roleId);
    }

    @Operation(summary = "为角色分配权限")
    @PostMapping("/{roleId}/permissions")
    public Result<?> assignPermissions(@PathVariable Long roleId, @RequestBody List<Long> permissionIds) {
        return roleService.assignPermissions(roleId, permissionIds);
    }

    @Operation(summary = "获取角色的权限列表")
    @GetMapping("/{roleId}/permissions")
    public Result<?> getRolePermissions(@PathVariable Long roleId) {
        return roleService.getRolePermissions(roleId);
    }

    @Operation(summary = "获取用户的角色列表")
    @GetMapping("/user/{userId}")
    public Result<?> getUserRoles(@PathVariable Long userId) {
        return roleService.getUserRoles(userId);
    }

    @Operation(summary = "为用户分配角色")
    @PostMapping("/user/{userId}")
    public Result<?> assignUserRoles(@PathVariable Long userId, @RequestBody List<Long> roleIds) {
        return roleService.assignUserRoles(userId, roleIds);
    }
}