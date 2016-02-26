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
			function personalInfoManager(){
				var photopath = document.getElementById('photopath').value;
				if(photopath==null || photopath.length==0){
		 			alert("请选择照片!");
					return false;
				}
				if(confirm("操作将上传用户照片,确认?")){
					document.forms[0].action="User.do?method=personalInfoManager";
					document.forms[0].submit();
				}
			}
			
		function myLoad(flag,status){
			if(flag=="0"){
				document.getElementById('TR1').style.display='block';
				document.getElementById('TR2').style.display='block';
				document.getElementById('TR3').style.display='none';
				document.getElementById('TR4').style.display='none';
				document.getElementById('TR5').style.display='none';
				document.getElementById('TR6').style.display='none';
			}else{
				document.getElementById('TR1').style.display='none';
				document.getElementById('TR2').style.display='none';
				document.getElementById('TR3').style.display='block';
				document.getElementById('TR4').style.display='block';
				document.getElementById('TR5').style.display='block';
				document.getElementById('TR6').style.display='block';
			}
			document.getElementById('zxName1-1').style.display='none';
			document.getElementById('zxName2-1').style.display='none';
			document.getElementById('zxName3-1').style.display='none';
			document.getElementById('zxName4-1').style.display='none';
			document.getElementById('zxName5-1').style.display='none';
			document.getElementById('zxName6-1').style.display='none';
			document.getElementById('zxName7-1').style.display='none';
			document.getElementById('zxName8-1').style.display='none';
			document.getElementById('zxName9-1').style.display='none';
			document.getElementById('zxName1-2').style.display='none';
			document.getElementById('zxName2-2').style.display='none';
			document.getElementById('zxName3-2').style.display='none';
			document.getElementById('zxName4-2').style.display='none';
			document.getElementById('zxName5-2').style.display='none';
			document.getElementById('zxName6-2').style.display='none';
			document.getElementById('zxName7-2').style.display='none';
			document.getElementById('zxName8-2').style.display='none';
			document.getElementById('zxName9-2').style.display='none';
		}
	</script>
	<body onload="myLoad('<%=flag %>','<%=status %>')">
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
								个人信息
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/User.do?method=personalInfoManagerPage" method="post"
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
																		<html:text property="name" size="100%"
																		 	readonly="true"></html:text>
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
																<tr id="TR1">
																	<td align="right" class="tabletext02">
																		员工号
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="userCode" size="100%"
																		 	readonly="true"></html:text>
																	</td>
																</tr>
																<tr id="TR2">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>职务
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="post" size="100%"
																		 	readonly="true"></html:text>
																	</td>
																</tr>
																<tr id="TR3">
																	<td align="right" class="tabletext02">
																		证件类型
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="cardType" size="100%"
																		 	readonly="true"></html:text>
																	</td>
																</tr>
																<tr id="TR4">
																	<td align="right" class="tabletext02">
																		证件号码
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="cardId" size="100%"
																		 	readonly="true"></html:text>
																	</td>
																</tr>
																<tr id="TR5">
																	<td align="right" class="tabletext02">
																		最高学历
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="education" size="100%"
																		 	readonly="true"></html:text>
																	</td>
																</tr>
																<tr id="TR6">
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
																		<html:text property="userCard" size="100%"
																		 	readonly="true"></html:text>
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
																		<html:text property="dept" size="100%"
																		 	readonly="true"></html:text>
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
																					if (principals.get(i).equals(
																							principal.getName())) {
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
																		征信系统用户类型
																	</td>
																	<td class="tdstylebotton" colspan="4">
																		<table>
																			<tr>
																				<td align='left' width='10%' height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName1" disabled 
																						onclick='return changZXName(this);' />
																				企业征信管理员用户
																				</td>
																				<td id="zxName1-1" width='20%'>
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName1"
																						styleClass="text11123232323232"></html:text>
																				</td>
																				<td id="zxName1-2">
																					<font color='#FF0000'>*</font>征信系统用户所在机构
																					<html:text property="zxOrg1Choice"
																						styleClass="text111555555" readonly="true"></html:text>
																					<input type="button" value="选择" class="botton01"
																						onclick="getOrgTreeOfZx1('zxOrg1')" />
																					<html:hidden property="zxOrg1Choice"></html:hidden>
																					<html:hidden property="zxOrg1"></html:hidden>
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName2" disabled 
																						onclick='return changZXName(this);' />
																				&nbsp;企业征信查询用户
																				</td>
																				<td id="zxName2-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName2"
																						styleClass="text11123232323232"></html:text>
																				</td>
																				<td id="zxName2-2">
																					<font color='#FF0000'>*</font>征信系统用户所在机构
																					<html:text property="zxOrg2Choice"
																						styleClass="text111555555" readonly="true"></html:text>
																					<input type="button" value="选择" class="botton01"
																						onclick="getOrgTreeOfZx2('zxOrg2')" />
																					<html:hidden property="zxOrg2Choice"></html:hidden>
																					<html:hidden property="zxOrg2"></html:hidden>
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName3" disabled 
																						onclick='return changZXName(this);' />
																					&nbsp;企业征信数据报送用户
																				</td>
																				<td id="zxName3-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName3"
																						styleClass="text11123232323232"></html:text>
																				</td>
																				<td id="zxName3-2">
																					<font color='#FF0000'>*</font>征信系统用户所在机构
																					<html:text property="zxOrg3Choice"
																						styleClass="text111555555" readonly="true"></html:text>
																					<input type="button" value="选择" class="botton01"
																						onclick="getOrgTreeOfZx3('zxOrg3')" />
																					<html:hidden property="zxOrg3Choice"></html:hidden>
																					<html:hidden property="zxOrg3"></html:hidden>
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName4" disabled
																						onclick='return changZXName(this);' />
																					&nbsp;企业征信异议处理用户
																				</td>
																				<td id="zxName4-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName4"
																						styleClass="text11123232323232"></html:text>
																				</td>
																				<td id="zxName4-2">
																					<font color='#FF0000'>*</font>征信系统用户所在机构
																					<html:text property="zxOrg4Choice"
																						styleClass="text111555555" readonly="true"></html:text>
																					<input type="button" value="选择" class="botton01"
																						onclick="getOrgTreeOfZx4('zxOrg4')" />
																					<html:hidden property="zxOrg4Choice"></html:hidden>
																					<html:hidden property="zxOrg4"></html:hidden>
																				</td>
																			</tr>
																			<tr>
																				<td align='left' width='10%'  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName5" disabled
																						onclick='return changZXName(this);' />
																					&nbsp;个人征信管理员用户
																				</td>
																				<td id="zxName5-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName5"
																						styleClass="text11123232323232"></html:text>
																				</td>
																				<td id="zxName5-2">
																					<font color='#FF0000'>*</font>征信系统用户所在机构
																					<html:text property="zxOrg5Choice"
																						styleClass="text111555555" readonly="true"></html:text>
																					<input type="button" value="选择" class="botton01"
																						onclick="getOrgTreeOfZx5('zxOrg5')" />
																					<html:hidden property="zxOrg5Choice"></html:hidden>
																					<html:hidden property="zxOrg5"></html:hidden>
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName6" disabled 
																						onclick='return changZXName(this);' />
																					&nbsp;个人征信查询用户
																				</td>
																				<td id="zxName6-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName6"
																						styleClass="text11123232323232"></html:text>
																				</td>
																				<td id="zxName6-2">
																					<font color='#FF0000'>*</font>征信系统用户所在机构
																					<html:text property="zxOrg6Choice"
																						styleClass="text111555555" readonly="true"></html:text>
																					<input type="button" value="选择" class="botton01"
																						onclick="getOrgTreeOfZx6('zxOrg6')" />
																					<html:hidden property="zxOrg6Choice"></html:hidden>
																					<html:hidden property="zxOrg6"></html:hidden>
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName7" disabled 
																						onclick='return changZXName(this);' />
																					&nbsp;个人征信数据报送用户
																				</td>
																				<td id="zxName7-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName7"
																						styleClass="text11123232323232"></html:text>
																				</td>
																				<td id="zxName7-2">
																					<font color='#FF0000'>*</font>征信系统用户所在机构
																					<html:text property="zxOrg7Choice"
																						styleClass="text111555555" readonly="true"></html:text>
																					<input type="button" value="选择" class="botton01"
																						onclick="getOrgTreeOfZx7('zxOrg7')" />
																					<html:hidden property="zxOrg7Choice"></html:hidden>
																					<html:hidden property="zxOrg7"></html:hidden>
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName8" disabled 
																						onclick='return changZXName(this);' />
																					&nbsp;个人征信异议处理用户
																				</td>
																				<td id="zxName8-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName8"
																						styleClass="text11123232323232"></html:text>
																				</td>
																				<td id="zxName8-2">
																					<font color='#FF0000'>*</font>征信系统用户所在机构
																					<html:text property="zxOrg8Choice"
																						styleClass="text111555555" readonly="true"></html:text>
																					<input type="button" value="选择" class="botton01"
																						onclick="getOrgTreeOfZx8('zxOrg8')" />
																					<html:hidden property="zxOrg8Choice"></html:hidden>
																					<html:hidden property="zxOrg8"></html:hidden>
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName9" disabled 
																						onclick='return changZXName(this);' />
																					&nbsp;其他
																				</td>
																				<td id="zxName9-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName9"
																						styleClass="text11123232323232"></html:text>
																				</td>
																				<td id="zxName9-2">
																					<font color='#FF0000'>*</font>征信系统用户所在机构
																					<html:text property="zxOrg9Choice"
																						styleClass="text111555555" readonly="true"></html:text>
																					<input type="button" value="选择" class="botton01"
																						onclick="getOrgTreeOfZx9('zxOrg9')" />
																					<html:hidden property="zxOrg9Choice"></html:hidden>
																					<html:hidden property="zxOrg9"></html:hidden>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		上传照片
																	</td>
																	<td align="left" colspan="4">
																		<%
																			StringBuffer sb1 = new StringBuffer("");
																			if(!"".equals(photoPath) && photoPath !=null){
																				sb1.append("<img src='\\csims\\")
																				   .append(photoPath)
																				   .append("'  width='160px' height='120px' /><br>");
																			}
																			out.write(sb1.toString());
																		%>
																		<input type="file" name="photopath" id="photo"
																			class="filepath01" size="70%" />
																		<html:hidden property="photo" styleId="photo" />
																	</td>
																</tr>
																<tr>
																	<td colspan="5" align="center">
																		<input type="button" value="完成"  
																			class="botton01" onclick="return personalInfoManager();" />
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