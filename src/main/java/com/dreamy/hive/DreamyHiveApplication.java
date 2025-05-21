package com.dreamy.hive;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Dreamy Hive 应用程序入口
 * 一个融合了商品商城与用户社区的轻量级系统
 */
@SpringBootApplication
@MapperScan("com.dreamy.hive.mapper")
public class DreamyHiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(DreamyHiveApplication.class, args);
        System.out.println("Dreamy Hive 启动成功");
        System.out.println("\n===========================================\n");
        System.out.println("api文档访问地址:");
        System.out.println("http://localhost:8080/api/swagger-ui.html");
        System.out.println("http://localhost:8080/api/doc.html");
        System.out.println("\n===========================================\n");
    }
}
