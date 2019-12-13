package com.sun.demo.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.xml.soap.Text;

/**
 * Title:
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: demo
 * Author: 果子
 * Create Time:2019/12/10 11:33
 */
public class MessageProducer {
    //定义ActivMQ的连接地址
    private static final String ACTIVEMQ_URL = "tcp://127.0.0.1:61616";
    //定义发送消息的队列名称
    private static final String QUEUE_NAME = "MyMessage";

    public static void main(String[] args) throws JMSException {
        //创建连接工厂
//        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //创建连接
//        Connection connection = activeMQConnectionFactory.createConnection();
        Connection connection = activeMQConnectionFactory.createConnection();
        //打开连接
//        connection.start();
        connection.start();
        //创建会话
//        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建队列目标
//        Destination destination = session.createQueue(QUEUE_NAME);
        Destination destination = session.createQueue(QUEUE_NAME);
        //创建一个生产者
//        javax.jms.MessageProducer producer = session.createProducer(destination);
        javax.jms.MessageProducer producer = session.createProducer(destination);
        //创建模拟100个消息
//        for (int i = 1 ; i <= 100 ; i++){
        for (int i=1;i<=100;i++) {
//            TextMessage message = session.createTextMessage("我发送message:" + i);
            TextMessage message = session.createTextMessage("我发送message：" + i);
            //发送消息
//            producer.send(message);
            producer.send(message);
            //在本地打印消息
//            System.out.println("我现在发的消息是：" + message.getText());
            System.out.println("我现在发的消息是：" + message.getText());
//        }
        }
        //关闭连接
//        connection.close();
        connection.close();
    }
}
