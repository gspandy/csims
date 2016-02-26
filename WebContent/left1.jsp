<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="bean" uri="/WEB-INF/tld/struts-bean.tld"%>
<%@ taglib prefix="jguard" uri="/WEB-INF/tld/jguard.tld"%>
<%@ page import="java.util.Date,java.text.SimpleDateFormat"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title><bean:message key="CUSTOM_NAME" />-<bean:message
		key="PROJECT_NAME" />
</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
<script src="js/manu.js" language="javascript1.2"></script>
<style type="text/css">
<!--
body {
	background-color: #F0F8FB;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
-->
</style>
<link href="css/home.css" rel="stylesheet" type="text/css" />
<%
    //Calendar c = Calendar.getInstance();
    //String year = String.valueOf(c.get(Calendar.YEAR));
    //String month = String.valueOf(c.get(Calendar.MONTH) + 1);
    //String day = String.valueOf(c.get(Calendar.DATE));
    //String hour = String.valueOf(c.get(Calendar.HOUR_OF_DAY));
    //String minute = String.valueOf(c.get(Calendar.MINUTE));
    //DecimalFormat df = new DecimalFormat("0#");
    //String hour = df.format(Calendar.HOUR_OF_DAY);
    //String minute = df.format(c.get(Calendar.MINUTE));
    Date now = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy年MM月dd日");
    SimpleDateFormat timeFormat = new SimpleDateFormat(
            "HH:mm");
    String dateStr = dateFormat.format(now);
    String timeStr = timeFormat.format(now);
%>
</head>
<body>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td><table width="100%" border="0" cellspacing="0"
					cellpadding="0">
					<tr>
						<td width="192" valign="top"><table width="100%" border="0"
								cellspacing="0" cellpadding="0">
								<tr>
									<td align="center" valign="top"><table width="100%"
											border="0" cellpadding="0" cellspacing="0" bgcolor="#DAECF8">
											<tr>
												<td><table width="100%" border="0" cellspacing="0"
														cellpadding="0">
														<tr>
															<td class="tdstyle"><table width="167" border="0"
																	align="center" cellpadding="0" cellspacing="0">
																	<tr>
																		<td width="8"><img src="images/viewleft.jpg"
																			width="8" height="79" />
																		</td>
																		<td class="bgview"><table width="100%" border="0"
																				cellspacing="0" cellpadding="0">
																				<tr>
																					<td><table width="100%" border="0"
																							cellspacing="0" cellpadding="0">
																							<tr>
																								<td></td>
																								<td class="titilew" align="center" colspan="2">
																									<%= dateStr %>
																								</td>
																							</tr>
																						</table>
																					</td>
																				</tr>
																				<tr>
																					<td class="viewtext"><%= timeStr %></td>
																				</tr>
																			</table>
																		</td>
																		<td width="8"><img src="images/view02.jpg"
																			width="8" height="79" />
																		</td>
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
							</table>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
