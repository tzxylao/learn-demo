package com.tzxylao.java8.array;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * @author laoliangliang
 * @date 2019/9/6 10:51
 */
public class ListLearn {

    public static void main(String[] args) {
//        System.out.println(factorial(100000));

        ArrayList<String> strings = Lists.newArrayList("1", "2", "3");
        System.out.println(strings.stream().skip(1).limit(1).collect(Collectors.toList()));
        HashMap<String, String> map = Maps.newHashMap();
        map.put("a", "1");
        map.put("b", "2");
        map.forEach((k,v) ->{
            System.out.println(k+v);
        });
    }

    private static BigDecimal factorial(long n) {
        BigDecimal result = new BigDecimal("1");
        for (long i = 2; i <= n; i++) {
            result = result.multiply(new BigDecimal(String.valueOf(i)));
        }
        return result;
    }
}
