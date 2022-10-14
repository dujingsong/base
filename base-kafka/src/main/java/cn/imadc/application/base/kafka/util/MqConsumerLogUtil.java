package cn.imadc.application.base.kafka.util;

import cn.imadc.application.base.kafka.message.Message;
import cn.imadc.application.base.kafka.support.AbstractKafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 消费者日志工具类
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-29
 */
public class MqConsumerLogUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractKafkaConsumer.class);

    public static void info(Message message) {
        LOGGER.info("-=-=-= [Consume Message] Consume successfully -=-=-= \n Topic            = " + message.getTopic() + "\n" + " Content          = " + message.getContent() + "\n");
    }

    public static void info(String message) {
        LOGGER.info("-=-=-= [Consume Message] Consume successfully -=-=-= \n message            = " + message + "\n");
    }

    public static void error(Message message) {
        LOGGER.error("-=-=-= [Consume Message] Consume failed -=-=-= \n Topic            = " + message.getTopic() + "\n" + " Content          = " + message.getContent() + "\n");
    }

    public static void error(String message) {
        LOGGER.error("-=-=-= [Consume Message] Consume failed -=-=-= \n Topic            = " + message + "\n");
    }
}
