# dreamy hive开发文档

---

# 📄 Dreamy Hive 完整开发文档

---

## 一、项目概述

### 项目名称：

**Dreamy Hive**

### 项目类型：

Java 单体架构 Web 应用

### 项目定位：

一个融合了**商品商城**与**用户社区**的轻量级系统，类似“得物”的简化版本，仅用于学习和练手目的，不考虑实际部署上线。

### 项目目标：

- 实现基础的商品展示、下单购买功能
- 提供用户发布内容、互动交流的社区功能
- 架构简单清晰，便于理解和维护
- 支持管理员操作：商品管理、分类管理、订单管理、帖子管理等
- 所有用户共用一张表，管理员通过 `role` 字段区分（无需独立注册）

---

## 二、功能模块划分

### 模块一：用户端（User）

| 功能 | 描述 |
| --- | --- |
| 商品浏览 | 展示商品列表，支持按分类、关键词搜索 |
| 商品详情 | 查看商品主图、轮播图、描述、价格、库存等信息 |
| SKU 支持 | 同一商品支持多个规格（如颜色、尺寸） |
| 购物车 | 用户可添加商品到购物车，修改数量或删除 |
| 下单支付 | 简化版下单流程，模拟付款成功即可生成订单 |
| 订单中心 | 展示用户的订单历史（状态如待发货、已发货、已完成） |
| 社区模块 | 浏览/发布帖子、评论、点赞 |

---

### 模块二：管理端（Admin）

> 管理员是普通用户的一种角色形式，所有用户共用一张表，通过字段区分是否为管理员。
> 

| 功能 | 描述 |
| --- | --- |
| 登录模块 | 使用已有用户登录，判断 role 决定权限 |
| 商品管理 | 新增/编辑/删除商品，上传多图，管理 SKU |
| 分类管理 | 添加/修改/删除商品分类 |
| SKU 管理 | 为商品添加规格（如颜色、尺寸） |
| 图片管理 | 查看并上传商品相关图片 |
| 订单管理 | 查看订单详情、发货、取消等操作 |
| 帖子管理 | 审核/删除用户发布的帖子 |
| 用户管理（可选） | 查看用户列表、修改角色（进阶） |

---

## 三、技术选型建议

### 后端（Java）

- **Spring Boot 3.x**
- **MyBatis / MyBatis Plus**
- **MySQL 8.x**
- **Lombok**
- **PageHelper / 分页插件**
- **前后端分离使用 Vue.js + Element Plus**

### 前端（Vue）

- **Vue 3 + Composition API**
- **Element Plus UI 组件库**
- **Axios + Vue Router**
- **Vuex（可选）**

### 工具

- **Maven / Gradle**
- **IDEA / VS Code**
- **Postman / Apifox**

---

## 四、数据库设计（SQL 脚本）

```sql
-- 用户表
CREATE TABLE `user` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `username` varchar(50) NOT NULL COMMENT '用户名',
    `password` varchar(100) NOT NULL COMMENT '密码',
    `nickname` varchar(50) NOT NULL COMMENT '昵称',
    `avatar` varchar(255) DEFAULT NULL COMMENT '头像URL',
    `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
    `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
    `gender` tinyint DEFAULT '0' COMMENT '性别（0-未知，1-男，2-女）',
    `birthday` date DEFAULT NULL COMMENT '生日',
    `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-禁用，1-正常）',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` bigint DEFAULT NULL COMMENT '创建人',
    `update_by` bigint DEFAULT NULL COMMENT '更新人',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_username` (`username`),
    KEY `idx_phone` (`phone`),
    KEY `idx_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';

-- 商品分类表
CREATE TABLE `category` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `parent_id` bigint DEFAULT '0' COMMENT '父分类ID',
    `name` varchar(50) NOT NULL COMMENT '分类名称',
    `level` int DEFAULT '1' COMMENT '分类级别：1->一级；2->二级',
    `icon` varchar(255) DEFAULT NULL COMMENT '图标',
    `description` text COMMENT '描述',
    `sort` int DEFAULT '0' COMMENT '排序',
    `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-禁用，1-启用）',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品分类表';

-- 商品属性表
CREATE TABLE `attribute` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `category_id` bigint NOT NULL COMMENT '分类ID',
    `name` varchar(50) NOT NULL COMMENT '属性名称',
    `input_type` tinyint DEFAULT '1' COMMENT '输入类型：1->输入框；2->单选；3->多选',
    `select_list` text COMMENT '可选值列表，以逗号分隔',
    `sort` int DEFAULT '0' COMMENT '排序',
    `is_filter` tinyint DEFAULT '0' COMMENT '是否用于筛选：0->否；1->是',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    KEY `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品属性表';

