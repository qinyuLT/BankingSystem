<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.dc.DAO.GetFlowNoDAO"%>
<%@page import="com.dc.bean.LoginBean"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'putMoney.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="javascript" src="js/getFlowNo.js"></script>
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
<script type="text/javascript" >

	function check(){
		var oo = document.getElementById("username").Value;
		var flow_no=document.form2.flowNo.value;
		var card=document.form2.card_id.value;
		var guest=document.form2.guest_id.value;
		var oldPsw=document.form2.old_password.value;
		var newPsw1=document.form2.new_password1.value;
		var newPsw2=document.form2.new_password2.value;
		
		var patrn="/^(\(\d{0,3}\))|(\d{0,3}\-))?13|15\d{9}$/";
		if(flow_no==""){
			alert("流水号不能为空");
			return false;
		}
		if(card==""){
			alert("卡号不能为空");
			return false;
		}		
		if(guest==""){
			alert("身份证不能为空");
			return false;
		}
		if(oldPsw==""){
			alert("旧密码不能为空");
			return false;
		}
		if(newPsw1 != newPsw2){
			alert("两次密码不一致");
			return false;
		}
	}
</script>


  </head>
  
  <%
  LoginBean bean =(LoginBean)session.getAttribute("user");
	request.setAttribute("user",bean);
   %>
  
   <body>
  <form name="form2" method="POST" action="ChangeAccPsw" onsubmit="return check()">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" height="29" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
    <td width="935" height="29" valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">修改密码</div></td>
      </tr>
    </table></td>
    <td width="16" valign="top" background="images/mail_rightbg.gif"><img src="images/nav-right-bg.gif" width="16" height="29" /></td>
  </tr>
  <tr>
    <td height="71" valign="middle" background="images/mail_leftbg.gif">&nbsp;</td>
    <td valign="top" bgcolor="#F7F8F9"><table width="100%" height="138" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td height="13" valign="top">&nbsp;</td>
      </tr>
      <tr>
        <td valign="top"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td class="left_txt">当前位置：基本设置</td>
          </tr>
          <tr>
            <td height="20"><table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
              <tr>
                <td></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td><table width="100%" height="55" border="0" cellpadding="0" cellspacing="0">
              <tr>
                <td width="10%" height="55" valign="middle"><img src="images/title.gif" width="54" height="55"></td>
                <td width="90%" valign="top"><span class="left_txt2">在这里，您可以根据
				客户信息填入</span><span class="left_txt3">基本参数</span><span class="left_txt2">！</span><br>
                          <span class="left_txt2">包括</span><span class="left_txt3">用户姓名，金额，卡号</span><span class="left_txt2">以及卡密码</span><span class="left_txt3">会员及等级积分设置</span><span class="left_txt2">。 </span></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="nowtable">
              <tr>
                <td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;修改密码页面</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			 <tr>
              
			  	<td>
	<input name="server_id" type="hidden" value="15" />
	<input name="user" type="hidden" value="<%=bean.getUser_name() %>" />
			  	</td>
                </tr> 
                            
                <tr>
                <td height="30" align="right" class="left_txt2">流&nbsp;&nbsp;水&nbsp;&nbsp;号：</td>
                <td>&nbsp;</td>
                <td height="30">
                <input type="text" name="flowNo" size="19" id="username" readOnly/><input type="button" id="b1" onclick="checkUserid()" value="获取流水"><a class="must">*</a>
                </td>
                <td height="30" class="left_txt">系统流水号</td>
              </tr>	
           
              <tr>
                <td height="30" align="right" class="left_txt2">卡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
                <td>&nbsp;</td>
                <td height="30"><input type="text" name="card_id" style="width:222px"/><a class="must">*</a></td>
                <td height="30" class="left_txt">卡ID</td>
              </tr>
              
              <tr>
                <td height="30" align="right" class="left_txt2">证&nbsp;&nbsp;件&nbsp;&nbsp;号：</td>
                <td>&nbsp;</td>
                <td height="30"><input type="text" name="guest_id" style="width:222px"/><a class="must">*</a></td>
                <td height="30" class="left_txt">证件号</td>
              </tr>
              
              <tr>
                <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">原&nbsp;&nbsp;密&nbsp;&nbsp;码：</td>
                <td bgcolor="#f2f2f2">&nbsp;</td>
                <td height="30" bgcolor="#f2f2f2"><input type="password" name="old_password" style="width:222px" /><a class="must">*</a></td>
                <td height="30" bgcolor="#f2f2f2" class="left_txt">原密码</td>
              </tr>
 			<tr>
                <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">新&nbsp;&nbsp;密&nbsp;&nbsp;码：</td>
                <td bgcolor="#f2f2f2">&nbsp;</td>
                <td height="30" bgcolor="#f2f2f2"><input type="password" name="new_password1" style="width:222px" /><a class="must">*</a></td>
                <td height="30" bgcolor="#f2f2f2" class="left_txt">新密码</td>
              </tr>
              <tr>
                <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">确定新密码：</td>
                <td bgcolor="#f2f2f2">&nbsp;</td>
                <td height="30" bgcolor="#f2f2f2"><input type="password" name="new_password2" style="width:222px" /><a class="must">*</a></td>
                <td height="30" bgcolor="#f2f2f2" class="left_txt">确定新密码</td>
              </tr>
              <tr>
                <td height="17" colspan="4" align="right" >&nbsp;</td>
              </tr>
              
              <tr>
                <td height="30" colspan="4" class="left_txt2">
				<table width="100%" height="99" border="0" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="20%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt"> </td>
                    <td width="20%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt"></td>
                    <td width="60%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="30" align="right" class="left_txt"></td>
                    <td height="30" align="right" class="left_txt"></td>
                    <td height="30" align="right" class="left_txt">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt">
                     </td>
                    <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt"><br><br></td>
                    <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt">&nbsp;</td>
                  </tr>
                  <tr>
                    <td height="16" colspan="3" align="right">&nbsp;</td>
                  </tr>
                  
                 
                </table></td>
              </tr>
              
            </table></td>
          </tr>
        </table>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="50%" height="30" align="right"><input type="submit" value="提交" name="B1" /></td>
              <td width="6%" height="30" align="right">&nbsp;</td>
              <td width="44%" height="30"><input type="reset" value="取消" name="B12" /></td>
            </tr>
            
          </table></td>
      </tr>
    </table></td>

  </tr>
  
</table>
</form>
<% 
 String result = (String)request.getAttribute("result");
 if(result != null) {
 	String[] mess = result.split(";");
 	String[] getmess = mess[0].split("=");
 	if(getmess[1].equals("0")){
 	%>
	 <script type="text/javascript">
	 alert("修改密码成功！");
	 </script>
 	<%
 	}else{
 	%>
	 <script type="text/javascript">
	 alert("修改密码失败！");
	 </script>
 	<%
 	}
 }
 %>
</body>
</html>
