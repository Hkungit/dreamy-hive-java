# Dreamy Hive 注解包

本包包含项目中使用的自定义注解。

## @CurrentUser

`@CurrentUser` 注解用于在控制器方法参数中获取当前登录用户的ID。

### 使用方式

```java
@GetMapping("/orders")
public Result<IPage<OrderResponseDTO>> getUserOrders(@CurrentUser Long userId,
                                                  @RequestParam(defaultValue = "1") Integer page,
                                                  @RequestParam(defaultValue = "10") Integer size) {
    IPage<OrderResponseDTO> orderPage = orderService.getUserOrders(userId, page, size);
    return Result.success(orderPage);
}
```

### 实现原理

该注解通过 `CurrentUserMethodArgumentResolver` 类解析，该解析器使用 Sa-Token 的 `StpUtil.getLoginIdAsLong()` 方法获取当前登录用户的ID。

### 优势

1. **简化代码**：无需在每个控制器方法中手动获取用户ID
2. **统一处理**：集中处理用户身份识别逻辑
3. **可读性**：使代码更加清晰，明确标识参数来源
4. **可维护性**：认证逻辑变更时，只需修改解析器实现

## @RequiresPermission

`@RequiresPermission` 注解用于标记需要特定权限才能访问的方法。

### 使用方式

```java
@RequiresPermission("post:delete")
@DeleteMapping("/{id}")
public Result<?> deletePost(@PathVariable Long id) {
    // 删除帖子的逻辑
    return Result.success();
}
```

此注解通过 AOP 实现，在方法执行前检查当前用户是否具有指定权限。 