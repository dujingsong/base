package cn.imadc.application.base.samples.kafka;

import cn.imadc.application.base.kafka.core.ConsumeStatus;
import cn.imadc.application.base.kafka.message.Message;
import cn.imadc.application.base.kafka.support.AbstractKafkaConsumer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-10-06
 */
@Slf4j
@Component
@KafkaListener(topics = "TOPIC_BA_KAFKA_CLUSTER00_DEV", groupId = "CID_TEST")
public class KafkaConsumer extends AbstractKafkaConsumer {

    @Override
    public ConsumeStatus consume(Message message) {
        try {
            // do sth
        } catch (Exception exception) {
            // 日志、落库...
            log.error("consume exception, message：{}", message, exception);
        }
        return ConsumeStatus.SUCCEED;
    }
}
