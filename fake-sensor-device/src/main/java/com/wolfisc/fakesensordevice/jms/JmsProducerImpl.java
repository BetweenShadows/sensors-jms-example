package com.wolfisc.fakesensordevice.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

public class JmsProducerImpl implements JmsProducer {

    private final String brokerUrl;
    private final String user;
    private final String pass;
    private ActiveMQConnectionFactory activeMQConnectionFactory;

    public JmsProducerImpl(String brokerUrl, String user, String pass) {
        this.brokerUrl = brokerUrl;
        this.activeMQConnectionFactory = new ActiveMQConnectionFactory(brokerUrl);
        this.user = user;
        this.pass = pass;
    }

    @Override
    public void sendMessage(String queueName, String message, QueueType queueType) {
        try {
            Connection conn = activeMQConnectionFactory.createConnection(user, pass);
            conn.start();
            Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
            MessageProducer producer;
            if(queueType == QueueType.QUEUE) {
                Queue queue = session.createQueue(queueName);
                producer = session.createProducer(queue);
            } else {
                Topic topic = session.createTopic(queueName);
                producer = session.createProducer(topic);
            }
            TextMessage textMessage = session.createTextMessage(message);
            producer.send(textMessage);

            producer.close();
            session.close();
            conn.stop();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
