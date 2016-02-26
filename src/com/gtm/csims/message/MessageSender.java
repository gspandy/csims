package com.gtm.csims.message;

/**
 * 所消息发送者
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public interface MessageSender {
    /**
     * 发送实现了Message接口的所有消息
     * 
     * @param o
     */
    void send(Message o);
}
