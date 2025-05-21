# 处理 Spring Boot 中的循环依赖

## 问题描述

在 Spring Boot 应用程序中，当两个或多个 Bean 相互依赖时，会形成循环依赖。Spring 容器在尝试初始化这些 Bean 时无法确定实例化顺序，从而导致应用程序启动失败。

我们的应用程序中存在以下循环依赖：
1. `SkuServiceImpl` 依赖于 `SkuAttributeService`
2. `SkuAttributeServiceImpl` 依赖于 `SkuService`

## 解决方案

### 1. 创建中间服务

我们创建了一个 `ProductValidationService` 来处理验证逻辑，这样 `SkuServiceImpl` 和 `SkuAttributeServiceImpl` 都可以依赖于这个服务，而不是相互依赖。

### 2. 使用 @Lazy 注解

可以在其中一个依赖上使用 `@Lazy` 注解来延迟初始化：

```java
@Service
public class ServiceA {
    private final ServiceB serviceB;
    
    public ServiceA(@Lazy ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
```

### 3. 使用 setter 注入而不是构造函数注入

可以使用 setter 注入来代替构造函数注入，这样 Spring 可以先创建对象，然后再设置依赖：

```java
@Service
public class ServiceA {
    private ServiceB serviceB;
    
    @Autowired
    public void setServiceB(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
```

### 4. 启用循环引用支持（不推荐）

作为最后的手段，可以在 `application.properties` 中添加以下配置来允许循环引用：

```properties
spring.main.allow-circular-references=true
```

但这只是一种临时解决方案，不推荐在生产环境中使用。最好的做法是重构代码以消除循环依赖。

## 最佳实践

1. 遵循单一职责原则，确保每个服务只负责一个功能领域
2. 使用中间服务或抽象接口来打破循环依赖
3. 考虑使用事件驱动架构来解耦服务
4. 重构代码以消除不必要的依赖关系 