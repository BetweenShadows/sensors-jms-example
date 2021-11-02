package com.wolfisc.fakesensordevice.deviceinfo;

import com.github.javafaker.Faker;
import com.wolfisc.fakesensordevice.config.Environment;
import com.wolfisc.fakesensordevice.utils.Math;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeviceInfoGenerator {

    private final Faker faker;
    private final int MIN_DEVICE_TEMP = 0;
    private final int MAX_DEVICE_TEMP = 70;

    public DeviceInfoGenerator(Faker faker) {
        this.faker = faker;
    }

    public DeviceInfo generar() {
        int idDispositivo = Environment.get(Environment.DEVICE_ID, Integer.class);
        double temperatura = (faker.random().nextInt(MIN_DEVICE_TEMP, MAX_DEVICE_TEMP)) + faker.random().nextDouble();
        double humedad = faker.random().nextDouble();
        temperatura = Math.round(temperatura);
        humedad = Math.round(humedad);
        String fechaGeneracion = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        DeviceInfo deviceInfo = new DeviceInfo(idDispositivo, temperatura, humedad, fechaGeneracion);
        return deviceInfo;
    }
}
