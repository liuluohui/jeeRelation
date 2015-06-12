package com.jee.web.controller;

import com.jee.web.api.entity.AuthInfo;
import com.jee.web.api.entity.AuthResult;
import com.jee.web.context.FackCache;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * Created by Administrator on 2015/6/6.
 */
@Scope("request")
@RestController
public class AuthController {

    //只处理请求的header accept=application/json或者application/xml的数据请求
    @RequestMapping(value = "/auth", method = RequestMethod.POST, headers = {"Accept=application/json,application/xml"})
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResult auth(@RequestBody AuthInfo authInfo) {

        AuthResult result = new AuthResult();
        result.setAppId(authInfo.getAppId());
        result.setToken(UUID.randomUUID().toString());

        //put token to cache
        FackCache.addToken(result.getAppId(), result.getToken());

        return result;
    }

}
