package com.example.newsfeeds.net.renren.base;

import android.text.TextUtils;

/**
 * Created by demor on 13-6-28.
 */
public abstract class BaseModel {
    public int error_code = 0;
    public String error_msg;

	public boolean isErrorMsg(){
		return error_code != 0 || !TextUtils.isEmpty(error_msg);
	}

    @Override
    public String toString() {
        return "BaseModel{" +
                "error_code=" + error_code +
                ", error_msg='" + error_msg + '\'' +
                '}';
    }
}
