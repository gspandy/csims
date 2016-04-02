package com.gtm.csims.controll.enforce;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import net.sweet.dao.generic.support.Page;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;
import org.springframework.dao.DataIntegrityViolationException;

import com.gtm.csims.base.BaseAction;
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
import com.gtm.csims.model.BsAdmpunishcons;
import com.gtm.csims.model.BsAeFeedBack;
import com.gtm.csims.model.BsAePublishFeedBack;
import com.gtm.csims.model.BsAeconclusion;
import com.gtm.csims.model.BsAeinspection;
import com.gtm.csims.model.BsAeinspectionAnl;
import com.gtm.csims.model.BsAepeople;
import com.gtm.csims.model.BsFactbook;
import com.gtm.csims.model.BsNogenerate;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.utils.DateUtil;
import com.gtm.csims.utils.JsonDateValueProcessor;
import com.gtm.csims.utils.RequestUtil;

/**
 * 行政处罚管理Action.
 * 
 * @author Sweet
 * 
 */
@SuppressWarnings("unchecked")
public class AdministrativeSanctionManagerAction extends BaseAction {

    /**
     * 日志调试.
     */
    private static Logger LOGGER = Logger.getLogger(AdministrativeSanctionManagerAction.class);

    /**
     * 
     */
    private static JsonConfig JSON_CONFIG = new JsonConfig();
    static {
        JSON_CONFIG.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor());
    }

    private PunishService punishService;
    private NoGenerator noGenerator;
    private EnforceService enforceService;
    private SystemBaseInfoManager systemBaseInfoManager;
    @SuppressWarnings("unused")
    private RemindService remindService;
    private FileHandler fileHandler;
    private WordGenerator wordGenerator;

    /**
     * 下载附件.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward downloadAtt(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        String attId = request.getParameter("attId").toString();
        try {
            fileHandler.download(attId, request, response);
        } catch (Exception e) {
            LOGGER.error("下载附件", e);
            super.printMessage(request, response, String.format(ERROR4, e.getMessage()), ATTR_ERROR);
        }
        return null;
    }

    /**
     * 跳转行政处罚信息列表页面.
     */
    public ActionForward toAeSanctionList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals(StringUtils.EMPTY)) ? "1"
                : (String) dyna.get("pageNo");
        String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals(StringUtils.EMPTY)) ? PAGE_CONTAIN_NUMBER
                .toString() : (String) dyna.get("pageSize");
        String punishno = (dyna.get("punishno") == null || dyna.get("punishno").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("punishno");
        String aeorgChoice = (dyna.get("aeorgChoice") == null || dyna.get("aeorgChoice").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("aeorgChoice");
        String illegalunit = (dyna.get("illegalunit") == null || dyna.get("illegalunit").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("illegalunit");
        String aeplanstdate = (dyna.get("aeplanstdate") == null || dyna.get("aeplanstdate").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("aeplanstdate");
        String aeplantmdate = (dyna.get("aeplantmdate") == null || dyna.get("aeplantmdate").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("aeplantmdate");
        int pageCountTemp = 0;
        // 调用获取执法检查人员分页方法
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
        if (StringUtils.isEmpty(loginOrgNo)) {
            request.setAttribute(ATTR_MESSAGE,
                    String.format(ERROR3, this.getPrivCredential(UserCredentialName.login.name(), request, response)));
            request.setAttribute("methodname", "toAeSanctionList");
            return mapping.findForward("toAeSanctionMessage");
        }
        Page page = punishService.queryAdmPunish(punishno, illegalunit, aeorgChoice, aeplanstdate, aeplantmdate,
                Integer.parseInt(pageNo), Integer.parseInt(pageSize), loginOrgNo);
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
            // 分页处理End
        }

        // 再次将分页相关数据返回页面Start
        request.setAttribute("pageNo", pageNo);
        dyna.set("pageNo", pageNo);
        request.setAttribute("pageCount", pageCountTemp);
        dyna.set("pageCount", pageCountTemp);
        request.setAttribute("totalNo", page.getTotalCount());
        // 再次将分页相关数据返回页面End
        List<Object> list = (List<Object>) page.getResult();
        request.setAttribute("list", list);
        return mapping.findForward("toAeSanctionList");
    }

    /**
     * 跳转行政处罚信息登记页面.
     */
    public ActionForward toAeSanctionInitPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        this.saveToken(request);// 产生令牌值
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String enid = dyna.getString("id");
        String nowLoginUser = this.getPrivCredential(UserCredentialName.login.name(), request, response);
        // 判断当前用户是否有权限修改该信息
        BsAdmenforce bs = enforceService.getAdmenforce(enid);
        if (bs == null) {
            super.printMessage(request, response, "没有找到行政执法立项【" + enid + ",请重新选择", ATTR_ERROR);
            return null;
        }
        BsAeconclusion bsAeconclusion = enforceService.getAeconclusionByAeno(bs.getAeno());
        if (bsAeconclusion == null) {
            super.printMessage(request, response, "没有找到行政执法结论【" + bs.getAeno() + ",请重新选择", ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bsAeconclusion.getCrtlogin();
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
        dyna.set("aeno", bs.getAeno());
        dyna.set("aeopnionno", bsAeconclusion.getAeopnionno());
        dyna.set("aeorgno", bs.getAeorgno());
        dyna.set("aeorgnm", bs.getAeorgnm());
        dyna.set("aeedorgno", bs.getAeedorgno());
        dyna.set("aeedorgnm", bs.getAeedorgnm());
        // RequestUtil.setFormFromBean(dyna, bs);
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
        // 获取执法检查编号
        BsNogenerate bsNogenerate = noGenerator.getNoGenerate(loginOrgNo, noGenerator.getYear());
        if (bsNogenerate == null) {
            request.setAttribute(ATTR_MESSAGE, "<b>登记行政处罚发生错误</b><br><b>失败原因:</b>不能获取当前机构的执法编号规则： " + loginOrgNo
                    + "<br><b>解决方法:</b>需要联系管理员维护该机构执法立项规则!");
            return mapping.findForward("toAdminEnforceMessage");
        } else {
            dyna.set("pbnshnotext", bsNogenerate.getPbnshnotext());
            dyna.set("pbnshnoyear", bsNogenerate.getPbnshnoyear());
            dyna.set("pbnshnoindex", bsNogenerate.getPbnshnoindex());
            request.setAttribute("ayList", super.getDicMap("AY"));
            return mapping.findForward("toAeSanctionInitPage");
        }
    }

    /**
     * 撤销行政处罚信息.
     */
    public ActionForward repealPunish(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String id = dyna.getString("id");
        BsAeconclusion bsAeconclusion = enforceService.getAeconclusion(id);
        // 判断当前用户是否有权限撤销该信息
        if (bsAeconclusion == null) {
            this.printMessage(request, response, String.format(ERROR8, id, "行政处罚立项"), ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bsAeconclusion.getCrtlogin();
            String nowLoginUser = this.getPrivCredential(UserCredentialName.login.name(), request, response);
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "行政处罚立项"), ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLoginUser)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLoginUser.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "行政处罚立项"), ATTR_ERROR);
                return null;
            }
        }
        // 判断当前执法结论是否已经存在行政处罚立项信息，如果存在，则不能撤销
        List exsitPunishLt = punishService.getAdmPunishListByAeNo(bsAeconclusion.getAeno().trim());
        if (CollectionUtils.isNotEmpty(exsitPunishLt)) {
            this.printMessage(request, response,
                    String.format("错误原因：当前行政执法检查工作%s已经存在%s，不能撤销", bsAeconclusion.getAeno().trim(), "行政处罚立项"),
                    ATTR_ERROR);
            return null;
        }
        bsAeconclusion.setFlag("Repealed");
        try {
            enforceService.saveAeconclusion(bsAeconclusion);
        } catch (Exception e) {
            LOGGER.error("撤销行政处罚信息", e);
        }
        request.setAttribute("methodname", "toAeSanctionList");
        request.setAttribute(ATTR_MESSAGE, "撤销行政处罚【" + bsAeconclusion.getAeno() + "】操作成功!");
        return mapping.findForward("toAeSanctionMessage");
    }

    /**
     * 从处罚登记处跳转行政处罚信息登记页面.
     */
    public ActionForward toAeSanctionInitPageFromPunish(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        this.saveToken(request);// 产生令牌值
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String id = dyna.getString("id");

        String nowLoginUser = this.getPrivCredential(UserCredentialName.login.name(), request, response);
        // 判断当前用户是否有权限修改该信息
        BsAeconclusion bsAeconclusion = enforceService.getAeconclusion(id);
        if (bsAeconclusion == null) {
            super.printMessage(request, response, "没有找到行政执法检查结论【" + id + ",请重新选择", ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bsAeconclusion.getCrtlogin();
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

        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
        if (!bsAeconclusion.getAeorgno().equals(loginOrgNo)) {
            request.setAttribute(ATTR_MESSAGE, "当前登录用户不能添加非本机构创建的行政处罚信息");
            request.setAttribute("methodname", "toAeSanctionConList");
            return mapping.findForward("toAeSanctionMessage");
        }
        List<BsAdmpunish> pnshlt = punishService.getAdmPunishListByAeNo(bsAeconclusion.getAeno());
        if (CollectionUtils.isNotEmpty(pnshlt)) {
            request.setAttribute(ATTR_MESSAGE, "已经存在执法检查工作记录编号为【" + bsAeconclusion.getAeno() + "】的行政处罚记录，不能重复添加");
            request.setAttribute("methodname", "toAeSanctionList");
            return mapping.findForward("toAeSanctionMessage");
        }
        dyna.set("aeno", bsAeconclusion.getAeno());
        dyna.set("aeopnionno", bsAeconclusion.getAeopnionno());
        dyna.set("aeorgno", bsAeconclusion.getAeorgno());
        dyna.set("aeorgnm", bsAeconclusion.getAeorgnm());
        dyna.set("aeedorgno", bsAeconclusion.getAeedorgno());
        dyna.set("aeedorgnm", bsAeconclusion.getAeedorgnm());
        // RequestUtil.setFormFromBean(dyna, bs);
        // 获取执法检查编号
        BsNogenerate bsNogenerate = noGenerator.getNoGenerate(loginOrgNo, noGenerator.getYear());
        if (bsNogenerate == null) {
            request.setAttribute(ATTR_MESSAGE, "<b>登记行政处罚发生错误</b><br><b>失败原因:</b>不能获取当前机构的执法编号规则： " + loginOrgNo
                    + "<br><b>解决方法:</b>需要联系管理员维护该机构执法立项规则!");
            return mapping.findForward("toAdminEnforceMessage");
        } else {
            dyna.set("pbnshnotext", bsNogenerate.getPbnshnotext());
            dyna.set("pbnshnoyear", bsNogenerate.getPbnshnoyear());
            dyna.set("pbnshnoindex", bsNogenerate.getPbnshnoindex());
            request.setAttribute("ayList", super.getDicMap("AY"));
            return mapping.findForward("toAeSanctionInitPage");
        }
    }

    /**
     * 行政处罚信息保存方法.
     */
    public ActionForward createAeSanction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        if (this.isTokenValid(request)) {// 正常提交
            this.resetToken(request);// 清空当前会话中的token值
            // 如果当前用户不属于人行用户，则不能使用该功能
            if (!super.isPcbUser(request, response)) {
                this.printMessage(request, response, ERROR1, ATTR_ERROR);
                return null;
            }
            String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(), request, response);
            String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
            DynaActionForm dyna = (DynaActionForm) form;
            request.setAttribute("methodname", "toAeSanctionList");
            // 调用行政执法保存方法
            try {
                // System.out.println(dyna.get("reason").toString());
                BsAdmpunish bs = RequestUtil.getBeanFromParams(request, BsAdmpunish.class);
                bs.setPunishno(dyna.get("pbnshnotext").toString() + "【" + dyna.get("pbnshnoyear").toString() + "】第【"
                        + dyna.get("pbnshnoindex").toString() + "】号");
                bs.setIllegalunit(dyna.getString("aeedorgnm"));
                bs.setCrtdate(new Date());
                // 保存创建人为当前登录人id
                bs.setCrtlogin(this.getPrivCredential(UserCredentialName.login.name(), request, response));
                // 处理文本框中有换行符等情况
                bs.setSummarys(StringEscapeUtils.escapeJava(bs.getSummarys()));
                bs.setPeopleopnion(StringEscapeUtils.escapeJava(bs.getPeopleopnion()));
                bs.setDptopnion(StringEscapeUtils.escapeJava(bs.getDptopnion()));
                bs.setChairopnion(StringEscapeUtils.escapeJava(bs.getChairopnion()));
                // 关联违法人员 (暂时关闭)
                // String illegalpeople = request.getParameter("illegalpeople");
                // if (StringUtils.isNotBlank(illegalpeople)) {
                // try {
                // String[] illegalpeopleArr = illegalpeople.split(",");
                // for (int i = 0; i < illegalpeopleArr.length; i++) {
                // // System.out.println(relpeoplesArr[i]);
                // // 插入涉及用户档案信息
                // String[] tempArr = illegalpeopleArr[i].split("--");
                // enforceService.relatePersonProfile("行政处罚违法人员", tempArr[1],
                // tempArr[0], "在《执法检查意见书（"
                // + StringUtils.trimToEmpty(bs.getAeopnionno()) +
                // "）》中被列为处罚对象");
                // }
                // } catch (Exception e) {
                // e.printStackTrace();
                // }
                // }
                bs.setIsfinished(false);
                bs.setCanbeupdate(true);
                try {
                    punishService.saveAdmPunish(bs, loginOrgNo, noGenerator.getYear());
                    punishService.updateAeCon(bs.getAeno());
                } catch (DataIntegrityViolationException e) {
                    request.setAttribute(
                            ATTR_MESSAGE,
                            "<b>保存行政处罚操作失败,行政处罚立项编号重复</b><br><b>失败原因:</b>因为在你保存处罚信息之前有其他用户已经保存了行政处罚立项编号 "
                                    + bs.getPunishno() + " 的处罚信息<br><b>解决方法:</b>请重新点击'行政处罚登记'菜单获取新行政处罚立项编号，再输入保存!");
                    return mapping.findForward("toAeSanctionMessage");
                }
                request.setAttribute(ATTR_MESSAGE, "保存行政处罚登记【" + bs.getAeno() + "】操作成功！");
                LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "保存行政处罚登记信息", "操作成功!"));
            } catch (Exception e) {
                LOGGER.error(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "保存行政处罚登记信息", "操作失败!"), e);
                request.setAttribute(ATTR_MESSAGE, "保存行政处罚登记操作失败！错误原因：" + e.getMessage());
            }
            return mapping.findForward("toAeSanctionMessage");
        } else {// 重复提交
            this.printMessage(request, response, String.format(ERROR2, "保存行政处罚登记信息"), ATTR_ERROR);
            return null;
        }
    }

    /**
     * 跳转行政处罚信息详细页面.
     */
    public ActionForward toAeSanctionDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");
        BsAdmpunish bs = punishService.getAdmPunish(id);
        RequestUtil.setFormFromBean(dyna, bs);
        // 由于数据库保存内容中换行符已转义，现需要反转义
        request.setAttribute("summarys", StringEscapeUtils.unescapeJava(bs.getSummarys()));
        request.setAttribute("dptopnion", StringEscapeUtils.unescapeJava(bs.getDptopnion()));
        request.setAttribute("peopleopnion", StringEscapeUtils.unescapeJava(bs.getPeopleopnion()));
        request.setAttribute("chairopnion", StringEscapeUtils.unescapeJava(bs.getChairopnion()));

        request.setAttribute("dicMap", super.getDicMap());
        request.setAttribute("form", dyna);
        BsAepeople aePeople = null;
        try {
            aePeople = enforceService.getAePeopleByCardId(bs.getCrtlogin());
        } catch (Exception e) {
            LOGGER.error("跳转行政处罚信息详细页面", e);
        }
        if (aePeople != null) {
            request.setAttribute("crtLoginNm", aePeople.getPepname() + "-" + aePeople.getCreditno());
        } else {
            request.setAttribute("crtLoginNm",
                    enforceService.getCredential(bs.getCrtlogin(), UserCredentialName.nickname));
        }
        return mapping.findForward("toAeSanctionDetailPage");
    }

    /**
     * 跳转行政处罚立项信息修改页面.
     */
    public ActionForward toUpdateAeSanctionPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
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
        BsAdmpunish bs = null;
        try {
            bs = punishService.getAdmPunish(id);
        } catch (Exception e) {
            LOGGER.error("跳转行政处罚立项信息修改页面", e);
        }
        // 判断当前用户是否有权限修改该信息
        if (bs == null) {
            this.printMessage(request, response, String.format(ERROR8, id, "行政处罚立项"), ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bs.getCrtlogin();
            String nowLoginUser = this.getPrivCredential(UserCredentialName.login.name(), request, response);
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "行政处罚立项"), ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLoginUser)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLoginUser.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "行政处罚立项"), ATTR_ERROR);
                return null;
            }
        }
        // 行政处罚信息能否修改开关，如果开启，则行政处罚流程结束则不能修改，否则可以修改
        if (BooleanUtils.isFalse(bs.isCanbeupdate())) {
            this.printMessage(request, response, String.format(ERROR9, bs.getAeno(), "行政处罚立项"), ATTR_ERROR);
            return null;
        }
        RequestUtil.setFormFromBean(dyna, bs);
        // 由于数据库保存内容中换行符已转义，现需要反转义
        dyna.set("summarys", StringEscapeUtils.unescapeJava(bs.getSummarys()));
        dyna.set("dptopnion", StringEscapeUtils.unescapeJava(bs.getDptopnion()));
        dyna.set("peopleopnion", StringEscapeUtils.unescapeJava(bs.getPeopleopnion()));
        dyna.set("chairopnion", StringEscapeUtils.unescapeJava(bs.getChairopnion()));

        request.setAttribute("ayList", super.getDicMap("AY"));
        request.setAttribute("form", dyna);
        request.setAttribute("reason", dyna.get("reason"));
        BsAepeople aePeople = enforceService.getAePeopleByCardId(bs.getCrtlogin());
        if (aePeople != null) {
            request.setAttribute("crtLoginNm", aePeople.getPepname() + "-" + aePeople.getCreditno());
        } else {
            request.setAttribute("crtLoginNm",
                    enforceService.getCredential(bs.getCrtlogin(), UserCredentialName.nickname));
        }
        return mapping.findForward("toUpdateAeSanctionPage");
    }

    /**
     * 行政处罚信息修改.
     */
    public ActionForward updateAeSanction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        if (this.isTokenValid(request)) {// 正常提交
            this.resetToken(request);// 清空当前会话中的token值
            // 如果当前用户不属于人行用户，则不能使用该功能
            if (!super.isPcbUser(request, response)) {
                this.printMessage(request, response, ERROR1, ATTR_ERROR);
                return null;
            }
            DynaActionForm dyna = (DynaActionForm) form;
            String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(), request, response);
            String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
            request.setAttribute("methodname", "toAeSanctionList");
            try {
                // BsAdmpunish bs = RequestUtil.getBeanFromParams(request,
                // BsAdmpunish.class);
                BsAdmpunish bs = punishService.getAdmPunishById(dyna.getString("id"));
                bs.setIllegalpeople(dyna.getString("illegalpeople"));
                bs.setReason(dyna.getString("reason"));
                bs.setSummarys(dyna.getString("summarys"));
                bs.setDptopnion(dyna.getString("dptopnion"));
                bs.setPeopleopnion(dyna.getString("peopleopnion"));
                bs.setChairopnion(dyna.getString("chairopnion"));
                bs.setDpter(dyna.getString("dpter"));
                bs.setChairer(dyna.getString("chairer"));
                bs.setPeopler(dyna.getString("peopler"));
                bs.setDpterdate(DateUtils.parseDate(dyna.getString("dpterdate"), DateUtil.DATE_FORMAT_ARRAY));
                bs.setChairerdate(DateUtils.parseDate(dyna.getString("chairerdate"), DateUtil.DATE_FORMAT_ARRAY));
                bs.setPeoplerdate(DateUtils.parseDate(dyna.getString("peoplerdate"), DateUtil.DATE_FORMAT_ARRAY));
                bs.setUpdateate(new Date());
                // 处理文本框中有换行符等情况
                bs.setSummarys(StringEscapeUtils.escapeJava(bs.getSummarys()));
                bs.setPeopleopnion(StringEscapeUtils.escapeJava(bs.getPeopleopnion()));
                bs.setDptopnion(StringEscapeUtils.escapeJava(bs.getDptopnion()));
                bs.setChairopnion(StringEscapeUtils.escapeJava(bs.getChairopnion()));
                punishService.saveAdmPunish(bs);
                request.setAttribute(ATTR_MESSAGE, "修改行政处罚登记【" + bs.getAeno() + "】操作成功！");
                LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "修改行政处罚信息", "操作成功!"));
            } catch (Exception e) {
                LOGGER.error(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "修改行政处罚信息", "操作失败!"), e);
                request.setAttribute(ATTR_MESSAGE, "修改行政处罚登记操作失败！错误原因：" + e.getMessage());
            }
            return mapping.findForward("toAeSanctionMessage");
        } else {// 重复提交
            this.printMessage(request, response, String.format(ERROR2, "行政处罚立项查询"), ATTR_ERROR);
            return null;
        }
    }

    /**
     * 行政处罚信息删除.
     */
    public ActionForward delAeSanction(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(), request, response);
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
        request.setAttribute("methodname", "toAeSanctionList");
        String id = request.getParameter("id");
        BsAdmpunish bs = null;
        try {
            bs = punishService.getAdmPunish(id);
        } catch (Exception e) {
            LOGGER.error("行政处罚信息删除时获取处罚信息失败", e);
        }
        // 判断当前用户是否有权限修改该信息
        if (bs == null) {
            this.printMessage(request, response, String.format(ERROR8, id, "行政处罚立项"), ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bs.getCrtlogin();
            String nowLogin = this.getPrivCredential(UserCredentialName.login.name(), request, response);
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "行政处罚立项"), ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLogin)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLogin.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "行政处罚立项"), ATTR_ERROR);
                return null;
            }
        }
        try {
            punishService.deleteAdmPunishById(id);
            request.setAttribute(ATTR_MESSAGE, "删除行政处罚登记【" + id + "】操作成功!");
            LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "删除行政处罚登记", "操作成功!"));
        } catch (Exception e) {
            LOGGER.error(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "删除行政处罚登记", "操作失败!"), e);
            request.setAttribute(ATTR_MESSAGE, "操作失败!错误原因:" + e.getMessage());
        }
        return mapping.findForward("toAeSanctionMessage");
    }

    /**
     * 跳转行政处罚结论登记页面.
     */
    public ActionForward toAeSanctionConInitPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        this.saveToken(request);// 产生令牌值
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");

        String nowLoginUser = this.getPrivCredential(UserCredentialName.login.name(), request, response);
        // 判断当前用户是否有权限修改该信息
        BsAdmpunish bs = null;
        try {
            bs = punishService.getAdmPunish(id);
        } catch (Exception e) {
            LOGGER.error("跳转行政处罚结论登记页面", e);
        }
        if (bs == null) {
            super.printMessage(request, response, "没有找到行政处罚立项【" + id + ",请重新选择", ATTR_ERROR);
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
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
        if (!bs.getAeorgno().equals(loginOrgNo)) {
            request.setAttribute(ATTR_MESSAGE, "当前登录用户不能添加非本机构创建的行政处罚信息");
            request.setAttribute("methodname", "toAeSanctionConList");
            return mapping.findForward("toAeSanctionMessage");
        }
        if (punishService.getAdmPunishConByIno(bs.getAeno()) != null) {
            request.setAttribute(ATTR_MESSAGE, "已经存在执法检查工作记录编号为【" + bs.getAeno() + "】的行政处罚结论记录，不能重复添加");
            request.setAttribute("methodname", "toAeSanctionConList");
            return mapping.findForward("toAeSanctionMessage");
        }
        RequestUtil.setFormFromBean(dyna, bs);
        // 获取执法检查意见书编号
        // String loginOrgNo = this.getPubCredential(
        // UserCredentialName.organization.name(), request, response);
        // BsNogenerate bsNogenerate =
        // noGenerator.getNoGenerate(Constants.PCB_SC_ORG_NO,
        // String.valueOf(year));
        // dyna.set("aetext", bsNogenerate..getAenotext());
        // dyna.set("aeyear", bsNogenerate.getAenoyear());
        // dyna.set("aeindex", bsNogenerate.getAenoindex());

        // 取消自动生成处罚决定书编号，目前改为采用手动
        // dyna.set("punishbookno", "处罚决定书第【" + DateFormatUtils.format(new
        // Date(), DateUtil.DATE_FORMAT_YYYYMMDDHHMMSSSSS)
        // + "】号");
        dyna.set("punishbookno", StringUtils.EMPTY);

        return mapping.findForward("toAeSanctionConInitPage");
    }

    /**
     * 行政处罚结论信息保存方法.
     */
    public ActionForward createAeSanctionCon(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        if (this.isTokenValid(request)) {// 正常提交
            this.resetToken(request);// 清空当前会话中的token值
            // 如果当前用户不属于人行用户，则不能使用该功能
            if (!super.isPcbUser(request, response)) {
                this.printMessage(request, response, ERROR1, ATTR_ERROR);
                return null;
            }
            DynaActionForm dyna = (DynaActionForm) form;
            String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(), request, response);
            String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
            request.setAttribute("methodname", "toAeSanctionConList");

            // 调用行政执法保存方法
            try {
                BsAdmpunishcons bs = RequestUtil.getBeanFromParams(request, BsAdmpunishcons.class);
                FormFile file = null;
                String attchuuid = null;

                String punishbookno = dyna.getString("punishbookno");
                if (StringUtils.isBlank(punishbookno)) {
                    this.printMessage(request, response, " 行政处罚处罚决定书编号不能为空", ATTR_ERROR);
                    return null;
                }

                /** 保存行政处罚决定书 * */
                file = (FormFile) dyna.get("punishbookattapath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request.getSession()
                            .getServletContext().getRealPath(File.separator));
                    bs.setPunishbookatta(attchuuid);
                }
                /** 保存行政处罚告知书 * */
                file = (FormFile) dyna.get("punishgzbookattapath");
                if (file != null && StringUtils.isNotBlank(file.getFileName())) {
                    attchuuid = fileHandler.saveBsAttachment(nowLoginUser, file, request.getSession()
                            .getServletContext().getRealPath(File.separator));
                    bs.setPunishgzbookatta(attchuuid);
                }
                // 处罚金额
                String punishCountStr = dyna.get("punishcount").toString();
                if (StringUtils.isNotBlank(punishCountStr)) {
                    try {
                        BigDecimal punishCount = new BigDecimal(punishCountStr);
                        bs.setPunishcount(punishCount);
                    } catch (Exception e) {
                        bs.setPunishcount(BigDecimal.ZERO);
                    }
                } else {
                    bs.setPunishcount(BigDecimal.ZERO);
                }
                // 保存创建人为当前登录人id
                bs.setCrtlogin(this.getPrivCredential(UserCredentialName.login.name(), request, response));
                bs.setIsfinished(false);
                bs.setCanbeupdate(true);
                try {
                    punishService.saveAdmPunishCon(bs);
                } catch (DataIntegrityViolationException e) {
                    request.setAttribute(
                            ATTR_MESSAGE,
                            "<b>保存行政处罚结论操作失败,处罚决定书编号重复</b><br><b>失败原因:</b>因为在你保存处罚结论信息之前有其他用户已经保存了处罚决定书编号 "
                                    + bs.getPunishbookno() + " 的处罚结论信息<br><b>解决方法:</b>请重新点击'录入行政处罚'按钮获取新处罚决定书编号，再输入保存!");
                    return mapping.findForward("toAeSanctionMessage");
                }
                try {
                    punishService.refreshUpdateFlag(bs.getAeno());
                } catch (Exception e) {
                    request.setAttribute(ATTR_MESSAGE, "<b>保存行政处罚结论信息操作成功，但是未成功修改该行政处罚状态为【不能修改】");
                    return mapping.findForward("toAdminEnforceMessage");
                }
                request.setAttribute(ATTR_MESSAGE, "保存行政处罚结论登记【" + bs.getAeno() + "】操作成功!");
                LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "保存行政处罚结论信息", "操作成功!"));

                // 更新执法检查工作记录中执法统计信息的处罚金额
                try {
                    String ino = bs.getAeno();
                    if (StringUtils.isNotBlank(ino)) {
                        BsAeinspection insp = enforceService.getAeinspectionByIno(ino);
                        if (insp != null) {
                            BsAeinspectionAnl inspAnl = enforceService.getInspectionAnl(ino, insp.getStat());
                            if (inspAnl != null) {
                                inspAnl.setP1(bs.getPunishcount());
                                enforceService.saveInspectionAnl(inspAnl);
                            }
                        }
                    }
                } catch (Exception e) {
                    LOGGER.error("更新执法检查工作记录中执法统计信息的处罚金额", e);
                }
            } catch (Exception e) {
                LOGGER.error(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "保存行政处罚结论信息", "操作失败!"), e);
                request.setAttribute(ATTR_MESSAGE, "保存行政处罚结论登记操作失败!错误原因:" + e.getMessage());
            }
            return mapping.findForward("toAeSanctionMessage");
        } else {// 重复提交
            this.printMessage(request, response, String.format(ERROR2, "行政处罚结论查询"), ATTR_ERROR);
            return null;
        }
    }

    /**
     * 跳转行政处罚结论信息列表页面.
     */
    public ActionForward toAeSanctionConList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo").equals(StringUtils.EMPTY)) ? "1"
                : (String) dyna.get("pageNo");
        String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize").equals(StringUtils.EMPTY)) ? PAGE_CONTAIN_NUMBER
                .toString() : (String) dyna.get("pageSize");
        String punishno = (dyna.get("punishno") == null || dyna.get("punishno").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("punishno");
        String aeorgChoice = (dyna.get("aeorgChoice") == null || dyna.get("aeorgChoice").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("aeorgChoice");
        String illegalunit = (dyna.get("illegalunit") == null || dyna.get("illegalunit").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("illegalunit");
        String aeplanstdate = (dyna.get("aeplanstdate") == null || dyna.get("aeplanstdate").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("aeplanstdate");
        String aeplantmdate = (dyna.get("aeplantmdate") == null || dyna.get("aeplantmdate").equals(StringUtils.EMPTY)) ? null
                : (String) dyna.get("aeplantmdate");
        int pageCountTemp = 0;
        // 调用获取执法检查人员分页方法
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
        if (StringUtils.isEmpty(loginOrgNo)) {
            request.setAttribute(ATTR_MESSAGE,
                    String.format(ERROR3, this.getPrivCredential(UserCredentialName.login.name(), request, response)));
            request.setAttribute("methodname", "toAeSanctionConList");
            return mapping.findForward("toAeSanctionMessage");
        }
        Page page = punishService.queryAdmPunishCon(punishno, illegalunit, null, aeorgChoice, aeplanstdate,
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
            // 分页处理End
        }

        // 再次将分页相关数据返回页面Start
        request.setAttribute("pageNo", pageNo);
        dyna.set("pageNo", pageNo);
        request.setAttribute("pageCount", pageCountTemp);
        dyna.set("pageCount", pageCountTemp);
        request.setAttribute("totalNo", page.getTotalCount());
        // 再次将分页相关数据返回页面End
        List<Object> list = (List<Object>) page.getResult();
        request.setAttribute("list", list);
        return mapping.findForward("toAeSanctionConList");
    }

    /**
     * 行政处罚结论信息删除.
     */
    public ActionForward delAeSanctionCon(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        String nowLoginUser = this.getPubCredential(UserCredentialName.nickname.name(), request, response);
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
        request.setAttribute("methodname", "toAeSanctionConList");
        String id = request.getParameter("id");
        BsAdmpunishcons bs = punishService.getAdmPunishCon(id);
        // 判断当前用户是否有权限修改该信息
        if (bs == null) {
            this.printMessage(request, response, String.format(ERROR8, id, "行政处罚立项"), ATTR_ERROR);
            return null;
        } else {
            String crtLogin = bs.getCrtlogin();
            String nowLogin = this.getPrivCredential(UserCredentialName.login.name(), request, response);
            if (StringUtils.isBlank(crtLogin)) {
                this.printMessage(request, response, String.format(ERROR5, "行政处罚立项"), ATTR_ERROR);
                return null;
            }
            if (StringUtils.isBlank(nowLogin)) {
                this.printMessage(request, response, ERROR6, ATTR_ERROR);
                return null;
            }
            if (!nowLogin.trim().equals(crtLogin.trim())) {
                this.printMessage(request, response, String.format(ERROR7, "行政处罚立项"), ATTR_ERROR);
                return null;
            }
        }
        try {
            punishService.deleteAdmPunishConById(id);
            request.setAttribute(ATTR_MESSAGE, "删除行政处罚结论【" + id + "】操作成功!");
            LOGGER.info(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "删除行政处罚信息", "操作成功!"));
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute(ATTR_MESSAGE, "删除行政处罚结论操作失败!错误原因:" + e.getMessage());
            LOGGER.error(String.format(LOG_FORMAT, bsorg.getName(), nowLoginUser, "删除行政处罚信息", "操作失败!"));
        }
        return mapping.findForward("toAeSanctionMessage");
    }

    /**
     * 跳转行政处罚结论信息详细页面.
     */
    public ActionForward toAeSanctionConDetailPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        // 如果当前用户不属于人行用户，则不能使用该功能
        if (!super.isPcbUser(request, response)) {
            this.printMessage(request, response, ERROR1, ATTR_ERROR);
            return null;
        }
        DynaActionForm dyna = (DynaActionForm) form;
        String id = request.getParameter("id");
        BsAdmpunishcons bs = punishService.getAdmPunishCon(id);
        RequestUtil.setFormFromBean(dyna, bs);
        request.setAttribute("punishbookatta", bs.getPunishbookatta());
        request.setAttribute("punishgzbookatta", bs.getPunishgzbookatta());
        BsAepeople aePeople = enforceService.getAePeopleByCardId(bs.getCrtlogin());
        if (aePeople != null) {
            request.setAttribute("crtLoginNm", aePeople.getPepname() + "-" + aePeople.getCreditno());
        } else {
            request.setAttribute("crtLoginNm",
                    enforceService.getCredential(bs.getCrtlogin(), UserCredentialName.nickname));
        }
        return mapping.findForward("toAeSanctionConDetailPage");
    }

    /**
     * 生成行政处罚立项审批表Word文件.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward generateXzcflxbWord(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        String wordTemplateId = request.getParameter("wordTemplateId").toString();
        String punishno = request.getParameter("punishno");
        FileInputStream fis = null;
        OutputStream repos = null;
        try {
            BsAdmpunish fb = punishService.getAdmPunishByNo(punishno);
            if (fb == null) {
                this.printMessage(request, response, "系统不能找到行政处罚" + punishno + "，请确认已经保存后再导出！", ATTR_ERROR);
                return null;
            }
            String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
            BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
            Map<String, String> map = new HashMap<String, String>();
            map.put("PCB_BANK", bsorg.getName());
            map.put("PUNISHNO", fb.getPunishno());
            map.put("REASON", super.getDicMap().get(fb.getReason()));
            map.put("AEEDORGNM", fb.getAeedorgnm());
            map.put("ILLEGALPEOPLE", fb.getIllegalpeople());
            map.put("SUMMARYS", StringEscapeUtils.unescapeJava(fb.getSummarys()));
            map.put("peopleopnion", StringEscapeUtils.unescapeJava(fb.getPeopleopnion()));
            map.put("peopler", fb.getPeopler());
            map.put("peoplerdate", DateFormatUtils.format(fb.getPeoplerdate(), DateUtil.DATE_FORMAT_ZH));
            map.put("dptopnion", StringEscapeUtils.unescapeJava(fb.getDptopnion()));
            map.put("dpter", fb.getDpter());
            map.put("dpterdate", DateFormatUtils.format(fb.getDpterdate(), DateUtil.DATE_FORMAT_ZH));
            map.put("chairopnion", StringEscapeUtils.unescapeJava(fb.getChairopnion()));
            map.put("chairer", fb.getChairer());
            map.put("chairerdate", DateFormatUtils.format(fb.getChairerdate(), DateUtil.DATE_FORMAT_ZH));
            fis = new FileInputStream(request.getSession().getServletContext().getRealPath(File.separator)
                    + FileHandler.WORD_TEMPLATE_FILE_PATH + File.separator + wordTemplateId + ".doc");
            // 设置response的Header
            response.setContentType("application/vnd.ms-word");
            response.addHeader("Content-Disposition",
                    "attachment;filename=" + super.getDownloadFileName(fb.getPunishno(), StringUtils.EMPTY));
            repos = response.getOutputStream();
            wordGenerator.replaceDoc(fis, map, repos);
            repos.flush();
            return null;
        } catch (Exception e) {
            LOGGER.error("生成行政处罚立项审批表Word文件", e);
            this.printMessage(request, response, "行政处罚" + punishno + "立项审批表导出失败！错误原因:" + e.getMessage(), ATTR_ERROR);
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
     * 跳转系统基础参数设置页面.
     */
    public ActionForward toSetSystemParam(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        request.setAttribute("stats", enforceService.getParentDataStr());
        return mapping.findForward("toSetSystemParam");
    }

    /**
     * 分页查询系统参数表.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward pageDctionarys(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        /** 获取参数 */
        String type = request.getParameter("type");
        String dkey = request.getParameter("dkey");
        String dvalue = request.getParameter("dvalue");
        String dsumry = request.getParameter("dsumry");

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
            LOGGER.error("分页查询系统参数表", e);
        }
        Page page = null;
        try {
            page = enforceService.pageDctionarys(type, dkey, dvalue, dsumry, pageNo, limit);
        } catch (Exception e) {
            LOGGER.error("分页查询系统参数表", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        JSONArray jsonObject = JSONArray.fromObject(page.getResult());
        String outputStr = jsonObject.toString();
        outputStr = "{totalProperty:" + page.getTotalCount() + ",\"root\":" + outputStr + "}";
        // System.out.println(outputStr);
        if (out != null) {
            out.print(outputStr);
        }
        return null;
    }

    /**
     * 保存系统参数.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward saveDctionary(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        String idM = request.getParameter("id_m");
        String typeM = request.getParameter("type_m_hidden");
        String dkeyM = request.getParameter("dkey_m");
        String dvalueM = request.getParameter("dvalue_m");
        String dsumryM = request.getParameter("dsumry_m");
        String statM = request.getParameter("stat_m_hidden");
        String flagM = request.getParameter("flag_m_hidden");
        String isenableM = request.getParameter("isenable_m_hidden");

        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("保存系统参数", e);
        }
        try {
            enforceService.saveDctionary(idM, typeM, dkeyM, dvalueM, dsumryM, statM, flagM, isenableM);
        } catch (Exception e) {
            LOGGER.error("保存系统参数", e);
            if (out != null) {
                out.print("{success:true,msg:'保存系统参数:" + dvalueM + "失败,失败原因:" + e.getMessage() + "'}");
            }
            return null;
        }
        if (out != null) {
            out.print("{success:true,msg:'保存系统参数:" + dvalueM + "成功'}");
        }
        return null;
    }

    /**
     * 删除系统参数.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward deleteParam(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        String id = request.getParameter("id");
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("删除系统参数", e);
        }
        if (StringUtils.isEmpty(id)) {
            if (out != null) {
                out.print("{success:true,msg:'请选择系统参数删除'}");
            }
            return null;
        }

        try {
            enforceService.deleteDctionary(id);
        } catch (Exception e) {
            LOGGER.error("删除系统参数", e);
            if (out != null) {
                out.print("{success:true,msg:'删除系统参数:" + id + "失败,失败原因:" + e.getMessage() + "'}");
            }
            return null;
        }
        if (out != null) {
            out.print("{success:true,msg:'删除系统参数:" + id + "成功'}");
        }
        return null;
    }

    /**
     * 跳转商业银行获取行政执法结论分页页面.
     */
    public ActionForward toPageAeconclusionInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        return mapping.findForward("toPageAeconclusionInfo");
    }

    /**
     * 商业银行获取行政执法结论分页信息.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward pageAeconclusionInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        // 获取当前登录人机构
        String loginOrgNo = this.getPubCredential(UserCredentialName.organization.name(), request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(loginOrgNo);
        if (bsorg == null) {
            return null;
        }
        String isPcb = bsorg.getIspcb();// 获取当前登录人是否为人行用户

        // 获取查询条件中的行政执法检查工作记录编号
        String iNo = request.getParameter("iNo");
        String aeOpnionNo = request.getParameter("aeOpnionNo");
        String aeorgName = request.getParameter("aeorgName");
        String aeedorgName = request.getParameter("aeedorgName");
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
            LOGGER.error("商业银行获取行政执法结论分页信息", e);
        }
        Page page = null;
        try {
            if (isPcb != null && isPcb.trim().equalsIgnoreCase("YES")) {
                page = enforceService.queryAeconclusionByPcb(iNo, aeOpnionNo, aeorgName, aeedorgName, stDate, tmDate,
                        pageNo, PAGE_CONTAIN_NUMBER, loginOrgNo);
            } else if (isPcb != null && isPcb.trim().equalsIgnoreCase("NO")) {
                page = enforceService.queryAeconclusionByBank(iNo, aeOpnionNo, aeorgName, aeedorgName, stDate, tmDate,
                        pageNo, PAGE_CONTAIN_NUMBER, loginOrgNo);
            } else {
                out.print("{totalProperty:0,\"root\":[]}");
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("商业银行获取行政执法结论分页信息", e);
            out.print("{totalProperty:0,\"root\":[]}");
            return null;
        }
        if (page == null) {
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
     * 获取行政执法结论相关详细信息.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getAeconclusionInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("获取行政执法结论相关详细信息", e);
        }
        BsAeconclusion aeCon = null;
        try {
            if (StringUtils.isNotBlank(id)) {
                aeCon = enforceService.getAeconclusionById(id);
            } else {
                out.print("{root:[]}");
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("获取行政执法结论相关详细信息", e);
            out.print("{root:[]}");
            return null;
        }
        StringBuffer outputSb = new StringBuffer("{'aeno':'").append(aeCon.getAeno()).append("'");
        outputSb.append(",'aeorgnm':'").append(aeCon.getAeorgnm()).append("'");
        outputSb.append(",'aeedorgnm':'").append(aeCon.getAeedorgnm()).append("'");
        outputSb.append(",'createdate':'")
                .append(DateFormatUtils.format(aeCon.getCreatedate(), DateUtil.DATE_FORMAT_ZH)).append("'");
        outputSb.append(",'aeopnionno':'").append(aeCon.getAeopnionno()).append("'");
        outputSb.append(",'aeopnionbook':'").append(aeCon.getAeopnionbook()).append("'");
        BsAdmpunishcons aePnshCon = punishService.getAdmPunishConByIno(aeCon.getAeno());
        if (aePnshCon != null) {
            outputSb.append(",'punishno':'").append(aePnshCon.getPunishno()).append("'");
            outputSb.append(",'punishbookno':'").append(aePnshCon.getPunishbookno()).append("'");
            outputSb.append(",'punishbookatta':'").append(aePnshCon.getPunishbookatta()).append("'");
        }
        BsFactbook fb = enforceService.getFactBook(aeCon.getAeno());
        if (fb != null) {
            String[] selectedwtgkArr = fb.getSelectedwtgk().split(",");
            String content = enforceService.getWTGKContent(selectedwtgkArr);
            outputSb.append(",'selectedwtgk':'").append(content).append("'");
        }
        outputSb.append("}");
        // System.out.println(outputSb.toString());
        if (out != null) {
            out.print(outputSb.toString());
        }
        return null;
    }

    /**
     * 初始化录入检查结论反馈页面.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getFeedbackInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("获取行政执法结论相关详细信息", e);
        }
        BsAeconclusion aeCon = null;
        try {
            if (StringUtils.isNotBlank(id)) {
                aeCon = enforceService.getAeconclusionById(id);
            } else {
                out.print("{root:[]}");
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("获取行政执法结论相关详细信息", e);
            out.print("{root:[]}");
            return null;
        }
        StringBuffer outputSb = new StringBuffer("{'aeno':'").append(aeCon.getAeno()).append("'");
        outputSb.append(",'aeorgnm':'").append(aeCon.getAeorgnm()).append("'");
        outputSb.append(",'aeedorgnm':'").append(aeCon.getAeedorgnm()).append("'");
        outputSb.append(",'aeopnionno':'").append(aeCon.getAeopnionno()).append("'");

        BsAeFeedBack aeFeedBack = enforceService.getFeedBack(aeCon.getAeno());
        if (aeFeedBack != null) {
            if (aeFeedBack.getFeedbackDate() != null) {
                outputSb.append(",'feedbackdt':'")
                        .append(DateFormatUtils.format(aeFeedBack.getFeedbackDate(), DateUtil.DATE_FORMAT_ZH))
                        .append("'");
            }
            outputSb.append(",'feedbackopnion':'").append(aeFeedBack.getFeedbackOpnion()).append("'");
        }

        outputSb.append("}");
        if (out != null) {
            out.print(outputSb.toString());
        }
        IOUtils.closeQuietly(out);
        return null;
    }

    /**
     * 保存检查结论反馈意见.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward saveFeedBack(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        // String nowLoginUserName =
        // this.getPubCredential(UserCredentialName.nickname.name(), request,
        // response);
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
        BsAeFeedBack feedback = enforceService.getFeedBack(request.getParameter("aeno_v"));
        BsAeconclusion aeCon = enforceService.getAeconclusionByAeno(request.getParameter("aeno_v"));
        if (aeCon == null) {
            LOGGER.error("获取行政执法结论相关详细信息");
            out.print("{root:[]}");
            return null;
        }
        if (feedback == null) {
            feedback = new BsAeFeedBack();
            try {
                BeanUtils.copyProperties(feedback, aeCon);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BsOrg aeedOrg = enforceService.getOrgByNo(aeCon.getAeedorgno());
            // BsOrg topLevelBank =
            // enforceService.getTopLevelOrg(aeCon.getAeedOrgBankType());
            BsOrg topLevelBank = enforceService.getTopLevelOrgByNo(aeCon.getAeedorgno());
            feedback.setDirectParentlBankNm(aeedOrg.getParentname());
            feedback.setDirectParentlBankNo(aeedOrg.getParentno());
            feedback.setTopLevelBankNm(topLevelBank.getName());
            feedback.setTopLevelBankNo(topLevelBank.getNo());
            feedback.setId(null);
        }
        feedback.setFeedbackDate(new Date());
        feedback.setFeedbackOpnion(request.getParameter("feedbackOpnion_v"));

        try {
            enforceService.saveFeedBack(feedback);
        } catch (Exception e) {
            LOGGER.error("保存检查结论反馈意见发生错误", e);
            out.print("{success:false,msg:'保存检查结论反馈意见发生错误" + e.getMessage() + "'}");
            return null;
        }
        if (StringUtils.isBlank(aeCon.getTopLevelBankNo())) {
            aeCon.setDirectParentlBankNm(feedback.getDirectParentlBankNm());
            aeCon.setDirectParentlBankNo(feedback.getDirectParentlBankNo());
            aeCon.setTopLevelBankNm(feedback.getTopLevelBankNm());
            aeCon.setTopLevelBankNo(feedback.getTopLevelBankNo());
            enforceService.saveAeconclusionSimple(aeCon);
        }
        if (out != null) {
            out.print("{success:true,msg:'保存检查结论反馈意见:" + feedback.getAeno() + "成功'}");
        }

        return null;

    }

    /**
     * 初始化录入行政处罚反馈页面.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward getPublishFeedbackInfo(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        String id = request.getParameter("id");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("获取行政执法结论相关详细信息", e);
        }
        BsAeconclusion aeCon = null;
        try {
            if (StringUtils.isNotBlank(id)) {
                aeCon = enforceService.getAeconclusionById(id);
            } else {
                out.print("{root:[]}");
                return null;
            }
        } catch (Exception e) {
            LOGGER.error("获取行政执法结论相关详细信息", e);
            out.print("{root:[]}");
            return null;
        }
        StringBuffer outputSb = new StringBuffer("{'aeno':'").append(aeCon.getAeno()).append("'");
        outputSb.append(",'aeorgnm':'").append(aeCon.getAeorgnm()).append("'");
        outputSb.append(",'aeedorgnm':'").append(aeCon.getAeedorgnm()).append("'");

        BsAdmpunishcons aePnshCon = punishService.getAdmPunishConByIno(aeCon.getAeno());
        if (aePnshCon != null) {
            outputSb.append(",'punishbookno':'").append(aePnshCon.getPunishbookno()).append("'");
        }

        BsAePublishFeedBack aePublishFeedBack = enforceService.getPublishFeedBack(aeCon.getAeno());
        if (aePublishFeedBack != null) {
            if (aePublishFeedBack.getFeedbackDate() != null) {
                outputSb.append(",'feedbackdt':'")
                        .append(DateFormatUtils.format(aePublishFeedBack.getFeedbackDate(), DateUtil.DATE_FORMAT_ZH))
                        .append("'");
            }
            outputSb.append(",'feedbackopnion':'").append(aePublishFeedBack.getFeedbackOpnion()).append("'");
        }

        outputSb.append("}");
        if (out != null) {
            out.print(outputSb.toString());
        }
        IOUtils.closeQuietly(out);
        return null;
    }

    /**
     * 保存行政处罚反馈意见.
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public ActionForward savePublishFeedBack(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        // String nowLoginUserName =
        // this.getPubCredential(UserCredentialName.nickname.name(), request,
        // response);
        response.setContentType("text/html;charset=UTF-8");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            LOGGER.error("保存行政处罚反馈意见错误", e);
            return null;
        }
        BsAdmpunishcons aePnshCon = punishService.getAdmPunishConByIno(request.getParameter("aeno_v"));
        if (aePnshCon == null) {
            out.print("{success:true,msg:'当前工作检查记录不存在行政处罚信息'}");
            return null;
        }
        BsAePublishFeedBack feedback = enforceService.getPublishFeedBack(request.getParameter("aeno_v"));
        BsAeconclusion aeCon = enforceService.getAeconclusionByAeno(request.getParameter("aeno_v"));
        if (aeCon == null) {
            LOGGER.error("获取行政执法结论相关详细信息");
            out.print("{root:[]}");
            return null;
        }
        if (feedback == null) {
            feedback = new BsAePublishFeedBack();
            try {
                BeanUtils.copyProperties(feedback, aeCon);
            } catch (Exception e) {
                e.printStackTrace();
            }
            BsOrg aeedOrg = enforceService.getOrgByNo(aeCon.getAeedorgno());
            BsOrg topLevelBank = enforceService.getTopLevelOrgByNo(aeCon.getAeedorgno());
            feedback.setDirectParentlBankNm(aeedOrg.getParentname());
            feedback.setDirectParentlBankNo(aeedOrg.getParentno());
            feedback.setTopLevelBankNm(topLevelBank.getName());
            feedback.setTopLevelBankNo(topLevelBank.getNo());
            feedback.setId(null);
        }
        feedback.setFeedbackDate(new Date());
        feedback.setFeedbackOpnion(request.getParameter("publishfeedbackOpnion_v"));

        try {
            enforceService.savePublishFeedBack(feedback);
        } catch (Exception e) {
            LOGGER.error("保存行政处罚反馈意见发生错误", e);
            out.print("{success:false,msg:'保存行政处罚反馈意见发生错误" + e.getMessage() + "'}");
            return null;
        }
        if (StringUtils.isBlank(aeCon.getTopLevelBankNo())) {
            aeCon.setDirectParentlBankNm(feedback.getDirectParentlBankNm());
            aeCon.setDirectParentlBankNo(feedback.getDirectParentlBankNo());
            aeCon.setTopLevelBankNm(feedback.getTopLevelBankNm());
            aeCon.setTopLevelBankNo(feedback.getTopLevelBankNo());
            enforceService.saveAeconclusionSimple(aeCon);
        }
        if (out != null) {
            out.print("{success:true,msg:'保存行政处罚反馈意见:" + feedback.getAeno() + "成功'}");
        }

        return null;

    }

    public void setPunishService(PunishService punishService) {
        this.punishService = punishService;
    }

    public void setNoGenerator(NoGenerator noGenerator) {
        this.noGenerator = noGenerator;
    }

    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    public void setEnforceService(EnforceService enforceService) {
        this.enforceService = enforceService;
    }

    public void setSystemBaseInfoManager(SystemBaseInfoManager systemBaseInfoManager) {
        this.systemBaseInfoManager = systemBaseInfoManager;
    }

    public void setRemindService(RemindService remindService) {
        this.remindService = remindService;
    }

    public void setWordGenerator(WordGenerator wordGenerator) {
        this.wordGenerator = wordGenerator;
    }
}
