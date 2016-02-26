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
	String aebasis = (String) request.getAttribute("aebasis");
	String enquirercd = (String) request.getAttribute("enquirercd");
	String actualrcd = (String) request.getAttribute("actualrcd");
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
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/home.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/home03.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/manu.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
        <script type="text/javascript"
            src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext-3.2.0/build/locale/ext-lang-zh_CN.js"></script>
		<script type="text/javascript">
			function toUpdateAeinspectionPage(){
				document.forms[0].action="AdminEnforceManagerAction.do?method=toUpdateAeinspectionPage";
				document.forms[0].submit();	
			}
			
			function toAeconclusionInitPage(){
				document.forms[0].action="AdminEnforceManagerAction.do?method=toAeconclusionInitPage";
				document.forms[0].submit();	
			}
			
			function downloadAtt(id){
				var attId = id;
				document.forms[0].action="AdminEnforceManagerAction.do?method=downloadAtt&attId="+attId;
				document.forms[0].submit();	
			}
			
		</script>
<script type="text/javascript">
Ext.MessageBox.minWidth = 400;
//=======================工作底稿==================================
function viewBasisGrid() {
 	var sm = new Ext.grid.CheckboxSelectionModel({ dataIndex: "id" ,singleSelect: true  } ); 
    var cm = new Ext.grid.ColumnModel([sm, {
        header : 'id',
        dataIndex : 'id',
        hidden : true
    }, {
        header : '被检查人',
        dataIndex : 'filed1'
    }, {
        header : '项目名称',
        dataIndex : 'filed2'
    }, {
        header : '检查人',
        dataIndex : 'filed3'
    }, {
        header : '复核人',
        dataIndex : 'filed4'
    }, {
        header : '开始时间',
        dataIndex : 'filed6'
    }, {
        header : '结束时间',
        dataIndex : 'filed12'
    }]);
    var ds = new Ext.data.Store({
        proxy : new Ext.data.HttpProxy({
            url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=pageBasises',
            method : 'POST',
            baseParams : {
                aeinspectionNo : $("input[name='ino']").val(),
                inspect_token : $("input[name='inspect_token']").val()
            }
        }),
        //jsonReader({constructor args},[create fields]);
        reader : new Ext.data.JsonReader({
            totalProperty : 'totalProperty',
            root : 'root'
        }, ['id', 'filed1', 'filed2', 'filed3', 'filed4', 'filed6', 'filed12', 'createdate'])
    });
    ds.load({
        params : {
            aeinspectionNo : $("input[name='ino']").val(),
            inspect_token : $("input[name='inspect_token']").val(),
            start : 0,
            limit : 5
        }
    });
    ds.on("beforeload", function(thiz, options) {
        thiz.baseParams["aeinspectionNo"] = $("input[name='ino']").val();
        thiz.baseParams["inspect_token"] = $("input[name='inspect_token']").val();
    });
    var grid = new Ext.grid.GridPanel({
        id : 'workBasises-grid', // 设置标识ID，方便以后引用！
        title : '工作底稿', // 标题
        //renderTo : 'workBasises',// 显示表格的地方
        selModel: sm,// 复选框
        cm : cm, // 列模型
        ds : ds, // 数据源
        region : 'center',
        // layout:'form'  //从上往下布局
        stripeRows : true, // 加上行条纹
        loadMask : true, // 加载数据时遮蔽表格
        border : true, // 加上边框
        frame : true, // 显示天蓝色圆角框
        autoHeight : true, // 自动设置高度，这个配置很重要
        width : 790,
        x : 1, // 设置初始位置横坐标
        y : 1, // 设置初始位置纵坐标
        // enableDragDrop : true,//容许行的拖曳
        // collapsible : true, // 面板可以折叠
        // titleCollapse : true,// 单击表头任何地方都可以折叠
        //floating : true,
        viewConfig : {
            //True表示为自动展开/缩小列的宽度以适应grid的宽度，这样就不会出现水平的滚动条
            forceFit : true
        },
        bbar : new Ext.PagingToolbar({
            pageSize : 5,
            store : ds,
            displayInfo : true,
            displayMsg : '显示第{0}条到{1}条  记录共{2}条',
            emptyMsg : '没有记录'
        })
    });
    grid.addListener('rowdblclick', onrowdoubleclick);
    var row1 = {
        layout : 'form',
        items : [{
            id : 'textField5',
            xtype : 'displayfield',
            value : '--双击下面列表中记录查看检查详情',
            fieldLabel : '',
            height : 180,
            autoScroll: true
        }]
    };
    var viewFiled5From = new Ext.form.FormPanel({//注意：Ext.form.FormPanel=Ext.FormPanel
        title : '<%= request.getAttribute("inputBasis_title")%> 检查详情    ' + $("input[name='ino']").val(),
        width : 800,
        height : 250,
        // autoHeight:true,
        bodyStyle : 'padding:5px 5px 0',
        // renderTo:Ext.getBody(),
        frame : true, //在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
        // layout : 'form',
        region : 'north',
        url : '', //提交地址
        method : '', //提交方法
        labelWidth : 1,
        labelAlign : 'right',
        items : [row1],
        // buttons : [],
        // buttonAlign : 'center', //按钮对其方式
        style : 'padding:10px'
    });

    //grid.render();
    var viewBasisWin = new Ext.Window({
        title : '查看工作底稿',
        width : 800,
        height : 520,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        layout : 'border',
        items : [viewFiled5From, grid],
        buttons : [{
            text : '关闭',
            handler : function() {//点击取消按钮的操作事件
                viewBasisWin.close();
                viewFiled5From.destroy();
                viewBasisWin.destroy();
            }
        },{
            text : '导出',
            handler : function() {
                var selectionModel = grid.getSelectionModel();
                if (selectionModel.hasSelection()){
                     var record = selectionModel.getSelected();
                       // alert(record.get('id'));
                        document.forms[0].action = "AdminEnforceManagerAction.do?method=generateGzdgWord&wordTemplateId=gzdg&gzdgId="+record.get('id');
    					document.forms[0].submit();
                }else{
                   	Ext.Msg.alert('提示', '请选择需要导出的工作底稿！');
                }
            }
        }],
        buttonAlign : 'center' //按钮对其方式
    });
    viewBasisWin.show();
}

