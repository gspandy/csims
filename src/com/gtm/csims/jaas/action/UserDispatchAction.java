package com.gtm.csims.jaas.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jguard.core.authentication.credentials.JGuardCredential;
import net.sf.jguard.core.principals.RolePrincipal;
import net.sf.jguard.ext.SecurityConstants;
import net.sf.jguard.ext.authentication.AuthenticationException;
import net.sf.jguard.ext.authentication.manager.AuthenticationManager;
import net.sf.jguard.ext.registration.RegistrationException;
import net.sf.jguard.ext.registration.SubjectTemplate;
import net.sf.jguard.jee.authentication.http.HttpAuthenticationUtils;
import net.sf.jguard.jee.authentication.http.HttpConstants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sweet.dao.generic.support.Page;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.jaas.service.UserService;
import com.gtm.csims.log.level.Logger;
import com.gtm.csims.utils.MD5;
import com.gtm.csims.model.BsDept;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsPhoto;
import com.gtm.csims.model.BsUserApplyInfo;
import com.gtm.csims.model.BsUserInfoOfJg;
import com.gtm.csims.model.BsUserInfoOfZx;
import com.gtm.csims.model.BsUserReportInfo;
import com.gtm.csims.business.managment.system.SystemBaseInfoManager;
import com.gtm.csims.business.remind.RemindService;
import com.gtm.csims.file.FileHandler;

/**
 * 系统用户处理Action
 * 
 * @author
 * 
 */
@SuppressWarnings("unchecked")
public class UserDispatchAction extends BaseAction {
	private static final Integer PAGE_CONTAIN_NUMBER = 10;
	private SystemBaseInfoManager systemBaseInfoManager;
	private RemindService remindService;
	private UserService userService;
	private FileHandler fileHandler;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public SystemBaseInfoManager getSystemBaseInfoManager() {
		return systemBaseInfoManager;
	}

	public void setSystemBaseInfoManager(
			SystemBaseInfoManager systemBaseInfoManager) {
		this.systemBaseInfoManager = systemBaseInfoManager;
	}

	public void setRemindService(RemindService remindService) {
		this.remindService = remindService;
	}

	public void setFileHandler(FileHandler fileHandler) {
		this.fileHandler = fileHandler;
	}
	
	@SuppressWarnings("unchecked")
	public void userTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String orgNo = request.getParameter("orgNo");
		try {
			response.setCharacterEncoding("UTF-8");
			List list = new ArrayList();
			List<BsUserInfoOfJg> result = userService.getUserByOrgNo(orgNo);
			for (int i = 0; i < result.size(); i++) {
				BsUserInfoOfJg bs = result.get(i);
				JSONObject object = new JSONObject();
				object.put("id", bs.getLoginId());
				object.put("text", bs.getName());
				list.add(object);
			}
			JSONArray array = JSONArray.fromObject(list);
			response.getWriter().write(array.toString());
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public void ajaxPepByOrgNoandDeptNoForJson(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String orgNo = request.getParameter("orgNo");
		String deptNo = request.getParameter("deptNo");
		String text = userService.getAepeople(orgNo, deptNo);
		try {
			renderJson(response, text);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addCredential(Set credentials, String id, String value) {
		if (StringUtils.isNotEmpty(value)) {
			JGuardCredential jcred = new JGuardCredential();
			jcred.setId(id);
			jcred.setValue(value);
			credentials.add(jcred);
		}
	}

	private void addPrincipals(String principalNames, Set principalsSet,
			AuthenticationManager am) {
		Logger.debug("principalNames=" + principalNames);
		if (!"".equals(principalNames)) {
			String[] prinNames = principalNames.split("-");
			for (int i = 0; i < prinNames.length; i++) {
				try {
					String principalName = prinNames[i];
					Logger.debug("current principal name=" + principalName);
					RolePrincipal principal = (RolePrincipal) am
							.getLocalPrincipal(principalName);
					if (principal == null) {
						Logger.action("local principal not found");
						return;
					}
					Logger.debug("local principal found=" + principal);
					principalsSet.add(principal);
				} catch (AuthenticationException e) {
					Logger.error(e.getMessage());
				}
			}
		}
	}

	/**
	 * List all system user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward userList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String loginName = this.getPrivCredential(UserCredentialName.login
				.name(), request, response);
		String loginNickName = this.getPubCredential(UserCredentialName.nickname
				.name(), request, response);
		String loginOrg = this.getPubCredential(UserCredentialName.organization
				.name(), request, response);
		BsUserInfoOfJg bs = userService.getBsUserInfoOfJgByLoginId(loginName);
		String loginUserType = bs.getUserType();
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String userName = (dyna.get("name") == null || dyna.get("name")
				.equals("")) ? "" : (String) dyna.get("name");
		String loginId = (dyna.get("loginId") == null || dyna.get("loginId")
				.equals("")) ? "" : (String) dyna.get("loginId");
		String organization = (dyna.get("organization") == null || dyna.get("organization")
				.equals("")) ? "" : (String) dyna.get("organization");
		String userRole = (dyna.get("userRole") == null || dyna.get("userRole")
				.equals("")) ? "" : (String) dyna.get("userRole");
		String userType = (dyna.get("userType") == null || dyna.get("userType")
				.equals("")) ? "" : (String) dyna.get("userType");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
				.get("pageSize");
		int pageCountTemp = 0;
		// 列表循环
		Page page = userService.getUserList(loginName,loginNickName,loginOrg,loginUserType,userName,loginId,
				organization,userRole,userType,Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		pageCountTemp = Long.valueOf(page.getTotalCount()).intValue();
		if (pageCountTemp == 0)
			pageCountTemp = 1;
		if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0)
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
		else
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
		/*
		 * 判断是否显示上一页或下一页
		 */
		if (Integer.valueOf(pageNo) != 1)
			dyna.set("previous", "1");
		else
			dyna.set("previous", "0");
		if (Integer.valueOf(pageNo) != pageCountTemp)
			dyna.set("next", "1");
		else
			dyna.set("next", "0");
		// 分页处理End

		// 再次将分页相关数据返回页面Start
		request.setAttribute("pageNo", pageNo);
		dyna.set("pageNo", pageNo);
		request.setAttribute("pageCount", pageCountTemp);
		dyna.set("pageCount", pageCountTemp);
		request.setAttribute("totalNo", page.getTotalCount());
		// 再次将分页相关数据返回页面End
		List userList = (List) page.getResult();
		request.setAttribute("userList", userList);
		List principalList = userService.getPrincipalList();
		request.setAttribute("principalList", principalList);
		if(!"内控监督员".equals(loginUserType)){
			return mapping.findForward("toUserList");
		}else{
			return mapping.findForward("toUserListOfNK");
		}
	}

	/**
	 * List all system user（兼任用户）
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward pluralUserList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String loginName = this.getPrivCredential(UserCredentialName.login
				.name(), request, response);
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
				.get("pageSize");
		int pageCountTemp = 0;
		// 列表循环
		Page page = userService.getPluralUserList(loginName, Integer
				.parseInt(pageNo), Integer.parseInt(pageSize));
		pageCountTemp = Long.valueOf(page.getTotalCount()).intValue();
		if (pageCountTemp == 0)
			pageCountTemp = 1;
		if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0)
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
		else
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
		/*
		 * 判断是否显示上一页或下一页
		 */
		if (Integer.valueOf(pageNo) != 1)
			dyna.set("previous", "1");
		else
			dyna.set("previous", "0");
		if (Integer.valueOf(pageNo) != pageCountTemp)
			dyna.set("next", "1");
		else
			dyna.set("next", "0");
		// 分页处理End

		// 再次将分页相关数据返回页面Start
		request.setAttribute("pageNo", pageNo);
		dyna.set("pageNo", pageNo);
		request.setAttribute("pageCount", pageCountTemp);
		dyna.set("pageCount", pageCountTemp);
		request.setAttribute("totalNo", page.getTotalCount());
		// 再次将分页相关数据返回页面End
		List userList = (List) page.getResult();
		request.setAttribute("userList", userList);
		return mapping.findForward("toPluralUserList");
	}

	@SuppressWarnings("unused")
	private Set filteUser(Set users, String credName, String credValue,
			boolean isPrivateCred) {
		Set newSet = new HashSet();
		Iterator usersIt = users.iterator();
		while (usersIt.hasNext()) {
			Subject user = (Subject) usersIt.next();
			Set credentials = null;
			if (isPrivateCred) {
				credentials = user.getPrivateCredentials();
			} else {
				credentials = user.getPublicCredentials();
			}
			for (Iterator iterator = credentials.iterator(); iterator.hasNext();) {
				JGuardCredential credential = (JGuardCredential) iterator
						.next();
				if (((String) credential.getId()).trim()
						.equals(credName.trim())) {
					if (((String) credential.getValue()).indexOf(credValue
							.trim()) != -1) {
						newSet.add(user);
						break;
					}
				}
			}
		}
		return newSet;
	}

	/**
	 * Go to the create user page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward createUserPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		AuthenticationManager authenticationManager = (AuthenticationManager) request
				.getSession().getServletContext().getAttribute(
						SecurityConstants.AUTHENTICATION_MANAGER);
		String nowLoginUserId = this.getPrivCredential(UserCredentialName.login
				.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsUserInfoOfJg bsLogin = userService.getBsUserInfoOfJgByLoginId(nowLoginUserId);
		String loginUserType = bsLogin.getUserType();
		request.setAttribute("loginUserType", loginUserType);
		Set<?> allPrincipal = authenticationManager.getLocalPrincipals();
		List returnList = new ArrayList();
		for (Iterator<?> it = allPrincipal.iterator(); it.hasNext();) {
			RolePrincipal principal = (RolePrincipal) it.next();
			returnList.add(principal);
		}
		PrincipalComparator comparator = new PrincipalComparator();
        Collections.sort(returnList, comparator);
		request.setAttribute("allPrincipals", returnList);
		dyna.set("dept", "");
		if(bsLogin.getUserType().equals("内控监督员")){
			BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
			dyna.set("organizationChoice", bsorg.getName());
			dyna.set("organization", nowloginerOrgNo);
			dyna.set("orgCodeOfZz", bsorg.getOrgCodeOfZz());
			dyna.set("orgCodeOfXy", bsorg.getOrgCodeOfXy());
			List useDeptList = systemBaseInfoManager.getDepByOrgNoForSelect(nowloginerOrgNo);
			request.setAttribute("useDeptList", useDeptList);
			return mapping.findForward("toCreateUserPageOfNK");
		}else{
			List useDeptList = systemBaseInfoManager.getDepByOrgNoForSelect("");
			request.setAttribute("useDeptList", useDeptList);
			return mapping.findForward("toCreateUserPage");
		}
	}

	/**
	 * Go to the create user apply
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public String createUserApply(BsUserApplyInfo bsUserApplyInfo) {
		try {
			userService.savaBsUserApplyInfo(bsUserApplyInfo);
			return "操作成功!";
		} catch (Exception e) {
			e.printStackTrace();
			return "操作失败!";
		}
	}

	public String createUser(BsUserInfoOfJg bsUserInfoOfJg, AuthenticationManager am) {
		SubjectTemplate st = new SubjectTemplate();
		// Private required credentials
		Set privRequiredCred = new HashSet();
		addCredential(privRequiredCred, UserCredentialName.login.name(),
				bsUserInfoOfJg.getLoginId().toString());
		MD5 md5 = new MD5();
		addCredential(privRequiredCred, UserCredentialName.password.name(), md5
				.getMD5ofStr("111111").toLowerCase());
		// add private required credentials to user
		st.setPrivateRequiredCredentials(privRequiredCred);

		// Public required creadentials
		Set publicRequiredCred = new HashSet();
		addCredential(publicRequiredCred, UserCredentialName.nickname.name(),
				bsUserInfoOfJg.getName().toString());
		addCredential(publicRequiredCred, UserCredentialName.organization
				.name(), bsUserInfoOfJg.getOrgCodeOfJr().toString());// 保存用户的机构ID
		addCredential(publicRequiredCred, UserCredentialName.ispcbuser
				.name(), bsUserInfoOfJg.getIsPcbUser().toString());// 保存用户的属性
		// add public required credentials to user
		st.setPublicRequiredCredentials(publicRequiredCred);

		/*
		 * // Private optional credentials Set privOptionalCred = new HashSet();
		 * addCredential(privOptionalCred, "country", (String)
		 * dyna.get("country")); addCredential(privOptionalCred, "religion",
		 * (String) dyna .get("religion")); // add private optional credentials
		 * to user st.setPrivateOptionalCredentials(privOptionalCred);
		 */

		// Public optional credentials
		/*
		 * Set publicOptionalCred = new HashSet();
		 * addCredential(publicOptionalCred, "hobbies", (String) dyna
		 * .get("hobbies")); // add public optional credentials to user
		 * st.setPublicOptionalCredentials(publicOptionalCred);
		 */

		// add principals
		st.getPrincipals().clear();
		String principalsNames = bsUserInfoOfJg.getJgPrincipal().toString();
		Logger.debug(" create user: principalsNames from form ="
				+ principalsNames);
		try {
			SubjectTemplate stClone = (SubjectTemplate) am
					.getDefaultSubjectTemplate().clone();
			stClone.getPrincipals().clear();
			addPrincipals(principalsNames, stClone.getPrincipals(), am);
			am.createUser(st, stClone);
			// JGuardCredential credentialId =
			// SubjectUtils.getIdentityCredential(
			// userCreated, stClone);
			BsUserInfoOfJg bs = userService.getBsUserInfoOfJgByLoginId(bsUserInfoOfJg.getLoginId());
			if(bs != null){
				bs.setNowUser("0");
				userService.savaBsUserInfoOfJg(bs);
			}
			userService.savaBsUserInfoOfJg(bsUserInfoOfJg);
			return "操作成功!";
		} catch (RegistrationException e) {
			Logger.error(e.getMissingPrivateCredential().toString());
			Logger.error(e.getMissingPublicCredential().toString());
			Logger.error(e.getMessage());
			return "操作失败!";
		} catch (AuthenticationException e) {
			Logger.error(e.getMessage());
			return "操作失败!";
		} catch (CloneNotSupportedException e) {
			Logger.error(e.getMessage());
			return "操作失败!";
		}
	}

