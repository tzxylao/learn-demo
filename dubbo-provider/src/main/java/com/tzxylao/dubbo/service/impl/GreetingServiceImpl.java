package com.tzxylao.dubbo.service.impl;


import com.tzxylao.dubbo.service.GreetingService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

@Service(version = "1.0.0", dynamic = true)
public class GreetingServiceImpl implements GreetingService {

    @Value("${dubbo.application.name}")
    private String serviceName;

    private int random = 2;

    @Override
    public String sayHello(String name) {
        return String.format("[%s][%s] : Hello, %s", serviceName, random, name);
    }
}