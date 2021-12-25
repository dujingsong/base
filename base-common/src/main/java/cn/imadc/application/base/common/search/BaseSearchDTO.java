package cn.imadc.application.base.common.search;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class BaseSearchDTO implements Serializable {
    public static final long serialVersionUID = -1L;

    private Integer pageNo;
    private Integer pageSize;
    private Map<String, String> extra;
    private String keywords;

    public boolean pageQuery() {
        return null != pageNo && null != pageSize;
    }
}
