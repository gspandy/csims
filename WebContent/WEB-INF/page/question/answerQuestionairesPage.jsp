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
	String str = (String) request.getAttribute("str");
	String status = (String) request.getAttribute("status");
%>
<html>
	<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

.text {
	font-family: "宋体";
	font-size: 24px;
}
</style>
	<title><bean:message key="PROJECT_NAME" /></title>
	<link href="css/css.css" rel="stylesheet" type="text/css" />
	<link href="css/manus.css" rel="stylesheet" type="text/css" />
	<link href="css/home.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="<%=basePath%>css/css2.css" type="text/css"></link>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/home.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/home03.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/manu.js"></script>
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/common.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
	function toCommitResult(){
		var str=document.getElementsByName("checkbox");
		var objarray=str.length;
		var chestr="";
		for (i=0;i<objarray;i++){
			if(str[i].checked == true){
   				chestr+=str[i].value+",";
  			}
		}
		$("input[name='answerResult']").val(chestr);
        $("form[name='questionForm']").attr("action", "./QuestionAction.do?method=savaAnserResult");
        $("form[name='questionForm']").submit();
	}
	
	function myLoad(status){
		if(status=="1"){
			document.getElementById('INPUT1').disabled=false;
		}else{
			document.getElementById('INPUT1').disabled=true;
		}
	}
</script>
	<body onload="myLoad(<%=status%>)">
		<html:form
			action="/QuestionAction.do?method=toAnswerQuestionairesPage">
			<html:hidden property="qid" />
			<html:hidden property="answerResult" />
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td>
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<%=str%>
							<tr>
								<td class="text" align="center">
									<input name="button2" type="button" class="botton01" id="INPUT1"
										onclick="toCommitResult();" value="保 存" />
									<input name="button2" type="button" value="返 回"
										class="botton01" onclick="javascript:history.back()" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
