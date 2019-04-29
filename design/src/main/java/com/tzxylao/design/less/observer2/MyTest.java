package com.tzxylao.design.less.observer2;

import com.tzxylao.design.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.SubmissionPublisher;

/**
 * 这个用来封装发布订阅，监管者模式比较好用
 *
 * @author laoliangliang
 * @since 19/4/29 下午10:11
 */
@Slf4j
public class MyTest {

    public static void main(String[] args) {
        MySubscriber subscriber = new MySubscriber();
        SubmissionPublisher<Person> publisher = new SubmissionPublisher<>();
        publisher.subscribe(subscriber);
        for (int i = 0; i < 100; i++) {
            publisher.submit(new Person().setAge(i).setName("tom" + i));
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
