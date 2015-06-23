package com.uap.web.rabbit.topic.provider;

import com.uap.web.rabbit.routing.provider.RouteProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Date;

/**
 * Created by Administrator on 2015/6/19.
 */
@ContextConfiguration(locations = "/topic/applicationContext-provider.xml")
public class TestProvider extends AbstractJUnit4SpringContextTests {

    @Autowired
    private TopicProvider provider;

    @Test
    public void testSendMsg() {
        provider.sendMsg("quick.orange.rabbit", "patten is quick.orange.rabbit ");
        provider.sendMsg("quick.orange.fox", "patten is  quick.orange.fox ");
        provider.sendMsg("lazy.brown.fox", "patten is  lazy.brown.fox ");
        provider.sendMsg("lazy.pink.rabbit", "patten is  lazy.pink.rabbit ");
        provider.sendMsg("quick.brown.fox", "patten is quick.brown.fox ");
        provider.sendMsg("quick.brown.fox", "patten is quick.brown.fox ");
        provider.sendMsg("lazy", "patten is lazy");
    }

}
