package com.wolfisc.jmsclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PublishDStatus {

    private final ObjectMapper objectMapper;
    private final RandomDStatusGenerator statusGenerator;
    private final RabbitTemplate rabbitTemplate;
    private static final Logger log = LoggerFactory.getLogger(PublishDStatus.class);

    public PublishDStatus(ObjectMapper objectMapper, RandomDStatusGenerator statusGenerator, RabbitTemplate rabbitTemplate) {
        this.objectMapper = objectMapper;
        this.statusGenerator = statusGenerator;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelayString = "${app.send.rate}")
    @Transactional(rollbackFor = {JsonProcessingException.class})
    public void send() {
        DeviceStatus current = statusGenerator.get();
        String dStatusJson;
        try {
            dStatusJson = objectMapper.writeValueAsString(current);
            rabbitTemplate.convertAndSend(DeviceConfig.EXCHANGE, DeviceConfig.ROUTE, dStatusJson);
            log.info("Sending current data: {} as json {}", current, dStatusJson);
        } catch (Exception e) {
            log.error("Error sending the current device status: {}", e.getMessage());
        }
    }
}

