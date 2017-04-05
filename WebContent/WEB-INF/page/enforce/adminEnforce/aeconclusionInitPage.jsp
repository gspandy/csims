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
		<link rel="stylesheet"
            href="<%=basePath%>ext-3.2.0/resources/css/MultiSelect.css"
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
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/build/locale/ext-lang-zh_CN.js"></script>  
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/MultiSelect.js"></script>
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ItemSelector.js"></script>
		
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
		<script type="text/javascript">
		    Ext.MessageBox.minWidth = 400;
			function createAeconclusion(){
			 if(validateForm()){
				Ext.Msg.confirm('提示', '是否确认新增行政执法检查结论信息?' , function(btn) {
                    if(btn == 'yes') {
                        var msgbox = Ext.Msg.progress("提示","保存数据","请稍候....."); //显示等待对话框 
                        document.forms[0].action="AdminEnforceManagerAction.do?method=createAeconclusion";
                        document.forms[0].submit();
                    }
                });
			  }
			}
			
			function validateForm(){
                var errorGif='<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
                var okGif='<img src="<%=basePath%>ext-3.2.0//resources//images//default//dd//drop-yes.gif"></img>'; 
                var isPass=true;
                if(isNullOrEmpty($("input[name='aeopnionno']").val())){
                    $('#aeopnionno_span').html(errorGif+'必填');
                    isPass=false;
                }else{
                    $('#aeopnionno_span').html(okGif);
                }
                
                if(isNullOrEmpty($("select[name='decision']").val())){
                    $('#decision_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#decision_span').html(okGif);
                }
                
                if(isNullOrEmpty($("input[name='aeopnionbookpath']").val())){
                    $('#aeopnionbookpath_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#aeopnionbookpath_span').html(okGif);
                }
                return isPass;
            }
            
            
