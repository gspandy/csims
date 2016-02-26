<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix="beanel" uri="/WEB-INF/tld/struts-bean-el.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%
		String path = request.getContextPath();
	%>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title></title>
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F1F8FC;
}
-->
</style>
		<style type="text/css">
a {
	text-decoration: none;
	border: 0;
	color: black;
	text-indent: 0;
}
</style>
		<link href="css/css.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript">
 function lookMessage(messageId){	
 document.forms[0].action="./messageManage.do?method=lookMessage&messageId="+messageId;
document.forms[0].submit();
}
function querySystem(){
document.forms[0].action="./messageManage.do?method=messageQuery&type=SYSTEM_NOTICE";
document.forms[0].submit();
}
function queryList(){
document.forms[0].action="./messageManage.do?method=messageQuery&type=TASK_ASSIGN";
document.forms[0].submit();
}
</script>
	</head>

	<body>
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="35" class="nawzjj">
					<table width="100%" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td class="ndwz">
								&nbsp;
							</td>
							<td class="ndwzzml">
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								<bean:message key="PROJECT_NAME" />
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								后台首页
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/messageManage.do" method="post">
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td width="71%" valign="top">
									<table width="97%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td class="tablestyle001">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td>
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td width="24">
																		<img src="images/zhenxin01.jpg" width="24" height="26" />
																	<br /></td>
																	<td>
																		<table width="100%" border="0" cellpadding="0"
																			cellspacing="0" class="newstitle">
																			<tr>
																				<td>
																					系统消息
																				<br /></td>
																				<td class="newstitle1">
																					<a href="#" onclick="querySystem();"
																						class="newstitle1">更多</a>
																				<br /></td>
																			</tr>
																		</table>
																	<br /></td>
																	<td width="8">
																		<img src="images/zhenxin10.jpg" width="8" height="26" />
																	<br /></td>
																</tr>
															</table>
														<br /></td>
													</tr>
													<tr>
														<td valign="top" class="newstiltlebg">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td height="1px" class="tablec"><br /></td>
																</tr>
																<tr>
																	<td valign="top">
																		<table width="100%" border="0" cellpadding="0"
																			cellspacing="0" class="text04">
																			<logic:empty name="messageSystem">
																				<tr>
																					<td align="center">
																						暂时没有系统消息
																					<br /></td>
																				</tr>
																			</logic:empty>
																			<logic:notEmpty name="messageSystem">
																				<logic:iterate id="messageSys" name="messageSystem">
																					<tr>
																						<td align="left">
																							<a href="#"
																								onclick="lookMessage('<bean:write name="messageSys" property="id"/>');"> []</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																							<font><logic:equal value="READED"
																									name="messageSys" property="flag">已读取</logic:equal>
																							</font><font color="red"><logic:equal
																									value="UNREAD" name="messageSys"
																									property="flag">未读取</logic:equal>
																							</font>
																						<br /></td>
																					</tr>
																				</logic:iterate>
																			</logic:notEmpty>
																		</table>
																	<br /></td>
																</tr>
															</table>
														<br /></td>
													</tr>
												</table>
											<br /></td>
										</tr>
										<tr>
											<td align="center" class="tablestyle001">
												<table width="100%" border="0" align="left" cellpadding="0"
													cellspacing="0">
													<tr>
														<td>
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td width="24">
																		<img src="images/zhenxin01.jpg" width="24" height="26" />
																	<br /></td>
																	<td>
																		<table width="100%" border="0" cellpadding="0"
																			cellspacing="0" class="newstitle">
																			<tr>

																				<td align="left">
																					任务待办消息
																				<br /></td>
																				<td class="newstitle1">
																					<a href="#" class="newstitle1"
																						onclick="queryList();">更多</a>
																				<br /></td>
																			</tr>
																		</table>
																	<br /></td>
																	<td width="8">
																		<img src="images/zhenxin10.jpg" width="8" height="26" />
																	<br /></td>
																</tr>
															</table>
														<br /></td>
													</tr>
													<tr>
														<td valign="top" class="newstiltlebg">
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td height="1px" class="tablec"><br /></td>
																</tr>
																<tr>
																	<td valign="top">
																		<table width="100%" border="0" cellpadding="0"
																			cellspacing="0" class="text04">
																			<logic:empty name="messageTrankList">
																				<tr>
																					<td align="center">
																						暂时没有待办的任务
																					<br /></td>
																				</tr>
																			</logic:empty>
																			<logic:notEmpty name="messageTrankList">
																				<logic:iterate id="message" name="messageTrankList">
																					<tr>
																						<td align="left">
																							<a href="#"
																								onclick="lookMessage('<bean:write name="message" property="id"/>');"> []</a>
																							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																							<font><logic:equal value="READED"
																									name="message" property="flag">已读取</logic:equal>
																							</font><font color="red"><logic:equal
																									value="UNREAD" name="message" property="flag">未读取</logic:equal>
																							</font>
																						<br /></td>
																					</tr>
																				</logic:iterate>
																			</logic:notEmpty>
																		</table>
																	<br /></td>
																</tr>
															</table>
														<br /></td>
													</tr>
												</table>
											<br /></td>
										</tr>
									</table>
								<br /></td>
								<td width="29%" valign="top" class="tablestyle001">
									<table width="100%" border="0" align="left" cellpadding="0"
										cellspacing="0">
										<tr>
											<td>
												<table width="99%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td width="24">
															<img src="images/zhenxin01.jpg" width="24" height="26" />
														<br /></td>
														<td>
															<table width="100%" border="0" cellpadding="0"
																cellspacing="0" class="newstitle">
																<tr>
																	<td width="100%">
																		问题常见解答
																	<br /></td>
																</tr>
															</table>
														<br /></td>

													</tr>
												</table>
											<br /></td>
										</tr>
										<tr>
											<td valign="top" class="newstiltlebg">
												<table border="0" cellspacing="0" cellpadding="0">
													<tr>
														<td width="254">
															<table border="0" cellpadding="0" cellspacing="0"
																class="text04">
																<tr>
																	<td width="234">
																		<span class="text01">系统问题常见解答</span>
																	<br /></td>
																</tr>
																<tr>
																	<td>
																		<a href="<%=path%>/fqa.jsp" class="text04"
																			target="top">为什么有些菜单无法操作?</a>
																	<br /></td>
																</tr>
																<tr>
																	<td>
																		<a href="<%=path%>/fqa.jsp" class="text04"
																			target="top">为什么进不了系统?</a>
																	<br /></td>
																</tr>
																<tr>
																	<td>
																		<a href="<%=path%>/fqa.jsp" class="text04"
																			target="top">我怎样才能对有些功能进行操作?</a>
																	<br /></td>
																</tr>
																<tr>
																	<td height="1" class="tablec"><br /></td>
																</tr>
															</table>
														<br /></td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0"
																class="text04">
																<tr>
																	<td>
																		<span class="text01">常用问题常见解答</span>
																	<br /></td>
																</tr>
																<tr>
																	<td>
																		<a href="<%=path%>/fqa.jsp" class="text04"
																			target="top">为什么有些数据不能删除?</a>
																	<br /></td>
																</tr>
																
																<tr>
																	<td>
																		<a href="<%=path%>/fqa.jsp" class="text04"
																			target="top">怎样快速查询?</a>
																	<br /></td>
																</tr>
																<tr>
																	<td height="1" class="tablec">&nbsp;<br /></td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td>
															<table border="0" cellpadding="0" cellspacing="0"
																class="text04">
																<tr>
																	<td>
																		<span class="text01">其他问题常见解答</span>
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
