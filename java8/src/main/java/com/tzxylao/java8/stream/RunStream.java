package com.tzxylao.java8.stream;

import java.util.Random;
import java.util.stream.Stream;

/**
 * 验证stram运行机制
 * <p>
 * 1. 所有操作是链式调用，一个元素只迭代一次
 * 2. 每一个中间操作返回一个新的流，流里面有一个属性sourceStage指向同一个地方，就是Head
 * 3. Head -> nextStage -> nextStage ->...->null
 * 4. 有状态操作（两个参数）会把无状态操作阶段单独处理
 * 5. 并行环境下，有状态的中间操作不一定能并行操作

 * 6. parallel/sequetial 这两个操作也是中间操作（也是返回stream），但是他们不创建流，他们只修改Head的并行标志
 *
 * @author laoliangliang
 * @since 19/4/18 下午10:42
 */
public class RunStream {
    public static void main(String[] args) {
        Random random = new Random();
//        Stream<Integer> stream = Stream.generate(() -> random.nextInt())
//                //产生500个（无限流需要短路操作）
//                .limit(500)
//                //第一个无状态操作
//                .peek(s -> System.out.println("peek:" + s))
//                //第二个无状态操作
//                .filter(s -> {
//                    System.out.println("filter:" + s);
//                    return s > 1000000;
//                });

        Stream<Integer> stream = Stream.generate(() -> random.nextInt())
                //产生500个（无限流需要短路操作）
                .limit(500)
                //第一个无状态操作
                .peek(s -> System.out.println(Thread.currentThread().getName() + " peek:" + s))
                //第二个无状态操作
                .filter(s -> {
                    System.out.println(Thread.currentThread().getName() + " filter:" + s);
                    return s > 1000000;
                })
                //有状态操作
                .sorted((i1, i2) -> {
                    System.out.println(Thread.currentThread().getName() + " 排序：" + i1 + "," + i2);
                    return i1.compareTo(i2);
                })
                //又一个无状态操作
                .peek(s -> {
                    System.out.println(Thread.currentThread().getName() + " peek2:" + s);
                }).parallel();

        //终止操作
        stream.count();
    }
}
