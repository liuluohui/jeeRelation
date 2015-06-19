package com.uap.web.rabbit.routing.provider;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Date;

/**
 * Created by Administrator on 2015/6/19.
 */
@ContextConfiguration(locations = "/routing/applicationContext-provider.xml")
public class TestProvider extends AbstractJUnit4SpringContextTests {

    @Autowired
    private RouteProvider provider;

    @Test
    public void testSendOrange() {
        provider.sendMsg("orange", "hello,color is orange." + new Date());
    }

    @Test
    public void testSendGreen() {
        provider.sendMsg("green", "hello,color is green." + new Date());
    }

    @Test
    public void testSendBlack() {
        provider.sendMsg("black", "hello,color is black." + new Date());
    }

    @Test
    public void testSendRed() {
        //this msg will be drop by direct exchange
        provider.sendMsg("red", "hello,color is red." + new Date());
    }
}
