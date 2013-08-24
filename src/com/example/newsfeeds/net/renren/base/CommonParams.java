package com.example.newsfeeds.net.renren.base;

import com.example.newsfeeds.AccountType;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-16 下午11:53
 */
public abstract class CommonParams extends BaseParams{
    {
        put("session_key", AccountType.RENREN.getSessionKeyForRenren());
    }
}
