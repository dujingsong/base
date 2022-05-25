package cn.imadc.application.base.common.search;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Map;

@Getter
@Setter
public class BaseSearchDTO implements Serializable {
    public static final long serialVersionUID = -1L;

    private Integer pageNo;
    private Integer pageSize;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate startDateTime;
    private LocalDate endDateTime;
    private String keywords;

    private Map<String, String> extra;

    public boolean pageQuery() {
        return null != pageNo && null != pageSize;
    }
}
