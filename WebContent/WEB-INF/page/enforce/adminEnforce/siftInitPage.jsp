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
								随机筛选检查组和被查机构
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" valign="top">
						<html:form
				            action="/AdminEnforceManagerAction.do?method=downloadAtt"
				            method="post">
				        </html:form>
						<div id="_panel" align="center"></div>
					</td>
				</tr>
			</table>
	</body>
<script type="text/javascript">
Ext.MessageBox.minWidth = 400;
var row1 = {
    layout : 'column',
    items : [{
        columnWidth : .5,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '近两年是否参加过',
            name : 'iNo',
            id : 'iNo',
            width : 180
        }]
    }, {
        columnWidth : .5,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '执法能力等级',
            name : 'aeOpnionNo',
            id : 'aeOpnionNo',
            width : 180
        }]
    }]
};

var row2 = {
	    layout : 'column',
	    items : [{
	        columnWidth : .5,
	        layout : 'form',
	        items : [{
	            xtype : 'textfield',
	            fieldLabel : '参与执法检查次数',
	            name : 'aeorgName',
	            id : 'aeorgName',
	            width : 180
	        }]
	    }, {
	        columnWidth : .5,
	        layout : 'form',
	        items : [{
	            xtype : 'textfield',
	            fieldLabel : '筛选人数',
	            name : 'aa',
	            id : 'aa',
	            width : 180
	        }]
	    }]
	};

var form = new Ext.form.FormPanel({
    title : '',
    width : 1050,
    autoHeight : true,
    bodyStyle : 'padding:5px 5px 0',
    region : 'north',
    frame : true,
    layout : 'form',
    url : '',
    method : 'post',
    labelWidth : 120,
    labelAlign : 'right',
    items : [row1, row2],
    buttonAlign : 'center',
    style : 'padding:10px',
    buttons : [{
        text : '筛选',
        handler : function() {
            grid.store.reload({
                params : {
                    iNo : Ext.getCmp('iNo').getValue(),
                    aeOpnionNo : Ext.getCmp('aeOpnionNo').getValue(),
                    aeorgName : Ext.getCmp('aeorgName').getValue(),
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
    }]
});

var cm = new Ext.grid.ColumnModel([{
    header : 'ID',
    dataIndex : 'id',
    hidden : true
}, {
    header : '工作检查记录编号',
    dataIndex : 'aeno',
    width : 200
}, {
    header : '执法意见书编号',
    dataIndex : 'aeopnionno'
}, {
    header : '检查机构',
    dataIndex : 'aeorgnm'
}, {
    header : '被检查机构',
    dataIndex : 'aeedorgnm'
}, {
    header : '录入时间',
    dataIndex : 'createdate'
}]);
var ds = new Ext.data.Store({
    proxy : new Ext.data.HttpProxy({
        url : '<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=pageAeconclusionInfo',
        method : 'POST',
        baseParams : {
            iNo : Ext.getCmp('iNo').getValue(),
            aeOpnionNo : Ext.getCmp('aeOpnionNo').getValue(),
            aeorgName : Ext.getCmp('aeorgName').getValue()
        }
    }),
    reader : new Ext.data.JsonReader({
        totalProperty : 'totalProperty',
        root : 'root'
    }, ['id', 'aeno', 'aeopnionno', 'aeorgnm', 'aeedorgnm', 'createdate'])
});
ds.load({
    params : {
        iNo : Ext.getCmp('iNo').getValue(),
        aeOpnionNo : Ext.getCmp('aeOpnionNo').getValue(),
        aeorgName : Ext.getCmp('aeorgName').getValue(),
        start : 0,
        limit : 10
    }
});
ds.on("beforeload", function(thiz, options) {
    thiz.baseParams["iNo"] = Ext.getCmp('iNo').getValue();
    thiz.baseParams["aeOpnionNo"] = Ext.getCmp('aeOpnionNo').getValue();
    thiz.baseParams["aeorgName"] = Ext.getCmp('aeorgName').getValue();
});
var grid = new Ext.grid.GridPanel({
    id : 'grid',
    title : '',
    cm : cm,
    ds : ds,
    region : 'center',
    stripeRows : true,
   // loadMask : true,
    border : true,
    frame : true,
    autoHeight : true,
    width : 1050,
   // x : 1,
    //y : 1,
    //floating : true,
    viewConfig : {
        forceFit : true
    },
    bbar : new Ext.PagingToolbar({
        id : 'pageBar',
        pageSize : 10,
        store : ds,
        displayInfo : true,
        displayMsg : '显示第{0}条到{1}条  记录共{2}条',
        emptyMsg : '没有记录'
    }),
    listeners : {
        'rowdblclick' : function(grid, rowIndex, e) {
            var record = grid.getSelectionModel().getSelected();
            viewAeCon(record.get('id'));
        }
    }
});

var _panel1 = new Ext.Panel({
    title : '筛选检查组成员',
    frame : true,
    width : 1050,
    height : 1000,
    layout : "border",
    multi: true,
    animate: true,
    items : [form, grid],
    buttonAlign : 'center'
})

//======================================================== 筛选主查人
var row3 = {
    layout : 'column',
    items : [{
        columnWidth : .5,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '近两年是否参加过',
            name : 'iNo2',
            id : 'iNo2',
            width : 180
        }]
    }, {
        columnWidth : .5,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '执法能力等级',
            name : 'aeOpnionNo2',
            id : 'aeOpnionNo2',
            width : 180
        }]
    }]
};

