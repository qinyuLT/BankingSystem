package com.dc.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dc.DAO.LoginDAO;
import com.dc.bean.LoginBean;

public class LeaveServlet extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("leave");
		if(id.equals("10")){
			LoginBean bean = (LoginBean)request.getSession().getAttribute("user");
			LoginDAO loginDAO = new LoginDAO(bean);
			int n = loginDAO.leave();
			System.out.println(n);
		}
		response.sendRedirect("right.jsp");
	}

	
}
