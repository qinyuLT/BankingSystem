package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.utils.ConnDB;

public class TellerWriteFlow {
	/*
	  流水号--FLOWNUM
	  交易时间--sysdate自动获取
	  交易金额--FLOWMONEY
	  交易账户号--CARDID
	  交易柜员号--USERID
	  交易状态--FLOWSTATE
	  交易备注--FLOWMEDO
	  工作日--FLOWWORKDAY
	  交易编号--DEALID
	**/
	private String flowNo = null;
	private float DealMoney = 0;
	private String CardId = null;
	private String UserId = null;
	private String DealState = null;
	private String DealDemo = null;
	private String workday = null;
	private String serverNo = null;
	
	public TellerWriteFlow(String flow_num, float flow_money, String card_id, String user_id,String flow_state,String flow_medo,String workday,String deal_id){
		this.flowNo = flow_num;
		this.DealMoney = flow_money;
		this.CardId = card_id;
		this.UserId = user_id;
		this.DealState = flow_state;
		this.DealDemo = flow_medo;
		this.workday = workday;
		this.serverNo = deal_id;
	}
	public boolean write(){
		boolean flag = false;
		Connection conn=ConnDB.getConnection();
		CallableStatement cs = null;
		ResultSet rs = null;
		
		try{
		cs = conn.prepareCall("{call TELLERliushui(?,?,?,?,?,?,?,?,?,?)}");
		//给?赋值
		cs.setString(1,this.flowNo);
		cs.setFloat(2,this.DealMoney);
		cs.setString(5,this.DealDemo);
		cs.setString(3, this.CardId);
		cs.setString(4,this.UserId);
		cs.setString(6,this.DealState);
		cs.setString(7,this.workday);
		cs.setString(8,this.serverNo);

		//注册返回标志flag
		cs.registerOutParameter(9, oracle.jdbc.OracleTypes.INTEGER);
		//注册返回的结果集p_cursor
		cs.registerOutParameter(10, oracle.jdbc.OracleTypes.CURSOR);
		
		//执行
		cs.execute();
		//接收返回标志，true表示操作成功，false表示操作失败
		flag = cs.getBoolean(9);
		if(flag)
			System.out.println("Teller端：流水插入成功！");
		rs=(ResultSet)cs.getObject(10);
		//输出TELLER流水表
		/*while(rs.next()){
			
			System.out.println("交易时间:"+rs.getString(1)+
					"  流水号:"+rs.getString(2)+"  交易金额:"+rs.getFloat(3)+
					"  交易账户号:"+rs.getString(4)+"  交易柜员号:"+rs.getString(5)+
					"  交易状态:"+rs.getString(6)+"  交易状态:"+rs.getString(7)+"  交易状态:" +
							""+rs.getString(8)+"  交易状态:"+rs.getString(9));
			
		}*/
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if(rs != null){
				rs.close();
			}
			if(cs != null){
				cs.close();
			}
			if(conn != null ){
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

		return flag;
	}
	
	
	public static void main(String[] args) {
		TellerWriteFlow flow = new TellerWriteFlow("112", 100, "card_id", "user_id", "demo", "state", "2011-8-25", "01");
		System.out.println(flow.write());
	}
}
