<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@page import="com.dc.DAO.GetFlowNoDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  
  <script language="javascript" src="js/getFlowNo.js"></script>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
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
<script type="text/javascript" >

	function check(){
		var oo = document.getElementById("username").Value;
		var flow_no=document.form1.flowNo.value;
		var card=document.form1.guest_id.value;
		var name=document.form1.guest_name.value;
		var home=document.form1.guest_home.value;
		var tel=document.form1.guest_tel.value;
		var age=document.form1.guest_age.value;
		var birthday=document.form1.guest_birthday.value;
		var patrn="/^(\(\d{0,3}\))|(\d{0,3}\-))?13|15\d{9}$/";
		if(oo==""){
			alert("流水号不能为空");
			return false;
		}
		if(card==""){
			alert("身份证号不能为空");
			return false;
		}
		if(card.length!=16){
			alert("身份证必须是十六位");
			return false;
		}
		
		if(name==""){
			alert("姓名不能为空");
			return false;
		}
		if(home==""){
			alert("地址不能为空");
			return false;
		}
		
		if(tel==""){
			alert("联系电话不能为空");
			return false;
		}
		//if(!patrn.exec(tel)){
			//alert("电话非法输入");
			//return false;
		//}
		
		if(age==""){
			alert("年龄不能为空");
			return false;
		}
		if(age.search("^-?\\d+$")!=0){
			alert("年龄不合法");
			return false;
		}
		
		if(birthday==""){
			alert("生日不能为空");
			return false;
		}
	}
</script>
  </head>
  
  <body>
 <form name="form1" method="POST" action="RegisterServlet" onsubmit="return check()">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" height="29" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
    <td width="935" height="29" valign="top" background="images/content-bg.gif">
    <table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">开户设置</div></td>
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
				客户信息填入基</span><span class="left_txt3">基本参数</span><span class="left_txt2">！</span><br>
                          <span class="left_txt2">包括</span><span class="left_txt3">用户编号，用户姓名，用户性别，用户住址，用户电话，用户家庭，用户年龄，备注，用户生日，卡号</span>
                          <span class="left_txt2">以及卡密码</span><span class="left_txt3">会员及等级积分设置</span><span class="left_txt2">。 </span></td>
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
                <td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;注册页面</td>
              </tr>
            </table>
            </td>
          </tr>
          <tr>
            <td>
            
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
<td><input name="server_id" type="hidden" value="06"/></td>
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
                <td width="20%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">身份证号：</td>
                <td width="3%" bgcolor="#f2f2f2">&nbsp;</td>
                <td width="32%" height="30" bgcolor="#f2f2f2"><input name="guest_id" type="text" id="title" size="30" /><a class="must">*</a></td>
                <td width="45%" height="30" bgcolor="#f2f2f2" class="left_txt">用户ID</td>
              </tr>
              <tr>
                <td height="30" align="right" class="left_txt2">用户姓名：</td>
                <td>&nbsp;</td>
                <td height="30"><input type="text" name="guest_name" size="30" /><a class="must">*</a></td>
                <td height="30" class="left_txt">用户姓名</td>
              </tr>
              <tr>
                <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">用户性别：</td>
                <td bgcolor="#f2f2f2">&nbsp;</td>
                <td height="30" bgcolor="#f2f2f2">男：<input type="radio" name="guest_sex" size="25" checked="checked" />
				&nbsp;女：<input type="radio" name="guest_sex" size="25" /></td>
                <td height="30" bgcolor="#f2f2f2" class="left_txt">用户性别</td>
              </tr>
              <tr>
                <td height="30" align="right" class="left_txt2">用户住址： </td>
                <td>&nbsp;</td>
                <td height="30"><input type="text" name="guest_home" size="30" /><a class="must">*</a></td>
                <td height="30" class="left_txt">家庭住址</td>
              </tr>
              <tr>
                <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">用户电话：</td>
                <td bgcolor="#f2f2f2">&nbsp;</td>
                <td height="30" bgcolor="#f2f2f2"><input type="text" name="guest_tel" size="30" /><a class="must">*</a></td>
                <td height="30" bgcolor="#f2f2f2" class="left_txt">联系方式</td>
              </tr>
             
              <tr>
                <td height="30" align="right" bgcolor="#F2F2F2" class="left_txt2">用户年龄：</td>
                <td bgcolor="#F2F2F2">&nbsp;</td>
                <td height="30" bgcolor="#F2F2F2"><input name="guest_age" type="text" id="guest_age" size="30" /><a class="must">*</a></td>
                <td height="30" bgcolor="#F2F2F2"><span class="left_txt">年龄</span></td>
              </tr>
              <tr>
                <td height="30" align="right" class="left_txt2">备注：</td>
                <td>&nbsp;</td>
                <td height="30"><input type="text" name="guest_demo" size="30" value="--" /></td>
                <td height="30"><span class="left_txt">说明</span></td>
              </tr>
              <tr>
                <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">用户出生年月日： </td>
                <td bgcolor="#f2f2f2">&nbsp;</td>
                <td height="30" bgcolor="#f2f2f2"><input type="text" name="guest_birthday" size="30" /><a class="must">*</a></td>
                <td height="30" bgcolor="#f2f2f2"><span class="left_txt">出生年月日</span></td>
              </tr>
              
              <tr>
                <td height="17" colspan="4" align="right" >&nbsp;</td>
              </tr>
              <tr>
                <td height="30" colspan="4" align="right" class="left_txt2"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="nowtable">
                  <tr>
                    <td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;</td>
                  </tr>
                </table></td>
              </tr>
        
            </table>
          </tr>
        </table>
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td width="50%" height="30" align="right"><input type="submit" value="提交" name="B1" /></td>
              <td width="6%" height="30" align="right">&nbsp;</td>
              <td width="44%" height="30"><input type="reset" value="取消" name="B12" /></td>
            </tr>
            <tr>
              <td height="30" colspan="3">&nbsp;</td>
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
 		String[] id = mess[1].split("=");
 		String[] psw = mess[2].split("=");
 %>
 <script type="text/javascript">
 alert("开户成功！\n"+"你的账户号是："+<%out.println(id[1]); %>+"\n"+"你的账密码是："+<%out.println(psw[1]);%>);
 </script>
	<div align="center" style="color: red" id="ok" >你的账户号是：<%out.println(id[1]); %>
	你的账密码是：<%out.println(psw[1]); %> <input type="button" value="确定" onclick="hidden()"/>
	</div>
	if(confor())
	<script type="text/javascript">
	function hidden(){
	document.getElementById("ok").innerHTML="";
	}
 </script>
	
 <%
 	}else{
 %>
 <script type="text/javascript">
 alert("开户失败！！！！");
 </script>
 <%
 	}
 }
 %>
</body>

</html>
