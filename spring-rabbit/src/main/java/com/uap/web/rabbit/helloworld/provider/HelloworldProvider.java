package com.uap.web.rabbit.helloworld.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/6/19.
 */
public class HelloworldProvider {

    private static final Logger logger = LoggerFactory.getLogger(HelloworldProvider.class);


    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sayHello(Object obj) {
        logger.info("try to send msg [{}] to mq use routing-key hello", obj);
        amqpTemplate.convertAndSend("hello", obj);
    }

}
