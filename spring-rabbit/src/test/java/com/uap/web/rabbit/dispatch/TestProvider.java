package com.uap.web.rabbit.dispatch;

import com.uap.web.rabbit.helloworld.provider.HelloworldProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Date;

/**
 * Created by Administrator on 2015/6/19.
 */
@ContextConfiguration(locations = "/dispatch/applicationContext-provider.xml")
public class TestProvider extends AbstractJUnit4SpringContextTests {

    @Autowired
    private HelloworldProvider provider;

    @Test
    public void testSend() {
        for (int i = 0; i < 100000; i++) {
            provider.sayHello("Hello" + new Date());
        }
    }

    @Test
    public void testPreferchAck() {
        for (int i = 0; i < 10; i++) {
            provider.sayHello("Hello" + new Date());
        }
    }

}
