# Dreamy Hive E-commerce Project

## 功能模块

### 1. 用户认证与授权

我们使用 Sa-Token 实现了用户认证和授权功能，并添加了自定义的 `@CurrentUser` 注解，方便在控制器方法中获取当前登录用户ID。

- **Sa-Token 集成**：实现了基于角色的访问控制，如 `/api/admin/**` 路径需要 admin 角色
- **@CurrentUser 注解**：自定义注解，用于在控制器方法中自动注入当前登录用户ID
- **CurrentUserMethodArgumentResolver**：注解解析器，使用 Sa-Token 获取当前用户ID

详细文档请参考 [认证与授权实现文档](docs/authentication.md)。

### 2. SKU规格选择逻辑

我们实现了SKU规格选择逻辑，允许商品有多个不同规格的SKU，每个SKU可以关联多个属性。主要包含以下功能：

- **属性管理**：实现了商品属性的CRUD操作，支持按分类管理属性
- **SKU属性关联**：实现了SKU与属性的关联，支持为每个SKU设置不同的属性值
- **SKU与属性组合DTO**：创建了DTO类，用于前后端交互时同时传递SKU信息和属性信息

### 3. 多图片上传与管理功能

我们实现了多图片上传与管理功能，支持商品主图和详情图的上传和管理。主要包含以下功能：

- **文件存储服务**：实现了本地文件存储服务，支持文件上传、下载、删除等操作
- **多文件上传**：支持同时上传多个文件，并返回文件URL列表
- **商品图片管理**：实现了商品图片的CRUD操作，支持按SPU和SKU管理图片
- **图片类型支持**：支持区分主图和详情图，便于前端展示

### 4. 购物车与订单系统

我们实现了完整的购物车和订单系统，支持商品加入购物车、下单、支付、发货、确认收货等功能。

- **购物车功能**：实现了购物车的添加、修改、删除、选择等功能
- **地址管理**：实现了收货地址的增删改查和设置默认地址功能
- **订单流程**：实现了订单创建、支付、发货、确认收货、取消等完整流程
- **订单管理**：实现了管理员查看订单、发货、取消订单等功能

## API接口

### 用户认证接口

- `POST /api/auth/login` - 用户登录
- `POST /api/auth/logout` - 用户注销
- `GET /api/auth/info` - 获取当前用户信息

### 订单管理接口

- `POST /api/orders` - 创建订单
- `GET /api/orders` - 获取用户订单列表
- `GET /api/orders/{orderId}` - 获取订单详情
- `POST /api/orders/{orderId}/cancel` - 取消订单
- `POST /api/orders/{orderId}/pay` - 支付订单
- `POST /api/orders/{orderId}/confirm` - 确认收货

### 管理员订单接口

- `GET /api/admin/orders` - 获取所有订单列表
- `GET /api/admin/orders/{orderId}` - 获取订单详情
- `POST /api/admin/orders/{orderId}/ship` - 订单发货
- `POST /api/admin/orders/{orderId}/cancel` - 取消订单

### 属性管理接口

- `GET /admin/attributes/category/{categoryId}` - 获取分类属性列表
- `POST /admin/attributes` - 添加属性
- `PUT /admin/attributes/{id}` - 更新属性
- `DELETE /admin/attributes/{id}` - 删除属性

### SKU管理接口

- `GET /admin/skus/spu/{spuId}` - 获取SPU下的SKU列表
- `GET /admin/skus/{id}` - 获取SKU详情
- `GET /admin/skus/{id}/attributes` - 获取SKU及其属性
- `POST /admin/skus` - 添加SKU
- `POST /admin/skus/with-attributes` - 添加SKU及其属性
- `PUT /admin/skus/{id}` - 更新SKU
- `PUT /admin/skus/{id}/with-attributes` - 更新SKU及其属性
- `DELETE /admin/skus/{id}` - 删除SKU
- `PUT /admin/skus/{id}/status/{status}` - 更新SKU状态

### 文件上传接口

- `POST /local-files/upload/{directory}` - 上传单个文件
- `POST /local-files/upload-multiple/{directory}` - 上传多个文件
- `GET /local-files/{directory}/{filename}` - 下载文件
- `GET /local-files/image/{directory}/{filename}` - 获取图片
- `DELETE /local-files/{directory}/{filename}` - 删除文件

### 商品图片管理接口

- `POST /admin/product-images/upload/{type}` - 上传商品图片
- `POST /admin/product-images/upload-multiple/{type}` - 批量上传商品图片
- `POST /admin/product-images` - 添加商品图片
- `POST /admin/product-images/batch` - 批量添加商品图片
- `GET /admin/product-images/spu/{spuId}` - 获取商品图片列表
- `GET /admin/product-images/sku/{skuId}` - 获取SKU图片列表
- `DELETE /admin/product-images/{id}` - 删除商品图片
- `PUT /admin/product-images/{id}/sort/{sort}` - 更新商品图片排序

## 配置说明

在`application.properties`中添加以下配置：

```properties
# 文件上传配置
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
file.upload.root-dir=uploads
```

## 使用示例

### @CurrentUser 注解使用示例

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

### 添加SKU及其属性

```json
POST /admin/skus/with-attributes
{
  "sku": {
    "spuId": 1,
    "skuCode": "SKU001",
    "name": "商品名称-红色-XL",
    "specs": "颜色:红色;尺寸:XL",
    "price": 199.00,
    "originalPrice": 299.00,
    "stock": 100,
    "lowStock": 10,
    "pic": "http://example.com/image.jpg",
    "status": 1
  },
  "attributes": [
    {
      "attributeId": 1,
      "attributeValue": "红色"
    },
    {
      "attributeId": 2,
      "attributeValue": "XL"
    }
  ]
}
```

### 上传商品图片

```
POST /admin/product-images/upload/1
Content-Type: multipart/form-data
file: [文件内容]
```

### 添加商品图片

```json
POST /admin/product-images
{
  "spuId": 1,
  "url": "http://example.com/image.jpg",
  "sort": 0,
  "type": 1
}
``` 