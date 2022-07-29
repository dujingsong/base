package cn.imadc.application.base.samples;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("cn.imadc")
@SpringBootApplication
public class BaseSamplesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BaseSamplesApplication.class, args);
    }

}
