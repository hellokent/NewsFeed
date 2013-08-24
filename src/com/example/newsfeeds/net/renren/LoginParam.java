package com.example.newsfeeds.net.renren;

import com.example.newsfeeds.Config;
import com.example.newsfeeds.net.renren.base.BaseParams;
import com.example.newsfeeds.utils.Utils;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-16 下午11:46
 */
public class LoginParam extends BaseParams {
    public LoginParam(String user, String pwd){
        put("isverify", "1");
        put("uniq_id", System.currentTimeMillis());
        put("password", Utils.toMD5(pwd));
        put("user", user);
        put("sig", Utils.getSignature(this, Config.RENREN_SECRET_KEY));
    }
}
