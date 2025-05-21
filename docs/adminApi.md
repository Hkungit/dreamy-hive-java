# Admin API Documentation

This document provides a detailed specification of the Admin APIs for the Dreamy Hive system.

## Common Data Structures

### Standard API Response Wrapper (`Result<T>`)

All API responses are wrapped in a standard structure:

```json
{
  "code": "integer", 
  "message": "string", 
  "data": "T" 
}
```

-   `code`: `0` for success, other values indicate errors.
-   `message`: "success" or a descriptive error message.
-   `data`: The actual data payload. The type `T` varies per endpoint.

### Pagination Object (`Page<T>`)

For paginated lists, the `data` field will contain a pagination object:

```json
{
  "records": [/* Array of T objects */],
  "total": "long",        // Total number of records
  "size": "long",         // Number of records per page
  "current": "long",      // Current page number
  "pages": "long"         // Total number of pages
}
```

## API Endpoints

---

## 管理端-商品分类 (AdminCategoryController)

**Base Path:** `/admin/categories`

### 1. 获取分类树形结构

-   **Endpoint:** `GET /tree`
-   **Summary:** 获取分类树形结构
-   **Description:** 获取所有分类的树形结构，通常用于分类选择器。
-   **Request:**
    -   Path Parameters: None
    -   Query Parameters: None
    -   Request Body: None
-   **Response (200 OK):** `Result<List<Category>>`
    ```json
    {
      "code": 0,
      "message": "success",
      "data": [
        {
          "id": 1,
          "name": "Electronics",
          "parentId": 0,
          "level": 1,
          "productCount": 150,
          "productUnit": "件",
          "navStatus": 1, // 0->不显示, 1->显示
          "showStatus": 1, // 0->不显示, 1->显示
          "sort": 0,
          "icon": "url-to-icon.png",
          "keywords": "electronics, gadgets",
          "description": "All kinds of electronic products",
          "children": [ /* nested Category objects */ ]
        }
      ]
    }
    ```

### 2. 分页查询分类列表

-   **Endpoint:** `GET /`
-   **Summary:** 分页查询分类列表
-   **Description:** 分页查询分类列表，支持按名称搜索。
-   **Request:**
    -   Path Parameters: None
    -   Query Parameters:
        -   `pageNum` (integer, optional, default: 1): 页码
        -   `pageSize` (integer, optional, default: 10): 每页数量
        -   `name` (string, optional): 分类名称，用于模糊搜索
    -   Request Body: None
-   **Response (200 OK):** `Result<Page<Category>>`
    ```json
    {
      "code": 0,
      "message": "success",
      "data": {
        "records": [/* Array of Category objects (see structure above) */],
        "total": 50,
        "size": 10,
        "current": 1,
        "pages": 5
      }
    }
    ```

### 3. 添加分类

-   **Endpoint:** `POST /`
-   **Summary:** 添加分类
-   **Description:** 添加新的商品分类。
-   **Request:**
    -   Path Parameters: None
    -   Query Parameters: None
    -   Request Body: `application/json` (`Category` object)
        ```json
        {
          "name": "New Category",
          "parentId": 1, // ID of parent category, or 0 for root
          "level": 2,
          "productUnit": "个",
          "navStatus": 1,
          "showStatus": 1,
          "sort": 10,
          "icon": "icon.png",
          "keywords": "new, category",
          "description": "Description for new category"
        }
        ```
-   **Response (200 OK):** `Result<?>` (Typically `data` is `null` or a success indicator)
    ```json
    {
      "code": 0,
      "message": "success",
      "data": null 
    }
    ```

### 4. 更新分类

-   **Endpoint:** `PUT /{id}`
-   **Summary:** 更新分类
-   **Description:** 根据ID更新分类信息。
-   **Request:**
    -   Path Parameters:
        -   `id` (long, required): 分类ID
    -   Query Parameters: None
    -   Request Body: `application/json` (`Category` object, fields to update)
        ```json
        {
          "name": "Updated Category Name",
          /* other fields to update */
        }
        ```
