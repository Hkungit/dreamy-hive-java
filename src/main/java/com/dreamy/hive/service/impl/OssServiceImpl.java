package com.dreamy.hive.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.dreamy.hive.config.OssConfig;
import com.dreamy.hive.dto.response.FileUploadResponseDTO;
import com.dreamy.hive.exception.FileUploadException;
import com.dreamy.hive.service.OssService;
import com.dreamy.hive.util.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 阿里云OSS服务实现类
 */
@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OSS ossClient;

    @Autowired
    private OssConfig ossConfig;

    @Value("${aliyun.oss.dir.avatar}")
    private String avatarDir;

    @Value("${aliyun.oss.dir.image}")
    private String imageDir;

    @Value("${aliyun.oss.dir.product}")
    private String productDir;

    @Value("${aliyun.oss.dir.post}")
    private String postDir;

    @Value("${aliyun.oss.domain}")
    private String ossDomain;
    
    // Using hardcoded allowed image types instead of property injection
    private final List<String> allowedImageTypes = List.of("bmp", "jpg", "jpeg", "gif", "png");
    
    @Value("${file.upload.maxFileSize}")
    private long maxFileSize;
    
    @Value("${file.upload.maxImageSize}")
    private long maxImageSize;

    @Override
    public FileUploadResponseDTO validateAndUploadImage(MultipartFile file) throws IOException {
        // 验证图片
        validateImage(file);
        
        // 上传图片
        String fileUrl = uploadFile(file, imageDir);
        
        // 构建响应
        return buildResponseDTO(file, fileUrl);
    }

    @Override
    public FileUploadResponseDTO validateAndUploadAvatar(MultipartFile file) throws IOException {
        // 验证图片
        validateImage(file);
        
        // 上传头像
        String fileUrl = uploadFile(file, avatarDir);
        
        // 构建响应
        return buildResponseDTO(file, fileUrl);
    }

    @Override
    public FileUploadResponseDTO validateAndUploadProductImage(MultipartFile file) throws IOException {
        // 验证图片
        validateImage(file);
        
        // 上传商品图片
        String fileUrl = uploadFile(file, productDir);
        
        // 构建响应
        return buildResponseDTO(file, fileUrl);
    }

    @Override
    public FileUploadResponseDTO validateAndUploadPostImage(MultipartFile file) throws IOException {
        // 验证图片
        validateImage(file);
        
        // 上传帖子图片
        String fileUrl = uploadFile(file, postDir);
        
        // 构建响应
        return buildResponseDTO(file, fileUrl);
    }
    
    /**
     * 上传文件到OSS
     * @param file 文件
     * @param dirPrefix 目录前缀
     * @return 文件访问URL
     */
    private String uploadFile(MultipartFile file, String dirPrefix) throws IOException {
        // 生成唯一文件名
        String originalFilename = file.getOriginalFilename();
        String extension = FilenameUtils.getExtension(originalFilename);
        String newFilename = UUID.randomUUID().toString().replaceAll("-", "") + "." + extension;
        
        // 构建OSS路径
        String ossPath = dirPrefix + newFilename;
        
        try (InputStream inputStream = file.getInputStream()) {
            // 设置元数据
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(FileUtils.getContentType(extension));
            metadata.setContentLength(file.getSize());
            
            // 上传文件到OSS
            PutObjectRequest putObjectRequest = new PutObjectRequest(ossConfig.getBucketName(), ossPath, inputStream, metadata);
            ossClient.putObject(putObjectRequest);
            
            // 返回文件访问URL
            return getFileUrl(ossPath);
        } catch (OSSException e) {
            log.error("OSS上传文件失败", e);
            throw new FileUploadException("文件上传失败: " + e.getMessage());
        }
    }

    @Override
    public boolean deleteFile(String fileUrl) {
        if (fileUrl == null || fileUrl.isEmpty()) {
            return false;
        }
        
        try {
            // 从URL提取对象名
            String objectName = extractObjectNameFromUrl(fileUrl);
            if (objectName == null) {
                return false;
            }
            
            // 从OSS删除文件
            ossClient.deleteObject(ossConfig.getBucketName(), objectName);
            return true;
        } catch (Exception e) {
            log.error("OSS删除文件失败: {}", fileUrl, e);
            return false;
        }
    }

    /**
     * 获取文件的访问URL
     * @param objectName 文件在OSS中的对象名称
     * @return 文件访问URL
     */
    public String getFileUrl(String objectName) {
        // 如果已配置自定义域名，直接使用
        if (ossDomain != null && !ossDomain.isEmpty()) {
            return ossDomain + "/" + objectName;
        }
        
        // 否则生成签名URL
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        URL url = ossClient.generatePresignedUrl(ossConfig.getBucketName(), objectName, expiration);
        return url.toString();
    }
    
    /**
     * 校验图片
     */
    private void validateImage(MultipartFile file) throws IOException {
        // 基本安全检查
        FileUtils.validateFileSecurity(file);
        
        // 检查文件是否为空
        if (file.isEmpty()) {
            throw new FileUploadException("图片文件为空");
        }
        
        // 检查文件大小
        if (file.getSize() > maxImageSize * 1024 * 1024) {
            throw new FileUploadException("图片大小不能超过 " + maxImageSize + "MB");
        }
        
        // 检查文件类型
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (extension == null || !allowedImageTypes.contains(extension.toLowerCase())) {
            throw new FileUploadException("图片格式不支持，支持的格式有: " + String.join(", ", allowedImageTypes));
        }
    }
    
    /**
     * 构建文件上传响应DTO
     */
    private FileUploadResponseDTO buildResponseDTO(MultipartFile file, String fileUrl) {
        return FileUploadResponseDTO.builder()
                .fileName(FileUtils.generateUniqueFilename(file.getOriginalFilename()))
                .originalFileName(file.getOriginalFilename())
                .url(fileUrl)
                .size(file.getSize())
                .contentType(file.getContentType())
                .build();
    }
    
    /**
     * 从URL提取OSS对象名
     */
    private String extractObjectNameFromUrl(String fileUrl) {
        // 如果使用自定义域名
        if (ossDomain != null && !ossDomain.isEmpty() && fileUrl.startsWith(ossDomain)) {
            return fileUrl.substring(ossDomain.length() + 1);
        }
        
        // 如果使用默认域名（格式通常为: https://bucket-name.endpoint/object-name）
        try {
            URL url = new URL(fileUrl);
            String path = url.getPath();
            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            return path;
        } catch (Exception e) {
            log.error("从URL提取对象名失败: {}", fileUrl, e);
            return null;
        }
    }
} 