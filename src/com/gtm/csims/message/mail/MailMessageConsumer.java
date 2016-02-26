package com.gtm.csims.message.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.gtm.csims.log.level.Logger;
import com.gtm.csims.message.Message;

/**
 * 系统邮件消息消费者
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
public class MailMessageConsumer {

    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void consume(Message o) {
        /*
         * User user = processActorDecider.getUser(o.getReceiver()); if (user ==
         * null || user.getEmail() == null || user.getEmail().equals(""))
         * return;
         * 
         * this.sendMimeMail(o, user.getEmail());
         */
    }

    @SuppressWarnings("unused")
    private void sendSimpleMail(Message o, String receiveEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(o.getSender());
        message.setSentDate(o.getCreateDate());
        message.setSubject(o.getTitle());
        message.setText(o.getMessage());
        message.setTo(receiveEmail);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            Logger.debug("Mail [" + receiveEmail + "] is sended failure!");
        }
        Logger.debug("send simple mail:" + o.getMessage() + " :"
                + o.getCreateDate());
    }

    @SuppressWarnings("unused")
    private void sendMimeMail(Message o, String receiveEmail) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(
                    mailMessage, true, "UTF-8");
            messageHelper.setFrom(o.getSender());
            messageHelper.setTo(receiveEmail);
            messageHelper.setSubject(o.getTitle() + "-[" + o.getCategory()
                    + "]");
            messageHelper
                    .setText(
                            o.getMessage()
                                    + "<br/><a href='http://localhost:8088/sweet_pro'>中国银行印章管理系统</a>",
                            true);
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            Logger.debug("Mail [" + receiveEmail + "] is sended failure!");
        }
        Logger.debug("send mime mail:" + o.getMessage() + " :"
                + o.getCreateDate());
    }
}
