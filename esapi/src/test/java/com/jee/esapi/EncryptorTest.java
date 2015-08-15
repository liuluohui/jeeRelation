package com.jee.esapi;

import com.jee.esapi.codec.EncoderUtils;
import com.jee.esapi.codec.EncryptorUtils;
import com.jee.esapi.exception.EncryptException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;

/**
 * Created by zengxs on 2015/8/15.
 */
public class EncryptorTest {

    private String cipherText;

    private String customCipherText;

    private SecretKey customKey;

    private String plainText = "abcdefg";

    private String salt = "hijklmn";

    private long expiration;

    private String sign;

    private String hash;

    private String seal;

    @Before
    public void init() throws NoSuchAlgorithmException, EncryptException {
        cipherText = EncryptorUtils.encrypt(plainText);
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom());
        customKey = keyGenerator.generateKey();
        customCipherText = EncryptorUtils.encrypt(customKey, plainText);
        sign = EncryptorUtils.sign(plainText);
        hash = EncryptorUtils.hash(plainText, salt);
        expiration = new Date().getTime() + 60000;
        seal = EncryptorUtils.seal(plainText, expiration);
    }


    @Test
    public void testEncryptText() throws IOException, EncryptException {
        Assert.assertNotNull(cipherText);
        Assert.assertEquals(plainText, EncryptorUtils.decrypt(cipherText));
    }

    @Test
    public void testEncryptByte() throws IOException, EncryptException {
        Assert.assertNotNull(cipherText);
        Assert.assertEquals(plainText, EncryptorUtils.decrypt(EncoderUtils.decodeFromBase64(cipherText)));
    }


    @Test
    public void testEncryptCustomText() throws IOException, EncryptException {
        Assert.assertNotNull(customCipherText);
        Assert.assertNotNull(customKey);
        Assert.assertEquals(plainText, EncryptorUtils.decrypt(customKey, customCipherText));
    }

    @Test
    public void testEncryptCustomByte() throws IOException, EncryptException {
        Assert.assertNotNull(customCipherText);
        Assert.assertNotNull(customKey);
        Assert.assertEquals(plainText, EncryptorUtils.decrypt(customKey, EncoderUtils.decodeFromBase64(customCipherText)));
    }


    @Test
    public void testHash() throws EncryptException {
        Assert.assertEquals(hash, EncryptorUtils.hash(plainText, salt));
    }


    @Test
    public void testSign() {
        Assert.assertTrue(EncryptorUtils.verifySignature(sign, plainText));
    }

    @Test
    public void testSeal() throws EncryptException {
        Assert.assertEquals(plainText, EncryptorUtils.unseal(seal));
    }


}
