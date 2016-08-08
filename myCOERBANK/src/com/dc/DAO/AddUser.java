package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.dc.utils.ConnDB;

public class AddUser {
	
	public int add(String userID,String userName, int userAge, String userSex, String userDome, String userAddr, String userTel){
		int result = -1;
		Connection conn = ConnDB.getConnection();
		CallableStatement cs = null;
		
		try {
			cs = conn.prepareCall("{call SP_BproAddUser(?,?,?,?,?,?,?,?)}");
			cs.setString(1, userID);
			cs.setString(2, userName);
			cs.setInt(3, userAge);
			cs.setString(4, userSex);
			cs.setString(5, userDome);
			cs.setString(6, userAddr);
			cs.setString(7, userTel);
			cs.registerOutParameter(8, oracle.jdbc.OracleTypes.NUMBER);
			cs.execute();
			result = cs.getInt(8);
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
				e.printStackTrace();
			}
		}
		return result;
	}

}
