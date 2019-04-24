package com.tzxylao.feign.annotation;

/**
 * @author laoliangliang
 * @date 2019/4/24 14:49
 */
public @interface MyAnnotation {
    String[] value() default {};
}
