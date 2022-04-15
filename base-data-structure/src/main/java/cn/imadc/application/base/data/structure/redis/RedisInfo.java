package cn.imadc.application.base.data.structure.redis;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * redis info信息
 * </p>
 *
 * @author 杜劲松
 * @since 2022-03-04
 */
@Getter
@Setter
public class RedisInfo implements Serializable {

    @JSONField(name = "Server")
    private Server server;

    @JSONField(name = "Clients")
    private Clients clients;

    @JSONField(name = "Memory")
    private Memory memory;

    @JSONField(name = "Persistence")
    private Persistence persistence;

    @JSONField(name = "Stats")
    private Stats stats;

    @JSONField(name = "Replication")
    private Replication replication;

    @JSONField(name = "CPU")
    private Cpu Cpu;

    @JSONField(name = "Cluster")
    private Cluster cluster;

    @JSONField(name = "Keyspace")
    private Keyspace keyspace;

    @JSONField(name = "Sentinel")
    private Sentinel sentinel;

    @Getter
    @Setter
    public static class Server {
        @JSONField(name = "redis_version")
        private String redisVersion;

        @JSONField(name = "redis_git_sha1")
        private String redisGitSha1;

        @JSONField(name = "redis_git_dirty")
        private String redisGitDirty;

        @JSONField(name = "redis_build_id")
        private String redisBuildId;

        @JSONField(name = "redis_mode")
        private String redisMode;

        @JSONField(name = "os")
        private String os;

        @JSONField(name = "arch_bits")
        private String archBits;

        @JSONField(name = "multiplexing_api")
        private String multiplexingApi;

        @JSONField(name = "atomicvar_api")
        private String atomicVarApi;

        @JSONField(name = "gcc_version")
        private String gccVersion;

        @JSONField(name = "process_id")
        private Integer processId;

        @JSONField(name = "run_id")
        private String runId;

        @JSONField(name = "tcp_port")
        private String tcpPort;

        @JSONField(name = "uptime_in_seconds")
        private Long uptimeInSeconds;

        @JSONField(name = "uptime_in_days")
        private String uptimeInDays;

        @JSONField(name = "hz")
        private Integer hz;

        @JSONField(name = "lru_clock")
        private String lruClock;

        @JSONField(name = "executable")
        private String executable;

        @JSONField(name = "config_file")
        private String configFile;
    }

    @Getter
    @Setter
    public static class Clients {
        @JSONField(name = "connected_clients")
        private Integer connectedClients;

        @JSONField(name = "client_longest_output_list")
        private Long clientLongestOutputList;

        @JSONField(name = "client_biggest_input_buf")
        private Long clientBiggestInputBuf;

        @JSONField(name = "blocked_clients")
        private Integer blockedClients;
    }

    @Getter
    @Setter
    public static class Memory {

        @JSONField(name = "used_memory")
        private Long usedMemory;

        @JSONField(name = "used_memory_human")
        private String usedMemoryHuman;

        @JSONField(name = "used_memory_rss")
        private Long usedMemoryRss;

        @JSONField(name = "used_memory_rss_human")
        private String usedMemoryRssHuman;

        @JSONField(name = "used_memory_peak")
        private Long usedMemoryPeak;

        @JSONField(name = "used_memory_peak_human")
        private String usedMemoryPeakHuman;

        @JSONField(name = "used_memory_peak_perc")
        private String usedMemoryPeakPerc;

        @JSONField(name = "used_memory_overhead")
        private Long usedMemoryOverhead;

        @JSONField(name = "used_memory_startup")
        private Long usedMemoryStartup;

        @JSONField(name = "used_memory_dataset")
        private Long usedMemoryDataset;

        @JSONField(name = "used_memory_dataset_perc")
        private String usedMemoryDatasetPerc;

        @JSONField(name = "total_system_memory")
        private Long totalSystemMemory;

        @JSONField(name = "total_system_memory_human")
        private String totalSystemMemoryHuman;

