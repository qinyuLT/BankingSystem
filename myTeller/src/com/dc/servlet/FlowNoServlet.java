package com.dc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dc.DAO.GetFlowNoDAO;

//获取流水号
public class FlowNoServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("GBK");
		resp.setContentType("text/html;charset=GBK");
		
		PrintWriter out = resp.getWriter() ;
		GetFlowNoDAO getFlowNo = new GetFlowNoDAO();
		String flowNo = getFlowNo.NO();
		out.print(flowNo);
		System.out.println("获得的流水号是："+ flowNo);
		
	}

}
