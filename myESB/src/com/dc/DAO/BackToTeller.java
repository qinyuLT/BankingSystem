package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.dc.bean.SplitString;
import com.dc.utils.ConnDB;

public class BackToTeller {
	
	public String toTeller(String response) {
		String result = null;
		String[][] mess = new SplitString(response).getMessages();
		Connection conn=ConnDB.getConnection();
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall("{call SP_EproChangeListState(?,?,?,?)}");
			
			cs.setString(1, mess[0][1]);
			cs.setInt(2, Integer.parseInt(mess[2][1]));
			cs.setString(3, mess[1][1]);
			cs.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
			
			cs.execute();
			if(cs.getInt(4)==0){
				
				if(mess.length==5){
					result = "ESB_STATE=" + mess[1][1]+";"+mess[3][0]+"="+mess[3][1]+";"+mess[4][0]+"="+mess[4][1];
				} else {
					result = "BANK_STATE="+mess[1][1]+";ESB_STATE=" + mess[1][1]+";"+mess[3][0]+"="+mess[3][1];
				}
			} else {
				result = "BANK_STATE="+mess[1][1]+";ESB_STATE=" + mess[1][1]+";"+mess[3][0]+"="+mess[3][1];
			}
			
		} catch (SQLException e) {
		
			e.printStackTrace();
		} finally {
			try {
				if(cs != null){
					cs.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
