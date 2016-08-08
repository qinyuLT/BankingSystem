package com.dc.DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.dc.bean.SplitString;
import com.dc.utils.ConnDB;

public class DoSthESB {
	private String mess = null;
	
	public DoSthESB(String request){
		this.mess = request;
	}
	
	public String[] doIt(){
		String[] response = null;
		int j = -2;
		String[][] messages = new SplitString(this.mess).getMessages();
		String id = messages[0][1];
		try {
			Connection conn=ConnDB.getConnection();
			//拆分 SP_EproFindDepart:该存储过程存有服务类型对应编号
			CallableStatement cs = conn.prepareCall("{call SP_EproFindDepart(?,?)}");
			cs.setString(1, id);
			cs.registerOutParameter(2, oracle.jdbc.OracleTypes.CURSOR);
			cs.execute();
			ResultSet rs = (ResultSet)cs.getObject(2);
			/*if(id.equals("04")){
				response = new String[2];
			} else {
				response = new String[1];
			}*/
			response = new String[1];
			int i = 0;
			while(rs.next()){
				response[i] = "deal_compare_Id="+rs.getString(1) +";"+this.mess;
				i++;
			}
			//System.out.println(response[0]);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String date = dateFormat.format(Calendar.getInstance().getTime());
			CallableStatement cs2 = ConnDB.getConnection().prepareCall("{call SP_EproList(?,?,?,?)}");
			System.out.println(messages[1][1]);
			cs2.setString(1, messages[1][1]);
			cs2.setString(2, date);
			cs2.setString(3, messages[0][1]);
			cs2.registerOutParameter(4, oracle.jdbc.OracleTypes.NUMBER);
			cs2.execute();
			System.out.println("ESB流水插入成功");
			j = cs2.getInt(4);
			System.out.println(j);
			for(int n = 0; n < response.length; n++){
				response[n] = "ESB_ID="+j+";"+ response[n];
				System.out.println(response[n]);
			}
			
			if(rs != null){
				rs.close();
			}
			if(cs != null){
				cs.close();
			}
			if(ConnDB.getConnection() != null){
				ConnDB.getConnection().close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		if(id.equals("13")){
//			Flush flush = new Flush();
//			String[] result = flush.getFlushID(messages[5][1]);
//			System.out.println("BC_LIST_ID:"+result[0]);
//			System.out.println("BC_LIST_ID:"+result[1]);
			response[0] = response[0]+";BC_LIST_ID=" +null+";DEAL_STYLE_ID="+"13";
			return response;
		}
		if(j != -1 || id.equals("01")){
			return response;
		} 
		return null;
	}
	public static void main(String[] args) {
		String str = "server_id=13;flow_no=715;card_id=000001;card_password=null;getMoney=null";
		DoSthESB doSthESB = new DoSthESB(str);
		String[] response = doSthESB.doIt();
		for(int i = 0; i < response.length; i++){
			System.out.println(response[i]);
		}
	}
}