	/**
	 * Go to the create user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public ActionForward createUserStart(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		AuthenticationManager am = (AuthenticationManager) request.getSession()
				.getServletContext().getAttribute(
						SecurityConstants.AUTHENTICATION_MANAGER);
		DynaActionForm dyna = (DynaActionForm) form;
		String message = "";
		String nowLoginUserId = this.getPrivCredential(UserCredentialName.login
				.name(), request, response);
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		BsUserInfoOfJg bsLogin = userService.getBsUserInfoOfJgByLoginId(nowLoginUserId);
		String loginId = (dyna.get("loginId") == null || dyna.get("loginId")
				.equals("")) ? "" : (String) dyna.get("loginId");
		String name = (dyna.get("name") == null || dyna.get("name").equals("")) ? ""
				: (String) dyna.get("name");
		String zxName1 = (dyna.get("zxName1") == null || dyna.get("zxName1")
				.equals("")) ? "" : (String) dyna.get("zxName1");
		String zxName2 = (dyna.get("zxName2") == null || dyna.get("zxName2")
				.equals("")) ? "" : (String) dyna.get("zxName2");
		String zxName3 = (dyna.get("zxName3") == null || dyna.get("zxName3")
				.equals("")) ? "" : (String) dyna.get("zxName3");
		String zxName4 = (dyna.get("zxName4") == null || dyna.get("zxName4")
				.equals("")) ? "" : (String) dyna.get("zxName4");
		String zxName5 = (dyna.get("zxName5") == null || dyna.get("zxName5")
				.equals("")) ? "" : (String) dyna.get("zxName5");
		String zxName6 = (dyna.get("zxName6") == null || dyna.get("zxName6")
				.equals("")) ? "" : (String) dyna.get("zxName6");
		String zxName7 = (dyna.get("zxName7") == null || dyna.get("zxName7")
				.equals("")) ? "" : (String) dyna.get("zxName7");
		String zxName8 = (dyna.get("zxName8") == null || dyna.get("zxName8")
				.equals("")) ? "" : (String) dyna.get("zxName8");
		String zxName9 = (dyna.get("zxName9") == null || dyna.get("zxName9")
				.equals("")) ? "" : (String) dyna.get("zxName9");
		String zxOrg1 = (dyna.get("zxOrg1") == null || dyna.get("zxOrg1")
				.equals("")) ? "" : (String) dyna.get("zxOrg1");
		String zxOrg2 = (dyna.get("zxOrg2") == null || dyna.get("zxOrg2")
				.equals("")) ? "" : (String) dyna.get("zxOrg2");
		String zxOrg3 = (dyna.get("zxOrg3") == null || dyna.get("zxOrg3")
				.equals("")) ? "" : (String) dyna.get("zxOrg3");
		String zxOrg4 = (dyna.get("zxOrg4") == null || dyna.get("zxOrg4")
				.equals("")) ? "" : (String) dyna.get("zxOrg4");
		String zxOrg5 = (dyna.get("zxOrg5") == null || dyna.get("zxOrg5")
				.equals("")) ? "" : (String) dyna.get("zxOrg5");
		String zxOrg6 = (dyna.get("zxOrg6") == null || dyna.get("zxOrg6")
				.equals("")) ? "" : (String) dyna.get("zxOrg6");
		String zxOrg7 = (dyna.get("zxOrg7") == null || dyna.get("zxOrg7")
				.equals("")) ? "" : (String) dyna.get("zxOrg7");
		String zxOrg8 = (dyna.get("zxOrg8") == null || dyna.get("zxOrg8")
				.equals("")) ? "" : (String) dyna.get("zxOrg8");
		String zxOrg9 = (dyna.get("zxOrg9") == null || dyna.get("zxOrg9")
				.equals("")) ? "" : (String) dyna.get("zxOrg9");
		String zxOrg1Choice = (dyna.get("zxOrg1Choice") == null || dyna.get("zxOrg1Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg1Choice");
		String zxOrg2Choice = (dyna.get("zxOrg2Choice") == null || dyna.get("zxOrg2Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg2Choice");
		String zxOrg3Choice = (dyna.get("zxOrg3Choice") == null || dyna.get("zxOrg3Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg3Choice");
		String zxOrg4Choice = (dyna.get("zxOrg4Choice") == null || dyna.get("zxOrg4Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg4Choice");
		String zxOrg5Choice = (dyna.get("zxOrg5Choice") == null || dyna.get("zxOrg5Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg5Choice");
		String zxOrg6Choice = (dyna.get("zxOrg6Choice") == null || dyna.get("zxOrg6Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg6Choice");
		String zxOrg7Choice = (dyna.get("zxOrg7Choice") == null || dyna.get("zxOrg7Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg7Choice");
		String zxOrg8Choice = (dyna.get("zxOrg8Choice") == null || dyna.get("zxOrg8Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg8Choice");
		String zxOrg9Choice = (dyna.get("zxOrg9Choice") == null || dyna.get("zxOrg9Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg9Choice");
		String cardType = (dyna.get("cardType") == null || dyna.get("cardType")
				.equals("")) ? "" : (String) dyna.get("cardType");
		String education = (dyna.get("education") == null || dyna.get(
				"education").equals("")) ? "" : (String) dyna.get("education");
		String post = (dyna.get("post") == null || dyna.get("post").equals("")) ? ""
				: (String) dyna.get("post");
		String phone = (dyna.get("phone") == null || dyna.get("phone").equals("")) ? ""
				: (String) dyna.get("phone");
		String userType = (dyna.get("userType") == null || dyna.get("userType").equals("")) ? ""
				: (String) dyna.get("userType");
		String userCard = (dyna.get("userCard") == null || dyna.get("userCard").equals("")) ? ""
				: (String) dyna.get("userCard");
		String organization = (dyna.get("organization") == null || dyna.get(
				"organization").equals("")) ? "" : (String) dyna
				.get("organization");
		String orgCodeOfXy = (dyna.get("orgCodeOfXy") == null || dyna.get(
				"orgCodeOfXy").equals("")) ? "" : (String) dyna
				.get("orgCodeOfXy");
		String orgCodeOfZz = (dyna.get("orgCodeOfZz") == null || dyna.get(
				"orgCodeOfZz").equals("")) ? "" : (String) dyna
				.get("orgCodeOfZz");
		String organizationChoice = (dyna.get("organizationChoice") == null || dyna
				.get("organizationChoice").equals("")) ? "" : (String) dyna
				.get("organizationChoice");
		String userPrincipalsNames = (dyna.get("userPrincipalsNames") == null || dyna
				.get("userPrincipalsNames").equals("")) ? "" : (String) dyna
				.get("userPrincipalsNames");
		String isPcbUser = systemBaseInfoManager.getOrgByNo(organization).getIspcb();
		String deptNo = (dyna.get("dept") == null || dyna.get("dept")
				.equals("")) ? "" : (String) dyna.get("dept");
		BsDept bsDept = new BsDept();
		if (!"0".equals(deptNo)) {
			bsDept = systemBaseInfoManager.getDeptByNo(deptNo);
		} else {
			bsDept.setId("0");
			bsDept.setName("无部门");
		}
		String[] zxPrincipals = request.getParameterValues("zxPrincipal");
		String zxPrincipal = "";
		if (zxPrincipals != null && zxPrincipals.length>0) {
			for (int i = 0; i < zxPrincipals.length; i++) {
				String str = zxPrincipals[i].trim().trim();
				String strName = super.getZXPriName(str);
				zxPrincipal = zxPrincipal + strName + ",";
			}
		}
		Date date = new Date();
		userPrincipalsNames = userPrincipalsNames.substring(0,
				userPrincipalsNames.length() - 1);
		String loginUserType = bsLogin.getUserType().toString();
		if ("内控监督员".equals(loginUserType)) {
			BsUserApplyInfo bsUserApplyInfo = new BsUserApplyInfo();
			bsUserApplyInfo.setId(loginId);
			bsUserApplyInfo.setName(name);
			bsUserApplyInfo.setIsPcbUser(isPcbUser);
			bsUserApplyInfo.setCardType(cardType);
			bsUserApplyInfo.setJgPrincipal(userPrincipalsNames);
			bsUserApplyInfo.setZxPrincipal(zxPrincipal);
			bsUserApplyInfo.setEducation(education);
			bsUserApplyInfo.setPost(post);
			bsUserApplyInfo.setUserCard(userCard);
			bsUserApplyInfo.setDeptNo(deptNo);
			bsUserApplyInfo.setDeptName(bsDept.getName());
			bsUserApplyInfo.setOrgCodeOfJr(organization);
			bsUserApplyInfo.setOrgCodeOfXy(orgCodeOfXy);
			bsUserApplyInfo.setOrgCodeOfZz(orgCodeOfZz);
			bsUserApplyInfo.setOrgName(organizationChoice);
			bsUserApplyInfo.setUserType(userType);
			bsUserApplyInfo.setPhone(phone);
			bsUserApplyInfo.setApplyCode(nowLoginUserId);
			bsUserApplyInfo.setApplyName(nowLoginUserName);
			bsUserApplyInfo.setCreaterCode(bsLogin.getManagerCode());
			bsUserApplyInfo.setFlag("1");// 申请提交到审核
			request.setAttribute("methodname", "applyUserList");
			message = this.createUserApply(bsUserApplyInfo);
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户申请【" + name + "】【"+message+"】", "", "", "HTTP",nowLoginUserName);
		} else {
			BsUserInfoOfJg bsUserInfoOfJg = new BsUserInfoOfJg();
			String id = UUID.randomUUID().toString().replace("-", "");
			bsUserInfoOfJg.setId(id);
			bsUserInfoOfJg.setLoginId(loginId);
			bsUserInfoOfJg.setName(name);
			bsUserInfoOfJg.setIsPcbUser(isPcbUser);
			bsUserInfoOfJg.setCardType(cardType);
			bsUserInfoOfJg.setJgPrincipal(userPrincipalsNames);
			bsUserInfoOfJg.setEducation(education);
			bsUserInfoOfJg.setPost(post);
			bsUserInfoOfJg.setOrgCodeOfJr(organization);
			bsUserInfoOfJg.setOrgCodeOfXy(orgCodeOfXy);
			bsUserInfoOfJg.setOrgCodeOfZz(orgCodeOfZz);
			bsUserInfoOfJg.setOrgName(organizationChoice);
			bsUserInfoOfJg.setDeptNo(deptNo);
			bsUserInfoOfJg.setDeptName(bsDept.getName());
			bsUserInfoOfJg.setPhone(phone);
			bsUserInfoOfJg.setUserType(userType);
			bsUserInfoOfJg.setStoperCode("");
			bsUserInfoOfJg.setUserStatus("启用");
			bsUserInfoOfJg.setNowUser("1");
			bsUserInfoOfJg.setUserCard(userCard);
			bsUserInfoOfJg.setCreaterCode(nowLoginUserId);
			bsUserInfoOfJg.setCreaterName(nowLoginUserName);
			bsUserInfoOfJg.setCreateDate(date);
			bsUserInfoOfJg.setManagerCode(nowLoginUserId);
			bsUserInfoOfJg.setStoperName("");
			bsUserInfoOfJg.setStoperCode("");
			request.setAttribute("methodname", "userList");
			message = this.createUser(bsUserInfoOfJg, am);
			String[] zxUserTypes = zxPrincipal.split(",");
			for(int i=0;i<zxUserTypes.length;i++){
				BsUserInfoOfZx bs = new BsUserInfoOfZx();
				String zxUserType = zxUserTypes[i];
				bs.setUserId(id);
				bs.setLoginId(loginId);
				bs.setNowUser("1");
				bs.setCreateDate(date);
				bs.setZxUserType(zxUserType);
				bs.setUserStatus("启用");
				if("企业征信管理员用户".equals(zxUserType)){
					bs.setZxUserName(zxName1);
					bs.setOrgCode(zxOrg1);
					bs.setOrgName(zxOrg1Choice);
					userService.savaBsUserInfoOfZx(bs);
				}
				if("企业征信查询用户".equals(zxUserType)){
					bs.setZxUserName(zxName2);
					bs.setOrgCode(zxOrg2);
					bs.setOrgName(zxOrg2Choice);
					userService.savaBsUserInfoOfZx(bs);
				}
				if("企业征信数据报送用户".equals(zxUserType)){
					bs.setZxUserName(zxName3);
					bs.setOrgCode(zxOrg3);
					bs.setOrgName(zxOrg3Choice);
					userService.savaBsUserInfoOfZx(bs);
				}
				if("企业征信异议处理用户".equals(zxUserType)){
					bs.setZxUserName(zxName4);
					bs.setOrgCode(zxOrg4);
					bs.setOrgName(zxOrg4Choice);
					userService.savaBsUserInfoOfZx(bs);
				}
				if("个人征信管理员用户".equals(zxUserType)){
					bs.setZxUserName(zxName5);
					bs.setOrgCode(zxOrg5);
					bs.setOrgName(zxOrg5Choice);
					userService.savaBsUserInfoOfZx(bs);
				}
				if("个人征信查询用户".equals(zxUserType)){
					bs.setZxUserName(zxName6);
					bs.setOrgCode(zxOrg6);
					bs.setOrgName(zxOrg6Choice);
					userService.savaBsUserInfoOfZx(bs);
				}
				if("个人征信数据报送用户".equals(zxUserType)){
					bs.setZxUserName(zxName7);
					bs.setOrgCode(zxOrg7);
					bs.setOrgName(zxOrg7Choice);
					userService.savaBsUserInfoOfZx(bs);
				}
				if("个人征信异议处理用户".equals(zxUserType)){
					bs.setZxUserName(zxName8);
					bs.setOrgCode(zxOrg8);
					bs.setOrgName(zxOrg8Choice);
					userService.savaBsUserInfoOfZx(bs);
				}
				if("其他".equals(zxUserType)){
					bs.setZxUserName(zxName9);
					bs.setOrgCode(zxOrg9);
					bs.setOrgName(zxOrg9Choice);
					userService.savaBsUserInfoOfZx(bs);
				}
			}
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户创建【" + name + "】【"+message+"】", "", "", "HTTP",nowLoginUserName);
		}
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}

	/**
	 * Go to the apply user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward applyUserList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String nowLoginUserName = this.getPrivCredential(
				UserCredentialName.login.name(), request, response);
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
				.get("pageSize");
		String userName = (dyna.get("name") == null || dyna.get("name")
			.equals("")) ? "" : (String) dyna.get("name");
		String loginId = (dyna.get("loginId") == null || dyna.get("loginId")
				.equals("")) ? "" : (String) dyna.get("loginId");
		String organization = (dyna.get("organization") == null || dyna.get("organization")
				.equals("")) ? "" : (String) dyna.get("organization");
		int pageCountTemp = 0;
		// 列表循环
		Page page = userService.getApplyUserList(nowLoginUserName,userName,loginId,organization, Integer
				.parseInt(pageNo), Integer.parseInt(pageSize));
		pageCountTemp = Long.valueOf(page.getTotalCount()).intValue();
		if (pageCountTemp == 0)
			pageCountTemp = 1;
		if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0)
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
		else
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
		/*
		 * 判断是否显示上一页或下一页
		 */
		if (Integer.valueOf(pageNo) != 1)
			dyna.set("previous", "1");
		else
			dyna.set("previous", "0");
		if (Integer.valueOf(pageNo) != pageCountTemp)
			dyna.set("next", "1");
		else
			dyna.set("next", "0");
		// 分页处理End

