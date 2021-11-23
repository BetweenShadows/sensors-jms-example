package com.wolfisc.devicestatistics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class DStatusConsumer {

    private final SimpMessagingTemplate simpMessagingTemplate;
    public static final Logger log = LoggerFactory.getLogger(DStatusConsumer.class);

    public DStatusConsumer(SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    @RabbitListener(queues = "notificacion_sensores")
    public void get(final String message) {
        simpMessagingTemplate.convertAndSend("/topic/info_sensores", message);
    }
}
