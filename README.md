# SpringCloud-Renting 
这是一个基于 **SpringCloud**、**Vue** 和 **uniapp** 的租房系统，提供后端服务、管理端以及用户端应用的整合。该系统通过 Spring Cloud 实现微服务架构，采用 Vue 和 uniapp 分别作为管理端和用户端的前端技术栈。

## 技术栈

### 后端技术栈（SpringCloud）
- **Spring Cloud**：微服务架构，提供服务注册、发现、负载均衡、熔断等功能
- **Eureka**：服务注册与发现
- **UAA (User Authentication and Authorization)**：用户认证与授权服务
- **Redis**：缓存管理，提升系统性能
- **MongoDB**：非关系型数据库，用于存储租房相关的数据
- **Spring Boot**：后端服务的基础框架，提供 REST API 服务

### 前端技术栈

#### 管理端（Vue）
- **Vue**：前端框架，提供响应式 UI 和组件化开发
- **Element UI**：UI 组件库，用于构建管理端的用户界面
- **Axios**：用于发送 HTTP 请求与后端通信

#### 用户端（uniapp）
- **uniapp**：跨平台开发框架，支持在多种平台上运行（如 iOS、Android、H5、小程序等）

---

## 项目架构

### 后端架构
1. **Eureka 服务注册与发现**：各个微服务通过 Eureka 注册自己，并向 Eureka 服务器进行服务发现。
2. **API 网关**：通过 Spring Cloud Gateway 实现请求的路由转发、负载均衡、统一认证等。
3. **微服务模块**：
   - **账户服务（account）**：处理用户账户的管理，包括用户注册、登录等功能。
   - **用户服务（user）**：处理与用户信息相关的操作，如获取用户信息、修改用户资料等。
   - **地点服务（place）**：提供租房地点的信息。
   - **房源服务（housing）**：提供房源的管理、查询等功能。
   - **即时通讯服务（IM）**：为用户提供实时聊天功能。

### 前端架构
1. **Vue 管理端**：通过 Vue 和 Element UI 构建管理员控制台，用于管理房源、用户等信息。
2. **uniapp 用户端**：通过 uniapp 开发，支持多平台运行，提供租房信息展示、用户操作等功能。