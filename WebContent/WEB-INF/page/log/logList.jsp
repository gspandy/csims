<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sweet" uri="/WEB-INF/tld/sweet-tag.tld"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
	<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F1F8FC;
}
</style>
	<title><bean:message key="PROJECT_NAME" /></title>
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<link href="css/manus.css" rel="stylesheet" type="text/css" />
	<link href="css/home.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/home.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/home03.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/manu.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/WdatePicker.js" defer="defer"></script>
	<script type="text/javascript">
	function resets(){
		document.getElementById("message").value="";
		document.getElementById("begin").value="";
		document.getElementById("end").value="";
	}
	
	function search(){
		document.forms[0].action="Log.do?method=listLog4j";		 		 	
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
								日志列表
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="Log.do?method=listLog4j" method="post">
			<html:hidden name="logForm" property="pageCount" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="tablestyle">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td align="left">
															<table width="100%" border="0" align="left"
																cellpadding="0" cellspacing="0">
																<tr>
																	<td>
																		内容
																		<html:text property="message" maxlength="30" size="30"
																			styleClass="text111"
																			onkeydown="if(event.keyCode==13){search();}" />
																	</td>

																	<td>
																		起始时间
																		<html:text property="begin" maxlength="20" size="15"
																			styleClass="text111"
																			onkeydown="if(event.keyCode==13){search();}"
																			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
																	</td>
																	<td>
																		截至时间
																		<html:text property="end" maxlength="20" size="15"
																			styleClass="text111"
																			onkeydown="if(event.keyCode==13){search();}"
																			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
																	</td>
																	<td>
																		<html:button property="id" onclick="search();"
																			value="查 询" styleClass="botton01" />
																	</td>
																	<td>
																		<html:button property="id" onclick="resets();"
																			value="重  置" styleClass="botton01" />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0" class="tableline01">
													<tr class="tabletext01">
														<td width="5%">
															序号
														</td>
														<td width="75%">
															内容
														</td>
														<td width="20%">
															时间
														</td>
													</tr>
													<logic:empty name="logs">
														<tr>
															<td align="center" colspan="3">
																暂无日志
															</td>
														</tr>
													</logic:empty>
													<logic:notEmpty name="logs">
														<logic:iterate id="item" name="logs" indexId="indexnum">
															<tr>
																<td align="center">
																	<c:out value="${(indexnum + 1) + (pageNo - 1) * 10}"></c:out>
																</td>
																<td align="center">
																	<bean:write name="item" property="message" />
																</td>
																<td align="center">
																	<bean:write name="item" property="createdate"
																		format="yyyy-MM-dd HH:mm:ss" />
																</td>
															</tr>
														</logic:iterate>
													</logic:notEmpty>
												</table>
											</td>
										</tr>
										<tr>
											<td class='left' align="right">
												<sweet:page formName="logForm" hrefClass="left" />
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
