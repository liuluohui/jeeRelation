package com.uap.web.rabbit.pub.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/6/19.
 */
public class PublishProvider {

    @Autowired
    private AmqpTemplate publishTemplate;

    public void sendMsg(Object obj) {
        publishTemplate.convertAndSend(obj);
    }
}
