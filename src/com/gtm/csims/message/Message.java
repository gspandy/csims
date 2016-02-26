package com.gtm.csims.message;

import java.io.Serializable;
import java.util.Date;

/**
 * 统一的系统消息接口
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public interface Message extends Serializable {
    String getTitle();

    void setTitle(String title);

    String getMessage();

    void setMessage(String message);

    Date getCreateDate();

    void setCreateDate(Date createDate);

    String getReceiver();

    void setReceiver(String receiver);

    String getSender();

    void setSender(String sender);

    String getState();

    void setState(String state);

    String getFlag();

    void setFlag(String flag);

    /**
     * Return the value associated with the column: category
     */
    java.lang.String getCategory();

    /**
     * Set the value related to the column: category
     * 
     * @param category
     *            the category value
     */
    void setCategory(java.lang.String category);

    /**
     * Return the value associated with the column: receiveDate
     */
    java.util.Date getReceiveDate();

    /**
     * Set the value related to the column: receiveDate
     * 
     * @param receiveDate
     *            the receiveDate value
     */
    void setReceiveDate(java.util.Date receiveDate);
}
