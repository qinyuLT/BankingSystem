package com.dc.receiveMessage;

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
			ConnectionFactory factory = 
				new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
						ActiveMQConnection.DEFAULT_PASSWORD, "tcp://"+ this.IP +":61616");
			Connection connection = factory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(this.destination);
			
			TextMessage message = session.createTextMessage(this.request);
			message.setStringProperty("MessageID", this.ID);
			//消息的生产者
			MessageProducer producer = session.createProducer(destination); 
			producer.send(message);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		this.send2Message();
	}
	
}
