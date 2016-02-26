<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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
			String.prototype.trim = function(){
		   		return this.replace(/(^\s+)|\s+$/g,"");
			} 
       	var MyWindowUiEvalOrg="";
		   function getEvalOrgTree(id){
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
											document.getElementById("evalorgno").value= node.id;
											document.getElementById("evalorgnm").value= node.text;
										 	MyWindowUiEvalOrg.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiEvalOrg == "" || typeof(MyWindowUiEvalOrg) != "object" ){
					MyWindowUiEvalOrg = new Ext.Window({
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
					          MyWindowUiEvalOrg.hide();
							} 
			            }
				    });   
					MyWindowUiEvalOrg.show();
	         }else{
	             MyWindowUiEvalOrg.show();
	         }
          }
          
          var MyWindowUiEvaledOrg="";
		   function getEvaledOrgTree(id){
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
											document.getElementById("evaledorgno").value= node.id;
											document.getElementById("evaledorgnm").value= node.text;
										 	MyWindowUiEvaledOrg.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiEvaledOrg == "" || typeof(MyWindowUiEvaledOrg) != "object" ){
					MyWindowUiEvaledOrg = new Ext.Window({
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
					          MyWindowUiEvaledOrg.hide();
							} 
			            }
				    });   
					MyWindowUiEvaledOrg.show();
	         }else{
	             MyWindowUiEvaledOrg.show();
	         }
          }
          
	function createBusieval(){
		if(confirm("操作将新增业务评价信息,确认?")){
			document.forms[0].action="DailyMgrAction.do?method=createBusieval";
			document.forms[0].submit();
		}
	}
	
	function changTR1(){
	  	var evalCondition = document.getElementById("evalCondition").value;
		if(evalCondition=="基本情况"){
			document.getElementById('TR1').style.display='block';
		}else{
			document.getElementById('TR1').style.display='none';
		}
		if(evalCondition=="存在问题"){
			document.getElementById('TR2').style.display='block';
		}else{
			document.getElementById('TR2').style.display='none';
		}
	}
	
	function changTR2(){
		var evalCondition = document.getElementById("evalCondition").value;
		if(evalCondition=="" || evalCondition.length==0){
			alert("请选择平价情况!");
			return false;
		}
	  	var evalInfo = document.getElementById("evalInfo").value;
		if(evalCondition=="存在问题"){
			if(evalInfo=="综合管理" || evalInfo=="系统建设" || evalInfo=="征信管理" || evalInfo=="征信宣传与维权" || evalInfo=="信用体系建设"){
				document.getElementById('TR3').style.display='block';
			}else{
				document.getElementById('TR3').style.display='none';
			}
		}else{
			document.getElementById('TR3').style.display='none';
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
								日常管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								业务评价信息录入
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/DailyMgrAction.do?method=createBusieval"
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
																		业务评价信息录入
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
																		评价机构
																	</td>
																	<td align="left">
																		<html:text property="evalorgnm" readonly="true"
																			size="100%"></html:text>
																		<html:hidden property="evalorgno"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>被评价机构
																	</td>
																	<td align="left">
																		<html:text property="evaledOrgChoice"
																			styleClass="text11155"></html:text>
																		<input type="button" value="选择" class="botton01"
																			onclick="getEvaledOrgTree('evaledOrg')" />
																		<html:hidden property="evaledOrg" styleId="evaledOrg" />
																		<html:hidden property="evaledOrgChoice"></html:hidden>
																		<html:hidden property="evaledorgno"></html:hidden>
																		<html:hidden property="evaledorgnm"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>评价所属年
																	</td>
																	<td>
																		<html:text property="evalYear" maxlength="20"
																			size="15" styleClass="text111"
																			onkeydown="if(event.keyCode==13){search();}"
																			onclick="WdatePicker({dateFmt:'yyyy'});" />
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>评价所属期
																	</td>
																	<td align="left">
																		<html:select property="evalDuring">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="上半年">上半年</html:option>
																			<html:option value="全年">全年</html:option>
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>评价情况
																	</td>
																	<td align="left">
																		<html:select property="evalCondition" onchange="changTR1();">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="基本情况">基本情况</html:option>
																			<html:option value="存在问题">存在问题</html:option>
																			<html:option value="加分项目">加分项目</html:option>
																		</html:select>
																	</td>
																</tr>
																
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>评价信息
																	</td>
																	<td align="left">
																		<html:select property="evalInfo" onchange="changTR2();">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="综合管理">综合管理</html:option>
																			<html:option value="系统建设">系统建设</html:option>
																			<html:option value="征信管理">征信管理</html:option>
																			<html:option value="征信宣传与维权">征信宣传与维权</html:option>
																			<html:option value="信用体系建设">信用体系建设</html:option>
																			<html:option value="加分项目">加分项目</html:option>
																		</html:select>
																	</td>
																</tr>
																<tr id='TR1' style="display: none;">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>基本分
																	</td>
																	<td>
																		<html:text property="baseScore" size="20%"
																			styleClass="text111"></html:text>
																	</td>
																</tr>
																<tr id='TR2' style="display: none;">
																	<td align="right" class="tabletext02" >
																		<font color='#FF0000'>*</font>扣分
																	</td>
																	<td>
																		<html:text property="deScore" size="20%"
																			styleClass="text111"></html:text>
																	</td>
																</tr>
																<tr id='TR3' style="display: none;">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>评价细则
																	</td>
																	<td>
																		<html:textarea property="evalRule" cols="140%"
																			rows="5"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>评价内容
																	</td>
																	<td>
																		<html:textarea property="evalContent" cols="140%"
																			rows="5"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="保 存" class="botton01"
																			onclick="return createBusieval();" />
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