var row4 = {
	    layout : 'column',
	    items : [{
	        columnWidth : .5,
	        layout : 'form',
	        items : [{
	            xtype : 'textfield',
	            fieldLabel : '参与执法检查次数',
	            name : 'aeorgName3',
	            id : 'aeorgName3',
	            width : 180
	        }]
	    }, {
	        columnWidth : .5,
	        layout : 'form',
	        items : [{
	            xtype : 'textfield',
	            fieldLabel : '筛选人数',
	            name : 'aa4',
	            id : 'aa4',
	            width : 180
	        }]
	    }]
	};

var form2 = new Ext.form.FormPanel({
    title : '',
    width : 1050,
    autoHeight : true,
    bodyStyle : 'padding:5px 5px 0',
    region : 'north',
    frame : true,
    layout : 'form',
    url : '',
    method : 'post',
    labelWidth : 120,
    labelAlign : 'right',
    items : [row3, row4],
    buttonAlign : 'center',
    style : 'padding:10px',
    buttons : [{
        text : '筛选',
        handler : function() {
            grid2.store.reload({
                params : {
                    iNo : Ext.getCmp('iNo').getValue(),
                    aeOpnionNo : Ext.getCmp('aeOpnionNo').getValue(),
                    aeorgName : Ext.getCmp('aeorgName').getValue(),
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
    }]
});

var cm2 = new Ext.grid.ColumnModel([{
    header : 'ID',
    dataIndex : 'id',
    hidden : true
}, {
    header : '工作检查记录编号',
    dataIndex : 'aeno',
    width : 200
}, {
    header : '执法意见书编号',
    dataIndex : 'aeopnionno'
}, {
    header : '检查机构',
    dataIndex : 'aeorgnm'
}, {
    header : '被检查机构',
    dataIndex : 'aeedorgnm'
}, {
    header : '录入时间',
    dataIndex : 'createdate'
}]);
var ds2 = new Ext.data.Store({
    proxy : new Ext.data.HttpProxy({
        url : '<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=pageAeconclusionInfo',
        method : 'POST',
        baseParams : {
            iNo : Ext.getCmp('iNo').getValue(),
            aeOpnionNo : Ext.getCmp('aeOpnionNo').getValue(),
            aeorgName : Ext.getCmp('aeorgName').getValue()
        }
    }),
    reader : new Ext.data.JsonReader({
        totalProperty : 'totalProperty',
        root : 'root'
    }, ['id', 'aeno', 'aeopnionno', 'aeorgnm', 'aeedorgnm', 'createdate'])
});
ds2.load({
    params : {
        iNo : Ext.getCmp('iNo').getValue(),
        aeOpnionNo : Ext.getCmp('aeOpnionNo').getValue(),
        aeorgName : Ext.getCmp('aeorgName').getValue(),
        start : 0,
        limit : 10
    }
});
ds2.on("beforeload", function(thiz, options) {
    thiz.baseParams["iNo"] = Ext.getCmp('iNo').getValue();
    thiz.baseParams["aeOpnionNo"] = Ext.getCmp('aeOpnionNo').getValue();
    thiz.baseParams["aeorgName"] = Ext.getCmp('aeorgName').getValue();
});
var grid2 = new Ext.grid.GridPanel({
    id : 'grid2',
    title : '',
    cm : cm2,
    ds : ds2,
    region : 'center',
    stripeRows : true,
    // loadMask : true,
    border : true,
    frame : true,
    autoHeight : true,
    width : 1050,
   // x : 1,
    //y : 1,
    //floating : true,
    viewConfig : {
        forceFit : true
    },
    bbar : new Ext.PagingToolbar({
        id : 'pageBar2',
        pageSize : 10,
        store : ds2,
        displayInfo : true,
        displayMsg : '显示第{0}条到{1}条  记录共{2}条',
        emptyMsg : '没有记录'
    }),
    listeners : {
        'rowdblclick' : function(grid, rowIndex, e) {
            var record = grid.getSelectionModel().getSelected();
            viewAeCon(record.get('id'));
        }
    }
});

var _panel2 = new Ext.Panel({
    title : '筛选主查人',
    frame : true,
    width : 1050,
    height : 1000,
    layout : "border",
    items : [form2, grid2],
    buttonAlign : 'center'
})

//======================================================== 筛选被检查机构
var row5 = {
    layout : 'column',
    items : [{
        columnWidth : .5,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '近两年是否参加过',
            name : 'iNo25',
            id : 'iNo25',
            width : 180
        }]
    }, {
        columnWidth : .5,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '执法能力等级',
            name : 'aeOpnionNo25',
            id : 'aeOpnionNo25',
            width : 180
        }]
    }]
};

