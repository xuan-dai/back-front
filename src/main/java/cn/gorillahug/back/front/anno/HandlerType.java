package cn.gorillahug.back.front.anno;

import cn.gorillahug.back.front.enums.DatePrecisionEnum;

import java.lang.annotation.*;

/**
 * @author daixuan
 * @version 2019/9/13 16:47
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface HandlerType {
    DatePrecisionEnum value();
}
