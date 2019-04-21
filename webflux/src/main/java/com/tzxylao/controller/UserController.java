package com.tzxylao.controller;

import com.tzxylao.domain.User;
import com.tzxylao.repository.UserRepository;
import com.tzxylao.util.CheckUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * @author laoliangliang
 * @since 19/4/20 上午11:05
 */
@RestController
@Slf4j
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 查询全部数据
     *
     * @return
     */
    @GetMapping("/")
    public Flux<User> getAll() {
        return userRepository.findAll();
    }

    /**
     * 以流的形式查询全部数据
     *
     * @return
     */
    @GetMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getSteramAll() {
        return userRepository.findAll();
    }

    /**
     * 新增数据
     *
     * @param user
     * @return
     */
    @PostMapping("/")
    public Mono<User> createUser(@Valid @RequestBody User user) {
        //有id是修改，id为空是新增
        user.setId(null);
        CheckUtil.checkName(user.getName());
        return this.userRepository.save(user);
    }

    /**
     * 根据id删除用户
     * 存在返回200，不存在返回404
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteUser(@PathVariable("id") String id) {
        //没有返回值，不能判断数据是否存在
//        this.userRepository.deleteById(id);

        //当你要操作数据，并返回一个Mono 这个时候使用flatMap
        //如果不操作数据，只是转换数据，使用map
        return this.userRepository.findById(id)
                .flatMap(user -> this.userRepository.delete(user)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 修改数据，存在的时候返回200和修改的数据，不存在的时候返回404
     *
     * @param id
     * @param user
     * @return
     */
    @PutMapping("/id")
    public Mono<ResponseEntity<User>> updateUser(
            @PathVariable("id") String id,
            @Valid @RequestBody User user
    ) {
        return this.userRepository.findById(id)
                //flatMap操作数据
                .flatMap(u -> {
                    u.setAge(user.getAge());
                    u.setName(user.getName());
                    return this.userRepository.save(u);
                })
                //map：转换数据
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据id查找用户
     *
     * @return
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<User>> findUserById(@PathVariable("id") String id) {
        return this.userRepository.findById(id)
                .map(u -> new ResponseEntity<>(u, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * 根据年龄查找用户
     *
     * @param start
     * @param end
     * @return
     */
    @GetMapping("/age/{start}/{end}")
    public Flux<User> findByAge(@PathVariable("start") int start, @PathVariable("end") int end) {
        return this.userRepository.findByAgeBetween(start, end);
    }

    /**
     * 得到20-30岁的用户
     *
     * @return
     */
    @GetMapping("/old")
    public Flux<User> oldUser() {
        return this.userRepository.oldUser();
    }

}
