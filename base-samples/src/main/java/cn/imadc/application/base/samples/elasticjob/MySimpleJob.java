package cn.imadc.application.base.samples.elasticjob;

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