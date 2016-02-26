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
								考试成绩管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								考试成绩导入
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center" valign="top">
					<div id="form" align="center"></div>
				</td>
			</tr>
		</table>
	</body>
	<script type="text/javascript">
Ext.MessageBox.minWidth = 400;
var row1 = {
	layout : 'column', //从左往右布局
	items : [{
		columnWidth : 1, //该列有整行中所占百分比
		layout : 'form', //从上往下布局
		items : [{
			xtype : 'field',
			fieldLabel : '上传数据文件文件(*.xls或者*.csv) ',
			allowBlank : false,
			emptyText : '请选择要上传的文件',
			inputType : 'file',
			id : 'file',
			name : 'file',
			width : 650
		}]
	}]
};
var row2 = {
	layout : 'column', //从左往右布局
	items : [{
		columnWidth : 1, //该列有整行中所占百分比
		layout : 'form', //从上往下布局
		items : [{
			id : 'logMessage',
			xtype : 'displayfield',
			fieldLabel : '上传信息',
			value : '未上传',
			width : 750
		}]
	}]
};
var fm = new Ext.FormPanel({
	title : '批量导入考试成绩文件',
	url : 'upload/trfmlisn.svt?type=examscore&filetype=CSV&t=' + new Date(),
	bodyStyle : 'padding:5px 5px 0',
	autoScroll : true,
	applyTo : 'form',
	// height : 480,
	autoHeight : true,
	width : 1050,
	renderTo : Ext.getBody(),
	buttonAlign : 'center', //按钮对其方式
	frame : true,
	fileUpload : true,
	labelWidth : 250,
	labelAlign : 'right',
	style : 'padding:10px',
	items : [row1, row2],
	buttons : [{
		text : '开始上传',
		handler : function() {
			// var filePath = Ext.getCmp('file').getValue();
			var filePath = document.getElementById("file").value;
			//点击'开始上传'之后，将由这个function来处理。
			if(fm.getForm().isValid() && filePath != null && filePath != '') {//验证form
				if(filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase() != "xls" && filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase() != "csv") {
					Ext.Msg.alert("提示", "目前只支持Office Excel 2003数据格式文件（xls）或者CSV格式文件!");
				} else {
					Ext.Msg.confirm('提示', '是否确认上传考试成绩？', function(btn) {
						if(btn == 'yes') {
							//显示进度条
							Ext.MessageBox.show({
								title : '正在上传文件',
								//msg: 'Processing...',
								width : 600,
								progress : true,
								closable : false,
								buttons : {
									cancel : 'Cancel'
								}
							});
							Ext.getCmp('logMessage').setValue('正在上传处理文件');
							//form提交
							if(filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase() == "xls") {
								fm.getForm().submit({
									url : 'upload/trfmlisn.svt?type=examscore&t=' + new Date()
								});
							} else {
								fm.getForm().submit({
									url : 'upload/trfmlisn.svt?type=examscore&filetype=CSV&t=' + new Date()
								});
							}
							//设置一个定时器，每500毫秒向processController发送一次ajax请求
							var i = 0;
							if(filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase() == "xls") {
								var timer = setInterval(function() {
									//请求事例
									Ext.Ajax.request({
										//下面的url的写法很关键，我为了这个调试了好半天
										//以后凡是在ajax的请求的url上面都要带上日期戳，
										//否则极有可能每次出现的数据都是一样的，
										//这和浏览器缓存有关
										url : 'upload/prcscontl.svt?type=examscore&t=' + new Date(),
										method : 'get',
										//处理ajax的返回数据
										success : function(response, options) {
											status = response.responseText + " " + i++;
											var obj = Ext.util.JSON.decode(response.responseText);
											// alert(obj.percentage+' '+obj.msg);
											if(obj.success != false) {
												if(obj.finished) {
													clearInterval(timer);
													//status = response.responseText;
													Ext.MessageBox.updateProgress(1, 'finished', 'finished');
													Ext.MessageBox.hide();
													Ext.Msg.alert('提示', '考试成绩数据文件处理完成！');
													Ext.getCmp('logMessage').setValue('考试成绩数据文件处理完成！');
													var obj = document.getElementById("file");
													obj.outerHTML = obj.outerHTML;
												} else {
													Ext.MessageBox.updateProgress(obj.percentage, obj.msg);
												}
											}
										},
										failure : function() {
											clearInterval(timer);
											Ext.Msg.alert('错误', '批量导入考试成绩文件发生错误');
										}
									});
								}, 500);
							} else {
								var timer = setInterval(function() {
									//请求事例
									Ext.Ajax.request({
										url : 'upload/prcscontl.svt?type=examscoreCSV&t=' + new Date(),
										method : 'get',
										//处理ajax的返回数据
										success : function(response, options) {
											status = response.responseText + " " + i++;
											var obj = Ext.util.JSON.decode(response.responseText);
											// alert(obj.percentage+' '+obj.msg);
											if(obj.success != false) {
												if(obj.finished) {
													clearInterval(timer);
													//status = response.responseText;
													Ext.MessageBox.updateProgress(1, 'finished', 'finished');
													Ext.MessageBox.hide();
													Ext.Msg.alert('提示', '考试成绩数据文件处理完成！');
													Ext.getCmp('logMessage').setValue('考试成绩数据文件处理完成！');
													var obj = document.getElementById("file");
													obj.outerHTML = obj.outerHTML;
												} else {
													Ext.MessageBox.updateProgress(obj.percentage, obj.msg);
												}
											}
										},
										failure : function() {
											clearInterval(timer);
											Ext.Msg.alert('错误', '批量导入考试成绩文件发生错误');
										}
									});
								}, 500);
							}
						}
					});
				}
			} else {
				Ext.Msg.alert("提示", "请先选择数据文件文件再上传!");
			}
		}
	}]
});

</script>
</html>