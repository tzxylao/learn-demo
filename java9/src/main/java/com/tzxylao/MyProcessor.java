package com.tzxylao;

import java.util.concurrent.Flow;
import java.util.concurrent.SubmissionPublisher;

/**
 * Processor，需要继承SubmissionPublisher并实现Processor接口
 *
 * 输入源数据integer，过滤掉小于0的，然后转换成字符串发布出去
 *
 * @author laoliangliang
 * @since 19/4/19 下午9:30
 */
public class MyProcessor extends SubmissionPublisher<String> implements Flow.Processor<Integer,String> {

    private Flow.Subscription subscription;


    public static void main(String[] args) {
        //1. 定义发布者，发布的数据类型是Integer
        //直接使用jdk自带的SubmissionPublisher
        SubmissionPublisher<Integer> publisher = new SubmissionPublisher<>();

        //2. 定义处理器，对数据进行过滤，并转换为String类型
        MyProcessor processor = new MyProcessor();

        //3. 发布者 和 处理器建立订阅关系
        publisher.subscribe(processor);

        //4. 定义最终订阅者，消费String类型数据
        Flow.Subscriber<String> subscriber = new Flow.Subscriber<>(){

            private Flow.Subscription subscription;
            @Override
            public void onSubscribe(Flow.Subscription subscription) {
                //建立订阅关系时调用，保存订阅关系，需要用它来给发布者响应
                this.subscription = subscription;

                //请求一个数据
                this.subscription.request(1);
            }

            @Override
            public void onNext(String item) {
                //接收到一个数据，处理
                System.out.println("2接收到数据：" + item);

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
                System.out.println("2处理完了！");
            }
        };

        //5. 处理器 和 最终订阅者 建立订阅关系
        processor.subscribe(subscriber);

        //6. 生产数据，并发布
        //这里忽略数据生产过程
        publisher.submit(-111);
        publisher.submit(111);
        publisher.submit(123);
        publisher.submit(234);

        //7. 结束后关闭发布者
        publisher.close();
        try {
            Thread.currentThread().join(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        //保存订阅关系，需要用它来给发布者响应
        this.subscription = subscription;

        //请求一个数据
        this.subscription.request(1);
    }

    @Override
    public void onNext(Integer item) {
        //接收到一个数据，处理
        System.out.println("1处理器接收到数据："+item);

        //过滤掉小于0的，然后发布出去
        if (item > 0) {
            this.submit("1转换后的数据：" + item);
        }

        //处理完调用request再请求一个数据
        this.subscription.request(1);

        //或者已经达到目标，调用cancel告诉发布者不再接受数据了
//        this.subscription.cancel();
    }

    @Override
    public void onError(Throwable throwable) {
        throwable.printStackTrace();
        this.subscription.cancel();
    }

    @Override
    public void onComplete() {
        System.out.println("1处理完了！");
    }
}
