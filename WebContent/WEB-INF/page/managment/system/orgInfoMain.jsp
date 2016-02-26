<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String orgComInfoTR = (String) request.getAttribute("orgComInfoTR");
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
    		$("input#printOrgInfo").click(function(){
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
								系统数据维护
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								机构维护
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/SystemBaseInfoManagerAction.do?method=readOrg"
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
																		机构信息
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td height="180" valign="top">
															<div id="printTable">
																<table width="100%" border="0" cellpadding="0"
																	cellspacing="0" class="tableline01">
																	<tr>
																		<td align="right" class="tabletext02" width="15%">
																			机构名称
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="orgName" size="100%"
																				readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			机构编号
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="orgNo" size="100%"
																				readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			组织机构代码
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="orgCodeOfZz" size="100%"
																				readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			机构信用代码
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="orgCodeOfXy" size="100%"
																				readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			一级类别名称
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="e" size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			二级类别名称
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="g" size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			直属上级行名称
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="kChoice" size="100%"
																				readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			直属上级行代码
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="k" size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			所属人民银行名称
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="mChoice" size="100%"
																				readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			所属人民银行代码
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="m" size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			机构所在省地区名称
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="o" size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			机构所在地区名称
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="q" size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			机构所在城市名称
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="s" size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			机构邮政编码
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="t" size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			地址
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="z" size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			法人代表/负责人
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="aa" size="100%" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			机构属性
																		</td>
																		<td align="left" colspan="3">
																			<html:text property="isPcb" size="100%"
																				readonly="true"></html:text>
																		</td>
																	</tr>
																	<%=orgComInfoTR%>
																</table>
															</div>
														</td>
													</tr>
													<tr>
														<td colspan="2" align="center">
															<input id="printOrgInfo" name="printUserInfo"
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
