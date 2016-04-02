<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sweet" uri="/WEB-INF/tld/sweet-tag.tld"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<%
    String htmlStr = (String) request.getAttribute("htmlStr");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>报表统计</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="description" content="template report">
    <link href="css/css.css" rel="stylesheet" type="text/css" />
    <link href="css/manus.css" rel="stylesheet" type="text/css" />
    <link href="css/home.css" rel="stylesheet" type="text/css" />
    <link rel="stylesheet" href="<%=basePath%>css/css2.css"
      type="text/css"></link>
    <link rel="stylesheet"
      href="<%=basePath%>ext-3.2.0/resources/css/ext-all.css"
      type="text/css"></link>
    <script type="text/javascript"
      src="<%=request.getContextPath()%>/js/home.js"></script>
    <script type="text/javascript"
      src="<%=request.getContextPath()%>/js/home03.js"></script>
    <script type="text/javascript"
      src="<%=request.getContextPath()%>/js/manu.js"></script>
    <script type="text/javascript"
      src="<%=request.getContextPath()%>/js/common.js"></script>
    <script type="text/javascript"
      src="<%=request.getContextPath()%>/js/WdatePicker.js"></script>
    <script type="text/javascript"
            src="<%=request.getContextPath()%>/js/validate.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
    <script type="text/javascript"
      src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
    <script type="text/javascript"
      src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
    <style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F1F8FC;
}
</style>
    <script type="text/javascript" language="javascript">
		function initStatisticsExcel(){
          Ext.Msg.confirm('提示', '是否确认进行导出统计Excel文件?', function(btn) {
              if(btn == 'yes') {
                  document.forms[0].action = "<%=request.getContextPath()%>/StatisticsAction.do?method=displayFeedbackExcel";
                  document.forms[0].submit();
              }
          });
	}
	</script>
  </head>
  <body>
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td height="35" class="nawzjj">
            <table width="100%" border="0" cellspacing="0"
              cellpadding="0">
              <tr>
                <td class="ndwz">
                  &nbsp;
                </td>
                <td class="ndwzzml">
                  <bean:message key="PROJECT_NAME" />
                  <img
                    src="<%=request.getContextPath()%>/images/index11.jpg"
                    width="6" height="10" hspace="5" />
                                                报表统计
                  <img
                    src="<%=request.getContextPath()%>/images/index11.jpg"
                    width="6" height="10" hspace="5" />
                                                统计结果
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
      <%= htmlStr%>
      <html:form action="StatisticsAction.do?method=displayFeedbackExcel"
        method="post">
      <table width="100%">
        <tr>
          <td align="center">
            <html:hidden property="reportYear"></html:hidden>
            <input type="button" onclick="initStatisticsExcel();"
              value="生成Excel" class="botton01" />
            <input type="button" value="返回" class="botton01"
              onclick="history.go(-1);" />
          </td>
        </tr>
      </table>
    </html:form>
  </body>
</html>