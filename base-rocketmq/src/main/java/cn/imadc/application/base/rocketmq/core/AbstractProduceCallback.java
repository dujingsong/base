package cn.imadc.application.base.rocketmq.core;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-29
 */
public abstract class AbstractProduceCallback implements SendCallback {
    @Override
    public void onSuccess(SendResult sendResult) {
        ProduceResult produceResult = new ProduceResult();
        produceResult.setProduceStatus(ProduceStatus.SUCCESS);
        this.onProduceResult(produceResult);
    }

    @Override
    public void onException(Throwable e) {
        ProduceResult produceResult1 = new ProduceResult();
        produceResult1.setProduceStatus(ProduceStatus.FAIL);
        this.onProduceResult(produceResult1);
    }

    protected abstract void onProduceResult(ProduceResult produceResult);

}
