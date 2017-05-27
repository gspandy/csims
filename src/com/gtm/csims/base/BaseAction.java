package com.gtm.csims.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.security.Permission;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;

import net.sf.jguard.core.CoreConstants;
import net.sf.jguard.core.authentication.credentials.JGuardCredential;
import net.sf.jguard.core.authorization.permissions.PermissionUtils;
import net.sf.jguard.core.authorization.permissions.URLPermission;
import net.sf.jguard.core.principals.RolePrincipal;
import net.sf.jguard.ext.SecurityConstants;
import net.sf.jguard.ext.principals.PrincipalUtils;
import net.sf.jguard.jee.authentication.http.HttpAuthenticationUtils;
import net.sf.jguard.jee.authentication.http.HttpConstants;
import net.sf.jguard.jee.authorization.http.HttpAccessControllerUtils;

import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.actions.ActionDispatcher;
import org.apache.struts.util.MessageResources;

import com.gtm.csims.dao.BsDictionaryDAO;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.model.BsDictionary;

/**
 * 所有Action基类
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("unchecked")
public class BaseAction extends Action {
	/**
     * 
     */
	private static final Logger LOGGER = Logger.getLogger(BaseAction.class);

	/**
     * 
     */
	protected static final String ALL = "ALL";
	protected static final String ANY = "ANY";
	protected static final String NONE = "NONE";
	/**
	 * 用于在错误提示页面显示信息的属性名
	 */
	protected static final String ATTR_MESSAGE = "message";
	/**
	 * 用于在错误提示页面提示js对话框信息的标示
	 */
	protected static final String ATTR_ERROR = "error";
	/**
	 * 列表页面表格默认每页显示记录数
	 */
	protected static final Integer PAGE_CONTAIN_NUMBER = 10;
	protected static Map<String, String> ZX_PRI_NAME_MAP = new HashMap<String, String>();
	protected static Map<String, String> AREA_NAME_MAP = new HashMap<String, String>();
	protected static Map<String, String> AREA_COL_NAME_MAP = new HashMap<String, String>();
	protected static Map<String, String> ORG_TYPE_MAP = new HashMap<String, String>();
	protected static final String ERROR1 = "错误原因：当前用户不属于人民银行用户，不能使用该功能";
	protected static final String ERROR2 = "错误原因：请不要通过点击浏览器后退按钮进行页面提交\\r解决办法：请通过点击菜单【%s】进行该操作";
	protected static final String ERROR3 = "错误原因：不能获取当前登录用户【%s】的所属机构信息";
	protected static final String ERROR4 = "错误原因：当前没有可以下载的附件！%s";
	protected static final String ERROR5 = "错误原因：该%s的创建人为空，请联系管理员";
	protected static final String ERROR6 = "错误原因：不能正确获得当前登录人信息，请联系管理员";
	protected static final String ERROR7 = "错误原因：该%s不是当前登录用户创建，只能对自己创建的信息进行修改";
	protected static final String ERROR8 = "错误原因：不能正确获得id为%s的%s信息，请联系管理员";
	protected static final String ERROR9 = "错误原因：当前%s的%s信息录入流程已经结束，不能修改";
	protected static final String LOG_FORMAT = "【%1$s】机构的【%2$s】用户进行【%3$s】操作，【%4$s】";
	protected ActionDispatcher dispatcher = new ActionDispatcher(this, ActionDispatcher.DEFAULT_FLAVOR);

	static {
		ZX_PRI_NAME_MAP.put("zxName1", "企业征信管理员用户");
		ZX_PRI_NAME_MAP.put("zxName2", "企业征信查询用户");
		ZX_PRI_NAME_MAP.put("zxName3", "企业征信数据报送用户");
		ZX_PRI_NAME_MAP.put("zxName4", "企业征信异议处理用户");
		ZX_PRI_NAME_MAP.put("zxName5", "个人征信管理员用户");
		ZX_PRI_NAME_MAP.put("zxName6", "个人征信查询用户");
		ZX_PRI_NAME_MAP.put("zxName7", "个人征信数据报送用户");
		ZX_PRI_NAME_MAP.put("zxName8", "个人征信异议处理用户");
		ZX_PRI_NAME_MAP.put("zxName9", "其他");

		AREA_NAME_MAP.put("A1000151000028", "省级机构");
		AREA_NAME_MAP.put("A1000151000410", "营管部");
		AREA_NAME_MAP.put("A1000151000131", "绵阳");
		AREA_NAME_MAP.put("A1000151000079", "德阳");
		AREA_NAME_MAP.put("A1000151000257", "雅安");
		AREA_NAME_MAP.put("A1000151000182", "内江");
		AREA_NAME_MAP.put("A1000151000244", "乐山");
		AREA_NAME_MAP.put("A1000151000129", "眉山");
		AREA_NAME_MAP.put("A1000151000713", "自贡");
		AREA_NAME_MAP.put("A1000151000030", "巴中");
		AREA_NAME_MAP.put("A1000151000081", "甘孜");
		AREA_NAME_MAP.put("A1000151000701", "资阳");
		AREA_NAME_MAP.put("A1000151000042", "凉山");
		AREA_NAME_MAP.put("A1000151000055", "阿坝");
		AREA_NAME_MAP.put("A1000151000117", "泸州");
		AREA_NAME_MAP.put("A1000151000220", "南充");
		AREA_NAME_MAP.put("A1000151000105", "广元");
		AREA_NAME_MAP.put("A1000151000093", "广安");
		AREA_NAME_MAP.put("A1000151000067", "达州");
		AREA_NAME_MAP.put("A1000151000269", "遂宁");
		AREA_NAME_MAP.put("A1000151000699", "宜宾");
		AREA_NAME_MAP.put("A1000151000232", "攀枝花");

		AREA_COL_NAME_MAP.put("省级机构", "T1");
		AREA_COL_NAME_MAP.put("营管部", "T2");
		AREA_COL_NAME_MAP.put("绵阳", "T3");
		AREA_COL_NAME_MAP.put("德阳", "T4");
		AREA_COL_NAME_MAP.put("雅安", "T5");
		AREA_COL_NAME_MAP.put("内江", "T6");
		AREA_COL_NAME_MAP.put("乐山", "T7");
		AREA_COL_NAME_MAP.put("眉山", "T8");
		AREA_COL_NAME_MAP.put("自贡", "T9");
		AREA_COL_NAME_MAP.put("巴中", "T10");
		AREA_COL_NAME_MAP.put("甘孜", "T11");
		AREA_COL_NAME_MAP.put("资阳", "T12");
		AREA_COL_NAME_MAP.put("凉山", "T13");
		AREA_COL_NAME_MAP.put("阿坝", "T14");
		AREA_COL_NAME_MAP.put("泸州", "T15");
		AREA_COL_NAME_MAP.put("南充", "T16");
		AREA_COL_NAME_MAP.put("广元", "T17");
		AREA_COL_NAME_MAP.put("广安", "T18");
		AREA_COL_NAME_MAP.put("达州", "T19");
		AREA_COL_NAME_MAP.put("遂宁", "T20");
		AREA_COL_NAME_MAP.put("宜宾", "T21");
		AREA_COL_NAME_MAP.put("攀枝花", "T22");

		ORG_TYPE_MAP.put("0", "国家开发银行股份有限公司四川省分行");
		ORG_TYPE_MAP.put("1", "中国进出口银行成都分行");
		ORG_TYPE_MAP.put("2", "中国农业发展银行四川省分行");
		ORG_TYPE_MAP.put("3", "中国工商银行股份有限公司四川省分行");
		ORG_TYPE_MAP.put("4", "中国农业银行股份有限公司四川省分行");
		ORG_TYPE_MAP.put("5", "中国银行股份有限公司四川省分行");
		ORG_TYPE_MAP.put("6", "中国建设银行股份有限公司四川省分行");
		ORG_TYPE_MAP.put("7", "交通银行股份有限公司四川省分行");
		ORG_TYPE_MAP.put("8", "中国邮政储蓄银行股份有限公司四川省分行");
		ORG_TYPE_MAP.put("9", "四川省农村信用联社");
		ORG_TYPE_MAP.put("10", "中信银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("11", "中国光大银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("12", "华夏银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("13", "平安银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("14", "招商银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("15", "上海浦东发展银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("16", "兴业银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("17", "中国民生银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("18", "恒丰银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("19", "浙商银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("20", "渤海银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("21", "上海银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("22", "重庆银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("23", "浙江民泰商业银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("24", "哈尔滨银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("25", "大连银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("26", "包商银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("27", "贵阳银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("28", "天津银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("29", "广发银行股份有限公司成都分行");
		ORG_TYPE_MAP.put("30", "花旗银行（中国）有限公司成都分行");
		ORG_TYPE_MAP.put("31", "渣打银行（中国）有限公司成都分行");
		ORG_TYPE_MAP.put("32", "汇丰银行（中国）有限公司成都分行");
		ORG_TYPE_MAP.put("33", "东亚银行（中国）有限公司成都分行");
		ORG_TYPE_MAP.put("34", "华侨银行（中国）有限公司成都分行");
		ORG_TYPE_MAP.put("35", "苏格兰皇家银行（中国）有限公司成都分行");
		ORG_TYPE_MAP.put("36", "大华银行（中国）有限公司成都分行");
		ORG_TYPE_MAP.put("37", "南洋商业银行（中国）有限公司成都分行");
		ORG_TYPE_MAP.put("38", "摩根大通银行（中国）有限公司成都分行");
		ORG_TYPE_MAP.put("39", "三菱东京日联银行（中国）有限公司成都分行");
		ORG_TYPE_MAP.put("40", "泰国开泰银行（大众）有限公司成都分行");
		ORG_TYPE_MAP.put("41", "友利银行（中国）有限公司成都分行");
		ORG_TYPE_MAP.put("42", "中铁信托有限责任公司");
		ORG_TYPE_MAP.put("43", "四川信托有限公司");
		ORG_TYPE_MAP.put("44", "新希望财务有限公司");
		ORG_TYPE_MAP.put("45", "东方电气集团财务有限公司");
		ORG_TYPE_MAP.put("46", "攀钢集团财务有限公司");
		ORG_TYPE_MAP.put("47", "中国石化财务有限责任公司成都分公司");
		ORG_TYPE_MAP.put("48", "中国长城资产管理公司成都办事处");
		ORG_TYPE_MAP.put("49", "中国信达资产管理股份有限公司四川省分公司");
		ORG_TYPE_MAP.put("50", "中国东方资产管理公司成都办事处");
	}
	private BsDictionaryDAO bsDictionaryDao;

	public void setBsDictionaryDao(BsDictionaryDAO bsDictionaryDao) {
		this.bsDictionaryDao = bsDictionaryDao;
	}

	/**
	 * 根据参数类型获取系统基础参数对象
	 * 
	 * @param type
	 * @return
	 */
	public List<BsDictionary> getDicMap(String type) {
		List<BsDictionary> list = bsDictionaryDao.find(
		        "from BsDictionary where type = ? and isenable = 'true' order by id", new Object[] { type });
		return list;
	}

	/**
	 * 获取所有系统基础参数
	 * 
	 * @return
	 */
	public Map<String, String> getDicMap() {
		List<BsDictionary> list = bsDictionaryDao.find("from BsDictionary where isenable = 'true' order by createDate",
		        new Object[] {});
		Map<String, String> map = new HashMap<String, String>();
		for (Iterator<BsDictionary> iterator = list.iterator(); iterator.hasNext();) {
			BsDictionary object = iterator.next();
			map.put(object.getDkey(), object.getDvalue());
		}
		return map;
	}

	/**
	 * 判断当前用户是否为人行用户
	 * 
	 * @param request
	 * @param response
	 */
	protected Boolean isPcbUser(HttpServletRequest request, HttpServletResponse response) {
		String ispcbuser = this.getPubCredential(UserCredentialName.ispcbuser.name(), request, response);
		if (ispcbuser == null) {
			return false;
		}
		if (ispcbuser.trim().equalsIgnoreCase("YES")) {
			return true;
		}
		if (ispcbuser.trim().equalsIgnoreCase("NO")) {
			return false;
		}
		return false;
	}

	public static String getZXPriName(String str) {
		return ZX_PRI_NAME_MAP.get(str);
	}

	// public static String getZxUserName(String str) {
	// Map<String, String> ZxUserNameInfo = new HashMap<String, String>();
	// ZxUserNameInfo.put("企业征信异议处理用户", "and ZxUserType='企业征信数据报送用户'");
	// ZxUserNameInfo.put("个人征信异议处理用户", "and ZxUserType='个人征信数据报送用户'");
	// ZxUserNameInfo.put("企业征信管理员用户",
	// "and (ZxUserType='企业征信查询用户' or ZxUserType='企业征信数据报送用户')");
	// ZxUserNameInfo.put("个人征信管理员用户",
	// "and (ZxUserType='个人征信查询用户' or ZxUserType='个人征信数据报送用户')");
	// ZxUserNameInfo.put("企业征信查询用户",
	// "and (ZxUserType='企业征信管理员用户' or ZxUserType='企业征信数据报送用户')");
	// ZxUserNameInfo.put("个人征信查询用户",
	// "and (ZxUserType='个人征信管理员用户' or ZxUserType='个人征信数据报送用户')");
	// ZxUserNameInfo.put("企业征信数据报送用户",
	// "and (ZxUserType='企业征信查询用户' or ZxUserType='企业征信数据报送用户' or
	// ZxUserType='企业征信管理员用户')");
	// ZxUserNameInfo.put("个人征信数据报送用户",
	// "and (ZxUserType='个人征信查询用户' or ZxUserType='个人征信数据报送用户' or
	// ZxUserType='个人征信管理员用户')");
	// return ZxUserNameInfo.get(str);
	// }

	public static String getAreaName(String str) {
		return AREA_NAME_MAP.get(str);
	}

	public static String getAreaColName(String str) {
		return AREA_COL_NAME_MAP.get(str);
	}

	public static String getOrgTypeMap(String orgType) {
		return ORG_TYPE_MAP.get(orgType);
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		return dispatcher.execute(mapping, form, request, response);
	}

	/**
	 * initialize some stuff (the Spring framework webAppplicationContext or
	 * another one).
	 * 
	 * @param actionServlets
	 */
	public void setServlet(ActionServlet actionServlet) {
		super.setServlet(actionServlet);
		// ServletContext servletContext = actionServlet.getServletContext();
		// WebApplicationContext wac =
		// WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
		// this.facade = (Facade of your business layer) wac.getBean(facade bean
		// id in your Spring config file);
	}

	/**
	 * Print message only through the java script alert()
	 * 
	 * @param request
	 * @param response
	 * @param msg
	 * @param url
	 */
	public void printMessageOnlyJs(HttpServletRequest request, HttpServletResponse response, String msg, String url) {
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();

			StringBuffer buf = new StringBuffer();
			buf.append("<script>");
			buf.append("alert('" + msg + "');");
			if ("error".equals(url)) {
				buf.append("window.history.go(-1);");
			} else if ("error2".equals(url)) {
				buf.append("window.history.go(-2);");
			} else if ("error0".equals(url)) {
				buf.append("window.history.go(0);");
			} else if (!"back".equals(url)) {
				buf.append("if(window.name=='content'){\n");
				buf.append("if(history.length)window.parent.location.replace('" + url + "');\n");
				buf.append("else\n");
				buf.append("window.parent.location='" + url + "';");
				buf.append("}");
				buf.append("else{\n");
				buf.append("if(history.length)location.replace('" + url + "');\n");
				buf.append("else\n");
				buf.append("window.location='" + url + "';\n");
				buf.append("}");
			}
			buf.append("</script>");
			out.println(buf.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Print message through the java script alert()
	 * 
	 * with page background css
	 * 
	 * @param request
	 * @param response
	 * @param msg
	 * @param url
	 */
	public void printMessage(HttpServletRequest request, HttpServletResponse response, String msg, String url) {
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			MessageResources messages = (MessageResources) getResources(request);
			String projectName = messages.getMessage("PROJECT_NAME");
			StringBuffer buf = new StringBuffer();
			buf.append("<html><head><link href=\"").append(request.getContextPath()).append("/css/css.css\"")
			        .append(" rel=\"stylesheet\" type=\"text/css\" />");
			buf.append("<style type=\"text/css\">");
			buf.append("body {background-color: #F0F8FB;margin-left: 0px;margin-top: 0px;margin-right: 0px;margin-bottom: 0px;}");
			buf.append("A {COLOR: #0033FF; font face=Verdana;font-size: 9pt}");
			buf.append("</style>");
			buf.append("</head><body><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">")
			        .append("<tr>").append("<td height=\"35\" class=\"nawzjj\">")
			        .append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">").append("<tr>")
			        .append("<td class=\"ndwz\">").append("&nbsp;").append("</td>").append("<td class=\"ndwzzml\">")
			        .append(projectName).append("<img src=\"").append(request.getContextPath())
			        .append("/images/index11.jpg\" ").append(" width=\"6\" height=\"10\" hspace=\"5\" />")
			        .append("系统错误提示").append("</td>").append("</tr>").append("</table>").append("</td>")
			        .append("</tr>").append("</table>").append("</body></html>");
			buf.append("<script>");
			buf.append("alert('" + msg + "');");
			if ("error".equals(url)) {
				buf.append("window.history.go(-1);");
			} else if ("error2".equals(url)) {
				buf.append("window.history.go(-2);");
			} else if ("error0".equals(url)) {
				buf.append("window.history.go(0);");
			} else if (!"back".equals(url)) {
				buf.append("if(window.name=='content'){\n");
				buf.append("if(history.length)window.parent.location.replace('" + url + "');\n");
				buf.append("else\n");
				buf.append("window.parent.location='" + url + "';");
				buf.append("}");
				buf.append("else{\n");
				buf.append("if(history.length)location.replace('" + url + "');\n");
				buf.append("else\n");
				buf.append("window.location='" + url + "';\n");
				buf.append("}");
			}
			buf.append("</script>");
			out.println(buf.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printMessageTemp(HttpServletRequest request, HttpServletResponse response, String msg, String url) {
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			StringBuffer buf = new StringBuffer();
			buf.append("<script>");
			buf.append("alert('" + msg + "');");
			if ("error".equals(url)) {
				buf.append("window.history.go(-1);");
			} else if ("error2".equals(url)) {
				buf.append("window.history.go(-1);");

			} else if ("error0".equals(url)) {
				buf.append("window.history.go(0);");
			} else if (!"back".equals(url)) {
				buf.append("if(window.name=='content'){\n");
				buf.append("if(history.length)window.parent.location.replace('" + url + "');\n");
				buf.append("else\n");
				buf.append("window.parent.location='" + url + "';");
				buf.append("}");
				buf.append("else{\n");
				buf.append("if(history.length)location.replace('" + url + "');\n");
				buf.append("else\n");
				buf.append("window.location='" + url + "';\n");
				buf.append("}");
			}
			buf.append("window.location.reload();");
			buf.append("</script>");
			out.println(buf.toString());
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printMessageRe(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		StringBuffer buf = new StringBuffer();
		buf.append("<script>");
		if ("error".equals(url)) {
			buf.append("window.history.go(-1);");
		} else if (!"back".equals(url)) {
			buf.append("if(window.name=='content'){\n");
			buf.append("if(history.length)window.parent.location.replace('" + url + "');\n");
			buf.append("else\n");
			buf.append("window.parent.location='" + url + "';");
			buf.append("}");
			buf.append("else{\n");
			buf.append("if(history.length)location.replace('" + url + "');\n");
			buf.append("else\n");
			buf.append("window.location='" + url + "';\n");
			buf.append("}");
		}
		buf.append("</script>");
		out.println(buf.toString());
		out.flush();
		out.close();
	}

	public void printMessageToParent(HttpServletRequest request, HttpServletResponse response, String msg, String url)
	        throws IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		StringBuffer buf = new StringBuffer();
		buf.append("<script>");
		buf.append("alert('" + msg + "');");
		if ("error".equals(url)) {
			buf.append("window.history.go(-1);");
		} else if (!"back".equals(url)) {
			buf.append("if(window.name=='content'){\n");
			buf.append("if(history.length)window.parent.location.replace('" + url + "');\n");
			buf.append("else\n");
			buf.append("window.parent.location='" + url + "';");
			buf.append("}");
			buf.append("else{\n");
			buf.append("if(history.length)window.parent.location.replace('" + url + "');\n");
			buf.append("else\n");
			buf.append("window.parent.location='" + url + "';\n");
			buf.append("}");
		}
		buf.append("</script>");
		out.println(buf.toString());
		out.flush();
		out.close();
	}

	/**
	 * Grab the subject from request
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws JspTagException
	 */
	public Subject getSubject(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		HttpAuthenticationUtils httpUtils = (HttpAuthenticationUtils) session.getAttribute(HttpConstants.AUTHN_UTILS);
		Subject subject = null;
		// AuthenticationManager authenticationManager = (AuthenticationManager)
		// request
		// .getSession().getServletContext().getAttribute(
		// SecurityConstants.AUTHENTICATION_MANAGER);
		if (httpUtils != null) {
			subject = httpUtils.getSubject();
			// subject =
			// authenticationManager.findUser(this.getPrivCredential(UserCredentialName.login
			// .name(), request, response));
		}
		boolean local = true;
		String authenticationScope = (String) session.getServletContext().getAttribute(
		        SecurityConstants.AUTHENTICATION_SCOPE);
		if (SecurityConstants.JVM_SCOPE.equalsIgnoreCase(authenticationScope)) {
			local = false;
		}
		if (subject == null || session.getAttribute(HttpConstants.AUTHN_UTILS) == null) {
			try {
				HttpAuthenticationUtils.authenticate((HttpServletRequest) request, (HttpServletResponse) response,
				        false, local);
				// we grab the subject authenticated
				subject = ((HttpAuthenticationUtils) session.getAttribute(HttpConstants.AUTHN_UTILS)).getSubject();
			} catch (IOException e) {
				return null;
			}
		}
		return subject;
	}

	/**
	 * get private credential value
	 * 
	 * @param name
	 * @param request
	 * @param response
	 * @return
	 */
	public String getPrivCredential(String name, HttpServletRequest request, HttpServletResponse response) {
		Subject subject = this.getSubject(request, response);
		String value = "";
		try {
			if (subject != null) {
				Set<Object> privateCredentials = subject.getPrivateCredentials();
				Iterator<Object> it = privateCredentials.iterator();
				JGuardCredential cred = null;

				while (it.hasNext()) {
					cred = (JGuardCredential) it.next();
					// if the id wanted by the webapp developer
					// is encountered in the public credentials subject
					if (cred.getId().equals(name)) {
						value = (String) cred.getValue();
						break;
					}
				}
			}
		} catch (SecurityException sex) {
			sex.printStackTrace();
		}
		return value;
	}

	/**
	 * get public credential value
	 * 
	 * @param name
	 *            数据库列名
	 * @param request
	 * @param response
	 * @return
	 */

	public String getPubCredential(String name, HttpServletRequest request, HttpServletResponse response) {
		Subject subject = this.getSubject(request, response);
		String value = "";
		try {
			if (subject != null) {

				Set<Object> publicCredentials = subject.getPublicCredentials();
				Iterator<Object> it = publicCredentials.iterator();
				JGuardCredential cred = null;

				while (it.hasNext()) {
					cred = (JGuardCredential) it.next();
					// if the id wanted by the webapp developer
					// is encountered in the public credentials subject
					if (cred.getId().equals(name)) {
						value = (String) cred.getValue();
						break;
					}
				}
			}
		} catch (SecurityException sex) {
			sex.printStackTrace();
		}
		return value;
	}

	/**
	 * 判断当前用户是否属于指定的角色集合
	 * 
	 * @param principalsStr
	 * @param operator
	 * 
	 *            ANY：有指定角色集合中的其中任一角色
	 * 
	 *            ALL：有指定角色集合中的全部角色
	 * 
	 *            NONE：没有指定角色集合中的任一角色
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean hasPrincipals(String principalsStr, String operator, HttpServletRequest request,
	        HttpServletResponse response) {
		List<String> principalsArray;
		try {
			principalsArray = Arrays.asList(principalsStr.split(","));
		} catch (Exception e1) {
			return false;
		}

		Subject subject = this.getSubject(request, response);
		if (subject == null) {
			return false;
		}

		Set<Principal> principals = subject.getPrincipals();

		// for (Iterator iterator = principals.iterator(); iterator.hasNext();)
		// {
		// Principal object = (Principal) iterator.next();
		// System.out.println("*******" + object.getClass());
		// System.out.println("*******" + object.getName());
		// }
		String applicationName = null;
		for (int j = 0; j < principalsArray.size(); j++) {
			Principal ppal = null;

			List<String> args = new ArrayList<String>();
			args.add(principalsArray.get(j));

			// we always add the applicationName for RolePrincipal
			if (applicationName == null) {
				applicationName = (String) request.getSession().getServletContext()
				        .getAttribute(CoreConstants.APPLICATION_NAME);
			}
			args.add(applicationName);
			ppal = PrincipalUtils.getPrincipal(RolePrincipal.class, new Class[] { String.class, String.class },
			        args.toArray());

			if (ppal == null) {
				return false;
			}

			if (!principals.contains(ppal)) {
				if (operator.equalsIgnoreCase(ALL)) {
					return false;
				}
			} else {
				Iterator<Principal> it = principals.iterator();
				boolean active = false;
				while (it.hasNext()) {
					Principal principal = (Principal) it.next();
					if (ppal.equals(principal) && ((RolePrincipal) principal).isActive()) {
						active = true;
						break;
					}
				}
				if (active == false) {
					return false;
				}
				// principals contains principalsArray[j]
				if (operator.equalsIgnoreCase(ANY)) {
					return true;
				} else if (operator.equalsIgnoreCase(NONE)) {
					return false;
				}
				return false;
			}
		}
		if (operator.equalsIgnoreCase(ALL) || operator.equalsIgnoreCase(NONE)) {
			return true;
		} else if (operator.equalsIgnoreCase(ANY)) {
			return false;
		}
		return false;
	}

	/**
	 * 判断当前用户是否具有指定权限
	 * 
	 * @param name
	 * @param actions
	 * @param className
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean hasPermission(String actions, HttpServletRequest request, HttpServletResponse response) {
		Subject subject = this.getSubject(request, response);
		if (subject == null) {
			return false;
		}
		Permission permission = null;
		try {
			permission = (Permission) PermissionUtils
			        .getPermission(URLPermission.class.getName(), "dumy name", actions);
		} catch (ClassNotFoundException e) {
			return false;
		}
		if (!HttpAccessControllerUtils.hasPermission(request, permission)) {
			return false;
		}
		return true;
	}

	/**
	 * 判断当前用户是否被授权相应的资源权限
	 * 
	 * @param uri
	 * @param permission
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean authorized(String uri, HttpServletRequest request, HttpServletResponse response) {
		Subject subject = this.getSubject(request, response);
		if (subject == null) {
			return false;
		}
		StringBuffer actions = new StringBuffer();
		actions.append(uri);
		Permission urlPermission = null;
		try {
			urlPermission = (Permission) PermissionUtils.getPermission(URLPermission.class.getName(), "dummy name",
			        actions.toString());
		} catch (ClassNotFoundException e) {
			return false;
		}
		if (!HttpAccessControllerUtils.hasPermission(request, urlPermission)) {
			return false;
		}
		return true;
	}

	/**
	 * 通过response直接返回json字符串内容
	 * 
	 * @param response
	 * @param text
	 * @throws IOException
	 */
	public void renderJson(HttpServletResponse response, String text) throws IOException {
		response.setHeader("CharSet", "UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/x-json;charset=UTF-8");
		response.getOutputStream().write(text.getBytes("UTF-8"));
	}

	/**
	 * 通过response直接返回js代码
	 * 
	 * @param response
	 * @param msg
	 */
	public void writeJsToFrontPage(HttpServletResponse response, String msg) {
		try {
			response.setContentType("text/html; charset=utf-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter pw = response.getWriter();
			pw.write("<SCRIPT TYPE='text/javascript'>" + msg + "</SCRIPT>");
			pw.close();
		} catch (Exception e) {
			LOGGER.error("writeJsToFrontPage error", e);
		}
	}

	/**
	 * 获取中文编码下载附件文件名
	 * 
	 * @param fileName
	 * @return
	 */
	protected String getDownloadFileName(final String fileName, final String attType) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("下载文件名（编码前）：fileName=" + fileName);
		}
		String attchName = fileName.replace("（", "").replace("）", "").replace("【", "").replace("】", "");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("下载文件名（替换符号后）：fileName=" + attchName + ", size is :" + attchName.length());
		}
		try {
			attchName = URLEncoder.encode(
			        String.format("%s_%s.doc", StringUtils.trimToEmpty(attchName), StringUtils.trimToEmpty(attType)),
			        CharEncoding.UTF_8);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("下载文件名（URLEncoder.encode）：fileName=" + attchName);
			}
			attchName = new String(attchName.getBytes(CharEncoding.UTF_8), CharEncoding.ISO_8859_1);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("下载文件名（new String(attchName.getBytes）：fileName=" + attchName);
			}
			return attchName;
		} catch (Exception e) {
			LOGGER.error("getDownloadFileName error", e);
			return fileName;
		}
	}
	
	/**
	 * 获取中文编码下载附件文件名
	 * 
	 * @param fileName
	 * @return
	 */
	protected String getDownloadFileNameAsZip(final String fileName, final String attType) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("下载文件名（编码前）：fileName=" + fileName);
		}
		
		String attchName = fileName.replace("（", "").replace("）", "").replace("【", "").replace("】", "");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("下载文件名（替换符号后）：fileName=" + attchName + ", size is :" + attchName.length());
		}
		
		try {
			attchName = URLEncoder.encode(
			        String.format("%s_%s.zip", StringUtils.trimToEmpty(attchName), StringUtils.trimToEmpty(attType)),
			        CharEncoding.UTF_8);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("下载文件名（URLEncoder.encode）：fileName=" + attchName);
			}
			attchName = new String(attchName.getBytes(CharEncoding.UTF_8), CharEncoding.ISO_8859_1);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("下载文件名（new String(attchName.getBytes）：fileName=" + attchName);
			}
			return attchName;
		} catch (Exception e) {
			LOGGER.error("getDownloadFileName error", e);
			return fileName;
		}
	}
	
	/**
	 * 获取中文编码下载附件文件名
	 * 
	 * @param fileName
	 * @return
	 */
	protected String getDownloadFileNameAsTxt(final String fileName, final String attType) {
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("下载文件名（编码前）：fileName=" + fileName);
		}
		
		String attchName = fileName.replace("（", "").replace("）", "").replace("【", "").replace("】", "");
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("下载文件名（替换符号后）：fileName=" + attchName + ", size is :" + attchName.length());
		}
		
		try {
			attchName = URLEncoder.encode(
			        String.format("%s_%s.txt", StringUtils.trimToEmpty(attchName), StringUtils.trimToEmpty(attType)),
			        CharEncoding.UTF_8);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("下载文件名（URLEncoder.encode）：fileName=" + attchName);
			}
			attchName = new String(attchName.getBytes(CharEncoding.UTF_8), CharEncoding.ISO_8859_1);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("下载文件名（new String(attchName.getBytes）：fileName=" + attchName);
			}
			return attchName;
		} catch (Exception e) {
			LOGGER.error("getDownloadFileName error", e);
			return fileName;
		}
	}

}