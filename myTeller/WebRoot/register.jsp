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
			alert("��ˮ�Ų���Ϊ��");
			return false;
		}
		if(card==""){
			alert("���֤�Ų���Ϊ��");
			return false;
		}
		if(card.length!=16){
			alert("���֤������ʮ��λ");
			return false;
		}
		
		if(name==""){
			alert("��������Ϊ��");
			return false;
		}
		if(home==""){
			alert("��ַ����Ϊ��");
			return false;
		}
		
		if(tel==""){
			alert("��ϵ�绰����Ϊ��");
			return false;
		}
		//if(!patrn.exec(tel)){
			//alert("�绰�Ƿ�����");
			//return false;
		//}
		
		if(age==""){
			alert("���䲻��Ϊ��");
			return false;
		}
		if(age.search("^-?\\d+$")!=0){
			alert("���䲻�Ϸ�");
			return false;
		}
		
		if(birthday==""){
			alert("���ղ���Ϊ��");
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
        <td height="31"><div class="titlebt">��������</div></td>
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
            <td class="left_txt">��ǰλ�ã���������</td>
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
                <td width="90%" valign="top"><span class="left_txt2">����������Ը���
				�ͻ���Ϣ�����</span><span class="left_txt3">��������</span><span class="left_txt2">��</span><br>
                          <span class="left_txt2">����</span><span class="left_txt3">�û���ţ��û��������û��Ա��û�סַ���û��绰���û���ͥ���û����䣬��ע���û����գ�����</span>
                          <span class="left_txt2">�Լ�������</span><span class="left_txt3">��Ա���ȼ���������</span><span class="left_txt2">�� </span></td>
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
                <td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;ע��ҳ��</td>
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
                <td height="30" align="right" class="left_txt2">��&nbsp;&nbsp;ˮ&nbsp;&nbsp;�ţ�</td>
                <td>&nbsp;</td>
                <td height="30">
                <input type="text" name="flowNo" size="19" id="username" readOnly/><input type="button" id="b1" onclick="checkUserid()" value="��ȡ��ˮ"><a class="must">*</a>
                </td>
                <td height="30" class="left_txt">ϵͳ��ˮ��</td>
              </tr>		
              	<tr>
                <td width="20%" height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">���֤�ţ�</td>
                <td width="3%" bgcolor="#f2f2f2">&nbsp;</td>
                <td width="32%" height="30" bgcolor="#f2f2f2"><input name="guest_id" type="text" id="title" size="30" /><a class="must">*</a></td>
                <td width="45%" height="30" bgcolor="#f2f2f2" class="left_txt">�û�ID</td>
              </tr>
              <tr>
                <td height="30" align="right" class="left_txt2">�û�������</td>
                <td>&nbsp;</td>
                <td height="30"><input type="text" name="guest_name" size="30" /><a class="must">*</a></td>
                <td height="30" class="left_txt">�û�����</td>
              </tr>
              <tr>
                <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">�û��Ա�</td>
                <td bgcolor="#f2f2f2">&nbsp;</td>
                <td height="30" bgcolor="#f2f2f2">�У�<input type="radio" name="guest_sex" size="25" checked="checked" />
				&nbsp;Ů��<input type="radio" name="guest_sex" size="25" /></td>
                <td height="30" bgcolor="#f2f2f2" class="left_txt">�û��Ա�</td>
              </tr>
              <tr>
                <td height="30" align="right" class="left_txt2">�û�סַ�� </td>
                <td>&nbsp;</td>
                <td height="30"><input type="text" name="guest_home" size="30" /><a class="must">*</a></td>
                <td height="30" class="left_txt">��ͥסַ</td>
              </tr>
              <tr>
                <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">�û��绰��</td>
                <td bgcolor="#f2f2f2">&nbsp;</td>
                <td height="30" bgcolor="#f2f2f2"><input type="text" name="guest_tel" size="30" /><a class="must">*</a></td>
                <td height="30" bgcolor="#f2f2f2" class="left_txt">��ϵ��ʽ</td>
              </tr>
             
              <tr>
                <td height="30" align="right" bgcolor="#F2F2F2" class="left_txt2">�û����䣺</td>
                <td bgcolor="#F2F2F2">&nbsp;</td>
                <td height="30" bgcolor="#F2F2F2"><input name="guest_age" type="text" id="guest_age" size="30" /><a class="must">*</a></td>
                <td height="30" bgcolor="#F2F2F2"><span class="left_txt">����</span></td>
              </tr>
              <tr>
                <td height="30" align="right" class="left_txt2">��ע��</td>
                <td>&nbsp;</td>
                <td height="30"><input type="text" name="guest_demo" size="30" value="--" /></td>
                <td height="30"><span class="left_txt">˵��</span></td>
              </tr>
              <tr>
                <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">�û����������գ� </td>
                <td bgcolor="#f2f2f2">&nbsp;</td>
                <td height="30" bgcolor="#f2f2f2"><input type="text" name="guest_birthday" size="30" /><a class="must">*</a></td>
                <td height="30" bgcolor="#f2f2f2"><span class="left_txt">����������</span></td>
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
              <td width="50%" height="30" align="right"><input type="submit" value="�ύ" name="B1" /></td>
              <td width="6%" height="30" align="right">&nbsp;</td>
              <td width="44%" height="30"><input type="reset" value="ȡ��" name="B12" /></td>
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
 alert("�����ɹ���\n"+"����˻����ǣ�"+<%out.println(id[1]); %>+"\n"+"����������ǣ�"+<%out.println(psw[1]);%>);
 </script>
	<div align="center" style="color: red" id="ok" >����˻����ǣ�<%out.println(id[1]); %>
	����������ǣ�<%out.println(psw[1]); %> <input type="button" value="ȷ��" onclick="hidden()"/>
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
 alert("����ʧ�ܣ�������");
 </script>
 <%
 	}
 }
 %>
</body>

</html>
