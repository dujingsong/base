# rocketmq消息生产与消费模块

## 消息生产与消费组件，采用rocketmq作为依赖实现

### usage

引入依赖

~~~
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-rocketmq</artifactId>
</dependency>
~~~

生产者yml配置示例

~~~
# rocketmq生产者
rocketmq:
  # host:port;host:port
  name-server: host:port;host:port
  producer:
    group: PID_BASE_SAMPLES_DEV
    sendMessageTimeout: 3000
    retryTimesWhenSendFailed: 2
    retryTimesWhenSendAsyncFailed: 2
    retryNextServer: false
    access-key: accessKey
    secret-key: secretKey
    enableMsgTrace: true
~~~

生产者java类示例

~~~
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

        MessageProduceResult produceResult = rocketMQProducer.produce(message);
        System.out.println(produceResult.getProduceResult().toString());

        rocketMQProducer.produce(message, new AbstractProduceCallback() {
            @Override
            protected void onProduceResult(MessageProduceResult produceResult) {
                System.out.println(produceResult.getProduceResult().toString());
            }
        });
    }

}
~~~

消费者yml配置示例

~~~
# rocketmq
rocketmq:
  # host:port;host:port
  name-server: host:port;host:port
  consumer:
    access-key: accessKey
    secret-key: secretKey
    topic-biz-a: TOPIC_BA_DEMO_DEV
    consumer-biz-a: CID_BA_DEMO_DEV
~~~

消费者java代码示例

~~~
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
~~~


