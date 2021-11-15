package com.wolfisc.embeddedjms;

import org.apache.activemq.broker.BrokerService;

public class EmbeddedJmsApplication {

    public static void main(String[] args) throws Exception {
        BrokerService broker = new BrokerService();
        broker.setPersistent(false);
        broker.addConnector("tcp://0.0.0.0:61616");
        broker.addConnector("ws://0.0.0.0:61617");
        broker.start();
    }
}
