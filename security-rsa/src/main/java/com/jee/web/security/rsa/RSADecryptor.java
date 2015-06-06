package com.jee.web.security.rsa;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.jee.web.security.api.Decryptor;
import com.jee.web.security.exception.SecurityException;

/**
 * 基于 RSA 公钥算法的数据解密实现；
 * 
 * @author haiq
 *
 */
public class RSADecryptor extends RSACryptor implements Decryptor {

	public RSADecryptor(RSAKeyType keyType, byte[] key)
			throws SecurityException {
		super(keyType, key);
	}

	@Override
	public byte[] decrypt(byte[] data) throws SecurityException {
		return decrypt(data, 0, data.length);
	}

	@Override
	public byte[] decrypt(byte[] data, int offset) throws SecurityException {
		return decrypt(data, offset, data.length - offset);
	}

	@Override
	public byte[] decrypt(byte[] data, int offset, int length) throws SecurityException {
		// 对数据解密
		try {
			Cipher cipher = Cipher.getInstance(getKeyFactory().getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, getKey());

			return cipher.doFinal(data, offset, length);
		} catch (InvalidKeyException e) {
			throw new SecurityException(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			throw new SecurityException(e.getMessage(), e);
		} catch (NoSuchPaddingException e) {
			throw new SecurityException(e.getMessage(), e);
		} catch (IllegalBlockSizeException e) {
			throw new SecurityException(e.getMessage(), e);
		} catch (BadPaddingException e) {
			throw new SecurityException(e.getMessage(), e);
		}
	}

}
