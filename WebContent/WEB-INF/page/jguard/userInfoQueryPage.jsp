<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sweet" uri="/WEB-INF/tld/sweet-tag.tld"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="js/windowopen.js"></script>
		<script type="text/javascript" src="js/organization.js"></script>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/WdatePicker.js" defer="defer"></script>
		<script type="text/javascript">	
		function changTR(){
	  		var queryWay = document.getElementById("queryWay").value;
			if(queryWay=="1"){
				document.getElementById('areaTR').style.display='block';
				document.getElementById('orgTR').style.display='none';
				document.getElementById('zxUserTypeTR').style.display='none';
				document.getElementById('principalTR').style.display='none';
			}else if(queryWay=="2"){
				document.getElementById('areaTR').style.display='none';
				document.getElementById('principalTR').style.display='none';
				document.getElementById('orgTR').style.display='block';
				document.getElementById('zxUserTypeTR').style.display='none';
			}else if(queryWay=="3"){
				document.getElementById('areaTR').style.display='none';
				document.getElementById('orgTR').style.display='none';
				document.getElementById('zxUserTypeTR').style.display='block';
				document.getElementById('principalTR').style.display='none';
			}else if(queryWay=="4"){
				document.getElementById('areaTR').style.display='none';
				document.getElementById('orgTR').style.display='none';
				document.getElementById('principalTR').style.display='block';
				document.getElementById('zxUserTypeTR').style.display='none';
			}
		}
		
		function userInfoQuery(){
			var queryWay = document.getElementById("queryWay").value;
			var areaChoice = document.getElementById("areaChoice").value;
			var organizationChoice = document.getElementById("organizationChoice").value;
			var zxUserType = document.getElementById("zxUserType").value;
			var userRole = document.getElementById("userRole").value;
			if(queryWay==""){
				alert("请选择查询方式!");
				return false;
			}
			if(areaChoice=="" && organizationChoice=="" && zxUserType=="" && userRole==""){
				alert("请选择查询条件!");
				return false;
			}
			document.forms[0].action="User.do?method=userInfoQuery";
			document.forms[0].submit();
		}
		
		
		String.prototype.trim = function()
		{
		   return this.replace(/(^\s+)|\s+$/g,"");
		} 
		var MyWindowUiArea="";
		   function getAreaTree(id){
		       Ext.BLANK_IMAGE_URL = "/csims/ext-3.2.0/resources/images/default/tree/s.gif";	
		       var url="SystemBaseInfoManagerAction.do?method=areaListCommonTree";
			    var config={
			            autoScroll : true,
			            height:400,
						width:290,
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
										 	MyWindowUiArea.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiArea == "" || typeof(MyWindowUiArea) != "object" ){
					MyWindowUiArea = new Ext.Window({
					    title: '地区',
					    id: totalId,
					    width: 300,
					    height: 430,
					    layout: 'column',
					    resizable:false, //变大小 
					    closeAction : 'hide' ,
					    items: tree,
					    listeners:{ 
					       "close":function(){ 
					          MyWindowUiArea.hide();
							} 
			            }
				    });   
					MyWindowUiArea.show();
	         }else{
	             MyWindowUiArea.show();
	         }
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
								系统用户查询
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/User.do?method=userInfoQuery" method="post">
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
																		查询条件设置
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
																	<td align="right" class="tabletext02" width="20%">
																		查询方式
																	</td>
																	<td align="left">
																		<html:select property="queryWay" onchange="changTR();">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="1">按地区查询</html:option>
																			<html:option value="2">按机构查询</html:option>
																			<html:option value="3">按征信系统用户类型查询</html:option>
																			<html:option value="4">按监管系统用户角色查询</html:option>
																		</html:select>
																	</td>
																</tr>
																<tr id="areaTR" style="display: none;">
																	<td align="right" class="tabletext02" width="20%">
																		地区
																	</td>
																	<td align="left">
																		<html:text property="areaChoice"
																			styleClass="text11155" readonly="true"></html:text>
																		<input type="button" value="选择" class="botton01"
																			onclick="getAreaTree('area')" />
																		<html:hidden property="areaChoice"></html:hidden>
																		<html:hidden property="area" />
																	</td>
																</tr>
																<tr id="orgTR" style="display: none;">
																	<td align="right" class="tabletext02" width="20%">
																		机构
																	</td>
																	<td align="left">
																		<html:text property="organizationChoice"
																			styleClass="text11155" readonly="true"></html:text>
																		<input type="button" value="选择" class="botton01"
																			onclick="getOrgTree('organization')" />
																		<html:hidden property="organizationChoice"></html:hidden>
																		<html:hidden property="organization" />
																	</td>
																</tr>
																<tr id="zxUserTypeTR" style="display: none;">
																	<td align="right" class="tabletext02" width="20%">
																		征信系统用户类型
																	</td>
																	<td align="left">
																		<html:select property="zxUserType">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="企业征信管理员用户">企业征信管理员用户</html:option>
																			<html:option value="企业征信查询用户">企业征信查询用户</html:option>
																			<html:option value="企业征信数据报送用户">企业征信数据报送用户</html:option>
																			<html:option value="企业征信异议处理用户">企业征信异议处理用户</html:option>
																			<html:option value="个人征信管理员用户">个人征信管理员用户</html:option>
																			<html:option value="个人征信查询用户">个人征信查询用户</html:option>
																			<html:option value="个人征信数据报送用户">个人征信数据报送用户</html:option>
																			<html:option value="个人征信异议处理用户">个人征信异议处理用户</html:option>
																			<html:option value="其他">其他</html:option>
																		</html:select>
																	</td>
																</tr>
																<tr id="principalTR" style="display: none;">
																	<td align="right" class="tabletext02" width="20%">
																		监管系统用户角色
																	</td>
																	<td align="left">
																		<html:select property="userRole" styleClass="text111"
																			styleId="userRole" style="width:150">
																			<html:optionsCollection name="principalList" />
																			<html:hidden property="userRole" styleId="userRole" />
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="查 询" class="botton01"
																			onclick="return userInfoQuery();" />
																		<input type="button" value="返 回" class="botton01"
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