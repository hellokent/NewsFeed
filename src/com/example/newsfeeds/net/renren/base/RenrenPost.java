package com.example.newsfeeds.net.renren.base;

import com.example.newsfeeds.utils.L;
import com.example.newsfeeds.utils.Utils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-16 下午11:14
 */
public class RenrenPost extends HttpPost {
    public static final String MCS = "http://api.m.renren.com/api/";
    public static String sSecretKey = BaseParams.SECRET_KEY;

    public RenrenPost(String interfaceName){
        super(MCS + interfaceName);
        addHeader("Connection", "keep-alive");
        addHeader("Accept", "*/*");
        addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        removeHeaders(HTTP.EXPECT_DIRECTIVE);
    }

    public void setArgs(BaseParams params){
        try {
            setEntity(getEntity(params));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static StringEntity getEntity(BaseParams obj) throws UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : obj.entrySet()) {
            builder.append(entry.getKey()).
                    append('=').
                    append(URLEncoder.encode(entry.getValue(), "UTF-8")).
                    append('&');
        }
        builder.append("sig=")
                .append(Utils.getSignature(obj, sSecretKey));
	    L.v("get request %s", builder.toString());
	    return new StringEntity(builder.toString());
    }

}
