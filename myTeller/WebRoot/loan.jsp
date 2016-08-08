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
    
    <title>My JSP 'loan.jsp' starting page</title>
    
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
  </head>
  
  <body>
  <form name="form2" method="POST" action="">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td width="17" height="29" valign="top" background="images/mail_leftbg.gif"><img src="images/left-top-right.gif" width="17" height="29" /></td>
    <td width="935" height="29" valign="top" background="images/content-bg.gif"><table width="100%" height="31" border="0" cellpadding="0" cellspacing="0" class="left_topbg" id="table2">
      <tr>
        <td height="31"><div class="titlebt">贷款设置</div></td>
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
                <td class="left_bt2">&nbsp;&nbsp;&nbsp;&nbsp;贷款页面</td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
			 <tr>
              
			  	<td>
	<input name="server_id" type="hidden" value="01" />
			  	</td>
                </tr> 
                           
                <tr>
                <td height="30" align="right" class="left_txt2">流&nbsp;&nbsp;水&nbsp;&nbsp;号：</td>
                <td>&nbsp;</td>
                <td height="30">
                <input type="text" name="flowNo" size="19" id="username" readOnly/>
                <input type="button" id="b1" onclick="checkUserid()" value="获取流水"><a class="must">*</a>
                </td>
                <td height="30" class="left_txt">系统流水号</td>
              </tr>	
              <tr>
                <td height="30" align="right" class="left_txt2">签&nbsp;&nbsp;约&nbsp;&nbsp;人：</td>
                <td>&nbsp;</td>
                <td height="30"><input type="text" name="guest_name" size="30" /><a class="must">*</a></td>
                <td height="30" class="left_txt">签约人姓名</td>
              </tr>
               <tr>
                <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">经&nbsp;&nbsp;办&nbsp;&nbsp;人：</td>
                <td bgcolor="#f2f2f2">&nbsp;</td>
                <td height="30" bgcolor="#f2f2f2"><input type="text" name="conductor" size="30" /><a class="must">*</a></td>
                <td height="30" bgcolor="#f2f2f2" class="left_txt">经办人名字</td>
              </tr>
 				<tr>
                <td height="30" align="right" class="left_txt2">金&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;额：</td>
                <td>&nbsp;</td>
                <td height="30"><input type="text" name="loanMoney" size="30" /><a class="must">*</a></td>
                <td height="30" class="left_txt">贷款金额</td>
              </tr>
              <tr>
                <td height="30" align="right" class="left_txt2">发放卡号：</td>
                <td>&nbsp;</td>
                <td height="30"><input type="text" name="card_id" size="30" /><a class="must">*</a></td>
                <td height="30" class="left_txt">卡ID</td>
              </tr>
             
              <tr>
                <td height="30" align="right" bgcolor="#f2f2f2" class="left_txt2">回收卡号：</td>
                <td bgcolor="#f2f2f2">&nbsp;</td>
                <td height="30" bgcolor="#f2f2f2"><input type="text" name="card_id_back" size="30" /><a class="must">*</a></td>
                <td height="30" bgcolor="#f2f2f2" class="left_txt">卡ID</td>
              </tr>
 				<tr>
                <td height="30" align="right" class="left_txt2">签约时间：</td>
                <td>&nbsp;</td>
                <td height="30"><input type="text" name="time" size="30" /><a class="must">*</a></td>
                <td height="30" class="left_txt">签约时间</td>
              </tr>
              <tr>
                <td height="30" align="right" class="left_txt2">产品代号：</td>
                <td>&nbsp;</td>
                <td height="30"><input type="text" name="product_id" size="30" /><a class="must">*</a></td>
                <td height="30" class="left_txt">金融产品代号</td>
              </tr>
			  <tr>
			   <td colspan="4" height="30" align="center" class="left_txt2" style="font-size: 18px"><b>发放计划</b></td>
			 </tr>
			 <tr>
			   <td colspan="4" height="30" align="center" class="left_txt2"><input type="button" value="添加" onclick="insertRow()"/>
			   &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="减少" onclick="deleteRow()"/>&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="count_EX" id="count_EX" value="1"/> </td>
			 </tr>
			 <table align="center" id="table_EX">
			 <tbody>
			 <tr>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">贷款编号</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">时间</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">金额</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">发放卡号</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">负责人</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">状态</td>
			 </tr>
			 <tr height="30"  class="left_txt2">
			 <td><input type="text" name="ex_ID1" size="15" /></td>
			 <td><input type="text" name="ex_time1" size="15" /></td>
			 <td><input type="text" name="ex_money1" size="15" /></td>
			 <td><input type="text" name="ex_cardId1" size="15" /></td>
			 <td><input type="text" name="ex_man1" size="15" /></td>
			 <td><input type="text" name="ex_state1" size="15" /></td>
			 </tr>
			 </tbody>
			 </table>
			 <tr>
			   <td colspan="4" height="30" align="center" class="left_txt2" style="font-size: 18px"><b>回收计划</b></td>
			 </tr>
			 
			 <table align="center" id="table_IN">
			 <tbody>
			 <tr>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">贷款编号</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">回收编号</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">时间</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">金额</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">回收卡号</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">负责人</td>
			 <td height="30"  bgcolor="#f2f2f2" class="left_txt2">状态</td>
			 </tr>
			 <tr height="30"  class="left_txt2">
			 <td><input type="text" name="ex_ID1" size="15" /></td>
			 <td><input type="text" name="in_ID1" size="15" /></td>
			 <td><input type="text" name="in_time1" size="15" /></td>
			 <td><input type="text" name="in_money1" size="15" /></td>
			 <td><input type="text" name="in_cardId1" size="15" /></td>
			 <td><input type="text" name="in_man1" size="15" /></td>
			 <td><input type="text" name="in_state1" size="15" /></td>
			 </tr>
			 </tbody>
			 </table>
			 
			 <tr>
			   <td colspan="4" height="30" align="center" class="left_txt2"><input type="button" value="添加" onclick="insertRow2()"/>
			   &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="减少" onclick="deleteRow2()"/>&nbsp;&nbsp;&nbsp;&nbsp; <input type="text" name="count_IN" id="count_IN" value="1"/></td>
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
<% if(request.getAttribute("200") != null){
%>
<script type="text/javascript">
alert("成功");
</script>
<%
}
 %>

