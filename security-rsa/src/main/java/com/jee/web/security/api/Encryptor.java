package com.jee.web.security.api;


import com.jee.web.security.exception.SecurityException;

/**
 * Encryptor 定义数据加密接口；
 * 
 * @author haiq
 *
 */
public interface Encryptor {
	
	public byte[] encrypt(byte[] data) throws SecurityException;
	public byte[] encrypt(byte[] data, int offset) throws SecurityException;
	public byte[] encrypt(byte[] data, int offset, int length) throws SecurityException;
	
}
