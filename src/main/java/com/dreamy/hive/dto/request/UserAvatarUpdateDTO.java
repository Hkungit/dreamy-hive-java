package com.dreamy.hive.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户头像更新请求DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAvatarUpdateDTO {
    /**
     * 头像文件
     */
    private MultipartFile file;
} 