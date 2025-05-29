# SaToken上下文异常修复文档

## 问题描述

在请求 `http://localhost:8080/api/v1/admin/categories/tree` 时，后端抛出异常：

```
cn.dev33.satoken.exception.SaTokenContextException: SaTokenContext 上下文尚未初始化
```

## 问题原因分析

这个问题的根本原因是**路径配置不匹配**：

1. **应用配置**：在 `application.yml` 中设置了 `context-path: /api`
2. **控制器路径**：使用了 `@RequestMapping("/api/v1/admin/categories")`
3. **实际访问路径**：由于context-path的存在，实际路径变成了 `/api/api/v1/admin/categories`
4. **SaToken拦截器配置**：配置为拦截 `/api/v1/admin/**`，但实际应该拦截 `/v1/admin/**`

## 修复方案

### 1. 修复后端控制器路径

移除所有控制器 `@RequestMapping` 注解中的 `/api` 前缀，因为 `context-path` 已经提供了这个前缀。

**修复前：**
```java
@RequestMapping("/api/v1/admin/categories")
```

**修复后：**
```java
@RequestMapping("/v1/admin/categories")
```

**涉及的控制器文件：**
- `AdminCategoryController.java`
- `SkuController.java`
- `ProductImageController.java`
- `AdminOrderController.java`
- `AttributeController.java`
- `AdminPermissionController.java`
- `AdminRoleController.java`
- `CategoryController.java`（用户端）

### 2. 修复SaToken拦截器配置

更新 `SaTokenConfig.java` 中的路径匹配规则：

**修复前：**
```java
SaRouter.match("/api/v1/admin/**", r -> StpUtil.checkRole("admin"));
```

**修复后：**
```java
SaRouter.match("/v1/admin/**", r -> StpUtil.checkRole("admin"));
```

### 3. 修复前端API配置

**修复前端baseURL：**
```typescript
// 修复前
baseURL: 'http://localhost:8080/api'

// 修复后
baseURL: 'http://localhost:8080'
```

**修复API路径：**
```typescript
// 修复前
url: '/v1/admin/categories/tree'

// 修复后
url: '/api/v1/admin/categories/tree'
```

## 路径映射关系

| 组件 | 配置项 | 修复前 | 修复后 |
|------|--------|--------|--------|
| 应用配置 | context-path | `/api` | `/api` (不变) |
| 后端控制器 | @RequestMapping | `/api/v1/admin/categories` | `/v1/admin/categories` |
| SaToken拦截器 | SaRouter.match | `/api/v1/admin/**` | `/v1/admin/**` |
| 前端baseURL | axios配置 | `http://localhost:8080/api` | `http://localhost:8080` |
| 前端API路径 | 请求URL | `/v1/admin/categories/tree` | `/api/v1/admin/categories/tree` |

## 最终访问路径

**前端请求：** `http://localhost:8080/api/v1/admin/categories/tree`

**路径解析过程：**
1. 前端发送请求到：`http://localhost:8080/api/v1/admin/categories/tree`
2. Spring Boot接收到：`/api/v1/admin/categories/tree`
3. 去除context-path `/api` 后：`/v1/admin/categories/tree`
4. 匹配到控制器：`@RequestMapping("/v1/admin/categories")` + `@GetMapping("/tree")`
5. SaToken拦截器匹配：`/v1/admin/**` ✅

## 验证步骤

1. 启动后端服务：`mvn spring-boot:run`
2. 启动前端服务：`npm run dev`
3. 访问前端页面，测试分类管理功能
4. 检查浏览器网络请求是否正常
5. 检查后端日志是否还有SaToken异常

## 注意事项

1. **context-path的作用**：为所有请求添加统一前缀，避免与其他应用冲突
2. **路径一致性**：确保前端请求路径、后端控制器路径、拦截器路径保持一致
3. **SaToken路径匹配**：拦截器路径应该是去除context-path后的实际路径

## 相关文件清单

### 后端文件
- `src/main/resources/application.yml`
- `src/main/java/com/dreamy/hive/config/SaTokenConfig.java`
- `src/main/java/com/dreamy/hive/controller/v1/admin/*.java`
- `src/main/java/com/dreamy/hive/controller/v1/user/*.java`

### 前端文件
- `frontend/admin/src/api/request.ts`
- `frontend/admin/src/api/category.ts`
- `frontend/admin/src/api/user.ts`
- `frontend/admin/src/store/modules/user.ts`

## 总结

这个问题的核心是理解Spring Boot的context-path机制和SaToken的路径匹配规则。通过统一路径配置，确保了：

1. ✅ SaToken能够正确初始化上下文
2. ✅ 路径拦截规则正确匹配
3. ✅ 前后端API调用路径一致
4. ✅ 权限验证正常工作

修复后，系统应该能够正常处理管理员权限验证和API请求。 