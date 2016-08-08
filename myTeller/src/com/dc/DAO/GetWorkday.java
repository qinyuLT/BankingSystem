package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.db.utils.ConnDB;

public class GetWorkday {
	
	private Connection conn=null;
	private CallableStatement cs=null;
	
	public String get(){
		String n = null;
		
		try {
			conn=ConnDB.getConnection();
			this.cs = this.conn.prepareCall("{call SP_TproGetWorkday(?)}");
			this.cs.registerOutParameter(1,oracle.jdbc.OracleTypes.VARCHAR);
			this.cs.execute();
			n = this.cs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(this.cs != null){
					this.cs.close();
				}
				if(this.conn != null){
					this.conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return n;
	}
	public static void main(String[] args) {
				GetWorkday workday = new GetWorkday();
				System.out.println(workday.get());
	}
}
