package com.jee.esapi;

import com.jee.esapi.codec.EncryptorUtils;
import com.jee.esapi.exception.EncryptException;
import org.junit.Assert;
import org.junit.Test;
import org.owasp.esapi.errors.EncryptionException;
import org.owasp.esapi.reference.crypto.JavaEncryptor;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Created by Administrator on 2015/8/14.
 */
public class TestEncryptor {

    @Test
    public void init() throws Exception {
        JavaEncryptor.main(new String[]{});
    }


    @Test
    public void testEncryWithMasterKey() throws EncryptionException, IOException, EncryptException {
        String text = "abcdefg";
        String encrypText = EncryptorUtils.encrypt(text);
        System.out.println(encrypText);
        Assert.assertEquals(text, EncryptorUtils.decrypt(encrypText));
    }

    @Test
    public void testTwiceDecrypt() throws IOException, EncryptException {
        Assert.assertEquals("abcdefg", EncryptorUtils.decrypt("ETMsDgAAAU8sD2LVABRBRVMvQ0JDL1BLQ1M1UGFkZGluZwCAABAAEB7ThDYplrFbWP0VdXTBCxQAAAAQVsbSrxbh73ypbc1X/9DP/AAU3bMJYVDBjj1Qu17E779vJhKlmNw="));
    }

    @Test
    public void testEncryWithCustomKey() throws EncryptionException, IOException, NoSuchAlgorithmException, EncryptException {
        String text = "abcdefg";
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128, new SecureRandom("abcdefghigklmn".getBytes()));
        SecretKey secretKey = kgen.generateKey();
        String encrypText = EncryptorUtils.encrypt(secretKey, text);
        Assert.assertEquals(text, EncryptorUtils.decrypt(secretKey, encrypText));
    }

    @Test
    public void testSign() throws EncryptException {
        String text = "abcdefg";
        String sign = EncryptorUtils.sign(text);
        System.out.println(sign);
        Assert.assertTrue(EncryptorUtils.verifySignature(sign, "abcdefg"));
    }

    @Test
    public void testTwiceVerify() {
        Assert.assertTrue(EncryptorUtils.verifySignature("MCwCFAfZku7vmx35iWMOye8PF/M/ADKlAhQO1iEZYtNrYQ+4jljDS6QVJvzyRQ==", "abcdefg"));
    }

}
