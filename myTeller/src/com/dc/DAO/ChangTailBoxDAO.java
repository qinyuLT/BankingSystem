package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.db.utils.ConnDB;

public class ChangTailBoxDAO {
	
	/**
	 * 
	 * @param flag 1是加  0是减
	 * @param tellerName
	 * @param Money
	 * @return 返回0正确   非零则错误
	 */
	public int changeTailBox(int flag, String tellerName, int money){
		int result = -1;
		Connection conn=ConnDB.getConnection();		
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall("{call SP_TproChangTailBox(?,?,?,?)}");
			cs.setInt(1, flag);
			cs.setInt(2, money);
			cs.setString(3, tellerName);
			cs.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
			cs.execute();
			result = cs.getInt(4);
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
	public static void main(String[] args) {
		ChangTailBoxDAO changTailBox = new ChangTailBoxDAO();
		System.out.println(changTailBox.changeTailBox(0, "zhangwenjia", 100));
	}
}
