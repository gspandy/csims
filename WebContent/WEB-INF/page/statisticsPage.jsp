<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sweet" uri="/WEB-INF/tld/sweet-tag.tld"%>
<%
	String path = request.getContextPath() + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>统计</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="template report">
		<link href="<%=path%>css/css.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>css/css2.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="<%=path%>js/page.js"></script>
		<script type="text/javascript" src="<%=path%>js/home.js"></script>
		<script type="text/javascript" src="<%=path%>js/home03.js"></script>
		<script type="text/javascript" src="<%=path%>js/manu.js"></script>
		<script type="text/javascript" src="<%=path%>js/hashmap.js"></script>
		<script type="text/javascript" src="<%=path%>js/jquery.js"></script>
		<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F1F8FC;
}

.x-form-field-wrap {
	display: inline-block;
	width: 50px;
}

#ext-gen7 {
	padding-right: 55px;
	margin-right: 30px;
}

#ext-gen9 {
	
}

#from-to {
	width: 30px;
}
</style>
		<script type="text/javascript">
			function getStatistics(id){
				if(id=='0' || id=='1' || id=='2' || id=='4' || id=='5'){
					document.getElementById("Statistics1").style.display = "none";
				}else{
					document.getElementById("Statistics1").style.display = "inline";
				}
				document.getElementById("stasticType").value = id;
			}
			
			function toReturn(){
				document.forms[0].action = "QuestionAction.do?method=toAnswerResultList";
				document.forms[0].submit();
			}
	     			   
			function toStatistics(){
				var stasticType = document.getElementById("stasticType").value;
		     	var orgChoice = document.getElementById("orgChoice").value;
	  			if(stasticType ==""){
	  				alert("请选择统计方案!");
					return false;
	  			}
	  			if(stasticType =="3" && orgChoice ==""){
	  				alert("请选择统计机构!");
					return false;
	  			}
		     	document.forms[0].action = "QuestionAction.do?method=displayHtml";
				document.forms[0].submit();
			}
		</script>
	</head>
	<body>
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
								<img src="<%=path%>images/index11.jpg" width="6" height="10"
									hspace="5" />
								问卷调查
								<img src="<%=path%>images/index11.jpg" width="6" height="10"
									hspace="5" />
								统计分析
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/QuestionAction.do?method=toStatisticsPage"
			method="post">
			<html:hidden property="qid" styleId="qid" />
			<html:hidden property="stasticType" styleId="stasticType" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td valign="top">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td class="tablestyle">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td>
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0" class="tableline01">
													<tr>
														<td align="center" width="10%">
															序号
														</td>
														<td align="center">
															统计方案
														</td>
													</tr>
													<tr>
														<td align="center">
															1
														</td>
														<td>
															<input name="radio520" id="radio1" type="radio" value="1"
																onclick="getStatistics('0')" />
															问卷调查结果全量统计
														</td>
													</tr>
													<tr>
														<td align="center">
															2
														</td>
														<td>
															<input name="radio520" id="radio2" type="radio"
																onclick="getStatistics('1')" />
															问卷调查结果按地区统计
														</td>
													</tr>
													<tr>
														<td align="center">
															3
														</td>
														<td>
															<input name="radio520" id="radio6" type="radio"
																onclick="getStatistics('5')" />
															问卷调查结果按机构类型统计
														</td>
													</tr>
													<tr>
														<td align="center">
															4
														</td>
														<td>
															<input name="radio520" id="radio3" type="radio"
																onclick="getStatistics('2')" />
															问卷调查结果按机构统计
														</td>
													</tr>
													<tr>
														<td align="center">
															5
														</td>
														<td>
															<input name="radio520" id="radio4" type="radio"
																onclick="getStatistics('3')" />
															问卷调查结果按单家机构统计
														</td>
													</tr>
													<tr>
														<td align="center">
															5
														</td>
														<td>
															<input name="radio520" id="radio5" type="radio"
																onclick="getStatistics('4')" />
															问卷调查结果明细
														</td>
													</tr>
													
													<tr id="Statistics1" style="display: none">
														<td align="center">
															机构选择
														</td>
														<td>
															<html:select property="orgChoice" styleClass="text111"
																title="机构" styleId="orgChoice">
																<html:optionsCollection name="orgList" />
																<html:hidden property="orgChoice" styleId="orgChoice" />
															</html:select>
														</td>
													</tr>
													<tr class="tabletext02">
														<td align="center" colspan="2">
															<input type="button" onclick="toStatistics();"
																value="统 计" class="botton01" />
															<input type="button" onclick="toReturn();" value="返 回"
																class="botton01" />
														<td>
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
</html>
