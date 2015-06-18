package com.jee.web.jms;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/6/15.
 */
@Service
public class JmsProvider {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired()
    private AmqpTemplate publicTemplate;

    public void sendDataToCrQueue(Object obj) {
        amqpTemplate.convertAndSend("queue_one_key", obj);
        System.out.println("send msg..");
    }

    public void sendPublic(Object obj) {
        publicTemplate.convertAndSend(obj);
    }

}
