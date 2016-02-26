<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sweet" uri="/WEB-INF/tld/sweet-tag.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<html>
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
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="template report">
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
	<script type="text/javascript"
		src="<%=request.getContextPath()%>/js/WdatePicker.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
	<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
	<script type="text/javascript">
	function excelreportPage(){
		var filepath = document.getElementById("filepath").value;
		var colValue = document.getElementById("colValue").value;
		var filestr = filepath.split('.');
		if(filetype ==null){
			alert("请上传Excel报表!");
			return false;
		}else if(filetype!="xls"){
			alert("请上传Excel:97-2003版本!");
			return false;
		}else if(filetype=="xls"){
			var filetype = filestr[filestr.length-1];
			if(colValue ==""){
 				alert("请指定结束行!");
				return false;
			}
			progressExt();
			document.forms[0].action = "CompExcelReportAction.do?method=excelReport&colValue="+colValue;
			document.forms[0].submit();	
		}
	}
	
	function progressExt() {
		Ext.MessageBox.show({
			title : '请等待',
			msg : '',
			progressText : '数据正在读取中...',
			width : 300,
			progress : true, //此属性证明这是一个进度条
			closable : false
		});
	}
</script>
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
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								投诉信息
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								投诉信息录入
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="CompExcelReportAction.do?method=excelReport"
			method="post" enctype="multipart/form-data">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td class="tablestyle">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td height="180" valign="top">
															<table width="100%" border="0" cellpadding="0"
																cellspacing="0" class="tableline01">

																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		Excel上报
																	</td>
																	<td align="left">
																		<input type="file" name="filepath" id="filepath"
																			class="filepath01" size="70" />
																		<font color='#FF0000'>Excel报表版本:97-2003</font>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		结束行
																	</td>
																	<td align="left">
																		<input type="text" name="colValue" id="colValue"
																			class="filepath01" size="5">
																	</td>
																</tr>
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="Excel上报" class="botton01"
																			onclick="return excelreportPage();" />
																	</td>
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
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
