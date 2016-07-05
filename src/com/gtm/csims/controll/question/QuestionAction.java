package com.gtm.csims.controll.question;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sweet.dao.generic.support.Page;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.business.managment.system.SystemBaseInfoManager;
import com.gtm.csims.business.question.QuestionService;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsQuestionaire;
import com.gtm.csims.utils.RequestUtil;

/**
 * 问卷调查.
 * 
 * @author yangyongzhi 
 * @version 1.5
 * @see java.lang.Class
 * @since JDK1.0
 */
public class QuestionAction extends BaseAction {

	/**
	 * 日志调试.
	 */
	private static Logger LOGGER = Logger.getLogger(QuestionAction.class);

	private QuestionService questionService;

	private SystemBaseInfoManager systemBaseInfoManager;

	/**
	 * 跳转问卷调查列表页面.
	 */
	public ActionForward toQuestionairesList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		// 如果当前用户不属于人行用户，则不能使用该功能
		if (!super.isPcbUser(request, response)) {
			this.printMessage(request, response, ERROR1, ATTR_ERROR);
			return null;
		}

		String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
		if (StringUtils.isBlank(loginOrgNo)) {
			this.printMessage(request, response,
			        String.format(ERROR3, this.getPrivCredential(UserCredentialName.login.name(), request, response)),
			        ATTR_ERROR);
			return null;
		}

		DynaActionForm dyna = (DynaActionForm) form;
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals(StringUtils.EMPTY)) ? "1"
		        : (String) dyna.get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals(StringUtils.EMPTY)) ? PAGE_CONTAIN_NUMBER
		        .toString() : (String) dyna.get("pageSize");
		String qtitle = (dyna.get("qtitle") == null || dyna.get("qtitle").equals(StringUtils.EMPTY)) ? null
		        : (String) dyna.get("qtitle");
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
		return mapping.findForward("toQuestionairesList");
	}

	/**
	 * 新增问卷调查.
	 */
	public ActionForward createQuestionaire(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	        HttpServletResponse response) {
		if (this.isTokenValid(request)) {// 正常提交
			this.resetToken(request);// 清空当前会话中的token值
			// 如果当前用户不属于人行用户，则不能使用该功能
			if (!super.isPcbUser(request, response)) {
				this.printMessage(request, response, ERROR1, ATTR_ERROR);
				return null;
			}
			// DynaActionForm dyna = (DynaActionForm) form;
			String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(), request, response);
			String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
			BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
			try {
				BsQuestionaire questionaire = RequestUtil.getBeanFromParams(request, BsQuestionaire.class);
				Date now = new Date();
				questionaire.setCreatedate(now);
				questionaire.setCreator(nowLoginUser);
				questionaire.setCreatororgno(loginOrgNo);
				questionaire.setCreatororg(bsorg.getName());
				questionaire.setCrtdate(now);

				questionService.create(questionaire);
				LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "保存问卷调查", "操作成功!"));
				this.printMessage(request, response, "保存成功", "");
				return null;
			} catch (Exception e) {
				LOGGER.error("行政执法登记保存方法发生错误", e);
				this.printMessage(request, response, "保存行政执法登记操作失败，错误原因：" + e.getMessage(), "");
				return null;
			}
		} else {
			// 重复提交
			this.printMessage(request, response, String.format(ERROR2, "行政执法立项登记"), ATTR_ERROR);
			return null;
		}
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

	public void setSystemBaseInfoManager(SystemBaseInfoManager systemBaseInfoManager) {
		this.systemBaseInfoManager = systemBaseInfoManager;
	}

}
