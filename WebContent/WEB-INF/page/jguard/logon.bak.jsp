<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="jguard" uri="/WEB-INF/tld/jguard.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<head>
<title><bean:message key="common.text.version"/></title>
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link rel="icon" href="favicon.ico" type="image/x-icon"/>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F1F8FC;
}
</style>
<link href="css/css.css" rel="stylesheet" type="text/css" />
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
		<div>
			<div id="content"></div>
			<div id="content"></div>
			<div id="content"></div>
			<div id="content"></div>
			<div id="main">				
				<table align="left">
							<tr>
								<td width="20%"></td>
								<td align="left" width="80%">				
									<img src="image/bankofchina_LOGO.gif" border="0"/>									
								</td>
							</tr>
						</table>				
				<div id="content">
					<div id="query">						
						<table align="center">							
							<tr>
								<td>
									<b>
										印章管理系统&nbsp;
									</b>
								</td>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td align="right" width="20%">
									<bean:message key="logon.jsp.lable.login"/>
								</td>
								<td align="left" width="80%">
									<input id="login" type="text" maxlength="20" size="30" name="login"
										tabindex="1" class="text111" />
								</td>
							</tr>
							<tr>
								<td align="right" >
									<bean:message key="logon.jsp.lable.password"/>
								</td>
								<td align="left" >
									<input id="password" type="password" value="" size="30" name="password" 
										tabindex="2" class="text111"/>
								</td>
							</tr>
							<tr>
								<td align="right" width="20%">
									<bean:message key="logon.jsp.lable.languageversion"/>
								</td>
								<td align="left" width="80%">									
									<select id="locale" name="locale" tabindex="3">
										<option value="zh_CN">
											<bean:message key="logon.jsp.select.language.option.china"/>
										</option>
										<option value="en_US">
											<bean:message key="logon.jsp.select.language.option.usa"/>
										</option>
										<option value="zh_TW">
											<bean:message key="logon.jsp.select.language.option.china_tw"/>
										</option>
									</select>
								</td>
							</tr>
							<tr>
								<td align="right" >									
									<img id="captcha" src="<html:rewrite action='/Captcha.do'/>" 
										style="width: 170px;height: 40px"  onclick="this.src='<html:rewrite action='/Captcha.do'/>'"/>									
								</td>
								<td align="left" >
									<input id="captchaAnswer" type="text" value="" size="30" name="captchaAnswer"
										tabindex="4" class="text111"/>
								</td>
							</tr>							
						</table>
						<table align="center">
							<tr>
								<td align="center" width="40%">
									<input type="button" id="deng" tabindex="4" onclick="onclickLogin()" class="botton01"
										value="<bean:message key="logon.jsp.button.login"/>"/>
								</td>
								<td align="center" width="55%">
									<input type="reset" tabindex="5"  class="botton01"
										value="<bean:message key="logon.jsp.button.Reset"/>"/>
								</td>
							</tr>
						</table>						
					</div>
				</div>
				<div id="content"></div>
				<div id="content"></div>
				<div id="content"></div>
				<div id="content"></div>
				<div id="content"></div>
				<div id="content"></div>
				<div id="content"></div>
				<div id="content"></div>
				<div id="content">
					<table align="right">
						<tr>
							<td align="right" width="35%"/>
							<td width="52%">
								<font style="font-size:11px;color:#000000;">
									版权所有：中国银行 Bank of China
								</font>								
							</td>
							<td align="right" width="13%"/>
						</tr>						
					</table>
				</div>
			</div>
	</html:form>
</body>
