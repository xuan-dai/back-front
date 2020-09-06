package cn.gorillahug.back.front.controller;

import cn.gorillahug.back.front.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/async")
@Slf4j
public class AsyncController {

    @Resource
    private AsyncService asyncService;

    @GetMapping("/do")
    public void doAsync() throws Exception {
        asyncService.doAsync();
        String future = asyncService.doAsyncWithReturn().get();
        log.info("middle time");
        log.info("future:{}", future);
        log.info("end time");
    }
}
