package com.tzxylao.java8.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author laoliangliang
 * @date 2019/4/4 14:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Person {
    private String name;

    private int age;

    public void close(){
        System.out.println("close");
    }
}
