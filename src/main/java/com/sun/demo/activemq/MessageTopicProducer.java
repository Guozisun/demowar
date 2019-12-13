package com.sun.demo.activemq;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.jms.MessageProducer;

/**
 * Title:主题模型的消息
 * Description:
 * Copyright: 2019 北京拓尔思信息技术股份有限公司 版权所有.保留所有权
 * Company:北京拓尔思信息技术股份有限公司(TRS)
 * Project: demo
 * Author: 果子
 * Create Time:2019/12/10 13:58
 */
public class MessageTopicProducer {
    //定义ActiveMQ的链接地址
    private static final String ACTIVEMQ_URL="tcp://127.0.0.1:61616";
    //定义发送消息的主题名称
    private static final String TOPIC_NAME="MyTopicMessage";

    public static void main(String[] args) throws JMSException {
        //创建链接工厂
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ACTIVEMQ_URL);
        //创建链接
        Connection connection = activeMQConnectionFactory.createConnection();
        //开启链接
        connection.start();
        //开启会话
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        //创建队列目标
        Destination destination = session.createTopic(TOPIC_NAME);
        //创建生产者
        MessageProducer messageProducer = session.createProducer(destination);
        //创建模拟100个消息
        for (int i = 1; i <= 100; i++) {
            TextMessage message = session.createTextMessage("当前message是(主题模型):" + i);
            //发送消息
            messageProducer.send(message);
            //在本地打印消息
            System.out.println("我现在发的消息是：" + message.getText());
        }
        //关闭连接
        connection.close();

    }
}
