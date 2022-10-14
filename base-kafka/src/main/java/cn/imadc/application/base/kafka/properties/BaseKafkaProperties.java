package cn.imadc.application.base.kafka.properties;

import lombok.Getter;
import lombok.Setter;
import org.apache.kafka.clients.consumer.RoundRobinAssignor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.LogIfLevelEnabled;

import java.time.Duration;
import java.util.List;

/**
 * <p>
 * kafka配置信息
 * </p>
 *
 * @author 杜劲松
 * @since 2022-10-08
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "base.kafka")
public class BaseKafkaProperties {

    private List<String> bootstrapServers;
    private final String securityProtocol = "SASL_PLAINTEXT";
    private final String saslMechanism = "SCRAM-SHA-256";
    private String username;
    private String password;

    private Boolean logContainerConfig = true;
    private LogIfLevelEnabled.Level commitLogLevel = LogIfLevelEnabled.Level.INFO;
    private Duration authorizationExceptionRetryInterval = Duration.ofSeconds(30);
    private Boolean missingTopicsFatal = false;

    private Producer producer = new Producer();
    private Listener listener = new Listener();

    @Getter
    @Setter
    public static class Producer {

        private Integer retries = 0;
        private String acks = "all";
        private Integer batchSize = 16384;
        private Long lingerMs = 2L;
        private Integer requestTimeoutMs = 30000;
        private Integer deliveryTimeoutMs = 120000;
        private Integer sendBufferBytes = -1;
        private Integer receiveBufferBytes = -1;
    }

    @Getter
    @Setter
    public static class Listener {

        private Integer maxPollRecords = 500;
        private Integer maxPollIntervalMs = 300000;
        private Integer sessionTimeoutMs = 45000;
        private Integer heartbeatIntervalMs = 3000;
        private String partitionAssignmentStrategy = RoundRobinAssignor.class.getName();
        private String autoOffsetReset = "earliest";
        private Integer fetchMinBytes = 1;
        private Integer fetchMaxWaitMs = 500;
        private Integer sendBufferBytes = 131072;
        private Integer receiveBufferBytes = 32768;

        private Integer concurrency = 1;
        private Long pollTimeout = 5_000L;
        private ContainerProperties.AckMode ackMode = ContainerProperties.AckMode.MANUAL;
    }

}
