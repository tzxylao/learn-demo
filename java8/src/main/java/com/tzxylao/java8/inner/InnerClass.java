package com.tzxylao.java8.inner;

/**
 * 内部类
 * @author laoliangliang
 * @date 2019/9/10 10:32
 */
public class InnerClass {


    public static void main(String[] args) {
        InnerClass inner = new InnerClass();
        final Integer prop = 1;
        inner.addListener(new IClass() {
            @Override
            public void onEvent() {
                System.out.println("onEvent" +prop);
            }
        });

    }

    public void addListener(IClass iClass){
        iClass.onEvent();
    }

}
