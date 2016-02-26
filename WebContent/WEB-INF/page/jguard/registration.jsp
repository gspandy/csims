<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<script type="text/javascript">


function validate(){
  var required = "login,password,nickname".split(',');
  for(var i = 0;i<document.forms[0].elements.length;i++){
     var element = document.forms[0].elements[i];

     for(var j=0;j<required.length;j++){

        if(element.name == required[j] && element.value==""){
           alert(element.name+" required ");
           element.focus();
           return false;
        }
     }
  }
  return true;
}

</script>
<title>registration</title>
<form name="registrationForm" action="./RegistrationProcess.do"
	method="post" onsubmit="return validate();">
	<fieldset id="loginFieldSet">
		<legend>
			registration
		</legend>
		<div>
			<label for="login">
				登录名(required):
			</label>
			<input id="login" type="text" value="" size="30" name="login"
				tabindex="1" />
		</div>
		<div>
			<label for="password">
				密码(required):
			</label>
			<input id="password" type="password" value="" size="30"
				name="password" tabindex="2" />
		</div>
		<div>
			<label for="nickname">
				昵称 (required):
			</label>
			<input id="nickname" type="text" value="" size="30" name="nickname"
				tabindex="3" />
		</div>

		<div
			style="position: relative; left: 50%; width: 200px; margin-left: -100px;">
			<img id="captcha" src="<html:rewrite action='/Captcha.do'/>" />
		</div>
		<div>
			<label for="captchaAnswer">
				captchaAnswer(required)
			</label>
			<input id="captchaAnswer" type="text" value="" size="30"
				name="captchaAnswer" tabindex="4" />
		</div>

		<input type="submit" value="提 交" tabindex="5" />
		<input type="reset" value="重 置" tabindex="6" />
	</fieldset>
</form>