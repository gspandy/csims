package com.gtm.csims.controll.managment.system;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sweet.dao.generic.support.Page;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;
import org.apache.struts.upload.FormFile;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.business.managment.system.SystemBaseInfoManager;
import com.gtm.csims.business.remind.RemindService;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.model.BsDept;
import com.gtm.csims.model.BsOrg;
import com.gtm.csims.model.BsOrgComInfo;
import com.gtm.csims.model.BsArea;

/**
 * @ClassName: ${SystemBaseInfoManagerAction}
 * @Description: ${系统基础数据维护Action}
 * @author qhy
 * @date ${date} ${time}
 * 
 */
@SuppressWarnings("unchecked")
public class SystemBaseInfoManagerAction extends BaseAction {
    private static final Integer PAGE_CONTAIN_NUMBER = 10;
    private SystemBaseInfoManager systemBaseInfoManager;
    private RemindService remindService;

    public void setSystemBaseInfoManager(
            SystemBaseInfoManager systemBaseInfoManager) {
        this.systemBaseInfoManager = systemBaseInfoManager;
    }

    public void setRemindService(RemindService remindService) {
        this.remindService = remindService;
    }

    /**
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    public void ajaxDeptByOrgNoForJson(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String orgNo = request.getParameter("orgNo");
        String text = systemBaseInfoManager.getDepByOrgNoForJson(orgNo);
        try {
            renderJson(response, text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据不同权限展现不同地区树Tree
    @SuppressWarnings("unchecked")
    public void areaListCommonTree(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        String parent = request.getParameter("node");
        String userOrgNo = super.getPubCredential(
                UserCredentialName.organization.name(), request, response);
        BsOrg bsOrg = systemBaseInfoManager.getOrgByNo(userOrgNo);
        String areaLevel = "";

        // String userAreaId =
        // super.getPubCredential(UserCredentialName.area.name(), request,
        // response);

        // if("".equals(userAreaId) || userAreaId==null){
        String userAreaId = "510000";
        // }
        boolean isFirst = false;
        if (parent.equals("0")) {
            parent = userAreaId;
            isFirst = true;
        }
        try {
            List list = new ArrayList();
            List<BsArea> resultArea = systemBaseInfoManager.areaList(parent,
                    isFirst);
            for (int i = 0; i < resultArea.size(); i++) {
                BsArea ba = resultArea.get(i);
                JSONObject object = new JSONObject();
                object.put("id", ba.getId());
                object.put("text", ba.getName());
                object.put("singleClickExpand", true);
                list.add(object);
            }
            JSONArray array = JSONArray.fromObject(list);
            response.getWriter().write(array.toString());
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void orgTree(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String parent = request.getParameter("node");
        String nowloginerOrgNo = this.getPubCredential(
                UserCredentialName.organization.name(), request, response);
        String isPcbUser = this.getPubCredential(
                UserCredentialName.ispcbuser.name(), request, response);
        response.setCharacterEncoding("UTF-8");
        try {
            List list = new ArrayList();
            if ("YES".equals(isPcbUser)) {
                List<BsOrg> resultOrg = systemBaseInfoManager
                        .orgListByParentNo(parent);
                for (int i = 0; i < resultOrg.size(); i++) {
                    BsOrg bc = resultOrg.get(i);
                    JSONObject object = new JSONObject();
                    object.put("id", bc.getNo());
                    object.put("text", bc.getName());
                    object.put("singleClickExpand", bc.getIspcb());
                    list.add(object);
                }
            } else {
                if ("0".equals(parent)) {
                    BsOrg bsOrg = systemBaseInfoManager
                            .getOrgByNo(nowloginerOrgNo);
                    JSONObject object = new JSONObject();
                    object.put("id", bsOrg.getNo());
                    object.put("text", bsOrg.getName());
                    object.put("singleClickExpand", bsOrg.getIspcb());
                    list.add(object);
                } else {
                    List<BsOrg> resultOrg = systemBaseInfoManager
                            .orgListByParentNo(parent);
                    for (int i = 0; i < resultOrg.size(); i++) {
                        BsOrg bc = resultOrg.get(i);
                        JSONObject object = new JSONObject();
                        object.put("id", bc.getNo());
                        object.put("text", bc.getName());
                        object.put("singleClickExpand", bc.getIspcb());
                        list.add(object);
                    }
                }
            }
            JSONArray array = JSONArray.fromObject(list);
            response.getWriter().write(array.toString());
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

   

    @SuppressWarnings("unchecked")
    public void pcbOrgTree(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String parent = request.getParameter("node");
        try {
            response.setCharacterEncoding("UTF-8");
            List list = new ArrayList();
            if ("0".equals(parent)) {
                parent = this.getPubCredential(
                        UserCredentialName.organization.name(), request,
                        response);
                BsOrg bs = systemBaseInfoManager.getOrgByNo(parent);
                JSONObject object = new JSONObject();
                object.put("id", bs.getNo());
                object.put("text", bs.getName());
                object.put("singleClickExpand", bs.getIspcb());
                list.add(object);
            } else {
                List<BsOrg> resultOrg = systemBaseInfoManager
                        .pcbOrgList(parent);
                for (int i = 0; i < resultOrg.size(); i++) {
                    BsOrg bc = resultOrg.get(i);
                    JSONObject object = new JSONObject();
                    object.put("id", bc.getNo());
                    object.put("text", bc.getName());
                    object.put("singleClickExpand", bc.getIspcb());
                    list.add(object);
                }
            }
            JSONArray array = JSONArray.fromObject(list);
            response.getWriter().write(array.toString());
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 机构维护主页
     */
    public ActionForward toOrgMain(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("toOrgMain");
    }

