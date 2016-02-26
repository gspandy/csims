package com.gtm.csims.jaas.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jguard.jee.authentication.http.CaptchaChallengeBuilder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.octo.captcha.service.CaptchaServiceException;
import com.gtm.csims.base.BaseAction;
import com.sun.image.codec.jpeg.ImageFormatException;

/**
 * 验证码处理Action，页面通过调用image属性
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class CaptchaAction extends BaseAction {

    private static Log logger = LogFactory.getLog(CaptchaAction.class);

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) {
        try {
            // response.addHeader("Cache-Control", "no-cache");
            CaptchaChallengeBuilder.buildCaptchaChallenge(request, response);
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage());
            return mapping.findForward("CaptchaKO");
        } catch (CaptchaServiceException e) {
            logger.error(e.getMessage());
            return mapping.findForward("CaptchaKO");
        } catch (ImageFormatException e) {
            logger.error(e.getMessage());
            return mapping.findForward("CaptchaKO");
        } catch (IOException e) {
            logger.error(e.getMessage());
            return mapping.findForward("CaptchaKO");
        }
        return null;
    }
}
