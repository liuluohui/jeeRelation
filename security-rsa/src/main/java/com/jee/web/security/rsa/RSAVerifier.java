package com.jee.web.security.rsa;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

import com.jee.web.security.exception.SecurityException;
import com.jee.web.security.api.Verifier;

public class RSAVerifier extends RSACryptor implements Verifier{

	public RSAVerifier(byte[] publicKey) throws SecurityException {
		super(RSAKeyType.PUBLIC, publicKey);
	}

	@Override
	public boolean verify(byte[] data, byte[] sign) throws SecurityException {
		try {
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify((PublicKey) getKey());
			signature.update(data);
			// 验证签名是否正常
			return signature.verify(sign);
		} catch (InvalidKeyException e) {
			throw new SecurityException(e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			throw new SecurityException(e.getMessage(), e);
		} catch (SignatureException e) {
			throw new SecurityException(e.getMessage(), e);
		}
	}

}
