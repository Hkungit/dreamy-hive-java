package com.dreamy.hive.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * MyBatis-Plus 元对象处理器
 * 用于自动填充创建时间、更新时间等公共字段
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    
    @Override
    public void insertFill(MetaObject metaObject) {
        // 创建时间
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        // 更新时间
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        
        // 创建人和更新人需要从当前登录用户获取，这里先设置为null，后续在Service层设置
        // this.strictInsertFill(metaObject, "createBy", Long.class, getCurrentUserId());
        // this.strictUpdateFill(metaObject, "updateBy", Long.class, getCurrentUserId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        // 更新时间
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        
        // 更新人需要从当前登录用户获取，这里先设置为null，后续在Service层设置
        // this.strictUpdateFill(metaObject, "updateBy", Long.class, getCurrentUserId());
    }
    
    /**
     * 获取当前登录用户ID
     * 需要在用户认证模块实现后完善
     */
    private Long getCurrentUserId() {
        // TODO: 从SecurityContext或Token中获取当前用户ID
        return null;
    }
}
