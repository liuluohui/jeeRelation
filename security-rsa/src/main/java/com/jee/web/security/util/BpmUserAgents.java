package com.jee.web.security.util;

public abstract class BpmUserAgents {

	public static final String SDK_VERSION = "1.0.0";

	public static final String SDK_LANGUAGE_JAVA = "java";
	public static final String SDK_LANGUAGE_NET = "net";

	public static final String SDK_AGENT_NAME_BASE = "BPM_SDK";

	public static final String SDK_AGENT_JAVA = concat(SDK_AGENT_NAME_BASE,
			SDK_LANGUAGE_JAVA, SDK_VERSION);
	
	public static final String SDK_AGENT_NET = concat(SDK_AGENT_NAME_BASE,
			SDK_LANGUAGE_NET, SDK_VERSION);

	private static String concat(String... strings) {
		StringBuilder retn = new StringBuilder(strings[0]);
		for (int i = 1; i < strings.length; i++) {
			retn.append("_").append(strings[i]);
		}
		return retn.toString();
	}
}
