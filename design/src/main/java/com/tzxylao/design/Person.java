package com.tzxylao.design;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author laoliangliang
 * @since 19/4/29 下午10:10
 */
@Data
@ToString
@Accessors(chain = true)
public class Person {
    private String name;

    private int age;
}
