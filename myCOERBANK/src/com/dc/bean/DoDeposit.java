package com.dc.bean;

import com.dc.DAO.AddFlowOrChangeState;
import com.dc.DAO.AddProof;
import com.dc.DAO.AddProofDetail;
import com.dc.DAO.ChangeAccountBalance;
import com.dc.DAO.CheckAccount;
import com.dc.DAO.FindSubject;
import com.dc.DAO.SplitString;

public class DoDeposit implements DoSthBank{
	

	@Override
	public String doIt(String messages) {
		// TODO Auto-generated method stub
		String back = "BANK_LIST_ID=";
		String[][] mess = new SplitString(messages).getMessages();
		AddFlowOrChangeState addFlowOrChange = new AddFlowOrChangeState();
		
		int listId = addFlowOrChange.AddFlowToTable(messages);
		//添加流水号
		System.out.println("Bank端：流水号插入成功");
		back = back + listId;
		CheckAccount checkAccount = new CheckAccount();
		
		boolean check = checkAccount.checkIt(mess[4][1], mess[5][1]);
		if(check){
			System.out.println("Bank端：用户已确认");
			
			ChangeAccountBalance changeBalance = new ChangeAccountBalance();
			int result = changeBalance.changeMoney(1, mess[4][1], Integer.parseInt(mess[6][1]));
			
			if(result == 0){
				addFlowOrChange.changeFlowState(listId, "0");
				System.out.println("Bank端：存款成功");
				back = back + ";BANK_STATE=0;" +mess[0][0]+"="+mess[0][1]+";TELLER_FLOW="+mess[3][1];
				
				AddProof proof = new AddProof();
				int proofId = proof.addToTable("存款", mess[1][1], listId);
				System.out.println("凭证号："+proofId);
				FindSubject findSubject = new FindSubject();
				String[] subjects = findSubject.dealToFindSubject(mess[1][1]);
				System.out.println("影响科目"+subjects[0]+"  "+subjects[1]);
				AddProofDetail proofDetail = new AddProofDetail();
				proofDetail.AddToTable(mess[1][1], proofId, subjects[0], subjects[1], Integer.parseInt(mess[6][1]));
				System.out.println("Bank端：记账成功");
			}
		} else if(!check) {
			back = back + ";BANK_STATE=1;" +mess[0][0]+"="+mess[0][1]+";TELLER_FLOW="+mess[3][1];
		}
		
		return back = back + ";BANK_STATE=1;" +mess[0][0]+"="+mess[0][1]+";TELLER_FLOW="+mess[3][1];
	}
	public void i() {
		try {
			DoSthBank doSth = (DoSthBank) getClass().getClassLoader().loadClass("com.dc.bean.DoDeposit").newInstance();
			doSth.doIt("ESB_ID=11;deal_compare_Id=02;server_id=02;flow_no=174;card_id=110;card_password=000000;putMoney=100");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) {
		DoDeposit deposit = new DoDeposit();
		deposit.i();
	}
}