-   **Response (200 OK):** `Result<?>`
    ```json
    {
      "code": 0,
      "message": "success",
      "data": null
    }
    ```

### 5. 删除分类

-   **Endpoint:** `DELETE /{id}`
-   **Summary:** 删除分类
-   **Description:** 根据ID删除分类。注意：可能有子分类或关联商品，需处理相关逻辑。
-   **Request:**
    -   Path Parameters:
        -   `id` (long, required): 分类ID
    -   Query Parameters: None
    -   Request Body: None
-   **Response (200 OK):** `Result<?>`
    ```json
    {
      "code": 0,
      "message": "success",
      "data": null
    }
    ```

### 6. 根据ID获取分类

-   **Endpoint:** `GET /{id}`
-   **Summary:** 根据ID获取分类
-   **Description:** 根据ID获取单个分类的详细信息。
-   **Request:**
    -   Path Parameters:
        -   `id` (long, required): 分类ID
    -   Query Parameters: None
    -   Request Body: None
-   **Response (200 OK):** `Result<Category>`
    ```json
    {
      "code": 0,
      "message": "success",
      "data": { /* Category object structure */ }
    }
    ```

### 7. 更新分类状态

-   **Endpoint:** `PUT /{id}/status/{status}`
-   **Summary:** 更新分类状态
-   **Description:** 根据ID更新分类的显示状态或导航状态 (具体哪个状态由后端业务决定，通常是 `showStatus` 或 `navStatus`)。
-   **Request:**
    -   Path Parameters:
        -   `id` (long, required): 分类ID
        -   `status` (integer, required): 新的状态值 (e.g., 0 or 1)
    -   Query Parameters: None
    -   Request Body: None
-   **Response (200 OK):** `Result<?>`
    ```json
    {
      "code": 0,
      "message": "success",
      "data": null
    }
    ```

---

## 管理端-评论管理 (AdminCommentController)

**Base Path:** `/admin/comments`

*(Detailed documentation for AdminCommentController endpoints would follow a similar structure, including request/response schemas for Comment DTOs. Due to length constraints, this will be summarized.)*

-   `GET /`: 分页查询评论列表 (`Result<Page<Comment>>`)
    -   Query Params: `pageNum`, `pageSize`, `userId` (optional), `productId` (optional), `status` (optional)
-   `DELETE /{id}`: 删除评论 (`Result<?>`)
-   `PUT /{id}/status/{status}`: 更新评论状态 (审核) (`Result<?>`)

---

## 管理端-订单管理 (AdminOrderController)

**Base Path:** `/admin/orders`

*(Detailed documentation for AdminOrderController endpoints.)*

-   `GET /`: 分页查询订单列表 (`Result<Page<Order>>`)
    -   Query Params: `pageNum`, `pageSize`, `orderSn` (optional), `userId` (optional), `status` (optional)
-   `GET /{id}`: 根据ID获取订单详情 (`Result<OrderDetail>`) 
-   `PUT /{id}/status`: 更新订单状态 (`Result<?>`)
    -   Request Body: `{"status": new_status_code, "note": "optional note"}`
-   `POST /{id}/ship`: 订单发货 (`Result<?>`)
    -   Request Body: `{"deliveryCompany": "SF Express", "deliverySn": "1234567890"}`
-   `DELETE /{id}`: 删除订单 (逻辑删除) (`Result<?>`)

---

## 管理端-权限管理 (AdminPermissionController)

**Base Path:** `/admin/permissions`

*(Detailed documentation for AdminPermissionController endpoints.)*

-   `GET /tree`: 获取权限树 (`Result<List<PermissionNode>>`)
-   `GET /`: 分页查询权限列表 (`Result<Page<Permission>>`)
-   `POST /`: 添加权限 (`Result<?>`)
    -   Request Body: `Permission` object (e.g., `{"name": "sys:user:create", "type": 1, "parentId": 0, "uri": "/admin/users/create"}`)
-   `PUT /{id}`: 更新权限 (`Result<?>`)
    -   Request Body: `Permission` object
