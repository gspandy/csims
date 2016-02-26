<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" " http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="jguard" uri="/WEB-INF/tld/jguard.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
<head>
<title></title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
			href="<%=basePath%>ext-3.2.0/resources/css/ext-all.css"
			type="text/css"></link>
<script src="js/manu.js" language="javascript1.2"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
<script type="text/javascript"
	src="<%=basePath%>ext-3.2.0/build/locale/ext-lang-zh_CN.js"></script>
<script type="text/javascript">
Ext.Ajax.request({
	url : '<%= path %>/AdminEnforceManagerAction.do?method=getLoginInfo',
	params : {
		id : ''
	},
	method : 'POST',
	callback : function(options, success, response) {
		if(success) {
			// alert(response.responseText);
			var responseJson = Ext.util.JSON.decode(response.responseText);
			// alert(responseJson);
			$('#logininfo').html('登录名：' + responseJson.login + '&nbsp;&nbsp;' + '姓名：' + responseJson.nickname + '&nbsp;&nbsp;' +'所属机构：' + responseJson.orgname + '&nbsp;&nbsp;&nbsp;&nbsp;角色：' + responseJson.principals);
		} else {
			//do anything
		}
	}
});
</script>
</head>
<body>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		id="bottom"
		style="background-image:url(images/bottom.jpg); background-repeat:repeat-x">
		<tr>
			<td width="70%" height="20" align="left" class="whitefonts">
			<!-- 
				&nbsp;&nbsp;登录名：<jguard:privCredential default="" id="login" />
                &nbsp;&nbsp;姓名：<jguard:pubCredential default="" id="nickname" />
             -->
                &nbsp;&nbsp;<span id='logininfo'></span>
			</td>
			<td width="30%" height="20" align="right" class="whitefonts">
				Copyright &copy; 2014 <bean:message key="CUSTOM_NAME" />. All Rights Reserved
			</td>
		</tr>
	</table>
</body>
</html>