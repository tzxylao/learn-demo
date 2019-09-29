package com.tzxylao.java8.single;

import java.util.concurrent.atomic.AtomicReference;

/**
 * cas实现单例
 *
 * @author laoliangliang
 * @date 2019/9/9 15:27
 */
public class Singleton {
    private static final AtomicReference<Singleton> INSTANCE = new AtomicReference<>();

    private Singleton() {
    }

    public Object readResolver() {
        return INSTANCE.get();
    }

    public static Singleton getInstance() {
        for (; ; ) {
            Singleton singleton = INSTANCE.get();
            if (singleton != null) {
                return singleton;
            }
            singleton = new Singleton();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }
}
