package com.gtm.csims.controll.dailymgr;

import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sweet.dao.generic.support.Page;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.business.dailymgr.DailyManagementService;
import com.gtm.csims.business.enforce.EnforceService;
import com.gtm.csims.business.managment.system.SystemBaseInfoManager;
import com.gtm.csims.business.remind.RemindService;
import com.gtm.csims.business.serialnumber.NoGenerator;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.model.BsAdmenforce;
import com.gtm.csims.model.BsAeconclusion;
import com.gtm.csims.model.BsAeinspection;
import com.gtm.csims.model.BsAerectification;
import com.gtm.csims.model.BsBusieval;
import com.gtm.csims.model.BsBusievalInfo;
import com.gtm.csims.model.BsOffsitecheck;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsTraining;
import com.gtm.csims.model.BsWorkcheck;
import com.gtm.csims.utils.DateUtil;
import com.gtm.csims.utils.RequestUtil;

/**
 * @ClassName: ${AdministrationEnforceManagerAction}
 * @Description: ${行政执法管理Action}
 * @author qhy
 * @date ${date} ${time}
 * 
 */
@SuppressWarnings("unchecked")
public class DailyManagementAction extends BaseAction {
	private static final Integer PAGE_CONTAIN_NUMBER = 10;
	private DailyManagementService dailyManagementService;
	private NoGenerator noGenerator;
	private SystemBaseInfoManager systemBaseInfoManager;
	private RemindService remindService;

	public void setDailyManagementService(
			DailyManagementService dailyManagementService) {
		this.dailyManagementService = dailyManagementService;
	}

	public void setNoGenerator(NoGenerator noGenerator) {
		this.noGenerator = noGenerator;
	}
	public void setRemindService(RemindService remindService) {
		this.remindService = remindService;
	}

	public void setSystemBaseInfoManager(
			SystemBaseInfoManager systemBaseInfoManager) {
		this.systemBaseInfoManager = systemBaseInfoManager;
	}


