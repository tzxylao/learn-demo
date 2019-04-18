package com.tzxylao.java8.lambda;

import java.util.function.*;

/**
 * @author laoliangliang
 * @since 19/4/17 下午9:38
 */

class Dog {
    private String name = "狗";

    private int food = 10;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    public static void bark(Dog dog) {
        System.out.println(dog + "叫了");
    }

    public int eat(int num) {
        food -= num;
        System.out.println("吃了：" + num + "斤狗粮");
        return this.food;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

public class MethodRefrenceDemo {
    public static void main(String[] args) {
        //方法引用
        Consumer<String> consumer = System.out::println;
        consumer.accept("hello world");

        //静态方法的方法引用
        Consumer<Dog> consumer1 = Dog::bark;
        Dog dog = new Dog();
        consumer1.accept(dog);

        //非静态方法，使用对象实例的方法引用
        UnaryOperator<Integer> unaryOperator = dog::eat;
        unaryOperator.apply(4);

        //使用类名来方法引用
        BiFunction<Dog, Integer, Integer> eat = Dog::eat;
        eat.apply(dog, 3);

        //构造函数的方法引用
        Supplier<Dog> aNew = Dog::new;
        Dog dog1 = aNew.get();
        System.out.println("创建了新对象："+aNew.get());

        //带参数的构造函数的方法引用
        Function<String,Dog> function = Dog::new;
        System.out.println("创建了新对象："+function.apply("Tom"));

    }
}
