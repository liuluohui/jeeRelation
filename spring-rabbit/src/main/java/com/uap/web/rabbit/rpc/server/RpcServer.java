package com.uap.web.rabbit.rpc.server;

import com.uap.web.rabbit.rpc.client.RpcClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Address;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/6/19.
 */
public class RpcServer implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    @Autowired
    private AmqpTemplate rpcTemplate;

    public void onMessage(Message message) {
        logger.info("receive msg {}", new String(message.getBody()));
        Address replyTo = message.getMessageProperties().getReplyToAddress();
        rpcTemplate.convertAndSend(replyTo.getExchangeName(), replyTo.getRoutingKey(), "i got u");
    }
}
