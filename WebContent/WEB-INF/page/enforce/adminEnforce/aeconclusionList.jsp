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
        document.forms[0].action="./AdminEnforceManagerAction.do?method=toAeconclusionList&source=<c:out value='${source}'/>";
        document.forms[0].submit();
    }
    
    function resets(){	
		document.getElementById("aeopnionno").value="";
		document.getElementById("aeorgChoice").value="";
		document.getElementById("aeedorgChoice").value="";
		document.getElementById("aeplanstdate").value="";
		document.getElementById("aeplantmdate").value="";
	}
    
    function exportCvs(){
        document.forms[0].action="./AdminEnforceManagerAction.do?method=exportXls&obj=7";
        document.forms[0].submit();
    }
    
	function isDelete(id){ 
        Ext.Msg.confirm('提示', '是否确认删除行政执法检查结论信息?' , function(btn) {
                    if(btn == 'yes') {
                        var msgbox = Ext.Msg.progress("提示","保存数据","请稍候....."); //显示等待对话框 
                        document.forms[0].action="AdminEnforceManagerAction.do?method=delAeconclusion&id="+id;
                        document.forms[0].submit(); 
                    }
                });
    }
	
    function isRepeal(id){ 
        Ext.Msg.confirm('提示', '是否确认撤销行政处罚立项?' , function(btn) {
                    if(btn == 'yes') {
                        var msgbox = Ext.Msg.progress("提示","正在撤销","请稍候....."); //显示等待对话框 
                        document.forms[0].action="AdminSanctionManagerAction.do?method=repealPunish&id="+id;
                        document.forms[0].submit(); 
                    }
                });
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
						行政执法管理
						<img src="<%=request.getContextPath()%>/images/index11.jpg"
							width="6" height="10" hspace="5" />
						<c:if test="${source == null||source ==''}">
						检查结论查询
						</c:if>
						<c:if test="${source == 'punish'}">
						行政处罚登记
						</c:if>
				</tr>
			</table>
		</td>
	</tr>
</table>
<html:form
	action="/AdminEnforceManagerAction.do?method=toAeconclusionList">
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
																执法意见书编号
																<html:text property="aeopnionno" styleClass="text111"
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
																&nbsp;&nbsp;&nbsp;&nbsp;被检查单位
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
										<table width="100%" border="0" cellpadding="0" cellspacing="0"
											class="tableline01">
											<tr class="tabletext01">
												<td width="5%" align="center">
													行政执法检查工作记录编号
												</td>
												<td width="5%" align="center">
													执法意见书编号
												</td>
												<td width="25%" align="center">
													检查单位名称
												</td>
												<td width="25%" align="center">
													被检查单位名称
												</td>
												<td width="20%" align="center">
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
													<td align="left">
														<bean:write name="item" property="aeno" />
													</td>
													<td align="left">
														<bean:write name="item" property="aeopnionno" />
													</td>
													<td align="left">
														<bean:write name="item" property="aeorgnm" />
													</td>
													<td align="left">
														<bean:write name="item" property="aeedorgnm" />
													</td>
													<td align="center">
														<bean:write name="item" property="createdate" format="yyyy-MM-dd HH:mm:ss" />
													</td>
														<c:if test="${source == null||source ==''}">
														<td align="center">
															<a class="botton01"
																href="AdminEnforceManagerAction.do?method=toAeconclusionDetailPage&id=<bean:write name="item" property="id"/>">查
																看</a> &nbsp;&nbsp;
															<a class="botton01"
																href="AdminEnforceManagerAction.do?method=toUpdateAeconclusionPage&id=<bean:write name="item" property="id"/>">修
																改</a>
														<jguard:authorized uri="/AdminEnforceManagerAction.do?method=delAeconclusion">
															&nbsp;&nbsp;
															<a class="botton01"
                                                                href="javascript:isDelete('<bean:write name="item" property="id"/>')">删 除</a>
                                                         </jguard:authorized>
													   </td>
														</c:if>
													<c:if test="${source == 'punish'}">
														<td align="center">
															<a class="botton0001"
																	href="AdminSanctionManagerAction.do?method=toAeSanctionInitPageFromPunish&id=<bean:write name="item" property="id"/>">
																登记行政处罚
															</a>
                                                            <a class="botton01"
                                                                href="javascript:isRepeal('<bean:write name="item" property="id"/>')">撤 销</a>
														</td>
													</c:if>
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
