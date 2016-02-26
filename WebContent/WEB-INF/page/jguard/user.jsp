<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<script type="text/javascript">
	function getBankTree(){
	var para = "<%=request.getContextPath()%>/AccountManager.do?method=getbrhTreeInfo";
    openShowModelWindow(para,window,350,410);
    }
    
	function getBank(id,brhName){
		    document.getElementById('branch').value = id;
		    document.getElementById('brhName').value = brhName;
		}
	
	function save(){
		var nametry = /^([a-z]|[A-Z]|[0-9]|[\u0391-\uFFE5])+$/ ;
		
		var prinNames = document.getElementById('userPrincipals');
		var strUserPrincipals="";
		//alert('login is :'+document.getElementById("login").value);
		//alert('password is :'+document.getElementById("password").value);
		//alert('nickname is :'+document.getElementById("nickname").value);
		if(document.getElementById("login").value==""){
			alert("请输入用户名!");
			document.getElementById("login").focus();
			return false;
		}
		if(document.getElementById("password").value==""){
			alert("请输入密码名!");
			document.getElementById("password").focus();
			return false;
		}
		if(document.getElementById("nickname").value==""){
			alert("请输入昵称!");
			document.getElementById("nickname").focus();
			return false;
		}
		if(!nametry.test(document.getElementById("login").value)){
		alert("Login name has be compose with A~Z or a~z letter!");
		return false;
		}
		for(var r=1;r<prinNames.options.length;r++){
		    strUserPrincipals += prinNames.options[r].value+"-";
		}
        //alert('xxxxxx');
		document.forms[0].userPrincipalsNames.value=strUserPrincipals;
		document.forms[0].submit();
	}
	
	function addPrincipal(var1, var2){
		moveOptions(var1, var2);
	}

	function removePrincipal(var1, var2){
		moveOptions(var1, var2);
	}

	function addOption(theSel, theText, theValue){
		var newOpt = new Option(theText, theValue);
		var selLength = theSel.length;
		theSel.options[selLength] = newOpt;
	}

	function deleteOption(theSel, theIndex){
		var selLength = theSel.length;
		if(selLength>0)	{
			theSel.options[theIndex] = null;
		}
	}

	function moveOptions(theSelFrom, theSelTo){
		var selLength = theSelFrom.length;
		selectedText = new Array();
		selectedValues = new Array();
		var selectedCount = 0;

		var i;
	
		// Find the selected Options in reverse order
		// and delete them from the 'from' Select.
		for(i=selLength-1; i>=0; i--)	{
			if(theSelFrom.options[i].selected){
				selectedText[selectedCount] = theSelFrom.options[i].text;
				selectedValues[selectedCount] = theSelFrom.options[i].value;
				deleteOption(theSelFrom, i);
				selectedCount++;
			}
		}
	
		// Add the selected text/values in reverse order.
		// This will add the Options to the 'to' Select
		// in the same order as they were in the 'from' Select.
		for(i=selectedCount-1; i>=0; i--){
			addOption(theSelTo, selectedText[i], selectedValues[i]);
		}
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
         <bean:message key="common.navigation.narrow"/>
          <img src="<%= request.getContextPath() %>/images/index11.jpg" width="6" height="10" hspace="5" />
           User Setting</td>
	</tr></table>
	</td>
	</tr>
</table>
<html:form action="/User.do">
	<link href="css/base.css" rel="stylesheet" type="text/css" />
	<script src="<%=request.getContextPath()%>/js/common.js" language="javascript1.2"></script>
	<input type="hidden" name="method" value="<c:out value="${action}"/>">
	<div id="main">
		
		<div id="content">
			<div class="query">
				<ul>
					<li>
						<label>
							Login Name:
						</label>
						<html:text property="login" value="${generalForm.map.login}" />
						<html:hidden property="oldLogin"
							value="${generalForm.map.oldLogin}" />
						<html:hidden property="userPrincipalsNames" value="" />
					</li>
					<li>
						<label>
							Password:
						</label>
						<html:password property="password"
							value="${generalForm.map.password}" name="password"/>
					</li>
					<li>
						<label>
							NickName:
						</label>
						<html:text property="nickname" value="${generalForm.map.nickname}"
							name="realname" />
					</li>
				</ul>
			</div>
			<div id="slt">
				<select id="userPrincipals" multiple="multiple" size="10"
					class="selwt">
					<option value="----" disabled="disabled">
						- - - - - - used principal - - - - - -
					</option>
					<c:forEach var="userPrincipal"
						items="${generalForm.map.principals}" varStatus="statusPrincipal">
						<option value="<c:out value="${userPrincipal.name}"/>">
							<c:out value="${userPrincipal.name}" />
						</option>
					</c:forEach>
				</select>

				<select id="principals" multiple="multiple" size="10" class="selwt">
					<option value="----" disabled="disabled">
						- - - -  validate principal - - - -
					</option>
					<c:forEach var="principal" items="${allPrincipals}"
						varStatus="statusPrincipal">
						<option value="<c:out value="${principal.name}"/>">
							<c:out value="${principal.name}" />
						</option>
					</c:forEach>
				</select>
			</div>

			<div class="wt1">
				<input type="button" value=""
					onclick="javascript:addPrincipal(this.form.principals, this.form.userPrincipals);"
					class="buttong" />
				<input type="button" value=""
					onclick="javascript:removePrincipal(this.form.userPrincipals, this.form.principals);"
					class="buttonf" />
			</div>
			<br>
			<br>
			<br>
			<br>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<div class="butwt1">
				<input type="button" value="Save" onclick="javascript:save();"
					class="button" />
				<input type="button" value="Return" class="button"
					onclick="history.go(-1)" />
			</div>
		</div>
	</div>
</html:form>
