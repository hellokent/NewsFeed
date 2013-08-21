package com.example.newsfeeds.net.renren;

import com.example.newsfeeds.net.renren.base.CommonParams;
import com.example.newsfeeds.utils.Utils;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-17 上午12:44
 */
public class UserInfoParam extends CommonParams {
    public static final int GENDER = 0x1;//获取被查看用户的性别信息
    public static final int BIRTH = 0x2;//获取被查看用户的生日信息
    public static final int HOMETOWN = 0x4;//获取被查看用户的个人爱好
    public static final int PERSON = 0x8;//获取被查看用户的个人爱好
    public static final int UNIVERSITY = 0x10;//获取被查看用户的大学信息列表
    public static final int WORKPLACE = 0x40;//获取被查看用户的公司信息列表
    public static final int ONLINE = 0x80;//获取被查看用户是否在线
    public static final int FILL_STAGE = 0x100;//获取被查看用户是否需要完善资料
    public static final int USER_STATUS = 0x200;//获取被查看用户帐号当前状态 0:激活；3:未激活；6:封禁；7:自杀；10 只有用户名和密码；
    public static final int IS_FOCUES = 0x400;// 	获取被查看用户与查看用户是否为特别关注好友。1=是，0=不是
    public static final int HEAD_S = 0x800;// 	获取被查看用户个人头像 （100*100）
    public static final int HEAD_M = 0x1000;//获取被查看用户个人头像 （200*200）
    public static final int HEAD_L = 0x2000;//获取被查看用户定宽720px的图片
    public static final int PREFIX = 0x4000;// 	短域名
    public static final int ALL = GENDER | BIRTH | HOMETOWN | PERSON | UNIVERSITY | WORKPLACE | ONLINE
            | FILL_STAGE | USER_STATUS | HEAD_S | HEAD_M | HEAD_L | PREFIX;

    public UserInfoParam(String[] uids, int type){
        this(Utils.join(",", uids), type);
    }
    public UserInfoParam(String uid, int type){
        put("uids", uid);
        put("type", type);
    }
}
