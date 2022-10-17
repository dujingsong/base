//package cn.imadc.application.base.samples;
//
//import com.mysql.cj.jdbc.MysqlDataSource;
//import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
//import org.apache.shardingsphere.elasticjob.api.ShardingContext;
//import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
//import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
//import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
//import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
//import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
//import org.apache.shardingsphere.elasticjob.tracing.api.TracingConfiguration;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import javax.sql.DataSource;
//
//@SpringBootTest
//class BaseSamplesApplicationTests {
//
//    @Test
//    void contextLoads() {
//
//        new ScheduleJobBootstrap(createRegistryCenter(), new MyJob(), createJobConfiguration()).schedule();
//        while (true) {
//        }
//    }
//
//    private static CoordinatorRegistryCenter createRegistryCenter() {
//        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration("10.100.14.89:2181", "BaseSamplesApplicationTests");
//        zookeeperConfiguration.setMaxSleepTimeMilliseconds(6000);
//        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
//        regCenter.init();
//        return regCenter;
//    }
//
//    private static JobConfiguration createJobConfiguration() {
//        DataSource dataSource = DataSourceBuilder.create()
//                .driverClassName("com.mysql.cj.jdbc.Driver")
//                .url("jdbc:mysql://10.100.15.50:3306/job_event_storage?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai&useSSL=false")
//                .username("root")
//                .password("xsmysql")
//                .type(MysqlDataSource.class)
//                .build();
//        TracingConfiguration tracingConfiguration = new TracingConfiguration("RDB", dataSource);
//
//        // 创建作业配置
//        return JobConfiguration.newBuilder("MyJob", 3)
//                .cron("0/5 * * * * ?")
//                .addExtraConfigurations(tracingConfiguration)
//                .build();
//    }
//
//    class MyJob implements SimpleJob {
//
//        @Override
//        public void execute(ShardingContext context) {
//            switch (context.getShardingItem()) {
//                case 0:
//                    // do something by sharding item 0
//                    System.out.println(0);
//                    break;
//                case 1:
//                    // do something by sharding item 1
//                    System.out.println(1);
//                    break;
//                case 2:
//                    // do something by sharding item 2
//                    System.out.println(2);
//                    break;
//                // case n: ...
//            }
//        }
//    }
//
//}
