package com.example.newsfeeds.net.renren.base;

import java.util.TreeMap;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-16 下午11:44
 */
public abstract class BaseParams extends TreeMap<String, String> {
    public static final String SECRET_KEY = "xxx";
	public static final String API_KEY = "yyy";
	public static final String FROM = "zzz";

    {
        put("api_key", API_KEY);
        put("v", "1.0");
        put("call_id", System.currentTimeMillis());
        put("format", "JSON");
        put("gz", "compression");
        put("client_info", ClientInfo.JSON_STRING);
    }
    public void put(String key, Object obj){
        super.put(key, obj.toString());
    }
}
