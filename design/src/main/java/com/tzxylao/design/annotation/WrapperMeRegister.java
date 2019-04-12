package com.tzxylao.design.annotation;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author laoliangliang
 * @date 2019/4/12 10:39
 */
public class WrapperMeRegister implements ImportBeanDefinitionRegistrar ,ResourceLoaderAware{
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        AnnotationAttributes annotationAttributes = AnnotationAttributes.fromMap(annotationMetadata.getAnnotationAttributes(WrapperMe.class.getName()));
        Class<?> markerInterface = annotationAttributes.getClass("markerInterface");
        System.out.println(markerInterface.getName());
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        System.out.println(resourceLoader);
    }
}
