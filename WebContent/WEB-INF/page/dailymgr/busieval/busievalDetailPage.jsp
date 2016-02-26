<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String tableString = (String) request.getAttribute("tableString");
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
			function changTR1(){
			  	var evalCondition = document.getElementById("evalCondition").value;
				if(evalCondition=="基本情况"){
					document.getElementById('TR1').style.display='block';
				}else{
					document.getElementById('TR1').style.display='none';
				}
				if(evalCondition=="存在问题"){
					document.getElementById('TR2').style.display='block';
				}else{
					document.getElementById('TR2').style.display='none';
				}
			}
	
			function changTR2(){
				var evalCondition = document.getElementById("evalCondition").value;
				if(evalCondition=="" || evalCondition.length==0){
					alert("请选择平价情况!");
					return false;
				}
			  	var evalInfo = document.getElementById("evalInfo").value;
				if(evalCondition=="存在问题"){
					if(evalInfo=="综合管理" || evalInfo=="系统建设" || evalInfo=="征信管理" || evalInfo=="征信宣传与维权" || evalInfo=="信用体系建设"){
						document.getElementById('TR3').style.display='block';
					}else{
						document.getElementById('TR3').style.display='none';
					}
				}else{
					document.getElementById('TR3').style.display='none';
				}
			}
			function showAddEvalInfo(){
				document.getElementById("showAddEvalInfo").style.display = "inline";
			}
			function hiddenEvalInfo(){
				document.getElementById("showAddEvalInfo").style.display = "none";
			}
			function addEvalInfo(){
				if(confirm("操作将添加业务评价信息,确认?")){
					document.forms[0].action = "DailyMgrAction.do?method=addEvalInfo";
					document.forms[0].submit();
				}
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
								日常管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								业务评价信息详细
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/DailyMgrAction.do?method=toBusievalDetailPage" method="post">
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
																		业务评价信息详细
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
																		评价机构
																	</td>
																	<td colspan="9">
																	<html:text property="evalorgnm"  readonly="true" size="100%"></html:text>
																		<html:hidden property="evalorgno"></html:hidden>
																		<html:hidden property="busiEvalId"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		被评价机构
																	</td>
																	<td colspan="9">
																	<html:text property="evaledorgnm"  readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		被评价机构代码
																	</td>
																	<td colspan="9">
																	<html:text property="evaledorgno"  readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		评价年份
																	</td>
																	<td colspan="9">
																	<html:text property="evalYear"  readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		评价所属期
																	</td>
																	<td colspan="9">
																	<html:text property="evalDuring"  readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<%=tableString%>
																<tr>
																	<td colspan="10" align="center">
																		<input type="button" value="添加" class="botton01"
																			onclick="showAddEvalInfo();" />
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
										
										<tr>
											<td class="tablestyle">
												<div id="showAddEvalInfo" style="display: none;">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0">
														<tr>
															<td>
																<table width="100%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tr>
																		<td class="tiltlegerner">
																			添加业务评价信息
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
																		<font color='#FF0000'>*</font>评价情况
																	</td>
																	<td align="left">
																		<html:select property="evalCondition" onchange="changTR1();">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="基本情况">基本情况</html:option>
																			<html:option value="存在问题">存在问题</html:option>
																			<html:option value="加分项目">加分项目</html:option>
																		</html:select>
																	</td>
																</tr>
																
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>评价信息
																	</td>
																	<td align="left">
																		<html:select property="evalInfo" onchange="changTR2();">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="综合管理">综合管理</html:option>
																			<html:option value="系统建设">系统建设</html:option>
																			<html:option value="征信管理">征信管理</html:option>
																			<html:option value="征信宣传与维权">征信宣传与维权</html:option>
																			<html:option value="信用体系建设">信用体系建设</html:option>
																			<html:option value="加分项目">加分项目</html:option>
																		</html:select>
																	</td>
																</tr>
																<tr id='TR1' style="display: none;">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>基本分
																	</td>
																	<td>
																		<html:text property="baseScore" size="20%"
																			styleClass="text111"></html:text>
																	</td>
																</tr>
																<tr id='TR2' style="display: none;">
																	<td align="right" class="tabletext02" >
																		<font color='#FF0000'>*</font>扣分
																	</td>
																	<td>
																		<html:text property="deScore" size="20%"
																			styleClass="text111"></html:text>
																	</td>
																</tr>
																<tr id='TR3' style="display: none;">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>评价细则
																	</td>
																	<td>
																		<html:textarea property="evalRule" cols="140%"
																			rows="5"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>评价内容
																	</td>
																	<td>
																		<html:textarea property="evalContent" cols="140%"
																			rows="5"></html:textarea>
																	</td>
																</tr>
																	<tr>
																		<td colspan="2" align="center">
																			<input type="button" value="保存" class="botton01"
																				onclick="addEvalInfo();" />
																			<input type="button" value="取消" class="botton01"
																				onclick="hiddenEvalInfo();" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</div>
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