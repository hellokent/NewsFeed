package com.example.newsfeeds.net.renren.getfriends;

/**
 * {
 "user_id": 30663774,
 "user_name": "婧昕",
 "head_url": "http://hdn.xnimg.cn/photos/hdn321/20120627/1505/tiny_3bi6_1a7c0000bba61375.jpg",
 "is_online": 1,
 "is_friend": 1,
 "network": "清华大学,山东省青岛第六中学,千橡互动",
 "group": "",
 "gender": "女生",
 "vip_icon_url": ""
 }
 * Created by demor on 13-6-28.
 */
public class FriendItem {
    public String user_id;
    public String user_name;
    public String head_url;
    public String network;
    public String gender;

    @Override
    public String toString() {
        return "FriendItem{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", head_url='" + head_url + '\'' +
                ", network='" + network + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}
