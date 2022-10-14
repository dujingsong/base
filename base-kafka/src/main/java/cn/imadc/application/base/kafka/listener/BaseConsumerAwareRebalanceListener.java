package cn.imadc.application.base.kafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.listener.ConsumerAwareRebalanceListener;

import java.util.Collection;

/**
 * <p>
 * 事件通知
 * </p>
 *
 * @author 杜劲松
 * @since 2022-10-08
 */
@Slf4j
public class BaseConsumerAwareRebalanceListener implements ConsumerAwareRebalanceListener {
    @Override
    public void onPartitionsRevokedBeforeCommit(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
        ConsumerAwareRebalanceListener.super.onPartitionsRevokedBeforeCommit(consumer, partitions);
    }

    @Override
    public void onPartitionsRevokedAfterCommit(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
        ConsumerAwareRebalanceListener.super.onPartitionsRevokedAfterCommit(consumer, partitions);
    }

    @Override
    public void onPartitionsAssigned(Consumer<?, ?> consumer, Collection<TopicPartition> partitions) {
        ConsumerAwareRebalanceListener.super.onPartitionsAssigned(consumer, partitions);
    }

    @Override
    public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
        ConsumerAwareRebalanceListener.super.onPartitionsRevoked(partitions);
    }

    @Override
    public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
        ConsumerAwareRebalanceListener.super.onPartitionsAssigned(partitions);
    }
}
