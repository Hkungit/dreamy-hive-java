package com.dreamy.hive.controller.v1.admin;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.User;
import com.dreamy.hive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import cn.dev33.satoken.annotation.SaCheckRole;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@Tag(name = "后台用户管理", description = "管理员用户管理相关接口")
@RestController
@RequestMapping("/v1/admin/user")
@SaCheckRole("admin")
public class AdminUserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "用户分页列表")
    @GetMapping("/list")
    public Result<?> list(@RequestParam(defaultValue = "1") int pageNum,
                          @RequestParam(defaultValue = "10") int pageSize,
                          @RequestParam(required = false) String username,
                          @RequestParam(required = false) Integer status) {
        return userService.adminList(pageNum, pageSize, username, status);
    }

    @Operation(summary = "修改用户状态")
    @PutMapping("/status")
    public Result<?> updateStatus(@RequestParam Long userId, @RequestParam Integer status) {
        return userService.adminUpdateStatus(userId, status);
    }

    @Operation(summary = "获取用户详情")
    @GetMapping("/{userId}")
    public Result<?> getDetail(@PathVariable Long userId) {
        return userService.adminGetDetail(userId);
    }

    @Operation(summary = "编辑用户信息")
    @PutMapping("/{userId}")
    public Result<?> updateUser(@PathVariable Long userId, @RequestBody User user) {
        return userService.adminUpdateUser(userId, user);
    }

    @Operation(summary = "重置用户密码")
    @PutMapping("/{userId}/reset-password")
    public Result<?> resetPassword(@PathVariable Long userId, @RequestParam String newPassword) {
        return userService.adminResetPassword(userId, newPassword);
    }
}