<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="java.util.Set,java.util.List,java.util.Map,java.util.Iterator,net.sf.jguard.core.authorization.permissions.Domain,java.security.Permission;"%>
<title><bean:message key="PROJECT_NAME"/></title>
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
<!--
function checkDomain(tableId,checkboxThis){
	var table = document.getElementById(tableId);
	var checkboxes = table.getElementsByTagName("input");
	for(var i = 0;i < checkboxes.length;i++){
		if(checkboxThis.checked == true){
			checkboxes[i].disabled = true;
			checkboxes[i].checked = true;
		}else{
			checkboxes[i].disabled = false;
			checkboxes[i].checked = false;
		}
	}
}

function updatePrincipal(){
var domNames = document.getElementById('principalDomains');
var strDomainNames="";
var nametry = /^([a-z]|[A-Z]|[0-9]|[\u0391-\uFFE5])+$/ ;
	if(document.getElementById("principalName").value==""){
		alert("The principal name be required!");
		document.getElementById("principalName").focus();
		return false;
	}
	if(!nametry.test(document.getElementById("principalName").value)){
		alert("The other sign has not be used in principal name!");
		return false;
	}
	document.forms[0].action="./Principal.do?method=updateCheckBox";
	document.forms[0].submit();
}

function checkAll(name) {     
	var el = document.getElementsByTagName('input');     
	var len = el.length;     
	for(var i=0; i<len; i++) {         
		if((el[i].type=="checkbox") && (el[i].name==name)){             
			el[i].checked = true;         
		}    
	} 
} 
	
function clearAll(name) {     
	var el = document.getElementsByTagName('input');     
	var len = el.length;     
	for(var i=0; i<len; i++){        
		if((el[i].type=="checkbox") && (el[i].name==name)){             
			el[i].checked = false;         
		}     
	} 
}
-->
</script>
<html:form action="/Principal.do?method=update">
	<input type="hidden" id="oldPrincipalName" name="oldPrincipalName"
		value="<%=(String) request.getAttribute("principalName")%>">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="35" class="nawzjj">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
          <td class="ndwz">&nbsp;</td>
          <td class="ndwzzml"><bean:message key="PROJECT_NAME"/>
          <img src="<%= request.getContextPath() %>/images/index11.jpg" width="6" height="10" hspace="5" />
         角色信息
</td>
	</tr></table>
	</td>
	</tr>
</table>	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top">
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td class="tablestyle">
<table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
             <td align="left">
    <table width="40%" border="0" align="left" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="gtitle" align="left">角色名:</td>
                    <td><b><input type="text" id="principalName" name="principalName" size="30" class="text111" style="color:#339999;"
							value="<%=(String) request.getAttribute("principalName")%>"></b></td>
					<td>
                    <input type="button" value="保 存" class="botton01"
					onclick="javascript:updatePrincipal();return false;" />
				<input type="button" value="返 回" class="botton01"
					onclick="history.go(-1)" />
                   </td> 
               </tr>
  </table></td>
          </tr>
         <tr>
         <td> <div>
				<table>					
					<%
						StringBuffer sb = new StringBuffer("");
							Set<?> allDomains = (Set<?>) request.getAttribute("allDomains");
							List<?> hasDomains = (List<?>) request
									.getAttribute("hasDomains");
							Set<?> hasOrphanedPermissions = (Set<?>) request
									.getAttribute("hasOrphanedPermissions");
							Map<String, Set<?>> allDomainPermissionsMap = (Map<String, Set<?>>) request
									.getAttribute("allDomainPermissionsMap");
							int tableIndex = 0;
							boolean blocked = false;

							for (Iterator<?> it = allDomains.iterator(); it.hasNext();) {
								Domain domain = (Domain) it.next();
								sb.append("<table class='tableline01' width='100%' border='0' cellpadding='0' cellspacing='0'>");								
								//判断public权限域不显示
								if (domain.getName().trim().equalsIgnoreCase("public")) {
									sb.append("<tr style='display:none;'>");
									sb.append("<td align='left' width='3%'>");
									sb
											.append("<input id='domains' name='domains' type='checkbox' checked='checked' value='");
									sb.append(domain.getName());
									sb.append("'/></td>");
									sb.append("<td align='left' width='80%'>");
									sb.append("<b>" + domain.getName() + "</b>");
									sb.append("</td>");
									sb.append("</div>");
									sb.append("</tr>");
									sb.append("</table>");
									continue;
								}
								//判断public权限域不显示
								sb.append("<tr class='tabletext01'>");
								sb.append("<td align='left' width='3%'>");
								sb
										.append("<input id='domains' name='domains' type='checkbox' value='");
								sb.append(domain.getName());
								sb.append("' onclick='checkDomain(" + tableIndex
										+ ",this);' ");
								for (int i = 0; i < hasDomains.size(); i++) {
									Domain domainTmp = (Domain) hasDomains.get(i);
									if (domainTmp.getName().equals(domain.getName())) {
										sb.append(" checked='checked'");
										blocked = true;
										break;
									}
								}
								sb.append("/></td>");
								sb.append("<td align='left' width='80%'>");
								sb.append("<b>" + domain.getName() + "</b>");
								sb.append("</td>");
								sb.append("</tr>");
								sb.append("</table>");
								sb.append("<table class='tableline01' width='100%' border='0' cellpadding='0' cellspacing='0' id='" + tableIndex + "' >" );
								//内循环---------------
								if (allDomainPermissionsMap.get(domain.getName()) != null
										&& allDomainPermissionsMap.get(domain.getName())
												.size() > 0) {
									Set<?> tempDomainPermissions = allDomainPermissionsMap
											.get(domain.getName());
									for (Iterator<?> iterator = tempDomainPermissions
											.iterator(); iterator.hasNext();) {
										Permission permission = (Permission) iterator
												.next();
										sb.append("<tr>");
										sb.append("<td align='right' width='10%'>");
										sb
												.append("<input id='permissions' name='permissions' type='checkbox' value='");
										sb.append(permission.getName());
										sb.append("' ");

										if (blocked) {
											sb.append(" disabled='disabled' ");
										}
										for (Iterator<?> orphanedPermissionsIt = hasOrphanedPermissions
												.iterator(); orphanedPermissionsIt
												.hasNext();) {
											Permission orphanedPermission = (Permission) orphanedPermissionsIt
													.next();
											if (orphanedPermission.getName().equals(
													permission.getName())) {
												sb.append(" checked='checked'");
												break;
											}
										}
										sb.append("/></td>");
										sb.append("<td align='left' width='70%'>");
										sb.append(permission.getName());
										sb.append("</td>");
										sb.append("</tr>");
									}
								}
								blocked = false;
								
								
								//-----------------
								
								sb.append("</table>");
								tableIndex++;
							}
							out.write(sb.toString());
					%>
					<tr>
         <td><div class="tdstyle">
				<input type="button" value="保 存" class="botton01"
					onclick="javascript:updatePrincipal();return false;" />
				<input type="button" value="返 回" class="botton01"
					onclick="history.go(-1)" />
			</div></td>
         </tr>
				</table>
			</div></td>
         </tr>
</table>
		</td></tr></table></td></tr></table>
</html:form>