package com.tzxylao.webfluxclient;

import com.tzxylao.framework.interfaces.ProxyCreator;
import com.tzxylao.framework.proxys.JDKProxyCreator;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebfluxClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebfluxClientApplication.class, args);
    }


    @Bean
    ProxyCreator jdkProxyCreator(){
        return new JDKProxyCreator();
    }

    @Bean
    FactoryBean<IUserApi> userApi(ProxyCreator proxyCreator){
        return new FactoryBean<IUserApi>() {
            @Override
            public IUserApi getObject() throws Exception {
                return (IUserApi) proxyCreator.createProxy(getObjectType());
            }

            @Override
            public Class<?> getObjectType() {
                return IUserApi.class;
            }
        };
    }
}
