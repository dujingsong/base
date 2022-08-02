package cn.imadc.application.base.samples;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-08-01
 */
@Getter
@Setter
public class User {

    private Long id;

    private String name;
    private String username = UUID.randomUUID().toString().substring(0, 6);
    private String password = UUID.randomUUID().toString().substring(0, 6);
    private Integer delFlag = 0;
    private LocalDateTime createTime = LocalDateTime.now();
    private LocalDateTime updateTime = LocalDateTime.now();
    private Integer status = 1;

}
