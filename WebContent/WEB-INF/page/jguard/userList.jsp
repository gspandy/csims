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

	function createUser(){
		document.getElementsByName("name")[0].value="";
		document.getElementsByName("loginId")[0].value="";
		document.getElementsByName("organization")[0].value="";
		document.getElementsByName("organizationChoice")[0].value="";
		document.forms[0].action="./User.do?method=createUserPage";
		document.forms[0].submit();
	}
	
	function deleteUser(id){
        if(confirm("操作将删除选中用户,确认?")){
			document.forms[0].action = "./User.do?method=deleteUser&id="+id;
			document.forms[0].submit();
		}	
    }
    
    function updateUserPage(id){
		document.forms[0].action = "./User.do?method=updateUserPage&id="+id;
		document.forms[0].submit();
    }
    
    function readUser(id){
		document.forms[0].action = "./User.do?method=readUser&id="+id;
		document.forms[0].submit();
    }
	
	function search(){
        document.forms[0].action="./User.do?method=userList";
        document.forms[0].submit();
    }
    
    function resets(){	
		document.getElementsByName("name")[0].value="";
		document.getElementsByName("loginId")[0].value="";
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
				</tr>
			</table>
		</td>
	</tr>
</table>
<html:form action="/User.do?method=userList">
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
															<td>
																用户姓名
																<html:text property="name" styleClass="text111"
																	size="15" maxlength="15" />
															</td>
															<td>
																身份识别码
																<html:text property="loginId" styleClass="text111"
																	size="30" maxlength="30" />
															</td>
															<td>
																用户角色
																<html:select property="userRole" styleClass="text111"
																	title="用户角色" styleId="userRole" style="width:150">
																	<html:optionsCollection name="principalList" />
																	<html:hidden property="userRole" styleId="userRole" />
																</html:select>
															</td>
														</tr>
														<tr>
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
															<td>
																监管系统用户类型
																<html:select property="userType">
																	<html:option value="">---请选择---</html:option>
																	<html:option value="高级管理员">高级管理员</html:option>
																	<html:option value="一般管理员">一般管理员</html:option>
																	<html:option value="内控监督员">内控监督员</html:option>
																	<html:option value="普通用户">普通用户</html:option>
																	<html:option value="其他">其他</html:option>
																</html:select>
															</td>
															<td align="right">
																<input name="button" type="button" class="botton01"
																	onclick="search();" value="查 询" />
																<input name="button" type="button" class="botton01"
																	onclick="resets();" value="重 置" />
																<input type="button" value="创 建" class="botton01"
																	onclick="javascript:createUser();" />
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
												<td width="10%" align="center">
													用户姓名
												</td>
												<td width="20%" align="center">
													身份识别码
												</td>
												<td width="30%" align="center">
													所在机构
												</td>
												<td width="15%" align="center">
													监管系统用户类型
												</td>
												<td width="5%" align="center">
													用户状态
												</td>
												<td width="15%" align="center">
													操 作
												</td>
											</tr>
											<logic:empty name="userList">
												<tr>
													<td colspan="7" align="center">
														无查询数据......
													</td>
												</tr>
											</logic:empty>
											<logic:iterate id="item" name="userList" indexId="indexnum">
												<tr>
													<td align="center">
														<c:out value="${(indexnum + 1) + (pageNo - 1) * 10}"></c:out>
													</td>
													<td align="center">
														<bean:write name="item" property="name" />
													</td>
													<td align="center">
														<bean:write name="item" property="loginId" />
													</td>
													<td align="center">
														<bean:write name="item" property="orgName" />
													</td>
													<td align="center">
														<bean:write name="item" property="userType" />
													</td>
													<td align="center">
														<bean:write name="item" property="userStatus" />
													</td>
													<td align="center">
														<input type="button" value="修改" class="botton01" onclick="updateUserPage('<bean:write name="item" property="id"/>');" />
														&nbsp;&nbsp;&nbsp;
														<input type="button" value="查看" class="botton01" onclick="readUser('<bean:write name="item" property="id"/>');" />
														&nbsp;&nbsp;&nbsp;
														<input type="button" value="删除" class="botton01" onclick="deleteUser('<bean:write name="item" property="id"/>');" />
														&nbsp;&nbsp;&nbsp;
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="6" class='left' align="right">
										<sweet:page formName="generalForm" hrefClass="left" />
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
