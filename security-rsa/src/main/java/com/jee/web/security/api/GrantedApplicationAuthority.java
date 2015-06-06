package com.jee.web.security.api;

import org.springframework.security.core.GrantedAuthority;

public interface GrantedApplicationAuthority extends GrantedAuthority{
	
	public String getApplicationID();
	
	public String getApplicationCode();
	
	public String getCategoryID();
}
