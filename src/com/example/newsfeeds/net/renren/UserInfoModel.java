package com.example.newsfeeds.net.renren;

import com.example.newsfeeds.net.renren.base.BaseModel;
import com.example.newsfeeds.net.renren.userinfo.UserInfoItem;

import java.util.ArrayList;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-17 上午1:01
 */
public class UserInfoModel extends BaseModel {
    public int count;
    public ArrayList<UserInfoItem> user_info_list = new ArrayList<UserInfoItem>();

    @Override
    public String toString() {
        return "UserInfoModel{" +
                "count=" + count +
                ", user_info_list=" + user_info_list +
                '}';
    }
}
