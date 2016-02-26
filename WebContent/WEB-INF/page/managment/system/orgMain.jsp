<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/tld/struts-logic.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="template report">
		<link href="<%=request.getContextPath()%>/css/css.css"
			rel="stylesheet" type="text/css" />
		<link rel="stylesheet"
			href="<%=basePath%>ext-3.2.0/resources/css/ext-all.css"
			type="text/css"></link>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/interface/SelectSystemDataBase.js'></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/engine.js'></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/util.js'></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/WdatePicker.js" defer="defer"></script>
		<style type="text/css">
<!--
body {
	background-color: #F0F8FB;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}

A {
	COLOR: #0033FF; font face=Verdana;
	font-size: 9pt
}
-->
</style>
		<script type="text/javascript">

	String.prototype.trim = function()
{
   return this.replace(/(^\s+)|\s+$/g,"");
   }
		Ext.BLANK_IMAGE_URL = "/csims/ext-3.2.0/resources/images/default/tree/s.gif";	 
		Ext.onReady(function(){
			var url="SystemBaseInfoManagerAction.do?method=orgTree";
		    var config={
		         //指定当前的元素渲染的层Id
	            renderTo:"tree-div",
	            autoScroll : true,
	            height:500,
				width:300,
	            
	             //指定当前树的根节点id
	            root:new Ext.tree.AsyncTreeNode({
							id:'0'
				}),
			     listeners:{
							"click":function(node,event){
								getOrgByNo(node.id);
							}
						}
	         };
   			 var tree = new TreePanelFil(config,url,null)	
		});
		
		function getOrgByNo(orgNo){
			SelectSystemDataBase.getOrgByNo(orgNo,setOrg);
		}
		function setOrg(data){
			document.getElementById("orgName").value = data[0];	
			document.getElementById("orgNo").value = data[1];
			document.getElementById("orgCodeOfZz").value = data[2];	
			document.getElementById("orgCodeOfXy").value = data[3];
			document.getElementById("e").value = data[4];	
			document.getElementById("g").value = data[5];
			document.getElementById("k").value = data[6];
			document.getElementById("kChoice").value = data[7];
			document.getElementById("m").value = data[8];
			document.getElementById("mChoice").value = data[9];
			document.getElementById("o").value = data[10];
			document.getElementById("q").value = data[11];
			document.getElementById("s").value = data[12];
			document.getElementById("t").value = data[13];
			document.getElementById("z").value = data[14];
			document.getElementById("aa").value = data[15];
			document.getElementById("isPcb").value = data[16];
		}									

		function showOrgCreate(){
			var orgNo = document.getElementById("orgNo").value;
			if(orgNo=="" || orgNo ==null){
				alert("请选择上级机构!");
				return false;
			}
			document.getElementById("newOrg").style.display = "inline";
		}
		
		function orgHidden(){
			document.getElementById("newOrg").style.display = "none";
		}
		
		function showOrgComCreate(){
			var orgNo = document.getElementById("orgNo").value;
			if(orgNo=="" || orgNo ==null){
				alert("请选择被表彰机构!");
				return false;
			}
			document.getElementById("newOrgCom").style.display = "inline";
		}
		
		function orgComHidden(){
			document.getElementById("newOrgCom").style.display = "none";
		}
		
		function orgComCreate(){
			var comDate = document.getElementById("comDate").value;
			var comContent = document.getElementById("comContent").value;
			var organizationChoice = document.getElementById("organizationChoice").value;
			if(comDate == ""){
				alert("请选择表彰时间！");
				return false;
			}
			if(comContent == ""){
				alert("请输入表彰内容！");
				return false;
			}
			if(organizationChoice == ""){
				alert("请选择表彰机构！");
				return false;
			}
			if(confirm("操作将在选择机构下新增表彰信息,确认?")){
				document.forms[0].action = "SystemBaseInfoManagerAction.do?method=orgComCreate";
				document.forms[0].submit();
			}
		}
		
		function orgDelete(){
			var orgNo = document.getElementById("orgNo").value;
			if(orgNo == ""){
				alert("请选择拟删除机构!");
				return false;
			}
			if(confirm("操作将删除选中机构,确认?")){
				document.forms[0].action = "SystemBaseInfoManagerAction.do?method=orgDelete";
				document.forms[0].submit();
			}
		}
		
		function orgCreate(){
			var newOrgNo = document.getElementById("newOrgNo").value;
			var newOrgName = document.getElementById("newOrgName").value;
			var newIsPcb = document.getElementById("newIsPcb").value;
			var newE = document.getElementById("newE").value;
			var newG = document.getElementById("newG").value;
			var newKChoice = document.getElementById("newKChoice").value;
			var newMChoice = document.getElementById("newMChoice").value;
			if(newOrgNo == ""){
				alert("请输入机构编号！");
				return false;
			}
			if(newOrgName == ""){
				alert("请输入机构名称！");
				return false;
			}
			if(newE == ""){
				alert("请选择一级类别名称！");
				return false;
			}
			if(newG == ""){
				alert("请选择二级类别名称！");
				return false;
			}
			if(newKChoice == ""){
				alert("请选择直属上级行！");
				return false;
			}
			if(newMChoice == ""){
				alert("请选择所属人民银行！");
				return false;
			}
			if(newIsPcb == ""){
				alert("请选择机构属性！");
				return false;
			}
			if(confirm("操作将在选择机构下新增机构,确认?")){
				document.forms[0].action = "SystemBaseInfoManagerAction.do?method=orgCreate";
				document.forms[0].submit();
			}
		}
		
		function orgModify(){
			var orgNo = document.getElementById("orgNo").value;
			var orgName = document.getElementById("orgName").value;
			var isPcb = document.getElementById("isPcb").value;
			var e = document.getElementById("e").value;
			var g = document.getElementById("g").value;
			if(orgNo == ""){
				alert("请选择拟修改机构!");
				return false;
			}
			if(orgName == ""){
				alert("请输入机构名称!");
				return false;
			}
			if(e == ""){
				alert("请选择一级类别名称！");
				return false;
			}
			if(g == ""){
				alert("请选择二级类别名称！");
				return false;
			}
			if(isPcb == ""){
				alert("请选择机构属性!");
				return false;
			}
			if(confirm("操作将修改选中机构名称,确认?")){
				document.forms[0].action = "SystemBaseInfoManagerAction.do?method=orgModify";
				document.forms[0].submit();
			}
		}

		String.prototype.trim = function(){
		   	return this.replace(/(^\s+)|\s+$/g,"");
		}
		var MyWindowUiPcbOrg="";
		   	function getPcbOrgTree(id){
				Ext.BLANK_IMAGE_URL = "/csims/ext-3.2.0/resources/images/default/tree/s.gif";
				var url="SystemBaseInfoManagerAction.do?method=pcbOrgTree";
			    var config={
			   			//指定当前的元素渲染的层Id
			            autoScroll : true,
			            height:420,
						width:340,
			             //指定当前树的根节点id
			            root:new Ext.tree.AsyncTreeNode({
			            url : url,      
                                  	requestMethod : 'POST',  
									id:'0'
						}),
					    listeners:{
									"dblclick":function(node,event){
									   if(confirm("是否选择:"+node.text)){
									   		var ChoiceId = id+'Choice';
									 		document.getElementById(ChoiceId).value= node.text;
											document.getElementById(id).value= node.id;
										 	MyWindowUiPcbOrg.hide();
									 	  }
								  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiPcbOrg == "" || typeof(MyWindowUiPcbOrg) != "object" ){
					MyWindowUiPcbOrg = new Ext.Window({
					    title: '人民银行机构',
					    id: totalId,
					    width: 350,
					    height: 450,
					    layout: 'column',
					    resizable:false, //变大小 
					    closeAction : 'hide' ,
					    items: tree,
					    listeners:{ 
					       "close":function(){ 
					          MyWindowUiPcbOrg.hide();
							} 
			            }
				    });   
					MyWindowUiPcbOrg.show();
	         }else{
	             MyWindowUiPcbOrg.show();
	         }
         }
         
       	var MyWindowUiOrg="";
		   function getOrgTree(id){
				Ext.BLANK_IMAGE_URL = "/csims/ext-3.2.0/resources/images/default/tree/s.gif";
				var url="SystemBaseInfoManagerAction.do?method=orgTree";
			    var config={
			   			//指定当前的元素渲染的层Id
			            autoScroll : true,
			            height:420,
						width:340,
			             //指定当前树的根节点id
			            root:new Ext.tree.AsyncTreeNode({
			            url : url,      
                                  	requestMethod : 'POST',  
									id:'0'
						}),
					    listeners:{
									"dblclick":function(node,event){
									   if(confirm("是否选择:"+node.text)){
									   		var ChoiceId = id+'Choice';
									 		document.getElementById(ChoiceId).value= node.text;
											document.getElementById(id).value= node.id;
										 	MyWindowUiOrg.hide();
									   }
								}
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiOrg == "" || typeof(MyWindowUiOrg) != "object" ){
					MyWindowUiOrg = new Ext.Window({
					    title: '机构',
					    id: totalId,
					    width: 350,
					    height: 450,
					    layout: 'column',
					    resizable:false, //变大小 
					    closeAction : 'hide' ,
					    items: tree,
					    listeners:{ 
					       "close":function(){ 
					          MyWindowUiOrg.hide();
							} 
			            }
				    });   
					MyWindowUiOrg.show();
	         }else{
	             MyWindowUiOrg.show();
	         }
         }
		
		
       	var MyWindowUiPcbOrgOfNew="";
		   	function getPcbOrgTreeOfNew(id){
				Ext.BLANK_IMAGE_URL = "/csims/ext-3.2.0/resources/images/default/tree/s.gif";
				var url="SystemBaseInfoManagerAction.do?method=pcbOrgTree";
			    var config={
			   			//指定当前的元素渲染的层Id
			            autoScroll : true,
			            height:420,
						width:340,
			             //指定当前树的根节点id
			            root:new Ext.tree.AsyncTreeNode({
			            url : url,      
                                  	requestMethod : 'POST',  
									id:'0'
						}),
					    listeners:{
									"dblclick":function(node,event){
									   if(confirm("是否选择:"+node.text)){
									   		var ChoiceId = id+'Choice';
									 		document.getElementById(ChoiceId).value= node.text;
											document.getElementById(id).value= node.id;
										 	MyWindowUiPcbOrgOfNew.hide();
									 	  }
								  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiPcbOrgOfNew == "" || typeof(MyWindowUiPcbOrgOfNew) != "object" ){
					MyWindowUiPcbOrgOfNew = new Ext.Window({
					    title: '人民银行机构',
					    id: totalId,
					    width: 350,
					    height: 450,
					    layout: 'column',
					    resizable:false, //变大小 
					    closeAction : 'hide' ,
					    items: tree,
					    listeners:{ 
					       "close":function(){ 
					          MyWindowUiPcbOrgOfNew.hide();
							} 
			            }
				    });   
					MyWindowUiPcbOrgOfNew.show();
	         }else{
	             MyWindowUiPcbOrg.show();
	         }
         }
         
       	var MyWindowUiOrgOfNew="";
		   function getOrgTreeOfNew(id){
				Ext.BLANK_IMAGE_URL = "/csims/ext-3.2.0/resources/images/default/tree/s.gif";
				var url="SystemBaseInfoManagerAction.do?method=orgTree";
			    var config={
			   			//指定当前的元素渲染的层Id
			            autoScroll : true,
			            height:420,
						width:340,
			             //指定当前树的根节点id
			            root:new Ext.tree.AsyncTreeNode({
			            url : url,      
                                  	requestMethod : 'POST',  
									id:'0'
						}),
					    listeners:{
									"dblclick":function(node,event){
									   if(confirm("是否选择:"+node.text)){
									   		var ChoiceId = id+'Choice';
									 		document.getElementById(ChoiceId).value= node.text;
											document.getElementById(id).value= node.id;
										 	MyWindowUiOrgOfNew.hide();
									   }
								}
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiOrgOfNew == "" || typeof(MyWindowUiOrgOfNew) != "object" ){
					MyWindowUiOrgOfNew = new Ext.Window({
					    title: '机构',
					    id: totalId,
					    width: 350,
					    height: 450,
					    layout: 'column',
					    resizable:false, //变大小 
					    closeAction : 'hide' ,
					    items: tree,
					    listeners:{ 
					       "close":function(){ 
					          MyWindowUiOrgOfNew.hide();
							} 
			            }
				    });   
					MyWindowUiOrgOfNew.show();
	         }else{
	             MyWindowUiOrgOfNew.show();
	         }
         }
         
         var MyWindowUiOrgCom="";
		   function getOrgComTree(id){
				Ext.BLANK_IMAGE_URL = "/csims/ext-3.2.0/resources/images/default/tree/s.gif";
				var url="SystemBaseInfoManagerAction.do?method=orgTree";
			    var config={
			   			//指定当前的元素渲染的层Id
			            autoScroll : true,
			            height:420,
						width:340,
			             //指定当前树的根节点id
			            root:new Ext.tree.AsyncTreeNode({
			            url : url,      
                                  	requestMethod : 'POST',  
									id:'0'
						}),
					    listeners:{
									"dblclick":function(node,event){
									   if(confirm("是否选择:"+node.text)){
									   		var ChoiceId = id+'Choice';
									 		document.getElementById(ChoiceId).value= node.text;
											document.getElementById(id).value= node.id;
										 	MyWindowUiOrgCom.hide();
									   }
								}
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiOrgCom == "" || typeof(MyWindowUiOrgCom) != "object" ){
					MyWindowUiOrgCom = new Ext.Window({
					    title: '机构',
					    id: totalId,
					    width: 350,
					    height: 450,
					    layout: 'column',
					    resizable:false, //变大小 
					    closeAction : 'hide' ,
					    items: tree,
					    listeners:{ 
					       "close":function(){ 
					          MyWindowUiOrgCom.hide();
							} 
			            }
				    });   
					MyWindowUiOrgCom.show();
	         }else{
	             MyWindowUiOrgCom.show();
	         }
         }
</script>
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
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								系统数据维护
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								机构维护
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/SystemBaseInfoManagerAction.do?method=orgCreate"
			method="post">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="20%" style="padding-left: 10px">
						<div class="tiltlegerner">
							机构
						</div>
						<div id="tree-div"
							style="height: 1200px; width: 220px; border: 0px solid #c3daf9;"></div>
					</td>
					<td align="left" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td valign="top">
									<table width="100%" border="0" cellpadding="0" cellspacing="0">
										<tr>
											<td class="tablestyle">
												<table width="100%" border="0" cellpadding="0"
													cellspacing="0">
													<tr>
														<td>
															<table width="100%" border="0" cellspacing="0"
																cellpadding="0">
																<tr>
																	<td class="tiltlegerner">
																		机构信息
																	</td>
																</tr>
															</table>
														</td>
													</tr>
													<tr>
														<td height="180" valign="top">
															<table width="100%" border="0" cellpadding="0"
																cellspacing="0" class="tableline01">
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>机构名称
																	</td>
																	<td align="left">
																		<html:text property="orgName" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>机构编号
																	</td>
																	<td align="left">
																		<html:text property="orgNo" size="70%"
																			styleClass="text11155" readonly="true"></html:text>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		组织机构代码
																	</td>
																	<td align="left">
																		<html:text property="orgCodeOfZz" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		机构信用代码
																	</td>
																	<td align="left">
																		<html:text property="orgCodeOfXy" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>一级类别名称
																	</td>
																	<td align="left">
																		<html:select property="e">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="货币当局">货币当局</html:option>
																			<html:option value="银行业存款类金融机构">银行业存款类金融机构</html:option>
																			<html:option value="银行业非存款类金融机构">银行业非存款类金融机构</html:option>
																			<html:option value="交易及结算类金融机构">交易及结算类金融机构</html:option>
																			<html:option value="证券业金融机构">证券业金融机构</html:option>
																			<html:option value="保险业金融机构">保险业金融机构</html:option>
																			<html:option value="其他">其他</html:option>
																		</html:select>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>二级类别名称
																	</td>
																	<td align="left">
																		<html:select property="g">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="中国人民银行">中国人民银行</html:option>
																			<html:option value="银行">银行</html:option>
																			<html:option value="银行卡组织">银行卡组织</html:option>
																			<html:option value="农村信用合作社（含联社）">农村信用合作社（含联社）</html:option>
																			<html:option value="农村资金互助社">农村资金互助社</html:option>
																			<html:option value="贷款公司">贷款公司</html:option>
																			<html:option value="小额贷款公司">小额贷款公司</html:option>
																			<html:option value="金融资产管理公司">金融资产管理公司</html:option>
																			<html:option value="财产保险公司">财产保险公司</html:option>
																			<html:option value="融资性担保公司">融资性担保公司</html:option>
																			<html:option value="消费金融公司">消费金融公司</html:option>
																			<html:option value="证券公司">证券公司</html:option>
																			<html:option value="信托公司">信托公司</html:option>
																			<html:option value="人身保险公司">人身保险公司</html:option>
																			<html:option value="企业年金">企业年金</html:option>
																			<html:option value="期货公司">期货公司</html:option>
																			<html:option value="财务公司">财务公司</html:option>
																			<html:option value="非金融支付机构">非金融支付机构</html:option>
																			<html:option value="其他">其他</html:option>
																		</html:select>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>直属上级行名称
																	</td>
																	<td align="left">
																		<html:text property="kChoice" size="70%"
																			styleClass="text11155" readonly="true"></html:text>
																		<input type="button" value="选择" class="botton01"
																			onclick="getOrgTree('k')" />
																		<html:hidden property="kChoice"></html:hidden>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		直属上级行代码
																	</td>
																	<td align="left">
																		<html:text property="k" size="70%"
																			styleClass="text11155" readonly="true"></html:text>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>所属人民银行名称
																	</td>
																	<td align="left">
																		<html:text property="mChoice" size="70%"
																			styleClass="text11155" readonly="true"></html:text>
																		<input type="button" value="选择" class="botton01"
																			onclick="getPcbOrgTree('m')" />
																		<html:hidden property="mChoice"></html:hidden>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		所属人民银行代码
																	</td>
																	<td align="left">
																		<html:text property="m" size="70%"
																			styleClass="text11155" readonly="true"></html:text>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		机构所在省地区名称
																	</td>
																	<td align="left">
																		<html:text property="o" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		机构所在地区名称
																	</td>
																	<td align="left">
																		<html:text property="q" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		机构所在城市名称
																	</td>
																	<td align="left">
																		<html:text property="s" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		机构邮政编码
																	</td>
																	<td align="left">
																		<html:text property="t" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		地址
																	</td>
																	<td align="left">
																		<html:text property="z" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		法人代表/负责人
																	</td>
																	<td align="left">
																		<html:text property="aa" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr class="tabletext02">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>机构属性
																	</td>
																	<td align="left">
																		<html:select property="isPcb">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="YES">人行机构</html:option>
																			<html:option value="NO">非人行机构</html:option>
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td colspan="2" align="center">
																		<input type="button" value="新增机构" class="botton01"
																			onclick="return showOrgCreate();" />
																		<input type="button" value="新增表彰" class="botton01"
																			onclick="return showOrgComCreate();" />
																		<input type="button" value="修改机构" class="botton01"
																			onclick="return orgModify();" />
																		<input type="button" name="orgDelete" disabled
																			value="删除机构" class="botton01"
																			onclick="return orgDelete();" />
																		<input type="button" value="返回" class="botton01"
																			onclick="javascript:history.back()" />
																	</td>
																</tr>
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td class="tablestyle">
												<div id="newOrg" style="display: none;">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0">
														<tr>
															<td>
																<table width="100%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tr>
																		<td class="tiltlegerner">
																			新增机构
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td height="180" valign="top">
																<table width="100%" border="0" cellpadding="0"
																	cellspacing="0" class="tableline01">
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			<font color='#FF0000'>*</font>机构编号
																		</td>
																		<td align="left">
																			<html:text property="newOrgNo" size="70%"
																				styleClass="text11155"></html:text>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			<font color='#FF0000'>*</font>机构名称
																		</td>
																		<td align="left">
																			<html:text property="newOrgName" size="70%"
																				styleClass="text11155"></html:text>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			组织机构代码
																		</td>
																		<td align="left">
																			<html:text property="newOrgCodeOfZz" size="70%"
																				styleClass="text11155"></html:text>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			机构信用代码
																		</td>
																		<td align="left">
																			<html:text property="newOrgCodeOfXy" size="70%"
																				styleClass="text11155"></html:text>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			<font color='#FF0000'>*</font>一级类别名称
																		</td>
																		<td align="left">
																			<html:select property="newE">
																				<html:option value="">---请选择---</html:option>
																				<html:option value="货币当局">货币当局</html:option>
																				<html:option value="银行业存款类金融机构">银行业存款类金融机构</html:option>
																				<html:option value="银行业非存款类金融机构">银行业非存款类金融机构</html:option>
																				<html:option value="交易及结算类金融机构">交易及结算类金融机构</html:option>
																				<html:option value="证券业金融机构">证券业金融机构</html:option>
																				<html:option value="保险业金融机构">保险业金融机构</html:option>
																				<html:option value="其他">其他</html:option>
																			</html:select>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			<font color='#FF0000'>*</font>二级类别名称
																		</td>
																		<td align="left">
																			<html:select property="newG">
																				<html:option value="">---请选择---</html:option>
																				<html:option value="中国人民银行">中国人民银行</html:option>
																				<html:option value="银行">银行</html:option>
																				<html:option value="银行卡组织">银行卡组织</html:option>
																				<html:option value="农村信用合作社（含联社）">农村信用合作社（含联社）</html:option>
																				<html:option value="农村资金互助社">农村资金互助社</html:option>
																				<html:option value="贷款公司">贷款公司</html:option>
																				<html:option value="小额贷款公司">小额贷款公司</html:option>
																				<html:option value="金融资产管理公司">金融资产管理公司</html:option>
																				<html:option value="财产保险公司">财产保险公司</html:option>
																				<html:option value="融资性担保公司">融资性担保公司</html:option>
																				<html:option value="消费金融公司">消费金融公司</html:option>
																				<html:option value="证券公司">证券公司</html:option>
																				<html:option value="信托公司">信托公司</html:option>
																				<html:option value="人身保险公司">人身保险公司</html:option>
																				<html:option value="企业年金">企业年金</html:option>
																				<html:option value="期货公司">期货公司</html:option>
																				<html:option value="财务公司">财务公司</html:option>
																				<html:option value="非金融支付机构">非金融支付机构</html:option>
																				<html:option value="其他">其他</html:option>
																			</html:select>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			<font color='#FF0000'>*</font>直属上级行名称
																		</td>
																		<td align="left">
																			<html:text property="newKChoice" size="70%"
																				styleClass="text11155" readonly="true"></html:text>
																			<input type="button" value="选择" class="botton01"
																				onclick="getOrgTreeOfNew('newK')" />
																			<html:hidden property="newKChoice"></html:hidden>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			直属上级行代码
																		</td>
																		<td align="left">
																			<html:text property="newK" size="70%"
																				styleClass="text11155" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			<font color='#FF0000'>*</font>所属人民银行名称
																		</td>
																		<td align="left">
																			<html:text property="newMChoice" size="70%"
																				styleClass="text11155" readonly="true"></html:text>
																			<input type="button" value="选择" class="botton01"
																				onclick="getPcbOrgTreeOfNew('newM')" />
																			<html:hidden property="newMChoice"></html:hidden>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			所属人民银行代码
																		</td>
																		<td align="left">
																			<html:text property="newM" size="70%"
																				styleClass="text11155" readonly="true"></html:text>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			机构所在省地区名称
																		</td>
																		<td align="left">
																			<html:text property="newO" size="70%"
																				styleClass="text11155"></html:text>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			机构所在地区名称
																		</td>
																		<td align="left">
																			<html:text property="newQ" size="70%"
																				styleClass="text11155"></html:text>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			机构所在城市名称
																		</td>
																		<td align="left">
																			<html:text property="newS" size="70%"
																				styleClass="text11155"></html:text>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			机构邮政编码
																		</td>
																		<td align="left">
																			<html:text property="newT" size="70%"
																				styleClass="text11155"></html:text>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			地址
																		</td>
																		<td align="left">
																			<html:text property="newZ" size="70%"
																				styleClass="text11155"></html:text>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			法人代表/负责人
																		</td>
																		<td align="left">
																			<html:text property="newAA" size="70%"
																				styleClass="text11155"></html:text>
																		</td>
																	</tr>
																	<tr>
																		<td align="right" class="tabletext02">
																			<font color='#FF0000'>*</font>机构属性
																		</td>
																		<td align="left">
																			<html:select property="newIsPcb">
																				<html:option value="">---请选择---</html:option>
																				<html:option value="YES">人行机构</html:option>
																				<html:option value="NO">非人行机构</html:option>
																			</html:select>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2" align="center">
																			<input type="button" value="保存" class="botton01"
																				onclick="orgCreate();" />
																			<input type="button" value="取消" class="botton01"
																				onclick="orgHidden();" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</div>
											</td>
										</tr>
										<tr>
											<td class="tablestyle">
												<div id="newOrgCom" style="display: none;">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0">
														<tr>
															<td>
																<table width="100%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tr>
																		<td class="tiltlegerner">
																			机构表彰信息
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
														<tr>
															<td height="180" valign="top">
																<table width="100%" border="0" cellpadding="0"
																	cellspacing="0" class="tableline01">
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			<font color='#FF0000'>*</font>表彰时间
																		</td>
																		<td align="left">
																			<html:text property="comDate" maxlength="20"
																				size="15" styleClass="text111"
																				onkeydown="if(event.keyCode==13){search();}"
																				onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" />
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			<font color='#FF0000'>*</font>表彰内容
																		</td>
																		<td align="left">
																			<html:textarea property="comContent" cols="140%"
																				rows="5"></html:textarea>
																		</td>
																	</tr>
																	<tr class="tabletext02">
																		<td align="right" class="tabletext02">
																			<font color='#FF0000'>*</font>表彰机构
																		</td>
																		<td align="left">
																			<html:text property="organizationChoice"
																				styleClass="text11155" readonly="true"></html:text>
																			<input type="button" value="选择" class="botton01"
																				onclick="getOrgComTree('organization')" />
																			<html:hidden property="organization"></html:hidden>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2" align="center">
																			<input type="button" value="保存" class="botton01"
																				onclick="orgComCreate();" />
																			<input type="button" value="取消" class="botton01"
																				onclick="orgComHidden();" />
																		</td>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</div>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</html:form>
	</body>
</html>