-   `DELETE /{id}`: 删除权限 (`Result<?>`)
-   `GET /{id}`: 根据ID获取权限 (`Result<Permission>`)

---

## 管理端-文章管理 (AdminPostController)

**Base Path:** `/admin/posts`

*(Detailed documentation for AdminPostController endpoints.)*

-   `GET /`: 分页查询文章列表 (`Result<Page<Post>>`)
-   `POST /`: 添加文章 (`Result<Post>` or `Result<?>`)
    -   Request Body: `Post` object (title, content, categoryId, tags, etc.)
-   `PUT /{id}`: 更新文章 (`Result<Post>` or `Result<?>`)
-   `DELETE /{id}`: 删除文章 (`Result<?>`)
-   `GET /{id}`: 根据ID获取文章 (`Result<Post>`)
-   `PUT /{id}/status/{status}`: 更新文章发布状态 (`Result<?>`)

---

## 管理端-角色管理 (AdminRoleController)

**Base Path:** `/admin/roles`

*(Detailed documentation for AdminRoleController endpoints.)*

-   `GET /`: 分页查询角色列表 (`Result<Page<Role>>`)
-   `POST /`: 添加角色 (`Result<Role>` or `Result<?>`)
    -   Request Body: `{"name": "Editor", "description": "Can edit articles"}`
-   `PUT /{id}`: 更新角色 (`Result<Role>` or `Result<?>`)
-   `DELETE /{id}`: 删除角色 (`Result<?>`)
-   `GET /{id}`: 根据ID获取角色 (`Result<Role>`)
-   `GET /{id}/permissions`: 获取角色拥有的权限ID列表 (`Result<List<Long>>`)
-   `PUT /{id}/permissions`: 为角色分配权限 (`Result<?>`)
    -   Request Body: `{"permissionIds": [1, 2, 3]}`

---

## 管理端-商品SPU管理 (AdminSpuController)

**Base Path:** `/admin/spus`

*(SPU: Standard Product Unit)*

### 1. 分页查询SPU列表
-   **Endpoint:** `GET /`
-   **Summary:** 分页查询SPU列表
-   **Description:** 支持按名称、分类ID等条件搜索。
-   **Request:**
    -   Query Parameters: `pageNum`, `pageSize`, `name` (optional), `categoryId` (optional), `publishStatus` (optional)
-   **Response (200 OK):** `Result<Page<Spu>>`
    ```json
    // Spu DTO example (simplified)
    {
      "id": 101,
      "name": "iPhone 15 Pro",
      "subTitle": "Titanium. So strong. So light. So Pro.",
      "categoryId": 10, // FK to Category
      "publishStatus": 1, // 0->下架, 1->上架
      "newStatus": 1, // 0->非新品, 1->新品
      "recommandStatus": 1, // 0->不推荐, 1->推荐
      "pic": "url-to-main-image.jpg",
      "albumPics": "url1.jpg,url2.jpg", // Comma-separated list of album pictures
      "description": "Detailed HTML description",
      // ... other SPU fields like sale, price (min SKU price), etc.
    }
    ```

### 2. 添加SPU
-   **Endpoint:** `POST /`
-   **Summary:** 添加新的商品SPU
-   **Request Body:** `Spu` DTO (full SPU details including SKUs and attributes often handled in a more complex DTO or subsequent calls)
-   **Response (200 OK):** `Result<Spu>` or `Result<?>`

### 3. 更新SPU
-   **Endpoint:** `PUT /{id}`
-   **Request Body:** `Spu` DTO (fields to update)
-   **Response (200 OK):** `Result<Spu>` or `Result<?>`

### 4. 删除SPU
-   **Endpoint:** `DELETE /{id}`
-   **Response (200 OK):** `Result<?>`

### 5. 根据ID获取SPU详情
-   **Endpoint:** `GET /{id}`
-   **Response (200 OK):** `Result<SpuDetailDto>` (Spu + Skus + Attributes + Images)
    ```json
    // SpuDetailDto example (conceptual)
    {
      "spuInfo": { /* Spu DTO */ },
      "skuList": [ /* Sku DTO list */ ],
      "attributeGroups": [ /* Groups of attributes with values */ ],
      "images": [ /* ProductImage DTO list */ ]
    }
    ```

