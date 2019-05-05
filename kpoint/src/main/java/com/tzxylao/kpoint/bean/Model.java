package com.tzxylao.kpoint.bean;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author laoliangliang
 * @date 2019/5/5 14:36
 */
@Data
@Accessors(chain = true)
@ToString
public class Model {
    private int id;
    private String name;
}
