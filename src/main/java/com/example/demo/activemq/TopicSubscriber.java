package com.example.demo.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author unisk1123
 * @Description
 * @create 2019/5/18
 */
public class TopicSubscriber {

    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory();

        try {
            Connection connection = factory.createConnection();
            connection.start();
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("activemq-topic-test1");
            MessageConsumer consumer = session.createConsumer(topic);
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println("消费者1接受消息：" + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            MessageConsumer consumer2 = session.createConsumer(topic);
            consumer2.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println("消费者2接受消息：" + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            MessageConsumer consumer3 = session.createConsumer(topic);
            consumer3.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    try {
                        System.out.println("消费者3接受消息：" + ((TextMessage) message).getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            });

            Thread.sleep(100*1000);
            session.close();
            connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
