package cn.imadc.application.base.toolkit.serialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

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

    public static String objectToJson(Object source) {
        return JSON.toJSONString(source, serializeConfig, features);
    }

    public static <T> T jsonToObject(String source, Class<T> clazz) {
        return JSON.parseObject(source, clazz);
    }

    public static <T> List<T> jsonToList(String source, Class<T> clazz) {
        return JSON.parseArray(source, clazz);
    }
}