    /**
     * 导入机构表彰信息主页
     */
    public ActionForward toUploadOrgComInfoPage(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        return mapping.findForward("toUploadOrgComInfoPage");
    }

    /**
     * 导入机构表彰信息
     */
    public ActionForward uploadOrgComInfo(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        String nowLoginUserName = this.getPubCredential(
                UserCredentialName.nickname.name(), request, response);
        String nowloginerOrgNo = this.getPubCredential(
                UserCredentialName.organization.name(), request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
        DynaActionForm dyna = (DynaActionForm) form;
        request.setAttribute("methodname", "toUploadOrgComInfoPage");
        String endRow = (dyna.get("endRow") == null || dyna.get("endRow")
                .equals("")) ? null : (String) dyna.get("endRow");
        FormFile excelpath = (FormFile) dyna.get("excelpath");
        String message = "";
        try {
            InputStream is = excelpath.getInputStream();
            systemBaseInfoManager.savaOrgComInfo(is, endRow);
            message = "操作成功!";
        } catch (Exception e) {
            message = "操作失败!";
        }
        remindService.writeLog("【" + bsorg.getName() + "】下的 【"
                + nowLoginUserName + "】 用户导入机构表彰信息【" + message + "】", "", "",
                "HTTP", nowLoginUserName);
        request.setAttribute("message", message);
        return mapping.findForward("toMessage");
    }

    /**
     * 机构查询
     */
    public ActionForward toOrgList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
                .equals("")) ? "1" : (String) dyna.get("pageNo");
        String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
                .equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
                .get("pageSize");
        String orgName = (dyna.get("organizationChoice") == null || dyna.get(
                "organizationChoice").equals("")) ? null : (String) dyna
                .get("organizationChoice");
        String org = (dyna.get("organization") == null || dyna.get(
                "organization").equals("")) ? null : (String) dyna
                .get("organization");
        String orgNo = (dyna.get("orgNo") == null || dyna.get("orgNo").equals(
                "")) ? null : (String) dyna.get("orgNo");
        int pageCountTemp = 0;
        Page page = systemBaseInfoManager.getOrgList(orgNo, orgName, org,
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
        List orgList = (List) page.getResult();
        request.setAttribute("orgList", orgList);
        return mapping.findForward("toOrgList");
    }

