package com.tzxylao.java8;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author laoliangliang
 * @date 2019/4/8 16:23
 */
public class AtomicTests {

    public static void main(String[] args) {
        Person2 p1 = new Person2();
        p1.setName("p1");
        p1.setAge(12);
        AtomicReferenceFieldUpdater<Person2, Integer> fieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Person2.class, Integer.class, "i");
        fieldUpdater.compareAndSet(p1, 0, 13);
        System.out.println(p1.getI());
    }
}
