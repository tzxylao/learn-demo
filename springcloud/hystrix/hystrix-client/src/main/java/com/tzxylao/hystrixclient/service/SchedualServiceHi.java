package com.tzxylao.hystrixclient.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author laoliangliang
 * @date 2019/4/25 10:30
 */
@FeignClient(value = "feign-client", fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {

    @RequestMapping(value = "/feign/{name}", method = RequestMethod.GET)
    String call(@PathVariable("name") String name);
}
