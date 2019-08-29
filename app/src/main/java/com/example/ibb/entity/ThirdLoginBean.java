package com.example.ibb.entity;

/**
 * Created by ASUS on 2018/5/23.
 */

public class ThirdLoginBean {
    private boolean bindPhone;
    private boolean hasPassword;
    private User userinfo;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isBindPhone() {
        return bindPhone;
    }

    public void setBindPhone(boolean bindPhone) {
        this.bindPhone = bindPhone;
    }

    public boolean isHasPassword() {
        return hasPassword;
    }

    public void setHasPassword(boolean hasPassword) {
        this.hasPassword = hasPassword;
    }

    public User getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(User userinfo) {
        this.userinfo = userinfo;
    }
}
