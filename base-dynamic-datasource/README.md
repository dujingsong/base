# 多数据源模块

## 数据源sql打印组件，采用dynamic-datasource-spring-boot-starter依赖实现

### usage

* 引入依赖

~~~
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-dynamic-datasource</artifactId>
</dependency>
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-datasource-druid</artifactId>
</dependency>
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-datasource-decorator-p6spy</artifactId>
</dependency>
~~~


* 配置示例

~~~
spring:
  datasource:
    dynamic:
      primary: master #设置默认的数据源或者数据源组,默认值即为master
      strict: false #严格匹配数据源,默认false. true未匹配到指定数据源时抛异常,false使用默认数据源
      datasource:
        master:
          url: jdbc:p6spy:mysql://host:3306/app_skeleton?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false
          username: xxx
          password: xxx
          driver-class-name: com.p6spy.engine.spy.P6SpyDriver # 3.2.0开始支持SPI可省略此配置
          druid:
            initial-size: 10
            min-idle: 10
            max-active: 100
            pool-prepared-statements: true
            max-pool-prepared-statement-per-connection-size: 20
            validation-query: SELECT 1
            validation-query-timeout: 1000
            test-on-borrow: true
            test-on-return: true
            test-while-idle: true
            time-between-eviction-runs-millis: 60000
            min-evictable-idle-time-millis: 300000
            max-evictable-idle-time-millis: 300000

        slave_1:
          url: jdbc:p6spy:mysql://host:3306/app_skeleton1?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false
          username: xxx
          password: xxx
          driver-class-name: com.p6spy.engine.spy.P6SpyDriver
          druid:
            initial-size: 10
            min-idle: 10
            max-active: 100
            pool-prepared-statements: true
            max-pool-prepared-statement-per-connection-size: 20
            validation-query: SELECT 1
            validation-query-timeout: 1000
            test-on-borrow: true
            test-on-return: true
            test-while-idle: true
            time-between-eviction-runs-millis: 60000
            min-evictable-idle-time-millis: 300000
            max-evictable-idle-time-millis: 300000
~~~

配置映射的类为：com.baomidou.dynamic.datasource.spring.boot.autoconfigure

类的方法上添加注解，注解的可以值是yml文件中的 slave_1，代表使用slave_1数据源，不填默认是主数据源master
