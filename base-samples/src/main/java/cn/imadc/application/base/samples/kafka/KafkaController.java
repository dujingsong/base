//package cn.imadc.application.base.samples.kafka;
//
//import cn.imadc.application.base.kafka.core.AbstractProduceCallback;
//import cn.imadc.application.base.kafka.core.ProduceResult;
//import cn.imadc.application.base.kafka.message.Message;
//import cn.imadc.application.base.kafka.support.KafkaProducer;
//import com.alibaba.fastjson.JSONObject;
//import lombok.AllArgsConstructor;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * <p>
// *
// * </p>
// *
// * @author 杜劲松
// * @since 2022-09-30
// */
//@AllArgsConstructor
//@RestController
//@RequestMapping("kafka")
//public class KafkaController {
//
//    private final KafkaTemplate<String, String> kafkaTemplate;
//    private final KafkaProducer kafkaProducer;
//
//    // https://docs.spring.io/spring-kafka/docs/current/reference/html/#examples
//    @RequestMapping(value = "send", method = RequestMethod.GET)
//    public void send() {
//        Message message = buildMessage();
//        ProduceResult produceResult = kafkaProducer.produce(message);
//        System.out.println("同步发送结果：" + produceResult.getProduceStatus().toString());
//    }
//
//    private Message buildMessage() {
//        Message message = new Message();
//        message.setTopic("TOPIC_BA_KAFKA_CLUSTER_DEV");
//        message.setKey("KEY_" + System.currentTimeMillis());
//        JSONObject content = new JSONObject();
//        content.put("timestamp", System.currentTimeMillis());
//        content.put("orderNo", "orderNo_" + System.currentTimeMillis());
//        message.setContent(content.toJSONString());
//        return message;
//    }
//
//    @RequestMapping(value = "asyncSend", method = RequestMethod.GET)
//    public void asyncSend() {
//        Message message = buildMessage();
//        kafkaProducer.produce(message, new AbstractProduceCallback() {
//            @Override
//            protected void onProduceResult(ProduceResult produceResult) {
//                System.out.println("异步发送结果：" + produceResult.getProduceStatus().toString());
//            }
//        });
//    }
//}
