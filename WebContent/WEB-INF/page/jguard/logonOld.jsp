<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="jguard" uri="/WEB-INF/tld/jguard.tld"%>
<html>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<head>
		<title><bean:message key="CUSTOM_NAME"/>-<bean:message key="PROJECT_NAME"/></title>
		<link type="text/css" rel="StyleSheet" href="css/font2.css"></link>
		<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
		<link rel="icon" href="favicon.ico" type="image/x-icon" />
		<script language="javascript">	
	function onclickLogin(){
		if(document.getElementById("login").value==""){
			alert('<bean:message key="logon.jsp.message.loginRequired"/>');
			return false;
		}
		if(document.getElementById("password").value==""){
			alert('<bean:message key="logon.jsp.message.passwordRequired"/>');
			return false;
		}
		document.generalForm.submit();  
	}
</script>
		<script language="javascript" event="onkeydown" for="document">  
	if(event.keyCode==13){  
		document.all('deng').focus();  
	}
</script>
	</head>

	<body>
		<html:form action="/LogonProcess" method="post">
			<table width="100%" height="100%" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td bgcolor="b3b3b3">
						<table height="600" width="658" border="0" align="center"
							cellpadding="0" cellspacing="0">
							<tr>
								<td colspan="2" height="85"></td>
							</tr>
							<tr>
								<td colspan="2">
									<img src="images/login_03.jpg" width="658" height="275">
								</td>
							</tr>
							<tr>
								<td colspan="2">
									<table width="100%" border="0" cellspacing="0" cellpadding="0">
										<tr>
											<td width="47%">
												<img src="images/login_05.jpg" width="312" height="110">
											</td>
											<td width="53%" background="images/login_06.jpg">
												<table width="300" border="0" cellspacing="0"
													cellpadding="3">

													<tr>
														<td align="right" class="Font2"></td>
														<td></td>
														<td width="48" rowspan="4" valign="bottom">
															<input type="button" id="deng" onclick="onclickLogin()"
																class="botton01"
																value="<bean:message key="logon.jsp.button.login"/>" />
															<input type="reset" class="botton01"
																value="<bean:message key="logon.jsp.button.Reset"/>" />
														</td>
													</tr>

													<tr>
														<td align="right" class="Font2">
															姓名：
														</td>
														<td>
															<input id="login" name="login" type="text" class="input1"
																size="20" />
														</td>
													</tr>
													<tr>
														<td align="right" class="Font2">
															密码：
														</td>
														<td>
															<input id="password" name="password" type="password"
																class="input1" size="20" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td width="590">
									<img src="images/login_07.jpg" width="590" height="68">
								</td>
								<td width="68">
									<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
										codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0"
										width="68" height="68">
										<param name="movie" value="images/lingdang.swf">
										<param name="quality" value="high">
										<embed src="images/lingdang.swf" quality="high"
											pluginspage="http://www.macromedia.com/go/getflashplayer"
											type="application/x-shockwave-flash" width="68" height="68"></embed>
									</object>
								</td>
							</tr>
							<tr>
								<td colspan="2" height="85"></td>
							</tr>
						</table>
					</td>
				</tr>				
			</table>
		</html:form>
	</body>
</html>
