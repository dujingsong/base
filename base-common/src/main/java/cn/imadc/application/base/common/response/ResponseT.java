package cn.imadc.application.base.common.response;

import cn.imadc.application.base.common.code.BaseResponseCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.beans.Transient;
import java.io.Serializable;

/**
 * <p>
 * 带泛型的响应体基类
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
@Getter
@Setter
public class ResponseT<T extends Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String msg;
    private T body;

    public ResponseT(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseT(String code) {
        this.code = code;
    }

    public ResponseT(String code, T body) {
        this.code = code;
        this.body = body;
    }

    public static <T> ResponseT<T> success() {
        return new ResponseT<T>(BaseResponseCode.SUCCESS);
    }

    public static <T> ResponseT<T> success(T body) {
        return new ResponseT<T>(BaseResponseCode.SUCCESS, body);
    }

    public static <T> ResponseT<T> error(String msg) {
        return new ResponseT<T>(BaseResponseCode.ERROR, msg);
    }

    @Transient
    public boolean isSuccess() {
        return StringUtils.equals(this.code, BaseResponseCode.SUCCESS);
    }
}
