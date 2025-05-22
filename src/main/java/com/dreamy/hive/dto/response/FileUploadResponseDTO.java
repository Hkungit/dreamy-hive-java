package com.dreamy.hive.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文件上传响应DTO
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadResponseDTO {
    
    /**
     * 文件名
     */
    private String fileName;
    
    /**
     * 原始文件名
     */
    private String originalFileName;
    
    /**
     * 文件访问URL
     */
    private String url;
    
    /**
     * 文件大小，单位byte
     */
    private Long size;
    
    /**
     * 文件类型
     */
    private String contentType;
} 