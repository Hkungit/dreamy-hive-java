package com.dreamy.hive.config;

import cn.dev33.satoken.exception.DisableServiceException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Sa-Token 全局异常处理
 */
@RestControllerAdvice
public class SaTokenExceptionHandler {

    /**
     * 处理未登录异常
     */
    @ExceptionHandler(NotLoginException.class)
    public Map<String, Object> handleNotLoginException(NotLoginException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 401);
        result.put("success", false);
        
        String message;
        if (e.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token";
        } else if (e.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if (e.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else if (e.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        } else if (e.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        } else {
            message = "当前会话未登录";
        }
        
        result.put("message", message);
        return result;
    }

    /**
     * 处理没有权限异常
     */
    @ExceptionHandler(NotPermissionException.class)
    public Map<String, Object> handleNotPermissionException(NotPermissionException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 403);
        result.put("success", false);
        result.put("message", "无此权限：" + e.getPermission());
        return result;
    }

    /**
     * 处理没有角色异常
     */
    @ExceptionHandler(NotRoleException.class)
    public Map<String, Object> handleNotRoleException(NotRoleException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 403);
        result.put("success", false);
        result.put("message", "无此角色：" + e.getRole());
        return result;
    }

    /**
     * 处理服务被封禁异常
     */
    @ExceptionHandler(DisableServiceException.class)
    public Map<String, Object> handleDisableServiceException(DisableServiceException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("code", 403);
        result.put("success", false);
        result.put("message", "服务已被禁用：" + e.getService() + "，剩余时间：" + e.getDisableTime() + "秒");
        return result;
    }
}
