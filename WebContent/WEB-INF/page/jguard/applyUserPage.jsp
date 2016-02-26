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
	String flag = (String) request.getAttribute("flag");
	String zxStr1 = (String) request.getAttribute("zxStr1");
	String zxStr2 = (String) request.getAttribute("zxStr2");
	String zxStr3=  (String) request.getAttribute("zxStr3");
	String zxStr4 = (String) request.getAttribute("zxStr4");
	String zxStr5 = (String) request.getAttribute("zxStr5");
	String zxStr6 = (String) request.getAttribute("zxStr6");
	String zxStr7 = (String) request.getAttribute("zxStr7");
	String zxStr8 = (String) request.getAttribute("zxStr8");
	String zxStr9 = (String) request.getAttribute("zxStr9");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
</style>
		<title><bean:message key="PROJECT_NAME" /></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="template report">
		<link href="css/css.css" rel="stylesheet" type="text/css" />
		<link href="css/manus.css" rel="stylesheet" type="text/css" />
		<link href="css/home.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>css/css2.css"
			type="text/css"></link>
		<link rel="stylesheet"
			href="<%=basePath%>ext-3.2.0/resources/css/ext-all.css"
			type="text/css"></link>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/home.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/home03.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/manu.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/WdatePicker.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/idCard.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/zxPrincipal.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/interface/SelectSystemDataBase.js'></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/engine.js'></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/util.js'></script>
		<script type="text/javascript" src="js/organization.js"></script>
		<link href="css/menu.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/windowopen.js"></script>
		<script type="text/javascript">	
			String.prototype.trim = function(){
		   		return this.replace(/(^\s+)|\s+$/g,"");
			} 
       		var MyWindowUiOrg="";
		   	function getOrgTree(id){
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
										 	MyWindowUiOrg.hide();
										 	var orgNo = document.getElementById("organization").value;
										 	getOrgInfo(orgNo);
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiOrg == "" || typeof(MyWindowUiOrg) != "object" ){
					MyWindowUiOrg = new Ext.Window({
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
					          MyWindowUiOrg.hide();
							} 
			            }
				    });   
					MyWindowUiOrg.show();
	         }else{
	             MyWindowUiOrg.show();
	         }
         }
         
         var openCallbackBuildDept = function (optionString){
	   		 $("#dept").html(optionString);
		}
		function getOrgInfo(orgNo){
			SelectSystemDataBase.getOrgInfo(orgNo,setOrgInfo);
		}
		function setOrgInfo(data){
			var orgNo = data[0];
			var orgCodeOfZz = data[1];
			var orgCodeOfXy = data[2];
			document.getElementById("orgCodeOfZz").value= orgCodeOfZz;
			document.getElementById("orgCodeOfXy").value= orgCodeOfXy;
			getDeptByOrgNo(orgNo);
		}
         
		</script>
		<script type="text/javascript">
			function changTR(){
		  		var userType = document.getElementById("userType").value;
				if(userType=="内控监督员"){
					document.getElementById('TR1').style.display='block';
				}else{
					document.getElementById('TR1').style.display='none';
				}
			}
			
			function changZXName(obj){
				var idName = obj.value +"-1";
				if(obj.checked){
					document.getElementById(idName).style.display='block';
				}else{
					document.getElementById(idName).style.display='none';
					document.getElementById(obj.value).value='';
				}
			}
			
			function applyUser(){
				var name = document.getElementById("name").value;
				var cardType = document.getElementById("cardType").value;
				var cardId = document.getElementById("cardId").value;
				var education = document.getElementById("education").value;
				alert(education);
				var phone = document.getElementById("phone").value;
				alert(phone);
				var organization = document.getElementById("organization").value;
				alert(organization);
				var orgCodeOfZz = document.getElementById("orgCodeOfZz").value;
				alert(orgCodeOfZz);
				var orgCodeOfXy = document.getElementById("orgCodeOfXy").value;
				alert(orgCodeOfXy);
				var dept = document.getElementById("dept").value;
				alert(dept);
				var userType = document.getElementById("userType").value;
				alert(userType);
				
				var strUserPrincipals="";
				if(name == null || name == ""){
					alert("请输入用户姓名!");
					return false;
				}
				if(userType == null || userType == ""){
					alert("请选择监管系统用户类型!");
					return false;
				}else{
					if(cardType == null || cardType == ""){
							alert("请选择证件类型!");
							return false;
						}else{
							if(cardId == null || cardId == ""){
								alert("请输入证件号!");
								return false;
							}else{
								if(cardType == "身份证"){
									var bool = IdCardValidate(cardId);
								}else{
									var bool = IdCardValidateOfPassport(cardId);
									if(!bool){
										alert("请输入正确的护照号!");
										return false;
									}
								}
							}
						document.getElementById("loginId").value = cardId;
						if(education == null || education == ""){
							alert("请选择用户最高学历!");
							return false;
						}
						if(phone == null || phone == ""){
							alert("请输入用户电话!");
							return false;
						}
					}
				}
				var chkbs = document.getElementsByName("selectPrincipal");  
				var chkNum = 0;
				for(i=0;i<chkbs.length;i++){
					if(chkbs(i).checked){
						chkNum++;
						strUserPrincipals = strUserPrincipals + chkbs(i).value +"-";
					}
				}
				document.getElementById("userPrincipalsNames").value = strUserPrincipals;
				if(chkNum==0){
					alert("请选择监管系统用户角色!");
					return false;
				}
				if(organization == null || organization == ""){
					alert("请选择监管系统用户所属机构!");
					return false;
				}
				if(dept == null || dept == ""){
					alert("请选择监管系统用户所属部门!");
					return false;
				}
				if(confirm("操作将修改待创建用户信息,确认?")){
					document.forms[0].action="User.do?method=applyUser";
					document.forms[0].submit();
				}
			}
	</script>
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
								用户管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								申请用户
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/User.do?method=applyUser" method="post"
			enctype="multipart/form-data">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td class="tablestyle">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td height="180" valign="top">
															<table width="100%" border="0" cellpadding="0"
																cellspacing="0" class="tableline01">
																<tr>
																	<td align="right" class="tabletext02" width="15%">
																		<font color='#FF0000'>*</font>监管系统用户姓名
																	</td>
																	<td align="left" colspan="2">
																		<html:text property="name" size="70%"
																			styleClass="text11155"></html:text>
																		<html:hidden property="userPrincipalsNames"></html:hidden>
																		<html:hidden property="loginId" styleId="loginId" />
																		<html:hidden property="oldLoginId" styleId="oldLoginId" />
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>监管系统用户类型
																	</td>
																	<td align="left" colspan="2">
																		<html:select property="userType" onchange="changeTR();">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="一般管理员">一般管理员</html:option>
																			<html:option value="普通用户">普通用户</html:option>
																			<html:option value="其他">其他</html:option>
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>证件类型
																	</td>
																	<td align="left" colspan="2">
																		<html:select property="cardType">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="身份证">身份证</html:option>
																			<html:option value="护照">护照</html:option>
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>证件号码
																	</td>
																	<td align="left" colspan="2">
																		<html:text property="cardId" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>最高学历
																	</td>
																	<td align="left" colspan="2">
																		<html:select property="education">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="研究生及以上">研究生及以上</html:option>
																			<html:option value="大学本科">大学本科</html:option>
																			<html:option value="专科">专科</html:option>
																			<html:option value="高中及以下">高中及以下</html:option>
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>联系电话
																	</td>
																	<td align="left" colspan="2">
																		<html:text property="phone" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		用户使用者身份
																	</td>
																	<td align="left">
																		<html:text property="userCard" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>监管系统用户所在机构名称
																	</td>
																	<td align="left" colspan="2">
																		<html:text property="organizationChoice"
																			styleClass="text11155"></html:text>
																		<input type="button" value="选择" class="botton01"
																			onclick="getOrgTree('organization')" />
																		<html:hidden property="organizationChoice"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		监管系统用户所在机构代码
																	</td>
																	<td align="left" colspan="2">
																		<html:text property="organization" size="70%"
																			readonly="true" styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>组织机构代码
																	</td>
																	<td align="left" colspan="2">
																		<html:text property="orgCodeOfZz" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>机构信用代码
																	</td>
																	<td align="left" colspan="2">
																		<html:text property="orgCodeOfXy" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td class="tabletext02">
																		<font color='#FF0000'>*</font>监管系统用户所在部门
																	</td>
																	<td class="tdstylebotton" colspan="2">
																		<html:select property="dept" styleClass="text111"
																			title="部门" styleId="dept" style="width:300">
																			<html:optionsCollection name="useDeptList" />
																			<html:hidden property="dept" styleId="dept" />
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>监管系统用户角色
																	</td>
																	<td class="tdstylebotton" colspan="2">
																		<div id="slt">
																			<table>
																				<%
																					StringBuffer sb = new StringBuffer("");
																						List<?> allPrincipals = (List<?>) request
																								.getAttribute("allPrincipals");
																						List<?> principals = (List<?>) request
																								.getAttribute("principals");
																						for (Iterator<?> it = allPrincipals.iterator(); it.hasNext();) {
																							RolePrincipal principal = (RolePrincipal) it.next();
																							if (principal.getLocalName().trim().equalsIgnoreCase(
																									"admin")
																									|| principal.getLocalName().trim()
																											.equalsIgnoreCase("systemadmin")
																									|| principal.getLocalName().trim()
																											.equalsIgnoreCase("guest")) {
																								continue;
																							}
																							sb.append("<tr>");
																			sb.append("<td align='left' width='3%'>");
																			sb
																					.append("<input id='selectPrincipal' name='selectPrincipal' type='checkbox' onclick='return changTR(this);' value='");
																			sb.append(principal.getName());
																			sb.append("' ");
																			if (principals != null && principals.size() > 0) {
																				for (int i = 0; i < principals.size(); i++) {
																					if (principals.get(i).equals(
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
																<tr>
																	<td align="right" class="tabletext02">
																		征信系统用户类型
																	</td>
																	<td class="tdstylebotton" colspan="2">
																		<table>
																			<tr>
																				<%=zxStr1 %>
																			</tr>
																			<tr>
																				<%=zxStr2 %>
																			</tr>
																			<tr>
																				<%=zxStr3 %>
																			</tr>
																			<tr>
																				<%=zxStr4 %>
																			</tr>
																			<tr>
																				<%=zxStr5 %>
																			</tr>
																			<tr>
																				<%=zxStr6 %>
																			</tr>
																			<tr>
																				<%=zxStr7 %>
																			</tr>
																			<tr>
																				<%=zxStr8 %>
																			</tr>
																			<tr>
																				<%=zxStr9 %>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td colspan="3" align="center">
																		<input type="button" value="完成" class="botton01"
																			onclick="return applyUser();" />
																		<input type="button" value="返回" class="botton01"
																			onclick="javascript:history.back()" />
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
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>