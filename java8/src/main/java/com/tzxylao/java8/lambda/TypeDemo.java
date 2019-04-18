package com.tzxylao.java8.lambda;

/**
 * @author laoliangliang
 * @since 19/4/17 下午10:07
 */
@FunctionalInterface
interface  IMath{
    int add(int x, int y);
}

@FunctionalInterface
interface  IMath2{
    int add(int x, int y);
}
public class TypeDemo {
    public static void main(String[] args) {
        //变量类型定义
        IMath lambda = (x, y) -> x + y;

        //数组里
        IMath[] lambads = {(x, y) -> x + y};

        //强转
        Object lambda2 = (IMath)(x, y) -> x + y;

        //通过返回类型
        IMath createLambda = createLambda();

        TypeDemo demo = new TypeDemo();
        System.out.println(demo.test((IMath) (x, y) -> x + y));
    }

    public int test(IMath math) {
        return math.add(2, 3);
    }

    public int test(IMath2 math) {
        return math.add(2, 7);
    }

    public static IMath createLambda(){
        return (x, y) -> x + y;
    }
}
