package com.dreamy.hive.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.dreamy.hive.annotation.RequiresPermission;
import com.dreamy.hive.common.Result;
import com.dreamy.hive.entity.Permission;
import com.dreamy.hive.service.PermissionService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限校验切面
 */
@Aspect
@Component
public class PermissionAspect {
    @Autowired
    private PermissionService permissionService;

    /**
     * 环绕通知，用于权限校验
     */
    @Around("@annotation(com.dreamy.hive.annotation.RequiresPermission)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取登录用户ID
        if (!StpUtil.isLogin()) {
            return Result.fail("未登录");
        }
        
        Long userId = StpUtil.getLoginIdAsLong();
        
        // 获取方法上的注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequiresPermission requiresPermission = method.getAnnotation(RequiresPermission.class);
        String requiredPermission = requiresPermission.value();
        boolean logical = requiresPermission.logical();
        
        // 获取用户所有权限
        Result<?> result = permissionService.getPermissionsByUserId(userId);
        if (!result.isSuccess()) {
            return Result.fail("获取权限失败");
        }
        
        List<Permission> permissions = (List<Permission>) result.getData();
        List<String> permissionCodes = permissions.stream()
                .map(Permission::getCode)
                .collect(Collectors.toList());
        
        // 判断是否包含所需权限
        if (requiredPermission.contains(",")) {
            // 多个权限
            String[] requiredPermissions = requiredPermission.split(",");
            if (logical) {
                // 需要同时具有所有权限
                for (String permission : requiredPermissions) {
                    if (!permissionCodes.contains(permission.trim())) {
                        return Result.fail("权限不足");
                    }
                }
            } else {
                // 只需要具有其中一个权限
                boolean hasPermission = false;
                for (String permission : requiredPermissions) {
                    if (permissionCodes.contains(permission.trim())) {
                        hasPermission = true;
                        break;
                    }
                }
                if (!hasPermission) {
                    return Result.fail("权限不足");
                }
            }
        } else {
            // 单个权限
            if (!permissionCodes.contains(requiredPermission)) {
                return Result.fail("权限不足");
            }
        }
        
        // 执行目标方法
        return joinPoint.proceed();
    }
} 