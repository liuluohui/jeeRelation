package solr;

import com.jee.solr.query.SimpleShardQuery;
import com.jee.solr.repositories.ProductReposity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.util.ClassUtils;

/**
 * Created by Administrator on 2015/7/25.
 */
@ContextConfiguration(locations = "/applicationContext-solr.xml")
public class TestSolr extends AbstractJUnit4SpringContextTests {

    @Autowired
    private ProductReposity productReposity;

    @Test
    public void testQueyr(){
        System.out.println(productReposity.queryProductByTitle("aaa"));
    }

    @Test
    public void testAssginForm(){
        System.out.println(ClassUtils.isAssignable(SimpleShardQuery.class, SimpleShardQuery.class));
    }

}
