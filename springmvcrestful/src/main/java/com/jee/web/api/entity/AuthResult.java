package com.jee.web.api.entity;

/**
 * Created by Administrator on 2015/6/6.
 */
public class AuthResult {

    private String appId;

    private String token;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthResult{" +
                "appId='" + appId + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
