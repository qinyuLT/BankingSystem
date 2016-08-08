package com.dc.sendMessage;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

public class SendMessage extends Thread{
	private String request = null;
	private String destination = null;
	private String ID = null;
	private String IP = "127.0.0.1";
	
	public SendMessage(String req, String des, String ID) {
		this.request = req;
		this.destination = des;
		this.ID = ID;
	}
	
	public void send2Message(){
		try {
			// 构造ConnectionFactory实例对象，此处采用ActiveMq的实现
			ConnectionFactory factory = 
				new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
						ActiveMQConnection.DEFAULT_PASSWORD, "tcp://"+ this.IP +":61616");
			// 构造从工厂得到连接对象
			Connection connection = factory.createConnection();
			// 启动
			connection.start();
			// 获取操作连接
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// 获取session注意参数值 destination 是一个服务器的queue，须在ActiveMq的console配置
			Destination destination = session.createQueue(this.destination);
			//得到消息
			TextMessage message = session.createTextMessage(this.request);
			//给消息加 MessageID 标签，解决串包问题之一（自己只能收自己发的包）
			message.setStringProperty("MessageID", this.ID);
			// 得到消息生成者【发送者】
			MessageProducer producer = session.createProducer(destination); 
			// 发送消息到 消息队列
			producer.send(message);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		this.send2Message();
	}
}
