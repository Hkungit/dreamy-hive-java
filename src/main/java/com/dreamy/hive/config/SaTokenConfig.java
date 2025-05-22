package com.dreamy.hive.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * Sa-Token 配置类
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {
    
    /**
     * 注册 Sa-Token 拦截器，打开注解式鉴权功能 
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册 Sa-Token 拦截器，定义拦截规则
        registry.addInterceptor(new SaInterceptor(handler -> {
            // 指定路由拦截 - 允许super_admin和admin角色访问管理员接口
            SaRouter.match("/v1/admin/**", r -> {
                // 检查用户是否拥有super_admin或admin角色
                if (StpUtil.hasRole("super_admin") || StpUtil.hasRole("admin")) {
                    // 有权限，继续执行
                    return;
                } else {
                    // 没有权限，抛出异常
                    StpUtil.checkRole("admin"); // 这会抛出角色不足的异常
                }
            });

            // 可以添加其他匹配规则
            // SaRouter.match("/v1/user/**", r -> StpUtil.checkLogin());
        })).addPathPatterns("/**");
        
        System.out.println("[Sa-Token] 已配置管理员权限拦截：允许super_admin和admin角色访问");
    }
    
    /**
     * 添加参数解析器
     */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // 添加 @CurrentUser 注解解析器
        resolvers.add(new CurrentUserMethodArgumentResolver());
    }
}
