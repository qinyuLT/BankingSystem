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

import com.dc.DAO.ChangTailBoxDAO;
import com.dc.DAO.ChangeFlow;
import com.dc.DAO.GetWorkday;
import com.dc.DAO.TellerWriteFlow;
import com.dc.bean.Reference;

public class PutMoneyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String IP = Reference.IP;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("GBK");
		resp.setContentType("text/html;charset=GBK");
		String server_id = req.getParameter("server_id");
		
		System.out.println("Teller端：服务号是"+server_id);
		Socket socket = new Socket(IP, 9999);
		PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String flow_no = req.getParameter("flowNo");
		
		String card_id = req.getParameter("card_id");
		String card_password = req.getParameter("card_password");
		int putMoney = Integer.parseInt(req.getParameter("putMoney"));
		
		GetWorkday workday = new GetWorkday();
		String day = workday.get();
		
		String putMoneyMessge ="server_id="+server_id+";flow_no="+flow_no+";card_id="+card_id+";card_password="+card_password+";putMoney="+putMoney;
		
		TellerWriteFlow flow = new TellerWriteFlow(flow_no, putMoney , card_id, "-", "-", "等待", day, server_id);
		flow.write();
		out.println(putMoneyMessge);
		System.out.println("Teller端：发送的数据是 |"+putMoneyMessge);
		out.flush();
		
		String myResp = in.readLine();
		socket.close();
		System.out.println("Teller端：收到的数据是 |"+myResp);
		String[] mess = myResp.split(";");
		ChangeFlow changeFlow = new ChangeFlow();
		if(mess != null){
			if(mess[0].split("=")[1].equals("0")){
			String flag = changeFlow.changeState(flow_no, day, "1");
			if(flag.equals("ok")){
				ChangTailBoxDAO changTailBox = new ChangTailBoxDAO();
				String User_name = req.getParameter("user");
				int n = changTailBox.changeTailBox(1, User_name, putMoney);
				if(n==0){
					System.out.println("Teller端：尾箱修改成功与否|"+n);
				}
			}
			System.out.println("Teller端：流水修改成功与否|"+flag);
			}
		}
		req.setAttribute("result",myResp );
		req.getRequestDispatcher("putMoney.jsp").forward(req, resp);
	}

}
