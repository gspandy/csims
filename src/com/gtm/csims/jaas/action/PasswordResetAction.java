package com.gtm.csims.jaas.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.jaas.service.UserService;

/**
 * 密码修改Action
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class PasswordResetAction extends BaseAction {

    private static final Integer PASSWORD_LENGTH = 5;

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public ActionForward toReset(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        return mapping.findForward("toReset");
    }

    public ActionForward reset(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) {
        String oldPassword = request.getParameter("old");
        String newPassword = request.getParameter("new");
        String againPassword = request.getParameter("newAgain");
        if (StringUtils.isEmpty(oldPassword) || StringUtils.isEmpty(newPassword)
                || StringUtils.isEmpty(againPassword)) {
            super.printMessage(request, response, "修改密码信息填写不完整!", request.getContextPath()
                    + "/resetPassword.do?method=toReset");
            return null;
        }

        if (newPassword.equals(oldPassword)) {
            super.printMessage(request, response, "新密码与旧密码相同,请重新修改!", request.getContextPath()
                    + "/resetPassword.do?method=toReset");
            return null;
        }

        if (!newPassword.equals(againPassword)) {
            super.printMessage(request, response, "密码信息不能为空，或者新密码确认不正确！", request.getContextPath()
                    + "/resetPassword.do?method=toReset");
            return null;
        }

        if (newPassword.length() < PASSWORD_LENGTH) {
            super.printMessage(request, response, "密码必须是" + PASSWORD_LENGTH + "位以上",
                    request.getContextPath() + "/resetPassword.do?method=toReset");
            return null;
        }
        String passwordStr = userService.getPassword(UserCredentialName.login.name(),
                UserCredentialName.password.name(), request.getRemoteUser());
        if (userService.getIsPasswordMD5()) {
            if (!DigestUtils.md5Hex(oldPassword).equalsIgnoreCase(passwordStr)) {
                super.printMessage(request, response, "旧密码输入错误！", request.getContextPath()
                        + "/resetPassword.do?method=toReset");
                return null;
            }
        } else {
            if (!oldPassword.equalsIgnoreCase(passwordStr)) {
                super.printMessage(request, response, "旧密码输入错误！", request.getContextPath()
                        + "/resetPassword.do?method=toReset");
                return null;
            }
        }
        userService.updateUserPassword(UserCredentialName.login.name(),
                UserCredentialName.password.name(),
                userService.getIsPasswordMD5() ? DigestUtils.md5Hex(againPassword) : againPassword,
                request.getRemoteUser());
        try {
            // request.getSession().invalidate();
            super.printMessageToParent(request, response, "密码修改成功!", "error");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
