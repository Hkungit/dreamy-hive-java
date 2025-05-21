package com.dreamy.hive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 存储策略工厂
 * 用于获取不同的存储策略实现
 */
@Component
public class StorageFactory {
    
    private final Map<String, StorageStrategy> strategies;
    
    @Autowired
    public StorageFactory(Map<String, StorageStrategy> strategies) {
        this.strategies = new ConcurrentHashMap<>(strategies);
    }
    
    /**
     * 获取存储策略
     * @param type 存储类型，如 "local", "oss" 等
     * @return 存储策略实现
     */
    public StorageStrategy getStrategy(String type) {
        StorageStrategy strategy = strategies.get(type + "StorageStrategy");
        if (strategy == null) {
            // 默认使用本地存储
            strategy = strategies.get("localStorageStrategy");
        }
        return strategy;
    }
}
