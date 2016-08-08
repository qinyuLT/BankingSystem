/**   
* @Title: ChangeAccountState.java  
* @Description: TODO(用一句话描述该文件做什么) 
* @author jinlin 
* @date 2011-9-13 下午01:16:57 
* @version V1.0   
*/


package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import com.dc.utils.ConnDB;

/**
 * @author jinlin
 *
 */
public class ChangeAccountState {
 
	public static int updateChangeState(String accid){
		Connection conn = ConnDB.getConnection();
		CallableStatement cs = null;
		int flag=-1;
		try {
			cs=conn.prepareCall("call add_bprochangeState(?,?)");
			cs.setString(1,accid);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.executeUpdate();
			 flag=cs.getInt(2);
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
		return flag;
	}
	
	public static String getChangeState(String accid){
		Connection conn = ConnDB.getConnection();
		CallableStatement cs = null;
		String flag=null;
		try {
			cs=conn.prepareCall("call sp_getAccountState(?,?)");
			cs.setString(1,accid);
			cs.registerOutParameter(2, Types.INTEGER);
			cs.executeUpdate();
			 flag=cs.getString(2);
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
		return flag;
	}
		 
	public static int updateChangeStateToNormal(String accid){
			Connection conn = ConnDB.getConnection();
			CallableStatement cs = null;
			int flag=-1;
			try {
				cs=conn.prepareCall("call add_bprochangeStateToNormal(?,?)");
				cs.setString(1,accid);
				cs.registerOutParameter(2, Types.INTEGER);
				cs.executeUpdate();
				 flag=cs.getInt(2);
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
			return flag;
		}
	
	public static void main(String[] args) {
		System.out.println(new ChangeAccountState().getChangeState("110"));
	}
}
