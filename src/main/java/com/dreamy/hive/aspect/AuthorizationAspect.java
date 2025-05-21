package com.dreamy.hive.aspect;

import cn.dev33.satoken.stp.StpUtil;
import com.dreamy.hive.annotation.RequiresLogin;
import com.dreamy.hive.annotation.RequiresPermission;
import com.dreamy.hive.annotation.RequiresRole;
import com.dreamy.hive.common.exception.BusinessException;
import com.dreamy.hive.service.impl.PermissionServiceExtension;
import com.dreamy.hive.service.impl.RoleServiceExtension;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限校验切面
 * 用于处理 RequiresLogin, RequiresPermission, RequiresRole 注解
 */
@Aspect
@Component
public class AuthorizationAspect {
    
    private static final Logger log = LoggerFactory.getLogger(AuthorizationAspect.class);
    
    @Autowired
    private PermissionServiceExtension permissionService;
    
    @Autowired
    private RoleServiceExtension roleService;
    
    /**
     * 处理 RequiresLogin 注解
     */
    @Around("@annotation(requiresLogin) || @within(requiresLogin)")
    public Object checkLogin(ProceedingJoinPoint joinPoint, RequiresLogin requiresLogin) throws Throwable {
        if (!StpUtil.isLogin()) {
            log.warn("未登录用户尝试访问需要登录的接口");
            throw new BusinessException("请先登录");
        }
        return joinPoint.proceed();
    }
    
    /**
     * 处理 RequiresPermission 注解
     */
    @Around("@annotation(com.dreamy.hive.annotation.RequiresPermission)")
    public Object checkPermission(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!StpUtil.isLogin()) {
            log.warn("未登录用户尝试访问需要权限的接口");
            throw new BusinessException("请先登录");
        }
        
        Long userId = StpUtil.getLoginIdAsLong();
        
        // 获取方法上的注解
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        RequiresPermission requiresPermission = method.getAnnotation(RequiresPermission.class);
        
        String permissionValue = requiresPermission.value();
        boolean logical = requiresPermission.logical();
        
        // 检查权限
        if (permissionValue.contains(",")) {
            // 多个权限
            List<String> permissionCodes = Arrays.stream(permissionValue.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
            
            boolean hasPermission;
            if (logical) {
                // 需要同时具有所有权限
                hasPermission = permissionService.hasAllPermissions(userId, permissionCodes);
            } else {
                // 只需要具有其中一个权限
                hasPermission = permissionService.hasAnyPermission(userId, permissionCodes);
            }
            
            if (!hasPermission) {
                log.warn("用户 {} 权限不足，需要权限: {}", userId, permissionValue);
                throw new BusinessException("权限不足");
            }
        } else {
            // 单个权限
            if (!permissionService.hasPermission(userId, permissionValue)) {
                log.warn("用户 {} 权限不足，需要权限: {}", userId, permissionValue);
                throw new BusinessException("权限不足");
            }
        }
        
        return joinPoint.proceed();
    }
    
    /**
     * 处理 RequiresRole 注解
     */
    @Around("@annotation(requiresRole) || @within(requiresRole)")
    public Object checkRole(ProceedingJoinPoint joinPoint, RequiresRole requiresRole) throws Throwable {
        if (!StpUtil.isLogin()) {
            log.warn("未登录用户尝试访问需要角色的接口");
            throw new BusinessException("请先登录");
        }
        
        Long userId = StpUtil.getLoginIdAsLong();
        String roleCode = requiresRole.value();
        
        // 检查角色
        if (!roleService.hasRole(userId, roleCode)) {
            log.warn("用户 {} 角色不足，需要角色: {}", userId, roleCode);
            throw new BusinessException("角色权限不足");
        }
        
        return joinPoint.proceed();
    }
}
