package com.dreamy.hive.dto.request;

import lombok.Data;

@Data
public class UserChangePasswordRequestDTO {
    private String oldPassword;
    private String newPassword;
}