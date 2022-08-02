package cn.imadc.application.base.elastic.job;

import org.apache.shardingsphere.elasticjob.api.ShardingContext;
import org.apache.shardingsphere.elasticjob.simple.job.SimpleJob;

/**
 * <p>
 * SimpleJob逻辑，具体任务不直接实现SimpleJob，增加一层拦截，方便后续做其它的处理
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-28
 */
public abstract class AbstractSimpleJob implements SimpleJob {

    @Override
    public void execute(ShardingContext shardingContext) {
        this.executeJob(shardingContext);
    }

    /**
     * 执行任务
     *
     * @param shardingContext 任务分片相关信息
     */
    protected abstract void executeJob(ShardingContext shardingContext);
}
