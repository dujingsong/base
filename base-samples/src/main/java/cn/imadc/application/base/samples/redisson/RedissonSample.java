package cn.imadc.application.base.samples.redisson;

import lombok.AllArgsConstructor;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.client.codec.StringCodec;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class RedissonSample {

    private final RedissonClient redissonClient;

    public void test() {
        RBucket<String> rBucket = redissonClient.getBucket("rBucket", StringCodec.INSTANCE);
        rBucket.set("rBucket");

        String val = rBucket.get();
        System.out.println(val);

        RMap<String, String> rMap = redissonClient.getMap("rMap", StringCodec.INSTANCE);
        rMap.put("rMapKey", "rMapVal");

        System.out.println(rMap.get("rMapKey"));
    }
}
