package com.tzxylao.hystrixclient.service;

import org.springframework.stereotype.Component;

/**
 * @author laoliangliang
 * @date 2019/4/25 10:31
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

    @Override
    public String call(String name) {
        return "sorry " + name;
    }
}
