package com.tzxylao.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author laoliangliang
 * @since 19/4/19 下午10:29
 */
@RestController
@Slf4j
public class TestController {

    @GetMapping("/1")
    private String get1() {
        log.info("get1 start");
        String str = createStr();
        log.info("get1 end");
        return str;
    }


    /**
     * Mono返回0-1个元素
     *
     * @return
     */
    @GetMapping(value = "/2")
    private Mono<String> get2() {
        log.info("get2 start");
        Mono<String> result = Mono.fromSupplier(() -> createStr());
        log.info("get2 end");
        return result;
    }

    /**
     * Flux返回1-n个元素
     *
     * @return
     */
    @GetMapping(value = "/3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    private Flux<String> get3() {
        log.info("get3 start");
        Flux<String> flux = Flux.fromStream(IntStream.range(1, 100).mapToObj(i -> {
            log.info("3 - i");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "flux data -- " + i;
        }));
        log.info("get3 end");
        return flux;
    }

    private String createStr() {
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "some string";
    }
}
