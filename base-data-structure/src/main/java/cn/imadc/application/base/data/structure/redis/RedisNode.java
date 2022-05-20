package cn.imadc.application.base.data.structure.redis;

import cn.imadc.application.base.common.action.IEnumAble;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-03-05
 */
@Getter
@AllArgsConstructor
public enum RedisNode implements IEnumAble {

    MASTER(0, "主节点"),
    SLAVE(1, "从节点"),
    SENTINEL(2, "哨兵节点"),
    ;

    private final int value;
    private final String desc;

    @Override
    public String v() {
        return this.name();
    }

    public static RedisNode of(int value) {
        return RedisNode.values()[value];
    }
}
