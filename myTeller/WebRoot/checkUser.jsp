<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.dc.DAO.GetFlowNoDAO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'check.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F8F9FA;
}
-->
.must{color: red ;font-size: 20px; margin-top: 10px}
</style>

<link href="images/skin.css" rel="stylesheet" type="text/css" />
  </head>
  
  <body>
 
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" height="29" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
    <td width="935" height="29" valign="top" background="images/content-bg.gif">
    <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">查询设置</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td height="71" valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9">
    <table width="100%" height="138" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="13" valign="top">&nbsp;</td>
      </tr>
      <tr>
        <td valign="top">
        <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
           <form name="form2" method="POST" action="SearchServlet">
            <td class="left_txt">请选择：
            <select name="check">
            <option value="userOne">单个用户</option>
            <option value="userAll">全部用户</option>
            </select>&nbsp;&nbsp;&nbsp;
            <input name="server_id" type="hidden" value="01" />
           	 账号<input name="card_id" type="text"  />&nbsp;&nbsp;&nbsp;
            <input type="submit" value="确定">
            </td>
             </form>
          </tr>
         

          <tr>
            <td height="20">
            <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              <tr>
                <td></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td>
            <table width="100%" height="55" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="10%" height="55" valign="middle"><img src="images/title.gif" width="54" height="55"></td>
                <td width="90%" valign="top"><span class="left_txt2">在这里，您可以根据
				客户信息填入基</span><span class="left_txt3">基本参数</span><span class="left_txt2">！</span><br>
                          <span class="left_txt2">包括</span><span class="left_txt3">用户姓名，金额，卡号</span><span class="left_txt2">以及卡密码</span><span class="left_txt3">会员及等级积分设置</span><span class="left_txt2">。 </span></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td>
            <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="nowtable">
              <tr>
                <td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;用户查询页面</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td>
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
			 <tr>
              
			  	<td>
	<input name="server_id" type="hidden" value="01" />
			  	</td>
                </tr> 
   
           <tr>
			   <td colspan="4" height="30" align="center" class="left_txt2" style="font-size: 18px"><b>账户信息</b></td>
			 </tr>
			 <tr>
			 <td height="30" colspan="4" class="left_txt2">
				
			 <table align="center">
			 <tr>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2" width="100">账号</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2" width="100">ID卡</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2" width="100">余额</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2" width="100">状态</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2" width="100">详细</td>
			 </tr>
			 <%
			String mess = (String)request.getAttribute("result");
			if(mess != null){
			String[] mess1 = mess.split(";");
				if(mess1[0].split("=")[1].equals("0")){
					String[] message = mess1[2].split("=");
					if(!message[1].equals("-1")){
					String[] messages = message[1].split("#");
					%>
			
			 
			 <tr height="30"  class="left_txt2">
			<td height="30"  bgcolor="#f2f2f2" class="left_txt2"><%=messages[0]%></td>
			<td height="30"  bgcolor="#f2f2f2" class="left_txt2"><%=messages[1]%></td>
			<td height="30"  bgcolor="#f2f2f2" class="left_txt2"><%=messages[2]%></td>
			<td height="30"  bgcolor="#f2f2f2" class="left_txt2"><%=messages[3]%></td>
			<td height="30"  bgcolor="#f2f2f2" class="left_txt2"><%=messages[4]%></td>
			 </tr>
			 <% 
			 }else{
			 out.print("<script>alert('没有此结果');</script>");
			 }
			 	}
			}
			%>
			</table>
			</td>
			</tr>
              <tr>
                <td height="30" colspan="4" class="left_txt2">
				</td>
              </tr>
              
            </table></td>
          </tr>
        </table>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="50%" height="30" align="right"><input type="button" value="上一页" name="Button1" /></td>
              <td width="6%" height="30" align="right">&nbsp;</td>
              <td width="44%" height="30"><input type="button" value="下一页" name="Button2" /></td>
            </tr>
            
          </table></td>
      </tr>
    </table></td>

  </tr>
  
</table>

</body>
</html>
