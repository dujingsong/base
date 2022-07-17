package cn.imadc.application.base.data.structure.rocketmq;

import cn.imadc.application.base.common.action.IEnumAble;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * rocketMQ节点类型
 * </p>
 *
 * @author 杜劲松
 * @since 2022-05-31
 */
@Getter
@AllArgsConstructor
public enum RocketMQNode implements IEnumAble {

    MASTER(0, "主节点"),
    SLAVE(1, "从节点"),
    NAMESRV(2, "namesrv节点"),
    ;

    private final int value;
    private final String desc;

    @Override
    public String v() {
        return this.name();
    }

    public static RocketMQNode of(int value) {
        return RocketMQNode.values()[value];
    }
}
