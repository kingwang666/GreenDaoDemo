package com.wang.interviewassistant.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.wang.interviewassistant.model.UserInfo;


/**
 * 用户数据存储类(用户信息处理)
 */
public class UserHelper {

    private static final String PREFERENCE_NAME = "user_info";
    private static final String KEY_LOGIN_USER = "login_user";

//    保存用户信息
    public static void saveUserInfo(Context context, UserInfo userInfo) {

        SharedPreferences pref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        pref.edit().putString(KEY_LOGIN_USER, new Gson().toJson(userInfo)).apply();

    }

//    读取保存的用户信息
    public static UserInfo readUserInfo(Context context) {
        if (context == null) {
            return null;
        }

        SharedPreferences pref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);

        String loginUserJson = pref.getString(KEY_LOGIN_USER, null);
        if (!TextUtils.isEmpty(loginUserJson)) {
            return new Gson().fromJson(loginUserJson, UserInfo.class);
        }

        return null;
    }


    public static boolean isHaveUserInfo(Context context){
        if (context == null) {
            return false;
        }
        SharedPreferences pref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return pref.contains(KEY_LOGIN_USER);
    }


//    清除保存的用户信息
    public static void clearUserInfo(Context context) {
        SharedPreferences pref = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        pref.edit().remove(KEY_LOGIN_USER).apply();
    }

}
