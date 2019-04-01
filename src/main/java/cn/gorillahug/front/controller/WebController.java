package cn.gorillahug.front.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/web")
@Slf4j
public class WebController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }
}
