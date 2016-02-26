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
        <script type="text/javascript" src="<%=request.getContextPath()%>js/jquery.js"></script>
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
								考试成绩查询
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								考试成绩列表
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
				            action="/ExamAction.do?method=downloadAtt"
				            method="post">
				        </html:form>
						<div id="_panel" align="center"></div>
					</td>
				</tr>
			</table>
	</body>
<script type="text/javascript">
Ext.QuickTips.init();
Ext.chart.Chart.CHART_URL = '<%=request.getContextPath()%>/ext-3.2.0/resources/charts.swf';
Ext.BLANK_IMAGE_URL = "<%=request.getContextPath()%>/ext-3.2.0/resources/images/default/tree/s.gif";
Ext.MessageBox.minWidth = 400;
var row1 = {
    layout : 'column',
    items : [{
        columnWidth : .33,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '姓名',
            name : 'peoNm',
            id : 'peoNm',
            width : 180
        }]
    }, {
        columnWidth : .33,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '准考证号',
            name : 'peoId',
            id : 'peoId',
            width : 180
        }]
    },{
            columnWidth : .33, //该列有整行中所占百分比
            layout : 'form', //从上往下布局
            items : [{
                xtype : 'combo',
                fieldLabel : '试卷类型',
                id : 'peotype',
                // name : 'peotype',
                hiddenName : 'type_m_hidden',
                displayField : 'typeDesc',
                valueField : 'type',
                triggerAction : 'all',
                emptyText : '请选择',
                mode : 'local',
                width : 180,
                allowBlank : true,
                editable : false,
                forceSelection : false,
                store : new Ext.data.SimpleStore({
                    fields : ['type', 'typeDesc'],
                    data : [['', '请选择'], ['征信系统其它考试用户考试试卷', '征信系统其它考试用户考试试卷'], ['征信系统数据报送用户考试试卷', '征信系统数据报送用户考试试卷']]
                })
            }]
        }]
};

