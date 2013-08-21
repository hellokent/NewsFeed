package com.example.newsfeeds.net.renren.userinfo;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-17 上午1:09
 */
public class Workplace {
    int id;
    int workplace_id;
    String workplace_name;
    int join_year;
    int join_month;
    String addrss;
    String description;
    String province;
    int city_id;
    String city_name;
    int quit_year;
    int quit_month;
    int type;
    int jon_title_id;
    int position_id;
    int user_id;

    @Override
    public String toString() {
        return "Workplace{" +
                "id=" + id +
                ", workplace_id=" + workplace_id +
                ", workplace_name='" + workplace_name + '\'' +
                ", join_year=" + join_year +
                ", join_month=" + join_month +
                ", addrss='" + addrss + '\'' +
                ", description='" + description + '\'' +
                ", province='" + province + '\'' +
                ", city_id=" + city_id +
                ", city_name='" + city_name + '\'' +
                ", quit_year=" + quit_year +
                ", quit_month=" + quit_month +
                ", type=" + type +
                ", jon_title_id=" + jon_title_id +
                ", position_id=" + position_id +
                ", user_id=" + user_id +
                '}';
    }
}
