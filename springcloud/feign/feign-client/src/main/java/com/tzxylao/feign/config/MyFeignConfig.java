package com.tzxylao.feign.config;

import com.tzxylao.feign.annotation.MyAnnotation;
import com.tzxylao.feign.register.MyFeignRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author laoliangliang
 * @date 2019/4/24 14:43
 */
@Configuration
@MyAnnotation("my-first-annotation")
@Import(MyFeignRegistrar.class)
public class MyFeignConfig {
}
