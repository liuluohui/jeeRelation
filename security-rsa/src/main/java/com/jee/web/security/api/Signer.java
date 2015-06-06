package com.jee.web.security.api;

import com.jee.web.security.exception.SecurityException;

/**
 * 数据签名接口；
 * 
 * @author haiq
 *
 */
public interface Signer {

	/**
	 * 对数据进行签名；
	 * 
	 * @param data
	 *            要签名的数据；
	 * @return 签名；
	 * @throws SecurityException
	 */
	public byte[] sign(byte[] data) throws SecurityException;

}