-- 商品SPU表
CREATE TABLE `spu` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `name` varchar(100) NOT NULL COMMENT '商品名称',
    `sub_title` varchar(200) DEFAULT NULL COMMENT '副标题',
    `category_id` bigint NOT NULL COMMENT '分类ID',
    `description` text COMMENT '商品描述',
    `detail_html` text COMMENT '商品详情HTML',
    `status` tinyint NOT NULL DEFAULT '0' COMMENT '状态（0-下架，1-上架）',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` bigint DEFAULT NULL COMMENT '创建人',
    `update_by` bigint DEFAULT NULL COMMENT '更新人',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    KEY `idx_category_id` (`category_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品SPU表';

-- 商品SKU表
CREATE TABLE `sku` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `spu_id` bigint NOT NULL COMMENT 'SPU ID',
    `sku_code` varchar(64) NOT NULL COMMENT 'SKU编码',
    `name` varchar(100) NOT NULL COMMENT 'SKU名称',
    `specs` json DEFAULT NULL COMMENT '规格属性，JSON格式：{"属性ID":"属性值"}',
    `price` decimal(10,2) NOT NULL COMMENT '销售价格',
    `original_price` decimal(10,2) DEFAULT NULL COMMENT '市场价',
    `stock` int NOT NULL DEFAULT '0' COMMENT '库存',
    `low_stock` int DEFAULT '0' COMMENT '预警库存',
    `pic` varchar(255) DEFAULT NULL COMMENT '展示图片',
    `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-禁用，1-启用）',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_sku_code` (`sku_code`),
    KEY `idx_spu_id` (`spu_id`),
    KEY `idx_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品SKU表';

-- 商品图片表
CREATE TABLE `product_image` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `spu_id` bigint NOT NULL COMMENT 'SPU ID',
    `sku_id` bigint DEFAULT NULL COMMENT 'SKU ID，为空表示SPU图片',
    `url` varchar(255) NOT NULL COMMENT '图片URL',
    `sort` int DEFAULT '0' COMMENT '排序',
    `type` tinyint DEFAULT '0' COMMENT '图片类型：0-普通图片，1-主图，2-详情图',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    KEY `idx_spu_id` (`spu_id`),
    KEY `idx_sku_id` (`sku_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='商品图片表';

-- 地址表
CREATE TABLE `address` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` bigint NOT NULL COMMENT '所属用户ID',
    `receiver` varchar(50) NOT NULL COMMENT '收货人',
    `phone` varchar(20) NOT NULL COMMENT '收货人电话',
    `province` varchar(50) NOT NULL COMMENT '省',
    `city` varchar(50) NOT NULL COMMENT '市',
    `district` varchar(50) NOT NULL COMMENT '区/县',
    `detail` varchar(255) NOT NULL COMMENT '详细地址',
    `is_default` tinyint DEFAULT '0' COMMENT '是否默认地址（0-否，1-是）',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户地址表';

-- 订单表
CREATE TABLE `order` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_no` varchar(50) NOT NULL COMMENT '订单编号',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `address_id` bigint NOT NULL COMMENT '收货地址ID',
    `total_amount` decimal(10,2) NOT NULL COMMENT '订单总金额',
    `status` tinyint NOT NULL DEFAULT '0' COMMENT '订单状态（0-待支付，1-已支付，2-已发货，3-已完成，4-已取消）',
    `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
    `shipping_time` datetime DEFAULT NULL COMMENT '发货时间',
    `completion_time` datetime DEFAULT NULL COMMENT '完成时间',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` bigint DEFAULT NULL COMMENT '创建人',
    `update_by` bigint DEFAULT NULL COMMENT '更新人',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_order_no` (`order_no`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_address_id` (`address_id`),
    CONSTRAINT `fk_order_address` FOREIGN KEY (`address_id`) REFERENCES `address`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单表';

