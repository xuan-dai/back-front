package cn.gorillahug.back.front.service;

import cn.gorillahug.back.front.context.PrecisionContext;
import cn.gorillahug.back.front.enums.DatePrecisionEnum;
import cn.gorillahug.back.front.handler.AbstractEnumHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author daixuan
 * @version 2019/9/13 16:47
 */
@Service
@Slf4j
public class DatePrecisionQueryService {

    @Autowired
    private PrecisionContext precisionContext;

    public void query(DatePrecisionEnum precision) {
        AbstractEnumHandler instance = precisionContext.getInstance(precision);
        String result = instance.handler(precision);
        log.info("precision query result:{}", result);
    }
}
