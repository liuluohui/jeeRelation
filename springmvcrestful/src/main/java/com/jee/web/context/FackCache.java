package com.jee.web.context;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Administrator on 2015/6/6.
 */
public class FackCache {

    private static final Map<String, String> cache = new ConcurrentHashMap<String, String>();

    public static void addToken(String appId, String token) {
        cache.put(appId, token);

    }

    public static void getToken(String appId) {
        String value = cache.get(appId);

//        concurrent get and put
//        if (value == null) {
//            synchronized (cache) {
//                value = cache.get(appId);
//                if (value == null) {
//                    value = load(key);
//                    cache.put(key, value);
//                }
//            }
//        }
    }


    public static void removeToken(String appId, String token) {
        cache.remove(appId);
    }


}
