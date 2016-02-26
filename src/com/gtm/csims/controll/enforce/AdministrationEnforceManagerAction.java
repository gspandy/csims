package com.gtm.csims.controll.enforce;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jguard.core.principals.RolePrincipal;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sweet.dao.generic.support.Page;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.CharEncoding;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;
import org.springframework.dao.DataIntegrityViolationException;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.business.dataapp.statistics.impl.AeinspectionAnlStsicSvce;
import com.gtm.csims.business.enforce.EnforceService;
import com.gtm.csims.business.enforce.PunishService;
import com.gtm.csims.business.managment.system.SystemBaseInfoManager;
import com.gtm.csims.business.remind.RemindService;
import com.gtm.csims.business.serialnumber.NoGenerator;
import com.gtm.csims.business.word.WordGenerator;
import com.gtm.csims.file.FileHandler;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.model.BsAdmenforce;
import com.gtm.csims.model.BsAdmpunish;
import com.gtm.csims.model.BsAeconclusion;
import com.gtm.csims.model.BsAeinspection;
import com.gtm.csims.model.BsAeinspectionAnl;
import com.gtm.csims.model.BsAepeople;
import com.gtm.csims.model.BsAerectification;
import com.gtm.csims.model.BsDictionary;
import com.gtm.csims.model.BsFactbook;
import com.gtm.csims.model.BsNogenerate;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsUserInfoOfJg;
import com.gtm.csims.model.BsWorkbasis;
import com.gtm.csims.model.BsWorkcoming;
import com.gtm.csims.model.BsWorkgoaway;
import com.gtm.csims.model.BsWorktalksummary;
import com.gtm.csims.utils.DateUtil;
import com.gtm.csims.utils.JsonDateValueProcessor;
import com.gtm.csims.utils.RequestUtil;
import com.gtm.csims.utils.StringUtil;

/**
 * 行政执法视图控制类
 * 
 * @author yangyongzhi 
 * @version 1.5
 * @see java.lang.Class
 * @since JDK1.0
 */
@SuppressWarnings("unchecked")
public class AdministrationEnforceManagerAction extends BaseAction {

    /**
     * 日志调试.
     */
    private static Logger LOGGER = Logger.getLogger(AdministrationEnforceManagerAction.class);

    /**
     * 
     */
    private static JsonConfig JSON_CONFIG = new JsonConfig();
    static {
        JSON_CONFIG.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
    }

    private NoGenerator noGenerator;
    private FileHandler fileHandler;
    private WordGenerator wordGenerator;
    private EnforceService enforceService;
    private PunishService punishService;
    @SuppressWarnings("unused")
    private RemindService remindService;
    private SystemBaseInfoManager systemBaseInfoManager;
    private AeinspectionAnlStsicSvce aeinspectionAnlStsicSvce;

