package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dc.utils.ConnDB;

public class FindSubject {
	/**
	 * 
	 * @param deal_style_id
	 * @return 返回交易对照表中对应交易码影响的科目
	 */
	public String[] dealToFindSubject(String deal_style_id) {
		String[] subjects = new String[2];
		Connection conn = ConnDB.getConnection();		CallableStatement cs = null;
		ResultSet rs = null;
		try {
			cs = conn.prepareCall("{call SP_BproFindProofPara(?,?)}");//SP_BproFindProofPara
			
			cs.setString(1, deal_style_id);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			
			cs.execute();
			rs = (ResultSet)cs.getObject(2);
			while(rs.next()) {
				subjects[0] = rs.getString(2);
				subjects[1] = rs.getString(3);
			}
			
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
		return subjects;
	}
}