	// /**
	// * @Description:下载检查方案
	// * @author qhy
	// */
	// public void downloadAeplan(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response) {
	// DynaActionForm dyna = (DynaActionForm) form;
	// String id = (dyna.get("id") == null || dyna.get("id").equals("")) ? null
	// : (String) dyna.get("id");
	// String aeplan = (dyna.get("aeplan") == null || dyna.get("aeplan")
	// .equals("")) ? null : (String) dyna.get("aeplan");
	// // 调用检查方案下载方法
	// }
	//
	// /**
	// * @Description:下载检查工作记录底稿
	// * @author qhy
	// */
	// public void downloadAebasis(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response) {
	// DynaActionForm dyna = (DynaActionForm) form;
	// String id = (dyna.get("id") == null || dyna.get("id").equals("")) ? null
	// : (String) dyna.get("id");
	// String aebasis = (dyna.get("aebasis") == null || dyna.get("aebasis")
	// .equals("")) ? null : (String) dyna.get("aebasis");
	// // 调用检查工作记录底稿下载方法
	// }
	//
	// /**
	// * @Description:下载询问笔录
	// * @author qhy
	// */
	// public void downloadEnquirercd(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response) {
	// DynaActionForm dyna = (DynaActionForm) form;
	// String id = (dyna.get("id") == null || dyna.get("id").equals("")) ? null
	// : (String) dyna.get("id");
	// String enquirercd = (dyna.get("enquirercd") == null || dyna.get(
	// "enquirercd").equals("")) ? null : (String) dyna
	// .get("enquirercd");
	// // 调用询问笔录下载方法
	// }
	//
	// /**
	// * @Description:下载进场记录
	// * @author qhy
	// */
	// public void downloadInrcd(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response) {
	// DynaActionForm dyna = (DynaActionForm) form;
	// String id = (dyna.get("id") == null || dyna.get("id").equals("")) ? null
	// : (String) dyna.get("id");
	// String inrcd = (dyna.get("inrcd") == null || dyna.get("inrcd").equals(
	// "")) ? null : (String) dyna.get("inrcd");
	// // 调用进场记录下载方法
	// }
	//
	// /**
	// * @Description:下载离场纪要
	// * @author qhy
	// */
	// public void downloadActualrcd(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response) {
	// DynaActionForm dyna = (DynaActionForm) form;
	// String id = (dyna.get("id") == null || dyna.get("id").equals("")) ? null
	// : (String) dyna.get("id");
	// String outrcd = (dyna.get("outrcd") == null || dyna.get("outrcd")
	// .equals("")) ? null : (String) dyna.get("outrcd");
	// // 调用离场纪要下载方法
	// }
	//
	// /**
	// * @Description:下载事实认定书
	// * @author qhy
	// */
	// public void downloadOutrcd(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response) {
	// DynaActionForm dyna = (DynaActionForm) form;
	// String id = (dyna.get("id") == null || dyna.get("id").equals("")) ? null
	// : (String) dyna.get("id");
	// String actualrcd = (dyna.get("actualrcd") == null || dyna.get(
	// "actualrcd").equals("")) ? null : (String) dyna
	// .get("actualrcd");
	// // 调用事实认定书下载方法
	// }
	//	
	// /**downloadAeopnionbook
	// * @Description:下载执法检查意见书
	// * @author qhy
	// */
	// public void downloadAeopnionbook(ActionMapping mapping, ActionForm form,
	// HttpServletRequest request, HttpServletResponse response) {
	// DynaActionForm dyna = (DynaActionForm) form;
	// String id = (dyna.get("id") == null || dyna.get("id").equals("")) ? null
	// : (String) dyna.get("id");
	// String aeopnionno = (dyna.get("aeopnionno") == null || dyna.get(
	// "aeopnionno").equals("")) ? null : (String) dyna
	// .get("aeopnionno");
	// // 调用下载执法检查意见书方法
	// }
	//
	// /**
	// * @Description:跳转行政执法信息Excel导入页面
	// * @author qhy
	// */
	// public ActionForward toAdminEnforceExcel(ActionMapping mapping,
	// ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) {
	// return mapping.findForward("toAdminEnforceExcel");
	// }
	//
	// /**
	// * @Description:行政执法信息Excel导入保存方法
	// * @author qhy
	// */
	// public ActionForward importAdminEnforceExcel(ActionMapping mapping,
	// ActionForm form, HttpServletRequest request,
	// HttpServletResponse response) {
	// request.setAttribute("methodname", "toAdminEnforceList");
	// // 调用行政执法excel保存方法
	// request.setAttribute("message", "操作成功!");
	// return mapping.findForward("toAdminEnforceMessage");
	// }

