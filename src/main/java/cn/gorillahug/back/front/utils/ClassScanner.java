package cn.gorillahug.back.front.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.util.ClassUtils;

import javax.validation.constraints.NotNull;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author daixuan
 * @version 2019/9/14 1:55
 */
@Slf4j
public class ClassScanner {
    private String basePackages;

    private final ClassPathScanningCandidateComponentProvider scanner =
            new ClassPathScanningCandidateComponentProvider(false);

    @NotNull
    public final Collection<Class<?>> findClasses() {
        final List<Class<?>> classes = new ArrayList<Class<?>>();
        log.info("basePackages is:{}", basePackages);

        for (final String basePackage : basePackages.split("\\.")) {
            for (final BeanDefinition candidate : scanner.findCandidateComponents(basePackage)) {
                classes.add(ClassUtils.resolveClassName(candidate.getBeanClassName(),
                        ClassUtils.getDefaultClassLoader()));
            }
        }

        return classes;
    }

    @NotNull
    public ClassScanner withIncludeFilter(final @NotNull String basePackages, final @NotNull TypeFilter filter) {
        scanner.addIncludeFilter(filter);
        this.basePackages = basePackages;
        return this;
    }

    @NotNull
    public ClassScanner withAnnotationFilter(final @NotNull String basePackages, final @NotNull Class<? extends Annotation> annotationClass) {
        return withIncludeFilter(basePackages, new AnnotationTypeFilter(annotationClass));
    }
}
