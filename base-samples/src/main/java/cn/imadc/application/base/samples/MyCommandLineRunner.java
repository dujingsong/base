package cn.imadc.application.base.samples;

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

    private final Producer producer;

    @Override
    public void run(String... args) throws Exception {
        producer.send();
    }
}
