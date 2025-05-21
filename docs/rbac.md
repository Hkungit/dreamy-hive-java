# RBAC权限模型实现文档

## 1. 概述

基于角色的访问控制（Role-Based Access Control, RBAC）是一种广泛应用的权限控制机制，本项目通过RBAC实现了灵活的权限管理系统。

## 2. 数据库设计

### 2.1 角色表 (role)

存储系统中的角色信息。

| 字段名 | 类型 | 说明 |
|-------|-----|------|
| id | BIGINT | 主键ID |
| name | VARCHAR(50) | 角色名称 |
| code | VARCHAR(50) | 角色编码，唯一 |
| description | VARCHAR(255) | 角色描述 |
| status | TINYINT | 状态：0-禁用，1-启用 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |
| create_by | BIGINT | 创建者ID |
| update_by | BIGINT | 更新者ID |
| deleted | TINYINT | 逻辑删除：0-未删除，1-已删除 |

### 2.2 权限表 (permission)

存储系统中的权限信息。

| 字段名 | 类型 | 说明 |
|-------|-----|------|
| id | BIGINT | 主键ID |
| name | VARCHAR(50) | 权限名称 |
| code | VARCHAR(50) | 权限编码，唯一 |
| description | VARCHAR(255) | 权限描述 |
| status | TINYINT | 状态：0-禁用，1-启用 |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |
| create_by | BIGINT | 创建者ID |
| update_by | BIGINT | 更新者ID |
| deleted | TINYINT | 逻辑删除：0-未删除，1-已删除 |

### 2.3 用户角色关联表 (user_role)

存储用户与角色的多对多关系。

| 字段名 | 类型 | 说明 |
|-------|-----|------|
| id | BIGINT | 主键ID |
| user_id | BIGINT | 用户ID |
| role_id | BIGINT | 角色ID |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |
| create_by | BIGINT | 创建者ID |
| update_by | BIGINT | 更新者ID |
| deleted | TINYINT | 逻辑删除：0-未删除，1-已删除 |

### 2.4 角色权限关联表 (role_permission)

存储角色与权限的多对多关系。

| 字段名 | 类型 | 说明 |
|-------|-----|------|
| id | BIGINT | 主键ID |
| role_id | BIGINT | 角色ID |
| permission_id | BIGINT | 权限ID |
| create_time | DATETIME | 创建时间 |
| update_time | DATETIME | 更新时间 |
| create_by | BIGINT | 创建者ID |
| update_by | BIGINT | 更新者ID |
| deleted | TINYINT | 逻辑删除：0-未删除，1-已删除 |

## 3. 权限控制方式

本系统实现了两种权限控制方式：

### 3.1 基于角色的路由权限控制

通过Sa-Token框架实现的路由级别的权限控制，主要针对后台管理API进行控制。

```java
// SaTokenConfig.java
@Override
public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new SaInterceptor(handler -> {
        // 指定路由拦截
        SaRouter.match("/api/admin/**", r -> StpUtil.checkRole("admin"));
    })).addPathPatterns("/**");
}
```

### 3.2 基于注解的细粒度权限控制

通过自定义注解和AOP实现方法级别的细粒度权限控制。

```java
// 使用示例
@RequiresPermission("user:view")
public Result<?> getUserInfo() {
    // 业务逻辑
}

// 多个权限，满足其中一个即可
@RequiresPermission("user:edit,user:create")
public Result<?> updateUserInfo() {
    // 业务逻辑
}

// 多个权限，需要同时满足
@RequiresPermission(value = "user:edit,role:edit", logical = true)
public Result<?> complexOperation() {
    // 业务逻辑
}
```

## 4. API接口说明

### 4.1 角色管理接口

- **角色列表**：`GET /api/admin/role/list`
- **角色详情**：`GET /api/admin/role/{roleId}`
- **创建角色**：`POST /api/admin/role`
- **更新角色**：`PUT /api/admin/role/{roleId}`
- **删除角色**：`DELETE /api/admin/role/{roleId}`
- **为角色分配权限**：`POST /api/admin/role/{roleId}/permissions`
- **获取角色的权限列表**：`GET /api/admin/role/{roleId}/permissions`
- **获取用户的角色列表**：`GET /api/admin/role/user/{userId}`
- **为用户分配角色**：`POST /api/admin/role/user/{userId}`

### 4.2 权限管理接口

- **权限列表**：`GET /api/admin/permission/list`
- **权限详情**：`GET /api/admin/permission/{permissionId}`
- **创建权限**：`POST /api/admin/permission`
- **更新权限**：`PUT /api/admin/permission/{permissionId}`
- **删除权限**：`DELETE /api/admin/permission/{permissionId}`
- **根据用户ID获取权限列表**：`GET /api/admin/permission/user/{userId}`
- **根据角色ID获取权限列表**：`GET /api/admin/permission/role/{roleId}`

### 4.3 用户权限相关接口

- **获取当前用户的权限编码列表**：`GET /api/user/permissions`
- **获取当前用户的角色编码列表**：`GET /api/user/roles`

## 5. 初始化数据

系统内置了以下角色和权限：

### 5.1 默认角色

- 超级管理员 (super_admin)：拥有所有权限
- 管理员 (admin)：拥有大部分管理权限
- 普通用户 (user)：拥有基本功能权限

### 5.2 默认权限分类

- 用户管理：user:view, user:create, user:edit, user:delete
- 角色管理：role:view, role:create, role:edit, role:delete, role:assign
- 权限管理：permission:view, permission:create, permission:edit, permission:delete
- 内容管理：content:view, content:create, content:edit, content:delete, content:audit
- 系统管理：system:setting, system:monitor, system:log

## 6. 使用示例

### 6.1 后台管理页面中的权限控制

1. 通过角色控制菜单显示
2. 通过权限控制按钮显示
3. 接口调用时进行权限校验

### 6.2 普通用户使用过程中的权限控制

1. 只显示有权限访问的功能
2. 接口调用时进行权限校验
3. 内容管理中的编辑/删除权限控制

## 7. 后续扩展

1. 动态权限配置：支持在后台动态添加/修改权限及其关联关系
2. 数据权限：基于用户角色控制可访问的数据范围
3. 权限继承：支持角色间的权限继承关系 