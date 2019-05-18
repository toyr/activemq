package com.example.demo.activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.transport.stomp.StompConnection;

/**
 * @author unisk1123
 * @Description
 * @create 2019/5/18
 */
public class StompProducer {


    public static void main(String[] args) {
        StompConnection connection = new StompConnection();
        try {
            connection.open("localhost", 61613);
            connection.connect(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD);
            String message = "<a href=\"https://www.baidu.com\" target=\"_blank\">微醺好时光,哇哈啊哈哈哈</a>";
            // 使用stomp发送消息
            connection.send("/topic/shopping-discount", message);
            connection.disconnect();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
