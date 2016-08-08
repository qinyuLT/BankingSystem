package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.utils.ConnDB;
import com.dc.bean.LoginBean;

public class MenuDAO {
	private Connection conn = null;
	private CallableStatement cs = null;
	private ResultSet rs = null;
	private LoginBean loginBean = null;
	
	public MenuDAO(LoginBean loginBean){
		this.loginBean = loginBean;
	}
	public ResultSet getResult(){
		conn=ConnDB.getConnection();
		this.rs = null;
		try {
			cs = conn.prepareCall("{call SP_TproMenu(?,?)}");
			cs.setString(1, loginBean.getUser_name());
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet)cs.getObject(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.rs;
	}
	public void closeConn(){
		try {
			if(this.rs != null){
				this.rs.close();
			}
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
