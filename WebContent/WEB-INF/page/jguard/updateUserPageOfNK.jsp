<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="java.util.Set,java.util.List,java.util.Iterator,net.sf.jguard.core.principals.RolePrincipal"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String flag = (String) request.getAttribute("flag");
	String status = (String) request.getAttribute("status");
	String zxStr = (String) request.getAttribute("zxStr");
	String photoPath = (String) request.getAttribute("photoPath");
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
			function disabledZxUser(zxUserId){
				if(confirm("操作将禁用征信用户,确认?")){
					document.forms[0].action="User.do?method=disabledZxUser&zxUserId="+zxUserId;;
					document.forms[0].submit();
				}
			}
			
			function enabledZxUser(zxUserId){
				if(confirm("操作将启用征信用户,确认?")){
					document.forms[0].action="User.do?method=enabledZxUser&zxUserId="+zxUserId;;
					document.forms[0].submit();
				}
			}
			
			function cancelZxUser(zxUserId){
				if(confirm("操作将删除征信用户,确认?")){
					document.forms[0].action="User.do?method=cancelZxUser&zxUserId="+zxUserId;;
					document.forms[0].submit();
				}
			}
		
			function disableUser(){
				if(confirm("操作将申请停用监管系统用户,确认?")){
					document.forms[0].action="User.do?method=disableUserOfNK";
					document.forms[0].submit();
				}
			}
			
			function enableUser(){
				if(confirm("操作将申请启用监管系统用户,确认?")){
					document.forms[0].action="User.do?method=enableUserOfNK";
					document.forms[0].submit();
				}
			}
			
			
		function myLoad(status){
			if(status=="1"){
				document.getElementById('TD1').style.display='block';
				document.getElementById('TD2').style.display='none';
			}else{
				document.getElementById('TD1').style.display='none';
				document.getElementById('TD2').style.display='block';
			}
		}
	</script>
	<body onload="myLoad('<%=status%>')">
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
								修改用户
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/User.do?method=updateUserPageOfNK" method="post"
			enctype="multipart/form-data">
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
															<table width="100%" border="0" cellpadding="0"
																cellspacing="0" class="tableline01">
																<tr>
																	<td align="right" class="tabletext02" width="15%">
																		监管系统用户姓名
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="name" size="100%" readonly="true"></html:text>
																		<html:hidden property="id" styleId="id" />
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		监管系统用户类型
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="userType" size="100%"
																			readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		证件类型
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="cardType" size="100%"
																			readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		证件号码
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="cardId" size="100%"
																			readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		最高学历
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="education" size="100%"
																			readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		联系电话
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="phone" size="100%"
																			readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		用户使用者身份
																	</td>
																	<td align="left">
																		<html:text property="userCard" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		监管系统用户所在机构名称
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="organizationChoice" size="100%"
																			readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		监管系统用户所在机构代码
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="organization" size="100%"
																			readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		组织机构代码
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="orgCodeOfZz" size="100%"
																			readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		机构信用代码
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="orgCodeOfXy" size="100%"
																			readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td class="tabletext02">
																		监管系统用户所在部门
																	</td>
																	<td class="tdstylebotton" colspan="4">
																		<html:text property="dept" size="100%" readonly="true"></html:text>
																	</td>
																</tr>
																<%=zxStr%>
																<tr>
																	<td align="right" class="tabletext02">
																		监管系统用户角色
																	</td>
																	<td class="tdstylebotton" colspan="4">
																		<div id="slt">
																			<table>
																				<%
																					StringBuffer sb = new StringBuffer("");
																						List<?> allPrincipals = (List<?>) request
																								.getAttribute("allPrincipals");
																						List<?> principals = (List<?>) request
																								.getAttribute("principals");
																						for (Iterator<?> it = allPrincipals.iterator(); it.hasNext();) {
																							RolePrincipal principal = (RolePrincipal) it.next();
																							if (principal.getLocalName().trim().equalsIgnoreCase(
																									"admin")
																									|| principal.getLocalName().trim()
																											.equalsIgnoreCase("systemadmin")
																									|| principal.getLocalName().trim()
																											.equalsIgnoreCase("guest")) {
																								continue;
																							}
																							sb.append("<tr>");
																							sb.append("<td align='left' width='3%'>");
																							sb
																									.append("<input id='selectPrincipal' name='selectPrincipal' type='checkbox' disabled value='");
																							sb.append(principal.getName());
																							sb.append("' ");
																							if (principals != null && principals.size() > 0) {
																								for (int i = 0; i < principals.size(); i++) {
																									if (principals.get(i).equals(principal.getName())) {
																										sb.append(" checked='checked' ");
																										break;
																									}
																								}
																							}
																							sb.append("/></td>");
																							sb.append("<td align='left' width='80%'>");
																							sb.append(principal.getLocalName());
																							sb.append("</td>");
																							sb.append("</tr>");
																						}
																						out.write(sb.toString());
																				%>
																			</table>
																		</div>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		照片
																	</td>
																	<td align="left" colspan="4">
																		<%
																			StringBuffer sb1 = new StringBuffer("");
																			if(!"".equals(photoPath) && photoPath !=null){
																				sb1.append("<img src='\\csims\\")
																				   .append(photoPath)
																				   .append("'  width='160px' height='120px' /><br>");
																			}else{
																				sb1.append("未上传照片");
																			}
																			out.write(sb1.toString());
																		%>
																	</td>
																</tr>
																<tr>
																	<td colspan="5" id="TD1" align="center">
																		<input type="button" value="禁用" disabled
																			class="botton01" onclick="return disableUser();" />
																		<input type="button" value="启用" class="botton01"
																			onclick="return enableUser();" />
																		<input type="button" value="返回" class="botton01"
																			onclick="javascript:history.back()" />
																	</td>
																	<td colspan="5" id="TD2" align="center">
																		<input type="button" value="禁用" class="botton01"
																			onclick="return disableUser();" />
																		<input type="button" value="启用" disabled
																			class="botton01" onclick="return enableUser();" />
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
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>