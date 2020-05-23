package cn.gorillahug.back.front.service;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author daixuan
 * @version 2020/5/19 22:08
 */
@Slf4j
@Service
public class WebService {

    public String sayHello(@Valid @NotBlank String word){
        return Optional.ofNullable(word).orElse("123");
    }
}
