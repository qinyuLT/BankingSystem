package com.dc.bean;

public class SplitString {
	
	private String message = null;
	
	public SplitString(String request){
		this.message = request;
	}
	
	public String[][] getMessages(){
		String[] requests = this.message.split(";");
		String[][] messages = new String[requests.length][];
		for(int i = 0; i < requests.length; i++){
			messages[i] = requests[i].split("=");
		}
		return messages;
	}
}
