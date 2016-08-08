package com.dc.bean;

import com.dc.DAO.AddFlowOrChangeState;
import com.dc.DAO.ChangePsw;
//import com.dc.DAO.GetAccount;
import com.dc.DAO.SplitString;

public class DoChangePsw implements DoSthBank {

	@Override
	public String doIt(String messages) {
		
		System.out.println(" messages=: "+messages);
		//String[] acc = null;
		String back = "BANK_LIST_ID=";
		String[][] mess = new SplitString(messages).getMessages();
		AddFlowOrChangeState addFlowOrChange = new AddFlowOrChangeState();
		int listId = addFlowOrChange.AddFlowToTable(messages);
		//添加流水号
		System.out.println("Bank端：流水号插入成功");
		back = back + listId;
		//调用存储过程，真正修改密码
		ChangePsw change = new ChangePsw();
		//判断密码修改是否成功
		boolean res=change.changePsw(mess[4][1], mess[5][1], mess[6][1], mess[7][1]);
		
		if(res){
			//GetAccount getAccount = new GetAccount();
			//acc = getAccount.getAcc(mess[4][1]);//?????
			addFlowOrChange.changeFlowState(listId, "0");
			System.out.println("Bank端：修改流水成功");
			back = back + ";BANK_STATE=0;" +mess[0][0]+"="+mess[0][1]+";ACC_ID="+mess[4][1]+";ACC_PWD="+mess[7][1];
		}else{
			back = back + ";BANK_STATE=1;" +mess[0][0]+"="+mess[0][1]+";ACC_ID=-1";
		}
		
		return back;
	}
	public static void main(String[] args) {
		DoChangePsw change = new DoChangePsw();
		System.out.println("result: "+change.doIt("ESB_ID=123;deal_compare_Id=15;server_id=15;flow_no=852;card_id=220;guest_id=2;card_password=000000;new_password=220"));
	}
}
