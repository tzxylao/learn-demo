package com.tzxylao.java8.pojo;

/**
 * @author laoliangliang
 * @date 2019/9/30 11:05
 */
public interface DTOConvert<S,T> {
    T convert(S s);
}