var onrowdoubleclick = function(grid, index, e) {
    var selectionModel = grid.getSelectionModel();
    var record = selectionModel.getSelected();
    // alert(record.get('id'));
    Ext.Ajax.request({
        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getBasisContent',
        params : {
            id : record.get('id')
        },
        method : 'POST',
        callback : function(options, success, response) {
            if(success) {
                // alert(response.responseText);
                var responseJson = Ext.util.JSON.decode(response.responseText);
                // Ext.Msg.alert("成功" + responseJson.filed5);
                // alert(Ext.getCmp('textField5'));
                Ext.getCmp('textField5').setValue(responseJson.filed5);
            } else {
                Ext.Msg.confirm('失败', '请求超时或网络故障,错误编号：[' + response.status + ']是否要重新发送？', function(btn) {
                    if(btn == 'yes') {
                        Ext.Ajax.request(options);
                    }
                });
            }
        }
    });
}
//=======================进场记录==================================

function viewComingGrid() {
    var cm = new Ext.grid.ColumnModel([{
        header : 'id',
        dataIndex : 'id',
        hidden : true
    }, {
        header : '被检查人',
        dataIndex : 'filed1'
    }, {
        header : '进场时间',
        dataIndex : 'filed2'
    }, {
        header : '进场地点',
        dataIndex : 'filed3'
    }, {
        header : '被检查负责人',
        dataIndex : 'filed11'
    }, {
        header : '检查组组长',
        dataIndex : 'filed12'
    }, {
        header : '记录人',
        dataIndex : 'filed13'
    }, {
        header : '时间',
        dataIndex : 'filed6'
    }]);
    var ds = new Ext.data.Store({
        proxy : new Ext.data.HttpProxy({
            url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=pageComings',
            method : 'POST',
            baseParams : {
                aeinspectionNo : $("input[name='ino']").val(),
                inspect_token : $("input[name='inspect_token']").val()
            }
        }),
        //jsonReader({constructor args},[create fields]);
        reader : new Ext.data.JsonReader({
            totalProperty : 'totalProperty',
            root : 'root'
        }, ['id', 'filed1', 'filed2', 'filed3', 'filed11', 'filed12', 'filed13', 'filed6'])
    });
    ds.load({
        params : {
            aeinspectionNo : $("input[name='ino']").val(),
            inspect_token : $("input[name='inspect_token']").val(),
            start : 0,
            limit : 5
        }
    });
    ds.on("beforeload", function(thiz, options) {
        thiz.baseParams["aeinspectionNo"] = $("input[name='ino']").val();
        thiz.baseParams["inspect_token"] = $("input[name='inspect_token']").val();
    });
    var comingGrid = new Ext.grid.GridPanel({
        id : 'workComings-grid', // 设置标识ID，方便以后引用！
        title : '进场记录', // 标题
        //renderTo : 'workBasises',// 显示表格的地方
        // sm : sm,// 复选框
        cm : cm, // 列模型
        ds : ds, // 数据源
        region : 'center',
        // layout:'form'  //从上往下布局
        stripeRows : true, // 加上行条纹
        loadMask : true, // 加载数据时遮蔽表格
        border : true, // 加上边框
        frame : true, // 显示天蓝色圆角框
        autoHeight : true, // 自动设置高度，这个配置很重要
        width : 790,
        x : 1, // 设置初始位置横坐标
        y : 1, // 设置初始位置纵坐标
        // enableDragDrop : true,//容许行的拖曳
        // collapsible : true, // 面板可以折叠
        // titleCollapse : true,// 单击表头任何地方都可以折叠
        floating : true,
        viewConfig : {
            //True表示为自动展开/缩小列的宽度以适应grid的宽度，这样就不会出现水平的滚动条
            forceFit : true
        },
        bbar : new Ext.PagingToolbar({
            pageSize : 5,
            store : ds,
            displayInfo : true,
            displayMsg : '显示第{0}条到{1}条  记录共{2}条',
            emptyMsg : '没有记录'
        })
    });
    comingGrid.addListener('rowdblclick', onComingdoubleclick);
    var row1 = {
        layout : 'form',
        items : [{
            id : 'textField5',
            xtype : 'displayfield',
            value : '--双击下面列表中记录查看--进场情况',
            fieldLabel : '',
            height : 310,
            autoScroll: true
        }]
    };
    var viewFiled5From = new Ext.form.FormPanel({//注意：Ext.form.FormPanel=Ext.FormPanel
        title : '<%= request.getAttribute("inputComing_title")%> 进场情况    ' + $("input[name='ino']").val(),
        width : 800,
        height : 250,
        // autoHeight:true,
        bodyStyle : 'padding:5px 5px 0',
        // renderTo:Ext.getBody(),
        frame : true, //在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
        // layout : 'form',
        region : 'north',
        url : '', //提交地址
        method : '', //提交方法
        labelWidth : 1,
        labelAlign : 'right',
        items : [row1],
        buttonAlign : 'center', //按钮对其方式
        style : 'padding:10px'
    });

    //grid.render();
    var viewBasisWin = new Ext.Window({
        title : '查看进场记录',
        width : 800,
        height : 520,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        layout : 'border',
        items : [viewFiled5From, comingGrid],
        buttons : [{
            text : '关闭',
            handler : function() {//点击取消按钮的操作事件
                viewBasisWin.close();
                viewFiled5From.destroy();
                viewBasisWin.destroy();
            }
        }],
        buttonAlign : 'center' //按钮对其方式
    });
    viewBasisWin.show();
}

