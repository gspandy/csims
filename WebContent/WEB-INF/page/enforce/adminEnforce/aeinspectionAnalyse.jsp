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
		<title><bean:message key="PROJECT_NAME" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link href="css/css.css" rel="stylesheet" type="text/css" />
		<link href="css/manus.css" rel="stylesheet" type="text/css" />
		<link href="css/home.css" rel="stylesheet" type="text/css" />
		<link rel="stylesheet" href="<%=basePath%>css/css2.css"
			type="text/css"></link>
		<link rel="stylesheet" type="text/css"
			href="<%=request.getContextPath()%>/ext-3.2.0/resources/css/ext-all.css" />
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
			src="<%=request.getContextPath()%>/ext-3.2.0/adapter/ext/ext-base.js"> </script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/ext-3.2.0/ext-all.js"> </script>
        <script type="text/javascript" src="<%=basePath%>ext-3.2.0/build/locale/ext-lang-zh_CN.js"></script>
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
								行政执法统计
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								行政执法检查情况统计
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" valign="top">
						<div id="_panel" align="center"></div>
					</td>
				</tr>
			</table>
		<html:form
            action="AdminEnforceManagerAction.do?method=generateAeinspectionAnlExcel"
            method="post">
            <html:hidden property="form_a1" value=""/>
            <html:hidden property="form_b1" value=""/>
            <html:hidden property="form_stdate" value=""/>
            <html:hidden property="form_tmdate" value=""/>
        </html:form>
	</body>
