package cn.imadc.application.base.data.structure;

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

    DEFAULT,
    SENTINEL,
    ;

    @Override
    public String v() {
        return this.name();
    }
}
