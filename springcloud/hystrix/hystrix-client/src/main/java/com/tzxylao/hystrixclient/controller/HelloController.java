package com.tzxylao.hystrixclient.controller;

import com.tzxylao.hystrixclient.bean.Condition;
import com.tzxylao.hystrixclient.service.HelloService;
import com.tzxylao.hystrixclient.service.SchedualServiceHi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laoliangliang
 * @date 2019/4/25 9:48
 */
@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @Autowired
    private Condition condition;

    @Autowired
    private SchedualServiceHi schedualServiceHi;

    @GetMapping("/hi/{name}")
    public String hi(@PathVariable("name") String name){
        return helloService.hiService(name);
    }

    @GetMapping("/hi2/{name}")
    public String hi2(@PathVariable("name") String name){
        return schedualServiceHi.call(name);
    }

    @GetMapping("/condition")
    public String condition(){
        return condition.getValue();
    }
}
