<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String status = (String) request.getAttribute("status");
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
<link href="css/menu.css" rel="stylesheet" type="text/css" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/manus.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/css2.css" type="text/css"></link>
<link rel="stylesheet" href="<%=basePath%>ext-3.2.0/resources/css/ext-all.css" type="text/css"></link>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/home.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/home03.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/manu.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/WdatePicker.js" defer="defer"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/idCard.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/zxPrincipal.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
<script type="text/javascript" src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
<script type="text/javascript" src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/interface/SelectSystemDataBase.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/engine.js'></script>
<script type='text/javascript' src='<%=request.getContextPath()%>/dwr/util.js'></script>
<script type="text/javascript" src="js/organization.js"></script>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/windowopen.js"></script>
<script type="text/javascript">	
	String.prototype.trim = function(){
		return this.replace(/(^\s+)|\s+$/g,"");
	} 
          
	function createQuestionaire(){
		var qtitle = document.getElementById("qtitle").value;
		var qenddatetime = document.getElementById("qenddatetime").value;
		if(document.getElementsByName("nextArea")[0].checked){
			document.getElementById("checkNextArea").value = "true";
		}else{
			document.getElementById("checkNextArea").value = "false";
		}
		if(document.getElementsByName("nextOrg")[0].checked){
			document.getElementById("checkNextOrg").value = "true";
		}else{
			document.getElementById("checkNextOrg").value = "false";
		}
		if(qtitle.length <=0){
			alert("请输入问卷调查标题!");
			return false;
		}
		if(qenddatetime.length <=0){
			alert("请输入问卷调查结束时间!");
			return false;
		}
		if(confirm("操作将保存问卷调查信息,确认?")){
			document.forms[0].action="QuestionAction.do?method=createQuestionaire";
			document.forms[0].submit();
		}
		
		
	}
	
	function delBsSurveyobject(id){
		document.getElementById("sid").value=id;
		if(confirm("操作将删除问卷调查参与机构,确认?")){
			document.forms[0].action="QuestionAction.do?method=delBsSurveyobject";
			document.forms[0].submit();
		}
	}
	
	function myLoad(status){
		if(status=="0"){
			document.getElementById('INPUT1').disabled=false;
		}else{
			document.getElementById('INPUT1').disabled=true;
		}
	}
