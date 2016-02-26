<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
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
<script type="text/javascript">
function deletePermission(permissionName){
	if(confirm('<bean:message key="permissionList.jsp.message.deletepermission"/>')){
		document.getElementById("permissionName").value=permissionName;
		document.forms[0].action="./Permission.do?method=delete";
		document.forms[0].submit();
	}
}
function deleteDomain(domainName){
	if(confirm('<bean:message key="permissionList.jsp.message.deletedomain"/>')){
		document.getElementById("domainName").value=domainName;
		document.forms[0].action="./Domain.do?method=delete";
		document.forms[0].submit();
	}
}
function updatePermission(permissionName){
	var nametry = /^([a-z]|[A-Z]|[0-9]|[\u0391-\uFFE5])+$/ ;
	if(document.getElementById(permissionName+".permissionName").value==""){
		alert('<bean:message key="permissionList.jsp.message.permissionnamerequired"/>');
		document.getElementById(permissionName+".permissionName").value.focus();
		return false;
	}
	if(!nametry.test(document.getElementById(permissionName+".permissionName").value)){
		alert('<bean:message key="permissionList.jsp.message.nosigninpermissionname"/>');
		return false;
	}
	if(document.getElementById(permissionName+".actions").value==""){
		alert('<bean:message key="permissionList.jsp.message.permissionactionrequired"/>');
		document.getElementById(permissionName+".actions").value.focus();
		return false;
	}
document.getElementById("oldPermissionName").value=permissionName;
document.getElementById("permissionActions").value=document.getElementById(permissionName+".actions").value;
document.getElementById("permissionClass").value=document.getElementById(permissionName+".class").value;
document.getElementById("permissionName").value=document.getElementById(permissionName+".permissionName").value;
document.getElementById("domainName").value=document.getElementById(permissionName+".domainName").options[document.getElementById(permissionName+".domainName").selectedIndex].value;
document.forms[0].action="./Permission.do?method=update";
document.forms[0].submit();
}
function createDomain(){
	var nametry = /^([a-z]|[A-Z]|[0-9]|[\u0391-\uFFE5])+$/ ;
	if(document.getElementById("domainNameField").value==""){
		alert('<bean:message key="permissionList.jsp.message.domainnamerequired"/>');
		document.getElementById("domainNameField").focus();
		return false;
	}
	if(!nametry.test(document.getElementById("domainNameField").value)){
		alert('<bean:message key="permissionList.jsp.message.nosignindomainname"/>');
		return false;
	}
 document.getElementById("domainName").value=document.getElementById("domainNameField").value;
 document.forms[0].action="./Domain.do?method=create";
 document.forms[0].submit();
}
function updateDomain(oldDomainName){
	var nametry = /^([a-z]|[A-Z]|[0-9]|[\u0391-\uFFE5])+$/ ;
	if(document.getElementById(oldDomainName).value==""){
		alert('<bean:message key="permissionList.jsp.message.domainnamerequired"/>');
		document.getElementById(oldDomainName).focus();
		return false;
	}
	if(!nametry.test(document.getElementById(oldDomainName).value)){
		alert('<bean:message key="permissionList.jsp.message.nosignindomainname"/>');
		return false;
	}
document.getElementById("oldDomainName").value=oldDomainName;
document.getElementById("domainName").value = document.getElementById(oldDomainName).value;
document.forms[0].action="./Domain.do?method=update";
document.forms[0].submit();
}

