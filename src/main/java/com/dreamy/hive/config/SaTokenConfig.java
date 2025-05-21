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
            // 指定路由拦截
            SaRouter.match("/api/admin/**", r -> StpUtil.checkRole("admin"));

            // 可以添加其他匹配规则
            // SaRouter.match("/api/user/**", r -> StpUtil.checkLogin());
        })).addPathPatterns("/**");
        
        System.out.println("[Sa-Token] 已放行 Swagger/Knife4j 相关资源路径");
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
