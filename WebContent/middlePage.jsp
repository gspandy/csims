<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title></title>
  <link href="css/css.css" rel="stylesheet" type="text/css" />
<script src="js/manu.js" language="javascript1.2"></script>
<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
	background-color: #D9ECF7;
}

.hand
{cursor:pointer;}
.div1{
	width: 8px;
	padding-left: 0px;
   padding-top: 200px;
   text-align: center;
}
-->
</style>
<link href="css/home.css" rel="stylesheet" type="text/css" />
<script language="javascript" type="text/javascript"> 
	function show_Hide_Menu()
	{ 
		if(window.parent.parent.center.cols=="185,8,*")
		{
			document.getElementById("menuICON").src="images/CollapseBtn_2.jpg";
			document.getElementById("menuICON").alt="Show Menu"
			window.parent.parent.center.cols="0,8,*";
		}
		else
		{
			document.getElementById("menuICON").src="images/CollapseBtn_1.jpg";
			document.getElementById("menuICON").alt="Hidden Menu"
			window.parent.parent.center.cols="185,8,*";
		}
	}

</script>
<script type="text/javascript">
function overchangeico()
{
	if (document.getElementById("menuICON").alt == "Hidden Menu")
	{
		document.getElementById("menuICON").src="images/CollapseBtn_11.jpg";
	}
	else
	{
		document.getElementById("menuICON").src="images/CollapseBtn_22.jpg";
	}
}
function outchangico()
{
  if (document.getElementById("menuICON").alt == "Hidden Menu")
	{
		document.getElementById("menuICON").src="images/CollapseBtn_1.jpg";
	}
	else
	{
		document.getElementById("menuICON").src="images/CollapseBtn_2.jpg";
	}
}
</script>
</head>

<body >
<!--<table  width="6" height="100%" border="0" cellspacing="0" cellpadding="0" 
  style="LEFT: 0px; POSITION: absolute;background-image:url(images/CollapseBtn_bg.jpg);background-repeat:repeat-x;">
  <tr>
    <td vAlign="middle" class="tablecl2" >
	   <img id="menuICON" class="hand" src="images/zhengxin253.jpg" width="6" height="50" alt="Hidden Menu"
		  onClick="javascript:show_Hide_Menu();"/>
	 </td>
  </tr>
</table>-->


<div class="div1">
	   <img id="menuICON" class="hand" src="images/CollapseBtn_1.jpg" width="8" height="50" alt="Hidden Menu"
		 onMouseOver="overchangeico()" onMouseOut="outchangico()"  onClick="javascript:show_Hide_Menu();"/>
</div>

</body>
</html>