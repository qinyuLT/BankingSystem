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
		//从页面获取 服务id
		String server_id = req.getParameter("server_id");
		//从页面获取 流水号，用于交易日志的记录
		String flow_no = req.getParameter("flowNo");
		
		 //建立与 ESB 端的 Socket 连接，创建socket通信管道
		Socket socket = new Socket(IP, 9999);     
		//创建socket通信管道 读（out）写（in）对象
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

		////从页面获取form表单输入信息
		String card_id = req.getParameter("card_id");
		String guest_id = req.getParameter("guest_id");
		String old_password = req.getParameter("old_password");
		String new_password1 = req.getParameter("new_password1");
		
//		String new_password2 = req.getParameter("new_password2");
//		if(new_password1 != new_password2){                   //两次密码不一致
//			System.out.println("pasw1= "+new_password1);
//			System.out.println("pasw2= "+new_password2);
//			req.setAttribute("result", "-1");
//			req.getRequestDispatcher("changeAccPsw.jsp").forward(req, resp);	
//		}
		
		/*
		  流水号--FLOWNUM
		  交易时间--sysdate自动获取
		  交易金额--FLOWMONEY    0
		  交易账户号--CARDID
		  交易柜员号--USERID
		  交易状态--FLOWSTATE
		  交易备注--FLOWMEDO
		  工作日--FLOWWORKDAY
		  交易编号--DEALID
		**/
		//新的服务添加日志功能需要在 Teller.RS_DEAL 添加记录
		//日志记录
		GetWorkday workday = new GetWorkday();
		String day = workday.get();
		TellerWriteFlow flow = new TellerWriteFlow(flow_no, 0, card_id, "-", "-", "等待", day, server_id);
		flow.write();
		
		//封装发送信息
		String putMessage = "server_id="+server_id+";flow_no="+flow_no+";card_id="+card_id+";guest_id="+guest_id+";card_password="+old_password+";new_password="+new_password1;
		//将发送消息放入socket通信缓冲区
		out.println(putMessage);
		System.out.println("Teller端：发送的数据是 |"+putMessage);
		//刷新socket通信缓冲区的数据，即发送消息
		out.flush();
		
		//从socket缓冲区读取数据
		String myResp = in.readLine();
		//关闭socket通信
		socket.close();
		System.out.println("Teller端：收到的数据是 |"+myResp);
		
		//拆分消息
		String[] mess = myResp.split(";");
		//修改日志状态：“等待”----》“OK”
		ChangeFlow changeFlow = new ChangeFlow();
		if(mess != null){
			if(mess[0].split("=")[1].equals("0")){
				String flag = changeFlow.changeState(flow_no, day, "1");
				if(flag.equals("ok")){
					System.out.println("Teller端 【修改密码】 日志处理成功！");
				}
			}
		}
		//将收到处理后的消息发送回页面
		req.setAttribute("result", myResp);
		req.getRequestDispatcher("changeAccPsw.jsp").forward(req, resp);	
	}
	
	public static void main(String[] args) {
		//流水日志插入测试
		GetWorkday workday = new GetWorkday();
		String day = workday.get();
		System.out.println("day: "+day);
		TellerWriteFlow flow = new TellerWriteFlow("948", 120, "110", "-", "-", "等待", day, "15");
		flow.write();
	}
}
