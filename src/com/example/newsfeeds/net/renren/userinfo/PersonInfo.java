package com.example.newsfeeds.net.renren.userinfo;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-17 上午1:05
 */
public class PersonInfo {
    public String interest;
    public String music;
    public String movie;

    @Override
    public String toString() {
        return "PersonInfo{" +
                "interest='" + interest + '\'' +
                ", music='" + music + '\'' +
                ", movie='" + movie + '\'' +
                '}';
    }
}
