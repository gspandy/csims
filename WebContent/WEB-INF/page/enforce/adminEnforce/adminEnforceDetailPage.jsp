<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sweet" uri="/WEB-INF/tld/sweet-tag.tld"%>
<%@ taglib prefix="jguard" uri="/WEB-INF/tld/jguard.tld"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	// String step = (String) request.getAttribute("step");
	String aeplan = (String) request.getAttribute("aeplan");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
</style>
		<title><bean:message key="PROJECT_NAME" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="template report">
		<link href="css/css.css" rel="stylesheet" type="text/css" />
		<link href="css/manus.css" rel="stylesheet" type="text/css" />
		<link href="css/home.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>css/css2.css"
			type="text/css"></link>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/home.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/home03.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/manu.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript">
		
			function toUpdateAdminEnforcePage(){
				document.forms[0].action="AdminEnforceManagerAction.do?method=toUpdateAdminEnforcePage";
				document.forms[0].submit();	
			}
			
			function downloadAtt(id){
				var attId = id;
				document.forms[0].action="AdminEnforceManagerAction.do?method=downloadAtt&attId="+attId;
				document.forms[0].submit();	
			}
			
			function generateLxspbWord(wordId){
                document.forms[0].action="AdminEnforceManagerAction.do?method=generateLxspbWord&wordTemplateId="+wordId+"&from=detail";
                document.forms[0].submit(); 
            }
			
            function generateSjsxjgWord(wordId){
                document.forms[0].action="AdminEnforceManagerAction.do?method=generateSjsxjgWord&wordTemplateId="+wordId+"&from=detail";
                document.forms[0].submit(); 
            }
            
            function generateDesktopClientFile(){
                document.forms[0].action="AdminEnforceManagerAction.do?method=generateDesktopClientInitialFile";
                document.forms[0].submit(); 
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
								行政执法管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								行政执法信息详细
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form
			action="/AdminEnforceManagerAction.do?method=toAdminEnforceDetailPage"
			method="post">
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
														<td>
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td class="tiltlegerner">
																		行政执法登记信息
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td height="180" valign="top">
															<table width="100%" border="0" cellpadding="0"
																cellspacing="0" class="tableline01">
																<tr>
																	<td align="right" class="tabletext02">
																		执法检查立项编号（征信）
																	</td>
																	<td>
																		<html:text property="aeno" readonly="true" size="100%"></html:text>
																		<html:hidden property="id"></html:hidden>
																		<html:hidden property="maxino"></html:hidden>
																	</td>
																</tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                执法检查立项编号（行内）
                                                                    </td>
                                                                    <td>
                                                                        <html:text property="innerno" readonly="true" size="100%"></html:text>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		执法检查单位
																	</td>
																	<td>
																		<html:text property="aeorgnm" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		被检查机构
																	</td>
																	<td>
																		<html:textarea property="aeedorgnm" cols="140%"
                                                                            readonly="true" rows="5"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		计划检查时间
																	</td>
																	<td>
																		<html:text property="aeplanstdate" readonly="true"></html:text>
																		至&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<html:text property="aeplantmdate" readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		项目名称
																	</td>
																	<td>
																		<html:text property="prjnm" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		立项依据
																	</td>
																	<td>
																		<%
																		    java.util.Map dicMap = (java.util.Map) request
																		                .getAttribute("dicMap");
																		    String[] aePrjbasisArr = (String[]) request
																		                .getAttribute("aePrjbasisArr");
																		    for (int i = 0; i < aePrjbasisArr.length; i++) {
																		        if(i != 0) out.print("&nbsp;");
                                                                                out.print((i+1)+"."+dicMap.get(aePrjbasisArr[i]));
                                                                                if(i!=aePrjbasisArr.length-1)
                                                                                    out.print("<br>");
                                                                            }
																		%>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		检查依据
																	</td>
																	<td>
																		<%
																		    String[] aebasisArr = (String[]) request
                                                                                        .getAttribute("aebasisArr");
                                                                            for (int i = 0; i < aebasisArr.length; i++) {
                                                                                if(i != 0) out.print("&nbsp;");
                                                                                out.print((i+1)+"."+dicMap.get(aebasisArr[i]));
                                                                                if(i!=aebasisArr.length-1)
                                                                                    out.print("<br>");
                                                                            }
																		%>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		检查内容
																	</td>
																	<td>
																		<html:textarea property="aecontent" cols="140%"
																			readonly="true" rows="5"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		检查期限
																	</td>
																	<td>
																		<html:text property="aelimt" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		检查方式
																	</td>
																	<td>
																		<%
                                                                            String[] aeAwayArr = (String[]) request
                                                                                        .getAttribute("aeAwayArr");
                                                                            for (int i = 0; i < aeAwayArr.length; i++) {
                                                                                if(i != 0) out.print("&nbsp;");
                                                                                out.print((i + 1) + "." + dicMap.get(aeAwayArr[i]));
                                                                                if(i != aeAwayArr.length - 1)
                                                                                    out.print("<br>");
                                                                            }
                                                                        %>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		检查组组长
																	</td>
																	<td>
																		<html:text property="aeheadman" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		检查组主查人
																	</td>
																	<td>
																	    <html:textarea property="aemaster" cols="100%"
                                                                            rows="3" readonly="true"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		检查组其他成员
																	</td>
																	<td>
																	    <html:textarea property="aeother" cols="100%"
                                                                            rows="3" readonly="true"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		检查方案
																	</td>
																	<td>
																		<input type="button" value="下 载" class="botton01"
																			onclick="return downloadAtt('<%=aeplan%>');" />
																		<html:hidden property="aeplan"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		备注
																	</td>
																	<td>
																		<html:textarea property="aesummary" cols="140%"
																			readonly="true" rows="5"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		部门负责人意见
																	</td>
																	<td>
																		<html:textarea property="dptopnion" cols="140%"
																			readonly="true" rows="5"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		部门负责人
																	</td>
																	<td>
																		<html:text property="deptman" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		部门审核时间
																	</td>
																	<td>
																		<html:text property="deptauditdate" readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		行领导审批意见
																	</td>
																	<td align="left">
																		<html:textarea property="chairopnion" cols="140%"
																			readonly="true" rows="5"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		行领导名称
																	</td>
																	<td align="left">
																		<html:text property="chairman" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		行领导审批时间
																	</td>
																	<td align="left">
																		<html:text property="chairauditdate" readonly="true"></html:text>
																	</td>
																</tr>
                                                                <jguard:hasPrincipal principals="admin">
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                登记人
                                                                    </td>
                                                                    <td align="left">
                                                                        <html:text property="crtlogin" readonly="true"></html:text>
                                                                        <%= request.getAttribute("crtLoginNm") %>
                                                                    </td>
                                                                </tr>
                                                                </jguard:hasPrincipal>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                登记时间
                                                                    </td>
                                                                    <td align="left">
                                                                        <html:text property="createdate" readonly="true"></html:text>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="修 改" class="botton01"
																			onclick="return toUpdateAdminEnforcePage();" />
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<input type="button" value="返 回" class="botton01"
																			onclick="javascript:history.back()" />
																		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
																		<input type="button" value="导出行政执法立项审批表" class="botton0001"
																			onclick="return generateLxspbWord('lxspb');" />
																		<input type="button" value="导出执法检查随机筛选结果" class="botton0001"
																			onclick="return generateSjsxjgWord('sjsxjg');" />
																		<input type="button" value="导出单机版初始化文件" class="botton0001"
																			onclick="return generateDesktopClientFile();" />
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