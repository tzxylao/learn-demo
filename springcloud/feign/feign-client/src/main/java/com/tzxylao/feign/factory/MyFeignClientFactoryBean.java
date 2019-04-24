package com.tzxylao.feign.factory;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author laoliangliang
 * @date 2019/4/24 14:58
 */
@Slf4j
@Data
public class MyFeignClientFactoryBean {

    private Class<?> clazz;

    private String name;

    public MyFeignClientFactoryBean() {
        log.info("MyFeignClientFactoryBean构造器初始化");
    }

}
