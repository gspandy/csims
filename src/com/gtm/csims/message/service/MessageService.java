package com.gtm.csims.message.service;

import java.util.List;

import net.sweet.dao.generic.support.Page;

import com.gtm.csims.model.BsMessage;

@SuppressWarnings("unchecked")
public interface MessageService {

    /**
     * 根据登录人获取消息
     * 
     * @param ower
     * @return
     */
    List getMessageByLoginUser(String ower);

    /**
     * 获取所以消息
     * 
     * @return
     */
    List getAllMessage();

    /**
     * 根据消息ID获取消息对象
     * 
     * @param messgeId
     * @return
     */
    BsMessage getMessageById(String messgeId);

    /**
     * 根据消息ID删除消息
     * 
     * @param messageId
     */
    void deleteMessageById(String messageId);

    /**
     * 保存消息
     * 
     * @param message
     */
    void saveMessage(BsMessage message);

    /**
     * 根据参数查询消息
     * 
     * @param title
     * @param begin
     * @param end
     * @param state
     * @return
     */
    List getMessageByParam(String title, String begin, String end,
            String state, String user);

    /**
     * 统计消息数
     * 
     * @param title
     * @param begin
     * @param end
     * @param state
     * @param owner
     * @return
     */
    int countMessageByParam(String title, String begin, String end,
            String state, String type, String owner);

    /**
     * 分页查询消息
     * 
     * @param title
     * @param begin
     * @param end
     * @param state
     * @param owner
     * @param pageNo
     * @param pageSize
     * @return
     */
    Page getMessageByParamOfPage(String title, String begin, String end,
            String state, String type, String owner, int pageNo, int pageSize);

    /**
     * 获取任务消息
     * 
     * @return
     */
    List getMessageListByTrank(String loginUser);

    /**
     * 获取系统消息
     * 
     * @return
     */
    List getMessageListBySystem();
}
