package cn.imadc.application.base.common.response;

import cn.imadc.application.base.common.code.ResponseCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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

    public static ResponseW success() {
        return new ResponseW(ResponseCode.SUCCESS);
    }

    public static ResponseW error() {
        return new ResponseW(ResponseCode.ERROR);
    }

    public static ResponseW success(Object body) {
        return new ResponseW(ResponseCode.SUCCESS, body);
    }

    public static ResponseW error(String msg) {
        return new ResponseW(ResponseCode.ERROR, msg);
    }
}
