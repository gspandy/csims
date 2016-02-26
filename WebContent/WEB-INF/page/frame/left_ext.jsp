<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="jguard" uri="/WEB-INF/tld/jguard.tld"%>
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
		<title><bean:message key="PROJECT_NAME" /></title>
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
		<link rel="stylesheet"
            href="<%=basePath%>ext-3.2.0/resources/css/MultiSelect.css"
            type="text/css"></link>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/home.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/home03.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/manu.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/common.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/validate.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/js/WdatePicker.js" defer="defer"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/MultiSelect.js"></script>
        <script type="text/javascript" src="<%=basePath%>ext-3.2.0/ItemSelector.js"></script>
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
		<script type="text/javascript">
		Ext.define("AM.view.menuView", {
    extend: 'Ext.panel.Panel',
    layout: 'accordion', //手风琴布局
    alias: 'widget.menuview',
    layoutConfig: {
        titleCollapse: false,
        animate: true,
        activeOnTop: true
    },
    items: [{
            xtype:'treepanel',
            title: '文章设置',
            rootVisible: true,
            displayField: 'text',
            store:'DeptStoreTree'//
    }, {
        title: '第二个应用程序',
        html: '<h1>面板2</h1>'
    }, {
        title: '第三个应用程序',
        html: '<h1>面板3</h1>'
    }]
});
		</script>
	</head>
	<body style="height: 1000">
	</body>
</html>
