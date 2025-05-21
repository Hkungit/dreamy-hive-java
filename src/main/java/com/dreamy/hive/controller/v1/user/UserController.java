package com.dreamy.hive.controller.v1.user;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.controller.v1.base.BaseController;
import com.dreamy.hive.dto.request.*;
import com.dreamy.hive.entity.User;
import com.dreamy.hive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.dreamy.hive.annotation.RequiresLogin;
import com.dreamy.hive.annotation.RequiresPermission;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 用户控制器
 * 处理用户注册、登录、个人信息管理等功能
 */
@Tag(name = "用户模块", description = "用户注册、登录、信息管理等接口")
@RestController
@RequestMapping("/v1/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    /**
     * 用户注册
     * @param dto 注册信息
     * @return 注册结果
     */
    @Operation(summary = "用户注册", description = "新用户注册接口，需提供用户名、密码等信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "注册成功"),
        @ApiResponse(responseCode = "400", description = "注册失败，用户名已存在或参数不合法")
    })
    @PostMapping("/register")
    public Result<?> register(@RequestBody UserRegisterRequestDTO dto) {
        return userService.register(dto);
    }

    /**
     * 用户登录
     * @param dto 登录信息
     * @return 登录结果，包含token
     */
    @Operation(summary = "用户登录", description = "用户登录接口，需提供用户名和密码")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "登录成功，返回token"),
        @ApiResponse(responseCode = "400", description = "登录失败，用户名或密码错误")
    })
    @PostMapping("/login")
    public Result<?> login(@RequestBody UserLoginRequestDTO dto) {
        return userService.login(dto);
    }

    /**
     * 用户登出
     * @return 登出结果
     */
    @Operation(summary = "用户登出", description = "用户登出接口，清除登录状态")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "登出成功")
    })
    @PostMapping("/logout")
    @RequiresLogin
    public Result<?> logout() {
        return userService.logout();
    }

    /**
     * 获取个人信息
     * @return 用户个人信息
     */
    @Operation(summary = "获取个人信息", description = "获取当前登录用户的个人信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取成功", 
                    content = @Content(schema = @Schema(implementation = User.class))),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @GetMapping("/profile")
    @RequiresLogin
    public Result<User> getProfile() {
        return userService.getProfile();
    }

    /**
     * 修改个人信息
     * @param dto 个人信息
     * @return 修改结果
     */
    @Operation(summary = "修改个人信息", description = "修改当前登录用户的个人信息")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "修改成功"),
        @ApiResponse(responseCode = "400", description = "修改失败，参数不合法"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @PutMapping("/profile")
    @RequiresLogin
    public Result<?> updateProfile(@RequestBody UserUpdateInfoRequestDTO dto) {
        return userService.updateProfile(dto);
    }

    /**
     * 修改密码
     * @param dto 密码信息
     * @return 修改结果
     */
    @Operation(summary = "修改密码", description = "修改当前登录用户的密码")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "修改成功"),
        @ApiResponse(responseCode = "400", description = "修改失败，原密码错误或参数不合法"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @PutMapping("/password")
    @RequiresLogin
    public Result<?> changePassword(@RequestBody UserChangePasswordRequestDTO dto) {
        return userService.changePassword(dto);
    }

    /**
     * 上传并更新用户头像
     * @param file 头像文件
     * @return 更新结果
     */
    @Operation(summary = "上传并更新用户头像", description = "上传并更新当前登录用户的头像")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "上传成功"),
        @ApiResponse(responseCode = "400", description = "上传失败，文件格式不支持或文件过大"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @PostMapping("/avatar")
    @RequiresLogin
    public Result<?> updateAvatar(
            @Parameter(description = "头像文件，支持jpg、png格式，大小不超过2MB") 
            @RequestParam("file") MultipartFile file) {
        return userService.updateAvatar(file);
    }

    /**
     * 获取当前用户的权限编码列表
     * @return 权限编码列表
     */
    @Operation(summary = "获取当前用户的权限编码列表", description = "获取当前登录用户拥有的所有权限编码")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取成功"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @GetMapping("/permissions")
    @RequiresLogin
    public Result<?> getPermissions() {
        Long userId = getCurrentUserId();
        return userService.getPermissionCodes(userId);
    }

    /**
     * 获取当前用户的角色编码列表
     * @return 角色编码列表
     */
    @Operation(summary = "获取当前用户的角色编码列表", description = "获取当前登录用户拥有的所有角色编码")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "获取成功"),
        @ApiResponse(responseCode = "401", description = "未登录")
    })
    @GetMapping("/roles")
    @RequiresLogin
    public Result<?> getRoles() {
        Long userId = getCurrentUserId();
        return userService.getRoleCodes(userId);
    }

    /**
     * 测试权限控制
     * @return 测试结果
     */
    @Operation(summary = "测试权限控制", description = "测试权限控制功能，需要user:view权限")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "有权限访问"),
        @ApiResponse(responseCode = "403", description = "无权限访问")
    })
    @GetMapping("/test-permission")
    @RequiresPermission("user:view")
    public Result<?> testPermission() {
        return success("您有权限访问此接口");
    }
}