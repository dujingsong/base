package cn.imadc.application.base.rocketmq.util;

import cn.imadc.application.base.rocketmq.message.Message;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * <p>
 * 消息工具类
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-29
 */
public class MessageUtil {

    /**
     * 校验消息
     *
     * @param message 消息
     */
    public static void checkMessage(Message message) {

        if (StringUtils.isEmpty(message.getTopic())) {
            throw new IllegalArgumentException("message topic can not be null");
        }

        if (StringUtils.isEmpty(message.getProducerGroup())) {
            throw new IllegalArgumentException("message producerGroup can not be null");
        }

        if (StringUtils.isEmpty(message.getContent())) {
            throw new IllegalArgumentException("message content can not be null");
        }

        if (!Pattern.matches("^[A-Z]+[A-Z_]+[A-Z]+$", message.getTopic())) {
            throw new IllegalArgumentException("message topic invalid");
        }

        if (!message.getTopic().startsWith("TOPIC_")) {
            throw new IllegalArgumentException("message topic should be stat with TOPIC_");
        }

        if (!Pattern.matches("^[A-Z]+[A-Z_]+[A-Z]+$", message.getProducerGroup())) {
            throw new IllegalArgumentException("message producerGroup invalid");
        }

        if (!message.getProducerGroup().startsWith("PID_")) {
            throw new IllegalArgumentException("message producerGroup should be stat with PID_");
        }

        if (StringUtils.isEmpty(message.getKey())) {
            throw new IllegalArgumentException("message key can not be null");
        }

    }
}
