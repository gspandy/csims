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
	function search(){
        document.forms[0].action="./User.do?method=userReportListOfTotal";
        document.forms[0].submit();
    }
    
    function resets(){
		document.getElementsByName("begin")[0].value="";
		document.getElementsByName("end")[0].value="";
		document.getElementsByName("organization")[0].value="";
		document.getElementsByName("organizationChoice")[0].value="";
		document.getElementsByName("reportType")[0].value="";
	}
	
	function exportExcel(){
		document.forms[0].action="./User.do?method=exportExcelOfTotal";
        document.forms[0].submit();
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
						用户备案信息查询(汇总)
				</tr>
			</table>
		</td>
	</tr>
</table>
<html:form action="/User.do?method=userReportListOfTotal">
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
															<td>
																起始时间
																<html:text property="begin" maxlength="20" size="15"
																	styleClass="text111"
																	onkeydown="if(event.keyCode==13){search();}"
																	onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
															</td>
															<td>
																截至时间
																<html:text property="end" maxlength="20" size="15"
																	styleClass="text111"
																	onkeydown="if(event.keyCode==13){search();}"
																	onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
															</td>
															<td>
																报备事项
																<html:select property="reportType">
																	<html:option value="">---请选择---</html:option>
																	<html:option value="创建用户">创建用户</html:option>
																	<html:option value="禁用用户">禁用用户</html:option>
																	<html:option value="重启用户">重启用户</html:option>
																</html:select>
															</td>
															<td>
																所在机构
																<html:text property="organizationChoice"
																	styleClass="text11155"></html:text>
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
																	onclick="resets();" value="重 置" />
																<input name="button" type="button" class="botton01"
																	onclick="exportExcel();" value="导 出" />
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
												<td align="center">
													序号
												</td>
												<td align="center">
													金融机构编码
												</td>
												<td align="center">
													机构信用代码
												</td>
												<td align="center">
													机构名称
												</td>
												<td align="center">
													所在部门
												</td>
												<td align="center">
													所在省
												</td>
												<td align="center">
													所在城市
												</td>
												<td align="center">
													报备事项
												</td>
												<td align="center">
													征信系统名称
												</td>
												<td align="center">
													征信系统用户类型
												</td>
												<td align="center">
													用户名
												</td>
												<td align="center">
													用户姓名
												</td>
												<td align="center">
													性别
												</td>
												<td align="center">
													证件号码
												</td>
												<td align="center">
													学历
												</td>
												<td align="center">
													工作电话
												</td>
												<td align="center">
													报备时间
												</td>
											</tr>
											<logic:empty name="userList">
												<tr>
													<td colspan="17" align="center">
														无查询数据......
													</td>
												</tr>
											</logic:empty>
											<logic:iterate id="item" name="userList" indexId="indexnum">
												<tr>
													<td align="center">
														<c:out value="${(indexnum + 1)}"></c:out>
													</td>
													<td align="center">
														<bean:write name="item" property="orgCodeOfJr" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="orgCodeOfXy" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="orgName" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="deptName" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="p" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="c" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="reportType" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="zxXtName" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="zxXtUserType" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="userZxName" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="name" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="six" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="loginId" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="education" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="phone" />&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="reportDate" />&nbsp;
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
</html:form>