var onComingdoubleclick = function(grid, index, e) {
    var selectionModel = grid.getSelectionModel();
    var record = selectionModel.getSelected();
    // alert(record.get('id'));
    Ext.Ajax.request({
        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getComingContent',
        params : {
            id : record.get('id')
        },
        method : 'POST',
        callback : function(options, success, response) {
            if(success) {
                // alert(response.responseText);
                var responseJson = Ext.util.JSON.decode(response.responseText);
                // Ext.Msg.alert("成功" + responseJson.filed5);
                // alert(Ext.getCmp('textField5'));
                Ext.getCmp('textField5').setValue(responseJson.filed5);
            } else {
                Ext.Msg.confirm('失败', '请求超时或网络故障,错误编号：[' + response.status + ']是否要重新发送？', function(btn) {
                    if(btn == 'yes') {
                        Ext.Ajax.request(options);
                    }
                });
            }
        }
    });
}
//=======================离场记录==================================

function viewGoawayGrid() {
    var cm = new Ext.grid.ColumnModel([{
        header : 'id',
        dataIndex : 'id',
        hidden : true
    }, {
        header : '被检查单位',
        dataIndex : 'filed1'
    }, {
        header : '检查项目',
        dataIndex : 'filed2'
    }, {
        header : '会谈时间',
        dataIndex : 'filed3'
    }, {
        header : '会谈地点',
        dataIndex : 'filed4'
    }, {
        header : '会谈主持人',
        dataIndex : 'filed5'
    }, {
        header : '会谈记录人',
        dataIndex : 'filed6'
    }, {
        header : '检查组组长',
        dataIndex : 'filed12'
    }, {
        header : '被检查单位负责人',
        dataIndex : 'filed11'
    }]);
    var ds = new Ext.data.Store({
        proxy : new Ext.data.HttpProxy({
            url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=pageGoaways',
            method : 'POST',
            baseParams : {
                aeinspectionNo : $("input[name='ino']").val(),
                inspect_token : $("input[name='inspect_token']").val()
            }
        }),
        //jsonReader({constructor args},[create fields]);
        reader : new Ext.data.JsonReader({
            totalProperty : 'totalProperty',
            root : 'root'
        }, ['id', 'filed1', 'filed2', 'filed3', 'filed4', 'filed5', 'filed6', 'filed12', 'filed11'])
    });
    ds.load({
        params : {
            aeinspectionNo : $("input[name='ino']").val(),
            inspect_token : $("input[name='inspect_token']").val(),
            start : 0,
            limit : 5
        }
    });
    ds.on("beforeload", function(thiz, options) {
        thiz.baseParams["aeinspectionNo"] = $("input[name='ino']").val();
        thiz.baseParams["inspect_token"] = $("input[name='inspect_token']").val();
    });
    var goawayGrid = new Ext.grid.GridPanel({
        id : 'workGoaways-grid', // 设置标识ID，方便以后引用！
        title : '离场会谈纪要', // 标题
        //renderTo : 'workBasises',// 显示表格的地方
        // sm : sm,// 复选框
        cm : cm, // 列模型
        ds : ds, // 数据源
        region : 'center',
        // layout:'form'  //从上往下布局
        stripeRows : true, // 加上行条纹
        loadMask : true, // 加载数据时遮蔽表格
        border : true, // 加上边框
        frame : true, // 显示天蓝色圆角框
        autoHeight : true, // 自动设置高度，这个配置很重要
        width : 790,
        x : 1, // 设置初始位置横坐标
        y : 1, // 设置初始位置纵坐标
        // enableDragDrop : true,//容许行的拖曳
        // collapsible : true, // 面板可以折叠
        // titleCollapse : true,// 单击表头任何地方都可以折叠
        //floating : true,
        viewConfig : {
            //True表示为自动展开/缩小列的宽度以适应grid的宽度，这样就不会出现水平的滚动条
            forceFit : true
        },
        bbar : new Ext.PagingToolbar({
            pageSize : 5,
            store : ds,
            displayInfo : true,
            displayMsg : '显示第{0}条到{1}条  记录共{2}条',
            emptyMsg : '没有记录'
        })
    });
    goawayGrid.addListener('rowdblclick', onGoawaydoubleclick);
    var row1 = {
        layout : 'form',
        items : [{
            id : 'textField5',
            xtype : 'displayfield',
            value : '--双击下面列表中记录查看--离场会谈纪要会谈内容',
            fieldLabel : '',
            height : 310,
            autoScroll: true
        }]
    };
    var viewFiled5From = new Ext.form.FormPanel({//注意：Ext.form.FormPanel=Ext.FormPanel
        title : '<%= request.getAttribute("inputGoaway_title")%> 离场会谈纪要会谈内容    ' + $("input[name='ino']").val(),
        width : 800,
        height : 250,
        // autoHeight:true,
        bodyStyle : 'padding:5px 5px 0',
        // renderTo:Ext.getBody(),
        frame : true, //在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
        // layout : 'form',
        region : 'north',
        url : '', //提交地址
        method : '', //提交方法
        labelWidth : 1,
        labelAlign : 'right',
        items : [row1],
        buttonAlign : 'center', //按钮对其方式
        style : 'padding:10px'
    });

    //grid.render();
    var viewBasisWin = new Ext.Window({
        title : '查看离场会谈纪要',
        width : 800,
        height : 520,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        layout : 'border',
        items : [viewFiled5From, goawayGrid],
        buttons : [{
            text : '关闭',
            handler : function() {//点击取消按钮的操作事件
                viewBasisWin.close();
                viewFiled5From.destroy();
                viewBasisWin.destroy();
            }
        }],
        buttonAlign : 'center' //按钮对其方式
    });
    viewBasisWin.show();
}

