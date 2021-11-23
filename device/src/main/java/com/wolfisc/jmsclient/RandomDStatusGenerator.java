package com.wolfisc.jmsclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class RandomDStatusGenerator {

    private final Integer id;

    public RandomDStatusGenerator(@Value("${app.device.id}") Integer id) {
        this.id = id;
    }

    @Transactional
    public DeviceStatus get() {
        double temperature = RandomUtils.randDouble(28.5, 86.5);
        double humidity = RandomUtils.randDouble(0.3, 0.9);
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
        DeviceStatus status = DeviceStatus.builder()
                .id(id)
                .temperature(temperature)
                .humidity(humidity)
                .timestamp(timestamp)
                .build();
        return status;
    }
}
