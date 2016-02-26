package com.gtm.csims.log.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sweet.dao.generic.support.Page;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.jaas.service.DataAuthorizationService;
import com.gtm.csims.log.service.LogService;
import com.gtm.csims.message.MessageSendFacade;
import com.gtm.csims.model.BsLog;

/**
 * 日志维护Action
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("unused")
public class LogDispatchAction extends BaseAction {
    private LogService log4jService;

    /**
     * 分页显示日志信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    public ActionForward listLog4j(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dyna = (DynaActionForm) form;
        String loginUserNickName = this.getPubCredential(
                UserCredentialName.nickname.name(), request, response);
        String begin = (dyna.get("begin") == null || dyna.get("begin").equals(
                "")) ? null : (String) dyna.get("begin");
        String end = (dyna.get("end") == null || dyna.get("end").equals("")) ? null
                : (String) dyna.get("end");
        String message = (dyna.get("message") == null || dyna.get("message")
                .equals("")) ? null : (String) dyna.get("message");
        String priority = (dyna.get("priority") == null || dyna.get("priority")
                .equals("")) ? null : (String) dyna.get("priority");

        // 分页处理Start
        String pageNo = (dyna.get("pageNo") == null || dyna.get("pageNo")
                .equals("")) ? "1" : (String) dyna.get("pageNo");
        String pageSize = (dyna.get("pageSize") == null || dyna.get("pageSize")
                .equals("")) ? PAGE_CONTAIN_NUMBER.toString() : (String) dyna
                .get("pageSize");
        int pageCountTemp = 0;
        // 列表循环
        Page page = log4jService.findLog4jList(loginUserNickName, message,
                begin, end, priority, Integer.valueOf(pageNo), Integer
                        .valueOf(pageSize));
        // pageCountTemp = log4jService.countLog4j(message, begin, end,
        // priority);
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

        request.setAttribute("logs", page.getResult());
        return mapping.findForward("listlogOK");
    }

    /**
     * 显示日志详细信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    public ActionForward viewLog(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String idTemp = request.getParameter("id");
        BsLog log4j = log4jService.loginfo(idTemp);
        request.setAttribute("log", log4j);
        // Logger.action("log [" + id + "] has been view");

        /*
         * MessageResources message = this.getResources(request);
         * Logger.debug(message.getMessage("common.text.welcome"));
         */

        // test message and log effient
        // for (int i = 0; i < 5; i++) {
        // messageSendFacade.send("admin", "title " + i, "message " + i,
        // MessageCategory.SYSTEM_NOTICE);
        // Logger.action("send title " + i + " sucessfully!");
        // }
        return mapping.findForward("viewlogOK");
    }

    public void setLog4jService(LogService log4jService) {
        this.log4jService = log4jService;
    }

}
