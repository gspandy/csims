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
	String qtitle = request.getAttribute("qtitle").toString();
	String status = (String) request.getAttribute("status");
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
		<title><bean:message key="PROJECT_NAME" /></title>
		<link href="css/css.css" rel="stylesheet" type="text/css" />
		<link href="css/manus.css" rel="stylesheet" type="text/css" />
		<link href="css/home.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>css/css2.css"
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
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/interface/SelectSystemDataBase.js'></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/engine.js'></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/util.js'></script>
		<script type="text/javascript">
			function showQuestionCreate(){
				$("input[name='qqid']").val("");
				$("textarea[name='qqtitle']").val("");
				$("textarea[name='answera']").val("");
				$("textarea[name='answerb']").val("");
				$("textarea[name='answerc']").val("");
				$("textarea[name='answerd']").val("");
				$("textarea[name='answere']").val("");
				$("textarea[name='answerf']").val("");
				$("textarea[name='answerg']").val("");
				document.getElementById("newQuestion").style.display = "inline";
			}
			
			function showQuestionDetail(id){
				$("input[name='qqid']").val(id);
				SelectSystemDataBase.getQuestionById(id,setQuestion);
			}
			
			function setQuestion(data){
				$("textarea[name='qqtitle']").val(data[0]);
				$("textarea[name='answera']").val(data[1]);
				$("textarea[name='answerb']").val(data[2]);
				$("textarea[name='answerc']").val(data[3]);
				$("textarea[name='answerd']").val(data[4]);
				$("textarea[name='answere']").val(data[5]);
				$("textarea[name='answerf']").val(data[6]);
				$("textarea[name='answerg']").val(data[7]);
				document.getElementById("newQuestion").style.display = "inline";
			}
			
			function hiddenQuestionCreate(){
				document.getElementById("newQuestion").style.display = "none";
				$("input[name='qqid']").val("");
				$("textarea[name='qqtitle']").val("");
				$("textarea[name='answera']").val("");
				$("textarea[name='answerb']").val("");
				$("textarea[name='answerc']").val("");
				$("textarea[name='answerd']").val("");
				$("textarea[name='answere']").val("");
				$("textarea[name='answerf']").val("");
				$("textarea[name='answerg']").val("");
			}
			
			function questionCreate(){
				var qqtitle = $("textarea[name='qqtitle']").val();
				if(qqtitle.length<=0){
					alert("请输入题干内容!");
					return false;
				}
				if(confirm("操作将保存问卷调查问题信息,确认?")){
					document.forms[0].action="QuestionAction.do?method=saveQuestion";
					document.forms[0].submit();
				}
			}
			
			function delQuestion(id){
				$("input[name='qqid']").val(id);
				if(confirm("操作将保存删除问卷调查问题信息,确认?")){
					document.forms[0].action="QuestionAction.do?method=delQuestion";
					document.forms[0].submit();
				}
			}
			
			function toQuestionairesList(){
				document.forms[0].action="QuestionAction.do?method=toQuestionairesList";
				document.forms[0].submit();
			}
			
			function myLoad(status){
				if(status=="0"){
					document.getElementById('INPUT1').disabled=false;
					document.getElementById('INPUT2').disabled=false;
				}else{
					document.getElementById('INPUT1').disabled=true;
					document.getElementById('INPUT2').disabled=true;
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
								问卷问题管理
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/QuestionAction.do?method=toQuestionDetailPage">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="tablestyle">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table width="100%" border="0" cellspacing="0"
													cellpadding="0">
													<tr>
														<td>
															<table width="100%" border="0" align="left"
																cellpadding="0" cellspacing="0">
																<tr>
																	<td align="left">
																		问卷调查标题：<%=qtitle%>
																	</td>
																	<td align="right">
																		<input type="button" value="新增问题" id="INPUT1"
																			class="botton01"
																			onclick="return showQuestionCreate('');" />
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
														<td width="80%" align="center">
															题干
														</td>
														<td width="15%" align="center">
															操作
														</td>
													</tr>
													<logic:empty name="list">
														<tr>
															<td colspan="3" align="center">
																无问卷调查问题数据......
															</td>
														</tr>
													</logic:empty>
													<logic:iterate id="item" name="list" indexId="indexnum">
														<tr>
															<td align="center">
																<c:out value="${(indexnum + 1)}"></c:out>
															</td>
															<td align="left">
																<bean:write name="item" property="qqtitle" />
															</td>
															<td align="center">
																<input type="button" value="查看详细" class="botton01"
																	onclick="return showQuestionDetail('<bean:write name="item" property="id"/>');" />
																<logic:equal name="item"
																	property="bsQuestionaire.status" value="0">
																	<input type="button" value="删除问题" class="botton01"
																		onclick="return delQuestion('<bean:write name="item" property="id"/>');" />
																</logic:equal>
																<logic:equal name="item"
																	property="bsQuestionaire.status" value="1">
																	<input type="button" value="删除问题" class="botton01"
																		disabled
																		onclick="return delQuestion('<bean:write name="item" property="id"/>');" />
																</logic:equal>
																<logic:equal name="item"
																	property="bsQuestionaire.status" value="2">
																	<input type="button" value="删除问题" class="botton01"
																		disabled
																		onclick="return delQuestion('<bean:write name="item" property="id"/>');" />
																</logic:equal>
															</td>
														</tr>
													</logic:iterate>
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
												<div id="newQuestion" style="display: none;">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0">
														<tr>
															<td height="180" valign="top">
																<table width="100%" border="0" cellpadding="0"
																	cellspacing="0" class="tableline01">
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			题干
																		</td>
																		<td align="left">
																			<html:textarea property="qqtitle" cols="140%"
																				rows="5"></html:textarea>
																			<html:hidden property="qid" />
																			<html:hidden property="qqid" />
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			A
																		</td>
																		<td align="left">
																			<html:textarea property="answera" cols="140%"
																				rows="5"></html:textarea>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			B
																		</td>
																		<td align="left">
																			<html:textarea property="answerb" cols="140%"
																				rows="5"></html:textarea>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			C
																		</td>
																		<td align="left">
																			<html:textarea property="answerc" cols="140%"
																				rows="5"></html:textarea>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			D
																		</td>
																		<td align="left">
																			<html:textarea property="answerd" cols="140%"
																				rows="5"></html:textarea>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			E
																		</td>
																		<td align="left">
																			<html:textarea property="answere" cols="140%"
																				rows="5"></html:textarea>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			F
																		</td>
																		<td align="left">
																			<html:textarea property="answerf" cols="140%"
																				rows="5"></html:textarea>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			G
																		</td>
																		<td align="left">
																			<html:textarea property="answerg" cols="140%"
																				rows="5"></html:textarea>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2" align="center">
																			<input type="button" value="保 存" id="INPUT2"
																				class="botton01" onclick="questionCreate();" />
																			<input type="button" value="取 消" class="botton01"
																				onclick="hiddenQuestionCreate();" />
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
													</table>
												</div>
											</td>
										</tr>
										<tr>
											<td align="center">
												<input type="button" value="返 回" class="botton01"
													onclick="toQuestionairesList();" />
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
