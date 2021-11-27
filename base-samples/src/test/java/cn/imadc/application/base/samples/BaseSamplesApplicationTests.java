package cn.imadc.application.base.samples;

import cn.imadc.application.base.samples.redisson.RedissonSample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseSamplesApplicationTests {

    @Autowired
    private RedissonSample redissonSample;

    @Test
    void contextLoads() {

        redissonSample.test();

    }

}
