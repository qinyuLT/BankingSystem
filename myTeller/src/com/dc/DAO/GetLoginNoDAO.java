package com.dc.DAO;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.db.utils.ConnDB;

public class GetLoginNoDAO {
	private Connection conn = null;
	private Statement stmt = null;
	private CallableStatement cs = null; 
	private ResultSet rs = null;
	public String NO(){
		String lastNo = null;
		try {
			conn=ConnDB.getConnection();
			this.conn.setAutoCommit(false);
			//this.stmt = this.conn.createStatement();
			/*String sqlGet = "select reference_values from rs_right_reference where reference_name='loginNo'";
			this.rs = this.stmt.executeQuery(sqlGet);
			
			while(this.rs.next()){
				lastNo = this.rs.getString(1);
			}*/
			//RS_RIGHT_REFERENCE 记录了下一个登录号和流水号，每次只需select一下
			this.cs = this.conn.prepareCall("{call sp_TproGetLoginNo(?)}");
			this.cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
			this.cs.execute();
			lastNo = this.cs.getString(1);
			System.out.println("Login:你的登录号是："+lastNo);
			
			//登录号+1，更新下一次的登录号
			//字符串 转 整型
			int no = Integer.parseInt(lastNo) + 1;
			this.cs = this.conn.prepareCall("{call sp_TproChangeLoginNo(?)}");
			//整型数字 转 字符串数字
			this.cs.setString(1, ""+no);
			this.cs.execute();
			this.conn.commit();
			this.closeConn();
		} catch (SQLException e) {
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
		GetLoginNoDAO loginNo = new GetLoginNoDAO();
		System.out.println(loginNo.NO());
	}
}
