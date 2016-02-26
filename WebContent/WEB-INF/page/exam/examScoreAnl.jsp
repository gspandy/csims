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
			src="<%=request.getContextPath()%>/ext-3.2.0/ext-all-debug.js"> </script>
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
								考试成绩统计
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								考试成绩统计展示
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
function _count() {
	var datastore = new Ext.data.JsonStore({
		url : '<%=request.getContextPath()%>/ExamAction.do?method=getExamScoreAnl',
		autoLoad : true,
		root : 'data',
		fields : ['data', 'total']
	});
	
	charwin.show();
}

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
    }, {
        columnWidth : .33,
        layout : 'form',
        items : [{
            xtype : 'textfield',
            fieldLabel : '用户类型',
            name : 'peotype',
            id : 'peotype',
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
    }]
});
//=============================================  CHART ====================================
var datastore = new Ext.data.JsonStore({
	url : '<%=request.getContextPath()%>/ExamAction.do?method=getExamScoreAnl',
	autoLoad : true,
	root : 'data',
	fields : ['data', 'total']
});
datastore.load();
var pieChart = new Ext.chart.PieChart({
 xtype: 'piechart',
	width : 450,
	height : 320,
	store : datastore,
	region : 'center',
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
});

var _panel = new Ext.Panel({
    title : '',
    applyTo : '_panel',
    frame : true,
    width : 1050,
    height : 480,
    layout : "border",
    items : [form, pieChart],
    buttonAlign : 'center',
    buttons : [{
        text : "返回",
        handler : function() {
            window.history.go(-1);
        }
    }]
});
</script>
</html>