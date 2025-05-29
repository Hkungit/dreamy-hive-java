package com.dreamy.hive.controller.v1.admin;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.service.StorageFactory;
import com.dreamy.hive.service.StorageStrategy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理端文件上传控制器
 */
@Slf4j
@RestController
@RequestMapping("/admin")
@Tag(name = "管理端-文件上传", description = "管理端文件上传相关接口")
public class AdminFileUploadController {

    @Autowired
    private StorageFactory storageFactory;

    @Value("${file.upload.storage-type:local}")
    private String defaultStorageType;

    /**
     * 通用文件上传接口
     */
    @PostMapping("/upload")
    @Operation(summary = "上传文件", description = "管理端通用文件上传接口，支持商品图片等")
    public Result<Map<String, Object>> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            // 使用配置的存储策略（优先使用OSS，如果配置不可用则使用本地存储）
            StorageStrategy strategy = getStorageStrategy();
            
            // 根据文件类型确定存储目录
            String directory = determineDirectory(file);
            
            // 存储文件
            Result<String> result = strategy.store(file, directory);
            
            if (!result.isSuccess()) {
                return Result.fail(result.getMessage());
            }
            
            // 构建响应数据，符合前端期望的格式
            Map<String, Object> data = new HashMap<>();
            data.put("url", result.getData());
            data.put("name", file.getOriginalFilename());
            data.put("size", file.getSize());
            
            return Result.success(data);
            
        } catch (Exception e) {
            log.error("文件上传失败", e);
            return Result.fail("文件上传失败: " + e.getMessage());
        }
    }

    /**
     * 批量文件上传接口
     */
    @PostMapping("/upload-multiple")
    @Operation(summary = "批量上传文件", description = "管理端批量文件上传接口")
    public Result<List<Map<String, Object>>> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) {
        try {
            StorageStrategy strategy = getStorageStrategy();
            
            // 存储所有文件到商品目录
            Result<List<String>> result = strategy.storeAll(files, "products");
            
            if (!result.isSuccess()) {
                return Result.fail(result.getMessage());
            }
            
            // 构建响应数据
            List<Map<String, Object>> dataList = result.getData().stream()
                    .map(url -> {
                        Map<String, Object> data = new HashMap<>();
                        data.put("url", url);
                        return data;
                    })
                    .toList();
            
            return Result.success(dataList);
            
        } catch (Exception e) {
            log.error("批量文件上传失败", e);
            return Result.fail("批量文件上传失败: " + e.getMessage());
        }
    }

    /**
     * 获取存储策略
     */
    private StorageStrategy getStorageStrategy() {
        try {
            // 优先尝试使用OSS存储
            StorageStrategy ossStrategy = storageFactory.getStrategy("oss");
            if (ossStrategy != null) {
                return ossStrategy;
            }
        } catch (Exception e) {
            log.warn("OSS存储不可用，使用本地存储: {}", e.getMessage());
        }
        
        // 如果OSS不可用，使用本地存储
        return storageFactory.getStrategy("local");
    }

    /**
     * 根据文件类型确定存储目录
     */
    private String determineDirectory(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            return "general";
        }
        
        String extension = getFileExtension(originalFilename).toLowerCase();
        
        // 根据文件扩展名确定目录
        if (isImageFile(extension)) {
            return "products"; // 商品图片目录
        } else {
            return "files"; // 其他文件目录
        }
    }

    /**
     * 判断是否为图片文件
     */
    private boolean isImageFile(String extension) {
        return extension.matches("jpg|jpeg|png|gif|bmp|webp");
    }

    /**
     * 获取文件扩展名
     */
    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex == -1) {
            return "";
        }
        return filename.substring(lastDotIndex + 1);
    }
} 