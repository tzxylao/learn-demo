package com.tzxylao.rabbitmq.provider;

import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author laoliangliang
 * @date 2019/8/5 9:54
 */
@Component
public class RabbitMQProvider {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(Object message, Map<String, Object> properties) {
        MessageHeaders mhs = new MessageHeaders(properties);
        Message<Object> msg = MessageBuilder.createMessage(message, mhs);
        CorrelationData correlationData = new CorrelationData("111111");
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("correlationData: " + correlationData);
                System.out.println("ack: " + ack);
                System.out.println("cause: " + cause);
            }
        });
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(org.springframework.amqp.core.Message message, int replyCode, String replyText,
                                        String exchange,
                                        String routingKey) {
                System.out.println("return exchange: " + exchange + ", routingKey: " + routingKey + " , replyCode: " + replyCode + ", replyText: " + replyText);
            }
        });
        rabbitTemplate.convertAndSend("exchange-1", "springboot.a", msg, correlationData);
    }

}
