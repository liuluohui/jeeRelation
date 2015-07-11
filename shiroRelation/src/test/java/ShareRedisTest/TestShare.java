package ShareRedisTest;

import com.jee.shiro.sessionDAO.RedisSessionDAO;
import org.apache.shiro.session.mgt.SimpleSession;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Created by Administrator on 2015/7/9.
 */
@ContextConfiguration(locations = {"/applicationContext.xml", "/spring-redis.xml"})
public class TestShare extends AbstractJUnit4SpringContextTests {

    @Autowired
    private RedisSessionDAO sessionDAO;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void testSave() {
//        sessionDAO.create(new SimpleSession());
//        redisTemplate.boundValueOps("a").set("v");
        System.out.println(redisTemplate.boundValueOps("a").get());
        System.out.println(sessionDAO.getActiveSessions());
    }



}
