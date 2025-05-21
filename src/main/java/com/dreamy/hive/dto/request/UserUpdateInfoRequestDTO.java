package com.dreamy.hive.dto.request;

import lombok.Data;

@Data
public class UserUpdateInfoRequestDTO {
    // 根据实际需求，可以添加如昵称、头像、邮箱、手机号等字段
    private String nickname;
    private String email;
    private String phone;
    // 如果允许修改用户名，也可以加上 private String username;
}