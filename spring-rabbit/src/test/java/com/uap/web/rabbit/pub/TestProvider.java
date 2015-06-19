package com.uap.web.rabbit.pub;

import com.uap.web.rabbit.pub.provider.PublishProvider;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Date;

/**
 * Created by Administrator on 2015/6/19.
 */
@ContextConfiguration(locations = {"/publish-subscribe/applicationContext-provider.xml"})
public class TestProvider extends AbstractJUnit4SpringContextTests {

    @Autowired
    private PublishProvider provider;

    @Test
    public void testPublish() {
        provider.sendMsg("Hello," + new Date());
    }

}
