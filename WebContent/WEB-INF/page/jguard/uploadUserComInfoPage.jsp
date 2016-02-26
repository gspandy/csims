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
	function uploadUserComInfo(){
				var excelpath = document.getElementById('excelpath').value;
				var filetype = excelpath.substring(excelpath.lastIndexOf(".") + 1, excelpath.length);
				var endRow = document.getElementById('endRow').value;
				if(excelpath==null || excelpath.length==0){
		 			alert("请选择导入文件!");
					return false;
				}
				if(filetype != "xls"){
					alert("请导入97-2003版Excel文件!");
					return false;
				}
				if(endRow == ""){
					alert("请指定结束行!");
					return false;
				}
				if(confirm("操作将导入用户表彰信息,确认?")){
					document.forms[0].action="User.do?method=uploadUserComInfo";
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
						系统管理
						<img src="<%=request.getContextPath()%>/images/index11.jpg"
							width="6" height="10" hspace="5" />
						导入机构表彰信息
				</tr>
			</table>
		</td>
	</tr>
</table>
<html:form
	action="/User.do?method=uploadUserComInfo"
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
										<table width="100%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td height="180" valign="top">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0" class="tableline01">
														<tr>
															<td class="tabletext02" align="right" width="15%">
																<font color='#FF0000'>*</font>导入文件
															</td>
															<td align="left">
																<input type="file" name="excelpath" class="filepath01"
																	size="70%" />
															</td>
														</tr>
														<tr>
															<td class="tabletext02" align="right">
																<font color='#FF0000'>*</font>指定结束行
															</td>
															<td align="left">
																<html:text property="endRow" styleClass="text11155"></html:text>
															</td>
														</tr>
														<tr>
															<td align="center" colspan="2">
																<input name="button" type="button" class="botton01"
																	onclick="uploadUserComInfo();" value="导 入" />
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
