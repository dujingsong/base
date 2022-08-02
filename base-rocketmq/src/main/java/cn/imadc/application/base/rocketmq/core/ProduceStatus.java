package cn.imadc.application.base.rocketmq.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 消息发送状态
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-29
 */
@Getter
@AllArgsConstructor
public enum ProduceStatus {

    SUCCESS,
    FAIL,

}
