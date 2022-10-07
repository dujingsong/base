package cn.imadc.application.base.kafka.support;

import cn.imadc.application.base.kafka.core.AbstractProduceCallback;
import cn.imadc.application.base.kafka.core.ProduceResult;
import cn.imadc.application.base.kafka.core.ProduceStatus;
import cn.imadc.application.base.kafka.message.Message;
import cn.imadc.application.base.kafka.util.MessageUtil;
import cn.imadc.application.base.toolkit.serialization.JsonUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.messaging.MessagingException;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * <p>
 * 消息生产者
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-29
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    /**
     * 同步发送
     *
     * @param message 消息
     * @return 发送结果
     */
    public ProduceResult produce(Message message) {
        MessageUtil.checkMessage(message);

        ProduceResult produceResult = new ProduceResult();

        try {

            String content = JsonUtil.objectToJson(message);

            ListenableFuture<SendResult<String, String>> listenableFuture
                    = kafkaTemplate.send(message.getTopic(), message.getKey(), content);

            SendResult<String, String> sendResult = listenableFuture.get();

            RecordMetadata recordMetadata = sendResult.getRecordMetadata();
            long offset = recordMetadata.offset();
            log.debug("send message to kafka success. offset:{}", offset);

            produceResult.setProduceStatus(ProduceStatus.SUCCESS);

        } catch (MessagingException | ExecutionException | InterruptedException messagingException) {
            produceResult.setProduceStatus(ProduceStatus.FAIL);
            log.error("send message to kafka fail. message:{}", message, messagingException);
        }

        return produceResult;
    }

    /**
     * 异步发送
     *
     * @param message         消息
     * @param produceCallback 发送回调
     */
    public void produce(Message message, AbstractProduceCallback produceCallback) {
        MessageUtil.checkMessage(message);

        String content = JsonUtil.objectToJson(message);

        ListenableFuture<SendResult<String, String>> listenableFuture
                = kafkaTemplate.send(message.getTopic(), message.getKey(), content);

        listenableFuture.addCallback(produceCallback);

    }


}
