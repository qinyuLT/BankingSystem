package com.dc.bean;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

//import com.dc.DAO.OrclConnect;
import com.dc.utils.ConnDB;

public class Flush {
	
	public String[] getFlushID(String TellerFlowNo){
		String[] result = new String[2];
		Connection conn = ConnDB.getConnection();
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall("{call SP_EproFindList(?,?,?)}");
			cs.setString(1, TellerFlowNo);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
			cs.execute();
			result[0] = cs.getString(2);
			result[1] = cs.getString(3);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block 000001
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
		/*if(result[0]==null || result[1]==null){
			result[0] = "-1";
			result[1] = "-1";
			return result;
		}*/
		return result;
	}
	public static void main(String[] args) {
		Flush flush = new Flush();
		String[] s = flush.getFlushID("756");
		System.out.println(s[0]+"  "+s[1]);
	}
}