### 6. 更新SPU上下架状态
-   **Endpoint:** `PUT /{id}/status/{status}`
-   **Path Parameters:** `id` (SPU ID), `status` (0 for unpublish, 1 for publish)
-   **Response (200 OK):** `Result<?>`

---

## 管理端-用户管理 (AdminUserController)

**Base Path:** `/admin/users`

*(Detailed documentation for AdminUserController endpoints.)*

-   `POST /login`: 管理员登录 (`Result<LoginResponse>`) 
    -   Request Body: `{"username": "admin", "password": "password123"}`
    -   LoginResponse: `{"token": "jwt-token-string", "userInfo": { /* User DTO */ }}`
-   `POST /logout`: 管理员登出 (`Result<?>`)
-   `GET /info`: 获取当前登录管理员信息 (`Result<UserInfo>`) (UserInfo includes user details and permissions/roles)
-   `GET /`: 分页查询用户列表 (`Result<Page<User>>`)
-   `POST /`: 添加用户 (管理员) (`Result<User>` or `Result<?>`)
-   `PUT /{id}`: 更新用户信息 (`Result<User>` or `Result<?>`)
-   `DELETE /{id}`: 删除用户 (`Result<?>`)
-   `GET /{id}`: 根据ID获取用户信息 (`Result<User>`)
-   `PUT /{id}/status/{status}`: 更新用户状态 (启用/禁用) (`Result<?>`)
-   `GET /{id}/roles`: 获取用户拥有的角色ID列表 (`Result<List<Long>>`)
-   `PUT /{id}/roles`: 为用户分配角色 (`Result<?>`)
    -   Request Body: `{"roleIds": [1, 2]}`

---

## 管理端-商品属性管理 (AttributeController)

**Base Path:** `/admin/attributes`

*(Detailed documentation for AttributeController endpoints.)*

-   `GET /`: 分页查询属性列表 (`Result<Page<Attribute>>`)
    -   Query Params: `pageNum`, `pageSize`, `name` (optional), `categoryId` (optional), `type` (optional: 0->规格, 1->参数)
-   `POST /`: 添加属性 (`Result<Attribute>` or `Result<?>`)
    -   Request Body: `Attribute` object (e.g., `{"name": "Color", "categoryId": 1, "type": 0, "inputType": 0, "inputList": "Red,Green,Blue"}`)
-   `PUT /{id}`: 更新属性 (`Result<Attribute>` or `Result<?>`)
-   `DELETE /{id}`: 删除属性 (`Result<?>`)
-   `GET /{id}`: 根据ID获取属性 (`Result<Attribute>`)
-   `GET /category/{categoryId}`: 根据分类ID获取属性列表 (`Result<List<Attribute>>`)

---

## 管理端-商品图片管理 (ProductImageController)

**Base Path:** `/admin/product-images`

### 1. 上传商品图片
-   **Endpoint:** `POST /upload`
-   **Summary:** 上传单个商品图片
-   **Description:** Typically uses `multipart/form-data`.
-   **Request:**
    -   Form Data: `file` (the image file)
-   **Response (200 OK):** `Result<FileUploadResponse>`
    ```json
    {
      "code": 0,
      "message": "success",
      "data": {
        "url": "http://cdn.example.com/path/to/image.jpg",
        "name": "image.jpg"
      }
    }
    ```

### 2. 批量上传商品图片
-   **Endpoint:** `POST /upload/batch`
-   **Request:**
    -   Form Data: `files` (multiple image files)
-   **Response (200 OK):** `Result<List<FileUploadResponse>>`

### 3. 删除商品图片
-   **Endpoint:** `DELETE /`
-   **Request Body:** `application/json` 
    ```json
    {
      "imageUrl": "http://cdn.example.com/path/to/image.jpg"
    }
    ```
-   **Response (200 OK):** `Result<?>`

