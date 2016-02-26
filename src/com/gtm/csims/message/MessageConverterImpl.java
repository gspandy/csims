package com.gtm.csims.message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.apache.activemq.command.ActiveMQObjectMessage;
import org.apache.activemq.util.ByteArrayInputStream;
import org.apache.activemq.util.ByteArrayOutputStream;

/**
 * 消息格式转化类
 * 
 * @author <a href="mailto:sweet.yangyz@gmail.com">Sweet</a>
 * 
 */
@SuppressWarnings( { "unchecked" })
public class MessageConverterImpl implements
        org.springframework.jms.support.converter.MessageConverter {

    public Message toMessage(Object obj, Session session) throws JMSException {
        // check Type
        if (obj instanceof com.gtm.csims.message.Message) {
            ActiveMQObjectMessage objMsg = (ActiveMQObjectMessage) session
                    .createObjectMessage();
            HashMap map = new HashMap();
            try {
                // net.sweet.message.Message must implements Seralizable
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
                oos.writeObject(obj);
                bos.close();
                map.put("MESSAGE_OBJECT", bos.toByteArray());
                objMsg.setObjectProperty("MAP", map);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return objMsg;
        } else {
            throw new JMSException("Object:[" + obj
                    + "] is not [net.sweet.message.Message]");
        }
    }

    public Object fromMessage(Message msg) throws JMSException {
        if (msg instanceof ObjectMessage) {
            HashMap map = (HashMap) ((ObjectMessage) msg)
                    .getObjectProperty("MAP");
            try {
                // net.sweet.message.Message must implements Seralizable
                ByteArrayInputStream bis = new ByteArrayInputStream(
                        (byte[]) map.get("MESSAGE_OBJECT"));
                ObjectInputStream ois = new ObjectInputStream(bis);
                return ois.readObject();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        } else {
            throw new JMSException("Msg:[" + msg + "] is not Map");
        }
    }
}
