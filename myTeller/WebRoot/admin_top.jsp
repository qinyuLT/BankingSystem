<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.dc.bean.LoginBean"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<base href="<%=basePath %>">
<head>
<title> - 管理页面 </title>
<script language=JavaScript>
function logout(){
	if (confirm("您确定要退出系统吗？"))
	top.location = "leave.jsp";
	return false;
}
</script>
<script language=JavaScript1.2>
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
</script>

<script language=JavaScript1.2>
function showsubmenu(sid) {
	var whichEl = eval("submenu" + sid);
	var menuTitle = eval("menuTitle" + sid);
	if (whichEl.style.display == "none"){
		eval("submenu" + sid + ".style.display=\"\";");
	}else{
		eval("submenu" + sid + ".style.display=\"none\";");
	}
}
</script>

<link href="images/skin.css" rel="stylesheet" type="text/css">

    
    <meta http-equiv=Content-Type content=text/html;charset=gb2312>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  <%
  LoginBean bean = (LoginBean)request.getSession().getAttribute("user");
   %>
  
  <body leftmargin="0" topmargin="0">
 
<table width="100%" height="64" border="0" cellpadding="0" cellspacing="0" class="admin_topbg">
  <tr>
    <td width="61%" height="64"><img src="images/logo.gif" width="262" height="64"></td>
    <td width="39%" valign="top"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr style="margin: 0px">
        <td width="89%" height="38" class="admin_txt"><b>
        <%=bean.getUser_name()%>你好！&nbsp;
             登录号：<%=bean.getLogin_no() %>&nbsp;
             网点号：<%=bean.getNet_id() %>
        </b>
        </td>
        <td width="10%"><a href="#" target="_self" onClick="logout();"><img src="images/out.gif" alt="安全退出" width="46" height="20" border="0"></a></td>
        <td width="1%">&nbsp;</td>
      </tr>
      <tr>
        <td height="19" colspan="3">&nbsp;</td>
        </tr>
    </table>
    </td>
  </tr>
</table>

</body>
</html>
