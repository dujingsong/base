package cn.imadc.application.base.toolkit.serialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

/**
 * <p>
 * JSON工具类
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-21
 */
public class JsonUtil {

    private static final SerializeConfig serializeConfig;

    static {
        serializeConfig = new SerializeConfig();
        serializeConfig.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        serializeConfig.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
    }

    public static final SerializerFeature[] features = {
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.DisableCircularReferenceDetect
    };

    /**
     * 转换为json字符串
     *
     * @param source 待转换为json字符串的对象
     * @return json字符串
     */
    public static String objectToJson(Object source) {
        return JSON.toJSONString(source, serializeConfig, features);
    }

    /**
     * json字符串转换为对象
     *
     * @param source json字符串
     * @param clazz  对象的类型
     * @param <T>    对象的类型
     * @return 对象
     */
    public static <T> T jsonToObject(String source, Class<T> clazz) {
        return JSON.parseObject(source, clazz);
    }

    /**
     * json字符串转换为对象列表
     *
     * @param source json字符串
     * @param clazz  对象类型
     * @param <T>    对象类型
     * @return 对象列表
     */
    public static <T> List<T> jsonToList(String source, Class<T> clazz) {
        return JSON.parseArray(source, clazz);
    }
}