        @JSONField(name = "used_memory_lua")
        private Long usedMemoryLua;

        @JSONField(name = "used_memory_lua_human")
        private String usedMemoryLuaHuman;

        @JSONField(name = "maxmemory")
        private Long maxMemory;

        @JSONField(name = "maxmemory_human")
        private String maxMemoryHuman;

        @JSONField(name = "maxmemory_policy")
        private String maxMemoryPolicy;

        @JSONField(name = "mem_fragmentation_ratio")
        private String memFragmentationRatio;

        @JSONField(name = "mem_allocator")
        private String memAllocator;

        @JSONField(name = "active_defrag_running")
        private String activeDefragRunning;

        @JSONField(name = "lazyfree_pending_objects")
        private String lazyFreePendingObjects;
    }

    @Getter
    @Setter
    public static class Persistence {

        @JSONField(name = "loading")
        private String loading;

        @JSONField(name = "rdb_changes_since_last_save")
        private String rdbChangesSinceLastSave;

        @JSONField(name = "rdb_bgsave_in_progress")
        private String rdbBgSaveInProgress;

        @JSONField(name = "rdb_last_save_time")
        private String rdbLastSaveTime;

        @JSONField(name = "rdb_last_bgsave_status")
        private String rdbLastBgSaveStatus;

        @JSONField(name = "rdb_last_bgsave_time_sec")
        private String rdbLastBgSaveTimeSec;

        @JSONField(name = "rdb_current_bgsave_time_sec")
        private String rdbCurrentBgSaveTimeSec;

        @JSONField(name = "rdb_last_cow_size")
        private String rdbLastCowSize;

        @JSONField(name = "aof_enabled")
        private String aofEnabled;

        @JSONField(name = "aof_rewrite_in_progress")
        private String aofRewriteInProgress;

        @JSONField(name = "aof_rewrite_scheduled")
        private String aofRewriteScheduled;

        @JSONField(name = "aof_last_rewrite_time_sec")
        private String aofLastRewriteTimeSec;

        @JSONField(name = "aof_current_rewrite_time_sec")
        private String aofCurrentRewriteTimeSec;

        @JSONField(name = "aof_last_bgrewrite_status")
        private String aofLastBgRewriteStatus;

        @JSONField(name = "aof_last_write_status")
        private String aofLastWriteStatus;

        @JSONField(name = "aof_last_cow_size")
        private String aofLastCowSize;

        @JSONField(name = "aof_current_size")
        private String aofCurrentSize;

        @JSONField(name = "aof_base_size")
        private String aofBaseSize;

        @JSONField(name = "aof_pending_rewrite")
        private String aofPendingRewrite;

        @JSONField(name = "aof_buffer_length")
        private String aofBufferLength;

        @JSONField(name = "aof_rewrite_buffer_length")
        private String aofRewriteBufferLength;

        @JSONField(name = "aof_pending_bio_fsync")
        private String aofPendingBioFsync;

        @JSONField(name = "aof_delayed_fsync")
        private String aofDelayedFsync;
    }

    @Getter
    @Setter
    public static class Stats {

        @JSONField(name = "total_connections_received")
        private Long totalConnectionsReceived;

        @JSONField(name = "total_commands_processed")
        private Long totalCommandsProcessed;

        @JSONField(name = "instantaneous_ops_per_sec")
        private Long instantaneousOpsPerSec;

        @JSONField(name = "total_net_input_bytes")
        private Long totalNetInputBytes;

        @JSONField(name = "total_net_output_bytes")
        private Long totalNetOutputBytes;

        @JSONField(name = "instantaneous_input_kbps")
        private Double instantaneousInputKbps;

        @JSONField(name = "instantaneous_output_kbps")
        private Double instantaneousOutputKbps;

        @JSONField(name = "rejected_connections")
        private Long rejectedConnections;

        @JSONField(name = "sync_full")
        private String syncFull;

        @JSONField(name = "sync_partial_ok")
        private String syncPartialOk;

