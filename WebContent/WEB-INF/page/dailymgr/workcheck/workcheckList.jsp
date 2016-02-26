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
        document.forms[0].action="./DailyMgrAction.do?method=toWorkcheckList";
        document.forms[0].submit();
    }
    
    function resets(){	
		document.getElementById("chkorgnm").value="";
		document.getElementById("chkorgno").value="";
		document.getElementById("chkedorgnm").value="";
		document.getElementById("chkedorgno").value="";
		document.getElementById("chkOrg").value="";
		document.getElementById("chkOrgChoice").value="";
		document.getElementById("chkedOrg").value="";
		document.getElementById("chkedOrgChoice").value="";
	}
	
	function toCreateWorkcheckPage(){	
		document.getElementById("chkorgnm").value="";
		document.getElementById("chkorgno").value="";
		document.getElementById("chkedorgnm").value="";
		document.getElementById("chkedorgno").value="";
		document.getElementById("chkOrg").value="";
		document.getElementById("chkOrgChoice").value="";
		document.getElementById("chkedOrg").value="";
		document.getElementById("chkedOrgChoice").value="";
		document.forms[0].action="./DailyMgrAction.do?method=toCreateWorkcheckPage";
        document.forms[0].submit();
	}
	
	
	var MyWindowUiChkOrg="";
		   function getChkOrgTree(id){
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
											document.getElementById("chkorgno").value= node.id;
											document.getElementById("chkorgnm").value= node.text;
										 	MyWindowUiChkOrg.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiChkOrg == "" || typeof(MyWindowUiChkOrg) != "object" ){
					MyWindowUiChkOrg = new Ext.Window({
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
					          MyWindowUiChkOrg.hide();
							} 
			            }
				    });   
					MyWindowUiChkOrg.show();
	         }else{
	             MyWindowUiChkOrg.show();
	         }
          }
          
          var MyWindowUiChkedOrg="";
		   function getChkedOrgTree(id){
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
											document.getElementById("chkedorgno").value= node.id;
											document.getElementById("chkedorgnm").value= node.text;
										 	MyWindowUiChkedOrg.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiChkedOrg == "" || typeof(MyWindowUiChkedOrg) != "object" ){
					MyWindowUiChkedOrg = new Ext.Window({
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
					          MyWindowUiChkedOrg.hide();
							} 
			            }
				    });   
					MyWindowUiChkedOrg.show();
	         }else{
	             MyWindowUiChkedOrg.show();
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
						工作检查信息查询
				</tr>
			</table>
		</td>
	</tr>
</table>
<html:form action="/DailyMgrAction.do?method=toWorkcheckList">
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
																检查机构
																<html:text property="chkOrgChoice"
																	styleClass="text11155"></html:text>
																<input type="button" value="选择" class="botton01"
																	onclick="getChkOrgTree('chkOrg')" />
																<html:hidden property="chkOrg" styleId="chkOrg" />
																<html:hidden property="chkOrgChoice"></html:hidden>
																<html:hidden property="chkorgno"></html:hidden>
																<html:hidden property="chkorgnm"></html:hidden>
															</td>
															<td align="left">
																被检查机构
																<html:text property="chkedOrgChoice"
																	styleClass="text11155"></html:text>
																<input type="button" value="选择" class="botton01"
																	onclick="getChkedOrgTree('chkedOrg')" />
																<html:hidden property="chkedOrg" styleId="chkedOrg" />
																<html:hidden property="chkedOrgChoice"></html:hidden>
																<html:hidden property="chkedorgno"></html:hidden>
																<html:hidden property="chkedorgnm"></html:hidden>
															</td>
															<td align="right">
																<input name="button" type="button" class="botton01"
																	onclick="search();" value="查 询" />
																<input name="button" type="button" class="botton01"
																	onclick="toCreateWorkcheckPage();" value="新 增" />
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
												<td width="10%" align="center">
													序号
												</td>
												<td width="35%" align="center">
													检查机构名称
												</td>
												<td width="35%" align="center">
													被检查机构名称
												</td>
												<td width="20%" align="center">
													操 作
												</td>
											</tr>
											<logic:empty name="list">
												<tr>
													<td colspan="4" align="center">
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
														<bean:write name="item" property="chkorgnm" />
													</td>
													<td align="center">
														<bean:write name="item" property="chkedorgnm" />
													</td>
													<td align="center">
														<a class="botton01"
															href="DailyMgrAction.do?method=toWorkcheckDetailPage&id=<bean:write name="item" property="id"/>">查
															看</a> &nbsp;&nbsp;
														<a class="botton01"
															href="DailyMgrAction.do?method=delWorkcheck&id=<bean:write name="item" property="id"/>">删
															除</a>
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4" class='left' align="right">
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
