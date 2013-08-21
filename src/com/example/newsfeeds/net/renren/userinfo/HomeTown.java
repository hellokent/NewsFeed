package com.example.newsfeeds.net.renren.userinfo;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-17 上午1:05
 */
public class HomeTown {
    String province;
    String city;
    String city_code;

    @Override
    public String toString() {
        return "HomeTown{" +
                "province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", city_code='" + city_code + '\'' +
                '}';
    }
}
