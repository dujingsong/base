# redis通信客户端模块

## redis通信客户端模块lettuce，采用lettuce作为依赖实现

### usage

引入依赖

~~~
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-lettuce</artifactId>
</dependency>
~~~

见cn.imadc.application.base.lettuce.RedisClient

#### 实例化一个客户端，将根据host+port作为唯一标识缓存
RedisClient.getClient(String host, int port, String password)

#### 获取一个哨兵节点的连接，将根据host+port作为唯一标识缓存
RedisClient.getSentinelConnection(String host, int port , String password)

#### 获取一个数据节点的连接，将根据host+port作为唯一标识缓存
RedisClient.getConnection(String host, int port, String password)

#### 获取一个数据节点可执行命令的连接管道，将根据host+port作为唯一标识缓存
RedisClient.getRedisCommands(String host, int port, String password)

#### 获取一个哨兵节点可执行命令的连接管道，将根据host+port作为唯一标识缓存
RedisClient.getRedisSentinelCommands(String host, int port, String password)

#### 获取一个哨兵节点可执行补充命令（getRedisSentinelCommands中没有的）的连接管道，将根据host+port作为唯一标识缓存
RedisClient.getRedisSentinelCommands(String host, int port, String password)

