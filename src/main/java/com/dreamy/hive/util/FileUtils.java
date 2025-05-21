package com.dreamy.hive.util;

import com.dreamy.hive.exception.FileUploadException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 文件工具类
 */
public class FileUtils {
    
    /**
     * 文件扩展名与MIME类型的映射
     */
    private static final Map<String, String> CONTENT_TYPE_MAP = new HashMap<>();
    
    static {
        // 图片
        CONTENT_TYPE_MAP.put("jpg", "image/jpeg");
        CONTENT_TYPE_MAP.put("jpeg", "image/jpeg");
        CONTENT_TYPE_MAP.put("png", "image/png");
        CONTENT_TYPE_MAP.put("gif", "image/gif");
        CONTENT_TYPE_MAP.put("bmp", "image/bmp");
        
        // 文档
        CONTENT_TYPE_MAP.put("doc", "application/msword");
        CONTENT_TYPE_MAP.put("docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        CONTENT_TYPE_MAP.put("xls", "application/vnd.ms-excel");
        CONTENT_TYPE_MAP.put("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        CONTENT_TYPE_MAP.put("ppt", "application/vnd.ms-powerpoint");
        CONTENT_TYPE_MAP.put("pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        CONTENT_TYPE_MAP.put("pdf", "application/pdf");
        CONTENT_TYPE_MAP.put("txt", "text/plain");
        
        // 压缩文件
        CONTENT_TYPE_MAP.put("zip", "application/zip");
        CONTENT_TYPE_MAP.put("rar", "application/x-rar-compressed");
    }
    
    /**
     * 获取文件的内容类型
     * @param extension 文件扩展名
     * @return 文件内容类型
     */
    public static String getContentType(String extension) {
        if (extension == null) {
            return "application/octet-stream";
        }
        
        String contentType = CONTENT_TYPE_MAP.get(extension.toLowerCase());
        return contentType != null ? contentType : "application/octet-stream";
    }
    
    /**
     * 生成唯一文件名
     * @param originalFilename 原始文件名
     * @return 新文件名
     */
    public static String generateUniqueFilename(String originalFilename) {
        String extension = FilenameUtils.getExtension(originalFilename);
        return UUID.randomUUID().toString().replaceAll("-", "") + "." + extension;
    }
    
    /**
     * 生成基于文件内容的唯一文件名（MD5）
     * @param file 文件
     * @return 新文件名
     */
    public static String generateContentBasedFilename(MultipartFile file) throws IOException {
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        String md5 = DigestUtils.md5DigestAsHex(file.getBytes());
        return md5 + "." + extension;
    }
    
    /**
     * 检查文件类型是否允许
     * @param filename 文件名
     * @param allowedTypes 允许的类型列表
     * @return 是否允许
     */
    public static boolean isAllowedFileType(String filename, List<String> allowedTypes) {
        String extension = FilenameUtils.getExtension(filename);
        return extension != null && allowedTypes.contains(extension.toLowerCase());
    }
    
    /**
     * 校验文件安全性
     * @param file 文件
     * @throws FileUploadException 如果文件不安全
     */
    public static void validateFileSecurity(MultipartFile file) throws IOException {
        if (file.isEmpty()) {
            throw new FileUploadException("文件为空");
        }
        
        // 检查文件名中的特殊字符
        String filename = file.getOriginalFilename();
        if (filename == null || filename.contains("../") || filename.contains("..\\")) {
            throw new FileUploadException("文件名不合法");
        }
        
        // 可以添加更多的安全检查，如文件内容分析、病毒扫描等
    }
} 