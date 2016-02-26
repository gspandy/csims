<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%> 
<html>
	<head>
		<title>选择地区</title>
		<link rel="stylesheet"
			href="<%=path%>/ext-3.2.0/resources/css/ext-all.css" type="text/css"></link>
		<script type="text/javascript"
			src="<%=path%>/ext-3.2.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=path%>/ext-3.2.0/ext-all.js"></script>
		<script type="text/javascript" src="<%=path%>/ext-3.2.0/epandsTree.js"></script>
	</head>
	<script type="text/javascript">
    var a = "";
   	var aId = "";
   	Ext.BLANK_IMAGE_URL = "<%=basePath%>ext-3.2.0/resources/images/default/tree/s.gif";	 
	Ext.onReady(function(){
	var url="User.do?method=areaListTree";
		      var config={
		         //指定当前的元素渲染的层Id
	             renderTo:"treeArea-div",
	            
	             //指定当前树的根节点id
	             root:new Ext.tree.AsyncTreeNode({
							id:'0'
						}),
				 autoScroll : true,
				 width:320,
				 height:360,
			     listeners:{"click":function(node,event){
								a = node.text;
								aId = node.id;
								document.getElementById("larea").innerText = " 选择的地区是："+node.text;
							}
						}
	         };
	      
   			var trees = new TreePanelFil(config,url,null);
   });
 
var main=window.dialogArguments;

	function selectArea(){
		if(a != ""){
			if(confirm("确定选中的地区吗？")){
				main.area = a;
				main.areaId = aId;
				main.setArea();
				window.close();
			}
		}else{
			alert("请选择一个地区！");
		}	
	}
</script>

	<body style="overflow: hidden;">
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td></td>
			</tr>
			<tr>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="back01">
						<tr>
							<td bgcolor="#A0BFDB">
								<table width="100%" border="0" cellpadding="0" cellspacing="1"
									class="jianju01">

									<tr>
										<td colspan="2" bgcolor="#CFEAFF" class="txt04">
											<div id="treeArea-div"
												style="height: 360px; width: 340px; border: 0px solid #c3daf9; margin-left: 10px;">

											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="2" class="txt03">
								<div align="center">
									<input name="Submit1023" type="button" class="button02"
										onclick="selectArea();" value="确 定" />
									<input name="Submit1022" type="button" class="button02"
										onclick="window.close();" value="关 闭" />
								</div>
								<div align="left" style="font-size: 12">
									&nbsp;
								</div>
								<div id="larea" align="left" style="font-size: 12">
								</div>
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<br>
					<br>
					<br>
				</td>
			</tr>
		</table>
	</body>
</html>
