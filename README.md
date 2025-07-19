# Online Judge 项目

## 项目简介
Online Judge 是一个基于 Spring Boot 的在线判题系统，支持用户提交代码并自动判题。本项目集成了 [JavaNative-Sandbox](https://github.com/Study944/JavaNative-Sandbox) 作为代码执行沙箱，提供安全高效的代码运行环境。

<img width="3056" height="1668" alt="image" src="https://github.com/user-attachments/assets/aa333874-8bab-4b5a-82ec-075db2e23203" />

<img width="3056" height="1668" alt="image" src="https://github.com/user-attachments/assets/0df62dc3-ec85-4fc6-9bc3-c4153064924a" />

<img width="3056" height="1668" alt="image" src="https://github.com/user-attachments/assets/1abd361c-8f5e-411d-884d-bed86603f026" />

<img width="3056" height="1668" alt="image" src="https://github.com/user-attachments/assets/21ccf8a2-1ac7-426d-a9a6-6e1e2d58dde8" />

## 技术栈
- **后端框架**: Spring Boot 3.2.0
- **数据库**: MySQL
- **ORM**: MyBatis-Plus
- **API文档**: Knife4j
- **工具类**: Hutool
- **安全**: JWT
- **代码沙箱**: JavaNative-Sandbox
- **其他**: Lombok, Spring AI, AOP

## 项目结构
```
├── src/main/java/com/onlinejudge
│   ├── controller      # 控制器
│   ├── manager         # 业务管理器
│   │   ├── judge       # 判题服务
│   │   └── sandbox     # 代码沙箱
│   ├── model           # 数据模型
│   ├── service         # 业务逻辑
│   └── OnlineJudgeApplication.java # 启动类
├── src/main/resources
│   └── application.yml # 配置文件
└── sql/table.sql       # 数据库脚本
```

## 核心功能
1. **用户管理**: 注册、登录、权限控制
2. **题目管理**: 题目CRUD、测试用例管理
3. **代码提交**: 支持多种编程语言提交
4. **自动判题**: 基于代码沙箱的自动判题流程

## 判题流程
1. 用户提交代码至 `/submission` 接口
2. 系统保存提交记录至数据库
3. 异步调用判题服务 `JudgeService.doJudge()`
4. 通过 `SandBoxFactory` 获取远程沙箱实例
5. 调用 JavaNative-Sandbox 执行代码
6. 比对输出结果与预期结果
7. 更新提交状态和判题信息

## 代码沙箱集成
本项目使用 [JavaNative-Sandbox](https://github.com/Study944/JavaNative-Sandbox) 作为代码执行环境：

### 配置方式
在 `application.yml` 中配置沙箱类型：
```yaml
sandbox:
  type: remote  # 远程沙箱模式
```

### 实现原理
1. 通过 `SandBoxFactory` 根据配置获取沙箱实例
2. `RemoteCodeSandBoxImpl` 类负责与远程沙箱通信
3. 发送 HTTP 请求至沙箱服务端点：`http://192.168.67.129:8777/sandbox/run`
4. 接收并解析沙箱返回的执行结果

## 数据库设计
主要表结构：
- `user`: 用户信息
- `question`: 题目信息
- `submission`: 代码提交记录

## 安装部署
1. 克隆项目
```bash
git clone https://github.com/yourusername/online-judge.git
cd online-judge
```

2. 初始化数据库
```bash
# 执行 sql/table.sql 文件
```

3. 配置数据库
修改 `application.yml` 中的数据库连接信息

4. 启动项目
```bash
mvn spring-boot:run
```

## 接口文档
项目集成了 Knife4j，启动后可访问：
`http://localhost:8111/doc.html`

## 许可证
MIT
