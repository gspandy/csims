<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
</style>
		<title><bean:message key="PROJECT_NAME" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="template report">
		<link href="css/css.css" rel="stylesheet" type="text/css" />
		<link href="css/manus.css" rel="stylesheet" type="text/css" />
		<link href="css/home.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>css/css2.css"
			type="text/css"></link>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/home.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/home03.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/manu.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/jquery-1.11.0.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/jquery.PrintArea.js"></script>
		<script type="text/javascript" src="js/windowopen.js"></script>
		<script type="text/javascript">
		$(document).ready(function(){
    		$("input#printUserReportInfo").click(function(){
        		$("div#printTable").printArea(); 
    		});
		});
 	</script>
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
								用户管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								用户备案信息
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/User.do?method=readUserReportInfoOfP" method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td class="tablestyle">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td height="180" valign="top">
															<div id="printTable">
																<table width="100%" border="0" cellpadding="0"
																	cellspacing="0" class="tableline01">
																	<tr>
																		<td colspan="2" align="center">
																			金融信用信息基础数据库用户备案表（个人）
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02" width="25%">
																			用户姓名
																		</td>
																		<td align="left" >
																			<html:text property="name" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02" width="25%">
																			联系电话
																		</td>
																		<td align="left" >
																			<html:text property="phone"  size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02" width="25%">
																			机构名称
																		</td>
																		<td align="left" >
																			<html:text property="orgName"  size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02" width="25%">
																			所在部门
																		</td>
																		<td align="left" >
																			<html:text property="deptName"  size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02" width="25%">
																			用户名
																		</td>
																		<td align="left" >
																			<html:text property="userZxName"  size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02" width="25%">
																			报备时间
																		</td>
																		<td align="left" >
																			<html:text property="reportDate"  size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02" width="25%">
																			用户名所属机构名称
																		</td>
																		<td align="left" >
																			<html:text property="userZxOrgName"  size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02" width="25%">
																			征信系统名称
																		</td>
																		<td align="left" >
																			<html:text property="zxXtName"  size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02" width="25%">
																			征信系统用户类型
																		</td>
																		<td align="left" >
																			<html:text property="zxXtType"  size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02" width="25%">
																			报备事项
																		</td>
																		<td align="left">
																			<html:text property="reportType" readonly="true"></html:text>
																		</td>
																	</tr>
																	
																	<tr>
																		<td align="right" class="tabletext02" width="25%">
																			地址
																		</td>
																		<td align="left" >
																			<html:text property="adr"  size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02" width="25%">
																			报备人
																		</td>
																		<td align="left" >
																			<html:text property="reporter" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02" width="25%">
																			审批人
																		</td>
																		<td align="left" >
																			<html:text property="applyName" readonly="true"></html:text>
																		</td>
																	</tr>
																</table>
															</div>
														</td>
													</tr>
													<tr>
														<td align="center">
															<input id="printUserReportInfo" name="printUserReportInfo"
																type="button" value="打 印" class="botton01" />
															<input type="button" value="返回" class="botton01"
																onclick="javascript:history.back()" />
														</td>
													</tr>
												</table>
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
	</body>
</html>