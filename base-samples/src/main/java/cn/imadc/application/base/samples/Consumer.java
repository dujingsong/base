package cn.imadc.application.base.samples;

import cn.imadc.application.base.rocketmq.core.ConsumeResult;
import cn.imadc.application.base.rocketmq.message.Message;
import cn.imadc.application.base.rocketmq.support.AbstractRocketMQConsumer;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-29
 */
@RocketMQMessageListener(topic = "${rocketmq.consumer.topic-biz-a}", consumerGroup = "${rocketmq.consumer.consumer-biz-a}")
@Component
public class Consumer extends AbstractRocketMQConsumer {

    @Override
    protected ConsumeResult consume(Message message) {
        System.out.println(message.toString());
        return ConsumeResult.success();
    }
}
