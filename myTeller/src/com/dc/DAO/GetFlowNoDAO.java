package com.dc.DAO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.utils.ConnDB;

public class GetFlowNoDAO {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	public String NO(){
		String lastNo = null;
		try {
			conn=ConnDB.getConnection();
			this.conn.setAutoCommit(false);
			this.stmt = this.conn.createStatement();
			String sqlGet = "select reference_values from rs_right_reference where reference_name='flowNo'";
			this.rs = this.stmt.executeQuery(sqlGet);
			
			while(this.rs.next()){
				lastNo = this.rs.getString(1);
			}
			System.out.println(lastNo);
			int no = Integer.parseInt(lastNo) + 1;
			String add = "" + no;
			String sqlUpdate = "update rs_right_reference set reference_values='"+add+"' where reference_name='flowNo'";
			this.stmt.executeUpdate(sqlUpdate);
			this.conn.commit();
			this.closeConn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			try {
				this.conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.closeConn();
		}
		return lastNo;
	}
	public void closeConn(){
		try {
			if(this.rs != null){
				this.rs.close();
			}
			if(this.stmt != null){
				this.stmt.close();
			}
			if(this.conn != null){
				this.conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		GetFlowNoDAO no = new GetFlowNoDAO();
		System.out.println(no.NO());
	}
}
