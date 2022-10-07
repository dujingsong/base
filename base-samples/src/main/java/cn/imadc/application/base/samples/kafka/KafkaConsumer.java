package cn.imadc.application.base.samples.kafka;

import cn.imadc.application.base.kafka.support.AbstractKafkaConsumer;
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
@Component
@KafkaListener(topics = "TOPIC_BA_KAFKA_CLUSTER00_DEV")
public class KafkaConsumer extends AbstractKafkaConsumer {

    @Override
    public void consume(String message) {
        System.out.println(message);
    }
}
