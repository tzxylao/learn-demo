package com.tzxylao.feign.interceptors;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

/**
 * @author laoliangliang
 * @date 2019/4/24 11:21
 */
@Slf4j
@Configuration
public class MyInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        template.header("test", "my-test");
    }
}
