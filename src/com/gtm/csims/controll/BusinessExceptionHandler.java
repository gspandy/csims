package com.gtm.csims.controll;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

/**
 * 业务异常处理类，Struts捕获业务异常后的处理方式
 * 
 * @author Sweet
 * 
 */
public class BusinessExceptionHandler extends ExceptionHandler {
    @Override
    public ActionForward execute(Exception ex, ExceptionConfig ae,
            ActionMapping mapping, ActionForm formInstance,
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException {

        ActionForward forward = null;
        // ActionMessage error = null;
        // String property = null;

        if (ae.getPath() != null) {// 配置文件中的path
            forward = new ActionForward(ae.getPath());
        } else {
            forward = mapping.getInputForward();// 如果没有找到path，转到input配置的路径
        }

        request.setAttribute("LocalizedMessage", ex.getLocalizedMessage());
        request.setAttribute("Message", ex.getMessage());
        request.setAttribute("Class", ex.getClass());
        request.setAttribute("StackTrace", ex.getStackTrace());
        // StackTraceElement[] ste = ex.getStackTrace();
        // for (int i = 0; i < ste.length; i++) {
        // System.out.println(ste[i].getClassName());
        // System.out.println(ste[i].getMethodName());
        // }
        // this.logException(ex);

        // 处理自定义的异常类SystemException
        return forward;
    }
}
