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
											document.getElementById("tranedorgno").value= node.id;
											document.getElementById("tranedorgnm").value= node.text;
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
          
	function getUserCheckbox(){
		var orgNo = document.getElementById("org").value;
		if(orgNo==null || orgNo==""){
			alert("请选择机构!");
		 	return false;
		}
		SelectSystemDataBase.getUserCheckboxByOrgNo(orgNo,setUserCheckboxInfo);
	}
	
	function setUserCheckboxInfo(data){
		var trString = data;
		document.getElementById('TD').innerHTML=trString;
		document.getElementById('choiceUser').style.display='block';
		document.getElementById('bg').style.display='block';
	}
	
    function closeDiv(){
		document.getElementById('choiceUser').style.display='none';
		document.getElementById('bg').style.display='none';
	}
	
	function choiceUser(){
		var userCheckbox = document.getElementsByName("userCheckbox");
		var tranuser = document.getElementById("tranuser").value;
		var objarray = userCheckbox.length;
		for(i=0;i<objarray;i++){
			if(userCheckbox[i].checked){
				tranuser+=userCheckbox[i].value+",";
			}
		}
		document.getElementById("tranuser").value = tranuser;
		document.getElementById('choiceUser').style.display='none';
		document.getElementById('bg').style.display='none';
	}
	
	function createTraining(){
		if(confirm("操作将新增培训信息,确认?")){
			document.forms[0].action="DailyMgrAction.do?method=createTraining";
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
								日常管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								培训信息录入
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/DailyMgrAction.do?method=createTran" method="post">
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
																		培训信息录入
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
																		培训机构
																	</td>
																	<td align="left">
																		<html:text property="tranorgnm" readonly="true" size="100%"></html:text>
																		<html:hidden property="tranorgno"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>被培训机构
																	</td>
																	<td align="left">
																		<html:text property="orgChoice" styleClass="text11155"></html:text>
																		<input type="button" value="选择" class="botton01"
																			onclick="getOrgTree('org')" />
																		<html:hidden property="org" styleId="org" />
																		<html:hidden property="orgChoice"></html:hidden>
																		<html:hidden property="tranedorgno"></html:hidden>
																		<html:hidden property="tranedorgnm"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>培训人员
																	</td>
																	<td>
																		<html:textarea property="tranuser" cols="140%"
																			rows="5"></html:textarea>
																		<input type="button" value="选择" class="botton01"
																			onclick="getUserCheckbox()" />
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>培训日期
																	</td>
																	<td>
																		<html:text property="transtartdt" maxlength="20"
																			size="15" styleClass="text111"
																			onkeydown="if(event.keyCode==13){search();}"
																			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
																		&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
																		<html:text property="tranenddt" maxlength="20"
																			size="15" styleClass="text111"
																			onkeydown="if(event.keyCode==13){search();}"
																			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>培训时长
																	</td>
																	<td align="left">
																		<html:text property="hours" maxlength="3" size="3"
																			styleClass="text111"></html:text>
																		小时
																		<html:text property="minute" maxlength="3" size="3"
																			styleClass="text111"></html:text>
																		分钟

																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>培训事项
																	</td>
																	<td>
																		<html:textarea property="trancont" cols="140%"
																			rows="5"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="保 存" class="botton01"
																			onclick="return createTraining();" />
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
			<div id="choiceUser" class="mydiv" style="display: none;">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					class="tableline01">
					<tr>
					<td id="TD">
						&nbsp;
					</td>
					</tr>
					<tr>
						<td align="center">
							<input type="button" value="确 定" class="botton01" 
								onclick="return choiceUser();" />
							<input type="button" value="关 闭" class="botton01"
								onclick="return closeDiv();" />
						</td>
					</tr>
				</table>
			</div>
			<div id="bg" class="bg" style="display: none;"></div>
		</html:form>
	</body>
</html>