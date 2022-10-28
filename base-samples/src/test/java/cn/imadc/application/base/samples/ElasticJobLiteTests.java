//package cn.imadc.application.base.samples;
//
//import org.apache.shardingsphere.elasticjob.api.JobConfiguration;
//import org.apache.shardingsphere.elasticjob.api.ShardingContext;
//import org.apache.shardingsphere.elasticjob.lite.api.bootstrap.impl.ScheduleJobBootstrap;
//import org.apache.shardingsphere.elasticjob.reg.base.CoordinatorRegistryCenter;
//import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperConfiguration;
//import org.apache.shardingsphere.elasticjob.reg.zookeeper.ZookeeperRegistryCenter;
//import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;
//import org.junit.Test;
//
///**
// * <p>
// *
// * </p>
// *
// * @author 杜劲松
// * @since 2022-07-27
// */
//public class ElasticJobLiteTests {
//
//    @Test
//    public void testJob() {
//        new ScheduleJobBootstrap(createRegistryCenter(), new MyJob(), createJobConfiguration()).schedule();
//    }
//
//    private static CoordinatorRegistryCenter createRegistryCenter() {
//        ZookeeperConfiguration zookeeperConfiguration = new ZookeeperConfiguration("10.100.14.89:2181", "my-job");
//        zookeeperConfiguration.setMaxSleepTimeMilliseconds(6000);
//        CoordinatorRegistryCenter regCenter = new ZookeeperRegistryCenter(zookeeperConfiguration);
//        regCenter.init();
//        return regCenter;
//    }
//
//    private static JobConfiguration createJobConfiguration() {
//        // 创建作业配置
//        return JobConfiguration.newBuilder("MyJob", 3)
//                .cron("0/5 * * * * ?")
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
//}
