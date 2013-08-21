package com.example.newsfeeds.net.renren.getfriends;

import com.example.newsfeeds.net.renren.base.BaseModel;

import java.util.ArrayList;

/**
 * Created by demor on 13-6-28.
 */
public class GetFriendsModel extends BaseModel {
    public int count;
    public ArrayList<FriendItem> friend_list;

    @Override
    public String toString() {
        return "GetFriendsModel{" +
                "count=" + count +
                ", friend_list=" + friend_list +
                '}';
    }
}
