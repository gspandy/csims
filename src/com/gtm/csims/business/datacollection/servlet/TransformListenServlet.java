package com.gtm.csims.business.datacollection.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.security.auth.Subject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspTagException;

import net.sf.jguard.core.authentication.credentials.JGuardCredential;
import net.sf.jguard.ext.SecurityConstants;
import net.sf.jguard.jee.authentication.http.HttpAuthenticationUtils;
import net.sf.jguard.jee.authentication.http.HttpConstants;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gtm.csims.business.datacollection.submit.base.BaseReportReader;
import com.gtm.csims.business.datacollection.submit.base.CSVFileReader;
import com.gtm.csims.business.datacollection.submit.base.ExcelProgressListener;
import com.gtm.csims.jaas.UserCredentialName;

/**
 * 文件上传进度获取类.
 * 
 * @author Sweet
 * 
 */
public class TransformListenServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * 日志调试.
     */
    private static final Logger LOGGER = Logger.getLogger(TransformListenServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        super.doGet(req, resp);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        FileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        String reportType = req.getParameter("type");
        String fileType = req.getParameter("filetype");
        if (StringUtils.isBlank(reportType)) {
            resp.getWriter().write("{success:true,msg:'请确认上传数据文件类型'}");
            resp.getWriter().flush();
            return;
        }

        /**
         * 文件上传监听器.
         */
        class FileProgressListener implements ProgressListener {
            private HttpServletRequest request = null;
            private String type;

            FileProgressListener(HttpServletRequest request, String type) {
                this.request = request;
                this.type = type;
            }

            public void update(long hasFinished, long total, int pItems) {
                double percentage = (double) hasFinished / (double) total;
                // 上传的进度保存到session，以便processController.jsp使用
                request.getSession().setAttribute(type + "_uploadPercentage", percentage);
            }
        }

        /**
         * Excel文件处理监听器.
         */
        class AEExcelProgressListener implements ExcelProgressListener {
            private HttpServletRequest request = null;
            private String type;

            AEExcelProgressListener(HttpServletRequest request, String type) {
                this.request = request;
                this.type = type;
            }

            public void update(long pBytesRead, long pContentLength, int pItems) {
                double percentage = (double) pBytesRead / (double) pContentLength;
                // 处理进度保存到session
                request.getSession().setAttribute(type + "_processExcelPercentage", percentage);
            }
        }

        if (StringUtils.equalsIgnoreCase(fileType, "CSV")) {
            upload.setProgressListener(new FileProgressListener(req, reportType + fileType));
        } else {
            upload.setProgressListener(new FileProgressListener(req, reportType));
        }
        List<FileItem> items = null;
        try {
            items = (List<FileItem>) upload.parseRequest(req);
        } catch (FileUploadException e1) {
            LOGGER.error("上传文件组件获取请求时失败", e1);
            resp.getWriter().write("{success:true,msg:'请确认文件选择是否正确'}");
            resp.getWriter().flush();
        }
        if (items != null) {
            Iterator<FileItem> iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = iter.next();
                if (item.isFormField()) {
                    LOGGER.debug("处理表单内容 ...");
                } else {
                    LOGGER.debug("处理上传的文件 ...");
                    // String fieldName = item.getFieldName();
                    // 保存Excel文件到服务器
                    // String fileName = item.getName();
                    // String contentType = item.getContentType();
                    // boolean isInMemory = item.isInMemory();
                    // long sizeInBytes = item.getSize();
                    // LOGGER.debug(isInMemory + "-" + sizeInBytes);
                    // File uploadedFile = new File(req.getSession()
                    // .getServletContext().getRealPath(File.separator)
                    // + FileHandler.UPLOAD_FILE_FOLDER_PATH
                    // + File.separator
                    // + FileHandler.EXCEL_FILE_FOLDER_PATH
                    // + File.separator + "");
                    // item.write(uploadedFile);

                    // 读取Excel文件并处理
                    InputStream is = null;
                    try {
                        is = item.getInputStream();
                        // is = new FileInputStream(uploadedFile);
                        ServletContext servletContext = this.getServletContext();
                        WebApplicationContext ctx = WebApplicationContextUtils
                                .getWebApplicationContext(servletContext);
                        BaseReportReader reportReader = null;
                        CSVFileReader reportCSVReader = null;
                        if (reportType.equals("aeforce")) {
                            reportReader = (BaseReportReader) ctx.getBean("aenforcReader");
                            reportReader.setListener(new AEExcelProgressListener(req, reportType));
                            reportReader.read(is, null);
                        } else if (reportType.equals("aeinspectionanl")) {
                            reportReader = (BaseReportReader) ctx.getBean("aeinspectionAnlReader");
                            reportReader.setListener(new AEExcelProgressListener(req, reportType));
                            Map<String, String> params = new HashMap<String, String>();
                            String nowLoginUserName = this.getPrivCredential(
                                    UserCredentialName.login.name(), req, resp);
                            params.put("PARAMS_REPORTER", nowLoginUserName);
                            reportReader.read(is, params);
                        } else if (reportType.equals("examscore")) {
                            if (StringUtils.equalsIgnoreCase(fileType, "CSV")) {
                                reportCSVReader = (CSVFileReader) ctx.getBean("examScoreCsvReader");
                                reportCSVReader.setListener(new AEExcelProgressListener(req,
                                        reportType + fileType));
                                Map<String, String> params = new HashMap<String, String>();
                                String nowLoginUserName = this.getPrivCredential(
                                        UserCredentialName.login.name(), req, resp);
                                params.put("PARAMS_REPORTER", nowLoginUserName);
                                reportCSVReader.read(is, params);
                            } else {
                                reportReader = (BaseReportReader) ctx.getBean("examScoreReader");
                                reportReader.setListener(new AEExcelProgressListener(req,
                                        reportType));
                                Map<String, String> params = new HashMap<String, String>();
                                String nowLoginUserName = this.getPrivCredential(
                                        UserCredentialName.login.name(), req, resp);
                                params.put("PARAMS_REPORTER", nowLoginUserName);
                                reportReader.read(is, params);
                            }
                        }
                        LOGGER.debug("文件上传完成");
                    } catch (Exception e) {
                        LOGGER.error("文件上传servlet发生错误", e);
                    } finally {
                        if (is != null) {
                            is.close();
                        }
                    }
                }
            }
            resp.getWriter().write("{success:true,msg:'保存上传文件数据并处理数据文件成功!'}");
            resp.getWriter().flush();
        } else {
            resp.getWriter().write("{success:true,msg:'请确认文件选择是否正确'}");
            resp.getWriter().flush();
        }
    }

    /**
     * get private credential value
     * 
     * @param name
     * @param request
     * @param response
     * @return
     */
    @SuppressWarnings("unchecked")
    public String getPrivCredential(String name, HttpServletRequest request,
            HttpServletResponse response) {
        Subject subject = this.getSubject(request, response);
        String value = "";
        try {
            if (subject != null) {
                Set<Object> privateCredentials = subject.getPrivateCredentials();
                Iterator<Object> it = privateCredentials.iterator();
                JGuardCredential cred = null;
                while (it.hasNext()) {
                    cred = (JGuardCredential) it.next();
                    // if the id wanted by the webapp developer
                    // is encountered in the public credentials subject
                    if (cred.getId().equals(name)) {
                        value = (String) cred.getValue();
                        break;
                    }
                }
            }
        } catch (SecurityException sex) {
            sex.printStackTrace();
        }
        return value;
    }

    /**
     * Grab the subject from request
     * 
     * @param request
     * @param response
     * @return
     * @throws JspTagException
     */
    public Subject getSubject(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        HttpAuthenticationUtils httpUtils = (HttpAuthenticationUtils) session
                .getAttribute(HttpConstants.AUTHN_UTILS);
        Subject subject = null;
        // AuthenticationManager authenticationManager = (AuthenticationManager)
        // request
        // .getSession().getServletContext().getAttribute(
        // SecurityConstants.AUTHENTICATION_MANAGER);
        if (httpUtils != null) {
            subject = httpUtils.getSubject();
            // subject =
            // authenticationManager.findUser(this.getPrivCredential(UserCredentialName.login
            // .name(), request, response));
        }
        boolean local = true;
        String authenticationScope = (String) session.getServletContext().getAttribute(
                SecurityConstants.AUTHENTICATION_SCOPE);
        if (SecurityConstants.JVM_SCOPE.equalsIgnoreCase(authenticationScope)) {
            local = false;
        }
        if (subject == null || session.getAttribute(HttpConstants.AUTHN_UTILS) == null) {
            try {
                HttpAuthenticationUtils.authenticate((HttpServletRequest) request,
                        (HttpServletResponse) response, false, local);
                // we grab the subject authenticated
                subject = ((HttpAuthenticationUtils) session
                        .getAttribute(HttpConstants.AUTHN_UTILS)).getSubject();
            } catch (IOException e) {
                return null;
            }
        }
        return subject;
    }
}