var row6 = {
	    layout : 'column',
	    items : [{
	        columnWidth : .5,
	        layout : 'form',
	        items : [{
	            xtype : 'textfield',
	            fieldLabel : '参与执法检查次数',
	            name : 'aeorgName36',
	            id : 'aeorgName36',
	            width : 180
	        }]
	    }, {
	        columnWidth : .5,
	        layout : 'form',
	        items : [{
	            xtype : 'textfield',
	            fieldLabel : '筛选人数',
	            name : 'aa46',
	            id : 'aa46',
	            width : 180
	        }]
	    }]
	};

var form3 = new Ext.form.FormPanel({
    title : '',
    width : 1050,
    autoHeight : true,
    bodyStyle : 'padding:5px 5px 0',
    region : 'north',
    frame : true,
    layout : 'form',
    url : '',
    method : 'post',
    labelWidth : 120,
    labelAlign : 'right',
    items : [row5, row6],
    buttonAlign : 'center',
    style : 'padding:10px',
    buttons : [{
        text : '筛选',
        handler : function() {
            grid2.store.reload({
                params : {
                    iNo : Ext.getCmp('iNo').getValue(),
                    aeOpnionNo : Ext.getCmp('aeOpnionNo').getValue(),
                    aeorgName : Ext.getCmp('aeorgName').getValue(),
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
    }]
});

var cm3 = new Ext.grid.ColumnModel([{
    header : 'ID',
    dataIndex : 'id',
    hidden : true
}, {
    header : '工作检查记录编号',
    dataIndex : 'aeno',
    width : 200
}, {
    header : '执法意见书编号',
    dataIndex : 'aeopnionno'
}, {
    header : '检查机构',
    dataIndex : 'aeorgnm'
}, {
    header : '被检查机构',
    dataIndex : 'aeedorgnm'
}, {
    header : '录入时间',
    dataIndex : 'createdate'
}]);
var ds3 = new Ext.data.Store({
    proxy : new Ext.data.HttpProxy({
        url : '<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=pageAeconclusionInfo',
        method : 'POST',
        baseParams : {
            iNo : Ext.getCmp('iNo').getValue(),
            aeOpnionNo : Ext.getCmp('aeOpnionNo').getValue(),
            aeorgName : Ext.getCmp('aeorgName').getValue()
        }
    }),
    reader : new Ext.data.JsonReader({
        totalProperty : 'totalProperty',
        root : 'root'
    }, ['id', 'aeno', 'aeopnionno', 'aeorgnm', 'aeedorgnm', 'createdate'])
});
ds3.load({
    params : {
        iNo : Ext.getCmp('iNo').getValue(),
        aeOpnionNo : Ext.getCmp('aeOpnionNo').getValue(),
        aeorgName : Ext.getCmp('aeorgName').getValue(),
        start : 0,
        limit : 10
    }
});
ds3.on("beforeload", function(thiz, options) {
    thiz.baseParams["iNo"] = Ext.getCmp('iNo').getValue();
    thiz.baseParams["aeOpnionNo"] = Ext.getCmp('aeOpnionNo').getValue();
    thiz.baseParams["aeorgName"] = Ext.getCmp('aeorgName').getValue();
});
var grid3 = new Ext.grid.GridPanel({
    id : 'grid3',
    title : '',
    cm : cm3,
    ds : ds3,
    region : 'center',
    stripeRows : true,
    // loadMask : true,
    border : true,
    frame : true,
    autoHeight : true,
    width : 1050,
   // x : 1,
    //y : 1,
    //floating : true,
    viewConfig : {
        forceFit : true
    },
    bbar : new Ext.PagingToolbar({
        id : 'pageBar3',
        pageSize : 10,
        store : ds2,
        displayInfo : true,
        displayMsg : '显示第{0}条到{1}条  记录共{2}条',
        emptyMsg : '没有记录'
    }),
    listeners : {
        'rowdblclick' : function(grid, rowIndex, e) {
            var record = grid.getSelectionModel().getSelected();
            viewAeCon(record.get('id'));
        }
    }
});

var _panel3 = new Ext.Panel({
    title : '筛选被检查机构',
    frame : true,
    width : 1050,
    height : 1000,
    layout : "border",
    items : [form3, grid3],
    buttonAlign : 'center'
})


var _panel = new Ext.Panel({
    title : '',
    applyTo : '_panel',
    frame : true,
    width : 1050,
    height : 900,
    layout : "accordion",
    items : [_panel1, _panel2, _panel3],
    buttonAlign : 'center',
    buttons : [{
        text : "返回",
        handler : function() {
            window.history.go(-1);
        }
    }]
})



 
</script>
</html>