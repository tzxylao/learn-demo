package com.tzxylao.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author laoliangliang
 * @date 2019/10/25 17:05
 */
@Component
@RabbitListener(queues = "queueCall")
public class TopicReceiver {

    @RabbitHandler
    public void process(String message) {
        System.out.println("TopicReceiver ：" + message);
    }
}
