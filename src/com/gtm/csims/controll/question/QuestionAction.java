package com.gtm.csims.controll.question;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sweet.dao.generic.support.Page;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.business.managment.system.SystemBaseInfoManager;
import com.gtm.csims.business.question.QuestionService;
import com.gtm.csims.business.question.QuestionStsicSvce;
import com.gtm.csims.business.remind.RemindService;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.model.BsAnswerresult;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsQuestion;
import com.gtm.csims.model.BsQuestionaire;
import com.gtm.csims.model.BsSurveyobject;

/**
 * 问卷调查.
 * 
 * @author yangyongzhi 
 * @version 1.5
 * @see java.lang.Class
 * @since JDK1.0
 */
public class QuestionAction extends BaseAction {

	private static final String[] DATE_FORMAT = new String[] { "yyyy-MM-dd HH:mm:ss" };

	private QuestionService questionService;

	private QuestionStsicSvce questionStsicSvce;

	private SystemBaseInfoManager systemBaseInfoManager;

	@SuppressWarnings("unused")
	private RemindService remindService;

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setSystemBaseInfoManager(SystemBaseInfoManager systemBaseInfoManager) {
		this.systemBaseInfoManager = systemBaseInfoManager;
	}

	public void setRemindService(RemindService remindService) {
		this.remindService = remindService;
	}

	public void setQuestionStsicSvce(QuestionStsicSvce questionStsicSvce) {
		this.questionStsicSvce = questionStsicSvce;
	}

