package com.tzxylao.java8.random;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author laoliangliang
 * @date 2019/9/6 15:56
 */
public class RandomLearn {
    public static ThreadLocalRandom random = ThreadLocalRandom.current();

    public static void main(String[] args) {
        System.out.println(random.nextInt(100));

        LongAdder longAdder = new LongAdder();
        longAdder.increment();
        System.out.println(longAdder.longValue());

        String str = null;
        Optional<String> optional = Optional.ofNullable(str);
        System.out.println(optional.isPresent());
        str = "123";
        optional = Optional.ofNullable(str);
        System.out.println(optional.isPresent());
    }
}
