package com.dreamy.hive.controller.v1.common;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.service.StorageFactory;
import com.dreamy.hive.service.StorageStrategy;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 统一文件上传下载控制器
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/common/files")
@Tag(name = "文件管理", description = "文件上传下载相关接口")
public class UnifiedFileController {

    @Autowired
    private StorageFactory storageFactory;

    /**
     * 上传单个文件
     */
    @PostMapping("/upload/{storageType}/{directory}")
    @Operation(summary = "上传单个文件", description = "上传单个文件到指定存储类型和目录")
    public Result<String> uploadFile(
            @RequestParam("file") MultipartFile file,
            @PathVariable String storageType,
            @PathVariable String directory) {

        StorageStrategy strategy = storageFactory.getStrategy(storageType);
        return strategy.store(file, directory);
    }

    /**
     * 上传多个文件
     */
    @PostMapping("/upload-multiple/{storageType}/{directory}")
    @Operation(summary = "上传多个文件", description = "上传多个文件到指定存储类型和目录")
    public Result<List<String>> uploadMultipleFiles(
            @RequestParam("files") MultipartFile[] files,
            @PathVariable String storageType,
            @PathVariable String directory) {

        StorageStrategy strategy = storageFactory.getStrategy(storageType);
        return strategy.storeAll(files, directory);
    }

    /**
     * 下载文件
     */
    @GetMapping("/{storageType}/{directory}/{filename:.+}")
    @Operation(summary = "下载文件", description = "从指定存储类型和目录下载文件")
    public ResponseEntity<Resource> downloadFile(
            @PathVariable String storageType,
            @PathVariable String directory,
            @PathVariable String filename) {

        StorageStrategy strategy = storageFactory.getStrategy(storageType);
        Result<Resource> result = strategy.loadAsResource(filename, directory);

        if (!result.isSuccess()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(result.getData());
    }

    /**
     * 获取图片
     */
    @GetMapping(value = "/image/{storageType}/{directory}/{filename:.+}", produces = MediaType.IMAGE_JPEG_VALUE)
    @Operation(summary = "获取图片", description = "从指定存储类型和目录获取图片")
    public ResponseEntity<Resource> getImage(
            @PathVariable String storageType,
            @PathVariable String directory,
            @PathVariable String filename) {

        StorageStrategy strategy = storageFactory.getStrategy(storageType);
        Result<Resource> result = strategy.loadAsResource(filename, directory);

        if (!result.isSuccess()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(result.getData());
    }

    /**
     * 删除文件
     */
    @DeleteMapping("/{storageType}/{directory}/{filename:.+}")
    @Operation(summary = "删除文件", description = "从指定存储类型和目录删除文件")
    public Result<?> deleteFile(
            @PathVariable String storageType,
            @PathVariable String directory,
            @PathVariable String filename) {

        StorageStrategy strategy = storageFactory.getStrategy(storageType);
        return strategy.deleteFile(filename, directory);
    }

    /**
     * 兼容旧版本的本地文件上传接口
     */
    @PostMapping("/upload/{directory}")
    @Operation(summary = "上传单个文件（兼容旧版本）", description = "上传单个文件到本地存储的指定目录")
    public Result<String> uploadFileLocal(
            @RequestParam("file") MultipartFile file,
            @PathVariable String directory) {

        StorageStrategy strategy = storageFactory.getStrategy("local");
        return strategy.store(file, directory);
    }

    /**
     * 兼容旧版本的本地文件下载接口
     */
    @GetMapping("/{directory}/{filename:.+}")
    @Operation(summary = "下载文件（兼容旧版本）", description = "从本地存储的指定目录下载文件")
    public ResponseEntity<Resource> downloadFileLocal(
            @PathVariable String directory,
            @PathVariable String filename) {

        StorageStrategy strategy = storageFactory.getStrategy("local");
        Result<Resource> result = strategy.loadAsResource(filename, directory);

        if (!result.isSuccess()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .body(result.getData());
    }
}
