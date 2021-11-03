package com.wolfisc.fakesensordevice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.github.lalyos.jfiglet.FigletFont;
import com.wolfisc.fakesensordevice.config.Environment;
import com.wolfisc.fakesensordevice.deviceinfo.DeviceInfoGenerator;
import com.wolfisc.fakesensordevice.deviceinfo.DeviceSenderTask;
import com.wolfisc.fakesensordevice.jms.JmsProducer;
import com.wolfisc.fakesensordevice.jms.JmsProducerImpl;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class WeatherSensorApplication {

    public static void main(String[] args) throws IOException {
        String brokenUrl = Environment.get(Environment.BROKEN_URL, String.class);
        String jmsUser = Environment.get(Environment.JMS_USER, String.class);
        String jmsPass = Environment.get(Environment.JMS_PASS, String.class);
        String queueName = Environment.get(Environment.QUEUE_NAME, String.class);
        int sendEveryMs = Environment.get(Environment.SEND_INFO_EVERY_MS, Integer.class);
        int deviceId = Environment.get(Environment.DEVICE_ID, Integer.class);

        // Printing out banner logo of application...
        String banner = FigletFont.convertOneLine(String.format("Device Sensor (%d)", deviceId));
        System.out.println(banner);

        Faker faker = new Faker();
        ObjectMapper objectMapper = new ObjectMapper();
        DeviceInfoGenerator deviceInfoGenerator = new DeviceInfoGenerator(faker);
        JmsProducer producer = new JmsProducerImpl(brokenUrl, jmsUser, jmsPass);

        Timer sendDeviceInfoJob = new Timer();
        TimerTask sender = new DeviceSenderTask(deviceInfoGenerator, objectMapper, queueName, producer);
        sendDeviceInfoJob.scheduleAtFixedRate(sender, sendEveryMs, sendEveryMs);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            sendDeviceInfoJob.cancel();
            sendDeviceInfoJob.purge();
        }));
    }
}
