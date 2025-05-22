package com.dreamy.hive.config;

import com.dreamy.hive.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 文件上传配置类
 */
@Configuration
public class FileUploadConfiguration implements WebMvcConfigurer {

    @Autowired
    private FileStorageService fileStorageService;
    
    @Value("${file.upload.root-dir:uploads}")
    private String rootDirectory;
    
    /**
     * 初始化存储目录
     */
    @Bean
    CommandLineRunner initFileStorage() {
        return args -> {
            fileStorageService.init();
        };
    }
    
    /**
     * 配置静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 原有的uploads路径映射
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:uploads/");
        
        // 本地存储文件访问路径
        registry.addResourceHandler("/local-files/**")
                .addResourceLocations("file:" + rootDirectory + "/");
        
        // 通用文件访问路径
        registry.addResourceHandler("/files/**")
                .addResourceLocations("file:" + rootDirectory + "/");
    }
} 