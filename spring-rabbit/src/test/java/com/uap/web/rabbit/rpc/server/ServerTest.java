package com.uap.web.rabbit.rpc.server;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by Administrator on 2015/6/19.
 */
@ContextConfiguration(locations = {"/rpc/applicationContext-server.xml"})
public class ServerTest extends AbstractJUnit4SpringContextTests {

    @Test
    public void start() {
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
