package com.tzxylao.java8;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author laoliangliang
 * @date 2019/4/4 13:55
 */
@Configuration
@EnableConfigurationProperties({Java8Properties.class})
@ConditionalOnClass(Java8Properties.class)
@ConditionalOnProperty(prefix = "tzxylao.java8",value = "enabled",matchIfMissing = false)
public class Java8AutoConfiguration {
}