function createPermission(){
var nametry = /^([a-z]|[A-Z]|[0-9]|[\u0391-\uFFE5])+$/ ;
	if(document.getElementById("permName").value==""){
		alert('<bean:message key="permissionList.jsp.message.permissionnamerequired"/>');
		document.getElementById("permName").focus();
		return false;
	}
	if(!nametry.test(document.getElementById("permName").value)){
		alert('<bean:message key="permissionList.jsp.message.nosigninpermissionname"/>');
		return false;
	}
	if(document.getElementById("permActions").value==""){
		alert('<bean:message key="permissionList.jsp.message.permissionactionrequired"/>');
		document.getElementById("permActions").focus();
		return false;
	}
document.getElementById("domainName").value=document.getElementById('selectDomainPermission').options[document.getElementById('selectDomainPermission').selectedIndex].value;
document.getElementById("permissionName").value=document.getElementById("permName").value;
document.getElementById("permissionClass").value=document.getElementById("permClass").value;
document.getElementById("permissionActions").value=document.getElementById("permActions").value;
document.forms[0].action="./Permission.do?method=create";
document.forms[0].submit();
}
</script>
<title><bean:message key="common.text.version" />
</title>


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
						<bean:message key="permissionList.jsp.navigation.2" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<html:form action="/Permission.do?method=update">
	<html:hidden styleId="oldPermissionName" title="oldPermissionName"
		property="oldPermissionName" value="" />
	<html:hidden styleId="permissionName" title="permissionName"
		property="permissionName" value="" />
	<html:hidden styleId="oldDomainName" title="oldDomainName"
		property="oldDomainName" value="" />
	<html:hidden styleId="domainName" title="domainName"
		property="domainName" value="" />
	<html:hidden styleId="permissionClass" title="permissionClass"
		property="permissionClass" value="" />
	<html:hidden styleId="permissionActions" title="permissionActions"
		property="permissionActions" value="" />
	<table width="100%" border="0" cellspacing="0" cellpadding="0">

		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="tablestyle">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="tableline01">
											<tr class="tabletext01">
												<td colspan="2" align="left">
													创建权限
												</td>
											</tr>
											<tr>
												<td class="tabletext02">
													<b>新建权限域：</b>
												</td>
												<td>
													<input id="domainNameField" class="text111" value=""
														maxlength="50">
													<input type="button"
														title='<bean:message key="permissionList.jsp.button.createdomain"/>'
														onclick="javascript:createDomain();"
														value='<bean:message key="permissionList.jsp.button.createdomain"/>'
														class="botton01">
												</td>
											</tr>
											<tr>
												<td colspan="2">
													<b>新建权限</b>
												</td>
											</tr>
											<tr>
												<td>
													权限名称:
												</td>
												<td>
													<input id="permName" size="50" value="" class="text111"
														maxlength="50">
												</td>
											</tr>
											<tr>
												<td>
													权限类:
												</td>
												<td>
													<!-- <input id="permClass" size="80"
							value="net.sf.jguard.core.authorization.permissions.URLPermission"
							readonly="readonly"/> -->
													<select id="permClass" name="permClass">
														<option
															value="net.sf.jguard.core.authorization.permissions.URLPermission">
															URL控制权限
														</option>
														<!-- add other permission class here -->
													</select>
												</td>
											</tr>
											<tr>
												<td>
													权限URL：
												</td>
												<td>
													<input id="permActions" size="50" value="" class="text111">
												</td>
											</tr>
											<tr>
												<td>
													所属域:
												</td>
												<td>
													<select name="select" id="selectDomainPermission">
														<c:forEach var="dom" items="${generalForm.map.Domains}">
															<option value="<c:out value="${dom.name}"/>">
																<c:out value="${dom.name}" />
														</c:forEach>
													</select>
												</td>
											</tr>
											<tr>
												<td height="30" colspan="2" align="center">
													<div class="dkbutton">
														<input type="button"
															value='<bean:message key="permissionList.jsp.button.createpermission"/>'
															title='<bean:message key="permissionList.jsp.button.createpermission"/>'
															class="botton01" onclick="javascript:createPermission();">
													</div>
												</td>
											</tr>
										</table>
									</td>
								</tr>

								<tr>
									<td>
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="tableline01">
											<tr class="tabletext01">
												<td colspan="3" align="left">
													权限域列表:
												</td>
											</tr>
											<c:forEach var="domain" items="${generalForm.map.Domains}"
												varStatus="statusDomain">




												<tr class="tabletext03">
													<td colspan="2">
														<b> <c:out value="${statusDomain.index + 1}" />&nbsp;.&nbsp;<c:out
																value="${domain.name}" />
														</b>
													</td>
												</tr>
												<tr>
													<td>
														<bean:message
															key="permissionList.jsp.lable.list.domainname" />
													</td>
													<td>
														<input id="<c:out value="${domain.name}"/>" size="50"
															class="text111" value="<c:out value="${domain.name}" />">
														<input type="button" class="botton01"
															value="<bean:message key="permissionList.jsp.button.update"/>"
															style="border: 0px;"
															onclick="javascript:updateDomain('<c:out value="${domain.name}" />');" />
														<input type="button" class="botton01"
															value="<bean:message key="permissionList.jsp.button.delete"/>"
															style="border: 0px;" size="70"
															onclick="javascript:deleteDomain('<c:out value="${domain.name}" />');" />
													</td>
												</tr>
												<c:forEach var="permission" items="${domain.permissions}"
													varStatus="statusPerm">
													<tr>

														<td>
															<font style="color: #000000;"> <bean:message
																	key="permissionList.jsp.lable.create.permissionname" />
															</font>
														</td>
														<td>
															<input
																id="<c:out value="${permission.name}" />.permissionName"
																class="text111" size="50" type="text"
																value="<c:out value="${permission.name}" />" />
														</td>
													</tr>
													<tr>
														<td>
															<bean:message key="permissionList.jsp.lable.create.class" />
														</td>
														<td>
															<input id="<c:out value="${permission.name}" />.class"
																class="text111" style="color: #339999;" type="text"
																value="<c:out value="${permission.class.name}" />"
																readonly="readonly" size="70" />
														</td>
													</tr>
													<tr>
														<td>
															<bean:message
																key="permissionList.jsp.lable.create.action" />
														</td>
														<td>
															<input id="<c:out value="${permission.name}" />.actions"
																class="text111" type="text"
																value="<c:out value="${permission.actions}" />"
																size="70" />
														</td>
													</tr>
													<tr>
														<td>
															<bean:message
																key="permissionList.jsp.lable.create.domain" />
														</td>
														<td>
															<select
																id="<c:out value="${permission.name}" />.domainName">
																<c:forEach var="dom" items="${generalForm.map.Domains}">
																	<option value="<c:out value="${dom.name}"/>"
																		<c:if test='${dom.name == domain.name}'>selected="selected"</c:if>>
																		<c:out value="${dom.name}" />
																	</option>
																</c:forEach>
															</select>
														</td>
													</tr>
													<tr>
														<td colspan="3" align="center">

															<input
																value="<bean:message key="permissionList.jsp.button.update"/>"
																type="button" class="botton01" style="border: 0px;"
																onclick="javascript:updatePermission('<c:out value="${permission.name}" />');" />
															<input
																value="<bean:message key="permissionList.jsp.button.delete"/>"
																type="button" class="botton01" style="border: 0px;"
																onclick="javascript:deletePermission('<c:out value="${permission.name}" />');" />
														</td>
													</tr>
												</c:forEach>

											</c:forEach>
										</table>
										</html:form>