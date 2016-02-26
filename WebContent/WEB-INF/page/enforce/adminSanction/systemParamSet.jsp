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
								系统参数维护
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								系统基础参数设置
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
	</body>
<script type="text/javascript">
Ext.MessageBox.minWidth = 400;
Ext.BLANK_IMAGE_URL = "<%=request.getContextPath()%>/ext-3.2.0/resources/images/default/tree/s.gif";
var row1 = {
    layout : 'column', //从左往右布局
    items : [{
        columnWidth : .5, //该列有整行中所占百分比
        layout : 'form', //从上往下布局
        items : [{
            xtype : 'combo',
            fieldLabel : '数据类型',
            id : 'type',
            name : 'type',
            displayField : 'typeDesc',
            valueField : 'type',
            triggerAction : 'all',
            emptyText : '请选择',
            mode : 'local',
            width : 350,
            allowBlank : true,
            editable : false,
            forceSelection : false,
            store : new Ext.data.SimpleStore({
                fields : ['type', 'typeDesc'],
                data : [['', '请选择'], ['LXYJ', '立项依据'], ['JCYJ', '检查依据'], ['JCFS', '检查方式'], ['WTGK', '问题概况'], ['CLJD', '处理决定'], ['AY', '案由']]
            })
        }]
    }, {
        columnWidth : .5,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '数据编号',
            name : 'dkey',
            id : 'dkey',
            width : 350
        }]
    }]
};
var row2 = {
    layout : 'column', //从左往右布局
    items : [{
        columnWidth : .5,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '数据值',
            name : 'dvalue',
            id : 'dvalue',
            width : 350
        }]
    }]
};
var form = new Ext.form.FormPanel({
    title : '',
    width : 1050,
    //height:250,
    autoHeight : true,
    bodyStyle : 'padding:5px 5px 0',
    region : 'north',
    // renderTo : Ext.getBody(),
    // applyTo : 'form',
    frame : true,
    layout : 'form',
    url : '',
    method : 'post',
    labelWidth : 100,
    labelAlign : 'right',
    items : [row1, row2],
    buttonAlign : 'center',
    style : 'padding:10px',
    buttons : [{
        text : '查询',
        handler : function() {
            grid.store.reload({
                params : {
                    type : Ext.getCmp('type').getValue(),
                    dkey : Ext.getCmp('dkey').getValue(),
                    dvalue : Ext.getCmp('dvalue').getValue(),
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
//=============================================  GRID ====================================
var cm = new Ext.grid.ColumnModel([{
    header : 'ID',
    dataIndex : 'id', hidden : true
   // ,    width: 25
}, {
    header : '数据类型',
    dataIndex : 'type'
   // ,    width: 15
}, {
    header : '数据编号',
    dataIndex : 'dkey'
   //, width: 15
}, {
    header : '数据值',
    dataIndex : 'dvalue'
}, {
    header : '父数据项',
    dataIndex : 'stat',
    hidden : true
}, {
    header : '是否可用',
    dataIndex : 'isenable',
    hidden : true
}, {
    header : '是否能选中',
    dataIndex : 'flag',
    hidden : true
}, {
    header : '数据描述',
    dataIndex : 'DSumry',
    hidden : true
}]);
var ds = new Ext.data.Store({
    proxy : new Ext.data.HttpProxy({
        url : '<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=pageDctionarys',
        method : 'POST',
        baseParams : {
            type : Ext.getCmp('type').getValue(),
            dkey : Ext.getCmp('dkey').getValue(),
            dvalue : Ext.getCmp('dvalue').getValue()
        }
    }),
    reader : new Ext.data.JsonReader({
        totalProperty : 'totalProperty',
        root : 'root'
    }, ['id', 'type', 'dkey', 'dvalue', 'stat', 'flag', 'DSumry','isenable'])
});
ds.load({
    params : {
        type : Ext.getCmp('type').getValue(),
        dkey : Ext.getCmp('dkey').getValue(),
        dvalue : Ext.getCmp('dvalue').getValue(),
        start : 0,
        limit : 10
    }
});
ds.on("beforeload", function(thiz, options) {
    thiz.baseParams["type"] = Ext.getCmp('type').getValue();
    thiz.baseParams["dkey"] = Ext.getCmp('dkey').getValue();
    thiz.baseParams["dvalue"] = Ext.getCmp('dvalue').getValue();
});
var grid = new Ext.grid.GridPanel({
    id : 'grid',
    title : '系统基础参数',
    //renderTo : 'workBasises',// 显示表格的地方
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
        id : 'pageBar',
        pageSize : 10,
        store : ds,
        displayInfo : true,
        displayMsg : '显示第{0}条到{1}条  记录共{2}条',
        emptyMsg : '没有记录'
    }),
    listeners : {
        'rowdblclick' : function(grid, rowIndex, e) {
            //  grid.getSelectionModel().clearSelections();
            //  grid.getSelectionModel().selectRow(rowIndex);
            //  alert(rowIndex+'---'+grid.id);
            var record = grid.getSelectionModel().getSelected();
            //   alert(record.get("id"));
            modifyParam(record);
        }
    }
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
    }, {
        text : "新增",
        handler : function() {
            addParam();
        }
    }, {
        text : "删除",disabled : true,
        handler : function() {
            var record = grid.getSelectionModel().getSelected();
            if(record==null)return ;
            Ext.Msg.confirm('提示', '是否确认删除系统参数' + record.get('dkey') + '？', function(btn) {
                if(btn == 'yes') {
                    Ext.Ajax.request({
                        url : '<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=deleteParam',
                        params : {
                            id : record.get('id')
                        },
                        method : 'POST',
                        callback : function(options, success, response) {
                            if(success) {
                                // alert(response.responseText);
                                var responseJson = Ext.util.JSON.decode(response.responseText);
                                Ext.Msg.alert("提示", responseJson.msg);
                                var pagingToolbar = Ext.getCmp('pageBar');
                                grid.store.reload({
                                    params : {
                                        type : Ext.getCmp('type').getValue(),
                                        dkey : Ext.getCmp('dkey').getValue(),
                                        dvalue : Ext.getCmp('dvalue').getValue(),
                                        start : (pagingToolbar.getPageData().activePage - 1) * 10,
                                        limit : 10
                                    }
                                });
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
            });
        }
    }]
});
// ======================修改系统参数========================
function modifyParam(record) {
    var row1 = {
        layout : 'column', //从左往右布局
        items : [{
            columnWidth : .5, //该列有整行中所占百分比
            layout : 'form', //从上往下布局
            items : [{
                xtype : 'combo',
                fieldLabel : '数据类型',
                id : 'type_m',
                // name : 'type_m',
                hiddenName : 'type_m_hidden',
                displayField : 'typeDesc',
                valueField : 'type',
                triggerAction : 'all',
                emptyText : '请选择',
                mode : 'local',
                width : 250,
                allowBlank : true,
                editable : false,
                forceSelection : false,
                store : new Ext.data.SimpleStore({
                    fields : ['type', 'typeDesc'],
                    data : [['', '请选择'], ['LXYJ', '立项依据'], ['JCYJ', '检查依据'], ['JCFS', '检查方式'], ['WTGK', '问题概况'], ['CLJD', '处理决定'], ['AY', '案由']]
                }),
                value : record.get("type")
            }]
        }, {
            columnWidth : .5,
            layout : 'form',
            items : [{
                xtype : 'textfield',
                fieldLabel : '数据编号',
                name : 'dkey_m',
                id : 'dkey_m',
                width : 250,
                allowBlank : false,
                readOnly : true,disabled : true,
                value : record.get("dkey")
            }]
        }]
    };
    var row2 = {
        layout : 'column',
        items : [{
            columnWidth : 1,
            layout : 'form',
            items : [{
                xtype : 'textfield',
                fieldLabel : '数据值',
                name : 'dvalue_m',
                id : 'dvalue_m',
                width : 629,
                allowBlank : false,
                value : record.get("dvalue")
            }]
        }]
    };

    var row4 = {
        layout : 'column',
        items : [{
            columnWidth : 1,
            layout : 'form',
            items : [{
                xtype : 'textarea',
                fieldLabel : '数据描述',
                id : 'dsumry_m',
                name : 'dsumry_m',
                height : 200,
                width : 629,
                // grow : true,
                allowBlank : true,
                value : record.get("DSumry")
            }]
        }]
    };

    var row5 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                xtype : 'combo',
                fieldLabel : '父数据项',
                id : 'stat_m',
             //   name : 'stat_m',
             hiddenName : 'stat_m_hidden',   
                displayField : 'statDesc',
                valueField : 'stat',
                triggerAction : 'all',
                mode : 'local',
                width : 250,
                allowBlank : true,
                editable : false,
                forceSelection : false,
                store : new Ext.data.SimpleStore({
                    fields : ['stat', 'statDesc'],
                    data : <%= request.getAttribute("stats")%>
                }),
                value : record.get("stat")
            }]
        }, {
            columnWidth : .5,
            layout : 'form',
            items : [{
                xtype : 'combo',
                fieldLabel : '是否可被选中',
                id : 'flag_m',
             //   name : 'flag_m',
             hiddenName : 'flag_m_hidden',   
                displayField : 'flagDesc',
                valueField : 'flag',
                triggerAction : 'all',
                mode : 'local',
                width : 250,
                allowBlank : true,
                editable : false,
                forceSelection : false,
                store : new Ext.data.SimpleStore({
                    fields : ['flag', 'flagDesc'],
                    data : [['false', '否'], ['true', '是']]
                }),
                value : record.get("flag")
            }]
        }]
    };
      var row7 = {
        layout : 'column',
        items : [{
            columnWidth : 1,
            layout : 'form',
            items : [{
                xtype : 'combo',
                fieldLabel : '是否可用',
                id : 'isenable_m',
               // name : 'isenable_m',
                   hiddenName : 'isenable_m_hidden',   
                displayField : 'isenableDesc',
                valueField : 'isenable',
                triggerAction : 'all',
                mode : 'local',
                width : 629,
                allowBlank : true,
                editable : false,
                forceSelection : false,
                store : new Ext.data.SimpleStore({
                    fields : ['isenable', 'isenableDesc'],
                    data : [ ['true', '是'],['false', '否']]
                }),
                value : record.get("isenable")
            }]
        }]
    };
    var row6 = {
        layout : 'form',
        items : [{
            xtype : 'textfield',
            name : 'id_m',
            id : 'id_m',
            hidden : true,
            hideLabel : true,
            value : record.get("id")
        }]
    };
    var fp = new Ext.form.FormPanel({
        title : '修改系统参数',
        width : 800,
        //height:250,
        autoHeight : true,
        bodyStyle : 'padding:5px 5px 0',
        renderTo : Ext.getBody(),
        frame : true,
        layout : 'form',
        url : '',
        method : 'post',
        labelWidth : 90,
        labelAlign : 'right',
        items : [row1, row2, row4, row5, row7, row6],
        buttonAlign : 'center',
        style : 'padding:10px',
        buttons : [{
            text : '确定',
            handler : function() {
                Ext.Msg.confirm('提示', '是否确认维护系统参数？', function(btn) {
                    if(btn == 'yes') {
                        fp.getForm().submit({
                            url : '<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=saveDctionary',
                            waitTitle : '请等待',
                            waitMsg : '正在维护系统参数',
                            success : function(form, action) {
                                //url后台返回的数据{success:true,msg:'成功'}
                                Ext.Msg.alert('提示', action.result.msg);
                                var pagingToolbar = Ext.getCmp('pageBar');
                                grid.store.reload({
                                    params : {
                                        type : Ext.getCmp('type').getValue(),
                                        dkey : Ext.getCmp('dkey').getValue(),
                                        dvalue : Ext.getCmp('dvalue').getValue(),
                                        start : (pagingToolbar.getPageData().activePage - 1) * 10,
                                        limit : 10
                                    }
                                });
                                addBasisWin.close();
                                fp.destroy();
                                addBasisWin.destroy();
                            },
                            failure : function(form, action) {
                                Ext.Msg.alert('提示', '保存系统参数失败！请重新填写');
                            }
                        });
                    }
                });
            }
        }, {
            text : '关闭',
            handler : function() {//点击取消按钮的操作事件
                addBasisWin.close();
                fp.destroy();
                addBasisWin.destroy();
            }
        }]
    });

    var addBasisWin = new Ext.Window({
        title : '系统基础参数设置',
        width : 800,
        height : 500,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        items : [fp]
    });
    addBasisWin.show();
}

// ======================新增系统参数========================
function addParam(record) {
    var row1 = {
        layout : 'column', //从左往右布局
        items : [{
            columnWidth : .5, //该列有整行中所占百分比
            layout : 'form', //从上往下布局
            items : [{
                xtype : 'combo',
                fieldLabel : '数据类型',
                id : 'type_m',
                // name : 'type_m',
                hiddenName : 'type_m_hidden',
                displayField : 'typeDesc',
                valueField : 'type',
                triggerAction : 'all',
                emptyText : '请选择',
                mode : 'local',
                width : 250,
                allowBlank : false,
                editable : false,
                forceSelection : false,
                store : new Ext.data.SimpleStore({
                    fields : ['type', 'typeDesc'],
                    data : [['', '请选择'], ['LXYJ', '立项依据'], ['JCYJ', '检查依据'], ['JCFS', '检查方式'], ['WTGK', '问题概况'], ['CLJD', '处理决定'], ['AY', '案由']]
                })
            }]
        }, {
            columnWidth : .5,
            layout : 'form',
            items : [{
                xtype : 'textfield',
                fieldLabel : '数据编号',
                name : 'dkey_m',
                id : 'dkey_m',
                width : 250,
                 allowBlank : true,disabled : true,
                readOnly : true
            }]
        }]
    };
    var row2 = {
        layout : 'column',
        items : [{
            columnWidth : 1,
            layout : 'form',
            items : [{
                xtype : 'textfield',
                fieldLabel : '数据值',
                name : 'dvalue_m',
                id : 'dvalue_m',
                width : 629,
                allowBlank : false
            }]
        }]
    };

    var row4 = {
        layout : 'column',
        items : [{
            columnWidth : 1,
            layout : 'form',
            items : [{
                xtype : 'textarea',
                fieldLabel : '数据描述',
                id : 'dsumry_m',
                name : 'dsumry_m',
                height : 200,
                width : 629,
                // grow : true,
                allowBlank : true
            }]
        }]
    };
    var row5 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                xtype : 'combo',
                fieldLabel : '父数据项',
                id : 'stat_m',
             //   name : 'stat_m',
             hiddenName : 'stat_m_hidden',   
                displayField : 'statDesc',
                valueField : 'stat',
                triggerAction : 'all',
                mode : 'local',
                width : 250,
                allowBlank : true,
                editable : false,
                forceSelection : false,
                store : new Ext.data.SimpleStore({
                    fields : ['stat', 'statDesc'],
                    data : <%= request.getAttribute("stats")%>
                }) 
            }]
        }, {
            columnWidth : .5,
            layout : 'form',
            items : [{
                xtype : 'combo',
                fieldLabel : '是否可被选中',
                id : 'flag_m',
                name : 'flag_m',
                hiddenName : 'flag_m_hidden',
                displayField : 'flagDesc',
                valueField : 'flag',
                triggerAction : 'all',
                mode : 'local',
                width : 250,
                allowBlank : true,
                editable : false,
                forceSelection : false,
                store : new Ext.data.SimpleStore({
                    fields : ['flag', 'flagDesc'],
                    data : [['false', '否'], ['true', '是']]
                })
            }]
        }]
    };
     var row7 = {
        layout : 'column',
        items : [{
            columnWidth : 1,
            layout : 'form',
            items : [{
                xtype : 'combo',
                fieldLabel : '是否可用',
                id : 'isenable_m',
                name : 'isenable_m',
                hiddenName : 'isenable_m_hidden',
                displayField : 'isenableDesc',
                valueField : 'isenable',
                triggerAction : 'all',
                mode : 'local',
                width : 629,
              allowBlank : false,
                editable : false,
                forceSelection : false,
                store : new Ext.data.SimpleStore({
                    fields : ['isenable', 'isenableDesc'],
                    data : [ ['true', '是'],['false', '否']]
                })
            }]
        }]
    };
    var row6 = {
        layout : 'form',
        items : [{
            xtype : 'textfield',
            name : 'id_m',
            id : 'id_m',
            hidden : true,
            hideLabel : true
        }]
    };
    var fp = new Ext.form.FormPanel({
        title : '新增系统参数',
        width : 800,
        //height:250,
        autoHeight : true,
        bodyStyle : 'padding:5px 5px 0',
        renderTo : Ext.getBody(),
        frame : true,
        layout : 'form',
        url : '',
        method : 'post',
        labelWidth : 90,
        labelAlign : 'right',
        items : [row1, row2, row4, row5, row7, row6],
        buttonAlign : 'center',
        style : 'padding:10px',
        buttons : [{
            text : '确定',
            handler : function() {
                Ext.Msg.confirm('提示', '是否确认新增系统参数？', function(btn) {
                    if(btn == 'yes') {
                        fp.getForm().submit({
                            url : '<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=saveDctionary',
                            waitTitle : '请等待',
                            waitMsg : '正在新增系统参数',
                            success : function(form, action) {
                                //url后台返回的数据{success:true,msg:'成功'}
                                Ext.Msg.alert('提示', action.result.msg);
                                var pagingToolbar = Ext.getCmp('pageBar');
                                grid.store.reload({
                                    params : {
                                        type : Ext.getCmp('type').getValue(),
                                        dkey : Ext.getCmp('dkey').getValue(),
                                        dvalue : Ext.getCmp('dvalue').getValue(),
                                        start : 0,
                                        limit : 10
                                    }
                                });
                                addBasisWin.close();
                                fp.destroy();
                                addBasisWin.destroy();
                            },
                            failure : function(form, action) {
                                Ext.Msg.alert('提示', '保存系统参数失败！请重新填写');
                            }
                        });
                    }
                });
            }
        }, {
            text : '关闭',
            handler : function() {//点击取消按钮的操作事件
                addBasisWin.close();
                fp.destroy();
                addBasisWin.destroy();
            }
        }]
    });

    var addBasisWin = new Ext.Window({
        title : '系统基础参数设置',
        width : 800,
        height : 500,
        resizable : false,
        closeAction : 'close',
        //autoHeight : true,
        constrainHeader : true,
        modal : true,
        plain : true,
        items : [fp]
    });
    addBasisWin.show();
}
</script>
</html>