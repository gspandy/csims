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
<title><bean:message key="PROJECT_NAME" /></title>
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
        document.forms[0].action="./EnforceOfficerManagerAction.do?method=toEnOfficerList";
        document.forms[0].submit();
    }
    function toCreateEnOfficerPage(){
    	document.getElementById("pepname").value="";
		document.getElementById("certno").value="";
		document.forms[0].action="./EnforceOfficerManagerAction.do?method=toCreateEnOfficerPage";
		document.forms[0].submit();
    }
    function resets(){	
		document.getElementById("pepname").value="";
		document.getElementById("certno").value="";
		document.getElementsByName("organization")[0].value="";
		document.getElementsByName("organizationChoice")[0].value="";
	}
</script>
<script type="text/javascript">	
String.prototype.trim = function()
		{
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
						执法人员查询
				</tr>
			</table>
		</td>
	</tr>
</table>
<html:form
	action="/EnforceOfficerManagerAction.do?method=toEnOfficerList">
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
																&nbsp;&nbsp;&nbsp;&nbsp;姓名
																<html:text property="pepname" styleClass="text111"
																	size="40" maxlength="40" />
															</td>
															<td align="left">
																执法证件编号
																<html:text property="certno" styleClass="text111"
																	size="50" maxlength="50" />
															</td>
														</tr>
														<tr>
															<td>
																所在机构
																<html:text property="organizationChoice"
																	styleClass="text11155" readonly="true" ></html:text>
																<input type="button" value="选择" class="botton01"
																	onclick="getOrgTree('organization')" />
																<html:hidden property="organization"
																	styleId="organization" />
																<html:hidden property="organizationChoice"></html:hidden>
															</td>
															<td align="right">
																<input name="button" type="button" class="botton01"
																	onclick="search();" value="查 询" />
																<input name="button" type="button" class="botton01"
																	onclick="toCreateEnOfficerPage();" value="新 增" />
																<input name="button" type="button" class="botton01"
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
													执法证件编号
												</td>
												<td width="30%" align="center">
													执法人员名称
												</td>
												<td width="30%" align="center">
													所属机构名称
												</td>
												<td width="15%" align="center">
													所属部门名称
												</td>
												<td width="20%" align="center">
													操 作
												</td>
											</tr>
											<logic:empty name="list">
												<tr>
													<td colspan="5" align="center">
														无查询数据......
													</td>
												</tr>
											</logic:empty>
											<logic:iterate id="item" name="list">
												<tr>
													<td align="center">
														<bean:write name="item" property="certno" />
													</td>
													<td align="center">
														<bean:write name="item" property="pepname" />
													</td>
													<td align="center">
														<bean:write name="item" property="orgnm" />
													</td>
													<td align="center">
														<bean:write name="item" property="dptnm" />
													</td>
													<td align="center">
														<a class="botton01"
															href="EnforceOfficerManagerAction.do?method=toEnOfficerDetailPage&id=<bean:write name="item" property="id"/>">查
															看</a> &nbsp;&nbsp;
														<a class="botton01"
															href="EnforceOfficerManagerAction.do?method=toUpdateEnOfficerPage&id=<bean:write name="item" property="id"/>">修
															改</a> &nbsp;&nbsp;
														<a class="botton01"
															href="EnforceOfficerManagerAction.do?method=delEnOfficer&id=<bean:write name="item" property="id"/>">删
															除</a>
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="5" class='left' align="right">
										<sweet:page formName="enforceInfoForm" hrefClass="left" />
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
