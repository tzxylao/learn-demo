package com.tzxylao.webfluxclient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 类名随意，字段相同
 * @author laoliangliang
 * @since 19/4/20 上午10:57
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String id;

    private String name;

    private int age;
}
