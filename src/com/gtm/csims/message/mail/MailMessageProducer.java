package com.gtm.csims.message.mail;

import javax.jms.Queue;

import org.springframework.jms.core.JmsTemplate;

import com.gtm.csims.message.Message;
import com.gtm.csims.message.MessageSender;

/**
 * 系统邮件消息消费者
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class MailMessageProducer implements MessageSender {
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
