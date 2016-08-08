package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.db.utils.ConnDB;
import com.dc.bean.LoginBean;

public class LoginDAO {
	private Connection conn = null;
	private CallableStatement cs = null;
	private ResultSet rs = null;
	private LoginBean bean = null;
	
	public LoginDAO(LoginBean bean){
		this.bean = bean;
	}
	/**
	 * 
	 * @return 返回是否有记录
	 */
	public boolean isLogin() {
		boolean flag = false;
		conn=ConnDB.getConnection();// 连接数据库
		try { // 调用 SP_login 存储过程(没有检测机构号和网点号)！！！！
			this.cs = this.conn.prepareCall("{call SP_login(?,?,?)}");
			this.cs.setString(1, bean.getUser_name());
			this.cs.setString(2, bean.getUser_password());
			this.cs.registerOutParameter(3, oracle.jdbc.OracleTypes.INTEGER);
			this.cs.execute();
			if(this.cs.getInt(3) != 0) {
				flag = true;
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			this.closeConn();
		}
		return flag;
	}
	// 添加登录日志
	public int addToLoginTable(String IP){
		int n = -1;
		conn=ConnDB.getConnection();
		try {
			/*this.stmt = this.conn.createStatement();
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh24:mm:ss");
			Calendar time = Calendar.getInstance();
			String num = "" + time.getTimeInMillis();
			String sql = "insert into rs_right_login(login_id,login_uptime,login_ip,user_id) values ('"
				+ num +"','"+ num +"','"+ IP +
				"',(select user_id from RS_right_user where user_name='"+ this.bean.getUser_name() +"'))";
			n = this.stmt.executeUpdate(sql);*/
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = dateFormat.format(Calendar.getInstance().getTime());
			cs = conn.prepareCall("{call SP_loginFlow(?,?,?,?,?,?)}");
			cs.setString(1, this.bean.getLogin_no());
			cs.setString(2, this.bean.getNet_id());
			cs.setString(3, date);
			cs.setString(4, IP);
			cs.setString(5, this.bean.getUser_name());
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.INTEGER);
			cs.execute();
			if(cs.getInt(6) == 0){
				n = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConn();
		}
		
		return n;
	}
	
	public int leave(){
		int n = -1;
		conn=ConnDB.getConnection();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(Calendar.getInstance().getTime());
		try {
			cs = conn.prepareCall("{call SP_TproLeave(?,?,?)}");
			cs.setString(1, this.bean.getLogin_no());
			cs.setString(2, date);
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.INTEGER);
			this.cs.execute();
			if(cs.getInt(3) == 0){
				n = 0;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.closeConn();
		}
		return n;
	}
	
	private void closeConn(){
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
			e.printStackTrace();
		}
	}
}
