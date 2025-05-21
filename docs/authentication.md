# Dreamy Hive 认证与授权实现文档

## Sa-Token 集成

本项目使用 Sa-Token 作为认证和授权框架，实现了用户登录、角色权限控制等功能。

### 1. 基本配置

Sa-Token 配置类 `SaTokenConfig` 实现了 `WebMvcConfigurer` 接口，主要完成以下配置：

```java
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
```

### 2. @CurrentUser 注解实现

为了方便在控制器方法中获取当前登录用户ID，我们实现了自定义注解 `@CurrentUser`。

#### 2.1 注解定义

```java
package com.dreamy.hive.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 获取当前登录用户ID的注解
 */
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface CurrentUser {
}
```

#### 2.2 注解解析器

```java
package com.dreamy.hive.config;

import cn.dev33.satoken.stp.StpUtil;
import com.dreamy.hive.annotation.CurrentUser;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * CurrentUser注解的参数解析器
 */
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentUser.class) && 
               parameter.getParameterType().equals(Long.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                 NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        // 使用Sa-Token获取当前登录用户ID
        if (StpUtil.isLogin()) {
            return StpUtil.getLoginIdAsLong();
        }
        return null;
    }
}
```

### 3. 使用方式

在控制器方法中，可以通过 `@CurrentUser` 注解直接获取当前登录用户的ID：

```java
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 创建订单
     * @param userId 当前用户ID
     * @param orderCreateRequestDTO 订单创建请求
     * @return 订单信息
     */
    @PostMapping
    public Result<OrderResponseDTO> createOrder(@CurrentUser Long userId, 
                                              @RequestBody @Valid OrderCreateRequestDTO orderCreateRequestDTO) {
        OrderResponseDTO orderResponseDTO = orderService.createOrder(userId, orderCreateRequestDTO);
        return Result.success(orderResponseDTO);
    }
}
```

### 4. 管理员权限控制

通过 Sa-Token 的路由拦截器，我们实现了基于角色的访问控制，例如 `/api/admin/**` 路径需要 `admin` 角色才能访问：

```java
SaRouter.match("/api/admin/**", r -> StpUtil.checkRole("admin"));
```

### 5. 登录与注销

在用户登录时，调用 `StpUtil.login(user.getId())` 进行登录认证，登录成功后可以获取 token 信息：

```java
StpUtil.login(user.getId());
return Result.success(StpUtil.getTokenInfo());
```

用户注销时，调用 `StpUtil.logout()` 方法：

```java
StpUtil.logout();
```

### 6. 获取当前用户ID

在服务层中，可以通过 `StpUtil.getLoginIdAsLong()` 获取当前登录用户ID：

```java
Long userId = StpUtil.getLoginIdAsLong();
```

也可以通过 `StpUtil.isLogin()` 判断用户是否已登录：

```java
if (StpUtil.isLogin()) {
    // 用户已登录
    Long currentUserId = StpUtil.getLoginIdAsLong();
}
```

## 总结

通过集成 Sa-Token 框架并实现自定义的 `@CurrentUser` 注解，我们实现了一个简洁而强大的认证与授权系统，使得控制器方法可以更方便地获取当前登录用户的信息，同时也实现了基于角色的访问控制。 