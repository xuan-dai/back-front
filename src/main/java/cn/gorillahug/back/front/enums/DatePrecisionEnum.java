package cn.gorillahug.back.front.enums;

/**
 * @author daixuan
 * @version 2019/9/13 16:41
 */
public enum DatePrecisionEnum {
    HOUR,
    DAY,
    MONTH,
    ;

    String get(){
        return this.name();
    }
}
