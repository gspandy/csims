package com.gtm.csims.business.datacollection.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 实现获取服务器处理状态进度条数据.
 * 
 * @author Sweet
 * 
 */
public class ProcessControllerServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    /**
     * 日志调试.
     */
    private static final Logger LOGGER = Logger.getLogger(ProcessControllerServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
            IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String reportType = req.getParameter("type");
        if (StringUtils.isNotBlank(reportType)) {
            // 设置respense content type,否则会有ajax乱码问题。
            resp.setContentType("text/html;charset=UTF-8");
            // 从session取出uploadPercentage并送回浏览器
            Object percent = req.getSession().getAttribute(reportType + "_uploadPercentage");
            String msg = StringUtils.EMPTY;
            double d = 0;
            if (percent == null) {
                d = 0;
            } else {
                d = (Double) percent;
            }
            // d < 1 代表正在上传文件
            if (d < 1) {
                msg = "正在上传文件...";
                resp.getWriter().write(
                        "{success:true, msg:'" + msg + "',percentage:'" + d + "',finished: false}");
                LOGGER.info("文件上传进度: " + d);
            } else if (d >= 1) {
                // d>=1 代表上传已经结束，开始处理分析excel
                // 在session中放置一个processExcelPercentage，代表分析excel的进度
                msg = "正在分析处理Excel...";
                double processExcelPercentage = 0.0;
                Object o = req.getSession().getAttribute(reportType + "_processExcelPercentage");
                if (o == null) {
                    processExcelPercentage = 0.0;
                    req.getSession().setAttribute(reportType + "_processExcelPercentage", 0.0);
                } else {
                    processExcelPercentage = (Double) req.getSession().getAttribute(
                            reportType + "_processExcelPercentage");
                    if (processExcelPercentage < 1) {
                        msg = "正在导入Excel文件数据...";
                        resp.getWriter().write(
                                "{success:true, msg:'" + msg + "',percentage:'"
                                        + processExcelPercentage + "',finished: false}");
                    } else {
                        // 当processExcelPercentage >= 1代表excel分析完毕
                        req.getSession().removeAttribute(reportType + "_uploadPercentage");
                        req.getSession().removeAttribute(reportType + "_processExcelPercentage");
                        // 客户端判断是否结束的标志
                        resp.getWriter().write(
                                "{success:true, msg:'" + msg + "',percentage:'1',finished: true}");
                    }
                }
                LOGGER.debug("处理Excel进度: " + processExcelPercentage);
                /* 
                 * 注意返回的数据，success代表状态
                 * percentage是百分比
                 * finished代表整个过程是否结束。
                 */
            }
            resp.getWriter().flush();
        } else {
            resp.getWriter().write("{success:true, msg:'请确认上传Excel的类型'}");
        }
    }
}
