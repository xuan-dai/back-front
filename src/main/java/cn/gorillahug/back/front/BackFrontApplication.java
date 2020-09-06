package cn.gorillahug.back.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class BackFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackFrontApplication.class, args);
    }

}
