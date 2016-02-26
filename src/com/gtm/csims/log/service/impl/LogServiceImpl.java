package com.gtm.csims.log.service.impl;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.rules.InvalidRuleSessionException;
import javax.rules.StatelessRuleSession;

import net.sweet.dao.generic.support.Page;

import org.springframework.transaction.annotation.Transactional;
import org.springmodules.jsr94.core.Jsr94Template;
import org.springmodules.jsr94.support.StatelessRuleSessionCallback;

import com.gtm.csims.dao.BsLogDAO;
import com.gtm.csims.log.service.LogService;
import com.gtm.csims.model.BsLog;
import com.gtm.csims.utils.StringUtil;

/**
 * Log维护业务实现类
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("unchecked")
public class LogServiceImpl implements LogService {
    public static final String LOGS_RULE_URI = "logs";

    private Jsr94Template jsr94template;
    private BsLogDAO bsLogDao;

    @Transactional(readOnly = true)
    public int countLog4j(String message, String begin, String end, String priority) {
        StringBuffer sb = new StringBuffer("From BsLog where 1=1 ");
        List<Object> param = new ArrayList<Object>();
        if (message != null) {
            sb.append(" and Message like ? ");
            param.add("%" + message.trim() + "%");
        }
        if (begin != null) {
            sb.append(" and Createdate > ? ");
            param.add(StringUtil.convert(begin));
        }

        if (end != null) {
            sb.append(" and Createdate < ? ");
            param.add(StringUtil.convert(end));
        }

        if (priority != null) {
            sb.append(" and Priority like ? ");
            param.add("%" + priority.trim() + "%");
        }
        int countPage = bsLogDao.find(sb.toString(), param.toArray()).size();

        return countPage;
    }

    /**
     * 日志列表查看(按查询条件)
     */
    @Transactional(readOnly = true)
    public Page findLog4jList(String loginUserNickName, String message, String begin, String end,
            String priority, int pageNo, int pageSize) {
        StringBuffer sb = new StringBuffer("From BsLog where 1=1 ");
        List<Object> param = new ArrayList<Object>();
        if (!"admin".equals(loginUserNickName)) {
            sb.append(" and Processer = ? ");
            param.add(loginUserNickName);
        }
        if (message != null) {
            sb.append(" and Message like ? ");
            param.add("%" + message + "%");
        }
        if (begin != null) {
            sb.append(" and Createdate > ? ");
            param.add(StringUtil.convert(begin));
        }

        if (end != null) {
            sb.append(" and Createdate < ? ");
            param.add(StringUtil.convert(end));
        }

        if (priority != null) {
            sb.append(" and Priority like ? ");
            param.add("%" + priority + "%");
        }
        sb.append(" order by createdate desc");
        Page page = bsLogDao.pagedQuery(sb.toString(), pageNo, pageSize, param.toArray());
        return page;
    }

    @Transactional(readOnly = true)
    public BsLog loginfo(String id) {
        return bsLogDao.get(id);
    }

    @Transactional(readOnly = true)
    public List getAllLogs() {
        return bsLogDao.getAll();
    }

    @Transactional(readOnly = true)
    public List getAboveGoodLogs() {
        final List logs = bsLogDao.getAll();
        this.jsr94template.executeStateless(LOGS_RULE_URI, null,
                new StatelessRuleSessionCallback() {
                    public Object execute(StatelessRuleSession session)
                            throws InvalidRuleSessionException, RemoteException {
                        return session.executeRules(logs);
                    }
                });
        List aboveGoodLogs = new ArrayList();
        for (Iterator i = logs.iterator(); i.hasNext();) {
            BsLog log = (BsLog) i.next();
            if (log.getThread().equals("Good")) {
                aboveGoodLogs.add(log);
            }
        }
        return aboveGoodLogs;
    }

    public void setJsr94template(Jsr94Template jsr94template) {
        this.jsr94template = jsr94template;
    }

    public void setBsLogDao(BsLogDAO bsLogDao) {
        this.bsLogDao = bsLogDao;
    }

}
