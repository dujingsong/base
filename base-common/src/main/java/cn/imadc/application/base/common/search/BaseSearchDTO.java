package cn.imadc.application.base.common.search;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Setter
public class BaseSearchDTO implements Serializable {
    public static final long serialVersionUID = -1L;

    private Integer pageNo;
    private Integer pageSize;

    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private String keywords;

    private Map<String, String> extra;

    public boolean pageQuery() {
        return null != pageNo && null != pageSize;
    }
}
