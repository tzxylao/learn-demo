package com.tzxylao.hystrixclient.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author laoliangliang
 * @date 2019/4/25 9:28
 */
@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://feign-client/feign/" + name, String.class);
    }

    public String hiError(String name) {
        return "hi, " + name + ",sorry,error!";
    }

}
