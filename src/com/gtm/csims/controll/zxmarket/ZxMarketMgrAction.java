package com.gtm.csims.controll.zxmarket;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sweet.dao.generic.support.Page;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.business.managment.system.SystemBaseInfoManager;
import com.gtm.csims.business.zxmarket.ZxMarketMgrService;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsTraining;
import com.gtm.csims.model.BsZxOrgDoc;
import com.gtm.csims.model.BsZxPersonalDoc;
import com.gtm.csims.utils.DateUtil;
import com.gtm.csims.utils.RequestUtil;


/**
 * @ClassName: ${ZxMarketMgrAction}
 * @author qhy
 * @date ${date} ${time}
 * 
 */
@SuppressWarnings("unchecked")
public class ZxMarketMgrAction extends BaseAction {
	private static final Integer PAGE_CONTAIN_NUMBER = 10;
	private ZxMarketMgrService zxMarketMgrService;
	private SystemBaseInfoManager systemBaseInfoManager;
	
	public void setZxMarketMgrService(ZxMarketMgrService zxMarketMgrService) {
		this.zxMarketMgrService = zxMarketMgrService;
	}
	
	public void setSystemBaseInfoManager(SystemBaseInfoManager systemBaseInfoManager) {
		this.systemBaseInfoManager = systemBaseInfoManager;
	}

	/**
	 * @Description:跳转征信机构诚信档案新增页面
	 * @author qhy
	 */
	public ActionForward toZxOrgIntegrityDocumentInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("toZxOrgIntegrityDocumentInit");
	}
	
	public ActionForward createZxOrgIntegrityDocument(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "toZxOrgIntegrityDocumentList");
		try {
			BsZxOrgDoc bs = RequestUtil.getBeanFromParams(request,
					BsZxOrgDoc.class);
			String name = (dyna.get("organizationChoice") == null || dyna.get("organizationChoice").equals(
				"")) ? null : (String) dyna.get("organizationChoice");
			String infoDate = (dyna.get("infoDate") == null || dyna.get("infoDate").equals(
				"")) ? null : (String) dyna.get("infoDate");
			String year = infoDate.substring(0, 4);
			bs.setName(name);
			bs.setYear(year);
			Date date = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			String dateString = sdf.format(date);
			bs.setRecordDate(dateString);
			zxMarketMgrService.saveBsZxOrgDoc(bs);
			
			// Calendar cal = Calendar.getInstance();
			// int year = cal.get(Calendar.YEAR);
			// noGenerator.updateNo(1, loginOrgNo, String.valueOf(year));
			request.setAttribute("message", "操作成功!");
//			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
//					+ "】 用户保存培训信息【操作成功!】", "", "", "HTTP",nowLoginUserName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
//			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
//					+ "】 用户保存培训信息登记【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		return mapping.findForward("toMessage");
	}
	
	/**
	 * @Description:跳转征信机构诚信档案新增页面
	 * @author qhy
	 */
	public ActionForward toZxPersonalIntegrityDocumentInit(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		return mapping.findForward("toZxPersonalIntegrityDocumentInit");
	}
	
	public ActionForward createZxPersonalIntegrityDocument(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		request.setAttribute("methodname", "toZxPersonalIntegrityDocumentList");
		try {
			BsZxPersonalDoc bs = RequestUtil.getBeanFromParams(request,
					BsZxPersonalDoc.class);
			String orgName = (dyna.get("organizationChoice") == null || dyna.get("organizationChoice").equals(
				"")) ? null : (String) dyna.get("organizationChoice");
			String infoDate = (dyna.get("infoDate") == null || dyna.get("infoDate").equals(
				"")) ? null : (String) dyna.get("infoDate");
			String year = infoDate.substring(0, 4);
			bs.setYear(year);
			Date date = new Date();
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
			String dateString = sdf.format(date);
			bs.setRecordDate(dateString);
			bs.setOrgName(orgName);
			zxMarketMgrService.saveBsZxPersonalDoc(bs);
			request.setAttribute("message", "操作成功!");
//			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
//					+ "】 用户保存培训信息【操作成功!】", "", "", "HTTP",nowLoginUserName);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("message", "操作失败!");
//			remindService.writeLog("【" + bsorg.getName() + "】下的 【" + nowLoginUserName
//					+ "】 用户保存培训信息登记【操作失败!】", "", "", "HTTP",nowLoginUserName);
		}
		return mapping.findForward("toMessage");
	}
	
	public ActionForward toZxOrgIntegrityDocumentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
				.get("pageSize");
		String orgNo = (dyna.get("organization") == null || dyna.get("organization")
				.equals("")) ? "" : (String) dyna.get("organization");
		String year = (dyna.get("year") == null || dyna.get("year")
				.equals("")) ? "" : (String) dyna.get("year");
		int pageCountTemp = 0;
		// 列表循环
		// 调用获取行政执法分页方法
		Page page = zxMarketMgrService.queryZxOrgIntegrityDocumentList(orgNo,year,Integer.parseInt(pageNo), Integer
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
		return mapping.findForward("toZxOrgIntegrityDocumentList");
	}
	
	public ActionForward toZxPersonalIntegrityDocumentList(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		DynaActionForm dyna = (DynaActionForm) form;
		String orgNo = (dyna.get("organization") == null || dyna.get("organization")
				.equals("")) ? "" : (String) dyna.get("organization");
		String year = (dyna.get("year") == null || dyna.get("year")
				.equals("")) ? "" : (String) dyna.get("year");
		String name = (dyna.get("name") == null || dyna.get("name")
				.equals("")) ? "" : (String) dyna.get("name");
		String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
				.equals("")) ? "1" : (String) dyna.get("pageNo");
		String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
				.equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
				.get("pageSize");
		int pageCountTemp = 0;
		// 列表循环
		// 调用获取行政执法分页方法
		Page page = zxMarketMgrService.queryZxPersonalIntegrityDocumentList(name,orgNo,year,Integer.parseInt(pageNo), Integer
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
		return mapping.findForward("toZxPersonalIntegrityDocumentList");
	}

}
