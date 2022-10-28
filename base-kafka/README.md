# kafka消息生产与消费模块

## 消息生产与消费组件，采用spring-kafka作为依赖实现

### usage

引入依赖

~~~
<dependency>
    <groupId>cn.imadc.application</groupId>
    <artifactId>base-kafka</artifactId>
</dependency>
~~~

生产者yml配置示例

~~~
# 生产者
base:
  kafka:
    bootstrap-servers:
      - host:port
      - host:port
      - host:port
    username: 用户名
    password: 密码
~~~

生产者java类示例

~~~
    private final KafkaProducer kafkaProducer;

    // https://docs.spring.io/spring-kafka/docs/current/reference/html/#examples
    @RequestMapping(value = "send", method = RequestMethod.GET)
    public void send() {
        Message message = buildMessage();
        ProduceResult produceResult = kafkaProducer.produce(message);
        System.out.println("同步发送结果：" + produceResult.getProduceStatus().toString());
    }

    private Message buildMessage() {
        Message message = new Message();
        message.setTopic("TOPIC_TEST");
        message.setKey("KEY_" + System.currentTimeMillis());
        JSONObject content = new JSONObject();
        content.put("timestamp", System.currentTimeMillis());
        content.put("orderNo", "orderNo_" + System.currentTimeMillis());
        message.setContent(content.toJSONString());
        return message;
    }

    @RequestMapping(value = "asyncSend", method = RequestMethod.GET)
    public void asyncSend() {
        Message message = buildMessage();
        kafkaProducer.produce(message, new AbstractProduceCallback() {
            @Override
            protected void onProduceResult(ProduceResult produceResult) {
                System.out.println("异步发送结果：" + produceResult.getProduceStatus().toString());
            }
        });
    }
~~~

消费者yml配置示例

~~~
# 消费者
base:
  kafka:
    bootstrap-servers:
      - host:port
      - host:port
      - host:port
    username: 用户名
    password: 密码
~~~

消费者java代码示例

~~~
@Slf4j
@Component
@KafkaListener(topics = "TOPIC_TEST", groupId = "CID_TEST")
public class KafkaConsumer extends AbstractKafkaConsumer {

    @Override
    public ConsumeStatus consume(Message message) {
        try {
            // do sth
        } catch (Exception exception) {
            // 日志、落库...
            log.error("consume exception, message：{}", message, exception);
        }
        return ConsumeStatus.SUCCEED;
    }
}
~~~


