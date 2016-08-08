package com.dc.DAO;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.SQLException;

import com.dc.utils.ConnDB;



public class ChangeAccountBalance {
	
	public int changeMoney(int flag, String acc_id,int money){
		
		int result = -1;
		
		Connection conn = ConnDB.getConnection();	
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall("{call SP_BproChangeACC(?,?,?,?)}");
			cs.setInt(1,flag);
			cs.setString(2, acc_id);
			cs.setLong(3, money);
			
			cs.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
			
			cs.execute();
			if(cs.getInt(4) == 0){
				result = 0;
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



/*package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.dc.utils.ConnDB;

public class ChangeAccountBalance {

	
	public String changeMoney(int flag, String acc_id, int money) {
		
		 String result = null;
		Connection conn = ConnDB.getConnection();
		CallableStatement cs = null;
		
		try {
			cs = conn.prepareCall("{call SP_BproChangeACC(?,?,?,?)}");
			cs.setInt(1, flag);
			cs.setString(2, acc_id);
			cs.setInt(3, money);
			cs.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
			cs.execute();
			
			if(cs.getInt(4) == 0)
			{
				result = "0";
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
}
*/