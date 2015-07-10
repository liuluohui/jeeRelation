package ShareRedisTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2015/7/9.
 */
@ContextConfiguration(locations = "/spring-redis.xml")
public class TestShare extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ShardedJedisPool pool;


    @Test
    public void test() {

        // 从池中获取一个Jedis对象
        ShardedJedis jedis = pool.getResource();
        String keys = "name";
        String value = "snowolf";
        // 删数据
        jedis.del(keys);
        // 存数据
        jedis.set(keys, value);
        // 取数据
        String v = jedis.get(keys);

        System.out.println(v);

        // 释放对象池
        pool.returnResource(jedis);

        assertEquals(value, v);
    }
}
