package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.dc.utils.ConnDB;

public class GetAccount {

	public static String strAdd(String str){//字符串数字自加
		Integer id=Integer.parseInt(str);
	    id++;
	    return id.toString();		
	  }

	public String getAcc(String guest_id) {
		// TODO Auto-generated method stub
		String ress = null;
		Connection conn = ConnDB.getConnection();
		CallableStatement cs = null;
	try {
			cs = conn.prepareCall("{call SP_BproGetAccId(?)}");
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
			cs.execute();
			String res = cs.getString(1);
			ress = strAdd(res);
			
			cs = conn.prepareCall("{call SP_BproAddAccId(?,?,?)}");
			cs.setString(1, ""+ress);
			cs.setString(2, guest_id);
			cs.setString(3, "001");
			//cs.registerOutParameter(4, oracle.jdbc.OracleTypes.VARCHAR);
			cs.execute();
	
			System.out.println("res = "+res);
			System.out.println("zijia = "+ress);
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
		return ress;
}

	
}




/*package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.dc.utils.ConnDB;

public class GetAccount {

	public String getAcc(String guest_id) {
		String  res=null;
		Connection conn = ConnDB.getConnection();
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall("{call SP_BproGetAccId(?)}");
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
			cs.execute();
			
			System.out.println("最大 卡号   = "+cs.getString(1));
			res= strAdd(cs.getString(1));
			System.out.println("卡号自加后= "+res);
			
			//SP_BproAddAccId
			cs = conn.prepareCall("{call SP_BproAddAccId(?,?,?)}");
			cs.setString(1, res);
			cs.setString(2,guest_id );
			cs.setString(3, "001");
			cs.execute();
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return res;
	}
	public static String strAdd(String str){//字符串数字自加
		Integer id=Integer.parseInt(str);
	    id++;
	    return id.toString();		
	}
}
*/