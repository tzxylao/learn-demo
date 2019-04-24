package com.tzxylao.feign.register;

import com.tzxylao.feign.annotation.MyAnnotation;
import com.tzxylao.feign.factory.MyFeignClientFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * @author laoliangliang
 * @date 2019/4/24 14:45
 */
@Slf4j
public class MyFeignRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MyAnnotation.class.getName());
        String[] value = (String[]) annotationAttributes.get("value");
        log.info("MyFeignRegistrar value:{}", value);

        BeanDefinitionBuilder definition = BeanDefinitionBuilder
                .genericBeanDefinition(MyFeignClientFactoryBean.class);
        definition.addPropertyValue("name", "tom");
        BeanDefinitionHolder beanDefinitionHolder = new BeanDefinitionHolder(definition.getBeanDefinition(), MyFeignClientFactoryBean.class.getCanonicalName());
        BeanDefinitionReaderUtils.registerBeanDefinition(beanDefinitionHolder, registry);
    }
}
