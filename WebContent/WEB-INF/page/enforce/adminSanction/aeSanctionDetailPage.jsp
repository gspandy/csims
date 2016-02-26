<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="jguard" uri="/WEB-INF/tld/jguard.tld"%>
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
			function toUpdateAeSanctionPage(){
				document.forms[0].action="AdminSanctionManagerAction.do?method=toUpdateAeSanctionPage";
				document.forms[0].submit();	
			}
			
			function toAeSanctionConInitPage(){
				document.forms[0].action="AdminSanctionManagerAction.do?method=toAeSanctionConInitPage";
				document.forms[0].submit();	
			}
			function generateXzcflxbWord(wordId){
                document.forms[0].action="AdminSanctionManagerAction.do?method=generateXzcflxbWord&wordTemplateId="+wordId;
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
								行政处罚管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								行政处罚信息详细
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form
			action="/AdminSanctionManagerAction.do?method=toUpdateAeSanctionPage"
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
																		行政处罚信息详细
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
																		行政执法工作检查记录编号
																	</td>
																	<td>
																		&nbsp;<html:text property="aeno" readonly="true" size="100%"></html:text>
																		<html:hidden property="id"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		执法检查意见书编号
																	</td>
																	<td align="left">
																		&nbsp;<html:text property="aeopnionno" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		行政处罚立项编号
																	</td>
																	<td align="left">
																		&nbsp;<html:text property="punishno" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		执法检查单位
																	</td>
																	<td>
																		&nbsp;<html:text property="aeorgnm" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		违法单位
																	</td>
																	<td>
																		&nbsp;<html:text property="aeedorgnm" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		违法人员
																	</td>
																	<td>
																		<html:textarea property="illegalpeople" cols="100%"
                                                                            rows="8" readonly="true"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		案由
																	</td>
																	<td align="left">
																		<% java.util.Map dicMap=(java.util.Map)request.getAttribute("dicMap"); 
                                                                        org.apache.struts.action.DynaActionForm dyna = (org.apache.struts.action.DynaActionForm) request.getAttribute("form");  %>
                                                                            <%=dicMap.get(dyna.get("reason").toString()) %>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		违法事实摘要
																	</td>
																	<td align="left" style="word-break:break-all;word-wrap:break-word;" width="1300">
																		<%= request.getAttribute("summarys") %>
																	</td>
																</tr>
																<tr>
                                                                    <td align="right" class="tabletexttdbreak">
                                                                                                                                                  行政处罚单位承办人意见
                                                                    </td>
                                                                    <td align="left" style="word-break:break-all;word-wrap:break-word;" width="1300">
                                                                        <%= request.getAttribute("peopleopnion") %>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                                                                                   承办人
                                                                    </td>
                                                                    <td>
                                                                        &nbsp;<html:text property="peopler" readonly="true"
                                                                            size="100%"></html:text>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                                                                                   承办时间
                                                                    </td>
                                                                    <td>
                                                                        &nbsp;<html:text property="peoplerdate" readonly="true"
                                                                            size="100%"></html:text>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		行政处罚单位承办部门意见
																	</td>
																	<td align="left" style="word-break:break-all;word-wrap:break-word;" width="800">
																		<%= request.getAttribute("dptopnion") %>
																	</td>
																</tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                                                                                                    部门负责人
                                                                    </td>
                                                                    <td>
                                                                        &nbsp;<html:text property="dpter" readonly="true"
                                                                            size="100%"></html:text>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                                                                                   承办部门负责人签署时间
                                                                    </td>
                                                                    <td>
                                                                        &nbsp;<html:text property="dpterdate" readonly="true"
                                                                            size="100%"></html:text>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletexttdbreak">
																		领导审批意见
																	</td>
																	<td align="left" style="word-break:break-all;word-wrap:break-word;" width="800">
                                                                        <%= request.getAttribute("chairopnion") %>
																	</td>
																</tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                                                                                                   审批人
                                                                    </td>
                                                                    <td>
                                                                        &nbsp;<html:text property="chairer" readonly="true"
                                                                            size="100%"></html:text>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                                                                                   审批时间
                                                                    </td>
                                                                    <td>
                                                                        &nbsp;<html:text property="chairerdate" readonly="true"
                                                                            size="100%"></html:text>
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
																			onclick="return toUpdateAeSanctionPage();" />
																		<input type="button" value="录入行政处罚结论"
																			class="botton0001" 
																			onclick="return toAeSanctionConInitPage();" />
																		<input type="button" value="导出行政处罚立项审批表"
                                                                            class="botton0001" 
                                                                            onclick="return generateXzcflxbWord('xzcflxb');" />
																		<input type="button" value="返 回" class="botton01"
																			onclick="javascript:history.back()" />
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