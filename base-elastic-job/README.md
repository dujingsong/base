# elastic-job模块

## 分布式定时任务组件，采用elasticjob作为依赖实现

### usage

引入依赖
~~~
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-elastic-job</artifactId>
</dependency>
~~~
如果需要使用到任务执行记录上报跟踪到mysql，需要引入mysql驱动
~~~
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
~~~
yml配置示例
~~~
# elasticjob配置
# https://shardingsphere.apache.org/elasticjob/current/cn/user-manual/elasticjob-lite/configuration/spring-boot-starter/
elasticjob:
  # 注册中心配置
  reg-center:
    # host1:2181,host2:2181
    serverLists: host1:2181
    namespace: BaseSamplesApplicationTests
    baseSleepTimeMilliseconds: 1000
    maxSleepTimeMilliseconds: 6000
    maxRetries: 3
    sessionTimeoutMilliseconds: 60000
    connectionTimeoutMilliseconds: 15000
    # 连接 ZooKeeper 的权限令牌
    digest:
  # 任务执行记录跟踪配置
  tracing:
    type: RDB
    dataSource:
      driverClassName: com.mysql.cj.jdbc.Driver
      url: jdbc:mysql://ip:3306/job_event_storage?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false
      username: username
      password: password
    includeJobNames:
    excludeJobNames:
  # 任务定义
  jobs:
    MySimpleJob:
      elasticJobClass: cn.imadc.application.base.samples.elasticjob.MySimpleJob
      cron: 0/5 * * * * ?
      timeZone: GMT+08:00
      sharding-total-count: 3
      sharding-item-parameters: "0=Beijing,1=Shanghai,2=Guangzhou"
      failover: false
      misfire: true
      overwrite: true
    MySimpleJob1:
      elasticJobClass: cn.imadc.application.base.samples.elasticjob.MySimpleJob1
      cron: 0/5 * * * * ?
      timeZone: GMT+08:00
      sharding-total-count: 3
      sharding-item-parameters: "0=Beijing,1=Shanghai,2=Guangzhou"
      failover: false
      misfire: true
      jobParameter: "12312"
      overwrite: true
~~~
simple类型任务java类示例
~~~
import cn.imadc.application.base.elastic.job.AbstractSimpleJob;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.springframework.stereotype.Component;

@Component
public class MySimpleJob extends AbstractSimpleJob {

    @Override
    protected void executeJob(ShardingContext shardingContext) {
        System.out.println(shardingContext.getShardingItem());
    }
}
~~~



