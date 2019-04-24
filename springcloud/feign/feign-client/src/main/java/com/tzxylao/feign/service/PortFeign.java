package com.tzxylao.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author laoliangliang
 * @date 2019/4/24 10:17
 */
@FeignClient(value = "feign-server")
public interface PortFeign {

    @RequestMapping(value = "/feign/{name}", method = RequestMethod.GET)
    String feignTest(@PathVariable("name") String name);

}
