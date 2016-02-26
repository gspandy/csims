<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
    <title><bean:message key="CUSTOM_NAME" />-<bean:message
        key="PROJECT_NAME" />-常见问题解答</title>
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
  <%
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://"
              + request.getServerName() + ":" + request.getServerPort()
              + path + "/";
  %>
  <body>
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td class="ndwz">
          &nbsp;
        </td>
        <td class="ndwzzml">
          <bean:message key="PROJECT_NAME" />
          <img src="images/index11.jpg" width="6" height="10" hspace="5" />
          常见问题解答
        </td>
      </tr>
    </table>
    <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="top" class="tablestyle">
          <table width="100%" border="0" cellpadding="0" cellspacing="0">
            <tr>
              <td align="right" class="tdstylebotton">
                <span class="tdstyle"> <input name="Submit"
                    type="submit" class="bottonbbs01"
                    onclick="window.close();" value="  关 闭" /> </span>
              </td>
            </tr>

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
                            <span class="tablewline">&nbsp;&nbsp;系统常见问题解答</span>
                          </td>
                        </tr>
                        <tr>
                          <td width="400" rowspan="2" valign="top"
                            bgcolor="#F7F7F7" class="tablewline3">
                            <table width="100%" border="0"
                              cellspacing="0" cellpadding="0">
                              <tr>
                                <td align="center">
                                  常见问题
                                </td>
                              </tr>
                              <tr>
                                <td align="left"></td>
                              </tr>
                            </table>
                          </td>
                          <td align="center">
                            问题解答
                          </td>
                        </tr>
                        <tr>
                          <td class="tablewline5"></td>
                        </tr>
                      </table>
                    </td>
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
                                <td align="left">
                                  <img src="images/index_15_swap.jpg"
                                    width="20" height="20" hspace="5"
                                    vspace="2" />
                                  为什么有些菜单无法操作?
                                </td>
                              </tr>
                              <tr>
                                <td align="left"></td>
                              </tr>
                            </table>
                          </td>
                          <td class="tablewline5">
                            您没有被授权,请与管理员联系;
                          </td>
                        </tr>

                      </table>
                    </td>
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
                                <td align="left">
                                  <img src="images/index_15_swap.jpg"
                                    width="20" height="20" hspace="5"
                                    vspace="2" />
                                  为什么进不了系统?
                                </td>
                              </tr>
                              <tr>
                                <td align="left"></td>
                              </tr>
                            </table>
                          </td>
                          <td class="tablewline5">
                            您输入的用户名和密码不匹配;
                            <br />
                            您没有被授权进入系统;
                          </td>
                        </tr>

                      </table>
                    </td>
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
                                <td align="left">
                                  <img src="images/index_15_swap.jpg"
                                    width="20" height="20" hspace="5"
                                    vspace="2" />
                                  我怎样才能对有些功能进行操作?
                                </td>
                              </tr>
                              <tr>
                                <td align="left"></td>
                              </tr>
                            </table>
                          </td>
                          <td class="tablewline5">
                            您输入的用户名和密码匹配,并且该用户被授权对指定功能有操作权限;
                            <br />
                            超级管理员赋予您操作权限;
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>

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
                            <span class="tablewline">&nbsp;&nbsp;业务常见问题解答</span>
                          </td>
                        </tr>
                        <tr>
                          <td width="400" rowspan="2" valign="top"
                            bgcolor="#F7F7F7" class="tablewline3">
                            <table width="100%" border="0"
                              cellspacing="0" cellpadding="0">
                              <tr>
                                <td align="center">
                                  常见问题
                                </td>
                              </tr>
                              <tr>
                                <td align="left"></td>
                              </tr>
                            </table>
                          </td>
                          <td align="center">
                            问题解答
                          </td>
                        </tr>
                        <tr>
                          <td class="tablewline5"></td>
                        </tr>
                      </table>
                    </td>
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
                                <td align="left">
                                  <img src="images/index_15_swap.jpg"
                                    width="20" height="20" hspace="5"
                                    vspace="2" />
                                  为什么有些数据不能删除?
                                </td>
                              </tr>
                              <tr>
                                <td align="left"></td>
                              </tr>
                            </table>
                          </td>
                          <td class="tablewline5">
                            有些数据是系统数据,不能删除,否则会引起系统错误;
                            <br />
                            有些数据之间有关联,不能强制删除,否则会影响数据的不正确性,列入系统中的条线;
                          </td>
                        </tr>
                      </table>
                    </td>
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
                                <td align="left">
                                  <img src="images/index_15_swap.jpg"
                                    width="20" height="20" hspace="5"
                                    vspace="2" />
                                  怎样快速查询?
                                </td>
                              </tr>
                              <tr>
                                <td align="left"></td>
                              </tr>
                            </table>
                          </td>
                          <td class="tablewline5">
                            该系统支持模糊查询,您只要输入相关的字句,就会查询出相包含该字词的信息;
                            <br />
                            如要精确查询,输入多个查询条件;
                          </td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
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
                            <span class="tablewline">&nbsp;&nbsp;系统相关excel模板下载</span>
                          </td>
                        </tr>
                        <tr>
                          <td align="center" width="40%">
                            模板名称
                          </td>
                          <td align="left"></td>
                        </tr>
                        <tr>
                          <td align="left">
                            <a
                              href="<%=basePath%>FileHandlerManagerAction.do?method=downloadAtt&attId=00001">执法检查情况统计表导入模板.xls</a>
                          </td>
                          <td align="left">
                            用于向系统中导入执法检查情况统计表数据，为excel文件格式
                          </td>
                        </tr>
                        <tr>
                          <td align="left">
                            <a
                              href="<%=basePath%>FileHandlerManagerAction.do?method=downloadAtt&attId=00002">执法立项数据导入模板.xls</a>
                          </td>
                          <td align="left">
                            用于向系统中导入执法立项数据，为excel文件格式
                          </td>
                        </tr>
                        <tr>
                          <td align="left">
                            <a
                              href="<%=basePath%>FileHandlerManagerAction.do?method=downloadAtt&attId=00003">考试成绩导入表.csv</a>
                          </td>
                          <td align="left">
                            用于向系统中导入考试成绩数据的excel格式，为csv文件格式
                          </td>
                        </tr>
                        <tr>
                          <td align="left">
                            <a
                              href="<%=basePath%>FileHandlerManagerAction.do?method=downloadAtt&attId=00004">考试成绩导入表.xls</a>
                          </td>
                          <td align="left">
                            用于向系统中导入考试成绩数据的excel格式，为excel文件格式
                          </td>
                        </tr>
                        <tr>
                          <td align="left">
                            <a
                              href="<%=basePath%>FileHandlerManagerAction.do?method=downloadAtt&attId=00005">问题概要统计导出格式.xls</a>
                          </td>
                          <td align="left">
                            系统导出行政执法检查工作中问题概要统计报表格式
                          </td>
                        </tr>
                        <tr>
                          <td class="tablewline5"></td>
                        </tr>
                      </table>
                    </td>
                  </tr>
                </table>
              </td>
            </tr>
            <tr>
              <td align="right" width="35%">
                <font style="font-size: 11px;"> 版权所有：<bean:message
                    key="CUSTOM_NAME" /> </font>
              </td>
              <td width="5%" />
            </tr>
            <tr>
              <td>
                &nbsp;
              </td>
            </tr>
          </table>
        </td>
      </tr>
    </table>
  </body>
</html>
