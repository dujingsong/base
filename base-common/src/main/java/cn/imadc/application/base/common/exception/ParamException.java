package cn.imadc.application.base.common.exception;

import lombok.Getter;

/**
 * <p>
 * 参数校验异常
 * </p>
 *
 * @author 杜劲松
 * @since 2023-05-10
 */
@Getter
public class ParamException extends RuntimeException {
    static final long serialVersionUID = -1L;

    private Integer code;

    public ParamException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ParamException(Integer code) {
        this.code = code;
    }

    public ParamException(String message) {
        super(message);
    }
}
