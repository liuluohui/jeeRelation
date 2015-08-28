package com.zxs.security.digest.md;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * java          实现         MD2 MD5
 * Bouncy Castle 实现         MD4
 * <p/>
 * Created by Administrator on 2015/8/27 0027.
 */
public class MDUtils {

    public static byte[] md5(String data) {
        return DigestUtils.md5(data);
    }

    public static String md5Hex(String data) {
        return DigestUtils.md5Hex(data);
    }

    public static String md5InputStream(InputStream in) throws IOException {
        return DigestUtils.md5Hex(in);
    }

    public static byte[] md2(String data) {
        return DigestUtils.md2(data);
    }

    public static String md2Hex(String data) {
        return DigestUtils.md2Hex(data);
    }

    public static String md2InputStream(InputStream in) throws IOException {
        return DigestUtils.md2Hex(in);
    }

    public static byte[] md4(String data) {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest md = getMD("MD4");
        md.update(StringUtils.getBytesUtf8(data));
        return md.digest();
    }

    public static String md4Hex(String data) {
        Security.addProvider(new BouncyCastleProvider());
        MessageDigest md = getMD("MD4");
        md.update(StringUtils.getBytesUtf8(data));
        return Hex.encodeHexString(md.digest());
    }

    public static String md4InputStream(InputStream in) throws IOException {
        DigestInputStream digestIn = null;
        try {
            Security.addProvider(new BouncyCastleProvider());
            MessageDigest md = getMD("MD4");
            digestIn = new DigestInputStream(in, md);
            byte[] data = new byte[in.available()];
            digestIn.read(data, 0, data.length);
            return Hex.encodeHexString(digestIn.getMessageDigest().digest());
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    private static MessageDigest getMD(String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("MD4", e);
        }
    }


}
