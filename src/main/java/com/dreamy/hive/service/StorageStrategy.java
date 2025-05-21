package com.dreamy.hive.service;

import com.dreamy.hive.common.Result;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * 存储策略接口
 * 定义不同存储方式的通用操作
 */
public interface StorageStrategy {
    
    /**
     * 初始化存储
     */
    void init();
    
    /**
     * 存储单个文件
     * @param file 文件
     * @param directory 存储目录
     * @return 文件URL
     */
    Result<String> store(MultipartFile file, String directory);
    
    /**
     * 存储多个文件
     * @param files 文件列表
     * @param directory 存储目录
     * @return 文件URL列表
     */
    Result<List<String>> storeAll(MultipartFile[] files, String directory);
    
    /**
     * 加载文件
     * @param filename 文件名
     * @param directory 存储目录
     * @return 文件资源
     */
    Result<Resource> loadAsResource(String filename, String directory);
    
    /**
     * 删除文件
     * @param filename 文件名
     * @param directory 存储目录
     * @return 操作结果
     */
    Result<?> deleteFile(String filename, String directory);
    
    /**
     * 获取目录下所有文件
     * @param directory 存储目录
     * @return 文件路径流
     */
    Result<Stream<Path>> loadAll(String directory);
}
