package com.tzxylao.spring;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * @author laoliangliang
 * @since 19/4/21 下午7:44
 */
@Component
@Data
public class Person {
    public Person() {
        System.out.println("无参函数Person");
    }

    private String name;


}