    /**
     * 机构信息展示
     */
    public ActionForward readOrg(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        String orgNo = request.getParameter("orgNo");
        BsOrg bs = systemBaseInfoManager.getOrgByNo(orgNo);
        dyna.set("orgName", bs.getName());
        dyna.set("orgNo", bs.getNo());
        dyna.set("orgCodeOfZz", bs.getOrgCodeOfZz());
        dyna.set("orgCodeOfXy", bs.getOrgCodeOfXy());
        dyna.set("e", bs.getE());
        dyna.set("g", bs.getG());
        dyna.set("kChoice", bs.getJ());
        dyna.set("k", bs.getK());
        dyna.set("mChoice", bs.getL());
        dyna.set("m", bs.getM());
        dyna.set("o", bs.getO());
        dyna.set("q", bs.getQ());
        dyna.set("s", bs.getS());
        dyna.set("t", bs.getT());
        dyna.set("z", bs.getZ());
        dyna.set("aa", bs.getAa());
        if (bs.getIspcb().equals("YES")) {
            dyna.set("isPcb", "人行机构");
        } else {
            dyna.set("isPcb", "非人行机构");
        }
        String orgComInfoTR = systemBaseInfoManager.getOrgComInfoTR(orgNo);
        request.setAttribute("orgComInfoTR", orgComInfoTR);
        return mapping.findForward("toReadOrg");
    }