var row2 = {
    layout : 'column',
    items : [{
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
    } , {
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
    },{
        columnWidth : .33,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '',
            name : 'a',
            id : 'a',
            width : 180,
            hidden : true,
            hideLabel : true
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
                    peoNm : Ext.getCmp('peoNm').getValue(),
                    peoId : Ext.getCmp('peoId').getValue(),
                    peotype : Ext.getCmp('peotype').getValue(),
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
    }, {
        text : '导出',
        handler : function() {//点击取消按钮的操作事件
            Ext.Msg.confirm('提示', '导出Excel文件可能会花费一定时间，是否确认按照所选条件导出考试成绩表？', function(btn) {
                if(btn == 'yes') {
                    document.forms[0].action="ExamAction.do?method=generateExamScoreAnlExcel&form_peoid="
                    + encodeURI(encodeURI(Ext.getCmp('peoId').getValue()))
                    + "&form_peonm="
                    + encodeURI(encodeURI(Ext.getCmp('peoNm').getValue()))
                    + "&form_peotype="
                    + encodeURI(encodeURI(Ext.getCmp('peotype').getValue()))
                    + "&form_stdate="
                    + Ext.util.Format.date(Ext.getCmp('stDate').getValue(), 'Y-m-d')
                    + "&form_tmdate="
                    + Ext.util.Format.date(Ext.getCmp('tmDate').getValue(), 'Y-m-d');
                    document.forms[0].submit();
                }
            });
        }
    }, {
        text : '统计',
        handler : function() {//点击取消按钮的操作事件
            examScoreAnl();
        }
    }]
});
//=============================================  GRID ====================================
var cm = new Ext.grid.ColumnModel([{
    header : 'ID',
    dataIndex : 'id',
    hidden : true
}, {
    header : '姓名',
    dataIndex : 'peonm',
    width : 40
}, {
    header : '准考证号',
    dataIndex : 'peoid',
    width : 100
}, {
    header : '用户类型',
    dataIndex : 'peotype'
}, {
    header : '所属机构',
    dataIndex : 'orgname'
}, {
    header : '开始时间',
    dataIndex : 'exstdt'
}, {
    header : '结束时间',
    dataIndex : 'extmdt'
}, {
    header : '考试成绩',
    dataIndex : 'score'
}]);
var ds = new Ext.data.Store({
    proxy : new Ext.data.HttpProxy({
        url : '<%=request.getContextPath()%>/ExamAction.do?method=pageExamScoreInfo',
        method : 'POST',
        baseParams : {
            peoNm : Ext.getCmp('peoNm').getValue(),
            peoId : Ext.getCmp('peoId').getValue(),
            peotype : Ext.getCmp('peotype').getValue(),
            stDate : Ext.util.Format.date(Ext.getCmp('stDate').getValue(), "Y-m-d"),
            tmDate : Ext.util.Format.date(Ext.getCmp('tmDate').getValue(), "Y-m-d")
        }
    }),
    reader : new Ext.data.JsonReader({
        totalProperty : 'totalProperty',
        root : 'root'
    }, ['id', 'peonm', 'peoid', 'peotype', 'orgname', 'exstdt' ,'exstdt' ,'extmdt','score'])
});
ds.load({
    params : {
        peoNm : Ext.getCmp('peoNm').getValue(),
        peoId : Ext.getCmp('peoId').getValue(),
        peotype : Ext.getCmp('peotype').getValue(),
        stDate : Ext.util.Format.date(Ext.getCmp('stDate').getValue(), "Y-m-d"),
        tmDate : Ext.util.Format.date(Ext.getCmp('tmDate').getValue(), "Y-m-d"),
        start : 0,
        limit : 10
    }
});
ds.on("beforeload", function(thiz, options) {
    thiz.baseParams["peoNm"] = Ext.getCmp('peoNm').getValue();
    thiz.baseParams["peoId"] = Ext.getCmp('peoId').getValue();
    thiz.baseParams["peotype"] = Ext.getCmp('peotype').getValue();
    thiz.baseParams["stDate"] = Ext.util.Format.date(Ext.getCmp('stDate').getValue(), "Y-m-d");
    thiz.baseParams["tmDate"] = Ext.util.Format.date(Ext.getCmp('tmDate').getValue(), "Y-m-d");
});
var grid = new Ext.grid.GridPanel({
    id : 'grid',
    title : '考试成绩信息',
    cm : cm,
    ds : ds,
    region : 'center',
    stripeRows : true,
    loadMask : true,
    border : true,
    frame : true,
    autoHeight : true,
    width : 1050,
    // x : 1,
    // y : 1,
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
            viewExamScoreDetail(record.get('id'));
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
});
// ======================查看考试成绩详情========================
function viewExamScoreDetail(id) {
    var row1 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                id : 'peoid_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '准考证号',
                width : 345,
                readOnly : true
            }]
        }, {
            columnWidth : .5,
            layout : 'form', //从上往下布局
            items : [{
                id : 'peonm_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '姓名',
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
                id : 'certtype_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '证件类型',
                width : 345,
                readOnly : true
            }]
        }, {
            columnWidth : .5,
            layout : 'form', //从上往下布局
            items : [{
                id : 'peotype_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '用户类型',
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
                id : 'orgname_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '金融机构',
                width : 345,
                readOnly : true
            }]
        }, {
            columnWidth : .5,
            layout : 'form', //从上往下布局
            items : [{
                id : 'tele_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '联系电话',
                width : 345,
                readOnly : true
            }]
        }]
    };
     var row4 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                id : 'exstdt_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '考试开始时间',
                width : 345,
                readOnly : true
            }]
        }, {
            columnWidth : .5,
            layout : 'form', //从上往下布局
            items : [{
                id : 'extmdt_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '考试结束时间',
                width : 345,
                readOnly : true
            }]
        }]
    };
    
     var row5 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                id : 'fullmark_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '满分',
                width : 345,
                readOnly : true
            }]
        }, {
            columnWidth : .5,
            layout : 'form', //从上往下布局
            items : [{
                id : 'pass_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '及格分数',
                width : 345,
                readOnly : true
            }]
        }]
    };
     var row6 = {
        layout : 'column',
        items : [{
            columnWidth : .5,
            layout : 'form',
            items : [{
                id : 'score_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '成绩',
                width : 345,
                readOnly : true
            }]
        }, {
            columnWidth : .5,
            layout : 'form', //从上往下布局
            items : [{
                id : 'crtdt_v',
                xtype : 'textfield',
                value : '',
                fieldLabel : '导入时间',
                width : 345,
                readOnly : true
            }]
        }]
    };

    var fp = new Ext.form.FormPanel({
        title : '查看考试成绩详细信息',
        width : 990,
        autoHeight : true,
        bodyStyle : 'padding:5px 5px 0',
        renderTo : Ext.getBody(),
        frame : true,
        layout : 'form',
        url : '',
        method : 'post',
        labelWidth : 120,
        labelAlign : 'right',
        items : [row1, row2, row3, row4, row5, row6],
        buttonAlign : 'center',
        style : 'padding:10px',
        buttons : [ {
            text : '关闭',
            handler : function() {
                addBasisWin.close();
                fp.destroy();
                addBasisWin.destroy();
            }
        }]
    });

    var addBasisWin = new Ext.Window({
        title : '考试成绩详情',
        width : 1000,
        height : 300,
        resizable : false,
        closeAction : 'close',
        constrainHeader : true,
        modal : true,
        plain : true,
        items : [fp]
    });
    addBasisWin.show();
    Ext.Ajax.request({
        url : '<%=request.getContextPath()%>/ExamAction.do?method=getExamScoreDetail',
        params : {
            id : id
        },
        method : 'POST',
        callback : function(options, success, response) {
            if(success) {
				//对返回字符串中的\n做处理
                var responseJson = Ext.util.JSON.decode(response.responseText.replace(/\n/g, '\\n'));
                Ext.getCmp('peoid_v').setValue(responseJson.peoid);
                Ext.getCmp('peonm_v').setValue(responseJson.peonm);
                Ext.getCmp('certtype_v').setValue(responseJson.certtype);
                Ext.getCmp('peotype_v').setValue(responseJson.peotype);
                Ext.getCmp('orgname_v').setValue(responseJson.orgname);
                Ext.getCmp('tele_v').setValue(responseJson.tele);
                Ext.getCmp('exstdt_v').setValue(responseJson.exstdt);
                Ext.getCmp('extmdt_v').setValue(responseJson.extmdt);
                Ext.getCmp('fullmark_v').setValue(responseJson.fullmark);
                Ext.getCmp('pass_v').setValue(responseJson.pass);
                Ext.getCmp('score_v').setValue(responseJson.score);
                Ext.getCmp('crtdt_v').setValue(responseJson.crtdt);
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

// ======================统计图表========================
function examScoreAnl() {
	var datastore = new Ext.data.Store({
    proxy : new Ext.data.HttpProxy({
        url : '<%=request.getContextPath()%>/ExamAction.do?method=getExamScoreAnl',
        method : 'POST',
        baseParams : {
             peotype : Ext.getCmp('peotype').getValue(),
        stDate : Ext.util.Format.date(Ext.getCmp('stDate').getValue(), "Y-m-d"),
        tmDate : Ext.util.Format.date(Ext.getCmp('tmDate').getValue(), "Y-m-d")
        }
    }),
    reader : new Ext.data.JsonReader({
        root : 'data'
    }, ['data', 'total'])
});
datastore.load({
    params : {
       peotype : Ext.getCmp('peotype').getValue(),
        stDate : Ext.util.Format.date(Ext.getCmp('stDate').getValue(), "Y-m-d"),
        tmDate : Ext.util.Format.date(Ext.getCmp('tmDate').getValue(), "Y-m-d")
    }
});
	var charwin = new Ext.Window({
		width : 450,
		height : 320,
		modal : true,
		autoScroll : true,
		closeAction : 'close',
		layout : 'fit',
		maximizable : false,
		title : '考试及格情况',
		items : {
			store : datastore,
			xtype : 'piechart',
			url : '<%=request.getContextPath()%>/ext-3.2.0/resources/charts.swf',
			dataField : 'total',
			categoryField : 'data',
			extraStyle : {
				dataTip : {
					border : {
						color : 0x2e434d,
						size : 2
					},
					font : {
						name : "Arial Black",
						size : 12,
						color : 0x000000
					}
				}, //提示框显示字体样式
				legend : {
					display : "bottom",
					padding : 5,
					spacing : 2,
					font : {
						color : 0x000000,
						family : "Arial",
						size : 12
					},
					border : {
						size : 1,
						color : 0x999999
					}
				}
			}
		}
	});
	charwin.show();
}
</script>
</html>