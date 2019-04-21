package com.tzxylao;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

import java.util.concurrent.TimeUnit;

/**
 * @author laoliangliang
 * @since 19/4/19 下午10:43
 */
public class ReactorDemo {
    public static void main(String[] args) {
        //reactor=jdk8 stream + jdk9 reactive stream
        //Mono 0-1个元素
        //Flux 0-N个元素
        String[] strs = {"1", "2", "3"};

        Subscriber<Integer> subscriber = new Subscriber<Integer>() {
            private Subscription subscription;

            @Override
            public void onSubscribe(Subscription subscription) {
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
                    TimeUnit.SECONDS.sleep(2);
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


        Flux.fromArray(strs).map(s -> Integer.parseInt(s))
                //最终操作
                //这里就是jdk9的reactive stream
                .subscribe(subscriber);
    }
}
