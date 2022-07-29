package cn.imadc.application.base.rocketmq.core;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 消息发送出参
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-29
 */
@Getter
@Setter
public class ProduceResult {

    /**
     * 发送结果
     */
    private ProduceStatus produceStatus;

}
