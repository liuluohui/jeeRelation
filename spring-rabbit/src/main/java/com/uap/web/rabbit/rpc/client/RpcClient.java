package com.uap.web.rabbit.rpc.client;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Address;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/6/19.
 */
public class RpcClient {

    @Autowired
    private AmqpTemplate rpcTemplate;


    public <T> T sendMsg(final String exchangeName, final String routingKey, Object obj) {
        return (T) rpcTemplate.convertSendAndReceive(routingKey, obj);
    }
}
