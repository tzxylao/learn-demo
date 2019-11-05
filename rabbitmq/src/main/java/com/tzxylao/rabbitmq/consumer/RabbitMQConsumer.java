package com.tzxylao.rabbitmq.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;

import javax.annotation.PostConstruct;

/**
 * @author laoliangliang
 * @date 2019/8/5 9:54
 */
//@Component
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

    @RabbitListener(bindings = @QueueBinding(
            value = @org.springframework.amqp.rabbit.annotation.Queue(value = "queue-1",
                    durable = "true"),
            exchange = @org.springframework.amqp.rabbit.annotation.Exchange(value = "exchange-1",
                    durable = "true",
                    type = "topic",
                    ignoreDeclarationExceptions = "true"),
            key = "springboot.*"
    )
    )
    @RabbitHandler
    public void onMessage(Message message, Channel channel) throws Exception {
        System.err.println("--------------------------------------");
        System.err.println("消费端Payload: " + message.getPayload());
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        //手工ACK,获取deliveryTag
        channel.basicAck(deliveryTag, false);
    }
}
