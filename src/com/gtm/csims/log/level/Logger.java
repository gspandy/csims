package com.gtm.csims.log.level;

import org.apache.log4j.MDC;

/**
 * 统一的日记记录器
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class Logger {
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger
            .getLogger("SWEET_LOG");

    /**
     * 调试级别
     * 
     * @param logInfo
     */
    public static void debug(Object logInfo) {
        LOG.log(LogLevel.DEBUG_LEVEL, logInfo);
    }

    /**
     * Action级别
     * 
     * @param logInfo
     */
    public static void action(Object logInfo) {
        LOG.log(LogLevel.ACTION_LEVEL, logInfo);
    }

    /**
     * Service级别
     * 
     * @param logInfo
     */
    public static void service(Object logInfo) {
        LOG.log(LogLevel.SERVICE_LEVEL, logInfo);
    }

    /**
     * Service级别
     * 
     * @param logInfo
     */
    public static void error(Object logInfo) {
        LOG.log(LogLevel.ERROR_LEVEL, logInfo);
    }

    /**
     * 记录业务方法日志
     * 
     * @param logInfo
     *            需要记录的日志信息
     * @param login
     *            日志主语
     * @param actions
     *            日志谓语
     * @param doWhat
     *            日志宾语
     * @param summary
     *            日志备注
     */
    public static void log(Object logInfo, Object login, Object actions,
            Object doWhat, Object summary, Object title) {
        MDC.put("login", login);
        MDC.put("actions", actions);
        MDC.put("doWhat", doWhat);
        MDC.put("summary", summary);
        MDC.put("title", title);
        MDC.put("content", logInfo);
        LOG.log(LogLevel.SERVICE_LEVEL, logInfo);
    }
}
