package cn.imadc.application.base.samples.elasticjob;

import cn.imadc.application.base.elastic.job.AbstractSimpleJob;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MySimpleJob1 extends AbstractSimpleJob {

    @Override
    protected void executeJob(ShardingContext shardingContext) {
        log.info(String.valueOf(shardingContext.getShardingItem()));
    }
}