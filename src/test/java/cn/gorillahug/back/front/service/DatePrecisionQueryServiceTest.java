package cn.gorillahug.back.front.service;

import cn.gorillahug.back.front.BackFrontApplication;
import cn.gorillahug.back.front.enums.DatePrecisionEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author daixuan
 * @version 2019/9/14 2:08
 */
@SpringBootTest(classes = BackFrontApplication.class)
@RunWith(value = SpringJUnit4ClassRunner.class)
@Slf4j
public class DatePrecisionQueryServiceTest {

    @Autowired
    private DatePrecisionQueryService datePrecisionQueryService;

    @Test
    public void query() {
        datePrecisionQueryService.query(DatePrecisionEnum.DAY);
    }
}