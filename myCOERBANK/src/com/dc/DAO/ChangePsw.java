package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import com.dc.bean.DoChangePsw;
import com.dc.utils.ConnDB;

public class ChangePsw {
	public boolean changePsw(String card_id,String guest_id,String oldPsw,String newPsw){
		boolean res = false;
		
		Connection conn = ConnDB.getConnection();
		CallableStatement cs = null;
		try {//��ѯ�û�״̬�� Ϊ��0���Ĳſ��Ը��� ƥ�俨�ź�����
			cs = conn.prepareCall("{call SP_BproCheckAcc(?,?,?)}");
			cs.setString(1, card_id);
			cs.setString(2, oldPsw);
			cs.registerOutParameter(3, oracle.jdbc.OracleTypes.NUMBER);
			cs.execute();
			if(cs.getInt(3) == 1)
			{//�������룺ƥ�� ���š�֤����
				cs = conn.prepareCall("{call SP_BproChangePsw(?,?,?,?)}");
				cs.setString(1,card_id );
				cs.setString(2,guest_id);
				cs.setString(3, newPsw);
				cs.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
				cs.execute();
				
				if(cs.getInt(4) == 1)
					res=true;
			}
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
	////////////////////////////////��ȡ���ţ��Լ�////////////////////////////////
	public static void main(String[] args) {
//		ChangePsw change = new ChangePsw();
//		change.changePsw("110", "1", "000000", "110");
		
		Connection conn = ConnDB.getConnection();
		CallableStatement cs = null;
		try {
			cs = conn.prepareCall("{call SP_BproGetAccId(?)}");
			cs.registerOutParameter(1, oracle.jdbc.OracleTypes.VARCHAR);
			cs.execute();
			String res = cs.getString(1);
			System.out.println("��� ����   = "+res);
			
			System.out.println("�����ԼӺ�= "+strAdd(res));
			
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
	}
	public static String strAdd(String str){//�ַ��������Լ�
		Integer id=Integer.parseInt(str);
	    id++;
	    return id.toString();		
	}
}
