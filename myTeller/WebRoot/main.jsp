<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.dc.bean.LoginBean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
   <title>管理中心</title>
  </head>
  <%
  LoginBean user = (LoginBean)session.getAttribute("user");
  if(user != null){
   %>
  <frameset rows="64,*"  frameborder="NO" border="0" framespacing="0">
	<frame src="admin_top.jsp" noresize="noresize" frameborder="0" name="topFrame" scrolling="no" marginwidth="0" marginheight="0" target="_self" />
	  <frameset cols="200,*"  rows="*" id="frame">
		<frame src="left.jsp" name="leftFrame" noresize="noresize" marginwidth="0" marginheight="0" frameborder="0" scrolling="no" target="main" />
		<frame src="right.jsp" name="main" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto" target="_self" />
	 </frameset>
	</frameset>
 <noframes>
 <body></body>
  </noframes>
 <%
 } else {
  %>
  <style>
  body{background-color: blue}
  .lo{background-color: red; text-align: center; font-size: 30px; font-weight: 5px; margin-top: 150px;}
  </style>
   <body>
   <script type="text/javascript">
   alert("没有登录！");
   </script>
   <div class="lo">
   <a href="login.jsp">登录</a>
   </div>
   </body>
  <%
  }
   %>
   
</html>
