package com.gtm.csims.log.level;

import org.apache.log4j.Level;

/**
 * 所有系统调试的日志记录级别
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("serial")
public class DebugLevel extends Level {
    public DebugLevel(int level, String name, int sysLogLevel) {
        super(level, name, sysLogLevel);
    }
}
