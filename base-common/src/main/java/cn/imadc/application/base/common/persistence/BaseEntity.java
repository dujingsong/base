package cn.imadc.application.base.common.persistence;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 实体基类
 * </p>
 *
 * @author 杜劲松
 * @since 2021-12-17
 */
@Getter
@Setter
public class BaseEntity implements Serializable {
    public static final long serialVersionUID = 1L;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 创建者
     */
    private String createBy;

    /**
     * 更新者
     */
    private String updateBy;

    /**
     * 创建者ID
     */
    private String createById;

    /**
     * 更新者ID
     */
    private String updateById;

    /**
     * 是否删除0：未删除；1：已删除
     */
    private Integer delFlag;
}
