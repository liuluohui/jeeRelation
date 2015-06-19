package com.uap.web.rabbit.topic.consumer;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by Administrator on 2015/6/19.
 */
@ContextConfiguration(locations = {"/topic/applicationContext-consumer.xml"})
public class TestConsumer extends AbstractJUnit4SpringContextTests {

    @Test
    public void testConsumer() {
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
