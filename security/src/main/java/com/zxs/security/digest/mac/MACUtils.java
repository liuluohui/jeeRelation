package com.zxs.security.digest.mac;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 算法             摘要长度        实现
 * HmacMD5          128            java
 * HmacSHA1         160            java
 * HmacSHA256       256            java
 * HmacSHA384       384            java
 * HmacSHA512       512            java
 * HmacMD2          128            Bouncy Castle
 * HmacMD4          128            Bouncy Castle
 * HmacMD224        224            Bouncy Castle
 * <p/>
 * Created by Administrator on 2015/8/28 0028.
 */
public class MACUtils {

    public static byte[] initHmacMD5Key() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacMD5");
        SecretKey secretKey = keyGenerator.generateKey();
        return secretKey.getEncoded();
    }

    public static String hmacMD5(byte[] key, byte[] data) throws NoSuchAlgorithmException, InvalidKeyException {
        SecretKey secretKey = new SecretKeySpec(key, "HmacMD5");
        Mac mac = Mac.getInstance(secretKey.getAlgorithm());
        mac.init(secretKey);
        return Hex.encodeHexString(mac.doFinal(data));
    }


}
