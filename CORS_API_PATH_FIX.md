# CORS和API路径问题修复文档

## 🔍 **问题分析**

从前端错误日志可以看出有以下问题：

### 1. **CORS跨域问题**
```
Access to XMLHttpRequest at 'http://localhost:8080/v1/user/roles' from origin 'http://localhost:5173' has been blocked by CORS policy: Response to preflight request doesn't pass access control check: No 'Access-Control-Allow-Origin' header is present on the requested resource.
```

### 2. **API路径错误**
- **期望路径**：`http://localhost:8080/api/v1/user/roles`
- **实际请求**：`http://localhost:8080/v1/user/roles`
- **问题**：缺少 `/api` 前缀

### 3. **Chrome扩展错误**
```
Unchecked runtime.lastError: The message port closed before a response was received.
```
这是Chrome扩展的问题，不影响应用功能。

## ✅ **已完成的修复**

### 1. **修复前端Store中的API调用**

**文件：** `frontend/admin/src/store/modules/user.ts`

**问题：** 在`getUserRoles`和`getUserPermissions`方法中直接使用request，路径缺少`/api`前缀

**修复前：**
```typescript
async getUserRoles() {
  try {
    const response: any = await request({
      url: '/v1/user/roles',  // 缺少/api前缀
      method: 'get'
    })
    // ...
  }
}
```

**修复后：**
```typescript
async getUserRoles() {
  try {
    const response: any = await getUserRoles()  // 使用正确的API函数
    if (response.success) {
      this.roles = response.data || []
    }
  } catch (error) {
    console.error('获取用户角色失败:', error)
    this.roles = []
  }
}
```

### 2. **CORS配置已存在**

**文件：** `src/main/java/com/dreamy/hive/config/CorsConfig.java`

CORS配置已经正确设置：
```java
@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOriginPattern("*");  // 允许所有域名
        config.setAllowCredentials(true);     // 允许携带cookie
        config.addAllowedHeader("*");         // 允许所有请求头
        config.addAllowedMethod("*");         // 允许所有请求方法
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}
```

## 🔧 **配置说明**

### 后端配置
- **Context Path**：`/api` (在application.yml中配置)
- **实际API路径**：`http://localhost:8080/api/v1/user/roles`
- **CORS**：已配置允许跨域

### 前端配置
- **Base URL**：`http://localhost:8080`
- **API路径**：`/api/v1/user/roles`
- **完整URL**：`http://localhost:8080/api/v1/user/roles`

## 🧪 **测试验证**

### 1. 启动服务
```bash
# 后端
mvn spring-boot:run

# 前端
cd frontend/admin
npm run dev
```

### 2. 测试API
```bash
# 测试登录
curl -X POST "http://localhost:8080/api/v1/user/login" \
  -H "Content-Type: application/json" \
  -d '{"username":"hkun33375","password":"123456"}'

# 测试角色获取（需要token）
curl -X GET "http://localhost:8080/api/v1/user/roles" \
  -H "satoken: YOUR_TOKEN"
```

### 3. 前端测试
1. 访问 `http://localhost:5173`
2. 使用账号 `hkun33375/123456` 登录
3. 检查浏览器控制台是否还有CORS错误
4. 验证用户角色和权限是否正确获取

## 📋 **相关文件**

### 前端文件
- `frontend/admin/src/api/request.ts` - Axios配置
- `frontend/admin/src/api/user.ts` - 用户API接口
- `frontend/admin/src/store/modules/user.ts` - 用户状态管理

### 后端文件
- `src/main/java/com/dreamy/hive/config/CorsConfig.java` - CORS配置
- `src/main/resources/application.yml` - 应用配置
- `src/main/java/com/dreamy/hive/controller/v1/user/UserController.java` - 用户控制器

## 🎯 **预期结果**

修复后应该能够：
1. ✅ 前端成功调用后端API
2. ✅ 不再出现CORS跨域错误
3. ✅ 正确获取用户角色和权限信息
4. ✅ 管理员接口权限验证正常工作

## 🚨 **注意事项**

1. **Chrome扩展错误**：`runtime.lastError` 是浏览器扩展的问题，不影响应用功能
2. **Token格式**：确保前端正确保存和发送SaToken
3. **路径一致性**：所有API调用都必须包含 `/api` 前缀
4. **角色权限**：确保用户有正确的角色（super_admin或admin）

## 🔄 **下一步**

1. 重启前后端服务
2. 测试登录和权限获取功能
3. 验证管理员接口访问权限
4. 检查所有API调用是否正常工作 