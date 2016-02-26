package com.gtm.csims.message.action;

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
import com.gtm.csims.log.level.Logger;
import com.gtm.csims.message.MessageCategory;
import com.gtm.csims.message.MessageState;
import com.gtm.csims.message.service.MessageService;
import com.gtm.csims.model.BsMessage;
import com.gtm.csims.utils.DateUtil;

/**
 * 消息功能Action
 * 
 * @author Sweet
 * 
 */
@SuppressWarnings("unchecked")
public class MessageDispatchAction extends BaseAction {
    private static final Integer PAGE_CONTAIN_NUMBER = 12;
    private MessageService messageService;

    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * 消息管理
     * 
     * 消息加载列表
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    public ActionForward messageQuery(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        DynaActionForm dynaForm = (DynaActionForm) form;
        // String btnType = request.getParameter("btnType");
        String ower = request.getRemoteUser();
        // 获取页面查询条件信息
        String title = (dynaForm.get("title") == null || dynaForm.get("title")
                .equals("")) ? null : dynaForm.getString("title");
        String begin = (dynaForm.get("begin") == null || dynaForm.get("begin")
                .equals("")) ? null : dynaForm.getString("begin");
        String end = (dynaForm.get("end") == null || dynaForm.get("end")
                .equals("")) ? null : dynaForm.getString("end");
        String state = (dynaForm.get("state") == null || dynaForm.get("state")
                .equals("")) ? null : dynaForm.getString("state");
        String type = (dynaForm.get("type") == null || dynaForm.get("type")
                .equals("")) ? null : dynaForm.getString("type");

        String pageNo = (dynaForm.get("pageNo") == null || dynaForm.get(
                "pageNo").equals("")) ? "1" : (String) dynaForm.get("pageNo");
        String pageSize = (dynaForm.get("pageSize") == null || dynaForm.get(
                "pageSize").equals("")) ? PAGE_CONTAIN_NUMBER.toString()
                : (String) dynaForm.get("pageSize");
        // 统计查询满足条件的消息信息
        int pageCountTemp = 0;
        Page page = messageService.getMessageByParamOfPage(title, begin, end,
                state, type, ower, Integer.valueOf(pageNo), Integer
                        .valueOf(pageSize));
        // pageCountTemp = messageService.countMessageByParam(title, begin, end,
        // state, type, ower);
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
            dynaForm.set("previous", "1");
        } else {
            dynaForm.set("previous", "0");
        }
        if (Integer.valueOf(pageNo) != pageCountTemp) {
            dynaForm.set("next", "1");
        } else {
            dynaForm.set("next", "0");
        }

        // 分页列表循环

        request.setAttribute("pageNo", pageNo);
        dynaForm.set("pageNo", pageNo);
        request.setAttribute("pageCount", pageCountTemp);
        dynaForm.set("pageCount", pageCountTemp);
        request.setAttribute("messageList", page.getResult());
        request.setAttribute("messageCategorys", MessageState.values());
        request.setAttribute("MessageCategory", MessageCategory.values());
        Logger.debug(request.getRemoteUser());
        Logger.debug(super.getPrivCredential("login", request, response));
        return mapping.findForward("toMessage");
    }

    /**
     * 消息管理
     * 
     * 查看消息详细内容
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward lookMessage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String messageId = request.getParameter("messageId");
        Logger.debug("message id is" + messageId);
        BsMessage message = null;
        try {
            if (messageId == null || messageId.equals("")) {
                super.printMessage(request, response, "查看消息失败!", "error");
                return null;
            }
            message = messageService.getMessageById(messageId);

            request.setAttribute("message", message);
            return mapping.findForward("toMessageDetail");
        } catch (Exception e) {
            e.printStackTrace();
            super.printMessage(request, response, "查看消息失败!", "error");
            return null;
        }
    }

    /**
     * 消息管理
     * 
     * 删除消息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward deleteMessage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String messageId = request.getParameter("messageId");
        BsMessage message = messageService.getMessageById(messageId);
        try {
            if (messageId == null || messageId.equals("")) {
                this.printMessage(request, response, "获取消息ID失败", "error");
                return null;
            }
            Logger.debug("message id is" + messageId);
            messageService.deleteMessageById(messageId);
            Logger.action(request.getRemoteUser() + "用户在"
                    + DateUtil.convert(new Date()) + "删除消息【"
                    + message.getTitle() + "】成功");
            return messageQuery(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            super.printMessage(request, response, "消息删除失败!", "error");
            return null;
        }

    }

    /**
     * 消息管理
     * 
     * 查看未读信息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward ascertainLook(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        String messageId = request.getParameter("messageId");
        BsMessage message = null;
        try {
            if (messageId == null || messageId.equals("")) {
                super.printMessage(request, response, "确认消息失败!", "error");
                return null;
            }
            Logger.debug("get message id is:" + messageId);
            message = messageService.getMessageById(messageId);
            Logger.debug("my message is:" + message);

            message.setFlag("READED");
            messageService.saveMessage(message);
            return messageQuery(mapping, form, request, response);
        } catch (Exception e) {
            e.printStackTrace();
            super.printMessage(request, response, "确认消息失败!", "error");
            return null;
        }
    }

    /**
     * 在主页显示消息
     * 
     * @param mapping
     * @param form
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public ActionForward forwordToshowMessage(ActionMapping mapping,
            ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        String loginUser = request.getRemoteUser();
        try {
            List messageTrankList = messageService
                    .getMessageListByTrank(loginUser);
            List messageSystem = messageService.getMessageListBySystem();
            /*
             * if (messageTrankList == null || messageTrankList.size() == 0)
             * messageTrankList = null; if (messageSystem == null ||
             * messageSystem.size() == 0) messageSystem = null;
             */
            request.setAttribute("messageSystem", messageSystem);
            request.setAttribute("messageTrankList", messageTrankList);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return mapping.findForward("toIndexShow");
    }
}
