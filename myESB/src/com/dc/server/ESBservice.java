package com.dc.server;

import java.net.ServerSocket;
import java.net.Socket;

import com.dc.sendMessage.ESBListenerMessage;

public class ESBservice {

	public static void main(String[] args) {

		try {
			System.out.println("欢迎光临 来到 ESB 监听------------------------------------------------------");
			//创建与Teller端的socket通信
			ServerSocket serverSocket = new ServerSocket(9999);
			//循环等待Teller端的socket连接
			while(true){
				Socket cs = serverSocket.accept();       // 一直阻塞
				//ESBListenerMessage：1.处理teller端数据（拆包加一个deal_compare_Id字段）
				//                                       2.创建MQ消息队列，转发数据
				new ESBListenerMessage(cs).start();   // 直到有新的socket连接，开一个线程处理
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

}
