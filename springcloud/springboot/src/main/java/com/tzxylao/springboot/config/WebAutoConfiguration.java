package com.tzxylao.springboot.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Import;

/**
 * @author laoliangliang
 * @date 2019/4/22 15:03
 */
@Configurable
@Import(WebConfiguration.class)
public class WebAutoConfiguration {
}
