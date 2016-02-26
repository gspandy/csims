package com.gtm.csims.log.level;

import org.apache.log4j.Level;

/**
 * 所有ActionServlet的日志记录级别
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("serial")
public class ActionLevel extends Level {
    public ActionLevel(int level, String name, int sysLogLevel) {
        super(level, name, sysLogLevel);
    }
}