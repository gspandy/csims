<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title><bean:message key="CUSTOM_NAME" />-<bean:message key="PROJECT_NAME" />-错误信息</title>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F1F8FC;
}
-->
</style>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<link rel="shortcut icon" href="favicon.ico" type="image/x-icon" />
<link rel="icon" href="favicon.ico" type="image/x-icon" />
</head>

<body>
  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
      <td class="ndwz">&nbsp;</td>
      <td class="ndwzzml"><bean:message key="PROJECT_NAME" /> <img
        src="images/index11.jpg" width="6" height="10" hspace="5" />
        错误信息</td>
    </tr>
  </table>
  <table width="100%" border="0" cellspacing="5" cellpadding="0">
    <tr>
      <td valign="top">
        <table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td valign="top" class="tablestyle">
              <table width="100%" border="0" cellpadding="0"
                cellspacing="0">
                <tr>
                  <td>
                    <table width="100%" border="0" cellspacing="5"
                      cellpadding="0">
                      <tr>
                        <td>
                          <table width="100%" border="0" cellpadding="0"
                            cellspacing="0" class="tableline04">
                            <tr>
                              <td colspan="2" class="tablewline2Copy1">
                                <img
                                src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>
                                <span class="tablewline">&nbsp;&nbsp;系统出现错误</span>
                              </td>
                            </tr>
                            <tr>
                              <td width="400" rowspan="2" valign="top"
                                bgcolor="#F7F7F7" class="tablewline3">
                                <table width="100%" border="0"
                                  cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td align="center">错误类型</td>
                                  </tr>
                                  <tr>
                                    <td align="left"></td>
                                  </tr>
                                </table></td>
                              <td align="center">错误信息</td>
                            </tr>
                            <tr>
                              <td class="tablewline5"></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td>
                          <table width="100%" border="0" cellpadding="0"
                            cellspacing="0" class="tableline04">
                            <tr>
                              <td width="400" valign="top"
                                class="tablewline3">
                                <table width="100%" border="0"
                                  cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td align="left"><%=request.getAttribute("Class")%>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td align="left"></td>
                                  </tr>
                                </table></td>
                              <td class="tablewline5"><%=request.getAttribute("LocalizedMessage")%>
                              </td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td>
                          <table width="100%" border="0" cellpadding="0"
                            cellspacing="0" class="tableline04">
                            <tr>
                              <td colspan="2" class="tablewline2Copy1">
                                <img
                                src="<%=basePath%>ext-3.2.0//resources//images//default//form//exclamation.gif"></img>
                                <span class="tablewline">&nbsp;&nbsp;错误详细信息</span>
                              </td>
                            </tr>
                            <tr>
                              <td width="400" rowspan="2" valign="top"
                                bgcolor="#F7F7F7" class="tablewline3">
                                <table width="100%" border="0"
                                  cellspacing="0" cellpadding="0">
                                  <tr>
                                    <td align="center">错误方法名称</td>
                                  </tr>
                                  <tr>
                                    <td align="left"></td>
                                  </tr>
                                </table></td>
                              <td align="center">错误信息</td>
                            </tr>
                            <tr>
                              <td class="tablewline5"></td>
                            </tr>
                          </table></td>
                      </tr>
                      <tr>
                        <td>
                          <table width="100%" border="0" cellpadding="0"
                            cellspacing="0" class="tableline04">
                            <logic:iterate id="ste" name="StackTrace">
                              <tr>
                                <td width="400" valign="top"
                                  class="tablewline3">
                                  <table width="100%" border="0"
                                    cellspacing="0" cellpadding="0">
                                    <tr>
                                      <td align="left"><bean:write
                                          name="ste"
                                          property="className" /></td>
                                    </tr>
                                    <tr>
                                      <td align="left"></td>
                                    </tr>
                                  </table></td>
                                <td class="tablewline5"><bean:write
                                    name="ste" property="methodName" />
                                </td>
                              </tr>
                            </logic:iterate>
                          </table></td>
                      </tr>
                    </table></td>
                </tr>
                <tr>
                  <td align="right" width="35%"><font
                    style="font-size: 11px;"> 版权所有：<bean:message
                        key="CUSTOM_NAME" /> </font></td>
                  <td width="5%" />
                </tr>
                <tr>
                  <td>&nbsp;</td>
                </tr>
                <tr>
                  <td align="center" class="tdstylebotton"><span
                    class="tdstyle"> <input name="Submit"
                      type="submit" class="bottonbbs01"
                      onclick="javascript:window.history.go(-1);"
                      value="  返 回" /> </span></td>
                </tr>
              </table></td>
          </tr>
        </table></td>
    </tr>
  </table>
</body>
</html>
