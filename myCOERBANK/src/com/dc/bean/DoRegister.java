package com.dc.bean;
import com.dc.DAO.AddFlowOrChangeState;
import com.dc.DAO.AddUser;
import com.dc.DAO.GetAccount;
import com.dc.DAO.SplitString;

public class DoRegister implements DoSthBank {

	@Override
	public String doIt(String messages) {
		// TODO Auto-generated method stub
		String acc = null;
		String back = "BANK_LIST_ID=";
		String[][] mess = new SplitString(messages).getMessages();
		AddFlowOrChangeState addFlowOrChange = new AddFlowOrChangeState();
		int listId = addFlowOrChange.AddFlowToTable(messages);
		//添加流水号
		System.out.println("Bank端：流水号插入成功");
		back = back + listId;
		AddUser addUser = new AddUser();
		int n = addUser.add(mess[4][1], mess[5][1], Integer.parseInt(mess[9][1]), mess[6][1], mess[10][1], mess[7][1], mess[8][1]);
		if(n==0){
			
			GetAccount getAccount = new GetAccount();
			acc = getAccount.getAcc(mess[4][1]);
		
			addFlowOrChange.changeFlowState(listId, "0");
			System.out.println("Bank端：修改流水成功");
			back = back + ";BANK_STATE=0;" +mess[0][0]+"="+mess[0][1]+";ACC_ID="+acc+";ACC_PWD="+acc;
		}else{
			back = back + ";BANK_STATE=1;" +mess[0][0]+"="+mess[0][1]+";ACC_ID=-1";
		}
		return back;
	}
}

