package com.tzxylao.java8.thread;

/**
 * @author laoliangliang
 * @date 2019/10/23 16:16
 */
public class ThreadLocalLearn {
    public static void main(String[] args) {
        ThreadLocal<String> local = new ThreadLocal<>();
        local.set("hello world!");
        System.out.println(local.get());
    }
}
