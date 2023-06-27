package cn.imadc.application.base.common.response;

import cn.imadc.application.base.common.code.BaseResponseCode;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * <p>
 * 响应体基类
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
@Getter
@Setter
public class ResponseW implements Serializable {
    private static final long serialVersionUID = 1L;

    private String code;
    private String msg;
    private Object body;

    public ResponseW(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseW(String code) {
        this.code = code;
    }

    public ResponseW(String code, Object body) {
        this.code = code;
        this.body = body;
    }

    public ResponseW(String code, String msg, Object body) {
        this.code = code;
        this.msg = msg;
        this.body = body;
    }

    public static ResponseW success() {
        return new ResponseW(BaseResponseCode.SUCCESS);
    }

    public static ResponseW error() {
        return new ResponseW(BaseResponseCode.ERROR);
    }

    public static ResponseW success(Object body) {
        return new ResponseW(BaseResponseCode.SUCCESS, body);
    }

    public static ResponseW error(String msg) {
        return new ResponseW(BaseResponseCode.ERROR, msg);
    }

    public static ResponseW fromT(ResponseT<?> responseT) {
        return new ResponseW(responseT.getCode(), responseT.getMsg(), responseT.getBody());
    }

    public boolean isSuccess() {
        return StringUtils.equals(this.code, BaseResponseCode.SUCCESS);
    }
}
