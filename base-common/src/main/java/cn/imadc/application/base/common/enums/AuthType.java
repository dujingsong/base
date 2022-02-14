package cn.imadc.application.base.common.enums;

import cn.imadc.application.base.common.action.IEnumAble;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <p>
 * 鉴权类型
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-24
 */
@Getter
@AllArgsConstructor
public enum AuthType implements IEnumAble {

    ANONYMOUS(0, "匿名访问"),          // 匿名访问
    LOGIN(1, "登录之后访问"),           // 登录之后访问
    AUTHORIZED(2, "授权后访问"),       // 授权后访问
    ;

    private final int value;
    private final String description;

    @Override
    public String v() {
        return this.toString();
    }
}