	/**
	 * @Description:跳转培训信息新增页面
	 * @author qhy
	 */
	public ActionForward toCreateTrainingPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
		dyna.set("tranorgnm", bsorg.getName().toString());
		dyna.set("tranorgno", bsorg.getNo().toString());
		return mapping.findForward("toCreateTrainingPage");
	}

	/**
	 * @Description:培训信息保存方法
	 * @author qhy
	 */
	public ActionForward createTraining(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "toTrainingList");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		// 调用行政执法保存方法
		try {
			BsTraining bs = RequestUtil.getBeanFromParams(request,
					BsTraining.class);
			// ae.setAeno(dyna.get("aetext").toString()
			// + dyna.get("aeyear").toString()
			// + dyna.get("aeindex").toString());
			bs.setTranterm(dyna.getString("hours") + "小时"
					+ dyna.getString("minute") + "分钟");
			bs.setTranstartdt(DateUtil.parseDate("yyyy-MM-dd", dyna
					.getString("transtartdt")));
			bs.setTranenddt(DateUtil.parseDate("yyyy-MM-dd", dyna
					.getString("tranenddt")));
			dailyManagementService.saveTraning(bs);
			// Calendar cal = Calendar.getInstance();
			// int year = cal.get(Calendar.YEAR);
			// noGenerator.updateNo(1, loginOrgNo, String.valueOf(year));
			request.setAttribute("message", "操作成功!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户保存培训信息【操作成功!】", "", "", "HTTP",nowLoginUserName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户保存培训信息登记【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		return mapping.findForward("toDailyMgrMessage");
	}

	/**
	 * @Description:跳转培训信息列表页面
	 * @author qhy
	 */
	public ActionForward toTrainingList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
				.get("pageSize");
		String trainingno = (dyna.get("trainingno") == null || dyna.get(
				"trainingno").equals("")) ? null : (String) dyna
				.get("trainingno");
		String tranorgno = (dyna.get("tranorgno") == null || dyna.get(
				"tranorgno").equals("")) ? null : (String) dyna
				.get("tranorgno");
		String transtartdt = (dyna.get("transtartdt") == null || dyna.get(
				"transtartdt").equals("")) ? null : (String) dyna
				.get("transtartdt");
		String tranenddt = (dyna.get("tranenddt") == null || dyna.get(
				"tranenddt").equals("")) ? null : (String) dyna
				.get("tranenddt");
		int pageCountTemp = 0;
		// 列表循环
		// 调用获取行政执法分页方法
		Page page = dailyManagementService.queryTraning(trainingno, tranorgno,
				transtartdt, tranenddt, Integer.parseInt(pageNo), Integer
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
		return mapping.findForward("toTrainingList");
	}

	/**
	 * @Description:跳转培训信息详细页面
	 * @author qhy
	 */
	public ActionForward toTrainingDetailPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = request.getParameter("id");
		BsTraining bs = dailyManagementService.getTraning(id);
		RequestUtil.setFormFromBean(dyna, bs);
		return mapping.findForward("toTrainingDetailPage");
	}

	/**
	 * @Description:跳转培训信息修改页面
	 * @author qhy
	 */
	public ActionForward toUpdateTrainingPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = request.getParameter("id");
		if ("".equals(id) || id == null) {
			id = dyna.get("id").toString();
		}
		BsTraining bs = dailyManagementService.getTraning(id);
		RequestUtil.setFormFromBean(dyna, bs);
		// dyna.set("aeorgChoice", bs.getAeorgnm());
		// dyna.set("aeorg", bs.getAeorgno());
		// dyna.set("aeedorgChoice", bs.getAeedorgnm());
		// dyna.set("aeedorg", bs.getAeedorgno());
		// String aeno = bs.getAeno();
		// dyna.set("aetext", aeno);
		// dyna.set("aeyear", aeno);
		return mapping.findForward("toUpdateTrainingPage");
	}

	/**
	 * @Description:培训信息修改
	 * @author qhy
	 */
	public ActionForward updateTraining(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		request.setAttribute("methodname", "toTrainingList");
		try {
			BsTraining bs = RequestUtil.getBeanFromParams(request,
					BsTraining.class);
			dailyManagementService.saveTraning(bs);
			request.setAttribute("message", "操作成功!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户修改培训信息【操作成功!】", "", "", "HTTP",nowLoginUserName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户修改培训信息【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		return mapping.findForward("toDailyMgrMessage");
	}

	/**
	 * @Description:培训信息删除
	 * @author qhy
	 */
	public ActionForward delTraining(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("methodname", "toTrainingList");
		String id = request.getParameter("id");
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		try {
			dailyManagementService.deleteTraningById(id);
			request.setAttribute("message", "操作成功!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户删除培训信息【操作成功!】", "", "", "HTTP",nowLoginUserName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户删除培训信息【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		return mapping.findForward("toDailyMgrMessage");
	}

	/**
	 * @Description:跳转工作检查信息列表页面
	 * @author qhy
	 */
	public ActionForward toWorkcheckList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
				.get("pageSize");
		String workchkno = (dyna.get("workchkno") == null || dyna.get(
				"workchkno").equals("")) ? null : (String) dyna
				.get("workchkno");
		String chkedorgno = (dyna.get("chkedorgno") == null || dyna.get(
				"chkedorgno").equals("")) ? null : (String) dyna
				.get("chkedorgno");
		String chkorgno = (dyna.get("chkorgno") == null || dyna.get("chkorgno")
				.equals("")) ? null : (String) dyna.get("chkorgno");
		String chkstartdt = (dyna.get("chkstartdt") == null || dyna.get(
				"chkstartdt").equals("")) ? null : (String) dyna
				.get("chkstartdt");
		String chkenddt = (dyna.get("chkenddt") == null || dyna.get("chkenddt")
				.equals("")) ? null : (String) dyna.get("chkenddt");
		int pageCountTemp = 0;
		// 列表循环
		// 调用获取行政执法分页方法
		Page page = dailyManagementService.queryWorkCheck(workchkno, chkorgno,
				chkedorgno, chkstartdt, chkenddt, Integer.parseInt(pageNo),
				Integer.parseInt(pageSize));
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
		return mapping.findForward("toWorkcheckList");
	}
	
	/**
	 * @Description
	 * @author qhy
	 */
	public ActionForward toWorkcheckDetailPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = request.getParameter("id");
		BsWorkcheck bs = dailyManagementService.getWorkCheck(id);
		RequestUtil.setFormFromBean(dyna, bs);
		return mapping.findForward("toWorkcheckDetailPage");
	}
	

	public ActionForward toCreateWorkcheckPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
		dyna.set("chkorgnm", bsorg.getName().toString());
		return mapping.findForward("toCreateWorkcheckPage");
	}

	/**
	 * @Description:工作检查信息保存方法
	 * @author qhy
	 */
	public ActionForward createWorkcheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		request.setAttribute("methodname", "toWorkcheckList");
		// 调用行政执法保存方法
		try {
			BsWorkcheck bs = RequestUtil.getBeanFromParams(request,
					BsWorkcheck.class);
			// ae.setAeno(dyna.get("aetext").toString()
			// + dyna.get("aeyear").toString()
			// + dyna.get("aeindex").toString());
			dailyManagementService.saveWorkCheck(bs);
			// Calendar cal = Calendar.getInstance();
			// int year = cal.get(Calendar.YEAR);
			// noGenerator.updateNo(1, loginOrgNo, String.valueOf(year));
			request.setAttribute("message", "操作成功!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户保存工作检查信息【操作成功!】", "", "", "HTTP",nowLoginUserName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户保存工作检查信息【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		return mapping.findForward("toDailyMgrMessage");
	}
	
	
	public ActionForward delWorkcheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		request.setAttribute("methodname", "toWorkcheckList");
		String id = request.getParameter("id");
		try {
			dailyManagementService.deleteWorkCheckById(id);
			request.setAttribute("message", "操作成功!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户删除工作检查信息【操作成功!】", "", "", "HTTP",nowLoginUserName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户删除工作检查信息【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		return mapping.findForward("toDailyMgrMessage");
	}
	
	
	/**
	 * @Description:跳转非现场检查信息列表页面
	 * @author qhy
	 */
	public ActionForward toOffsitecheckList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
				.get("pageSize");
		String workchkno = (dyna.get("workchkno") == null || dyna.get(
				"workchkno").equals("")) ? null : (String) dyna
				.get("workchkno");
		String chkedorgno = (dyna.get("chkedorgno") == null || dyna.get(
				"chkedorgno").equals("")) ? null : (String) dyna
				.get("chkedorgno");
		String chkorgno = (dyna.get("chkorgno") == null || dyna.get("chkorgno")
				.equals("")) ? null : (String) dyna.get("chkorgno");
		String chkstartdt = (dyna.get("chkstartdt") == null || dyna.get(
				"chkstartdt").equals("")) ? null : (String) dyna
				.get("chkstartdt");
		String chkenddt = (dyna.get("chkenddt") == null || dyna.get("chkenddt")
				.equals("")) ? null : (String) dyna.get("chkenddt");
		int pageCountTemp = 0;
		// 列表循环
		// 调用获取行政执法分页方法
		Page page = dailyManagementService.queryOffsiteCheck(workchkno, chkorgno,
				chkedorgno, chkstartdt, chkenddt, Integer.parseInt(pageNo),
				Integer.parseInt(pageSize));
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
		return mapping.findForward("toOffsitecheckList");
	}
	
	/**
	 * @Description:跳转行政执法信息详细页面
	 * @author qhy
	 */
	public ActionForward toOffsitecheckDetailPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = request.getParameter("id");
		BsOffsitecheck bs = dailyManagementService.getOffsiteCheck(id);
		RequestUtil.setFormFromBean(dyna, bs);
		return mapping.findForward("toOffsitecheckDetailPage");
	}
	

	public ActionForward toCreateOffsitecheckPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
		dyna.set("chkorgnm", bsorg.getName().toString());
		return mapping.findForward("toCreateOffsitecheckPage");
	}

	/**
	 * @Description:工作检查信息保存方法
	 * @author qhy
	 */
	public ActionForward createOffsitecheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		request.setAttribute("methodname", "toOffsitecheckList");
		// 调用行政执法保存方法
		try {
			BsOffsitecheck bs = RequestUtil.getBeanFromParams(request,
					BsOffsitecheck.class);
			// ae.setAeno(dyna.get("aetext").toString()
			// + dyna.get("aeyear").toString()
			// + dyna.get("aeindex").toString());
			dailyManagementService.saveOffsiteCheck(bs);
			// Calendar cal = Calendar.getInstance();
			// int year = cal.get(Calendar.YEAR);
			// noGenerator.updateNo(1, loginOrgNo, String.valueOf(year));
			request.setAttribute("message", "操作成功!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户保存非现场检查信息【操作成功!】", "", "", "HTTP",nowLoginUserName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户保存非现场检查信息【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		return mapping.findForward("toDailyMgrMessage");
	}
	
	
	public ActionForward delOffsitecheck(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		request.setAttribute("methodname", "toOffsitecheckList");
		String id = request.getParameter("id");
		try {
			dailyManagementService.deleteOffsiteCheckById(id);
			request.setAttribute("message", "操作成功!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户删除非现场检查信息【操作成功!】", "", "", "HTTP",nowLoginUserName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户删除非现场检查信息【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		return mapping.findForward("toDailyMgrMessage");
	}
	
	
	/**
	 * @Description:跳转业务评价信息列表页面
	 * @author qhy
	 */
	public ActionForward toBusievalList(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
				.get("pageSize");
		String busievalno = (dyna.get("busievalno") == null || dyna.get(
				"busievalno").equals("")) ? null : (String) dyna
				.get("busievalno");
		String evalorgno = (dyna.get("evalorgno") == null || dyna.get(
				"evalorgno").equals("")) ? null : (String) dyna
				.get("evalorgno");
		String evaledorgno = (dyna.get("evaledorgno") == null || dyna.get("evaledorgno")
				.equals("")) ? null : (String) dyna.get("evaledorgno");
		int pageCountTemp = 0;
		// 列表循环
		// 调用获取行政执法分页方法
		Page page = dailyManagementService.queryBusiEval(busievalno, evalorgno, evaledorgno, Integer.parseInt(pageNo),
				Integer.parseInt(pageSize));
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
		return mapping.findForward("toBusievalList");
	}
	
	/**
	 * @Description:跳转业务评价信息详细页面
	 * @author qhy
	 */
	public ActionForward toBusievalDetailPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = request.getParameter("id");
		if(id==null || "".equals(id)){
			id = dyna.getString("id");
		}
		BsBusieval bs = dailyManagementService.getBusiEval(id);
		dyna.set("busiEvalId", id);
		String tableString = dailyManagementService.getBusievalInfoString(bs);
//		String staResult = dailyManagementService.getBusievalStaResult(bs.getEvaledorgno());
		if("".equals(tableString)){
			tableString="无评价数据!";
		}
		request.setAttribute("tableString", tableString);
		RequestUtil.setFormFromBean(dyna, bs);
		return mapping.findForward("toBusievalDetailPage");
	}
	public ActionForward addEvalInfo(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String busiEvalId = dyna.getString("busiEvalId").toString();
		BsBusieval bs = dailyManagementService.getBusiEval(busiEvalId);
		BsBusievalInfo bsBusievalInfo = RequestUtil.getBeanFromParams(request,
				BsBusievalInfo.class);
		request.setAttribute("id", busiEvalId);
		dyna.set("id", busiEvalId);
		bsBusievalInfo.setBsBusieval(bs);
		try{
			dailyManagementService.saveBsBusiEvalInfo(bsBusievalInfo);
			request.setAttribute("message", "操作成功!");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
//			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
//					+ "】 用户保存业务评价信息【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		request.setAttribute("methodname", "toBusievalList");
		return mapping.findForward("toDailyMgrMessage");
	}
		
	public ActionForward toCreateBusievalPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
		dyna.set("evalorgnm", bsorg.getName());
		dyna.set("evalorgno", loginOrgNo);
		return mapping.findForward("toCreateBusievalPage");
	}

	/**
	 * @Description:业务评价信息保存方法
	 * @author qhy
	 */
	public ActionForward createBusieval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		String evaledorgno = (dyna.get("evaledorgno") == null || dyna.get(
				"evaledorgno").equals("")) ? null : (String) dyna
				.get("evaledorgno");
		String evaledorgnm = (dyna.get("evaledorgnm") == null || dyna.get(
				"evaledorgnm").equals("")) ? null : (String) dyna
				.get("evaledorgnm");
		String evaldt = (dyna.get("evaldt") == null || dyna.get(
				"evaldt").equals("")) ? null : (String) dyna
				.get("evaldt");
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		request.setAttribute("methodname", "toBusievalList");
		try {
			BsBusieval bsBusieval = RequestUtil.getBeanFromParams(request,
					BsBusieval.class);
			bsBusieval.setId(UUID.randomUUID().toString().replace("-",""));
			BsBusievalInfo bsBusievalInfo = RequestUtil.getBeanFromParams(request,
					BsBusievalInfo.class);
			// ae.setAeno(dyna.get("aetext").toString()
			// + dyna.get("aeyear").toString()
			// + dyna.get("aeindex").toString());
			bsBusievalInfo.setBsBusieval(bsBusieval);
			dailyManagementService.saveBsBusiEval(bsBusieval,bsBusievalInfo);
			// Calendar cal = Calendar.getInstance();
			// int year = cal.get(Calendar.YEAR);
			// noGenerator.updateNo(1, loginOrgNo, String.valueOf(year));
			request.setAttribute("message", "操作成功!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户保存业务评价信息【操作成功!】", "", "", "HTTP",nowLoginUserName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户保存业务评价信息【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		return mapping.findForward("toDailyMgrMessage");
	}
	
	
	public ActionForward delBusieval(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String nowLoginUserName = this.getPubCredential(
				UserCredentialName.nickname.name(), request, response);
		String nowloginerOrgNo = this.getPubCredential(
				UserCredentialName.organization.name(), request, response);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
		request.setAttribute("methodname", "toBusievalList");
		String id = request.getParameter("id");
		try {
			dailyManagementService.deleteBusiEvalById(id);
			request.setAttribute("message", "操作成功!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户删除业务评价信息【操作成功!】", "", "", "HTTP",nowLoginUserName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
					+ "】 用户删除业务评价信息【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		return mapping.findForward("toDailyMgrMessage");
	}
	
	public ActionForward toBusievalStaPage(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("toBusievalStaPage");
	}
	
	public ActionForward busievalSta(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String evaledorgnm = dyna.get("evaledorgnm").toString();
		String evaledorgno = dyna.get("evaledorgno").toString();
		dyna.set("evaledorgnm", evaledorgnm);
		String staResult = dailyManagementService.getBusievalStaResult(evaledorgno);
		if("".equals(staResult)){
			staResult="无统计数据";
		}
		request.setAttribute("staResult", staResult);
		return mapping.findForward("toBusievalStaResultPage");
	}
	
}
