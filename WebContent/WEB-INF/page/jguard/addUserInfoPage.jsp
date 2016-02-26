<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String trStr = (String) request.getAttribute("trStr");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
			function disabledUserPlural(pluralId){
				if(confirm("操作将禁用用户兼任,确认?")){
					document.forms[0].action="User.do?method=disabledUserPlural&pluralId="+pluralId;;
					document.forms[0].submit();
				}
			}
			
			function enabledUserPlural(pluralId){
				if(confirm("操作将启用用户兼任,确认?")){
					document.forms[0].action="User.do?method=enabledUserPlural&pluralId="+pluralId;;
					document.forms[0].submit();
				}
			}
		
			function addUser(){
				var zxPrincipal = document.getElementsByName("zxPrincipal");
				var organization = document.getElementById("organization").value;
				var orgCodeOfZz = document.getElementById("orgCodeOfZz").value;
				var orgCodeOfXy = document.getElementById("orgCodeOfXy").value;
				var dept = document.getElementById("dept").value;
				var objarray=zxPrincipal.length;
				var principal="";
				for (i=0;i<objarray;i++){
					if(zxPrincipal[i].checked){
						principal+=zxPrincipal[i].value+",";
						var zxPrincipalValue = zxPrincipal[i].value;
						var zxPrincipalName = document.getElementById(zxPrincipalValue).value;
						if(zxPrincipalName==""){
							alert("请输入征信系统对应用户名!");
							return false;
						}
					}
				}
				var principals = principal.split(",");
				if(principals.length==0){
					alert("请选择征信系统用户类型!");
					return false;
				}
				var m=0;
				var n=0;
				for(var i=0;i<principals.length-1;i++){
					var pr = principals[i];
					if(pr=="zxName1" || pr=="zxName2" || pr=="zxName3" || pr=="zxName4"){
						m+=1;
					}
				}
				for(var i=0;i<principals.length-1;i++){
					var pr = principals[i];
					if(pr=="zxName5" || pr=="zxName6" || pr=="zxName7" || pr=="zxName8"){
						n+=1;
					}
				}
				if(m>3){
					alert("拟创建用户最多拥有三种企业征信系统用户类型");
					return false;
				}
				if(n>3){
					alert("拟创建用户最多拥有三种个人征信系统用户类型");
					return false;
				}
				if(organization == null || organization == ""){
					alert("请选择用户所属机构!");
					return false;
				}
				if(dept == null || dept == ""){
					alert("请选择用户所属部门!");
					return false;
				}
				if(confirm("操作将为用户添加兼任信息,确认?")){
					document.forms[0].action="User.do?method=addUser";
					document.forms[0].submit();
				}
			}
			
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
		
		function changZXName(obj){
				var idName = obj.value +"-1";
				if(obj.checked){
					document.getElementById(idName).style.display='block';
				}else{
					document.getElementById(idName).style.display='none';
					document.getElementById(obj.value).value='';
				}
			}
			
		function myLoad(){
			document.getElementById('zxName1-1').style.display='none';
			document.getElementById('zxName2-1').style.display='none';
			document.getElementById('zxName3-1').style.display='none';
			document.getElementById('zxName4-1').style.display='none';
			document.getElementById('zxName5-1').style.display='none';
			document.getElementById('zxName6-1').style.display='none';
			document.getElementById('zxName7-1').style.display='none';
			document.getElementById('zxName8-1').style.display='none';
			document.getElementById('zxName9-1').style.display='none';
		}
	</script>
	<body onload="myLoad()">
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
								添加用户
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/User.do?method=addUser" method="post">
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
																<%=trStr%>
																<tr>
																	<td align="right" class="tabletext02"  width="20%">
																		<font color='#FF0000'>*</font>兼任机构名称
																	</td>
																	<td align="left">
																		<html:text property="organizationChoice"
																			styleClass="text11155"></html:text>
																		<input type="button" value="选择" class="botton01"
																			onclick="getOrgTree('organization')" />
																		<html:hidden property="organizationChoice"></html:hidden>
																		<html:hidden property="loginId"></html:hidden>
																		<html:hidden property="id"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		兼任机构代码
																	</td>
																	<td align="left">
																		<html:text property="organization" styleId="organization" 
																		size="70%" readonly="true" styleClass="text11155"></html:text>
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
																		<font color='#FF0000'>*</font>兼任部门
																	</td>
																	<td align="left">
																		<html:select property="dept" styleClass="text111"
																			title="部门" styleId="dept" style="width:300">
																			<html:optionsCollection name="useDeptList" />
																			<html:hidden property="dept" styleId="dept" />
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>征信系统用户类型
																	</td>
																	<td class="tdstylebotton">
																		<table>
																			<tr>
																				<td align='left' width='50%'>
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName1"
																						onclick='return changZXName(this);' />
																				企业征信管理员用户
																				</td>
																				<td id="zxName1-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName1"
																						styleClass="text1112323232"></html:text>
																				</td>
																			</tr>
																			<tr>
																				<td>
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName2"
																						onclick='return changZXName(this);' />
																				&nbsp;企业征信查询用户
																				</td>
																				<td id="zxName2-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName2"
																						styleClass="text1112323232"></html:text>
																				</td>
																			</tr>
																			<tr>
																				<td>
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName3"
																						onclick='return changZXName(this);' />
																					&nbsp;企业征信数据报送用户
																				</td>
																				<td id="zxName3-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName3"
																						styleClass="text1112323232"></html:text>
																				</td>
																			</tr>
																			<tr>
																				<td>
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName4"
																						onclick='return changZXName(this);' />
																					&nbsp;企业征信异议处理用户
																				</td>
																				<td id="zxName4-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName4"
																						styleClass="text1112323232"></html:text>
																				</td>
																			</tr>
																			<tr>
																				<td align='left' width='10%'>
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName5"
																						onclick='return changZXName(this);' />
																					&nbsp;个人征信管理员用户
																				</td>
																				<td id="zxName5-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName5"
																						styleClass="text1112323232"></html:text>
																				</td>
																			</tr>
																			<tr>
																				<td>
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName6"
																						onclick='return changZXName(this);' />
																					&nbsp;个人征信查询用户
																				</td>
																				<td id="zxName6-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName6"
																						styleClass="text1112323232"></html:text>
																				</td>
																			</tr>
																			<tr>
																				<td>
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName7"
																						onclick='return changZXName(this);' />
																					&nbsp;个人征信数据报送用户
																				</td>
																				<td id="zxName7-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName7"
																						styleClass="text1112323232"></html:text>
																				</td>
																			</tr>
																			<tr>
																				<td>
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName8"
																						onclick='return changZXName(this);' />
																					&nbsp;个人征信异议处理用户
																				</td>
																				<td id="zxName8-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName8"
																						styleClass="text1112323232"></html:text>
																				</td>
																			</tr>
																			<tr>
																				<td>
																					<input type="checkbox" name="zxPrincipal"
																						value="zxName9"
																						onclick='return changZXName(this);' />
																					&nbsp;其他
																				</td>
																				<td id="zxName9-1">
																					<font color='#FF0000'>*</font>征信系统用户名
																					<html:text property="zxName9"
																						styleClass="text1112323232"></html:text>
																				</td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="完成" class="botton01"
																			onclick="return addUser();" />
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