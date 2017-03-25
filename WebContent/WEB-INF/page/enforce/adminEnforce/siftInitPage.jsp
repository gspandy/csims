<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
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
		<style type="text/css"></style>
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
	</head>
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
						<html:form action="/AdminEnforceManagerAction.do?method=toAdminEnforceInitPageFromSift" method="post" >
						<html:hidden property="selectAeOthers"/>
						<html:hidden property="selectAeMasterMans"/>
						<html:hidden property="selectAeedOrg"/>
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
	        id : 'isJoinWithin2Years',
	        name : 'isJoinWithin2Years',
	        xtype : 'combo',
	        fieldLabel : '近两年是否参加过',
	        displayField : 'isJoin',
	        triggerAction : 'all',
	        emptyText : '请选择',
	        mode : 'local',
	        // readOnly:true,
	        width : 180,
	        allowBlank : false,
	        editable : false,
	        forceSelection : true,
	        store : new Ext.data.SimpleStore({
	            fields : ['isJoin'],
	            data : [['是']]
	        })
	    }]
    }, {
        columnWidth : .5,
        layout : 'form',
        items : [{
	        id : 'aeLevel',
	        name : 'aeLevel',
	        xtype : 'combo',
	        fieldLabel : '执法能力等级',
	        displayField : 'levels',
	        triggerAction : 'all',
	        emptyText : '请选择',
	        mode : 'local',
	        // readOnly:true,
	        width : 180,
	        allowBlank : false,
	        editable : false,
	        forceSelection : true,
	        store : new Ext.data.SimpleStore({
	            fields : ['levels'],
	            data : [['A'], ['B'], ['C']]
	        })
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
	            name : 'joinTime',
	            id : 'joinTime',
	            regex : /^\d+$/,
                regexText : '请输入正确的数据类型',
	            width : 180
	        }]
	    }, {
	        columnWidth : .5,
	        layout : 'form',
	        items : [{
	            xtype : 'textfield',
	            fieldLabel : '筛选人数',
	            name : 'peopleQuantity',
	            id : 'peopleQuantity',
	            regex : /^\d+$/,
                regexText : '请输入正确的数据类型',
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
                	isJoinWithin2Years : Ext.getCmp('isJoinWithin2Years').getValue(),
                	aeLevel : Ext.getCmp('aeLevel').getValue(),
                	joinTime : Ext.getCmp('joinTime').getValue(),
                	peopleQuantity : Ext.getCmp('peopleQuantity').getValue(),
                    start : 0,
                    limit : 100
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
var sm = new Ext.grid.CheckboxSelectionModel({ dataIndex: "id", singleSelect: false } ); 
var cm = new Ext.grid.ColumnModel([sm, {
    header : 'ID',
    dataIndex : 'id',
    hidden : true
}, {
    header : '姓名',
    dataIndex : 'pepname',
    width : 200
}, {
    header : '执法证编号',
    dataIndex : 'certno'
}, {
    header : '身份证号',
    dataIndex : 'cardid'
}, {
    header : '所属机构',
    dataIndex : 'orgnm'
}]);
var ds = new Ext.data.Store({
    proxy : new Ext.data.HttpProxy({
        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=siftAePeoples',
        method : 'POST',
        baseParams : {
        	isJoinWithin2Years : Ext.getCmp('isJoinWithin2Years').getValue(),
        	aeLevel : Ext.getCmp('aeLevel').getValue(),
        	joinTime : Ext.getCmp('joinTime').getValue(),
        	peopleQuantity : Ext.getCmp('peopleQuantity').getValue()
        }
    }),
    reader : new Ext.data.JsonReader({
        totalProperty : 'totalProperty',
        root : 'root'
    }, ['id', 'pepname', 'certno', 'cardid', 'orgnm'])
});
ds.load({
    params : {
    	isJoinWithin2Years : Ext.getCmp('isJoinWithin2Years').getValue(),
    	aeLevel : Ext.getCmp('aeLevel').getValue(),
    	joinTime : Ext.getCmp('joinTime').getValue(),
    	peopleQuantity : Ext.getCmp('peopleQuantity').getValue(),
        start : 0,
        limit : 100
    }
});
ds.on("beforeload", function(thiz, options) {
    thiz.baseParams["isJoinWithin2Years"] = Ext.getCmp('isJoinWithin2Years').getValue();
    thiz.baseParams["aeLevel"] = Ext.getCmp('aeLevel').getValue();
    thiz.baseParams["joinTime"] = Ext.getCmp('joinTime').getValue();
    thiz.baseParams["peopleQuantity"] = Ext.getCmp('peopleQuantity').getValue();
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
 //   bbar : new Ext.PagingToolbar({
 //     id : 'pageBar',
 //     pageSize : 10,
       // store : ds,
       // displayInfo : true,
       // displayMsg : '显示第{0}条到{1}条  记录共{2}条',
       // emptyMsg : '没有记录'
    //}),
    listeners : {
        'rowdblclick' : function(grid, rowIndex, e) {
            var record = grid.getSelectionModel().getSelected();
            // alert(record.get('id'));
            grid.store.remove(record);
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
	        id : 'aeLevel2',
	        name : 'aeLevel2',
	        xtype : 'combo',
	        fieldLabel : '执法能力等级',
	        displayField : 'levels',
	        triggerAction : 'all',
	        emptyText : '请选择',
	        mode : 'local',
	        // readOnly:true,
	        width : 180,
	        allowBlank : false,
	        editable : false,
	        forceSelection : true,
	        store : new Ext.data.SimpleStore({
	            fields : ['levels'],
	            data : [['A'], ['B'], ['C']]
	        })
	    }]
    }, {
        columnWidth : .5,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '筛选人数',
            name : 'peopleQuantity2',
            id : 'peopleQuantity2',
            regex : /^\d+$/,
            regexText : '请输入正确的数据类型',
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
    items : [row3],
    buttonAlign : 'center',
    style : 'padding:10px',
    buttons : [{
        text : '筛选',
        handler : function() {
            grid2.store.reload({
                params : {
                	aeLevel : Ext.getCmp('aeLevel2').getValue(),
                	peopleQuantity : Ext.getCmp('peopleQuantity2').getValue(),
                    start : 0,
                    limit : 100
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

var sm2 = new Ext.grid.CheckboxSelectionModel({ dataIndex: "id", singleSelect: false } ); 
var cm2 = new Ext.grid.ColumnModel([sm2, {
    header : 'ID',
    dataIndex : 'id',
    hidden : true
}, {
    header : '姓名',
    dataIndex : 'pepname',
    width : 200
}, {
    header : '执法证编号',
    dataIndex : 'certno'
}, {
    header : '身份证号',
    dataIndex : 'cardid'
}, {
    header : '所属机构',
    dataIndex : 'orgnm'
}]);
var ds2 = new Ext.data.Store({
    proxy : new Ext.data.HttpProxy({
        url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=siftAePeoples',
        method : 'POST',
        baseParams : {
        	aeLevel : Ext.getCmp('aeLevel2').getValue(),
        	peopleQuantity : Ext.getCmp('peopleQuantity2').getValue()
        }
    }),
    reader : new Ext.data.JsonReader({
        totalProperty : 'totalProperty',
        root : 'root'
    }, ['id', 'pepname', 'certno', 'cardid', 'orgnm'])
});
ds2.load({
    params : {
    	aeLevel : Ext.getCmp('aeLevel2').getValue(),
    	peopleQuantity : Ext.getCmp('peopleQuantity2').getValue(),
        start : 0,
        limit : 100
    }
});
ds2.on("beforeload", function(thiz, options) {
    thiz.baseParams["aeLevel"] = Ext.getCmp('aeLevel2').getValue();
    thiz.baseParams["peopleQuantity"] = Ext.getCmp('peopleQuantity2').getValue();
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
    listeners : {
        'rowdblclick' : function(grid, rowIndex, e) {
            var record = grid2.getSelectionModel().getSelected();
            grid2.store.remove(record);
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
	        id : 'hasNotBeCheckWithin2Years',
	        name : 'hasNotBeCheckWithin2Years',
	        xtype : 'combo',
	        fieldLabel : '近两年未接受过检查',
	        displayField : 'hasNotBeCheck',
	        triggerAction : 'all',
	        emptyText : '请选择',
	        mode : 'local',
	        // readOnly:true,
	        width : 180,
	        allowBlank : false,
	        editable : false,
	        forceSelection : true,
	        store : new Ext.data.SimpleStore({
	            fields : ['hasNotBeCheck'],
	            data : [['是']]
	        })
	    }]
    }, {
        columnWidth : .5,
        layout : 'form',
        items : [{
	        id : 'adjustResult',
	        name : 'adjustResult',
	        xtype : 'combo',
	        fieldLabel : '综合评价结果',
	        displayField : 'ar',
	        triggerAction : 'all',
	        emptyText : '请选择',
	        mode : 'local',
	        // readOnly:true,
	        width : 180,
	        allowBlank : false,
	        editable : false,
	        forceSelection : true,
	        store : new Ext.data.SimpleStore({
	            fields : ['ar'],
	            data : [['A'], ['B'], ['C']]
	        })
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
	            fieldLabel : '机构被检查次数',
	            name : 'aeedTime',
	            id : 'aeedTime',
	            regex : /^\d+$/,
                regexText : '请输入正确的数据类型',
	            width : 180
	        }]
	    }, {
	        columnWidth : .5,
	        layout : 'form',
	        items : [{
	            xtype : 'textfield',
	            fieldLabel : '筛选数量',
	            name : 'peopleQuantity3',
	            id : 'peopleQuantity3',
	            regex : /^\d+$/,
                regexText : '请输入正确的数据类型',
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
            grid3.store.reload({
                params : {
                	hasNotBeCheckWithin2Years : Ext.getCmp('hasNotBeCheckWithin2Years').getValue(),
                	adjustResult : Ext.getCmp('adjustResult').getValue(),
                	aeedTime : Ext.getCmp('aeedTime').getValue(),
                	peopleQuantity : Ext.getCmp('peopleQuantity3').getValue(),
                    start : 0,
                    limit : 100
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

var sm3 = new Ext.grid.CheckboxSelectionModel({ dataIndex: "NO", singleSelect: false } ); 
var cm3 = new Ext.grid.ColumnModel([sm3, {
    header : 'ID',
    dataIndex : 'NO',
    hidden : true
}, {
    header : '机构名称',
    dataIndex : 'NAME',
    width : 200
}, {
    header : '所属人民银行',
    dataIndex : 'PCBNAME'
}]);
var ds3 = new Ext.data.Store({
    proxy : new Ext.data.HttpProxy({
    	url : '<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=siftAeedOrg',
        method : 'POST',
        baseParams : {
        	hasNotBeCheckWithin2Years : Ext.getCmp('hasNotBeCheckWithin2Years').getValue(),
        	adjustResult : Ext.getCmp('adjustResult').getValue(),
        	aeedTime : Ext.getCmp('aeedTime').getValue(),
        	peopleQuantity : Ext.getCmp('peopleQuantity3').getValue()
        }
    }),
    reader : new Ext.data.JsonReader({
        totalProperty : 'totalProperty',
        root : 'root'
    }, ['NO', 'NAME', 'PCBNAME'])
});
ds3.load({
    params : {
    	hasNotBeCheckWithin2Years : Ext.getCmp('hasNotBeCheckWithin2Years').getValue(),
    	adjustResult : Ext.getCmp('adjustResult').getValue(),
    	aeedTime : Ext.getCmp('aeedTime').getValue(),
    	peopleQuantity : Ext.getCmp('peopleQuantity3').getValue(),
        start : 0,
        limit : 100
    }
});
ds3.on("beforeload", function(thiz, options) {
    thiz.baseParams["hasNotBeCheckWithin2Years"] = Ext.getCmp('hasNotBeCheckWithin2Years').getValue();
    thiz.baseParams["adjustResult"] = Ext.getCmp('adjustResult').getValue();
    thiz.baseParams["aeedTime"] = Ext.getCmp('aeedTime').getValue();
    thiz.baseParams["peopleQuantity"] = Ext.getCmp('peopleQuantity3').getValue();
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
    listeners : {
        'rowdblclick' : function(grid, rowIndex, e) {
            var record = grid3.getSelectionModel().getSelected();
            grid3.store.remove(record);
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
    },{
        text : "立项",
        handler : function() {
        	var selectionModel = grid.getSelectionModel();
        	selectionModel.selectAll();
    		var record = selectionModel.getSelections();
    		var ids = "";
            for(var i = 0; i < record.length; i++){
                ids += (record[i].get("pepname") + '--' + record[i].get("certno"));
                if(i < record.length - 1){
                    ids = ids + ",";
                }
            }
            $("input[name='selectAeOthers']").val(ids);
            
            var selectionModel2 = grid2.getSelectionModel();
        	selectionModel2.selectAll();
    		var record2 = selectionModel2.getSelections();
    		var ids2 = "";
            for(var i = 0; i < record2.length; i++){
                ids2 += (record2[i].get("pepname") + '--' + record2[i].get("certno"));
                if(i < record2.length-1){
                    ids2 = ids2 + ",";
                }
            }
            $("input[name='selectAeMasterMans']").val(ids2);
            
            var selectionModel3 = grid3.getSelectionModel();
        	selectionModel3.selectAll();
    		var record3 = selectionModel3.getSelections();
    		var ids3 = "";
            for(var i = 0; i < record3.length; i++){
                ids3 += (record3[i].get("NAME") + '--' + record3[i].get("NO"));
                if(i < record3.length-1){
                    ids3 = ids3 + ",";
                }
            }
            $("input[name='selectAeedOrg']").val(ids3);
            
            // alert($("input[name='selectAeOthers']").val());
            // alert($("input[name='selectAeMasterMans']").val());
            // alert($("input[name='selectAeedOrg']").val());
            
            Ext.Msg.confirm('提示', '是否确认将所筛选结果用于立项？', function(btn) {
				if(btn == 'yes') {
					document.forms[0].action="AdminEnforceManagerAction.do?method=toAdminEnforceInitPageFromSift";
                    document.forms[0].submit(); 
				}
			});
        }
    }]
})



 
</script>

</html>