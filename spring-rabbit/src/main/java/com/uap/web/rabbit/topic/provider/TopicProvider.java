package com.uap.web.rabbit.topic.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/6/19.
 */
public class TopicProvider {

    @Autowired
    private AmqpTemplate topicTemplate;

    public void sendMsg(String routingKey, Object obj) {
        topicTemplate.convertAndSend(routingKey, obj);
    }
}
