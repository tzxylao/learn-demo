package com.tzxylao.webfluxclient;

import com.tzxylao.framework.ApiServer;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author laoliangliang
 * @since 19/4/20 下午9:22
 */
@ApiServer("http://localhost:8080/user")
public interface IUserApi {

    @GetMapping("/")
    Flux<User> getAllUser();

    @GetMapping("/{id}")
    Mono<User> getUserByid(@PathVariable("id") String id);

    @DeleteMapping("/{id}")
    Mono<Void> deleteUserByid(@PathVariable("id") String id);

    @PostMapping("/")
    Mono<Void> createUser(@RequestBody Mono<User> user);
}
