package com.dreamy.hive.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.dto.request.*;
import com.dreamy.hive.dto.response.FileUploadResponseDTO;
import com.dreamy.hive.entity.Permission;
import com.dreamy.hive.entity.Role;
import com.dreamy.hive.entity.User;
import com.dreamy.hive.mapper.UserMapper;
import com.dreamy.hive.mapper.UserRoleMapper;
import com.dreamy.hive.service.OssService;
import com.dreamy.hive.service.PermissionService;
import com.dreamy.hive.service.RoleService;
import com.dreamy.hive.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    
    @Autowired
    private UserRoleMapper userRoleMapper;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private PermissionService permissionService;

    @Autowired
    private OssService ossService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public Result<?> register(UserRegisterRequestDTO dto) {
        // 检查用户名是否已存在
        if (userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, dto.getUsername())) != null) {
            return Result.fail("用户名已存在");
        }
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setNickname(dto.getUsername());
        save(user);
        return Result.success();
    }

    @Override
    public Result<?> login(UserLoginRequestDTO dto) {
        User user = userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, dto.getUsername()));
        if (user == null || !passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            return Result.fail("用户名或密码错误");
        }
        StpUtil.login(user.getId());
        return Result.success(StpUtil.getTokenInfo());
    }

    @Override
    public Result<?> logout() {
        StpUtil.logout();
        return Result.success();
    }

    @Override
    public Result<User> getProfile() {
        Long userId = StpUtil.getLoginIdAsLong();
        User user = getById(userId);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        return Result.success(user);
    }

    @Override
    public Result<?> updateProfile(UserUpdateInfoRequestDTO dto) {
        Long userId = StpUtil.getLoginIdAsLong();
        User user = getById(userId);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        user.setNickname(dto.getNickname());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        updateById(user);
        return Result.success();
    }

    @Override
    public Result<?> changePassword(UserChangePasswordRequestDTO dto) {
        Long userId = StpUtil.getLoginIdAsLong();
        User user = getById(userId);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        if (!passwordEncoder.matches(dto.getOldPassword(), user.getPassword())) {
            return Result.fail("原密码错误");
        }
        user.setPassword(passwordEncoder.encode(dto.getNewPassword()));
        updateById(user);
        return Result.success();
    }

    @Override
    public Result<?> adminList(int pageNum, int pageSize, String username, Integer status) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        if (username != null && !username.isEmpty()) {
            wrapper.like(User::getUsername, username);
        }
        if (status != null) {
            wrapper.eq(User::getStatus, status);
        }
        Page<User> page = this.page(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(page);
    }

    @Override
    public Result<?> adminUpdateStatus(Long userId, Integer status) {
        User user = this.getById(userId);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        user.setStatus(status);
        this.updateById(user);
        return Result.success();
    }

    @Override
    public Result<?> adminGetDetail(Long userId) {
        User user = this.getById(userId);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        return Result.success(user);
    }

    @Override
    public Result<?> adminUpdateUser(Long userId, User updateUser) {
        User user = this.getById(userId);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        // 只允许更新部分字段（如昵称、邮箱、手机号、状态等）
        user.setNickname(updateUser.getNickname());
        user.setEmail(updateUser.getEmail());
        user.setPhone(updateUser.getPhone());
        user.setStatus(updateUser.getStatus());
        user.setAvatar(updateUser.getAvatar());
        this.updateById(user);
        return Result.success();
    }

    @Override
    public Result<?> adminResetPassword(Long userId, String newPassword) {
        User user = this.getById(userId);
        if (user == null) {
            return Result.fail("用户不存在");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        this.updateById(user);
        return Result.success();
    }
    
    @Override
    public Result<?> getPermissionCodes(Long userId) {
        Result<?> result = permissionService.getPermissionsByUserId(userId);
        if (!result.isSuccess()) {
            return Result.fail("获取权限失败");
        }
        
        List<Permission> permissions = (List<Permission>) result.getData();
        List<String> permissionCodes = permissions.stream()
                .map(Permission::getCode)
                .collect(Collectors.toList());
        
        return Result.success(permissionCodes);
    }
    
    @Override
    public Result<?> getRoleCodes(Long userId) {
        Result<?> result = roleService.getUserRoles(userId);
        if (!result.isSuccess()) {
            return Result.fail("获取角色失败");
        }
        
        List<Role> roles = (List<Role>) result.getData();
        List<String> roleCodes = roles.stream()
                .map(Role::getCode)
                .collect(Collectors.toList());
        
        return Result.success(roleCodes);
    }

    @Override
    public Result<?> updateAvatar(MultipartFile file) {
        try {
            Long userId = StpUtil.getLoginIdAsLong();
            User user = getById(userId);
            if (user == null) {
                return Result.fail("用户不存在");
            }
            
            // 如果用户已有头像，先删除旧头像
            if (user.getAvatar() != null && !user.getAvatar().isEmpty()) {
                ossService.deleteFile(user.getAvatar());
            }
            
            // 上传新头像
            FileUploadResponseDTO responseDTO = ossService.validateAndUploadAvatar(file);
            
            // 更新用户头像信息
            user.setAvatar(responseDTO.getUrl());
            updateById(user);
            
            return Result.success(responseDTO);
        } catch (Exception e) {
            log.error("更新头像失败: {}", e.getMessage());
            return Result.fail("更新头像失败: " + e.getMessage());
        }
    }
} 