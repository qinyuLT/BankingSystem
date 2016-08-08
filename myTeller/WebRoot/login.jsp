<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page import="com.dc.DAO.GetLoginNoDAO"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- saved from url=(0036)http://localhost:10868/hr/login.aspx -->
<!-- saved from url=(0041)http://www.youhao.com/manage/YHLogin.aspx -->
<html>
	<HEAD>
	<base href="<%=basePath%>">
	<TITLE>用户登录</TITLE>
	<LINK href="images/Default.css" type=text/css rel=stylesheet>
	<LINK href="images/xtree.css" type=text/css rel=stylesheet>
	<LINK href="images/User_Login.css" type=text/css rel=stylesheet>
    <META http-equiv=Content-Type content="text/html; charset=utf-8">
    <META content="MSHTML 6.00.6000.16809" name=GENERATOR>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<STYLE type=text/css>
BODY {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
TD {
	FONT-SIZE: 12px; COLOR: #ffffff; FONT-FAMILY: 宋体
}
</STYLE>


  </head>
  
 
 <BODY>


<SCRIPT type=text/javascript>
<!--
var theForm = document.forms['form1'];
if (!theForm) {
    theForm = document.form1;
}
function __doPostBack(eventTarget, eventArgument) {
    if (!theForm.onsubmit || (theForm.onsubmit() != false)) {
        theForm.__EVENTTARGET.value = eventTarget;
        theForm.__EVENTARGUMENT.value = eventArgument;
        theForm.submit();
    }
}
// -->
</SCRIPT>

<SCRIPT src="login_files/WebResource.axd" type=text/javascript></SCRIPT>

<SCRIPT src="login_files/WebResource(1).axd" type=text/javascript></SCRIPT>

<SCRIPT src="login_files/ScriptResource.axd" type=text/javascript></SCRIPT>

<SCRIPT src="login_files/ScriptResource(1).axd" type=text/javascript></SCRIPT>

<SCRIPT type=text/javascript>
<!--
function WebForm_OnSubmit() {
if (typeof(ValidatorOnSubmit) == "function" && ValidatorOnSubmit() == false) return false;
return true;
}
// -->
</SCRIPT>

<SCRIPT type=text/javascript>
//<![CDATA[
Sys.WebForms.PageRequestManager._initialize('ScriptManager1', document.getElementById('form1'));
Sys.WebForms.PageRequestManager.getInstance()._updateControls(['tUpdatePanel1'], [], [], 90);
//]]>
</SCRIPT>

<DIV id=UpdatePanel1>
<DIV id=div1 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<DIV id=div2 
style="LEFT: 0px; POSITION: absolute; TOP: 0px; BACKGROUND-COLOR: #0066ff"></DIV>
<SCRIPT language=JavaScript> 
var speed=20;
var temp=new Array(); 
var clipright=document.body.clientWidth/2,clipleft=0;
for (i=1;i<=2;i++){ 
	temp[i]=eval("document.all.div"+i+".style");
	temp[i].width=document.body.clientWidth/2;
	temp[i].height=document.body.clientHeight;
	temp[i].left=(i-1)*parseInt(temp[i].width);
} 
function openit(){ 
	clipright-=speed;
	temp[1].clip="rect(0 "+clipright+" auto 0)";
	clipleft+=speed;
	temp[2].clip="rect(0 auto auto "+clipleft+")";
	if (clipright<=0)
		clearInterval(tim);
} 
tim=setInterval("openit()",100);
                </SCRIPT>

<DIV>&nbsp;&nbsp; </DIV>
<DIV>
<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=0>
  <TBODY>
  <TR>
    <TD style="HEIGHT: 105px"><IMG src="login_files/login_1.gif" 
  border=0></TD></TR>
  <TR>
    <TD background=login_files/login_2.jpg height=300>
    <FORM id=form1 name=form1 onSubmit="javascript:return WebForm_OnSubmit();" action="LoginServlet" method="post">
      <TABLE height=300 cellPadding=0 width=900 border=0>
        <TBODY>
        <TR>
          <TD colSpan=2 height=35></TD></TR>
        <TR>
          <TD width=360></TD>
          <TD>
            <TABLE cellSpacing=0 cellPadding=2 border=0>
              <TBODY>
              <%
              GetLoginNoDAO loginNo = new GetLoginNoDAO();
               %>
              <TR>
                <TD style="HEIGHT: 28px">签 到 号：</TD>
                <TD style="HEIGHT: 28px">
                <INPUT id=txtcode style="WIDTH: 130px" name="login_no" value="<%=loginNo.NO() %>" readonly></TD>
                <TD style="HEIGHT: 28px">&nbsp;</TD>
              </TR>
               <TR>
                <TD style="HEIGHT: 28px">机 构 号：</TD>
                <TD style="HEIGHT: 28px">
                <INPUT id=txtcode style="WIDTH: 130px" name="org_id"></TD>
                <TD style="HEIGHT: 28px">&nbsp;</TD>
              </TR>
              <TR>
                <TD style="HEIGHT: 28px">网 点 号：</TD>
                <TD style="HEIGHT: 28px">
                <INPUT id=txtcode style="WIDTH: 130px" name="net_id"></TD>
                <TD style="HEIGHT: 28px">&nbsp;</TD>
              </TR>
              <TR>
                <TD style="HEIGHT: 28px" width=80>登 录 名：</TD>
                <TD style="HEIGHT: 28px" width=150>
                <INPUT id=txtName style="WIDTH: 130px" name="user_name"></TD>
                <TD style="HEIGHT: 28px" width=370>
                <SPAN id=RequiredFieldValidator3 
                  style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入登录名</SPAN></TD>
              </TR>
              <TR>
                <TD style="HEIGHT: 28px">登录密码：</TD>
                <TD style="HEIGHT: 28px">
                <INPUT id=txtPwd style="WIDTH: 130px" type=password name="user_password"></TD>
                <TD style="HEIGHT: 28px"><SPAN id=RequiredFieldValidator4 
                  style="FONT-WEIGHT: bold; VISIBILITY: hidden; COLOR: white">请输入密码</SPAN></TD>
              </TR>
             
              <TR>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD>
                <TD style="HEIGHT: 18px"></TD></TR>
              <TR>
                <TD></TD>
                <TD>
                <INPUT id=btn 
                  style="BORDER-TOP-WIDTH: 0px; BORDER-LEFT-WIDTH: 0px; BORDER-BOTTOM-WIDTH: 0px; BORDER-RIGHT-WIDTH: 0px" 
                  onclick='javascript:WebForm_DoPostBackWithOptions(new WebForm_PostBackOptions("btn", "", true, "", "", false, false))' 
                  type=image src="login_files/login_button.gif" name=btn>
              </TD>
              </TR>
              </TBODY>
              </TABLE>
              </TD>
              </TR>
              </TBODY>
              </TABLE>
</FORM>
              </TD>
              </TR>
  		<TR>
    		<TD><IMG src="login_files/login_3.jpg" border=0></TD>
    	</TR>
    	</TBODY>
    	</TABLE>
    	</DIV>
    	</DIV>
<SCRIPT type=text/javascript>
<!--
var Page_Validators =  new Array(document.getElementById("RequiredFieldValidator3"), document.getElementById("RequiredFieldValidator4"));
// -->
</SCRIPT>

<SCRIPT type=text/javascript>
<!--
var RequiredFieldValidator3 = document.all ? document.all["RequiredFieldValidator3"] : document.getElementById("RequiredFieldValidator3");
RequiredFieldValidator3.controltovalidate = "txtName";
RequiredFieldValidator3.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
RequiredFieldValidator3.initialvalue = "";
var RequiredFieldValidator4 = document.all ? document.all["RequiredFieldValidator4"] : document.getElementById("RequiredFieldValidator4");
RequiredFieldValidator4.controltovalidate = "txtPwd";
RequiredFieldValidator4.evaluationfunction = "RequiredFieldValidatorEvaluateIsValid";
RequiredFieldValidator4.initialvalue = "";
// -->
</SCRIPT>


<SCRIPT type=text/javascript>
<!--
var Page_ValidationActive = false;
if (typeof(ValidatorOnLoad) == "function") {
    ValidatorOnLoad();
}

function ValidatorOnSubmit() {
    if (Page_ValidationActive) {
        return ValidatorCommonOnSubmit();
    }
    else {
        return true;
    }
}
// -->
</SCRIPT>

<SCRIPT type=text/javascript>
<!--
Sys.Application.initialize();
// -->
</SCRIPT>

 
</BODY>
</html>
