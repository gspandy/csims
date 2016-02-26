<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
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
		<title><bean:message key="PROJECT_NAME" /></title>
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
            src="<%=request.getContextPath()%>/js/validate.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/WdatePicker.js" defer="defer"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
        <script type="text/javascript"
            src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
		<script type="text/javascript">
		    Ext.MessageBox.minWidth = 400;
			function createAerectification(){
			 if(validateForm()){
				Ext.Msg.confirm('提示', '是否确认新增行政执法跟踪整改信息信息?' , function(btn) {
                    if(btn == 'yes') {
                        var msgbox = Ext.Msg.progress("提示","保存数据","请稍候....."); //显示等待对话框 
                         document.forms[0].action="AdminEnforceManagerAction.do?method=createAerectification";
                         document.forms[0].submit();
                    }
                });
			  }
			}
			function validateForm(){
                var errorGif='<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
                var okGif='<img src="<%=basePath%>ext-3.2.0//resources//images//default//dd//drop-yes.gif"></img>'; 
                var isPass=true;
                if(isNullOrEmpty($("input[name='rectificationstdate']").val())){
                    $('#rectificationdate_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                } else {
                   if($("input[name='rectificationstdate']").val() < $("input[name='aeplantmdate']").val()){
                       $('#rectificationdate_span').html(errorGif + '&nbsp;&nbsp;回访开始时间应该大于检查结束时间');
                       isPass = false;
                   }else{
                       $('#rectificationdate_span').html(okGif);
                   }
                }
                
                if(isNullOrEmpty($("textarea[name='trackcontend']").val())){
                    $('#trackcontend_span').html(errorGif+'必填');
                    isPass=false;
                }else{
                    $('#trackcontend_span').html(okGif);
                }
                
                return isPass;
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
								跟踪整改信息录入
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form
			action="/AdminEnforceManagerAction.do?method=createAerectification"
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
														<td>
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td class="tiltlegerner">
																		跟踪整改信息录入
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
																		<html:text property="aeno" readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		中国人民银行执法检查意见书编号
																	</td>
																	<td>
																		<html:text property="aeopnionno" readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>跟踪整改编号
																	</td>
																	<td>
																		<html:text property="trackno" readonly="true" size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		执法检查单位
																	</td>
																	<td>
																		<html:text property="aeorgnm" readonly="true" size="100%"></html:text>
																		<html:hidden property="aeorgno"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		被检查机构
																	</td>
																	<td>
																		<html:text property="aeedorgnm" readonly="true" size="100%"></html:text>
																		<html:hidden property="aeedorgno"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		检查时间
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
                                                                        <font color='#FF0000'>*</font>回访时间
                                                                    </td>
                                                                    <td>
                                                                        <html:text property="rectificationstdate" maxlength="20"
                                                                            size="15" styleClass="text111"
                                                                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                                                                        <span id="rectificationdate_span"></span>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>整改情况
																	</td>
																	<td align="left">
																		<html:textarea property="trackcontend" cols="140%"
																			rows="5"></html:textarea>
																		<br>
																		<span id="trackcontend_span"></span>
																	</td>
																</tr>
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="保 存" class="botton01"
																			onclick="return createAerectification();" />
																			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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