    /**
     * 下载附件.
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
            LOGGER.error(String.format(ERROR4, e.getMessage()), e);
            super.printMessage(request, response, String.format(ERROR4, e.getMessage()), ATTR_ERROR);
        }
        return null;
    }

    /**
     * 跳转行政执法信息Excel导入页面.
     */
    public ActionForward toAdminEnforceExcel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        return mapping.findForward("toAdminEnforceExcel");
    }

    /**
     * 跳转行政执法检查情况统计表Excel导入页面.
     */
    public ActionForward toAEInspectionAnlExcel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        return mapping.findForward("toAEInspectionAnlExcel");
    }

    /**
     * @Description:跳转行政执法检查情况统计表统计页面
     * @author yangyz
     */
    public ActionForward toAeinspectionAnl(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        return mapping.findForward("toAEInspectionAnalyse");
    }

    /**
     * 生成行政执法检查情况统计表Excel文件.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward generateAeinspectionAnlExcel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // DynaActionForm dyna = (DynaActionForm) form;
        String nowLoginUser = this.getPubCredential(UserCredentialName.organization.name(),
                request, response);
        if (StringUtils.isBlank(nowLoginUser)) {
            this.printMessage(request, response, ERROR6, ATTR_ERROR);
            return null;
        }

        String stdate = request.getParameter("form_stdate");
        String tmdate = request.getParameter("form_tmdate");
        String formA1 = StringUtils.EMPTY;
        String formB1 = StringUtils.EMPTY;
        try {
            formA1 = java.net.URLDecoder
                    .decode(request.getParameter("form_a1"), CharEncoding.UTF_8);
            formB1 = java.net.URLDecoder
                    .decode(request.getParameter("form_b1"), CharEncoding.UTF_8);
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("FROM_DATE", stdate);
        params.put("TO_DATE", tmdate);
        params.put("A1", formA1);
        params.put("B1", formB1);
        params.put("ORGNO", nowLoginUser.trim());
        Map<String, String> keyValue = new HashMap<String, String>();
        // keyValue
        // .put("CREATEDATE", DateUtil.convertToStrWithoutSign(new Date()));
        // if (params.get("BANK_TYPE_NAME") != null
        // && !"".equals(params.get("BANK_TYPE_NAME")))
        // keyValue.put("BANK_TYPE_NAME", params.get("BANK_TYPE_NAME"));
        // else
        // keyValue.put("BANK_TYPE_NAME", "人民银行成都分行");
        // keyValue.put("DURING", BaseReportValidator.getDuringChn(reportDuring
        // .split("#")[0]));
        OutputStream repos = null;
        try {
            repos = response.getOutputStream();
            HSSFWorkbook wb = null;
            Map<String, Object> data = aeinspectionAnlStsicSvce.doStatistics(params);
            wb = aeinspectionAnlStsicSvce.generateExcel("1", data, keyValue);
            String fileName = String
                    .format("行政执法检查情况统计表-%1$tm%1$td%1$tH%1$tM%1$tS.xls", new Date());
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
            // FileOutputStream fos=new FileOutputStream("F:/aaaa.xls");
            wb.write(repos);
            // fos.close();
            repos.flush();
            repos.close();
        } catch (Exception e) {
            LOGGER.error("生成行政执法检查情况统计表Excel文件发生错误", e);
        } finally {
            try {
                if (repos != null) {
                    repos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 行政执法信息Excel导入保存方法.
     */
    public ActionForward importAdminEnforceExcel(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        request.setAttribute("methodname", "toAdminEnforceList&source=1");
        // 调用行政执法excel保存方法
        request.setAttribute(ATTR_MESSAGE, "导入行政执法信息操作成功!");
        return mapping.findForward("toAdminEnforceMessage");
    }

    /**
     * 跳转行政执法登记页面.
     */
    public ActionForward toAdminEnforceInitPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        LOGGER.info("Test for apache log");
        this.saveToken(request);// 产生令牌值
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);

        request.setAttribute("aeorg", bsorg.getNo());
        request.setAttribute("aeorgChoice", bsorg.getName());
        request.setAttribute("aeorgno", bsorg.getNo());
        request.setAttribute("aeorgnm", bsorg.getName());

        // 获取执法检查编号
        BsNogenerate bs = noGenerator.getNoGenerate(loginOrgNo, noGenerator.getYear());
        // 没有行政执法编号规则
        if (bs == null) {
            request.setAttribute("methodname", "toAdminEnforceList&source=1");
            request.setAttribute(ATTR_MESSAGE, "<b>登记行政执法发生错误</b><br><b>失败原因:</b>不能获取当前机构的执法编号规则： "
                    + loginOrgNo + "<br><b>解决方法:</b>需要联系管理员维护该机构执法立项规则!");
            return mapping.findForward("toAdminEnforceMessage");
        } else {
            dyna.set("aetext", bs.getAenotext());
            dyna.set("aeyear", bs.getAenoyear());
            dyna.set("aeindex", bs.getAenoindex());
            request.setAttribute("lxyjList", super.getDicMap("LXYJ"));
            request.setAttribute("jcyjList", super.getDicMap("JCYJ"));
            request.setAttribute("jcfsList", super.getDicMap("JCFS"));
            return mapping.findForward("toAdminEnforceInitPage");
        }
    }

    /**
     * 行政执法登记保存方法.
     */
    public ActionForward createAdminEnforce(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        if (this.isTokenValid(request)) {// 正常提交
            this.resetToken(request);// 清空当前会话中的token值
            // 如果当前用户不属于人行用户，则不能使用该功能
            if (!super.isPcbUser(request, response)) {
                this.printMessage(request, response, ERROR1, ATTR_ERROR);
                return null;
            }
            DynaActionForm dyna = (DynaActionForm) form;
            String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(),
                    request, response);
            String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                    request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
            request.setAttribute("methodname", "toAdminEnforceList&source=1");

            // 调用行政执法保存方法
            try {
                String aeedorgChoice = request.getParameter("aeedorgChoice");
                StringBuffer aeedorgNos = new StringBuffer();
                StringBuffer aeedorgNms = new StringBuffer();
                if (StringUtils.isNotBlank(aeedorgChoice)) {
                    String[] aeedorgChoiceArr = aeedorgChoice.split(",");
                    String[] aeedOrg = null;
                    for (int i = 0; i < aeedorgChoiceArr.length; i++) {
                        aeedOrg = aeedorgChoiceArr[i].split("--");
                        aeedorgNos.append(aeedOrg[1]);
                        aeedorgNms.append(aeedOrg[0]);
                        if (i != aeedorgChoiceArr.length - 1) {
                            aeedorgNos.append(",");
                            aeedorgNms.append(",");
                        }
                    }
                }
                BsAdmenforce ae = RequestUtil.getBeanFromParams(request, BsAdmenforce.class);
                ae.setAeedorgno(aeedorgNos.toString());
                ae.setAeedorgnm(aeedorgNms.toString());
                ae.setStep("1");
                ae.setAeno(noGenerator.generateNoStr(dyna.get("aetext").toString(),
                        dyna.get("aeyear").toString(), dyna.get("aeindex").toString()));
                FormFile file = null;
                String attchuuid = null;
                /** 保存检查方案 * */
                file = (FormFile) dyna.get("aeplanpath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    ae.setAeplan(attchuuid);
                }
                /** 保存检查方案end * */
                ae.setMaxino(1);
                String[] prjbasisArr = request.getParameterValues("prjbasis");
                String[] aebasisArr = request.getParameterValues("aebasis");
                String[] aewayArr = request.getParameterValues("aeway");
                ae.setPrjbasis(StringUtil.join(prjbasisArr, StringUtils.EMPTY, StringUtils.EMPTY,
                        ","));
                ae.setAebasis(StringUtil
                        .join(aebasisArr, StringUtils.EMPTY, StringUtils.EMPTY, ","));
                ae.setAeway(StringUtil.join(aewayArr, StringUtils.EMPTY, StringUtils.EMPTY, ","));
                // 保存创建人为当前登录人id
                ae.setCrtlogin(this.getPrivCredential(UserCredentialName.login.name(), request,
                        response));
                ae.setIsfinished(false);
                ae.setCanbeupdate(true);
                //处理文本框中有换行符等情况
                ae.setAecontent(StringEscapeUtils.escapeJava(ae.getAecontent()));
                ae.setAesummary(StringEscapeUtils.escapeJava(ae.getAesummary()));
                ae.setDptopnion(StringEscapeUtils.escapeJava(ae.getDptopnion()));
                ae.setChairopnion(StringEscapeUtils.escapeJava(ae.getChairopnion()));
                //如果立项信息中行内编号为空，则保存空字符串。
                if (StringUtils.isBlank(ae.getInnerno())) {
                    ae.setInnerno(StringUtils.EMPTY);
                }
                try {
                    enforceService.saveAdmenforce(ae, bsorg.getNo(), noGenerator.getYear());
                } catch (DataIntegrityViolationException e) {
                    request.setAttribute(ATTR_MESSAGE,
                            "<b>保存行政执法登记操作失败,编号重复</b><br><b>失败原因:</b>因为在你保存执法信息之前有其他用户已经保存了执法编号 "
                                    + ae.getAeno()
                                    + " 的执法信息<br><b>解决方法:</b>请重新点击'行政执法登记'菜单获取新执法编号，再输入保存!");
                    return mapping.findForward("toAdminEnforceMessage");
                }
                request.setAttribute(ATTR_MESSAGE, "保存行政执法登记【" + ae.getAeno() + "】操作成功!");
                LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "保存行政执法登记",
                        "操作成功!"));
            } catch (Exception e) {
                LOGGER.error("行政执法登记保存方法发生错误", e);
                request.setAttribute(ATTR_MESSAGE, "保存行政执法登记操作失败，错误原因：" + e.getMessage());
            }
            return mapping.findForward("toAdminEnforceMessage");
        } else {// 重复提交
            this.printMessage(request, response, String.format(ERROR2, "行政执法立项登记"), ATTR_ERROR);
            return null;
        }
    }

    /**
     * 根据人民银行机构获取机构下执法人员.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getAepeoplesByPcbOrgNo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String parentNo = request.getParameter("parentNo");
        // String orgNo = "C1031851000018";
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("根据人民银行机构获取机构下执法人员发生错误", e);
            return null;
        }
        List<BsAepeople> childrenAepeopleslt = null;
        try {
            childrenAepeopleslt = enforceService.getAepeoplesByPcbOrgNo(parentNo);
        } catch (Exception e) {
            LOGGER.error("根据人民银行机构获取机构下执法人员发生错误", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        StringBuffer outputSb = new StringBuffer("{totalProperty:" + childrenAepeopleslt.size()
                + ",root:[");
        BsAepeople aepeople = null;
        for (int i = 0; i < childrenAepeopleslt.size(); i++) {
            aepeople = childrenAepeopleslt.get(i);
            outputSb.append("{'value':'").append(aepeople.getPepname()).append("--")
                    .append(aepeople.getCreditno()).append("','text':'")
                    .append(aepeople.getPepname()).append("-").append(aepeople.getCreditno())
                    .append("'}");
            if (i != childrenAepeopleslt.size() - 1) {
                outputSb.append(",");
            }
        }
        outputSb.append("]}");
        // JSONArray jsonObject = JSONArray.fromObject(userlt);

        // String outputStr = jsonObject.toString();
        if (out != null) {
            // System.out.println(outputSb.toString());
            out.print(outputSb.toString());
        }
        out.close();
        return null;
    }

    /**
     * 跳转行政执法信息列表页面.
     */
    public ActionForward toAdminEnforceList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        String source = request.getParameter("source");
        DynaActionForm dyna = (DynaActionForm) form;
        String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals(StringUtils.EMPTY)) ? "1"
                : (String) dyna.get("pageNo");
        String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals(
                StringUtils.EMPTY)) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
                .get("pageSize");
        String aeno = (dyna.get("aeno") == null || dyna.get("aeno").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("aeno");
        String aeorgChoice = (dyna.get("aeorgChoice") == null || dyna.get("aeorgChoice").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeorgChoice");
        String aeedorgChoice = (dyna.get("aeedorgChoice") == null || dyna.get("aeedorgChoice")
                .equals(StringUtils.EMPTY)) ? null : (String) dyna.get("aeedorgChoice");
        String aeplanstdate = (dyna.get("aeplanstdate") == null || dyna.get("aeplanstdate").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeplanstdate");
        String aeplantmdate = (dyna.get("aeplantmdate") == null || dyna.get("aeplantmdate").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeplantmdate");
        int pageCountTemp = 0;
        // 列表循环
        // 调用获取行政执法分页方法
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        if (StringUtils.isBlank(loginOrgNo)) {
            request.setAttribute(ATTR_MESSAGE, String.format(ERROR3,
                    this.getPrivCredential(UserCredentialName.login.name(), request, response)));
            request.setAttribute("methodname", "toAdminEnforceList");
            return mapping.findForward("toAdminEnforceMessage");
        }
        Page page = null;
        if (source != null && source.equals("2")) {
            page = enforceService.queryAdmenforceForInspc(aeno, aeorgChoice, aeedorgChoice,
                    aeplanstdate, aeplantmdate, Integer.parseInt(pageNo),
                    Integer.parseInt(pageSize), loginOrgNo);
        } else {
            page = enforceService.queryAdmenforce(aeno, aeorgChoice, aeedorgChoice, aeplanstdate,
                    aeplantmdate, Integer.parseInt(pageNo), Integer.parseInt(pageSize), loginOrgNo);
        }
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
        List<?> list = (List<?>) page.getResult();
        //将数据库查询记录其中某些字段按固定长度截取，以便展示
        BsAdmenforce waitViewAdapt = null;
        for (Iterator<?> iterator = list.iterator(); iterator.hasNext();) {
            Object object = iterator.next();
            if (object != null && object instanceof BsAdmenforce) {
                waitViewAdapt = (BsAdmenforce) object;
                waitViewAdapt.setPrjnm(StringUtils.abbreviate(waitViewAdapt.getPrjnm(), 20));
                waitViewAdapt
                        .setAeedorgnm(StringUtils.abbreviate(waitViewAdapt.getAeedorgnm(), 15));
            }
        }
        request.setAttribute("list", list);
        if (StringUtils.isBlank(source)) {
            request.setAttribute("source", StringUtils.EMPTY);
        } else {
            request.setAttribute("source", source);
        }
        return mapping.findForward("toAdminEnforceList");
    }

    /**
     * 跳转行政执法信息详细页面.
     */
    public ActionForward toAdminEnforceDetailPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");
        BsAdmenforce bs = enforceService.getAdmenforce(id);
        request.setAttribute("step", bs.getStep());
        request.setAttribute("aeplan", bs.getAeplan().toString());
        RequestUtil.setFormFromBean(dyna, bs);
        //由于数据库保存内容中换行符已转义，现需要饭转义
        dyna.set("aecontent", StringEscapeUtils.unescapeJava(bs.getAecontent()));
        dyna.set("aesummary", StringEscapeUtils.unescapeJava(bs.getAesummary()));
        dyna.set("dptopnion", StringEscapeUtils.unescapeJava(bs.getDptopnion()));
        dyna.set("chairopnion", StringEscapeUtils.unescapeJava(bs.getChairopnion()));
        // 获取多选值，先转化为数组
        String[] aePrjbasisArr = bs.getPrjbasis().split(",");
        String[] aebasisArr = bs.getAebasis().split(",");
        String[] aeAwayArr = bs.getAeway().split(",");
        request.setAttribute("dicMap", super.getDicMap());
        request.setAttribute("aePrjbasisArr", aePrjbasisArr);
        request.setAttribute("aebasisArr", aebasisArr);
        request.setAttribute("aeAwayArr", aeAwayArr);
        BsAepeople aePeople = enforceService.getAePeopleByCardId(bs.getCrtlogin());
        if (aePeople != null) {
            request.setAttribute("crtLoginNm", aePeople.getPepname() + "-" + aePeople.getCreditno());
        } else {
            request.setAttribute("crtLoginNm",
                    enforceService.getCredential(bs.getCrtlogin(), UserCredentialName.nickname));
        }
        request.setAttribute("form", dyna);
        return mapping.findForward("toAdminEnforceDetailPage");
    }

    /**
     * 跳转行政执法信息修改页面.
     */
    public ActionForward toUpdateAdminEnforcePage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        this.saveToken(request);// 产生令牌值
        // 1.如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            id = dyna.get("id").toString();
        }
        BsAdmenforce bs = enforceService.getAdmenforce(id);
        String nowLoginUser = this.getPrivCredential(UserCredentialName.login.name(), request,
                response);
        // 2.判断当前用户是否有权限修改该信息，登记人才能修改
        if (bs == null) {
            this.printMessage(request, response, String.format(ERROR8, id, "行政执法立项"), ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bs.getCrtlogin();
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "行政执法立项"), ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLoginUser)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLoginUser.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "行政执法立项信息"), ATTR_ERROR);
                return null;
            }
        }
        // 3.如果对应当前立项信息已经录入了工作检查记录，则不能修改立项信息
        List inspLt = enforceService.getAeinspectionsByAeNo(bs.getAeno());
        if (CollectionUtils.isNotEmpty(inspLt)) {
            this.printMessage(request, response,
                    String.format("错误原因：该%s已经录入了对应的行政执法检查工作记录，不能修改", "行政执法立项信息"), ATTR_ERROR);
            return null;
        }
        RequestUtil.setFormFromBean(dyna, bs);
        //由于数据库保存内容中换行符已转义，现需要饭转义
        dyna.set("aecontent", StringEscapeUtils.unescapeJava(bs.getAecontent()));
        dyna.set("aesummary", StringEscapeUtils.unescapeJava(bs.getAesummary()));
        dyna.set("dptopnion", StringEscapeUtils.unescapeJava(bs.getDptopnion()));
        dyna.set("chairopnion", StringEscapeUtils.unescapeJava(bs.getChairopnion()));
        dyna.set("aeorgChoice", bs.getAeorgnm());
        dyna.set("aeorg", bs.getAeorgno());
        String aeedorgNos = bs.getAeedorgno();
        String aeedorgNms = bs.getAeedorgnm();
        if (StringUtils.isNotBlank(aeedorgNos) && StringUtils.isNotBlank(aeedorgNms)) {
            String[] aeedorgNosArr = aeedorgNos.split(",");
            String[] aeedorgNmsArr = aeedorgNms.split(",");
            StringBuffer aeorgChoice = new StringBuffer();
            for (int i = 0; i < aeedorgNosArr.length; i++) {
                aeorgChoice.append(aeedorgNmsArr[i]).append("--").append(aeedorgNosArr[i]);
                if (i != aeedorgNosArr.length - 1) {
                    aeorgChoice.append(",");
                }
            }
            dyna.set("aeedorgChoice", aeorgChoice.toString());
        } else {
            dyna.set("aeedorgChoice", StringUtils.EMPTY);
        }
        String aeno = bs.getAeno();
        dyna.set("aetext", aeno);
        dyna.set("aeyear", aeno);
        request.setAttribute("aeplan", bs.getAeplan().toString());
        request.setAttribute("lxyjList", super.getDicMap("LXYJ"));
        request.setAttribute("jcyjList", super.getDicMap("JCYJ"));
        request.setAttribute("jcfsList", super.getDicMap("JCFS"));
        request.setAttribute("form", dyna);
        // 获取多选值，先转化为数组
        String[] aePrjbasisArr = bs.getPrjbasis().split(",");
        String[] aebasisArr = bs.getAebasis().split(",");
        String[] aeAwayArr = bs.getAeway().split(",");
        request.setAttribute("aePrjbasisArr", aePrjbasisArr);
        request.setAttribute("aebasisArr", aebasisArr);
        request.setAttribute("aeAwayArr", aeAwayArr);
        BsAepeople aePeople = enforceService.getAePeopleByCardId(bs.getCrtlogin());
        if (aePeople != null) {
            request.setAttribute("crtLoginNm", aePeople.getPepname() + "-" + aePeople.getCreditno());
        } else {
            request.setAttribute("crtLoginNm",
                    enforceService.getCredential(bs.getCrtlogin(), UserCredentialName.nickname));
        }
        return mapping.findForward("toUpdateAdminEnforcePage");
    }

    /**
     * 行政执法信息修改.
     */
    public ActionForward updateAdminEnforce(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        if (this.isTokenValid(request)) {// 正常提交
            this.resetToken(request);// 清空当前会话中的token值
            // 如果当前用户不属于人行用户，则不能使用该功能
            if (!super.isPcbUser(request, response)) {
                this.printMessage(request, response, ERROR1, ATTR_ERROR);
                return null;
            }
            DynaActionForm dyna = (DynaActionForm) form;
            String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(),
                    request, response);
            String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                    request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
            request.setAttribute("methodname", "toAdminEnforceList&source=1");
            // String loginOrgNo = this.getPubCredential(
            // UserCredentialName.organization.name(), request, response);
            try {
                // BsAdmenforce ae = RequestUtil.getBeanFromParams(request,
                // BsAdmenforce.class);
                String aeedorgChoice = request.getParameter("aeedorgChoice");
                StringBuffer aeedorgNos = new StringBuffer();
                StringBuffer aeedorgNms = new StringBuffer();
                if (StringUtils.isNotBlank(aeedorgChoice)) {
                    String[] aeedorgChoiceArr = aeedorgChoice.split(",");
                    String[] aeedOrg = null;
                    for (int i = 0; i < aeedorgChoiceArr.length; i++) {
                        aeedOrg = aeedorgChoiceArr[i].split("--");
                        aeedorgNos.append(aeedOrg[1]);
                        aeedorgNms.append(aeedOrg[0]);
                        if (i != aeedorgChoiceArr.length - 1) {
                            aeedorgNos.append(",");
                            aeedorgNms.append(",");
                        }
                    }
                }
                BsAdmenforce ae = enforceService.getAdmenforceById(dyna.getString("id"));
                ae.setAeedorgno(aeedorgNos.toString());
                ae.setAeedorgnm(aeedorgNms.toString());
                FormFile file = null;
                String attchuuid = null;
                /** 保存检查方案 * */
                file = (FormFile) dyna.get("aeplanpath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    ae.setAeplan(attchuuid);
                }
                /** 保存检查方案end * */
                String[] prjbasisArr = request.getParameterValues("prjbasis");
                String[] aebasisArr = request.getParameterValues("aebasis");
                String[] aewayArr = request.getParameterValues("aeway");
                ae.setPrjbasis(StringUtil.join(prjbasisArr, StringUtils.EMPTY, StringUtils.EMPTY,
                        ","));
                ae.setAebasis(StringUtil
                        .join(aebasisArr, StringUtils.EMPTY, StringUtils.EMPTY, ","));
                ae.setAeway(StringUtil.join(aewayArr, StringUtils.EMPTY, StringUtils.EMPTY, ","));
                ae.setAeplanstdate(DateUtils.parseDate(dyna.getString("aeplanstdate"),
                        DateUtil.DATE_FORMAT_ARRAY));
                ae.setAeplantmdate(DateUtils.parseDate(dyna.getString("aeplantmdate"),
                        DateUtil.DATE_FORMAT_ARRAY));
                ae.setPrjnm(dyna.getString("prjnm"));
                ae.setAecontent(dyna.getString("aecontent"));
                ae.setAeheadman(dyna.getString("aeheadman"));
                ae.setAemaster(dyna.getString("aemaster"));
                ae.setAeother(dyna.getString("aeother"));
                ae.setAesummary(dyna.getString("aesummary"));
                ae.setDptopnion(dyna.getString("dptopnion"));
                ae.setDeptman(dyna.getString("deptman"));
                ae.setDeptauditdate(DateUtils.parseDate(dyna.getString("deptauditdate"),
                        DateUtil.DATE_FORMAT_ARRAY));
                ae.setChairopnion(dyna.getString("chairopnion"));
                ae.setChairman(dyna.getString("chairman"));
                ae.setAelimt(dyna.getString("aelimt"));
                ae.setChairauditdate(DateUtils.parseDate(dyna.getString("chairauditdate"),
                        DateUtil.DATE_FORMAT_ARRAY));
                ae.setInnerno(dyna.getString("innerno"));
                //处理文本框中有换行符等情况
                ae.setAecontent(StringEscapeUtils.escapeJava(ae.getAecontent()));
                ae.setAesummary(StringEscapeUtils.escapeJava(ae.getAesummary()));
                ae.setDptopnion(StringEscapeUtils.escapeJava(ae.getDptopnion()));
                ae.setChairopnion(StringEscapeUtils.escapeJava(ae.getChairopnion()));
                enforceService.saveAdmenforce(ae);
                request.setAttribute(ATTR_MESSAGE, "修改行政执法登记【" + ae.getAeno() + "】操作成功!");
                LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "修改行政执法信息",
                        "操作成功!"));
            } catch (Exception e) {
                LOGGER.error(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "修改行政执法信息",
                        "操作失败!"), e);
                request.setAttribute(ATTR_MESSAGE, "修改行政执法登记操作失败，错误原因：" + e.getMessage());
            }
            return mapping.findForward("toAdminEnforceMessage");
        } else {// 重复提交
            this.printMessage(request, response, String.format(ERROR2, "行政执法立项查询"), ATTR_ERROR);
            return null;
        }
    }

    /**
     * 行政执法信息删除.
     */
    public ActionForward delAdminEnforce(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(), request,
                response);
        String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                request, response);
        String id = request.getParameter("id");
        BsAdmenforce bs = enforceService.getAdmenforce(id);
        String nowLogin = this
                .getPrivCredential(UserCredentialName.login.name(), request, response);
        // 判断当前用户是否有权限修改该信息
        if (bs == null) {
            this.printMessage(request, response, String.format(ERROR8, id, "行政执法立项"), ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bs.getCrtlogin();
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "行政执法立项"), ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLogin)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLogin.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "行政执法立项信息"), ATTR_ERROR);
                return null;
            }
        }
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
        request.setAttribute("methodname", "toAdminEnforceList&source=1");

        try {
            enforceService.deleteAdmenforceById(id);
            request.setAttribute(ATTR_MESSAGE, "删除行政执法登记【" + id + "】操作成功!");
            LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "删除行政执法登记",
                    "操作成功!"));
        } catch (Exception e) {
            LOGGER.error(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "删除行政执法登记",
                    "操作失败!"));
            request.setAttribute(ATTR_MESSAGE, "删除行政执法登记操作失败!错误原因:" + e.getMessage());
        }
        return mapping.findForward("toAdminEnforceMessage");
    }

    /**
     * 查看立项检查表.
     */
    public ActionForward toSeeCheckPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // DynaActionForm dyna = (DynaActionForm) form;
        // String id = request.getParameter("id");
        // 调用检查方案下载方法
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        return mapping.findForward("toSeeCheckPage");
    }

    /**
     * 跳转检查工作记录录入页面.
     */
    public ActionForward toAeinspectionInitPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 产生令牌值
        this.saveToken(request);

        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        dyna.set("aeplanstdate", StringUtils.EMPTY);
        dyna.set("aeplantmdate", StringUtils.EMPTY);

        String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            this.printMessage(request, response, "选择的行政执法立项id为空,请重新选择", ATTR_ERROR);
            return null;
        }
        String nowLoginUser = this.getPrivCredential(UserCredentialName.login.name(), request,
                response);

        //获取当前等待录入的工作检查记录对应的立项信息
        BsAdmenforce af = enforceService.getAdmenforce(id);
        if (af == null) {
            super.printMessage(request, response, "没有找到行政执法立项【" + id + ",请重新选择", ATTR_ERROR);
            return null;
        } else {
            // 判断当前用户是否有权限修改该信息
            String crtLogin = af.getCrtlogin();
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "行政执法立项"), ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLoginUser)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLoginUser.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "行政执法立项信息"), ATTR_ERROR);
                return null;
            }
        }

        /* 工作检查记录编号隐藏标示，用于判断当前提交记录编号是否重复
                      用于进场、离场、事实认定书的重复判断*/
        request.setAttribute("inspect_token", UUID.randomUUID().toString());

        request.setAttribute("step", af.getStep());
        request.setAttribute("aeplan", af.getAeplan().toString());

        // 获取行政执法立项被检查机构下拉框信息
        List<BsOrg> orglt = new ArrayList<BsOrg>();
        String aeedorgs = af.getAeedorgno();
        if (StringUtils.isNotBlank(aeedorgs)) {
            String[] aeedorgsArr = aeedorgs.split(",");
            for (int i = 0; i < aeedorgsArr.length; i++) {
                BsOrg org = systemBaseInfoManager.getOrgByNo(aeedorgsArr[i]);
                orglt.add(org);
            }
        }
        request.setAttribute("aeedorgs", orglt);

        // 获取检查组组长下拉框信息
        List<BsAepeople> aeheadmanlt = new ArrayList<BsAepeople>();
        String aeheadmans = af.getAeheadman();
        if (StringUtils.isNotBlank(aeheadmans)) {
            String[] aeheadmanArr = aeheadmans.split(",");
            for (int i = 0; i < aeheadmanArr.length; i++) {
                BsAepeople people = new BsAepeople();
                String[] temp = aeheadmanArr[i].split("--");
                if (temp.length == 2) {
                    people.setCertno(temp[1]);
                    people.setPepname(temp[0]);
                }
                aeheadmanlt.add(people);
            }
        }
        request.setAttribute("aeheadmans", aeheadmanlt);

        // 获取检查组主查人下拉框信息
        List<BsAepeople> aemasterlt = new ArrayList<BsAepeople>();
        String aemasters = af.getAemaster();
        if (StringUtils.isNotBlank(aemasters)) {
            String[] aemasterArr = aemasters.split(",");
            for (int i = 0; i < aemasterArr.length; i++) {
                BsAepeople people = new BsAepeople();
                String[] temp = aemasterArr[i].split("--");
                if (temp.length == 2) {
                    people.setCertno(temp[1]);
                    people.setPepname(temp[0]);
                }
                aemasterlt.add(people);
            }
        }
        request.setAttribute("aemasters", aemasterlt);

        // 获取检查组成员下拉框信息
        List<BsAepeople> aeotherlt = new ArrayList<BsAepeople>();
        String aeothers = af.getAeother();
        if (StringUtils.isNotBlank(aeothers)) {
            String[] aeotherArr = aeothers.split(",");
            for (int i = 0; i < aeotherArr.length; i++) {
                BsAepeople people = new BsAepeople();
                String[] temp = aeotherArr[i].split("--");
                if (temp.length == 2) {
                    people.setCertno(temp[1]);
                    people.setPepname(temp[0]);
                }
                aeotherlt.add(people);
            }
        }
        request.setAttribute("aeothers", aeotherlt);

        //将立项信息赋予工作检查记录，用于页面值初始化
        RequestUtil.setFormFromBean(dyna, af);

        //获取立项信息中维护的检查记录当前序号
        dyna.set("maxino", (af != null && af.getMaxino() != null) ? String.valueOf(af.getMaxino())
                : "1");

        //获取登录人机构
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);

        request.setAttribute("inputBasis_title", bsorg.getName() + " 执法检查工作底稿");
        request.setAttribute("inputComing_title", bsorg.getName() + " 执法检查进场记录");
        request.setAttribute("inputGoaway_title", bsorg.getName() + " 执法检查离场会谈纪要");
        request.setAttribute("inputTalkSummary_title", bsorg.getName() + " 执法检查会谈纪要");
        request.setAttribute("inputFactBook_title", bsorg.getName() + " 事实认定书");
        request.setAttribute("inputInspectionAnl_title", bsorg.getName() + " 执法检查情况");

        //获取当前需要录入的工作检查记录对应的立项的立项内容
        if (StringUtils.isBlank(af.getAecontent())) {
            request.setAttribute("filed9", StringUtils.EMPTY);
        } else {
            request.setAttribute("filed9", af.getAecontent());
        }

        //获取当前需要录入的工作检查记录对应的立项的内部编号
        String aeInnerNo = af.getInnerno();
        if (StringUtils.isNotBlank(aeInnerNo)) {
            dyna.set("innerno", aeInnerNo);
        } else {
            dyna.set("innerno", StringUtils.EMPTY);
        }
        return mapping.findForward("toAeinspectionInitPage");

    }

    /**
     * 保存工作检查记录.
     */
    public ActionForward createAeinspection(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        if (this.isTokenValid(request)) {// 正常提交
            this.resetToken(request);// 清空当前会话中的token值
            // 如果当前用户不属于人行用户，则不能使用该功能
            if (!super.isPcbUser(request, response)) {
                this.printMessage(request, response, ERROR1, ATTR_ERROR);
                return null;
            }
            DynaActionForm dyna = (DynaActionForm) form;
            String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(),
                    request, response);
            String nowLogin = this.getPrivCredential(UserCredentialName.login.name(), request,
                    response);
            String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                    request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
            request.setAttribute("methodname", "toAeinspectionList");
            // 调用检查工作记录保存方法
            try {
                BsAeinspection bs = RequestUtil.getBeanFromParams(request, BsAeinspection.class);
                String inspectToken = request.getParameter("inspect_token");
                if (StringUtils.isNotBlank(inspectToken)) {
                    bs.setStat(inspectToken);
                }
                bs.setAeedorgno(request.getParameter("aeedorgnoToServer"));
                String aeedorgNo = bs.getAeedorgno();
                if (StringUtils.isNotBlank(aeedorgNo)) {
                    BsOrg org = systemBaseInfoManager.getOrgByNo(aeedorgNo);
                    bs.setAeedorgnm(org.getName());
                }
                // 从页面获取检查组组长
                bs.setAeheadman(request.getParameter("aeHeadmanToServer").replace(" ",
                        StringUtils.EMPTY));
                // 从页面获取检查组主查人
                String[] aemaster = request.getParameterValues("aemaster");
                if (aemaster != null && aemaster.length > 0) {
                    bs.setAemaster(StringUtil.join(aemaster, StringUtils.EMPTY, StringUtils.EMPTY,
                            ","));
                }
                // 从页面获取检查组其他成员
                String[] aeother = request.getParameterValues("aeother");
                if (aeother != null && aeother.length > 0) {
                    bs.setAeother(StringUtil.join(aeother, StringUtils.EMPTY, StringUtils.EMPTY,
                            ","));
                }
                String ino = dyna.get("aeno").toString() + "第【" + dyna.get("maxino").toString()
                        + "】号";
                bs.setIno(ino);
                /*
                 * 工作检查记录在保存内部编号时，需要判断当前立项信息的行内编号是否为空
                 * 如果为空，则不保存工作记录的行内编号。
                 */
                if (StringUtils.isBlank(dyna.get("innerno").toString())) {
                    bs.setInnerno(StringUtils.EMPTY);
                } else {
                    String innerno = dyna.get("innerno").toString() + "第【"
                            + dyna.get("maxino").toString() + "】号";
                    bs.setInnerno(innerno);
                }

                // 更新工作检查记录中的进场编号
                BsWorkcoming wc = enforceService.getWorkcoming(ino);
                if (wc != null) {
                    bs.setInrcdno(wc.getFiled10());
                } else {
                    bs.setInrcdno(StringUtils.EMPTY);
                }
                // 更新工作检查记录中的离场编号
                BsWorkgoaway wg = enforceService.getWorkgoaway(ino);
                if (wg != null) {
                    bs.setOutrcdno(wg.getFiled10());
                } else {
                    bs.setOutrcdno(StringUtils.EMPTY);
                }
                // 更新工作检查记录中的事实认定书编号
                BsFactbook fb = enforceService.getFactBook(ino);
                if (fb != null) {
                    bs.setActualrcdno(fb.getFiled7());
                } else {
                    bs.setActualrcdno(StringUtils.EMPTY);
                }
                FormFile file = null;
                String attchuuid = null;
                // 保存执法检查工作底稿附件
                file = (FormFile) dyna.get("aebasispath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    bs.setAebasis(attchuuid);
                }
                // 保存询问笔录附件
                file = (FormFile) dyna.get("enquirercdpath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    bs.setEnquirercd(attchuuid);
                }
                // 保存进场会谈记录附件
                file = (FormFile) dyna.get("inrcdpath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    bs.setInrcd(attchuuid);
                }
                // 保存离场会谈纪要附件
                file = (FormFile) dyna.get("outrcdpath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    bs.setOutrcd(attchuuid);
                }
                // 保存事实认定书附件
                file = (FormFile) dyna.get("actualrcdpath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    bs.setActualrcd(attchuuid);
                }
                // 保存创建人为当前登录人id
                bs.setCreator(nowLogin);
                bs.setCrtdate(new Date());
                bs.setCrtlogin(nowLogin);
                bs.setIsfinished(false);
                bs.setIsfinishedRefti(false);
                bs.setCanbeupdate(true);
                // 如果当前检查工作记录已经在数据库存在
                try {
                    enforceService.saveAeinspection(bs);
                    enforceService.updateAeStatu(dyna.getString("aeno"));
                } catch (DataIntegrityViolationException e) {
                    try {
                        /*
                         * 则删除当前该工作记录相关的其他信息
                         * 例如进场、离场、工作底稿、事实认定书、会谈纪要、执法情况统计等
                         */
                        enforceService.deleteAeinspectionInfo(inspectToken);
                    } catch (Exception e1) {
                        LOGGER.error("删除当前工作检查记录的所有相关信息失败", e1);
                    }
                    // 并提示用户
                    request.setAttribute(ATTR_MESSAGE,
                            "<b>保存行政执法登记操作失败,编号重复</b><br><b>失败原因:</b>因为在你保存执法工作检查记录之前有其他用户已经保存了工作检查编号 "
                                    + bs.getIno()
                                    + " 的工作检查信息<br><b>解决方法:</b>请重新点击'检查工作记录登记'菜单获取新工作检查编号，再输入保存!");
                    return mapping.findForward("toAdminEnforceMessage");
                }
                request.setAttribute(ATTR_MESSAGE, "保存行政执法工作检查记录【" + bs.getIno() + "】操作成功!");
                LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "保存检查工作记录",
                        "操作成功!"));
            } catch (Exception e) {
                LOGGER.error(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "保存检查工作记录",
                        "操作失败!"), e);
                request.setAttribute(ATTR_MESSAGE, "保存行政执法工作检查记录操作失败！错误原因：" + e.getMessage());
            }
            return mapping.findForward("toAdminEnforceMessage");
        } else {// 重复提交
            this.printMessage(request, response, String.format(ERROR2, "检查工作记录登记"), ATTR_ERROR);
            return null;
        }
    }

    /**
     * 判断当前工作检查记录是否已经完成了所有相关工作内容.<br>
     * 
     * 包括事实认定书、进场、立场、会谈纪要、工作底稿、工作检查记录统计情况等
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward isInputAllWork(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("判断当前工作检查记录是否已经完成了所有相关工作内容发生错误", e);
            return null;
        }
        String aeinspectionNo = request.getParameter("aeinspectionNo");
        String inspectToken = request.getParameter("inspect_token");
        String result = "{success:%s,msg:'%s必须录入'}";
        StringBuffer message = new StringBuffer();
        Boolean allHave = true;
        try {
            if (!enforceService.hasWorkcoming(aeinspectionNo, inspectToken)) {
                allHave = false;
                message.append("进场记录，");
            }
            if (!enforceService.hasWorktalksummary(aeinspectionNo, inspectToken)) {
                allHave = false;
                message.append("执法检查会谈纪要，");
            }
            if (!enforceService.hasWorkgoaway(aeinspectionNo, inspectToken)) {
                allHave = false;
                message.append("离场会谈纪要，");
            }
            if (!enforceService.hasFactBook(aeinspectionNo, inspectToken)) {
                allHave = false;
                message.append("事实认定书，");
            }
            if (!enforceService.hasWorkBasis(aeinspectionNo, inspectToken)) {
                allHave = false;
                message.append("工作底稿，");
            }
            if (!enforceService.hasInspectionAnl(aeinspectionNo, inspectToken)) {
                allHave = false;
                message.append("检查情况统计，");
            }
            out.print(String.format(result, allHave, message));
            return null;
        } catch (Exception e) {
            LOGGER.error("判断当前工作检查记录是否已经完成了所有相关工作内容发生错误", e);
            out.print("{success:false,msg:'查询成功错误" + e.getMessage() + "'}");
            return null;
        }
    }

    /**
     * 判断当前被检查机构是否已经录入了该立项的工作检查记录.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward isExsitAeInspc(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("判断当前被检查机构是否已经录入了该立项的工作检查记录发生错误", e);
            return null;
        }

        String aeno = request.getParameter("aeno");
        String aeedorgno = request.getParameter("aeedorgno");

        if (StringUtils.isBlank(aeno) || StringUtils.isBlank(aeedorgno)) {
            out.print("{success:false,msg:'系统错误，不能正确获取被检查机构和立项编号'}");
            return null;
        }

        try {
            Boolean isExsitAeInspc = enforceService.isExsitAeInspc(aeno, aeedorgno);
            out.print("{success:true,msg:'" + String.valueOf(isExsitAeInspc).toLowerCase() + "'}");
            return null;
        } catch (Exception e) {
            LOGGER.error("判断当前被检查机构是否已经录入了该立项的工作检查记录发生错误", e);
            out.print("{success:false,msg:'查询成功错误" + e.getMessage() + "'}");
            return null;
        }
    }

    /**
     * 保存工作底稿.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward saveBasis(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String nowLoginUserName = this.getPubCredential(UserCredentialName.nickname.name(),
                request, response);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        BsWorkbasis wb = new BsWorkbasis();
        wb.setFiled1(request.getParameter("field1"));
        wb.setFiled2(request.getParameter("field2"));
        wb.setFiled3(request.getParameter("field3"));
        wb.setFiled4(request.getParameter("field4"));
        wb.setFiled5(request.getParameter("field5"));
        wb.setFiled6(request.getParameter("field6"));
        wb.setFiled7(request.getParameter("field7"));
        wb.setFiled8(request.getParameter("field8"));
        wb.setFiled9(nowLoginUserName);
        wb.setFiled10(request.getParameter("field7")
                + DateFormatUtils.format(new Date(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSSSSS));
        wb.setFiled12(request.getParameter("field12"));

        if (StringUtils.isNotBlank(request.getParameter("aeorgno"))) {
            wb.setAeorgno(request.getParameter("aeorgno"));
        }
        if (StringUtils.isNotBlank(request.getParameter("aeorgnm"))) {
            wb.setAeorgnm(request.getParameter("aeorgnm"));
        }
        if (StringUtils.isNotBlank(request.getParameter("aeedorgno"))) {
            wb.setAeedorgno(request.getParameter("aeedorgno"));
        }
        if (StringUtils.isNotBlank(request.getParameter("aeedorgnm"))) {
            wb.setAeedorgnm(request.getParameter("aeedorgnm"));
        }
        // 保存当前录入界面的唯一标识
        String inspectToken = request.getParameter("inspect_token");
        if (StringUtils.isNotBlank(inspectToken)) {
            wb.setStat(inspectToken);
        }
        wb.setCreatedate(new Date());

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("保存工作底稿发生错误", e);
            return null;
        }
        try {
            enforceService.saveWorkBasis(wb);
        } catch (Exception e) {
            LOGGER.error("保存工作底稿发生错误", e);
            out.print("{success:false,msg:'保存工作底稿发生错误" + e.getMessage() + "'}");
            return null;
        }
        out.print("{success:true,msg:'保存工作底稿:" + wb.getFiled10() + "成功'}");
        return null;
    }

    /**
     * 保存工作底稿.<br>
     * 
     * 用于修改检查工作记录时
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward saveBasisAtUpdateInspc(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String nowLoginUserName = this.getPubCredential(UserCredentialName.nickname.name(),
                request, response);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        BsWorkbasis wb = new BsWorkbasis();
        wb.setFiled1(request.getParameter("field1"));
        wb.setFiled2(request.getParameter("field2"));
        wb.setFiled3(request.getParameter("field3"));
        wb.setFiled4(request.getParameter("field4"));
        wb.setFiled5(request.getParameter("field5"));
        wb.setFiled6(request.getParameter("field6"));
        wb.setFiled7(request.getParameter("field7"));
        wb.setFiled8(request.getParameter("field8"));
        wb.setFiled9(nowLoginUserName);
        wb.setFiled10(request.getParameter("field7")
                + DateFormatUtils.format(new Date(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSSSSS));
        wb.setFiled12(request.getParameter("field12"));
        if (StringUtils.isNotBlank(request.getParameter("field7"))) {
            BsAeinspection aeinspec = enforceService.getAeinspectionByIno(request
                    .getParameter("field7"));
            if (aeinspec != null) {
                wb.setAeorgno(aeinspec.getAeorgno());
                wb.setAeorgnm(aeinspec.getAeorgnm());
                wb.setAeedorgno(aeinspec.getAeedorgno());
                wb.setAeedorgnm(aeinspec.getAeedorgnm());
            }
        }
        // 保存当前录入界面的唯一标识
        String inspectToken = request.getParameter("inspect_token");
        if (StringUtils.isNotBlank(inspectToken)) {
            wb.setStat(inspectToken);
        }
        wb.setCreatedate(new Date());

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("保存工作底稿发生错误", e);
            return null;
        }
        try {
            enforceService.saveWorkBasis(wb);
        } catch (Exception e) {
            LOGGER.error("保存工作底稿发生错误", e);
            out.print("{success:false,msg:'保存工作底稿发生错误" + e.getMessage() + "'}");
            return null;
        }
        out.print("{success:true,msg:'保存工作底稿:" + wb.getFiled10() + "成功'}");
        return null;
    }

    /**
     * 查询工作底稿.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward queryBasises(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String aeinspectionNo = request.getParameter("aeinspectionNo");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("查询工作底稿发生错误", e);
            return null;
        }
        List<BsWorkbasis> lt = null;
        try {
            lt = enforceService.queryWorkBasis(aeinspectionNo);
        } catch (Exception e) {
            LOGGER.error("查询工作底稿发生错误", e);
            out.print("{root:[]}");
            return null;
        }
        JSONArray jsonObject = JSONArray.fromObject(lt);

        String outputStr = jsonObject.toString();
        outputStr = "{\"root\":" + outputStr + "}";
        if (out != null) {
            out.print(outputStr);
        }

        return null;
    }

    /**
     * 分页查询工作底稿.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward pageBasises(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String aeinspectionNo = request.getParameter("aeinspectionNo");
        String inspectToken = request.getParameter("inspect_token");
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
        Integer pageNo = start / limit + 1;
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("分页查询工作底稿发生错误", e);
        }
        Page page = null;
        try {
            page = enforceService.pageWorkBasis(aeinspectionNo, pageNo, limit, inspectToken);
        } catch (Exception e) {
            LOGGER.error("分页查询工作底稿发生错误", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        JSONArray jsonObject = JSONArray.fromObject(page.getResult(), JSON_CONFIG);

        String outputStr = jsonObject.toString();
        outputStr = "{totalProperty:" + page.getTotalCount() + ",\"root\":" + outputStr + "}";
        // System.out.println(outputStr);
        if (out != null) {
            out.print(outputStr);
        }

        return null;
    }

    /**
     * 获取工作底稿检查详情.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getBasisContent(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("获取工作底稿检查详情发生错误", e);
        }
        BsWorkbasis wb = null;
        try {
            wb = enforceService.getWorkBasisById(id);
        } catch (Exception e) {
            LOGGER.error("获取工作底稿检查详情发生错误", e);
            out.print("{root:[]}");
            return null;
        }
        // JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
        // jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
        // jsonConfig.setExcludes(new String[] { "createdate", "updateate" });
        BsWorkbasis newWb = new BsWorkbasis();
        newWb.setId(id);
        newWb.setFiled5(wb.getFiled5());
        //newWb.setFiled5(StringUtils.rightPad(wb.getFiled5(), 100, "如需查看更多详情，请导出"));
        JSONObject jsonObject = JSONObject.fromObject(newWb);

        String outputStr = jsonObject.toString();
        // outputStr = "{\"root\":" + outputStr + "}";
        if (out != null) {
            out.print(outputStr);
        }
        return null;
    }

    /**
     * 删除工作底稿检查详情.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward deleteBasis(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("删除工作底稿检查详情发生错误", e);
        }
        try {
            enforceService.deleteWorkBasis(id);
            out.print("{success:true}");
        } catch (Exception e) {
            LOGGER.error("删除工作底稿检查详情发生错误", e);
            out.print("{success:false}");
        }
        return null;
    }

    /**
     * 保存进场记录.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward saveComing(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String nowLoginUserName = this.getPubCredential(UserCredentialName.nickname.name(),
                request, response);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("保存进场记录发生错误", e);
            return null;
        }

        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);

        BsWorkcoming wc = null;
        if (StringUtils.isNotBlank(request.getParameter("field7"))) {
            /*目前录入工作检查记录者只会同一时间录入一笔工作检查记录，制度上将不允许并发操作
             * ，因此一旦存在进场记录，编号就维持不变
             */
            //  wc = enforceService.getWorkcoming(request.getParameter("field7"),
            //  request.getParameter("inspect_token"));
            wc = enforceService.getWorkcoming(request.getParameter("field7"));
            Boolean isNew = false;
            if (wc == null) {
                wc = new BsWorkcoming();
                wc.setCreatedate(new Date());
                // 获取执法检查编号
                BsNogenerate bs = noGenerator.getNoGenerate(loginOrgNo, noGenerator.getYear());
                if (bs == null) {
                    wc.setFiled10("未明确编号"
                            + UUID.randomUUID().toString().replace("-", StringUtils.EMPTY));
                } else {
                    wc.setFiled10(noGenerator.generateNoStr(bs.getComeinnotext(),
                            bs.getComeinnoyear(), String.valueOf(bs.getComeinindex())));
                }
                isNew = true;
            } else {
                wc.setUpdateate(new Date());
            }
            wc.setFiled1(request.getParameter("field1"));
            wc.setFiled2(request.getParameter("field2"));
            wc.setFiled3(request.getParameter("field3"));
            wc.setFiled4(request.getParameter("field4"));
            wc.setFiled5(request.getParameter("field5"));
            wc.setFiled6(request.getParameter("field6"));
            wc.setFiled7(request.getParameter("field7"));
            wc.setFiled8(request.getParameter("field8"));
            wc.setFiled9(nowLoginUserName);
            wc.setFiled11(request.getParameter("field11"));
            wc.setFiled12(request.getParameter("field12"));
            wc.setFiled13(request.getParameter("field13"));
            if (StringUtils.isNotBlank(request.getParameter("aeorgno"))) {
                wc.setAeorgno(request.getParameter("aeorgno"));
            }
            if (StringUtils.isNotBlank(request.getParameter("aeorgnm"))) {
                wc.setAeorgnm(request.getParameter("aeorgnm"));
            }
            if (StringUtils.isNotBlank(request.getParameter("aeedorgno"))) {
                wc.setAeedorgno(request.getParameter("aeedorgno"));
            }
            if (StringUtils.isNotBlank(request.getParameter("aeedorgnm"))) {
                wc.setAeedorgnm(request.getParameter("aeedorgnm"));
            }
            // 保存当前录入界面的唯一标识
            String inspectToken = request.getParameter("inspect_token");
            if (StringUtils.isNotBlank(inspectToken)) {
                wc.setStat(inspectToken);
            }
            try {
                enforceService.saveWorkcoming(wc, isNew, loginOrgNo, noGenerator.getYear());
            } catch (Exception e) {
                LOGGER.error("保存进场记录发生错误", e);
                out.print("{success:false,msg:'保存进场记录发生错误" + e.getMessage() + "'}");
                return null;
            }
            // 更新行政执法检查记录进场记录编号
            try {
                String ino = request.getParameter("field7");
                BsAeinspection aeinspt = enforceService.getAeinspectionByIno(ino);
                if (aeinspt != null) {
                    aeinspt.setInrcdno(wc.getFiled10());
                    enforceService.saveAeinspection(aeinspt);
                }
            } catch (Exception e) {
                LOGGER.error("保存进场记录发生错误", e);
            }
            if (out != null) {
                out.print("{success:true,msg:'保存进场记录:" + wc.getFiled10() + "成功'}");
            }
        } else {
            if (out != null) {
                out.print("{success:true,msg:'保存进场记录失败，不能获取工作记录编号'}");
            }
        }
        return null;
    }

    /**
     * 分页查询进场记录.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward pageComings(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String aeinspectionNo = request.getParameter("aeinspectionNo");
        String inspectToken = request.getParameter("inspect_token");
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
        Integer pageNo = start / limit + 1;
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("分页查询进场记录发生错误", e);
        }
        Page page = null;
        try {
            page = enforceService.pageWorkcoming(aeinspectionNo, pageNo, limit, inspectToken);
        } catch (Exception e) {
            LOGGER.error("分页查询进场记录发生错误", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        JSONArray jsonObject = JSONArray.fromObject(page.getResult());

        String outputStr = jsonObject.toString();
        outputStr = "{totalProperty:" + page.getTotalCount() + ",\"root\":" + outputStr + "}";
        if (out != null) {
            out.print(outputStr);
        }
        return null;
    }

    /**
     * 获取进场记录检查详情.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getComingContent(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("获取进场记录检查详情", e);
        }
        BsWorkcoming wc = null;
        try {
            wc = enforceService.getWorkcomingById(id);
        } catch (Exception e) {
            LOGGER.error("获取进场记录检查详情", e);
            out.print("{root:[]}");
            return null;
        }
        // JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
        // jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
        // jsonConfig.setExcludes(new String[] { "createdate", "updateate" });
        BsWorkcoming newWb = new BsWorkcoming();
        newWb.setId(id);
        newWb.setFiled5(wc.getFiled5());
        JSONObject jsonObject = JSONObject.fromObject(newWb);

        String outputStr = jsonObject.toString();
        // outputStr = "{\"root\":" + outputStr + "}";
        if (out != null) {
            out.print(outputStr);
        }
        return null;
    }

    /**
     * 保存离场记录.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward saveGoaway(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String nowLoginUserName = this.getPubCredential(UserCredentialName.nickname.name(),
                request, response);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("保存离场记录发生错误", e);
            return null;
        }
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        BsWorkgoaway wb = null;
        if (StringUtils.isNotBlank(request.getParameter("field14"))) {
            // wb = enforceService.getWorkgoaway(request.getParameter("field14"),
            // request.getParameter("inspect_token"));
            wb = enforceService.getWorkgoaway(request.getParameter("field14"));
            Boolean isNew = false;
            if (wb == null) {
                wb = new BsWorkgoaway();
                wb.setCreatedate(new Date());
                BsNogenerate bs = noGenerator.getNoGenerate(loginOrgNo, noGenerator.getYear());
                if (bs == null) {
                    wb.setFiled10("未明确编号"
                            + UUID.randomUUID().toString().replace("-", StringUtils.EMPTY));
                } else {
                    String filed10 = noGenerator.generateNoStr(bs.getAwaynotext(),
                            bs.getAwaynoyear(), String.valueOf(bs.getAwaynoindex()));
                    wb.setFiled10(filed10);
                }
                isNew = true;
            } else {
                wb.setUpdateate(new Date());
            }
            wb.setFiled1(request.getParameter("field1"));
            wb.setFiled2(request.getParameter("field2"));
            wb.setFiled3(request.getParameter("field3"));
            wb.setFiled4(request.getParameter("field4"));
            wb.setFiled5(request.getParameter("field5"));
            wb.setFiled6(request.getParameter("field6"));
            wb.setFiled7(request.getParameter("field7"));
            wb.setFiled8(request.getParameter("field8"));
            wb.setFiled9(nowLoginUserName);
            wb.setFiled11(request.getParameter("field11"));
            wb.setFiled12(request.getParameter("field12"));
            wb.setFiled13(request.getParameter("field13"));
            wb.setFiled14(request.getParameter("field14"));
            wb.setFiled15(request.getParameter("field15"));
            if (StringUtils.isNotBlank(request.getParameter("aeorgno"))) {
                wb.setAeorgno(request.getParameter("aeorgno"));
            }
            if (StringUtils.isNotBlank(request.getParameter("aeorgnm"))) {
                wb.setAeorgnm(request.getParameter("aeorgnm"));
            }
            if (StringUtils.isNotBlank(request.getParameter("aeedorgno"))) {
                wb.setAeedorgno(request.getParameter("aeedorgno"));
            }
            if (StringUtils.isNotBlank(request.getParameter("aeedorgnm"))) {
                wb.setAeedorgnm(request.getParameter("aeedorgnm"));
            }
            // 保存当前录入界面的唯一标识
            String inspectToken = request.getParameter("inspect_token");
            if (StringUtils.isNotBlank(inspectToken)) {
                wb.setStat(inspectToken);
            }
            try {
                enforceService.saveWorkgoaway(wb, isNew, loginOrgNo, noGenerator.getYear());
            } catch (Exception e) {
                e.printStackTrace();
                out.print("{success:false,msg:'保存离场记录发生错误" + e.getMessage() + "'}");
                return null;
            }
            // 更新行政执法检查记录离场记录编号
            try {
                String ino = request.getParameter("field14");
                BsAeinspection aeinspt = enforceService.getAeinspectionByIno(ino);
                if (aeinspt != null) {
                    aeinspt.setOutrcdno(wb.getFiled10());
                    enforceService.saveAeinspection(aeinspt);
                }
            } catch (Exception e) {
                LOGGER.error("保存离场记录发生错误", e);
            }
            if (out != null) {
                out.print("{success:true,msg:'保存离场记录:" + wb.getFiled10() + "成功'}");
            }
        } else {
            if (out != null) {
                out.print("{success:true,msg:'保存离场记录失败，不能获取工作记录编号'}");
            }
        }

        return null;
    }

    /**
     * 分页查询离场记录.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward pageGoaways(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String aeinspectionNo = request.getParameter("aeinspectionNo");
        String inspectToken = request.getParameter("inspect_token");
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
        Integer pageNo = start / limit + 1;
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("分页查询离场记录发生错误", e);
        }
        Page page = null;
        try {
            page = enforceService.pageWorkgoaway(aeinspectionNo, pageNo, limit, inspectToken);
        } catch (Exception e) {
            LOGGER.error("分页查询离场记录发生错误", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        JSONArray jsonObject = JSONArray.fromObject(page.getResult());

        String outputStr = jsonObject.toString();
        outputStr = "{totalProperty:" + page.getTotalCount() + ",\"root\":" + outputStr + "}";
        if (out != null) {
            out.print(outputStr);
        }
        return null;
    }

    /**
     * 获取离场记录检查详情.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getGoawayContent(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("获取离场记录检查详情发生错误", e);
        }
        BsWorkgoaway wb = null;
        try {
            wb = enforceService.getWorkgoawayById(id);
        } catch (Exception e) {
            LOGGER.error("获取离场记录检查详情发生错误", e);
            out.print("{root:[]}");
            return null;
        }
        // JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
        // jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
        // jsonConfig.setExcludes(new String[] { "createdate", "updateate" });
        BsWorkgoaway newWb = new BsWorkgoaway();
        newWb.setId(id);
        newWb.setFiled8(wb.getFiled8());
        JSONObject jsonObject = JSONObject.fromObject(newWb);

        String outputStr = jsonObject.toString();
        // outputStr = "{\"root\":" + outputStr + "}";
        if (out != null) {
            out.print(outputStr);
        }
        return null;
    }

    /**
     * 保存会谈纪要.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward saveTalkSummary(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String nowLoginUserName = this.getPubCredential(UserCredentialName.nickname.name(),
                request, response);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("保存会谈纪要发生错误", e);
            return null;
        }
        BsWorktalksummary ws = null;
        if (StringUtils.isNotBlank(request.getParameter("field16"))) {
            //  ws = enforceService.getWorktalksummary(request.getParameter("field16"),
            //  request.getParameter("inspect_token"));
            ws = enforceService.getWorktalksummary(request.getParameter("field16"));
            if (ws == null) {
                ws = new BsWorktalksummary();
                ws.setCreatedate(new Date());
                ws.setFiled12(request.getParameter("field16")
                        + DateFormatUtils
                                .format(new Date(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSSSSS));
            } else {
                ws.setUpdateate(new Date());
            }
            ws.setFiled1(request.getParameter("field1"));
            ws.setFiled2(request.getParameter("field2"));
            ws.setFiled3(request.getParameter("field3"));
            ws.setFiled4(request.getParameter("field4"));
            ws.setFiled5(request.getParameter("field5"));
            ws.setFiled6(request.getParameter("field6"));
            ws.setFiled7(request.getParameter("field7"));
            ws.setFiled8(request.getParameter("field8"));
            ws.setFiled9(request.getParameter("field15"));
            ws.setFiled10(request.getParameter("field10"));
            ws.setFiled11(nowLoginUserName);
            ws.setFiled13(request.getParameter("field13"));
            ws.setFiled14(request.getParameter("field14"));
            ws.setFiled15(request.getParameter("field15"));
            ws.setFiled16(request.getParameter("field16"));
            ws.setFiled17(request.getParameter("field17"));
            if (StringUtils.isNotBlank(request.getParameter("aeorgno"))) {
                ws.setAeorgno(request.getParameter("aeorgno"));
            }
            if (StringUtils.isNotBlank(request.getParameter("aeorgnm"))) {
                ws.setAeorgnm(request.getParameter("aeorgnm"));
            }
            if (StringUtils.isNotBlank(request.getParameter("aeedorgno"))) {
                ws.setAeedorgno(request.getParameter("aeedorgno"));
            }
            if (StringUtils.isNotBlank(request.getParameter("aeedorgnm"))) {
                ws.setAeedorgnm(request.getParameter("aeedorgnm"));
            }
            // 保存当前录入界面的唯一标识
            String inspectToken = request.getParameter("inspect_token");
            if (StringUtils.isNotBlank(inspectToken)) {
                ws.setStat(inspectToken);
            }
            try {
                enforceService.saveWorktalksummary(ws);
            } catch (Exception e) {
                LOGGER.error("保存会谈纪要发生错误", e);
                out.print("{success:false,msg:'保存会谈纪要发生错误" + e.getMessage() + "'}");
                return null;
            }
            if (out != null) {
                out.print("{success:true,msg:'保存会谈纪要:" + ws.getFiled12() + "成功'}");
            }
        } else {
            if (out != null) {
                out.print("{success:true,msg:'保存会谈纪要失败，不能获取工作记录编号'}");
            }
        }

        return null;

    }

    /**
     * 分页查询会谈纪要.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward pageTalkSummarys(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String aeinspectionNo = request.getParameter("aeinspectionNo");
        String inspectToken = request.getParameter("inspect_token");
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
        Integer pageNo = start / limit + 1;
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("分页查询会谈纪要发生错误", e);
        }
        Page page = null;
        try {
            page = enforceService.pageWorktalksummary(aeinspectionNo, pageNo, limit, inspectToken);
        } catch (Exception e) {
            LOGGER.error("分页查询会谈纪要发生错误", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        JSONArray jsonObject = JSONArray.fromObject(page.getResult());

        String outputStr = jsonObject.toString();
        outputStr = "{totalProperty:" + page.getTotalCount() + ",\"root\":" + outputStr + "}";
        if (out != null) {
            out.print(outputStr);
        }
        return null;
    }

    /**
     * 获取会谈纪要检查详情.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getTalkSummaryContent(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("获取会谈纪要检查详情发生错误", e);
        }
        BsWorktalksummary wb = null;
        try {
            wb = enforceService.getWorktalksummaryById(id);
        } catch (Exception e) {
            LOGGER.error("获取会谈纪要检查详情发生错误", e);
            out.print("{root:[]}");
            return null;
        }
        // JsonConfig jsonConfig = new JsonConfig(); // 建立配置文件
        // jsonConfig.setIgnoreDefaultExcludes(false); // 设置默认忽略
        // jsonConfig.setExcludes(new String[] { "createdate", "updateate" });
        BsWorktalksummary newWb = new BsWorktalksummary();
        newWb.setId(id);
        newWb.setFiled10(wb.getFiled10());
        JSONObject jsonObject = JSONObject.fromObject(newWb);

        String outputStr = jsonObject.toString();
        // outputStr = "{\"root\":" + outputStr + "}";
        if (out != null) {
            out.print(outputStr);
        }
        return null;
    }

    /**
     * 获取问题概况树.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getProblemSummay(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String parent = request.getParameter("node");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("获取问题概况树发生错误", e);
        }
        List<JSONObject> list = new ArrayList<JSONObject>();
        try {
            List<BsDictionary> resultOrg = enforceService.getProblemSummary(parent);
            for (int i = 0; i < resultOrg.size(); i++) {
                BsDictionary bc = resultOrg.get(i);
                JSONObject object = new JSONObject();
                object.put("id", bc.getId());
                object.put("text", bc.getDvalue());
                object.put("leaf", Boolean.valueOf(bc.getFlag()));
                object.put("checked", Boolean.valueOf(bc.getFlag()));
                list.add(object);
            }
        } catch (Exception e) {
            LOGGER.error("获取问题概况树发生错误", e);
        }
        JSONArray array = JSONArray.fromObject(list);
        String outputStr = array.toString();
        if (out != null) {
            out.print(outputStr);
        }

        return null;
    }

    /**
     * 根据选择的问题概况生成事实认定书内容.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward generateFactBook(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String selectIds = request.getParameter("selectIds");
        // String factBookNo = request.getParameter("factBookNo");
        // String aeedOrg = request.getParameter("aeedOrg");
        // String nowloginerOrgNo = this.getPubCredential(
        // UserCredentialName.organization.name(), request, response);
        // BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
        String[] selectIdArry = selectIds.split(",");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("根据选择的问题概况生成事实认定书内容发生错误", e);
        }

        BsDictionary dc = null;
        StringBuffer content = new StringBuffer();
        try {
            for (int i = 0; i < selectIdArry.length; i++) {
                if (StringUtils.isNotBlank(selectIdArry[i])) {
                    dc = enforceService.getBsDictionary(selectIdArry[i]);
                    content.append(dc.getDSumry()).append("<BR>");
                }
            }
        } catch (Exception e) {
            LOGGER.error("根据选择的问题概况生成事实认定书内容发生错误", e);
        }
        StringBuffer result = new StringBuffer("{\"content\":\"");
        // result.append(enforceService.generateFactBook(bsorg.getName(),
        // factBookNo,
        // aeedOrg, content.toString()));
        result.append(content);
        result.append("\"}");
        String outputStr = result.toString();
        if (out != null) {
            out.print(outputStr);
        }
        return null;
    }

    /**
     * 保存事实认定书内容.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward saveFactBook(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String aeNo = request.getParameter("aeno");
        String factBookContent = request.getParameter("factBookContent");
        String inspectionNo = request.getParameter("aeinspectionNo");
        String aeedOrg = request.getParameter("aeedOrg");
        String filed6 = request.getParameter("filed6");
        String filed9 = request.getParameter("filed9");
        String filed12 = request.getParameter("filed12");
        String selectedwtgk = request.getParameter("selectedwtgk");
        String aeorgno = request.getParameter("aeorgno");
        String aeorgnm = request.getParameter("aeorgnm");
        String aeedorgno = request.getParameter("aeedorgno");
        String aeedorgnm = request.getParameter("aeedorgnm");

        // 保存当前录入界面的唯一标识
        String inspectToken = request.getParameter("inspect_token");

        String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("保存事实认定书内容发生错误", e);
        }
        String factBookNo = null;
        try {
            factBookNo = enforceService.saveFactBook(aeNo, inspectionNo, bsorg.getName(),
                    nowloginerOrgNo, aeedOrg, factBookContent, filed6, filed12, filed9,
                    selectedwtgk, aeorgno, aeorgnm, aeedorgno, aeedorgnm, inspectToken);
        } catch (Exception e) {
            LOGGER.error("保存事实认定书内容发生错误", e);
            if (out != null) {
                out.print("{success:true,msg:'保存事实认定书:" + factBookNo + "失败'}");
            }
            return null;
        }
        // 更新行政执法检查记录事实认定书编号
        try {
            BsAeinspection aeinspt = enforceService.getAeinspectionByIno(inspectionNo);
            if (aeinspt != null) {
                aeinspt.setActualrcdno(factBookNo);
                enforceService.saveAeinspection(aeinspt);
            }
        } catch (Exception e) {
            LOGGER.error("保存事实认定书内容发生错误", e);
        }
        if (out != null) {
            out.print("{success:true,msg:'保存事实认定书:" + factBookNo + "成功'}");
        }
        return null;
    }

    /**
     * 分页查询事实认定书.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward pageFactBook(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String aeinspectionNo = request.getParameter("aeinspectionNo");
        String inspectToken = request.getParameter("inspect_token");
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
        Integer pageNo = start / limit + 1;
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("分页查询事实认定书发生错误", e);
        }
        Page page = null;
        try {
            page = enforceService.pageFactBook(aeinspectionNo, pageNo, limit, inspectToken);
        } catch (Exception e) {
            LOGGER.error("分页查询事实认定书发生错误", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        JSONArray jsonObject = JSONArray.fromObject(page.getResult());
        String outputStr = jsonObject.toString();
        outputStr = "{totalProperty:" + page.getTotalCount() + ",\"root\":" + outputStr + "}";
        if (out != null) {
            out.print(outputStr);
        }
        return null;
    }

    /**
     * 保存执法统计情况.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward saveInspectionAnl(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String nowLoginUserName = this.getPubCredential(UserCredentialName.nickname.name(),
                request, response);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("保存执法统计情况发生错误", e);
            return null;
        }
        BsAeinspectionAnl wb = null;
        if (StringUtils.isNotBlank(request.getParameter("aeinspectionno"))) {
            // wb = enforceService.getInspectionAnl(request.getParameter("aeinspectionno"),
            // request.getParameter("inspect_token"));
            wb = enforceService.getInspectionAnl(request.getParameter("aeinspectionno"));
            Date now = new Date();
            if (wb == null) {
                wb = new BsAeinspectionAnl();
                wb.setAeinspectionno(request.getParameter("aeinspectionno"));
                wb.setAeno(request.getParameter("aeno"));
                wb.setCreatedate(new Date());
                wb.setReporter(nowLoginUserName);
                wb.setAnldate(now);
            } else {
                wb.setUpdateate(new Date());
            }
            wb.setA1(request.getParameter("a1"));
            wb.setB1(request.getParameter("b1"));
            Long zero = Long.valueOf(0);
            try {
                wb.setC1(Long.valueOf(request.getParameter("c1")));
            } catch (Exception e) {
                wb.setC1(zero);
            }
            try {
                wb.setD1(Long.valueOf(request.getParameter("d1")));
            } catch (Exception e) {
                wb.setD1(zero);
            }
            try {
                wb.setE1(Long.valueOf(request.getParameter("e1")));
            } catch (Exception e) {
                wb.setE1(zero);
            }
            try {
                wb.setF1(Long.valueOf(request.getParameter("f1")));
            } catch (Exception e) {
                wb.setF1(zero);
            }
            try {
                wb.setG1(Long.valueOf(request.getParameter("g1")));
            } catch (Exception e) {
                wb.setG1(zero);
            }
            try {
                wb.setH1(Long.valueOf(request.getParameter("h1")));
            } catch (Exception e) {
                wb.setH1(zero);
            }
            try {
                wb.setI1(Long.valueOf(request.getParameter("i1")));
            } catch (Exception e) {
                wb.setI1(zero);
            }
            try {
                wb.setJ1(Long.valueOf(request.getParameter("j1")));
            } catch (Exception e) {
                wb.setC1(zero);
            }
            try {
                wb.setK1(Long.valueOf(request.getParameter("k1")));
            } catch (Exception e) {
                wb.setK1(zero);
            }
            try {
                wb.setL1(Long.valueOf(request.getParameter("l1")));
            } catch (Exception e) {
                wb.setL1(zero);
            }
            try {
                wb.setM1(Long.valueOf(request.getParameter("m1")));
            } catch (Exception e) {
                wb.setM1(zero);
            }
            wb.setN1(request.getParameter("n1"));
            try {
                wb.setP1(new BigDecimal(request.getParameter("p1")));
            } catch (Exception e) {
                wb.setP1(BigDecimal.ZERO);
            }
            try {
                wb.setQ1(new BigDecimal(request.getParameter("q1")));
            } catch (Exception e) {
                wb.setQ1(BigDecimal.ZERO);
            }
            if (StringUtils.isNotBlank(request.getParameter("aeorgno"))) {
                wb.setAeorgno(request.getParameter("aeorgno"));
            }
            if (StringUtils.isNotBlank(request.getParameter("aeorgnm"))) {
                wb.setAeorgnm(request.getParameter("aeorgnm"));
            }
            if (StringUtils.isNotBlank(request.getParameter("aeedorgno"))) {
                wb.setAeedorgno(request.getParameter("aeedorgno"));
            }
            if (StringUtils.isNotBlank(request.getParameter("aeedorgnm"))) {
                wb.setAeedorgnm(request.getParameter("aeedorgnm"));
            }
            // 保存当前录入界面的唯一标识
            String inspectToken = request.getParameter("inspect_token");
            if (StringUtils.isNotBlank(inspectToken)) {
                wb.setStat(inspectToken);
            }
            try {
                enforceService.saveInspectionAnl(wb);
            } catch (Exception e) {
                LOGGER.error("保存执法统计情况发生错误", e);
                out.print("{success:false,msg:'保存执法统计情况发生错误" + e.getMessage() + "'}");
                return null;
            }
            if (out != null) {
                out.print("{success:true,msg:'保存执法统计情况:" + wb.getAeinspectionno() + "成功'}");
            }
        } else {
            if (out != null) {
                out.print("{success:true,msg:'保存执法统计情况失败，不能获取工作记录编号'}");
            }
        }
        return null;
    }

    /**
     * 查询执法统计情况.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward pageInspectionAnl(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String aeinspectionNo = request.getParameter("aeinspectionNo");
        String inspectToken = request.getParameter("inspect_token");
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
        Integer pageNo = start / limit + 1;
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("查询执法统计情况发生错误", e);
        }
        Page page = null;
        try {
            page = enforceService.pageInspectionAnl(aeinspectionNo, pageNo, limit, inspectToken);
        } catch (Exception e) {
            LOGGER.error("查询执法统计情况发生错误", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        JSONArray jsonObject = JSONArray.fromObject(page.getResult());

        String outputStr = jsonObject.toString();
        outputStr = "{totalProperty:" + page.getTotalCount() + ",\"root\":" + outputStr + "}";
        if (out != null) {
            // System.out.println(outputStr);
            out.print(outputStr);
        }

        return null;
    }

    /**
     * 生成事实认定书Word文件.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward generateWord(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String from = request.getParameter("from");
        String wordTemplateId = request.getParameter("wordTemplateId").toString();
        String aeinspectionNo = null;
        if (StringUtils.equals(from, "detail")) {
            aeinspectionNo = request.getParameter("ino");
        } else {
            aeinspectionNo = request.getParameter("aeno") + "第【" + request.getParameter("maxino")
                    + "】号";
        }
        String inspectToken = request.getParameter("inspect_token");
        BsFactbook fb = null;
        try {
            fb = enforceService.getFactBook(aeinspectionNo, inspectToken);
        } catch (Exception e) {
            LOGGER.error("生成事实认定书Word文件发生错误", e);
        }
        if (fb == null) {
            super.printMessage(request, response, "还未添加事实认定书" + aeinspectionNo + "，请添加后再导出！",
                    ATTR_ERROR);
            return null;
        }
        Map<String, String> map = new HashMap<String, String>();
        map.put("FILED10", fb.getFiled10());
        map.put("FILED7", fb.getFiled7());
        map.put("FILED8", fb.getAeedorgnm());
        /*    
         * 表格内的param1使用“\r”没有换行，而表格内的参数使用“(char)11”换行；
         * 表格外的参数param3使用“\r”换行，而表格外的参数param4使用“(char)11”换行
         * 去除字符串中的空格,回车,换行符,制表符
         */
        String f5 = fb.getFiled5().replaceAll("<BR>", "\r    ").replaceAll("<br>", "\r    ")
                .replaceAll("</?[^>]+>", StringUtils.EMPTY)
                .replaceAll("<a>\\s*|\t|\r|\n</a>", StringUtils.EMPTY).replace("&nbsp;", " ");
        map.put("FILED5", StringEscapeUtils.unescapeHtml(f5));
        map.put("FILED6", fb.getFiled6());
        map.put("FILED12", fb.getFiled12());
        map.put("FILED9", fb.getFiled9());
        FileInputStream fis = null;
        OutputStream repos = null;
        try {
            fis = new FileInputStream(request.getSession().getServletContext()
                    .getRealPath(File.separator)
                    + FileHandler.WORD_TEMPLATE_FILE_PATH
                    + File.separator
                    + wordTemplateId
                    + ".doc");
            // 设置response的Header
            response.setContentType("application/vnd.ms-word");
            response.addHeader(
                    "Content-Disposition",
                    "attachment;filename="
                            + super.getDownloadFileName(fb.getFiled7(), StringUtils.EMPTY));
            repos = response.getOutputStream();
            wordGenerator.replaceDoc(fis, map, repos);
            repos.flush();
            return null;
        } catch (Exception e) {
            LOGGER.error("生成事实认定书Word文件发生错误", e);
            super.printMessage(request, response,
                    "事实认定书" + aeinspectionNo + "导出失败！错误原因:" + e.getMessage(), ATTR_ERROR);
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (repos != null) {
                    repos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成工作底稿Word文件.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward generateGzdgWord(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String wordTemplateId = request.getParameter("wordTemplateId").toString();
        String aeinspectionNo = request.getParameter("ino");
        String gzdgId = request.getParameter("gzdgId");
        FileInputStream fis = null;
        OutputStream repos = null;
        try {
            BsWorkbasis fb = enforceService.getWorkbasis(gzdgId);
            if (fb == null) {
                super.printMessage(request, response, "还未添加工作底稿" + aeinspectionNo + "，请添加后再导出！",
                        ATTR_ERROR);
                return null;
            }
            String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                    request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
            Map<String, String> map = new HashMap<String, String>();
            map.put("FILED1", fb.getFiled1());
            map.put("FILED2", fb.getFiled2());
            map.put("FILED3", fb.getFiled3());
            map.put("FILED4", fb.getFiled4());
            String htmlContent = fb.getFiled5().replaceAll("<BR>", "\r    ")
                    .replaceAll("<br>", "\r    ").replaceAll("</?[^>]+>", StringUtils.EMPTY)
                    .replaceAll("<a>\\s*|\t|\r|\n</a>", StringUtils.EMPTY).replace("&nbsp;", " ");
            map.put("FILED5", StringEscapeUtils.unescapeHtml(htmlContent));
            map.put("FILED6", fb.getFiled6());
            map.put("FILED7", fb.getFiled7());
            map.put("PCB_BANK", bsorg.getName());
            fis = new FileInputStream(request.getSession().getServletContext()
                    .getRealPath(File.separator)
                    + FileHandler.WORD_TEMPLATE_FILE_PATH
                    + File.separator
                    + wordTemplateId
                    + ".doc");
            // 设置response的Header
            response.setContentType("application/vnd.ms-word");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + super.getDownloadFileName(fb.getFiled7(), "底稿"));
            repos = response.getOutputStream();
            wordGenerator.replaceDoc(fis, map, repos);
            repos.flush();
            return null;
        } catch (Exception e) {
            LOGGER.error("生成工作底稿Word文件发生错误", e);
            super.printMessage(request, response,
                    "工作底稿" + aeinspectionNo + "导出失败！错误原因:" + e.getMessage(), ATTR_ERROR);
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (repos != null) {
                    repos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成进场记录Word文件.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward generateJcjlWord(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String wordTemplateId = request.getParameter("wordTemplateId").toString();
        String aeinspectionNo = request.getParameter("ino");
        String inspectToken = request.getParameter("inspect_token");
        FileInputStream fis = null;
        OutputStream repos = null;
        try {
            BsWorkcoming fb = enforceService.getWorkcoming(aeinspectionNo, inspectToken);
            if (fb == null) {
                super.printMessage(request, response, "还未添加进场会谈记录" + aeinspectionNo + "，请添加后再导出！",
                        ATTR_ERROR);
                return null;
            }
            String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                    request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
            Map<String, String> map = new HashMap<String, String>();
            map.put("FILED1", fb.getFiled1());
            map.put("FILED2", fb.getFiled2());
            map.put("FILED3", fb.getFiled3());
            map.put("FILED12", fb.getFiled12());
            String htmlContent = fb.getFiled5().replaceAll("<BR>", "\r    ")
                    .replaceAll("<br>", "\r    ").replaceAll("</?[^>]+>", StringUtils.EMPTY)
                    .replaceAll("<a>\\s*|\t|\r|\n</a>", StringUtils.EMPTY).replace("&nbsp;", " ");
            map.put("FILED5", StringEscapeUtils.unescapeHtml(htmlContent));
            map.put("FILED6", fb.getFiled6());
            map.put("FILED7", fb.getFiled7());
            map.put("PCB_BANK", bsorg.getName());
            fis = new FileInputStream(request.getSession().getServletContext()
                    .getRealPath(File.separator)
                    + FileHandler.WORD_TEMPLATE_FILE_PATH
                    + File.separator
                    + wordTemplateId
                    + ".doc");
            // 设置response的Header
            response.setContentType("application/vnd.ms-word");
            response.addHeader(
                    "Content-Disposition",
                    "attachment;filename="
                            + super.getDownloadFileName(fb.getFiled10(), StringUtils.EMPTY));
            repos = response.getOutputStream();
            wordGenerator.replaceDoc(fis, map, repos);
            repos.flush();
            return null;
        } catch (Exception e) {
            LOGGER.error("生成进场记录Word文件发生错误", e);
            super.printMessage(request, response,
                    "进场会谈记录" + aeinspectionNo + "导出失败！错误原因:" + e.getMessage(), ATTR_ERROR);
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (repos != null) {
                    repos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成离场会谈记录Word文件.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward generateLchtjlWord(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String wordTemplateId = request.getParameter("wordTemplateId").toString();
        String aeinspectionNo = request.getParameter("ino");
        String inspectToken = request.getParameter("inspect_token");
        FileInputStream fis = null;
        OutputStream repos = null;
        try {
            BsWorkgoaway fb = enforceService.getWorkgoaway(aeinspectionNo, inspectToken);
            if (fb == null) {
                super.printMessage(request, response, "还未添加离场会谈纪要" + aeinspectionNo + "，请添加后再导出！",
                        ATTR_ERROR);
                return null;
            }
            String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                    request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
            Map<String, String> map = new HashMap<String, String>();
            map.put("FILED1", fb.getFiled1());
            map.put("FILED2", fb.getFiled2());
            map.put("FILED3", fb.getFiled3());
            map.put("FILED4", fb.getFiled4());
            map.put("FILED5", fb.getFiled5());
            map.put("FILED6", fb.getFiled6());
            map.put("FILED7", fb.getFiled7());
            String htmlContent = fb.getFiled8().replaceAll("<BR>", "\r    ")
                    .replaceAll("<br>", "\r    ").replaceAll("</?[^>]+>", StringUtils.EMPTY)
                    .replaceAll("<a>\\s*|\t|\r|\n</a>", StringUtils.EMPTY).replace("&nbsp;", " ");
            map.put("FILED8", StringEscapeUtils.unescapeHtml(htmlContent));
            map.put("FILED10", fb.getFiled10());
            map.put("FILED11", fb.getFiled11());
            map.put("FILED12", fb.getFiled12());
            map.put("PCB_BANK", bsorg.getName());
            fis = new FileInputStream(request.getSession().getServletContext()
                    .getRealPath(File.separator)
                    + FileHandler.WORD_TEMPLATE_FILE_PATH
                    + File.separator
                    + wordTemplateId
                    + ".doc");
            // 设置response的Header
            response.setContentType("application/vnd.ms-word");
            response.addHeader(
                    "Content-Disposition",
                    "attachment;filename="
                            + super.getDownloadFileName(fb.getFiled10(), StringUtils.EMPTY));
            repos = response.getOutputStream();
            wordGenerator.replaceDoc(fis, map, repos);
            repos.flush();
            return null;
        } catch (Exception e) {
            LOGGER.error("生成离场会谈记录Word文件发生错误", e);
            super.printMessage(request, response,
                    "离场会谈纪要" + aeinspectionNo + "导出失败！错误原因:" + e.getMessage(), ATTR_ERROR);
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (repos != null) {
                    repos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成会谈纪要Word文件.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward generateHtjyWord(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String wordTemplateId = request.getParameter("wordTemplateId").toString();
        String aeinspectionNo = request.getParameter("ino");
        String inspectToken = request.getParameter("inspect_token");
        FileInputStream fis = null;
        OutputStream repos = null;
        try {
            BsWorktalksummary fb = enforceService.getWorktalksummary(aeinspectionNo, inspectToken);
            if (fb == null) {
                super.printMessage(request, response, "还未添加会谈纪要" + aeinspectionNo + "，请添加后再导出！",
                        ATTR_ERROR);
                return null;
            }
            String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                    request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
            Map<String, String> map = new HashMap<String, String>();
            map.put("FILED1", fb.getFiled1());
            map.put("FILED2", fb.getFiled2());
            map.put("FILED3", fb.getFiled3());
            map.put("FILED4", fb.getFiled4());
            map.put("FILED5", fb.getFiled5());
            map.put("FILED6", fb.getFiled6());
            String htmlContent = fb.getFiled10().replaceAll("<BR>", "\r    ")
                    .replaceAll("<br>", "\r    ").replaceAll("</?[^>]+>", StringUtils.EMPTY)
                    .replaceAll("<a>\\s*|\t|\r|\n</a>", StringUtils.EMPTY).replace("&nbsp;", " ");
            map.put("FILED10", StringEscapeUtils.unescapeHtml(htmlContent));
            map.put("FILED16", fb.getFiled16());
            map.put("PCB_BANK", bsorg.getName());
            fis = new FileInputStream(request.getSession().getServletContext()
                    .getRealPath(File.separator)
                    + FileHandler.WORD_TEMPLATE_FILE_PATH
                    + File.separator
                    + wordTemplateId
                    + ".doc");
            // 设置response的Header
            response.setContentType("application/vnd.ms-word");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + super.getDownloadFileName(fb.getFiled16(), "会谈"));
            repos = response.getOutputStream();
            wordGenerator.replaceDoc(fis, map, repos);
            repos.flush();
            return null;
        } catch (Exception e) {
            LOGGER.error("生成会谈纪要Word文件发生错误", e);
            super.printMessage(request, response,
                    "会谈纪要" + aeinspectionNo + "导出失败！错误原因:" + e.getMessage(), ATTR_ERROR);
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (repos != null) {
                    repos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 生成立项审批表Word文件.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward generateLxspbWord(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String wordTemplateId = request.getParameter("wordTemplateId").toString();
        String aeNo = request.getParameter("aeno");
        FileInputStream fis = null;
        OutputStream repos = null;
        try {
            BsAdmenforce fb = enforceService.getAdminEnforce(aeNo);
            if (fb == null) {
                super.printMessage(request, response, "还未添加立项信息:" + aeNo + "，请添加后再导出！", ATTR_ERROR);
                return null;
            }
            // String nowloginerOrgNo = this.getPubCredential(
            // UserCredentialName.organization.name(), request, response);
            // BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
            Map<String, String> map = new HashMap<String, String>();
            map.put("AEORGNM", fb.getAeorgnm());
            // 如果当前立项信息行内编号不为空，则采用行内编号，否则采用征信编号
            if (StringUtils.isBlank(fb.getInnerno())) {
                map.put("AENO", StringUtils.trimToEmpty(fb.getAeno()));
            } else {
                map.put("AENO", StringUtils.trimToEmpty(fb.getInnerno()));
            }
            map.put("PRJNM", fb.getPrjnm());
            Map<String, String> dicMap = super.getDicMap();
            if (StringUtils.isNotBlank(fb.getPrjbasis())) {
                try {
                    String[] prjBasisArr = fb.getPrjbasis().split(",");
                    StringBuffer prjBasisValueSb = new StringBuffer();
                    for (int i = 0; i < prjBasisArr.length; i++) {
                        prjBasisValueSb.append(dicMap.get(prjBasisArr[i]));
                        if (i != prjBasisArr.length - 1) {
                            prjBasisValueSb.append("，");
                        }
                    }
                    map.put("PRJBASIS", prjBasisValueSb.toString());
                } catch (Exception e) {
                    map.put("PRJBASIS", StringUtils.EMPTY);
                }
            }
            if (StringUtils.isNotBlank(fb.getAebasis())) {
                try {
                    String[] aeBasisArr = fb.getAebasis().split(",");
                    StringBuffer aeBasisValueSb = new StringBuffer();
                    for (int i = 0; i < aeBasisArr.length; i++) {
                        aeBasisValueSb.append(dicMap.get(aeBasisArr[i]));
                        if (i != aeBasisArr.length - 1) {
                            aeBasisValueSb.append("，");
                        }
                    }
                    map.put("AEBASIS", aeBasisValueSb.toString());
                } catch (Exception e) {
                    map.put("AEBASIS", StringUtils.EMPTY);
                }
            }
            map.put("AEEDORGNM", fb.getAeedorgnm());
            map.put("AECONTENT", StringEscapeUtils.unescapeJava(fb.getAecontent()));
            map.put("AELIMT", fb.getAelimt());
            if (StringUtils.isNotBlank(fb.getAeway())) {
                try {
                    String[] awayArr = fb.getAeway().split(",");
                    StringBuffer awayValueSb = new StringBuffer();
                    for (int i = 0; i < awayArr.length; i++) {
                        awayValueSb.append(dicMap.get(awayArr[i]));
                        if (i != awayArr.length - 1) {
                            awayValueSb.append("，");
                        }
                    }
                    map.put("AEWAY", awayValueSb.toString());
                } catch (Exception e) {
                    map.put("AEWAY", StringUtils.EMPTY);
                }
            }
            map.put("AEPLANSTDATE",
                    DateFormatUtils.format(fb.getAeplanstdate(), DateUtil.DATE_FORMAT_ZH));
            map.put("AEPLANTMDATE",
                    DateFormatUtils.format(fb.getAeplantmdate(), DateUtil.DATE_FORMAT_ZH));
            map.put("AEHEADMAN", fb.getAeheadman());
            map.put("AEMASTER", fb.getAemaster());
            map.put("AEOTHER", fb.getAeother());
            map.put("AESUMMARY", StringEscapeUtils.unescapeJava(fb.getAesummary()));
            map.put("DPTOPNION", StringEscapeUtils.unescapeJava(fb.getDptopnion()));
            map.put("DEPTMAN", fb.getDeptman());
            map.put("DEPTAUDITDATE",
                    DateFormatUtils.format(fb.getDeptauditdate(), DateUtil.DATE_FORMAT_ZH));
            map.put("CHAIROPNION", StringEscapeUtils.unescapeJava(fb.getChairopnion()));
            map.put("CHAIRMAN", fb.getChairman());
            map.put("CHAIRAUDITDATE",
                    DateFormatUtils.format(fb.getChairauditdate(), DateUtil.DATE_FORMAT_ZH));
            fis = new FileInputStream(request.getSession().getServletContext()
                    .getRealPath(File.separator)
                    + FileHandler.WORD_TEMPLATE_FILE_PATH
                    + File.separator
                    + wordTemplateId
                    + ".doc");
            // 设置response的Header
            response.setContentType("application/vnd.ms-word");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + super.getDownloadFileName(fb.getAeno(), "审批"));
            repos = response.getOutputStream();
            wordGenerator.replaceDoc(fis, map, repos);
            repos.flush();
            return null;
        } catch (Exception e) {
            LOGGER.error("生成立项审批表Word文件发生错误", e);
            super.printMessage(request, response, "检查立项表" + aeNo + "导出失败！错误原因:" + e.getMessage(),
                    ATTR_ERROR);
            return null;
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (repos != null) {
                    repos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 跳转检查工作记录列表页面.
     */
    public ActionForward toAeinspectionList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals(StringUtils.EMPTY)) ? "1"
                : (String) dyna.get("pageNo");
        String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals(
                StringUtils.EMPTY)) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
                .get("pageSize");
        String ino = (dyna.get("ino") == null || dyna.get("ino").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("ino");
        String aeorgChoice = (dyna.get("aeorgChoice") == null || dyna.get("aeorgChoice").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeorgChoice");
        String aeedorgChoice = (dyna.get("aeedorgChoice") == null || dyna.get("aeedorgChoice")
                .equals(StringUtils.EMPTY)) ? null : (String) dyna.get("aeedorgChoice");
        String aeplanstdate = (dyna.get("aeplanstdate") == null || dyna.get("aeplanstdate").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeplanstdate");
        String aeplantmdate = (dyna.get("aeplantmdate") == null || dyna.get("aeplantmdate").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeplantmdate");
        int pageCountTemp = 0;
        // 列表循环
        // 调用获取检查工作记录分页方法
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        if (StringUtils.isBlank(loginOrgNo)) {
            request.setAttribute(ATTR_MESSAGE, String.format(ERROR3,
                    this.getPrivCredential(UserCredentialName.login.name(), request, response)));
            request.setAttribute("methodname", "toAeinspectionList");
            return mapping.findForward("toAdminEnforceMessage");
        }
        Page page = enforceService.queryAeinspection(ino, aeorgChoice, aeedorgChoice, aeplanstdate,
                aeplantmdate, Integer.parseInt(pageNo), Integer.parseInt(pageSize), loginOrgNo);
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
        List<Object> list = (List<Object>) page.getResult();
        request.setAttribute("list", list);
        return mapping.findForward("toAeinspectionList");
    }

    /**
     * 点击登记执法检查结论菜单.
     */
    public ActionForward toAeinspListForCreateConcl(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals(StringUtils.EMPTY)) ? "1"
                : (String) dyna.get("pageNo");
        String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals(
                StringUtils.EMPTY)) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
                .get("pageSize");
        String ino = (dyna.get("ino") == null || dyna.get("ino").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("ino");
        String aeorgChoice = (dyna.get("aeorgChoice") == null || dyna.get("aeorgChoice").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeorgChoice");
        String aeedorgChoice = (dyna.get("aeedorgChoice") == null || dyna.get("aeedorgChoice")
                .equals(StringUtils.EMPTY)) ? null : (String) dyna.get("aeedorgChoice");
        String aeplanstdate = (dyna.get("aeplanstdate") == null || dyna.get("aeplanstdate").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeplanstdate");
        String aeplantmdate = (dyna.get("aeplantmdate") == null || dyna.get("aeplantmdate").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeplantmdate");
        int pageCountTemp = 0;
        // 列表循环
        // 调用获取检查工作记录分页方法
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        if (StringUtils.isBlank(loginOrgNo)) {
            request.setAttribute(ATTR_MESSAGE, String.format(ERROR3,
                    this.getPrivCredential(UserCredentialName.login.name(), request, response)));
            request.setAttribute("methodname", "toAeinspListForCreateConcl");
            return mapping.findForward("toAdminEnforceMessage");
        }
        Page page = enforceService.queryAeinspectionForCon(ino, aeorgChoice, aeedorgChoice,
                aeplanstdate, aeplantmdate, Integer.parseInt(pageNo), Integer.parseInt(pageSize),
                loginOrgNo);
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
        List<Object> list = (List<Object>) page.getResult();
        request.setAttribute("list", list);
        return mapping.findForward("toAeinspectionListForCreateConcl");
    }

    /**
     * 分页查询执法检查情况统计表.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward pageAeInspectionAnalyse(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        /** 获取参数 */
        String a1 = request.getParameter("a1");
        String b1 = request.getParameter("b1");
        String stDate = request.getParameter("stdate");
        String tmDate = request.getParameter("tmdate");
        String nowLoginUser = this.getPubCredential(UserCredentialName.organization.name(),
                request, response);
        if (StringUtils.isBlank(nowLoginUser)) {
            this.printMessage(request, response, ERROR6, ATTR_ERROR);
            return null;
        }
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
            LOGGER.error("分页查询执法检查情况统计表发生错误", e);
        }
        Page page = null;
        try {
            page = enforceService.pageAeInspectionAnalyse(a1, b1, stDate, tmDate, pageNo, limit,
                    nowLoginUser);
        } catch (Exception e) {
            LOGGER.error("分页查询执法检查情况统计表发生错误", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        JSONArray jsonObject = JSONArray.fromObject(page.getResult());
        String outputStr = jsonObject.toString();
        outputStr = "{totalProperty:" + page.getTotalCount() + ",\"root\":" + outputStr + "}";
        if (out != null) {
            out.print(outputStr);
        }
        return null;
    }

    /**
     * 跳转检查工作记录详细页面.
     */
    public ActionForward toAeinspectionDetailPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");
        BsAeinspection bs = enforceService.getAeinspection(id);
        BsAdmenforce af = enforceService.getAdmenforceByNo(bs.getAeno());
        request.setAttribute("prjnm", af.getPrjnm());
        RequestUtil.setFormFromBean(dyna, bs);
        request.setAttribute("aebasis", bs.getAebasis());
        request.setAttribute("enquirercd", bs.getEnquirercd());
        request.setAttribute("inrcd", bs.getInrcd());
        request.setAttribute("actualrcd", bs.getActualrcd());
        request.setAttribute("outrcd", bs.getOutrcd());
        request.setAttribute("inspect_token", bs.getStat());

        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
        request.setAttribute("inputBasis_title", bsorg.getName() + " 执法检查工作底稿");
        request.setAttribute("inputComing_title", bsorg.getName() + " 执法检查进场记录");
        request.setAttribute("inputGoaway_title", bsorg.getName() + " 执法检查离场会谈纪要");
        request.setAttribute("inputTalkSummary_title", bsorg.getName() + " 执法检查会谈纪要");
        request.setAttribute("inputFactBook_title", bsorg.getName() + " 事实认定书");
        BsAepeople aePeople = enforceService.getAePeopleByCardId(bs.getCrtlogin());
        if (aePeople != null) {
            request.setAttribute("crtLoginNm", aePeople.getPepname() + "-" + aePeople.getCreditno());
        } else {
            request.setAttribute("crtLoginNm",
                    enforceService.getCredential(bs.getCrtlogin(), UserCredentialName.nickname));
        }
        return mapping.findForward("toAeinspectionDetailPage");
    }

    /**
     * 跳转检查工作记录修改页面.
     */
    public ActionForward toUpdateAeinspectionPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        this.saveToken(request);// 产生令牌值
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            id = dyna.get("id").toString();
        }
        BsAeinspection bs = enforceService.getAeinspection(id);
        // 判断当前用户是否有权限修改该信息
        if (bs == null) {
            this.printMessage(request, response, String.format(ERROR8, id, "行政执法工作检查记录"),
                    ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bs.getCrtlogin();
            String nowLoginUser = this.getPrivCredential(UserCredentialName.login.name(), request,
                    response);
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "行政执法工作检查记录"),
                        ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLoginUser)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLoginUser.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "行政执法工作检查记录"),
                        ATTR_ERROR);
                return null;
            }
        }
        if (BooleanUtils.isFalse(bs.isCanbeupdate())) {
            this.printMessage(request, response, String.format(ERROR9, bs.getAeno(), "行政执法工作检查记录"),
                    ATTR_ERROR);
            return null;
        }
        BsAdmenforce af = enforceService.getAdmenforceByNo(bs.getAeno());
        request.setAttribute("prjnm", af.getPrjnm());
        RequestUtil.setFormFromBean(dyna, bs);
        dyna.set("aeno", bs.getAeno());
        request.setAttribute("aebasis", bs.getAebasis());
        request.setAttribute("enquirercd", bs.getEnquirercd());
        request.setAttribute("inrcd", bs.getInrcd());
        request.setAttribute("actualrcd", bs.getActualrcd());
        request.setAttribute("outrcd", bs.getOutrcd());
        request.setAttribute("inspect_token", bs.getStat());

        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
        request.setAttribute("inputBasis_title", bsorg.getName() + " 执法检查工作底稿");
        request.setAttribute("inputComing_title", bsorg.getName() + " 执法检查进场记录");
        request.setAttribute("inputGoaway_title", bsorg.getName() + " 执法检查离场会谈纪要");
        request.setAttribute("inputTalkSummary_title", bsorg.getName() + " 执法检查会谈纪要");
        request.setAttribute("inputFactBook_title", bsorg.getName() + " 事实认定书");
        request.setAttribute("inputInspectionAnl_title", bsorg.getName() + " 执法检查情况");
        BsAepeople aePeople = enforceService.getAePeopleByCardId(bs.getCrtlogin());
        if (aePeople != null) {
            request.setAttribute("crtLoginNm", aePeople.getPepname() + "-" + aePeople.getCreditno());
        } else {
            request.setAttribute("crtLoginNm",
                    enforceService.getCredential(bs.getCrtlogin(), UserCredentialName.nickname));
        }
        return mapping.findForward("toUpdateAeinspectionPage");
    }

    /**
     * 检查工作记录修改.
     */
    public ActionForward updateAeinspection(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        if (this.isTokenValid(request)) {// 正常提交
            this.resetToken(request);// 清空当前会话中的token值
            // 如果当前用户不属于人行用户，则不能使用该功能
            if (!super.isPcbUser(request, response)) {
                this.printMessage(request, response, ERROR1, ATTR_ERROR);
                return null;
            }
            DynaActionForm dyna = (DynaActionForm) form;
            String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(),
                    request, response);
            String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                    request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
            request.setAttribute("methodname", "toAeinspectionList");
            // String id = dyna.get("id").toString();
            // 调用检查工作记录保存方法
            try {
                // BsAeinspection bs = RequestUtil.getBeanFromParams(request,
                // BsAeinspection.class);
                BsAeinspection bs = enforceService.getAeinspectionById(dyna.getString("id"));
                FormFile file = null;
                String attchuuid = null;
                /** 保存执法检查工作底稿 * */
                file = (FormFile) dyna.get("aebasispath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    bs.setAebasis(attchuuid);
                }
                /** 保存询问笔录 * */
                file = (FormFile) dyna.get("enquirercdpath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    bs.setEnquirercd(attchuuid);
                }
                /** 保存进场会谈记录编号 * */
                file = (FormFile) dyna.get("inrcdpath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    bs.setInrcd(attchuuid);
                }
                /** 保存离场会谈纪要 * */
                file = (FormFile) dyna.get("outrcdpath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    bs.setOutrcd(attchuuid);
                }
                /** 保存事实认定书 * */
                file = (FormFile) dyna.get("actualrcdpath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    bs.setActualrcd(attchuuid);
                }
                bs.setAeplanstdate(DateUtils.parseDate(dyna.getString("aeplanstdate"),
                        DateUtil.DATE_FORMAT_ARRAY));
                bs.setAeplantmdate(DateUtils.parseDate(dyna.getString("aeplantmdate"),
                        DateUtil.DATE_FORMAT_ARRAY));
                bs.setAeheadman(dyna.getString("aeheadman"));
                bs.setAemaster(dyna.getString("aemaster"));
                bs.setAereviewer(dyna.getString("aereviewer"));
                bs.setProbsumr(dyna.getString("probsumr"));
                bs.setUpdateate(new Date());
                bs.setCrtdate(new Date());
                bs.setCreator(this.getPrivCredential(UserCredentialName.login.name(), request,
                        response));

                BsWorkcoming wc = enforceService.getWorkcoming(bs.getIno());
                if (bs.getInrcdno() == null && wc != null) {
                    bs.setInrcdno(wc.getFiled10());
                }
                BsWorkgoaway wg = enforceService.getWorkgoaway(bs.getIno());
                if (bs.getOutrcdno() == null && wg != null) {
                    bs.setOutrcdno(wg.getFiled10());
                }
                BsFactbook fb = enforceService.getFactBook(bs.getIno());
                if (bs.getActualrcdno() == null && fb != null) {
                    bs.setActualrcdno(fb.getFiled7());
                }
                bs.setInnerno(dyna.getString("innerno"));
                enforceService.saveAeinspection(bs);
                request.setAttribute(ATTR_MESSAGE, "【" + bs.getIno() + "】操作成功！");
                LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser,
                        "修改行政执法工作检查记录", "操作成功!"));
            } catch (Exception e) {
                LOGGER.error(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser,
                        "修改行政执法工作检查记录", "操作失败!"), e);
                request.setAttribute(ATTR_MESSAGE, "修改行政执法工作检查记录操作失败！错误原因：" + e.getMessage());
            }
            return mapping.findForward("toAdminEnforceMessage");
        } else {// 重复提交
            this.printMessage(request, response, String.format(ERROR2, "检查工作记录查询"), ATTR_ERROR);
            return null;
        }
    }

    /**
     * 检查工作记录删除.
     */
    public ActionForward delAeinspection(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(), request,
                response);
        String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
        request.setAttribute("methodname", "toAeinspectionList");
        String id = request.getParameter("id");
        BsAeinspection bs = enforceService.getAeinspection(id);
        // 判断当前用户是否有权限修改该信息
        if (bs == null) {
            this.printMessage(request, response, String.format(ERROR8, id, "行政执法工作检查记录"),
                    ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bs.getCrtlogin();
            String nowLogin = this.getPrivCredential(UserCredentialName.login.name(), request,
                    response);
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "行政执法工作检查记录"),
                        ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLogin)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLogin.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "行政执法工作检查记录"),
                        ATTR_ERROR);
                return null;
            }
        }
        // 调用检查工作记录删除方法
        try {
            enforceService.deleteAeinspectionById(id);
            request.setAttribute(ATTR_MESSAGE, "删除行政执法工作检查记录【" + id + "】操作成功!");
            LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "删除检查工作记录",
                    "操作成功!"));
        } catch (Exception e) {
            LOGGER.error(
                    String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "删除检查工作记录", "操作失败!"),
                    e);
            request.setAttribute(ATTR_MESSAGE, "删除行政执法工作检查记录操作失败!错误原因:" + e.getMessage());
        }
        return mapping.findForward("toAdminEnforceMessage");
    }

    /**
     * 跳转检查结论登记页面.
     */
    public ActionForward toAeconclusionInitPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        this.saveToken(request);// 产生令牌值
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;

        String id = request.getParameter("id");

        String nowLoginUser = this.getPrivCredential(UserCredentialName.login.name(), request,
                response);
        // 判断当前用户是否有权限修改该信息
        BsAeinspection aeinsp = enforceService.getAeinspectionById(id);
        if (aeinsp == null) {
            super.printMessage(request, response, "没有找到行政执法工作检查记录【" + id + ",请重新选择", ATTR_ERROR);
            return null;
        } else {
            String crtLogin = aeinsp.getCrtlogin();
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "行政执法工作检查记录"),
                        ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLoginUser)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLoginUser.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "行政执法工作检查记录"),
                        ATTR_ERROR);
                return null;
            }
        }
        if (enforceService.getAeconclusionByAeno(aeinsp.getIno()) != null) {
            request.setAttribute(ATTR_MESSAGE,
                    "<b>保存行政执法检查结论失败</b><br><b>失败原因:</b>已经存在执法检查工作记录编号为【" + aeinsp.getIno()
                            + "】的检查结论记录，不能重复添加<br><b>解决方法:</b>通过行政执法结论查询功能对该结论信息进行修改");
            request.setAttribute("methodname", "toAeconclusionList");
            return mapping.findForward("toAdminEnforceMessage");
        }
        RequestUtil.setFormFromBean(dyna, aeinsp);
        dyna.set("aeno", aeinsp.getIno());
        dyna.set("aeorgno", aeinsp.getAeorgno());
        dyna.set("aeorgnm", aeinsp.getAeorgnm());
        dyna.set("aeedorgno", aeinsp.getAeedorgno());
        dyna.set("aeedorgnm", aeinsp.getAeedorgnm());
        dyna.set("aeplanstdate",
                DateFormatUtils.format(aeinsp.getAeplanstdate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
        dyna.set("aeplantmdate",
                DateFormatUtils.format(aeinsp.getAeplantmdate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
        request.setAttribute("cljdList", super.getDicMap("CLJD"));

        // 获取执法检查意见书编号
        // String loginOrgNo = this.getPubCredential(
        // UserCredentialName.organization.name(), request, response);
        // BsNogenerate bs = noGenerator.getNoGenerate(Constants.PCB_SC_ORG_NO,
        // String
        // .valueOf(year));
        // dyna.set("aetext", bs.getAenotext());
        // dyna.set("aeyear", bs.getAenoyear());
        // dyna.set("aeindex", bs.getAenoindex());
        return mapping.findForward("toAeconclusionInitPage");
    }

    /**
     * 根据机构No获取用户信息.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getUsersByOrgNo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String orgNo = request.getParameter("orgNo");
        // String orgNo = "C1031851000018";
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("根据机构No获取用户信息", e);
        }
        List<BsUserInfoOfJg> userlt = null;
        try {
            userlt = enforceService.getUsersByOrgNo(orgNo);
        } catch (Exception e) {
            LOGGER.error("根据机构No获取用户信息", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        StringBuffer outputSb = new StringBuffer("{totalProperty:" + userlt.size() + ",root:[");
        BsUserInfoOfJg bsUserInfoOfJg = null;
        for (int i = 0; i < userlt.size(); i++) {
            bsUserInfoOfJg = userlt.get(i);
            outputSb.append("{'value':'").append(bsUserInfoOfJg.getName()).append("--")
                    .append(bsUserInfoOfJg.getLoginId()).append("','text':'")
                    .append(bsUserInfoOfJg.getName()).append("-")
                    .append(bsUserInfoOfJg.getLoginId()).append("'}");
            if (i != userlt.size() - 1) {
                outputSb.append(",");
            }
        }
        outputSb.append("]}");
        // JSONArray jsonObject = JSONArray.fromObject(userlt);

        // String outputStr = jsonObject.toString();
        if (out != null) {
            // System.out.println(outputSb.toString());
            out.print(outputSb.toString());
        }
        return null;
    }

    /**
     * 根据多个机构No获取用户信息.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getUsersByOrgNos(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String orgNos = request.getParameter("orgNos");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("根据多个机构No获取用户信息", e);
        }
        if (StringUtils.isNotBlank(orgNos)) {
            List<BsUserInfoOfJg> userlt = null;
            try {
                String[] orgNoParamArr = orgNos.split(",");
                String[] orgNosArr = new String[orgNoParamArr.length];
                for (int i = 0; i < orgNoParamArr.length; i++) {
                    String[] orgArr = orgNoParamArr[i].split("--");
                    orgNosArr[i] = orgArr[1];
                }
                userlt = enforceService.getUsersByOrgNos(StringUtil.join(orgNosArr, "'", "'", ","));
            } catch (Exception e) {
                LOGGER.error("根据多个机构No获取用户信息", e);
                out.print("{totalProperty:0,\"root\":[]}");
                return null;
            }
            StringBuffer outputSb = new StringBuffer("{totalProperty:" + userlt.size() + ",root:[");
            BsUserInfoOfJg bsUserInfoOfJg = null;
            for (int i = 0; i < userlt.size(); i++) {
                bsUserInfoOfJg = userlt.get(i);
                outputSb.append("{'value':'").append(bsUserInfoOfJg.getName()).append("--")
                        .append(bsUserInfoOfJg.getLoginId()).append("','text':'")
                        .append(bsUserInfoOfJg.getName()).append("-")
                        .append(bsUserInfoOfJg.getLoginId()).append("'}");
                if (i != userlt.size() - 1) {
                    outputSb.append(",");
                }
            }
            outputSb.append("]}");
            // JSONArray jsonObject = JSONArray.fromObject(userlt);

            // String outputStr = jsonObject.toString();
            if (out != null) {
                // System.out.println(outputSb.toString());
                out.print(outputSb.toString());
            }
            return null;
        } else {
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }

    }

    /**
     * 根据机构No获取该机构下子机构.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getOrgsByParentOrgNo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String parentNo = request.getParameter("parentNo");
        // String orgNo = "C1031851000018";
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("根据机构No获取该机构下子机构", e);
        }
        List<BsOrg> childrenOrgslt = null;
        try {
            childrenOrgslt = enforceService.getOrgsByParentOrgNo(parentNo);
        } catch (Exception e) {
            LOGGER.error("根据机构No获取该机构下子机构", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        StringBuffer outputSb = new StringBuffer("{totalProperty:" + childrenOrgslt.size()
                + ",root:[");
        BsOrg org = null;
        for (int i = 0; i < childrenOrgslt.size(); i++) {
            org = childrenOrgslt.get(i);
            outputSb.append("{'value':'").append(org.getName()).append("--").append(org.getNo())
                    .append("','text':'").append(org.getName()).append("-").append(org.getNo())
                    .append("'}");
            if (i != childrenOrgslt.size() - 1) {
                outputSb.append(",");
            }
        }
        outputSb.append("]}");
        // JSONArray jsonObject = JSONArray.fromObject(userlt);

        // String outputStr = jsonObject.toString();
        if (out != null) {
            // System.out.println(outputSb.toString());
            out.print(outputSb.toString());
        }

        return null;
    }

    /**
     * 根据机构No获取该机构下子机构.<br>
     * 
     * 包含自己
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getOrgsByParentOrgNoContainSelf(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String parentNo = request.getParameter("parentNo");
        // String orgNo = "C1031851000018";
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("根据机构No获取该机构下子机构", e);
        }
        List<BsOrg> childrenOrgslt = null;
        try {
            childrenOrgslt = enforceService.getOrgsByParentOrgNoContainSelf(parentNo);
        } catch (Exception e) {
            LOGGER.error("根据机构No获取该机构下子机构", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        StringBuffer outputSb = new StringBuffer("{totalProperty:" + childrenOrgslt.size()
                + ",root:[");
        BsOrg org = null;
        for (int i = 0; i < childrenOrgslt.size(); i++) {
            org = childrenOrgslt.get(i);
            outputSb.append("{'value':'").append(org.getName()).append("--").append(org.getNo())
                    .append("','text':'").append(org.getName()).append("-").append(org.getNo())
                    .append("'}");
            if (i != childrenOrgslt.size() - 1) {
                outputSb.append(",");
            }
        }
        outputSb.append("]}");
        // JSONArray jsonObject = JSONArray.fromObject(userlt);

        // String outputStr = jsonObject.toString();
        if (out != null) {
            // System.out.println(outputSb.toString());
            out.print(outputSb.toString());
        }

        return null;
    }

    /**
     * 检查结论保存方法.
     */
    public ActionForward createAeconclusion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        if (this.isTokenValid(request)) {// 正常提交
            this.resetToken(request);// 清空当前会话中的token值

            // 如果当前用户不属于人行用户，则不能使用该功能
            if (!super.isPcbUser(request, response)) {
                this.printMessage(request, response, ERROR1, ATTR_ERROR);
                return null;
            }
            DynaActionForm dyna = (DynaActionForm) form;
            String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(),
                    request, response);
            String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                    request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
            request.setAttribute("methodname", "toAeconclusionList");
            // 调用检查结论保存方法
            try {
                BsAeconclusion bs = RequestUtil.getBeanFromParams(request, BsAeconclusion.class);
                String[] decisionArr = request.getParameterValues("decision");
                bs.setDecision(StringUtil.join(decisionArr, StringUtils.EMPTY, StringUtils.EMPTY,
                        ","));
                String relpeoples = request.getParameter("relpeoples");
                if (StringUtils.isNotBlank(relpeoples)) {
                    try {
                        String[] relpeoplesArr = relpeoples.split(",");
                        for (int i = 0; i < relpeoplesArr.length; i++) {
                            // System.out.println(relpeoplesArr[i]);
                            // 插入涉及用户档案信息
                            String[] tempArr = relpeoplesArr[i].split("--");
                            enforceService.relatePersonProfile("执法检查结论涉及用户", tempArr[1],
                                    tempArr[0],
                                    "在《执法检查意见书（" + StringUtils.trimToEmpty(bs.getAeopnionno())
                                            + "）》中被指定为涉及人员");
                        }
                    } catch (Exception e) {
                        LOGGER.error("检查结论保存方法", e);
                    }
                }
                String relorgnm = request.getParameter("relorgnm");
                if (StringUtils.isNotBlank(relorgnm)) {
                    try {
                        String[] relorgnmArr = relorgnm.split(",");
                        for (int i = 0; i < relorgnmArr.length; i++) {
                            // System.out.println(relpeoplesArr[i]);
                            // 插入涉及用户档案信息
                            String[] tempArr2 = relorgnmArr[i].split("--");
                            enforceService.relateOrgProfile(tempArr2[1], tempArr2[0], "在《执法检查意见书（"
                                    + StringUtils.trimToEmpty(bs.getAeopnionno()) + "）》中被指定为涉及机构");
                        }
                    } catch (Exception e) {
                        LOGGER.error("检查结论保存方法", e);
                    }
                }
                if (dyna.get("aeopnionno") != null) {
                    bs.setAeopnionno(dyna.get("aeopnionno").toString());
                }
                FormFile file = null;
                String attchuuid = null;
                /** 保存执法检查意见书 * */
                file = (FormFile) dyna.get("aeopnionbookpath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    bs.setAeopnionbook(attchuuid);
                }
                // 保存创建人为当前登录人id
                bs.setCrtlogin(this.getPrivCredential(UserCredentialName.login.name(), request,
                        response));
                bs.setIsfinished(false);
                bs.setCanbeupdate(true);
                try {
                    enforceService.saveAeconclusion(bs);
                    enforceService.updateInspcByConlusion(bs.getAeno());
                } catch (DataIntegrityViolationException e) {
                    request.setAttribute(
                            ATTR_MESSAGE,
                            "<b>保存检查结论操作失败,执法检查意见书编号重复</b><br><b>失败原因:</b>因为在你填写的执法检查意见书编号 "
                                    + bs.getAeopnionno()
                                    + " 系统已经存在<br><b>解决方法:</b>请确认该执法检查意见书编号唯一，再输入保存!");
                    return mapping.findForward("toAdminEnforceMessage");
                }
                request.setAttribute(ATTR_MESSAGE, "保存行政执法检查结论【" + bs.getAeno() + "】操作成功!");
                LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "保存检查结论",
                        "操作成功！"));
            } catch (Exception e) {
                LOGGER.error("检查结论保存方法", e);
                request.setAttribute(ATTR_MESSAGE, "保存行政执法检查结论操作失败！错误原因：" + e.getMessage());
                LOGGER.error(
                        String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "保存检查结论", "操作失败!"),
                        e);
            }
            return mapping.findForward("toAdminEnforceMessage");
        } else {// 重复提交
            this.printMessage(request, response, String.format(ERROR2, "检查结论登记"), ATTR_ERROR);
            return null;
        }
    }

    /**
     * 跳转检查结论列表页面.
     */
    public ActionForward toAeconclusionList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        String source = request.getParameter("source");
        if (StringUtils.isBlank(source)) {
            source = null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals(StringUtils.EMPTY)) ? "1"
                : (String) dyna.get("pageNo");
        String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals(
                StringUtils.EMPTY)) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
                .get("pageSize");
        String aeno = (dyna.get("aeopnionno") == null || dyna.get("aeopnionno").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeopnionno");
        String aeorgChoice = (dyna.get("aeorgChoice") == null || dyna.get("aeorgChoice").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeorgChoice");
        String aeedorgChoice = (dyna.get("aeedorgChoice") == null || dyna.get("aeedorgChoice")
                .equals(StringUtils.EMPTY)) ? null : (String) dyna.get("aeedorgChoice");
        String aeplanstdate = (dyna.get("aeplanstdate") == null || dyna.get("aeplanstdate").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeplanstdate");
        String aeplantmdate = (dyna.get("aeplantmdate") == null || dyna.get("aeplantmdate").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeplantmdate");
        int pageCountTemp = 0;
        // 列表循环
        // 调用获取检查工作记录分页方法

        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        if (StringUtils.isBlank(loginOrgNo)) {
            request.setAttribute(ATTR_MESSAGE, String.format(ERROR3,
                    this.getPrivCredential(UserCredentialName.login.name(), request, response)));
            request.setAttribute("methodname", "toAeconclusionList");
            return mapping.findForward("toAdminEnforceMessage");
        }
        Page page = null;
        if (source != null && source.trim().equals("punish")) {
            page = enforceService.queryAeconclusionWithPunish(aeno, aeorgChoice, aeedorgChoice,
                    aeplanstdate, aeplantmdate, Integer.parseInt(pageNo),
                    Integer.parseInt(pageSize), loginOrgNo);
        } else {
            page = enforceService.queryAeconclusion(aeno, aeorgChoice, aeedorgChoice, aeplanstdate,
                    aeplantmdate, Integer.parseInt(pageNo), Integer.parseInt(pageSize), loginOrgNo);
        }
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
        } // 分页处理End

        // 再次将分页相关数据返回页面Start
        request.setAttribute("pageNo", pageNo);
        dyna.set("pageNo", pageNo);
        request.setAttribute("pageCount", pageCountTemp);
        dyna.set("pageCount", pageCountTemp);
        request.setAttribute("totalNo", page.getTotalCount());
        // 再次将分页相关数据返回页面End
        List<Object> list = (List<Object>) page.getResult();
        request.setAttribute("list", list);
        if (StringUtils.isBlank(source)) {
            request.setAttribute("source", StringUtils.EMPTY);
        } else {
            request.setAttribute("source", source);
        }
        return mapping.findForward("toAeconclusionList");
    }

    /**
     * 跳转检查结论详细页面.
     */
    public ActionForward toAeconclusionDetailPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");
        BsAeconclusion bs = enforceService.getAeconclusion(id);
        RequestUtil.setFormFromBean(dyna, bs);
        request.setAttribute("aeopnionbook", bs.getAeopnionbook());
        // 获取多选值，先转化为数组
        String[] decisionArr = bs.getDecision().split(",");
        request.setAttribute("dicMap", super.getDicMap());
        request.setAttribute("decisionArr", decisionArr);
        BsAepeople aePeople = enforceService.getAePeopleByCardId(bs.getCrtlogin());
        if (aePeople != null) {
            request.setAttribute("crtLoginNm", aePeople.getPepname() + "-" + aePeople.getCreditno());
        } else {
            request.setAttribute("crtLoginNm",
                    enforceService.getCredential(bs.getCrtlogin(), UserCredentialName.nickname));
        }
        return mapping.findForward("toAeconclusionDetailPage");
    }

    /**
     * 跳转检查结论修改页面.
     */
    public ActionForward toUpdateAeconclusionPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        this.saveToken(request);// 产生令牌值
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            id = dyna.get("id").toString();
        }
        BsAeconclusion bs = enforceService.getAeconclusion(id);
        // 判断当前用户是否有权限修改该信息
        if (bs == null) {
            this.printMessage(request, response, String.format(ERROR8, id, "执法检查结论"), ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bs.getCrtlogin();
            String nowLoginUser = this.getPrivCredential(UserCredentialName.login.name(), request,
                    response);
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "执法检查结论"), ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLoginUser)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLoginUser.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "执法检查结论"), ATTR_ERROR);
                return null;
            }
        }
        if (BooleanUtils.isFalse(bs.isCanbeupdate())) {
            this.printMessage(request, response, String.format(ERROR9, bs.getAeno(), "执法检查结论"),
                    ATTR_ERROR);
            return null;
        }
        // 如果对应当前检查结论信息已经录入了处罚立项信息，则不能修改
        List pnshLt = punishService.getAdmPunishListByAeNo(bs.getAeno());
        if (CollectionUtils.isNotEmpty(pnshLt)) {
            this.printMessage(request, response,
                    String.format("错误原因：该%s已经录入了对应的处罚立项信息，不能修改", "检查结论信息"), ATTR_ERROR);
            return null;
        }

        RequestUtil.setFormFromBean(dyna, bs);
        String relorgnm = (dyna.get("relorgnm") == null || dyna.get("relorgnm").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("relorgnm");
        dyna.set("aeorgChoice", relorgnm);
        request.setAttribute("aeopnionbook", bs.getAeopnionbook());
        request.setAttribute("cljdList", super.getDicMap("CLJD"));
        request.setAttribute("form", dyna);
        // 获取多选值，先转化为数组
        String[] decisionArr = bs.getDecision().split(",");
        request.setAttribute("decisionArr", decisionArr);
        BsAepeople aePeople = enforceService.getAePeopleByCardId(bs.getCrtlogin());
        if (aePeople != null) {
            request.setAttribute("crtLoginNm", aePeople.getPepname() + "-" + aePeople.getCreditno());
        } else {
            request.setAttribute("crtLoginNm",
                    enforceService.getCredential(bs.getCrtlogin(), UserCredentialName.nickname));
        }
        return mapping.findForward("toUpdateAeconclusionPage");
    }

    /**
     * 检查结论修改.
     */
    public ActionForward updateAeconclusion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        if (this.isTokenValid(request)) {// 正常提交
            this.resetToken(request);// 清空当前会话中的token值
            // 如果当前用户不属于人行用户，则不能使用该功能
            if (!super.isPcbUser(request, response)) {
                this.printMessage(request, response, ERROR1, ATTR_ERROR);
                return null;
            }
            DynaActionForm dyna = (DynaActionForm) form;
            String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(),
                    request, response);
            String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                    request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
            request.setAttribute("methodname", "toAeconclusionList");
            String[] decisionArr = request.getParameterValues("decision");
            String decisionStr = StringUtil.join(decisionArr, StringUtils.EMPTY, StringUtils.EMPTY,
                    ",");
            String id = dyna.get("id").toString();
            BsAeconclusion bs = enforceService.getAeconclusion(id);
            List<BsAdmpunish> punishs = null;
            if (bs != null) {
                punishs = punishService.getAdmPunishListByAeNo(bs.getAeno());
                if (CollectionUtils.isNotEmpty(punishs)
                        && !bs.getDecision().trim().equals(decisionStr.trim())) {
                    request.setAttribute(ATTR_MESSAGE, "该执法检查已经录入了行政处罚信息，不能修改[处理决定]项");
                    return mapping.findForward("toAdminEnforceMessage");
                }
            }
            enforceService.evict(bs);
            // 调用检查结论保存方法)
            try {
                // bs = RequestUtil.getBeanFromParams(request,
                // BsAeconclusion.class);
                bs = enforceService.getAeconclusionById(id);
                FormFile file = null;
                String attchuuid = null;
                /** 保存执法检查意见书 * */
                file = (FormFile) dyna.get("aeopnionbookpath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request
                            .getSession().getServletContext().getRealPath(File.separator));
                    bs.setAeopnionbook(attchuuid);
                }
                bs.setRelorgnm(dyna.getString("relorgnm"));
                bs.setRelpeoples(dyna.getString("relpeoples"));

                bs.setAeopnionno(dyna.getString("aeopnionno"));

                bs.setDecision(decisionStr);
                bs.setUpdateate(new Date());

                enforceService.saveAeconclusion(bs);
                request.setAttribute(ATTR_MESSAGE, "修改行政执法检查结论【" + bs.getAeno() + "】操作成功!");
                LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "修改检查结论",
                        "操作成功！"));
            } catch (Exception e) {
                LOGGER.error(
                        String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "修改检查结论", "操作失败!"),
                        e);
                request.setAttribute(ATTR_MESSAGE, "修改行政执法检查结论操作失败！错误原因：" + e.getMessage());
            }
            return mapping.findForward("toAdminEnforceMessage");
        } else {// 重复提交
            this.printMessage(request, response, String.format(ERROR2, "检查结论查询"), ATTR_ERROR);
            return null;
        }
    }

    /**
     * 检查结论删除.
     */
    public ActionForward delAeconclusion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(), request,
                response);
        String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
        request.setAttribute("methodname", "toAeconclusionList");
        String id = request.getParameter("id");
        BsAeconclusion bs = enforceService.getAeconclusion(id);
        // 判断当前用户是否有权限修改该信息
        if (bs == null) {
            this.printMessage(request, response, String.format(ERROR8, id, "执法检查结论"), ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bs.getCrtlogin();
            String nowLogin = this.getPrivCredential(UserCredentialName.login.name(), request,
                    response);
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "执法检查结论"), ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLogin)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLogin.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "执法检查结论"), ATTR_ERROR);
                return null;
            }
        }
        // 调用检查工作记录删除方法
        try {
            enforceService.deleteAeconclusionById(id);
            request.setAttribute(ATTR_MESSAGE, "删除行政执法检查结论【" + id + "】操作成功!");
            LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "删除检查结论", "操作成功!"));
        } catch (Exception e) {
            LOGGER.error(
                    String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "删除检查结论", "操作失败!"), e);
            request.setAttribute(ATTR_MESSAGE, "删除行政执法检查结论操作失败!错误原因:" + e.getMessage());
        }
        return mapping.findForward("toAdminEnforceMessage");
    }

    /**
     * 点击登记执法检查整改记录菜单.
     */
    public ActionForward toAeinspListForCreateRecti(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals(StringUtils.EMPTY)) ? "1"
                : (String) dyna.get("pageNo");
        String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals(
                StringUtils.EMPTY)) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
                .get("pageSize");
        String ino = (dyna.get("ino") == null || dyna.get("ino").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("ino");
        String aeorgChoice = (dyna.get("aeorgChoice") == null || dyna.get("aeorgChoice").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeorgChoice");
        String aeedorgChoice = (dyna.get("aeedorgChoice") == null || dyna.get("aeedorgChoice")
                .equals(StringUtils.EMPTY)) ? null : (String) dyna.get("aeedorgChoice");
        String aeplanstdate = (dyna.get("aeplanstdate") == null || dyna.get("aeplanstdate").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeplanstdate");
        String aeplantmdate = (dyna.get("aeplantmdate") == null || dyna.get("aeplantmdate").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeplantmdate");
        int pageCountTemp = 0;
        // 列表循环
        // 调用获取检查工作记录分页方法
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        if (StringUtils.isBlank(loginOrgNo)) {
            request.setAttribute(ATTR_MESSAGE, String.format(ERROR3,
                    this.getPrivCredential(UserCredentialName.login.name(), request, response)));
            request.setAttribute("methodname", "toAeinspListForCreateRecti");
            return mapping.findForward("toAdminEnforceMessage");
        }
        Page page = enforceService.queryAeinspectionForRefti(ino, aeorgChoice, aeedorgChoice,
                aeplanstdate, aeplantmdate, Integer.parseInt(pageNo), Integer.parseInt(pageSize),
                loginOrgNo);
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
        List<Object> list = (List<Object>) page.getResult();
        request.setAttribute("list", list);
        return mapping.findForward("toAeinspectionListForCreateRecti");
    }

    /**
     * 跳转跟踪整改信息录入页面.
     */
    public ActionForward toAerectificationInitPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        this.saveToken(request);// 产生令牌值
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String nowLoginUser = this.getPrivCredential(UserCredentialName.login.name(), request,
                response);
        // 判断当前用户是否有权限修改该信息（是否属于本人创建）
        String id = request.getParameter("id");
        BsAeinspection aeinsp = enforceService.getAeinspectionById(id);
        if (aeinsp == null) {
            super.printMessage(request, response, "没有找到行政执法工作检查记录【" + id + ",请重新选择", ATTR_ERROR);
            return null;
        } else {
            String crtLogin = aeinsp.getCrtlogin();
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "行政执法工作检查记录"),
                        ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLoginUser)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLoginUser.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "行政执法工作检查记录"),
                        ATTR_ERROR);
                return null;
            }
        }
        // 判断当前检查工作记录是否存在整改信息，如存在，则不能登记
        if (enforceService.getAerectificationByIno(aeinsp.getIno()) != null) {
            request.setAttribute(ATTR_MESSAGE,
                    "<b>保存行政执法整改信息失败</b><br><b>失败原因:</b>已经存在执法检查工作记录编号为【" + aeinsp.getIno()
                            + "】的跟踪整改记录，不能重复添加<br><b>解决方法:</b>通过行政执法整改信息查询功能对该整改信息进行修改");
            request.setAttribute("methodname", "toAerectificationList");
            return mapping.findForward("toAdminEnforceMessage");
        }
        // 判断当前检查工作记录是否存在工作检查结论，如果不存在，则必须先登记检查工作结论
        if (enforceService.getAeconclusionByAeno(aeinsp.getIno()) == null) {
            request.setAttribute(ATTR_MESSAGE,
                    "<b>保存行政执法整改信息失败</b><br><b>失败原因:</b>目前还未登记执法检查工作记录编号为【" + aeinsp.getIno()
                            + "】的检查结论记录，请先登记检查结论<br><b>解决方法:</b>通过检查结论登记功能登记后,再进行整改信息登记");
            request.setAttribute("methodname", "toAerectificationList");
            return mapping.findForward("toAdminEnforceMessage");
        }
        RequestUtil.setFormFromBean(dyna, aeinsp);
        dyna.set("aeno", aeinsp.getIno());
        dyna.set("aeorgno", aeinsp.getAeorgno());
        dyna.set("aeorgnm", aeinsp.getAeorgnm());
        dyna.set("aeedorgno", aeinsp.getAeedorgno());
        dyna.set("aeedorgnm", aeinsp.getAeedorgnm());
        dyna.set("aeplanstdate",
                DateFormatUtils.format(aeinsp.getAeplanstdate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));
        dyna.set("aeplantmdate",
                DateFormatUtils.format(aeinsp.getAeplantmdate(), DateUtil.DATE_FORMAT_YYYY_MM_DD));

        String aeno = dyna.getString("aeno");
        BsAeconclusion bs = enforceService.getAeconclusionByAeno(aeno);
        if (bs == null) {
            request.setAttribute("methodname", "toAeinspListForCreateRecti");
            request.setAttribute(
                    ATTR_MESSAGE,
                    "<b>跳转录入整改页面失败,当前执法信息还未录入工作检查结论</b><br><b>失败原因:</b>当前还未录入工作检查记录 "
                            + aeinsp.getIno()
                            + " 的检查结论信息，请录入后再录入整改信息<br><b>解决方法:</b>请重新点击'检查结论登记'菜单选择工作记录录入结论信息!");
            return mapping.findForward("toAdminEnforceMessage");
        }
        dyna.set("aeopnionno", bs.getAeopnionno());
        dyna.set("trackno",
                aeno + DateFormatUtils.format(new Date(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSSSSS));
        return mapping.findForward("toAerectificationInitPage");
    }

    /**
     * 跟踪整改信息保存方法.
     */
    public ActionForward createAerectification(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        if (this.isTokenValid(request)) {// 正常提交
            this.resetToken(request);// 清空当前会话中的token值
            // 如果当前用户不属于人行用户，则不能使用该功能
            if (!super.isPcbUser(request, response)) {
                this.printMessage(request, response, ERROR1, ATTR_ERROR);
                return null;
            }
            String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(),
                    request, response);
            String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                    request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
            request.setAttribute("methodname", "toAerectificationList");
            // 调用跟踪整改信息保存方法
            try {
                BsAerectification bs = RequestUtil.getBeanFromParams(request,
                        BsAerectification.class);
                // bs.setRectificationstdate(rectificationstdate);
                // bs.setRectificationtmdate(rectificationtmdate);
                // 保存创建人为当前登录人id
                bs.setCrtlogin(this.getPrivCredential(UserCredentialName.login.name(), request,
                        response));
                bs.setIsfinished(false);
                bs.setCanbeupdate(true);
                //处理文本框中有换行符等情况
                bs.setTrackcontend(StringEscapeUtils.escapeJava(bs.getTrackcontend()));
                try {
                    enforceService.saveAerectification(bs);
                    enforceService.updateInspcByRefti(bs.getAeno());
                } catch (DataIntegrityViolationException e) {
                    request.setAttribute(ATTR_MESSAGE,
                            "<b>保存整改信息操作失败,跟踪整改编号重复</b><br><b>失败原因:</b>因为在你保存执法信息之前有其他用户已经保存了跟踪整改编号 "
                                    + bs.getTrackno()
                                    + " 的整改信息<br><b>解决方法:</b>请重新点击'整改信息登记'菜单获取新跟踪整改编号，再输入保存!");
                    return mapping.findForward("toAdminEnforceMessage");
                }
                try {
                    enforceService.refreshUpdateFlag(bs.getAeno());
                } catch (Exception e) {
                    LOGGER.error("设置检查流程能否修改标识失败", e);
                    request.setAttribute(ATTR_MESSAGE, "<b>保存整改信息操作成功，但是未成功修改该检查工作记录状态为【不能修改】");
                    return mapping.findForward("toAdminEnforceMessage");
                }
                request.setAttribute(ATTR_MESSAGE, "保存行政执法整改情况【" + bs.getAeno() + "】操作成功！");
                LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "保存跟踪整改信息",
                        "操作成功！"));
            } catch (Exception e) {
                LOGGER.error(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "保存跟踪整改信息",
                        "操作失败!"), e);
                request.setAttribute(ATTR_MESSAGE, "保存行政执法整改情况操作失败！错误原因：" + e.getMessage());
            }
            return mapping.findForward("toAdminEnforceMessage");
        } else {// 重复提交
            this.printMessage(request, response, String.format(ERROR2, "整改信息登记"), ATTR_ERROR);
            return null;
        }
    }

    /**
     * 跳转跟踪整改信息列表页面.
     */
    public ActionForward toAerectificationList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals(StringUtils.EMPTY)) ? "1"
                : (String) dyna.get("pageNo");
        String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals(
                StringUtils.EMPTY)) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
                .get("pageSize");
        String trackno = (dyna.get("trackno") == null || dyna.get("trackno").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("trackno");
        String aeorgChoice = (dyna.get("aeorgChoice") == null || dyna.get("aeorgChoice").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeorgChoice");
        String aeedorgChoice = (dyna.get("aeedorgChoice") == null || dyna.get("aeedorgChoice")
                .equals(StringUtils.EMPTY)) ? null : (String) dyna.get("aeedorgChoice");
        String aeplanstdate = (dyna.get("aeplanstdate") == null || dyna.get("aeplanstdate").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeplanstdate");
        String aeplantmdate = (dyna.get("aeplantmdate") == null || dyna.get("aeplantmdate").equals(
                StringUtils.EMPTY)) ? null : (String) dyna.get("aeplantmdate");
        int pageCountTemp = 0;
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request,
                response);
        if (StringUtils.isBlank(loginOrgNo)) {
            request.setAttribute(ATTR_MESSAGE, String.format(ERROR3,
                    this.getPrivCredential(UserCredentialName.login.name(), request, response)));
            request.setAttribute("methodname", "toAerectificationList");
            return mapping.findForward("toAdminEnforceMessage");
        }
        Page page = enforceService.queryAerectification(trackno, aeorgChoice, aeedorgChoice,
                aeplanstdate, aeplantmdate, Integer.parseInt(pageNo), Integer.parseInt(pageSize),
                loginOrgNo);
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
        } // 分页处理End

        // 再次将分页相关数据返回页面Start
        request.setAttribute("pageNo", pageNo);
        dyna.set("pageNo", pageNo);
        request.setAttribute("pageCount", pageCountTemp);
        dyna.set("pageCount", pageCountTemp);
        request.setAttribute("totalNo", page.getTotalCount());
        // 再次将分页相关数据返回页面End
        List<Object> list = (List<Object>) page.getResult();
        request.setAttribute("list", list);
        return mapping.findForward("toAerectificationList");
    }

    /**
     * 跳转跟踪整改信息详细页面.
     */
    public ActionForward toAerectificationDetailPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");
        BsAerectification bs = enforceService.getAerectification(id);
        RequestUtil.setFormFromBean(dyna, bs);
        //由于数据库保存内容中换行符已转义，现需要反转义
        dyna.set("trackcontend", StringEscapeUtils.unescapeJava(bs.getTrackcontend()));
        BsAepeople aePeople = enforceService.getAePeopleByCardId(bs.getCrtlogin());
        if (aePeople != null) {
            request.setAttribute("crtLoginNm", aePeople.getPepname() + "-" + aePeople.getCreditno());
        } else {
            request.setAttribute("crtLoginNm",
                    enforceService.getCredential(bs.getCrtlogin(), UserCredentialName.nickname));
        }
        return mapping.findForward("toAerectificationDetailPage");
    }

    /**
     * 跳转跟踪整改信息修改页面.
     */
    public ActionForward toUpdateAerectificationPage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        this.saveToken(request);// 产生令牌值
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            id = dyna.get("id").toString();
        }
        BsAerectification bs = enforceService.getAerectification(id);
        // 判断当前用户是否有权限修改该信息
        if (bs == null) {
            this.printMessage(request, response, String.format(ERROR8, id, "跟踪整改信息"), ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bs.getCrtlogin();
            String nowLoginUser = this.getPrivCredential(UserCredentialName.login.name(), request,
                    response);
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "跟踪整改信息"), ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLoginUser)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLoginUser.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "跟踪整改信息"), ATTR_ERROR);
                return null;
            }
        }
        if (BooleanUtils.isFalse(bs.isCanbeupdate())) {
            this.printMessage(request, response, String.format(ERROR9, bs.getAeno(), "跟踪整改信息"),
                    ATTR_ERROR);
            return null;
        }
        RequestUtil.setFormFromBean(dyna, bs);
        //由于数据库保存内容中换行符已转义，现需要饭转义
        dyna.set("trackcontend", StringEscapeUtils.unescapeJava(bs.getTrackcontend()));
        BsAepeople aePeople = enforceService.getAePeopleByCardId(bs.getCrtlogin());
        if (aePeople != null) {
            request.setAttribute("crtLoginNm", aePeople.getPepname() + "-" + aePeople.getCreditno());
        } else {
            request.setAttribute("crtLoginNm",
                    enforceService.getCredential(bs.getCrtlogin(), UserCredentialName.nickname));
        }
        return mapping.findForward("toUpdateAerectificationPage");
    }

    /**
     * 跟踪整改信息修改.
     */
    public ActionForward updateAerectification(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        if (this.isTokenValid(request)) {// 正常提交
            this.resetToken(request);// 清空当前会话中的token值
            // 如果当前用户不属于人行用户，则不能使用该功能
            if (!super.isPcbUser(request, response)) {
                this.printMessage(request, response, ERROR1, ATTR_ERROR);
                return null;
            }
            String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(),
                    request, response);
            String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                    request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
            DynaActionForm dyna = (DynaActionForm) form;
            request.setAttribute("methodname", "toAerectificationList");
            String id = dyna.get("id").toString();
            // 调用跟踪整改信息保存方法
            try {
                // BsAerectification bs = RequestUtil.getBeanFromParams(request,
                // BsAerectification.class);
                BsAerectification bs = enforceService.getAerectificationById(id);
                bs.setTrackcontend(dyna.get("trackcontend").toString());
                //处理文本框中有换行符等情况
                bs.setTrackcontend(StringEscapeUtils.escapeJava(bs.getTrackcontend()));
                if (dyna.get("rectificationstdate") != null
                        && !dyna.get("rectificationstdate").toString().trim()
                                .equals(StringUtils.EMPTY)) {
                    bs.setRectificationstdate(DateUtils.parseDate(dyna.get("rectificationstdate")
                            .toString(), DateUtil.DATE_FORMAT_ARRAY));
                }
                if (dyna.get("rectificationtmdate") != null
                        && !dyna.get("rectificationtmdate").toString().trim()
                                .equals(StringUtils.EMPTY)) {
                    bs.setRectificationtmdate(DateUtils.parseDate(dyna.get("rectificationtmdate")
                            .toString(), DateUtil.DATE_FORMAT_ARRAY));
                }
                bs.setUpdateate(new Date());
                enforceService.saveAerectification(bs);
                request.setAttribute(ATTR_MESSAGE, "修改行政执法整改情况【" + bs.getAeno() + "】操作成功！");
                LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "修改跟踪整改信息",
                        "操作成功!"));
            } catch (Exception e) {
                LOGGER.error(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "修改跟踪整改信息",
                        "操作失败!"), e);
                request.setAttribute(ATTR_MESSAGE, "修改行政执法整改情况操作失败！错误原因：" + e.getMessage());
            }
            return mapping.findForward("toAdminEnforceMessage");
        } else {// 重复提交
            this.printMessage(request, response, String.format(ERROR2, "整改信息查询"), ATTR_ERROR);
            return null;
        }
    }

    /**
     * 跟踪整改信息删除.
     */
    public ActionForward delAerectification(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(), request,
                response);
        String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
        request.setAttribute("methodname", "toAerectificationList");
        String id = request.getParameter("id");
        BsAerectification bs = enforceService.getAerectification(id);
        // 判断当前用户是否有权限修改该信息
        if (bs == null) {
            this.printMessage(request, response, String.format(ERROR8, id, "跟踪整改信息"), ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bs.getCrtlogin();
            String nowLogin = this.getPrivCredential(UserCredentialName.login.name(), request,
                    response);
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "跟踪整改信息"), ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLogin)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLogin.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "跟踪整改信息"), ATTR_ERROR);
                return null;
            }
        }
        // 调用检查工作记录删除方法
        try {
            enforceService.deleteAerectificationById(id);
            request.setAttribute(ATTR_MESSAGE, "刪除行政执法整改情况【" + id + "】操作成功!");
            LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "删除跟踪整改信息",
                    "操作成功!"));
        } catch (Exception e) {
            LOGGER.error(
                    String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "删除跟踪整改信息", "操作失败!"),
                    e);
            request.setAttribute(ATTR_MESSAGE, "删除行政执法整改情况操作失败!错误原因:" + e.getMessage());
        }
        return mapping.findForward("toAdminEnforceMessage");
    }

    /**
     * 获取数据字典数据.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getDictionary(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("获取数据字典数据", e);
        }

        String outputStr = "{InzcStatus:'aaa'}";
        if (out != null) {
            out.print(outputStr);
        }

        return null;
    }

    /**
     * 获取当前登陆用户相关信息.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getLoginInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("获取当前登陆用户相关信息", e);
        }
        StringBuffer outputStr = new StringBuffer();
        try {
            outputStr.append("{orgname:'");
            String orgNo = super.getPubCredential(UserCredentialName.organization.name(), request,
                    response);
            if (StringUtils.isNotBlank(orgNo)) {
                BsOrg org = systemBaseInfoManager.getOrgByNo(orgNo);
                if (org != null && StringUtils.isNotBlank(org.getName())) {
                    outputStr.append(org.getName());
                }
            }
            outputStr.append("',login:'");

            String login = super.getPrivCredential(UserCredentialName.login.name(), request,
                    response);
            if (StringUtils.isNotBlank(login)) {
                //屏蔽身份证号，eg：
                if (login.length() >= 15) {
                    outputStr.append(StringUtils.overlay(StringUtils.overlay(login, "*****", 1, 6),
                            "***", 14, 17));
                } else {
                    outputStr.append(login);
                }
            }
            outputStr.append("',nickname:'");
            String nickname = super.getPubCredential(UserCredentialName.nickname.name(), request,
                    response);
            if (StringUtils.isNotBlank(nickname)) {
                outputStr.append(nickname);
            }
            outputStr.append("',principals:'");
            Set<Principal> principal = super.getSubject(request, response).getPrincipals();
            if (CollectionUtils.isNotEmpty(principal)) {
                for (Iterator<Principal> iterator = principal.iterator(); iterator.hasNext();) {
                    Principal prcp = iterator.next();
                    if (prcp instanceof RolePrincipal) {
                        RolePrincipal rolePrcp = (RolePrincipal) prcp;
                        outputStr.append(rolePrcp.getLocalName()).append("，");
                    }
                }
            }
            outputStr.append("'");
            outputStr.append("}");
        } catch (Exception e) {
            LOGGER.error("获取当前登陆用户相关信息", e);
            if (out != null) {
                out.print("{login:'',nickname:'',orgname:'',principals:''}");
            }
            return null;
        }

        if (out != null) {
            out.print(outputStr.toString().replace("，'}", "'}"));
        }
        return null;

    }

    /**
     * 双击被检查机构树.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getAeedOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String parent = request.getParameter("parentNo");
        String nowloginerOrgNo = this.getPubCredential(UserCredentialName.organization.name(),
                request, response);
        BsOrg bsOrg = enforceService.getOrgByNo(nowloginerOrgNo);
        PrintWriter out = null;
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("双击被检查机构树", e);
        }
        List<BsOrg> childrenOrgslt = null;
        try {
            if (StringUtils.isNotBlank(parent) && StringUtils.isNotBlank(nowloginerOrgNo)
                    && bsOrg != null) {
                if (nowloginerOrgNo.equals("A1000151000028")) {
                    // 如果当前用户为成都分行用户
                    childrenOrgslt = enforceService.getOrgsByParentOrgNo(parent);
                } else if (StringUtils.isNotBlank(bsOrg.getParentno())
                        && bsOrg.getParentno().equals("A1000151000028")) {
                    // 如果为县支行
                    childrenOrgslt = enforceService.getOrgsByParentOrgNoForZHOrXZH(parent,
                            nowloginerOrgNo);
                } else if (StringUtils.isNotBlank(bsOrg.getParentno())
                        && !bsOrg.getParentno().equals("A1000151000028")) {
                    // 如果为中支
                    childrenOrgslt = enforceService.getOrgsByParentOrgNoForZHOrXZH(parent,
                            nowloginerOrgNo);
                }
            } else {
                out.print("{totalProperty:0,\"root\":[]}");
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("双击被检查机构树", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        StringBuffer outputSb = new StringBuffer("{totalProperty:" + childrenOrgslt.size()
                + ",root:[");
        BsOrg org = null;
        for (int i = 0; i < childrenOrgslt.size(); i++) {
            org = childrenOrgslt.get(i);
            outputSb.append("{'value':'").append(org.getName()).append("--").append(org.getNo())
                    .append("','text':'").append(org.getName()).append("-").append(org.getNo())
                    .append("'}");
            if (i != childrenOrgslt.size() - 1) {
                outputSb.append(",");
            }
        }
        outputSb.append("]}");
        if (out != null) {
            out.print(outputSb.toString());
        }
        return null;
    }

    /**
     * 选择涉及机构下属机构.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     */
    public void selectChildInvolveBank(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String parent = request.getParameter("node");
        /*
         * String nowloginerOrgNo = this.getPubCredential(
         * UserCredentialName.organization.name(), request, response); String
         * isPcbUser = this.getPubCredential(
         * UserCredentialName.ispcbuser.name(), request, response);
         */
        try {
            List<JSONObject> list = new ArrayList<JSONObject>();
            BsOrg bc = null;
            JSONObject object = null;
            /*
             * BsOrg bc = systemBaseInfoManager.getOrgByNo(parent); JSONObject
             * object = new JSONObject(); if (bc != null) { object.put("id",
             * bc.getNo()); object.put("text", bc.getName());
             * object.put("singleClickExpand", bc.getIspcb()); list.add(object); }
             */
            List<BsOrg> resultOrg = systemBaseInfoManager.orgListByParentNo(parent);
            if (resultOrg != null) {
                for (int i = 0; i < resultOrg.size(); i++) {
                    bc = resultOrg.get(i);
                    object = new JSONObject();
                    object.put("id", bc.getNo());
                    object.put("text", bc.getName());
                    object.put("singleClickExpand", bc.getIspcb());
                    list.add(object);
                }
            }
            JSONArray array = JSONArray.fromObject(list);
            response.setCharacterEncoding(CharEncoding.UTF_8);
            response.getWriter().write(array.toString());
            response.getWriter().close();
        } catch (Exception e) {
            LOGGER.error("选择涉及机构下属机构", e);
        }
    }

    public void setAeinspectionAnlStsicSvce(AeinspectionAnlStsicSvce aeinspectionAnlStsicSvce) {
        this.aeinspectionAnlStsicSvce = aeinspectionAnlStsicSvce;
    }

    /**
     * @param noGenerator
     */
    public void setNoGenerator(NoGenerator noGenerator) {
        this.noGenerator = noGenerator;
    }

    /**
     * @param enforceService
     */
    public void setEnforceService(EnforceService enforceService) {
        this.enforceService = enforceService;
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

    public void setPunishService(PunishService punishService) {
        this.punishService = punishService;
    }

    public void setWordGenerator(WordGenerator wordGenerator) {
        this.wordGenerator = wordGenerator;
    }

}
