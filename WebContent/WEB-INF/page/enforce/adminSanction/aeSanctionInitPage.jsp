<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
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
			String.prototype.trim = function(){
		   		return this.replace(/(^\s+)|\s+$/g,"");
			};
		</script>
		<script type="text/javascript">
Ext.MessageBox.minWidth = 400;
function createAeSanction() {
    if(validateForm()) {
        Ext.Msg.confirm('提示', '进行立项登记，必须先取得行领导书面批示！是否确认新增行政处罚信息?', function(btn) {
            if(btn == 'yes') {
                var msgbox = Ext.Msg.progress("提示", "保存数据", "请稍候.....");
                //显示等待对话框
                document.forms[0].action = "AdminSanctionManagerAction.do?method=createAeSanction";
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

//=====================================添加违法人员==============================
// Ext.QuickTips.init();//支持tips提示
Ext.form.Field.prototype.msgTarget = 'under';
function selectIllegalpeoples() {
    var row1 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                xtype : 'textfield',
                fieldLabel : '身份证',
                vtype : "alphanum", //只能输入数字和字母
                vtypeText : "不是有效的证件号码",
                name : 'certNo',
                id : 'certNo',
                value : '',
                width : 230,
                allowBlank : false,
                blankText : "必填"
            }]
        }, {
            columnWidth : .5,
            layout : 'form',
            items : [{
                xtype : 'textfield',
                fieldLabel : '姓名',
                name : 'peoName',
                id : 'peoName',
                width : 230,
                allowBlank : false,
                blankText : "必填"
            }]
        }]
    };

    var form = new Ext.form.FormPanel({
        title : '',
        width : 600,
        //height:250,
        autoHeight : true,
        region : 'center',
        bodyStyle : 'padding:5px 5px 0',
        //renderTo : Ext.getBody(),
        frame : true,
        layout : 'form',
        url : '',
        method : 'post',
        labelWidth : 79,
        labelAlign : 'right',
        items : [row1],
        buttonAlign : 'center',
        style : 'padding:10px',
        buttons : [{
            text : '添加',
            handler : function() {
                if(form.getForm().isValid()) {
                    var addedIllPeos = $("textarea[name='illegalpeople']").val();
                    var sepaChar = '--';
                    var sepsChar2 = ',';
                    if(addedIllPeos == null || addedIllPeos == '') {
                        $("textarea[name='illegalpeople']").val(Ext.getCmp('peoName').getValue() + sepaChar + Ext.getCmp('certNo').getValue());
                    } else {
                        $("textarea[name='illegalpeople']").val(addedIllPeos + sepsChar2 + Ext.getCmp('peoName').getValue() + sepaChar + Ext.getCmp('certNo').getValue());
                    }
                    addBasisWin.close();
                    form.destroy();
                    addBasisWin.destroy();
                }
            }
        }, {
            text : '关闭',
            handler : function() {//点击取消按钮的操作事件
                addBasisWin.close();
                form.destroy();
                addBasisWin.destroy();
            }
        }]
    });

    var addBasisWin = new Ext.Window({
        title : '添加违法人员',
        layout : 'border',
        width : 750,
        height : 140,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        items : [form]
    });
    addBasisWin.show();
}
function clearIllegalpeoples() {
    $("textarea[name='illegalpeople']").val('');
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
								行政处罚登记
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form
			action="/AdminSanctionManagerAction.do?method=createAeSanction"
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
																		行政处罚登记
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
																	<td align="left">
																		<html:text property="aeno" size="70%"
																			readonly="true" ></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		执法检查意见书编号
																	</td>
																	<td align="left">
																		<html:text property="aeopnionno" size="70%"
																			readonly="true" ></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>行政处罚立项编号
																	</td>
																	<td>
																		<html:text property="pbnshnotext"
																			readonly="true" ></html:text>
																		【<html:text property="pbnshnoyear"
																			readonly="true" size="4"></html:text>】
																		第【<html:text property="pbnshnoindex"
																			readonly="true" size="4"></html:text>】号
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>执法检查单位
																	</td>
																	<td align="left">
																		<html:text property="aeorgnm" size="100%"
																			readonly="true"></html:text>
																		<html:hidden property="aeorgno"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>违法单位
																	</td>
																	<td align="left">
																		<html:text property="aeedorgnm" size="100%"
																			readonly="true"></html:text>
																		<html:hidden property="aeedorgno"></html:hidden>
																	</td>
																</tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02" rowspan="2">
                                                                                    违法人员
                                                                    </td>
                                                                    <td align="left">
                                                                        <input type="button" value="添加" class="botton01"
                                                                            onclick="selectIllegalpeoples()" />
                                                                        <input type="button" value="清除" class="botton01"
                                                                            onclick="clearIllegalpeoples()" />
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="left">
                                                                        <html:textarea property="illegalpeople" cols="100%"
                                                                            rows="8" readonly="true"></html:textarea>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>案由
																	</td>
																	<td align="left">
																		<select id="reason" name="reason">
                                                                            <option value="">
                                                                                ---请选择---
                                                                            </option>
                                                                            <logic:iterate id="ay" name="ayList">
                                                                                <option
                                                                                    value="<bean:write name="ay" property="dkey" />">
                                                                                    <bean:write name="ay" property="dvalue" />
                                                                                </option>
                                                                            </logic:iterate>
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
																			rows="5"></html:textarea>
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
																			rows="5"></html:textarea>
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
                                                                            rows="5"></html:textarea>
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
																			rows="5"></html:textarea>
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
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="保 存" class="botton01"
																			onclick="return createAeSanction();" />
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