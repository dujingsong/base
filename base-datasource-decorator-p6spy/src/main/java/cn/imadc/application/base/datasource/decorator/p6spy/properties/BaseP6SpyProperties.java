package cn.imadc.application.base.datasource.decorator.p6spy.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * <p>
 * P6Spy的配置参数
 * </p>
 *
 * @author 杜劲松
 * @since 2022-10-14
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "base.p6spy")
public class BaseP6SpyProperties {

    /**
     * 打印sql的时候是否展示连接信息，多数据源情况下可开启
     */
    private Boolean showConnectionUrl = false;
    private static Boolean showConnUrl = false;

    @PostConstruct
    public void postConstruct() {
        // https://www.jianshu.com/p/3f9dfee94a04
        showConnUrl = showConnectionUrl;
    }

    public static Boolean getShowConnectionUrl() {
        return showConnUrl;
    }
}
