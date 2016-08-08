package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.dc.utils.ConnDB;

public class TransAccount {
		public  int transMoney(String acc_id, String acc_baiance,String acc_id1) {
			// TODO Auto-generated method stub
			
			int  result = -1;
			Connection conn = ConnDB.getConnection();
			CallableStatement cs = null;
			
			try {
				cs = conn.prepareCall("{call SP_BproTransfer(?,?,?,?)}");
			
				cs.setString(1, acc_id);
				cs.setString(2, acc_baiance);
				cs.setString(3, acc_id1);
				cs.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
				cs.execute();
				if(cs.getInt(4)==0)
				{
					result=0;
				}
				else if(cs.getInt(4)==1)
				{
					result=1;
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
