<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sweet" uri="/WEB-INF/tld/sweet-tag.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
	<head>
		<style type="text/css">
</style>
		<title><bean:message key="PROJECT_NAME" /></title>
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
		<link rel="stylesheet"
			href="<%=basePath%>ext-3.2.0/resources/css/ext-all.css"
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
			src="<%=request.getContextPath()%>/js/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/idCard.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/zxPrincipal.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/interface/SelectSystemDataBase.js'></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/engine.js'></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/util.js'></script>
		<script type="text/javascript" src="js/organization.js"></script>
		<link href="css/menu.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/windowopen.js"></script>
		<script type="text/javascript">
		 function toUpdateEnOfficerPage(){
		 	var id = document.getElementById("id").value;
		 	document.forms[0].action="EnforceOfficerManagerAction.do?method=toUpdateEnOfficerPage&id="+id;
			document.forms[0].submit();
		 }
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
								执法人员管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								执法人员信息
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form
			action="/EnforceOfficerManagerAction.do?method=toEnOfficerDetailPage"
			method="post">
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
														<td>
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td class="tiltlegerner">
																		执法人员信息
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td height="180" valign="top">
															<table width="100%" border="0" cellpadding="0"
																cellspacing="0" class="tableline01">
																<tr>
																	<td align="right" class="tabletext02">
																		所属机构
																	</td>
																	<td align="left">
																		<html:text property="orgnm" readonly="true" size="100%"></html:text>
																		<html:hidden property="id"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		所属部门
																	</td>
																	<td class="tdstylebotton">
																		<html:text property="deptnm" readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		执法人员姓名
																	</td>
																	<td>
																		<html:text property="pepname" readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		执法证件号码
																	</td>
																	<td align="left">
																		<html:text property="certno" readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		性别
																	</td>
																	<td align="left">
																		<html:text property="sex" readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		最高学历
																	</td>
																	<td align="left">
																		<html:text property="education" readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		职务
																	</td>
																	<td align="left">
																		<html:text property="prcipal" readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="修 改" class="botton01"
																			onclick="return toUpdateEnOfficerPage();" />
																		<input type="button" value="返 回" class="botton01"
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
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>