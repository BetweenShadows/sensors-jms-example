package com.wolfisc.jmsclient;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceConfig {

    public static final String EXCHANGE = "sensores";
    public static final String ROUTE = "datos_dispositivo";
    public static final String QUEUE = "notificacion_sensores";

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        return mapper;
    }

    @Bean
    public TopicExchange topicExchange() {
        TopicExchange exchange = new TopicExchange(EXCHANGE);
        return exchange;
    }

    @Bean
    public Queue defaultQueue() {
        Queue queue = new Queue(QUEUE);
        return queue;
    }

    @Bean
    public Binding queueToExchangeBinding(TopicExchange exchange, Queue queue) {
        Binding binding = BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(ROUTE);
        return binding;
    }
}
