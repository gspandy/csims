<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="java.util.Set,java.util.List,java.util.Iterator,net.sf.jguard.core.principals.RolePrincipal"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
<script type="text/javascript" src="js/windowopen.js"></script>
<script type="text/javascript" src="js/organization.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<link rel="stylesheet"
		href="<%=basePath%>ext-3.2.0/resources/css/ext-all.css"
		type="text/css"></link>
<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
<script type="text/javascript">	
	function save(){ 
		var nametry = /^([a-z]|[A-Z]|[0-9])+$/ ;
		var prinNames = document.getElementById('userPrincipals');
		var strUserPrincipals="";
		if(document.getElementById("login").value==""){
			alert("请输入登录名!");
			document.getElementById("login").focus();
			return false;
		}
		if(document.getElementById("password").value==""){
			alert("请输入密码！");
			document.getElementById("password").focus();
			return false;
		}
		if(document.getElementById("password").value.length<=4){
			alert("密码的长度必须是5位以上,请重新填写!");
			return false;
		}
		if(document.getElementById("nickname").value==""){
			alert("请输入昵称！");
			document.getElementById("nickname").focus();
			return false;
		}
		if(document.getElementById("organizationChoice").value==""){
			alert("请选择机构！");
			document.getElementById("organizationChoice").focus();
			return false;
		}
		var chkbs = document.getElementsByName("selectPrincipal");  
		var chkNum = 0;   
		for(i=0;i<chkbs.length;i++){if(chkbs(i).checked)chkNum++;}
		if(chkNum==0){alert("请为用户选择角色!");return false;}
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
	
	function CheckEmail(item) {
    var etext
    var elen
    var i
    var aa
    etext=item
    elen=etext.length
    if (elen<5)
      return 0;
    i= etext.indexOf("@",0)
    if (i==0 || i==-1 || i==elen-1)
       return 0;
    else {
      if (etext.indexOf("@",i+1)!=-1)
	return 0;
    }
    if (etext.indexOf("..",i+1)!=-1)
	return 0;
    i=etext.indexOf(".",0)
    if (i==0 || i==-1 || etext.charAt(elen-1)=='.')
	return 0;
    if ( etext.charAt(0)=='-' ||  etext.charAt(elen-1)=='-')
	return 0;
    if ( etext.charAt(0)=='_' ||  etext.charAt(elen-1)=='_')
	return 0;
    for (i=0;i<=elen-1;i++) {
      aa=etext.charAt(i)
      if (!((aa=='.') || (aa=='@') || (aa=='-') ||(aa=='_') || (aa>='0' && aa<='9') || (aa>='a' && aa<='z') || (aa>='A' && aa<='Z')))
	return 0;
    }
    return 1;
}

</script>
<script type="text/javascript">	
String.prototype.trim = function()
		{
		   return this.replace(/(^\s+)|\s+$/g,"");
		} 
        var MyWindowUiOrgType="";
		   function getOrgTypeTree(id){
				Ext.BLANK_IMAGE_URL = "/csims/ext-3.2.0/resources/images/default/tree/s.gif";
				var url="SystemBaseInfoManagerAction.do?method=orgTree";
			    var config={
			   			//指定当前的元素渲染的层Id
			            autoScroll : true,
			            height:420,
						width:340,
			             //指定当前树的根节点id
			            root:new Ext.tree.AsyncTreeNode({
			            url : url,      
                                  	requestMethod : 'POST',  
									id:'0'
						}),
					    listeners:{
									"dblclick":function(node,event){
									   if(confirm("是否选择:"+node.text)){
									   		var ChoiceId = id+'Choice';
									 		document.getElementById(ChoiceId).value= node.text;
											document.getElementById(id).value= node.id;
										 	MyWindowUiOrgType.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiOrgType == "" || typeof(MyWindowUiOrgType) != "object" ){
					MyWindowUiOrgType = new Ext.Window({
					    title: '机构',
					    id: totalId,
					    width: 350,
					    height: 450,
					    layout: 'column',
					    resizable:false, //变大小 
					    closeAction : 'hide' ,
					    items: tree,
					    listeners:{ 
					       "close":function(){ 
					          MyWindowUiOrgType.hide();
							} 
			            }
				    });   
					MyWindowUiOrgType.show();
	         }else{
	             MyWindowUiOrgType.show();
	         }
          }
</script>
<title><bean:message key="PROJECT_NAME" />
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
						添加用户
				</tr>
			</table>
		</td>
	</tr>
</table>
<html:form action="/User.do">
	<script src="<%=request.getContextPath()%>/js/common.js"
		language="javascript1.2"></script>
	<input type="hidden" name="method" value="<c:out value="${action}"/>">
	<div id="main">
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
														添加用户
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
													<td class="tabletext02">
														<font color='#FF0000'>*</font>登录名
													</td>
													<td class="tdstylebotton">
														<html:text name="generalForm" property="login"
															styleClass="text11155" />
														<html:hidden name="generalForm" property="oldLogin" />
														<html:hidden property="userPrincipalsNames" value="" />
													</td>

												</tr>
												<tr>
													<td class="tabletext02">
														<font color='#FF0000'>*</font>密 码
													</td>
													<td class="tdstylebotton">
														<html:password name="generalForm" property="password"
															styleClass="text11155" />
													</td>
												</tr>
												<tr>
													<td class="tabletext02">
														<font color='#FF0000'>*</font>用户姓名
													</td>
													<td class="tdstylebotton">
														<html:text name="generalForm" property="nickname"
															styleClass="text11155" />
													</td>
												</tr>
												<tr>
													<td class="tabletext02">
														<font color='#FF0000'>*</font>机 构
													</td>
													<td align="left">
														<html:text property="organizationChoice" styleClass="text11155"></html:text>
														<input type="button" value="选择" class="botton01"
																onclick="getOrgTypeTree('organization')" />
														<html:hidden property="organization"
																styleId="organization" name="generalForm" />
														<html:hidden property="organizationChoice"></html:hidden>
													</td>
												</tr>
												<tr>
													<td class="tabletext02">
														Email
													</td>
													<td class="tdstylebotton">
														<html:text name="generalForm" property="email"
															styleClass="text11155" />
													</td>
												</tr>

												<tr>
													<td class="tabletext02">
														<font color='#FF0000'>*</font>
														角色权限设置
													</td>
													<td class="tdstylebotton">
														<div id="slt">
															<table>
																<tr>
																	<td>
																		<input type="checkbox" name="checkboxAll" value=""
																			onclick="if(this.checked==true) { checkAll('selectPrincipal'); } else { clearAll('selectPrincipal'); }" />
																	</td>
																	<td>
																		<b>全选:</b>
																	</td>
																</tr>

																<%
																	StringBuffer sb = new StringBuffer("");
																		Set<?> allPrincipals = (Set<?>) request
																				.getAttribute("allPrincipals");
																		List<?> principals = (List<?>) request
																				.getAttribute("principals");
																		for (Iterator<?> it = allPrincipals.iterator(); it.hasNext();) {
																			RolePrincipal principal = (RolePrincipal) it.next();
																			if (principal.getLocalName().trim().equalsIgnoreCase(
																					"guest")
																					|| principal.getLocalName().trim()
																							.equalsIgnoreCase("other")) {
																				continue;
																			}
																			sb.append("<tr>");
																			sb.append("<td align='left' width='3%'>");
																			sb
																					.append("<input id='selectPrincipal' name='selectPrincipal' type='checkbox' value='");
																			sb.append(principal.getName());
																			sb.append("' ");
																			if (principals != null && principals.size() > 0) {
																				for (int i = 0; i < principals.size(); i++) {
																					RolePrincipal principalTemp = (RolePrincipal) principals
																							.get(i);
																					if (principalTemp.getName().equals(
																							principal.getName())) {
																						sb.append(" checked='checked'");
																						break;
																					}
																				}
																			}
																			sb.append("/></td>");
																			sb.append("<td align='left' width='80%'>");
																			sb.append(principal.getLocalName());
																			sb.append("</td>");
																			sb.append("</tr>");
																		}
																		out.write(sb.toString());
																%>
															</table>
														</div>
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
														<div class="butwt1">
															<input type="button" value="保 存"
																onclick="javascript:save();" class="botton01" />
															<input type="button" value="返 回" class="botton01"
																onclick="history.go(-1)" />
														</div>
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
	</div>
</html:form>
