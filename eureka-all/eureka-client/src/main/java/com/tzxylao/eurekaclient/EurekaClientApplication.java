package com.tzxylao.eurekaclient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
@Slf4j
public class EurekaClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaClientApplication.class, args);
    }
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 使用Ribbon的负载均衡
     * @return
     */
    @GetMapping("/")
    public ResponseEntity<String> getTime(){
        return restTemplate.getForEntity("http://eureka-server", String.class);
    }

    /**
     * 模拟轮询负载的调用
     * @return
     */
    @GetMapping("/discovery")
    public ResponseEntity<String> discovery(){
        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-server");
        int i = incrementAndGetModule(instances.size());
        return restTemplate.getForEntity(((EurekaDiscoveryClient.EurekaServiceInstance) instances.get(i)).getInstanceInfo().getHomePageUrl(), String.class);
    }

    private AtomicInteger nextIndex = new AtomicInteger();
    private int incrementAndGetModule(int module) {
        for (; ; ) {
            int current = nextIndex.get();
            int next = (current + 1) % module;
            if (nextIndex.compareAndSet(current,next) && current < module) {
                return current;
            }
        }
    }

    /**
     * 加上@LoadBalanced该注解使用的Ribbon的负载均衡算法
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
