package com.dc.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.dc.DAO.FindAccountDetail;
import com.dc.DAO.SplitString;
import com.dc.utils.ConnDB;

public class DoJieGua implements DoSthBank {
//解挂
	public String doIt(String messages) {
		// TODO Auto-generated method stub
		String back = "BANK_LIST_ID=";
		String[][] mess = new SplitString(messages).getMessages();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(Calendar.getInstance().getTime());
		Connection conn=ConnDB.getConnection();
		CallableStatement cs = null;
		ResultSet rs = null;
		int flag = -1;
		
		try{
		
			cs = conn.prepareCall("{call SP_BproList(?,?,?,?,?,?,?)}");
			cs.setString(2,mess[5][1]);
			cs.setString(3,date);
			cs.setString(4,mess[7][1]);
			cs.setString(5, mess[0][1]);
			cs.setString(6,mess[3][1]);
			cs.setString(7,mess[1][1]);
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.NUMBER);
			cs.execute();
			int listId = cs.getInt(1);
			
			back = back + listId;
			
			cs = conn.prepareCall("{call SP_BproCheckAcc(?,?,?)}");
			cs.setString(1,mess[5][1]);
			cs.setString(2, mess[6][1]);
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
			cs.execute();
			flag = cs.getInt(3);
		
		if(flag == 1)
		{
			back = back + ";BANK_STATE=1;" +mess[0][0]+"="+mess[0][1]+";TELLER_FLOW="+mess[3][1];
			System.out.println("COREBANK端解挂失败！");
			return back;
		}
			//调用存储过程
		cs = conn.prepareCall("{call add_bprochangeStateToNormal(?,?)}");
		cs.setString(1,mess[5][1]);
		System.out.println("mess[5][1]:"+mess[5][1]);
		cs.registerOutParameter(2, oracle.jdbc.OracleTypes.NUMBER);
		//注册返回的结果集p_cursor
		//执行
		cs.execute();
		//接收返回标志，true表示操作成功，false表示操作失败
		flag = cs.getInt(2);
		if(flag == 0)
		{
			System.out.println("COREBANK端解挂完成！");
		    back = back + ";BANK_STATE=0;" +mess[0][0]+"="+mess[0][1]+";TELLER_FLOW="+mess[3][1];
		}
		else
		{
			back = back + ";BANK_STATE=1;" +mess[0][0]+"="+mess[0][1]+";TELLER_FLOW="+mess[3][1];
			System.out.println("COREBANK端解挂失败！");
		}
	} catch (Exception e) {
		// TODO: handle exception
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

		return back;
	}
	
}
