package cn.imadc.application.base.samples;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 *
 * </p>
 *
 * @author 杜劲松
 * @since 2022-08-01
 */
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class Controller {

    private final ITestService testService;

    @RequestMapping(value = "index", method = RequestMethod.GET)
    public String index() {
        List<User> users = testService.list();
//        System.out.println(users.size());

        User user = new User();
        user.setName(UUID.randomUUID().toString().substring(0, 6));
        testService.asdasd(user);

        users = testService.list();

        return users.size() + "";
    }

}
