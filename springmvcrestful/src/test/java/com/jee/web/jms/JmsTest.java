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
        byte[] data =createData(1);
        while (true) {
            provider.sendDataToCrQueue(data);
        }

    }


    @Test
    public void testSendMsg1K() {
        byte[] data = createData(1024);
        while (true) {
            provider.sendDataToCrQueue(data);
        }

    }

    @Test
    public void testSendMsg256K() {
        byte[] data = createData(1024*256);
        while (true) {
            provider.sendDataToCrQueue(data);
        }

    }

    @Test
    public void testSendMsg10K() {
        byte[] data = createData(10240);
        while (true) {
            provider.sendDataToCrQueue(data);
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

    private byte[] createData(int size) {
        byte[] data = new byte[size];
        for (int i = 0; i < data.length; i++) {
            data[i] = (byte) i;
        }
        return data;
    }

}
