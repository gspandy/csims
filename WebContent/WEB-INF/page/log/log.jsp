<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />

<link href="css/css.css" rel="stylesheet" type="text/css" />
<link href="css/manus.css" rel="stylesheet" type="text/css" />
<link href="css/home.css" rel="stylesheet" type="text/css" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #F1F8FC;
}
</style>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/home.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/home03.js"></script>	
	
<script type="text/javascript"
	src="<%= request.getContextPath()%>/js/manu.js"></script>
	
		<title><bean:message key="PROJECT_NAME"/></title>
	</head>
	
<body>

    <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="35" class="nawzjj">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
          <td class="ndwz">&nbsp;</td>
          <td class="ndwzzml"><bean:message key="PROJECT_NAME"/>         
          <img src="<%= request.getContextPath() %>/images/index11.jpg" width="6" height="10" hspace="5" />
         日志详细列表</td>
	</tr></table>
	</td>
	</tr>
</table>
<table width="100%" border="0" cellspacing="0" cellpadding="0">

  <tr>
    <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td class="tablestyle">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
          <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="6"><img src="images/zhenxin26.jpg" width="6" height="26" /></td>
                <td class="tiltlegerner">日志详细信息</td>
                <td width="6"><img src="images/zhenxin28.jpg" width="6" height="26" /></td>
              </tr>
            </table>
            </td>
          </tr>
          
          <tr>
            <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableline01">
              
              <tr>
                <td width="15%" class="tabletext02">创建时间:</td>
                <td width="85%" class="tdstylebotton">                 
                <bean:write name="log" property="createdate"
						format="yyyy-MM-dd hh:ss:mm" />
                </td>
              </tr>
              
              <tr>
                <td class="tabletext02">优先级:</td>
                <td class="tdstylebotton"><bean:write name="log" property="priority" /></td>
              </tr>
              
              <tr>
                <td class="tabletext02">流程:</td>
                <td class="tdstylebotton">	<bean:write name="log" property="thread" /></td>
              </tr>
              
              <tr>
                <td class="tabletext02">类别:</td>
                <td class="tdstylebotton"><bean:write name="log" property="category" /></td>
              </tr>        
              <tr>
                 <td class="tabletext02">内容:</td>
                 <td class="tdstylebotton"  style="word-wrap:break-word;word-break:break-all;width:850px;">
                 	<bean:write name="log" property="message" />
                 </td>
              </tr>
            </table>
            </td>
          </tr>
          
          <tr>
            <td class="tdstylebotton"><table width="20%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
               <td><input type="button" value="返回" class="botton01" onclick="history.go(-1)" /></td>
              </tr>
            </table></td>

        </table></td>
        </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
