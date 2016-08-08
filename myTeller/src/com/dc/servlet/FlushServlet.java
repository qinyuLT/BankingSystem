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

public class FlushServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private String IP = Reference.IP;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("GBK");
		resp.setContentType("text/html;charset=GBK");
		String server_id = req.getParameter("server_id");
		
		System.out.println(server_id);
		Socket socket = new Socket(IP, 9999);
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		String flow_no = req.getParameter("flowNo");
		String flush_flow_no = req.getParameter("flush_flowNo");
		String flushMessge ="server_id="+server_id+";flow_no="+flow_no+";card_id=null;card_password=null;money=0;flush_flow_no="+flush_flow_no;
		GetWorkday workday = new GetWorkday();
		String day = workday.get();
		TellerWriteFlow flow = new TellerWriteFlow(flow_no, 0, null, "-", "-", "等待", day, server_id);
		flow.write();
		
		out.println(flushMessge);
		System.out.println("Teller端：发送的数据是 |"+flushMessge);
		out.flush();
		
		String myResp = in.readLine();
		socket.close();
		System.out.println("Teller端：收到的数据是 |"+myResp);
		String[] mess = myResp.split(";");
		ChangeFlow changeFlow = new ChangeFlow();
		if(mess != null){
			if(mess[0].split("=")[1].equals("0")){
			String flag = changeFlow.changeState(flow_no, day, "1");
			System.out.println("Teller端：流水修改成功与否|"+flag);
			}
		}
		req.setAttribute("result", myResp);
		req.getRequestDispatcher("flush.jsp").forward(req, resp);
	}

}
