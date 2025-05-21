package com.dreamy.hive.service.impl;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.service.StorageStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * 本地存储策略实现
 */
@Slf4j
@Service
public class LocalStorageStrategy implements StorageStrategy {

    @Value("${file.upload.root-dir:uploads}")
    private String rootDirectory;

    @Override
    public void init() {
        try {
            Path rootPath = Paths.get(rootDirectory);
            if (!Files.exists(rootPath)) {
                Files.createDirectories(rootPath);
                log.info("已创建根目录: {}", rootPath);
            }
        } catch (IOException e) {
            log.error("初始化存储目录失败", e);
            throw new RuntimeException("初始化存储目录失败", e);
        }
    }

    @Override
    public Result<String> store(MultipartFile file, String directory) {
        try {
            if (file.isEmpty()) {
                return Result.fail("文件为空");
            }
            
            // 创建目录
            Path dirPath = createDirectory(directory);
            
            // 生成唯一文件名
            String originalFilename = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));
            String fileExtension = getFileExtension(originalFilename);
            String newFilename = UUID.randomUUID() + fileExtension;
            
            // 保存文件
            Path destinationFile = dirPath.resolve(newFilename);
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
            
            // 生成访问URL
            String fileUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/local-files/")
                    .path(directory)
                    .path("/")
                    .path(newFilename)
                    .toUriString();
            
            log.info("文件已上传: {}", destinationFile);
            return Result.success(fileUrl);
        } catch (IOException e) {
            log.error("存储文件失败", e);
            return Result.fail("存储文件失败: " + e.getMessage());
        }
    }

    @Override
    public Result<List<String>> storeAll(MultipartFile[] files, String directory) {
        List<String> fileUrls = new ArrayList<>();
        
        for (MultipartFile file : files) {
            Result<String> result = store(file, directory);
            if (result.isSuccess()) {
                fileUrls.add(result.getData());
            } else {
                return Result.fail(result.getMessage());
            }
        }
        
        return Result.success(fileUrls);
    }

    @Override
    public Result<Resource> loadAsResource(String filename, String directory) {
        try {
            Path dirPath = Paths.get(rootDirectory, directory);
            Path file = dirPath.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            
            if (resource.exists() || resource.isReadable()) {
                return Result.success(resource);
            } else {
                return Result.fail("无法读取文件: " + filename);
            }
        } catch (MalformedURLException e) {
            log.error("加载文件失败", e);
            return Result.fail("加载文件失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> deleteFile(String filename, String directory) {
        try {
            Path dirPath = Paths.get(rootDirectory, directory);
            Path file = dirPath.resolve(filename);
            
            boolean deleted = FileSystemUtils.deleteRecursively(file);
            
            if (deleted) {
                log.info("文件已删除: {}", file);
                return Result.success();
            } else {
                return Result.fail("删除文件失败: " + filename);
            }
        } catch (IOException e) {
            log.error("删除文件失败", e);
            return Result.fail("删除文件失败: " + e.getMessage());
        }
    }

    @Override
    public Result<Stream<Path>> loadAll(String directory) {
        try {
            Path dirPath = Paths.get(rootDirectory, directory);
            
            if (!Files.exists(dirPath)) {
                return Result.fail("目录不存在: " + directory);
            }
            
            Stream<Path> files = Files.walk(dirPath, 1)
                    .filter(path -> !path.equals(dirPath))
                    .map(dirPath::relativize);
            
            return Result.success(files);
        } catch (IOException e) {
            log.error("加载文件列表失败", e);
            return Result.fail("加载文件列表失败: " + e.getMessage());
        }
    }
    
    /**
     * 创建目录
     * @param directory 目录名
     * @return 目录路径
     * @throws IOException IO异常
     */
    private Path createDirectory(String directory) throws IOException {
        Path dirPath = Paths.get(rootDirectory, directory);
        
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath);
            log.info("已创建目录: {}", dirPath);
        }
        
        return dirPath;
    }
    
    /**
     * 获取文件扩展名
     * @param filename 文件名
     * @return 扩展名（包含点号）
     */
    private String getFileExtension(String filename) {
        int lastDotIndex = filename.lastIndexOf('.');
        if (lastDotIndex > 0) {
            return filename.substring(lastDotIndex);
        }
        return "";
    }
}
