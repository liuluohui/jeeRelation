package com.uap.web.rabbit.dispatch;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Objects;

/**
 * Created by Administrator on 2015/6/19.
 */
@ContextConfiguration(locations = "/dispatch/applicationContext-consumer.xml")
public class TestConsumer extends AbstractJUnit4SpringContextTests {


    @Test
    public void testStartOneConsumner() {
        Object lock = new Object();
        try {
            synchronized (lock) {
                lock.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testStartAnotherConsumner() {
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
