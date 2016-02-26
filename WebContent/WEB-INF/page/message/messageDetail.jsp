<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tld/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><bean:message key="PROJECT_NAME" />
		</title>
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
								消息详细信息
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<form action="./messageManage.do?method=ascertainLook" method="post">
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
														<td width="6">
															<img src="images/zhenxin26.jpg" width="6" height="26" />
														</td>
														<td class="tiltlegerner">
															消息详细列表
														</td>
														<td width="6">
															<img src="images/zhenxin28.jpg" width="6" height="26" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td valign="top">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0" class="tableline01">
													<tr>
														<td width="15%" align="right" class="tabletext02">
															消息标题：
															<input type="hidden" name="messageId"
																value="<bean:write name="message" property="id"/>" />
														</td>
														<td colspan="3">
															<span class="tdstylebotton"> <bean:write
																	name="message" property="title" /> </span>
														</td>
													</tr>

													<tr>
														<td align="right" class="tabletext02">
															收信人：
														</td>
														<td width="27%">
															<span class="tdstylebotton"> <bean:write
																	name="message" property="receiver" /> </span>
														</td>
														<td width="8%" align="right" class="tabletext02">
															发信人：
														</td>
														<td width="50%" class="tdstylebotton">
															<bean:write name="message" property="sender" />
														</td>
													</tr>

													<tr>
														<td align="right" class="tabletext02">
															发送时间：
														</td>
														<td>
															<span class="tdstylebotton"> <bean:write
																	name="message" property="createdate"
																	format="yyyy-MM-dd HH:mm:ss" /> </span>
														</td>
														<td align="right" class="tabletext02">
															收到时间：
														</td>
														<td>
															<span class="tdstylebotton"> <bean:write
																	name="message" property="receivedate"
																	format="yyyy-MM-dd HH:mm:ss" /> </span>
														</td>
													</tr>
													<tr>
														<td align="right" class="tabletext02">
															消息内容：
														</td>
														<td class="tdstylebotton" colspan="3">
															<bean:write name="message" property="message" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td class="tdstylebotton">
									<table width="20%" border="0" align="center" cellpadding="0"
										cellspacing="0">
										<tr>
											<td align="center">
												<span class="tdstyle"> <input type="submit" name="ok"
														class="botton01" value="确 认" /> </span>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>