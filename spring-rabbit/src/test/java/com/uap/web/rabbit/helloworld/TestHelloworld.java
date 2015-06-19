package com.uap.web.rabbit.helloworld;

import com.uap.web.rabbit.helloworld.provider.HelloworldProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Date;

/**
 * Created by Administrator on 2015/6/19.
 */

@ContextConfiguration(locations = {"/helloworld/applicationContext-provider.xml", "/helloworld/applicationContext-consumer.xml"})
public class TestHelloworld extends AbstractJUnit4SpringContextTests {

    @Autowired
    private HelloworldProvider helloworldProvider;

    @Test
    public void testHelloworld() {
        helloworldProvider.sayHello("Hello," + new Date());

        Object lock = new Object();
        try {
            synchronized (lock){
                lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
