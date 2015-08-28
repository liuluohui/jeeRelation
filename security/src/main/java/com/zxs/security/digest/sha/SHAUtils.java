package com.zxs.security.digest.sha;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * 算法          摘要长度        实现
 * SHA1         160            java
 * SHA256       256            java
 * SHA384       384            java
 * SHA512       512            java
 * SHA224       224            Bouncy Castle
 * Created by Administrator on 2015/8/27 0027.
 */
public class SHAUtils {

    public static String sha1Hex(String data) {
        return DigestUtils.sha1Hex(data);
    }

    public static String sha256Hex(String data) {
        return DigestUtils.sha256Hex(data);
    }

    public static String sha384Hex(String data) {
        return DigestUtils.sha384Hex(data);
    }

    public String sha512Hex(String data) {
        return DigestUtils.sha512Hex(data);
    }

    public static String sha224Hex(String data) {
        MessageDigest sha224 = getSHA224();
        sha224.update(StringUtils.getBytesUtf8(data));
        return Hex.encodeHexString(sha224.digest());

    }

    public static byte[] sha224(String data) {
        MessageDigest sha224 = getSHA224();
        sha224.update(StringUtils.getBytesUtf8(data));
        return sha224.digest();

    }

    private static MessageDigest getSHA224() {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest sha224 = null;
        try {
            sha224 = MessageDigest.getInstance("SHA224");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("SHA224", e);
        }
        return sha224;
    }

}
