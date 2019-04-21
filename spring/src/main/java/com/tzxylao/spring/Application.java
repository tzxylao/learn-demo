package com.tzxylao.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext run =  SpringApplication.run(Application.class,args);
        InitBean initBean = (InitBean) run.getBean("initBean");
        initBean.init();
    }

}