<script type="text/javascript">
Ext.MessageBox.minWidth = 400;
var row1 = {
    layout : 'column', //从左往右布局
    items : [{
        columnWidth : .5, //该列有整行中所占百分比
        layout : 'form', //从上往下布局
        items : [{
            xtype : 'textfield',
            fieldLabel : '检查派出行',
            name : 'a1',
            id : 'a1',
            width : 350
        }]
    }, {
        columnWidth : .5,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '被检查机构',
            name : 'b1',
            id : 'b1',
            width : 350
        }]
    }]
};
var row2 = {
    layout : 'column', //从左往右布局
    items : [{
        columnWidth : .5, //该列有整行中所占百分比
        layout : 'form', //从上往下布局
        items : [{
            xtype : 'datefield',
            fieldLabel : '开始时间',
            format : "Y-m-d",
            name : 'stdate',
            id : 'stdate',
            width : 350,
            editable : false
        }]
    }, {
        columnWidth : .5,
        layout : 'form',
        items : [{
            xtype : 'datefield',
            fieldLabel : '结束时间',
            format : "Y-m-d",
            name : 'tmdate',
            id : 'tmdate',
            width : 350,
            editable : false
        }]
    }]
};
var form = new Ext.form.FormPanel({//注意：Ext.form.FormPanel=Ext.FormPanel
    title : '',
    width : 1050,
    //height:250,
    autoHeight : true,
    bodyStyle : 'padding:5px 5px 0',
    region : 'north',
    // renderTo : Ext.getBody(),
    // applyTo : 'form',
    frame : true, //在此，这个属性就很重要了，如果不为true，你会看到下面的button和panel看起来是分开的
    layout : 'form',
    url : 'login.aspx', //提交地址
    method : 'post', //提交方法
    labelWidth : 100,
    labelAlign : 'right',
    items : [row1, row2],
    buttonAlign : 'center', //按钮对其方式
    style : 'padding:10px',
    buttons : [{
        text : '查询',
        handler : function() {
            grid.store.reload({
                params : {
                    a1 : Ext.getCmp('a1').getValue(),
                    b1 : Ext.getCmp('b1').getValue(),
                    stdate : Ext.util.Format.date(Ext.getCmp('stdate').getValue(), 'Y-m-d'),
                    tmdate : Ext.util.Format.date(Ext.getCmp('tmdate').getValue(), 'Y-m-d'),
                    start : 0,
                    limit : 10
                }
            });
        }
    }, {
        text : '重置',
        handler : function() {//点击取消按钮的操作事件
            form.getForm().reset();
        }
    }, {
        text : '导出统计结果',
        handler : function() {//点击取消按钮的操作事件
            Ext.Msg.confirm('提示', '导出Excel文件可能会花费一定时间，是否确认按照所选条件导出行政执法检查情况统计表？', function(btn) {
                if(btn == 'yes') {
                    document.forms[0].action="AdminEnforceManagerAction.do?method=generateAeinspectionAnlExcel&form_a1="
                    + encodeURI(encodeURI(Ext.getCmp('a1').getValue()))
                    + "&form_b1="
                    + encodeURI(encodeURI(Ext.getCmp('b1').getValue()))
                    + "&form_stdate="
                    + Ext.util.Format.date(Ext.getCmp('stdate').getValue(), 'Y-m-d')
                    + "&form_tmdate="
                    + Ext.util.Format.date(Ext.getCmp('tmdate').getValue(), 'Y-m-d');
	                document.forms[0].submit();
                }
            });
        }
    }]
});
//=============================================  GRID ====================================
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
    header : '派出检查人员',
    dataIndex : 'c1'
}, {
    header : '现场检查',
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
    header : '调阅工作文件资料',
    dataIndex : 'k1'
}, {
    header : '制作询问笔录',
    dataIndex : 'l1'
}, {
    header : '制作工作底稿',
    dataIndex : 'm1'
}, {
    header : '执法方式',
    dataIndex : 'n1'
}, {
    header : '已实施处罚金额',
    dataIndex : 'p1'
}]);
var ds = new Ext.data.Store({
    proxy : new Ext.data.HttpProxy({
        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=pageAeInspectionAnalyse',
        method : 'POST',
        baseParams : {
            a1 : Ext.getCmp('a1').getValue(),
            b1 : Ext.getCmp('b1').getValue(),
            stdate : Ext.util.Format.date(Ext.getCmp('stdate').getValue(), 'Y-m-d'),
            tmdate : Ext.util.Format.date(Ext.getCmp('tmdate').getValue(), 'Y-m-d')
        }
    }),
    reader : new Ext.data.JsonReader({
        totalProperty : 'totalProperty',
        root : 'root'
    }, ['id', 'a1', 'b1', 'c1', 'd1', 'e1', 'f1', 'g1', 'h1', 'i1', 'j1', 'k1', 'l1', 'm1', 'n1', 'p1'])
});
ds.load({
    params : {
        a1 : Ext.getCmp('a1').getValue(),
        b1 : Ext.getCmp('b1').getValue(),
        stdate : Ext.util.Format.date(Ext.getCmp('stdate').getValue(), 'Y-m-d'),
        tmdate : Ext.util.Format.date(Ext.getCmp('tmdate').getValue(), 'Y-m-d'),
        start : 0,
        limit : 10
    }
});
ds.on("beforeload", function(thiz, options) {
    thiz.baseParams["a1"] = Ext.getCmp('a1').getValue();
    thiz.baseParams["b1"] = Ext.getCmp('b1').getValue();
    thiz.baseParams["stdate"] = Ext.util.Format.date(Ext.getCmp('stdate').getValue(), 'Y-m-d');
    thiz.baseParams["tmdate"] = Ext.util.Format.date(Ext.getCmp('tmdate').getValue(), 'Y-m-d');
    // thiz.baseParams["name"] = Ext.get("txtName").dom.value;
});
var grid = new Ext.grid.GridPanel({
    id : 'grid', // 设置标识ID，方便以后引用！
    title : '行政执法工作情况统计表', // 标题
    //renderTo : 'workBasises',// 显示表格的地方
    // sm : sm,// 复选框
    cm : cm, // 列模型
    ds : ds, // 数据源
    region : 'center',
    // layout:'form'  //从上往下布局
    // renderTo : Ext.getBody(),
    // applyTo : 'grid',
    stripeRows : true, // 加上行条纹
    loadMask : true, // 加载数据时遮蔽表格
    border : true, // 加上边框
    frame : true, // 显示天蓝色圆角框
    autoHeight : true, // 自动设置高度，这个配置很重要
    width : 1050,
    //x : 1, // 设置初始位置横坐标
    //y : 1, // 设置初始位置纵坐标
    // enableDragDrop : true,//容许行的拖曳
    // collapsible : true, // 面板可以折叠
    // titleCollapse : true,// 单击表头任何地方都可以折叠
    //floating : true,
    viewConfig : {
        //True表示为自动展开/缩小列的宽度以适应grid的宽度，这样就不会出现水平的滚动条
        forceFit : true
    },
    bbar : new Ext.PagingToolbar({
        pageSize : 10,
        store : ds,
        displayInfo : true,
        displayMsg : '显示第{0}条到{1}条  记录共{2}条',
        emptyMsg : '没有记录'
    })
});

var _panel = new Ext.Panel({
    title : "",
    // renderTo : Ext.getBody(),
    applyTo : '_panel',
    frame : true,
    width : 1050,
    height : 480,
    layout : "border",
    items : [form, grid],
    buttonAlign : 'center', //按钮对其方式
    buttons : [{
        text : "返回",
        handler : function() {
            window.history.go(-1);
        }
    }]
})
</script>
</html>