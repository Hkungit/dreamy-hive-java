# 角色权限问题修复文档

## 问题描述

用户登录后访问管理员接口时返回：
```json
{"code":403,"success":false,"message":"无此角色：admin"}
```

但用户实际角色是 `super_admin`。

## 问题分析

1. **角色配置不匹配**：
   - 数据库中用户角色为 `super_admin`
   - SaToken拦截器只检查 `admin` 角色
   - 需要允许 `super_admin` 角色也能访问管理员接口

2. **SaToken配置缺失**：
   - 缺少 `StpInterface` 接口实现
   - SaToken无法获取用户的角色和权限信息

## 已完成的修复

### 1. 修复SaToken拦截器配置

**文件：** `src/main/java/com/dreamy/hive/config/SaTokenConfig.java`

**修复内容：**
```java
// 修复前
SaRouter.match("/v1/admin/**", r -> StpUtil.checkRole("admin"));

// 修复后
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
```

### 2. 创建StpInterface接口实现

**文件：** `src/main/java/com/dreamy/hive/config/StpInterfaceImpl.java`

**功能：**
- 实现 `getPermissionList()` 方法获取用户权限
- 实现 `getRoleList()` 方法获取用户角色
- 为SaToken提供用户权限和角色信息

### 3. 修复API路径配置

**问题：** 路径配置不匹配导致SaToken上下文异常

**修复：**
- 移除控制器路径中的 `/api` 前缀
- 修复前端API请求路径
- 统一路径配置

## 角色权限体系

### 系统角色

| 角色编码 | 角色名称 | 描述 |
|---------|---------|------|
| super_admin | 超级管理员 | 拥有所有权限 |
| admin | 管理员 | 拥有大部分管理权限 |
| user | 普通用户 | 拥有基本功能权限 |

### 权限分类

- **用户管理**：user:view, user:create, user:edit, user:delete
- **角色管理**：role:view, role:create, role:edit, role:delete, role:assign
- **权限管理**：permission:view, permission:create, permission:edit, permission:delete
- **内容管理**：content:view, content:create, content:edit, content:delete, content:audit
- **系统管理**：system:setting, system:monitor, system:log

## API接口

### 用户角色权限接口

- **获取用户角色**：`GET /api/v1/user/roles`
- **获取用户权限**：`GET /api/v1/user/permissions`

### 管理员接口权限控制

- **路径匹配**：`/v1/admin/**`
- **允许角色**：`super_admin` 或 `admin`

## 测试验证

### 1. 用户角色获取测试

```bash
curl -H "satoken: YOUR_TOKEN" http://localhost:8080/api/v1/user/roles
```

**期望结果：**
```json
{
  "success": true,
  "code": 200,
  "message": "操作成功",
  "data": ["super_admin"]
}
```

### 2. 管理员接口访问测试

```bash
curl -H "satoken: YOUR_TOKEN" http://localhost:8080/api/v1/admin/categories/tree
```

**期望结果：** 正常返回分类数据，不再返回403错误

## 当前状态

✅ **已完成：**
- SaToken拦截器配置修复
- StpInterface接口实现
- API路径配置修复
- 用户角色权限接口正常工作

⚠️ **待验证：**
- StpInterface是否被正确调用
- 管理员接口权限验证是否正常

## 调试信息

在 `StpInterfaceImpl` 中添加了调试日志：
- 用户角色获取日志
- 用户权限获取日志
- 异常信息输出

## 下一步

1. 重启服务并查看调试日志
2. 验证StpInterface是否被正确调用
3. 测试管理员接口访问权限
4. 如果仍有问题，检查SaToken配置和数据库数据

## 相关文件

### 后端文件
- `src/main/java/com/dreamy/hive/config/SaTokenConfig.java`
- `src/main/java/com/dreamy/hive/config/StpInterfaceImpl.java`
- `src/main/java/com/dreamy/hive/controller/v1/user/UserController.java`
- `src/main/java/com/dreamy/hive/service/impl/UserServiceImpl.java`
- `src/main/java/com/dreamy/hive/service/impl/RoleServiceImpl.java`
- `src/main/java/com/dreamy/hive/service/impl/PermissionServiceImpl.java`

### 前端文件
- `frontend/admin/src/api/user.ts`
- `frontend/admin/src/store/modules/user.ts`

### 数据库文件
- `src/main/resources/db/schema-rbac.sql`
- `src/main/resources/db/data-rbac.sql` 