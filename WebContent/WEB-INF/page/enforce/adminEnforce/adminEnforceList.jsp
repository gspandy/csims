<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="jguard" uri="/WEB-INF/tld/jguard.tld"%>
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
<script type="text/javascript"
    src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/WdatePicker.js" defer="defer"></script>
<script type="text/javascript">
Ext.MessageBox.minWidth = 400;
	function search(){
        document.forms[0].action="./AdminEnforceManagerAction.do?method=toAdminEnforceList&source=<c:out value='${source}'/>";
        document.forms[0].submit();
    }
    
    function resets(){	
		document.getElementById("aeno").value="";
		document.getElementById("aeorgChoice").value="";
		document.getElementById("aeplanstdate").value="";
		document.getElementById("aeplantmdate").value="";
	}
    
    function exportCvs(){
        document.forms[0].action="./AdminEnforceManagerAction.do?method=exportXls&obj=5";
        document.forms[0].submit();
    }
	
	function isDelete(id){	
		Ext.Msg.confirm('提示', '是否确认删除行政执法信息?' , function(btn) {
                    if(btn == 'yes') {
                        var msgbox = Ext.Msg.progress("提示","保存数据","请稍候....."); //显示等待对话框 
                        document.forms[0].action="AdminEnforceManagerAction.do?method=delAdminEnforce&id="+id;
                        document.forms[0].submit(); 
                    }
                });
	}
  
    function cutStr(len){ 
        var obj=document.getElementsByName('lengthLimit'); 
        for (i=0;i<obj.length;i++){ 
            obj[i].innerHTML=obj[i].innerHTML.substring(0,len)+'...'; 
        } 
    } 
</script>
</head>
<body onload="cutStr(10)">
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
						行政执法管理
						<img src="<%=request.getContextPath()%>/images/index11.jpg"
							width="6" height="10" hspace="5" />
						<c:if test="${source == 1}">
						行政执法立项查询
						</c:if>
						<c:if test="${source == 2}">
						检查记录登记
						</c:if>
						<c:if test="${source == 3}">
						检查结论登记
						</c:if>
						<c:if test="${source == 4}">
						整改信息登记
						</c:if>
						<c:if test="${source == 5}">
						行政处罚登记
						</c:if>
				</tr>
			</table>
		</td>
	</tr>
</table>
<html:form
	action="/AdminEnforceManagerAction.do?method=toAdminEnforceList">
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
																执法检查立项编号
																<html:text property="aeno" styleClass="text111"
																	size="30" maxlength="30" />
															</td>
															<td align="left">
																检查单位
																<html:text property="aeorgChoice" styleClass="text111"
																	size="30" maxlength="30" />
															</td>
														</tr>
														<tr>
															<td align="left">
																&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;被检查单位
																<html:text property="aeedorgChoice" styleClass="text111"
																	size="30" maxlength="30" />
															</td>
															<td align="left">
																创建时间
																<html:text property="aeplanstdate" maxlength="20"
																	size="15" styleClass="text111"
																	onkeydown="if(event.keyCode==13){search();}"
																	onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
																&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
																<html:text property="aeplantmdate" maxlength="20"
																	size="15" styleClass="text111"
																	onkeydown="if(event.keyCode==13){search();}"
																	onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
															</td>
															<td align="left">
																<input name="button" type="button" class="botton01"
																	onclick="search();" value="查 询" />
																<input name="button2" type="button" class="botton01"
																	onclick="resets();" value="重 置" />
																<input name="button" type="button" class="botton01"
																	onclick="exportCvs();" value="导 出" />
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
										<table id="table1" width="100%" border="0" cellpadding="0" cellspacing="0"
											class="tableline01">
											<tr class="tabletext01">
												<td width="10%" align="center">
													执法检查立项编号
												</td>
												<td width="15%" align="center">
													项目名称
												</td>
												<td width="20%" align="center">
													检查单位名称
												</td>
                                                <td width="20%" align="center">
                                                            被检查单位名称
                                                </td>
												<td width="15%" align="center">
													创建时间
												</td>
												<td width="20%" align="center">
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
											<logic:iterate id="item" name="list">
												<tr>
													<td align="center">
														<bean:write name="item" property="aeno" />
													</td>
													<td align="center">
														<bean:write name="item" property="prjnm" />
													</td>
													<td align="center">
														<bean:write name="item" property="aeorgnm" />
													</td>
                                                    <td id="lengthLimit" title="<bean:write name="item" property="aeedorgnm" />" align="center" style="cursor:pointer">
                                                        <bean:write name="item" property="aeedorgnm" />
                                                    </td>
													<td align="center">
														<bean:write name="item" property="createdate" format="yyyy-MM-dd HH:mm:ss" />
													</td>
													<td align="center">
													<c:if test="${source == 1 || source == null}">
														<a class="botton01"
															href="AdminEnforceManagerAction.do?method=toAdminEnforceDetailPage&id=<bean:write name="item" property="id"/>">查 看</a>
														&nbsp;&nbsp;
														<a class="botton01"
															href="AdminEnforceManagerAction.do?method=toUpdateAdminEnforcePage&id=<bean:write name="item" property="id"/>">修 改</a>
														<jguard:authorized uri="/AdminEnforceManagerAction.do?method=delAdminEnforce">
														&nbsp;&nbsp;
														<a class="botton01"
															href="javascript:isDelete('<bean:write name="item" property="id"/>')">删 除</a>
														</jguard:authorized>
													</c:if>
													<c:if test="${source == 2}">
														<c:choose>
															<c:when test="${item.step >= 1}">
																<a class="botton0001"
																	href="AdminEnforceManagerAction.do?method=toAeinspectionInitPage&id=<bean:write name="item" property="id"/>">录入执法检查工作记录</a>
															</c:when>
															<c:otherwise>
																<input type="button" value="录入执法检查工作记录"
																			class="botton0001" id="input1"
																			onclick="" disabled="disabled"/>
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${source == 3}">
														<c:choose>
															<c:when test="${item.step >= 2}">
																<a class="botton0001"
																	href="AdminEnforceManagerAction.do?method=toAeconclusionInitPage&id=<bean:write name="item" property="id"/>">录入执法检查结论</a>
															</c:when>
															<c:otherwise>
																<input type="button" value="录入执法检查结论"
																			class="botton0001" id="input2"
																			onclick="" disabled="disabled"/>
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${source == 4}">
														<c:choose>
															<c:when test="${item.step >= 3}">
																<a class="botton0001"
																	href="AdminEnforceManagerAction.do?method=toAerectificationInitPage&id=<bean:write name="item" property="id"/>">录入执法检查整改信息</a>
															</c:when>
															<c:otherwise>
																<input type="button" value="录入执法检查整改信息"
																			class="botton0001" id="input3"
																			onclick="" disabled="disabled"/>
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:if test="${source == 5}">
														<c:choose>
															<c:when test="${item.step >= 3}">
																<a class="botton0001"
																	href="AdminSanctionManagerAction.do?method=toAeSanctionInitPage&id=<bean:write name="item" property="id"/>">录入行政处罚信息</a>
															</c:when>
															<c:otherwise>
																<input type="button" value="录入行政处罚信息"
																			class="botton0001" id="input3"
																			onclick="" disabled="disabled"/>
															</c:otherwise>
														</c:choose>
													</c:if>
													</td>
												</tr>
											</logic:iterate>
										</table>
								    </td>
								</tr>
								<tr>
									<td colspan="4" class='left' align="right">
										<sweet:page formName="enforceInfoForm" hrefClass="left" />
										<input type="hidden" name="source" value="<%=request.getAttribute("source").toString()%>">
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
