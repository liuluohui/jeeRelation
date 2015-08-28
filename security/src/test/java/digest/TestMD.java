package digest;

import com.zxs.security.digest.md.MDUtils;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

/**
 * Created by Administrator on 2015/8/27 0027.
 */
public class TestMD {

    @Test
    public void testMD4() throws IOException {
        String str = "MD4 测试";
        System.out.println(str);
        String md4Hex = MDUtils.md4Hex(str);
        String md4InputStream = MDUtils.md4InputStream(new ByteArrayInputStream(StringUtils.getBytesUtf8(str)));
        System.out.println("md4Hex:" + MDUtils.md4Hex(str));
        System.out.println("md4InputStream:" + md4InputStream);
        assertEquals(md4Hex, md4InputStream);
    }

}
