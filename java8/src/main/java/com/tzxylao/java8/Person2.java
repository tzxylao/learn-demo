package com.tzxylao.java8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author laoliangliang
 * @date 2019/4/4 14:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person2 {
    private String name;

    private int age;

    public volatile Integer i = 0;
}
