package com.tzxylao.rabbitmq.consumer;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author laoliangliang
 * @date 2019/8/5 9:54
 */
@Component
public class RabbitMQConsumer {

    @Autowired
    private AmqpAdmin amqpAdmin;
    @Autowired
    private AmqpTemplate amqpTemplate;
    /**
     * 经测试，直接挂了
     * 创建一堆消费者，超过65565
     */
    @PostConstruct
    public void init() {
        TopicExchange topicExchange = new TopicExchange("MaxConnect-test");
        amqpAdmin.declareExchange(topicExchange);

        for (int i = 0; i < 66000; i++) {
            String queueName = "myqueue-" + i;
            Queue queue = new Queue(queueName, false, false, true);
            amqpAdmin.declareQueue(queue);
            Binding b = BindingBuilder.bind(queue).to(topicExchange).with("test");
            amqpAdmin.declareBinding(b);
        }
    }
}
