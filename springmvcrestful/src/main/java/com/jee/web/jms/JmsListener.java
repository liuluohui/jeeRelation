package com.jee.web.jms;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/6/15.
 */
@Service("queueOneListener")
public class JmsListener implements MessageListener {

    public void onMessage(Message message) {
        System.out.println(" data :" + new String(message.getBody()));
    }
}
