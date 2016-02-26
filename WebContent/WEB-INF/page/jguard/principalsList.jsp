<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sweet" uri="/WEB-INF/tld/sweet-tag.tld"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/manus.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/home.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/home03.js"></script>	
	
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/manu.js"></script>
<script type="text/javascript" src="js/WdatePicker.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F1F8FC;
}
</style>
<script type="text/javascript">
	function deletePrincipal(principalName){
		if(confirm("是否确定删除该角色?")){
			document.getElementById("principalName").value=principalName;
			document.forms[0].action="./Principal.do?method=delete";
			document.forms[0].submit();
		}
	}
	
	function createPrincipal(){
		var nametry = /^([a-z]|[A-Z]|[0-9]|[\u0391-\uFFE5])+$/ ;
		if(document.getElementById("permName").value==""){
			alert("请输入角色名!");
			document.getElementById("permName").focus();
			return false;
		}
		if(!nametry.test(document.getElementById("permName").value)){
			alert("该角色已经存在!");
			return false;
		}
		document.forms[0].action = "./Principal.do?method=create";
		document.forms[0].principalName.value=document.getElementById("permName").value;
		document.forms[0].submit();
	}
	
	function readPrincipal(principalName){
		document.getElementById("principalName").value=principalName;
		document.forms[0].action="./Principal.do?method=read";
		document.forms[0].submit();
	}
	
	function readCheckedPrincipal(principalName){
		document.getElementById("principalName").value=principalName;
		document.forms[0].action="./Principal.do?method=readAsCheckBox";
		document.forms[0].submit();
	}
	
	function resets(){	
		document.getElementsByName("rolename")[0].value="";		
	}
	
	function search(){
        document.forms[0].action="./Principal.do?method=list&btnType=1";
        document.forms[0].submit();
    }
</script>
<title><bean:message key="PROJECT_NAME"/></title>
       <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="35" class="nawzjj">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
          <td class="ndwz">&nbsp;</td>
          <td class="ndwzzml"><bean:message key="PROJECT_NAME"/>
          <img src="<%= request.getContextPath() %>/images/index11.jpg" width="6" height="10" hspace="5" />
        角色管理
	</tr></table>
	</td>
	</tr>
</table>
<html:form action="/Principal.do?method=list">
<html:hidden property="pageCount" />
  <div>&nbsp;&nbsp; &nbsp;&nbsp; 角色名:<html:text property="rolename" styleClass="text111"  size="18" maxlength="10" />
  <span class="tdstyle">
	  <input name="button" type="button" class="botton01"
	         onclick="search();" value="查 询" />
  </span>                      
  <span class="tdstyle">
      <input name="button2" type="button" class="botton01"
	         onclick="resets();" value="重 置" />
  </span>
  &nbsp;&nbsp; &nbsp;&nbsp; 创建角色:  
          <input id="permName" class="text111" type="text" />&nbsp;
		<span class="tdstyle"><input type="button" value="新 建" class="botton01"
title="Create principal" onclick="javascript:createPrincipal();" /></span></div><br>
	<html:hidden styleId="oldPrincipalName" title="oldPrincipalName"
		property="oldPrincipalName" value="" />
	<html:hidden styleId="principalName" title="principalName"
		property="principalName" value="" />
	<html:hidden styleId="principalAsc" title="principalAsc"
		property="principalAsc" value="" />
	<html:hidden styleId="principalDesc" title="principalDesc"
		property="principalDesc" value="" />
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td class="tablestyle">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td>
            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableline01">
              <tr class="tabletext01">
                <td width="80%">角色名</td>
                <td colspan="2">操作</td>
              </tr>
              <c:forEach var="principal" items="${generalForm.map.principals}"
						varStatus="statusPrincipal">
						<tr>
							<!-- <td id="<c:out value="${principal.localName}" />">
								<a FFFF="javascript:readPrincipal('<c:out value="${principal.localName}" />');"><c:out
										value="${principal.localName}" /> </a>
							</td> -->
							<td  id="<c:out value="${principal.localName}" />" >
								<c:out
										value="${principal.localName}" />
							</td>
							<td align="center">
		<input type="button" value="修 改" title="update principal" class="botton01"
					onclick="javascript:readCheckedPrincipal('<c:out value="${principal.localName}" />');"/>
					<input type="button" value="删 除" title="delete principal" class="botton01"
					onclick="javascript:deletePrincipal('<c:out value="${principal.localName}" />');"/>
							</td>
						</tr>
					</c:forEach>
        </table></td>
        </tr>
        <tr> 
		    <td colspan="3" class='left' align="right">
		        <sweet:page formName="generalForm" hrefClass="left"/>
		    </td>
		</tr>
    </table></td>
  </tr>
</table></td></tr></table>
	
</html:form>