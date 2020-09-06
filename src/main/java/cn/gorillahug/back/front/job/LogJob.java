package cn.gorillahug.back.front.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author daixuan
 * @version 2019/9/16 21:34
 */
@Service
@Slf4j
public class LogJob {

    public void doLoopLogging(){
        while(true){
            log.info("now : {}",System.currentTimeMillis());
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
