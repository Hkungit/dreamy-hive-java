package com.dreamy.hive.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dreamy.hive.dto.request.*;
import com.dreamy.hive.entity.User;
import com.dreamy.hive.common.Result;
import org.springframework.web.multipart.MultipartFile;

public interface UserService extends IService<User> {
    Result<?> register(UserRegisterRequestDTO dto);

    Result<?> login(UserLoginRequestDTO dto);

    Result<?> logout();

    Result<User> getProfile();

    Result<?> updateProfile(UserUpdateInfoRequestDTO dto);

    Result<?> changePassword(UserChangePasswordRequestDTO dto);

    // 后台管理：用户分页列表
    Result<?> adminList(int pageNum, int pageSize, String username, Integer status);
    // 后台管理：修改用户状态
    Result<?> adminUpdateStatus(Long userId, Integer status);
    // 后台管理：获取用户详情
    Result<?> adminGetDetail(Long userId);
    // 后台管理：编辑用户信息
    Result<?> adminUpdateUser(Long userId, User user);
    // 后台管理：重置用户密码
    Result<?> adminResetPassword(Long userId, String newPassword);

    Result<?> getPermissionCodes(Long userId);

    Result<?> getRoleCodes(Long userId);

    Result<?> updateAvatar(MultipartFile file);
}