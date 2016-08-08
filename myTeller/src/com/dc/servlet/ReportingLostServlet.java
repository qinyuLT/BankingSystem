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

public class ReportingLostServlet extends HttpServlet {

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
		BufferedReader in = new BufferedReader(new InputStreamReader(socket
				.getInputStream()));

		String flow_no = req.getParameter("flowNo");
		String guest_id = req.getParameter("guest_id");
		String card_id = req.getParameter("card_id");
		String card_password = req.getParameter("card_password");
		// String account_id = req.getParameter("account_id");

		String lostMessage = "server_id=" + server_id + ";flow_no=" + flow_no
				+ ";guest_id=" + guest_id + ";card_id=" + card_id
				+ ";card_password=" + card_password + ";money=0";

		GetWorkday workday = new GetWorkday();
		String day = workday.get();
		TellerWriteFlow flow = new TellerWriteFlow(flow_no, 0, card_id,
				guest_id, "-", "等待", day, server_id);
		// TellerWriteFlow flow = new TellerWriteFlow("112", 100, "card_id",
		// "user_id", "demo", "state", "2011-8-25", "01");
		System.out.println(flow.write());
		out.println(lostMessage);
		System.out.println(lostMessage);
		out.flush();

		System.out.println("挂失信息发送成功！！！！！！！！！");
		String myResp = in.readLine();
		System.out.println(myResp);
		if (myResp != null) {
			String[] mess = myResp.split(";");
			String[] getFlag = mess[0].split("=");
			if (getFlag[1].equals("0")) {
				ChangeFlow changeFlow = new ChangeFlow();
				changeFlow.changeState(flow_no, day, "1");
				System.out.println("修改状态成功");
			}
		}
		req.setAttribute("result", myResp);
		req.getRequestDispatcher("reportingLost.jsp").forward(req, resp);
	}

}
