package cn.imadc.application.base.rocketmq.message;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 消息体
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-29
 */
@Getter
@Setter
public class Message implements Serializable {

    private String topic;
    private String tags;
    private String producerGroup;
    private String key;
    private String content;


    @Override
    public String toString() {
        return "topic:" + topic
                + ",tags:" + tags
                + ",producerGroup:" + producerGroup
                + ",key:" + key
                + ",content:" + content
                ;
    }
}
