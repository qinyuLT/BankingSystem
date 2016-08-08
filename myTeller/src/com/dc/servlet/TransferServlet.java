package com.dc.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dc.DAO.ChangeFlow;
import com.dc.DAO.GetWorkday;
import com.dc.DAO.TellerWriteFlow;
import com.dc.bean.Reference;

public class TransferServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private String IP = Reference.IP;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("GBK");
		resp.setContentType("text/html;charset=GBK");   //防止乱码
		String server_id = req.getParameter("server_id");    //得到服务号
		
		System.out.println(server_id);
		Socket socket = new Socket(IP, 9999);   //连接
		PrintWriter out = new PrintWriter(socket.getOutputStream());   //输出流
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //输入流
		
		String flow_no = req.getParameter("flowNo");         //得到jsp中的数据
		String guest_name= req.getParameter("guest_name");
		String card_id = req.getParameter("card_id");
		String card_password = req.getParameter("card_password");
		int transferMoney = Integer.parseInt(req.getParameter("transferMoney"));
		String card_id_target = req.getParameter("card_id_target");
		
/*		String flow_no = req.getParameter("flowNo");
		String guest_id = req.getParameter("guest_id");
		String card_id = req.getParameter("card_id");
		String card_password = req.getParameter("card_password");*/

		//String guaShiMessage="server_id="+server_id+";flow_no="+flow_no+";guest_id="+guest_id+";card_id="+card_id+";card_password="+card_password;
		//把要发给ESB端的信息 封装
		String transferMessage = "server_id="+server_id+";flow_no="+flow_no+";guest_name="+guest_name+";card_id="+card_id+";card_password="+card_password+";transferMoney="+transferMoney+";card_id_target="+card_id_target;
		
		GetWorkday workday = new GetWorkday();   
		String day = workday.get();       //处理日期
		//写流水号 TellerWriteFlow(String flow_num, float flow_money, String card_id, String user_id,
		//                                                 String flow_state,String flow_medo,String workday,String deal_id){
		TellerWriteFlow flow = new TellerWriteFlow(flow_no, 0, card_id, "-", "-", "等待", day, server_id);
		flow.write();
		out.println(transferMessage);
		System.out.println("Teller端：发送的数据是 |"+transferMessage);
		out.flush();
		
		
		String myResp = in.readLine();
		System.out.println("Teller端：收到的数据是 |"+myResp);
		if(myResp != null){
			
			String[] mess = myResp.split(";");
			String[] getFlag = mess[0].split("=");
			if(getFlag[1].equals("0")){
				ChangeFlow changeFlow = new ChangeFlow();
				String flag = changeFlow.changeState(flow_no, day, "1");
				System.out.println("Teller端：流水修改成功与否|"+flag);
			}
		}
		req.setAttribute("result", myResp);
		req.getRequestDispatcher("transfer.jsp").forward(req, resp);
	}
}
