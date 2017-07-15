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
		<script type="text/javascript">
			function importAdminEnforceExcel(){
				if(confirm("操作将导入单机版结果数据,确认?")){
					document.forms[0].action="AdminEnforceManagerAction.do?method=importDesktopClientFinalData";
					document.forms[0].submit();
				}
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
								单机版结果数据导入
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
            fieldLabel : '上传数据文件(*.zip) ',
            allowBlank : false,
            inputType : 'file',
            id : 'desktopClientFinalFile',
            name : 'desktopClientFinalFile',
            width : 750
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
            width : 750,
            allowBlank : false
        }]
    }]
};
var fm = new Ext.FormPanel({
    title : '批量导入行政执法离线工作检查记录信息文件',
    url : 'AdminEnforceManagerAction.do?method=importDesktopClientFinalData',
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
    labelWidth : 150,
    labelAlign : 'right',
    style : 'padding:10px',
    items : [row1, row2],
    buttons : [{
        text : '开始上传',
        handler : function() {
            // var filePath = Ext.getCmp('file').getValue();
            var filePath = document.getElementById("desktopClientFinalFile").value;
            //点击'开始上传'之后，将由这个function来处理。
            if(fm.getForm().isValid() && filePath != null && filePath != '') {//验证form
                if(filePath.substring(filePath.lastIndexOf(".") + 1).toLowerCase() != "zip") {
                    Ext.Msg.alert("提示", "请不要改动从单机版导出的文件格式，目前只支持zip压缩格式!");
                } else {
                    Ext.Msg.confirm('提示', '是否确认导入行政执法的离线工作检查记录信息？', function(btn) {
                        if(btn == 'yes') {
                            fm.getForm().submit({
                                method : 'post',
                                url : 'AdminEnforceManagerAction.do?method=importDesktopClientFinalData',
                                waitMsg : '文件上传中，请稍等...',
                                success : function(fp, o) {
                                    Ext.Msg.alert('成功', '行政执法的离线工作检查记录上传成功，请到工作检查记录查询相关记录');
                                }
                            });
                        }
                    });
                }
            } else {
                Ext.Msg.alert("提示", "请先选择Zip文件再上传!");
            }
        }
    }]
});
</script>
</html>