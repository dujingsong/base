# 数据源（连接池）模块

## 数据源（连接池）组件，采用druid依赖实现

### usage

引入依赖
~~~
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
</dependency>
~~~
如果mysql数据库，需要引入mysql驱动
~~~
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
~~~

yml配置示例
~~~
# druid
spring:
  datasource:
    druid:
      url: jdbc:p6spy:mysql://host:3306/app_skeleton?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false
      username: username
      password: password
      driver-class-name: com.p6spy.engine.spy.P6SpyDriver
      initial-size: 10
      min-idle: 10
      max-active: 100
      pool-prepared-statements: true
      max-pool-prepared-statement-per-connection-size: 20
      max-open-prepared-statements: 20
      validation-query: SELECT 1
      validation-query-timeout: 1000
      test-on-borrow: true
      test-on-return: true
      test-while-idle: true
      time-between-eviction-runs-millis: 60000
      min-evictable-idle-time-millis: 300000
      max-evictable-idle-time-millis: 300000
~~~

### 原理
spring-boot-autoconfigure依赖下spring.factories文件中有下面的自动装配配置类:
~~~
org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
~~~
这个配置类中会通过import引入几个数据库连接池配置类：
~~~
DataSourceConfiguration.Hikari
DataSourceConfiguration.Tomcat
DataSourceConfiguration.Dbcp2
DataSourceConfiguration.OracleUcp
DataSourceConfiguration.Generic
DataSourceJmxConfiguration
~~~
Hikari优先级高，会优先装配，因此为了优先装配druid的连接池，**druid-spring-boot-starter**使用了**AutoConfigureBefore**注解
~~~
@AutoConfigureBefore(DataSourceAutoConfiguration.class)
~~~
见druid-spring-boot-starter依赖下spring.factories文件中的自动装配配置类:
~~~
com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure
~~~
