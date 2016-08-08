package com.dc.sendMessage;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import com.dc.DAO.BackToTeller;
import com.dc.DAO.DoSthESB;
//ESBListenerMessage��1.����teller�����ݣ������һ��deal_compare_Id�ֶΣ�
//                                       2.����MQ��Ϣ���У�ת������
public class ESBListenerMessage extends Thread implements MessageListener{
	private Socket cs = null;
	private String ID = "" + Calendar.getInstance().getTimeInMillis();//��õ�ǰʱ��
	private BufferedReader in = null;
	private PrintWriter out = null;
	private MessageConsumer consumer = null;
	private String IP = "127.0.0.1";
	
	public ESBListenerMessage(Socket cs){
		this.cs = cs;
		try {
			this.in = new BufferedReader (new InputStreamReader(this.cs.getInputStream()));
			this.out = new PrintWriter(this.cs.getOutputStream());
			ConnectionFactory factory = 
				new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER,
						ActiveMQConnection.DEFAULT_PASSWORD, "tcp://"+ this.IP +":61616");

			Connection connection = factory.createConnection();
			connection.start();
			
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("BANK2ESB");
			
			this.consumer = session.createConsumer(destination, "MessageID='"+this.ID +"'");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//��Teller��������Ϣ
	private void readRequest(){
		try {
			if(!(this.cs.isClosed())){
				String request = this.in.readLine();
				System.out.println(request);
				//����Teller��������Ϣ;
				DoSthESB doESB = new DoSthESB(request);
				String[] toESB = doESB.doIt();
				
				System.out.println(toESB[0]);
				for(int i = 0; i < toESB.length; i++){
					System.out.println("��ϢID�ǣ�"+this.ID+",ESB������Ϣ����ESB2BANK����Ϣ��"+toESB[i]);
					System.out.println("----------------------------------------");
					// ������Ϣ����ESB2BANK����Ψһ��ʶID��������ϢtoESB[i]������Ϣ����
					new SendMessage(toESB[i], "ESB2BANK", this.ID).run();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	//������Ϣ���ص���ʽ��
	/*���÷���/���ķ�ʽ��publish / subscribe��
	*          ��Ϊ�������ĵķ�ʽ�����ܴ��ڶ�����տͻ��ˣ�
	*  ���ҽ��ն˿ͻ����뷢�Ϳͻ��˴���ʱ���ϵ�������
	*  һ�����ն�ֻ�ܽ����������Ժ��Ϳͻ��˷��͵���Ϣ��
	*  ��Ϊsubscriber ,�ڽ�����Ϣʱ�����ַ�����destination��receive������
	*  ��ʵ��message listener �ӿڵ�onMessage ������
	*/
	@Override
	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		if(message instanceof TextMessage){
			TextMessage resp = (TextMessage)message;
			try {
				//������ǰ�Ӻõ�MessageID��ǩ����ȡ�Լ�����Ϣ
				String ID = message.getStringProperty("MessageID");
				String response = (String)resp.getText();
				System.out.print("��ϢID�ǣ�" + ID);
				System.out.println(",ESBlistenerMessage�յ���Ϣ����BANK2ESB����Ϣ�ǣ�" + response);
				System.out.println("----------------------------------------");
				//
				//
				//����response;�����յ�����Ϣ���޸� ESB_LIST_TABLE ��״̬
				String back = new BackToTeller().toTeller(response);
				this.out.println(back);
				this.out.flush();
				System.out.println("ESBlistenerMessage����ǰ̨�ĵ���Ϣ�ǣ�" + back);
				System.out.println("----------------------------------------");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public void run() {		
		//���̺߳�socket��������
		try {
			this.consumer.setMessageListener(this);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		//��Teller��������Ϣ
		this.readRequest();
	}
	
}
