package cn.imadc.application.base.data.structure;

import cn.imadc.application.base.common.constant.BaseConstant;
import cn.imadc.application.base.toolkit.serialization.JsonUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * redis解析器
 * </p>
 *
 * @author 杜劲松
 * @since 2022-03-04
 */
public class RedisParser {

    private static final String COMMAND_SEPARATOR = "\r\n";

    /**
     * 解析redis info 命令的结果
     *
     * @param info info命令结果
     * @return 解析后的class
     */
    public static RedisInfo parseRedisInfo(String info) {
        // 按回车换行符解析
        String[] lineArray = info.split(COMMAND_SEPARATOR);

        // 存放每一个节点的数据
        Map<String, Map<String, String>> infoMap = new HashMap<>();

        // 节点名
        String section = BaseConstant.BLANK;
        // 存放每一个节点下各个kv
        Map<String, String> fieldMap = new HashMap<>();
        for (int i = 0; i < lineArray.length; i++) {
            // 按行读取
            String line = lineArray[i];
            if (StringUtils.isEmpty(line)) continue;

            // 判断是否是节点名
            if (line.startsWith(BaseConstant.WELL_NUMBER)) {
                if (i != 0) infoMap.put(section, fieldMap);

                section = line.replace(BaseConstant.WELL_NUMBER, BaseConstant.BLANK).trim();

                fieldMap = new HashMap<>();
                continue;
            }

            // 分割成左右结构
            int index = line.indexOf(BaseConstant.COLON);
            String k = line.substring(0, index);
            String v = line.substring(index + 1);

            fieldMap.put(k, v);

            // 记录最后一节点
            if (i == lineArray.length - 1) {
                infoMap.put(section, fieldMap);
                break;
            }
        }

        // 映射成class
        String infoMapJson = JsonUtil.objectToJson(infoMap);
        RedisInfo redisInfo = JsonUtil.jsonToObject(infoMapJson, RedisInfo.class);

        // 针对Replication节点特殊处理
        Map<String, String> replication = infoMap.get("Replication");

        if (null != replication && !replication.isEmpty()) {
            List<RedisInfo.Replication.Slave> slaves = new ArrayList<>();

            for (Map.Entry<String, String> entry : replication.entrySet()) {
                // 处理slave
                if (entry.getKey().startsWith("slave")) {
                    String val = entry.getValue();
                    String[] propArray = val.split(BaseConstant.COMMA);

                    RedisInfo.Replication.Slave slave = new RedisInfo.Replication.Slave();
                    for (String prop : propArray) {
                        int index = prop.indexOf(BaseConstant.EQUAL_SIGN);
                        String k = prop.substring(0, index);
                        String v = prop.substring(index + 1);

                        switch (k) {
                            case "ip":
                                slave.setIp(v);
                                break;
                            case "port":
                                slave.setPort(v);
                                break;
                            case "state":
                                slave.setState(v);
                                break;
                            case "offset":
                                slave.setOffset(v);
                                break;
                            case "lag":
                                slave.setLag(v);
                                break;
                        }
                    }
                    slaves.add(slave);
                }
            }

            redisInfo.getReplication().setSlaves(slaves);
        }

        // 针对Sentinel节点的特殊处理
        Map<String, String> sentinel = infoMap.get("Sentinel");

        if (null != sentinel && !sentinel.isEmpty()) {
            List<RedisInfo.Sentinel.Master> masters = new ArrayList<>();

            for (Map.Entry<String, String> entry : sentinel.entrySet()) {
                // 处理master
                if (entry.getKey().startsWith("master")) {
                    String val = entry.getValue();
                    String[] propArray = val.split(BaseConstant.COMMA);

                    RedisInfo.Sentinel.Master master = new RedisInfo.Sentinel.Master();
                    for (String prop : propArray) {

                        int index = prop.indexOf(BaseConstant.EQUAL_SIGN);
                        String k = prop.substring(0, index);
                        String v = prop.substring(index + 1);

                        switch (k) {
                            case "name":
                                master.setName(v);
                                break;
                            case "status":
                                master.setStatus(v);
                                break;
                            case "address":
                                master.setAddress(v);
                                break;
                            case "slaves":
                                master.setSlaves(v);
                                break;
                            case "sentinels":
                                master.setSentinels(v);
                                break;
                        }
                    }
                    masters.add(master);
                }
            }

            redisInfo.getSentinel().setMasters(masters);
        }

        return redisInfo;
    }

    /**
     * 解析多个redis sentinel master
     *
     * @param infoJson 预处理成的json
     * @return 解析后的class
     */
    public static List<RedisSentinelMaster> parseSentinelMasters(String infoJson) {
        return JsonUtil.jsonToList(infoJson, RedisSentinelMaster.class);
    }

    /**
     * 解析多个redis slaves master
     *
     * @param infoJson 预处理成的json
     * @return 解析后的class
     */
    public static List<RedisSentinelSlave> parseSentinelSlaves(String infoJson) {
        return JsonUtil.jsonToList(infoJson, RedisSentinelSlave.class);
    }

    /**
     * 解析单个redis sentinel master
     *
     * @param infoJson 预处理成的json
     * @return 解析后的class
     */
    public static RedisSentinelMaster parseSentinelMaster(String infoJson) {
        return JsonUtil.jsonToObject(infoJson, RedisSentinelMaster.class);
    }

    /**
     * 解析多个redis sentinel sentinels masterName
     *
     * @param infoJson 预处理成的json
     * @return 解析后的class
     */
    public static List<RedisSentinel> parseSentinels(String infoJson) {
        return JsonUtil.jsonToList(infoJson, RedisSentinel.class);
    }
}
