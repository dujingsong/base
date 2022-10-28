# 微服务开发模块

## 微服务开发组件，服务间的通信使用openfeign

### usage

引入依赖

~~~
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-cloud</artifactId>
</dependency>
~~~

### 搭配服务注册发现组件

#### eureka

引入依赖

~~~
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
~~~

yml配置

~~~
eureka:
  client:
    serviceUrl:
      defaultZone: http://localhost:8761/eureka/
  instance:
    prefer-ip-address: true
~~~

eureka配置相关
https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/appendix.html

### 参考资料

#### spring-cloud-openfeign

https://docs.spring.io/spring-cloud-openfeign/docs/3.0.3/reference/html/

#### openfeign可选配置

https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/appendix.html