	/**
	 * 跳转问卷调查列表页面.
	 */
	public ActionForward toQuestionairesList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals("")) ? "1" : (String) dyna
		        .get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals("")) ? PAGE_CONTAIN_NUMBER
		        .toString() : (String) dyna.get("pageSize");
		String qtitle = (dyna.get("qtitle") == null || dyna.get("qtitle").equals("")) ? "" : (String) dyna
		        .get("qtitle");
		int pageCountTemp = 0;
		Page page = questionService.queryQuestionairs(qtitle, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		pageCountTemp = Long.valueOf(page.getTotalCount()).intValue();
		if (pageCountTemp == 0) {
			pageCountTemp = 1;
		}
		if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0) {
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
		} else {
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
		}
		/*
		 * 判断是否显示上一页或下一页
		 */
		if (Integer.valueOf(pageNo) != 1) {
			dyna.set("previous", "1");
		} else {
			dyna.set("previous", "0");
		}
		if (Integer.valueOf(pageNo) != pageCountTemp) {
			dyna.set("next", "1");
		} else {
			dyna.set("next", "0");
		}
		// 分页处理End

		// 再次将分页相关数据返回页面Start
		request.setAttribute("pageNo", pageNo);
		dyna.set("pageNo", pageNo);
		request.setAttribute("pageCount", pageCountTemp);
		dyna.set("pageCount", pageCountTemp);
		request.setAttribute("totalNo", page.getTotalCount());
		// 再次将分页相关数据返回页面End
		request.setAttribute("list", page.getResult());
		dyna.set("qid", "");
		return mapping.findForward("toQuestionairesList");
	}

	public ActionForward toAnswerQuestionairesList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals("")) ? "1" : (String) dyna
		        .get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals("")) ? PAGE_CONTAIN_NUMBER
		        .toString() : (String) dyna.get("pageSize");
		String qtitle = (dyna.get("qtitle") == null || dyna.get("qtitle").equals("")) ? "" : (String) dyna
		        .get("qtitle");
		int pageCountTemp = 0;
		Page page = questionService.queryAnswerQuestionairs(qtitle, loginOrgNo, Integer.parseInt(pageNo),
		        Integer.parseInt(pageSize));
		pageCountTemp = Long.valueOf(page.getTotalCount()).intValue();
		if (pageCountTemp == 0) {
			pageCountTemp = 1;
		}
		if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0) {
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
		} else {
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
		}
		/*
		 * 判断是否显示上一页或下一页
		 */
		if (Integer.valueOf(pageNo) != 1) {
			dyna.set("previous", "1");
		} else {
			dyna.set("previous", "0");
		}
		if (Integer.valueOf(pageNo) != pageCountTemp) {
			dyna.set("next", "1");
		} else {
			dyna.set("next", "0");
		}
		// 分页处理End

		// 再次将分页相关数据返回页面Start
		request.setAttribute("pageNo", pageNo);
		dyna.set("pageNo", pageNo);
		request.setAttribute("pageCount", pageCountTemp);
		dyna.set("pageCount", pageCountTemp);
		request.setAttribute("totalNo", page.getTotalCount());
		// 再次将分页相关数据返回页面End
		request.setAttribute("list", page.getResult());
		dyna.set("qid", "");
		return mapping.findForward("toAnswerQuestionairesList");
	}

	/**
	 * 新增问卷调查页面.
	 */
	public ActionForward toCreateQuestionairesPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String id = (dyna.get("qid") == null || dyna.get("qid").equals("")) ? "" : (String) dyna.get("qid");
		if ("".equals(id)) {
			dyna.set("qid", "");
			dyna.set("qtitle", "");
			dyna.set("qenddatetime", "");
			dyna.set("qsumry", "");
			dyna.set("status", "0");
			request.setAttribute("status", "0");
		} else {
			BsQuestionaire bs = questionService.getBsQuestionaireById(id);
			dyna.set("qid", id);
			dyna.set("qtitle", bs.getQtitle().toString());
			dyna.set("qenddatetime", bs.getQenddatetime());
			dyna.set("qsumry", bs.getQsumry());
			dyna.set("status", bs.getStatus());
			request.setAttribute("status", bs.getStatus());
		}
		List<BsSurveyobject> list = questionService.getBsSurveyobjectByQuestionaireId(id);
		request.setAttribute("list", list);
		return mapping.findForward("toCreateQuestionairesPage");
	}

	public ActionForward createQuestionaire(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "toQuestionairesList");
		String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(), request, response);
		String nowLoginUserId = this.getPrivCredential(UserCredentialName.login.name(), request, response);
		String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
		// String cityId = systemBaseInfoManager.getCityIdByOrgNo(loginOrgNo);
		String cityId = "";
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
		String id = (dyna.get("qid") == null || dyna.get("qid").equals("")) ? "" : (String) dyna.get("qid");
		String qtitle = (dyna.get("qtitle") == null || dyna.get("qtitle").equals("")) ? "" : (String) dyna
		        .get("qtitle");
		String qenddatetime = (dyna.get("qenddatetime") == null || dyna.get("qenddatetime").equals("")) ? ""
		        : (String) dyna.get("qenddatetime");
		String orgStr = (dyna.get("org") == null || dyna.get("org").equals("")) ? "" : (String) dyna.get("org");
		String areaStr = (dyna.get("area") == null || dyna.get("area").equals("")) ? "" : (String) dyna.get("area");
		String qsumry = (dyna.get("qsumry") == null || dyna.get("qsumry").equals("")) ? "" : (String) dyna
		        .get("qsumry");
		try {
			BsQuestionaire bs = new BsQuestionaire();
			if (!"".equals(id)) {
				bs = questionService.getBsQuestionaireById(id);
			}
			Date date = new Date();
			bs.setId(UUID.randomUUID().toString().replace("-", ""));
			bs.setQtitle(qtitle.trim());
			bs.setQenddatetime(qenddatetime.trim());
			bs.setQsumry(qsumry.trim());
			bs.setCreatedate(date);
			bs.setQcreator(nowLoginUser);
			bs.setQcreatororgname(bsorg.getName());
			bs.setQcreatororgno(bsorg.getNo());
			bs.setStatus("0");// 状态：0未发布，1已发布，2完成

			List<BsOrg> listOrg = systemBaseInfoManager.getBsOrgList(orgStr.toString().trim(), areaStr.toString()
			        .trim());
			BsOrg bsOrg = new BsOrg();
			if (listOrg.size() > 0) {
				for (int i = 0; i < listOrg.size(); i++) {
					BsSurveyobject bss = new BsSurveyobject();
					bsOrg = listOrg.get(i);
					bss.setBsQuestionaire(bs);
					bss.setSoqorgno(bsOrg.getNo());
					bss.setSoqorgname(bsOrg.getName());
					bss.setProvince(bsOrg.getN());
					bss.setCity(bsOrg.getP() == null ? "" : bsOrg.getP());
					bss.setCountry(bsOrg.getQ() == null ? "" : bsOrg.getQ());
					questionService.savaBsSurveyobject(bss);
				}
			}
			questionService.saveBsQuestionaire(bs);
			request.setAttribute("message", "操作成功!");
		} catch (Exception e) {
			request.setAttribute("message", "操作失败!");
		}
		return mapping.findForward("toMessage");
	}

	public ActionForward toQuestionDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String qid = dyna.getString("qid");
		BsQuestionaire bs = questionService.getBsQuestionaireById(qid);
		List<BsQuestion> list = questionService.getBsQuestionByQuestionaireId(qid);
		request.setAttribute("list", list);
		request.setAttribute("qtitle", bs.getQtitle());
		dyna.set("qid", qid);
		request.setAttribute("status", bs.getStatus());
		return mapping.findForward("toQuestionDetailPage");
	}

	public ActionForward saveQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		// String nowLoginUser =
		// this.getPubCredential(UserCredentialName.nickname.name(), request,
		// response);
		// String nowLoginUserId =
		// this.getPrivCredential(UserCredentialName.login.name(), request,
		// response);
		// String loginOrgNo =
		// this.getPubCredential(UserCredentialName.organization.name(),
		// request, response);
		// String cityId = systemBaseInfoManager.getCityIdByOrgNo(loginOrgNo);
		// BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
		String qid = (dyna.get("qid") == null || dyna.get("qid").equals("")) ? "" : (String) dyna.get("qid");
		String qqid = (dyna.get("qqid") == null || dyna.get("qqid").equals("")) ? "" : (String) dyna.get("qqid");
		String qqtitle = (dyna.get("qqtitle") == null || dyna.get("qqtitle").equals("")) ? "" : (String) dyna
		        .get("qqtitle");
		String answera = (dyna.get("answera") == null || dyna.get("answera").equals("")) ? "" : (String) dyna
		        .get("answera");
		String answerb = (dyna.get("answerb") == null || dyna.get("answerb").equals("")) ? "" : (String) dyna
		        .get("answerb");
		String answerc = (dyna.get("answerc") == null || dyna.get("answerc").equals("")) ? "" : (String) dyna
		        .get("answerc");
		String answerd = (dyna.get("answerd") == null || dyna.get("answerd").equals("")) ? "" : (String) dyna
		        .get("answerd");
		String answere = (dyna.get("answere") == null || dyna.get("answere").equals("")) ? "" : (String) dyna
		        .get("answere");
		String answerf = (dyna.get("answerf") == null || dyna.get("answerf").equals("")) ? "" : (String) dyna
		        .get("answerf");
		String answerg = (dyna.get("answerg") == null || dyna.get("answerg").equals("")) ? "" : (String) dyna
		        .get("answerg");
		try {
			BsQuestion bs = new BsQuestion();
			if (StringUtils.isNotBlank(qqid)) {
				bs = questionService.getBsQuestionById(qqid);
			}
			Date date = new Date();
			bs.setQqtitle(qqtitle.trim());
			BsQuestionaire bsQuestionaire = questionService.getBsQuestionaireById(qid);
			bs.setBsQuestionaire(bsQuestionaire);
			bs.setAnswera(answera.trim());
			bs.setAnswerb(answerb.trim());
			bs.setAnswerc(answerc.trim());
			bs.setAnswerd(answerd.trim());
			bs.setAnswere(answere.trim());
			bs.setAnswerf(answerf.trim());
			bs.setAnswerg(answerg.trim());
			bs.setCreatedate(date);
			// bs.setQqindex();
			bs.setFlag(StringUtils.EMPTY);
			bs.setStatus(StringUtils.EMPTY);
			bs.setCreatedate(date);
			bs.setUpdateate(date);
			questionService.saveBsQuestion(bs);
			// remindService.writeLog(cityId, loginOrgNo, "【" + bsorg.getName()
			// + "】下的 【" + nowLoginUser
			// + "】 用户保存问卷调查问题信息【操作成功!】", "", "", "HTTP", nowLoginUser,
			// nowLoginUserId, bsorg.getOrgType());
		} catch (Exception e) {
			e.printStackTrace();
			// remindService.writeLog(cityId, loginOrgNo, "【" + bsorg.getName()
			// + "】下的 【" + nowLoginUser
			// + "】 用户保存问卷调查问题信息【操作失败!】", "", "", "HTTP", nowLoginUser,
			// nowLoginUserId, bsorg.getOrgType());
		}
		List<BsQuestion> list = questionService.getBsQuestionByQuestionaireId(qid);
		request.setAttribute("list", list);
		dyna.set("qid", qid);
		BsQuestionaire bsQ = questionService.getBsQuestionaireById(qid);
		request.setAttribute("qtitle", bsQ.getQtitle());
		request.setAttribute("status", bsQ.getStatus());
		return mapping.findForward("toQuestionDetailPage");
	}

	public ActionForward delQuestion(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		// String nowLoginUser =
		// this.getPubCredential(UserCredentialName.nickname.name(), request,
		// response);
		// String nowLoginUserId =
		// this.getPrivCredential(UserCredentialName.login.name(), request,
		// response);
		// String loginOrgNo =
		// this.getPubCredential(UserCredentialName.organization.name(),
		// request, response);
		// String cityId = systemBaseInfoManager.getCityIdByOrgNo(loginOrgNo);
		// BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
		String qid = (dyna.get("qid") == null || dyna.get("qid").equals("")) ? "" : (String) dyna.get("qid");
		String qqid = (dyna.get("qqid") == null || dyna.get("qqid").equals("")) ? "" : (String) dyna.get("qqid");
		try {
			BsQuestion bs = questionService.getBsQuestionById(qqid);
			questionService.delBsQuestion(bs);
			// remindService.writeLog(cityId, loginOrgNo, "【" + bsorg.getName()
			// + "】下的 【" + nowLoginUser
			// + "】 用户删除问卷调查问题信息【操作成功!】", "", "", "HTTP", nowLoginUser,
			// nowLoginUserId, bsorg.getOrgType());
		} catch (Exception e) {
			e.printStackTrace();
			// remindService.writeLog(cityId, loginOrgNo, "【" + bsorg.getName()
			// + "】下的 【" + nowLoginUser
			// + "】 用户删除问卷调查问题信息【操作失败!】", "", "", "HTTP", nowLoginUser,
			// nowLoginUserId, bsorg.getOrgType());
		}
		List<BsQuestion> list = questionService.getBsQuestionByQuestionaireId(qid);
		request.setAttribute("list", list);
		dyna.set("qid", qid);
		BsQuestionaire bsQ = questionService.getBsQuestionaireById(qid);
		request.setAttribute("qtitle", bsQ.getQtitle());
		request.setAttribute("status", bsQ.getStatus());
		return mapping.findForward("toQuestionDetailPage");
	}

	public ActionForward delBsSurveyobject(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(), request, response);
		String nowLoginUserId = this.getPrivCredential(UserCredentialName.login.name(), request, response);
		String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
		// String cityId = systemBaseInfoManager.getCityIdByOrgNo(loginOrgNo);
		String cityId = "";
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
		String qid = (dyna.get("qid") == null || dyna.get("qid").equals("")) ? "" : (String) dyna.get("qid");
		String sid = (dyna.get("sid") == null || dyna.get("sid").equals("")) ? "" : (String) dyna.get("sid");
		try {
			BsSurveyobject bs = questionService.getBsSurveyobjectById(sid);
			questionService.delBsSurveyobject(bs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		dyna.set("qid", qid);
		BsQuestionaire bs = questionService.getBsQuestionaireById(qid);
		dyna.set("qtitle", bs.getQtitle().toString());
		dyna.set("qenddatetime", bs.getQenddatetime());
		dyna.set("qsumry", bs.getQsumry());
		List<BsSurveyobject> list = questionService.getBsSurveyobjectByQuestionaireId(qid);
		request.setAttribute("list", list);
		request.setAttribute("status", "0");
		return mapping.findForward("toCreateQuestionairesPage");
	}

	public ActionForward changeQuestionairesStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		// String nowLoginUser =
		// this.getPubCredential(UserCredentialName.nickname.name(), request,
		// response);
		// String nowLoginUserId =
		// this.getPrivCredential(UserCredentialName.login.name(), request,
		// response);
		// String loginOrgNo =
		// this.getPubCredential(UserCredentialName.organization.name(),
		// request, response);
		// String cityId = systemBaseInfoManager.getCityIdByOrgNo(loginOrgNo);
		// BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
		String status = (dyna.get("status") == null || dyna.get("status").equals("")) ? "" : (String) dyna
		        .get("status");
		String qid = (dyna.get("qid") == null || dyna.get("qid").equals("")) ? "" : (String) dyna.get("qid");
		// String str = "";
		// if ("1".equals(status)) {
		// str = "发布";
		// } else {
		// str = "结束";
		// }
		try {
			BsQuestionaire bs = questionService.getBsQuestionaireById(qid);
			bs.setStatus(status.trim());
			questionService.updateBsQuestionaire(bs);
			// remindService.writeLog(cityId, loginOrgNo, "【" + bsorg.getName()
			// + "】下的 【" + nowLoginUser + "】 用户" + str
			// + "问卷调查【操作成功!】", "", "", "HTTP", nowLoginUser, nowLoginUserId,
			// bsorg.getOrgType());
		} catch (Exception e) {
			e.printStackTrace();
			// remindService.writeLog(cityId, loginOrgNo, "【" + bsorg.getName()
			// + "】下的 【" + nowLoginUser + "】 用户" + str
			// + "问卷调查【操作失败!】", "", "", "HTTP", nowLoginUser, nowLoginUserId,
			// bsorg.getOrgType());
		}
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals("")) ? "1" : (String) dyna
		        .get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals("")) ? PAGE_CONTAIN_NUMBER
		        .toString() : (String) dyna.get("pageSize");
		String qtitle = (dyna.get("qtitle") == null || dyna.get("qtitle").equals("")) ? "" : (String) dyna
		        .get("qtitle");
		int pageCountTemp = 0;
		Page page = questionService.queryQuestionairs(qtitle, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		pageCountTemp = Long.valueOf(page.getTotalCount()).intValue();
		if (pageCountTemp == 0) {
			pageCountTemp = 1;
		}
		if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0) {
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
		} else {
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
		}
		/*
		 * 判断是否显示上一页或下一页
		 */
		if (Integer.valueOf(pageNo) != 1) {
			dyna.set("previous", "1");
		} else {
			dyna.set("previous", "0");
		}
		if (Integer.valueOf(pageNo) != pageCountTemp) {
			dyna.set("next", "1");
		} else {
			dyna.set("next", "0");
		}
		// 分页处理End

		// 再次将分页相关数据返回页面Start
		request.setAttribute("pageNo", pageNo);
		dyna.set("pageNo", pageNo);
		request.setAttribute("pageCount", pageCountTemp);
		dyna.set("pageCount", pageCountTemp);
		request.setAttribute("totalNo", page.getTotalCount());
		// 再次将分页相关数据返回页面End
		request.setAttribute("list", page.getResult());
		dyna.set("qid", "");
		return mapping.findForward("toQuestionairesList");
	}

	public ActionForward delQuestionaires(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		// String nowLoginUser =
		// this.getPubCredential(UserCredentialName.nickname.name(), request,
		// response);
		// String nowLoginUserId =
		// this.getPrivCredential(UserCredentialName.login.name(), request,
		// response);
		// String loginOrgNo =
		// this.getPubCredential(UserCredentialName.organization.name(),
		// request, response);
		// String cityId = systemBaseInfoManager.getCityIdByOrgNo(loginOrgNo);
		// BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
		String qid = (dyna.get("qid") == null || dyna.get("qid").equals("")) ? "" : (String) dyna.get("qid");
		try {
			questionService.delBsQuestionaire(qid);
			// remindService.writeLog(cityId, loginOrgNo, "【" + bsorg.getName()
			// + "】下的 【" + nowLoginUser
			// + "】 用户删除问卷调查【操作成功!】", "", "", "HTTP", nowLoginUser,
			// nowLoginUserId, bsorg.getOrgType());
		} catch (Exception e) {
			e.printStackTrace();
			// remindService.writeLog(cityId, loginOrgNo, "【" + bsorg.getName()
			// + "】下的 【" + nowLoginUser
			// + "】 用户删除问卷调查【操作失败!】", "", "", "HTTP", nowLoginUser,
			// nowLoginUserId, bsorg.getOrgType());
		}
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals("")) ? "1" : (String) dyna
		        .get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals("")) ? PAGE_CONTAIN_NUMBER
		        .toString() : (String) dyna.get("pageSize");
		String qtitle = (dyna.get("qtitle") == null || dyna.get("qtitle").equals("")) ? "" : (String) dyna
		        .get("qtitle");
		int pageCountTemp = 0;
		Page page = questionService.queryQuestionairs(qtitle, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		pageCountTemp = Long.valueOf(page.getTotalCount()).intValue();
		if (pageCountTemp == 0) {
			pageCountTemp = 1;
		}
		if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0) {
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
		} else {
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
		}
		/*
		 * 判断是否显示上一页或下一页
		 */
		if (Integer.valueOf(pageNo) != 1) {
			dyna.set("previous", "1");
		} else {
			dyna.set("previous", "0");
		}
		if (Integer.valueOf(pageNo) != pageCountTemp) {
			dyna.set("next", "1");
		} else {
			dyna.set("next", "0");
		}
		// 分页处理End

		// 再次将分页相关数据返回页面Start
		request.setAttribute("pageNo", pageNo);
		dyna.set("pageNo", pageNo);
		request.setAttribute("pageCount", pageCountTemp);
		dyna.set("pageCount", pageCountTemp);
		request.setAttribute("totalNo", page.getTotalCount());
		// 再次将分页相关数据返回页面End
		request.setAttribute("list", page.getResult());
		dyna.set("qid", "");
		return mapping.findForward("toQuestionairesList");
	}

	public ActionForward toAnswerQuestionairesPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
		String qid = dyna.getString("qid");
		BsQuestionaire bs = questionService.getBsQuestionaireById(qid);
		String str = questionService.getBsQuestionStrByQuestionaireId(qid, bs.getQtitle().trim(), loginOrgNo);
		dyna.set("qid", qid);
		request.setAttribute("str", str);
		request.setAttribute("status", bs.getStatus().trim());
		return mapping.findForward("toAnswerQuestionairesPage");
	}

	public ActionForward savaAnserResult(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "toAnswerQuestionairesList");
		// String nowLoginUser =
		// this.getPubCredential(UserCredentialName.nickname.name(), request,
		// response);
		// String nowLoginUserId =
		// this.getPrivCredential(UserCredentialName.login.name(), request,
		// response);
		String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
		// String cityId = systemBaseInfoManager.getCityIdByOrgNo(loginOrgNo);
		BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
		String qid = (dyna.get("qid") == null || dyna.get("qid").equals("")) ? "" : (String) dyna.get("qid");
		String answerResult = (dyna.get("answerResult") == null || dyna.get("answerResult").equals("")) ? ""
		        : (String) dyna.get("answerResult");
		BsQuestionaire bsq = questionService.getBsQuestionaireById(qid);
		BsQuestion bsqq = new BsQuestion();
		try {
			questionService.delAnserResult(qid, loginOrgNo);
			String answerResults[] = answerResult.split(",");
			String stra = "";
			Date date = new Date();
			for (int i = 0; i < answerResults.length; i++) {
				BsAnswerresult bsa = new BsAnswerresult();
				bsa.setBsQuestionaire(bsq);
				stra = answerResults[i];
				bsqq = questionService.getBsQuestionById(stra.split("-")[0].trim());
				bsa.setBsQuestion(bsqq);
				bsa.setAnswerresult(stra.split("-")[1].trim());
				bsa.setArareano(bsorg.getP());
				bsa.setArarea(bsorg.getQ());
				bsa.setArorgno(loginOrgNo);
				bsa.setArorgname(bsorg.getName());
				// bsa.setArorgtypeno(bsorg.getOrgType() == null ? "" :
				// bsorg.getOrgType().toString());
				bsa.setArorgtypeno(StringUtils.EMPTY);
				bsa.setArorgtypename(StringUtils.EMPTY);
				bsa.setFlag(StringUtils.EMPTY);
				bsa.setStatus(StringUtils.EMPTY);
				bsa.setCreatedate(date);
				bsa.setUpdateate(date);
				questionService.savaBsAnserResult(bsa);
			}
			request.setAttribute("message", "操作成功!");
			// remindService.writeLog(cityId, loginOrgNo, "【" + bsorg.getName()
			// + "】下的 【" + nowLoginUser + "】 用户完成问卷调查【"
			// + bsq.getQtitle().toString().trim() + "】【操作成功!】", "", "", "HTTP",
			// nowLoginUser, nowLoginUserId,
			// bsorg.getOrgType());
		} catch (Exception e) {
			request.setAttribute("message", "操作失败!");
			// remindService.writeLog(cityId, loginOrgNo, "【" + bsorg.getName()
			// + "】下的 【" + nowLoginUser + "】 用户完成问卷调查【"
			// + bsq.getQtitle().toString().trim() + "】【操作失败!】", "", "", "HTTP",
			// nowLoginUser, nowLoginUserId,
			// bsorg.getOrgType());
		}
		return mapping.findForward("toMessage");
	}

	public ActionForward toAnswerResultList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals("")) ? "1" : (String) dyna
		        .get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals("")) ? PAGE_CONTAIN_NUMBER
		        .toString() : (String) dyna.get("pageSize");
		String qtitle = (dyna.get("qtitle") == null || dyna.get("qtitle").equals("")) ? "" : (String) dyna
		        .get("qtitle");
		int pageCountTemp = 0;
		Page page = questionService.queryQuestionairs(qtitle, Integer.parseInt(pageNo), Integer.parseInt(pageSize));
		pageCountTemp = Long.valueOf(page.getTotalCount()).intValue();
		if (pageCountTemp == 0) {
			pageCountTemp = 1;
		}
		if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0) {
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
		} else {
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
		}
		/*
		 * 判断是否显示上一页或下一页
		 */
		if (Integer.valueOf(pageNo) != 1) {
			dyna.set("previous", "1");
		} else {
			dyna.set("previous", "0");
		}
		if (Integer.valueOf(pageNo) != pageCountTemp) {
			dyna.set("next", "1");
		} else {
			dyna.set("next", "0");
		}
		// 分页处理End

		// 再次将分页相关数据返回页面Start
		request.setAttribute("pageNo", pageNo);
		dyna.set("pageNo", pageNo);
		request.setAttribute("pageCount", pageCountTemp);
		dyna.set("pageCount", pageCountTemp);
		request.setAttribute("totalNo", page.getTotalCount());
		// 再次将分页相关数据返回页面End
		request.setAttribute("list", page.getResult());
		dyna.set("qid", "");
		return mapping.findForward("toAnswerResultList");
	}

	/**
	 * 跳转问卷调查列表页面.
	 */
	public ActionForward toAnswerOrgPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals("")) ? "1" : (String) dyna
		        .get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals("")) ? PAGE_CONTAIN_NUMBER
		        .toString() : (String) dyna.get("pageSize");
		String qid = (dyna.get("qid") == null || dyna.get("qid").equals("")) ? "" : (String) dyna.get("qid");
		String orgNo = (dyna.get("org") == null || dyna.get("org").equals("")) ? "" : (String) dyna.get("org");
		int pageCountTemp = 0;
		Page page = questionService.queryBsSurveyobject(qid, orgNo, Integer.parseInt(pageNo),
		        Integer.parseInt(pageSize));
		pageCountTemp = Long.valueOf(page.getTotalCount()).intValue();
		if (pageCountTemp == 0) {
			pageCountTemp = 1;
		}
		if (pageCountTemp % PAGE_CONTAIN_NUMBER == 0) {
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER;
		} else {
			pageCountTemp = pageCountTemp / PAGE_CONTAIN_NUMBER + 1;
		}
		/*
		 * 判断是否显示上一页或下一页
		 */
		if (Integer.valueOf(pageNo) != 1) {
			dyna.set("previous", "1");
		} else {
			dyna.set("previous", "0");
		}
		if (Integer.valueOf(pageNo) != pageCountTemp) {
			dyna.set("next", "1");
		} else {
			dyna.set("next", "0");
		}
		// 分页处理End

		// 再次将分页相关数据返回页面Start
		request.setAttribute("pageNo", pageNo);
		dyna.set("pageNo", pageNo);
		request.setAttribute("pageCount", pageCountTemp);
		dyna.set("pageCount", pageCountTemp);
		request.setAttribute("totalNo", page.getTotalCount());
		// 再次将分页相关数据返回页面End
		request.setAttribute("list", page.getResult());
		dyna.set("qid", qid);
		dyna.set("sid", "");
		dyna.set("org", "");
		dyna.set("orgChoice", "");
		return mapping.findForward("toAnswerOrgPage");
	}

	public ActionForward toAnswerOrgResultPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String sid = (dyna.get("sid") == null || dyna.get("sid").equals("")) ? "" : (String) dyna.get("sid");
		BsSurveyobject bss = questionService.getBsSurveyobject(sid);
		String qid = bss.getBsQuestionaire().getId().toString().trim();
		BsQuestionaire bs = questionService.getBsQuestionaireById(qid);
		String str = questionService.getBsQuestionStrByQuestionaireId(qid, bs.getQtitle().trim(), bss.getSoqorgno()
		        .toString().trim());
		request.setAttribute("str", str);
		request.setAttribute("status", "2");
		return mapping.findForward("toAnswerQuestionairesPage");
	}

	/**
	 * 以HTML视图方式展示统计结果.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws SaveTargetException
	 * @throws Exception
	 */
	public ActionForward displayHtml(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) throws Exception {
		DynaActionForm dyna = (DynaActionForm) form;
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> keyValue = new HashMap<String, String>();
		String qid = dyna.getString("qid");
		BsQuestionaire bs = questionService.getBsQuestionaireById(qid);
		params.put("qid", qid);
		if (bs != null) {
			keyValue.put("TITLE", bs.getQtitle());
		} else {
			keyValue.put("TITLE", StringUtils.EMPTY);
		}
		String htmlStr = questionStsicSvce.getHTMLString("11", questionStsicSvce.doStatistics(params), keyValue);
		request.setAttribute("htmlStr", htmlStr);
		dyna.set("qid", qid);
		return mapping.findForward("toresultPage");
	}

	/**
	 * 以Excel视图方式展示辖内问题概况统计结果.
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward displayExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		Map<String, String> params = new HashMap<String, String>();
		Map<String, String> keyValue = new HashMap<String, String>();
		String qid = dyna.getString("qid");
		BsQuestionaire bs = questionService.getBsQuestionaireById(qid);
		params.put("qid", qid);
		if (bs != null) {
			keyValue.put("TITLE", bs.getQtitle());
		} else {
			keyValue.put("TITLE", StringUtils.EMPTY);
		}
		HSSFWorkbook wb = null;
		wb = questionStsicSvce.generateExcel("11", questionStsicSvce.doStatistics(params), keyValue);
		String excelFileName = "调查问卷统计表";
		try {
			OutputStream repos = response.getOutputStream();
			String fileName = excelFileName + "_" + System.currentTimeMillis() + ".xls";
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Content-Disposition", "attachment;filename="
			        + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			wb.write(repos);
			repos.flush();
			repos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
