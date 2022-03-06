package cn.imadc.application.base.lettuce;

import io.lettuce.core.RedisURI;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.dynamic.RedisCommandFactory;
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
    private final ConcurrentMap<String, RedisCommands<String, String>> redisCommandsTables = new ConcurrentHashMap<>();
    private final ConcurrentMap<String, RedisSentinelCommands<String, String>> redisSentinelCommandsTables = new ConcurrentHashMap<>();
    protected final Lock lockRedisCommandsTables = new ReentrantLock();
    protected final Lock lockRedisSentinelCommands = new ReentrantLock();
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

    public RedisCommands<String, String> getRedisCommands(String host, int port, String password)
            throws InterruptedException {

        RedisCommands<String, String> redisCommands = redisCommandsTables.get(host + port);
        if (null != redisCommands && redisCommands.isOpen()) return redisCommands;

        if (!this.lockRedisCommandsTables.tryLock(LOCK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
            return null;
        }

        try {

            io.lettuce.core.RedisClient redisClient = getClient(host, port, password);
            redisCommands = redisClient.connect().sync();
            redisCommandsTables.put(host + port, redisCommands);

        } finally {
            this.lockRedisCommandsTables.unlock();
        }

        return redisCommands;
    }

    public RedisSentinelCommands<String, String> getRedisSentinelCommands(String host, int port, String password)
            throws InterruptedException {

        RedisSentinelCommands<String, String> redisSentinelCommands = redisSentinelCommandsTables.get(host + port);
        if (null != redisSentinelCommands && redisSentinelCommands.isOpen()) return redisSentinelCommands;

        if (!this.lockRedisSentinelCommands.tryLock(LOCK_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)) {
            return null;
        }

        try {

            io.lettuce.core.RedisClient redisClient = getClient(host, port, password);
            redisSentinelCommands = redisClient.connectSentinel().sync();
            redisSentinelCommandsTables.put(host + port, redisSentinelCommands);

        } finally {
            this.lockRedisSentinelCommands.unlock();
        }

        return redisSentinelCommands;
    }

    public RedisSentinelExtensionCommands getRedisSentinelExtensionCommands(String host, int port, String password) {
        io.lettuce.core.RedisClient redisClient = getClient(host, port, password);
        RedisCommandFactory factory = new RedisCommandFactory(redisClient.connect());
        return factory.getCommands(RedisSentinelExtensionCommands.class);
    }
}
