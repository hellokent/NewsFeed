package com.example.newsfeeds.utils;

import android.app.Dialog;
import android.widget.Toast;
import com.example.newsfeeds.GlobalApplication;
import com.example.newsfeeds.net.renren.base.BaseParams;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * User: yang-chen@renren-inc.com
 * Date: 13-6-16 下午5:17
 */
public class Utils {

    public static final Gson GSON = new GsonBuilder()
            .create();

    public static String toMD5(String s) {
        if (s != null) {
            try {
                byte[] bs = s.getBytes("UTF-8");
                return encrypt(bs);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private synchronized static String encrypt(byte[] obj) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(obj);
            byte[] bs = md5.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bs) {
                sb.append(Integer.toHexString((0x000000ff & b) | 0xffffff00).substring(6));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static byte[] toByteArray(InputStream input) {
        if (input == null) {
            return null;
        }
        ByteArrayOutputStream output = null;
        byte[] result = null;
        try {
            output = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024 * 100];
            int n = 0;
            while (-1 != (n = input.read(buffer))) {
                output.write(buffer, 0, n);
            }
            result = output.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        } finally {
            closeQuietly(input);
            closeQuietly(output);
        }
        return result;
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a string containing the tokens joined by delimiters.
     * @param tokens an array objects to be joined. Strings will be formed from
     *     the objects by calling object.toString().
     */
    public static String join(CharSequence delimiter, Object[] tokens) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (Object token: tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(token);
        }
        return sb.toString();
    }

    /**
     * Returns a string containing the tokens joined by delimiters.
     * @param tokens an array objects to be joined. Strings will be formed from
     *     the objects by calling object.toString().
     */
    public static String join(CharSequence delimiter, Iterable tokens) {
        StringBuilder sb = new StringBuilder();
        boolean firstTime = true;
        for (Object token: tokens) {
            if (firstTime) {
                firstTime = false;
            } else {
                sb.append(delimiter);
            }
            sb.append(token);
        }
        return sb.toString();
    }

    public static String getSignature(BaseParams paramMap, String secret) {
        StringBuilder buffer = new StringBuilder();
        //1、参数格式化
        for(Map.Entry<String,String> param: paramMap.entrySet()){
            final String value = param.getValue();
            buffer.append(param.getKey())
                    .append("=")
                    .append(value.substring(0, Math.min(50, value.length())));
        }
        //2、追加script key
        buffer.append(secret);
        //3、将拼好的字符串转成MD5值
        return Utils.toMD5(buffer.toString());
    }

	public static void toast(final int strId, final Object... arg){
		toast(GlobalApplication.getApplication().getString(strId, arg));
	}

	public static void toast(final String msg, final Object... arg){
		GlobalApplication.getUiIHandler().post(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(
						GlobalApplication.getApplication(),
						String.format(msg, arg), Toast.LENGTH_SHORT
				).show();
			}
		});
	}

	public static void dismissDialog(final Dialog dialog){
		if (dialog.isShowing()){
			dialog.dismiss();
		}
	}

}
