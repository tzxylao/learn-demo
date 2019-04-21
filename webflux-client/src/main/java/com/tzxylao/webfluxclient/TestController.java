package com.tzxylao.webfluxclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author laoliangliang
 * @since 19/4/20 下午9:28
 */
@RestController
public class TestController {

    @Autowired
    private IUserApi userApi;

    @GetMapping("/")
    public void test() {
        //测试信息提取
        //不订阅，不会实际发出请求，但会进入我们的代理
//        userApi.getAllUser();
//        userApi.getUserByid("1111");
//        userApi.deleteUserByid("222222");
//        userApi.createUser(Mono.just(User.builder().name("xfq").age(33).build()));

        //直接调用，实现调用rest接口的效果
//        Flux<User> users = userApi.getAllUser();
//        users.subscribe(System.out::print);

        String id = "xxxxx";
        userApi.getUserByid(id).subscribe(user -> {
            System.out.println("getUserById:" + user);
        },e -> {
            System.err.println("找不到用户："+e.getMessage());
        });
//
//        userApi.deleteUserByid(id).subscribe();

        //创建用户
//        userApi.createUser(Mono.just(User.builder().name("嘻嘻嘻").age(21).build()))
//                .subscribe(System.out::println);
    }
}
