package com.tzxylao.design.annotation;

import com.tzxylao.design.less.observer.Event;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author laoliangliang
 * @date 2019/4/12 10:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(WrapperMeRegister.class)
public @interface WrapperMe {
    String[] value() default {};

    Class<?> markerInterface() default Event.class;
}
