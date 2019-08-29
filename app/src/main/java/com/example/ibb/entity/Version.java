package com.example.ibb.entity;

/**
 * Created by ASUS on 2018/5/31.
 */

public class Version {

    /**
     * id : 1
     * name : ibb
     * version : 1.0.0
     * url : http://oss.ibbtech.cn/android-apk/ibb-1.0.0.apk
     * time : 1527736013000
     * state : 0
     */

    private int id;
    private String name;
    private String version;
    private String url;
    private long time;
    private int state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
