<%@ page contentType="text/html;charset=GBK"%>
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

		<title>选择机构</title>
		<link rel="stylesheet" href="<%=path%>/ext-3.2.0/resources/css/ext-all.css" type="text/css"></link>
		<script type="text/javascript" src="<%=path%>/ext-3.2.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=path%>/ext-3.2.0/ext-all.js"></script>
		<script type="text/javascript" src="<%=path%>/ext-3.2.0/epandsTree.js"></script>
	</head>
	<script type="text/javascript">
    var o = "";
   	var oId = "";
   	Ext.BLANK_IMAGE_URL = "<%=basePath%>/ext-3.2.0/resources/images/default/tree/s.gif";	 
	Ext.onReady(function(){
	var url="User.do?method=orgListTree";
		      var config={
		         //指定当前的元素渲染的层Id
	             renderTo:"treeOrg-div",
	            
	             //指定当前树的根节点id
	             root:new Ext.tree.AsyncTreeNode({
							id:'0'
						}),
				 autoScroll : true,
				 width:320,
				 height:360,
			     listeners:{"click":function(node,event){
								o = node.text;
								oId = node.id;
								document.getElementById("lorg").innerText = " 选择的机构是："+node.text;
							}
						}
	         };
	      
   			var treess = new TreePanelFil(config,url,null);
   	});
 
	var main=window.dialogArguments;

	function selectOrg(){
		if(o != ""){
			if(confirm("确定选中的机构吗？")){
				main.org = o;
				main.orgId = oId;
				main.setOrg();
				window.close();
			}
		}else{
			alert("请选择一个机构！");
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
					<table width="100%" border="0" cellpadding="0" cellspacing="0"
						class="back01">
						<tr>
							<td bgcolor="#A0BFDB">
								<table width="100%" border="0" cellpadding="0" cellspacing="1"
									class="jianju01">

									<tr>
										<td colspan="2" bgcolor="#CFEAFF" class="txt04">
											<div id="treeOrg-div"
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
										onclick="selectOrg();" value="确 定" />
									<input name="Submit1022" type="button" class="button02"
										onclick="window.close();" value="关 闭" />
								</div>
								<div align="left" style="font-size: 12">
									&nbsp;
								</div>
								<div id="lorg" align="left" style="font-size: 12">
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
