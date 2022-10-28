package cn.imadc.application.base.graceful.shutdown.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.time.Duration;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-08-05
 */
@ConfigurationProperties(prefix = "base.graceful.shutdown")
@Getter
@Setter
public class BaseGracefulShutdownProperties {

    // --- rocketmq 优雅关机相关 ---

    /**
     * true: if you want to active GracefulShutdownRocketMQ. The default value could be false to keep the same behavior.
     */
    private Boolean rocketmq = false;

    /**
     * Time to wait rocketmq shutdown.
     */
    private Duration waitRocketmqCompleteTime = Duration.ofSeconds(30);


    // --- tomcat 优雅关机相关 ---

}
