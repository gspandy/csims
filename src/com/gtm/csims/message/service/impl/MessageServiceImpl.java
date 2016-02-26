package com.gtm.csims.message.service.impl;

import java.util.ArrayList;
import java.util.List;

import net.sweet.dao.generic.support.Page;

import org.springframework.transaction.annotation.Transactional;

import com.gtm.csims.dao.BsMessageDAO;
import com.gtm.csims.message.MessageCategory;
import com.gtm.csims.message.service.MessageService;
import com.gtm.csims.model.BsMessage;
import com.gtm.csims.utils.StringUtil;

@SuppressWarnings("unchecked")
public class MessageServiceImpl implements MessageService {
    private static final int INDEX_PAGE_NUMBER = 5;
    private BsMessageDAO messageDAO;

    public void setMessageDAO(BsMessageDAO messageDAO) {
        this.messageDAO = messageDAO;
    }

    /**
     * 获取所有消息
     * 
     * @return
     */

    public List getAllMessage() {
        return messageDAO.getAll();
    }

    /**
     * 根据登录用户获取消息
     * 
     * @param ower
     * @return
     */

    public List getMessageByLoginUser(String ower) {
        String hql = "FROM BsMessage ms where ms.Receiver = ?";
        return messageDAO.find(hql, new Object[] { ower });
    }

    /**
     * 根据消息ID查询消息对象
     * 
     * @param messgeId
     * @return
     */

    public BsMessage getMessageById(String messgeId) {
        return messageDAO.get(messgeId);
    }

    /**
     * 根据消息ID删除消息
     * 
     * @param messageId
     */
    @Transactional
    public void deleteMessageById(String messageId) {
        messageDAO.removeById(messageId);
    }

    /**
     * 保存消息
     * 
     * @param message
     */
    @Transactional
    public void saveMessage(BsMessage message) {
        messageDAO.save(message);
    }

    /**
     * 根据消息参数查询
     * 
     * @param title
     * @param begin
     * @param end
     * @param state
     * @return
     */

    public List getMessageByParam(String title, String begin, String end,
            String state, String user) {
        StringBuffer sb = new StringBuffer("FROM BsMessage where 1=1 ");
        List<Object> param = new ArrayList<Object>();
        if (title != null) {
            sb.append(" and Title like  ? ");
            param.add("%" + title.trim() + "%");
        }
        if (begin != null) {
            sb.append(" and Receivedate > ? ");
            param.add(StringUtil.convert(begin));
        }
        if (end != null) {
            sb.append(" and Receivedate < ? ");
            param.add(StringUtil.convert(end));
        }
        if (state != null) {
            sb.append(" and Flag like ? ");
            param.add("%" + state.trim() + "%");
        }
        if (user != null) {
            if (!"admin".equals(user)) {
                sb.append(" and (Receiver = ? or Category = ?)");
                param.add(user);
                param.add(MessageCategory.SYSTEM_NOTICE.name());
            }
        }
        sb.append(" order by createdate DESC");
        List mesageList = messageDAO.find(sb.toString(), param.toArray());
        return mesageList;
    }

    /**
     * 根据消息参数查询
     * 
     * @param title
     * @param begin
     * @param end
     * @param state
     * @return
     */

    public int countMessageByParam(String title, String begin, String end,
            String state, String type, String owner) {
        StringBuffer sb = new StringBuffer(
                "SELECT count(*) FROM BsMessage where 1=1 ");
        StringBuffer sb2 = new StringBuffer("FROM BsMessage where 1=1 ");
        List<Object> param = new ArrayList<Object>();
        if (title != null) {
            sb.append(" and Title like  ? ");
            sb2.append(" and Title like  ? ");
            param.add("%" + title.trim() + "%");
        }
        if (begin != null) {
            sb.append(" and Receivedate > ? ");
            sb2.append(" and Receivedate > ? ");
            param.add(StringUtil.convert(begin));
        }
        if (end != null) {
            sb.append(" and Receivedate < ? ");
            sb2.append(" and Receivedate < ? ");
            param.add(StringUtil.convert(end));
        }
        if (state != null) {
            sb.append(" and Flag like ? ");
            sb2.append(" and Flag like ? ");
            param.add("%" + state.trim() + "%");
        }
        if (type != null) {
            sb.append(" and Category like ? ");
            sb2.append(" and Category like ? ");
            param.add("%" + type.trim() + "%");
        }
        if (owner != null) {
            if (!"admin".equals(owner)) {
                sb.append(" and (Receiver = ? or Category = ?)");
                sb2.append(" and (Receiver = ? or Category = ?)");
                param.add(owner);
                param.add(MessageCategory.SYSTEM_NOTICE.name());
            }
        }
        List size = messageDAO.find(sb.toString(), param.toArray());
        if (size != null && size.size() > 0) {
            return ((Long) size.get(0)).intValue();
        } else {
            return messageDAO.find(sb2.toString(), param.toArray()).size();
        }
    }

    /**
     * 根据消息参数查询
     * 
     * @param title
     * @param begin
     * @param end
     * @param state
     * @return
     */

    public Page getMessageByParamOfPage(String title, String begin, String end,
            String state, String type, String owner, int pageNo, int pageSize) {
        StringBuffer sb = new StringBuffer("FROM BsMessage where 1=1 ");
        List<Object> param = new ArrayList<Object>();
        if (title != null) {
            sb.append(" and Title like  ? ");
            param.add("%" + title.trim() + "%");
        }
        if (begin != null) {
            sb.append(" and Createdate > ? ");
            // begin = begin + " 0:00:00";
            // param.add(Timestamp.valueOf(begin));
            param.add(StringUtil.convert(begin));
        }
        if (end != null) {
            sb.append(" and Createdate < ? ");
            // end = end + " 0:00:00";
            // param.add(Timestamp.valueOf(end));
            param.add(StringUtil.convert(end));
        }
        if (state != null) {
            sb.append(" and Flag like ? ");
            param.add("%" + state.trim() + "%");
        }
        if (type != null) {
            sb.append(" and Category like ? ");
            param.add("%" + type.trim() + "%");
        }
        if (owner != null) {
            if (!"admin".equals(owner)) {
                sb.append(" and (Receiver = ? or Category = ?)");
                param.add(owner);
                param.add(MessageCategory.SYSTEM_NOTICE.name());
            }
        }
        sb.append(" order by Createdate DESC");
        Page page = messageDAO.pagedQuery(sb.toString(), pageNo, pageSize,
                param.toArray());
        return page;
    }

    /**
     * 获取任务消息
     * 
     * @return
     */

    public List getMessageListByTrank(String loginUser) {
        String hql = "From BsMessage mes where Category = ? and mes.Receiver = ? and mes.Flag='UNREAD' order by Createdate DESC";
        List messageList = (List) messageDAO.pagedQuery(hql, 1,
                INDEX_PAGE_NUMBER,
                new Object[] { MessageCategory.TASK_ASSIGN.name(), loginUser })
                .getResult();
        return messageList;
    }

    /**
     * 获取系统消息
     * 
     * @return
     */

    public List getMessageListBySystem() {
        String hql = "From BsMessage mes where Category = ? and mes.Flag='UNREAD' order by Createdate DESC";
        List messageList = (List) messageDAO.pagedQuery(hql, 1,
                INDEX_PAGE_NUMBER,
                new Object[] { MessageCategory.SYSTEM_NOTICE.name() })
                .getResult();
        return messageList;
    }
}
