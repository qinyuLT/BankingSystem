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
	//���� serialVersionUID ���о���
	private static final long serialVersionUID = 1L;
	//ESB ��ip
	private String IP = null;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("GBK");	                     // ���ô��ݺͽ��ղ����ı���
		resp.setContentType("text/html;charset=GBK");// ������Ӧ��������
		//�� login.jsp ҳ���ȡ����� ��¼��Ϣ���û��������롢����š������ţ�
		String user_name = req.getParameter("user_name");
		String user_password = req.getParameter("user_password");
		String org_id = req.getParameter("org_id");
		String net_id = req.getParameter("net_id");
		String login_no = req.getParameter("login_no");//ǩ����
		//LoginBean ��¼��Ϣbean
		LoginBean loginBean = new LoginBean();
		//��װ��¼��Ϣ�� LoginBean
		loginBean.setUser_name(user_name);
		loginBean.setUser_password(user_password);
		loginBean.setOrg_id(org_id);
		loginBean.setNet_id(net_id);
		loginBean.setLogin_no(login_no);
		
		LoginDAO loginDAO = new LoginDAO(loginBean);
		
		boolean loginRes = loginDAO.isLogin();
		System.out.println("��¼�ɹ����"+loginRes);
		if(loginRes){
			this.IP = InetAddress.getLocalHost().getHostAddress(); //��ȡ����ip
			HttpSession session = req.getSession(true);
			session.setAttribute("user", loginBean);       //�� user ��¼�û���Ϣ���浽session��
			int n = loginDAO.addToLoginTable(IP);   //��¼�ɹ�����ӵ�¼��־
			System.out.println(n);
			resp.sendRedirect("main.jsp");   //��¼�ɹ�  ��ת main.jsp ҳ��             
		} else {
			resp.sendRedirect("login.jsp");    //��¼ʧ�� ���� login.jsp ҳ��
		}
	}
}
