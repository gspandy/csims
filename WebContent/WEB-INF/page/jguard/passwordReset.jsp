<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<title><bean:message key="PROJECT_NAME"/></title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
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
	function check(){
		if(document.getElementById("old").value==""){
			alert('请输入旧密码');
			document.getElementById("old").focus();
			return false;
		}
		if(document.getElementById("new").value==""){
			alert('请输入新密码');
			document.getElementById("new").focus();
			return false;
		}
		if(document.getElementById("new").value.length<5){
			alert('新密码的位数必须是5位以上，请重新填写！');
			return false;
		}
		if(document.getElementById("old").value==document.getElementById("new").value){
			alert('新密码与旧密码相同,请重新修改！');
			document.getElementById("old").value=""
			document.getElementById("new").value=""
			document.getElementById("newAgain").value=""
			return false;
		}
		if(document.getElementById("new").value!=document.getElementById("newAgain").value){
			alert('密码信息不能为空，或者新密码确认不正确！');
			document.getElementById("old").value=""
			document.getElementById("new").value=""
			document.getElementById("newAgain").value=""
			return false;
		}
		
			if(document.getElementById("new").value.length<=4){
			alert('密码必须是5位以上,请重新填写');
			document.getElementById("old").value=""
			document.getElementById("new").value=""
			document.getElementById("newAgain").value=""
			return false;
		}
		
		
		document.forms[0].submit();
	}
</script>

<html:form action="/resetPassword?method=reset" method="post">
     <table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="35" class="nawzjj">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
          <td class="ndwz">&nbsp;</td>
          <td class="ndwzzml"><bean:message key="PROJECT_NAME"/>
          <img src="<%= request.getContextPath() %>/images/index11.jpg" width="6" height="10" hspace="5" />
       密码修改
</td>
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
                <td class="tiltlegerner">修改密码</td>
                <td width="6"><img src="images/zhenxin28.jpg" width="6" height="26" /></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td valign="top"><table width="100%" border="0" cellpadding="0" cellspacing="0" class="tableline01">
              
              <tr>
                <td class="tabletext02">旧密码:</td>
               <td class="tdstylebotton"><input name="old" type="password" class="text111" size="21" maxlength="50" /></td>
                  
              </tr>
              <tr>
                <td class="tabletext02">新密码:</td>
                <td class="tdstylebotton"><input name="new" type="password" class="text111" size="21" maxlength="50" /></td>
              </tr>
             
              <tr>
                <td class="tabletext02">确认新密码:</td>
                <td class="tdstylebotton"><input name="newAgain" type="password" class="text111" size="21" maxlength="50" /></td>
              </tr>
            </table> </td>
          </tr>
          <tr>
            <td class="tdstylebotton"><table width="20%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td align="center"><span class="tdstyle">
                  <input  type="button" class="botton01" value="确 定" onclick="check()" />
                </span></td>
                <td align="center"><span class="tdstyle">
                 <input type="button" value="返 回" class="botton01"
					onclick="history.go(-1)" />
                </span></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
        </tr>
    </table></td>
  </tr>
</table>	
</html:form>