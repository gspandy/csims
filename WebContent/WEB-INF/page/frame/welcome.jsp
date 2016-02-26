<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%
	String path = request.getContextPath();
%>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title><bean:message key="CUSTOM_NAME"/>-<bean:message key="PROJECT_NAME"/></title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon"/>
<link rel="icon" href="favicon.ico" type="image/x-icon"/>
<script src="js/manu.js" language="javascript1.2"></script>
<script language="JavaScript"> javascript:window.history.forward(1); </script>

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
<script src="js/home03.js" language="javascript1.2"></script>
<script src="js/manu.js" language="javascript1.2"></script>
<link href="css/home.css" rel="stylesheet" type="text/css" />
</head>
<frameset id="frm1" border="0" frameSpacing="0" rows="60,*,20" frameBorder="no" cols="*">
<frame src="<%=path%>/top.do" name="topFrame" frameborder="0" scrolling="auto" />
    <frameset id="center" name="centerFrame"  border="0" frameSpacing="0"  frameBorder="no" cols="185,8,*">
     <frameset id="left" border="0" frameSpacing="0" frameBorder="no" rows="95,*">
       <frame  name="left1" src="<%=path%>/left1.jsp" scrolling="no" >
        <frame src="<%=path%>/left.do" name="leftFrame" frameborder="0" scrolling="no" />
         </frameset>
<frame  scrolling="no" id="middle" name="middle" src="<%=path%>/middlePage.jsp" scrolling="yes" >
		  <frame  id="middle" name="mainFrame" src="<%=path%>/Log.do?method=listLog4j" scrolling="yes" >
	 </frameset>
	 <frame id="bottomFrame" name="bottomFrame"  src="<%=path%>/foot.jsp" noResize scrolling="no">
</frameset>
</html>

