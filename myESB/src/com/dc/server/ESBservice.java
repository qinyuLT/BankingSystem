package com.dc.server;

import java.net.ServerSocket;
import java.net.Socket;

import com.dc.sendMessage.ESBListenerMessage;

public class ESBservice {

	public static void main(String[] args) {

		try {
			System.out.println("��ӭ���� ���� ESB ����------------------------------------------------------");
			//������Teller�˵�socketͨ��
			ServerSocket serverSocket = new ServerSocket(9999);
			//ѭ���ȴ�Teller�˵�socket����
			while(true){
				Socket cs = serverSocket.accept();       // һֱ����
				//ESBListenerMessage��1.����teller�����ݣ������һ��deal_compare_Id�ֶΣ�
				//                                       2.����MQ��Ϣ���У�ת������
				new ESBListenerMessage(cs).start();   // ֱ�����µ�socket���ӣ���һ���̴߳���
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
