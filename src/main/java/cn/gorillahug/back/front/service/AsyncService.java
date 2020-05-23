package cn.gorillahug.back.front.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @author daixuan
 * @version 2020/5/19 22:08
 */
@Slf4j
@Service
public class AsyncService {

    @Async("asyncThreadPoolTaskExecutor")
    public void doAsync() throws Exception {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
            log.info("finish doAsync:{}", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            log.error("sleep error, e...", e);
            throw e;
        }
    }

    @Async("asyncThreadPoolTaskExecutor")
    public Future<String> doAsyncWithReturn() throws Exception {
        try {
            TimeUnit.MILLISECONDS.sleep(100);
            log.info("finish doAsyncWithReturn:{}", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            log.error("sleep error, e...", e);
            throw e;
        }
        return new AsyncResult<>("finish doAsyncWithReturn");
    }
}
