package cn.imadc.application.base.common.response;

import cn.imadc.application.base.common.code.ResponseCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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

    public static <T> ResponseT<T> success(T body) {
        return new ResponseT<T>(ResponseCode.SUCCESS, body);
    }

    public static <T> ResponseT<T> error(String msg) {
        return new ResponseT<T>(ResponseCode.ERROR, msg);
    }
}
