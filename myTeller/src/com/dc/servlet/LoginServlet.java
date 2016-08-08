package com.dc.servlet;

import java.io.IOException;
import java.net.InetAddress;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dc.DAO.LoginDAO;
import com.dc.bean.LoginBean;

public class LoginServlet extends HttpServlet {
	//不加 serialVersionUID 会有警告
	private static final long serialVersionUID = 1L;
	//ESB 端ip
	private String IP = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("GBK");	                     // 设置传递和接收参数的编码
		resp.setContentType("text/html;charset=GBK");// 设置响应内容类型
		//从 login.jsp 页面获取输入的 登录信息（用户名、密码、网点号、机构号）
		String user_name = req.getParameter("user_name");
		String user_password = req.getParameter("user_password");
		String org_id = req.getParameter("org_id");
		String net_id = req.getParameter("net_id");
		String login_no = req.getParameter("login_no");//签到号
		//LoginBean 登录信息bean
		LoginBean loginBean = new LoginBean();
		//封装登录信息到 LoginBean
		loginBean.setUser_name(user_name);
		loginBean.setUser_password(user_password);
		loginBean.setOrg_id(org_id);
		loginBean.setNet_id(net_id);
		loginBean.setLogin_no(login_no);
		
		LoginDAO loginDAO = new LoginDAO(loginBean);
		
		boolean loginRes = loginDAO.isLogin();
		System.out.println("登录成功与否："+loginRes);
		if(loginRes){
			this.IP = InetAddress.getLocalHost().getHostAddress(); //获取本机ip
			HttpSession session = req.getSession(true);
			session.setAttribute("user", loginBean);       //将 user 登录用户信息保存到session中
			int n = loginDAO.addToLoginTable(IP);   //登录成功，添加登录日志
			System.out.println(n);
			resp.sendRedirect("main.jsp");   //登录成功  跳转 main.jsp 页面             
		} else {
			resp.sendRedirect("login.jsp");    //登录失败 返回 login.jsp 页面
		}
	}
}
