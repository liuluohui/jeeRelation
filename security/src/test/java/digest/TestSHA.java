package digest;

import com.zxs.security.digest.sha.SHAUtils;
import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by Administrator on 2015/8/27 0027.
 */
public class TestSHA {

    @Test
    public void testSHA224() {
        String str = "SHA224 测试";
        String digest = SHAUtils.sha224Hex(str);
        System.out.println(digest);
        System.out.println(digest.length());
        Assert.assertEquals(SHAUtils.sha224(str).length,224/8);
    }
}
