package com.uap.web.rabbit.helloworld.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by Administrator on 2015/6/19.
 */
public class HellowordConsumer implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(HellowordConsumer.class);

    public void onMessage(Message message) {
        logger.info("receive msg {}", new String(message.getBody()));
    }
}
