package com.tzxylao.feign.controller;

import com.tzxylao.feign.service.PortFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laoliangliang
 * @date 2019/4/24 10:23
 */
@RestController
public class TestController {

    @Autowired
    private PortFeign portFeign;

    @GetMapping("/feign/{name}")
    public String call(@PathVariable("name") String name) {
        String tom = portFeign.feignTest(name);
        return "call result: " + tom;
    }

    @GetMapping("/")
    public String call() {
        String tom = portFeign.feignTest("Tom");
        return "call result: " + tom;
    }
}
