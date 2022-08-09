package cn.imadc.application.base.samples;

import cn.imadc.application.base.common.thread.BaseThreadPoolExecutor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-08-01
 */
@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class Controller {

    private final ITestService testService;

    private final AtomicLong threadIndex = new AtomicLong(0);

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {

        BaseThreadPoolExecutor baseThreadPoolExecutor = new BaseThreadPoolExecutor(1, 1, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "MyThread_" + threadIndex.incrementAndGet());
            }
        });

        List<User> users = testService.list();

        User user = new User();
        user.setName(UUID.randomUUID().toString().substring(0, 6));
        testService.asdasd(user);

        users = testService.list();

        return users.size() + "";
    }

}
