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
			// ����ConnectionFactoryʵ�����󣬴˴�����ActiveMq��ʵ��
			ConnectionFactory factory = 
				new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
						ActiveMQConnection.DEFAULT_PASSWORD, "tcp://"+ this.IP +":61616");
			// ����ӹ����õ����Ӷ���
			Connection connection = factory.createConnection();
			// ����
			connection.start();
			// ��ȡ��������
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			// ��ȡsessionע�����ֵ destination ��һ����������queue������ActiveMq��console����
			Destination destination = session.createQueue(this.destination);
			//�õ���Ϣ
			TextMessage message = session.createTextMessage(this.request);
			//����Ϣ�� MessageID ��ǩ�������������֮һ���Լ�ֻ�����Լ����İ���
			message.setStringProperty("MessageID", this.ID);
			// �õ���Ϣ�����ߡ������ߡ�
			MessageProducer producer = session.createProducer(destination); 
			// ������Ϣ�� ��Ϣ����
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
