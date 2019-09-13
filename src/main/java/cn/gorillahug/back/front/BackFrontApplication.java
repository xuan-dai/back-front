package cn.gorillahug.back.front;

import cn.gorillahug.base.data.api.model.CustomAutoConfigBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BackFrontApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ac = SpringApplication.run(BackFrontApplication.class, args);
    }

}
