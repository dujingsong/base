package cn.imadc.application.base.lettuce;

import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.dynamic.RedisCommandFactory;
import io.lettuce.core.sentinel.api.StatefulRedisSentinelConnection;
import io.lettuce.core.sentinel.api.sync.RedisSentinelCommands;
import org.apache.commons.lang3.StringUtils;

import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * redis通信客户端
 * </p>
 *
 * @author 杜劲松
 * @since 2022-03-05
 */
public class RedisClient {

    private final ConcurrentMap<String, io.lettuce.core.RedisClient> clientTables = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, StatefulRedisSentinelConnection<String, String>>
            sentinelConnectionTables = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, StatefulRedisConnection<String, String>>
            connectionTables = new ConcurrentHashMap<>();
    protected final Lock lockSentinelConnectionTables = new ReentrantLock();
    protected final Lock lockConnectionTables = new ReentrantLock();

    protected static final long LOCK_TIMEOUT_MILLIS = 3000L;
    protected static final long CLIENT_DEFAULT_TIMEOUT = 10 * 1000;

    private io.lettuce.core.RedisClient getClient(String host, int port, String password) {
        io.lettuce.core.RedisClient redisClient = clientTables.get(host + port);
        if (null != redisClient) return redisClient;

        RedisURI.Builder builder = RedisURI.Builder.redis(host).withPort(port);
        if (StringUtils.isNotEmpty(password)) builder.withPassword((CharSequence) password);
        RedisURI redisURI = builder.build();

        redisClient = io.lettuce.core.RedisClient.create(redisURI);
        redisClient.setDefaultTimeout(Duration.ofMillis(CLIENT_DEFAULT_TIMEOUT));
        clientTables.put(host + port, redisClient);

        return redisClient;
    }

    public StatefulRedisSentinelConnection<String, String> getSentinelConnection(String host, int port
            , String password) throws InterruptedException {
        StatefulRedisSentinelConnection<String, String> connection = sentinelConnectionTables.get(host + port);
        if (null != connection && connection.isOpen()) return connection;

        if (!this.lockSentinelConnectionTables.tryLock(LOCK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
            return null;
        }

        try {

            io.lettuce.core.RedisClient redisClient = getClient(host, port, password);
            connection = redisClient.connectSentinel();
            sentinelConnectionTables.put(host + port, connection);

        } finally {
            this.lockSentinelConnectionTables.unlock();
        }

        return connection;
    }

    public StatefulRedisConnection<String, String> getConnection(String host, int port, String password)
            throws InterruptedException {
        StatefulRedisConnection<String, String> connection = connectionTables.get(host + port);
        if (null != connection && connection.isOpen()) return connection;

        if (!this.lockConnectionTables.tryLock(LOCK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
            return null;
        }

        try {

            io.lettuce.core.RedisClient redisClient = getClient(host, port, password);
            connection = redisClient.connect();
            connectionTables.put(host + port, connection);

        } finally {
            this.lockConnectionTables.unlock();
        }

        return connection;
    }

    public RedisCommands<String, String> getRedisCommands(String host, int port, String password)
            throws InterruptedException {

        return getConnection(host, port, password).sync();
    }

    public RedisSentinelCommands<String, String> getRedisSentinelCommands(String host, int port, String password)
            throws InterruptedException {

        return getSentinelConnection(host, port, password).sync();
    }

    public RedisSentinelExtensionCommands getRedisSentinelExtensionCommands(String host, int port, String password)
            throws InterruptedException {
        StatefulRedisConnection<String, String> statefulRedisConnection = getConnection(host, port, password);
        RedisCommandFactory factory = new RedisCommandFactory(statefulRedisConnection);
        return factory.getCommands(RedisSentinelExtensionCommands.class);
    }
}
