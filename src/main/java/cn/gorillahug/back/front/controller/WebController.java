package cn.gorillahug.back.front.controller;

import cn.gorillahug.back.front.service.WebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/web")
@Slf4j
@Validated
public class WebController {

    @Resource
    private WebService webService;

    @GetMapping("/ok")
    public String sayHello() {
        return webService.sayHello(null);
    }
}
