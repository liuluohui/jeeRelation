package com.uap.web.rabbit.rpc.client;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Date;

/**
 * Created by Administrator on 2015/6/19.
 */
@ContextConfiguration(locations = {"/rpc/applicationContext-client.xml"})
public class ClientTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private RpcClient client;

    @Test
    public void testRpc() {
        String reply = client.sendMsg("rpcExchange", "rpc", "Hello!" + new Date());
        System.out.println(reply);
        Assert.assertEquals("i got u", reply);
    }

}
