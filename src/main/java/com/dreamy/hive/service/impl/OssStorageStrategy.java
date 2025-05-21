package com.dreamy.hive.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.config.OssConfig;
import com.dreamy.hive.service.StorageStrategy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * 阿里云OSS存储策略实现
 */
@Slf4j
@Service
public class OssStorageStrategy implements StorageStrategy {

    @Autowired
    private OSS ossClient;

    @Autowired
    private OssConfig ossConfig;

    @Value("${aliyun.oss.domain}")
    private String ossDomain;

    @Override
    public void init() {
        // OSS不需要初始化目录
        log.info("OSS存储初始化");
    }

    @Override
    public Result<String> store(MultipartFile file, String directory) {
        try {
            if (file.isEmpty()) {
                return Result.fail("文件为空");
            }
            
            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extension = FilenameUtils.getExtension(originalFilename);
            String newFilename = UUID.randomUUID().toString().replaceAll("-", "") + "." + extension;
            
            // 构建OSS路径
            String ossPath = directory + "/" + newFilename;
            
            // 设置元数据
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentType(file.getContentType());
            metadata.setContentLength(file.getSize());
            
            // 上传到OSS
            PutObjectRequest putObjectRequest = new PutObjectRequest(
                    ossConfig.getBucketName(), 
                    ossPath, 
                    file.getInputStream(),
                    metadata);
            
            ossClient.putObject(putObjectRequest);
            
            // 生成访问URL
            String fileUrl = ossDomain + "/" + ossPath;
            
            log.info("文件已上传到OSS: {}", fileUrl);
            return Result.success(fileUrl);
        } catch (OSSException | IOException e) {
            log.error("上传文件到OSS失败", e);
            return Result.fail("上传文件失败: " + e.getMessage());
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
            String ossPath = directory + "/" + filename;
            URL url = new URL(ossDomain + "/" + ossPath);
            Resource resource = new UrlResource(url);
            
            return Result.success(resource);
        } catch (MalformedURLException e) {
            log.error("加载OSS文件失败", e);
            return Result.fail("加载文件失败: " + e.getMessage());
        }
    }

    @Override
    public Result<?> deleteFile(String filename, String directory) {
        try {
            String ossPath = directory + "/" + filename;
            ossClient.deleteObject(ossConfig.getBucketName(), ossPath);
            
            log.info("OSS文件已删除: {}", ossPath);
            return Result.success();
        } catch (OSSException e) {
            log.error("删除OSS文件失败", e);
            return Result.fail("删除文件失败: " + e.getMessage());
        }
    }

    @Override
    public Result<Stream<Path>> loadAll(String directory) {
        // OSS不支持直接列出文件，需要使用OSS SDK的特定方法
        // 这里返回一个空实现
        log.warn("OSS不支持直接列出文件，请使用OSS SDK的特定方法");
        return Result.fail("OSS不支持此操作");
    }
}
