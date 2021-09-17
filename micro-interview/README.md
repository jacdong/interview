## 技术选型
```java
服务注册于发现				nacos
配置中心					nacos
熔断,降级,限流				sentinel
网关					gateway
鉴权					oauth2/security
数据库					mysql
链路追踪					skywalking
APM					普罗米修斯，grafan
log					logback + slf4j
接口文档					swagger2
api版本控制				自定义注解	
全局请求日志				网关端拦截
全局ID					基于雪花算法实现
私服					Sonatype Nexus
```

## 系统体系结构
![](C:\Users\dongbin\Desktop\interview.png)

##pre-condtion
* 安装mysql
* 安装nacos
* 安装redis
* 安装skywalking

## 初始化操作
* 初始化nacos 数据库
* 创建用户基本信息表
* 创建用户权限表
* 初始化admin 用户


## 地址
nacos:http://121.5.65.114:8848/nacos <br/>
skywalking :http://139.196.217.88:8101/ <br/>
sentinel :http://139.196.217.88:9100/ <br/>

## 主要类介绍
```java
WebCorsConfiguration ：解决跨域问题
```