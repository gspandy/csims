package com.gtm.csims.message;

import java.util.Date;
import java.util.Iterator;
import java.util.Set;

import com.gtm.csims.log.level.Logger;

/**
 * 统一消息发送控制者
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class MessageSendFacade {

    private Set<MessageSender> senders;

    public MessageSendFacade() {
        super();
    }

    public void setSenders(Set<MessageSender> senders) {
        this.senders = senders;
    }

    public String getSendersMessage() {
        return senders.toString();
    }

    /**
     * 动态添加Sender
     * 
     * @param sender
     */
    public void addSender(MessageSender sender) {
        this.senders.add(sender);
    }

    /**
     * 移除指定sender
     * 
     * @param index
     */
    public void removeSender(int index) {
        // this.senders.remove(Integer.valueOf(index));
    }

    /**
     * 发送消息
     * 
     * @param message
     */
    public void send(Message message) {
        for (Iterator<MessageSender> iterator = senders.iterator(); iterator
                .hasNext();) {
            try {
                MessageSender sender = iterator.next();
                sender.send(message);
                Logger.debug("Send Message with [" + sender.getClass() + "]");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据信息组装消息，并发送
     * 
     * @param loginOfReceiver
     * @param title
     * @param message
     */
    public void send(String loginOfReceiver, String titleText,
            String messageText, MessageCategory category) {
        for (Iterator<MessageSender> iterator = senders.iterator(); iterator
                .hasNext();) {
            try {
                MessageSender sender = iterator.next();
                Message message = new BaseMessage();
                message.setCreateDate(new Date());
                message.setFlag(MessageState.UNREAD.name());
                message.setMessage(messageText);
                message.setReceiver(loginOfReceiver);
                message.setSender("SYSTEM_AUTO");
                message.setTitle(titleText);
                message.setState("NONE");
                message.setCategory(category.name());
                sender.send(message);
                Logger.debug("Send Message with [" + sender.getClass() + "]"
                        + "[" + loginOfReceiver + "," + titleText + ","
                        + message + "]");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
