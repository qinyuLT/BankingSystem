package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dc.utils.ConnDB;

public class FindDealSubjectPara {
	
	
	public ResultSet getResult(String dealId){
		Connection conn = ConnDB.getConnection();		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			cs = conn.prepareCall("{call SP_BproFindProofPara(?,?)}");
			cs.setString(1, dealId);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			rs = (ResultSet)cs.getObject(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(rs != null){
					rs.close();
				}
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
		return rs;
	}
}
