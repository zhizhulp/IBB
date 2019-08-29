package com.example.ibb.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.ibb.app.MyApp;
import com.example.ibb.entity.User;

/**
 * Created by ASUS on 2018/5/11.
 */

public class AppConfig {
    private static AppConfig appConfig;

    private SharedPreferences preferences;


    private AppConfig() {
        preferences = MyApp.getMyApp().getSharedPreferences("user", Context.MODE_PRIVATE);
    }

    public static AppConfig getInstance() {
        if (appConfig == null)
            synchronized (AppConfig.class) {
                if (appConfig == null)
                    appConfig = new AppConfig();
            }
        return appConfig;
    }

    public void putInt(String key, int value) {
        preferences.edit().putInt(key, value).apply();
    }

    public int getInt(String key, int defValue) {
        return preferences.getInt(key, defValue);
    }

    public void putString(String key, String value) {
        if (value != null && !value.equals(getString(key, null))) {
            preferences.edit().putString(key, value).apply();
        }
    }

    public String getString(String key, String defValue) {
        return preferences.getString(key, defValue);
    }

    public void putBoolean(String key, boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(String key, boolean defValue) {
        return preferences.getBoolean(key, defValue);
    }

    public void putLong(String key, long value) {
        preferences.edit().putLong(key, value).apply();
    }

    public long getLong(String key, long defValue) {
        return preferences.getLong(key, defValue);
    }

    public void clearXml() {
        preferences.edit().clear().apply();
    }
    /**
     * id : 34462860836865
     * nickname : iBB-5653
     * portrait : http://image.ibbtech.cn/image/portrait_default.png
     * signature : null
     * location : 海南省省直辖县级行政单位琼中黎族苗族自治县
     * phoneNumber : 15510115653
     * sex : 2
     * follow : 0
     * follower : 0
     * likes : 0
     * relation : 4
     */
    public void putUser(User user){
        preferences.edit()
                .putLong("id",user.getId())
                .putString("nickname",user.getNickname())
                .putString("portrait",user.getPortrait())
                .putString("signature",user.getSignature())
                .putString("location",user.getLocation())
                .putString("phoneNumber",user.getPhoneNumber())
                .putInt("sex",user.getSex())
                .putInt("follow",user.getFollow())
                .putInt("follower",user.getFollower())
                .putInt("likes",user.getLikes())
                .apply();
    }
}
