package com.dc.DAO;

import java.sql.*;

import com.db.utils.ConnDB;

public class ChangeFlow {
	private Connection conn=null;
	private CallableStatement cs=null;
	
	public String changeState(String flow_num, String workday, String flag){
		String n = "no";
		if(flag.equals("1")){
		try {
			conn=ConnDB.getConnection();
			this.cs = this.conn.prepareCall("{call SP_TproChangeState(?,?,?)}");
			this.cs.setString(1, flow_num);
			this.cs.setString(2, workday);
			this.cs.setString(3, flag);
			this.cs.execute();
			n = "ok";
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		return n;
	}
	public static void main(String[] args) {
		ChangeFlow change = new ChangeFlow();		
		System.out.println(change.changeState("123", "2011-8-29","1"));
	}
}
