package com.tzxylao.java8.lambda;

import java.text.DecimalFormat;
import java.util.function.Function;

/**
 * @author laoliangliang
 * @since 19/4/17 下午9:06
 */

interface  IMoneyFormat{
    String format(int i);
}
public class MyMoney {
    private final int money;

    public MyMoney(int money) {
        this.money = money;
    }

    public void printMoney(Function<Integer,String> moneyFormat) {
        System.out.println("我的存款： "+moneyFormat.apply(this.money));
    }

    public static void main(String[] args) {
        MyMoney me = new MyMoney(99999999);
        Function<Integer, String> format = i -> new DecimalFormat("#,###").format(i);

        //函数接口链式操作
        me.printMoney(format.andThen(s -> "人民币："+s));
    }

}
