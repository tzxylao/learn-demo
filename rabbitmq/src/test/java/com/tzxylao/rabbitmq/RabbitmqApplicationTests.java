package com.tzxylao.rabbitmq;

import com.tzxylao.rabbitmq.provider.RabbitMQProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RabbitMQProvider rabbitMQProvider;

    @Test
    public void contextLoads() {
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
            amqpTemplate.convertAndSend("hello", "hello world " + i);
        }
    }

    @Test
    public void sendTopic() {
        Map<String, Object> objectObjectMap = new HashMap<>();
        rabbitMQProvider.send("topic test", objectObjectMap);

    }

}
