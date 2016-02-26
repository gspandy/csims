<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="jguard" uri="/WEB-INF/tld/jguard.tld"%>
<%@ page import="com.gtm.csims.model.BsDictionary"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String aeplan = (String) request.getAttribute("aeplan");
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
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/MultiSelect.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext-3.2.0/ItemSelector.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
		<script type="text/javascript">
Ext.MessageBox.minWidth = 400;
			String.prototype.trim = function(){
		   		return this.replace(/(^\s+)|\s+$/g,"");
			}
			
//=====================================选择被检查机构==============================
function selectAeedOrgs() {
    var dsto = new Ext.data.ArrayStore({
        fields : ['value', 'text']
    });
    var httpFromStore = new Ext.data.Store({
        id : "httpFromStore",
        proxy : new Ext.data.HttpProxy({
            url : "<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getOrgsByParentOrgNo",
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
                legend : "已选择被检查机构",
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
        frame : true, 
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
                $("textarea[name='aeedorgChoice']").val(Ext.getCmp('itemselector').getValue());
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
        dataUrl : '<%=request.getContextPath()%>/SystemBaseInfoManagerAction.do?method=orgTree',
        listeners : {
            "beforeload" : function(treeLoader, node) {
                treeLoader.baseParams.id = (node.id != "0" ? node.id : "");
            }
        }
    });
    Ext.BLANK_IMAGE_URL = "<%=request.getContextPath()%>/ext-3.2.0/resources/images/default/tree/s.gif";
    var tree = new Ext.tree.TreePanel({
        title : '选择被检查机构',
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
                Ext.Msg.confirm('提示', '是否确定在'+node.text+'下选择机构？' , function(btn) {
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
        text : '机构',
        draggable : false,
        id : '0'
    });
    tree.setRootNode(root);
    // tree.render();
    root.expand();

    var addBasisWin = new Ext.Window({
        title : '选择执法检查立项被检查机构',
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
		</script>
		<script type="text/javascript">
			function updateAdminEnforce(){
			  if(validateForm()){
				Ext.Msg.confirm('提示', '是否确认修改行政执法登记信息?' , function(btn) {
                    if(btn == 'yes') {
                        var msgbox = Ext.Msg.progress("提示","保存数据","请稍候....."); //显示等待对话框 
                         document.forms[0].action="AdminEnforceManagerAction.do?method=updateAdminEnforce";
                         document.forms[0].submit();
                    }
                });
			  }
			}
			function downloadAtt(id){
				var attId = id;
				document.forms[0].action="AdminEnforceManagerAction.do?method=downloadAtt&attId="+attId;
				document.forms[0].submit();	
			}
			function validateForm(){
                var errorGif='<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
                var okGif='<img src="<%=basePath%>ext-3.2.0//resources//images//default//dd//drop-yes.gif"></img>'; 
                var isPass=true;
                if(isNullOrEmpty($("textarea[name='aeedorgChoice']").val())){
                    $('#aeedorgChoice_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#aeedorgChoice_span').html(okGif);
                }
                
                if(isNullOrEmpty($("input[name='aeplantmdate']").val())
                    || isNullOrEmpty($("input[name='aeplanstdate']").val())){
                    $('#plandate_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                } else {
			        if($("input[name='aeplantmdate']").val() < $("input[name='aeplanstdate']").val()) {
			            $('#plandate_span').html(errorGif + '&nbsp;&nbsp;开始时间应该小于结束时间');
			            isPass = false;
			            } else {
			                $('#plandate_span').html(okGif);
			            }
			    }
                
                if(isNullOrEmpty($("input[name='prjnm']").val())){
                    $('#prjnm_span').html(errorGif+'必填');
                    isPass=false;
                }else{
                    $('#prjnm_span').html(okGif);
                }
                
                if(isNullOrEmpty($("select[name='prjbasis']").val())){
                    $('#prjbasis_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#prjbasis_span').html(okGif);
                }
                
                if(isNullOrEmpty($("select[name='aebasis']").val())){
                    $('#aebasis_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#aebasis_span').html(okGif);
                }
                
                if(isNullOrEmpty($("select[name='aeway']").val())){
                    $('#aeway_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#aeway_span').html(okGif);
                }
                
                if(isNullOrEmpty($("input[name='aeheadman']").val())){
                    $('#aeheadman_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#aeheadman_span').html(okGif);
                }
                
                if(isNullOrEmpty($("textarea[name='aemaster']").val())){
                    $('#aemaster_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#aemaster_span').html(okGif);
                }
                
                if(isNullOrEmpty($("textarea[name='aeother']").val())){
                    $('#aeother_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#aeother_span').html(okGif);
                }
                
                if(isNullOrEmpty($("input[name='aelimt']").val())){
                    $('#aelimt_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#aelimt_span').html(okGif);
                }
                
                if(isNullOrEmpty($("textarea[name='dptopnion']").val())){
                    $('#dptopnion_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#dptopnion_span').html(okGif);
                }
                
                if(isNullOrEmpty($("input[name='deptman']").val())){
                    $('#deptMan_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#deptMan_span').html(okGif);
                }
                
                if(isNullOrEmpty($("input[name='deptauditdate']").val())) {
                    $('#deptAuditDate_span').html(errorGif + '&nbsp;&nbsp;必填');
                    isPass = false;
                } else {
                    if($("input[name='aeplanstdate']").val() < $("input[name='deptauditdate']").val()) {
                        $('#deptAuditDate_span').html(errorGif + '&nbsp;&nbsp;部门审核时间不应该大于检查计划开始时间');
                        isPass = false;
                    } else {
                        $('#deptAuditDate_span').html(okGif);
                    }
                }
                
                if(isNullOrEmpty($("textarea[name='chairopnion']").val())){
                    $('#chairopnion_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#chairopnion_span').html(okGif);
                }
                
                if(isNullOrEmpty($("input[name='chairman']").val())){
                    $('#chairMan_span').html(errorGif+'&nbsp;&nbsp;必填');
                    isPass=false;
                }else{
                    $('#chairMan_span').html(okGif);
                }
                if(isNullOrEmpty($("input[name='chairauditdate']").val())) {
                    $('#chairAuditDate_span').html(errorGif + '&nbsp;&nbsp;必填');
                    isPass = false;
                } else {
                    if($("input[name='chairauditdate']").val() < $("input[name='deptauditdate']").val()) {
                        $('#chairAuditDate_span').html(errorGif + '&nbsp;&nbsp;行领导审核时间不应该小于部门审核时间');
                        isPass = false;
                    } else {
                        if($("input[name='aeplanstdate']").val() < $("input[name='chairauditdate']").val()) {
                             $('#chairAuditDate_span').html(errorGif + '&nbsp;&nbsp;行领导审核时间不应该大于检查计划开始时间');
                             isPass = false;
                        } else {
                            $('#chairAuditDate_span').html(okGif);
                        }
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
								行政执法管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								行政执法信息修改
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form
			action="/AdminEnforceManagerAction.do?method=updateAdminEnforce"
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
																		行政执法信息修改
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
																	</td>
																</tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                执法检查立项编号（行内）
                                                                    </td>
                                                                    <td>
                                                                        <html:text property="innerno" styleClass="text11155"></html:text>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		执法检查单位
																	</td>
																	<td>
																		<html:text property="aeorgChoice"
																			readonly="true" size="80"></html:text>
																		<html:hidden property="aeorg"></html:hidden>
																		<html:hidden property="aeorgnm"></html:hidden>
																		<html:hidden property="aeorgno"></html:hidden>
																	</td>
																</tr>
																<tr>
                                                                    <td align="right" class="tabletext02" rowspan="2">
                                                                        <font color='#FF0000'>*</font>被检查机构
                                                                    </td>
                                                                    <td align="left">
                                                                        <input type="button" value="选择" class="botton01"
                                                                            onclick="selectAeedOrgs()" />
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="left">
                                                                        <html:textarea property="aeedorgChoice" cols="100%"
                                                                            rows="8" readonly="true"></html:textarea>
                                                                        <br>
                                                                        <span id="aeedorgChoice_span"></span>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>计划检查时间
																	</td>
																	<td>
																		<html:text property="aeplanstdate" readonly="true"/>
																		&nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
																		<html:text property="aeplantmdate" readonly="true"/>
																		<span id="plandate_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>项目名称
																	</td>
																	<td>
																		<html:text property="prjnm" size="70%"
																			styleClass="text11155"></html:text>
																		<span id="prjnm_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>立项依据
																	</td>
																	<td>
																		<select name="prjbasis" multiple="multiple" size="6">
																		<%
    java.util.List lxyjList = (java.util.List) request
                .getAttribute("lxyjList");
        String[] aePrjbasisArr= (String[])request.getAttribute("aePrjbasisArr");
        for (int i = 0; i < lxyjList.size(); i++) {
            BsDictionary bictionary = (BsDictionary) lxyjList.get(i);
            boolean hasValue = false;
            for (int j = 0; j < aePrjbasisArr.length; j++) {
                if (aePrjbasisArr[j].equals(bictionary.getDkey())) {
                    hasValue = true;
                }
            }
            if (hasValue) {
                out.print("<option value='" + bictionary.getDkey()
                        + "' selected='selected'>");
                out.print(bictionary.getDvalue());
                out.print("</option>");
            } else {
                out.print("<option value='" + bictionary.getDkey()
                        + "'>");
                out.print(bictionary.getDvalue());
                out.print("</option>");
            }
        }
                                                                        %>
																		</select>
																		<span id="prjbasis_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>检查依据
																	</td>
																	<td>
																		<select name="aebasis" multiple="multiple" size="6">
																		<%
    java.util.List jcyjList = (java.util.List) request
                .getAttribute("jcyjList");
        String[] aebasisArr= (String[])request.getAttribute("aebasisArr");
        for (int i = 0; i < jcyjList.size(); i++) {
            BsDictionary bictionary = (BsDictionary) jcyjList.get(i);
            boolean hasValue = false;
            for (int j = 0; j < aebasisArr.length; j++) {
                if (aebasisArr[j].equals(bictionary.getDkey())) {
                    hasValue = true;
                }
            }
            if (hasValue) {
                out.print("<option value='" + bictionary.getDkey()
                        + "' selected='selected'>");
                out.print(bictionary.getDvalue());
                out.print("</option>");
            } else {
                out.print("<option value='" + bictionary.getDkey()
                        + "'>");
                out.print(bictionary.getDvalue());
                out.print("</option>");
            }
        }
                                                                        %>
																		</select>
																		<span id="aebasis_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		检查内容
																	</td>
																	<td>
																		<html:textarea property="aecontent" cols="140%"
																			rows="5"></html:textarea>
																	</td>
																</tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                      <font color='#FF0000'>*</font>检查期限
                                                                    </td>
                                                                    <td align="left">
                                                                        <html:text property="aelimt"  
                                                                            styleClass="text11155"></html:text>
                                                                        <span id="aelimt_span"></span>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>检查方式
																	</td>
																	<td>
																		<select name="aeway"  multiple="multiple" size="6">
 <%
    java.util.List jcfsList = (java.util.List) request
                .getAttribute("jcfsList");
        String[] aeAwayArr= (String[])request.getAttribute("aeAwayArr");
        for (int i = 0; i < jcfsList.size(); i++) {
            BsDictionary bictionary = (BsDictionary) jcfsList.get(i);
            boolean hasValue = false;
            for (int j = 0; j < aeAwayArr.length; j++) {
                if (aeAwayArr[j].equals(bictionary.getDkey())) {
                    hasValue = true;
                }
            }
            if (hasValue) {
                out.print("<option value='" + bictionary.getDkey()
                        + "' selected='selected'>");
                out.print(bictionary.getDvalue());
                out.print("</option>");
            } else {
                out.print("<option value='" + bictionary.getDkey()
                        + "'>");
                out.print(bictionary.getDvalue());
                out.print("</option>");
            }
        }
                                                                        %>
                                                                        </select>
                                                                        <span id="aeway_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>检查组组长
																	</td>
																	<td>
																		<html:text property="aeheadman" readonly="true" size='100%'></html:text>
																		<span id="aeheadman_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>检查组主查人
																	</td>
																	<td>
																		<html:textarea property="aemaster" cols="100%"
                                                                            rows="3" readonly="true"></html:textarea>
																		<span id="aemaster_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>检查组其他成员
																	</td>
																	<td>
																		 <html:textarea property="aeother" cols="100%"
                                                                            rows="3" readonly="true"></html:textarea>
																		<span id="aeother_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>检查方案
																	</td>
																	<td>
																		<input type="button" value="下 载" class="botton01"
																			onclick="return downloadAtt('<%=aeplan%>');" />
																		<input type="file" name="aeplanpath" id="aeplanpath"
																			class="filepath01" size="70%" />
																		<html:hidden property="aeplan"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		备注
																	</td>
																	<td>
																		<html:textarea property="aesummary" cols="140%"
																			rows="5"></html:textarea>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>部门负责人意见
																	</td>
																	<td>
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
																		<html:text property="deptman" styleClass="text11155"></html:text>
																		<span id="deptMan_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>部门审核时间
																	</td>
																	<td>
																		<html:text property="deptauditdate" maxlength="20"
																			size="15" styleClass="text111"
																			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
																		<span id="deptAuditDate_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>行领导审批意见
																	</td>
																	<td>
																		<html:textarea property="chairopnion" cols="140%"
																			rows="5"></html:textarea>
																		<br>
                                                                        <span id="chairopnion_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>行领导名称
																	</td>
																	<td align="left">
																		<html:text property="chairman" styleClass="text11155"></html:text>
																		<span id="chairMan_span"></span>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>行领导审批时间
																	</td>
																	<td>
																		<html:text property="chairauditdate" maxlength="20"
																			size="15" styleClass="text111"
																			onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
																		<span id="chairAuditDate_span"></span>
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
																			onclick="return updateAdminEnforce();" />
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