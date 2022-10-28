# 优雅关机模块

## 优雅关机模块

### usage

引入依赖
~~~
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-graceful-shutdown</artifactId>
</dependency>
~~~

#### 启用rocketmq的优雅关机

~~~
base:
  graceful:
    shutdown:
      rocketmq: true                      # 启用rocketmq的优雅关机
      wait-rocketmq-complete-time: 30s    # 最大等待时间默认30s
~~~


