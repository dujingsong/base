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

可配置参数：
~~~
base:
  p6spy:
    # 为true，则在打印sql的时候，同时打印出url连接信息，推荐在多数据源的情况下开启，默认关闭
    show-connection-url: true
~~~
~~~
[2022-10-14 17:46:35.847] [app] [node01] [INFO] [p6spy:60  ] - -------------------------------------sql execution information-------------------------------------
sql         : SELECT id,name,username,password,del_flag,create_time,update_time,status FROM user WHERE id=1 
category    : statement
connectionId: 20
took        : 2
url         : jdbc:mysql://10.100.15.50:3306/app_skeleton?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false
~~~