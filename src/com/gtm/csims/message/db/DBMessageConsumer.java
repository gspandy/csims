package com.gtm.csims.message.db;

import java.util.Date;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsMessageDAO;
import com.gtm.csims.log.level.Logger;
import com.gtm.csims.message.Message;
import com.gtm.csims.model.BsMessage;

/**
 * 系统内部数据库消息消费者
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class DBMessageConsumer {
    private BsMessageDAO bsMessageDao;

    public void setBsMessageDao(BsMessageDAO bsMessageDao) {
        this.bsMessageDao = bsMessageDao;
    }

    @Transactional
    public void consume(Message o) {
        BsMessage bsMessage = null;
        if (o instanceof BsMessage) {
            bsMessage = (BsMessage) o;
        } else {
            try {
                bsMessage = new BsMessage();
                PropertyUtils.copyProperties(bsMessage, o);
            } catch (Exception e) {
                e.printStackTrace();
                Logger.debug("Message [" + o + "] send failure!");
            }
        }
        Logger.debug("db consume is " + o.getTitle());
        bsMessage.setReceivedate(new Date());
        bsMessageDao.save(bsMessage);
    }
}