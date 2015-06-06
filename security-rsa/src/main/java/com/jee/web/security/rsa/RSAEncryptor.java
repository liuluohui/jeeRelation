package com.jee.web.security.rsa;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.jee.web.security.api.Encryptor;
import com.jee.web.security.exception.SecurityException;

/**
 * 基于 RSA 公钥算法的数据加密实现；
 * 
 * 
 * 
 * @author haiq
 *
 */
public class RSAEncryptor extends RSACryptor implements Encryptor {

	public RSAEncryptor(RSAKeyType keyType, byte[] key)
			throws SecurityException {
		super(keyType, key);
	}

	@Override
	public byte[] encrypt(byte[] data) throws SecurityException {
		return encrypt(data, 0, data.length);
	}

	@Override
	public byte[] encrypt(byte[] data, int offset) throws SecurityException {
		return encrypt(data, offset, data.length - offset);
	}

	@Override
	public byte[] encrypt(byte[] data, int offset, int length)
			throws SecurityException {
		try {
			// 对数据加密
			Cipher cipher = Cipher.getInstance(getKeyFactory().getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, getKey());
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
