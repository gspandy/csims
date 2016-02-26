<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String methodname = request.getAttribute("methodname").toString();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link href="<%=request.getContextPath()%>/css/css.css"
			rel="stylesheet" type="text/css" />
		<style type="text/css">
<!--
body {
	background-color: #F0F8FB;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

A {
	COLOR: #0033FF; font face=Verdana;
	font-size: 9pt
}
-->
</style>

	</head>

	<body>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td height="35" class="nawzjj">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="ndwz">
								&nbsp;
							</td>
							<td class="ndwzzml">
								<bean:message key="PROJECT_NAME" />
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								日常管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								培训管理操作信息
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td width="100%" class="tiltlegerner">
					培训管理操作信息
				</td>
			</tr>
			<tr>
				<td class="tabletext10" align="center">
					<%
						if (request.getAttribute("message") != null) {
							out.println(request.getAttribute("message").toString());
						}
					%>
				</td>
			</tr>
			<tr>
				<td class="tabletext10" align="center">
					<input class="botton01" type="button" value="确 定"
						onclick="javascript:window.location.href = '<%=path%>/DailyMgrAction.do?method=<%=methodname%>';" />
				</td>
			</tr>
		</table>


	</body>
</html>
