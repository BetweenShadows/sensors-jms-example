package com.wolfisc.fakesensordevice.deviceinfo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfisc.fakesensordevice.jms.JmsProducer;
import com.wolfisc.fakesensordevice.jms.QueueType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.TimerTask;

public class DeviceSenderTask extends TimerTask {

    private final DeviceInfoGenerator deviceInfoGenerator;
    private final ObjectMapper objectMapper;
    private final String queueName;
    private final JmsProducer producer;
    private final Logger log = LoggerFactory.getLogger(DeviceSenderTask.class);

    public DeviceSenderTask(DeviceInfoGenerator deviceInfoGenerator, ObjectMapper objectMapper, String queueName, JmsProducer producer) {
        this.deviceInfoGenerator = deviceInfoGenerator;
        this.objectMapper = objectMapper;
        this.queueName = queueName;
        this.producer = producer;
    }

    @Override
    public void run() {
        DeviceInfo deviceInfo = deviceInfoGenerator.generar();
        String deviceInfoJson = null;
        try {
            deviceInfoJson = objectMapper.writeValueAsString(deviceInfo);
        } catch (JsonProcessingException e) {
            log.info("Error occurred while sending information to queue = " + queueName + " . {}", e.getMessage());
            e.printStackTrace();
        }
        producer.sendMessage(queueName, deviceInfoJson, QueueType.TOPIC);
    }
}
