package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.dc.utils.ConnDB;

public class CheckAccount {
	
	public boolean checkIt(String acc_id, String pwd){
		boolean result = false;
		Connection conn = ConnDB.getConnection();		CallableStatement cs = null;
		try {
			cs = conn.prepareCall("{call SP_BproCheckAcc(?,?,?)}");
			
			cs.setString(1, acc_id);
			cs.setString(2, pwd);
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
			
			cs.execute();
			if(cs.getInt(3) > 0){
				result = true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(cs != null){
					cs.close();
				}
				if(conn != null){
					conn.close();
				}
			} catch (final SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