var onGoawaydoubleclick = function(grid, index, e) {
    var selectionModel = grid.getSelectionModel();
    var record = selectionModel.getSelected();
    // alert(record.get('id'));
    Ext.Ajax.request({
        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getGoawayContent',
        params : {
            id : record.get('id')
        },
        method : 'POST',
        callback : function(options, success, response) {
            if(success) {
                // alert(response.responseText);
                var responseJson = Ext.util.JSON.decode(response.responseText);
                // Ext.Msg.alert("成功" + responseJson.filed5);
                // alert(Ext.getCmp('textField5'));
                Ext.getCmp('textField5').setValue(responseJson.filed8);
            } else {
                Ext.Msg.confirm('失败', '请求超时或网络故障,错误编号：[' + response.status + ']是否要重新发送？', function(btn) {
                    if(btn == 'yes') {
                        Ext.Ajax.request(options);
                    }
                });
            }
        }
    });
}
//=======================会谈纪要==================================

function viewTalkSummaryGrid() {
    var cm = new Ext.grid.ColumnModel([{
        header : 'id',
        dataIndex : 'id',
        hidden : true
    }, {
        header : '被检查人',
        dataIndex : 'filed1'
    }, {
        header : '会谈时间',
        dataIndex : 'filed6'
    }, {
        header : '会谈记录人',
        dataIndex : 'filed9'
    }, {
        header : '检查组组长',
        dataIndex : 'filed14'
    }, {
        header : '被检查单位负责人',
        dataIndex : 'filed13'
    }]);
    var ds = new Ext.data.Store({
        proxy : new Ext.data.HttpProxy({
            url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=pageTalkSummarys',
            method : 'POST',
            baseParams : {
                aeinspectionNo : $("input[name='ino']").val(),
                inspect_token : $("input[name='inspect_token']").val()
            }
        }),
        //jsonReader({constructor args},[create fields]);
        reader : new Ext.data.JsonReader({
            totalProperty : 'totalProperty',
            root : 'root'
        }, ['id', 'filed1', 'filed6', 'filed9', 'filed14', 'filed13'])
    });
    ds.load({
        params : {
            aeinspectionNo : $("input[name='ino']").val(),
            inspect_token : $("input[name='inspect_token']").val(),
            start : 0,
            limit : 5
        }
    });
    ds.on("beforeload", function(thiz, options) {
        thiz.baseParams["aeinspectionNo"] = $("input[name='ino']").val();
        thiz.baseParams["inspect_token"] = $("input[name='inspect_token']").val();
    });
    var talkSummaryGrid = new Ext.grid.GridPanel({
        id : 'workTalkSummarys-grid', // 设置标识ID，方便以后引用！
        title : '执法检查会谈纪要', // 标题
        //renderTo : 'workBasises',// 显示表格的地方
        // sm : sm,// 复选框
        cm : cm, // 列模型
        ds : ds, // 数据源
        region : 'center',
        // layout:'form'  //从上往下布局
        stripeRows : true, // 加上行条纹
        loadMask : true, // 加载数据时遮蔽表格
        border : true, // 加上边框
        frame : true, // 显示天蓝色圆角框
        autoHeight : true, // 自动设置高度，这个配置很重要
        width : 790,
        x : 1, // 设置初始位置横坐标
        y : 1, // 设置初始位置纵坐标
        // enableDragDrop : true,//容许行的拖曳
        // collapsible : true, // 面板可以折叠
        // titleCollapse : true,// 单击表头任何地方都可以折叠
        //floating : true,
        viewConfig : {
            //True表示为自动展开/缩小列的宽度以适应grid的宽度，这样就不会出现水平的滚动条
            forceFit : true
        },
        bbar : new Ext.PagingToolbar({
            pageSize : 5,
            store : ds,
            displayInfo : true,
            displayMsg : '显示第{0}条到{1}条  记录共{2}条',
            emptyMsg : '没有记录'
        })
    });
    talkSummaryGrid.addListener('rowdblclick', onTalkSummarydoubleclick);
    var row1 = {
        layout : 'form',
        items : [{
            id : 'textField5',
            xtype : 'displayfield',
            value : '--双击下面列表中记录查看--执法检查会谈纪要会谈内容',
            fieldLabel : '',
            height : 310,
            autoScroll: true
        }]
    };
    var viewFiled5From = new Ext.form.FormPanel({//注意：Ext.form.FormPanel=Ext.FormPanel
        title : '<%= request.getAttribute("inputTalkSummary_title")%> 执法检查会谈纪要会谈内容    ' + $("input[name='ino']").val(),
        width : 800,
        height : 250,
        // autoHeight:true,
        bodyStyle : 'padding:5px 5px 0',
        // renderTo:Ext.getBody(),
        frame : true, //在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
        // layout : 'form',
        region : 'north',
        url : '', //提交地址
        method : '', //提交方法
        labelWidth : 1,
        labelAlign : 'right',
        items : [row1],
        buttonAlign : 'center', //按钮对其方式
        style : 'padding:10px'
    });

    //grid.render();
    var viewBasisWin = new Ext.Window({
        title : '查看执法检查会谈纪要',
        width : 800,
        height : 520,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        layout : 'border',
        items : [viewFiled5From, talkSummaryGrid],
        buttons : [{
            text : '关闭',
            handler : function() {//点击取消按钮的操作事件
                viewBasisWin.close();
                viewFiled5From.destroy();
                viewBasisWin.destroy();
            }
        }],
        buttonAlign : 'center' //按钮对其方式
    });
    viewBasisWin.show();
}

