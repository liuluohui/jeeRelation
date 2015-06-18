package com.jee.web.jms;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Date;
import java.util.Objects;

/**
 * Created by Administrator on 2015/6/15.
 */
@ContextConfiguration("/applicationContext-mvc.xml")
public class JmsTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private JmsProvider provider;

    @Test
    public void testSendMsg() {
        String hello = "hello. " + new Date();
        while (true) {
            provider.sendDataToCrQueue(hello);
        }

    }

    @Test
    public void testSendTopicMsg() {
        final byte[] data = new byte[1024];
        for (int j = 0; j < data.length; j++) {
            data[j] = (byte) j;
        }
        for (int i = 0; i < 5; i++) {
            new Thread() {
                public void run() {

                    while (true) {
                        provider.sendPublic(data);
                    }
                }
            }.start();
        }

        Object obj = new Object();
        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @Test
    public void testConsume() {
        Object obj = new Object();
        synchronized (obj) {
            try {
                obj.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


}
