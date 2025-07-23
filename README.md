# 外卖微服务系统

## 项目介绍

外卖微服务系统是一个基于Spring Cloud的微服务架构外卖订餐平台，支持用户下单、商家接单、菜品管理、订单跟踪等完整的外卖业务流程。系统采用前后端分离架构，后端基于微服务设计，提供了良好的可扩展性和可维护性。

## 系统架构

![架构图](https://via.placeholder.com/800x400?text=外卖系统微服务架构)

### 微服务组成

- **注册中心**：Eureka服务注册与发现
- **配置中心**：Spring Cloud Config集中配置管理
- **API网关**：Spring Cloud Gateway统一入口
- **认证服务**：负责用户认证和权限管理
- **用户服务**：用户信息管理与购物车功能
- **商家服务**：商家信息和门店管理
- **菜品服务**：菜品信息管理
- **订单服务**：订单生命周期管理

## 技术栈

### 后端技术

- **基础框架**：Spring Boot 3.2.0、Spring Cloud 2023.0.0
- **服务注册与发现**：Spring Cloud Netflix Eureka
- **配置中心**：Spring Cloud Config
- **服务网关**：Spring Cloud Gateway
- **服务调用**：Spring Cloud OpenFeign
- **数据库**：MySQL 8.0
- **缓存**：Redis
- **消息队列**：（预留）
- **认证授权**：JWT (JSON Web Token)
- **持久层**：MyBatis
- **项目构建**：Maven

### 前端技术

- 前端采用Vue.js或React构建（未包含在本仓库）

## 模块说明

```
take-out-microservices/
├── take-out-common/            # 公共模块
├── take-out-registry/          # 注册中心
├── take-out-config/            # 配置中心
├── take-out-gateway/           # API网关
├── take-out-auth-service/      # 认证服务
├── take-out-user-service/      # 用户服务
├── take-out-merchant-service/  # 商家服务
├── take-out-dish-service/      # 菜品服务
└── take-out-order-service/     # 订单服务
```

### 各模块功能

| 模块名称 | 主要功能 |
|---------|--------|
| take-out-common | 包含公共配置、工具类、DTO对象、异常处理等 |
| take-out-registry | 服务注册中心，提供服务注册与发现功能 |
| take-out-config | 配置中心，提供集中配置管理 |
| take-out-gateway | API网关，请求路由、过滤、限流等 |
| take-out-auth-service | 用户认证、权限管理、JWT生成与验证 |
| take-out-user-service | 用户信息、地址管理、购物车功能 |
| take-out-merchant-service | 商家管理、门店管理、分类管理 |
| take-out-dish-service | 菜品管理、菜品展示、菜品推荐 |
| take-out-order-service | 订单创建、支付、状态管理、配送跟踪 |

## 环境要求

- JDK 17+
- Maven 3.8+
- MySQL 8.0+
- Redis 6.0+
- 其他依赖会通过Maven自动管理

## 本地开发环境搭建

### 1. 克隆代码

```bash
git clone https://github.com/yourusername/take-out-microservices.git
cd take-out-microservices
```

### 2. 准备数据库

```sql
CREATE DATABASE take_out_system;
```

### 3. 修改配置

根据需要修改各个服务的application.yml或bootstrap.yml配置文件。

### 4. 编译项目

```bash
mvn clean package -DskipTests
```

### 5. 启动服务

按照以下顺序启动服务：

```bash
# 1. 启动注册中心
cd take-out-registry
mvn spring-boot:run

# 2. 启动配置中心（可选，本地开发可以禁用）
cd take-out-config
mvn spring-boot:run

# 3. 启动各个业务服务
cd take-out-auth-service
mvn spring-boot:run

cd take-out-user-service
mvn spring-boot:run

# 以此类推启动其他服务...

# 4. 最后启动网关
cd take-out-gateway
mvn spring-boot:run
```

## API文档

API文档通过Swagger提供，服务启动后访问：

- 认证服务API: http://localhost:8080/api/auth/swagger-ui.html
- 用户服务API: http://localhost:8080/api/user/swagger-ui.html
- 商家服务API: http://localhost:8080/api/merchant/swagger-ui.html
- 菜品服务API: http://localhost:8080/api/dish/swagger-ui.html
- 订单服务API: http://localhost:8080/api/order/swagger-ui.html

## 部署指南

### Docker部署

项目提供了Docker支持，可以使用Docker Compose进行容器化部署。

```bash
# 构建镜像
docker-compose build

# 启动服务
docker-compose up -d
```

## 开发计划

详细的开发计划和待办事项请参考[开发计划](todolist.md)。

## 贡献指南

1. Fork 本仓库
2. 创建特性分支 (`git checkout -b feature/amazing-feature`)
3. 提交变更 (`git commit -m 'Add some amazing feature'`)
4. 推送到远程分支 (`git push origin feature/amazing-feature`)
5. 创建Pull Request

## 许可证

本项目采用MIT许可证 - 详情请查看[LICENSE](LICENSE)文件。

## 联系方式

项目维护者 - 邮箱地址

---

项目参考自开源社区的多个微服务项目，感谢所有开源贡献者。 