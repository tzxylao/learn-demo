package com.tzxylao.dubbo.service.impl;


import com.tzxylao.dubbo.service.GreetingService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Value;

@Service(version = "1.0.0")
public class GreetingServiceImpl implements GreetingService {

    @Value("${dubbo.application.name}")
    private String serviceName;
    @Value("${dubbo.protocol.port:0}")
    private int port;

    @Override
    public String sayHello(String name) {
        return String.format("[%s][%s] : Hello, %s", serviceName,port, name);
    }
}