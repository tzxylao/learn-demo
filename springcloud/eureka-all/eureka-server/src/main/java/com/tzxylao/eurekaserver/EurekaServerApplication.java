package com.tzxylao.eurekaserver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author laoliangliang
 */
@SpringBootApplication
@EnableDiscoveryClient
@RestController
@Slf4j
public class EurekaServerApplication {



    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }



    @Value("${server.port}")
    private int port;

    @GetMapping
    public String time() {
        log.info("call time port:{}", port);
        return String.valueOf(port);
    }
}
