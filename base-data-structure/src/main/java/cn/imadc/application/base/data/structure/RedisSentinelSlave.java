package cn.imadc.application.base.data.structure;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * sentinel slaves masterName
 * </p>
 *
 * @author 杜劲松
 * @since 2022-03-05
 */
@Getter
@Setter
public class RedisSentinelSlave implements Serializable {

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

    @JSONField(name = "info-refresh")
    private String infoRefresh;

    @JSONField(name = "role-reported")
    private String roleReported;

    @JSONField(name = "role-reported-time")
    private String roleReportedTime;

    @JSONField(name = "master-link-down-time")
    private String masterLinkDownTime;

    @JSONField(name = "master-link-status")
    private String masterLinkStatus;

    @JSONField(name = "master-host")
    private String masterHost;

    @JSONField(name = "master-port")
    private Integer masterPort;

    @JSONField(name = "slave-priority")
    private String slavePriority;

    @JSONField(name = "slave-repl-offset")
    private String slaveReplOffset;
}
