package com.gtm.csims.business.remind.impl;

import java.util.Date;

import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.business.remind.RemindService;
import com.gtm.csims.dao.BsLogDAO;
import com.gtm.csims.dao.BsMessageDAO;
import com.gtm.csims.log.level.Logger;
import com.gtm.csims.model.BsLog;
import com.gtm.csims.model.BsMessage;

/**
 * 写日志、发消息处理类,DAO实现
 * 
 * @author Sweet
 * 
 */
public class RemindServiceDB2Impl implements RemindService {
    BsMessageDAO bsMessageDao;
    BsLogDAO bsLogDao;

    public BsMessageDAO getBsMessageDao() {
        return bsMessageDao;
    }

    public void setBsMessageDao(BsMessageDAO bsMessageDao) {
        this.bsMessageDao = bsMessageDao;
    }

    public BsLogDAO getBsLogDao() {
        return bsLogDao;
    }

    public void setBsLogDao(BsLogDAO bsLogDao) {
        this.bsLogDao = bsLogDao;
    }

    @Transactional(readOnly = false)
    public void sendMessage(String title, String content, String receiver,
            String sender, String category) {
        Date date = new Date();
        BsMessage bsMessage = new BsMessage();
        bsMessage.setTitle(title);
        bsMessage.setMessage(content);
        bsMessage.setReceiver(receiver);
        bsMessage.setSender(sender);
        bsMessage.setCreatedate(date);
        bsMessage.setReceivedate(date);
        bsMessage.setCategory("TASK_ASSIGN");
        bsMessage.setFlag("UNREAD");
        bsMessage.setStatus("NONE");
        bsMessageDao.save(bsMessage);
        Logger.debug("sendMessage:" + title + content + receiver + sender
                + category);
        // StringBuffer sql = new StringBuffer(
        // "INSERT INTO BS_MESSAGE (Id , TITLE , MESSAGE , RECEIVER , SENDER ,
        // CREATEDATE, STATE, FLAG, CATEGORY, RECEIVEDATE ) VALUES ('");
        // sql.append(UUID.randomUUID().toString().replace("-",
        // "")).append("','").append(title)
        // .append("','").append(content).append("','").append(receiver)
        // .append("','").append(sender).append("', current timestamp,'")
        // .append("NONE").append("','").append("UNREAD").append("','")
        // .append("SYSTEM_NOTICE").append("', current timestamp)");
        // System.out.println(sql.toString());
        // jdbcTemplate.execute(sql.toString());

    }

    @Transactional(readOnly = false)
    public void writeLog(String content, String priority, String category,
            String thread, String processer) {
        BsLog bsLog = new BsLog();
        Date date = new Date();
        bsLog.setMessage(content);
        bsLog.setPriority("NONE");
        bsLog.setCategory("NONE");
        bsLog.setCreatedate(date);
        bsLog.setThread(thread);
        bsLog.setProcesser(processer);
        // if (content == null || content.length() < 0) {
        // return;
        // }
        // StringBuffer sql = new StringBuffer(
        // "INSERT INTO BS_LOG (Id , Message , CreateDate , Priority , Category
        // , Thread ) VALUES ('");
        // sql.append(UUID.randomUUID().toString().replace("-",
        // "")).append("','").append(content)
        // .append("', current timestamp,'").append(priority)
        // .append("','").append(category).append("','").append(thread)
        // .append("')");
        // System.out.println(sql.toString());
        // jdbcTemplate.execute(sql.toString());
        bsLogDao.save(bsLog);
        Logger.debug("writeLog:" + content + priority + category + date);
    }
}
