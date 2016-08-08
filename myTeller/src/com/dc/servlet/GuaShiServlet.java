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

public class GuaShiServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private String IP = Reference.IP;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("GBK");
		resp.setContentType("text/html;charset=GBK");
		String server_id = req.getParameter("server_id");
		
		System.out.println(server_id);
		Socket socket = new Socket(IP, 9999);
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		/*String flow_no = req.getParameter("flowNo");
		String card_id = req.getParameter("card_id");
		String card_password = req.getParameter("card_password");
		int transferMoney = Integer.parseInt(req.getParameter("transferMoney"));
		String card_id_target = req.getParameter("card_id_target");*/
		
		String flow_no = req.getParameter("flowNo");
		String guest_id = req.getParameter("guest_id");
		String card_id = req.getParameter("card_id");
		String card_password = req.getParameter("card_password");

		String guaShiMessage="server_id="+server_id+";flow_no="+flow_no+";guest_id="+guest_id+";card_id="+card_id+";card_password="+card_password;
		//String transferMessage = "server_id="+server_id+";flow_no="+flow_no+";guest_id="+card_id+";card_password="+card_password+";transferMoney="+transferMoney+";card_id_target="+card_id_target;
		
		GetWorkday workday = new GetWorkday();
		String day = workday.get();
		TellerWriteFlow flow = new TellerWriteFlow(flow_no, 0, card_id, "-", "-", "等待", day, server_id);
		flow.write();
		out.println(guaShiMessage);
		System.out.println("Teller端：发送的数据是 |"+guaShiMessage);
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
