package com.tzxylao.design.less.observer3;

/**
 * @author laoliangliang
 * @date 2019/7/19 9:50
 */
public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        ObserverA observerA = new ObserverA();
        subject.setState(2);
        subject.addObserver(observerA);
        subject.setState(1);
    }
}
