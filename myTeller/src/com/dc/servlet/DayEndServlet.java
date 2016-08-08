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
import com.dc.bean.Reference;

public class DayEndServlet extends HttpServlet{

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
		
		
		String putMoneyMessge ="server_id="+server_id;
		GetWorkday workday = new GetWorkday();
		workday.get();
		//String day = workday.get();
		
		out.println(putMoneyMessge);
		out.flush();
		
		String myResp = in.readLine();
		socket.close();
		
		myResp.split(";");
		//String[] mess = myResp.split(";");
		//ChangeFlow changeFlow = new ChangeFlow();
		
		req.setAttribute("result", myResp);
		req.getRequestDispatcher("main.jsp").forward(req, resp);
	}
}
	
	


