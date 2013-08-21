package com.example.newsfeeds.net.renren.userinfo;

import java.util.ArrayList;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-17 上午1:02
 */
public class UserInfoItem {
    public String gender;
    public int is_online;
    public int fill_stage;
    public int user_status;
    public int is_focues_friend;
    public String head_url;
    public String main_url;
    public String large_url;
    public int uid;
    public String user_name;
    public String prefix_url;
    public Birth birth;
    public HomeTown hometown_info;
    public PersonInfo persion_info;
    public ArrayList<University> university_list;
    public Contact contact;
    public ArrayList<Workplace> workplace_list = new ArrayList<Workplace>();

    @Override
    public String toString() {
        return "UserInfoItem{" +
                "gender='" + gender + '\'' +
                ", is_online=" + is_online +
                ", fill_stage=" + fill_stage +
                ", user_status=" + user_status +
                ", is_focues_friend=" + is_focues_friend +
                ", head_url='" + head_url + '\'' +
                ", main_url='" + main_url + '\'' +
                ", large_url='" + large_url + '\'' +
                ", uid=" + uid +
                ", user_name='" + user_name + '\'' +
                ", prefix_url='" + prefix_url + '\'' +
                ", birth=" + birth +
                ", hometown_info=" + hometown_info +
                ", persion_info=" + persion_info +
                ", university_info=" + university_list +
                ", contact=" + contact +
                ", workplace_list=" + workplace_list +
                '}';
    }
}
