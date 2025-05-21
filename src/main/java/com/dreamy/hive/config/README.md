# Dreamy Hive 配置包

本包包含项目中的各种配置类。

## SaTokenConfig

`SaTokenConfig` 是 Sa-Token 认证框架的配置类，实现了 `WebMvcConfigurer` 接口。它主要完成以下配置：

1. 注册 Sa-Token 拦截器，配置路由拦截规则
2. 添加自定义参数解析器，如 `CurrentUserMethodArgumentResolver`

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

## CurrentUserMethodArgumentResolver

`CurrentUserMethodArgumentResolver` 是一个自定义的方法参数解析器，用于解析带有 `@CurrentUser` 注解的方法参数。

```java
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

### 工作原理

1. `supportsParameter` 方法判断参数是否带有 `@CurrentUser` 注解且类型为 `Long`
2. `resolveArgument` 方法使用 Sa-Token 的 `StpUtil.getLoginIdAsLong()` 获取当前登录用户的ID
3. 如果用户未登录，则返回 `null`

### 使用场景

此解析器主要用于简化控制器方法中获取当前用户ID的代码，使得开发者可以通过简单的注解来获取当前用户信息，而不需要在每个方法中手动调用 `StpUtil.getLoginIdAsLong()`。 