package com.dc.bean;

import com.dc.DAO.FindAccountDetail;
import com.dc.DAO.SplitString;

public class DoSearch implements DoSthBank {

	@Override
	public String doIt(String messages) {

		String back = "BANK_LIST_ID=null";
		String[][] mess = new SplitString(messages).getMessages();
		
		FindAccountDetail findAccount = new FindAccountDetail();
		String result = null;
		//�����ˮ��
		
		result = findAccount.find(mess[4][1]);
		if(!result.equals("")){
			System.out.println("Bank�ˣ���ѯ�ɹ�");
			back = back + ";BANK_STATE=0;" +mess[0][0]+"="+mess[0][1]+";ACC_message="+result;
		}else{
			back = back + ";BANK_STATE=1;" +mess[0][0]+"="+mess[0][1]+";ACC_ID=-1";
		}
		return back;
	}

}
