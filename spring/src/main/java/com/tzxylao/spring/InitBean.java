package com.tzxylao.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author laoliangliang
 * @since 19/4/21 下午7:43
 */
@Component
public class InitBean implements InstantiationAwareBeanPostProcessor ,BeanFactoryPostProcessor,BeanNameAware,BeanFactoryAware {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            System.err.println("postProcessBeforeInitialization");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Person) {
            System.err.println("postProcessAfterInitialization");
        }
        return bean;
    }

    public void init() {
        System.err.println("InitBean的初始化方法");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.err.println("postProcessBeanFactory");
        //这里不要getBean，否则会提前初始化，postProcessBeforeInitialization就找不到这个类了
//        Person bean = configurableListableBeanFactory.getBean(Person.class);
//        String name = bean.getName();
//        System.err.println("postProcessBeanFactory: " + name);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.err.println("setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.err.println("setBeanName");
    }


}
