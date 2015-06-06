package com.jee.web.security.api;

import java.lang.*;

public interface TokenGenerator {

	public ApplicationToken createToken(String appCode, String external) throws com.jee.web.security.exception.SecurityException;

}
