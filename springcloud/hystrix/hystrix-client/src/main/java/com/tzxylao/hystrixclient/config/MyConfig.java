package com.tzxylao.hystrixclient.config;

import com.tzxylao.hystrixclient.bean.Condition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author laoliangliang
 * @date 2019/4/25 10:56
 */
@Configuration
public class MyConfig {
    
    @Bean
    @ConditionalOnProperty(name = "conditional.enabled")
    public Condition condition() {
        Condition condition = new Condition();
        condition.setValue("conditional.enabled test");
        return condition;
    }

}
