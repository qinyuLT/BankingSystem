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
    <td width="935" height="29" valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">查询设置</div></td>
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
         <form name="form2" method="POST" action="">
          <tr>
            <td class="left_txt">请选择：
            <select name="check">
            <option value="user">用户</option>
            <option value="loan">贷款</option>
            </select>&nbsp;&nbsp;&nbsp;<input type="submit" value="确定">
            </td>
          </tr></form>
          
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
                <td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;查询页面</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			 <tr>
              
			  	<td>
	<input name="server_id" type="hidden" value="001" />
			  	</td>
                </tr> 
   
           <tr>
			   <td colspan="4" height="30" align="center" class="left_txt2" style="font-size: 18px"><b>发放计划</b></td>
			 </tr>
			 <table align="center">
			 <tr>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">贷款人</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">发放账号</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">时间</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">金额</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">负责人</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">状态</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">详细</td>
			 </tr>
			 <tr height="30"  class="left_txt2">
			 <td><input type="text" name="ex_time1" size="15" /></td>
			 <td><input type="text" name="ex_money1" size="15" /></td>
			 <td><input type="text" name="ex_time1" size="15" /></td>
			 <td><input type="text" name="ex_money1" size="15" /></td>
			 <td><input type="text" name="ex_man1" size="15" /></td>
			 <td><input type="text" name="ex_state1" size="15" /></td>
			  <td><input type="text" name="ex_state1" size="15" /></td>
			 </tr>
			 <tr height="30" bgcolor="#f2f2f2" class="left_txt2">
			 <td><input type="text" name="ex_time2" size="15" /></td>
			 <td><input type="text" name="ex_money2" size="15" /></td>
			 <td><input type="text" name="ex_time2" size="15" /></td>
			 <td><input type="text" name="ex_money2" size="15" /></td>
			 <td><input type="text" name="ex_man2" size="15" /></td>
			 <td><input type="text" name="ex_state2" size="15" /></td>
			  <td><input type="text" name="ex_state1" size="15" /></td>
			 </tr>
			 
			 </table>
			 <tr>
			   <td colspan="4" height="30" align="center" class="left_txt2" style="font-size: 18px"><b>回收计划</b></td>
			 </tr>
			 <table align="center">
               <tr>
               <td height="30"  bgcolor="#f2f2f2" class="left_txt2">贷款人</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">回收账号</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">时间</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">金额</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">负责人</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">状态</td>
			 	 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">详细</td>
			 </tr>
			 <tr height="30"  class="left_txt2">
			  <td><input type="text" name="ex_state1" size="15" /></td>
			 <td><input type="text" name="in_time1" size="15" /></td>
			 <td><input type="text" name="in_money1" size="15" /></td>
			 <td><input type="text" name="in_time1" size="15" /></td>
			 <td><input type="text" name="in_money1" size="15" /></td>
			 <td><input type="text" name="in_man1" size="15" /></td>
			 <td><input type="text" name="in_state1" size="15" /></td>
			 </tr>
			 <tr height="30" bgcolor="#f2f2f2" class="left_txt2">
			  <td><input type="text" name="ex_state1" size="15" /></td>
			 <td><input type="text" name="in_time1" size="15" /></td>
			 <td><input type="text" name="in_money1" size="15" /></td>
			 <td><input type="text" name="in_time2" size="15" /></td>
			 <td><input type="text" name="in_money2" size="15" /></td>
			 <td><input type="text" name="in_man2" size="15" /></td>
			 <td><input type="text" name="in_state2" size="15" /></td>
			 
			 </table>
             
              
              <tr>
                <td height="30" colspan="4" class="left_txt2">
				</td>
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

<% if(request.getAttribute("200") != null){
%>
<script type="text/javascript">
alert("成功");
</script>
<%
}
 %>
</body>
</html>
