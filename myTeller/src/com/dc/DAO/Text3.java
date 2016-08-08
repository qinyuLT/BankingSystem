package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.db.utils.ConnDB;

public class Text3 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			 Connection conn=ConnDB.getConnection();
			CallableStatement stmt = conn.prepareCall("{call PS_login(?,?,?)}");
			stmt.setString(1, "zhangwenjia");
			stmt.setString(2, "zhangwenjia");
			stmt.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
			stmt.execute();
			System.out.println(stmt.getString(3));
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
