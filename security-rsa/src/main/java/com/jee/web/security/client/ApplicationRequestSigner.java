package com.jee.web.security.client;

import java.io.UnsupportedEncodingException;

import com.jee.web.security.api.ApplicationClientToken;
import com.jee.web.security.constant.ApplicationTokenConsts;
import com.jee.web.security.util.CryptUtils;
import com.jee.web.security.exception.SecurityException;
import com.jee.web.security.util.SecurityProperties;

/**
 * ApplicationRequestSigner 实现用 Token 对请求信息进行签名；
 * 
 * @author haiq
 *
 */
public class ApplicationRequestSigner {
	
	private ApplicationClientToken clientToken;
	
	public ApplicationRequestSigner(ApplicationClientToken clientToken) {
		this.clientToken = clientToken;
	}
	
	/**
	 * 对请求信息进行签名；
	 * 
	 * @param clientType 发起请求的客户端类型；
	 * @param requestPath 请求的服务路径；
	 * @return 签名的 Base64 编码文本；
	 * @throws SecurityException
	 */
	public String sign(String clientType, String requestPath) throws SecurityException{
		SecurityProperties props = new SecurityProperties();
		props.setAppCode(clientToken.code());
		props.setUserAgent(clientType);
		props.setRequestPath(requestPath);
		
		try {
			byte[] origData = props.toString().getBytes(ApplicationTokenConsts.CHARSET);
			byte[] sign = CryptUtils.sign(origData, clientToken.secretKey());
			return CryptUtils.encryptBASE64(sign);
		} catch (UnsupportedEncodingException e) {
			throw new SecurityException(e.getMessage(), e);
		}
		
	}
}
