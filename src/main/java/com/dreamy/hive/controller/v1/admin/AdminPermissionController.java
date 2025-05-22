package com.dreamy.hive.controller.v1.admin;

import com.dreamy.hive.annotation.RequiresPermission;
import com.dreamy.hive.annotation.RequiresRole;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Permission;
import com.dreamy.hive.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.dev33.satoken.annotation.SaCheckRole;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/v1/admin/permission")
@Tag(name = "管理端-权限管理", description = "管理端权限相关接口")
public class AdminPermissionController {
    @Autowired
    private PermissionService permissionService;

    @Operation(summary = "权限分页列表")
    @GetMapping("/list")
    public Result<?> list(@RequestParam(defaultValue = "1") int pageNum,
                          @RequestParam(defaultValue = "10") int pageSize,
                          @RequestParam(required = false) String name) {
        return permissionService.list(pageNum, pageSize, name);
    }

    @Operation(summary = "获取权限详情")
    @GetMapping("/{permissionId}")
    public Result<?> getDetail(@PathVariable Long permissionId) {
        return permissionService.getDetail(permissionId);
    }

    @Operation(summary = "创建权限")
    @PostMapping
    public Result<?> create(@RequestBody Permission permission) {
        return permissionService.create(permission);
    }

    @Operation(summary = "更新权限")
    @PutMapping("/{permissionId}")
    public Result<?> update(@PathVariable Long permissionId, @RequestBody Permission permission) {
        permission.setId(permissionId);
        return permissionService.update(permission);
    }

    @Operation(summary = "删除权限")
    @DeleteMapping("/{permissionId}")
    public Result<?> delete(@PathVariable Long permissionId) {
        return permissionService.delete(permissionId);
    }

    @Operation(summary = "根据用户ID获取权限列表")
    @GetMapping("/user/{userId}")
    public Result<?> getPermissionsByUserId(@PathVariable Long userId) {
        return permissionService.getPermissionsByUserId(userId);
    }

    @Operation(summary = "根据角色ID获取权限列表")
    @GetMapping("/role/{roleId}")
    public Result<?> getPermissionsByRoleId(@PathVariable Long roleId) {
        return permissionService.getPermissionsByRoleId(roleId);
    }
}