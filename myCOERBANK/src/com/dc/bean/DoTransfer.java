package com.dc.bean;

import com.dc.DAO.AddFlowOrChangeState;
import com.dc.DAO.AddProof;
import com.dc.DAO.AddProofDetail;
import com.dc.DAO.ChangeAccountBalance;
import com.dc.DAO.ChangeAccountState;
import com.dc.DAO.CheckAccount;
import com.dc.DAO.FindSubject;
import com.dc.DAO.SplitString;

import com.dc.DAO.TransAccount;;

public class DoTransfer  implements DoSthBank{
	
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
		boolean check = checkAccount.checkIt(mess[5][1], mess[6][1]);
		System.out.println(" check="+ check);
		
		ChangeAccountState  Changeaccountstate = new  ChangeAccountState();	
			String check_s = Changeaccountstate.getChangeState(mess[5][1]);
			System.out.println(" check_s="+ check_s);
		System.out.println("mess[5][1]="+mess[5][1]+"mess[6][1]="+mess[6][1]);
		
		//if(check&&(check_s.equals("0")))
			if(check)
				{
			System.out.println("Bank端：用户已确认");

			ChangeAccountBalance changeBalance = new ChangeAccountBalance();
					//	String result = changeBalance.changeMoney(1, mess[4][1], Integer.parseInt(mess[6][1]));
			TransAccount Transaccount=new TransAccount();
			int result=Transaccount.transMoney(mess[5][1], mess[7][1],mess[8][1]);
				
			if(result==0){
							
				addFlowOrChange.changeFlowState(listId, "0");
				back = back + ";BANK_STATE=0;" +mess[0][0]+"="+mess[0][1]+";TELLER_FLOW="+mess[3][1];
				System.out.println("Bank端：转账成功");
				AddProof proof = new AddProof();  //记录日志
				int proofId = proof.addToTable("转账", mess[1][1], listId);
				System.out.println("凭证号："+proofId);
				FindSubject findSubject = new FindSubject();
				String[] subjects = findSubject.dealToFindSubject(mess[1][1]);
				System.out.println("影响科目"+subjects[0]+"  "+subjects[1]);
				AddProofDetail proofDetail = new AddProofDetail();
				proofDetail.AddToTable(mess[1][1], proofId, subjects[0], subjects[1], Integer.parseInt(mess[6][1]));
				System.out.println("Bank端：记账成功");
			}
	}
	 else if(!check) {
			back = back + ";BANK_STATE=1;" +mess[0][0]+"="+mess[0][1]+";TELLER_FLOW="+mess[3][1];
		}
		return back;
		}
	/*	else
		{
			back = back + ";BANK_STATE=1;" +mess[0][0]+"="+mess[0][1]+";TELLER_FLOW="+mess[3][1];
		}
		return back;
	}*/
	public void i() {
		try {
			DoSthBank doSth = (DoSthBank) getClass().getClassLoader().loadClass("com.dc.bean.DoTransfer").newInstance();
			doSth.doIt("ESB_ID=11;deal_compare_Id=02;server_id=02;flow_no=174;card_id=110;card_password=000000;putMoney=100");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) {
		DoTransfer transfer = new DoTransfer();
		transfer.i();
	}
}

/*package com.dc.bean;

import com.dc.DAO.AddFlowOrChangeState;
import com.dc.DAO.AddProof;
import com.dc.DAO.AddProofDetail;
import com.dc.DAO.ChangeAccountBalance;
import com.dc.DAO.CheckAccount;
import com.dc.DAO.FindSubject;
import com.dc.DAO.SplitString;

import com.dc.DAO.TransAccount;;

public class DoTransfer  implements DoSthBank{
	
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
		System.out.println("1111111111111111111");
		CheckAccount checkAccount = new CheckAccount();
		System.out.println("22222222222222222");
		boolean check = checkAccount.checkIt(mess[4][1], mess[5][1]);
		System.out.println("33333333333333");
		//if(check){
			System.out.println("Bank端：用户已确认");
			ChangeAccountBalance changeBalance = new ChangeAccountBalance();
					//	String result = changeBalance.changeMoney(1, mess[4][1], Integer.parseInt(mess[6][1]));
			String result=TransAccount.transMoney(mess[5][1], mess[7][1],mess[8][1]);
			
			
			
			if(result.equals("0")){
				
				System.out.println("6666666666666");
				addFlowOrChange.changeFlowState(listId, "0");
				back = back + ";BANK_STATE=0;" +mess[0][0]+"="+mess[0][1]+";TELLER_FLOW="+mess[3][1];
				System.out.println("Bank端：转账成功");
				AddProof proof = new AddProof();  //记录日志
				int proofId = proof.addToTable("转账", mess[1][1], listId);
				System.out.println("凭证号："+proofId);
				FindSubject findSubject = new FindSubject();
				String[] subjects = findSubject.dealToFindSubject(mess[1][1]);
				System.out.println("影响科目"+subjects[0]+"  "+subjects[1]);
				AddProofDetail proofDetail = new AddProofDetail();
				proofDetail.AddToTable(mess[1][1], proofId, subjects[0], subjects[1], Integer.parseInt(mess[6][1]));
				System.out.println("Bank端：记账成功");
			}
		 else {
			back = back + ";BANK_STATE=1;" +mess[0][0]+"="+mess[0][1]+";TELLER_FLOW="+mess[3][1];
		}
		return back;
	}
	public void i() {
		try {
			DoSthBank doSth = (DoSthBank) getClass().getClassLoader().loadClass("com.dc.bean.DoTransfer").newInstance();
			doSth.doIt("ESB_ID=11;deal_compare_Id=02;server_id=02;flow_no=174;card_id=110;card_password=000000;putMoney=100");
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	public static void main(String[] args) {
		DoTransfer transfer = new DoTransfer();
		transfer.i();
	}
}*/