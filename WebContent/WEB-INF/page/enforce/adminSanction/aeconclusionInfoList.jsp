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
								行政执法相关信息查询
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								行政执法信息列表
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
        columnWidth : .33,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '检查记录编号',
            name : 'iNo',
            id : 'iNo',
            width : 180
        }]
    }, {
        columnWidth : .33,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '意见书编号',
            name : 'aeOpnionNo',
            id : 'aeOpnionNo',
            width : 180
        }]
    }, {
        columnWidth : .33,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '检查机构',
            name : 'aeorgName',
            id : 'aeorgName',
            width : 180
        }]
    }]
};

var row2 = {
    layout : 'column',
    items : [{
        columnWidth : .33,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '被检查机构',
            name : 'aeedorgName',
            id : 'aeedorgName',
            width : 180
        }]
    }, {
        columnWidth : .33,
        layout : 'form',
        items : [{
            xtype : 'datefield',
            fieldLabel : '开始时间',
            format : "Y-m-d",
            name : 'stDate',
            id : 'stDate',
            width : 180,
            allowBlank : true,
            editable : false
        }]
    }, {
        columnWidth : .33,
        layout : 'form',
        items : [{
            xtype : 'datefield',
            fieldLabel : '结束时间',
            format : "Y-m-d",
            name : 'tmDate',
            id : 'tmDate',
            width : 180,
            allowBlank : true,
            editable : false
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
        text : '查询',
        handler : function() {
            grid.store.reload({
                params : {
                    iNo : Ext.getCmp('iNo').getValue(),
                    aeOpnionNo : Ext.getCmp('aeOpnionNo').getValue(),
                    aeorgName : Ext.getCmp('aeorgName').getValue(),
                    aeedorgName : Ext.getCmp('aeedorgName').getValue(),
                    stDate : Ext.util.Format.date(Ext.getCmp('stDate').getValue(), "Y-m-d"),
                    tmDate : Ext.util.Format.date(Ext.getCmp('tmDate').getValue(), "Y-m-d"),
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
    },{
        text : '检查结论反馈',
        handler : function() {
        	var record = grid.getSelectionModel().getSelected();
            if(record == null || record.get('id') == null){
            	Ext.Msg.alert('提示', '请选择列表中的检查结论');
            }
            viewFeedBack(record.get('id'));
        }
    },{
        text : '行政处罚反馈',
        handler : function() {
        	var record = grid.getSelectionModel().getSelected();
            if(record == null || record.get('id') == null){
            	Ext.Msg.alert('提示', '请选择列表中的检查结论');
            }
            viewPublishFeedBack(record.get('id'));
        }
    }]
});
//=============================================  Detail GRID ====================================
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
            aeorgName : Ext.getCmp('aeorgName').getValue(),
            aeedorgName : Ext.getCmp('aeedorgName').getValue(),
            stDate : Ext.util.Format.date(Ext.getCmp('stDate').getValue(), "Y-m-d"),
            tmDate : Ext.util.Format.date(Ext.getCmp('tmDate').getValue(), "Y-m-d")
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
        aeedorgName : Ext.getCmp('aeedorgName').getValue(),
        stDate : Ext.util.Format.date(Ext.getCmp('stDate').getValue(), "Y-m-d"),
        tmDate : Ext.util.Format.date(Ext.getCmp('tmDate').getValue(), "Y-m-d"),
        start : 0,
        limit : 10
    }
});
ds.on("beforeload", function(thiz, options) {
    thiz.baseParams["iNo"] = Ext.getCmp('iNo').getValue();
    thiz.baseParams["aeOpnionNo"] = Ext.getCmp('aeOpnionNo').getValue();
    thiz.baseParams["aeorgName"] = Ext.getCmp('aeorgName').getValue();
    thiz.baseParams["aeedorgName"] = Ext.getCmp('aeedorgName').getValue();
    thiz.baseParams["stDate"] = Ext.util.Format.date(Ext.getCmp('stDate').getValue(), "Y-m-d");
    thiz.baseParams["tmDate"] = Ext.util.Format.date(Ext.getCmp('tmDate').getValue(), "Y-m-d");
});
var grid = new Ext.grid.GridPanel({
    id : 'grid',
    title : '行政执法结论信息',
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

var _panel = new Ext.Panel({
    title : '',
    applyTo : '_panel',
    frame : true,
    width : 1050,
    height : 480,
    layout : "border",
    items : [form, grid],
    buttonAlign : 'center',
    buttons : [{
        text : "返回",
        handler : function() {
            window.history.go(-1);
        }
    }]
})


//======================查看行政执法相关信息详情========================
function viewAeCon(id) {
    var row1 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                id : 'aeno_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '检查记录编号',
                width : 345,
                readOnly : true
            }]
        }, {
            columnWidth : .5,
            layout : 'form', //从上往下布局
            items : [{
                id : 'aeopnionno_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '执法意见书编号',
                width : 345,
                readOnly : true
            }]
        }]
    };
    var row2 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                id : 'aeorgnm_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '检查机构',
                width : 345,
                readOnly : true
            }]
        }, {
            columnWidth : .5,
            layout : 'form', //从上往下布局
            items : [{
                id : 'aeedorgnm_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '被检查机构',
                width : 345,
                readOnly : true
            }]
        }]
    };

    var row3 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                id : 'punishno_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '行政处罚立项编号',
                width : 345,
                readOnly : true
            }]
        }, {
            columnWidth : .5,
            layout : 'form', //从上往下布局
            items : [{
                id : 'punishbookno_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '行政处罚决定书编号',
                width : 345,
                readOnly : true
            }]
        }]
    };
    var row4 = {
        layout : 'form',
        items : [{
            id : 'wtgk_v',
            xtype : 'textarea',
            value : '',
            fieldLabel : '问题概况',
            height : 200,
            width : 820,
            readOnly : true
        }]
    };
    var row5 = {
        layout : 'form',
        items : [{
            id : 'createdate_v',
            xtype : 'textfield',
            value : '',
            fieldLabel : '登记日期',
            width : 345,
            readOnly : true
        }]
    };
    var row6 = {
        layout : 'form',
        items : [{
            xtype : 'textfield',
            name : 'aeopnionbook_v',
            id : 'aeopnionbook_v',
            hidden : true,
            hideLabel : true,
            value : ''
        }, {
            xtype : 'textfield',
            name : 'punishbookatta_v',
            id : 'punishbookatta_v',
            hidden : true,
            hideLabel : true,
            value : ''
        }]
    };

    var fp = new Ext.form.FormPanel({
        title : '查看行政执法结论相关信息',
        width : 990,
        autoHeight : true,
        bodyStyle : 'padding:5px 5px 0',
        //renderTo : Ext.getBody(),
        frame : true,
        layout : 'form',
        url : '',
        method : 'post',
        labelWidth : 120,
        labelAlign : 'right',
        items : [row1, row2, row3, row4, row5, row6],
        buttonAlign : 'center',
        style : 'padding:10px',
        buttons : [{
            text : '下载处理意见书',
            handler : function() {
                var attId = Ext.getCmp('aeopnionbook_v').getValue();
                document.forms[0].action = "AdminEnforceManagerAction.do?method=downloadAtt&attId=" + attId;
                document.forms[0].submit();
            }
        }, {
            text : '下载行政处罚决定书',
            handler : function() {
                var attId = Ext.getCmp('punishbookatta_v').getValue();
                document.forms[0].action = "AdminEnforceManagerAction.do?method=downloadAtt&attId=" + attId;
                document.forms[0].submit();
            }
        }, {
            text : '关闭',
            handler : function() {
                addBasisWin.close();
                fp.destroy();
                addBasisWin.destroy();
            }
        }]
    });

    var addBasisWin = new Ext.Window({
        title : '行政执法详情',
        width : 1000,
        height : 450,
        resizable : false,
        closeAction : 'close',
        constrainHeader : true,
        modal : true,
        plain : true,
        items : [fp]
    });
    addBasisWin.show();
    Ext.Ajax.request({
        url : '<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=getAeconclusionInfo',
        params : {
            id : id
        },
        method : 'POST',
        callback : function(options, success, response) {
            if(success) {
                var responseJson = Ext.util.JSON.decode(response.responseText.replace(/\n/g, '\\n'));
                //对返回字符串中的\n做处理
                Ext.getCmp('aeno_v').setValue(responseJson.aeno);
                Ext.getCmp('aeopnionno_v').setValue(responseJson.aeopnionno);
                Ext.getCmp('aeorgnm_v').setValue(responseJson.aeorgnm);
                Ext.getCmp('aeedorgnm_v').setValue(responseJson.aeedorgnm);
                Ext.getCmp('wtgk_v').setValue(responseJson.selectedwtgk);
                Ext.getCmp('createdate_v').setValue(responseJson.createdate);
                Ext.getCmp('punishno_v').setValue(responseJson.punishno);
                Ext.getCmp('punishbookno_v').setValue(responseJson.punishbookno);
                Ext.getCmp('aeopnionbook_v').setValue(responseJson.aeopnionbook);
                Ext.getCmp('punishbookatta_v').setValue(responseJson.punishbookatta);
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

//======================录入检查结论 反馈意见========================
function viewFeedBack(id) {
    var row1 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                id : 'aeno_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '检查记录编号',
                width : 345,
                readOnly : true
            }]
        }, {
            columnWidth : .5,
            layout : 'form', //从上往下布局
            items : [{
                id : 'aeopnionno_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '执法意见书编号',
                width : 345,
                readOnly : true
            }]
        }]
    };
    var row2 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                id : 'aeorgnm_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '检查机构',
                width : 345,
                readOnly : true
            }]
        }, {
            columnWidth : .5,
            layout : 'form', //从上往下布局
            items : [{
                id : 'aeedorgnm_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '被检查机构',
                width : 345,
                readOnly : true
            }]
        }]
    };

    var row4 = {
        layout : 'form',
        items : [{
            id : 'feedbackOpnion_v',
            xtype : 'textarea',
            value : '',
            fieldLabel : '反馈意见',
            height : 200,
            width : 820
        }]
    };
    var row5 = {
        layout : 'form',
        items : [{
            id : 'feedbackdate_v',
            xtype : 'textfield',
            value : '',
            fieldLabel : '反馈日期',
            width : 345,
            readOnly : true
        }]
    };

    var fp = new Ext.form.FormPanel({
        title : '检查结论反馈录入',
        width : 990,
        autoHeight : true,
        bodyStyle : 'padding:5px 5px 0',
        //renderTo : Ext.getBody(),
        frame : true,
        layout : 'form',
        url : '',
        method : 'post',
        labelWidth : 120,
        labelAlign : 'right',
        items : [row1, row2,  row4, row5],
        buttonAlign : 'center',
        style : 'padding:10px',
        buttons : [{
            text : '保存',
            handler : function() {
            	Ext.Msg.confirm('提示', '是否确认保存当前反馈意见？', function(btn) {
                    if(btn == 'yes') {
                        //FormPanel自身带异步提交方式
                        fp.getForm().submit({
                            url : '<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=saveFeedBack',
                            waitTitle : '请等待',
                            waitMsg : '正在保存当前反馈意见',
                            success : function(form, action) {
                                //url后台返回的数据{success:true,msg:'成功'}
                                Ext.Msg.alert('提示', action.result.msg);
                                addBasisWin.close();
                                fp.destroy();
                                addBasisWin.destroy();
                            },
                            failure : function(form, action) {
                                Ext.Msg.alert('提示', '保存保存当前反馈意见失败！请重新填写');
                            }
                        });
                    }
                });
            }
        }, {
            text : '关闭',
            handler : function() {
                addBasisWin.close();
                fp.destroy();
                addBasisWin.destroy();
            }
        }]
    });

    var addBasisWin = new Ext.Window({
        title : '检查结论反馈',
        width : 1000,
        height : 450,
        resizable : false,
        closeAction : 'close',
        constrainHeader : true,
        modal : true,
        plain : true,
        items : [fp]
    });
    addBasisWin.show();
    Ext.Ajax.request({
        url : '<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=getFeedbackInfo',
        params : {
            id : id
        },
        method : 'POST',
        callback : function(options, success, response) {
            if(success) {
            	//对返回字符串中的\n做处理
                var responseJson = Ext.util.JSON.decode(response.responseText.replace(/\n/g, '\\n'));
               
                Ext.getCmp('aeno_v').setValue(responseJson.aeno);
                Ext.getCmp('aeopnionno_v').setValue(responseJson.aeopnionno);
                Ext.getCmp('aeorgnm_v').setValue(responseJson.aeorgnm);
                Ext.getCmp('aeedorgnm_v').setValue(responseJson.aeedorgnm);
                Ext.getCmp('feedbackOpnion_v').setValue(responseJson.feedbackopnion);
                Ext.getCmp('feedbackdate_v').setValue(responseJson.feedbackdt);
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

//======================录入行政处罚 反馈意见========================
function viewPublishFeedBack(id) {
    var row1 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                id : 'aeno_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '检查记录编号',
                width : 345,
                readOnly : true
            }]
        }, {
            columnWidth : .5,
            layout : 'form', //从上往下布局
            items : [{
                id : 'punishbookno_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '行政处罚决定书编号',
                width : 345,
                readOnly : true
            }]
        }]
    };
    var row2 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                id : 'aeorgnm_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '检查机构',
                width : 345,
                readOnly : true
            }]
        }, {
            columnWidth : .5,
            layout : 'form', //从上往下布局
            items : [{
                id : 'aeedorgnm_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '被检查机构',
                width : 345,
                readOnly : true
            }]
        }]
    };

    var row4 = {
        layout : 'form',
        items : [{
            id : 'publishfeedbackOpnion_v',
            xtype : 'textarea',
            value : '',
            fieldLabel : '反馈意见',
            height : 200,
            width : 820
        }]
    };
    var row5 = {
        layout : 'form',
        items : [{
            id : 'publishfeedbackdate_v',
            xtype : 'textfield',
            value : '',
            fieldLabel : '反馈日期',
            width : 345,
            readOnly : true
        }]
    };

    var fp = new Ext.form.FormPanel({
        title : '行政处罚反馈录入',
        width : 990,
        autoHeight : true,
        bodyStyle : 'padding:5px 5px 0',
        //renderTo : Ext.getBody(),
        frame : true,
        layout : 'form',
        url : '',
        method : 'post',
        labelWidth : 120,
        labelAlign : 'right',
        items : [row1, row2,  row4, row5],
        buttonAlign : 'center',
        style : 'padding:10px',
        buttons : [{
            text : '保存',
            handler : function() {
            	Ext.Msg.confirm('提示', '是否确认保存当前反馈意见？', function(btn) {
                    if(btn == 'yes') {
                        //FormPanel自身带异步提交方式
                        fp.getForm().submit({
                            url : '<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=savePublishFeedBack',
                            waitTitle : '请等待',
                            waitMsg : '正在保存当前反馈意见',
                            success : function(form, action) {
                                //url后台返回的数据{success:true,msg:'成功'}
                                Ext.Msg.alert('提示', action.result.msg);
                                addBasisWin.close();
                                fp.destroy();
                                addBasisWin.destroy();
                            },
                            failure : function(form, action) {
                                Ext.Msg.alert('提示', '保存保存当前反馈意见失败！请重新填写');
                            }
                        });
                    }
                });
            }
        }, {
            text : '关闭',
            handler : function() {
                addBasisWin.close();
                fp.destroy();
                addBasisWin.destroy();
            }
        }]
    });

    var addBasisWin = new Ext.Window({
        title : '行政处罚反馈',
        width : 1000,
        height : 450,
        resizable : false,
        closeAction : 'close',
        constrainHeader : true,
        modal : true,
        plain : true,
        items : [fp]
    });
    addBasisWin.show();
    Ext.Ajax.request({
        url : '<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=getPublishFeedbackInfo',
        params : {
            id : id
        },
        method : 'POST',
        callback : function(options, success, response) {
            if(success) {
            	//对返回字符串中的\n做处理
                var responseJson = Ext.util.JSON.decode(response.responseText.replace(/\n/g, '\\n'));
               
                Ext.getCmp('aeno_v').setValue(responseJson.aeno);
                Ext.getCmp('punishbookno_v').setValue(responseJson.punishbookno);
                Ext.getCmp('aeorgnm_v').setValue(responseJson.aeorgnm);
                Ext.getCmp('aeedorgnm_v').setValue(responseJson.aeedorgnm);
                Ext.getCmp('publishfeedbackOpnion_v').setValue(responseJson.feedbackopnion);
                Ext.getCmp('publishfeedbackdate_v').setValue(responseJson.feedbackdt);
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
</script>
</html>