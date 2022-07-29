package cn.imadc.application.base.rocketmq.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 消息消费出参
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-29
 */
@AllArgsConstructor
@Getter
@Setter
public class ConsumeResult {

    /**
     * 消费结果
     */
    private ConsumeStatus consumeStatus;
    /**
     * 消费结果说明
     */
    private String message;

    public ConsumeResult(ConsumeStatus consumeStatus) {
        this.consumeStatus = consumeStatus;
    }

    public static ConsumeResult success() {
        return new ConsumeResult(ConsumeStatus.SUCCESS);
    }

    public static ConsumeResult fail(String message) {
        return new ConsumeResult(ConsumeStatus.FAIL, message);
    }
}
