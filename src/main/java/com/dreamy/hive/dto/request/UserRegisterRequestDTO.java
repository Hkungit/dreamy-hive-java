package com.dreamy.hive.dto.request;

import lombok.Data;

@Data
public class UserRegisterRequestDTO {
    private String username;
    private String password;
    // 可以添加其他注册所需字段，例如 email, phone等，根据实际需求
}