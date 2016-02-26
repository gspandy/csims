<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="jguard" uri="/WEB-INF/tld/jguard.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title><bean:message key="CUSTOM_NAME"/>-<bean:message
				key="PROJECT_NAME" /></title>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<link href="<%= request.getContextPath() %>/css/csslogon.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath() %>/css/contentlogon.css" rel="stylesheet" type="text/css" />
<link href="<%= request.getContextPath() %>/css/css.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript"
            src="<%=request.getContextPath()%>/js/validate.js"></script>
<script language="javascript">	
function onclickLogin() {
    var isPass = true;
    var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
    var okGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//dd//drop-yes.gif"></img>';
    if(isNullOrEmpty($("input[name='login']").val())) {
        $('#login_span').html(errorGif);
        isPass = false;
    } else {
        $('#login_span').html(okGif);
    }
    if(isNullOrEmpty($("input[name='password']").val())) {
        $('#password_span').html(errorGif);
        isPass = false;
    } else {
        $('#password_span').html(okGif);
    }
    return isPass;
}

function inputLogin() {
    if(onclickLogin()) {
        document.getElementsByTagName("form")[0].submit();
    }
}

function reset() {
    $(login).val('');
    $(password).val('');
}
</script>
</head>
<body>
<html:form action="/LogonProcess" method="post">
<!--<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#dfdfdf"-->
<table id="body">
  <tr>
    <td align="center" ><table width="1003" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td width="8%"><img src="images/logon/ce.jpg" width="289" height="552" border="0" /></td>
    <td valign="top" align="left">
	<div style="width:717px">
	   <div style="background-image:url(images/logon/logo-bg.jpg); height:157px; width:717px"><img src="images/logon/logo2.jpg" width="300"                 height="59" border="0"  style="margin-top:69px; padding-left:181px; float:left"/></div>
	   <div><img src="images/logon/bank_03.jpg" width="717" height="72" border="0" style="float:left"/></div>
	   <table width="717" border="0" cellspacing="0" cellpadding="0" style="float:left">
    <tr>
    <td style="background-image:url(images/logon/lan-bg.jpg); height:27px" >
	<div style="width:106px; height:27px; float:right; margin-right:88px; background-image:url(images/logon/lan.jpg); padding-top:" class="text">
		<a style="text-decoration: none;color: #FFFFFF;" href="#" >Version 1.1</a>
	</div>	</td>
  </tr>
  <tr>
    <td height="2"><img src="images/logon/tiao.jpg" width="717" height="2" border="0" /></td>
  </tr>
  <tr>
    <td style="background-image:url(images/logon/login-bg.jpg); height:224px">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td colspan="3" align="center" class="text2logon">
	<font style="color:#FFFFFF;">姓名：</font><input id="login" name="login" type="text" style="width:160px; height:18px " value=""/>
	<span id="login_span">&nbsp;&nbsp;</span>
	</td>
  </tr>
  <tr>
    <td height="52" colspan="3" align="center" class="text2logon">
	<font style="color:#FFFFFF;">密码：</font><input id="password" name="password" type="password" style="width:160px; height:18px" />
	<span id="password_span">&nbsp;&nbsp;</span>
	</td>
  </tr>   
  <tr>
    <td>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td align="center">
    <div style="background-image: url(images/logon/menu-b.jpg);float:left; width:65px; margin-left:0px; margin-top:15px">
    <a id="deng" href="#" onclick="inputLogin();" style="text-decoration: none;color: #FFFFFF;">
    	<font style="text-decoration: none;font-size:13px;line-height: 24px;">
    		<bean:message key="logon.jsp.button.login"/>
    	</font>
    </a>
    </div>
	<div style="background-image: url(images/logon/menu-y.jpg);float:left; width:65px; margin-left:10px; margin-top:15px">
	<a href="#" style="text-decoration: none;color: #FFFFFF;" onclick="reset()">
		<font style="text-decoration: none;font-size:13px;line-height: 24px;">
			<bean:message key="logon.jsp.button.Reset"/>
		</font>
	</a>
	</div></td>
    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
</table></td>
  </tr>
  <tr>
    <td height="2"><img src="images/logon/tiao-2.jpg" width="717" height="2" border="0" /></td>
  </tr>
  <tr>
    <td align="right" style="background-image:url(images/logon/di-bg.jpg)" class="text3">
     版权所有：<bean:message key="CUSTOM_NAME"/>
    &nbsp;&nbsp;
    <a href="http://<bean:message key="exam.host"/>:<bean:message key="exam.port"/>" target="_blank" class="left" style="text-decoration: none;color: #FFFFFF;">进入考试</a>
    </td>
  </tr>
</table>
    </div>
	</td>
  </tr>
</table>
	</td>
  </tr>
</table>
</html:form>
</body>
</html>
