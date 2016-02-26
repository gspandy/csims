package com.gtm.csims.webs.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gtm.csims.base.BaseAction;
import com.gtm.csims.jaas.UserCredentialName;
import com.gtm.csims.log.level.Logger;

/**
 * 用于单点登录的过滤器，判断CAS认证后的用户是否在该系统有效
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class SSOManageFilter extends BaseAction implements Filter {

    private String isOpen;

    public void init(FilterConfig config) throws ServletException {
        isOpen = config.getInitParameter("isOpen");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chian) throws IOException, ServletException {
        boolean isOpenb = true;
        try {
            isOpenb = Boolean.valueOf(isOpen);
        } catch (Exception e) {
            isOpenb = true;
        }
        if (isOpenb) {
            try {
                Boolean isCPB = Boolean.valueOf(super.getPubCredential(
                        UserCredentialName.ispcbuser.name(),
                        (HttpServletRequest) request,
                        (HttpServletResponse) response));
                Logger.debug("isCPB :" + isCPB);
                String login = super.getPrivCredential(UserCredentialName.login
                        .name(), (HttpServletRequest) request,
                        (HttpServletResponse) response);
                Logger.debug("login :" + login);
                if (!login.trim().equalsIgnoreCase("guest") && isCPB != null) {
                    if (isCPB) {
                        chian.doFilter(request, response);
                    } else {
                        HttpServletResponse rsp = (HttpServletResponse) response;
                        HttpServletRequest rqs = (HttpServletRequest) request;
                        rsp.sendRedirect(rqs.getContextPath()
                                + "/not_cpb_access_denied.jsp");
                        return;
                    }
                } else {
                    chian.doFilter(request, response);
                }
            } catch (Exception e) {
                chian.doFilter(request, response);
            }
        } else {
            chian.doFilter(request, response);
        }
    }

    public void destroy() {
        isOpen = null;
    }
}
