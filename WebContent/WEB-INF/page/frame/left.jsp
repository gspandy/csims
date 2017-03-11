<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="jguard" uri="/WEB-INF/tld/jguard.tld"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><bean:message key="PROJECT_NAME" /></title>

		<script src="<%=path%>/js/JS.js" language="javascript"
			type="text/javascript"></script>
		<script src="<%=path%>/js/home.js" language="javascript1.2"></script>
		<script src="<%=path%>/js/jquery.js" type="text/javascript"></script>
		<link href="<%=path%>/css/menus.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/dtree.css" rel="stylesheet" type="text/css" />
		<link href="<%=path%>/css/style.css" rel="stylesheet" type="text/css" />
		<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
		<script type="text/javascript">
function goMainPage(type,url){
    if(type=='2'){
      url = '../pages/creditMarkJsp/'+url;
	 }else if(type=='3'){
	   url = '../pages/reportStatJsp/'+url;
	 }else if(type=='4'){
	   url = '../pages/systemManage/'+url;
	 }
	 parent.parent.parent.main.location.href=url;
}
</script>

		<script type="text/javascript">
	function expandReport(blkObjName) {
			var selectDivObj = eval(blkObjName + "Child");
			var selectImgObj = eval(blkObjName + "IMG");
			if(selectDivObj.style.display=="none") {
				selectDivObj.style.display = "block";
				selectImgObj.src="<%=request.getContextPath()%>/image/report/folderopen.gif";
			}else{
			   selectDivObj.style.display = "none";
			   selectImgObj.src="<%=request.getContextPath()%>/image/report/folder.gif";
			}
		}
		
		function display(nodeid){
	    	var nodeParent = document.getElementById(nodeid);
	    	nodeParent.style.display = 'block';
		}
		
		window.onload = function(){
		    $("table[@id=odiv]").each(
		        function(){
					if($(this).html() == "<TBODY></TBODY>"){
					    $(this).parent().parent().parent().parent().parent().hide();
					}
				}
		    );
		};
		</script>
	</head>
	<body style="height: 1000">
		<table width="100%" border="0" cellspacing="0" cellpadding="0"
			bgcolor="#D9ECF7">
			<tr>
				<td bgcolor="#D9ECF7">
					<div
						style="float: left; overflow-x: visible; overflow-y: auto; height: 578px;">
						<table width="186" border="0" align="left" cellpadding="0"
							cellspacing="0" bgcolor="#D9ECF7">
							<tr>
								<td align="center" valign="top">
									<table border="0" cellpadding="0" cellspacing="0"
										class="tablejj10">
										<tr>
											<td>
												<table border="0" cellpadding="0" cellspacing="0"
													class="table01">
													<tr>
														<td align="left">
															<table border="0" cellpadding="0" cellspacing="0"
																width="100%">
																<tr>
																	<td>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table border="0" cellpadding="0" cellspacing="0"
																						width="100%">
																						<tr>
																							<td id="t1" class="title"
																								onclick="showObj(this,0)"
																								onfocusout="change(this)">
																								<bean:message
																									key="left.jsp.text.systemmanagement" />
																							</td>
																						</tr>
																					</table>
																					<table id="odiv" style="display: none" border="0"
																						cellpadding="0" cellspacing="0" width="100%">
																						<jguard:authorized uri="/Principal.do?method=list">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/Principal.do?method=list"
																										target="mainFrame" class="left"> <bean:message
																											key="left.jsp.text.principalmanagement" /> </a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized uri="/Domain.do?method=list">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/Domain.do?method=list"
																										target="mainFrame" class="left"><bean:message
																											key="left.jsp.text.permissionmanagement" />
																									</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized uri="/Log.do?method=listLog4j">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/Log.do?method=listLog4j&flag=1"
																										target="mainFrame" class="left"><bean:message
																											key="left.jsp.text.viewlog" /> </a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/AdminSanctionManagerAction.do?method=toSetSystemParam">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=toSetSystemParam"
																										target="mainFrame" class="left">系统基础参数设置</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="2"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table border="0" cellpadding="0" cellspacing="0"
																						width="100%">
																						<tr>
																							<td id="t2" class="title"
																								onclick="showObj(this,1)"
																								onfocusout="change(this)">
																								用户管理
																							</td>
																						</tr>
																					</table>
																					<table id="odiv" style="display: none" border="0"
																						cellpadding="0" cellspacing="0" width="100%">
																						<jguard:authorized uri="/User.do?method=userList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=userList"
																										target="mainFrame" class="left"><bean:message
																											key="left.jsp.text.usermanagement" /> </a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/EnforceOfficerManagerAction.do?method=toEnOfficerList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/EnforceOfficerManagerAction.do?method=toEnOfficerList"
																										target="mainFrame" class="left">执法人员管理</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=toUploadUserComInfoPage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=toUploadUserComInfoPage"
																										target="mainFrame" class="left">导入用户表彰信息</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=toUploadUserRepInfoPage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=toUploadUserRepInfoPage"
																										target="mainFrame" class="left">导入用户惩戒信息</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=toUserStopExcelPage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=toUserStopExcelPage"
																										target="mainFrame" class="left">征信系统用户批量停用</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=toUserEnbleExcelPage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=toUserEnbleExcelPage"
																										target="mainFrame" class="left">征信系统用户批量启用</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=toUserAddExcelPage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=toUserAddExcelPage"
																										target="mainFrame" class="left">征信系统用户批量新增</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=applyUserList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=applyUserList"
																										target="mainFrame" class="left">操作征信用户名回执</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=applyZxUserList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=applyZxUserList"
																										target="mainFrame" class="left">新增征信用户名申请</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=auditZxUserList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=auditZxUserList"
																										target="mainFrame" class="left">新增征信用户名任务</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=auditZxUserListDis">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=auditZxUserListDis"
																										target="mainFrame" class="left">停用征信用户名任务</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=auditZxUserListEn">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=auditZxUserListEn"
																										target="mainFrame" class="left">启用征信用户名任务</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=userReportListOfPersonal">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=userReportListOfPersonal"
																										target="mainFrame" class="left">单个用户报备查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=userReportListOfTotal">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=userReportListOfTotal"
																										target="mainFrame" class="left">汇总用户报备查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=personalInfoManagerPage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=personalInfoManagerPage"
																										target="mainFrame" class="left">本人信息</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/User.do?method=userInfoQueryPage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/User.do?method=userInfoQueryPage"
																										target="mainFrame" class="left">系统用户名查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="2"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table border="0" cellpadding="0" cellspacing="0"
																						width="100%">
																						<tr>
																							<td id="t2" class="title"
																								onclick="showObj(this,2)"
																								onfocusout="change(this)">
																								机构管理
																							</td>
																						</tr>
																					</table>
																					<table id="odiv" style="display: none" border="0"
																						cellpadding="0" cellspacing="0" width="100%">
																						<jguard:authorized
																							uri="/SystemBaseInfoManagerAction.do?method=toOrgMain">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/SystemBaseInfoManagerAction.do?method=toOrgMain"
																										class="left" target="mainFrame">机构维护</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/SystemBaseInfoManagerAction.do?method=toUploadOrgInfoPage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/SystemBaseInfoManagerAction.do?method=toUploadOrgInfoPage"
																										class="left" target="mainFrame">导入机构信息</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/SystemBaseInfoManagerAction.do?method=toUploadOrgComInfoPage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/SystemBaseInfoManagerAction.do?method=toUploadOrgComInfoPage"
																										class="left" target="mainFrame">导入机构表彰信息</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/SystemBaseInfoManagerAction.do?method=toUploadOrgRepInfoPage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/SystemBaseInfoManagerAction.do?method=toUploadOrgRepInfoPage"
																										target="mainFrame" class="left">导入机构惩戒信息</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/SystemBaseInfoManagerAction.do?method=toOrgList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/SystemBaseInfoManagerAction.do?method=toOrgList"
																										class="left" target="mainFrame">机构查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/SystemBaseInfoManagerAction.do?method=toDeptMain">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/SystemBaseInfoManagerAction.do?method=toDeptMain"
																										class="left" target="mainFrame">部门维护</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="2"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table border="0" cellpadding="0" cellspacing="0"
																						width="100%">
																						<tr>
																							<td id="t2" class="title"
																								onclick="showObj(this,3)"
																								onfocusout="change(this)">
																								行政执法管理
																							</td>
																						</tr>
																					</table>
																					<table id="odiv" style="display: none" border="0"
																						cellpadding="0" cellspacing="0" width="100%">
																						<jguard:authorized
																							uri="/AdminEnforceManagerAction.do?method=toSiftPage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=toSiftPage"
																										target="mainFrame" class="left">随机筛选</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/AdminEnforceManagerAction.do?method=toAdminEnforceInitPage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=toAdminEnforceInitPage"
																										target="mainFrame" class="left">行政执法立项登记</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/AdminEnforceManagerAction.do?method=createAeinspection">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=toAdminEnforceList&source=2"
																										target="mainFrame" class="left">检查工作记录登记</a>
																								</td>
																							</tr>
																						</jguard:authorized>

																						<jguard:authorized
																							uri="/AdminEnforceManagerAction.do?method=toAeinspListForCreateConcl">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=toAeinspListForCreateConcl"
																										target="mainFrame" class="left">检查结论登记</a>
																								</td>
																							</tr>
																						</jguard:authorized>

																						<jguard:authorized
																							uri="/AdminEnforceManagerAction.do?method=toAeinspListForCreateRecti">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=toAeinspListForCreateRecti"
																										target="mainFrame" class="left">整改信息登记</a>
																								</td>
																							</tr>
																						</jguard:authorized>

																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="2"></td>
																			</tr>
																		</table>
																	</td>
																</tr>

																<tr>
																	<td>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table border="0" cellpadding="0" cellspacing="0"
																						width="100%">
																						<tr>
																							<td id="t2" class="title"
																								onclick="showObj(this,4)"
																								onfocusout="change(this)">
																								执法信息查询
																							</td>
																						</tr>
																					</table>
																					<table id="odiv" style="display: none" border="0"
																						cellpadding="0" cellspacing="0" width="100%">
																						<jguard:authorized
																							uri="/AdminEnforceManagerAction.do?method=toAdminEnforceList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=toAdminEnforceList&source=1"
																										target="mainFrame" class="left">行政执法立项查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/AdminEnforceManagerAction.do?method=toAeinspectionList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=toAeinspectionList"
																										target="mainFrame" class="left">检查工作记录查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/AdminEnforceManagerAction.do?method=toAeconclusionList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=toAeconclusionList"
																										target="mainFrame" class="left">检查结论查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/AdminEnforceManagerAction.do?method=toAerectificationList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=toAerectificationList"
																										target="mainFrame" class="left">整改信息查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/AdminSanctionManagerAction.do?method=toPageAeconclusionInfo">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=toPageAeconclusionInfo"
																										target="mainFrame" class="left">行政执法相关信息</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="2"></td>
																			</tr>
																		</table>
																	</td>
																</tr>

																<tr>
																	<td>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table border="0" cellpadding="0" cellspacing="0"
																						width="100%">
																						<tr>
																							<td id="t2" class="title"
																								onclick="showObj(this,5)"
																								onfocusout="change(this)">
																								行政处罚管理
																							</td>
																						</tr>
																					</table>
																					<table id="odiv" style="display: none" border="0"
																						cellpadding="0" cellspacing="0" width="100%">
																						<jguard:authorized
																							uri="/AdminSanctionManagerAction.do?method=createAeSanction">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=toAeconclusionList&source=punish"
																										target="mainFrame" class="left">行政处罚立项登记</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/AdminSanctionManagerAction.do?method=toAeSanctionList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=toAeSanctionList"
																										target="mainFrame" class="left">行政处罚立项查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/AdminSanctionManagerAction.do?method=toAeSanctionConList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminSanctionManagerAction.do?method=toAeSanctionConList"
																										target="mainFrame" class="left">行政处罚结论查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="2"></td>
																			</tr>
																		</table>
																	</td>
																</tr>

																<tr>
																	<td>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table border="0" cellpadding="0" cellspacing="0"
																						width="100%">
																						<tr>
																							<td id="t2" class="title"
																								onclick="showObj(this,6)"
																								onfocusout="change(this)">
																								执法信息导入
																							</td>
																						</tr>
																					</table>
																					<table id="odiv" style="display: none" border="0"
																						cellpadding="0" cellspacing="0" width="100%">
																						<jguard:authorized
																							uri="/AdminEnforceManagerAction.do?method=toAdminEnforceExcel">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=toAdminEnforceExcel"
																										target="mainFrame" class="left">行政执法立项导入</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/AdminEnforceManagerAction.do?method=toAEInspectionAnlExcel">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=toAEInspectionAnlExcel"
																										target="mainFrame" class="left">行政执法检查情况导入</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="2"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table border="0" cellpadding="0" cellspacing="0"
																						width="100%">
																						<tr>
																							<td id="t2" class="title"
																								onclick="showObj(this,7)"
																								onfocusout="change(this)">
																								执法信息统计
																							</td>
																						</tr>
																					</table>
																					<table id="odiv" style="display: none" border="0"
																						cellpadding="0" cellspacing="0" width="100%">
																						<jguard:authorized
																							uri="/AdminEnforceManagerAction.do?method=toAeinspectionAnl">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/AdminEnforceManagerAction.do?method=toAeinspectionAnl"
																										target="mainFrame" class="left">行政执法检查情况统计</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/StatisticsAction.do?method=init">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/StatisticsAction.do?method=init"
																										target="mainFrame" class="left">辖内问题概况统计</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/StatisticsAction.do?method=initFeedback">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/StatisticsAction.do?method=initFeedback"
																										target="mainFrame" class="left">反馈情况统计</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="2"></td>
																			</tr>
																		</table>
																	</td>
																</tr>

																<tr>
																	<td>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table border="0" cellpadding="0" cellspacing="0"
																						width="100%">
																						<tr>
																							<td id="t2" class="title"
																								onclick="showObj(this,8)"
																								onfocusout="change(this)">
																								监督管理
																							</td>
																						</tr>
																					</table>
																					<table id="odiv" style="display: none" border="0"
																						cellpadding="0" cellspacing="0" width="100%">
																						<jguard:authorized
																							uri="/DailyMgrAction.do?method=toWorkDeployList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/DailyMgrAction.do?method=toWorkDeployList"
																										target="mainFrame" class="left">工作动态管理</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/DailyMgrAction.do?method=toTrainingList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/DailyMgrAction.do?method=toTrainingList"
																										target="mainFrame" class="left">培训信息管理</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/DailyMgrAction.do?method=toWorkcheckList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/DailyMgrAction.do?method=toWorkcheckList"
																										target="mainFrame" class="left">工作检查管理</a>
																								</td>
																							</tr>
																						</jguard:authorized>

																						<jguard:authorized
																							uri="/DailyMgrAction.do?method=toOffsitecheckList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/DailyMgrAction.do?method=toOffsitecheckList"
																										target="mainFrame" class="left">非现场检查管理</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/DailyMgrAction.do?method=toBusievalList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/DailyMgrAction.do?method=toBusievalList"
																										target="mainFrame" class="left">业务评价评分管理</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/DailyMgrAction.do?method=toEvalInfoList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/DailyMgrAction.do?method=toEvalInfoList"
																										target="mainFrame" class="left">业务评价信息管理</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/DailyMgrAction.do?method=toEvalRuleList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/DailyMgrAction.do?method=toEvalRuleList"
																										target="mainFrame" class="left">业务评价细则管理</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/DailyMgrAction.do?method=toEvalProportionList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/DailyMgrAction.do?method=toEvalProportionList"
																										target="mainFrame" class="left">业务评价占比管理</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/DailyMgrAction.do?method=toEvalBaseScoreList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/DailyMgrAction.do?method=toEvalBaseScoreList"
																										target="mainFrame" class="left">业务评价基本分管理</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/DailyMgrAction.do?method=toEvalMarkingList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/DailyMgrAction.do?method=toEvalMarkingList"
																										target="mainFrame" class="left">业务评价中支评分</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/DailyMgrAction.do?method=toBusievalStaPage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/DailyMgrAction.do?method=toBusievalStaPage"
																										target="mainFrame" class="left">业务评价统计</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/DailyMgrAction.do?method=toBusievalStaPage1">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/DailyMgrAction.do?method=toBusievalStaPage1"
																										target="mainFrame" class="left">金融机构排名</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="2"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table border="0" cellpadding="0" cellspacing="0"
																						width="100%">
																						<tr>
																							<td id="t2" class="title"
																								onclick="showObj(this,9)"
																								onfocusout="change(this)">
																								征信市场管理
																							</td>
																						</tr>
																					</table>
																					<table id="odiv" style="display: none" border="0"
																						cellpadding="0" cellspacing="0" width="100%">
																						<jguard:authorized
																							uri="/ZxMarketMgrAction.do?method=toZxOrgIntegrityDocumentInit">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/ZxMarketMgrAction.do?method=toZxOrgIntegrityDocumentInit"
																										target="mainFrame" class="left">机构诚信档案录入</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/ZxMarketMgrAction.do?method=toZxPersonalIntegrityDocumentInit">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/ZxMarketMgrAction.do?method=toZxPersonalIntegrityDocumentInit"
																										target="mainFrame" class="left">人员诚信档案录入</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/ZxMarketMgrAction.do?method=toZxOrgIntegrityDocumentListOfPJ">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/ZxMarketMgrAction.do?method=toZxOrgIntegrityDocumentListOfPJ"
																										target="mainFrame" class="left">评级机构诚信档案查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/ZxMarketMgrAction.do?method=toZxOrgIntegrityDocumentListOfZX">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/ZxMarketMgrAction.do?method=toZxOrgIntegrityDocumentListOfZX"
																										target="mainFrame" class="left">征信机构诚信档案查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/ZxMarketMgrAction.do?method=toZxPersonalIntegrityDocumentListOfPJ">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/ZxMarketMgrAction.do?method=toZxPersonalIntegrityDocumentListOfPJ"
																										target="mainFrame" class="left">评级人员诚信档案查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/ZxMarketMgrAction.do?method=toZxPersonalIntegrityDocumentListOfZX">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/ZxMarketMgrAction.do?method=toZxPersonalIntegrityDocumentListOfZX"
																										target="mainFrame" class="left">征信人员诚信档案查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="2"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table border="0" cellpadding="0" cellspacing="0"
																						width="100%">
																						<tr>
																							<td id="t2" class="title"
																								onclick="showObj(this,10)"
																								onfocusout="change(this)">
																								文档资料管理
																							</td>
																						</tr>
																					</table>
																					<table id="odiv" style="display: none" border="0"
																						cellpadding="0" cellspacing="0" width="100%">
																						<jguard:authorized
																							uri="/SystemBaseInfoManagerAction.do?method=getDatabakList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/SystemBaseInfoManagerAction.do?method=getDatabakList"
																										class="left" target="mainFrame">备份数据下载</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/FileHandlerManagerAction.do?method=toUploadFilePage">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/FileHandlerManagerAction.do?method=toUploadFilePage"
																										class="left" target="mainFrame">文档资料上传</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/FileHandlerManagerAction.do?method=toFileList&attType=1">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/FileHandlerManagerAction.do?method=toFileList&attType=1"
																										target="mainFrame" class="left">管理规定下载</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/FileHandlerManagerAction.do?method=toFileList&attType=2">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/FileHandlerManagerAction.do?method=toFileList&attType=2"
																										target="mainFrame" class="left">导入模板下载</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/FileHandlerManagerAction.do?method=toFileList&attType=3">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/FileHandlerManagerAction.do?method=toFileList&attType=3"
																										target="mainFrame" class="left">培训资料下载</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/FileHandlerManagerAction.do?method=toFileList&attType=4">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/FileHandlerManagerAction.do?method=toFileList&attType=4"
																										target="mainFrame" class="left">其他下载</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="2"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																<tr>
																	<td>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table border="0" cellpadding="0" cellspacing="0"
																						width="100%">
																						<tr>
																							<td id="t2" class="title"
																								onclick="showObj(this,11)"
																								onfocusout="change(this)">
																								问卷调查
																							</td>
																						</tr>
																					</table>
																					<table id="odiv" style="display: none" border="0"
																						cellpadding="0" cellspacing="0" width="100%">
																						<jguard:authorized
																							uri="/QuestionAction.do?method=toQuestionairesList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/QuestionAction.do?method=toQuestionairesList"
																										class="left" target="mainFrame">问卷调查管理</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/QuestionAction.do?method=toAnswerQuestionairesList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/QuestionAction.do?method=toAnswerQuestionairesList"
																										class="left" target="mainFrame">我的问卷调查</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/QuestionAction.do?method=toAnswerResultList">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/QuestionAction.do?method=toAnswerResultList"
																										class="left" target="mainFrame">问卷结果查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="2"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																
																<tr>
																	<td>
																		<table width="100%" border="0" cellspacing="0"
																			cellpadding="0">
																			<tr>
																				<td>
																					<table border="0" cellpadding="0" cellspacing="0"
																						width="100%">
																						<tr>
																							<td id="t2" class="title"
																								onclick="showObj(this,12)"
																								onfocusout="change(this)">
																								考试系统
																							</td>
																						</tr>
																					</table>
																					<table id="odiv" style="display: none" border="0"
																						cellpadding="0" cellspacing="0" width="100%">
																						<jguard:authorized uri="/toExam.do?method=toExam">
																							<tr class="h30">
																								<td>
																									<a
																										href="http://<bean:message key="exam.host"/>:<bean:message key="exam.port"/>/MiniExam/login"
																										target="_blank" class="left">考试系统管理</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/ExamAction.do?method=toImportExamScoreExcel">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/ExamAction.do?method=toImportExamScoreExcel"
																										target="mainFrame" class="left">考试系统成绩导入</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																						<jguard:authorized
																							uri="/ExamAction.do?method=toPageExamScoreInfo">
																							<tr class="h30">
																								<td>
																									<a
																										href="<%=request.getContextPath()%>/ExamAction.do?method=toPageExamScoreInfo"
																										target="mainFrame" class="left">考试成绩查询</a>
																								</td>
																							</tr>
																						</jguard:authorized>
																					</table>
																				</td>
																			</tr>
																			<tr>
																				<td height="2"></td>
																			</tr>
																		</table>
																	</td>
																</tr>
																
															</table>
														</td>
													</tr>
												</table>
											</td>
										</tr>
										<tr>
											<td align="center">
												<img src="images/zhenxin21.jpg" width="165" height="15" />
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
	</body>
</html>
