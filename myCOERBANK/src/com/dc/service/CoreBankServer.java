package com.dc.service;

import com.dc.receiveMessage.BANKListenerMessage;

public class CoreBankServer {

	public static void main(String[] args) {
		//开启监听消息的服务
		new BANKListenerMessage().start();
	}

}
