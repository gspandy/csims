package com.gtm.csims.log.service;

import java.util.List;

import net.sweet.dao.generic.support.Page;

import com.gtm.csims.model.BsLog;

/**
 * 日志业务处理类
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("unchecked")
public interface LogService {
    Page findLog4jList(String loginUserNickName, String message, String begin, String end,
            String priority, int pageNo, int pageSize);

    BsLog loginfo(String id);

    int countLog4j(String message, String begin, String end, String priority);

    List getAllLogs();

    List getAboveGoodLogs();
}
