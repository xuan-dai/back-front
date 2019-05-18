package cn.gorillahug.back.front.controller;

import cn.gorillahug.back.front.common.HttpResponseHelper;
import cn.gorillahug.back.front.common.RespCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web")
@Slf4j
public class WebController {

    @GetMapping("/ok")
    public ResponseEntity sayHello() {
        HttpResponseHelper response = new HttpResponseHelper();
        return response.buildResponse(200, RespCode.OK);
    }
}
