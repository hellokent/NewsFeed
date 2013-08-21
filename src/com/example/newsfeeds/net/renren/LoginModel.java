package com.example.newsfeeds.net.renren;

import com.example.newsfeeds.net.renren.base.BaseModel;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-16 下午11:52
 */
public class LoginModel extends BaseModel {
    public String session_key;
    public String ticket;
    public String uid;
    public String secret_key;
    public String user_name;
    public String head_url;
    public Long now;
    public Integer login_count;

    @Override
    public String toString() {
        return super.toString() + "LoginModel{" +
                "session_key='" + session_key + '\'' +
                ", ticket='" + ticket + '\'' +
                ", uid='" + uid + '\'' +
                ", secret_key='" + secret_key + '\'' +
                ", user_name='" + user_name + '\'' +
                ", head_url='" + head_url + '\'' +
                ", now=" + now +
                ", login_count=" + login_count +
                '}';
    }
}
