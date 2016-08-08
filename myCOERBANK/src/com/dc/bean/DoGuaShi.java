package com.dc.bean;

import com.dc.DAO.AddFlowOrChangeState;
//import com.dc.DAO.AddProof;
//import com.dc.DAO.AddProofDetail;
//import com.dc.DAO.ChangeAccountBalance;
//import com.dc.DAO.CheckAccount;
//import com.dc.DAO.FindAccountDetail;
//import com.dc.DAO.FindSubject;
import com.dc.DAO.SplitString;

import com.dc.DAO.ChangeAccountState;

public class DoGuaShi implements DoSthBank {

	public String doIt(String messages) {
		// TODO Auto-generated method stub
		String back = "BANK_LIST_ID=";
		String[][] mess = new SplitString(messages).getMessages();
		AddFlowOrChangeState addFlowOrChange = new AddFlowOrChangeState();
		int listId = addFlowOrChange.AddFlowToTable(messages);
		//
		System.out.println("Bank端：已经确认！");
		back = back + listId;
		 ChangeAccountState  Changeaccountstate = new  ChangeAccountState();
		
		int check = ChangeAccountState.updateChangeState(mess[5][1]);
		if(check == 0)
		{
			back = back + ";BANK_STATE=0;" +mess[0][0]+"="+mess[0][1]+";TELLER_FLOW="+mess[3][1];
			System.out.println("挂失成功！");
		}
	
	else {
		back = back + ";BANK_STATE=1;" +mess[0][0]+"="+mess[0][1]+";TELLER_FLOW="+mess[3][1];
	
		}
			
		return back;
	}		
}