<script type="text/javascript">
 var i=2;
 function deleteRow2(){
 var obj=document.getElementById("table_IN");
 var rowIndex=obj.firstChild.childNodes.length;
 if(rowIndex>2){
 obj.deleteRow(rowIndex-parseInt(1));
 i=i-1;
 document.getElementById("count_IN").value=i-1;
 }
 }
 
 function insertRow2(){
 var obj=document.getElementById("table_IN");
 var rowIndex=obj.firstChild.childNodes.length;
 var objTR=obj.insertRow(rowIndex);
 var onjTD1=objTR.insertCell();
 var onjTD2=objTR.insertCell();
 var onjTD3=objTR.insertCell();
 var onjTD4=objTR.insertCell();
 var onjTD5=objTR.insertCell();
 var onjTD6=objTR.insertCell();
 var onjTD7=objTR.insertCell();
 rowIndex2=parseInt(rowIndex)+parseInt(1);
 
 onjTD1.innerHTML= "<td><input type='text' name='ex_ID"+i+"' size='15' /></td>";
 onjTD2.innerHTML= "<td><input type='text' name='in_ID"+i+"' size='15' /></td>";
 onjTD3.innerHTML= "<td><input type='text' name='in_time"+i+"' size='15' /></td>";
 onjTD4.innerHTML= "<td><input type='text' name='in_money"+i+"' size='15' /></td>";
 onjTD5.innerHTML= "<td><input type='text' name='in_cardId"+i+"' size='15' /></td>";
 onjTD6.innerHTML= "<td><input type='text' name='in_man"+i+"' size='15' /></td>";
 onjTD7.innerHTML= "<td><input type='text' name='in_state"+i+"' size='15' /></td>";
 document.getElementById("count_IN").value=i;
 i=i+1;
 }
</script>
<script type="text/javascript">
 var i=2;
 function deleteRow(){
 var obj=document.getElementById("table_EX");
 var rowIndex=obj.firstChild.childNodes.length;
 if(rowIndex>2){
 obj.deleteRow(rowIndex-parseInt(1));
 i=i-1;
 document.getElementById("count_EX").value=i-1;
 }
 }
 
 function insertRow(){
 var obj=document.getElementById("table_EX");
 var rowIndex=obj.firstChild.childNodes.length;
 var objTR=obj.insertRow(rowIndex);
 var onjTD1=objTR.insertCell();
 var onjTD2=objTR.insertCell();
 var onjTD3=objTR.insertCell();
 var onjTD4=objTR.insertCell();
 var onjTD5=objTR.insertCell();
 var onjTD6=objTR.insertCell();
 rowIndex2=parseInt(rowIndex)+parseInt(1);
 
 onjTD1.innerHTML= "<td><input type='text' name='ex_ID"+i+"' size='15' /></td>";
 onjTD2.innerHTML= "<td><input type='text' name='ex_time"+i+"' size='15' /></td>";
 onjTD3.innerHTML= "<td><input type='text' name='ex_money"+i+"' size='15' /></td>";
 onjTD4.innerHTML= "<td><input type='text' name='ex_cardId"+i+"' size='15' /></td>";
 onjTD5.innerHTML= "<td><input type='text' name='ex_man"+i+"' size='15' /></td>";
 onjTD6.innerHTML= "<td><input type='text' name='ex_state"+i+"' size='15' /></td>";
 document.getElementById("count_EX").value=i;
 i=i+1;
 }
</script>
</body>
</html>
