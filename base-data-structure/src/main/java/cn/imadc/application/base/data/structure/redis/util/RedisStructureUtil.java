package cn.imadc.application.base.data.structure.redis.util;

import cn.imadc.application.base.data.structure.redis.RedisInfo;
import cn.imadc.application.base.data.structure.redis.RedisNode;

/**
 * <p>
 * redis info信息
 * </p>
 *
 * @author 杜劲松
 * @since 2022-04-15
 */
public class RedisStructureUtil {

    /**
     * 解析redis的节点类型
     *
     * @param redisInfo info命令解析的数据
     * @return redis的节点类型
     */
    public static RedisNode parseRedisNode(RedisInfo redisInfo) {
        if (null == redisInfo) return null;

        if (null != redisInfo.getSentinel()) return RedisNode.SENTINEL;

        if (redisInfo.getReplication().getRole().equals("slave")) return RedisNode.SLAVE;

        return RedisNode.MASTER;
    }

}
