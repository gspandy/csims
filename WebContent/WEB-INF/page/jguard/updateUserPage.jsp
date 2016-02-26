<%@ taglib prefix="html" uri="/WEB-INF/tld/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/tld/struts-bean.tld" prefix="bean"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="java.util.Set,java.util.List,java.util.Iterator,net.sf.jguard.core.principals.RolePrincipal"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String flag = (String) request.getAttribute("flag");
	String status = (String) request.getAttribute("status");
	String zxStr = (String) request.getAttribute("zxStr");
	String photoPath = (String) request.getAttribute("photoPath");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<style type="text/css">
</style>
		<title><bean:message key="PROJECT_NAME" /></title>
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
			src="<%=request.getContextPath()%>/js/idCard.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/js/zxPrincipal.js"></script>
		<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=basePath%>ext-3.2.0/ext-all.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTree.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>ext-3.2.0/epandsTreeOne.js"></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/interface/SelectSystemDataBase.js'></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/engine.js'></script>
		<script type='text/javascript'
			src='<%=request.getContextPath()%>/dwr/util.js'></script>
		<script type="text/javascript" src="js/organization.js"></script>
		<link href="css/menu.css" rel="stylesheet" type="text/css" />
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/windowopen.js"></script>
		<script type="text/javascript">	
			String.prototype.trim = function(){
		   		return this.replace(/(^\s+)|\s+$/g,"");
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
										 	var orgNo = document.getElementById("organization").value;
										 	getOrgInfo(orgNo);
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
         
        var openCallbackBuildDept = function (optionString){
	   		 $("#dept").html(optionString);
		}
		function getOrgInfo(orgNo){
			SelectSystemDataBase.getOrgInfo(orgNo,setOrgInfo);
		}
		function setOrgInfo(data){
			var orgNo = data[0];
			var orgCodeOfZz = data[1];
			var orgCodeOfXy = data[2];
			document.getElementById("orgCodeOfZz").value= orgCodeOfZz;
			document.getElementById("orgCodeOfXy").value= orgCodeOfXy;
			getDeptByOrgNo(orgNo);
		}
		
		var MyWindowUiZxOrg1="";
		   function getOrgTreeOfZx1(id){
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
										 	MyWindowUiZxOrg1.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiZxOrg1 == "" || typeof(MyWindowUiZxOrg1) != "object" ){
					MyWindowUiZxOrg1 = new Ext.Window({
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
					          MyWindowUiZxOrg1.hide();
							} 
			            }
				    });   
					MyWindowUiZxOrg1.show();
	         }else{
	             MyWindowUiZxOrg1.show();
	         }
         }
         
        var MyWindowUiZxOrg2="";
		   function getOrgTreeOfZx2(id){
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
										 	MyWindowUiZxOrg2.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiZxOrg2 == "" || typeof(MyWindowUiZxOrg2) != "object" ){
					MyWindowUiZxOrg2 = new Ext.Window({
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
					          MyWindowUiZxOrg2.hide();
							} 
			            }
				    });   
					MyWindowUiZxOrg2.show();
	         }else{
	             MyWindowUiZxOrg2.show();
	         }
         }
         
             
        var MyWindowUiZxOrg3="";
		   function getOrgTreeOfZx3(id){
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
										 	MyWindowUiZxOrg3.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiZxOrg3 == "" || typeof(MyWindowUiZxOrg3) != "object" ){
					MyWindowUiZxOrg3 = new Ext.Window({
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
					          MyWindowUiZxOrg3.hide();
							} 
			            }
				    });   
					MyWindowUiZxOrg3.show();
	         }else{
	             MyWindowUiZxOrg3.show();
	         }
         }
         
             
        var MyWindowUiZxOrg4="";
		   function getOrgTreeOfZx4(id){
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
										 	MyWindowUiZxOrg4.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiZxOrg4 == "" || typeof(MyWindowUiZxOrg4) != "object" ){
					MyWindowUiZxOrg4 = new Ext.Window({
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
					          MyWindowUiZxOrg4.hide();
							} 
			            }
				    });   
					MyWindowUiZxOrg4.show();
	         }else{
	             MyWindowUiZxOrg4.show();
	         }
         }
         
             
        var MyWindowUiZxOrg5="";
		   function getOrgTreeOfZx5(id){
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
										 	MyWindowUiZxOrg5.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiZxOrg5 == "" || typeof(MyWindowUiZxOrg5) != "object" ){
					MyWindowUiZxOrg5 = new Ext.Window({
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
					          MyWindowUiZxOrg5.hide();
							} 
			            }
				    });   
					MyWindowUiZxOrg5.show();
	         }else{
	             MyWindowUiZxOrg5.show();
	         }
         }
         
             
        var MyWindowUiZxOrg6="";
		   function getOrgTreeOfZx6(id){
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
										 	MyWindowUiZxOrg6.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiZxOrg6 == "" || typeof(MyWindowUiZxOrg6) != "object" ){
					MyWindowUiZxOrg6 = new Ext.Window({
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
					          MyWindowUiZxOrg6.hide();
							} 
			            }
				    });   
					MyWindowUiZxOrg6.show();
	         }else{
	             MyWindowUiZxOrg6.show();
	         }
         }
         
             
        var MyWindowUiZxOrg7="";
		   function getOrgTreeOfZx7(id){
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
										 	MyWindowUiZxOrg7.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiZxOrg7 == "" || typeof(MyWindowUiZxOrg7) != "object" ){
					MyWindowUiZxOrg7 = new Ext.Window({
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
					          MyWindowUiZxOrg7.hide();
							} 
			            }
				    });   
					MyWindowUiZxOrg7.show();
	         }else{
	             MyWindowUiZxOrg7.show();
	         }
         }
         
             
        var MyWindowUiZxOrg8="";
		   function getOrgTreeOfZx8(id){
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
										 	MyWindowUiZxOrg8.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiZxOrg8 == "" || typeof(MyWindowUiZxOrg8) != "object" ){
					MyWindowUiZxOrg8 = new Ext.Window({
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
					          MyWindowUiZxOrg8.hide();
							} 
			            }
				    });   
					MyWindowUiZxOrg8.show();
	         }else{
	             MyWindowUiZxOrg8.show();
	         }
         }
         
             
        var MyWindowUiZxOrg9="";
		   function getOrgTreeOfZx9(id){
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
										 	MyWindowUiZxOrg9.hide();
									   }
									  }
					    }
		         };
   			var tree = new TreePanelFil(config,url,null)
   		     var  totalId = id+'window';
              if(MyWindowUiZxOrg9 == "" || typeof(MyWindowUiZxOrg9) != "object" ){
					MyWindowUiZxOrg9 = new Ext.Window({
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
					          MyWindowUiZxOrg9.hide();
							} 
			            }
				    });   
					MyWindowUiZxOrg9.show();
	         }else{
	             MyWindowUiZxOrg9.show();
	         }
         }
		</script>

		<script type="text/javascript">
			function changZXName(obj){
				var idName1 = obj.value +"-1";
				var idName2 = obj.value +"-2";
				if(obj.checked){
					document.getElementById(idName1).style.display='block';
					document.getElementById(idName2).style.display='block';
				}else{
					document.getElementById(idName1).style.display='none';
					document.getElementById(idName2).style.display='none';
					var name = obj.value;
					var orgName = name.replace("Name", "Org")+"Choice";
					var orgCode = name.replace("Name", "Org");
					document.getElementById(orgName).value="";
					document.getElementById(orgCode).value="";
					document.getElementById(name).value="";
				}
			}
		
			function disabledZxUser(zxUserId){
				if(confirm("操作将禁用征信用户,确认?")){
					document.forms[0].action="User.do?method=disabledZxUser&zxUserId="+zxUserId;;
					document.forms[0].submit();
				}
			}
			
			function enabledZxUser(zxUserId){
				if(confirm("操作将启用征信用户,确认?")){
					document.forms[0].action="User.do?method=enabledZxUser&zxUserId="+zxUserId;;
					document.forms[0].submit();
				}
			}
			
			function cancelZxUser(zxUserId){
				if(confirm("操作将删除征信用户,确认?")){
					document.forms[0].action="User.do?method=cancelZxUser&zxUserId="+zxUserId;;
					document.forms[0].submit();
				}
			}
		
			function disableUser(){
				document.forms[0].action="User.do?method=disableUser";
				document.forms[0].submit();
			}
			
			function enableUser(){
				document.forms[0].action="User.do?method=enableUser";
				document.forms[0].submit();
			}
			
			function updateUser(){
				var name = document.getElementById("name").value;
				var education = document.getElementById("education").value;
				var post = document.getElementById("post").value;
				var userType = document.getElementById("userType").value;
				var phone = document.getElementById("phone").value;
				var organization = document.getElementById("organization").value;
				var dept = document.getElementById("dept").value;
				var strUserPrincipals="";
				if(name == null || name == ""){
					alert("请输入用户姓名!");
					return false;
				}
				if(userType=="内控监督员"){
					if(post == null || post == ""){
						alert("请输入用户职务!");
						return false;
					}
				}else{
					if(education == null || education == ""){
						alert("请选择用户最高学历!");
						return false;
					}
					if(phone == null || phone == ""){
						alert("请输入用户电话!");
						return false;
					}
				}
				var chkbs = document.getElementsByName("selectPrincipal");  
				var chkNum = 0;
				for(i=0;i<chkbs.length;i++){
					if(chkbs(i).checked){
						chkNum++;
						strUserPrincipals = strUserPrincipals + chkbs(i).value +"-";
					}
				}
				document.getElementById("userPrincipalsNames").value = strUserPrincipals;
				if(chkNum==0){
					alert("请选择监管系统用户角色!");
					return false;
				}
				if(organization == null || organization == ""){
					alert("请选择监管系统用户所属机构!");
					return false;
				}
				if(dept == null || dept == ""){
					alert("请选择监管系统用户所属部门!");
					return false;
				}
				if(confirm("操作将修改用户信息,确认?")){
					document.forms[0].action="User.do?method=updateUser";
					document.forms[0].submit();
				}
			}
		
		function showCreateUserOfZX(){
			document.getElementById("newUserOfZX").style.display = "inline";
		}
		
		function newUserOfZXHidden(){
			document.getElementById("newUserOfZX").style.display = "none";
		}
		
		function createUserOfZX(){
			var zxPrincipal = document.getElementsByName("zxPrincipal");
			var total = 0;
			var objarray=zxPrincipal.length;
			var principal="";
			for (i=0;i<objarray;i++){
				if(zxPrincipal[i].checked){
					principal+=zxPrincipal[i].value+",";
					var zxPrincipalValue = zxPrincipal[i].value;
					var orgCode = zxPrincipalValue.replace("Name", "Org");
					var zxPrincipalName = document.getElementById(zxPrincipalValue).value;
					var zxOrgCode = document.getElementById(orgCode).value;
					if(zxPrincipalName==""){
						alert("请输入征信系统对应用户名!");
						return false;
					}
					if(zxOrgCode==""){
						alert("请选择征信系统用户所在机构!");
						return false;
					}
					total++;
				}
			}
			if(total==0){
				alert("请输入征信系统用户信息!");
				return false;
			}
			if(total>1){
				alert("新增用户单次操作只能创建一个征信用户名!");
				return false;
			}
			if(confirm("操作将新增征信用户信息,确认?")){
					document.forms[0].action="User.do?method=createUserOfZX";
					document.forms[0].submit();
			}
		}
		
			
		function myLoad(flag,status){
			if(flag=="0"){
				document.getElementById('TR1').style.display='block';
				document.getElementById('TR2').style.display='block';
				document.getElementById('TR3').style.display='none';
				document.getElementById('TR4').style.display='none';
				document.getElementById('TR5').style.display='none';
				document.getElementById('TR6').style.display='none';
			}else{
				document.getElementById('TR1').style.display='none';
				document.getElementById('TR2').style.display='none';
				document.getElementById('TR3').style.display='block';
				document.getElementById('TR4').style.display='block';
				document.getElementById('TR5').style.display='block';
				document.getElementById('TR6').style.display='block';
			}
			if(status=="1"){
				document.getElementById('TD1').style.display='block';
				document.getElementById('TD2').style.display='none';
			}else{
				document.getElementById('TD1').style.display='none';
				document.getElementById('TD2').style.display='block';
			}
			document.getElementById('zxName1-1').style.display='none';
			document.getElementById('zxName2-1').style.display='none';
			document.getElementById('zxName3-1').style.display='none';
			document.getElementById('zxName4-1').style.display='none';
			document.getElementById('zxName5-1').style.display='none';
			document.getElementById('zxName6-1').style.display='none';
			document.getElementById('zxName7-1').style.display='none';
			document.getElementById('zxName8-1').style.display='none';
			document.getElementById('zxName9-1').style.display='none';
			document.getElementById('zxName1-2').style.display='none';
			document.getElementById('zxName2-2').style.display='none';
			document.getElementById('zxName3-2').style.display='none';
			document.getElementById('zxName4-2').style.display='none';
			document.getElementById('zxName5-2').style.display='none';
			document.getElementById('zxName6-2').style.display='none';
			document.getElementById('zxName7-2').style.display='none';
			document.getElementById('zxName8-2').style.display='none';
			document.getElementById('zxName9-2').style.display='none';
		}
	</script>
	<body onload="myLoad('<%=flag%>','<%=status%>')">
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
								用户管理
								<img src="<%=request.getContextPath()%>/images/index11.jpg"
									width="6" height="10" hspace="5" />
								修改用户
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<html:form action="/User.do?method=updateUser" method="post"
			enctype="multipart/form-data">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
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
														<td height="180" valign="top">
															<table width="100%" border="0" cellpadding="0"
																cellspacing="0" class="tableline01">
																<tr>
																	<td align="right" class="tabletext02" width="15%">
																		<font color='#FF0000'>*</font>监管系统用户姓名
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="name" size="70%"
																			styleClass="text11155"></html:text>
																		<html:hidden property="loginId" styleId="loginId" />
																		<html:hidden property="id" styleId="id" />
																		<html:hidden property="userPrincipalsNames"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		监管系统用户类型
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="userType" readonly="true"></html:text>
																	</td>
																</tr>
																<tr id="TR1">
																	<td align="right" class="tabletext02">
																		员工号
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="userCode" readonly="true"></html:text>
																	</td>
																</tr>
																<tr id="TR2">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>职务
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="post" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr id="TR3">
																	<td align="right" class="tabletext02">
																		证件类型
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="cardType" readonly="true"></html:text>
																	</td>
																</tr>
																<tr id="TR4">
																	<td align="right" class="tabletext02">
																		证件号码
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="cardId" readonly="true"></html:text>
																	</td>
																</tr>
																<tr id="TR5">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>最高学历
																	</td>
																	<td align="left" colspan="4">
																		<html:select property="education">
																			<html:option value="">---请选择---</html:option>
																			<html:option value="研究生及以上">研究生及以上</html:option>
																			<html:option value="大学本科">大学本科</html:option>
																			<html:option value="专科">专科</html:option>
																			<html:option value="高中及以下">高中及以下</html:option>
																		</html:select>
																	</td>
																</tr>
																<tr id="TR6">
																	<td align="right" class="tabletext02">
																		<font color='#FF0000'>*</font>联系电话
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="phone" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		用户使用者身份
																	</td>
																	<td align="left">
																		<html:text property="userCard" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		监管系统用户所在机构名称
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="organizationChoice"
																			styleClass="text11155" readonly="true"></html:text>
																		<input type="button" value="选择" class="botton01"
																			onclick="getOrgTree('organization')" />
																		<html:hidden property="organizationChoice"></html:hidden>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		监管系统用户所在机构代码
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="organization" size="100%"
																			readonly="true"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		组织机构代码
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="orgCodeOfZz" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		机构信用代码
																	</td>
																	<td align="left" colspan="4">
																		<html:text property="orgCodeOfXy" size="70%"
																			styleClass="text11155"></html:text>
																	</td>
																</tr>
																<tr>
																	<td class="tabletext02">
																		<font color='#FF0000'>*</font>监管系统用户所在部门
																	</td>
																	<td class="tdstylebotton" colspan="4">
																		<html:select property="dept" styleClass="text111"
																			title="部门" styleId="dept" style="width:300">
																			<html:optionsCollection name="useDeptList" />
																			<html:hidden property="dept" styleId="dept" />
																		</html:select>
																	</td>
																</tr>
																<tr>
																	<td align="right" class="tabletext02">
																		上传照片
																	</td>
																	<td align="left" colspan="4">
																		<%
																			StringBuffer sb1 = new StringBuffer("");
																				if (!"".equals(photoPath) && photoPath != null) {
																					sb1.append("<img src='\\csims\\").append(photoPath).append(
																							"'  width='160px' height='120px' /><br>");
																				} else {
																					sb1.append("无照片");
																				}
																				out.write(sb1.toString());
																		%>
																	</td>
																</tr>
																<%=zxStr%>
																<tr>
																	<td align="right" class="tabletext02">
																		监管系统用户角色
																	</td>
																	<td class="tdstylebotton" colspan="4">
																		<div id="slt">
																			<table>
																				<%
																					StringBuffer sb = new StringBuffer("");
																						List<?> allPrincipals = (List<?>) request
																								.getAttribute("allPrincipals");
																						List<?> principals = (List<?>) request
																								.getAttribute("principals");
																						for (Iterator<?> it = allPrincipals.iterator(); it.hasNext();) {
																							RolePrincipal principal = (RolePrincipal) it.next();
																							if (principal.getLocalName().trim().equalsIgnoreCase(
																									"admin")
																									|| principal.getLocalName().trim()
																											.equalsIgnoreCase("systemadmin")
																									|| principal.getLocalName().trim()
																											.equalsIgnoreCase("guest")) {
																								continue;
																							}
																							sb.append("<tr>");
																							sb.append("<td align='left' width='3%'>");
																							sb
																									.append("<input id='selectPrincipal' name='selectPrincipal' type='checkbox' value='");
																							sb.append(principal.getName());
																							sb.append("' ");
																							if (principals != null && principals.size() > 0) {
																								for (int i = 0; i < principals.size(); i++) {
																									if (principals.get(i).equals(principal.getName())) {
																										sb.append(" checked='checked' ");
																										break;
																									}
																								}
																							}
																							sb.append("/></td>");
																							sb.append("<td align='left' width='80%'>");
																							sb.append(principal.getLocalName());
																							sb.append("</td>");
																							sb.append("</tr>");
																						}
																						out.write(sb.toString());
																				%>
																			</table>
																		</div>
																	</td>
																</tr>
																<tr>
																	<td colspan="5" id="TD1" align="center">
																		<input type="button" value="完成" class="botton01"
																			onclick="return updateUser();" />
																		<input type="button" value="新增征信系统用户名" disabled
																			class="botton0001" onclick="return showCreateUserOfZX();" />
																		<input type="button" value="禁用" disabled
																			class="botton01" onclick="return disableUser();" />
																		<input type="button" value="启用" class="botton01"
																			onclick="return enableUser();" />
																		<input type="button" value="返回" class="botton01"
																			onclick="javascript:history.back()" />
																	</td>
																	<td colspan="5" id="TD2" align="center">
																		<input type="button" value="完成" class="botton01"
																			onclick="return updateUser();" />
																		<input type="button" value="新增征信系统用户名" class="botton0001"
																			onclick="return showCreateUserOfZX();" />
																		<input type="button" value="禁用" class="botton01"
																			onclick="return disableUser();" />
																		<input type="button" value="启用" disabled
																			class="botton01" onclick="return enableUser();" />
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
												<div id="newUserOfZX" style="display: none;">
													<table width="100%" border="0" cellpadding="0"
														cellspacing="0">
														<tr>
															<td>
																<table width="100%" border="0" cellspacing="0"
																	cellpadding="0">
																	<tr>
																		<td class="tiltlegerner" colspan="5">
																			新增征信用户
																		</td>
																	</tr>
																	<tr>
																		<td height="180" valign="top">
																			<table width="100%" border="0" cellpadding="0"
																				cellspacing="0" class="tableline01">
																				<tr>
																					<td align="right" class="tabletext02" width="15%">
																						征信系统用户类型
																					</td>
																					<td class="tdstylebotton" colspan="4">
																						<table>
																							<tr>
																								<td align='left' width='10%' height="25">
																									<input type="checkbox" name="zxPrincipal"
																										value="zxName1"
																										onclick='return changZXName(this);' />
																									企业征信管理员用户
																								</td>
																								<td id="zxName1-1" width='20%'>
																									<font color='#FF0000'>*</font>征信系统用户名
																									<html:text property="zxName1"
																										styleClass="text11123232323232"></html:text>
																								</td>
																								<td id="zxName1-2">
																									<font color='#FF0000'>*</font>征信系统用户所在机构
																									<html:text property="zxOrg1Choice"
																										styleClass="text111555555" readonly="true"></html:text>
																									<input type="button" value="选择"
																										class="botton01"
																										onclick="getOrgTreeOfZx1('zxOrg1')" />
																									<html:hidden property="zxOrg1Choice"></html:hidden>
																									<html:hidden property="zxOrg1"></html:hidden>
																								</td>
																							</tr>
																							<tr>
																								<td height="25">
																									<input type="checkbox" name="zxPrincipal"
																										value="zxName2"
																										onclick='return changZXName(this);' />
																									&nbsp;企业征信查询用户
																								</td>
																								<td id="zxName2-1">
																									<font color='#FF0000'>*</font>征信系统用户名
																									<html:text property="zxName2"
																										styleClass="text11123232323232"></html:text>
																								</td>
																								<td id="zxName2-2">
																									<font color='#FF0000'>*</font>征信系统用户所在机构
																									<html:text property="zxOrg2Choice"
																										styleClass="text111555555" readonly="true"></html:text>
																									<input type="button" value="选择"
																										class="botton01"
																										onclick="getOrgTreeOfZx2('zxOrg2')" />
																									<html:hidden property="zxOrg2Choice"></html:hidden>
																									<html:hidden property="zxOrg2"></html:hidden>
																								</td>
																							</tr>
																							<tr>
																								<td height="25">
																									<input type="checkbox" name="zxPrincipal"
																										value="zxName3"
																										onclick='return changZXName(this);' />
																									&nbsp;企业征信数据报送用户
																								</td>
																								<td id="zxName3-1">
																									<font color='#FF0000'>*</font>征信系统用户名
																									<html:text property="zxName3"
																										styleClass="text11123232323232"></html:text>
																								</td>
																								<td id="zxName3-2">
																									<font color='#FF0000'>*</font>征信系统用户所在机构
																									<html:text property="zxOrg3Choice"
																										styleClass="text111555555" readonly="true"></html:text>
																									<input type="button" value="选择"
																										class="botton01"
																										onclick="getOrgTreeOfZx3('zxOrg3')" />
																									<html:hidden property="zxOrg3Choice"></html:hidden>
																									<html:hidden property="zxOrg3"></html:hidden>
																								</td>
																							</tr>
																							<tr>
																								<td height="25">
																									<input type="checkbox" name="zxPrincipal"
																										value="zxName4"
																										onclick='return changZXName(this);' />
																									&nbsp;企业征信异议处理用户
																								</td>
																								<td id="zxName4-1">
																									<font color='#FF0000'>*</font>征信系统用户名
																									<html:text property="zxName4"
																										styleClass="text11123232323232"></html:text>
																								</td>
																								<td id="zxName4-2">
																									<font color='#FF0000'>*</font>征信系统用户所在机构
																									<html:text property="zxOrg4Choice"
																										styleClass="text111555555" readonly="true"></html:text>
																									<input type="button" value="选择"
																										class="botton01"
																										onclick="getOrgTreeOfZx4('zxOrg4')" />
																									<html:hidden property="zxOrg4Choice"></html:hidden>
																									<html:hidden property="zxOrg4"></html:hidden>
																								</td>
																							</tr>
																							<tr>
																								<td align='left' width='10%' height="25">
																									<input type="checkbox" name="zxPrincipal"
																										value="zxName5"
																										onclick='return changZXName(this);' />
																									&nbsp;个人征信管理员用户
																								</td>
																								<td id="zxName5-1">
																									<font color='#FF0000'>*</font>征信系统用户名
																									<html:text property="zxName5"
																										styleClass="text11123232323232"></html:text>
																								</td>
																								<td id="zxName5-2">
																									<font color='#FF0000'>*</font>征信系统用户所在机构
																									<html:text property="zxOrg5Choice"
																										styleClass="text111555555" readonly="true"></html:text>
																									<input type="button" value="选择"
																										class="botton01"
																										onclick="getOrgTreeOfZx5('zxOrg5')" />
																									<html:hidden property="zxOrg5Choice"></html:hidden>
																									<html:hidden property="zxOrg5"></html:hidden>
																								</td>
																							</tr>
																							<tr>
																								<td height="25">
																									<input type="checkbox" name="zxPrincipal"
																										value="zxName6"
																										onclick='return changZXName(this);' />
																									&nbsp;个人征信查询用户
																								</td>
																								<td id="zxName6-1">
																									<font color='#FF0000'>*</font>征信系统用户名
																									<html:text property="zxName6"
																										styleClass="text11123232323232"></html:text>
																								</td>
																								<td id="zxName6-2">
																									<font color='#FF0000'>*</font>征信系统用户所在机构
																									<html:text property="zxOrg6Choice"
																										styleClass="text111555555" readonly="true"></html:text>
																									<input type="button" value="选择"
																										class="botton01"
																										onclick="getOrgTreeOfZx6('zxOrg6')" />
																									<html:hidden property="zxOrg6Choice"></html:hidden>
																									<html:hidden property="zxOrg6"></html:hidden>
																								</td>
																							</tr>
																							<tr>
																								<td height="25">
																									<input type="checkbox" name="zxPrincipal"
																										value="zxName7"
																										onclick='return changZXName(this);' />
																									&nbsp;个人征信数据报送用户
																								</td>
																								<td id="zxName7-1">
																									<font color='#FF0000'>*</font>征信系统用户名
																									<html:text property="zxName7"
																										styleClass="text11123232323232"></html:text>
																								</td>
																								<td id="zxName7-2">
																									<font color='#FF0000'>*</font>征信系统用户所在机构
																									<html:text property="zxOrg7Choice"
																										styleClass="text111555555" readonly="true"></html:text>
																									<input type="button" value="选择"
																										class="botton01"
																										onclick="getOrgTreeOfZx7('zxOrg7')" />
																									<html:hidden property="zxOrg7Choice"></html:hidden>
																									<html:hidden property="zxOrg7"></html:hidden>
																								</td>
																							</tr>
																							<tr>
																								<td height="25">
																									<input type="checkbox" name="zxPrincipal"
																										value="zxName8"
																										onclick='return changZXName(this);' />
																									&nbsp;个人征信异议处理用户
																								</td>
																								<td id="zxName8-1">
																									<font color='#FF0000'>*</font>征信系统用户名
																									<html:text property="zxName8"
																										styleClass="text11123232323232"></html:text>
																								</td>
																								<td id="zxName8-2">
																									<font color='#FF0000'>*</font>征信系统用户所在机构
																									<html:text property="zxOrg8Choice"
																										styleClass="text111555555" readonly="true"></html:text>
																									<input type="button" value="选择"
																										class="botton01"
																										onclick="getOrgTreeOfZx8('zxOrg8')" />
																									<html:hidden property="zxOrg8Choice"></html:hidden>
																									<html:hidden property="zxOrg8"></html:hidden>
																								</td>
																							</tr>
																							<tr>
																								<td height="25">
																									<input type="checkbox" name="zxPrincipal"
																										value="zxName9"
																										onclick='return changZXName(this);' />
																									&nbsp;其他
																								</td>
																								<td id="zxName9-1">
																									<font color='#FF0000'>*</font>征信系统用户名
																									<html:text property="zxName9"
																										styleClass="text11123232323232"></html:text>
																								</td>
																								<td id="zxName9-2">
																									<font color='#FF0000'>*</font>征信系统用户所在机构
																									<html:text property="zxOrg9Choice"
																										styleClass="text111555555" readonly="true"></html:text>
																									<input type="button" value="选择"
																										class="botton01"
																										onclick="getOrgTreeOfZx9('zxOrg9')" />
																									<html:hidden property="zxOrg9Choice"></html:hidden>
																									<html:hidden property="zxOrg9"></html:hidden>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																			</table>
																		</td>
																	</tr>
																	<tr>
																		<td colspan="2" align="center">
																			<input type="button" value="保存" class="botton01"
																				onclick="createUserOfZX();" />
																			<input type="button" value="取消" class="botton01"
																				onclick="newUserOfZXHidden();" />
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