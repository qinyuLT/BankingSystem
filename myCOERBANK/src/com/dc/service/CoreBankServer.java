package com.dc.service;

import com.dc.receiveMessage.BANKListenerMessage;

public class CoreBankServer {

	public static void main(String[] args) {
		//����������Ϣ�ķ���
		new BANKListenerMessage().start();
	}

}
