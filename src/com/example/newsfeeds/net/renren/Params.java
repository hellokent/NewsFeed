package com.example.newsfeeds.net.renren;

import com.example.newsfeeds.net.renren.base.ClientInfo;

import java.util.TreeMap;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-16 下午10:47
 */
public class Params extends TreeMap<String, Object> {
    {
        put("api_key", API_KEY);
        put("v", "1.0");
        put("call_id", System.currentTimeMillis());
        put("format", "JSON");
        put("gz", "compression");
        put("client_info", ClientInfo.JSON_STRING);
    }

    public static final String SECRET_KEY = "caa335608f8340d1be2a4d4a6b244da0";
	public static final String API_KEY = "5f58df0c570f4be7a340e7cf548a8b7d";
}
