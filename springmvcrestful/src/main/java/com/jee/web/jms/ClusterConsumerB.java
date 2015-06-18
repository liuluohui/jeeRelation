package com.jee.web.jms;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/6/15.
 */
@Service("clusterConsumerB")
public class ClusterConsumerB implements MessageListener {
    public void onMessage(Message message) {

        System.out.println("ConsumerB receive msg ");
//        System.out.println("ConsumerB receive msg " + new String(message.getBody()));
    }
}
