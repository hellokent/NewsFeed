package com.example.newsfeeds.net.renren.userinfo;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-17 上午1:08
 */
public class Contact {
    public String qq;
    public String msg;
    public String mobile;
    public String homepage;

    @Override
    public String toString() {
        return "Contact{" +
                "qq='" + qq + '\'' +
                ", msg='" + msg + '\'' +
                ", mobile='" + mobile + '\'' +
                ", homepage='" + homepage + '\'' +
                '}';
    }
}
