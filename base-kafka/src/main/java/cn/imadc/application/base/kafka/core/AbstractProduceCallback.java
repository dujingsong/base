package cn.imadc.application.base.kafka.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFutureCallback;

/**
 * <p>
 * 发送回调
 * </p>
 *
 * @author 杜劲松
 * @since 2022-10-06
 */
@Slf4j
public abstract class AbstractProduceCallback implements ListenableFutureCallback<SendResult<String, String>> {

    @Override
    public void onFailure(Throwable ex) {
        log.error("async send message to kafka fail.", ex);
        ProduceResult produceResult = new ProduceResult();
        produceResult.setProduceStatus(ProduceStatus.FAIL);
        this.onProduceResult(produceResult);
    }

    @Override
    public void onSuccess(SendResult<String, String> result) {
        RecordMetadata recordMetadata = result.getRecordMetadata();
        ProduceResult produceResult = new ProduceResult();
        long offset = recordMetadata.offset();
        log.debug("async send message to kafka success. offset:{}", offset);
        produceResult.setProduceStatus(ProduceStatus.SUCCESS);
        this.onProduceResult(produceResult);
    }

    protected abstract void onProduceResult(ProduceResult produceResult);
}