        @JSONField(name = "sync_partial_err")
        private String syncPartialErr;

        @JSONField(name = "expired_keys")
        private Long expiredKeys;

        @JSONField(name = "expired_stale_perc")
        private String expiredStalePerc;

        @JSONField(name = "expired_time_cap_reached_count")
        private Long expiredTimeCapReachedCount;

        @JSONField(name = "evicted_keys")
        private Long evictedKeys;

        @JSONField(name = "keyspace_hits")
        private Long keyspaceHits;

        @JSONField(name = "keyspace_misses")
        private Long keyspaceMisses;

        @JSONField(name = "pubsub_channels")
        private Long pubsubChannels;

        @JSONField(name = "pubsub_patterns")
        private Long pubsubPatterns;

        @JSONField(name = "latest_fork_usec")
        private Long latestForkUsec;

        @JSONField(name = "migrate_cached_sockets")
        private Long migrateCachedSockets;

        @JSONField(name = "slave_expires_tracked_keys")
        private Long slaveExpiresTrackedKeys;

        @JSONField(name = "active_defrag_hits")
        private Long activeDefragHits;

        @JSONField(name = "active_defrag_misses")
        private Long activeDefragMisses;

        @JSONField(name = "active_defrag_key_hits")
        private Long activeDefragKeyHits;

        @JSONField(name = "active_defrag_key_misses")
        private Long activeDefragKeyMisses;
    }

    @Getter
    @Setter
    public static class Replication {

        @JSONField(name = "role")
        private String role;

        @JSONField(name = "connected_slaves")
        private String connectedSlaves;

        private List<Slave> slaves;

        @JSONField(name = "master_replid")
        private String masterReplId;

        @JSONField(name = "master_replid2")
        private String masterReplId2;

        @JSONField(name = "master_repl_offset")
        private String masterReplOffset;

        @JSONField(name = "second_repl_offset")
        private String secondReplOffset;

        @JSONField(name = "repl_backlog_active")
        private String replBacklogActive;

        @JSONField(name = "repl_backlog_size")
        private String replBacklogSize;

        @JSONField(name = "repl_backlog_first_byte_offset")
        private String replBacklogFirstByteOffset;

        @JSONField(name = "repl_backlog_histlen")
        private String replBacklogHistLen;

        @Getter
        @Setter
        public static class Slave {
            private String ip;
            private String port;
            private String state;
            private String offset;
            private String lag;
        }
    }

    @Getter
    @Setter
    public static class Cpu {

        @JSONField(name = "used_cpu_sys")
        private String usedCpuSys;

        @JSONField(name = "used_cpu_user")
        private String usedCpuUser;

        @JSONField(name = "used_cpu_sys_children")
        private String usedCpuSysChildren;

        @JSONField(name = "used_cpu_user_children")
        private String usedCpuUserChildren;
    }

    @Getter
    @Setter
    public static class Cluster {

        @JSONField(name = "cluster_enabled")
        private String clusterEnabled;
    }

    @Getter
    @Setter
    public static class Keyspace {

        private List<String> dbs;
        private List<DBInfo> dbInfoList;

        @Getter
        @Setter
        public static class DBInfo {
            private Long keys;
            private Long expires;
            private Long avgTtl;
        }
    }

    @Getter
    @Setter
    public static class Sentinel {

        @JSONField(name = "sentinel_masters")
        private String sentinelMasters;

        @JSONField(name = "sentinel_tilt")
        private String sentinelTilt;

        @JSONField(name = "sentinel_running_scripts")
        private String sentinelRunningScripts;

        @JSONField(name = "sentinel_scripts_queue_length")
        private String sentinelScriptsQueueLength;

        @JSONField(name = "sentinel_simulate_failure_flags")
        private String sentinelSimulateFailureFlags;

        private List<Master> masters;

        @Getter
        @Setter
        public static class Master {
            private String name;
            private String status;
            private String address;
            private String slaves;
            private String sentinels;
        }
    }
}


