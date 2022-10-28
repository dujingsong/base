package cn.imadc.application.base.kafka.config;

import cn.imadc.application.base.kafka.listener.BaseConsumerAwareRebalanceListener;
import cn.imadc.application.base.kafka.properties.BaseKafkaProperties;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ContainerProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * kafka配置相关
 * <p>
 * <a href="https://docs.spring.io/spring-kafka/docs/2.3.6.RELEASE/reference/html/#with-java-configuration">with-java-configuration</a>
 * </p>
 *
 * @author 杜劲松
 * @since 2022-10-08
 */
@AllArgsConstructor
@EnableConfigurationProperties(BaseKafkaProperties.class)
@Configuration
@EnableKafka
public class BaseKafkaConfiguration {

    private final BaseKafkaProperties baseKafkaProperties;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        // https://docs.spring.io/spring-kafka/docs/2.3.6.RELEASE/reference/html/#container-factory
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();

        BaseKafkaProperties.Listener listener = baseKafkaProperties.getListener();
        factory.setConcurrency(listener.getConcurrency());

        ContainerProperties containerProperties = factory.getContainerProperties();
        containerProperties.setAckMode(listener.getAckMode());
        containerProperties.setConsumerRebalanceListener(baseConsumerAwareRebalanceListener());
        containerProperties.setPollTimeout(listener.getPollTimeout());
        containerProperties.setLogContainerConfig(baseKafkaProperties.getLogContainerConfig());
        containerProperties.setCommitLogLevel(baseKafkaProperties.getCommitLogLevel());
        containerProperties.setMissingTopicsFatal(baseKafkaProperties.getMissingTopicsFatal());
        containerProperties.setAuthorizationExceptionRetryInterval(baseKafkaProperties.getAuthorizationExceptionRetryInterval());

        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerConfigs());
    }

    @Bean
    public Map<String, Object> consumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        BaseKafkaProperties.Listener listener = baseKafkaProperties.getListener();
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, listener.getMaxPollRecords());
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, listener.getMaxPollIntervalMs());
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, listener.getSessionTimeoutMs());
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, listener.getHeartbeatIntervalMs());
        String bootstrapServers = StringUtils.join(baseKafkaProperties.getBootstrapServers(), ",");
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, listener.getPartitionAssignmentStrategy());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, listener.getAutoOffsetReset());
        props.put(ConsumerConfig.FETCH_MIN_BYTES_CONFIG, listener.getFetchMinBytes());
        props.put(ConsumerConfig.FETCH_MAX_WAIT_MS_CONFIG, listener.getFetchMaxWaitMs());
        props.put(ConsumerConfig.SEND_BUFFER_CONFIG, listener.getSendBufferBytes());
        props.put(ConsumerConfig.RECEIVE_BUFFER_CONFIG, listener.getReceiveBufferBytes());
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, baseKafkaProperties.getSecurityProtocol());
        props.put("sasl.mechanism", baseKafkaProperties.getSaslMechanism());
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\""
                + baseKafkaProperties.getUsername()
                + "\" password=\""
                + baseKafkaProperties.getPassword() + "\";");

        return props;
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(producerConfigs());
    }

    @Bean
    public Map<String, Object> producerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        BaseKafkaProperties.Producer producer = baseKafkaProperties.getProducer();
        String bootstrapServers = StringUtils.join(baseKafkaProperties.getBootstrapServers(), ",");
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.ACKS_CONFIG, producer.getAcks());
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, producer.getBatchSize());
        props.put(ProducerConfig.LINGER_MS_CONFIG, producer.getLingerMs());
        props.put(ProducerConfig.REQUEST_TIMEOUT_MS_CONFIG, producer.getRequestTimeoutMs());
        props.put(ProducerConfig.DELIVERY_TIMEOUT_MS_CONFIG, producer.getDeliveryTimeoutMs());
        props.put(ProducerConfig.SEND_BUFFER_CONFIG, producer.getSendBufferBytes());
        props.put(ProducerConfig.RECEIVE_BUFFER_CONFIG, producer.getReceiveBufferBytes());
        props.put(ProducerConfig.RETRIES_CONFIG, producer.getRetries());
        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, baseKafkaProperties.getSecurityProtocol());

        props.put("sasl.mechanism", baseKafkaProperties.getSaslMechanism());
        props.put("sasl.jaas.config", "org.apache.kafka.common.security.scram.ScramLoginModule required username=\""
                + baseKafkaProperties.getUsername()
                + "\" password=\""
                + baseKafkaProperties.getPassword() + "\";");

        return props;
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public BaseConsumerAwareRebalanceListener baseConsumerAwareRebalanceListener() {
        return new BaseConsumerAwareRebalanceListener();
    }

}
