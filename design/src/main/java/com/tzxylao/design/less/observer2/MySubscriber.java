package com.tzxylao.design.less.observer2;

import com.tzxylao.design.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Flow;

/**
 * @author laoliangliang
 * @since 19/4/29 下午10:10
 */
@Slf4j
public class MySubscriber implements Flow.Subscriber<Person> {
    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription = subscription;
        log.info("onSubscribe");
        subscription.request(1);
    }

    @Override
    public void onNext(Person item) {
        log.info("onNext:{}",item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        log.info("error",throwable);
        subscription.cancel();
    }

    @Override
    public void onComplete() {
        log.info("onComplete");
    }
}
