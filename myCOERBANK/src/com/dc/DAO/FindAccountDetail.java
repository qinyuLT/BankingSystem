package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dc.utils.ConnDB;

public class FindAccountDetail {
	
	public String find(String Acc_ID){
		String result = "-1";
		Connection conn = ConnDB.getConnection();		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			cs = conn.prepareCall("{call SP_BproFindAccDetail(?,?)}");
			cs.setString(1, Acc_ID);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet)cs.getObject(2);
			while(rs.next()) {
				result = rs.getString(1)+"#"+rs.getString(2)+"#"+rs.getString(6)+"#"+rs.getString(7)+"#"+rs.getString(4);
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
