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
<link rel="stylesheet" href="<%=basePath%>css/css2.css" type="text/css"></link>
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
  src="<%=request.getContextPath()%>/js/WdatePicker.js" defer="defer"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript"
  src="<%=request.getContextPath()%>/js/validate.js"></script>
<script type="text/javascript"
  src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
<script type="text/javascript"
  src="<%=basePath%>ext-3.2.0/MultiSelect.js"></script>
<script type="text/javascript"
  src="<%=basePath%>ext-3.2.0/ItemSelector.js"></script>
<script type="text/javascript"
  src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
<script type="text/javascript"
  src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
<script type="text/javascript">
Ext.MessageBox.minWidth = 400;
String.prototype.trim = function() {
    return this.replace(/(^\s+)|\s+$/g, "");
};

function createAdminEnforce() {
     if(validateForm()) {
        Ext.Msg.confirm('提示', '进行立项登记，必须先取得行领导书面批示！是否确认新增行政执法登记信息?' , function(btn) {
                    if(btn == 'yes') {
                        var msgbox = Ext.Msg.progress("提示","保存数据","请稍候....."); //显示等待对话框 
                        document.forms[0].action = "AdminEnforceManagerAction.do?method=createAdminEnforce";
                        document.forms[0].submit();
                    }
                });
     }
}


