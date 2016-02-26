package com.gtm.csims.log.level;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.net.SyslogAppender;

/**
 * 日志级别维护类
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public interface LogLevel {
    Level DEBUG_LEVEL = new ServiceLevel(Priority.INFO_INT, LogCategory.DEBUG
            .name(), SyslogAppender.LOG_LOCAL0);

    Level ACTION_LEVEL = new ActionLevel(Priority.BUSINESS_INT,
            LogCategory.ACTION.name(), SyslogAppender.LOG_LOCAL0);

    Level SERVICE_LEVEL = new ServiceLevel(Priority.BUSINESS_INT,
            LogCategory.SERVICE.name(), SyslogAppender.LOG_LOCAL0);

    Level ERROR_LEVEL = new ErrorLevel(Priority.BUSINESS_INT, LogCategory.ERROR
            .name(), SyslogAppender.LOG_LOCAL0);
}
