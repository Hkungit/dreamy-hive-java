package com.dreamy.hive.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 文件上传配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "file.upload")
public class FileUploadProperties {
    /**
     * 文件上传基础路径
     */
    private String basePath = "/tmp/upload";
    
    /**
     * 图片上传路径
     */
    private String imagePath = "images";
    
    /**
     * 文件上传路径
     */
    private String filePath = "files";
    
    /**
     * 头像上传路径
     */
    private String avatarPath = "avatars";
    
    /**
     * 商品图片上传路径
     */
    private String productPath = "products";
    
    /**
     * 帖子图片上传路径
     */
    private String postPath = "posts";
    
    /**
     * 允许上传的图片类型
     */
    private String[] allowedImageTypes = {"bmp", "jpg", "jpeg", "gif", "png"};
    
    /**
     * 允许上传的文件类型
     */
    private String[] allowedFileTypes = {"pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "zip", "rar"};
    
    /**
     * 最大文件大小（MB）
     */
    private long maxFileSize = 10;
    
    /**
     * 最大图片大小（MB）
     */
    private long maxImageSize = 5;
    
    /**
     * 是否允许覆盖已存在的文件
     */
    private boolean allowOverride = false;
}
