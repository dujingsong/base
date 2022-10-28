package cn.imadc.application.base.lettuce;

import io.lettuce.core.dynamic.Commands;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 哨兵相关命令补充
 * </p>
 *
 * @author 杜劲松
 * @since 2022-03-05
 */
public interface RedisSentinelExtensionCommands extends Commands {

    List<Map<String, String>> sentinelSentinels(String key);

}
