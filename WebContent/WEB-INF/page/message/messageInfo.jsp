<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sweet" uri="/WEB-INF/tld/sweet-tag.tld"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib prefix="beanel" uri="/WEB-INF/tld/struts-bean-el.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><bean:message key="PROJECT_NAME" /></title>
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
 function lookMessage(messageId){	
 document.forms[0].action="./messageManage.do?method=lookMessage&messageId="+messageId;
document.forms[0].submit();
}
function deleteMessage(messageId){
if(confirm("你确认要删除吗?")){
document.forms[0].action="./messageManage.do?method=deleteMessage&messageId="+messageId;
document.forms[0].submit();
		}
	}
	
function queryMessage(){
document.forms[0].action="./messageManage.do?method=messageQuery&btnType="+1;
document.forms[0].submit();
}
function clean(){
document.getElementById('title').value="";
document.getElementById('begin').value="";
document.getElementById('end').value="";
document.getElementById('state').value="";
document.getElementById('type').value="";
}
</script>
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
								消息管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								消息列表
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/messageManage.do?method=messageQuery"
			method="post">
			<html:hidden name="messageForm" property="pageCount" />
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
															<table width="99%" border="0" align="left"
																cellpadding="0" cellspacing="3">
																<tr>
																	<td class="gtitle">
																		标题
																	</td>
																	<td class="tdstylebotton">
																		<html:text property="title" styleId="title"
																			styleClass="text111" size="18"></html:text>
																	</td>
																	<td class="gtitle">
																		起始时间
																	</td>
																	<td>
																		<html:text property="begin" maxlength="20" size="15"
																			styleClass="text111"
																			onkeydown="if(event.keyCode==13){search();}"
																			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
																	</td>
																	<td class="gtitle">
																		截至时间
																	</td>
																	<td>
																		<html:text property="end" maxlength="20" size="15"
																			styleClass="text111"
																			onkeydown="if(event.keyCode==13){search();}"
																			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
																	</td>
																	<td class="gtitle">
																		状态
																	</td>
																	<td class="tdstylebotton">
																		<html:select property="state" styleClass="text333">
																			<html:option value="">----请选择---</html:option>
																			<logic:iterate id="category" name="messageCategorys">
																				<option value="<bean:write name="category"/>">
																					<beanel:message key="${category}" />
																				</option>
																			</logic:iterate>
																		</html:select>
																	</td>
																	<td class="gtitle">
																		类型
																	</td>
																	<td class="tdstylebotton">
																		<html:select property="type" styleClass="text333">
																			<html:option value="">----请选择---</html:option>
																			<logic:iterate id="messageType"
																				name="MessageCategory">
																				<option value="<bean:write name="messageType"/>">
																					<beanel:message key="${messageType}" />
																				</option>
																			</logic:iterate>
																		</html:select>
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
											<td align="center">
												<table border="1">
													<tr>
														<td>
															<input name="add" type="button" class="botton01"
																value="查 询" onclick="queryMessage();" />
															<input name="add" type="button" class="botton01"
																value="重 置" onclick="clean();" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0" class="tableline01">
													<tr>
														<td width="40%" class="tabletext01">
															消息标题
														</td>
														<td class="tabletext01">
															发送人
														</td>
														<td class="tabletext01">
															发送时间
														</td>
														<td class="tabletext01">
															消息类型
														</td>
														<td class="tabletext01">
															消息状态
														</td>
														<td class="tabletext01">
															操作
														</td>
													</tr>
													<logic:empty name="messageList">
														<tr>
															<td align="center" colspan="6">
																暂无消息
															</td>
														</tr>
													</logic:empty>
													<logic:notEmpty name="messageList">
														<logic:iterate id="message" name="messageList">
															<tr>
																<td align="center">
																	<bean:write name="message" property="title" />
																	&nbsp;
																</td>
																<td align="center">
																	<bean:write name="message" property="sender" />
																	&nbsp;
																</td>
																<td align="center">
																	<bean:write name="message" property="createdate"
																		format="yyyy-MM-dd HH:mm:ss" />
																	&nbsp;
																</td>
																<td align="center">
																	<logic:empty name="message" property="category">
                 														&nbsp;
                													</logic:empty>
																	<logic:notEmpty name="message" property="category">
																		<beanel:message key="${message.category}" />
																	</logic:notEmpty>
																</td>
																<td align="center">
																	<logic:empty name="message" property="flag">
              	  																		&nbsp;
               													 	</logic:empty>
																	<logic:notEmpty name="message" property="flag">
																		<beanel:message key="${message.flag}" />
																	</logic:notEmpty>
																</td>
																<td align="center">
																	<span class="tdstyle"> <input name="Submit2"
																			type="submit" class="botton01" value="查 看"
																			onclick="lookMessage('<bean:write name="message" property="id"/>');" />
																	</span>&nbsp;
																	<logic:equal value="SYSTEM_NOTICE" name="message"
																		property="category">
																		<span style="display: none;"> <input
																				name="Submit" type="reset" class="botton01"
																				value="删 除"
																				onclick="deleteMessage('<bean:write name="message" property="id"/>');" />
																		</span>
																	</logic:equal>
																	<logic:equal value="TASK_ASSIGN" name="message"
																		property="category">
																		<span> <input name="Submit" type="reset"
																				class="botton01" value="删 除"
																				onclick="deleteMessage('<bean:write name="message" property="id"/>');" />
																		</span>
																	</logic:equal>
																</td>
															</tr>
														</logic:iterate>
													</logic:notEmpty>
												</table>
											</td>
										</tr>
										<tr>
											<td></td>
											<td class='left' align="right">
												<sweet:page formName="messageForm" hrefClass="left" />
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