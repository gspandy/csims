package com.gtm.csims.jaas.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jguard.jee.authentication.http.HttpConstants;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.gtm.csims.base.BaseAction;

/**
 * 安全认证失败Action
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class AuthenticationFailedAction extends BaseAction {

    @Override
    @SuppressWarnings("unchecked")
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        ActionErrors errors = new ActionErrors();
        // prevent bug when accessing directly to authorizationFailedURI without
        // authenticate
        if ((Class) session.getAttribute(HttpConstants.LOGIN_EXCEPTION_CLASS) != null) {
            // ActionMessage amClass = new ActionMessage(((Class) session
            // .getAttribute(HttpConstants.LOGIN_EXCEPTION_CLASS))
            // .getName(), false);
            ActionMessage amMessage = new ActionMessage((String) session
                    .getAttribute(HttpConstants.LOGIN_EXCEPTION_MESSAGE), false);
            // the LOGIN_EXCEPTION_CLASS is not put in the errors mechanism to
            // hide to the final user the exception class => this is not a user
            // concern,
            // and can be evaluated as a security risk (give information about
            // what
            // security system is used to securize your webapp)
            // errors.add(HttpConstants.LOGIN_EXCEPTION_CLASS,amClass);
            errors.add(HttpConstants.LOGIN_EXCEPTION_MESSAGE, amMessage);
            saveMessages(request, errors);
        }
        return mapping.findForward("authenticationFailedOK");
    }

}
