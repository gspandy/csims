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
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
		<script type="text/javascript">
Ext.MessageBox.minWidth = 400;
function updateAeSanction() {
    if(validateForm()) {
        Ext.Msg.confirm('提示', '是否确认修改行政处罚信息?', function(btn) {
            if(btn == 'yes') {
                var msgbox = Ext.Msg.progress("提示", "保存数据", "请稍候.....");
                //显示等待对话框
                document.forms[0].action = "AdminSanctionManagerAction.do?method=updateAeSanction";
                document.forms[0].submit();
            }
        });
    }
}

function validateForm() {
    var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
    var okGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//dd//drop-yes.gif"></img>';
    var isPass = true;
    if(isNullOrEmpty($("select[name='reason']").val())) {
        $('#reason_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#reason_span').html(okGif);
    }

    if(isNullOrEmpty($("textarea[name='summarys']").val())) {
        $('#summarys_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#summarys_span').html(okGif);
    }

    if(isNullOrEmpty($("textarea[name='peopleopnion']").val())) {
        $('#peopleopnion_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#peopleopnion_span').html(okGif);
    }
    if(isNullOrEmpty($("input[name='peopler']").val())) {
        $('#peopler_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#peopler_span').html(okGif);
    }
    if(isNullOrEmpty($("input[name='peoplerdate']").val())) {
        $('#peoplerdate_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#peoplerdate_span').html(okGif);
    }

    if(isNullOrEmpty($("textarea[name='dptopnion']").val())) {
        $('#dptopnion_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#dptopnion_span').html(okGif);
    }
    if(isNullOrEmpty($("input[name='dpter']").val())) {
        $('#dpter_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#dpter_span').html(okGif);
    }
    if(isNullOrEmpty($("input[name='dpterdate']").val())) {
        $('#dpterdate_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        if($("input[name='dpterdate']").val() < $("input[name='peoplerdate']").val()) {
            $('#dpterdate_span').html(errorGif + '&nbsp;&nbsp;部门负责人签署时间应该不小于承办时间');
            isPass = false;
        } else {
            $('#dpterdate_span').html(okGif);
        }
    }

    if(isNullOrEmpty($("textarea[name='chairopnion']").val())) {
        $('#chairopnion_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#chairopnion_span').html(okGif);
    }
    if(isNullOrEmpty($("input[name='chairer']").val())) {
        $('#chairer_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#chairer_span').html(okGif);
    }
    if(isNullOrEmpty($("input[name='chairerdate']").val())) {
        $('#chairerdate_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        if($("input[name='chairerdate']").val() < $("input[name='dpterdate']").val()) {
            $('#chairerdate_span').html(errorGif + '&nbsp;&nbsp;领导审批时间应该不小于部门负责人签署时间');
            isPass = false;
        } else {
            $('#chairerdate_span').html(okGif);
        }
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
								行政处罚管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								行政处罚信息修改
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form
			action="/AdminSanctionManagerAction.do?method=updateAeSanction"
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
																		行政处罚信息修改
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
																		<html:hidden property="id"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		执法检查意见书编号
																	</td>
																	<td align="left">
																		<html:text property="aeopnionno" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		行政处罚立项编号
																	</td>
																	<td align="left">
																		<html:text property="punishno" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		执法检查单位
																	</td>
																	<td>
																		<html:text property="aeorgnm" readonly="true"
																			size="100%"></html:text>
																		<html:hidden property="aeorgno"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		违法单位
																	</td>
																	<td>
																		<html:text property="illegalunit" readonly="true"
																			size="100%"></html:text>
																		<html:hidden property="aeedorgnm"></html:hidden>
																		<html:hidden property="aeedorgno"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		违法人员
																	</td>
																	<td align="left">
																		<html:textarea property="illegalpeople" cols="100%"
                                                                            rows="8" readonly="true"></html:textarea>
                                                                        <br>
                                                                        <font color='#FF0000'>目前暂不支持对违法人员进行修改</font>
																	</td>
																	</tr>
																<tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>案由
																	</td>
																	<td align="left">
																		<select name="reason">
                                                                            <c:forEach var="ay"
                                                                                items="${requestScope.ayList}">
                                                                                <c:choose>
                                                                                    <c:when test="${ay.dkey==requestScope.reason}">
                                                                                        <option value="<c:out value="${ay.dkey}" />" selected="selected">
                                                                                            <c:out value="${ay.dvalue}" />
                                                                                        </option>
                                                                                    </c:when>
                                                                                    <c:otherwise>
                                                                                        <option value="<c:out value="${ay.dkey}" />">
                                                                                            <c:out value="${ay.dvalue}" />
                                                                                        </option>
                                                                                    </c:otherwise>
                                                                                </c:choose>
                                                                            </c:forEach>
                                                                        </select>
                                                                        <span id="reason_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>违法事实摘要
																	</td>
																	<td align="left">
																		<html:textarea property="summarys" cols="140%"
																			rows="8"></html:textarea>
																		<br>
                                                                        <span id="summarys_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>行政处罚单位承办人意见
																	</td>
																	<td align="left">
																		<html:textarea property="peopleopnion" cols="140%"
																			rows="8"></html:textarea>
																		<br>
                                                                        <span id="peopleopnion_span"></span>
																	</td>
																</tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                        <font color='#FF0000'>*</font>承办人
                                                                    </td>
                                                                    <td align="left">
                                                                        <html:text property="peopler" size="70%"
                                                                            styleClass="text11155"></html:text>
                                                                        <span id="peopler_span"></span>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                        <font color='#FF0000'>*</font>承办时间
                                                                    </td>
                                                                    <td>
                                                                        <html:text property="peoplerdate" maxlength="20"
                                                                            size="15" styleClass="text111"
                                                                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                                                                        <span id="peoplerdate_span"></span>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                        <font color='#FF0000'>*</font>行政处罚单位承办部门意见
                                                                    </td>
                                                                    <td align="left">
                                                                        <html:textarea property="dptopnion" cols="140%"
                                                                            rows="8"></html:textarea>
                                                                        <br>
                                                                        <span id="dptopnion_span"></span>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                        <font color='#FF0000'>*</font>部门负责人
                                                                    </td>
                                                                    <td align="left">
                                                                        <html:text property="dpter" size="70%"
                                                                            styleClass="text11155"></html:text>
                                                                        <span id="dpter_span"></span>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                        <font color='#FF0000'>*</font>承办部门负责人签署时间
                                                                    </td>
                                                                    <td>
                                                                        <html:text property="dpterdate" maxlength="20"
                                                                            size="15" styleClass="text111"
                                                                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                                                                        <span id="dpterdate_span"></span>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>领导审批意见
																	</td>
																	<td align="left">
																		<html:textarea property="chairopnion" cols="140%"
																			rows="8"></html:textarea>
																		<br>
                                                                        <span id="chairopnion_span"></span>
																	</td>
																</tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                        <font color='#FF0000'>*</font>审批人
                                                                    </td>
                                                                    <td align="left">
                                                                        <html:text property="chairer" size="70%"
                                                                            styleClass="text11155"></html:text>
                                                                        <span id="chairer_span"></span>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                        <font color='#FF0000'>*</font>审批时间
                                                                    </td>
                                                                    <td>
                                                                        <html:text property="chairerdate" maxlength="20"
                                                                            size="15" styleClass="text111"
                                                                            onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                                                                        <span id="chairerdate_span"></span>
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
																		<input type="button" value="保 存" class="botton01"
																			onclick="return updateAeSanction();" />
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