package cn.imadc.application.base.kafka.util;

import cn.imadc.application.base.kafka.message.Message;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 * 生产者日志工具类
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-29
 */
@Slf4j
public class MqProducerLogUtil {


    public static void asyncInfo(Message message) {
        log.info("-=-=-= [Async Sending Message] -=-=-= \n Topic                = " + message.getTopic() + "\n" + " key            = " + message.getKey() + "\n" + " Content            = " + message.getContent() + "\n");
    }

    public static void info(Message message) {
        log.info("-=-=-= [Send Message] Send successfully -=-=-= \n Topic                = " + message.getTopic() + "\n" + " key            = " + message.getKey() + "\n" + " Content            = " + message.getContent() + "\n");
    }

    public static void error(Message message) {
        log.error("-=-=-= [Send Message] Send failed -=-=-= \n Topic                = " + message.getTopic() + "\n" + " key            = " + message.getKey() + "\n" + " Content            = " + message.getContent() + "\n");
    }
}
