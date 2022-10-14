package cn.imadc.application.base.kafka.core;


import cn.imadc.application.base.common.action.IEnumAble;

/**
 * <p>
 * 消费状态
 * <p>
 *
 * @author 杜劲松
 * @since 2022-10-08
 */
public enum ConsumeStatus implements IEnumAble {

    SUCCEED("S", "消费成功"),
    FAILED("F", "消费失败");

    private String value;

    private String desc;

    ConsumeStatus(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    @Override
    public String v() {
        return this.value;
    }
}
