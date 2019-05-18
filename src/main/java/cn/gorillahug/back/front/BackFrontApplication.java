package cn.gorillahug.back.front;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "cn.gorillahug.front.dao")
public class BackFrontApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackFrontApplication.class, args);
    }

}
