package cn.imadc.application.base.samples;

import cn.imadc.application.base.rocketmq.core.AbstractProduceCallback;
import cn.imadc.application.base.rocketmq.core.ProduceResult;
import cn.imadc.application.base.rocketmq.message.Message;
import cn.imadc.application.base.rocketmq.support.RocketMQProducer;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-29
 */
@AllArgsConstructor
@Component
public class Producer {

    private final RocketMQProducer rocketMQProducer;

    public void send() {
        Message message = new Message();
        message.setTopic("TOPIC_BA_DEMO_DEV");
        message.setTags("TAG_BA_DEMO_DEV");
        message.setProducerGroup("PID_BASE_SAMPLES_DEV");
        message.setKey(UUID.randomUUID().toString().replaceAll("-", ""));
        message.setContent("sadsadsasdaassd");

        ProduceResult messageProduceResult = rocketMQProducer.produce(message);
        System.out.println(messageProduceResult.getProduceStatus().toString());

        rocketMQProducer.produce(message, new AbstractProduceCallback() {
            @Override
            protected void onProduceResult(ProduceResult messageProduceResult) {
                System.out.println(messageProduceResult.getProduceStatus().toString());
            }
        });
    }

}
