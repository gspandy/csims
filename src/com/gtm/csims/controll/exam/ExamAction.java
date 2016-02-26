package com.gtm.csims.controll.exam;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sweet.dao.generic.support.Page;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.business.dataapp.statistics.impl.ExamScoreStsicSvce;
import com.gtm.csims.business.exam.ExamService;
import com.gtm.csims.business.managment.system.SystemBaseInfoManager;
import com.gtm.csims.business.remind.RemindService;
import com.gtm.csims.file.FileHandler;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.model.BsExamscore;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.utils.JsonTimeValueProcessor;

/**
 * 考试相关功能视图控制类
 * 
 * @author yangyz 
 * @version 1.5
 * @see java.lang.Class
 * @since JDK1.0
 */
public class ExamAction extends BaseAction {

    /**
     * 
     */
    private static JsonConfig JSON_CONFIG = new JsonConfig();
    static {
        JSON_CONFIG.registerJsonValueProcessor(Date.class, new JsonTimeValueProcessor());
    }

    private FileHandler fileHandler;
    private RemindService remindService;
    private ExamService examService;
    private ExamScoreStsicSvce examScoreStsicSvce;
    private SystemBaseInfoManager systemBaseInfoManager;

    /**
     * 下载附件
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward downloadAtt(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String attId = request.getParameter("attId").toString();
        try {
            fileHandler.download(attId, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            super.printMessage(request, response, "当前没有可以下载的附件！错误原因：" + e.getMessage(), ATTR_ERROR);
        }
        return null;
    }

    /**
     * @Description:跳转考试成绩Excel导入页面
     * @author Sweet
     */
    public ActionForward toImportExamScoreExcel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, "当前用户不属于人民银行用户，不能使用该功能", ATTR_ERROR);
            return null;
        }
        return mapping.findForward("toImportExamScoreExcel");
    }

    /**
     * @Description:跳转考试成绩统计表统计页面
     * @author yangyz
     */
    public ActionForward toExamScoreAnl(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, "当前用户不属于人民银行用户，不能使用该功能", ATTR_ERROR);
            return null;
        }
        return mapping.findForward("toExamScoreAnl");
    }

    /**
     * @Description:跳转商业银行获取行政执法结论分页页面
     * @author yangyz
     */
    public ActionForward toPageExamScoreInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("toPageExamScoreInfo");
    }

    /**
     * 获取考试成绩分页信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward pageExamScoreInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        /** 获取参数 */
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
        if (bsorg == null) {
            return null;
        }
        String isPcb = bsorg.getIspcb();// 获取当前登录人是否为人行用户

        // 获取查询条件中的行政执法检查工作记录编号
        String peoid = request.getParameter("peoId");
        String peonm = request.getParameter("peoNm");
        String peotype = request.getParameter("peotype");
        String stDate = request.getParameter("stDate");
        String tmDate = request.getParameter("tmDate");
        // 获取并处理分页参数
        Integer start = null;
        Integer limit = null;
        try {
            start = Integer.valueOf(request.getParameter("start"));
            limit = Integer.valueOf(request.getParameter("limit"));
        } catch (Exception e) {
            start = 0;
            limit = PAGE_CONTAIN_NUMBER;
        }
        /** 计算页号 */
        Integer pageNo = start / limit + 1;

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Page page = null;
        try {
            if (isPcb != null && isPcb.trim().equalsIgnoreCase("YES")) {
                page = examService.queryExamScoreByPcb(peoid, peonm, peotype, stDate, tmDate,
                        pageNo, PAGE_CONTAIN_NUMBER, loginOrgNo);
            } else if (isPcb != null && isPcb.trim().equalsIgnoreCase("NO")) {
                page = examService.queryExamScoreByBank(peoid, peonm, peotype, stDate, tmDate,
                        pageNo, PAGE_CONTAIN_NUMBER, loginOrgNo);
            } else {
                out.print("{totalProperty:0,\"root\":[]}");
                return null;
            }
            // } else {
            // out.print("{totalProperty:0,\"root\":[]}");
            // return null;
            // }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        JSONArray jsonObject = JSONArray.fromObject(page.getResult(), JSON_CONFIG);
        String outputStr = jsonObject.toString();
        outputStr = "{totalProperty:" + page.getTotalCount() + ",\"root\":" + outputStr + "}";
        if (out != null) {
            out.print(outputStr);
        }
        return null;
    }

    /**
     * 获取考试成绩相关详细信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getExamScoreDetail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 通过hibernate直接从数据库获取代理对象
        BsExamscore es = null;
        // 此对象为单纯POJO
        BsExamscore esNotLazy = new BsExamscore();
        try {
            if (StringUtils.isNotEmpty(id) && StringUtils.isNotBlank(id)) {
                es = examService.getExamScoreById(id);
                // 通过hibernate从数据库获取代理对象后转化为单纯POJO，防止json转换时出现属性死循环
                BeanUtils.copyProperties(esNotLazy, es);
            } else {
                out.print("{}");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{}");
            return null;
        }
        try {
            JSONObject jsonObject = JSONObject.fromObject(esNotLazy, JSON_CONFIG);
            String outputStr = jsonObject.toString();
            // System.out.println(outputStr);
            if (out != null) {
                out.print(outputStr);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print("{}");
        return null;
    }

    /**
     * 生成行考试成绩统计表Excel文件
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward generateExamScoreAnlExcel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String stdate = request.getParameter("form_stdate");
        String tmdate = request.getParameter("form_tmdate");
        String formPeoid = "";
        String formPeonm = "";
        String formPeotype = "";
        try {
            formPeoid = java.net.URLDecoder.decode(request.getParameter("form_peoid"), "UTF-8");
            formPeonm = java.net.URLDecoder.decode(request.getParameter("form_peonm"), "UTF-8");
            formPeotype = java.net.URLDecoder.decode(request.getParameter("form_peotype"), "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("FROM_DATE", stdate);
        params.put("TO_DATE", tmdate);
        params.put("PEOID", formPeoid);
        params.put("PEONM", formPeonm);
        params.put("PEOTYPE", formPeotype);
        /** 获取参数 */
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
        if (bsorg == null || bsorg.getIspcb() == null) {
            super.printMessage(request, response, "不能正确获取当前登陆用户所在机构信息：" + loginOrgNo, ATTR_ERROR);
            return null;
        }
        params.put("LOGIN_ORGNO", loginOrgNo);
        params.put("LOGIN_IS_PCB", bsorg.getIspcb());
        params.put("LOGIN_BANK_TYPE", bsorg.getH());
        Map<String, String> keyValue = new HashMap<String, String>();
        OutputStream repos = null;
        try {
            repos = response.getOutputStream();
            HSSFWorkbook wb = null;
            Map<String, Object> data = examScoreStsicSvce.doStatistics(params);
            wb = examScoreStsicSvce.generateExcel("2", data, keyValue);
            String fileName = String.format("考试成绩明细表-%1$tm%1$td%1$tH%1$tM%1$tS.xls", new Date());
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            // FileOutputStream fos=new FileOutputStream("F:/aaaa.xls");
            wb.write(repos);
            // fos.close();
            repos.flush();
            repos.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (repos != null) {
                    repos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(),
                    request, response);
            remindService.writeLog(
                    String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "导出考试成绩明细表", "操作成功!"),
                    "", "", "HTTP", nowLoginUser);
        }
        return null;
    }

    /**
     * 获取考试成绩统计信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unused")
    public ActionForward getExamScoreAnl(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        /** 获取参数 */
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
        Boolean isPcb = null;// 获取当前登录人是否为人行用户
        if (bsorg != null && bsorg.getIspcb() != null
                && bsorg.getIspcb().trim().equalsIgnoreCase("YES")) {
            isPcb = true;
        } else {
            isPcb = false;
        }

        // 获取查询条件
        String peotype = request.getParameter("peotype");
        String stDate = request.getParameter("stDate");
        String tmDate = request.getParameter("tmDate");

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        StringBuilder jsonSb = new StringBuilder("{'data':[");
        try {
            Map<String, String> passRateMap = examService.calculateExamPassRate(peotype, stDate,
                    tmDate, isPcb, loginOrgNo);
            Set<Map.Entry<String, String>> entrySet = passRateMap.entrySet();
            int count = 1;
            for (Iterator<Map.Entry<String, String>> iterator = entrySet.iterator(); iterator
                    .hasNext();) {
                Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
                jsonSb.append("{'data':'").append(entry.getKey()).append("','total':'")
                        .append(entry.getValue()).append("'}");
                if (count < entrySet.size()) {
                    jsonSb.append(",");
                }
                count++;
            }
            jsonSb.append("]}");
            // System.out.println(jsonSb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{'data':[{'data':'不及格','total':'0'},{'data':'及格','total':'0'},{'data':'满分','total':'0'}]}");
            return null;
        }
        if (out != null) {
            out.print(jsonSb.toString());
        }
        return null;
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void setRemindService(RemindService remindService) {
        this.remindService = remindService;
    }

    public void setSystemBaseInfoManager(SystemBaseInfoManager systemBaseInfoManager) {
        this.systemBaseInfoManager = systemBaseInfoManager;
    }

    public void setExamScoreStsicSvce(ExamScoreStsicSvce examScoreStsicSvce) {
        this.examScoreStsicSvce = examScoreStsicSvce;
    }

}