//=====================================选择涉及机构==============================
function selectOrgs() {
    var dsto = new Ext.data.ArrayStore({
        fields : ['value', 'text']
    });
    var httpFromStore = new Ext.data.Store({
        id : "httpFromStore",
        proxy : new Ext.data.HttpProxy({
            url : "<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getOrgsByParentOrgNoContainSelf",
            method : 'POST'
        }),
        reader : new Ext.data.JsonReader({
            totalProperty : 'totalProperty',
            root : 'root'
        }, ['text', 'value']),
        sortInfo: { field: 'text', direction: 'DESC' }
    });
     
    var row1 = {
        items : [{
            xtype : 'itemselector',
            id : 'itemselector',
            name : 'itemselector',
            fieldLabel : 'ItemSelector',
            imagePath : '<%=basePath%>ext-3.2.0/ux/images/',
            drawUpIcon : false,
            drawDownIcon : false,
            drawLeftIcon : true,
            drawRightIcon : true,
            drawTopIcon : false,
            drawBotIcon : false,
            multiselects : [{
                width : 335,
                height : 400,
                store : httpFromStore,
                legend : "待选择机构",
                displayField : 'text',
                valueField : 'value'
            }, {
                width : 335,
                height : 400,
                store : dsto,
                legend : "已选择机构",
                allowBlank : false,
                displayField : 'text',
                valueField : 'value',
                tbar : [{
                    text : '清除',
                    handler : function() {
                        form.getForm().findField('itemselector').reset();
                    }
                }]
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
        frame : true, //在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
        layout : 'form',
        url : '', //提交地址
        method : 'post', //提交方法
        labelWidth : 79,
        labelAlign : 'right',
        items : [row1],
        buttonAlign : 'center', //按钮对其方式
        style : 'padding:10px',
        buttons : [{
            text : '确定',
            handler : function() {
	            // form.getForm().getValues(true) 
                $("textarea[name='relorgnm']").val(Ext.getCmp('itemselector').getValue());
                addBasisWin.close();
                form.destroy();
                addBasisWin.destroy();
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
    var trNode;
    var loader = new Ext.tree.TreeLoader({
        dataUrl : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=selectChildInvolveBank',
        listeners : {
            "beforeload" : function(treeLoader, node) {
                treeLoader.baseParams.id = (node.id != "0" ? node.id : "");
            }
        }
    });
    Ext.BLANK_IMAGE_URL = "<%=request.getContextPath()%>/ext-3.2.0/resources/images/default/tree/s.gif";
    var tree = new Ext.tree.TreePanel({
        title : '选择机构',
        useArrows : true,
        autoScroll : true,
        containerScroll : true,
        region : 'west',
        animate : true,
        height : 200,
        width : 210,
        loader : loader,
        listeners : {
            "click" : function(node) {
                trNode = node;
            },
            "checkchange" : function(node, state) {
                if(node.hasChildNodes()) {
                    for( i = 0; i < node.childNodes.length; i++) {
                        node.childNodes[i].getUI().checkbox.checked = state;
                    }
                }
            },
            "dblclick" : function(node, event) {
                Ext.Msg.confirm('提示', '是否确定在'+node.text+'下选择子机构？' , function(btn) {
                    if(btn == 'yes') {
                        // alert(node.id);
                        httpFromStore.load({
					        params : {
					            parentNo : node.id
					        }
					    });
                    }
                });
            }
        }
    });

    var root = new Ext.tree.AsyncTreeNode({
        text : $("input[name='aeedorgnm']").val(),
        draggable : false,
        id : $("input[name='aeedorgno']").val()
    });
    tree.setRootNode(root);
    // tree.render();
    root.expand();

    var addBasisWin = new Ext.Window({
        title : '选择执法检查结论涉及机构',
        layout : 'border',
        width : 960,
        height : 500,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        items : [tree, form]
    });
    addBasisWin.show();
}

//=====================================选择涉及用户==============================
function selectUsers() {
    var dsto = new Ext.data.ArrayStore({
        fields : ['value', 'text']
    });
    var httpFromStore = new Ext.data.Store({
        id : "httpFromStore",
        proxy : new Ext.data.HttpProxy({
            url : "<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getUsersByOrgNos",
            method : 'POST'
        }),
        reader : new Ext.data.JsonReader({
            totalProperty : 'totalProperty',
            root : 'root'
        }, ['text', 'value']),
        sortInfo: { field: 'text', direction: 'DESC' }
    });
    httpFromStore.load({
                            params : {
                                orgNos : $("textarea[name='relorgnm']").val()
                            }
                        });
    var row1 = {
        items : [{
            xtype : 'itemselector',
            id : 'itemselector',
            name : 'itemselector',
            fieldLabel : 'ItemSelector',
            imagePath : '<%=basePath%>ext-3.2.0/ux/images/',
            drawUpIcon : false,
            drawDownIcon : false,
            drawLeftIcon : true,
            drawRightIcon : true,
            drawTopIcon : false,
            drawBotIcon : false,
            multiselects : [{
                width : 335,
                height : 400,
                store : httpFromStore,
                legend : "待选择用户",
                displayField : 'text',
                valueField : 'value'
            }, {
                width : 335,
                height : 400,
                store : dsto,
                legend : "已选择用户",
                allowBlank : false,
                displayField : 'text',
                valueField : 'value',
                tbar : [{
                    text : '清除',
                    handler : function() {
                        form.getForm().findField('itemselector').reset();
                    }
                }]
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
        frame : true, //在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
        layout : 'form',
        url : '', //提交地址
        method : 'post', //提交方法
        labelWidth : 79,
        labelAlign : 'right',
        items : [row1],
        buttonAlign : 'center', //按钮对其方式
        style : 'padding:10px',
        buttons : [{
            text : '确定',
            handler : function() {
                // form.getForm().getValues(true) 
                $("textarea[name='relpeoples']").val(Ext.getCmp('itemselector').getValue());
                addBasisWin.close();
                form.destroy();
                addBasisWin.destroy();
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
        title : '选择执法检查结论涉及用户',
        layout : 'border',
        width : 750,
        height : 500,
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

function clearUsers() {
    $("textarea[name='relpeoples']").val('');
}

function clearOrgs() {
    $("textarea[name='relorgnm']").val('');
}

//=====================================选择问题概况==============================
function selectWtgk() { 
        var trNode;
        var loader = new Ext.tree.TreeLoader({
            dataUrl : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getProblemSummay',
            listeners : {
                "beforeload" : function(treeLoader, node) {
                    treeLoader.baseParams.id = (node.id != "0" ? node.id : "");
                }
            }
        });
        
        Ext.BLANK_IMAGE_URL = "<%=request.getContextPath()%>/ext-3.2.0/resources/images/default/tree/s.gif";
        
        var tree = new Ext.tree.TreePanel({
            title : '选择问题概况',
            useArrows : true,
            autoScroll : true,
            containerScroll : true,
            region : 'center',
            animate : true,
            height : 200,
            width : 210,
            loader : loader,
            listeners : {
                "click" : function(node) {
                    trNode = node;
                },
                "checkchange" : function(node, state) {
                    if(node.hasChildNodes()) {
                        for( i = 0; i < node.childNodes.length; i++) {
                            node.childNodes[i].getUI().checkbox.checked = state;
                        }
                    }
                }
            },
            buttons : [{
                text : '选择',
                handler : function() {
                    //var nodes = tree.getChecked();
                    var nodes = tree.getRootNode().childNodes;
                    var selectedIds = '';
                    var selectedwtgkTexts = '';
                    for(var j = 0; j < nodes.length; j++) {
                        var node = tree.getRootNode().childNodes[j];
                        if(node.hasChildNodes()) {
                            for(var i = 0; i < node.childNodes.length; i++) {
                                if(node.childNodes[i].getUI().checkbox.checked) {
                                    // alert(node.childNodes[i].id);
                                    selectedIds = selectedIds + node.childNodes[i].id + ',';
                                    selectedwtgkTexts = selectedwtgkTexts + node.childNodes[i].text + ',';
                                }
                            }
                        }
                    }
                    
                    $("#selectedwtgkIds").val(selectedIds);
                    $("textarea[name='selectedwtgkTexts']").val(selectedwtgkTexts);
                    
                    addBasisWin.close();
                    
                   //alert($("#selectedwtgkIds").val() + $("textarea[name='selectedwtgkTexts']").val());
                   // Ext.getCmp('selectedwtgk').setValue(selectedIds);
                    
                //    Ext.Ajax.request({
                //        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=generateFactBook',
                //        method : 'post',
                //        params : {
                //            selectIds : selectedIds
                //        },
                //        success : function(response, options) {
                //            var o = Ext.util.JSON.decode(response.responseText);
                //            Ext.getCmp('factBookContent').setValue(o.content);
                //        },
                //        failure : function() {
                //            Ext.Msg.alert('提示', '生成事实认定书发生错误');
                //        }
                //    });

                }
            }],
            buttonAlign : 'center'
        });

        var root = new Ext.tree.AsyncTreeNode({
            text : '问题概况',
            draggable : false,
            id : '000011'
        });
        tree.setRootNode(root);
        //        tree.render();
        root.expand();

        var addBasisWin = new Ext.Window({
            title : '编辑事实认定书',
            layout : 'border',
            width : 600,
            height : 520,
            resizable : false,
            closeAction : 'close',
            //autoHeight : true,
            constrainHeader : true,
            modal : true,
            plain : true,
            items : [tree]
        });
        addBasisWin.show();
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
								检查结论登记
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form
			action="/AdminEnforceManagerAction.do?method=createAeconclusion"
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
																		检查结论登记
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
																		<font color='#FF0000'>*</font>中国人民银行执法检查意见书编号
																	</td>
																	<td>
																		<html:text property="aeopnionno" styleClass="text11155000" size="50"></html:text>
																		<span id="aeopnionno_span"></span>
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
																		<font color='#FF0000'>*</font>处理决定
																	</td>
																	<td align="left">
																	    <select id="decision" name="decision"  multiple="multiple" size="10">
                                                                            <logic:iterate id="cljd" name="cljdList">
                                                                                <option
                                                                                    value="<bean:write name="cljd" property="dkey" />">
                                                                                    <bean:write name="cljd" property="dvalue" />
                                                                                </option>
                                                                            </logic:iterate>
                                                                        </select>
                                                                        <span id="decision_span"></span>
																	</td>
																</tr>
																
																<tr>
																	<td align="right" class="tabletext02" rowspan="2">
																		<font color='#FF0000'>*</font>问题概况
																	</td>
																	<td align="left">
                                                                        <input type="button" value="选择" class="botton01"
                                                                            onclick="selectWtgk()" />&nbsp;&nbsp;
                                                                        <input type="hidden" name="selectedwtgkIds" id="selectedwtgkIds" value="">
                                                                    </td>
																</tr>
																<tr>
                                                                    <td align="left">
                                                                        <html:textarea property="selectedwtgkTexts" cols="100%"
                                                                            rows="8" readonly="true"></html:textarea>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>上传执法检查意见书
																	</td>
																	<td align="left">
																		<input type="file" name="aeopnionbookpath" id="aeplan"
																			class="filepath01" size="70%" />
																		<span id="aeopnionbookpath_span"></span>
																	</td>
																</tr>
																<tr>
                                                                    <td align="right" class="tabletext02" rowspan="2">
                                                                                                                                                                                                                                                                   执法检查意见书中涉及机构
                                                                    </td>
                                                                    <td align="left">
                                                                        <input type="button" value="选择" class="botton01"
                                                                            onclick="selectOrgs()" />
                                                                        <input type="button" value="清除" class="botton01"
                                                                            onclick="clearOrgs()" />
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="left">
                                                                        <html:textarea property="relorgnm" cols="100%"
                                                                            rows="8" readonly="true"></html:textarea>
                                                                        <br>
                                                                        <span id="relorgnm_span"></span>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02" rowspan="2">
																		执法检查意见书中涉及用户
																	</td>
																	<td align="left">
																		<input type="button" value="选择" class="botton01"
																			onclick="selectUsers()" />
																	    <input type="button" value="清除" class="botton01"
                                                                            onclick="clearUsers()" />
																	</td>
																</tr>
																<tr>
                                                                    <td align="left">
                                                                        <html:textarea property="relpeoples" cols="100%"
                                                                            rows="8" readonly="true"></html:textarea>
                                                                        <br>
                                                                        <span id="relpeoples_span"></span>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="保 存" class="botton01"
																			onclick="return createAeconclusion();" />
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