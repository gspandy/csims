<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sweet" uri="/WEB-INF/tld/sweet-tag.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
       		var MyWindowUiPcbOrg="";
		   	function getPcbOrgTree(id){
				Ext.BLANK_IMAGE_URL = "/csims/ext-3.2.0/resources/images/default/tree/s.gif";
				var url="SystemBaseInfoManagerAction.do?method=pcbOrgTree";
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
										 	MyWindowUiPcbOrg.hide();
										 	var orgNo = document.getElementById("organization").value;
										 	getDeptByOrgNo(orgNo);
									 	  }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiPcbOrg == "" || typeof(MyWindowUiPcbOrg) != "object" ){
					MyWindowUiPcbOrg = new Ext.Window({
					    title: '人民银行机构',
					    id: totalId,
					    width: 350,
					    height: 450,
					    layout: 'column',
					    resizable:false, //变大小 
					    closeAction : 'hide' ,
					    items: tree,
					    listeners:{ 
					       "close":function(){ 
					          MyWindowUiPcbOrg.hide();
							} 
			            }
				    });   
					MyWindowUiPcbOrg.show();
	         }else{
	             MyWindowUiPcbOrg.show();
	         }
         }
         
        var openCallbackBuildDept = function (optionString){
	   		$("#dept").html(optionString);
		}
		
		function createEnOfficer(){
				var organization = document.getElementById("organization").value;
				var pepname = document.getElementById("pepname").value;
				var certno = document.getElementById("certno").value;
				var dept = document.getElementById("dept").value;
				var education = document.getElementById("education").value;
				var prcipal = document.getElementById("prcipal").value;
			if(organization == null || organization == ""){
					alert("请选择用户所属机构!");
					return false;
				}
			if(dept == null || dept == ""){
					alert("请选择用户所属部门!");
					return false;
				}
			if(pepname == null || pepname == ""){
					alert("请输入执法人姓名!");
					return false;
				}
			if(certno == null || certno == ""){
					alert("请输入执法人员执法证件号码!");
					return false;
				}
			if(education == null || education == ""){
					alert("请选择最高学历!");
					return false;
				}
			if(prcipal == null || prcipal == ""){
					alert("请选择职务!");
					return false;
				}
			if(confirm("操作将保存执法人员,确认?")){
				document.forms[0].action="EnforceOfficerManagerAction.do?method=createEnOfficer";
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
								执法人员管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								执法人员维护
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form
			action="/EnforceOfficerManagerAction.do?method=createEnOfficer"
			method="post">
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
																		执法人员新增
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
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>所属机构
																	</td>
																	<td align="left">
																		<html:text property="organizationChoice"
																			styleClass="text11155"></html:text>
																		<input type="button" value="选择" class="botton01"
																			onclick="getPcbOrgTree('organization')" />
																		<html:hidden property="organizationChoice"></html:hidden>
																		<html:hidden property="organization"></html:hidden>
																		<html:hidden property="id"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>所属部门
																	</td>
																	<td class="tdstylebotton">
																		<html:select property="dept" styleClass="text111"
																			title="部门" styleId="dept" style="width:300" onchange="javascript:getAepeopleSelect();">
																			<html:optionsCollection name="useDeptList" />
																			<html:hidden property="dept" styleId="dept" />
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>执法人员姓名
																	</td>
																	<td align="left">
																		<html:text property="pepname"  size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>性别
																	</td>
																	<td align="left">
																		<html:select property="sex">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="男">男</html:option>
																			<html:option value="女">女</html:option>
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>证件号码
																	</td>
																	<td align="left">
																		<html:text property="certno"  size="70%"
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
																		<font color='#FF0000'>*</font>职务
																	</td>
																	<td align="left">
																			<html:select property="prcipal">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="副行长">副行长</html:option>
																			<html:option value="副行长">处长</html:option>
																			<html:option value="副处长">副处长</html:option>
																			<html:option value="科长">科长</html:option>
																			<html:option value="副科长">副科长</html:option>
                                                                            <html:option value="副科长">股长</html:option>
                                                                            <html:option value="副股长">副股长</html:option>
                                                                            <html:option value="副主任科员">副主任科员</html:option>
                                                                            <html:option value="助理调研员">助理调研员</html:option>
																			<html:option value="其他">其他</html:option>
																		</html:select>
																	</td>
																	</tr>
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="保 存" class="botton01"
																			onclick="return createEnOfficer();" />
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