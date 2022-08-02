package cn.imadc.application.base.rocketmq.support;

import cn.imadc.application.base.common.exception.BizException;
import cn.imadc.application.base.rocketmq.core.ConsumeResult;
import cn.imadc.application.base.rocketmq.core.ConsumeStatus;
import cn.imadc.application.base.rocketmq.message.Message;
import cn.imadc.application.base.toolkit.serialization.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;

/**
 * <p>
 * 消费者
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-29
 */
@Slf4j
@Configuration
public abstract class AbstractRocketMQConsumer implements RocketMQListener<MessageExt> {

    @Override
    public void onMessage(MessageExt messageExt) {

        String messageBodyStr = new String(messageExt.getBody(), StandardCharsets.UTF_8);

        try {

            Message message = JsonUtil.jsonToObject(messageBodyStr, Message.class);

            ConsumeResult consumeResult = this.consume(message);
            ConsumeStatus consumeStatus = consumeResult.getConsumeStatus();

            if (!consumeStatus.equals(ConsumeStatus.SUCCESS)) {
                throw new BizException(consumeResult.getMessage());
            }

        } catch (Exception e) {
            log.error("consume message fail, message:{}", messageExt, e);
            throw e;
        }

    }

    /**
     * 消费
     *
     * @param message 消息
     * @return 消费结果
     */
    protected abstract ConsumeResult consume(Message message);
}
