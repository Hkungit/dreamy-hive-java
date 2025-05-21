package com.dreamy.hive.dto.request;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 帖子创建请求DTO
 */
@Data
public class PostCreateRequestDTO {
    
    /**
     * 帖子标题
     */
    private String title;
    
    /**
     * 帖子内容
     */
    private String content;
    
    /**
     * 图片文件列表（用于表单提交）
     */
    private List<MultipartFile> imageFiles;
    
    /**
     * 图片URL列表（用于JSON提交，存储已上传的图片URL）
     */
    private List<String> imageUrls;
} 