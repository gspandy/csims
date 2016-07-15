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
	function search(){
		$("form[name='questionForm']").attr("action", "./QuestionAction.do?method=toQuestionairesList");
        $("form[name='questionForm']").submit();
    }
    
    function resets(){	
		$("input[name='qtitle']").val("");
	}
	
	function toCreatePage(id){
		document.getElementById("qid").value=id;
		document.forms[0].action="./QuestionAction.do?method=toCreateQuestionairesPage";
        document.forms[0].submit();
	}
	
	function toQuestionDetailPage(id){
		document.getElementById("qid").value=id;
		document.forms[0].action="./QuestionAction.do?method=toQuestionDetailPage";
        document.forms[0].submit();
	}
	
	function toDeploy(id){
		document.getElementById("status").value="1";
		document.getElementById("qid").value=id;
		if(confirm("操作将发布问卷调查,确认?")){
			document.forms[0].action="QuestionAction.do?method=changeQuestionairesStatus";
			document.forms[0].submit();
		}
	}
	
	function toDelQuestionaires(id){
		document.getElementById("qid").value=id;
		if(confirm("操作将删除问卷调查,确认?")){
			document.forms[0].action="QuestionAction.do?method=delQuestionaires";
			document.forms[0].submit();
		}
	}
	
	function toEnd(id){
		document.getElementById("status").value="2";
		document.getElementById("qid").value=id;
		if(confirm("操作将结束问卷调查,确认?")){
			document.forms[0].action="QuestionAction.do?method=changeQuestionairesStatus";
			document.forms[0].submit();
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
						问卷调查
						<img src="<%=request.getContextPath()%>/images/index11.jpg"
							width="6" height="10" hspace="5" />
						问卷调查列表
				</tr>
			</table>
		</td>
	</tr>
</table>
<html:form action="/QuestionAction.do?method=toQuestionairesList">
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
																标题
																<html:text property="qtitle" styleClass="text111"
																	size="30" maxlength="30" />
															</td>
															<td align="right">
																<input name="button" type="button" class="botton01"
																	onclick="search();" value="查 询" />
																<input name="button2" type="button" class="botton01"
																	onclick="toCreatePage('');" value="新 增" />
																<html:hidden property="qid" />
																<html:hidden property="status" />
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
										<table id="table1" width="100%" border="0" cellpadding="0"
											cellspacing="0" class="tableline01">
											<tr class="tabletext01">
												<td width="5%" align="center">
													序号
												</td>
												<td width="50%" align="center">
													标题
												</td>
												<td width="10%" align="center">
													创建者
												</td>
												<td width="10%" align="center">
													调查结束时间
												</td>
												<td width="5%" align="center">
													状态
												</td>
												<td width="20%" align="center">
													操作
												</td>
											</tr>
											<logic:empty name="list">
												<tr>
													<td colspan="6" align="center">
														无查询数据......
													</td>
												</tr>
											</logic:empty>
											<logic:iterate id="item" name="list" indexId="indexnum">
												<tr>
													<td align="center">
														<c:out value="${(indexnum + 1) + (pageNo - 1) * 10}"></c:out>
														&nbsp;
													</td>
													<td align="left">
														<bean:write name="item" property="qtitle" />
														&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="qcreator" />
														&nbsp;
													</td>
													<td align="center">
														<bean:write name="item" property="qenddatetime" />
														&nbsp;
													</td>
													<td align="center">
														<logic:equal name="item" property="status" value="0">未发布</logic:equal>
														<logic:equal name="item" property="status" value="1">已发布</logic:equal>
														<logic:equal name="item" property="status" value="2">已完成</logic:equal>
													</td>
													<td align="center">
														<logic:equal name="item" property="status" value="0">
															<input name="button2" type="button" class="botton01"
																onclick="toDeploy('<bean:write name="item" property="id"/>');" value="发 布" />
															<input name="button2" type="button" class="botton01"
																onclick="toDelQuestionaires'<bean:write name="item" property="id"/>');" value="删除标题" />
														</logic:equal>
														<logic:equal name="item" property="status" value="1">
															<input name="button2" type="button" class="botton01"
																onclick="toEnd('<bean:write name="item" property="id"/>');" value="结 束" />
														</logic:equal>
														<input name="button2" type="button" class="botton01"
															onclick="toCreatePage('<bean:write name="item" property="id"/>');"
															value="编辑标题" />
														<input name="button2" type="button" class="botton01"
															onclick="toQuestionDetailPage('<bean:write name="item" property="id"/>');"
															value="编辑问题" />
													</td>
												</tr>
											</logic:iterate>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="6" class='left' align="right">
										<sweet:page formName="questionForm" hrefClass="left" />
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
