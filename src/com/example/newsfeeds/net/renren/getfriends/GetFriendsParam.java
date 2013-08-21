package com.example.newsfeeds.net.renren.getfriends;

import com.example.newsfeeds.net.renren.base.CommonParams;

/**
 * Created by demor on 13-6-28.
 */
public class GetFriendsParam extends CommonParams {
    public GetFriendsParam(String uid){
        put("page", "1");
        put("page_size", "9999");
        put("hasNetwork", 1);
        put("hasGroup", 1);
        put("hasGender", 1);
        put("hasIsFriend", 1);
        put("uid", uid);
    }
}
