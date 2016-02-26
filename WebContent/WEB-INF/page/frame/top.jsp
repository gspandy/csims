<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="jguard" uri="/WEB-INF/tld/jguard.tld"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns=" http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text ml; charset=gb2312" />
<title><bean:message key="PROJECT_NAME"/></title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script src="js/manu.js" language="javascript1.2"></script>
<style type="text/css">
<!--
body {
	background-color: #F0F8FB;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="css/home.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
function linkMainPage(url){
    parent.parent.parent.main.location.href=url;
}

function goHome(){
    window.parent.location="<%=request.getContextPath()%>/indexAction.do?method=toForIndex";
}
</script>
</head>

<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="tiltlebg" width="10s"></td>
        <td width="60" class="tiltlebg"><img src="images/bankofchina_LOGO.jpg" width="138" height="60" /></td>
        <td class="tiltlebg"><img src="images/name.jpg" width="303" height="60" /></td>
        <td width="264" align="right" valign="top" background="images/zhenxin17.gif">
		  <table width="90%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td width="7"><img src="images/zhenxin03.gif" width="7" height="22" /></td>
            <td>
				<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tiltlebg01">
             <tr>
              	<td><img src="images/home22.jpg" width="15" height="22" /></td>
                <td><a href="<%=request.getContextPath()%>/messageManage.do?method=forwordToshowMessage"
							target="mainFrame"  class="tiltlebg01text">首页</a></td>
                <td><img src="images/zhenxin06.gif" width="15" height="22" /></td>
                <td><a href="<%=request.getContextPath()%>/resetPassword.do?method=toReset"						
							target="mainFrame"  class="tiltlebg01text">密码修改</a></td>
                <td><img src="images/zhenxin07.gif" width="15" height="22" /></td>
                <td><a href="<%=request.getContextPath()%>/fqa.jsp" target="_blank" class="tiltlebg01text">帮助</a></td>
                <td><img src="images/zhenxin08.jpg" width="15" height="22" /></td>
                <td><a  href="<%=request.getContextPath()%>/Logoff.do" target="_parent" class="tiltlebg01text">退出</a></td>
              </tr>
            </table></td>
            <td width="7"><img src="images/zhenxin05.gif" width="7" height="22" /></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>