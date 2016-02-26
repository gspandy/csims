package com.gtm.csims.webs.filter;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;

/**
 * 用户locale切换处理过滤器
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class LocaleManageFilter implements Filter {
    private String defaultLocale;

    public void init(FilterConfig config) throws ServletException {
        defaultLocale = config.getInitParameter("defaultlocale");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chian) throws IOException, ServletException {
        chian.doFilter(request, response);
        String selectedLocaleStr = defaultLocale;
        if (request.getParameter("locale") == null
                || request.getParameter("locale").equals("")) {
            return;
        }
        try {
            selectedLocaleStr = request.getParameter("locale");
            String[] lacaleArray = selectedLocaleStr.split("_");
            if (request instanceof HttpServletRequest) {
                ((HttpServletRequest) request).getSession().setAttribute(
                        Globals.LOCALE_KEY,
                        new Locale(lacaleArray[0], lacaleArray[1]));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        defaultLocale = null;
    }
}
