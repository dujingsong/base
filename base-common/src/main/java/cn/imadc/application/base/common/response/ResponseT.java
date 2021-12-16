package cn.imadc.application.base.common.response;

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
}
