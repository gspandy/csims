<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sweet" uri="/WEB-INF/tld/sweet-tag.tld"%>
<%@ page
	import="java.util.Set,java.util.Iterator,net.sf.jguard.core.principals.RolePrincipal"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String loginUserType = (String) request.getAttribute("loginUserType");
%>
<html>
	<head>
		<style type="text/css">
</style>
		<title><bean:message key="PROJECT_NAME" />
		</title>
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
			
		function createUser(){
			var name = document.getElementById("name").value;
			var cardType = document.getElementById("cardType").value;
			var cardId = document.getElementById("cardId").value;
			var education = document.getElementById("education").value;
			var phone = document.getElementById("phone").value;
			var organization = document.getElementById("organization").value;
			var orgCodeOfZz = document.getElementById("orgCodeOfZz").value;
			var orgCodeOfXy = document.getElementById("orgCodeOfXy").value;
			var dept = document.getElementById("dept").value;
			var userType = document.getElementById("userType").value;
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
							if(!bool){
								alert("请输入正确的身份证号!");
								return false;
							}
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
			if(dept == null || dept == ""){
				alert("请选择监管系统用户所属部门!");
				return false;
			}
			if(confirm("操作将创建用户,确认?")){
				document.forms[0].action="User.do?method=createUserStart";
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
								创建用户
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/User.do?method=createUserStart" method="post"
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
														<td>
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td class="tiltlegerner">
																		用户基础信息
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td height="180" valign="top">
															<table width="100%" border="0" cellpadding="0"
																cellspacing="0" class="tableline01">
																<tr>
																	<td align="right" class="tabletext02" width="15%">
																		<font color='#FF0000'>*</font>监管系统用户姓名
																	</td>
																	<td>
																		<html:text property="name" size="70%"
																			styleClass="text11155"></html:text>
																		<html:hidden property="userPrincipalsNames"></html:hidden>
																		<html:hidden property="loginId"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>监管系统用户类型
																	</td>
																	<td align="left">
																		<html:select property="userType">
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
																	<td align="left">
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
																	<td align="left">
																		<html:text property="cardId" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>最高学历
																	</td>
																	<td align="left">
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
																	<td align="left">
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
																	<td align="left">
																		<html:text property="organizationChoice" size="100%" readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		监管系统用户所在机构代码
																	</td>
																	<td align="left">
																		<html:text property="organization" size="100%" readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		组织机构代码
																	</td>
																	<td align="left">
																		<html:text property="orgCodeOfZz" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		机构信用代码
																	</td>
																	<td align="left">
																		<html:text property="orgCodeOfXy" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td class="tabletext02">
																		<font color='#FF0000'>*</font>监管系统用户所在部门
																	</td>
																	<td class="tdstylebotton">
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
																	<td class="tdstylebotton">
																			<table>
																				<%
																					StringBuffer sb = new StringBuffer("");
																						List<?> allPrincipals = (List<?>) request
																								.getAttribute("allPrincipals");
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
																									.append("<input id='selectPrincipal' name='selectPrincipal' type='checkbox'  value='");
																							sb.append(principal.getName());
																							sb.append("'/></td>");
																							sb.append("<td align='left' width='80%'>");
																							sb.append(principal.getLocalName());
																							sb.append("</td>");
																							sb.append("</tr>");
																						}
																						out.write(sb.toString());
																				%>
																			</table>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		征信系统用户类型
																	</td>
																	<td class="tdstylebotton">
																		<table>
																			<tr>
																				<td align='left' width='10%' height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName1" />
																				企业征信管理员用户
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName2" />
																				&nbsp;企业征信查询用户
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName3" />
																					&nbsp;企业征信数据报送用户
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName4" />
																					&nbsp;企业征信异议处理用户
																				</td>
																			</tr>
																			<tr>
																				<td align='left' width='10%'  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName5" />
																					&nbsp;个人征信管理员用户
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName6" />
																					&nbsp;个人征信查询用户
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName7" />
																					&nbsp;个人征信数据报送用户
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName8" />
																					&nbsp;个人征信异议处理用户
																				</td>
																			</tr>
																			<tr>
																				<td  height="25">
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName9"/>
																					&nbsp;其他
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="完成" class="botton01"
																			onclick="return createUser();" />
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