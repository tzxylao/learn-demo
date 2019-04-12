package com.tzxylao.java8;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author laoliangliang
 * @date 2019/4/4 13:55
 */
@ConfigurationProperties(prefix = "tzxylao.java8")
@Data
public class Java8Properties {
    private String value;

    private String name;

    private boolean enabled;

}
