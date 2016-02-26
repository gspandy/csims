<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sweet" uri="/WEB-INF/tld/sweet-tag.tld"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String zxUserCount = (String) request.getAttribute("zxUserCount");
%>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F1F8FC;
}
</style>
<title><bean:message key="PROJECT_NAME" />
</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/manus.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/css2.css" type="text/css"></link>
<link rel="stylesheet"
	href="<%=basePath%>ext-3.2.0/resources/css/ext-all.css" type="text/css"></link>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/home.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/home03.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/manu.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="js/windowopen.js"></script>
<script type="text/javascript" src="js/organization.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript"
	src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
<script type="text/javascript"
	src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
<script type="text/javascript"
	src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/WdatePicker.js" defer="defer"></script>
<script type="text/javascript">
	function userInfoQueryPage(){
		document.forms[0].action="User.do?method=userInfoQueryPage";
		document.forms[0].submit();			
	}
	
	function downloadExcel(){
		document.forms[0].action="User.do?method=userInfoQueryResultDownloadExcel";
		document.forms[0].submit();			
	}
</script>
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
						用户查询
				</tr>
			</table>
		</td>
	</tr>
</table>
<html:form action="/User.do?method=userInfoQuery">
	<html:hidden property="pageCount" />
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="tablestyle">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="tableline01">
											<tr class="tabletext01">
												<td width="5%" align="center">
													序号
													<html:hidden property="area" styleId="area" />
													<html:hidden property="zxUserType" styleId="zxUserType" />
													<html:hidden property="organization" styleId="organization" />
													<html:hidden property="userRole" styleId="userRole" />
												</td>
												<td width="15%" align="center">
													征信系统用户名
												</td>
												<td width="35%" align="center">
													所在机构
												</td>
												<td width="20%" align="center">
													征信系统用户类型
												</td>
												<td width="25%" align="center">
													监管系统身份识别码
												</td>
											</tr>
											<logic:empty name="userList">
												<tr>
													<td colspan="5" align="center">
														无查询数据......
													</td>
												</tr>
											</logic:empty>
											<logic:iterate id="item" name="userList" indexId="indexnum">
												<tr>
													<td align="center">
														<c:out value="${(indexnum + 1) + (pageNo - 1) * 10}"></c:out>
													</td>
													<td align="center">
														<bean:write name="item" property="zxUserName" />
													</td>
													<td align="center">
														<bean:write name="item" property="orgName" />
													</td>
													<td align="center">
														<bean:write name="item" property="zxUserType" />
													</td>
													<td align="center">
														<bean:write name="item" property="loginId" />
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="5" class='left' align="right">
										<input type="button" value="返  回" class="botton01" onclick="return userInfoQueryPage();" />
										<input type="button" value="导 出" class="botton01" onclick="return downloadExcel();" />
										<sweet:page formName="generalForm" hrefClass="left" />&nbsp;&nbsp;&nbsp;合 计<%=zxUserCount%>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</html:form>
