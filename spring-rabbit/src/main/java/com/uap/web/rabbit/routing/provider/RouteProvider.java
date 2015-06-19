package com.uap.web.rabbit.routing.provider;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Administrator on 2015/6/19.
 */
public class RouteProvider {

    @Autowired
    private AmqpTemplate routeTemplate;

    public void sendMsg(String routingKey, Object msg) {
        routeTemplate.convertAndSend(routingKey, msg);
    }
}
