package com.batsandrey.sweater.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@Slf4j
public class RabbitMQConfig {

    @Value("${rabbitMQ.Queue.Todos}")
    String passengerRoutingQueue;
    @Value("${rabbitMQ.Exchange.Todos}")
    String passengerExchange;
    @Value("${rabbitMQ.Queue.Key.TodosQueue}")
    String passengerQueueKey;

    @Bean
    public Queue queue() {
        return new Queue(passengerRoutingQueue);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(passengerExchange, false, false);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
                .bind(queue())
                .to(exchange())
                .with(passengerQueueKey);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    @Bean
    public AmqpAdmin amqpAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
