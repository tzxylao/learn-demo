package com.tzxylao.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author laoliangliang
 * @date 2019/10/25 15:30
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue queue(){
        return new Queue("hello");
    }

    @Bean
    public Queue queueCall(){
        return new Queue("queueCall");
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("exchange-1");
    }

    @Bean
    public Binding topicExchangeBinding(Queue queueCall,TopicExchange topicExchange){
        return BindingBuilder.bind(queueCall).to(topicExchange).with("springboot.#");
    }
}
