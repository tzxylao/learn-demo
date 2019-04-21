package com.tzxylao.util;

import com.tzxylao.exception.CheckException;

import java.util.stream.Stream;

/**
 * @author laoliangliang
 * @since 19/4/20 下午12:14
 */
public class CheckUtil {

    private static final String[] INVALID_NAMES = {"admin", "guanliyuan"};
    /**
     * 校验名字，不成功抛出校验异常
     */
    public static void checkName(String value) {
        Stream.of(INVALID_NAMES).filter(name -> name.equalsIgnoreCase(value))
                .findAny().ifPresent(name -> {
            throw new CheckException("name", value);
        });
    }
}
