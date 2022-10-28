package cn.imadc.application.base.kafka.support;

import cn.hutool.core.exceptions.ExceptionUtil;
import cn.imadc.application.base.common.exception.BizException;
import cn.imadc.application.base.kafka.core.ConsumeStatus;
import cn.imadc.application.base.kafka.message.Message;
import cn.imadc.application.base.kafka.util.MqConsumerLogUtil;
import cn.imadc.application.base.toolkit.serialization.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.support.Acknowledgment;

/**
 * <p>
 * 消费者顶级抽象类，消息体是Message
 * </p>
 *
 * @author 杜劲松
 * @since 2022-10-06
 */
@Slf4j
public abstract class AbstractKafkaConsumer {

    @KafkaHandler
    public void onMessage(String messageStr, Acknowledgment acknowledgment) {
        Message message = parseMessage(messageStr);
        try {
            ConsumeStatus result = consume(message);
            if (!ConsumeStatus.SUCCEED.v().equals(result.v())) {
                MqConsumerLogUtil.error(message);
            } else {
                MqConsumerLogUtil.info(message);
                acknowledgment.acknowledge();
            }
        } catch (BizException e) {
            log.error("message: {} , consume failed , errorCode is {} , errorMsg is {}.", message, e.getCode(), e.getMessage());
        } catch (Exception e) {
            log.error("message: {} , consume failed , {}", message, ExceptionUtil.stacktraceToOneLineString(e, -1));
        }
    }

    /**
     * 解析消息体
     *
     * @param value 消息原文
     * @return 消息体
     */
    private Message parseMessage(String value) {

        Message message = JsonUtil.jsonToObject(value, Message.class);

        return message;
    }

    public abstract ConsumeStatus consume(Message message);


}
