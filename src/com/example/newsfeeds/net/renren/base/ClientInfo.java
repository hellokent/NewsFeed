package com.example.newsfeeds.net.renren.base;

import android.os.Build;
import com.example.newsfeeds.Config;
import com.example.newsfeeds.utils.Utils;

import java.util.Random;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-16 下午10:57
 */
public final class ClientInfo {
    final String screen = "800*480";
    final String os = "17_4.2.2";
    final String model = Build.MODEL;
    final String other = "46000,,";
    final Long uniqid = new Random().nextLong();
    final String from = Config.RENREN_FROM;
    final String version = "5.9.4";
    final String mac;

    public static final String JSON_STRING = Utils.GSON.toJson(new ClientInfo());
    private ClientInfo(){
        String[] macs = new String[6];
        Random random = new Random();
        for (int i = 0; i < 6; ++i){
            macs[i] = Integer.toHexString(random.nextInt(255));
        }
        mac = Utils.join(":", macs);
    }
}
