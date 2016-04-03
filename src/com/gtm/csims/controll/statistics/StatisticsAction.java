package com.gtm.csims.controll.statistics;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.business.dataapp.statistics.StatisticsService;
import com.gtm.csims.business.dataapp.statistics.impl.AeFeedbackStsicSvce;
import com.gtm.csims.business.managment.system.SystemBaseInfoManager;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.model.BsOrg;

/**
 * 统计功能控制类.
 * 
 * @author Sweet
 * 
 */
public class StatisticsAction extends BaseAction {
    private StatisticsService wtgkStsicSvce;
    private SystemBaseInfoManager systemBaseInfoManager;
    private AeFeedbackStsicSvce aeFeedbackStsicSvce;

    /**
     * 初始化辖内问题概况统计筛选条件页面.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<String> years = new ArrayList<String>();
        for (int i = 2014; i <= year; i++) {
            years.add(String.valueOf(i));
        }
        request.setAttribute("years", years);
        return mapping.findForward("toinitPage");
    }

    /**
     * 以HTML视图方式展示辖内问题概况统计结果.
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
        String fromDate = dyna.getString("fromDate");
        String toDate = dyna.getString("toDate");
        String reportYear = dyna.getString("reportYear");
        if (reportYear == null || reportYear.trim().equals("")) {
            Calendar now = Calendar.getInstance();
            reportYear = String.valueOf(now.get(Calendar.YEAR));
        }
        if (fromDate != null && !fromDate.trim().equals("")) {
            params.put("FROM_DATE", fromDate + " 00:00:00");
            keyValue.put("FROM_DATE", fromDate);
        } else {
            params.put("FROM_DATE", reportYear + "-01-01 00:00:00");
            keyValue.put("FROM_DATE", reportYear + "-01-01");
        }
        if (toDate != null && !toDate.trim().equals("")) {
            params.put("TO_DATE", toDate + " 23:59:59");
            keyValue.put("TO_DATE", toDate);
        } else {
            params.put("TO_DATE", reportYear + "-12-31 23:59:59");
            keyValue.put("TO_DATE", reportYear + "-12-31");
        }
        keyValue.put("DURING", reportYear);
        /** 获取参数 */
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
        // 获取当前登录人是否为人行用户
        if (bsorg != null) {
            if (bsorg.getIspcb() != null && bsorg.getIspcb().trim().equalsIgnoreCase("YES")) {
                params.put("LOGIN_IS_PCB", "YES");
            } else {
                params.put("LOGIN_IS_PCB", "NO");
                params.put("LOGIN_BANK_TYPE", bsorg.getH());
            }
        }
        params.put("LOGIN_ORGNO", loginOrgNo);
        if (bsorg != null) {
            keyValue.put("ANL_ORG", bsorg.getName());
        } else {
            keyValue.put("ANL_ORG", StringUtils.EMPTY);
        }
        String htmlStr = wtgkStsicSvce.getHTMLString("3", wtgkStsicSvce.doStatistics(params), keyValue);
        request.setAttribute("htmlStr", htmlStr);
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
        String fromDate = dyna.getString("fromDate");
        String toDate = dyna.getString("toDate");
        String reportYear = dyna.getString("reportYear");
        if (StringUtils.isBlank(reportYear)) {
            Calendar now = Calendar.getInstance();
            reportYear = String.valueOf(now.get(Calendar.YEAR));
        }
        if (StringUtils.isNotBlank(fromDate)) {
            params.put("FROM_DATE", fromDate + " 00:00:00");
            keyValue.put("FROM_DATE", fromDate);
        } else {
            params.put("FROM_DATE", reportYear + "-01-01 00:00:00");
            keyValue.put("FROM_DATE", reportYear + "-01-01");
        }
        if (StringUtils.isNotBlank(toDate)) {
            params.put("TO_DATE", toDate + " 23:59:59");
            keyValue.put("TO_DATE", toDate);
        } else {
            params.put("TO_DATE", reportYear + "-12-31 23:59:59");
            keyValue.put("TO_DATE", reportYear + "-12-31");
        }
        keyValue.put("DURING", reportYear);
        /** 获取参数 */
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
        // 获取当前登录人是否为人行用户
        if (bsorg != null) {
            if (bsorg.getIspcb() != null && bsorg.getIspcb().trim().equalsIgnoreCase("YES")) {
                params.put("LOGIN_IS_PCB", "YES");
            } else {
                params.put("LOGIN_IS_PCB", "NO");
                params.put("LOGIN_BANK_TYPE", bsorg.getH());
            }
        }
        params.put("LOGIN_ORGNO", loginOrgNo);
        if (bsorg != null) {
            keyValue.put("ANL_ORG", bsorg.getName());
        } else {
            keyValue.put("ANL_ORG", StringUtils.EMPTY);
        }
        HSSFWorkbook wb = null;
        wb = wtgkStsicSvce.generateExcel("3", wtgkStsicSvce.doStatistics(params), keyValue);
        String excelFileName = "辖内问题概况统计表";
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

    /**
     * 初始化反馈意见统计筛选条件页面.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward initFeedback(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        List<String> years = new ArrayList<String>();
        for (int i = 2014; i <= year; i++) {
            years.add(String.valueOf(i));
        }
        request.setAttribute("years", years);
        return mapping.findForward("toinitFeedbackPage");
    }

    /**
     * 以HTML视图方式展示反馈意见统计结果.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws SaveTargetException
     * @throws Exception
     */
    public ActionForward displayFeedbackHtml(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        DynaActionForm dyna = (DynaActionForm) form;
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String> keyValue = new HashMap<String, String>();
        String reportYear = dyna.getString("reportYear");
        if (StringUtils.isBlank(reportYear)) {
            Calendar now = Calendar.getInstance();
            reportYear = String.valueOf(now.get(Calendar.YEAR));
        }

        keyValue.put("YEAR", reportYear);
        params.put("YEAR", reportYear);
        String htmlStr = aeFeedbackStsicSvce.getHTMLString("4", aeFeedbackStsicSvce.doStatistics(params), keyValue);
        request.setAttribute("htmlStr", htmlStr);
        return mapping.findForward("toFeedBackresultPage");
    }

    /**
     * 以Excel视图方式展示反馈意见统计结果.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward displayFeedbackExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String> keyValue = new HashMap<String, String>();
        String reportYear = dyna.getString("reportYear");
        if (StringUtils.isBlank(reportYear)) {
            Calendar now = Calendar.getInstance();
            reportYear = String.valueOf(now.get(Calendar.YEAR));
        }

        keyValue.put("YEAR", reportYear);
        params.put("YEAR", reportYear);
        HSSFWorkbook wb = null;
        wb = aeFeedbackStsicSvce.generateExcel("4", aeFeedbackStsicSvce.doStatistics(params), keyValue);
        String excelFileName = "意见反馈信息表";
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

    public void setWtgkStsicSvce(StatisticsService wtgkStsicSvce) {
        this.wtgkStsicSvce = wtgkStsicSvce;
    }

    public void setSystemBaseInfoManager(SystemBaseInfoManager systemBaseInfoManager) {
        this.systemBaseInfoManager = systemBaseInfoManager;
    }

    public void setAeFeedbackStsicSvce(AeFeedbackStsicSvce aeFeedbackStsicSvce) {
        this.aeFeedbackStsicSvce = aeFeedbackStsicSvce;
    }

}