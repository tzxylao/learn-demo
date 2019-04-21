package com.tzxylao;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;
import java.util.concurrent.TimeUnit;

/**
 * @author laoliangliang
 * @since 19/4/19 下午8:31
 */
public class FlowDemo {
    public static void main(String[] args) {
        //1. 定义发布者，发布的数据是Integer类型
        //直接使用jdk自带的SubmissionPublisher，它实现了Publisher接口
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        //2. 定义订阅者
        Flow.Subscriber<Integer> subscriber = new Flow.Subscriber<Integer>() {
            private Flow.Subscription subscription;

            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                //建立订阅关系时调用，保存订阅关系，需要用它来给发布者响应
                this.subscription = subscription;

                //请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(Integer item) {
                //接收到一个数据，处理
                System.out.println("接收到数据：" + item);

                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //处理完调用request再请求一个数据
                this.subscription.request(1);

                //或者已经达到了目标，调用cancal告诉发布者不再接受数据了
//                this.subscription.cancel();
            }

            @Override
            public void onError(Throwable throwable) {
                //出现异常
                throwable.printStackTrace();
                this.subscription.cancel();
            }

            @Override
            public void onComplete() {
                //数据全部处理完会调用，close后调用
                System.out.println("处理完了！");
            }
        };

        //3. 发布者和订阅者建立订阅关系
        publisher.subscribe(subscriber);

        //4. 产生数据并发布
        //submit是个block方法
        for (int i = 0; i < 1000; i++) {
            System.out.println("生产数据："+i);
            publisher.submit(i);
        }

        //5. 结束后，关闭发布者
        publisher.close();

        try {
            Thread.currentThread().join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
