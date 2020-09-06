package cn.gorillahug.back.front.context;

import cn.gorillahug.back.front.enums.DatePrecisionEnum;
import cn.gorillahug.back.front.handler.AbstractEnumHandler;
import cn.gorillahug.back.front.utils.SpringBeanTool;

import java.util.Map;

/**
 * @author daixuan
 * @version 2019/9/13 16:57
 */
public class PrecisionContext {


    private Map<DatePrecisionEnum,Class> handlerMap;

    public PrecisionContext(Map<DatePrecisionEnum,Class> map){
        this.handlerMap = map;
    }

    public AbstractEnumHandler getInstance(DatePrecisionEnum precision){
        Class clazz = handlerMap.get(precision);
        if(clazz == null){
            throw new IllegalArgumentException("precision type is error");
        }
        return (AbstractEnumHandler) SpringBeanTool.getBean(clazz);
    }
}
