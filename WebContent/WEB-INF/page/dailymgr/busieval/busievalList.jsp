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
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F1F8FC;
}
</style>
<title><bean:message key="PROJECT_NAME" />
</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/manus.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="<%=basePath%>css/css2.css" type="text/css"></link>
<link rel="stylesheet"
	href="<%=basePath%>ext-3.2.0/resources/css/ext-all.css" type="text/css"></link>
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
function cleanStampName(){
	    $("#name").attr('value','');
	}

 ellipsis : function(value, len, word){   
            if(value && value.length > len){   
                if(word){   
                    var vs = value.substr(0, len - 2);   
                    var index = Math.max(vs.lastIndexOf(' '), vs.lastIndexOf('.'), vs.lastIndexOf('!'), vs.lastIndexOf('?'));   
                    if(index == -1 || index < (len - 15)){   
                        return value.substr(0, len - 3) + "......";   
                    }else{   
                        return vs.substr(0, index) + "......";   
                    }   
                } else{   
                    return value.substr(0, len - 3) + "......";   
                }   
            }   
            return value;   
        }

	function search(){
        document.forms[0].action="./DailyMgrAction.do?method=toBusievalList";
        document.forms[0].submit();
    }
    
    function resets(){	
		document.getElementById("evalorgnm").value="";
		document.getElementById("evalorgno").value="";
		document.getElementById("evaledorgnm").value="";
		document.getElementById("evaledorgno").value="";
		document.getElementById("evalOrg").value="";
		document.getElementById("evalOrgChoice").value="";
		document.getElementById("evaledOrg").value="";
		document.getElementById("evaledOrgChoice").value="";
	}
	
	function toCreateBusievalPage(){
		document.getElementById("evalorgnm").value="";
		document.getElementById("evalorgno").value="";
		document.getElementById("evaledorgnm").value="";
		document.getElementById("evaledorgno").value="";
		document.getElementById("evalOrg").value="";
		document.getElementById("evalOrgChoice").value="";
		document.getElementById("evaledOrg").value="";
		document.getElementById("evaledOrgChoice").value="";
		document.forms[0].action="./DailyMgrAction.do?method=toCreateBusievalPage";
        document.forms[0].submit();
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
</script>
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
						业务评价信息查询
				</tr>
			</table>
		</td>
	</tr>
</table>
<html:form action="/DailyMgrAction.do?method=toBusievalList">
	<html:hidden property="pageCount" />
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="tablestyle">
							<table width="100%" border="0" cellpadding="0" cellspacing="0">
								<tr>
									<td>
										<table width="100%" border="0" cellspacing="0" cellpadding="0">
											<tr>
												<td>
													<table width="100%" border="0" align="left" cellpadding="0"
														cellspacing="0">
														<tr>
															<td align="left">
																评价机构
																<html:text property="evalOrgChoice"
																	styleClass="text11155"></html:text>
																<input type="button" value="选择" class="botton01"
																	onclick="getEvalOrgTree('evalOrg')" />
																<html:hidden property="evalOrg" styleId="evalOrg" />
																<html:hidden property="evalOrgChoice"></html:hidden>
																<html:hidden property="evalorgno"></html:hidden>
																<html:hidden property="evalorgnm"></html:hidden>
															</td>
															<td align="left">
																被评价机构
																<html:text property="evaledOrgChoice"
																	styleClass="text11155"></html:text>
																<input type="button" value="选择" class="botton01"
																	onclick="getEvaledOrgTree('evaledOrg')" />
																<html:hidden property="evaledOrg" styleId="evaledOrg" />
																<html:hidden property="evaledOrgChoice"></html:hidden>
																<html:hidden property="evaledorgno"></html:hidden>
																<html:hidden property="evaledorgnm"></html:hidden>
															</td>
															<td align="right">
																<input name="button" type="button" class="botton01"
																	onclick="search();" value="查 询" />
																<input name="button" type="button" class="botton01"
																	onclick="toCreateBusievalPage();" value="新 增" />
																<input name="button2" type="button" class="botton01"
																	onclick="resets();" value="重 置" />
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="tableline01">
											<tr class="tabletext01">
												<td width="5%" align="center">
													序号
												</td>
												<td width="30%" align="center">
													评价机构名称
												</td>
												<td width="30%" align="center">
													被评价机构名称
												</td>
												<td width="10%" align="center">
													评价年份
												</td>
												<td width="10%" align="center">
													评价所属期
												</td>
												<td width="15%" align="center">
													操 作
												</td>
											</tr>
											<logic:empty name="list">
												<tr>
													<td colspan="6" align="center">
														无查询数据......
													</td>
												</tr>
											</logic:empty>
											<logic:iterate id="item" name="list"  indexId="indexnum">
												<tr>
													<td align="center">
														<c:out value="${(indexnum + 1) + (pageNo - 1) * 10}"></c:out>
													</td>
													<td align="center">
														<bean:write name="item" property="evalorgnm" />
													</td>
													<td align="center">
														<bean:write name="item" property="evaledorgnm" />
													</td>
													<td align="center">
														<bean:write name="item" property="evalYear" />
													</td>
													<td align="center">
														<bean:write name="item" property="evalDuring" />
													</td>
													<td align="center">
														<a class="botton01"
															href="DailyMgrAction.do?method=toBusievalDetailPage&id=<bean:write name="item" property="id"/>">查
															看</a> &nbsp;&nbsp;
														<a class="botton01"
															href="DailyMgrAction.do?method=delBusieval&id=<bean:write name="item" property="id"/>">删
															除</a>
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="6" class='left' align="right">
										<sweet:page formName="dailymgrInfoForm" hrefClass="left" />
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
