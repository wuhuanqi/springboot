# 网络连接错误处理说明

## 概述
本项目已实现全局网络连接错误处理机制，用于捕获和处理各种网络相关的异常。

## 实现的功能

### 1. 全局异常处理器 (`GlobalExceptionHandler`)
位置: `src/main/java/com/wuhuanqi/girl/exception/GlobalExceptionHandler.java`

处理以下类型的网络异常：
- **SQLException**: 数据库连接错误
- **ConnectException**: 网络连接失败
- **SocketTimeoutException**: 网络超时
- **UnknownHostException**: 未知主机错误
- **ResourceAccessException**: 资源访问错误
- **其他网络相关异常**: 通用网络错误处理

### 2. 错误响应格式 (`ErrorResponse`)
位置: `src/main/java/com/wuhuanqi/girl/exception/ErrorResponse.java`

错误响应包含以下信息：
- `message`: 用户友好的错误消息
- `details`: 错误详细信息
- `requestId`: 唯一请求ID（UUID格式）
- `timestamp`: 错误发生时间戳

### 3. 数据库连接优化
位置: `src/main/resources/application.yml`

新增配置：
- 连接超时: 5秒
- Socket超时: 10秒
- HikariCP连接池配置
  - 最大连接数: 10
  - 最小空闲连接: 2
  - 连接超时: 5秒

## 错误消息示例

当发生网络连接错误时，系统会返回如下格式的错误信息：

```json
{
    "message": "Connection failed. If the problem persists, please check your internet connection or VPN",
    "details": "Network disconnected [unknown]",
    "requestId": "d889a9c2-79b3-4774-89ba-a9fa98acdb2d",
    "timestamp": 1728475200000
}
```

## 使用说明

1. **自动捕获**: 所有控制器中的网络异常会被自动捕获
2. **日志记录**: 错误信息会被记录到控制台
3. **HTTP状态码**: 
   - `503 SERVICE_UNAVAILABLE`: 网络连接失败
   - `408 REQUEST_TIMEOUT`: 请求超时
   - `500 INTERNAL_SERVER_ERROR`: 其他一般性错误

## 常见问题

### Q: 为什么会出现 "Connection failed" 错误？

**可能的原因：**
1. 数据库服务未启动
2. 网络连接不稳定
3. VPN连接问题
4. 数据库配置错误（主机名、端口、用户名、密码）
5. 防火墙阻止连接

**解决方案：**
1. 检查MySQL服务是否运行: `systemctl status mysql` (Linux) 或 `services.msc` (Windows)
2. 验证数据库连接配置: `application.yml`
3. 测试网络连接: `ping localhost`
4. 检查VPN状态
5. 查看应用日志中的详细错误信息和Request ID

### Q: 如何调试网络连接问题？

1. 查看控制台日志中的Request ID
2. 检查错误详情中的具体错误类型
3. 验证数据库配置是否正确
4. 使用数据库客户端测试连接

## 技术细节

- **@ControllerAdvice**: Spring注解，用于全局异常处理
- **@ExceptionHandler**: 指定处理特定类型的异常
- **ResponseEntity**: 自定义HTTP响应状态码和内容
- **HikariCP**: 高性能JDBC连接池

## 维护建议

1. 定期检查日志中的网络错误模式
2. 根据实际情况调整连接超时配置
3. 监控数据库连接池状态
4. 为生产环境配置合适的连接池参数
