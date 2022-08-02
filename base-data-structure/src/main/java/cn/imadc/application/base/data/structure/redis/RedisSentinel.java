package cn.imadc.application.base.data.structure.redis;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * sentinel sentinels masterName
 * </p>
 *
 * @author 杜劲松
 * @since 2022-03-05
 */
@Getter
@Setter
public class RedisSentinel implements Serializable {

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "ip")
    private String ip;

    @JSONField(name = "port")
    private Integer port;

    @JSONField(name = "runid")
    private String runId;

    @JSONField(name = "flags")
    private String flags;

    @JSONField(name = "link-pending-commands")
    private String linkPendingCommands;

    @JSONField(name = "link-refcount")
    private String linkRefcount;

    @JSONField(name = "last-ping-sent")
    private String lastPingSent;

    @JSONField(name = "last-ok-ping-reply")
    private String lastOkPingReply;

    @JSONField(name = "last-ping-reply")
    private String lastPingReply;

    @JSONField(name = "down-after-milliseconds")
    private String downAfterMilliseconds;

    @JSONField(name = "last-hello-message")
    private String lastHelloMessage;

    @JSONField(name = "voted-leader")
    private String votedLeader;

    @JSONField(name = "voted-leader-epoch")
    private String votedLeaderEpoch;
}
