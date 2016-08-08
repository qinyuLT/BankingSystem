package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.dc.utils.ConnDB;

public class AddProofDetail {
	/**
	 * 记录凭证明细
	 * @param deal_style_id
	 * @param proof_id
	 * @param lend_subj
	 * @param loan_subj
	 * @param money
	 * @return 返回0正确 非0则错误
	 */
	public String AddToTable(String deal_style_id,int proof_id,String lend_subj, String loan_subj, int money){
		
		String result = null;
		Connection conn = ConnDB.getConnection();
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall("{call SP_BproProofDetail(?,?,?,?,?,?)}");
			
			cs.registerOutParameter(6, oracle.jdbc.OracleTypes.NUMBER);
			cs.setString(1, deal_style_id);
			cs.setInt(2, proof_id);
			cs.setString(3, lend_subj);
			cs.setString(4, loan_subj);
			cs.setInt(5, money);
			
			cs.execute();
			result = ""+cs.getInt(6);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
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
