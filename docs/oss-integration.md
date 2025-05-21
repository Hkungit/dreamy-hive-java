# 阿里云OSS文件存储服务集成

## 概述

本项目使用阿里云OSS作为文件存储服务，实现了图片、头像等文件的上传和管理功能。

## 特性

- 支持头像、图片上传
- 支持商品图片上传
- 支持帖子图片上传
- 文件安全校验
- 文件大小限制
- 文件类型限制
- 文件名生成策略

## 配置说明

在`application.yml`中配置阿里云OSS相关参数：

```yaml
# 阿里云OSS配置
aliyun:
  oss:
    # 地域节点
    endpoint: oss-cn-hangzhou.aliyuncs.com
    # 访问密钥ID
    accessKeyId: your-access-key-id
    # 访问密钥密码
    accessKeySecret: your-access-key-secret
    # 存储桶名称
    bucketName: your-bucket-name
    # 访问域名
    domain: https://${aliyun.oss.bucketName}.${aliyun.oss.endpoint}
    # 文件存储目录
    dir:
      # 头像存储目录
      avatar: avatars/
      # 图片存储目录
      image: images/
      # 商品图片存储目录
      product: products/
      # 帖子图片存储目录
      post: posts/
```

文件上传配置：

```yaml
# 文件上传配置
file:
  upload:
    # 允许上传的图片类型
    allowedImageTypes: ["bmp", "jpg", "jpeg", "gif", "png"]
    # 允许上传的文件类型
    allowedFileTypes: ["pdf", "doc", "docx", "xls", "xlsx", "ppt", "pptx", "txt", "zip", "rar"]
    # 最大文件大小（MB）
    maxFileSize: 10
    # 最大图片大小（MB）
    maxImageSize: 5
    # 是否允许覆盖已存在的文件
    allowOverride: false
```

## 使用说明

### 上传头像

```http
POST /api/file/upload/avatar
Content-Type: multipart/form-data

file=@avatar.jpg
```

### 上传普通图片

```http
POST /api/file/upload/image
Content-Type: multipart/form-data

file=@image.png
```

### 上传商品图片

```http
POST /api/file/upload/product
Content-Type: multipart/form-data

file=@product.jpg
```

### 上传帖子图片

```http
POST /api/file/upload/post
Content-Type: multipart/form-data

file=@post.jpg
```

### 删除文件

```http
DELETE /api/file/delete?fileUrl=https://your-bucket.oss-cn-hangzhou.aliyuncs.com/images/example.jpg
```

## 响应格式

所有上传接口响应格式如下：

```json
{
  "code": 0,
  "message": "success",
  "data": {
    "fileName": "d41d8cd98f00b204e9800998ecf8427e.jpg",
    "originalFileName": "example.jpg",
    "url": "https://your-bucket.oss-cn-hangzhou.aliyuncs.com/images/d41d8cd98f00b204e9800998ecf8427e.jpg",
    "size": 1024,
    "contentType": "image/jpeg"
  }
}
```

## 安全措施

系统对上传的文件进行了以下安全检查：

1. 文件大小限制：防止超大文件上传
2. 文件类型限制：只允许上传指定类型的文件
3. 文件名安全检查：防止路径遍历攻击
4. 生成随机文件名：防止文件名冲突和路径泄露

## 注意事项

1. 在生产环境中，请一定要替换为自己的阿里云OSS账号信息
2. 建议为OSS设置防盗链和跨域访问策略
3. 对于敏感文件，可以设置私有读写权限并使用签名URL访问
4. 定期清理无用文件，避免存储成本浪费 