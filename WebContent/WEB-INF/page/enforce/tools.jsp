<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="sweet" uri="/WEB-INF/tld/sweet-tag.tld"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
  <head>
    <title>后台工具</title>
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
    <script type="text/javascript">
Ext.MessageBox.minWidth = 400;

function refreshTopLevelOrgs() {
        Ext.Msg.confirm('提示', '是否确认进行刷新检查结论顶级机构?', function(btn) {
            if(btn == 'yes') {
                var msgbox = Ext.Msg.progress("提示", "正在行刷新检查结论顶级机构", "请稍候.....");
                //显示等待对话框
                document.forms[0].action = "AdminSanctionManagerAction.do?method=refreshAeConclusionTopLevelOrgs";
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
          <table width="100%" border="0" cellspacing="0" cellpadding="0">
            <tr>
              <td class="ndwz">
                &nbsp;
              </td>
              <td class="ndwzzml">
                <bean:message key="PROJECT_NAME" />
                <img
                  src="<%=request.getContextPath()%>/images/index11.jpg"
                  width="6" height="10" hspace="5" />
                                         后台工具
                <img
                  src="<%=request.getContextPath()%>/images/index11.jpg"
                  width="6" height="10" hspace="5" />
                                         工具
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
    <form action=""
      method="post">
      <table width="100%" border="0" cellspacing="0" cellpadding="0">
        <tr>
          <td valign="top">
            <table width="100%" border="0" cellpadding="0"
              cellspacing="0">
              <tr>
                <td class="tablestyle">
                  <table width="100%" border="0" cellpadding="0"
                    cellspacing="0">
                    <tr>
                      <td>
                        <table width="100%" border="0" cellpadding="0"
                          cellspacing="0" class="tableline01">
                          <tr>
                          <td>刷新检查结论顶级机构</td>
                            <td align="center"  >
                              <input type="button"
                                onclick="refreshTopLevelOrgs();" value="刷新"
                                class="botton01" />
                            </td>
                          </tr>
                        </table>
                      </td>
                    </tr>
                  </table>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>