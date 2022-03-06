package cn.imadc.application.base.data.structure;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * sentinel masters信息
 * </p>
 *
 * @author 杜劲松
 * @since 2022-03-04
 */
@Getter
@Setter
public class RedisSentinelMaster implements Serializable {

    @JSONField(name = "name")
    private String name;

    @JSONField(name = "ip")
    private String ip;

    @JSONField(name = "port")
    private String port;

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

    @JSONField(name = "config-epoch")
    private String configEpoch;

    @JSONField(name = "num-slaves")
    private String numSlaves;

    @JSONField(name = "num-other-sentinels")
    private String numOtherSentinels;

    @JSONField(name = "quorum")
    private String quorum;

    @JSONField(name = "failover-timeout")
    private String failoverTimeout;

    @JSONField(name = "parallel-syncs")
    private String parallelSyncs;

}