var onTalkSummarydoubleclick = function(grid, index, e) {
    var selectionModel = grid.getSelectionModel();
    var record = selectionModel.getSelected();
    Ext.Ajax.request({
        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=getTalkSummaryContent',
        params : {
            id : record.get('id')
        },
        method : 'POST',
        callback : function(options, success, response) {
            if(success) {
                // alert(response.responseText);
                var responseJson = Ext.util.JSON.decode(response.responseText);
                // Ext.Msg.alert("成功" + responseJson.filed5);
                // alert(Ext.getCmp('textField5'));
                Ext.getCmp('textField5').setValue(responseJson.filed10);
            } else {
                Ext.Msg.confirm('失败', '请求超时或网络故障,错误编号：[' + response.status + ']是否要重新发送？', function(btn) {
                    if(btn == 'yes') {
                        Ext.Ajax.request(options);
                    }
                });
            }
        }
    });
}
//=======================执法情况统计==================================
function viewinspectionAnlGrid() {
    var cm = new Ext.grid.ColumnModel([{
        header : 'id',
        dataIndex : 'id',
        hidden : true
    }, {
        header : '检查派出行',
        dataIndex : 'a1'
    }, {
        header : '被检查机构',
        dataIndex : 'b1'
    }, {
        header : '派出检查人员（人天）',
        dataIndex : 'c1'
    }, {
        header : '现场检查（天）',
        dataIndex : 'd1'
    }, {
        header : '调阅企业档案',
        dataIndex : 'e1'
    }, {
        header : '调阅个人档案',
        dataIndex : 'f1'
    }, {
        header : '抽查总数（企业）',
        dataIndex : 'g1'
    }, {
        header : '错误总数（企业）',
        dataIndex : 'h1'
    }, {
        header : '抽查总数（个人）',
        dataIndex : 'i1'
    }, {
        header : '错误总数（个人）',
        dataIndex : 'j1'
    }, {
        header : '调阅工作文件资料（份）',
        dataIndex : 'k1'
    }, {
        header : '制作询问笔录（份）',
        dataIndex : 'l1'
    }, {
        header : '制作工作底稿（份）',
        dataIndex : 'm1'
    }, {
        header : '检查方式',
        dataIndex : 'n1'
    }, {
        header : '已实施行政处罚金额（元）',
        dataIndex : 'p1'
    }]);
    var ds = new Ext.data.Store({
        proxy : new Ext.data.HttpProxy({
            url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=pageInspectionAnl',
            method : 'POST',
            baseParams : {
                aeinspectionNo : $("input[name='ino']").val(),
                inspect_token : $("input[name='inspect_token']").val()
            }
        }),
        //jsonReader({constructor args},[create fields]);
        reader : new Ext.data.JsonReader({
            totalProperty : 'totalProperty',
            root : 'root'
        }, ['id', 'a1', 'b1', 'c1', 'd1', 'e1', 'f1', 'g1', 'h1', 'i1', 'j1', 'k1', 'l1', 'm1', 'n1', 'p1'])
    });
    ds.load({
        params : {
            aeinspectionNo : $("input[name='ino']").val(),
            inspect_token : $("input[name='inspect_token']").val(),
            start : 0,
            limit : 5
        }
    });
    ds.on("beforeload", function(thiz, options) {
        thiz.baseParams["aeinspectionNo"] = $("input[name='ino']").val();
        thiz.baseParams["inspect_token"] = $("input[name='inspect_token']").val();
    });
    var inspectionanlGrid = new Ext.grid.GridPanel({
        id : 'inspectionanl-grid', // 设置标识ID，方便以后引用！
        title : '执法检查情况', // 标题
        //renderTo : 'workBasises',// 显示表格的地方
        // sm : sm,// 复选框
        cm : cm, // 列模型
        ds : ds, // 数据源
        region : 'center',
        // layout:'form'  //从上往下布局
        stripeRows : true, // 加上行条纹
        loadMask : true, // 加载数据时遮蔽表格
        border : true, // 加上边框
        frame : true, // 显示天蓝色圆角框
        autoHeight : true, // 自动设置高度，这个配置很重要
        width : 790,
        x : 1, // 设置初始位置横坐标
        y : 1, // 设置初始位置纵坐标
        // enableDragDrop : true,//容许行的拖曳
        // collapsible : true, // 面板可以折叠
        // titleCollapse : true,// 单击表头任何地方都可以折叠
        //floating : true,
        viewConfig : {
            //True表示为自动展开/缩小列的宽度以适应grid的宽度，这样就不会出现水平的滚动条
            forceFit : true
        },
        bbar : new Ext.PagingToolbar({
            pageSize : 5,
            store : ds,
            displayInfo : true,
            displayMsg : '显示第{0}条到{1}条  记录共{2}条',
            emptyMsg : '没有记录'
        })
    });

    //grid.render();
    var viewBasisWin = new Ext.Window({
        title : '查看执法检查情况',
        width : 1000,
        height : 520,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        layout : 'border',
        items : [inspectionanlGrid],
        buttons : [{
            text : '关闭',
            handler : function() {//点击取消按钮的操作事件
                viewBasisWin.close();
                viewBasisWin.destroy();
            }
        }],
        buttonAlign : 'center' //按钮对其方式
    });
    viewBasisWin.show();
}
function generateFactBookWord(wordId) {
    document.forms[0].action = "AdminEnforceManagerAction.do?method=generateWord&wordTemplateId=" + wordId + "&from=detail";
    document.forms[0].submit();
}