		// 再次将分页相关数据返回页面Start
		request.setAttribute("pageNo", pageNo);
		dyna.set("pageNo", pageNo);
		request.setAttribute("pageCount", pageCountTemp);
		dyna.set("pageCount", pageCountTemp);
		request.setAttribute("totalNo", page.getTotalCount());
		// 再次将分页相关数据返回页面End
		List applyUserList = (List) page.getResult();
		request.setAttribute("applyUserList", applyUserList);
		return mapping.findForward("toApplyUserList");
	}

	/**
	 * Go to the apply user page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward applyUserPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String loginId = request.getParameter("id");
		BsUserApplyInfo bsUserApplyInfo = userService
				.getBsUserApplyInfo(loginId);
		dyna.set("loginId", loginId);
		dyna.set("oldLoginId", loginId);
		dyna.set("name", String.valueOf(bsUserApplyInfo.getName()));
		dyna.set("cardType", String.valueOf(bsUserApplyInfo.getCardType()));
		dyna.set("userType", String.valueOf(bsUserApplyInfo.getUserType()));
		dyna.set("education", String.valueOf(bsUserApplyInfo.getEducation()));
		dyna.set("post", String.valueOf(bsUserApplyInfo.getPost()));
		dyna.set("phone", String.valueOf(bsUserApplyInfo.getPhone()));
		dyna.set("organizationChoice", String.valueOf(bsUserApplyInfo
				.getOrgName()));
		dyna.set("orgCodeOfZz", String
				.valueOf(bsUserApplyInfo.getOrgCodeOfZz()));
		dyna.set("orgCodeOfXy", String
				.valueOf(bsUserApplyInfo.getOrgCodeOfXy()));
		String orgNo = bsUserApplyInfo.getOrgCodeOfJr();
		dyna.set("organization", orgNo);
		String deptNo = bsUserApplyInfo.getDeptNo();
		List useDeptList = systemBaseInfoManager.getDepByOrgNoForSelect(orgNo);
		request.setAttribute("useDeptList", useDeptList);
		dyna.set("dept", deptNo);
		String flag = "";
		if ("内控监督员".equals(bsUserApplyInfo.getUserType())) {
			flag = "0";
			dyna.set("userCode", loginId);
		} else {
			flag = "1";
			dyna.set("cardId", loginId);
		}
		request.setAttribute("flag", flag);
		List<String[]> zxStrList = userService.getUserZxInfoTrOfApply(loginId,"0");
		String[] zxStr = zxStrList.get(0);
		request.setAttribute("zxStr1", zxStr[0]);
		request.setAttribute("zxStr2", zxStr[1]);
		request.setAttribute("zxStr3", zxStr[2]);
		request.setAttribute("zxStr4", zxStr[3]);
		request.setAttribute("zxStr5", zxStr[4]);
		request.setAttribute("zxStr6", zxStr[5]);
		request.setAttribute("zxStr7", zxStr[6]);
		request.setAttribute("zxStr8", zxStr[7]);
		request.setAttribute("zxStr9", zxStr[8]);
		String userPrin = bsUserApplyInfo.getJgPrincipal();
		List principalsList = new ArrayList();
		String[] userPrins = userPrin.split("-");
		for (int i = 0; i < userPrins.length; i++) {
			principalsList.add(userPrins[i]);
		}
		AuthenticationManager authenticationManager = (AuthenticationManager) request
				.getSession().getServletContext().getAttribute(
						SecurityConstants.AUTHENTICATION_MANAGER);
		dyna.set("principals", principalsList);
		Collection tempCol = new HashSet(authenticationManager
				.getLocalPrincipals());
		List returnList = new ArrayList();
		for (Iterator<?> it = tempCol.iterator(); it.hasNext();) {
			RolePrincipal principal = (RolePrincipal) it.next();
			returnList.add(principal);
		}
		PrincipalComparator comparator = new PrincipalComparator();
        Collections.sort(returnList, comparator);
		request.setAttribute("allPrincipals", returnList);
		request.setAttribute("principals", principalsList);
		return mapping.findForward("toApplyUserPage");
	}

	/**
	 * Go to the apply user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public ActionForward applyUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "applyUserList");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		String message = "";
		String loginId = (dyna.get("loginId") == null || dyna.get("loginId")
				.equals("")) ? "" : (String) dyna.get("loginId");
		String oldLoginId = (dyna.get("oldLoginId") == null || dyna.get(
				"oldLoginId").equals("")) ? "" : (String) dyna
				.get("oldLoginId");
		String name = (dyna.get("name") == null || dyna.get("name").equals("")) ? ""
				: (String) dyna.get("name");
		String cardType = (dyna.get("cardType") == null || dyna.get("cardType")
				.equals("")) ? "" : (String) dyna.get("cardType");
		String education = (dyna.get("education") == null || dyna.get(
				"education").equals("")) ? "" : (String) dyna.get("education");
		String post = (dyna.get("post") == null || dyna.get("post").equals("")) ? ""
				: (String) dyna.get("post");
		String organization = (dyna.get("organization") == null || dyna.get(
				"organization").equals("")) ? "" : (String) dyna
				.get("organization");
		String orgCodeOfXy = (dyna.get("orgCodeOfXy") == null || dyna.get(
				"orgCodeOfXy").equals("")) ? "" : (String) dyna
				.get("orgCodeOfXy");
		String orgCodeOfZz = (dyna.get("orgCodeOfZz") == null || dyna.get(
				"orgCodeOfZz").equals("")) ? "" : (String) dyna
				.get("orgCodeOfZz");
		String organizationChoice = (dyna.get("organizationChoice") == null || dyna
				.get("organizationChoice").equals("")) ? "" : (String) dyna
				.get("organizationChoice");
		String phone = (dyna.get("phone") == null || dyna.get("phone").equals(
				"")) ? "" : (String) dyna.get("phone");
		String userType = (dyna.get("userType") == null || dyna.get("userType").equals(
				"")) ? "" : (String) dyna.get("userType");
		String[] zxPrincipals = request.getParameterValues("zxPrincipal");
		String zxPrincipal = "";
		if (zxPrincipals != null && zxPrincipals.length>0) {
			for (int i = 0; i < zxPrincipals.length; i++) {
				String str = zxPrincipals[i].trim().trim();
				String strName = super.getZXPriName(str);
				zxPrincipal = zxPrincipal + strName + ",";
			}
		}
		String userPrincipalsNames = (dyna.get("userPrincipalsNames") == null || dyna
				.get("userPrincipalsNames").equals("")) ? "" : (String) dyna
				.get("userPrincipalsNames");
		userPrincipalsNames = userPrincipalsNames.substring(0,
				userPrincipalsNames.length() - 1);
		String isPcbUser = systemBaseInfoManager.getOrgByNo(organization).getIspcb();
		String deptNo = (dyna.get("dept") == null || dyna.get("dept")
				.equals("")) ? "" : (String) dyna.get("dept");
		BsDept bsDept = new BsDept();
		if (!"0".equals(deptNo)) {
			bsDept = systemBaseInfoManager.getDeptByNo(deptNo);
		} else {
			bsDept.setId("0");
			bsDept.setName("无部门");
		}
		BsUserApplyInfo bsUserApplyInfo = userService
				.getBsUserApplyInfo(oldLoginId);
		bsUserApplyInfo.setId(loginId);
		bsUserApplyInfo.setName(name);
		bsUserApplyInfo.setIsPcbUser(isPcbUser);
		bsUserApplyInfo.setCardType(cardType);
		bsUserApplyInfo.setJgPrincipal(userPrincipalsNames);
		bsUserApplyInfo.setEducation(education);
		bsUserApplyInfo.setOrgCodeOfJr(organization);
		bsUserApplyInfo.setOrgCodeOfXy(orgCodeOfXy);
		bsUserApplyInfo.setOrgCodeOfZz(orgCodeOfZz);
		bsUserApplyInfo.setOrgName(organizationChoice);
		bsUserApplyInfo.setDeptNo(deptNo);
		bsUserApplyInfo.setDeptName(bsDept.getName());
		bsUserApplyInfo.setPhone(phone);
		bsUserApplyInfo.setUserType(userType);
		bsUserApplyInfo.setZxPrincipal(zxPrincipal);
		bsUserApplyInfo.setFlag("1");// 申请到审核
		message = this.createUserApply(bsUserApplyInfo);
		remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
				+ "】 用户提交用户申请【"+message+"】", "", "", "HTTP",nowLoginUserName);
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}

	/**
	 * Go to the audit user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward auditUserList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String nowLoginUserName = this.getPrivCredential(
				UserCredentialName.login.name(), request, response);
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
				.get("pageSize");
		String userName = (dyna.get("name") == null || dyna.get("name")
				.equals("")) ? "" : (String) dyna.get("name");
		String loginId = (dyna.get("loginId") == null || dyna.get("loginId")
				.equals("")) ? "" : (String) dyna.get("loginId");
		String organization = (dyna.get("organization") == null || dyna.get("organization")
				.equals("")) ? "" : (String) dyna.get("organization");
		int pageCountTemp = 0;
		// 列表循环
		Page page = userService.getAuditUserList(nowLoginUserName,userName,loginId,organization, 
				Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		pageCountTemp = Long.valueOf(page.getTotalCount()).intValue();
		if (pageCountTemp == 0)
			pageCountTemp = 1;
		if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0)
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
		else
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
		/*
		 * 判断是否显示上一页或下一页
		 */
		if (Integer.valueOf(pageNo) != 1)
			dyna.set("previous", "1");
		else
			dyna.set("previous", "0");
		if (Integer.valueOf(pageNo) != pageCountTemp)
			dyna.set("next", "1");
		else
			dyna.set("next", "0");
		// 分页处理End

		// 再次将分页相关数据返回页面Start
		request.setAttribute("pageNo", pageNo);
		dyna.set("pageNo", pageNo);
		request.setAttribute("pageCount", pageCountTemp);
		dyna.set("pageCount", pageCountTemp);
		request.setAttribute("totalNo", page.getTotalCount());
		// 再次将分页相关数据返回页面End
		List auditUserList = (List) page.getResult();
		request.setAttribute("auditUserList", auditUserList);
		return mapping.findForward("toAuditUserList");
	}

	/**
	 * Go to the audit user page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward auditUserPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String loginId = request.getParameter("id");
		BsUserApplyInfo bsUserApplyInfo = userService
				.getBsUserApplyInfo(loginId);
		dyna.set("loginId", loginId);
		dyna.set("oldLoginId", loginId);
		dyna.set("name", String.valueOf(bsUserApplyInfo.getName()));
		dyna.set("userType", String.valueOf(bsUserApplyInfo.getUserType()));
		dyna.set("cardType", String.valueOf(bsUserApplyInfo.getCardType()));
		dyna.set("applyName", String.valueOf(bsUserApplyInfo.getApplyName()));
		dyna.set("education", String.valueOf(bsUserApplyInfo.getEducation()));
		dyna.set("phone", String.valueOf(bsUserApplyInfo.getPhone()));
		dyna.set("organizationChoice", String.valueOf(bsUserApplyInfo
				.getOrgName()));
		dyna.set("orgCodeOfZz", String
				.valueOf(bsUserApplyInfo.getOrgCodeOfZz()));
		dyna.set("orgCodeOfXy", String
				.valueOf(bsUserApplyInfo.getOrgCodeOfXy()));
		String orgNo = bsUserApplyInfo.getOrgCodeOfJr();
		dyna.set("organization", orgNo);
		String deptNo = bsUserApplyInfo.getDeptNo();
		List useDeptList = systemBaseInfoManager.getDepByOrgNoForSelect(orgNo);
		request.setAttribute("useDeptList", useDeptList);
		dyna.set("dept", deptNo);
		String flag = "";
		if ("内控监督员".equals(bsUserApplyInfo.getUserType())) {
			flag = "0";
			dyna.set("userCode", loginId);
		} else {
			flag = "1";
			dyna.set("cardId", loginId);
		}
		request.setAttribute("flag", flag);
		List<String[]> zxStrList = userService.getUserZxInfoTrOfApply(loginId,"1");
		String[] zxStr = zxStrList.get(0);
		String[] flags = zxStrList.get(1);
		request.setAttribute("zxStr1", zxStr[0]);
		request.setAttribute("zxStr2", zxStr[1]);
		request.setAttribute("zxStr3", zxStr[2]);
		request.setAttribute("zxStr4", zxStr[3]);
		request.setAttribute("zxStr5", zxStr[4]);
		request.setAttribute("zxStr6", zxStr[5]);
		request.setAttribute("zxStr7", zxStr[6]);
		request.setAttribute("zxStr8", zxStr[7]);
		request.setAttribute("zxStr9", zxStr[8]);
		request.setAttribute("flag1", flags[0]);
		request.setAttribute("flag2", flags[1]);
		request.setAttribute("flag3", flags[2]);
		request.setAttribute("flag4", flags[3]);
		request.setAttribute("flag5", flags[4]);
		request.setAttribute("flag6", flags[5]);
		request.setAttribute("flag7", flags[6]);
		request.setAttribute("flag8", flags[7]);
		request.setAttribute("flag9", flags[8]);
		List principalsList = new ArrayList();
		String userPrin = bsUserApplyInfo.getJgPrincipal();
		String[] userPrins = userPrin.split("-");
		for (int i = 0; i < userPrins.length; i++) {
			principalsList.add(userPrins[i]);
		}
		AuthenticationManager authenticationManager = (AuthenticationManager) request
				.getSession().getServletContext().getAttribute(
						SecurityConstants.AUTHENTICATION_MANAGER);
		dyna.set("principals", principalsList);
		Collection tempCol = new HashSet(authenticationManager
				.getLocalPrincipals());
		List returnList = new ArrayList();
		for (Iterator<?> it = tempCol.iterator(); it.hasNext();) {
			RolePrincipal principal = (RolePrincipal) it.next();
			returnList.add(principal);
		}
		PrincipalComparator comparator = new PrincipalComparator();
        Collections.sort(returnList, comparator);
		request.setAttribute("allPrincipals", returnList);
		request.setAttribute("principals", principalsList);
		return mapping.findForward("toAuditUserPage");
	}

	/**
	 * Go to the audit user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public ActionForward auditUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		AuthenticationManager am = (AuthenticationManager) request.getSession()
				.getServletContext().getAttribute(
						SecurityConstants.AUTHENTICATION_MANAGER);
		String nowLoginUserId = this.getPrivCredential(UserCredentialName.login
				.name(), request, response);
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		request.setAttribute("methodname", "auditUserList");
		try {
			String message = "";
			String auditResult = (dyna.get("auditResult") == null || dyna.get(
					"auditResult").equals("")) ? "" : (String) dyna
					.get("auditResult");
			String loginId = (dyna.get("loginId") == null || dyna
					.get("loginId").equals("")) ? "" : (String) dyna
					.get("loginId");
			String oldLoginId = (dyna.get("oldLoginId") == null || dyna.get(
					"oldLoginId").equals("")) ? "" : (String) dyna
					.get("oldLoginId");
			String name = (dyna.get("name") == null || dyna.get("name").equals(
					"")) ? "" : (String) dyna.get("name");
			String cardType = (dyna.get("cardType") == null || dyna.get(
					"cardType").equals("")) ? "" : (String) dyna
					.get("cardType");
			String userType = (dyna.get("userType") == null || dyna.get(
					"userType").equals("")) ? "" : (String) dyna
					.get("userType");
			String education = (dyna.get("education") == null || dyna.get(
					"education").equals("")) ? "" : (String) dyna
					.get("education");
			String organization = (dyna.get("organization") == null || dyna
					.get("organization").equals("")) ? "" : (String) dyna
					.get("organization");
			String orgCodeOfXy = (dyna.get("orgCodeOfXy") == null || dyna.get(
					"orgCodeOfXy").equals("")) ? "" : (String) dyna
					.get("orgCodeOfXy");
			String orgCodeOfZz = (dyna.get("orgCodeOfZz") == null || dyna.get(
					"orgCodeOfZz").equals("")) ? "" : (String) dyna
					.get("orgCodeOfZz");
			String organizationChoice = (dyna.get("organizationChoice") == null || dyna
					.get("organizationChoice").equals("")) ? "" : (String) dyna
					.get("organizationChoice");
			String phone = (dyna.get("phone") == null || dyna.get("phone")
					.equals("")) ? "" : (String) dyna.get("phone");
			String zxName1 = (dyna.get("zxName1") == null || dyna.get("zxName1")
					.equals("")) ? "" : (String) dyna.get("zxName1");
			String zxName2 = (dyna.get("zxName2") == null || dyna.get("zxName2")
					.equals("")) ? "" : (String) dyna.get("zxName2");
			String zxName3 = (dyna.get("zxName3") == null || dyna.get("zxName3")
					.equals("")) ? "" : (String) dyna.get("zxName3");
			String zxName4 = (dyna.get("zxName4") == null || dyna.get("zxName4")
					.equals("")) ? "" : (String) dyna.get("zxName4");
			String zxName5 = (dyna.get("zxName5") == null || dyna.get("zxName5")
					.equals("")) ? "" : (String) dyna.get("zxName5");
			String zxName6 = (dyna.get("zxName6") == null || dyna.get("zxName6")
					.equals("")) ? "" : (String) dyna.get("zxName6");
			String zxName7 = (dyna.get("zxName7") == null || dyna.get("zxName7")
					.equals("")) ? "" : (String) dyna.get("zxName7");
			String zxName8 = (dyna.get("zxName8") == null || dyna.get("zxName8")
					.equals("")) ? "" : (String) dyna.get("zxName8");
			String zxName9 = (dyna.get("zxName9") == null || dyna.get("zxName9")
					.equals("")) ? "" : (String) dyna.get("zxName9");
			String zxOrg1 = (dyna.get("zxOrg1") == null || dyna.get("zxOrg1")
					.equals("")) ? "" : (String) dyna.get("zxOrg1");
			String zxOrg2 = (dyna.get("zxOrg2") == null || dyna.get("zxOrg2")
					.equals("")) ? "" : (String) dyna.get("zxOrg2");
			String zxOrg3 = (dyna.get("zxOrg3") == null || dyna.get("zxOrg3")
					.equals("")) ? "" : (String) dyna.get("zxOrg3");
			String zxOrg4 = (dyna.get("zxOrg4") == null || dyna.get("zxOrg4")
					.equals("")) ? "" : (String) dyna.get("zxOrg4");
			String zxOrg5 = (dyna.get("zxOrg5") == null || dyna.get("zxOrg5")
					.equals("")) ? "" : (String) dyna.get("zxOrg5");
			String zxOrg6 = (dyna.get("zxOrg6") == null || dyna.get("zxOrg6")
					.equals("")) ? "" : (String) dyna.get("zxOrg6");
			String zxOrg7 = (dyna.get("zxOrg7") == null || dyna.get("zxOrg7")
					.equals("")) ? "" : (String) dyna.get("zxOrg7");
			String zxOrg8 = (dyna.get("zxOrg8") == null || dyna.get("zxOrg8")
					.equals("")) ? "" : (String) dyna.get("zxOrg8");
			String zxOrg9 = (dyna.get("zxOrg9") == null || dyna.get("zxOrg9")
					.equals("")) ? "" : (String) dyna.get("zxOrg9");
			String zxOrg1Choice = (dyna.get("zxOrg1Choice") == null || dyna.get("zxOrg1Choice")
					.equals("")) ? "" : (String) dyna.get("zxOrg1Choice");
			String zxOrg2Choice = (dyna.get("zxOrg2Choice") == null || dyna.get("zxOrg2Choice")
					.equals("")) ? "" : (String) dyna.get("zxOrg2Choice");
			String zxOrg3Choice = (dyna.get("zxOrg3Choice") == null || dyna.get("zxOrg3Choice")
					.equals("")) ? "" : (String) dyna.get("zxOrg3Choice");
			String zxOrg4Choice = (dyna.get("zxOrg4Choice") == null || dyna.get("zxOrg4Choice")
					.equals("")) ? "" : (String) dyna.get("zxOrg4Choice");
			String zxOrg5Choice = (dyna.get("zxOrg5Choice") == null || dyna.get("zxOrg5Choice")
					.equals("")) ? "" : (String) dyna.get("zxOrg5Choice");
			String zxOrg6Choice = (dyna.get("zxOrg6Choice") == null || dyna.get("zxOrg6Choice")
					.equals("")) ? "" : (String) dyna.get("zxOrg6Choice");
			String zxOrg7Choice = (dyna.get("zxOrg7Choice") == null || dyna.get("zxOrg7Choice")
					.equals("")) ? "" : (String) dyna.get("zxOrg7Choice");
			String zxOrg8Choice = (dyna.get("zxOrg8Choice") == null || dyna.get("zxOrg8Choice")
					.equals("")) ? "" : (String) dyna.get("zxOrg8Choice");
			String zxOrg9Choice = (dyna.get("zxOrg9Choice") == null || dyna.get("zxOrg9Choice")
					.equals("")) ? "" : (String) dyna.get("zxOrg9Choice");
			String userPrincipalsNames = (dyna.get("userPrincipalsNames") == null || dyna
					.get("userPrincipalsNames").equals("")) ? ""
					: (String) dyna.get("userPrincipalsNames");
			userPrincipalsNames = userPrincipalsNames.substring(0,
					userPrincipalsNames.length() - 1);
			BsOrg bsOrg = systemBaseInfoManager.getOrgByNo(organization);
			String isPcbUser = bsOrg.getIspcb();
			String deptNo = (dyna.get("dept") == null || dyna.get("dept")
					.equals("")) ? "" : (String) dyna.get("dept");
			BsDept bsDept = new BsDept();
			if (!"0".equals(deptNo)) {
				bsDept = systemBaseInfoManager.getDeptByNo(deptNo);
			} else {
				bsDept.setId("0");
				bsDept.setName("无部门");
			}
			BsUserApplyInfo bsUserApplyInfo = userService
					.getBsUserApplyInfo(oldLoginId);
			if (!"通过".equals(auditResult)) {
				bsUserApplyInfo.setId(loginId);
				bsUserApplyInfo.setName(name);
				bsUserApplyInfo.setIsPcbUser(isPcbUser);
				bsUserApplyInfo.setCardType(cardType);
				bsUserApplyInfo.setJgPrincipal(userPrincipalsNames);
				bsUserApplyInfo.setEducation(education);
				bsUserApplyInfo.setOrgCodeOfJr(organization);
				bsUserApplyInfo.setOrgCodeOfXy(orgCodeOfXy);
				bsUserApplyInfo.setOrgCodeOfZz(orgCodeOfZz);
				bsUserApplyInfo.setOrgName(organizationChoice);
				bsUserApplyInfo.setDeptNo(deptNo);
				bsUserApplyInfo.setDeptName(bsDept.getName());
				bsUserApplyInfo.setPhone(phone);
				bsUserApplyInfo.setUserType(userType);
				bsUserApplyInfo.setFlag("0");// 审核驳回到申请
				message = this.createUserApply(bsUserApplyInfo);
				remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
						+ "】 用户退回用户申请【"+message+"】", "", "", "HTTP",nowLoginUserName);
			} else {
				Date date = new Date();
				String id = UUID.randomUUID().toString().replace("-", "");
				BsUserInfoOfJg bsUserInfoOfJg = new BsUserInfoOfJg();
				bsUserInfoOfJg.setId(id);
				bsUserInfoOfJg.setLoginId(loginId);
				bsUserInfoOfJg.setName(name);
				bsUserInfoOfJg.setIsPcbUser(isPcbUser);
				bsUserInfoOfJg.setCardType(cardType);
				bsUserInfoOfJg.setJgPrincipal(userPrincipalsNames);
				bsUserInfoOfJg.setEducation(education);
				bsUserInfoOfJg.setOrgCodeOfJr(organization);
				bsUserInfoOfJg.setOrgCodeOfXy(orgCodeOfXy);
				bsUserInfoOfJg.setOrgCodeOfZz(orgCodeOfZz);
				bsUserInfoOfJg.setOrgName(organizationChoice);
				bsUserInfoOfJg.setDeptNo(deptNo);
				bsUserInfoOfJg.setDeptName(bsDept.getName());
				bsUserInfoOfJg.setPhone(phone);
				bsUserInfoOfJg.setStoperCode("");
				bsUserInfoOfJg.setStoperName("");
				bsUserInfoOfJg.setUserStatus("启用");
				bsUserInfoOfJg.setNowUser("1");
				bsUserInfoOfJg.setUserType(userType);
				bsUserInfoOfJg.setApplyer(bsUserApplyInfo.getApplyName());
				bsUserInfoOfJg.setCreaterCode(nowLoginUserId);
				bsUserInfoOfJg.setCreaterName(nowLoginUserName);
				bsUserInfoOfJg.setManagerCode(nowLoginUserId);
				bsUserInfoOfJg.setCreateDate(date);
				message = this.createUser(bsUserInfoOfJg, am);
				String orgName = "";
				String userZxName = "";
				String zxUserType = "";
				String zxXtName = "";
				String zxXtUserType = "";
				String[] zxPrincipals = request.getParameterValues("zxPrincipal");
				String zxPrincipal = "";
				if (zxPrincipals != null && zxPrincipals.length>0) {
					for (int i = 0; i < zxPrincipals.length; i++) {
						String str = zxPrincipals[i].trim().trim();
						String strName = super.getZXPriName(str);
						zxPrincipal = zxPrincipal + strName + ",";
					}
				}
				String[] zxUserTypes = zxPrincipal.split(",");
				for(int i=0;i<zxUserTypes.length;i++){
					BsUserInfoOfZx bs = new BsUserInfoOfZx();
					zxUserType = zxUserTypes[i];
					bs.setUserId(id);
					bs.setLoginId(loginId);
					bs.setNowUser("1");
					bs.setCreateDate(date);
					bs.setZxUserType(zxUserType);
					bs.setUserStatus("启用");
					if("企业征信管理员用户".equals(zxUserType)){
						bs.setZxUserName(zxName1);
						bs.setOrgCode(zxOrg1);
						bs.setOrgName(zxOrg1Choice);
						orgName = zxOrg1Choice;
						userZxName = zxName1;
						zxXtName = "企业征信";
						zxXtUserType = "管理员用户";
						userService.savaBsUserInfoOfZx(bs);
					}
					if("企业征信查询用户".equals(zxUserType)){
						bs.setZxUserName(zxName2);
						bs.setOrgCode(zxOrg2);
						bs.setOrgName(zxOrg2Choice);
						orgName = zxOrg2Choice;
						userZxName = zxName2;
						zxXtName = "企业征信";
						zxXtUserType = "查询用户";
						userService.savaBsUserInfoOfZx(bs);
					}
					if("企业征信数据报送用户".equals(zxUserType)){
						bs.setZxUserName(zxName3);
						bs.setOrgCode(zxOrg3);
						bs.setOrgName(zxOrg3Choice);
						orgName = zxOrg3Choice;
						userZxName = zxName3;
						zxXtName = "企业征信";
						zxXtUserType = "数据报送用户";
						userService.savaBsUserInfoOfZx(bs);
					}
					if("企业征信异议处理用户".equals(zxUserType)){
						bs.setZxUserName(zxName4);
						bs.setOrgCode(zxOrg4);
						bs.setOrgName(zxOrg4Choice);
						orgName = zxOrg4Choice;
						userZxName = zxName4;
						zxXtName = "企业征信";
						zxXtUserType = "异议处理用户";
						userService.savaBsUserInfoOfZx(bs);
					}
					if("个人征信管理员用户".equals(zxUserType)){
						bs.setZxUserName(zxName5);
						bs.setOrgCode(zxOrg5);
						bs.setOrgName(zxOrg5Choice);
						orgName = zxOrg5Choice;
						userZxName = zxName5;
						zxXtName = "企业征信";
						zxXtUserType = "管理员用户";
						userService.savaBsUserInfoOfZx(bs);
					}
					if("个人征信查询用户".equals(zxUserType)){
						bs.setZxUserName(zxName6);
						bs.setOrgCode(zxOrg6);
						bs.setOrgName(zxOrg6Choice);
						orgName = zxOrg6Choice;
						userZxName = zxName6;
						zxXtName = "个人征信";
						zxXtUserType = "查询用户";
						userService.savaBsUserInfoOfZx(bs);
					}
					if("个人征信数据报送用户".equals(zxUserType)){
						bs.setZxUserName(zxName7);
						bs.setOrgCode(zxOrg7);
						bs.setOrgName(zxOrg7Choice);
						orgName = zxOrg7Choice;
						userZxName = zxName7;
						zxXtName = "个人征信";
						zxXtUserType = "数据报送用户";
						userService.savaBsUserInfoOfZx(bs);
					}
					if("个人征信异议处理用户".equals(zxUserType)){
						bs.setZxUserName(zxName8);
						bs.setOrgCode(zxOrg8);
						bs.setOrgName(zxOrg8Choice);
						orgName = zxOrg8Choice;
						userZxName = zxName8;
						zxXtName = "个人征信";
						zxXtUserType = "异议处理用户";
						userService.savaBsUserInfoOfZx(bs);
					}
					if("其他".equals(zxUserType)){
						bs.setZxUserName(zxName9);
						bs.setOrgCode(zxOrg9);
						bs.setOrgName(zxOrg9Choice);
						orgName = zxOrg9Choice;
						userZxName = zxName9;
						zxXtName = "其他";
						zxXtUserType = "其他";
						userService.savaBsUserInfoOfZx(bs);
					}
				}
				BsUserReportInfo bsUserReportInfo = new BsUserReportInfo();
				bsUserReportInfo.setUserZxName(userZxName);
				bsUserReportInfo.setUserZxOrgName(orgName);
				bsUserReportInfo.setZxXtName(zxXtName);
				bsUserReportInfo.setZxXtUserType(zxXtUserType);
				bsUserReportInfo.setSix("");
				bsUserReportInfo.setLoginId(loginId);
				bsUserReportInfo.setName(name);
				bsUserReportInfo.setOrgCodeOfJr(organization);
				bsUserReportInfo.setOrgCodeOfXy(orgCodeOfXy);
				bsUserReportInfo.setOrgCodeOfZz(orgCodeOfZz);
				bsUserReportInfo.setOrgName(organizationChoice);
				bsUserReportInfo.setDeptNo(deptNo);
				bsUserReportInfo.setDeptName(bsDept.getName());
				bsUserReportInfo.setPhone(phone);
				bsUserReportInfo.setReportDate(date);
				bsUserReportInfo.setReportType("创建用户");
				bsUserReportInfo.setCreaterName(nowLoginUserName);
				bsUserReportInfo.setApplyName(bsUserApplyInfo.getApplyName());
				bsUserReportInfo.setEducation(education);
				bsUserReportInfo.setC(bsOrg.getQ());
				bsUserReportInfo.setP(bsOrg.getO());
				userService.savaBsUserReportInfo(bsUserReportInfo);

				if ("操作成功!".equals(message)) {
					userService.delBsUserApplyInfo(bsUserApplyInfo);
				}
				remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
						+ "】 用户创建【"+name+"】【"+message+"】", "", "", "HTTP",nowLoginUserName);
			}
			request.setAttribute("message", message);
		} catch (Exception e) {
			request.setAttribute("message", "操作失败!");
		}
		return mapping.findForward("toUserMessgePage");
	}

	/**
	 * Read a user info
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward readUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		BsUserInfoOfJg bsUserInfoOfJg = new BsUserInfoOfJg();
		String nowLoginUserId = this.getPrivCredential(UserCredentialName.login
				.name(), request, response);
		if("".equals(id) || id==null) {
			bsUserInfoOfJg = userService.getBsUserInfoOfJgByLoginId(nowLoginUserId);
		}else{
			bsUserInfoOfJg = userService.getBsUserInfoOfJgById(id);
		}
		
		AuthenticationManager authenticationManager = (AuthenticationManager) request
				.getSession().getServletContext().getAttribute(SecurityConstants.AUTHENTICATION_MANAGER);
		String userPrin = bsUserInfoOfJg.getJgPrincipal();
		String[] userPrins = userPrin.split("-");
		List principalsList = new ArrayList();
		for (int i = 0; i < userPrins.length; i++) {
			principalsList.add(userPrins[i]);
		}
		Collection tempCol = new HashSet(authenticationManager
				.getLocalPrincipals());
		String zxStr = userService.getUserInfoTr(bsUserInfoOfJg,principalsList,tempCol);
		request.setAttribute("trStr", zxStr);
		return mapping.findForward("toUserInfoPage");
	}

//	/**
//	 * Read a user info
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	public ActionForward addUserPage(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		DynaActionForm dyna = (DynaActionForm) form;
//		String id = request.getParameter("id");
//		BsUserInfo bsUserInfo = userService.getBsUserInfoById(id);
//		AuthenticationManager authenticationManager = (AuthenticationManager) request
//				.getSession().getServletContext().getAttribute(SecurityConstants.AUTHENTICATION_MANAGER);
//		String userPrin = bsUserInfo.getJgPrincipal();
//		String[] userPrins = userPrin.split("-");
//		List principalsList = new ArrayList();
//		for (int i = 0; i < userPrins.length; i++) {
//			principalsList.add(userPrins[i]);
//		}
//		Collection tempCol = new HashSet(authenticationManager
//				.getLocalPrincipals());
//		dyna.set("id", bsUserInfo.getId());
//		dyna.set("loginId", bsUserInfo.getLoginId());
//		String trStr = userService.getUserInfoTrForPlural(bsUserInfo,principalsList,tempCol);
//		request.setAttribute("trStr", trStr);
//		List useDeptList = systemBaseInfoManager.getDepByOrgNoForSelect("");
//		request.setAttribute("useDeptList", useDeptList);
//		dyna.set("dept", "");
//		return mapping.findForward("toAddUserInfoPage");
//	}

//	/**
//	 * Go to the add User
//	 * 
//	 * @param mapping
//	 * @param form
//	 * @param request
//	 * @param response
//	 * @return
//	 */
//	public ActionForward addUser(ActionMapping mapping, ActionForm form,
//			HttpServletRequest request, HttpServletResponse response) {
//		DynaActionForm dyna = (DynaActionForm) form;
//		String loginId = (dyna.get("loginId") == null || dyna.get("loginId")
//				.equals("")) ? "" : (String) dyna.get("loginId");
//		String id = (dyna.get("id") == null || dyna.get("id")
//				.equals("")) ? "" : (String) dyna.get("id");
//		request.setAttribute("methodname", "addUserPage&id=" + id + "");
//		String orgCodeOfXy = (dyna.get("orgCodeOfXy") == null || dyna.get(
//				"orgCodeOfXy").equals("")) ? "" : (String) dyna
//				.get("orgCodeOfXy");
//		String orgCodeOfZz = (dyna.get("orgCodeOfZz") == null || dyna.get(
//				"orgCodeOfZz").equals("")) ? "" : (String) dyna
//				.get("orgCodeOfZz");
//		String organizationChoice = (dyna.get("organizationChoice") == null || dyna
//				.get("organizationChoice").equals("")) ? "" : (String) dyna
//				.get("organizationChoice");
//		String deptNo = (dyna.get("dept") == null || dyna.get("dept")
//				.equals("")) ? "" : (String) dyna.get("dept");
//		BsDept bsDept = new BsDept();
//		if (!"0".equals(deptNo)) {
//			bsDept = systemBaseInfoManager.getDeptByNo(deptNo);
//		} else {
//			bsDept.setId("0");
//			bsDept.setName("无部门");
//		}
//		String message = "";
//		String[] zxPrincipals = request.getParameterValues("zxPrincipal");
//		if (zxPrincipals != null) {
//			for (int i = 0; i < zxPrincipals.length; i++) {
//				String str = zxPrincipals[i].trim().trim();
//				String zxName = (dyna.get(str) == null || dyna.get(str).equals(
//						"")) ? "" : (String) dyna.get(str);
//				String strName = super.getZXPriName(str);
//				BsUserPluralInfo bs = new BsUserPluralInfo();
//				bs.setUserId(id);
//				bs.setZxName(zxName);
//				bs.setLoginId(loginId);
//				bs.setZxPrincipal(strName);
//				bs.setOrgCodeOfXy(orgCodeOfXy);
//				bs.setOrgCodeOfZz(orgCodeOfZz);
//				bs.setOrgName(organizationChoice);
//				bs.setDeptNo(deptNo);
//				bs.setDeptName(bsDept.getName());
//				bs.setNowUser("1");
//				bs.setUserStatus("启用");
//				bs.setCreateDate(new Date());
//				try {
//					userService.savaBsUserPluralInfo(bs);
//					message = "操作成功!";
//				} catch (Exception e) {
//					message = "操作失败!";
//				}
//			}
//		}
//		request.setAttribute("message", message);
//		return mapping.findForward("toUserMessgePage");
//	}

	/**
	 * Go to the cancel User禁用兼任用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward disabledZxUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = (dyna.get("id") == null || dyna.get("id")
				.equals("")) ? "" : (String) dyna.get("id");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		request.setAttribute("methodname", "updateUserPage&id=" + id + "");
		String message = "";
		String zxUserId = request.getParameter("zxUserId");
		BsUserInfoOfZx bs = userService.getBsUserInfoOfZxById(zxUserId);
		try {
			bs.setUserStatus("停用");
			bs.setStopDate(new Date());
			userService.savaBsUserInfoOfZx(bs);
			message = "操作成功!";
		} catch (Exception e) {
			message = "操作失败!";
		}
		remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
				+ "】 用户禁用征信系统用户【"+bs.getZxUserName()+"】【"+message+"】", "", "", "HTTP",nowLoginUserName);
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}

	/**
	 * cancel a user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward cancelZxUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = (dyna.get("id") == null || dyna.get("id")
				.equals("")) ? "" : (String) dyna.get("id");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		request.setAttribute("methodname", "updateUserPage&id=" + id + "");
		String message = "";
		String zxUserId = request.getParameter("zxUserId");
		BsUserInfoOfZx bs = userService.getBsUserInfoOfZxById(zxUserId);
		try {
			userService.cancelBsZxUserById(zxUserId);
			message = "操作成功!";
		} catch (Exception e) {
			message = "操作失败!";
		}
		remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
				+ "】 用户启用征信系统用户【"+bs.getZxUserName()+"】【"+message+"】", "", "", "HTTP",nowLoginUserName);
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}
	
	
	
	/**
	 * Go to the cancel User启用兼任用户
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward enabledZxUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = (dyna.get("id") == null || dyna.get("id")
				.equals("")) ? "" : (String) dyna.get("id");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		request.setAttribute("methodname", "updateUserPage&id=" + id + "");
		String message = "";
		String zxUserId = request.getParameter("zxUserId");
		BsUserInfoOfZx bs = userService.getBsUserInfoOfZxById(zxUserId);
		try {
			bs.setUserStatus("启用");
			bs.setStopDate(null);
			userService.savaBsUserInfoOfZx(bs);
			message = "操作成功!";
		} catch (Exception e) {
			message = "操作失败!";
		}
		remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
				+ "】 用户启用征信系统用户【"+bs.getZxUserName()+"】【"+message+"】", "", "", "HTTP",nowLoginUserName);
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}

	
	/**
	 * Go to the update user page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateUserPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = request.getParameter("id");
		BsUserInfoOfJg bsUserInfoOfJg = userService.getBsUserInfoOfJgById(id);
		dyna.set("id", id);
		dyna.set("loginId", bsUserInfoOfJg.getLoginId());
		dyna.set("name", String.valueOf(bsUserInfoOfJg.getName()));
		dyna.set("userType", String.valueOf(bsUserInfoOfJg.getUserType()));
		dyna.set("cardType", String.valueOf(bsUserInfoOfJg.getCardType()));
		dyna.set("education", String.valueOf(bsUserInfoOfJg.getEducation()));
		dyna.set("post", String.valueOf(bsUserInfoOfJg.getPost()));
		dyna.set("phone", String.valueOf(bsUserInfoOfJg.getPhone()));
		dyna.set("photo", String.valueOf(bsUserInfoOfJg.getPhoto()));
		dyna.set("organizationChoice", String.valueOf(bsUserInfoOfJg.getOrgName()));
		dyna.set("orgCodeOfZz", String.valueOf(bsUserInfoOfJg.getOrgCodeOfZz()));
		dyna.set("orgCodeOfXy", String.valueOf(bsUserInfoOfJg.getOrgCodeOfXy()));
		if ("禁用".equals(bsUserInfoOfJg.getUserStatus()) || "待启用".equals(bsUserInfoOfJg.getUserStatus())) {
			request.setAttribute("status", "1");
		}
		if ("启用".equals(bsUserInfoOfJg.getUserStatus()) || "待禁用".equals(bsUserInfoOfJg.getUserStatus())) {
			request.setAttribute("status", "2");
		}
		if(bsUserInfoOfJg.getPhoto()!=null && !"".equals(bsUserInfoOfJg.getPhoto())){
			BsPhoto bsPhoto = new BsPhoto();
			bsPhoto = fileHandler.getBsPhoto(bsUserInfoOfJg.getPhoto());
			request.setAttribute("photoPath", bsPhoto.getPhotopath().toString());
		}else{
			request.setAttribute("photoPath", "");
		}
		String orgNo = bsUserInfoOfJg.getOrgCodeOfJr();
		dyna.set("organization", orgNo);
		String deptNo = bsUserInfoOfJg.getDeptNo();
		List useDeptList = systemBaseInfoManager.getDepByOrgNoForSelect(orgNo);
		request.setAttribute("useDeptList", useDeptList);
		dyna.set("dept", deptNo);
		String flag = "";
		if (bsUserInfoOfJg.getUserType().equals("内控监督员")) {
			flag = "0";
			dyna.set("userCode", bsUserInfoOfJg.getLoginId());
		} else {
			flag = "1";
			dyna.set("cardId", bsUserInfoOfJg.getLoginId());
		}
		request.setAttribute("flag", flag);
		String zxStr = userService.getUserZxInfoTr(id,"0");
		AuthenticationManager authenticationManager = (AuthenticationManager) request
				.getSession().getServletContext().getAttribute(
						SecurityConstants.AUTHENTICATION_MANAGER);
		String userPrin = bsUserInfoOfJg.getJgPrincipal();
		String[] userPrins = userPrin.split("-");
		List principalsList = new ArrayList();
		for (int i = 0; i < userPrins.length; i++) {
			principalsList.add(userPrins[i]);
		}
		dyna.set("principals", principalsList);
		Collection tempCol = new HashSet(authenticationManager
				.getLocalPrincipals());
		List returnList = new ArrayList();
		for (Iterator<?> it = tempCol.iterator(); it.hasNext();) {
			RolePrincipal principal = (RolePrincipal) it.next();
			returnList.add(principal);
		}
		PrincipalComparator comparator = new PrincipalComparator();
        Collections.sort(returnList, comparator);
		request.setAttribute("allPrincipals", returnList);
		request.setAttribute("principals", principalsList);
		request.setAttribute("zxStr", zxStr);
		return mapping.findForward("toUpdateUserPage");
	}

	/**
	 * Go to the update user page
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward updateUserPageOfNK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = request.getParameter("id");
		BsUserInfoOfJg bsUserInfoOfJg = userService.getBsUserInfoOfJgById(id);
		dyna.set("id", id);
		dyna.set("loginId", bsUserInfoOfJg.getLoginId());
		dyna.set("name", String.valueOf(bsUserInfoOfJg.getName()));
		dyna.set("userType", String.valueOf(bsUserInfoOfJg.getUserType()));
		dyna.set("cardType", String.valueOf(bsUserInfoOfJg.getCardType()));
		dyna.set("education", String.valueOf(bsUserInfoOfJg.getEducation()));
		dyna.set("post", String.valueOf(bsUserInfoOfJg.getPost()));
		dyna.set("phone", String.valueOf(bsUserInfoOfJg.getPhone()));
		dyna.set("photo", String.valueOf(bsUserInfoOfJg.getPhoto()));
		dyna.set("organizationChoice", String.valueOf(bsUserInfoOfJg.getOrgName()));
		dyna.set("orgCodeOfZz", String.valueOf(bsUserInfoOfJg.getOrgCodeOfZz()));
		dyna.set("orgCodeOfXy", String.valueOf(bsUserInfoOfJg.getOrgCodeOfXy()));
		if ("停用".equals(bsUserInfoOfJg.getUserStatus())) {
			request.setAttribute("status", "1");
		}
		if ("启用".equals(bsUserInfoOfJg.getUserStatus())) {
			request.setAttribute("status", "2");
		}
		if(bsUserInfoOfJg.getPhoto()!=null && !"".equals(bsUserInfoOfJg.getPhoto())){
			BsPhoto bsPhoto = new BsPhoto();
			bsPhoto = fileHandler.getBsPhoto(bsUserInfoOfJg.getPhoto());
			request.setAttribute("photoPath", bsPhoto.getPhotopath().toString());
		}else{
			request.setAttribute("photoPath", "");
		}
		String orgNo = bsUserInfoOfJg.getOrgCodeOfJr();
		dyna.set("organization", orgNo);
		String deptNo = bsUserInfoOfJg.getDeptNo();
		List useDeptList = systemBaseInfoManager.getDepByOrgNoForSelect(orgNo);
		request.setAttribute("useDeptList", useDeptList);
		dyna.set("dept", deptNo);
		String flag = "";
		if (bsUserInfoOfJg.getUserType().equals("内控监督员")) {
			flag = "0";
			dyna.set("userCode", bsUserInfoOfJg.getLoginId());
		} else {
			flag = "1";
			dyna.set("cardId", bsUserInfoOfJg.getLoginId());
		}
		request.setAttribute("flag", flag);
		String zxStr = userService.getUserZxInfoTr(id,"0");
		AuthenticationManager authenticationManager = (AuthenticationManager) request
				.getSession().getServletContext().getAttribute(
						SecurityConstants.AUTHENTICATION_MANAGER);
		String userPrin = bsUserInfoOfJg.getJgPrincipal();
		String[] userPrins = userPrin.split("-");
		List principalsList = new ArrayList();
		for (int i = 0; i < userPrins.length; i++) {
			principalsList.add(userPrins[i]);
		}
		dyna.set("principals", principalsList);
		Collection tempCol = new HashSet(authenticationManager
				.getLocalPrincipals());
		List returnList = new ArrayList();
		for (Iterator<?> it = tempCol.iterator(); it.hasNext();) {
			RolePrincipal principal = (RolePrincipal) it.next();
			returnList.add(principal);
		}
		PrincipalComparator comparator = new PrincipalComparator();
        Collections.sort(returnList, comparator);
		request.setAttribute("allPrincipals", returnList);
		request.setAttribute("principals", principalsList);
		request.setAttribute("zxStr", zxStr);
		return mapping.findForward("toUpdateUserPageOfNK");
	}

	
	
	/**
	 * Go to the createUserOfZX user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public ActionForward createUserOfZX(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "userList");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		String message = "";
		String orgNo ="";
		String id = (dyna.get("id") == null || dyna.get("id")
				.equals("")) ? "" : (String) dyna.get("id");
		String name = (dyna.get("name") == null || dyna.get("name").equals("")) ? ""
				: (String) dyna.get("name");
		String zxName1 = (dyna.get("zxName1") == null || dyna.get("zxName1")
				.equals("")) ? "" : (String) dyna.get("zxName1");
		String zxName2 = (dyna.get("zxName2") == null || dyna.get("zxName2")
				.equals("")) ? "" : (String) dyna.get("zxName2");
		String zxName3 = (dyna.get("zxName3") == null || dyna.get("zxName3")
				.equals("")) ? "" : (String) dyna.get("zxName3");
		String zxName4 = (dyna.get("zxName4") == null || dyna.get("zxName4")
				.equals("")) ? "" : (String) dyna.get("zxName4");
		String zxName5 = (dyna.get("zxName5") == null || dyna.get("zxName5")
				.equals("")) ? "" : (String) dyna.get("zxName5");
		String zxName6 = (dyna.get("zxName6") == null || dyna.get("zxName6")
				.equals("")) ? "" : (String) dyna.get("zxName6");
		String zxName7 = (dyna.get("zxName7") == null || dyna.get("zxName7")
				.equals("")) ? "" : (String) dyna.get("zxName7");
		String zxName8 = (dyna.get("zxName8") == null || dyna.get("zxName8")
				.equals("")) ? "" : (String) dyna.get("zxName8");
		String zxName9 = (dyna.get("zxName9") == null || dyna.get("zxName9")
				.equals("")) ? "" : (String) dyna.get("zxName9");
		String zxOrg1 = (dyna.get("zxOrg1") == null || dyna.get("zxOrg1")
				.equals("")) ? "" : (String) dyna.get("zxOrg1");
		String zxOrg2 = (dyna.get("zxOrg2") == null || dyna.get("zxOrg2")
				.equals("")) ? "" : (String) dyna.get("zxOrg2");
		String zxOrg3 = (dyna.get("zxOrg3") == null || dyna.get("zxOrg3")
				.equals("")) ? "" : (String) dyna.get("zxOrg3");
		String zxOrg4 = (dyna.get("zxOrg4") == null || dyna.get("zxOrg4")
				.equals("")) ? "" : (String) dyna.get("zxOrg4");
		String zxOrg5 = (dyna.get("zxOrg5") == null || dyna.get("zxOrg5")
				.equals("")) ? "" : (String) dyna.get("zxOrg5");
		String zxOrg6 = (dyna.get("zxOrg6") == null || dyna.get("zxOrg6")
				.equals("")) ? "" : (String) dyna.get("zxOrg6");
		String zxOrg7 = (dyna.get("zxOrg7") == null || dyna.get("zxOrg7")
				.equals("")) ? "" : (String) dyna.get("zxOrg7");
		String zxOrg8 = (dyna.get("zxOrg8") == null || dyna.get("zxOrg8")
				.equals("")) ? "" : (String) dyna.get("zxOrg8");
		String zxOrg9 = (dyna.get("zxOrg9") == null || dyna.get("zxOrg9")
				.equals("")) ? "" : (String) dyna.get("zxOrg9");
		String zxOrg1Choice = (dyna.get("zxOrg1Choice") == null || dyna.get("zxOrg1Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg1Choice");
		String zxOrg2Choice = (dyna.get("zxOrg2Choice") == null || dyna.get("zxOrg2Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg2Choice");
		String zxOrg3Choice = (dyna.get("zxOrg3Choice") == null || dyna.get("zxOrg3Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg3Choice");
		String zxOrg4Choice = (dyna.get("zxOrg4Choice") == null || dyna.get("zxOrg4Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg4Choice");
		String zxOrg5Choice = (dyna.get("zxOrg5Choice") == null || dyna.get("zxOrg5Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg5Choice");
		String zxOrg6Choice = (dyna.get("zxOrg6Choice") == null || dyna.get("zxOrg6Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg6Choice");
		String zxOrg7Choice = (dyna.get("zxOrg7Choice") == null || dyna.get("zxOrg7Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg7Choice");
		String zxOrg8Choice = (dyna.get("zxOrg8Choice") == null || dyna.get("zxOrg8Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg8Choice");
		String zxOrg9Choice = (dyna.get("zxOrg9Choice") == null || dyna.get("zxOrg9Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg9Choice");
		String education = (dyna.get("education") == null || dyna.get(
				"education").equals("")) ? "" : (String) dyna.get("education");
		String organization = (dyna.get("organization") == null || dyna.get(
				"organization").equals("")) ? "" : (String) dyna
				.get("organization");
		String orgCodeOfXy = (dyna.get("orgCodeOfXy") == null || dyna.get(
				"orgCodeOfXy").equals("")) ? "" : (String) dyna
				.get("orgCodeOfXy");
		String orgCodeOfZz = (dyna.get("orgCodeOfZz") == null || dyna.get(
				"orgCodeOfZz").equals("")) ? "" : (String) dyna
				.get("orgCodeOfZz");
		String organizationChoice = (dyna.get("organizationChoice") == null || dyna
				.get("organizationChoice").equals("")) ? "" : (String) dyna
				.get("organizationChoice");
		String phone = (dyna.get("phone") == null || dyna.get("phone").equals(
				"")) ? "" : (String) dyna.get("phone");
		String userType = (dyna.get("userType") == null || dyna.get("userType")
				.equals("")) ? "" : (String) dyna.get("userType");
		String deptNo = (dyna.get("dept") == null || dyna.get("dept")
				.equals("")) ? "" : (String) dyna.get("dept");
		String userInfoOfZxId ="";
		BsDept bsDept = new BsDept();
		if (!"0".equals(deptNo)) {
			bsDept = systemBaseInfoManager.getDeptByNo(deptNo);
		} else {
			bsDept.setId("0");
			bsDept.setName("无部门");
		}
		Date date = new Date();
		BsUserInfoOfJg bsUserInfoOfJg = new BsUserInfoOfJg();
		bsUserInfoOfJg = userService.getBsUserInfoOfJgById(id);
		try {
			bsUserInfoOfJg.setName(name);
			bsUserInfoOfJg.setEducation(education);
			bsUserInfoOfJg.setOrgCodeOfJr(organization);
			bsUserInfoOfJg.setOrgCodeOfXy(orgCodeOfXy);
			bsUserInfoOfJg.setOrgCodeOfZz(orgCodeOfZz);
			bsUserInfoOfJg.setOrgName(organizationChoice);
			bsUserInfoOfJg.setDeptNo(deptNo);
			bsUserInfoOfJg.setDeptName(bsDept.getName());
			bsUserInfoOfJg.setPhone(phone);
			bsUserInfoOfJg.setUserType(userType);
			String orgName = "";
			String userZxName = "";
			String zxUserType = "";
			String zxXtName = "";
			String zxXtUserType = "";
			String[] zxPrincipals = request.getParameterValues("zxPrincipal");
			String zxPrincipal = "";
			if (zxPrincipals != null && zxPrincipals.length>0) {
				for (int i = 0; i < zxPrincipals.length; i++) {
					String str = zxPrincipals[i].trim().trim();
					String strName = super.getZXPriName(str);
					zxPrincipal = zxPrincipal + strName + ",";
				}
			}
			if (zxPrincipals != null && zxPrincipals.length>0){
				String[] zxUserTypes = zxPrincipal.split(",");
				for(int i=0;i<zxUserTypes.length;i++){
					userInfoOfZxId = UUID.randomUUID().toString().replace("-", "");
					BsUserInfoOfZx bs = new BsUserInfoOfZx();
					zxUserType = zxUserTypes[i];
					bs.setId(userInfoOfZxId);
					bs.setUserId(id);
					bs.setLoginId(bsUserInfoOfJg.getLoginId());
					bs.setNowUser("1");
					bs.setCreateDate(date);
					bs.setZxUserType(zxUserType);
					bs.setUserStatus("启用");
					if("企业征信管理员用户".equals(zxUserType)){
						bs.setZxUserName(zxName1);
						bs.setOrgCode(zxOrg1);
						bs.setOrgName(zxOrg1Choice);
						userService.savaBsUserInfoOfZx(bs);
						orgNo = zxOrg1;
						orgName = zxOrg1Choice;
						zxXtName = "企业征信";
						zxXtUserType = "管理员用户";
						userZxName = zxName1;
					}
					if("企业征信查询用户".equals(zxUserType)){
						bs.setZxUserName(zxName2);
						bs.setOrgCode(zxOrg2);
						bs.setOrgName(zxOrg2Choice);
						userService.savaBsUserInfoOfZx(bs);
						orgNo = zxOrg2;
						orgName = zxOrg2Choice;
						zxXtName = "企业征信";
						zxXtUserType = "查询用户";
						userZxName = zxName2;
					}
					if("企业征信数据报送用户".equals(zxUserType)){
						bs.setZxUserName(zxName3);
						bs.setOrgCode(zxOrg3);
						bs.setOrgName(zxOrg3Choice);
						userService.savaBsUserInfoOfZx(bs);
						orgNo = zxOrg3;
						orgName = zxOrg3Choice;
						zxXtName = "企业征信";
						zxXtUserType = "数据报送用户";
						userZxName = zxName3;
					}
					if("企业征信异议处理用户".equals(zxUserType)){
						bs.setZxUserName(zxName4);
						bs.setOrgCode(zxOrg4);
						bs.setOrgName(zxOrg4Choice);
						userService.savaBsUserInfoOfZx(bs);
						orgNo = zxOrg4;
						orgName = zxOrg4Choice;
						zxXtName = "企业征信";
						zxXtUserType = "异议处理用户";
						userZxName = zxName4;
					}
					if("个人征信管理员用户".equals(zxUserType)){
						bs.setZxUserName(zxName5);
						bs.setOrgCode(zxOrg5);
						bs.setOrgName(zxOrg5Choice);
						userService.savaBsUserInfoOfZx(bs);
						orgNo = zxOrg5;
						orgName = zxOrg5Choice;
						zxXtName = "个人征信";
						zxXtUserType = "管理员用户";
						userZxName = zxName5;
					}
					if("个人征信查询用户".equals(zxUserType)){
						bs.setZxUserName(zxName6);
						bs.setOrgCode(zxOrg6);
						bs.setOrgName(zxOrg6Choice);
						userService.savaBsUserInfoOfZx(bs);
						orgNo = zxOrg6;
						orgName = zxOrg6Choice;
						zxXtName = "个人征信";
						zxXtUserType = "查询用户";
						userZxName = zxName6;
					}
					if("个人征信数据报送用户".equals(zxUserType)){
						bs.setZxUserName(zxName7);
						bs.setOrgCode(zxOrg7);
						bs.setOrgName(zxOrg7Choice);
						userService.savaBsUserInfoOfZx(bs);
						orgNo = zxOrg7;
						orgName = zxOrg7Choice;
						zxXtName = "个人征信";
						zxXtUserType = "数据报送用户";
						userZxName = zxName7;
					}
					if("个人征信异议处理用户".equals(zxUserType)){
						bs.setZxUserName(zxName8);
						bs.setOrgCode(zxOrg8);
						bs.setOrgName(zxOrg8Choice);
						userService.savaBsUserInfoOfZx(bs);
						orgNo = zxOrg8;
						orgName = zxOrg8Choice;
						zxXtName = "个人征信";
						zxXtUserType = "异议处理用户";
						userZxName = zxName8;
					}
					if("其他".equals(zxUserType)){
						bs.setZxUserName(zxName9);
						bs.setOrgCode(zxOrg9);
						bs.setOrgName(zxOrg9Choice);
						userService.savaBsUserInfoOfZx(bs);
						orgNo = zxOrg9;
						orgName = zxOrg9Choice;
						zxXtName = "其他";
						zxXtUserType = "其他";
						userZxName = zxName9;
					}
				}
			}
			BsUserReportInfo bsUserReportInfo = new BsUserReportInfo();
			BsOrg bsOrg = systemBaseInfoManager.getOrgByNo(orgNo);
			bsUserReportInfo.setUserZxName(userZxName);
			bsUserReportInfo.setUserZxOrgName(orgName);
			bsUserReportInfo.setZxXtName(zxXtName);
			bsUserReportInfo.setZxXtUserType(zxXtUserType);
			bsUserReportInfo.setSix("");
			bsUserReportInfo.setLoginId(bsUserInfoOfJg.getLoginId());
			bsUserReportInfo.setName(bsUserInfoOfJg.getName());
			bsUserReportInfo.setOrgCodeOfJr(orgNo);
			bsUserReportInfo.setOrgCodeOfXy(bsOrg.getOrgCodeOfXy());
			bsUserReportInfo.setOrgCodeOfZz(bsOrg.getOrgCodeOfZz());
			bsUserReportInfo.setOrgName(bsOrg.getName());
			bsUserReportInfo.setDeptNo(bsUserInfoOfJg.getDeptNo());
			bsUserReportInfo.setDeptName(bsUserInfoOfJg.getDeptName());
			bsUserReportInfo.setPhone(bsUserInfoOfJg.getPhone());
			bsUserReportInfo.setReportDate(new Date());
			bsUserReportInfo.setReportType("创建");
			bsUserReportInfo.setCreaterName(nowLoginUserName);
			bsUserReportInfo.setApplyName(bsUserInfoOfJg.getApplyer());
			bsUserReportInfo.setEducation(bsUserInfoOfJg.getEducation());
			bsUserReportInfo.setC(bsOrg.getQ());
			bsUserReportInfo.setP(bsOrg.getO());
			userService.savaBsUserReportInfo(bsUserReportInfo);
			// remindService.writeLog(date + "【" + loginerOrgName + "】下的 【"
			// + loginer + "】 用户修改【" + (String) dyna.get("name") + "】成功!",
			// "", "", "HTTP", loginer);
			message = "操作成功!";
		} catch (Exception e) {
			e.printStackTrace();
			// remindService.writeLog(date + "【" + loginerOrgName + "】下的 【"
			// + loginer + "】 用户修改【" + (String) dyna.get("name") + "】失败!",
			// "", "", "HTTP", loginer);
			this
					.printMessage(request, response, super
							.getResources(request).getMessage(
									(Locale) request.getSession().getAttribute(
											Globals.LOCALE_KEY),
									"user.action.message"), "error");
			return null;
		}
		remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
				+ "】 用户新增征信系统用户名【"+message+"】", "", "", "HTTP",nowLoginUserName);
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}
	
	
	/**
	 * Go to the update user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public ActionForward updateUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "userList");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		String message = "";
		String id = (dyna.get("id") == null || dyna.get("id")
				.equals("")) ? "" : (String) dyna.get("id");
		String loginId = (dyna.get("loginId") == null || dyna.get("loginId")
				.equals("")) ? "" : (String) dyna.get("loginId");
		String name = (dyna.get("name") == null || dyna.get("name").equals("")) ? ""
				: (String) dyna.get("name");
		String zxName1 = (dyna.get("zxName1") == null || dyna.get("zxName1")
				.equals("")) ? "" : (String) dyna.get("zxName1");
		String zxName2 = (dyna.get("zxName2") == null || dyna.get("zxName2")
				.equals("")) ? "" : (String) dyna.get("zxName2");
		String zxName3 = (dyna.get("zxName3") == null || dyna.get("zxName3")
				.equals("")) ? "" : (String) dyna.get("zxName3");
		String zxName4 = (dyna.get("zxName4") == null || dyna.get("zxName4")
				.equals("")) ? "" : (String) dyna.get("zxName4");
		String zxName5 = (dyna.get("zxName5") == null || dyna.get("zxName5")
				.equals("")) ? "" : (String) dyna.get("zxName5");
		String zxName6 = (dyna.get("zxName6") == null || dyna.get("zxName6")
				.equals("")) ? "" : (String) dyna.get("zxName6");
		String zxName7 = (dyna.get("zxName7") == null || dyna.get("zxName7")
				.equals("")) ? "" : (String) dyna.get("zxName7");
		String zxName8 = (dyna.get("zxName8") == null || dyna.get("zxName8")
				.equals("")) ? "" : (String) dyna.get("zxName8");
		String zxName9 = (dyna.get("zxName9") == null || dyna.get("zxName9")
				.equals("")) ? "" : (String) dyna.get("zxName9");
		String zxOrg1 = (dyna.get("zxOrg1") == null || dyna.get("zxOrg1")
				.equals("")) ? "" : (String) dyna.get("zxOrg1");
		String zxOrg2 = (dyna.get("zxOrg2") == null || dyna.get("zxOrg2")
				.equals("")) ? "" : (String) dyna.get("zxOrg2");
		String zxOrg3 = (dyna.get("zxOrg3") == null || dyna.get("zxOrg3")
				.equals("")) ? "" : (String) dyna.get("zxOrg3");
		String zxOrg4 = (dyna.get("zxOrg4") == null || dyna.get("zxOrg4")
				.equals("")) ? "" : (String) dyna.get("zxOrg4");
		String zxOrg5 = (dyna.get("zxOrg5") == null || dyna.get("zxOrg5")
				.equals("")) ? "" : (String) dyna.get("zxOrg5");
		String zxOrg6 = (dyna.get("zxOrg6") == null || dyna.get("zxOrg6")
				.equals("")) ? "" : (String) dyna.get("zxOrg6");
		String zxOrg7 = (dyna.get("zxOrg7") == null || dyna.get("zxOrg7")
				.equals("")) ? "" : (String) dyna.get("zxOrg7");
		String zxOrg8 = (dyna.get("zxOrg8") == null || dyna.get("zxOrg8")
				.equals("")) ? "" : (String) dyna.get("zxOrg8");
		String zxOrg9 = (dyna.get("zxOrg9") == null || dyna.get("zxOrg9")
				.equals("")) ? "" : (String) dyna.get("zxOrg9");
		String zxOrg1Choice = (dyna.get("zxOrg1Choice") == null || dyna.get("zxOrg1Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg1Choice");
		String zxOrg2Choice = (dyna.get("zxOrg2Choice") == null || dyna.get("zxOrg2Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg2Choice");
		String zxOrg3Choice = (dyna.get("zxOrg3Choice") == null || dyna.get("zxOrg3Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg3Choice");
		String zxOrg4Choice = (dyna.get("zxOrg4Choice") == null || dyna.get("zxOrg4Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg4Choice");
		String zxOrg5Choice = (dyna.get("zxOrg5Choice") == null || dyna.get("zxOrg5Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg5Choice");
		String zxOrg6Choice = (dyna.get("zxOrg6Choice") == null || dyna.get("zxOrg6Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg6Choice");
		String zxOrg7Choice = (dyna.get("zxOrg7Choice") == null || dyna.get("zxOrg7Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg7Choice");
		String zxOrg8Choice = (dyna.get("zxOrg8Choice") == null || dyna.get("zxOrg8Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg8Choice");
		String zxOrg9Choice = (dyna.get("zxOrg9Choice") == null || dyna.get("zxOrg9Choice")
				.equals("")) ? "" : (String) dyna.get("zxOrg9Choice");
		String education = (dyna.get("education") == null || dyna.get(
				"education").equals("")) ? "" : (String) dyna.get("education");
		String post = (dyna.get("post") == null || dyna.get("post").equals("")) ? ""
				: (String) dyna.get("post");
		String organization = (dyna.get("organization") == null || dyna.get(
				"organization").equals("")) ? "" : (String) dyna
				.get("organization");
		String orgCodeOfXy = (dyna.get("orgCodeOfXy") == null || dyna.get(
				"orgCodeOfXy").equals("")) ? "" : (String) dyna
				.get("orgCodeOfXy");
		String orgCodeOfZz = (dyna.get("orgCodeOfZz") == null || dyna.get(
				"orgCodeOfZz").equals("")) ? "" : (String) dyna
				.get("orgCodeOfZz");
		String organizationChoice = (dyna.get("organizationChoice") == null || dyna
				.get("organizationChoice").equals("")) ? "" : (String) dyna
				.get("organizationChoice");
		String phone = (dyna.get("phone") == null || dyna.get("phone").equals(
				"")) ? "" : (String) dyna.get("phone");
		String userType = (dyna.get("userType") == null || dyna.get("userType")
				.equals("")) ? "" : (String) dyna.get("userType");
		String deptNo = (dyna.get("dept") == null || dyna.get("dept")
				.equals("")) ? "" : (String) dyna.get("dept");
		String userPrincipalsNames = (dyna.get("userPrincipalsNames") == null || dyna
				.get("userPrincipalsNames").equals("")) ? "" : (String) dyna
				.get("userPrincipalsNames");
		userPrincipalsNames = userPrincipalsNames.substring(0,
				userPrincipalsNames.length() - 1);
		BsDept bsDept = new BsDept();
		if (!"0".equals(deptNo)) {
			bsDept = systemBaseInfoManager.getDeptByNo(deptNo);
		} else {
			bsDept.setId("0");
			bsDept.setName("无部门");
		}
		Date date = new Date();
		BsUserInfoOfJg bsUserInfoOfJg = new BsUserInfoOfJg();
		bsUserInfoOfJg = userService.getBsUserInfoOfJgById(id);
		try {
			AuthenticationManager am = (AuthenticationManager) request.getSession()
					.getServletContext().getAttribute(SecurityConstants.AUTHENTICATION_MANAGER);
			Subject user = new Subject();
			Set privCred = user.getPrivateCredentials();
			addCredential(privCred, UserCredentialName.login.name(), loginId);
			MD5 md5 = new MD5();
			addCredential(privCred, UserCredentialName.password.name(), md5
					.getMD5ofStr("111111").toLowerCase());
			// Public credentials
			Set publicCred = user.getPublicCredentials();
			addCredential(publicCred, UserCredentialName.nickname.name(), name);
			addCredential(publicCred, UserCredentialName.organization.name(),
					organization);// 保存用户的机构ID
			// add principals
			user.getPrincipals().clear();
			String principalsNames = userPrincipalsNames;
			Logger.debug("update user :principalsNames from form ="
					+ principalsNames);
			addPrincipals(principalsNames, user.getPrincipals(), am);
			// Set identity credential
			JGuardCredential jcred = new JGuardCredential();
			jcred.setId(UserCredentialName.login.name());
			jcred.setValue(loginId);
			am.updateUser(jcred, user);
			bsUserInfoOfJg.setName(name);
			bsUserInfoOfJg.setJgPrincipal(userPrincipalsNames);
			bsUserInfoOfJg.setEducation(education);
			bsUserInfoOfJg.setPost(post);
			bsUserInfoOfJg.setOrgCodeOfJr(organization);
			bsUserInfoOfJg.setOrgCodeOfXy(orgCodeOfXy);
			bsUserInfoOfJg.setOrgCodeOfZz(orgCodeOfZz);
			bsUserInfoOfJg.setOrgName(organizationChoice);
			bsUserInfoOfJg.setDeptNo(deptNo);
			bsUserInfoOfJg.setDeptName(bsDept.getName());
			bsUserInfoOfJg.setPhone(phone);
			bsUserInfoOfJg.setUserType(userType);
			userService.savaBsUserInfoOfJg(bsUserInfoOfJg);
			String[] zxPrincipals = request.getParameterValues("zxPrincipal");
			String zxPrincipal = "";
			if (zxPrincipals != null && zxPrincipals.length>0) {
				for (int i = 0; i < zxPrincipals.length; i++) {
					String str = zxPrincipals[i].trim().trim();
					String strName = super.getZXPriName(str);
					zxPrincipal = zxPrincipal + strName + ",";
				}
			}
			if (zxPrincipals != null && zxPrincipals.length>0){
				String[] zxUserTypes = zxPrincipal.split(",");
				for(int i=0;i<zxUserTypes.length;i++){
					BsUserInfoOfZx bs = new BsUserInfoOfZx();
					String zxUserType = zxUserTypes[i];
					bs.setUserId(id);
					bs.setLoginId(bsUserInfoOfJg.getLoginId());
					bs.setNowUser("1");
					bs.setCreateDate(date);
					bs.setZxUserType(zxUserType);
					bs.setUserStatus("启用");
					if("企业征信管理员用户".equals(zxUserType)){
						bs.setZxUserName(zxName1);
						bs.setOrgCode(zxOrg1);
						bs.setOrgName(zxOrg1Choice);
						userService.savaBsUserInfoOfZx(bs);
					}
					if("企业征信查询用户".equals(zxUserType)){
						bs.setZxUserName(zxName2);
						bs.setOrgCode(zxOrg2);
						bs.setOrgName(zxOrg2Choice);
						userService.savaBsUserInfoOfZx(bs);
					}
					if("企业征信数据报送用户".equals(zxUserType)){
						bs.setZxUserName(zxName3);
						bs.setOrgCode(zxOrg3);
						bs.setOrgName(zxOrg3Choice);
						userService.savaBsUserInfoOfZx(bs);
					}
					if("企业征信异议处理用户".equals(zxUserType)){
						bs.setZxUserName(zxName4);
						bs.setOrgCode(zxOrg4);
						bs.setOrgName(zxOrg4Choice);
						userService.savaBsUserInfoOfZx(bs);
					}
					if("个人征信管理员用户".equals(zxUserType)){
						bs.setZxUserName(zxName5);
						bs.setOrgCode(zxOrg5);
						bs.setOrgName(zxOrg5Choice);
						userService.savaBsUserInfoOfZx(bs);
					}
					if("个人征信查询用户".equals(zxUserType)){
						bs.setZxUserName(zxName6);
						bs.setOrgCode(zxOrg6);
						bs.setOrgName(zxOrg6Choice);
						userService.savaBsUserInfoOfZx(bs);
					}
					if("个人征信数据报送用户".equals(zxUserType)){
						bs.setZxUserName(zxName7);
						bs.setOrgCode(zxOrg7);
						bs.setOrgName(zxOrg7Choice);
						userService.savaBsUserInfoOfZx(bs);
					}
					if("个人征信异议处理用户".equals(zxUserType)){
						bs.setZxUserName(zxName8);
						bs.setOrgCode(zxOrg8);
						bs.setOrgName(zxOrg8Choice);
						userService.savaBsUserInfoOfZx(bs);
					}
					if("其他".equals(zxUserType)){
						bs.setZxUserName(zxName9);
						bs.setOrgCode(zxOrg9);
						bs.setOrgName(zxOrg9Choice);
						userService.savaBsUserInfoOfZx(bs);
					}
				}
			}
			// remindService.writeLog(date + "【" + loginerOrgName + "】下的 【"
			// + loginer + "】 用户修改【" + (String) dyna.get("name") + "】成功!",
			// "", "", "HTTP", loginer);
			message = "操作成功!";
		} catch (RegistrationException e) {
			e.printStackTrace();
			// remindService.writeLog(date + "【" + loginerOrgName + "】下的 【"
			// + loginer + "】 用户修改【" + (String) dyna.get("name") + "】失败!",
			// "", "", "HTTP", loginer);
			this
					.printMessage(request, response, super
							.getResources(request).getMessage(
									(Locale) request.getSession().getAttribute(
											Globals.LOCALE_KEY),
									"user.action.message"), "error");
			return null;
		} catch (AuthenticationException e) {
			// remindService.writeLog(date + "【" + loginerOrgName + "】下的 【"
			// + loginer + "】 用户修改【" + (String) dyna.get("name") + "】失败!",
			// "", "", "HTTP", loginer);
			message = "操作失败!";
			return null;
		}
		remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
				+ "】 用户修改监管系统用户【"+bsUserInfoOfJg.getName()+"】【"+message+"】", "", "", "HTTP",nowLoginUserName);
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}

	/**
	 * disable a user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward disableUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "userList");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		String message = "";
		String id = dyna.get("id").toString();// 被停用用户
		String loginId = this.getPrivCredential(
				UserCredentialName.login.name(), request, response);// 登录用户
		String loginName = this.getPubCredential(UserCredentialName.nickname
				.name(), request, response);
		BsUserInfoOfJg bsUserInfoOfJg = userService.getBsUserInfoOfJgById(id);
		Date date = new Date();
		bsUserInfoOfJg.setStopDate(date);
		bsUserInfoOfJg.setStoperCode(loginId);
		bsUserInfoOfJg.setStoperName(loginName);
		bsUserInfoOfJg.setUserStatus("禁用");
		AuthenticationManager am = (AuthenticationManager) request.getSession()
				.getServletContext().getAttribute(
						SecurityConstants.AUTHENTICATION_MANAGER);
		try {
			Subject user = am.findUser(bsUserInfoOfJg.getLoginId().toString());
			am.deleteUser(user);
			userService.savaBsUserInfoOfJg(bsUserInfoOfJg);
			userService.disableZxUserByUserId(id);
			message = "操作成功!";
			BsOrg bsOrg = systemBaseInfoManager.getOrgByNo(bsUserInfoOfJg.getOrgCodeOfJr());
			List<BsUserInfoOfZx>  list = userService.getUserZxInfo(id);
			for(int i=0;i<list.size();i++){
				BsUserReportInfo bsUserReportInfo = new BsUserReportInfo();
				BsUserInfoOfZx bs = list.get(i);
				String zxXtName = "";
				String zxXtUserType = "";
				String zxUserType = bs.getZxUserType();
				if(zxUserType.indexOf("企业")>=0){
					zxXtName = "企业征信";
				}else{
					zxXtName = "个人征信";
				}
				if(zxUserType.indexOf("管理")>=0){
					zxXtUserType = "管理员用户";
				}
				if(zxUserType.indexOf("数据报送用户")>=0){
					zxXtUserType = "数据报送用户";
				}
				if(zxUserType.indexOf("查询")>=0){
					zxXtUserType = "查询用户";
				}
				if(zxUserType.indexOf("异议处理用户")>=0){
					zxXtUserType = "异议处理用户";
				}
				bsUserReportInfo.setUserZxName(bs.getZxUserName());
				bsUserReportInfo.setUserZxOrgName(bs.getOrgName());
				bsUserReportInfo.setZxXtName(zxXtName);
				bsUserReportInfo.setZxXtUserType(zxXtUserType);
				bsUserReportInfo.setLoginId(bsUserInfoOfJg.getLoginId());
				bsUserReportInfo.setName(bsUserInfoOfJg.getName());
				bsUserReportInfo.setOrgCodeOfJr(bsUserInfoOfJg.getOrgCodeOfJr());
				bsUserReportInfo.setOrgCodeOfXy(bsUserInfoOfJg.getOrgCodeOfXy());
				bsUserReportInfo.setOrgCodeOfZz(bsUserInfoOfJg.getOrgCodeOfZz());
				bsUserReportInfo.setOrgName(bsUserInfoOfJg.getOrgName());
				bsUserReportInfo.setDeptNo(bsUserInfoOfJg.getDeptNo());
				bsUserReportInfo.setDeptName(bsUserInfoOfJg.getDeptName());
				bsUserReportInfo.setPhone(bsUserInfoOfJg.getPhone());
				bsUserReportInfo.setReportDate(new Date());
				bsUserReportInfo.setReportType("禁用用户");
				bsUserReportInfo.setCreaterName(nowLoginUserName);
				bsUserReportInfo.setApplyName(bsUserInfoOfJg.getApplyer());
				bsUserReportInfo.setEducation(bsUserInfoOfJg.getEducation());
				bsUserReportInfo.setC(bsOrg.getQ());
				bsUserReportInfo.setP(bsOrg.getO());
				userService.savaBsUserReportInfo(bsUserReportInfo);
			}
		} catch (AuthenticationException e) {
			Logger.error(e.getMessage());
			message = "操作失败!";
		}
		remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
				+ "】 用户禁用监管系统用户【"+bsUserInfoOfJg.getName()+"】【"+message+"】", "", "", "HTTP",nowLoginUserName);
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}
	
	
	public ActionForward disableUserOfNK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "userList");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		String message = "";
		String id = dyna.get("id").toString();// 被停用用户
		BsUserInfoOfJg bsUserInfoOfJg = userService.getBsUserInfoOfJgById(id);
		bsUserInfoOfJg.setUserStatus("待禁用");
		bsUserInfoOfJg.setApplyer(nowLoginUserName);
		try {
			userService.savaBsUserInfoOfJg(bsUserInfoOfJg);
			userService.disableZxUserByUserId(id);
			message = "操作成功!";
		} catch (Exception e) {
			Logger.error(e.getMessage());
			message = "操作失败!";
		}
		remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
				+ "】 用户申请禁用监管系统用户【"+bsUserInfoOfJg.getName()+"】【"+message+"】", "", "", "HTTP",nowLoginUserName);
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}

	/**
	 * enable a user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward enableUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "userList");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		String message = "";
		String id = dyna.get("id").toString();// 被停用用户
		BsUserInfoOfJg bsUserInfoOfJg = userService.getBsUserInfoOfJgById(id);
		bsUserInfoOfJg.setNowUser("1");
		bsUserInfoOfJg.setUserStatus("启用");
		bsUserInfoOfJg.setStoperCode("");
		bsUserInfoOfJg.setStoperName("");
		bsUserInfoOfJg.setStopDate(null);
		AuthenticationManager am = (AuthenticationManager) request.getSession()
				.getServletContext().getAttribute(
						SecurityConstants.AUTHENTICATION_MANAGER);
		SubjectTemplate st = new SubjectTemplate();
		Set privRequiredCred = new HashSet();
		addCredential(privRequiredCred, UserCredentialName.login.name(), 
				bsUserInfoOfJg.getLoginId());
		MD5 md5 = new MD5();
		addCredential(privRequiredCred, UserCredentialName.password.name(), md5
				.getMD5ofStr("111111").toLowerCase());
		st.setPrivateRequiredCredentials(privRequiredCred);
		Set publicRequiredCred = new HashSet();
		addCredential(publicRequiredCred, UserCredentialName.nickname.name(),
				bsUserInfoOfJg.getName());
		addCredential(publicRequiredCred, UserCredentialName.organization
				.name(), bsUserInfoOfJg.getOrgCodeOfJr());// 保存用户的机构ID
		addCredential(publicRequiredCred, UserCredentialName.ispcbuser
				.name(), bsUserInfoOfJg.getIsPcbUser());// 保存用户的人民银行属性
		st.setPublicRequiredCredentials(publicRequiredCred);
		st.getPrincipals().clear();
		String principalsNames = bsUserInfoOfJg.getJgPrincipal();
		Logger.debug(" create user: principalsNames form =" + principalsNames);
		try {
			SubjectTemplate stClone = (SubjectTemplate) am
					.getDefaultSubjectTemplate().clone();
			stClone.getPrincipals().clear();
			addPrincipals(principalsNames, stClone.getPrincipals(), am);
			am.createUser(st, stClone);
			userService.savaBsUserInfoOfJg(bsUserInfoOfJg);
			userService.enableZxUserByUserId(id);
			message = "操作成功!";
			BsOrg bsOrg = systemBaseInfoManager.getOrgByNo(bsUserInfoOfJg.getOrgCodeOfJr());
			List<BsUserInfoOfZx>  list = userService.getUserZxInfo(id);
			for(int i=0;i<list.size();i++){
				BsUserReportInfo bsUserReportInfo = new BsUserReportInfo();
				BsUserInfoOfZx bs = list.get(i);
				String zxXtName = "";
				String zxXtUserType = "";
				String zxUserType = bs.getZxUserType();
				if(zxUserType.indexOf("企业")>=0){
					zxXtName = "企业征信";
				}else{
					zxXtName = "个人征信";
				}
				if(zxUserType.indexOf("管理")>=0){
					zxXtUserType = "管理员用户";
				}
				if(zxUserType.indexOf("数据报送用户")>=0){
					zxXtUserType = "数据报送用户";
				}
				if(zxUserType.indexOf("查询")>=0){
					zxXtUserType = "查询用户";
				}
				if(zxUserType.indexOf("异议处理用户")>=0){
					zxXtUserType = "异议处理用户";
				}
				bsUserReportInfo.setUserZxName(bs.getZxUserName());
				bsUserReportInfo.setUserZxOrgName(bs.getOrgName());
				bsUserReportInfo.setZxXtName(zxXtName);
				bsUserReportInfo.setZxXtUserType(zxXtUserType);
				bsUserReportInfo.setLoginId(bsUserInfoOfJg.getLoginId());
				bsUserReportInfo.setName(bsUserInfoOfJg.getName());
				bsUserReportInfo.setOrgCodeOfJr(bsUserInfoOfJg.getOrgCodeOfJr());
				bsUserReportInfo.setOrgCodeOfXy(bsUserInfoOfJg.getOrgCodeOfXy());
				bsUserReportInfo.setOrgCodeOfZz(bsUserInfoOfJg.getOrgCodeOfZz());
				bsUserReportInfo.setOrgName(bsUserInfoOfJg.getOrgName());
				bsUserReportInfo.setDeptNo(bsUserInfoOfJg.getDeptNo());
				bsUserReportInfo.setDeptName(bsUserInfoOfJg.getDeptName());
				bsUserReportInfo.setPhone(bsUserInfoOfJg.getPhone());
				bsUserReportInfo.setReportDate(new Date());
				bsUserReportInfo.setReportType("重启用户");
				bsUserReportInfo.setCreaterName(nowLoginUserName);
				bsUserReportInfo.setApplyName(bsUserInfoOfJg.getApplyer());
				bsUserReportInfo.setEducation(bsUserInfoOfJg.getEducation());
				bsUserReportInfo.setC(bsOrg.getQ());
				bsUserReportInfo.setP(bsOrg.getO());
				userService.savaBsUserReportInfo(bsUserReportInfo);
			}
		} catch (AuthenticationException e) {
			Logger.error(e.getMessage());
			message = "操作失败!";
		}
		remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
				+ "】 用户启用监管系统用户【"+bsUserInfoOfJg.getName()+"】【"+message+"】", "", "", "HTTP",nowLoginUserName);
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}
	
	
	/**
	 * enable a user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward enableUserOfNK(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "userList");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		String message = "";
		String id = dyna.get("id").toString();// 被停用用户
		BsUserInfoOfJg bsUserInfoOfJg = userService.getBsUserInfoOfJgById(id);
		bsUserInfoOfJg.setUserStatus("待启用");
		bsUserInfoOfJg.setApplyer(nowLoginUserName);
		try {
			userService.savaBsUserInfoOfJg(bsUserInfoOfJg);
			userService.enableZxUserByUserId(id);
			message = "操作成功!";
		} catch (Exception e) {
			Logger.error(e.getMessage());
			message = "操作失败!";
		}
		remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
				+ "】 用户申请启用监管系统用户【"+bsUserInfoOfJg.getName()+"】【"+message+"】", "", "", "HTTP",nowLoginUserName);
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}

	
	/**
	 * delete a user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public ActionForward deleteUser(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setAttribute("methodname", "userList");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		String message = "";
		String id = request.getParameter("id").toString();// 被删除用户
		BsUserInfoOfJg bsUserInfoOfJg = userService.getBsUserInfoOfJgById(id);
		if(!"禁用".equals(bsUserInfoOfJg.getUserStatus())){
			try {
				AuthenticationManager am = (AuthenticationManager) request.getSession().getServletContext().getAttribute(
						SecurityConstants.AUTHENTICATION_MANAGER);
				Subject user = am.findUser(bsUserInfoOfJg.getLoginId().toString());
				am.deleteUser(user);
				userService.deleteUser(bsUserInfoOfJg);
				message = "操作成功!";
			} catch (AuthenticationException e) {
				Logger.error(e.getMessage());
				message = "操作失败!";
			}
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户删除监管系统用户【"+bsUserInfoOfJg.getName()+"】【"+message+"】", "", "", "HTTP",nowLoginUserName);
		}else{
			message = "该用户已禁用,不能删除!";
		}
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}
	
	/**
	 * Read personalInfo
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward personalInfoManagerPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		BsUserInfoOfJg bsUserInfoOfJg = new BsUserInfoOfJg();
		String nowLoginUserId = this.getPrivCredential(UserCredentialName.login
				.name(), request, response);
		bsUserInfoOfJg = userService.getBsUserInfoOfJgByLoginId(nowLoginUserId);
		String id = "";
		id = bsUserInfoOfJg.getId();
		dyna.set("id", id);
		dyna.set("loginId", bsUserInfoOfJg.getLoginId());
		dyna.set("name", String.valueOf(bsUserInfoOfJg.getName()));
		dyna.set("userType", String.valueOf(bsUserInfoOfJg.getUserType()));
		dyna.set("cardType", String.valueOf(bsUserInfoOfJg.getCardType()));
		dyna.set("education", String.valueOf(bsUserInfoOfJg.getEducation()));
		dyna.set("post", String.valueOf(bsUserInfoOfJg.getPost()));
		dyna.set("phone", String.valueOf(bsUserInfoOfJg.getPhone()));
		dyna.set("photo", String.valueOf(bsUserInfoOfJg.getPhoto()));
		dyna.set("organizationChoice", String.valueOf(bsUserInfoOfJg.getOrgName()));
		dyna.set("orgCodeOfZz", String.valueOf(bsUserInfoOfJg.getOrgCodeOfZz()));
		dyna.set("orgCodeOfXy", String.valueOf(bsUserInfoOfJg.getOrgCodeOfXy()));
		if ("停用".equals(bsUserInfoOfJg.getUserStatus())) {
			request.setAttribute("status", "1");
		}
		if ("启用".equals(bsUserInfoOfJg.getUserStatus())) {
			request.setAttribute("status", "2");
		}
		if(bsUserInfoOfJg.getPhoto()!=null && !"".equals(bsUserInfoOfJg.getPhoto())){
			BsPhoto bsPhoto = new BsPhoto();
			bsPhoto = fileHandler.getBsPhoto(bsUserInfoOfJg.getPhoto());
			request.setAttribute("photoPath", bsPhoto.getPhotopath().toString());
		}else{
			request.setAttribute("photoPath", "");
		}
		String orgNo = bsUserInfoOfJg.getOrgCodeOfJr();
		dyna.set("organization", orgNo);
		String deptNo = bsUserInfoOfJg.getDeptNo();
		BsDept bsDept = systemBaseInfoManager.getDeptByNo(deptNo);
		List useDeptList = systemBaseInfoManager.getDepByOrgNoForSelect(orgNo);
		request.setAttribute("useDeptList", useDeptList);
		dyna.set("dept", bsDept.getName());
		String flag = "";
		if (bsUserInfoOfJg.getUserType().equals("内控监督员")) {
			flag = "0";
			dyna.set("userCode", bsUserInfoOfJg.getLoginId());
		} else {
			flag = "1";
			dyna.set("cardId", bsUserInfoOfJg.getLoginId());
		}
		request.setAttribute("flag", flag);
		String zxStr = userService.getUserZxInfoTr(id,"1");
		AuthenticationManager authenticationManager = (AuthenticationManager) request
				.getSession().getServletContext().getAttribute(
						SecurityConstants.AUTHENTICATION_MANAGER);
		String userPrin = bsUserInfoOfJg.getJgPrincipal();
		String[] userPrins = userPrin.split("-");
		List principalsList = new ArrayList();
		for (int i = 0; i < userPrins.length; i++) {
			principalsList.add(userPrins[i]);
		}
		dyna.set("principals", principalsList);
		Collection tempCol = new HashSet(authenticationManager
				.getLocalPrincipals());
		List returnList = new ArrayList();
		for (Iterator<?> it = tempCol.iterator(); it.hasNext();) {
			RolePrincipal principal = (RolePrincipal) it.next();
			returnList.add(principal);
		}
		PrincipalComparator comparator = new PrincipalComparator();
        Collections.sort(returnList, comparator);
		request.setAttribute("allPrincipals", returnList);
		request.setAttribute("principals", principalsList);
		request.setAttribute("zxStr", zxStr);
		return mapping.findForward("toPersonalInfoPage");
	}
	
	/**
	 * Go to the updatePersonalInfo
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public ActionForward personalInfoManager(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "personalInfoManagerPage");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		String message = "";
		String id = (dyna.get("id") == null || dyna.get("id")
				.equals("")) ? "" : (String) dyna.get("id");
		String photo = (dyna.get("photo") == null || dyna.get("photo").equals(
				"")) ? "" : (String) dyna.get("photo");
		FormFile photoFile = (FormFile) dyna.get("photopath");
		String photoUuid = "";
		if(photoFile.getFileSize()>0){
			if(photo ==null|| "".equals(photo)){
				photoUuid = fileHandler.saveBsPhoto(photoFile, request.getRealPath("/"));
			}else{
				photoUuid = fileHandler.updateBsPhoto(photoFile, request.getRealPath("/"), photo);
			}
		}else{
			photoUuid = photo;
		}
		BsUserInfoOfJg bsUserInfoOfJg = new BsUserInfoOfJg();
		bsUserInfoOfJg = userService.getBsUserInfoOfJgById(id);
		try {
			bsUserInfoOfJg.setPhoto(photoUuid);
			userService.savaBsUserInfoOfJg(bsUserInfoOfJg);
			message = "操作成功!";
		} catch (Exception e) {
			message = "操作失败!";
		}
		remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
				+ "】 用户上传照片【"+message+"】", "", "", "HTTP",nowLoginUserName);
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}
	
	public ActionForward setActiveOnRolePrincipal(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String roleName = request.getParameter("roleName");
		String applicationName = request.getParameter("applicationName");
		boolean active = Boolean.valueOf(request.getParameter("active"))
				.booleanValue();
		AuthenticationManager authenticationManager = (AuthenticationManager) request
				.getSession().getServletContext().getAttribute(
						SecurityConstants.AUTHENTICATION_MANAGER);
		HttpAuthenticationUtils auth = (HttpAuthenticationUtils) request
				.getSession(true).getAttribute(HttpConstants.AUTHN_UTILS);
		try {
			authenticationManager.setActiveOnRolePrincipal(auth.getSubject(),
					roleName, applicationName, active);
		} catch (AuthenticationException e) {
			Logger.error(e.getMessage());
		}
		return mapping.findForward("welcome");
	}

	/**
	 * userReportListOfPersonal all system user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward userReportListOfPersonal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String loginName = this.getPrivCredential(UserCredentialName.login
				.name(), request, response);
		String loginNickName = this.getPubCredential(UserCredentialName.nickname
				.name(), request, response);
		String loginOrg = this.getPubCredential(UserCredentialName.organization
				.name(), request, response);
		BsUserInfoOfJg bs = userService.getBsUserInfoOfJgByLoginId(loginName);
		String loginUserType = bs.getUserType();
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String userName = (dyna.get("name") == null || dyna.get("name")
				.equals("")) ? "" : (String) dyna.get("name");
		String loginId = (dyna.get("loginId") == null || dyna.get("loginId")
				.equals("")) ? "" : (String) dyna.get("loginId");
		String organization = (dyna.get("organization") == null || dyna.get("organization")
				.equals("")) ? "" : (String) dyna.get("organization");
		String reportType = (dyna.get("reportType") == null || dyna.get("reportType")
				.equals("")) ? "" : (String) dyna.get("reportType");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
				.get("pageSize");
		int pageCountTemp = 0;
		// 列表循环
		Page page = userService.getUserReportInfoList(loginName,loginNickName,loginOrg,loginUserType,userName,loginId,
				organization,reportType,Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		pageCountTemp = Long.valueOf(page.getTotalCount()).intValue();
		if (pageCountTemp == 0)
			pageCountTemp = 1;
		if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0)
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
		else
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
		/*
		 * 判断是否显示上一页或下一页
		 */
		if (Integer.valueOf(pageNo) != 1)
			dyna.set("previous", "1");
		else
			dyna.set("previous", "0");
		if (Integer.valueOf(pageNo) != pageCountTemp)
			dyna.set("next", "1");
		else
			dyna.set("next", "0");
		// 分页处理End

		// 再次将分页相关数据返回页面Start
		request.setAttribute("pageNo", pageNo);
		dyna.set("pageNo", pageNo);
		request.setAttribute("pageCount", pageCountTemp);
		dyna.set("pageCount", pageCountTemp);
		request.setAttribute("totalNo", page.getTotalCount());
		// 再次将分页相关数据返回页面End
		List userList = (List) page.getResult();
		request.setAttribute("userList", userList);
		return mapping.findForward("toUserReportOfPList");
	}
	
	/**
	 * readUserReportInfo a user info
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward readUserReportInfoOfP(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = request.getParameter("id");
		BsUserReportInfo bsUserReportInfo = userService.getBsUserReportInfoById(id);
		dyna.set("name", bsUserReportInfo.getName());
		dyna.set("loginId", bsUserReportInfo.getLoginId());
		dyna.set("phone", bsUserReportInfo.getPhone());
		dyna.set("orgName", bsUserReportInfo.getOrgName());
		dyna.set("deptName", bsUserReportInfo.getDeptName());
		dyna.set("reportType", bsUserReportInfo.getReportType());
		dyna.set("reportDate", bsUserReportInfo.getReportDate());
		dyna.set("adr", "");
		dyna.set("reporter", bsUserReportInfo.getCreaterName());
		dyna.set("applyName", bsUserReportInfo.getApplyName());
		dyna.set("userZxOrgName", bsUserReportInfo.getUserZxOrgName());
		dyna.set("userZxName", bsUserReportInfo.getUserZxName());
		dyna.set("zxXtName", bsUserReportInfo.getZxXtName());
		dyna.set("zxXtType", bsUserReportInfo.getZxXtUserType());
		return mapping.findForward("toUserReportInfoPageOfP");
	}
	
	/**
	 * userReportListOfTotal all system user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward userReportListOfTotal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String loginName = this.getPrivCredential(UserCredentialName.login
				.name(), request, response);
		String loginNickName = this.getPubCredential(UserCredentialName.nickname
				.name(), request, response);
		String loginOrg = this.getPubCredential(UserCredentialName.organization
				.name(), request, response);
		BsUserInfoOfJg bs = userService.getBsUserInfoOfJgByLoginId(loginName);
		String loginUserType = bs.getUserType();
//		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
//				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String begin = (dyna.get("begin") == null || dyna.get("begin").equals(
				"")) ? "" : (String) dyna.get("begin");
		String end = (dyna.get("end") == null || dyna.get("end").equals("")) ? ""
				: (String) dyna.get("end");
		String organization = (dyna.get("organization") == null || dyna.get("organization")
				.equals("")) ? "" : (String) dyna.get("organization");
		String reportType = (dyna.get("reportType") == null || dyna.get("reportType")
				.equals("")) ? "" : (String) dyna.get("reportType");
//		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
//				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
//				.get("pageSize");
//		int pageCountTemp = 0;
		// 列表循环
		List<BsUserReportInfo> list = userService.getBsUserReportInfoList(loginName,loginNickName,loginOrg,loginUserType,begin,end,
				organization,reportType);
//		Page page = userService.getUserReportInfoList(loginName,loginNickName,loginOrg,loginUserType,userName,loginId,
//				organization,reportType,Integer.parseInt(pageNo), Integer.parseInt(pageSize));
//		pageCountTemp = Long.valueOf(page.getTotalCount()).intValue();
//		if (pageCountTemp == 0)
//			pageCountTemp = 1;
//		if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0)
//			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
//		else
//			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
//		/*
//		 * 判断是否显示上一页或下一页
//		 */
//		if (Integer.valueOf(pageNo) != 1)
//			dyna.set("previous", "1");
//		else
//			dyna.set("previous", "0");
//		if (Integer.valueOf(pageNo) != pageCountTemp)
//			dyna.set("next", "1");
//		else
//			dyna.set("next", "0");
//		// 分页处理End
//
//		// 再次将分页相关数据返回页面Start
//		request.setAttribute("pageNo", pageNo);
//		dyna.set("pageNo", pageNo);
//		request.setAttribute("pageCount", pageCountTemp);
//		dyna.set("pageCount", pageCountTemp);
//		request.setAttribute("totalNo", page.getTotalCount());
//		// 再次将分页相关数据返回页面End
//		List userList = (List) page.getResult();
		request.setAttribute("userList", list);
		return mapping.findForward("toUserReportOfTList");
	}
	
	/**
	 * exportExcelOfTotal all system user
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void exportExcelOfTotal(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String loginName = this.getPrivCredential(UserCredentialName.login
				.name(), request, response);
		String loginNickName = this.getPubCredential(UserCredentialName.nickname
				.name(), request, response);
		String loginOrg = this.getPubCredential(UserCredentialName.organization
				.name(), request, response);
		BsUserInfoOfJg bs = userService.getBsUserInfoOfJgByLoginId(loginName);
		String loginUserType = bs.getUserType();
		String begin = (dyna.get("begin") == null || dyna.get("begin").equals(
				"")) ? "" : (String) dyna.get("begin");
		String end = (dyna.get("end") == null || dyna.get("end").equals("")) ? ""
				: (String) dyna.get("end");
		String organization = (dyna.get("organization") == null || dyna.get("organization")
				.equals("")) ? "" : (String) dyna.get("organization");
		String reportType = (dyna.get("reportType") == null || dyna.get("reportType")
				.equals("")) ? "" : (String) dyna.get("reportType");
		List<BsUserReportInfo> list = userService.getBsUserReportInfoList(loginName,loginNickName,loginOrg,loginUserType,begin,end,
				organization,reportType);
		Map<String, String> keyValueMap = new HashMap<String, String>();
		for(int i=1;i<=list.size();i++){
			BsUserReportInfo bsUserReportInfo = list.get(i-1);
			keyValueMap.put(i+"-1", String.valueOf(i));
			keyValueMap.put(i+"-2", bsUserReportInfo.getOrgCodeOfJr());
			keyValueMap.put(i+"-3", bsUserReportInfo.getOrgCodeOfXy());
			keyValueMap.put(i+"-4", bsUserReportInfo.getOrgName());
			keyValueMap.put(i+"-5", bsUserReportInfo.getDeptName());
			keyValueMap.put(i+"-6", bsUserReportInfo.getP());
			keyValueMap.put(i+"-7", bsUserReportInfo.getC());
			keyValueMap.put(i+"-8", bsUserReportInfo.getReportType());
			keyValueMap.put(i+"-9", bsUserReportInfo.getZxXtName());
			keyValueMap.put(i+"-10", bsUserReportInfo.getZxXtUserType());
			keyValueMap.put(i+"-11", bsUserReportInfo.getUserZxName());
			keyValueMap.put(i+"-12", bsUserReportInfo.getName());
			keyValueMap.put(i+"-13", bsUserReportInfo.getSix());
			keyValueMap.put(i+"-14", bsUserReportInfo.getLoginId());
			keyValueMap.put(i+"-15", bsUserReportInfo.getEducation());
			keyValueMap.put(i+"-16", bsUserReportInfo.getPhone());
			SimpleDateFormat sdf =   new SimpleDateFormat("yyyy-MM-dd");
			keyValueMap.put(i+"-17", sdf.format(bsUserReportInfo.getReportDate()));
		}
		HSSFWorkbook wb = null;
		wb = userService.generateExcel(keyValueMap);
		try {
			OutputStream repos = response.getOutputStream();
			try {
				String fileName = "金融信用信息基础数据库接入机构用户备案表（汇总）.xls";
				response.setContentType("application/vnd.ms-excel");
				response.addHeader("Content-Disposition",
						"attachment;filename="
								+ new String(fileName.getBytes("gb2312"),
										"ISO8859-1"));
				wb.write(repos);
				repos.flush();
				repos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 批量停用用户主页
	 */
	public ActionForward toUserStopExcelPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("toUserStopExcelPage");
	}
	
	/**
	 * 批量停用用户
	 */
	public ActionForward userStopExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String loginId = this.getPrivCredential(
				UserCredentialName.login.name(), request, response);// 登录用户
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "toUserStopExcelPage");
		String endRow = (dyna.get("endRow") == null || dyna.get("endRow").equals(
				"")) ? null : (String) dyna.get("endRow");
		FormFile excelpath = (FormFile) dyna.get("excelpath");
		String message = "";
		try {
			InputStream is = excelpath.getInputStream();
			userService.savaUserStopInfo(request,loginId,nowLoginUserName,is,endRow);
			message = "操作成功!";
		} catch (Exception e) {
			message = "操作失败!";
		}
		remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
				+ "】 用户批量停用用户【"+message+"】", "", "", "HTTP",nowLoginUserName);
		request.setAttribute("message", message);
		return mapping.findForward("toUserMessgePage");
	}
	
	/**
	 * 用户统计页面
	 */
	public ActionForward userStatisticsPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		return mapping.findForward("toUserStatisticsPage");
	}
	
	/**
	 * 用户统计
	 */
	public ActionForward userStatistics(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String statisticsWay = dyna.get("statisticsWay").toString();
		StringBuffer sb = new StringBuffer();
		if("1".equals(statisticsWay)){
			sb = userService.statisticsByArea();
		}else if("2".equals(statisticsWay)){
			sb = userService.statisticsByPrincipal();
		}else{
			sb = userService.statisticsByOrg();
//			sb.append("");
		}
		request.setAttribute("statisticsResult", sb.toString());
		return mapping.findForward("toUserStatisticsResultPage");
	}
	
	/**
	 * 用户统计
	 */
	public ActionForward userStatisticsByArea(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String areaNo = request.getParameter("flag");
		StringBuffer sb = new StringBuffer();
		sb = userService.statisticsByAreaNo(areaNo);
		request.setAttribute("statisticsResult", sb.toString());
		return mapping.findForward("toUserStatisticsResultPage");
	}
	
	/**
	 * 用户统计
	 */
	public ActionForward userStatisticsByOrg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String orgNo = request.getParameter("flag");
		StringBuffer sb = new StringBuffer();
		sb = userService.statisticsByParentOrgNo(orgNo);
		request.setAttribute("statisticsResult", sb.toString());
		return mapping.findForward("toUserStatisticsResultPage");
	}
	
	/**
	 * 查询用户条件设置主页
	 */
	public ActionForward userInfoQueryPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List principalList = userService.getPrincipalList();
		DynaActionForm dyna = (DynaActionForm) form;
		dyna.set("area", "");
		dyna.set("zxUserType", "");
		dyna.set("organization", "");
		dyna.set("userRole", "");
		request.setAttribute("principalList", principalList);
		return mapping.findForward("toUserInfoQueryPage");
	}
	
	/**
	 * 查询用户方法
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ActionForward userInfoQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
				.get("pageSize");
		String areaId = (dyna.get("area") == null || dyna.get("area")
				.equals("")) ? "" : (String) dyna.get("area");
		String zxUserType = (dyna.get("zxUserType") == null || dyna.get("zxUserType")
				.equals("")) ? "" : (String) dyna.get("zxUserType");
		String orgNo = (dyna.get("organization") == null || dyna.get("organization")
				.equals("")) ? "" : (String) dyna.get("organization");
		String jgUserRole = (dyna.get("userRole") == null || dyna.get("userRole")
				.equals("")) ? "" : (String) dyna.get("userRole");
		dyna.set("area", areaId);
		dyna.set("zxUserType", zxUserType);
		dyna.set("organization", orgNo);
		dyna.set("userRole", jgUserRole);
		int pageCountTemp = 0;
		// 列表循环
		Page page = null;
		if("".equals(jgUserRole)){
			page = userService.getUserInfoListPage(areaId,orgNo,zxUserType,Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		}else{
			page = userService.getUserInfoListPageOfJg(jgUserRole,Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		}
		pageCountTemp = Long.valueOf(page.getTotalCount()).intValue();
		if (pageCountTemp == 0)
			pageCountTemp = 1;
		if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0)
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
		else
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
		/*
		 * 判断是否显示上一页或下一页
		 */
		if (Integer.valueOf(pageNo) != 1)
			dyna.set("previous", "1");
		else
			dyna.set("previous", "0");
		if (Integer.valueOf(pageNo) != pageCountTemp)
			dyna.set("next", "1");
		else
			dyna.set("next", "0");
		// 分页处理End

		// 再次将分页相关数据返回页面Start
		request.setAttribute("pageNo", pageNo);
		dyna.set("pageNo", pageNo);
		request.setAttribute("pageCount", pageCountTemp);
		dyna.set("pageCount", pageCountTemp);
		request.setAttribute("totalNo", page.getTotalCount());
		// 再次将分页相关数据返回页面End
		List userList = (List) page.getResult();
		request.setAttribute("userList", userList);
		request.setAttribute("zxUserCount", String.valueOf(page.getTotalCount()));
		if("".equals(jgUserRole)){
			return mapping.findForward("toUserInfoQueryResultPage");
		}else{
			return mapping.findForward("toUserInfoQueryResultPageOfJg");
		}
		
	}
	
	/**
	 * 查询用户结果导出Excel
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void userInfoQueryResultDownloadExcel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String areaId = (dyna.get("area") == null || dyna.get("area")
				.equals("")) ? "" : (String) dyna.get("area");
		String zxUserType = (dyna.get("zxUserType") == null || dyna.get("zxUserType")
				.equals("")) ? "" : (String) dyna.get("zxUserType");
		String orgNo = (dyna.get("organization") == null || dyna.get("organization")
				.equals("")) ? "" : (String) dyna.get("organization");
		String jgUserRole = (dyna.get("userRole") == null || dyna.get("userRole")
				.equals("")) ? "" : (String) dyna.get("userRole");
		Map<String, String> keyValueMap = new HashMap<String, String>();
		String flag ="";
		if("".equals(jgUserRole)){
			flag = "0";
			List<BsUserInfoOfZx> list = userService.getUserInfoList(areaId,orgNo,zxUserType);
			for(int i=1;i<=list.size();i++){
				BsUserInfoOfZx bs = list.get(i-1);
				keyValueMap.put(i+"-1", String.valueOf(i));
				keyValueMap.put(i+"-2", bs.getZxUserName());
				keyValueMap.put(i+"-3", bs.getOrgName());
				keyValueMap.put(i+"-4", bs.getZxUserType());
				keyValueMap.put(i+"-5", bs.getLoginId());
			}
		}else{
			flag = "1";
			List<BsUserInfoOfJg> list = userService.getUserInfoListOfJg(jgUserRole);
			for(int i=1;i<=list.size();i++){
				BsUserInfoOfJg bs = list.get(i-1);
				keyValueMap.put(i+"-1", String.valueOf(i));
				keyValueMap.put(i+"-2", bs.getName());
				keyValueMap.put(i+"-3", bs.getOrgName());
				keyValueMap.put(i+"-4", bs.getLoginId());
			}
		}
		HSSFWorkbook wb = null;
		wb = userService.generateExcelOfUserInfoQueryResult(keyValueMap,flag);
		try {
			OutputStream repos = response.getOutputStream();
			try {
				String fileName = "金融信用信息基础数据库征信系统用户统计表.xls";
				if("1".equals(flag)){
					fileName = "金融信用信息基础数据库监管系统用户统计表.xls";
				}
				response.setContentType("application/vnd.ms-excel");
				response.addHeader("Content-Disposition",
						"attachment;filename="
								+ new String(fileName.getBytes("gb2312"),
										"ISO8859-1"));
				wb.write(repos);
				repos.flush();
				repos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 导入用户表彰信息主页
	 */
	public ActionForward toUploadUserComInfoPage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("toUploadUserComInfoPage");
	}
	
	/**
	 * 导入用户表彰信息
	 */
	public ActionForward uploadUserComInfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "toUploadUserComInfoPage");
		String endRow = (dyna.get("endRow") == null || dyna.get("endRow").equals(
				"")) ? null : (String) dyna.get("endRow");
		FormFile excelpath = (FormFile) dyna.get("excelpath");
		String message = "";
		try {
			InputStream is = excelpath.getInputStream();
			userService.savaUserComInfo(is,endRow);
			message = "操作成功!";
		} catch (Exception e) {
			message = "操作失败!";
		}
		remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
				+ "】 用户导入用户表彰信息【"+message+"】", "", "", "HTTP",nowLoginUserName);
		request.setAttribute("message", message);
		return mapping.findForward("toMessage");
	}
	
}
