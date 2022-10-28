package cn.imadc.application.base.kafka.support;


import cn.hutool.core.exceptions.ExceptionUtil;
import cn.imadc.application.base.common.exception.BizException;
import cn.imadc.application.base.kafka.core.ConsumeStatus;
import cn.imadc.application.base.kafka.util.MqConsumerLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.support.Acknowledgment;

/**
 * <p>
 * 消费者顶级抽象类，消息体是String类型的原文
 * </p>
 *
 * @author 杜劲松
 * @since 2022-10-06
 */
public abstract class AbstractOriginalMessageConsumer {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @KafkaHandler
    public void onMessage(String messageStr, Acknowledgment acknowledgment) {
        try {
            ConsumeStatus result = consume(messageStr);
            if (!ConsumeStatus.SUCCEED.v().equals(result.v())) {
                MqConsumerLogUtil.error(messageStr);
            } else {
                MqConsumerLogUtil.info(messageStr);
                acknowledgment.acknowledge();
            }
        } catch (BizException e) {
            logger.error("message: {} , consume failed , errorCode is {} , errorMsg is {}.", messageStr, e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error("message: {} , consume failed , {}", messageStr, ExceptionUtil.stacktraceToOneLineString(e, -1));
        }
    }

    /**
     * 消费消息内容
     *
     * @param message
     * @return
     */
    public abstract ConsumeStatus consume(String message);
}
