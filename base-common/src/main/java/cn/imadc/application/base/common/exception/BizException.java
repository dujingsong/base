package cn.imadc.application.base.common.exception;

import lombok.Getter;

import java.io.Serializable;

/**
 * <p>
 * 业务异常
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
@Getter
public class BizException extends RuntimeException implements Serializable {
    static final long serialVersionUID = -1L;

    private Integer code;

    public BizException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BizException(Integer code) {
        this.code = code;
    }

    public BizException(String message) {
        super(message);
    }
}
