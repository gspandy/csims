<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<title><bean:message key="logoff.jsp.title"/></title>
<h1>
	<bean:message key="logoff.jsp.message"/>
</h1>
<br>
<a href="<%=request.getContextPath()%>/Logon.do">
	<bean:message key="logoff.jsp.href"/>
</a>