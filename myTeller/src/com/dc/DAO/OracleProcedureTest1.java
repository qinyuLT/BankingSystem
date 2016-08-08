package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import com.db.utils.ConnDB;
import java.sql.SQLException;
//import java.sql.Date;
//import java.sql.ResultSet;
//import java.sql.Types;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import com.sun.crypto.provider.RSACipher;
//import sun.util.resources.CalendarData;

public class OracleProcedureTest1 {
	
	public static void main(String[] args) throws SQLException {
		Connection conn=ConnDB.getConnection();
		//CallableStatement cs = conn.prepareCall("{call PS_login(?,?,?)}");
		/*cs.setString(1, "zhangwenjia");
		cs.setString(2, "zhangwenjia");
		cs.registerOutParameter(3, oracle.jdbc.OracleTypes.INTEGER);*/
		CallableStatement cs = conn.prepareCall("{call SP_TproChangTailBox(?,?,?,?)}");
		cs.setInt(1, 1);
		cs.setDouble(2, 10000000.15);
		cs.setString(3, "zhangwenjia");
		cs.registerOutParameter(4, oracle.jdbc.OracleTypes.INTEGER);
		//cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
		//cs.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
		cs.execute();
		int n = cs.getInt(4);
		System.out.println(n);
		//System.out.println(cs.getString(3));
		/*ResultSet rs = (ResultSet)cs.getObject(2);
		
		System.out.println(rs);
		while(rs.next()){
			System.out.println(rs.getString(1));
		}*/
		/*SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = dateFormat.format(Calendar.getInstance().getTime());
		System.out.println(date);*/
	}
}
