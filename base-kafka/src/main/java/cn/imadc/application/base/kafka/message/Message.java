package cn.imadc.application.base.kafka.message;

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
    private String key;
    private String content;


    @Override
    public String toString() {
        return "topic:" + topic
                + ",key:" + key
                + ",content:" + content
                ;
    }
}
