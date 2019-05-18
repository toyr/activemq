package com.example.demo.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author unisk1123
 * @Description
 * @create 2019/5/18
 */
public class TopicPublisher {

    public static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    public static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    public static final String BROKER_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public static void main(String[] args) {
        // 创建连接工厂
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKER_URL);
        try {
            // 创建连接
            Connection connection = factory.createConnection();
            // 开启连接
            connection.start();
            // 创建回话， 不需要事务
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            // 创建主题，用作消费者订阅消息
            Topic topic = session.createTopic("activemq-topic-test1");
            // 消息生产者
            MessageProducer producer = session.createProducer(topic);
            // 生成消息
            for (int i = 0; i < 3; i++) {
                TextMessage textMessage = session.createTextMessage("发送消息" + i);
                producer.send(textMessage);
            }
            // 关闭资源
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
