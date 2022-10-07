package cn.imadc.application.base.kafka.support;

import org.springframework.kafka.annotation.KafkaHandler;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-10-06
 */
public abstract class AbstractKafkaConsumer {

    @KafkaHandler
    public void onMessage(String message) {
        this.consume(message);
    }

    public abstract void consume(String message);


}
