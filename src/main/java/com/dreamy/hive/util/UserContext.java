package com.dreamy.hive.util;

import cn.dev33.satoken.stp.StpUtil;
import com.dreamy.hive.common.exception.BusinessException;
import com.dreamy.hive.entity.User;
import com.dreamy.hive.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用户上下文工具类
 * 用于获取当前登录用户信息
 */
@Component
public class UserContext {
    
    @Autowired
    private UserService userService;
    
    /**
     * 获取当前登录用户ID
     * @return 用户ID
     */
    public Long getCurrentUserId() {
        return StpUtil.getLoginIdAsLong();
    }
    
    /**
     * 获取当前登录用户
     * @return 用户对象
     */
    public User getCurrentUser() {
        Long userId = getCurrentUserId();
        User user = userService.getById(userId);
        if (user == null) {
            throw new BusinessException("用户不存在");
        }
        return user;
    }
    
    /**
     * 检查是否有权限操作指定资源
     * @param resourceType 资源类型
     * @param resourceId 资源ID
     * @return 是否有权限
     */
    public boolean hasPermission(String resourceType, Long resourceId) {
        // 根据不同资源类型实现不同的权限检查逻辑
        // 这里只是一个示例框架，实际实现需要根据业务需求完善
        switch (resourceType) {
            case "address":
                return checkAddressPermission(resourceId);
            case "order":
                return checkOrderPermission(resourceId);
            case "post":
                return checkPostPermission(resourceId);
            default:
                return false;
        }
    }
    
    /**
     * 检查是否有权限操作地址
     * @param addressId 地址ID
     * @return 是否有权限
     */
    private boolean checkAddressPermission(Long addressId) {
        // 实际业务逻辑需要根据需求实现
        // 这里只是一个示例
        return true;
    }
    
    /**
     * 检查是否有权限操作订单
     * @param orderId 订单ID
     * @return 是否有权限
     */
    private boolean checkOrderPermission(Long orderId) {
        // 实际业务逻辑需要根据需求实现
        // 这里只是一个示例
        return true;
    }
    
    /**
     * 检查是否有权限操作帖子
     * @param postId 帖子ID
     * @return 是否有权限
     */
    private boolean checkPostPermission(Long postId) {
        // 实际业务逻辑需要根据需求实现
        // 这里只是一个示例
        return true;
    }
}
