package com.dreamy.hive.controller.v1.base;

import com.dreamy.hive.common.Result;
import com.dreamy.hive.util.UserContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 基础控制器
 * 提供通用的工具方法，如获取当前用户和统一响应格式
 */
public abstract class BaseController {
    
    protected final Logger log = LoggerFactory.getLogger(getClass());
    
    @Autowired
    protected UserContext userContext;
    
    /**
     * 获取当前用户ID
     */
    protected Long getCurrentUserId() {
        return userContext.getCurrentUserId();
    }
    
    /**
     * 成功响应
     */
    protected <T> Result<T> success(T data) {
        return Result.success(data);
    }
    
    /**
     * 成功响应（无数据）
     */
    protected Result<?> success() {
        return Result.success();
    }
    
    /**
     * 失败响应
     */
    protected Result<?> fail(String message) {
        return Result.fail(message);
    }
}
