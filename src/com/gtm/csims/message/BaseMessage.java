package com.gtm.csims.message;

import java.util.Date;

/**
 * 系统消息基类
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings("serial")
public class BaseMessage implements Message {
    private String title;
    private String message;
    private String receiver;
    private String sender;
    private Date createDate;
    private String state;
    private String flag;
    private Date receiveDate;
    private String category;

    public BaseMessage() {
        super();
    }

    public BaseMessage(String title, String message, String receiver,
            String sender, Date createDate, String state, String flag,
            Date receiveDate, String category) {
        super();
        this.title = title;
        this.message = message;
        this.receiver = receiver;
        this.sender = sender;
        this.createDate = createDate;
        this.state = state;
        this.flag = flag;
        this.receiveDate = receiveDate;
        this.category = category;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
