package com.tzxylao.java8.time.delay;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author laoliangliang
 * @date 2019/10/21 16:44
 */
@Data
@AllArgsConstructor
public class Order {

    private Long id;

    private Date createDate;
}
