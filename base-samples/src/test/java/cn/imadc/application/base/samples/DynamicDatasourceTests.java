package cn.imadc.application.base.samples;

import cn.imadc.application.base.samples.transaction.ITestService;
import cn.imadc.application.base.samples.transaction.IUserService;
import cn.imadc.application.base.samples.transaction.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DynamicDatasourceTests {

    @Autowired
    private ITestService testService;

    @Autowired
    private IUserService userService;

    @Test
    void contextLoads() {

    }

    @Test
    void testDynamicDatasource() {
        User user = testService.find();
        User user1 = userService.find();
        System.out.println("ad");
    }

}
