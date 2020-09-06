package cn.gorillahug.back.front.handler.date;

import cn.gorillahug.back.front.anno.HandlerType;
import cn.gorillahug.back.front.enums.DatePrecisionEnum;
import cn.gorillahug.back.front.handler.AbstractEnumHandler;
import org.springframework.stereotype.Component;

/**
 * @author daixuan
 * @version 2019/9/13 16:43
 */
@Component
@HandlerType(value = DatePrecisionEnum.MONTH)
public class MonthDataQueryHandler extends AbstractEnumHandler {
    @Override
    public String handler(DatePrecisionEnum precision) {
        return String.format("this is the handler for month and param is [%s]", precision.name());    }
}
