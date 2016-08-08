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

//import com.dc.DAO.GetWorkday;
//import com.dc.DAO.TellerWriteFlow;
import com.dc.DAO.ChangeFlow;
import com.dc.DAO.GetWorkday;
import com.dc.DAO.TellerWriteFlow;
import com.dc.bean.Reference;

public class ChangeAccPsw extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String IP = Reference.IP;
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("GBK");
		resp.setContentType("text/html;charset=GBK");
		//��ҳ���ȡ ����id
		String server_id = req.getParameter("server_id");
		//��ҳ���ȡ ��ˮ�ţ����ڽ�����־�ļ�¼
		String flow_no = req.getParameter("flowNo");
		
		 //������ ESB �˵� Socket ���ӣ�����socketͨ�Źܵ�
		Socket socket = new Socket(IP, 9999);     
		//����socketͨ�Źܵ� ����out��д��in������
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		////��ҳ���ȡform��������Ϣ
		String card_id = req.getParameter("card_id");
		String guest_id = req.getParameter("guest_id");
		String old_password = req.getParameter("old_password");
		String new_password1 = req.getParameter("new_password1");
		
//		String new_password2 = req.getParameter("new_password2");
//		if(new_password1 != new_password2){                   //�������벻һ��
//			System.out.println("pasw1= "+new_password1);
//			System.out.println("pasw2= "+new_password2);
//			req.setAttribute("result", "-1");
//			req.getRequestDispatcher("changeAccPsw.jsp").forward(req, resp);	
//		}
		
		/*
		  ��ˮ��--FLOWNUM
		  ����ʱ��--sysdate�Զ���ȡ
		  ���׽��--FLOWMONEY    0
		  �����˻���--CARDID
		  ���׹�Ա��--USERID
		  ����״̬--FLOWSTATE
		  ���ױ�ע--FLOWMEDO
		  ������--FLOWWORKDAY
		  ���ױ��--DEALID
		**/
		//�µķ��������־������Ҫ�� Teller.RS_DEAL ��Ӽ�¼
		//��־��¼
		GetWorkday workday = new GetWorkday();
		String day = workday.get();
		TellerWriteFlow flow = new TellerWriteFlow(flow_no, 0, card_id, "-", "-", "�ȴ�", day, server_id);
		flow.write();
		
		//��װ������Ϣ
		String putMessage = "server_id="+server_id+";flow_no="+flow_no+";card_id="+card_id+";guest_id="+guest_id+";card_password="+old_password+";new_password="+new_password1;
		//��������Ϣ����socketͨ�Ż�����
		out.println(putMessage);
		System.out.println("Teller�ˣ����͵������� |"+putMessage);
		//ˢ��socketͨ�Ż����������ݣ���������Ϣ
		out.flush();
		
		//��socket��������ȡ����
		String myResp = in.readLine();
		//�ر�socketͨ��
		socket.close();
		System.out.println("Teller�ˣ��յ��������� |"+myResp);
		
		//�����Ϣ
		String[] mess = myResp.split(";");
		//�޸���־״̬�����ȴ���----����OK��
		ChangeFlow changeFlow = new ChangeFlow();
		if(mess != null){
			if(mess[0].split("=")[1].equals("0")){
				String flag = changeFlow.changeState(flow_no, day, "1");
				if(flag.equals("ok")){
					System.out.println("Teller�� ���޸����롿 ��־����ɹ���");
				}
			}
		}
		//���յ���������Ϣ���ͻ�ҳ��
		req.setAttribute("result", myResp);
		req.getRequestDispatcher("changeAccPsw.jsp").forward(req, resp);	
	}
	
	public static void main(String[] args) {
		//��ˮ��־�������
		GetWorkday workday = new GetWorkday();
		String day = workday.get();
		System.out.println("day: "+day);
		TellerWriteFlow flow = new TellerWriteFlow("948", 120, "110", "-", "-", "�ȴ�", day, "15");
		flow.write();
	}
}