</script>
	</head>
	<body onload="myLoad(<%=status%>)">
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
								问卷调查
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								问卷调查管理
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/QuestionAction.do?method=createQuestionaire"
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
																		问卷调查信息录入
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
																		标题
																	</td>
																	<td align="left">
																		<html:textarea property="qtitle" cols="140%" rows="5"></html:textarea>
																		<html:hidden property="qid" />
																		<html:hidden property="sid" />
																		<html:hidden property="status" />
																		<html:hidden property="checkNextArea" />
																		<html:hidden property="checkNextOrg" />
																		&nbsp;
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		结束时间
																	</td>
																	<td>
																		<html:text property="qenddatetime" maxlength="30"
																			size="30" styleClass="text111"
																			onkeydown="if(event.keyCode==13){search();}"
																			onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'});" />
																		&nbsp;
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02"  rowspan="2">
																		问卷调查参与地区
																	</td>
																	<td>
																		<input type="button" value="地区选择" class="botton01" onclick="getAreaTree('area')" />
																		<html:hidden property="area" styleId="area" />
																	</td>
																	</tr>
																	<tr>
																	<td align="left">
																		<html:textarea property="areaChoice" cols="140%" rows="5" readonly="true"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02"  rowspan="2">
																		问卷调查参与机构
																	</td>
																	<td>
																		<input type="button" value="机构选择" class="botton01" onclick="getOrgTree('org')" />
																		<html:hidden property="org" styleId="org" />
																	</td>
																	</tr>
																	<tr>
																	<td align="left">
																		<html:textarea property="orgChoice" cols="140%" rows="5" readonly="true"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		备注
																	</td>
																	<td>
																		<html:textarea property="qsumry" cols="140%" rows="5"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="保 存" id="INPUT1"
																			class="botton01"
																			onclick="return createQuestionaire();" />
																		<input type="button" value="返 回" class="botton01"
																			onclick="javascript:history.back()" />
																	</td>
																</tr>

															</table>
														</td>
													</tr>
													<tr>
														<td>
															&nbsp;
														</td>
													</tr>
													<tr>
														<td>
															<table id="table1" width="100%" border="0"
																cellpadding="0" cellspacing="0" class="tableline01">
																<tr>
																	<td colspan="3" align="center">
																		问卷调查参与机构
																	</td>
																</tr>
																<tr class="tabletext01">
																	<td width="5%" align="center">
																		序号
																	</td>
																	<td width="80%" align="center">
																		机构名称
																	</td>
																	<td width="15%" align="center">
																		操作
																	</td>
																</tr>
																<logic:empty name="list">
																	<tr>
																		<td colspan="3" align="center">
																			无参与调查机构数据......
																		</td>
																	</tr>
																</logic:empty>
																<logic:iterate id="item" name="list" indexId="indexnum">
																	<tr>
																		<td align="center">
																			<c:out value="${(indexnum + 1)}"></c:out>
																			&nbsp;
																		</td>
																		<td align="left">
																			<bean:write name="item" property="soqorgname" />
																			&nbsp;
																		</td>
																		<td align="center">
																			<logic:equal name="item"
																				property="bsQuestionaire.status" value="0">
																				<input name="button2" type="button" class="botton01"
																					onclick="delBsSurveyobject('<bean:write name="item" property="id"/>');"
																					value="删除机构" />
																			</logic:equal>
																			<logic:equal name="item"
																				property="bsQuestionaire.status" value="1">
																				<input name="button2" type="button" class="botton01"
																					disabled
																					onclick="delBsSurveyobject('<bean:write name="item" property="id"/>');"
																					value="删除机构" />
																			</logic:equal>
																			<logic:equal name="item"
																				property="bsQuestionaire.status" value="2">
																				<input name="button2" type="button" class="botton01"
																					disabled
																					onclick="delBsSurveyobject('<bean:write name="item" property="id"/>');"
																					value="删除机构" />
																			</logic:equal>
																		</td>
																	</tr>
																</logic:iterate>
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
	<script type="text/javascript">
		var MyWindowUiArea="";
		var tree = null;
		var checkedAreaId = new Array();
		var checkedAreaName = new Array();
		function getAreaTree(id){
			Ext.BLANK_IMAGE_URL = "/csims/ext-3.2.0/resources/images/default/tree/s.gif";
			var url="SystemBaseInfoManagerAction.do?method=areaTreeQ";
			var config={
				//指定当前的元素渲染的层Id
				autoScroll : true,
				height:420,
				width:340,
				//指定当前树的根节点id
				root:new Ext.tree.AsyncTreeNode({
					url : url,      
					requestMethod : 'POST',  
					id:'0',
					isCheck:'YES'
				})
			};
			tree = new TreePanelFil(config,url,null)
      		if(MyWindowUiArea == "" || typeof(MyWindowUiArea) != "object" ){
				MyWindowUiArea = new Ext.Window({
					id: id+'window',
					width: 350,
					height: 450,
					layout: 'column',
					resizable:false, //变大小 
					closeAction : 'hide',
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
			tree.on('checkChange',function(node,checked){
				node.checked = checked;
				if(node.checked){
					if(jQuery.inArray(node.id, checkedAreaId)==-1){
						checkedAreaId.push(node.id);
						checkedAreaName.push(node.text);
					}
				}
				if(!node.checked){
					if(jQuery.inArray(node.id, checkedAreaId)>-1){
						checkedAreaId.splice(jQuery.inArray(node.id, checkedAreaId), 1);
						checkedAreaName.splice(jQuery.inArray(node.text, checkedAreaName), 1);
					}
				}
				if(node.isExpanded()){
					node.eachChild(function(child) {
						child.ui.toggleCheck(checked);    
						child.attributes.checked = checked;    
						child.fireEvent('checkchange', child, checked);
					})
				}
				document.getElementById("areaChoice").value=checkedAreaName;
				document.getElementById("area").value=checkedAreaId;
 			})
		}
		
		var MyWindowUiOrg="";
		var tree = null;
		var checkedOrgId = new Array();
		var checkedOrgName = new Array();
		function getOrgTree(id){
			Ext.BLANK_IMAGE_URL = "/csims/ext-3.2.0/resources/images/default/tree/s.gif";
			var url="SystemBaseInfoManagerAction.do?method=orgTreeQ";
			var config={
				//指定当前的元素渲染的层Id
				autoScroll : true,
				height:420,
				width:340,
				//指定当前树的根节点id
				root:new Ext.tree.AsyncTreeNode({
					url : url,      
					requestMethod : 'POST',  
					id:'0',
					isCheck:'YES'
				})
			};
			tree = new TreePanelFil(config,url,null)
      		if(MyWindowUiOrg == "" || typeof(MyWindowUiOrg) != "object" ){
				MyWindowUiOrg = new Ext.Window({
					id: id+'window',
					width: 350,
					height: 450,
					layout: 'column',
					resizable:false, //变大小 
					closeAction : 'hide',
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
			tree.on('checkChange',function(node,checked){
				node.checked = checked;
				if(node.checked){
					if(jQuery.inArray(node.id, checkedOrgId)==-1){
						checkedOrgId.push(node.id);
						checkedOrgName.push(node.text);
					}
				}
				if(!node.checked){
					if(jQuery.inArray(node.id, checkedOrgId)>-1){
						checkedOrgId.splice(jQuery.inArray(node.id, checkedOrgId), 1);
						checkedOrgName.splice(jQuery.inArray(node.text, checkedOrgName), 1);
					}
				}
				if(node.isExpanded()){
					node.eachChild(function(child) {
						child.ui.toggleCheck(checked);    
						child.attributes.checked = checked;    
						child.fireEvent('checkchange', child, checked);
					})
				}
				document.getElementById("orgChoice").value=checkedOrgName;
				document.getElementById("org").value=checkedOrgId;
 			})
		}
	</script>
</html>