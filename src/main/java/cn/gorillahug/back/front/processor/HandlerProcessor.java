package cn.gorillahug.back.front.processor;

import cn.gorillahug.back.front.anno.HandlerType;
import cn.gorillahug.back.front.context.PrecisionContext;
import cn.gorillahug.back.front.enums.DatePrecisionEnum;
import cn.gorillahug.back.front.utils.ClassScanner;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * 自动扫描指定包下的bean
 *
 * @author daixuan
 * @version 2019/9/14 0:41
 */
@Configuration
public class HandlerProcessor implements BeanFactoryPostProcessor {

    private String HANDLER_PATH = "cn.gorillahug.back.front.handler";


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        Map<DatePrecisionEnum, Class> handlerMap = new HashMap<>();
        ClassScanner classScanner = new ClassScanner();
        classScanner.withAnnotationFilter(HANDLER_PATH, HandlerType.class).findClasses().forEach(clazz -> {
            DatePrecisionEnum precision = clazz.getAnnotation(HandlerType.class).value();
            handlerMap.put(precision, clazz);
        });
        PrecisionContext context = new PrecisionContext(handlerMap);
        beanFactory.registerSingleton(context.getClass().getName(), context);
    }
}
