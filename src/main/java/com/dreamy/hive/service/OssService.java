package com.dreamy.hive.service;

import com.dreamy.hive.dto.response.FileUploadResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 阿里云OSS服务接口
 */
public interface OssService {
    
    /**
     * 验证并上传图片到OSS
     * @param file 图片文件
     * @return 文件上传响应DTO
     */
    FileUploadResponseDTO validateAndUploadImage(MultipartFile file) throws IOException;
    
    /**
     * 验证并上传头像到OSS
     * @param file 头像文件
     * @return 文件上传响应DTO
     */
    FileUploadResponseDTO validateAndUploadAvatar(MultipartFile file) throws IOException;
    
    /**
     * 验证并上传商品图片到OSS
     * @param file 商品图片文件
     * @return 文件上传响应DTO
     */
    FileUploadResponseDTO validateAndUploadProductImage(MultipartFile file) throws IOException;
    
    /**
     * 验证并上传帖子图片到OSS
     * @param file 帖子图片文件
     * @return 文件上传响应DTO
     */
    FileUploadResponseDTO validateAndUploadPostImage(MultipartFile file) throws IOException;
    
    /**
     * 从OSS删除文件
     * @param fileUrl 文件URL
     * @return 是否删除成功
     */
    boolean deleteFile(String fileUrl);
    
    /**
     * 获取文件的访问URL
     * @param objectName 文件在OSS中的对象名称
     * @return 文件访问URL
     */
    String getFileUrl(String objectName);
} 