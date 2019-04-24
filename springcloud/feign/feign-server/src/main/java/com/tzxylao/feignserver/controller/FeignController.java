package com.tzxylao.feignserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laoliangliang
 * @date 2019/4/24 10:14
 */
@RestController
public class FeignController {

    @Value("${server.port:0}")
    private int port;

    @GetMapping("/feign/{name}")
    public String feignTest(@PathVariable("name") String name) {
        return String.format("hello %s !,feign port:%s", name, port);
    }

}
