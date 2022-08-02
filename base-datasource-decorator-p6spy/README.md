# 数据源sql打印模块

## 数据源sql打印组件，采用p6spy依赖实现

### usage

* 引入依赖

~~~
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-datasource-decorator-p6spy</artifactId>
</dependency>
~~~

* 替换JDBC Driver为com.p6spy.engine.spy.P6SpyDriver

* 修改JDBC Url为jdbc:p6spy:xxxx

* base-datasource-decorator-p6spy组件中已预置了p6spy的配置文件**spy.properties**

日志打印样例：

~~~
2022-08-01 17:25:12.925  INFO 19476 --- [nio-8610-exec-9] p6spy                                    : -------------------------------------sql execution information-------------------------------------
sql         : INSERT INTO user  ( name, username, password, del_flag, create_time, update_time, status )  VALUES  ( '2acc48', 'b49cab', '1aadfd', 0, '2022-08-01T17:25:12.919', '2022-08-01T17:25:12.919', 1 )
category    : statement
connectionId: 1
took        : 2
~~~