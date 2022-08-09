package cn.imadc.application.base.graceful.shutdown.rocketmq;

import cn.imadc.application.base.graceful.shutdown.properties.BaseGracefulShutdownProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.spring.autoconfigure.ListenerContainerConfiguration;
import org.apache.rocketmq.spring.support.DefaultRocketMQListenerContainer;
import org.apache.rocketmq.spring.support.RocketMQListenerContainer;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

/**
 * <p>
 * rocketmq优雅关机
 * </p>
 *
 * @author 杜劲松
 * @since 2022-03-18
 */
@AllArgsConstructor
@Slf4j
@Order
@Component
@ConditionalOnClass(value = ListenerContainerConfiguration.class)
@EnableConfigurationProperties(BaseGracefulShutdownProperties.class)
public class GracefulShutdownRocketMQ implements ApplicationContextAware, CommandLineRunner {

    private final BaseGracefulShutdownProperties baseGracefulShutdownProperties;
    private ConfigurableApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (checkActiveStatus()) {
            log.info("GracefulShutdownRocketMQ: gracefulShutdownRocketMQ is active");
            this.applicationContext = (ConfigurableApplicationContext) applicationContext;
        } else {
            log.warn("GracefulShutdownRocketMQ: gracefulShutdownRocketMQ is not active");
        }
    }

    private boolean checkActiveStatus() {
        // https://github.com/apolloconfig/apollo/issues/903
        return null != baseGracefulShutdownProperties.getRocketmq() && baseGracefulShutdownProperties.getRocketmq();
    }

    @Override
    public void run(String... args) throws Exception {
        if (checkActiveStatus()) {
            log.info("GracefulShutdownRocketMQ: searching for all consumer instances");

            Map<String, RocketMQListenerContainer> beanMap
                    = this.applicationContext.getBeansOfType(RocketMQListenerContainer.class);

            log.info("GracefulShutdownRocketMQ: {} consumer instances searched", beanMap.size());

            beanMap.forEach(this::injectAwaitTerminationMillisWhenShutdown);
        }
    }

    private void injectAwaitTerminationMillisWhenShutdown(String containerName, RocketMQListenerContainer container) {
        DefaultRocketMQListenerContainer defaultRocketMQListenerContainer = (DefaultRocketMQListenerContainer) container;

        DefaultMQPushConsumer defaultMQPushConsumer = defaultRocketMQListenerContainer.getConsumer();
        Duration waitServiceOfflineCompleteTime = baseGracefulShutdownProperties.getWaitRocketmqCompleteTime();
        defaultMQPushConsumer.setAwaitTerminationMillisWhenShutdown(waitServiceOfflineCompleteTime.toMillis());

        String consumerGroup = defaultRocketMQListenerContainer.getConsumerGroup();
        log.info("GracefulShutdownRocketMQ: consumerGroup {} will be graceful shutdown", consumerGroup);
    }

}
