package cn.imadc.application.base.graceful.shutdown.properties;


import lombok.Getter;
import lombok.Setter;

import java.time.Duration;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-08-05
 */
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
    private Duration waitRocketmqCompleteTime;


    // --- tomcat 优雅关机相关 ---

}