function generateGzdgWord(wordId) {
    document.forms[0].action = "AdminEnforceManagerAction.do?method=generateGzdgWord&wordTemplateId=" + wordId;
    document.forms[0].submit();
}

function generateHtjyWord(wordId) {
    document.forms[0].action = "AdminEnforceManagerAction.do?method=generateHtjyWord&wordTemplateId=" + wordId + "&from=detail";
    document.forms[0].submit();
}

function generateJcjlWord(wordId) {
    document.forms[0].action = "AdminEnforceManagerAction.do?method=generateJcjlWord&wordTemplateId=" + wordId + "&from=detail";
    document.forms[0].submit();
}

function generateLchtjlWord(wordId) {
    document.forms[0].action = "AdminEnforceManagerAction.do?method=generateLchtjlWord&wordTemplateId=" + wordId + "&from=detail";
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
								检查工作信息详细
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form
			action="/AdminEnforceManagerAction.do?method=toAeinspectionDetailPage"
			method="post">
			<input type="hidden" id='inspect_token' name="inspect_token" value="<%= request.getAttribute("inspect_token") %>"/>
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
																		检查工作信息
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
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		检查工作记录编号（征信）
																	</td>
																	<td>
																		<html:text property="ino" readonly="true" size="100%"></html:text>
																		<html:hidden property="id"></html:hidden>
																	</td>
																</tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                  检查工作记录编号（行内）
                                                                    </td>
                                                                    <td>
                                                                        <html:text property="innerno" readonly="true" size="100%"></html:text>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		执法检查单位
																	</td>
																	<td align="left">
																		<html:text property="aeorgnm" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		被检查机构
																	</td>
																	<td align="left">
																		<html:text property="aeedorgnm" readonly="true"
																			size="100%"></html:text>
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
																		项目名称
																	</td>
																	<td align="left">
																		<%= request.getAttribute("prjnm") %>
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
																		<html:text property="aemaster" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                                检查组其他成员
                                                                    </td>
                                                                    <td>
                                                                        <html:text property="aeother" readonly="true"
                                                                            size="100%"></html:text>
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		进场会谈记录编号
																	</td>
																	<td>
																		<html:text property="inrcdno" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                                    进场会谈记录
                                                                    </td>
                                                                    <td>
                                                                        <input type="button" value="查 看" class="botton01"
                                                                            onclick="viewComingGrid()" />&nbsp;&nbsp;
                                                                        <input type="button" value="导 出" class="botton01"
                                                                            onclick="return generateJcjlWord('jcjl');" />
                                                                    </td>
                                                                </tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                                    执法检查会谈纪要
                                                                    </td>
                                                                    <td>
                                                                        <input type="button" value="查 看" class="botton01"
                                                                            onclick="viewTalkSummaryGrid()" />&nbsp;&nbsp;
                                                                        <input type="button" value="导 出" class="botton01"
                                                                            onclick="return generateHtjyWord('htjy');" />
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                    询问笔录
                                                                    </td>
                                                                    <td>
                                                                        <input type="button" value="下 载" class="botton01"
                                                                            onclick="return downloadAtt('<%=enquirercd %>');" />
                                                                        <html:hidden property="enquirercd"></html:hidden>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                    离场会谈纪要编号
                                                                    </td>
                                                                    <td>
                                                                        <html:text property="outrcdno" readonly="true"
                                                                            size="100%"></html:text>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                    离场会谈纪要
                                                                    </td>
                                                                    <td>
                                                                        <input type="button" value="查 看" class="botton01"
                                                                            onclick="viewGoawayGrid()" />&nbsp;&nbsp;
                                                                        <input type="button" value="导 出" class="botton01"
                                                                            onclick="return generateLchtjlWord('lchtjl');" />
                                                                    </td>
                                                                </tr>
																<tr>
																	<td align="right" class="tabletext02">
																		事实认定书编号
																	</td>
																	<td>
																		<html:text property="actualrcdno" readonly="true"
																			size="100%"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		事实认定书
																	</td>
																	<td>
																		<input type="button" value="下 载" class="botton01"
																			onclick="return downloadAtt('<%=actualrcd %>');" />&nbsp;&nbsp;
                                                                        <input type="button" value="导 出" class="botton01"
                                                                            onclick="return generateFactBookWord('factbook');" />
																		<html:hidden property="actualrcd"></html:hidden>
																	</td>
																</tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                工作底稿
                                                                    </td>
                                                                    <td align="left">
                                                                        <input type="button" value="查 看" class="botton01"
                                                                            onclick="viewBasisGrid()" />&nbsp;&nbsp;
                                                                       <!--  <input type="button" value="导 出" class="botton01"
                                                                            onclick="return generateGzdgWord('gzdg');" /> -->
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td align="right" class="tabletext02">
                                                                                工作底稿相关附件
                                                                    </td>
                                                                    <td>
                                                                        <input type="button" value="下 载" class="botton01"
                                                                            onclick="return downloadAtt('<%=aebasis %>');" />
                                                                        <html:hidden property="aebasis"></html:hidden>
                                                                    </td>
                                                                </tr>
																<tr>
                                                                    <td align="right" class="tabletext02">
                                                                                    检查情况统计
                                                                    </td>
                                                                    <td>
                                                                        <input type="button" value="查 看" class="botton01"
                                                                            onclick="viewinspectionAnlGrid()" />
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
																			onclick="return toUpdateAeinspectionPage();" />
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