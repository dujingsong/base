package cn.imadc.application.base.rocketmq.support;

import cn.imadc.application.base.common.constant.BaseConstant;
import cn.imadc.application.base.rocketmq.core.AbstractProduceCallback;
import cn.imadc.application.base.rocketmq.core.ProduceResult;
import cn.imadc.application.base.rocketmq.core.ProduceStatus;
import cn.imadc.application.base.rocketmq.message.Message;
import cn.imadc.application.base.rocketmq.util.MessageUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.support.MessageBuilder;

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
@ConditionalOnProperty(prefix = "rocketmq", value = {"name-server", "producer.group"})
@AllArgsConstructor
public class RocketMQProducer {

    private final RocketMQTemplate rocketMQTemplate;

    /**
     * 同步发送
     *
     * @param message 消息
     * @return 发送结果
     */
    public ProduceResult produce(Message message) {
        MessageUtil.checkMessage(message);

        ProduceResult produceResult = new ProduceResult();

        String destination = message.getTopic();
        if (StringUtils.isNotEmpty(message.getTags())) {
            destination = destination + BaseConstant.COLON + message.getTags();
        }

        try {

            MessageBuilder<?> messageBuilder = MessageBuilder.withPayload(message);

            if (StringUtils.isNotEmpty(message.getKey())) {
                messageBuilder.setHeader(RocketMQHeaders.KEYS, message.getKey());
            }

            SendResult sendResult = rocketMQTemplate.syncSend(destination, messageBuilder.build());
            SendStatus sendStatus = sendResult.getSendStatus();

            if (!sendStatus.equals(SendStatus.SEND_OK)) {
                log.warn("send message to rocketmq ok, but something wrong. sendStatus:{}, message:{}"
                        , sendStatus
                        , message
                );
            }
            produceResult.setProduceStatus(ProduceStatus.SUCCESS);

        } catch (MessagingException messagingException) {
            produceResult.setProduceStatus(ProduceStatus.FAIL);
            log.error("send message to rocketmq fail. message:{}", message, messagingException);
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

        String destination = message.getTopic();
        if (StringUtils.isNotEmpty(message.getTags())) {
            destination = destination + BaseConstant.COLON + message.getTags();
        }

        MessageBuilder<?> messageBuilder = MessageBuilder.withPayload(message);

        if (StringUtils.isNotEmpty(message.getKey())) {
            messageBuilder.setHeader(RocketMQHeaders.KEYS, message.getKey());
        }

        rocketMQTemplate.asyncSend(destination, messageBuilder.build(), produceCallback);
    }


}
