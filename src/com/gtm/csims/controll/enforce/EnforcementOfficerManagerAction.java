package com.gtm.csims.controll.enforce;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sweet.dao.generic.support.Page;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.business.enforce.EnforcePeopleService;
import com.gtm.csims.business.managment.system.SystemBaseInfoManager;
import com.gtm.csims.business.remind.RemindService;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.jaas.service.UserService;
import com.gtm.csims.model.BsAepeople;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.utils.RequestUtil;

/**
 * @ClassName: ${EnforcementOfficerManagerAction}
 * @Description: ${执法人员管理Action}
 * @author qhy
 * @date ${date} ${time}
 * 
 */
@SuppressWarnings("unchecked")
public class EnforcementOfficerManagerAction extends BaseAction {
	private static final Integer PAGE_CONTAIN_NUMBER = 10;
	private EnforcePeopleService enforcePeopleService;
	private UserService userService;
	private SystemBaseInfoManager systemBaseInfoManager;
	private RemindService remindService;

	public void setSystemBaseInfoManager(SystemBaseInfoManager systemBaseInfoManager) {
		this.systemBaseInfoManager = systemBaseInfoManager;
	}

	public void setEnforcePeopleService(
			EnforcePeopleService enforcePeopleService) {
		this.enforcePeopleService = enforcePeopleService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setRemindService(RemindService remindService) {
		this.remindService = remindService;
	}

	@SuppressWarnings("unchecked")
	public void enOfficerTree(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String parent = request.getParameter("node");
		String orgNo = request.getParameter("orgNo");
		try {
			response.setCharacterEncoding("UTF-8");
			List list = new ArrayList();
			List<BsAepeople> result = enforcePeopleService.getAepeopleListByOrgNo(orgNo);
			for (int i = 0; i < result.size(); i++) {
				BsAepeople bs = result.get(i);
				JSONObject object = new JSONObject();
				object.put("id", bs.getCardid());
				object.put("text", bs.getPepname());
				object.put("singleClickExpand", "");
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
	 * @Description:跳转执法人员信息列表页面
	 * @author qhy
	 */
	public ActionForward toEnOfficerList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
				.get("pageSize");
		String pepname = (dyna.get("pepname") == null || dyna.get("pepname")
				.equals("")) ? null : (String) dyna.get("pepname");
		String certno = (dyna.get("certno") == null || dyna.get("certno")
				.equals("")) ? null : (String) dyna.get("certno");
		String orgno = (dyna.get("organization") == null || dyna.get("organization").equals(
				"")) ? null : (String) dyna.get("organization");
		String aeplanstdate = (dyna.get("aeplanstdate") == null || dyna.get(
				"aeplanstdate").equals("")) ? null : (String) dyna
				.get("aeplanstdate");
		String aeplantmdate = (dyna.get("aeplantmdate") == null || dyna.get(
				"aeplantmdate").equals("")) ? null : (String) dyna
				.get("aeplantmdate");
		int pageCountTemp = 0;
		// 调用获取执法检查人员分页方法
		Page page = enforcePeopleService.queryAepeople(certno, pepname, orgno,
				aeplanstdate, aeplantmdate, Integer.parseInt(pageNo), Integer
						.parseInt(pageSize));
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
		List list = (List) page.getResult();
		request.setAttribute("list", list);
		return mapping.findForward("toEnOfficerList");
	}

	/**
	 * @Description:跳转行政执法执法人员新增页面
	 * @author qhy
	 */
	public ActionForward toCreateEnOfficerPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		List useDeptList = systemBaseInfoManager.getDepByOrgNoForSelect("");
		request.setAttribute("useDeptList", useDeptList);
		dyna.set("dept", "");
		List pepList = userService.getPepByOrgNoandDeptNoForSelect("","");
		request.setAttribute("pepList", pepList);
		dyna.set("cardid", "");
		return mapping.findForward("toCreateEnOfficerPage");
	}

	/**
	 * @Description:执法人员保存方法
	 * @author qhy
	 */
	public ActionForward createEnOfficer(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		request.setAttribute("methodname", "toEnOfficerList");
		// 调用执法人员保存方法
		try {
			BsAepeople bsAepeople = RequestUtil.getBeanFromParams(request,
					BsAepeople.class);
			bsAepeople.setOrgnm(dyna.getString("organizationChoice"));
			bsAepeople.setOrgno(dyna.getString("organization"));
			String deptNo = dyna.getString("dept");
			bsAepeople.setDptno(deptNo);
			if("0".equals(deptNo)){
				bsAepeople.setDptnm("无部门");
			}else{
				String deptName = systemBaseInfoManager.getDeptByNo(deptNo).getName();
				bsAepeople.setDptnm(deptName);
			}
			bsAepeople.setPepname(dyna.getString("pepname"));
			bsAepeople.setTele(dyna.getString("tele"));
			enforcePeopleService.saveAepeople(bsAepeople);
			request.setAttribute("message", "操作成功!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户保存执法人员信息【操作成功!】", "", "", "HTTP",nowLoginUserName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户保存执法人员信息【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		return mapping.findForward("toEnOfficerMessage");
	}
	
	public ActionForward toEnOfficerDetailPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = request.getParameter("id");
		BsAepeople bsAepeople = enforcePeopleService.getAepeople(id);
		dyna.set("deptnm", bsAepeople.getDptnm());
		dyna.set("pepname", bsAepeople.getPepname());
		dyna.set("orgnm", bsAepeople.getOrgnm());
		dyna.set("sex", bsAepeople.getSex());
		dyna.set("education", bsAepeople.getEducation());
		dyna.set("prcipal", bsAepeople.getPrcipal());
		dyna.set("cardid", bsAepeople.getCardid());
		dyna.set("certno", bsAepeople.getCertno());
		dyna.set("id", id);
		return mapping.findForward("toEnOfficerDetailPage");
	}
	
	public ActionForward toUpdateEnOfficerPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = request.getParameter("id");
		BsAepeople bsAepeople = enforcePeopleService.getAepeople(id);
		String orgNo = bsAepeople.getOrgno();
		dyna.set("organization", orgNo);
		dyna.set("organizationChoice", bsAepeople.getOrgnm());
		String deptNo = bsAepeople.getDptno();
		dyna.set("dept", deptNo);
		List useDeptList = systemBaseInfoManager.getDepByOrgNoForSelect(orgNo);
		request.setAttribute("useDeptList", useDeptList);
		dyna.set("pepname", bsAepeople.getPepname());
		dyna.set("tele", bsAepeople.getTele());
		dyna.set("userid", bsAepeople.getCardid());
		dyna.set("prcipal", bsAepeople.getPrcipal());
		dyna.set("cardid", bsAepeople.getCardid());
		List pepList = userService.getPepByOrgNoandDeptNoForSelect(orgNo,deptNo);
		request.setAttribute("pepList", pepList);
		dyna.set("certno", bsAepeople.getCertno());
		dyna.set("sex", bsAepeople.getSex());
		dyna.set("education", bsAepeople.getEducation());
		dyna.set("id", id);
		return mapping.findForward("toCreateEnOfficerPage");
	}
	
	public ActionForward delEnOfficer(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		request.setAttribute("methodname", "toEnOfficerList");
		String id = request.getParameter("id");
		try{
			enforcePeopleService.deleteAepeopleById(id);
			request.setAttribute("message", "操作成功!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户删除执法人员信息【操作成功!】", "", "", "HTTP",nowLoginUserName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户删除执法人员信息【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		return mapping.findForward("toEnOfficerMessage");
	}
}
