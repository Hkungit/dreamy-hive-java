package com.dreamy.hive.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传工具类
 */
public class FileUtil {

    // 允许上传的图片类型
    private static final String[] IMAGE_TYPE = new String[]{"bmp", "jpg", "jpeg", "gif", "png"};
    
    // 允许上传的文件类型
    private static final String[] FILE_TYPE = new String[]{"pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "zip", "rar"};
    
    /**
     * 上传文件
     * @param file 上传的文件
     * @param basePath 基础路径
     * @param folder 文件夹名称
     * @return 文件访问URL
     * @throws IOException
     */
    public static String upload(MultipartFile file, String basePath, String folder) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("文件不能为空");
        }
        
        // 获取文件原始名称
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new IllegalArgumentException("文件名不能为空");
        }
        
        // 获取文件后缀
        String fileSuffix = getFileSuffix(originalFilename);
        
        // 生成新的文件名
        String newFileName = generateNewFileName(fileSuffix);
        
        // 生成日期目录
        String dateFolder = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        
        // 创建目标文件
        File dest = new File(basePath + File.separator + folder + File.separator + dateFolder, newFileName);
        
        // 如果目录不存在则创建
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        
        // 保存文件
        file.transferTo(dest);
        
        // 返回文件访问路径
        return "/" + folder + "/" + dateFolder + "/" + newFileName;
    }
    
    /**
     * 检查文件类型是否是允许的图片类型
     */
    public static boolean isImageAllowed(String fileSuffix) {
        if (fileSuffix == null || fileSuffix.isEmpty()) {
            return false;
        }
        fileSuffix = fileSuffix.toLowerCase();
        for (String type : IMAGE_TYPE) {
            if (type.equals(fileSuffix)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * 检查文件类型是否是允许的文件类型
     */
    public static boolean isFileAllowed(String fileSuffix) {
        if (fileSuffix == null || fileSuffix.isEmpty()) {
            return false;
        }
        fileSuffix = fileSuffix.toLowerCase();
        for (String type : FILE_TYPE) {
            if (type.equals(fileSuffix)) {
                return true;
            }
        }
        return isImageAllowed(fileSuffix);
    }
    
    /**
     * 获取文件后缀名（不带点）
     */
    public static String getFileSuffix(String fileName) {
        if (fileName == null || !fileName.contains(".")) {
            return "";
        }
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    
    /**
     * 生成新的文件名
     */
    private static String generateNewFileName(String fileSuffix) {
        return UUID.randomUUID().toString().replace("-", "") + "." + fileSuffix;
    }
    
    /**
     * 获取文件大小（MB）
     */
    public static double getFileSizeM(long size) {
        return (double) size / (1024 * 1024);
    }
}