    /**
     * 新增机构
     */
    public ActionForward orgCreate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        request.setAttribute("methodname", "toOrgMain");
        String nowLoginUserName = this.getPubCredential(
                UserCredentialName.nickname.name(), request, response);
        String nowloginerOrgNo = this.getPubCredential(
                UserCredentialName.organization.name(), request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
        String orgNo = (dyna.get("orgNo") == null || dyna.get("orgNo").equals(
                "")) ? null : (String) dyna.get("orgNo");
        String newOrgNo = (dyna.get("newOrgNo") == null || dyna.get("newOrgNo")
                .equals("")) ? null : (String) dyna.get("newOrgNo");
        String newOrgName = (dyna.get("newOrgName") == null || dyna.get(
                "newOrgName").equals("")) ? null : (String) dyna
                .get("newOrgName");
        String newOrgCodeOfZz = (dyna.get("newOrgCodeOfZz") == null || dyna
                .get("newOrgCodeOfZz").equals("")) ? null : (String) dyna
                .get("newOrgCodeOfZz");
        String newOrgCodeOfXy = (dyna.get("newOrgCodeOfXy") == null || dyna
                .get("newOrgCodeOfXy").equals("")) ? null : (String) dyna
                .get("newOrgCodeOfXy");
        String newIsPcb = (dyna.get("newIsPcb") == null || dyna.get("newIsPcb")
                .equals("")) ? null : (String) dyna.get("newIsPcb");
        String newE = (dyna.get("newE") == null || dyna.get("newE").equals("")) ? null
                : (String) dyna.get("newE");
        String newG = (dyna.get("newG") == null || dyna.get("newG").equals("")) ? null
                : (String) dyna.get("newG");
        String newKChoice = (dyna.get("newKChoice") == null || dyna.get(
                "newKChoice").equals("")) ? null : (String) dyna
                .get("newKChoice");
        String newK = (dyna.get("newK") == null || dyna.get("newK").equals("")) ? null
                : (String) dyna.get("newK");
        String newMChoice = (dyna.get("newMChoice") == null || dyna.get(
                "newMChoice").equals("")) ? null : (String) dyna
                .get("newMChoice");
        String newM = (dyna.get("newM") == null || dyna.get("newM").equals("")) ? null
                : (String) dyna.get("newM");
        String newO = (dyna.get("newO") == null || dyna.get("newO").equals("")) ? null
                : (String) dyna.get("newO");
        String newQ = (dyna.get("newQ") == null || dyna.get("newQ").equals("")) ? null
                : (String) dyna.get("newQ");
        String newS = (dyna.get("newS") == null || dyna.get("newS").equals("")) ? null
                : (String) dyna.get("newS");
        String newT = (dyna.get("newT") == null || dyna.get("newT").equals("")) ? null
                : (String) dyna.get("newT");
        String newZ = (dyna.get("newZ") == null || dyna.get("newZ").equals("")) ? null
                : (String) dyna.get("newZ");
        String newAA = (dyna.get("newAA") == null || dyna.get("newAA").equals(
                "")) ? null : (String) dyna.get("newAA");
        BsOrg bs = new BsOrg();
        bs.setNo(newOrgNo);
        bs.setName(newOrgName);
        bs.setParentno(orgNo);
        bs.setOrgCodeOfZz(newOrgCodeOfZz);
        bs.setOrgCodeOfXy(newOrgCodeOfXy);
        bs.setE(newE);
        bs.setG(newG);
        bs.setJ(newK);
        bs.setK(newKChoice);
        bs.setL(newM);
        bs.setM(newMChoice);
        bs.setO(newO);
        bs.setQ(newQ);
        bs.setS(newS);
        bs.setT(newT);
        bs.setZ(newZ);
        bs.setAa(newAA);
        bs.setIspcb(newIsPcb);
        try {
            systemBaseInfoManager.savaBsOrg(bs);
            request.setAttribute("message", "操作成功!");
            remindService.writeLog("【" + bsorg.getName() + "】下的 【"
                    + nowLoginUserName + "】 用户创建机构【" + newOrgName + "】【操作成功!】",
                    "", "", "HTTP", nowLoginUserName);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "操作失败!");
            remindService.writeLog("【" + bsorg.getName() + "】下的 【"
                    + nowLoginUserName + "】 用户创建机构【" + newOrgName + "】【操作失败!】",
                    "", "", "HTTP", nowLoginUserName);
        }
        return mapping.findForward("toMessage");
    }

    /**
     * 新增机构表彰信息
     */
    public ActionForward orgComCreate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        request.setAttribute("methodname", "toOrgMain");
        String nowLoginUserName = this.getPubCredential(
                UserCredentialName.nickname.name(), request, response);
        String nowloginerOrgNo = this.getPubCredential(
                UserCredentialName.organization.name(), request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
        String orgNo = (dyna.get("orgNo") == null || dyna.get("orgNo").equals(
                "")) ? null : (String) dyna.get("orgNo");
        String comDate = (dyna.get("comDate") == null || dyna.get("comDate")
                .equals("")) ? null : (String) dyna.get("comDate");
        String comContent = (dyna.get("comContent") == null || dyna.get(
                "comContent").equals("")) ? null : (String) dyna
                .get("comContent");
        String organization = (dyna.get("organization") == null || dyna.get(
                "organization").equals("")) ? null : (String) dyna
                .get("organization");
        String organizationChoice = (dyna.get("organizationChoice") == null || dyna
                .get("organizationChoice").equals("")) ? null : (String) dyna
                .get("organizationChoice");
        BsOrg bsOrgComName = systemBaseInfoManager.getOrgByNo(orgNo);
        BsOrgComInfo bs = new BsOrgComInfo();
        bs.setNo(orgNo);
        bs.setComDate(comDate);
        bs.setComContent(comContent);
        bs.setComOrgNo(organization);
        bs.setComOrgName(organizationChoice);
        try {
            systemBaseInfoManager.savaBsOrgComInfo(bs);
            request.setAttribute("message", "操作成功!");
            remindService.writeLog("【" + bsorg.getName() + "】下的 【"
                    + nowLoginUserName + "】 用户新增【" + bsOrgComName.getName()
                    + "】机构表彰信息【操作成功!】", "", "", "HTTP", nowLoginUserName);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "操作失败!");
            remindService.writeLog("【" + bsorg.getName() + "】下的 【"
                    + nowLoginUserName + "】 用户新增【" + bsOrgComName.getName()
                    + "】机构表彰信息【操作失败!】", "", "", "HTTP", nowLoginUserName);
        }
        return mapping.findForward("toMessage");
    }

    /**
     * 修改机构
     */
    public ActionForward orgModify(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        request.setAttribute("methodname", "toOrgMain");
        String nowLoginUserName = this.getPubCredential(
                UserCredentialName.nickname.name(), request, response);
        String nowloginerOrgNo = this.getPubCredential(
                UserCredentialName.organization.name(), request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
        String orgNo = (dyna.get("orgNo") == null || dyna.get("orgNo").equals(
                "")) ? null : (String) dyna.get("orgNo");
        String orgName = (dyna.get("orgName") == null || dyna.get("orgName")
                .equals("")) ? null : (String) dyna.get("orgName");
        String orgCodeOfZz = (dyna.get("orgCodeOfZz") == null || dyna.get(
                "orgCodeOfZz").equals("")) ? null : (String) dyna
                .get("orgCodeOfZz");
        String orgCodeOfXy = (dyna.get("orgCodeOfXy") == null || dyna.get(
                "orgCodeOfXy").equals("")) ? null : (String) dyna
                .get("orgCodeOfXy");
        String e = (dyna.get("e") == null || dyna.get("e").equals("")) ? null
                : (String) dyna.get("e");
        String g = (dyna.get("g") == null || dyna.get("g").equals("")) ? null
                : (String) dyna.get("g");
        String kChoice = (dyna.get("kChoice") == null || dyna.get("kChoice")
                .equals("")) ? null : (String) dyna.get("kChoice");
        String k = (dyna.get("k") == null || dyna.get("k").equals("")) ? null
                : (String) dyna.get("k");
        String mChoice = (dyna.get("mChoice") == null || dyna.get("mChoice")
                .equals("")) ? null : (String) dyna.get("mChoice");
        String m = (dyna.get("m") == null || dyna.get("m").equals("")) ? null
                : (String) dyna.get("m");
        String o = (dyna.get("o") == null || dyna.get("o").equals("")) ? null
                : (String) dyna.get("o");
        String q = (dyna.get("q") == null || dyna.get("q").equals("")) ? null
                : (String) dyna.get("q");
        String s = (dyna.get("s") == null || dyna.get("s").equals("")) ? null
                : (String) dyna.get("s");
        String t = (dyna.get("t") == null || dyna.get("t").equals("")) ? null
                : (String) dyna.get("t");
        String z = (dyna.get("z") == null || dyna.get("z").equals("")) ? null
                : (String) dyna.get("z");
        String aa = (dyna.get("aa") == null || dyna.get("aa").equals("")) ? null
                : (String) dyna.get("aa");
        String isPcb = (dyna.get("isPcb") == null || dyna.get("isPcb").equals(
                "")) ? null : (String) dyna.get("isPcb");
        BsOrg bs = systemBaseInfoManager.getOrgByNo(orgNo);
        bs.setName(orgName);
        bs.setOrgCodeOfZz(orgCodeOfZz);
        bs.setOrgCodeOfXy(orgCodeOfXy);
        bs.setE(e);
        bs.setG(g);
        bs.setJ(k);
        bs.setK(kChoice);
        bs.setL(m);
        bs.setM(mChoice);
        bs.setO(o);
        bs.setQ(q);
        bs.setS(s);
        bs.setT(t);
        bs.setZ(z);
        bs.setAa(aa);
        bs.setIspcb(isPcb);
        try {
            systemBaseInfoManager.savaBsOrg(bs);
            request.setAttribute("message", "操作成功!");
            remindService.writeLog("【" + bsorg.getName() + "】下的 【"
                    + nowLoginUserName + "】 用户修改机构【" + orgName + "】【操作成功!】",
                    "", "", "HTTP", nowLoginUserName);
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("message", "操作失败!");
            remindService.writeLog("【" + bsorg.getName() + "】下的 【"
                    + nowLoginUserName + "】 用户修改机构【" + orgName + "】【操作失败!】",
                    "", "", "HTTP", nowLoginUserName);
        }
        return mapping.findForward("toMessage");
    }

    /**
     * 机构维护主页
     */
    public ActionForward toDeptMain(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
                .equals("")) ? "1" : (String) dyna.get("pageNo");
        String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
                .equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
                .get("pageSize");
        String deptName = (dyna.get("deptName") == null || dyna.get("deptName")
                .equals("")) ? null : (String) dyna.get("deptName");
        String orgNo = (dyna.get("organization") == null || dyna.get(
                "organization").equals("")) ? null : (String) dyna
                .get("organization");
        int pageCountTemp = 0;
        Page page = systemBaseInfoManager.getDeptList(deptName, orgNo,
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
        List list = (List) page.getResult();
        request.setAttribute("list", list);
        return mapping.findForward("toDeptMain");
    }

    /**
     * 机构维护主页
     */
    public ActionForward toCreateDeptPage(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        return mapping.findForward("toCreateDeptPage");
    }

    /**
     * 机构维护主页
     */
    public ActionForward createDept(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        request.setAttribute("methodname", "toDeptMain");
        String nowLoginUserName = this.getPubCredential(
                UserCredentialName.nickname.name(), request, response);
        String nowloginerOrgNo = this.getPubCredential(
                UserCredentialName.organization.name(), request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
        String organization = (dyna.get("organization") == null || dyna.get(
                "organization").equals("")) ? null : (String) dyna
                .get("organization");
        String organizationChoice = (dyna.get("organizationChoice") == null || dyna
                .get("organizationChoice").equals("")) ? null : (String) dyna
                .get("organizationChoice");
        String deptName = (dyna.get("deptName") == null || dyna.get("deptName")
                .equals("")) ? null : (String) dyna.get("deptName");
        BsDept bs = new BsDept();
        bs.setName(deptName);
        bs.setOrgno(organization);
        bs.setOrgname(organizationChoice);
        try {
            systemBaseInfoManager.savaBsDept(bs);
            request.setAttribute("message", "操作成功!");
            remindService.writeLog("【" + bsorg.getName() + "】下的 【"
                    + nowLoginUserName + "】 用户创建部门【" + deptName + "】【操作成功!】",
                    "", "", "HTTP", nowLoginUserName);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "操作失败!");
            remindService.writeLog("【" + bsorg.getName() + "】下的 【"
                    + nowLoginUserName + "】 用户创建部门【" + deptName + "】【操作失败!】",
                    "", "", "HTTP", nowLoginUserName);
        }
        dyna.set("deptName", deptName);
        dyna.set("organization", organization);
        return mapping.findForward("toDeptMessage");
    }

    /**
     * 部门维护修改页面
     */
    public ActionForward toUpdateDeptPage(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        String deptNo = request.getParameter("id");
        BsDept bs = systemBaseInfoManager.getDeptByNo(deptNo);
        dyna.set("organizationChoice", bs.getOrgname());
        dyna.set("organization", bs.getOrgno());
        dyna.set("deptName", bs.getName());
        dyna.set("deptNo", bs.getId());
        return mapping.findForward("toUpdateDeptPage");
    }

    /**
     * 部门维护修改
     */
    public ActionForward updateDept(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        request.setAttribute("methodname", "toDeptMain");
        String nowLoginUserName = this.getPubCredential(
                UserCredentialName.nickname.name(), request, response);
        String nowloginerOrgNo = this.getPubCredential(
                UserCredentialName.organization.name(), request, response);
        BsOrg bsorg = systemBaseInfoManager.getOrgByNo(nowloginerOrgNo);
        String organization = (dyna.get("organization") == null || dyna.get(
                "organization").equals("")) ? null : (String) dyna
                .get("organization");
        String organizationChoice = (dyna.get("organizationChoice") == null || dyna
                .get("organizationChoice").equals("")) ? null : (String) dyna
                .get("organizationChoice");
        String deptName = (dyna.get("deptName") == null || dyna.get("deptName")
                .equals("")) ? null : (String) dyna.get("deptName");
        String deptNo = (dyna.get("deptNo") == null || dyna.get("deptNo")
                .equals("")) ? null : (String) dyna.get("deptNo");
        BsDept bs = systemBaseInfoManager.getDeptByNo(deptNo);
        bs.setName(deptName);
        bs.setOrgno(organization);
        bs.setOrgname(organizationChoice);
        try {
            systemBaseInfoManager.savaBsDept(bs);
            request.setAttribute("message", "操作成功!");
            remindService.writeLog("【" + bsorg.getName() + "】下的 【"
                    + nowLoginUserName + "】 用户修改部门【" + deptName + "】【操作成功!】",
                    "", "", "HTTP", nowLoginUserName);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "操作失败!");
            remindService.writeLog("【" + bsorg.getName() + "】下的 【"
                    + nowLoginUserName + "】 用户修改部门【" + deptName + "】【操作失败!】",
                    "", "", "HTTP", nowLoginUserName);
        }
        dyna.set("deptName", deptName);
        dyna.set("organization", organization);
        return mapping.findForward("toDeptMessage");
    }

    // 地区树Tree
    @SuppressWarnings("unchecked")
    public void areaListTree(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        String parent = request.getParameter("node");
        boolean isFirst = false;
        if (parent.equals("0")) {
            parent = "510000";
            isFirst = true;
        }
        try {
            List list = new ArrayList();
            List<BsArea> resultArea = systemBaseInfoManager.areaList(parent,
                    isFirst);
            for (int i = 0; i < resultArea.size(); i++) {
                BsArea ba = resultArea.get(i);
                JSONObject object = new JSONObject();
                object.put("id", ba.getId());
                object.put("text", ba.getName());
                object.put("singleClickExpand", true);
                list.add(object);
            }

            JSONArray array = JSONArray.fromObject(list);
            response.getWriter().write(array.toString());
            response.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    

	@SuppressWarnings("unchecked")
	public void areaTreeQ(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String areaId = request.getParameter("node");
		boolean isFirst = false;
		if (areaId.equals("0")) {
			areaId = "510000";
			isFirst = true;
		}
		try {
			List list = new ArrayList();
			List resultArea = systemBaseInfoManager.areaList(areaId, isFirst);
			for (int i = 0; i < resultArea.size(); i++) {
				BsArea ba = (BsArea) resultArea.get(i);
				JSONObject object = new JSONObject();
				object.put("id", ba.getId());
				object.put("text", ba.getName());
				if ("0".equals(ba.getIsleaf().toString())) {
					object.put("leaf", false);
				} else {
					object.put("leaf", true);
				}
				object.put("checked", false);
				list.add(object);
			}
			JSONArray array = JSONArray.fromObject(list);
			response.getWriter().write(array.toString());
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public void orgTreeQ(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		response.setCharacterEncoding("UTF-8");
		String parent = request.getParameter("node");
		try {
			List list = new ArrayList();
			List resultOrg = systemBaseInfoManager.orgListByParentNo(parent);
			for (int i = 0; i < resultOrg.size(); i++) {
				BsOrg bc = (BsOrg) resultOrg.get(i);
				JSONObject object = new JSONObject();
				object.put("id", bc.getNo());
				object.put("text", bc.getName());
				if ("0".equals(bc.getIsleaf().toString())) {
					object.put("leaf", false);
				} else {
					object.put("leaf", true);
				}
				object.put("checked", false);
				list.add(object);
			}
			JSONArray array = JSONArray.fromObject(list);
			response.getWriter().write(array.toString());
			response.getWriter().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