-- 订单详情表
CREATE TABLE `order_detail` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `order_id` bigint NOT NULL COMMENT '订单ID',
    `spu_id` bigint NOT NULL COMMENT 'SPU ID',
    `sku_id` bigint NOT NULL COMMENT 'SKU ID',
    `sku_code` varchar(64) NOT NULL COMMENT 'SKU编码',
    `product_name` varchar(100) NOT NULL COMMENT '商品名称',
    `specs` json DEFAULT NULL COMMENT '规格属性，JSON格式：{"属性名":"属性值"}',
    `price` decimal(10,2) NOT NULL COMMENT '商品价格',
    `original_price` decimal(10,2) DEFAULT NULL COMMENT '商品原价',
    `quantity` int NOT NULL COMMENT '购买数量',
    `subtotal` decimal(10,2) NOT NULL COMMENT '小计金额',
    `pic` varchar(255) DEFAULT NULL COMMENT '商品图片',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    KEY `idx_order_id` (`order_id`),
    KEY `idx_sku_id` (`sku_id`),
    KEY `idx_spu_id` (`spu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='订单详情表';

-- 帖子表
CREATE TABLE `post` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `title` varchar(100) NOT NULL COMMENT '标题',
    `content` text NOT NULL COMMENT '内容',
    `images` text COMMENT '图片URLs（JSON数组）',
    `view_count` int NOT NULL DEFAULT '0' COMMENT '浏览量',
    `like_count` int NOT NULL DEFAULT '0' COMMENT '点赞数',
    `comment_count` int NOT NULL DEFAULT '0' COMMENT '评论数',
    `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-禁用，1-正常）',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` bigint DEFAULT NULL COMMENT '创建人',
    `update_by` bigint DEFAULT NULL COMMENT '更新人',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='帖子表';

-- 评论表
CREATE TABLE `comment` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `post_id` bigint NOT NULL COMMENT '帖子ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `content` text NOT NULL COMMENT '评论内容',
    `parent_id` bigint DEFAULT NULL COMMENT '父评论ID',
    `reply_user_id` bigint DEFAULT NULL COMMENT '回复用户ID',
    `status` tinyint NOT NULL DEFAULT '1' COMMENT '状态（0-禁用，1-正常）',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` bigint DEFAULT NULL COMMENT '创建人',
    `update_by` bigint DEFAULT NULL COMMENT '更新人',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    KEY `idx_post_id` (`post_id`),
    KEY `idx_user_id` (`user_id`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='评论表';

-- 点赞表
CREATE TABLE `like` (
    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `post_id` bigint NOT NULL COMMENT '帖子ID',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `create_by` bigint DEFAULT NULL COMMENT '创建人',
    `update_by` bigint DEFAULT NULL COMMENT '更新人',
    `deleted` tinyint NOT NULL DEFAULT '0' COMMENT '是否删除（0-未删除，1-已删除）',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_user_post` (`user_id`, `post_id`),
    KEY `idx_post_id` (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='点赞表';

```

---

## 五、接口设计（RESTful API）

### 用户端 API 示例

| 方法 | 路径 | 描述 |
| --- | --- | --- |
| GET | /api/products | 获取商品列表 |
| GET | /api/products/{id} | 获取商品详情（含 SKU 列表） |
| POST | /api/cart/add | 添加商品到购物车 |
| GET | /api/cart | 获取当前用户购物车 |
| POST | /api/order/create | 创建订单 |
| GET | /api/order/list | 获取订单列表 |
| GET | /api/posts | 获取帖子列表 |
| GET | /api/posts/{id} | 获取帖子详情 |
| POST | /api/posts | 发布新帖子 |
| POST | /api/comments | 发表评论 |
| POST | /api/likes | 点赞帖子 |

---

### 用户端 API 设计

#### 1. 认证相关
| 方法 | 路径 | 描述 |
| --- | --- | --- |
| POST | /api/auth/register | 用户注册 |
| POST | /api/auth/login | 用户登录 |
| POST | /api/auth/logout | 用户登出 |
| GET | /api/auth/info | 获取当前用户信息 |
| PUT | /api/auth/info | 更新用户信息 |
| PUT | /api/auth/password | 修改密码 |

#### 2. 商品相关
| 方法 | 路径 | 描述 |
| --- | --- | --- |
| GET | /api/categories | 获取商品分类列表 |
| GET | /api/categories/{id} | 获取分类详情 |
| GET | /api/spus | 获取SPU列表 |
| GET | /api/spus/{id} | 获取SPU详情 |
| GET | /api/skus | 获取SKU列表 |
| GET | /api/skus/{id} | 获取SKU详情 |
| GET | /api/spus/{spuId}/skus | 获取指定SPU的所有SKU |
| GET | /api/spus/search | 搜索商品 |

#### 3. 购物车相关
| 方法 | 路径 | 描述 |
| --- | --- | --- |
| GET | /api/cart | 获取购物车列表 |
| POST | /api/cart | 添加商品到购物车 |
| PUT | /api/cart/{id} | 修改购物车商品数量 |
| DELETE | /api/cart/{id} | 从购物车移除商品 |
| POST | /api/cart/checkout | 结算购物车 |

#### 4. 订单相关
| 方法 | 路径 | 描述 |
| --- | --- | --- |
| POST | /api/orders | 创建订单 |
| GET | /api/orders | 获取订单列表 |
| GET | /api/orders/{orderNo} | 获取订单详情 |
| PUT | /api/orders/{orderNo}/cancel | 取消订单 |
| POST | /api/orders/{orderNo}/pay | 支付订单 |
| GET | /api/orders/{orderNo}/status | 查询订单状态 |

#### 5. 社区相关
| 方法 | 路径 | 描述 |
| --- | --- | --- |
| GET | /api/posts | 获取帖子列表 |
| POST | /api/posts | 发布帖子 |
| GET | /api/posts/{id} | 获取帖子详情 |
| PUT | /api/posts/{id} | 更新帖子 |
| DELETE | /api/posts/{id} | 删除帖子 |
| POST | /api/posts/{id}/like | 点赞/取消点赞 |
| GET | /api/posts/{id}/comments | 获取评论列表 |
| POST | /api/posts/{id}/comments | 发表评论 |
| DELETE | /api/comments/{id} | 删除评论 |

### 管理端 API 设计

#### 1. 用户管理
| 方法 | 路径 | 描述 |
| --- | --- | --- |
| GET | /api/admin/users | 获取用户列表 |
| PUT | /api/admin/users/{id}/status | 修改用户状态 |
| DELETE | /api/admin/users/{id} | 删除用户 |

#### 2. 商品管理
| 方法 | 路径 | 描述 |
| --- | --- | --- |
| GET | /api/admin/categories | 获取分类列表 |
| POST | /api/admin/categories | 新增分类 |
| PUT | /api/admin/categories/{id} | 修改分类 |
| DELETE | /api/admin/categories/{id} | 删除分类 |
| GET | /api/admin/attributes | 获取属性列表 |
| POST | /api/admin/attributes | 新增属性 |
| PUT | /api/admin/attributes/{id} | 修改属性 |
| DELETE | /api/admin/attributes/{id} | 删除属性 |
| GET | /api/admin/spus | 获取SPU列表 |
| POST | /api/admin/spus | 新增SPU |
| PUT | /api/admin/spus/{id} | 修改SPU |
| DELETE | /api/admin/spus/{id} | 删除SPU |
| PUT | /api/admin/spus/{id}/status | 上下架SPU |
| GET | /api/admin/skus | 获取SKU列表 |
| POST | /api/admin/skus | 新增SKU |
| PUT | /api/admin/skus/{id} | 修改SKU |
| DELETE | /api/admin/skus/{id} | 删除SKU |
| PUT | /api/admin/skus/{id}/status | 上下架SKU |
| GET | /api/admin/spus/{spuId}/skus | 获取指定SPU的所有SKU |
| POST | /api/admin/product-images | 上传商品图片 |
| DELETE | /api/admin/product-images/{id} | 删除商品图片 |

#### 3. 订单管理
| 方法 | 路径 | 描述 |
| --- | --- | --- |
| GET | /api/admin/orders | 获取订单列表 |
| GET | /api/admin/orders/{orderNo} | 获取订单详情 |
| PUT | /api/admin/orders/{orderNo}/status | 更新订单状态 |
| PUT | /api/admin/orders/{orderNo}/ship | 订单发货 |

#### 4. 社区管理
| 方法 | 路径 | 描述 |
| --- | --- | --- |
| GET | /api/admin/posts | 获取帖子列表 |
| PUT | /api/admin/posts/{id}/status | 修改帖子状态 |
| DELETE | /api/admin/posts/{id} | 删除帖子 |
| GET | /api/admin/comments | 获取评论列表 |
| PUT | /api/admin/comments/{id}/status | 修改评论状态 |
| DELETE | /api/admin/comments/{id} | 删除评论 |

#### 5. 数据统计
| 方法 | 路径 | 描述 |
| --- | --- | --- |
| GET | /api/admin/statistics/overview | 获取概览数据 |
| GET | /api/admin/statistics/sales | 获取销售统计 |
| GET | /api/admin/statistics/user-growth | 获取用户增长数据 |
| GET | /api/admin/statistics/hot-products | 获取热销商品数据 |

---

## 六、前后端结构建议

### 后端结构（Spring Boot）

```
dreamy-hive/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── com/dreamy/hive/
│   │   │   │   ├── controller/
│   │   │   │   │   ├── user/           # 用户端控制器
│   │   │   │   │   └── admin/          # 管理端控制器
│   │   │   │   ├── service/
│   │   │   │   │   ├── impl/          # 服务实现类
│   │   │   │   │   └── interfaces/    # 服务接口
│   │   │   │   ├── mapper/          # MyBatis映射器
│   │   │   │   ├── entity/          # 数据库实体类
│   │   │   │   │   ├── User.java
│   │   │   │   │   ├── Category.java
│   │   │   │   │   ├── Attribute.java
│   │   │   │   │   ├── Spu.java
│   │   │   │   │   ├── Sku.java
│   │   │   │   │   ├── ProductImage.java
│   │   │   │   │   ├── Order.java
│   │   │   │   │   ├── OrderDetail.java
│   │   │   │   │   ├── Post.java
│   │   │   │   │   ├── Comment.java
│   │   │   │   │   └── Like.java
│   │   │   │   ├── dto/             # 数据传输对象
│   │   │   │   │   ├── request/       # 请求对象
│   │   │   │   │   └── response/      # 响应对象
│   │   │   │   ├── vo/              # 视图对象
│   │   │   │   ├── config/          # 配置类
│   │   │   │   ├── common/          # 公共类
│   │   │   │   │   ├── enums/         # 枚举类
│   │   │   │   │   ├── exception/     # 异常类
│   │   │   │   │   └── utils/         # 工具类
│   │   │   │   └── DreamyHiveApplication.java
│   │   └── resources/
│   │       ├── db/              # 数据库脚本
│   │       │   ├── schema.sql     # 表结构
│   │       │   └── data.sql       # 初始数据
│   │       ├── mapper/          # MyBatis映射文件
│   │       └── application.yml  # 应用配置
│   └── test/                # 测试代码
├── doc/                     # 项目文档
└── pom.xml                  # Maven配置

```

---

### 前端结构（Vue）

```
dreamy-hive-web/
├── public/
├── src/
│   ├── views/
│   │   ├── user/         # 用户端页面
│   │   │   ├── mall/
│   │   │   ├── community/
│   │   │   └── order/
│   │   └── admin/        # 管理端页面
│   │       ├── product/
│   │       ├── category/
│   │       ├── order/
│   │       └── post/
│   ├── router/
│   │   ├── index.js      # 路由配置（含 user 和 admin 区分）
│   ├── api/
│   │   ├── user.js       # 用户端请求封装
│   │   └── admin.js      # 管理端请求封装
│   ├── store/             # Vuex（可选）
│   ├── App.vue
│   └── main.js

```

---

## 七、开发流程建议

| 阶段 | 内容 | 时间估算 |
| --- | --- | --- |
| 第一阶段 | 搭建 Spring Boot + MyBatis + MySQL 基础环境 | 1天 |
| 第二阶段 | 商品模块（多图 + SKU + 分类 + 搜索） | 3天 |
| 第三阶段 | 社区模块（发布 + 浏览 + 评论 + 点赞） | 2天 |
| 第四阶段 | 购物车 + 下单流程 | 1天 |
| 第五阶段 | 管理端：商品管理、分类管理、SKU管理 | 2天 |
| 第六阶段 | 管理端：订单管理、帖子管理、登录逻辑 | 2天 |
| 第七阶段 | Vue 页面搭建 + 接口对接 + Bug 修复 | 2天 |

---

## 八、最终效果展示（示例截图描述）

### 用户端：

- **首页**：商品推荐 + 帖子推荐
- **商城页**：商品分类导航 + 商品卡片展示（带缩略图）
- **商品详情页**：轮播图 + SKU 规格选择 + 加入购物车按钮
- **社区页**：帖子瀑布流展示
- **帖子详情页**：图文内容 + 评论框 + 点赞按钮
- **购物车页**：商品列表 + SKU 显示 + 总价 + 模拟付款按钮
- **订单中心**：订单状态追踪（含商品名、规格、数量）

### 管理端：

- **登录页** → 商品管理页（CRUD）
- **分类管理页**（添加、编辑、删除）
- **SKU 管理页**（绑定商品 ID）
- **订单管理页**（查看订单详情、修改状态）
- **帖子管理页**（查看、删除）

---