package com.wolfisc.fakesensordevice.jms;

public interface JmsProducer {
    void sendMessage(String queueName, String message, QueueType queueType);
}
