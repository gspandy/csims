package com.gtm.csims.message.db;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.gtm.csims.message.Message;
import com.gtm.csims.message.MessageSender;

/**
 * 系统内部数据库消息生产者
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class DBMessageProducer implements MessageSender {
    private JmsTemplate template;

    private Queue destination;

    public void setTemplate(JmsTemplate template) {
        this.template = template;
    }

    public void setDestination(Queue destination) {
        this.destination = destination;
    }

    public void send(Message mo) {
        template.convertAndSend(this.destination, mo);
    }
}
