package cn.imadc.application.base.common.search;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * <p>
 * 查询参数基类
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
@Getter
@Setter
public class BaseSearchDTO implements Serializable {
    public static final long serialVersionUID = -1L;

    private Integer pageNo;
    private Integer pageSize;

    private LocalDate startDate;
    private LocalDate endDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startDateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(shape = JsonFormat.Shape.STRING, locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endDateTime;
    private String keywords;

    private Map<String, String> extra;

    public boolean pageQuery() {
        return null != pageNo && null != pageSize;
    }
}