function validateForm() {
    var errorGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>';
    var okGif = '<img src="<%=basePath%>ext-3.2.0//resources//images//default//dd//drop-yes.gif"></img>';
    var isPass = true;
    if(isNullOrEmpty($("input[name='aetext']").val()) || isNullOrEmpty($("input[name='aeyear']").val()) || isNullOrEmpty($("input[name='aeindex']").val())) {
        $('#aetext_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#aetext_span').html(okGif);
    }
    if(isNullOrEmpty($("input[name='aeorgChoice']").val())) {
        $('#aeorgChoice_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#aeorgChoice_span').html(okGif);
    }
    if(isNullOrEmpty($("textarea[name='aeedorgChoice']").val())) {
        $('#aeedorgChoice_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#aeedorgChoice_span').html(okGif);
    }
    if(isNullOrEmpty($("input[name='aeplantmdate']").val()) || isNullOrEmpty($("input[name='aeplanstdate']").val())) {
        $('#plandate_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        if($("input[name='aeplantmdate']").val() < $("input[name='aeplanstdate']").val()) {
            $('#plandate_span').html(errorGif + '&nbsp;&nbsp;开始时间应该小于结束时间');
            isPass = false;
        } else {
            $('#plandate_span').html(okGif);
        }
    }

    if(isNullOrEmpty($("input[name='prjnm']").val())) {
        $('#prjnm_span').html(errorGif + '必填');
        isPass = false;
    } else {
        $('#prjnm_span').html(okGif);
    }
    if(isNullOrEmpty($("select[name='prjbasis']").val())) {
        $('#prjbasis_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#prjbasis_span').html(okGif);
    }
    if(isNullOrEmpty($("select[name='aebasis']").val())) {
        $('#aebasis_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#aebasis_span').html(okGif);
    }
    if(isNullOrEmpty($("select[name='aeway']").val())) {
        $('#aeway_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#aeway_span').html(okGif);
    }
    if(isNullOrEmpty($("textarea[name='aeheadman']").val())) {
        $('#aeheadman_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#aeheadman_span').html(okGif);
    }
    if(isNullOrEmpty($("textarea[name='aemaster']").val())) {
        $('#aemaster_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#aemaster_span').html(okGif);
    }
    if(isNullOrEmpty($("textarea[name='aeother']").val())) {
        $('#aeother_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#aeother_span').html(okGif);
    }
    if(isNullOrEmpty($("input[name='aelimt']").val())) {
        $('#aelimt_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#aelimt_span').html(okGif);
    }
    if(isNullOrEmpty($("input[name='aeplanpath']").val())) {
        $('#aeplanpath_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        if(!validateFileType('aeplanpath')){
        	$('#aeplanpath_span').html(errorGif + '&nbsp;&nbsp;文件不能为exe,bat,js等格式');
        	isPass = false;
        }else{
        	$('#aeplanpath_span').html(okGif);
        }
    }
    if(isNullOrEmpty($("textarea[name='dptopnion']").val())) {
        $('#dptopnion_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#dptopnion_span').html(okGif);
    }
    if(isNullOrEmpty($("input[name='deptman']").val())) {
        $('#deptMan_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
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
    if(isNullOrEmpty($("textarea[name='chairopnion']").val())) {
        $('#chairopnion_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
        $('#chairopnion_span').html(okGif);
    }
    if(isNullOrEmpty($("input[name='chairman']").val())) {
        $('#chairMan_span').html(errorGif + '&nbsp;&nbsp;必填');
        isPass = false;
    } else {
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

//=====================================选择被检查机构==============================
function selectAeedOrgs() {
    var dsto = new Ext.data.ArrayStore({
        fields : ['value', 'text']
    });
    var httpFromStore = new Ext.data.Store({
        id : "httpFromStore",
        proxy : new Ext.data.HttpProxy({
            url : "<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getAeedOrg",
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

//==================选择检查组组长=======================
function selectAeheadman(){
 var dsto = new Ext.data.ArrayStore({
        fields : ['value', 'text']
    });
    var httpFromStore = new Ext.data.Store({
        id : "httpFromStore",
        proxy : new Ext.data.HttpProxy({
            url : "<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getAepeoplesByPcbOrgNo",
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
                legend : "待选择执法人员",
                displayField : 'text',
                valueField : 'value'
            }, {
                width : 335,
                height : 400,
                store : dsto,
                legend : "已选择执法人员",
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
        url : '',  
        method : 'post',  
        labelWidth : 79,
        labelAlign : 'right',
        items : [row1],
        buttonAlign : 'center', 
        style : 'padding:10px',
        buttons : [{
            text : '确定',
            handler : function() {
                $("textarea[name='aeheadman']").val(Ext.getCmp('itemselector').getValue());
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
        dataUrl : '<%=request.getContextPath()%>/SystemBaseInfoManagerAction.do?method=pcbOrgTree',
        listeners : {
            "beforeload" : function(treeLoader, node) {
                treeLoader.baseParams.id = (node.id != "PCB" ? node.id : "");
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
                Ext.Msg.confirm('提示', '是否确定在'+node.text+'下选择执法人员？' , function(btn) {
                    if(btn == 'yes') {
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
        id : 'PCB'
    });
    tree.setRootNode(root);
    // tree.render();
    root.expand();

    var addBasisWin = new Ext.Window({
        title : '选择执法检查组组长',
        layout : 'border',
        width : 960,
        height : 500,
        resizable : false,
        closeAction : 'close',
        constrainHeader : true,
        modal : true,
        plain : true,
        items : [tree, form]
    });
    addBasisWin.show();
}
//==================选择检查组主查人=======================
function selectAemaster(){
 var dsto = new Ext.data.ArrayStore({
        fields : ['value', 'text']
    });
    var httpFromStore = new Ext.data.Store({
        id : "httpFromStore",
        proxy : new Ext.data.HttpProxy({
            url : "<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getAepeoplesByPcbOrgNo",
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
                legend : "待选择执法人员",
                displayField : 'text',
                valueField : 'value'
            }, {
                width : 335,
                height : 400,
                store : dsto,
                legend : "已选择执法人员",
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
        url : '',  
        method : 'post',  
        labelWidth : 79,
        labelAlign : 'right',
        items : [row1],
        buttonAlign : 'center', 
        style : 'padding:10px',
        buttons : [{
            text : '确定',
            handler : function() {
                $("textarea[name='aemaster']").val(Ext.getCmp('itemselector').getValue());
                addBasisWin.close();
                form.destroy();
                addBasisWin.destroy();
            }
        }, {
            text : '关闭',
            handler : function() { 
                addBasisWin.close();
                form.destroy();
                addBasisWin.destroy();
            }
        }]
    });
    var trNode;
    var loader = new Ext.tree.TreeLoader({
        dataUrl : '<%=request.getContextPath()%>/SystemBaseInfoManagerAction.do?method=pcbOrgTree',
        listeners : {
            "beforeload" : function(treeLoader, node) {
                treeLoader.baseParams.id = (node.id != "PCB" ? node.id : "");
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
                Ext.Msg.confirm('提示', '是否确定在'+node.text+'下选择执法人员？' , function(btn) {
                    if(btn == 'yes') {
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
        id : 'PCB'
    });
    tree.setRootNode(root);
    // tree.render();
    root.expand();

    var addBasisWin = new Ext.Window({
        title : '选择执法检查组主查人',
        layout : 'border',
        width : 960,
        height : 500,
        resizable : false,
        closeAction : 'close',
        constrainHeader : true,
        modal : true,
        plain : true,
        items : [tree, form]
    });
    addBasisWin.show();
}


//==================选择检查组其他成员=======================
function selectAeother(){
 var dsto = new Ext.data.ArrayStore({
        fields : ['value', 'text']
    });
    var httpFromStore = new Ext.data.Store({
        id : "httpFromStore",
        proxy : new Ext.data.HttpProxy({
            url : "<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getAepeoplesByPcbOrgNo",
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
                legend : "待选择执法人员",
                displayField : 'text',
                valueField : 'value'
            }, {
                width : 335,
                height : 400,
                store : dsto,
                legend : "已选择执法人员",
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
        url : '',  
        method : 'post',  
        labelWidth : 79,
        labelAlign : 'right',
        items : [row1],
        buttonAlign : 'center', 
        style : 'padding:10px',
        buttons : [{
            text : '确定',
            handler : function() {
                $("textarea[name='aeother']").val(Ext.getCmp('itemselector').getValue());
                addBasisWin.close();
                form.destroy();
                addBasisWin.destroy();
            }
        }, {
            text : '关闭',
            handler : function() { 
                addBasisWin.close();
                form.destroy();
                addBasisWin.destroy();
            }
        }]
    });
    var trNode;
    var loader = new Ext.tree.TreeLoader({
        dataUrl : '<%=request.getContextPath()%>/SystemBaseInfoManagerAction.do?method=pcbOrgTree',
        listeners : {
            "beforeload" : function(treeLoader, node) {
                treeLoader.baseParams.id = (node.id != "PCB" ? node.id : "");
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
                Ext.Msg.confirm('提示', '是否确定在'+node.text+'下选择执法人员？' , function(btn) {
                    if(btn == 'yes') {
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
        id : 'PCB'
    });
    tree.setRootNode(root);
    // tree.render();
    root.expand();

    var addBasisWin = new Ext.Window({
        title : '选择执法检查组其他成员',
        layout : 'border',
        width : 960,
        height : 500,
        resizable : false,
        closeAction : 'close',
        constrainHeader : true,
        modal : true,
        plain : true,
        items : [tree, form]
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
            <td class="ndwz">&nbsp;</td>
            <td class="ndwzzml"><bean:message key="PROJECT_NAME" />
              <img
              src="<%=request.getContextPath()%>/images/index11.jpg"
              width="6" height="10" hspace="5" /> 行政执法管理 <img
              src="<%=request.getContextPath()%>/images/index11.jpg"
              width="6" height="10" hspace="5" /> 行政执法登记录入</td>
          </tr>
        </table></td>
    </tr>
  </table>
  <html:form
    action="/AdminEnforceManagerAction.do?method=createAdminEnforce"
    method="post" enctype="multipart/form-data"
    onsubmit="return validateEnforceInfoForm(this)">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td align="left" valign="top">
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td valign="top">
                <table width="100%" border="0" cellpadding="0"
                  cellspacing="0">
                  <tr>
                    <td class="tablestyle">
                      <table width="100%" border="0" cellpadding="0"
                        cellspacing="0">
                        <tr>
                          <td>
                            <table width="100%" border="0"
                              cellspacing="0" cellpadding="0">
                              <tr>
                                <td class="tiltlegerner">行政执法登记</td>
                              </tr>
                            </table></td>
                        </tr>
                        <tr>
                          <td height="180" valign="top">
                            <table width="100%" border="0"
                              cellpadding="0" cellspacing="0"
                              class="tableline01">
                              <tr>
                                <td align="right" class="tabletext02">
                                        执法检查立项编号（征信）
                                </td>
                                <td>&nbsp;&nbsp; <html:text
                                    property="aetext" readonly="true"
                                    size="15"></html:text> 【 <html:text
                                    property="aeyear" readonly="true"
                                    size="4"></html:text> 】 第【 <html:text
                                    property="aeindex" readonly="true"
                                    size="4"></html:text> 】号 <span
                                  id="aetext_span"></span></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                        执法检查立项编号（行内）
                                </td>
                                <td>&nbsp;&nbsp; 
                                    <html:text property="innerno" styleClass="text11155"></html:text>
                                </td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                        执法检查单位</td>
                                <td align="left">&nbsp;&nbsp; <input
                                  type="text" name="aeorgChoice"
                                  value="<%=request.getAttribute("aeorgChoice").toString()%>"
                                  size="80" readonly="readonly" /> <span
                                  id="aeorgChoice_span"></span> <input
                                  type="hidden" name="aeorg"
                                  value="<%=request.getAttribute("aeorg").toString()%>">
                                  <input type="hidden" name="aeorgnm"
                                  value="<%=request.getAttribute("aeorgnm").toString()%>">
                                  <input type="hidden" name="aeorgno"
                                  value="<%=request.getAttribute("aeorgno").toString()%>">

                                </td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02"
                                  rowspan="2"><font color='#FF0000'>*</font>被检查机构
                                </td>
                                <td align="left"><input
                                  type="button" value="选择"
                                  class="botton01"
                                  onclick="selectAeedOrgs()" /></td>
                              </tr>
                              <tr>
                                <td align="left"><html:textarea
                                    property="aeedorgChoice" cols="100%"
                                    rows="8" readonly="true"></html:textarea>
                                  <br> <span
                                  id="aeedorgChoice_span"></span></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  <font color='#FF0000'>*</font>计划检查时间</td>
                                <td><html:text
                                    property="aeplanstdate"
                                    maxlength="20" size="15"
                                    styleClass="text111"
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                                  &nbsp;&nbsp;&nbsp;至&nbsp;&nbsp;&nbsp;
                                  <html:text property="aeplantmdate"
                                    maxlength="20" size="15"
                                    styleClass="text111"
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
                                  <span id="plandate_span"></span></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  <font color='#FF0000'>*</font>项目名称</td>
                                <td align="left"><html:text
                                    property="prjnm"
                                    styleClass="text11155"></html:text>
                                  <span id="prjnm_span"></span></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  <font color='#FF0000'>*</font>立项依据</td>
                                <td align="left"><select
                                  id="prjbasis" name="prjbasis"
                                  multiple="multiple" size="6">
                                    <logic:iterate id="lxyj"
                                      name="lxyjList">
                                      <option
                                        value="<bean:write name="lxyj" property="dkey" />">
                                        <bean:write name="lxyj"
                                          property="dvalue" />
                                      </option>
                                    </logic:iterate>
                                </select> <span id="prjbasis_span"></span></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  <font color='#FF0000'>*</font>检查依据</td>
                                <td align="left">
                                <select
                                  id="aebasis" name="aebasis"
                                  multiple="multiple" size="6">
                                    <logic:iterate id="jcyj"
                                      name="jcyjList">
                                      <option
                                        value="<bean:write name="jcyj" property="dkey" />">
                                        <bean:write name="jcyj"
                                          property="dvalue" />
                                      </option>
                                    </logic:iterate>
                                </select> <span id="aebasis_span"></span></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                                                                                                       检查内容</td>
                                <td align="left"><html:textarea
                                    property="aecontent" cols="140%"
                                    rows="5"></html:textarea></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  <font color='#FF0000'>*</font>检查期限</td>
                                <td align="left"><html:text
                                    property="aelimt"
                                    styleClass="text11155"></html:text>
                                  <span id="aelimt_span"></span></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  <font color='#FF0000'>*</font>检查方式</td>
                                <td align="left"><select id="aeway"
                                  name="aeway" multiple="multiple"
                                  size="6">
                                    <logic:iterate id="jcfs"
                                      name="jcfsList">
                                      <option
                                        value="<bean:write name="jcfs" property="dkey" />">
                                        <bean:write name="jcfs"
                                          property="dvalue" />
                                      </option>
                                    </logic:iterate>
                                </select> <span id="aeway_span"></span></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02"
                                  rowspan="2"><font color='#FF0000'>*</font>检查组组长
                                </td>
                                <td align="left"><input
                                  type="button" value="选择"
                                  class="botton01"
                                  onclick="selectAeheadman()" /></td>
                              </tr>
                              <tr>
                                <td align="left"><html:textarea
                                    property="aeheadman" cols="100%"
                                    rows="3" readonly="true"></html:textarea>
                                  <br> <span id="aeheadman_span"></span>
                                </td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02"
                                  rowspan="2"><font color='#FF0000'>*</font>检查组主查人
                                </td>
                                <td align="left"><input
                                  type="button" value="选择"
                                  class="botton01"
                                  onclick="selectAemaster()" /></td>
                              </tr>
                              <tr>
                                <td align="left"><html:textarea
                                    property="aemaster" cols="100%"
                                    rows="3" readonly="true"></html:textarea>
                                  <br> <span id="aemaster_span"></span>
                                </td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02"
                                  rowspan="2"><font color='#FF0000'>*</font>检查组其他成员
                                </td>
                                <td align="left"><input
                                  type="button" value="选择"
                                  class="botton01"
                                  onclick="selectAeother()" /></td>
                              </tr>
                              <tr>
                                <td align="left"><html:textarea
                                    property="aeother" cols="100%"
                                    rows="3" readonly="true"></html:textarea>
                                  <br> <span id="aeother_span"></span>
                                </td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  <font color='#FF0000'>*</font>检查方案</td>
                                <td align="left"><input type="file"
                                  name="aeplanpath" id="aeplanpath"
                                  class="filepath01" size="70%" /> <span
                                  id="aeplanpath_span"></span></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  备注</td>
                                <td align="left"><html:textarea
                                    property="aesummary" cols="140%"
                                    rows="5"></html:textarea></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  <font color='#FF0000'>*</font>部门负责人意见
                                </td>
                                <td align="left"><html:textarea
                                    property="dptopnion" cols="140%"
                                    rows="5"></html:textarea> <br>
                                  <span id="dptopnion_span"></span></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  <font color='#FF0000'>*</font>部门负责人</td>
                                <td align="left"><html:text
                                    property="deptman"
                                    styleClass="text11155"></html:text>
                                  <span id="deptMan_span"></span></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  <font color='#FF0000'>*</font>部门审核时间</td>
                                <td><html:text
                                    property="deptauditdate"
                                    maxlength="20" size="15"
                                    styleClass="text111"
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                                  <span id="deptAuditDate_span"></span>
                                </td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  <font color='#FF0000'>*</font>行领导审批意见
                                </td>
                                <td align="left"><html:textarea
                                    property="chairopnion" cols="140%"
                                    rows="5"></html:textarea> <br>
                                  <span id="chairopnion_span"></span></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  <font color='#FF0000'>*</font>行领导名称</td>
                                <td align="left"><html:text
                                    property="chairman"
                                    styleClass="text11155"></html:text>
                                  <span id="chairMan_span"></span></td>
                              </tr>
                              <tr>
                                <td align="right" class="tabletext02">
                                  <font color='#FF0000'>*</font>行领导审批时间
                                </td>
                                <td><html:text
                                    property="chairauditdate"
                                    maxlength="20" size="15"
                                    styleClass="text111"
                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
                                  <span id="chairAuditDate_span"></span>
                                </td>
                              </tr>
                              <tr>
                                <td colspan="2" align="center"><input
                                  type="button" value="保 存"
                                  class="botton01"
                                  onclick="return createAdminEnforce();" />
                                  <input type="button" value="返 回"
                                  class="botton01"
                                  onclick="javascript:history.back()" />
                                </td>
                              </tr>
                            </table></td>
                        </tr>
                      </table></td>
                  </tr>
                </table></td>
            </tr>
          </table></td>
      </tr>
    </table>
  </html:form>
</body>
</html>