### 4. 获取SPU的图片列表
-   **Endpoint:** `GET /spu/{spuId}`
-   **Response (200 OK):** `Result<List<ProductImage>>` 
    ```json
    // ProductImage DTO
    {
        "id": 1, 
        "spuId": 101,
        "imgName": "front_view.jpg",
        "imgUrl": "url-to-image.jpg",
        "sort": 0,
        "isMain": 1 // 0->not main, 1->main image
    }
    ```

### 5. 关联SPU与图片
-   **Endpoint:** `POST /spu/{spuId}/associate`
-   **Path Parameters:** `spuId`
-   **Request Body:** `application/json` (List of image URLs or image data to associate)
    ```json
    {
      "imageUrls": ["url1.jpg", "url2.jpg"]
      // or detailed image objects if creating new ProductImage records
    }
    ```
-   **Response (200 OK):** `Result<?>`

### 6. 设置SPU主图
-   **Endpoint:** `PUT /{id}/set-main`
-   **Path Parameters:** `id` (ProductImage ID)
-   **Response (200 OK):** `Result<?>`

---

## 管理端-商品SKU管理 (SkuController)

**Base Path:** `/admin/skus`

*(SKU: Stock Keeping Unit)*

### 1. 根据SPU ID获取SKU列表
-   **Endpoint:** `GET /spu/{spuId}`
-   **Response (200 OK):** `Result<List<Sku>>`
    ```json
    // Sku DTO example (simplified)
    {
      "id": 1001,
      "spuId": 101,
      "skuCode": "IP15P-BLK-256G",
      "price": 7999.00,
      "stock": 100,
      "lowStock": 10, // Low stock warning threshold
      "pic": "url-to-sku-specific-image.jpg",
      "sale": 50, // Sales count
      "promotionPrice": 7899.00,
      "lockStock": 5, // Locked stock (e.g., in unpaid orders)
      "spData": "[{\"key\":\"颜色\",\"value\":\"黑色"},{\"key\":\"存储\",\"value\":\"256GB\"}]", // JSON string of spec attributes
      "status": 1 // 0->下架, 1->上架
    }
    ```

### 2. 根据ID获取SKU
-   **Endpoint:** `GET /{id}`
-   **Response (200 OK):** `Result<Sku>`

### 3. 根据ID获取SKU及其属性
-   **Endpoint:** `GET /{id}/with-attributes`
-   **Response (200 OK):** `Result<SkuWithAttributesDTO>`
    ```json
    // SkuWithAttributesDTO (conceptual)
    {
      "skuInfo": { /* Sku DTO */ },
      "attributes": [
        {"attributeName": "颜色", "attributeValue": "黑色"},
        {"attributeName": "存储", "attributeValue": "256GB"}
      ]
    }
    ```

### 4. 添加SKU
-   **Endpoint:** `POST /`
-   **Request Body:** `Sku` DTO
-   **Response (200 OK):** `Result<Sku>` or `Result<?>`

### 5. 添加SKU及其属性
-   **Endpoint:** `POST /with-attributes`
-   **Request Body:** `SkuWithAttributesDTO`
-   **Response (200 OK):** `Result<Sku>` or `Result<?>`

### 6. 更新SKU
-   **Endpoint:** `PUT /{id}`
-   **Request Body:** `Sku` DTO (fields to update)
-   **Response (200 OK):** `Result<Sku>` or `Result<?>`

### 7. 更新SKU及其属性
-   **Endpoint:** `PUT /{id}/with-attributes`
-   **Request Body:** `SkuWithAttributesDTO`
-   **Response (200 OK):** `Result<Sku>` or `Result<?>`

### 8. 删除SKU
-   **Endpoint:** `DELETE /{id}`
-   **Response (200 OK):** `Result<?>`

### 9. 更新SKU状态
-   **Endpoint:** `PUT /{id}/status/{status}`
-   **Path Parameters:** `id` (SKU ID), `status` (0 for unpublish, 1 for publish)
-   **Response (200 OK):** `Result<?>`

---

*This documentation is auto-generated based on controller annotations and method signatures. Specific DTO structures are illustrative and should be verified against the actual Java class definitions.*