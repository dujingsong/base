package cn.imadc.application.base.samples.transaction;

import cn.imadc.application.base.samples.transaction.ITestService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-07-29
 */
@AllArgsConstructor
@Component
public class MyCommandLineRunner implements CommandLineRunner {

    private final ITestService testService;

    @Override
    public void run(String... args) throws Exception {
//        List<User> users = testService.list();
//        System.out.println(users);
//
//        User user = new User();
//        user.setName(UUID.randomUUID().toString().substring(0, 6));
//        testService.asdasd(user);
//
//        users = testService.list();

    }

}
