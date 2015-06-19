package com.uap.web.rabbit.dispatch;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by Administrator on 2015/6/19.
 */
@ContextConfiguration(locations = "/dispatch/consumer-prefetch.xml")
public class TestPrefetchConsumer extends AbstractJUnit4SpringContextTests {


    @Test
    public void testStartConsumner() {
        Object lock = new Object();
        try {
            synchronized (lock) {
                lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
