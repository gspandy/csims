package com.gtm.csims.log.level;

import org.apache.log4j.Level;

/**
 * 所有业务Service的日志记录级别
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("serial")
public class ServiceLevel extends Level {
    public ServiceLevel(int level, String name, int sysLogLevel) {
        super(level, name, sysLogLevel);
    }
}
